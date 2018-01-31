
package bank_server;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Customer_table extends Data_db implements Result{
    
     public Customer_table(java.sql.Statement stmt, DBConnection mdbc) {
        super(stmt, mdbc);
    }
     
      @Override
    public ResultSet getResultFromTable() {
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery("SELECT c.customer_id, c.login, c.password, c.surname, c.name, c.lastname, ifnull(c.passport_number,\"0\") passport_number FROM customer c");
        } catch (SQLException e) {
        }
        return rs;
    }
    
    public String getRegistr(String [] data) {
        ResultSet rs = null;
        String result = "success";
        try {
            String query = "SELECT login, password FROM customer where surname=" + quotate(data[1])
                    + " AND name=" + quotate(data[2]) + " AND lastname=" + quotate(data[3]) 
                    + " AND passport_number=" + quotate(data[4]);
            rs = stmt.executeQuery(query);
            while(rs.next()){
                result += "," + rs.getString(1);
                result += "," + rs.getString(2);
            }
            return result;
        } catch (SQLException e) {
            return "fail";
        }
    }

    public String checkLogin(String login, String password) {
        ResultSet rs = getResultFromTable();
        int flag = 0;
        String tableLogin = "";
        String tablePassword = "";
        try {
            while (rs.next()) {
                tableLogin = rs.getString("login");
                tablePassword = rs.getString("password");
                if (tableLogin.equals(login) && tablePassword.equals(password)) {
                    flag = 1;
                    break;
                }
            }
            if (flag == 1) {
                return "success";
            } else {
                return "fail";
            }
        } catch (Exception e) {
            System.out.println("Exception in Table of customer");
            return "";
        } finally {
            mdbc.close(rs);
        }
    }
    
    public String check(String [] data){
        ResultSet rs = getResultFromTable();
        int flag = 0;
        String tableLogin = "";
        String tablePassword = "";
        String tablePassport = "";
        try {
            while (rs.next()) {
                tableLogin = rs.getString("login");
                tablePassword = rs.getString("password");
                tablePassport = rs.getString("passport_number");
                System.out.println(tableLogin);
                System.out.println(tablePassword);
                System.out.println(tablePassport);
                
                if (tableLogin.equals(data[1]) && tablePassword.equals(data[2])) {
                    flag = 1;
                } 
                else if(tablePassport.equals(data[6])){
                    flag = 1;
                }
            }
            if(flag==1){
                    return "fail";
                }
            else return "success";
        } catch (Exception e) {
            System.out.println("Exception in Table of customer");
            return "";
        } finally {
            mdbc.close(rs);
        }
    }
    
    public String addUser(String [] data) {
        try {
            String check = check(data);
            if(check.equals("success")){
            int done = stmt.executeUpdate("INSERT INTO customer (login, password, surname, name, lastname, passport_number) VALUES ("
                    + quotate(data[1]) + ","
                    + quotate(data[2]) + ","
                    + quotate(data[3]) + ","
                    + quotate(data[4]) + ","
                    + quotate(data[5]) + ","
                    + quotate(data[6]) + ")");
            return "success";
            }
            else return "fail";
        } catch (SQLException e) {
            System.err.println("Ошибка ввода данных");
            return "fail";
        }
    }
}
