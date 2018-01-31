
package bank_server;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DepositTable extends Data_db implements Result {
    
    public DepositTable(java.sql.Statement stmt, DBConnection mdbc) {
        super(stmt, mdbc);
    }
    
    @Override
    public ResultSet getResultFromTable() {
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery("SELECT deposit_name,currency,min_summa,period FROM dict_dep");
            
        } catch (SQLException e) {
        }
        return rs;
    }

    public String getAllDeposit() {
        
        String result = "success";
        
        ResultSet rs = null;
        try {
            String query = "SELECT c.surname, c.name, c.lastname, a.account_number,a.summa, a.currency, a.date_open, a.date_close " + 
                    "FROM account a join customer c on a.customer_id=c.customer_id where account_type=0";
            System.out.println(query);
            rs = stmt.executeQuery(query);
            while(rs.next()){
            result += "," + rs.getString(1);
            result += "," + rs.getString(2);
            result += "," + rs.getString(3);
            result += "," + rs.getString(4);
            result += "," + rs.getFloat(5);
            result += "," + rs.getString(6);
            result += "," + rs.getDate(7);
            result += "," + rs.getDate(8);
            }
            System.out.println(result);
            return result;
        } catch (SQLException e) {
            return "fail";
        }
       
    }
    
    public String getResult() {
        ResultSet rs = getResultFromTable();
        String result = "success";
        try {
            while(rs.next()){
              result += "," + rs.getString(1);
              result += "," + rs.getString(2);
              result += "," + rs.getFloat(3);
              result += "," + rs.getInt(4); 
            }  
            
            return result;
        } catch (SQLException e) {
        }
        return result;
    }
    public String getCurrency() {
        String query = "SELECT currency FROM dict_dep group by currency";
       
        String result = "success";
        try {
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
              result += "," + rs.getString(1);
            }  
            
            return result;
        } catch (SQLException e) {
            result = "fail";
            return result;
        }
    }
    
        public String getDeposit(String [] data) {
        String query = "SELECT deposit_name, currency, percent,is_reversal,period FROM dict_dep where currency=" 
                + quotate(data[1]) + " AND min_summa<=" + Float.valueOf(data[2])
                + " AND period<=" + Integer.parseInt(data[3]);
       
        String result = "success";
        try {
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
              result += "," + rs.getString(1);
              result += "," + rs.getString(2);
              result += "," + rs.getFloat(3);
              if(rs.getInt(4)==1)
              result += "," + "Да";
              else result += "," + "Нет";
              result += "," + rs.getInt(5);
            }  
            System.out.println(result);
            return result;
        } catch (SQLException e) {
            result = "fail";
            return result;
        }
    }
}
