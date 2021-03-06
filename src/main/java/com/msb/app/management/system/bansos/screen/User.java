/*
 * The MIT License
 *
 * Copyright 2021 mac.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.msb.app.management.system.bansos.screen;

/**
 *
 * @author mac
 */
//import javax.swing.*;
import com.msb.app.management.system.bansos.model.UserEntity;
import com.msb.app.management.system.bansos.service.user.UserDaoImpl;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class User extends javax.swing.JFrame {

    /**
     * Creates new form User
     */
    public User() {
        initComponents();
        TableColumnModel tcm = this.userTable.getColumnModel();
        TableColumn column = tcm.getColumn(3);
        column.setMinWidth(0);
        column.setMaxWidth(0);
        showTable();
    }

    public void showTable() {
        try {
            clearTable();
            ArrayList<UserEntity> listUser = new ArrayList();
            UserDaoImpl userService = new UserDaoImpl();
            listUser = (ArrayList<UserEntity>) userService.getAll();
            if (!listUser.isEmpty()) {
                DefaultTableModel newTableModel = (DefaultTableModel) this.userTable.getModel();
                newTableModel.setRowCount(listUser.size());
                this.userTable.setModel(newTableModel);

                int row = 0;
                for (UserEntity user : listUser) {
                    this.userTable.setValueAt(row + 1, row, 0);
                    this.userTable.setValueAt(user.getUsername(), row, 1);
                    this.userTable.setValueAt(user.getRole(), row, 2);
                    this.userTable.setValueAt(user.getId(), row, 3);
                    row++;
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    public void clearTable() {
        DefaultTableModel newTableModel = (DefaultTableModel) this.userTable.getModel();
        newTableModel.setRowCount(0);
        this.userTable.setModel(newTableModel);
    }

    public void searchTable() {
        try {
            ArrayList<UserEntity> listUser = new ArrayList();
            UserDaoImpl userService = new UserDaoImpl();
            listUser = (ArrayList<UserEntity>) userService.searchByUsername(this.textSearch.getText());
            // if search not found then clear table
            if (listUser.isEmpty()) {
                this.clearTable();
                return;
            }
            if (!listUser.isEmpty()) {
                DefaultTableModel newTableModel = (DefaultTableModel) this.userTable.getModel();
                newTableModel.setRowCount(listUser.size());
                this.userTable.setModel(newTableModel);

                int row = 0;
                for (UserEntity user : listUser) {
                    this.userTable.setValueAt(row + 1, row, 0);
                    this.userTable.setValueAt(user.getUsername(), row, 1);
                    this.userTable.setValueAt(user.getRole(), row, 2);
                    this.userTable.setValueAt(user.getId(), row, 3);
                    row++;
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        createBtn = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        textUserName = new javax.swing.JTextField();
        textPassword = new javax.swing.JPasswordField();
        textSearch = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        userTable = new javax.swing.JTable();
        radioBtnAdmin = new javax.swing.JRadioButton();
        radioBtnUser = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 204, 102));

        createBtn.setText("Create");
        createBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createBtnActionPerformed(evt);
            }
        });

        jButton1.setText("X");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Refresh");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        jLabel1.setText("Username");

        jLabel2.setText("Password");

        jLabel3.setText("Role");

        textSearch.setText("username");

        userTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "No", "Username", "Role", "Id"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        userTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                userTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(userTable);

        radioBtnAdmin.setSelected(true);
        radioBtnAdmin.setText("Admin");
        radioBtnAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioBtnAdminActionPerformed(evt);
            }
        });

        radioBtnUser.setText("User");
        radioBtnUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioBtnUserActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(92, Short.MAX_VALUE)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                        .add(jButton2)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 49, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(jPanel2Layout.createSequentialGroup()
                                .add(jLabel1)
                                .add(43, 43, 43)
                                .add(textUserName))
                            .add(jPanel2Layout.createSequentialGroup()
                                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel3)
                                    .add(jLabel2))
                                .add(46, 46, 46)
                                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(textPassword)
                                    .add(jPanel2Layout.createSequentialGroup()
                                        .add(radioBtnAdmin)
                                        .add(69, 69, 69)
                                        .add(radioBtnUser)
                                        .add(0, 0, Short.MAX_VALUE))))
                            .add(jPanel2Layout.createSequentialGroup()
                                .add(createBtn)
                                .add(183, 183, 183)
                                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jPanel2Layout.createSequentialGroup()
                                        .add(140, 140, 140)
                                        .add(btnSearch))
                                    .add(textSearch, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 221, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                            .add(jScrollPane2))
                        .add(84, 84, 84))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButton2)
                    .add(jButton1))
                .add(7, 7, 7)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(textUserName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(20, 20, 20)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(textPassword, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(23, 23, 23)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel3)
                    .add(radioBtnAdmin)
                    .add(radioBtnUser))
                .add(13, 13, 13)
                .add(textSearch, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(createBtn)
                    .add(btnSearch))
                .add(29, 29, 29)
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 271, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void createBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createBtnActionPerformed
        // TODO add your handling code here:
        if (this.textUserName.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Please fulfill all required field");
            return;
        }
        if (this.textPassword.getPassword().length == 0) {
            JOptionPane.showMessageDialog(this, "Please fulfill all required field");
            return;
        }
        try {
            UserDaoImpl userService = new UserDaoImpl();
            UserEntity user = new UserEntity();
            user.setUsername(this.textUserName.getText());
            user.setPassword(String.valueOf(this.textPassword.getPassword()));
            String role = this.getSelectedRadioButton(this.radioBtnAdmin, this.radioBtnUser);
            user.setRole(role);

            userService.create(user);
            showTable();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_createBtnActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        showTable();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        searchTable();
    }//GEN-LAST:event_btnSearchActionPerformed

    private int confirmAction(java.awt.event.MouseEvent evt) {
        int result = 0;
        if (evt.getClickCount() == 2 && evt.getButton() == MouseEvent.BUTTON1) {
            Object[] options1 = {"Delete", "Edit",
                "Cancel"};

            JPanel panel = new JPanel();
            panel.add(new JLabel("Action"));
            result = JOptionPane.showOptionDialog(null, panel, "Enter a Number",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, options1, null);

        }
        return result;
    }
    
    private String getSelectedRadioButton(JRadioButton admin, JRadioButton user){
        String role = admin.isSelected() ? "admin": "user";
        return role;
    }

    private void userTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userTableMouseClicked
        if (evt.getClickCount() == 2 && evt.getButton() == MouseEvent.BUTTON1) {
            int result = confirmAction(evt);
            int row = userTable.rowAtPoint(evt.getPoint());
            if (result == 1) {
                EditUser user = new EditUser();
                UserEntity userData = new UserEntity();
                userData.setId((int) this.userTable.getValueAt(row, 3));
                userData.setUsername((String) this.userTable.getValueAt(row, 1));
                userData.setRole((String) this.userTable.getValueAt(row, 2));
                user.setUserDataAndState(userData, this);
                user.render();
                user.setVisible(true);
                return;
            }
            if (result == 0) {
                UserDaoImpl userService = new UserDaoImpl();
                UserEntity user = new UserEntity();
                user.setId((int) this.userTable.getValueAt(row, 3));
                try {
                    userService.delete(user);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                } finally {
                    this.showTable();
                }
            }
        }

    }//GEN-LAST:event_userTableMouseClicked

    private void radioBtnAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioBtnAdminActionPerformed
        this.radioBtnUser.setSelected(false);
    }//GEN-LAST:event_radioBtnAdminActionPerformed

    private void radioBtnUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioBtnUserActionPerformed
       this.radioBtnAdmin.setSelected(false);
    }//GEN-LAST:event_radioBtnUserActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new User().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton createBtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton radioBtnAdmin;
    private javax.swing.JRadioButton radioBtnUser;
    private javax.swing.JPasswordField textPassword;
    private javax.swing.JTextField textSearch;
    private javax.swing.JTextField textUserName;
    private javax.swing.JTable userTable;
    // End of variables declaration//GEN-END:variables

}
