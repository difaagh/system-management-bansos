/*
 * The MIT License
 *
 * Copyright 2021 difaagh.
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

import com.msb.app.management.system.bansos.model.EventEntity;
import com.msb.app.management.system.bansos.model.PackageDaoImpl;
import com.msb.app.management.system.bansos.model.PackageEntity;
import com.msb.app.management.system.bansos.model.ReceiverEntity;
import com.msb.app.management.system.bansos.service.event.EventDaoImpl;
import com.msb.app.management.system.bansos.service.receiver.ReceiverDaoImpl;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author difaagh
 */
public class EventDetail extends javax.swing.JFrame {

    /**
     * Creates new form eventDetail
     */
    public EventDetail() {
        initComponents();
        setEditable(false);
        TableColumnModel tcm = this.receiverTable.getColumnModel();
        TableColumn column = tcm.getColumn(2);
        column.setMinWidth(0);
        column.setMaxWidth(0);
        column.setPreferredWidth(0);
        TableColumnModel tcm2 = this.packageTable.getColumnModel();
        TableColumn column2 = tcm2.getColumn(3);
        column2.setMinWidth(0);
        column2.setMaxWidth(0);
        column2.setPreferredWidth(0);
    }

    public void setEditable(boolean bool) {
        this.fieldName.setText(this.eventName.getText());
        this.fieldAmount.setText(this.eventAmount.getText());
        this.fieldName.setVisible(bool);
        this.fieldAmount.setVisible(bool);
        this.startDateVal.setEnabled(bool);
        this.endDateVal.setEnabled(bool);
        this.isEdit = bool;
        if (!bool) {
            this.eventAmount.setVisible(true);
            this.eventName.setVisible(true);
            this.saveTempEvent.setVisible(false);
        } else {
            this.eventName.setVisible(false);
            this.eventAmount.setVisible(false);
            this.saveTempEvent.setVisible(true);
        }
    }

    public void setEvent(String eventId, String role, String cash) {
        this.eventId = Integer.parseInt(eventId);
        this.role = role;
        this.cash = cash;
    }

    public int getEventId() {
        return this.eventId;
    }

    public void renderAll() {
        this.totalCashValue.setText(this.cash);
        this.renderEvent();
        this.renderTablePkg();
        this.renderTableReceiver();
    }

    public void renderEvent() {
        EventDaoImpl eventService = new EventDaoImpl();
        EventEntity event = null;
        try {
            event = eventService.getById(this.getEventId());
            if (event != null) {
                this.eventName.setText(event.getName());
                this.eventAmount.setText(event.getAmount());
                this.startDateVal.setDate(event.getStartDate());
                this.endDateVal.setDate(event.getEndDate());
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

    }

    private void clearTablePkg() {
        DefaultTableModel newTableModel = (DefaultTableModel) this.packageTable.getModel();
        newTableModel.setRowCount(0);
        this.packageTable.setModel(newTableModel);
    }

    public void renderTablePkg() {
        this.tablePkgCanAction = false;
        PackageDaoImpl pkgService = new PackageDaoImpl();
        ArrayList<PackageEntity> pkg = new ArrayList();
        try {
            pkg = (ArrayList<PackageEntity>) pkgService.getAllByEventId(this.getEventId());
            if (pkg.size() > 0) {
                this.tablePkgCanAction = true;
                DefaultTableModel newTableModel = (DefaultTableModel) this.packageTable.getModel();
                newTableModel.setRowCount(pkg.size());
                this.packageTable.setModel(newTableModel);
                int loop = 0;
                for (PackageEntity p : pkg) {
                    this.packageTable.setValueAt(p.getName(), loop, 0);
                    this.packageTable.setValueAt(p.getPrice(), loop, 1);
                    this.packageTable.setValueAt(p.getQty(), loop, 2);
                    this.packageTable.setValueAt(p.getId(), loop, 3);
                    loop += 1;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void clearTableReceiver() {
        DefaultTableModel newTableModel = (DefaultTableModel) this.receiverTable.getModel();
        newTableModel.setRowCount(0);
        this.receiverTable.setModel(newTableModel);
    }

    public void renderTableReceiver() {
        this.tableReceiverCanAction = false;
        ReceiverDaoImpl receiverService = new ReceiverDaoImpl();
        ArrayList<ReceiverEntity> receiver = new ArrayList();
        try {
            receiver = (ArrayList<ReceiverEntity>) receiverService.getAllByEventId(this.getEventId());
            if (receiver.size() > 0) {
                this.tableReceiverCanAction = true;
                DefaultTableModel newTableModel = (DefaultTableModel) this.packageTable.getModel();
                newTableModel.setRowCount(receiver.size() + 1);
                this.packageTable.setModel(newTableModel);
                int loop = 0;
                for (ReceiverEntity p : receiver) {
                    this.receiverTable.setValueAt(p.getName(), loop, 0);
                    this.receiverTable.setValueAt(p.getCode(), loop, 1);
                    this.receiverTable.setValueAt(p.getId(), loop, 2);
                    loop++;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        fieldName = new javax.swing.JTextField();
        fieldAmount = new javax.swing.JTextField();
        eventAmountLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        packageTable = new javax.swing.JTable();
        AddPackageButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        receiverTable = new javax.swing.JTable();
        AddReceiverButton = new javax.swing.JButton();
        saveEvent = new javax.swing.JButton();
        eventAmount = new javax.swing.JLabel();
        saveTempEvent = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        eventNameLabel1 = new javax.swing.JLabel();
        totalCashValue = new javax.swing.JLabel();
        eventNameLabel = new javax.swing.JLabel();
        startDateVal = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        endDateVal = new com.toedter.calendar.JDateChooser();
        eventName = new javax.swing.JLabel();
        editButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 204, 102));

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabel1.setText("Detail Event");

        fieldName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldNameActionPerformed(evt);
            }
        });

        fieldAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldAmountActionPerformed(evt);
            }
        });

        eventAmountLabel.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        eventAmountLabel.setText("Amount       Rp.");

        packageTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Package Name", "Price", "Quantity", "Hidden"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        packageTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                packageTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(packageTable);

        AddPackageButton.setText("Add Package");
        AddPackageButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddPackageButtonMouseClicked(evt);
            }
        });
        AddPackageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddPackageButtonActionPerformed(evt);
            }
        });

        receiverTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Name", "Code", "Hidden"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        receiverTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                receiverTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(receiverTable);

        AddReceiverButton.setText("Add Receiver");
        AddReceiverButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddReceiverButtonMouseClicked(evt);
            }
        });

        saveEvent.setText("Save");
        saveEvent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveEventMouseClicked(evt);
            }
        });
        saveEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveEventActionPerformed(evt);
            }
        });

        eventAmount.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        eventAmount.setText("0");

        saveTempEvent.setText("save");
        saveTempEvent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveTempEventMouseClicked(evt);
            }
        });

        backButton.setText("Back");
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backButtonMouseClicked(evt);
            }
        });

        eventNameLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        eventNameLabel1.setText("Total Cash   Rp.");

        totalCashValue.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        totalCashValue.setText("0");

        eventNameLabel.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        eventNameLabel.setText("Name             ");

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel2.setText("Start Date");

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel3.setText("End Date");

        eventName.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        eventName.setText("0");

        editButton.setText("Edit");
        editButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(backButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(417, 417, 417))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(eventNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eventName)
                        .addGap(18, 18, 18)
                        .addComponent(fieldName, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(eventAmountLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(eventAmount))
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(eventNameLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(totalCashValue)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fieldAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(endDateVal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(startDateVal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(173, 173, 173)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(saveTempEvent)
                                    .addComponent(editButton))))))
                .addGap(234, 234, 234))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 988, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AddPackageButton)
                            .addComponent(AddReceiverButton)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 988, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(425, 425, 425)
                        .addComponent(saveEvent)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(backButton))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(eventNameLabel)
                                    .addComponent(eventName)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(fieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eventAmountLabel)
                            .addComponent(eventAmount)
                            .addComponent(fieldAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eventNameLabel1)
                            .addComponent(totalCashValue))
                        .addGap(14, 14, 14)
                        .addComponent(jLabel2)
                        .addGap(9, 9, 9)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(startDateVal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(endDateVal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(saveTempEvent)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(AddPackageButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(AddReceiverButton)
                .addGap(4, 4, 4)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(saveEvent)
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fieldNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldNameActionPerformed

    private void fieldAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldAmountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldAmountActionPerformed

    private void AddPackageButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddPackageButtonMouseClicked
        AddPackage pkg = new AddPackage();
        pkg.setUsedScreenFor = "eventDetail";
        pkg.eventId = this.eventId;
        pkg.setEventDetail(this);
        pkg.setVisible(true);
    }//GEN-LAST:event_AddPackageButtonMouseClicked

    private void AddPackageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddPackageButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddPackageButtonActionPerformed

    private void AddReceiverButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddReceiverButtonMouseClicked
        AddReceiver receiver = new AddReceiver();

        receiver.setUsedScreenFor = "eventDetail";
        receiver.eventId = this.eventId;
        receiver.setEventDetail(this);
        receiver.setVisible(true);
    }//GEN-LAST:event_AddReceiverButtonMouseClicked

    private void saveEventMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveEventMouseClicked
        this.renderAll();

    }//GEN-LAST:event_saveEventMouseClicked

    private void saveEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveEventActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_saveEventActionPerformed

    private void saveTempEventMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveTempEventMouseClicked

        EventDaoImpl eventService = new EventDaoImpl();
        EventEntity event = new EventEntity();
        event.setAmount(this.fieldAmount.getText());
        event.setStartDate(this.startDateVal.getDate());
        event.setEndDate(this.endDateVal.getDate());
        event.setName(this.fieldName.getText());
        event.setId(this.eventId);
        try {
            eventService.update(event);
            this.setEditable(false);
            this.renderEvent();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_saveTempEventMouseClicked

    private void backButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backButtonMouseClicked
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_backButtonMouseClicked

    private void editButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editButtonMouseClicked

        boolean perf = this.isEdit ? true : false;
        if (!perf) {
            this.editButton.setText("Cancel");
            this.setEditable(true);
        } else {
            this.editButton.setText("Edit");
            this.setEditable(false);
        }
    }//GEN-LAST:event_editButtonMouseClicked

    private void packageTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_packageTableMouseClicked
        if (evt.getClickCount() == 2 && evt.getButton() == MouseEvent.BUTTON1 && this.tablePkgCanAction) {
            int result = confirmAction(evt);
            int row = receiverTable.rowAtPoint(evt.getPoint());
            if (result == 1) {
                EditPackage packages = new EditPackage();
                packages.setState(this, (int) this.packageTable.getValueAt(row, 3), this.getEventId());
                packages.render();
                packages.setVisible(true);
                return;
            }
            if (result == 0) {
                PackageDaoImpl packageService = new PackageDaoImpl();
                PackageEntity pkg = new PackageEntity();
                pkg.setId((int) this.packageTable.getValueAt(row, 3));
                try {
                    packageService.delete(pkg);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                } finally {
                    this.clearTablePkg();
                    this.renderTablePkg();
                }
            }
        }

    }//GEN-LAST:event_packageTableMouseClicked

    private void receiverTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_receiverTableMouseClicked
        if (evt.getClickCount() == 2 && evt.getButton() == MouseEvent.BUTTON1 && this.tableReceiverCanAction) {
            int result = confirmAction(evt);
            int row = receiverTable.rowAtPoint(evt.getPoint());
            if (result == 1) {
                EditReceiver receiver = new EditReceiver();
                receiver.setState(this, (int) this.receiverTable.getValueAt(row, 2), this.getEventId());
                receiver.render();
                receiver.setVisible(true);
                return;
            }
            if (result == 0) {
                ReceiverDaoImpl receiverService = new ReceiverDaoImpl();
                ReceiverEntity receiver = new ReceiverEntity();
                receiver.setId((int) this.receiverTable.getValueAt(row, 2));
                try {
                    receiverService.delete(receiver);
                    this.renderTableReceiver();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                } finally {
                    this.clearTableReceiver();
                    this.renderTableReceiver();
                }
            }
        }
    }//GEN-LAST:event_receiverTableMouseClicked

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
            java.util.logging.Logger.getLogger(EventDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EventDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EventDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EventDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EventDetail().setVisible(true);
            }
        });
    }

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
    private boolean tablePkgCanAction;
    private boolean tableReceiverCanAction;
    private int eventId;
    private String role;
    private String cash;
    private boolean isEdit;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddPackageButton;
    private javax.swing.JButton AddReceiverButton;
    private javax.swing.JButton backButton;
    private javax.swing.JButton editButton;
    private com.toedter.calendar.JDateChooser endDateVal;
    private javax.swing.JLabel eventAmount;
    private javax.swing.JLabel eventAmountLabel;
    private javax.swing.JLabel eventName;
    private javax.swing.JLabel eventNameLabel;
    private javax.swing.JLabel eventNameLabel1;
    private javax.swing.JTextField fieldAmount;
    private javax.swing.JTextField fieldName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable packageTable;
    private javax.swing.JTable receiverTable;
    private javax.swing.JButton saveEvent;
    private javax.swing.JButton saveTempEvent;
    private com.toedter.calendar.JDateChooser startDateVal;
    private javax.swing.JLabel totalCashValue;
    // End of variables declaration//GEN-END:variables
}
