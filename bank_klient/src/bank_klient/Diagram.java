
package bank_klient;


import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import java.util.Arrays;
import java.util.Scanner;

import javafx.scene.chart.XYChart;
import javafx.stage.Stage;


public class Diagram extends Application {

String [] data = new String[1];
Float [] sum = new Float[1];
String message = "";
String login = "";

@Override 
public void start(Stage stage) {
        stage.setTitle("Мои расходы");
        
        try(FileReader reader = new FileReader("d:\\КП\\bank_klient\\src\\bank_klient\\user.txt"))
        {
            Scanner scan = new Scanner(reader);
           // читаем посимвольно
            while (scan.hasNextLine()) {
            this.login = scan.nextLine();
        }
        }
        catch(IOException ex){
             
            System.out.println(ex.getMessage());
        }   
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        
        final BarChart<String,Number> bc = new BarChart<>(xAxis,yAxis);
        String message = "";
        String [] data = new String[0];
        Float [] sum = new Float[0];
        
        try {
            String clientMessage = "";
            int count = 0;                               
            clientMessage = "group," + login;

            Authorization.os.writeObject(clientMessage);
            
            message = (String) Authorization.is.readObject();
            
            String mesParts[] = message.split(",");
            

        xAxis.setLabel("Вид платежа");
        yAxis.setLabel("Сумма");
        
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("");
        for(int k=1;k<mesParts.length;){
            series1.getData().add(new XYChart.Data(mesParts[k+1], Float.valueOf(mesParts[k])));
            k=k+2;
        }
        Scene scene = new Scene(bc,800,600);
        
        bc.getData().addAll(series1);
        
        stage.setScene(scene);
        stage.show();

        } catch (Exception e) {
            
        }   
}
     public void f() {
                      launch();
                 }

}
