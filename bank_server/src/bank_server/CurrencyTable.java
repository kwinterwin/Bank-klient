
package bank_server;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CurrencyTable extends Data_db implements Result{
    
     public CurrencyTable(java.sql.Statement stmt, DBConnection mdbc) {
        super(stmt, mdbc);
    }
     
      @Override
    public ResultSet getResultFromTable() {
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery("SELECT * FROM currency_kurs");
        } catch (SQLException e) {
        }
        return rs;
    }
    
    public String show(){
        ResultSet rs = getResultFromTable();
        String result = "success";
        try {
            while (rs.next()) {
                result += "," + rs.getFloat("cost") + "," + rs.getFloat("sale");
            } 
          if (result.equals("success"))
              return "fail";
          else return result;
        } catch (Exception e) {
            System.out.println("Exception in Table of currency_kurs");
            return "";
        }
    }
    
    public String changeKurs(String [] data) {
        try {
            System.out.println(data.length);
            String query1 = "UPDATE currency_kurs SET sale =" + Float.valueOf(data[2]) +
                    ", cost="+ Float.valueOf(data[1]) + "where currency=\"USD\"";
            String query2 = "UPDATE currency_kurs SET sale =" + Float.valueOf(data[4]) +
                    ", cost="+ Float.valueOf(data[3]) + "where currency=\"EUR\"";
            String query3 = "UPDATE currency_kurs SET sale =" + Float.valueOf(data[6]) +
                    ", cost="+ Float.valueOf(data[5]) + "where currency=\"RUB\"";
            
            int done = stmt.executeUpdate(query1);
            done = stmt.executeUpdate(query2);
            done = stmt.executeUpdate(query3);      
            return "success";
            } catch (SQLException e) {
            System.err.println("Ошибка ввода данных");
            return "fail";
        }
    }
}
