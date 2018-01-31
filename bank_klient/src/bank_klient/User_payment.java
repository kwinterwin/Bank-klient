
package bank_klient;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;


public class User_payment extends javax.swing.JFrame {

    String login = "";
    String result = "";
    String [] account = new String[0];
    String [] payment = new String[0];
    
    public User_payment(String login) {
        this.login = login;
        add();
        sw();
        initComponents();
    }

    public void add(){
         try {
            String clientMessage = "";
            int count = 0;                               
            clientMessage = "myAccounts," + login;

            Authorization.os.writeObject(clientMessage);
            
            String message = (String) Authorization.is.readObject();
            
            String mesParts[] = message.split(",");
            
            if (mesParts[0].equals("success")) {
                count = (mesParts.length-1);
                account = new String[count];
                
                int j=0;
                for(int i=1;i<mesParts.length;i++){
                   account[j] = mesParts[i];
                   j++;   
                }               
            }
            if (mesParts[0].equals("fail")) {
                
                account = new String[1];
                account[0] = "У вас нет счетов";            
            }
            
           } catch (Exception e) {
            
        }      
    }
    
    public void sw(){
         try {
            String clientMessage = "";
            int count = 0;                               
            clientMessage = "payments";

            Authorization.os.writeObject(clientMessage);
            
            String message = (String) Authorization.is.readObject();
            
            String mesParts[] = message.split(",");
            
            if (mesParts[0].equals("success")) {
                count = (mesParts.length-1);
                payment = new String[count];
                
                int j=0;
                for(int i=1;i<mesParts.length;i++){
                   payment[j] = mesParts[i];
                   j++;   
                }               
            }
            if (mesParts[0].equals("fail")) { 
                payment = new String[1];
                payment[0] = "У вас нет счетов";            
            }
            
           } catch (Exception e) {
            
        }      
    }
     
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLocation(480,200);
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jTextField6 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Оплатить услугу");

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Выберите услугу:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Мобильная связь");
        jLabel2.setEnabled(false);

        jTextField1.setEnabled(false);

        jLabel3.setText("Введите номер телефона:");
        jLabel3.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Интернет");
        jLabel4.setEnabled(false);

        jLabel5.setText("Введите номер счёта:");
        jLabel5.setEnabled(false);

        jTextField2.setEnabled(false);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Расчётный счёт");
        jLabel6.setEnabled(false);

        jTextField3.setEnabled(false);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Билеты");
        jLabel7.setEnabled(false);

        jTextField4.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Введите сумму платежа:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Введите код банка:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Выберите счёт:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(account));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jComboBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox1MouseClicked(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jTextField6.setEnabled(false);

        jList2.setModel(new javax.swing.AbstractListModel() {
            String[] strings = payment;
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList2MouseClicked(evt);
            }
        });
        jList2.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList2ValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jList2);

        jButton1.setBackground(new java.awt.Color(255, 255, 204));
        jButton1.setText("Назад");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 255, 204));
        jButton2.setText("Оплатить");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel11.setText("Введите номер заказа:");
        jLabel11.setEnabled(false);

        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(100000.0f), Float.valueOf(1.0f)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel2))))
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(98, 98, 98)
                                .addComponent(jLabel6)
                                .addContainerGap(42, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addGap(24, 24, 24))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField6))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(22, 22, 22))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel4))
                                .addGap(62, 62, 62))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(26, 26, 26))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jLabel12))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox1MouseClicked
         try {
            String clientMessage = "";
            
            String account = "";
            
            account = (String) jComboBox1.getSelectedItem();
            clientMessage = "summAccount," + account;

            Authorization.os.writeObject(clientMessage);
            
            String message = (String) Authorization.is.readObject();
            String messageParts[] = message.split(",");
            
            if (messageParts[0].equals("success")) {
               
                jTextField6.setText(messageParts[2]);
            }
            if (messageParts[0].equals("fail")) {
               
            }
        } catch (Exception e) {
            
        }
    }//GEN-LAST:event_jComboBox1MouseClicked

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        try {
            String clientMessage = "";
            
            String account = "";
            
            account = (String) jComboBox1.getSelectedItem();
            clientMessage = "summAccount," + account;

            Authorization.os.writeObject(clientMessage);
            
            String message = (String) Authorization.is.readObject();
            String messageParts[] = message.split(",");
            
            if (messageParts[0].equals("success")) {
               
                jTextField6.setText(messageParts[2]);
            }
            if (messageParts[0].equals("fail")) {
               
            }
        } catch (Exception e) {
            
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jList2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList2MouseClicked
         try {
            String clientMessage = "";
             
            String sel = "";
            sel = (String) jList2.getSelectedValue();
            
            if (sel.equals("Интернет")) {
               
            }
            else if (sel.equals("Мобильная связь")){
                jLabel2.setEnabled(true);
                jLabel3.setEnabled(true);
                jTextField1.setEnabled(true);
            }
            else if (sel.equals("МВД")){
                
            }
            else if (sel.equals("Билеты")){
                
            }
            else if (sel.equals("Туризм")){
                
            }
            
        } catch (Exception e) {
            
        }
    }//GEN-LAST:event_jList2MouseClicked

    private void jList2ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList2ValueChanged
         try {
            String sel = "";
            sel = (String) jList2.getSelectedValue();
            
            
            if (sel.equals("Интернет")) {
                jLabel4.setEnabled(true);
                jLabel5.setEnabled(true);
                jTextField2.setEnabled(true);
                
                jLabel2.setEnabled(false);
                jLabel3.setEnabled(false);
                jTextField1.setEnabled(false);
                jLabel6.setEnabled(false);
                jTextField3.setEnabled(false);
                jLabel7.setEnabled(false);
                jLabel11.setEnabled(false);
                jTextField4.setEnabled(false);
                
               
            }
            else if (sel.equals("Мобильная связь")){
                jLabel2.setEnabled(true);
                jLabel3.setEnabled(true);
                jTextField1.setEnabled(true);
                
                jLabel4.setEnabled(false);
                jLabel5.setEnabled(false);
                jTextField2.setEnabled(false);
                jLabel6.setEnabled(false);
                jTextField3.setEnabled(false);
                jLabel7.setEnabled(false);
                jLabel11.setEnabled(false);
                jTextField4.setEnabled(false);
            }
            else if (sel.equals("МВД")){
                jLabel6.setEnabled(true);
                jTextField3.setEnabled(true);
                
                jLabel7.setEnabled(false);
                jLabel11.setEnabled(false);
                jTextField4.setEnabled(false);
                jLabel2.setEnabled(false);
                jLabel3.setEnabled(false);
                jTextField1.setEnabled(false);
                jLabel4.setEnabled(false);
                jLabel5.setEnabled(false);
                jTextField2.setEnabled(false);
            }
            else if (sel.equals("Билеты")){
                jLabel7.setEnabled(true);
                jLabel11.setEnabled(true);
                jTextField4.setEnabled(true);
                
                jLabel2.setEnabled(false);
                jLabel3.setEnabled(false);
                jTextField1.setEnabled(false);
                jLabel4.setEnabled(false);
                jLabel5.setEnabled(false);
                jTextField2.setEnabled(false);
                jLabel6.setEnabled(false);
                jTextField3.setEnabled(false);
                
            }
            else if (sel.equals("Туризм")){
                 jLabel6.setEnabled(true);
                jTextField3.setEnabled(true);
                
                jLabel7.setEnabled(false);
                jLabel11.setEnabled(false);
                jTextField4.setEnabled(false);
                jLabel2.setEnabled(false);
                jLabel3.setEnabled(false);
                jTextField1.setEnabled(false);
                jLabel4.setEnabled(false);
                jLabel5.setEnabled(false);
                jTextField2.setEnabled(false);
            }
            
        } catch (Exception e) {
            
        }
    }//GEN-LAST:event_jList2ValueChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new User_window(this.login).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            String sel = "";
            sel = (String) jList2.getSelectedValue();
            
            String clientMessage = "";
            int count = 0;                               
            clientMessage = "pay,";
           
            Date data = new Date();
            SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd");
            String date_close = formatForDateNow.format(data);
            
            if (sel.equals("Интернет")){  
                result = "";
                result += date_close;
                result += "," + jTextField5.getText();
                result += "," + jTextField2.getText();
                result += "," + jTextField6.getText();
                result += "," + (float) jSpinner1.getValue();
                result += "," + sel;
                result += "," + jComboBox1.getSelectedItem();
                result += ",0";
                System.out.println(result);
            }
            else if (sel.equals("Мобильная связь")){
                result = "";
                result += date_close;
                result += "," + jTextField5.getText();
                result += "," + jTextField1.getText();
                result += "," + jTextField6.getText();
                result += "," + (float) jSpinner1.getValue();
                result += "," + sel;
                result += "," + jComboBox1.getSelectedItem();
                result += ",0";
                System.out.println(result);
            }
            else if (sel.equals("МВД")){
                result = "";
                result += date_close;
                result += "," + jTextField5.getText();
                result += "," + jTextField3.getText();
                result += "," + jTextField6.getText();
                result += "," + (float) jSpinner1.getValue();
                result += "," + sel;
                result += "," + jComboBox1.getSelectedItem();
                result += ",0";
                System.out.println(result);
            }
            else if (sel.equals("Билеты")){
                result = "";
                result += date_close;
                result += "," + jTextField5.getText();
                result += "," + jTextField4.getText();
                result += "," + jTextField6.getText();
                result += "," + (float) jSpinner1.getValue();
                result += "," + sel;
                result += "," + jComboBox1.getSelectedItem();
                result += ",0";
                System.out.println(result);
                
            }
            else if (sel.equals("Туризм")){
                result = "";
                result += date_close;
                result += "," + jTextField5.getText();
                result += "," + jTextField3.getText();
                result += "," + jTextField6.getText();
                result += "," + (float) jSpinner1.getValue();
                result += "," + sel;
                result += "," + jComboBox1.getSelectedItem();
                result += ",0";
                System.out.println(result);
            }
            clientMessage += result;
            Authorization.os.writeObject(clientMessage);
            
            String message = (String) Authorization.is.readObject();
            
            if(message.equals("success")){
                jLabel12.setForeground(Color.red);
                jLabel12.setText("Платеж успешен!");
            }
            
            if(message.equals("fail")){
                jLabel12.setForeground(Color.red);
                jLabel12.setText("Платеж не прошел!");
            }
        } catch (Exception e) {
            
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jList2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables
}
