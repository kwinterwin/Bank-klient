
package bank_klient;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ann
 */
public class AccountTable extends AbstractTableModel {
    private int colnum = 3;
    private int rownum;

    private String[] colNames = {"Номер счёта","Остаток","Валюта"};

    private ArrayList<String[]> ResultSets;

    public AccountTable(String data) {
        ResultSets = new ArrayList<>();
        int i = 1;
        try {
            String messageParts[] = data.split(",");
            for(i=1;i<messageParts.length;) {
                    String[] row
                            = {
                                messageParts[i], messageParts[i+1], messageParts[i+2]
                            };
                    ResultSets.add(row);
                    i=i+3;
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
