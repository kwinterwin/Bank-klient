
package bank_server;

import java.sql.ResultSet;
import java.sql.SQLException;


public class PaymentTable extends Data_db implements Result{
    
    public PaymentTable(java.sql.Statement stmt, DBConnection mdbc) {
        super(stmt, mdbc);
    }
    
    @Override
    public ResultSet getResultFromTable() {
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery("SELECT * FROM dict_payment");
        } catch (SQLException e) {
        }
        return rs;
    }
    
    public String accID(String data){
        ResultSet rs = null;
        String result = "";
        try {
            rs = stmt.executeQuery("SELECT account_id FROM account where account_number=" + quotate(data));
            while(rs.next()){
                result += rs.getInt(1);
            }
        } catch (SQLException e) {
        }
        return result;
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
    
    public String allPayments(String data){
        ResultSet rs = null;
        String result = "success";
        String query = "select p.date_payment, p.reseiver_account, p.summa,p.currency,p.payment_name,a.account_number,p.card_number from payments p" + 
                " join account a on a.account_id = p.account_id where a.customer_id=" + 
                "(select customer_id from customer where login=" + quotate(data) + ")";
        
        try {
            rs = stmt.executeQuery(query);
            while(rs.next()){
                result += "," + rs.getDate(1);
                result += "," + rs.getString(2);
                result += "," + rs.getFloat(3);
                result += "," + rs.getString(4);
                result += "," + rs.getString(5);
                result += "," + rs.getString(6);
                result += "," + rs.getString(7);    
            }
           
        } catch (SQLException e) {
             return "fail";
        }
        return result;
    }
    
    public String group(String data){
        ResultSet rs = null;
        String result = "success";
        String query = "select sum(p.summa),p.payment_name from payments p" + 
                " join account a on a.account_id = p.account_id where a.customer_id=" + 
                "(select customer_id from customer where login=" + quotate(data) + ") group by p.payment_name";
       System.out.println(query);
        try {
            rs = stmt.executeQuery(query);
            while(rs.next()){
                result += "," + rs.getFloat(1);
                result += "," + rs.getString(2);
            }
           
        } catch (SQLException e) {
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

    public String getResult(String [] data) {
        ResultSet rs = null;
        String res = accID(data[7]);
      
        float summa = 0.0f;
        String t = checkCur(data[7]);
        String c = checkCur(data[8]);
        try{
        String q = "Select summa from account where account_id=" + res;
        rs = stmt.executeQuery(q);
        while(rs.next()){
            summa = rs.getFloat(1);
        }
        } catch(SQLException e){}
        
        if(summa>=Float.valueOf(data[5]) || (t.equals(c) && data[3].equals("NULL"))){
        String format = "%Y.%m.%d";
        String query = "INSERT INTO payments (date_payment,bank_code, reseiver_account, currency, summa, payment_name,account_id,card_number) VALUES ("
                    + "STR_TO_DATE(" + quotate(data[1]) + ", " + quotate(format) + "),"
                    + quotate(data[2]) + ","
                    + quotate(data[3]) + ","
                    + quotate(data[4]) + ","
                    + Float.valueOf(data[5]) + ","
                    + quotate(data[6]) + ","
                    + res + ","
                    + quotate(data[8]) + ")";
        
        try {
           
            int done = stmt.executeUpdate(query);
            String minus = "UPDATE account SET summa=summa-" + Float.valueOf(data[5])
                     + "where account_id=" + res;
           
            done = stmt.executeUpdate(minus);
            if(data[3].equals("NULL")){
            String plus = "UPDATE account SET summa=summa+" + Float.valueOf(data[5])
                     + "where account_number=" + quotate(data[8]);
            done = stmt.executeUpdate(plus);
            
            }
        return "success";
        } catch (SQLException e) {
            return "fail";
        }
        }
        else return "fail";    
    }
    
}
