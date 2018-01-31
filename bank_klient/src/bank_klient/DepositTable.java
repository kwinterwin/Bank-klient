/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank_klient;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ann
 */
public class DepositTable extends AbstractTableModel{
    private int colnum = 5;
    private int rownum;

    private String[] colNames = {"Название", "Валюта","Период", "Процент", "Отзыв"};

    private ArrayList<String[]> ResultSets;

    public DepositTable(String data) {
        ResultSets = new ArrayList<>();
        int counter = 0;
        int i = 1;
        try {
            String messageParts[] = data.split(",");
            for(i=1;i<messageParts.length;) {
                    String[] row
                            = {
                                messageParts[i],messageParts[i+1],messageParts[i+4],messageParts[i+2],messageParts[i+3]  
                            };
                    i +=5;
                    ResultSets.add(row);
            }
        } catch (Exception e) {
            System.out.println("Exception in DepositTable");
        }
    }

    @Override
    public int getRowCount() {
        return ResultSets.size();
    }

    @Override
    public int getColumnCount() {
        return colnum;
    }

    /**
     *
     * @param rowindex
     * @param columnindex
     * @return
     */
    @Override
    public Object getValueAt(int rowindex, int columnindex) {
        String[] row = ResultSets.get(rowindex);
        return row[columnindex];
    }

    @Override
    public String getColumnName(int param) {
        return colNames[param];
    }
}
