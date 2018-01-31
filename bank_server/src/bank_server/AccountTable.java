
package bank_server;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AccountTable extends Data_db implements Result {
    
    public AccountTable(java.sql.Statement stmt, DBConnection mdbc) {
        super(stmt, mdbc);
    }
    
    @Override
    public ResultSet getResultFromTable() {
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery("SELECT * FROM account");
        } catch (SQLException e) {
        }
        return rs;
    }
    
    public int getCustomerID(String [] data) {
        ResultSet rs = null;
        String query = "SELECT customer_id from customer WHERE surname ="
                    + quotate(data[7]) + " AND name=" + quotate(data[8])
                    + " AND lastname =" + quotate(data[9])+ " AND passport_number=" + quotate(data[10]);
        try {
            rs = stmt.executeQuery(query);
            while(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException e) {
        }
        return 0;
  }
    
    public int getCustomer_id(String data) {
        ResultSet rs = null;
        String query = "SELECT customer_id from customer WHERE login=" + quotate(data);
        try {
            rs = stmt.executeQuery(query);
            while(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException e) {
        }
        return 0;
  }
    
    public String all(String data) {
        int res = getCustomer_id(data);
        ResultSet rs = null;
        String result = "";
        String query = "SELECT account_number from account WHERE customer_id=" + res;
        try {
            rs = stmt.executeQuery(query);
            result = "success";
            while(rs.next()){
                result += "," + rs.getString(1);
            }
            
            return result;
        } catch (SQLException e) {
        }
        return "fail";
  }
    public String all_p() {
        ResultSet rs = null;
        String result = "";
        String query = "SELECT * from dict_payment";
        try {
            rs = stmt.executeQuery(query);
            result = "success";
            while(rs.next()){
                result += "," + rs.getString(1);
            }
            return result;
        } catch (SQLException e) {
             
        }
        return "fail";
  }
    
    public String Deposit(String data) {
        int res = getCustomer_id(data);
        ResultSet rs = null;
        String result = "";
        String query = "SELECT account_number from account WHERE customer_id=" + res + " AND account_type=0";
        try {
            rs = stmt.executeQuery(query);
            result = "success";
            while(rs.next()){
                result += "," + rs.getString(1);
            }
          
            return result;
        } catch (SQLException e) {
        }
        return "fail";
  }
    
    public String summ(String [] data) {
        ResultSet rs = null;
        String result = "";
        String query = "SELECT summa, currency from account WHERE account_number=" + quotate(data[1]);
        try {
            rs = stmt.executeQuery(query);
            result = "success";
            while(rs.next()){
                result += "," + rs.getFloat(1);
                result += "," + rs.getString(2);
            }
            
            return result;
        } catch (SQLException e) {
        }
        return "fail";
  }
    public String depData(String [] data) {
        ResultSet rs = null;
        String result = "";
        String query = "SELECT summa, currency, date_close from account WHERE account_number=" + quotate(data[1]);
        try {
            rs = stmt.executeQuery(query);
            result = "success";
            while(rs.next()){
                result += "," + rs.getFloat(1);
                result += "," + rs.getString(2);
                result += "," + rs.getDate(3);
            }
            
            return result;
        } catch (SQLException e) {
        }
        return "fail";
  }
    
    public int getCustomerID_search(String [] data) {
        ResultSet rs = null;
        String query = "SELECT customer_id from customer WHERE surname ="
                    + quotate(data[1]) + " AND name=" + quotate(data[2])
                    + " AND lastname =" + quotate(data[3])+ " AND passport_number =" 
                + quotate(data[4]);
        try {
            rs = stmt.executeQuery(query);
            while(rs.next()){
                return rs.getInt(1);
            }
            
        } catch (SQLException e) {
        }
        return 0;
  }
    
    public String search(String [] data) {
        ResultSet rs = null;
        String result = "success";
        int check = getCustomerID_search(data);
        String query = "SELECT account_number, summa, currency from account WHERE customer_id=" + check;
        try {
            rs = stmt.executeQuery(query);
            while(rs.next()){
                result += "," + rs.getString(1) ;
                result += "," + rs.getFloat(2) ;
                result += "," + rs.getString(3) ;
            }
            return result;
        } catch (SQLException e) {
        }
        return "fail";
  }
    
    public String addAccount(String [] data) throws ParseException {
        try {
            int check = getCustomerID(data);
            String format = "%Y.%m.%d";
            String query = "INSERT INTO account (account_number,date_open,date_close, summa, currency, account_type, customer_id) VALUES ("
                    + quotate(data[1]) + ","
                    + "STR_TO_DATE(" + quotate(data[2]) + ", " + quotate(format) + "),"
                    + "STR_TO_DATE(" + quotate("0000.00.00") + ", " + quotate(format) + "),"
                    + Float.valueOf(data[4]) + ","
                    + quotate(data[5]) + ","
                    + Integer.valueOf(data[6]) + ","
                    + check + ")";
            
            if(check > 0){
            int done = stmt.executeUpdate(query);
            return "success";
            }
            else return "fail";
        } catch (SQLException e) {
            System.err.println("Ошибка ввода данных");
            return "fail";
        }
    }
    
    public String plusAccount(String [] data){
        try {
            String result = accountCheck(data);
             String res[] = result.split(",");
             float sum = Float.parseFloat(res[2]);
             sum += Float.valueOf(data[2]);
            String query = "UPDATE account SET summa=" + sum +
                    " where account_number=" + quotate(data[1]);
            System.out.println(query);
           int done = stmt.executeUpdate(query);
           return "success";
        } catch (SQLException e) {
            System.err.println("Ошибка ввода данных");
            return "fail";
        }
    }
    
    public String accountCheck(String [] data){
        String result = "";
        System.out.println(data[1]);
       try {
            String query = "SELECT date_close, currency, summa from account where account_number="
                    + quotate(data[1]);
          
           ResultSet rs = stmt.executeQuery(query);
           result = "success";
            while(rs.next()){
//                result += "," + rs.getString(1);
                result += "," + rs.getString(2);
                result += "," + rs.getString(3);
            }
            
        } catch (SQLException e) {
            System.err.println("Ошибка ввода данных");
            return "fail";
        }
       return result;
    }
    
     public String addDeposit(String [] data) throws ParseException {
        try {
            int check = getCustomerID(data);
            String format = "%Y.%m.%d";
            String query = "INSERT INTO account (account_number,date_open,date_close, summa, currency, account_type, customer_id) VALUES ("
                    + quotate(data[1]) + ","
                    + "STR_TO_DATE(" + quotate(data[2]) + ", " + quotate(format) + "),"
                    + "STR_TO_DATE(" + quotate(data[3]) + ", " + quotate(format) + "),"
                    + Float.valueOf(data[4]) + ","
                    + quotate(data[5]) + ","
                    + Integer.valueOf(data[6]) + ","
                    + check + ")";
            
            if(check > 0){
            int done = stmt.executeUpdate(query);
            return "success";
            }
            else return "fail";
        } catch (SQLException e) {
            System.err.println("Ошибка ввода данных");
            return "fail";
        }
    }
     
     public String perevodCheck(String data){
        String result = "";
       try {
            String query = "SELECT summa from account where account_number="
                    + quotate(data);
           
           ResultSet rs = stmt.executeQuery(query);
           result = "success";
            while(rs.next()){
                result += "," + rs.getFloat(1);
            }
        } catch (SQLException e) {
            System.err.println("Ошибка ввода данных");
            return "fail";
        }
       return result;
    }
     
     public String checkCur(String data){
          try {          
            String query_check = "SELECT currency from account where account_number="
                    + quotate(data);
            
            ResultSet rs = stmt.executeQuery(query_check);
            
            String check = "";
            while(rs.next()){
                check = rs.getString(1);
            }

            return check;
        } catch (SQLException e) {
            System.err.println("Ошибка ввода данных");
            return "fail";
        }
     }
     
      public String perevod(String [] data){
        try {
            String account = perevodCheck(data[1]);
            String account_send = perevodCheck(data[2]);
            String res[] = account.split(",");
            String res_send[] = account_send.split(",");
            float sum = Float.parseFloat(res[1]);
            float sum_send = Float.parseFloat(res_send[1]);
            sum = sum - Float.parseFloat(data[3]);
            sum_send = sum_send + Float.parseFloat(data[3]);
            
            String t = checkCur(data[1]);
            String c = checkCur(data[2]);
            if (t.equals(c)){
            
            String query = "UPDATE account SET summa=" + sum +
                    " where account_number=" + quotate(data[1]);
            
            String query_send = "UPDATE account SET summa=" + sum_send +
                    " where account_number=" + quotate(data[2]);
           
           int done = stmt.executeUpdate(query);
           done = stmt.executeUpdate(query_send);
           return "success";
            }
            else return "fail";

        } catch (SQLException e) {
            System.err.println("Ошибка ввода данных");
            return "fail";
        }

    }
      
      public String serchData(String [] data){
          try {          
            String format = "%Y.%m.%d";
            
            String query = "SELECT c.surname, c.name, c.lastname, a.account_number,a.summa, a.currency, a.date_close "
                    + "FROM account a join customer c on a.customer_id=c.customer_id where account_type=0 AND date_close<=STR_TO_DATE("
                    + quotate(data[1]) + ", " + quotate(format) + ")";
            
            ResultSet rs = stmt.executeQuery(query);
            
            String check = "success";
            while(rs.next()){
                check += "," + rs.getString(1);
                check += "," + rs.getString(2);
                check += "," + rs.getString(3);
                check += "," + rs.getString(4);
                check += "," + rs.getFloat(5);
                check += "," + rs.getString(6);
                check += "," + rs.getDate(7);                
            }
          
            return check;
        } catch (SQLException e) {
            System.err.println("Ошибка ввода данных");
            return "fail";
        }
     }
      
}
