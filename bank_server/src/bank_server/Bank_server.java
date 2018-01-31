package bank_server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;

public class Bank_server {

    public static void main(String[] args) {
        try {
            int counter = 0;
            ServerSocket serverSocket = new ServerSocket(8071);
            System.out.println("Сервер запущен....");
            while (true) {
// ожидание клиента
                Socket socket = serverSocket.accept();
                System.out.println(socket.getInetAddress().getHostName() + " подключен");
                counter++;
                /*
                 создание отдельного потока для обмена данными
                 с соединившимся клиентом
                 */
                System.out.println("Подключен клиент №" + counter);
                ServerThread thread = new ServerThread(socket, counter); // запуск потока
                thread.start();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}

class ServerThread extends Thread {

    private InetAddress addr; // адрес клиента
    private ObjectOutputStream os; // передача
    private ObjectInputStream is; // прием
    private String clientMessage;
    private DBConnection mdbc;
    private java.sql.Statement stmt;
    private int counter;
    private Customer_table customer_table;
    private AccountTable accountTable;
    private CurrencyTable currencyTable;
    private DepositTable depositTable;
    private PaymentTable paymentTable;

    public ServerThread(Socket s, int counter) throws IOException {
        this.counter = counter;

        os = new ObjectOutputStream(s.getOutputStream());//поток вывода
        is = new ObjectInputStream(s.getInputStream());//поток ввода

        addr = s.getInetAddress();
        mdbc = new DBConnection();
        mdbc.init();
        Connection conn = mdbc.getMyConnection();
        try {
            stmt = conn.createStatement();
            
            customer_table = new Customer_table(stmt, mdbc);
            accountTable = new AccountTable(stmt, mdbc);
            currencyTable = new CurrencyTable(stmt, mdbc);
            depositTable = new DepositTable(stmt, mdbc);
            paymentTable = new PaymentTable(stmt, mdbc);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void writeObj(String str) {
        clientMessage = str;
        try {
            os.writeObject(clientMessage);
        } catch (IOException e) {
            System.err.println("ошибка I/О потока" + e);
        }
    }

    public void run() {
        int i = 0;
        String messageToClient = "";
        String str;
        String ThreadStop = "";
        try {
            System.out.println("Сервер ожидает действий от клиента");
            while (!ThreadStop.equals("Exit")) {
                str = (String) is.readObject();
                String messageParts[] = str.split(",");
                switch (messageParts[0]) {
                    case "checkLogin":
                        String UserLogin = messageParts[1];
                        String UserPassword = messageParts[2];
                        messageToClient = customer_table.checkLogin(UserLogin, UserPassword);
                        writeObj(messageToClient);
                        break;
                    case "addUser":
                        messageToClient = customer_table.addUser(messageParts);
                        writeObj(messageToClient);
                        break;
                    case "addAccount":
                        messageToClient = accountTable.addAccount(messageParts);
                        writeObj(messageToClient);
                        break;
                    case "openAccount":
                        messageToClient = accountTable.search(messageParts);
                       
                        writeObj(messageToClient);
                        break;
                    case "showKurs":
                        messageToClient = currencyTable.show();
                        writeObj(messageToClient);
                        break;
                    case "changeKurs":
                        messageToClient = currencyTable.changeKurs(messageParts);
                        writeObj(messageToClient);
                        break;
                    case "searchAccount":
                        messageToClient = accountTable.search(messageParts);
                        writeObj(messageToClient);
                        break;
                    case "allDeposit":
                        messageToClient = depositTable.getResult();
                        writeObj(messageToClient);
                        break;
                    case "openDeposit":
                        messageToClient = accountTable.addDeposit(messageParts);
                        writeObj(messageToClient);
                        break;
                    case "accountCheck":
                        messageToClient = accountTable.accountCheck(messageParts);
                        writeObj(messageToClient);
                        break;
                    case "plusAccount":
                        messageToClient = accountTable.plusAccount(messageParts);
                        writeObj(messageToClient);
                        break;
                    case "myAccounts":
                        String id = messageParts[1];
                        messageToClient = accountTable.all(id);
                        writeObj(messageToClient);
                        break;
                    case "summAccount":
                        messageToClient = accountTable.summ(messageParts);
                        writeObj(messageToClient);
                        break;
                    case "myDeposit":
                        messageToClient = accountTable.Deposit(messageParts[1]);
                        writeObj(messageToClient);
                        break;
                    case "depData":
                        messageToClient = accountTable.depData(messageParts);
                        writeObj(messageToClient);
                        break;
                    case "perevod":
                        messageToClient = accountTable.perevod(messageParts);
                        writeObj(messageToClient);
                        break;
                    case "dictDeposit":
                        messageToClient = depositTable.getCurrency();
                        writeObj(messageToClient);
                        break;
                    case "chooseDep":
                        messageToClient = depositTable.getDeposit(messageParts);
                        writeObj(messageToClient);
                        break;
                   case "allRegistDeposit":
                        messageToClient = depositTable.getAllDeposit();
                        writeObj(messageToClient);
                        break;
                    case "searchRegistr":
                        messageToClient = customer_table.getRegistr(messageParts);
                        writeObj(messageToClient);
                        break;
                    case "searchData":
                        messageToClient = accountTable.serchData(messageParts);
                        writeObj(messageToClient);
                        break;
                    case "payments":
                        messageToClient = accountTable.all_p();
                        writeObj(messageToClient);
                        break;
                    case "pay":
                        messageToClient = paymentTable.getResult(messageParts);
                        writeObj(messageToClient);
                        break;   
                    case "myPayments":
                        messageToClient = paymentTable.allPayments(messageParts[1]);
                        writeObj(messageToClient);
                        break;
                    case "group":
                        messageToClient = paymentTable.group(messageParts[1]);
                        System.out.println(messageToClient);
                        writeObj(messageToClient);
                        break;  
                    case "Exit":
                        ThreadStop = "Exit";
                        break;
                    default:
                        System.err.println("Неизвестная команда");
                }
            }
        } catch (Exception e) {
            System.err.println("Соединение закрыто");
        } finally {
            disconnect(); // уничтожение потока
        }
    }

    public void disconnect() {
        try {
            if (os != null) {
                os.close();
            }
            if (is != null) {
                is.close();
            }
            System.out.println(addr.getHostName() + " Закрытие соединения " + counter + "го клиента");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            this.interrupt();
        }
    }
}