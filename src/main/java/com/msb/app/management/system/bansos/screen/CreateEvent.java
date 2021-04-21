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

import com.msb.app.management.system.bansos.helper.DateFormatter;
import com.msb.app.management.system.bansos.model.CashEntity;
import com.msb.app.management.system.bansos.model.CashReportEntity;
import com.msb.app.management.system.bansos.model.EventEntity;
import com.msb.app.management.system.bansos.model.PackageDaoImpl;
import com.msb.app.management.system.bansos.model.PackageEntity;
import com.msb.app.management.system.bansos.model.ReceiverEntity;
import com.msb.app.management.system.bansos.service.cash.CashDaoImpl;
import com.msb.app.management.system.bansos.service.cash.CashReportDaoImpl;
import com.msb.app.management.system.bansos.service.event.EventDaoImpl;
import com.msb.app.management.system.bansos.service.receiver.ReceiverDaoImpl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 *
 * @author difaagh
 */
public class CreateEvent extends javax.swing.JFrame {

    /**
     * Creates new form CreateEvent
     */
    public CreateEvent() {
        initComponents();
        this.dataTablePkg = new ArrayList();
        this.dataTableReceiver = new ArrayList();
        this.lastRowPkg = 0;
        this.lastRowReceiver = 0;
        this.disableAddPkgAndReceiver();
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean checkValidDate() {
        int dateChecker = this.startDateVal.getDate().compareTo(this.endDateVal.getDate());
        DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd");
        DateTime dateNow = new DateTime();
        DateTime oneDayAgo = dateNow.minusDays(1);
        DateTime start = new DateTime(this.startDateVal.getDate());
        boolean dateToday = oneDayAgo.getMillis() > start.getMillis();

        if (dateToday) {
            JOptionPane.showMessageDialog(this, "Start date cannot less than today!");
            return false;
        }
        if (dateChecker > 0) {
            JOptionPane.showMessageDialog(this, "End date cannot less than Start date !");
            return false;
        }
//        if (dateChecker == 0) {
//            JOptionPane.showMessageDialog(this, "Start date and End date cannot be equal !");
//            return false;
//        }
        return true;
    }

    public void setTotalCash(String cash) {
        this.totalCashValue.setText(cash);
    }

    public void clearDataTablePkg() {
        this.dataTablePkg.clear();
    }

    public void clearDataTableReceiver() {
        this.dataTableReceiver.clear();
    }

    public void setDataTableReceiver(ArrayList<String> data) {
        data.forEach(d -> {
            this.dataTableReceiver.add(d);
        });
    }

    public void setDataTablePkg(ArrayList<String> data) {
        data.forEach(d -> {
            this.dataTablePkg.add(d);
        });
    }

    private ArrayList<String> getDataTablePkg() {
        return this.dataTablePkg;
    }

    private ArrayList<String> getDataTableReceiver() {
        return this.dataTableReceiver;
    }

    public void renderTableReceiver() {
        DefaultTableModel newTableModel = (DefaultTableModel) this.receiverTable.getModel();
        int lastCountRow = newTableModel.getRowCount();
        newTableModel.setRowCount(lastCountRow + 3);
        this.receiverTable.setModel(newTableModel);
        int next = this.lastRowReceiver - 1;
        this.receiverTable.setValueAt(this.dataTableReceiver.get(0), next, 0);
        this.receiverTable.setValueAt(this.dataTableReceiver.get(1), next, 1);
        this.receiverTable.setValueAt("Delete", next, 2);
    }

    public void renderTablePkg() {
        DefaultTableModel newTableModel = (DefaultTableModel) this.packageTable.getModel();
        int lastCountRow = newTableModel.getRowCount();
        newTableModel.setRowCount(lastCountRow + 3);
        this.packageTable.setModel(newTableModel);
        int next = this.lastRowPkg - 1;
        this.packageTable.setValueAt(this.dataTablePkg.get(0), next, 0);
        this.packageTable.setValueAt(this.dataTablePkg.get(1), next, 1);
        this.packageTable.setValueAt(this.dataTablePkg.get(2), next, 2);
        this.packageTable.setValueAt("Delete", next, 3);
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
        eventName = new javax.swing.JLabel();
        startDateVal = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        endDateVal = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 204, 102));

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabel1.setText("Create Event");

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
                "Package Name", "Amount", "Quantity", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
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
                "Name", "Code", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(receiverTable);

        AddReceiverButton.setText("Add Receiver");
        AddReceiverButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddReceiverButtonMouseClicked(evt);
            }
        });
        AddReceiverButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddReceiverButtonActionPerformed(evt);
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

        eventName.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel2.setText("Start Date");

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel3.setText("End Date");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(AddPackageButton)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(AddReceiverButton)
                                .addGap(79, 79, 79)
                                .addComponent(saveEvent)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(465, 465, 465)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(233, 233, 233)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(eventNameLabel1)
                        .addGap(5, 5, 5)
                        .addComponent(totalCashValue)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(eventAmountLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(eventAmount))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(eventNameLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(eventName)))
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(startDateVal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(endDateVal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(saveTempEvent))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(fieldName, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fieldAmount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 262, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addComponent(backButton)
                    .addContainerGap(909, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eventNameLabel1)
                    .addComponent(totalCashValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eventNameLabel)
                    .addComponent(eventName)
                    .addComponent(fieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eventAmountLabel)
                    .addComponent(fieldAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eventAmount))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(startDateVal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(endDateVal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(saveTempEvent))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(AddPackageButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(AddReceiverButton)
                            .addComponent(saveEvent))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(102, 102, 102))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(22, 22, 22)
                    .addComponent(backButton)
                    .addContainerGap(800, Short.MAX_VALUE)))
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

    private void fieldAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldAmountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldAmountActionPerformed

    private void saveEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveEventActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_saveEventActionPerformed

    private void fieldNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldNameActionPerformed

    private void backButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backButtonMouseClicked
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_backButtonMouseClicked

    private void saveTempEventMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveTempEventMouseClicked
        EventDaoImpl eventService = new EventDaoImpl();
        EventEntity existsEvent = null;
        try {
            existsEvent = eventService.getByStartDate(this.startDateVal.getDate());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
        if (existsEvent != null) {
            JOptionPane.showMessageDialog(this, "There's Already event on that start date! please select another start date");
            return;
        }
        int total = Integer.parseInt(this.totalCashValue.getText()) - Integer.parseInt(this.fieldAmount.getText());
        if (total < 0) {
            JOptionPane.showMessageDialog(this, "Cannot set Amount event greater than total cash!");
            return;
        }
        boolean isValid = checkValidDate() == true ? true : false;
        if (!isValid) {
            return;
        }
        String eventName = this.fieldName.getText();
        String amount = this.fieldAmount.getText();
        this.eventName.setText(eventName);
        this.eventAmount.setText(amount);
        this.fieldAmount.setVisible(false);
        this.fieldName.setVisible(false);
        this.saveTempEvent.setVisible(false);
        this.enableAddPkgAndReceiver();
    }//GEN-LAST:event_saveTempEventMouseClicked

    private void AddReceiverButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddReceiverButtonMouseClicked
        AddReceiver receiver = new AddReceiver();
        receiver.setCreateEvent(this);
        receiver.setVisible(true);
    }//GEN-LAST:event_AddReceiverButtonMouseClicked

    private void AddPackageButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddPackageButtonMouseClicked
        AddPackage addPkg = new AddPackage();
        addPkg.setCreateEvent(this);
        addPkg.setVisible(true);
    }//GEN-LAST:event_AddPackageButtonMouseClicked

    private void saveEventMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveEventMouseClicked
        boolean isValid = checkValidDate() == true ? true : false;
        if (!isValid) {
            return;
        }
        if (this.dataTablePkg.size() == 0) {
            JOptionPane.showMessageDialog(this, "Please fulfill all required field");
            return;
        }
        if (this.dataTableReceiver.size() == 0) {
            JOptionPane.showMessageDialog(this, "Please fulfill all required field");
            return;
        }
        int total = Integer.parseInt(this.totalCashValue.getText()) - Integer.parseInt(this.fieldAmount.getText());
        CashDaoImpl cashService = new CashDaoImpl();
        Collection<CashEntity> cash;
        try {
            cash = cashService.getAll();
            cash.forEach(c -> {
                c.setTotalAmount(String.valueOf(total));
                try {
                    cashService.update(c);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            });
        } catch (SQLException ex) {
            Logger.getLogger(CreateEvent.class.getName()).log(Level.SEVERE, null, ex);
        }

        CashReportDaoImpl cashReportService = new CashReportDaoImpl();
        CashReportEntity cashReport = new CashReportEntity();
        cashReport.setPrevCash(this.totalCashValue.getText());
        cashReport.setTransaction(this.fieldAmount.getText());
        cashReport.setType(this.fieldName.getText());
        cashReport.setAfterCash(String.valueOf(total));
        cashReport.setUser(this.username);

        EventDaoImpl eventService = new EventDaoImpl();
        EventEntity event = new EventEntity();
        event.setAmount(this.fieldAmount.getText());
        event.setStartDate(this.startDateVal.getDate());
        event.setEndDate(this.endDateVal.getDate());
        event.setName(this.fieldName.getText());

        try {
            eventService.create(event);
            cashReportService.create(cashReport);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } finally {
            PackageDaoImpl packageService = new PackageDaoImpl();
            ReceiverDaoImpl receiverService = new ReceiverDaoImpl();
            int eventId = event.getId();
            for (int i = 0; i < this.packageTable.getRowCount(); i++) {
                String qty = (String) this.packageTable.getValueAt(i, 2);
                if (qty != null) {
                    PackageEntity packages = new PackageEntity();
                    String name = (String) this.packageTable.getValueAt(i, 0);
                    String price = (String) this.packageTable.getValueAt(i, 1);
                    packages.setEventId(eventId);
                    packages.setName(name);
                    packages.setPrice(price);
                    packages.setQty(Integer.parseInt(qty));

                    try {
                        packageService.create(packages);
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(this, ex.getMessage());
                    }
                }

            }
            for (int a = 0; a < this.receiverTable.getRowCount(); a++) {
                String name = (String) this.receiverTable.getValueAt(a, 0);
                if (name != null) {
                    ReceiverEntity receiver = new ReceiverEntity();
                    receiver.setEventId(eventId);
                    receiver.setName(name);
                    receiver.setCode((String) this.receiverTable.getValueAt(a, 1));
                    receiver.setApproved(false);

                    try {
                        receiverService.create(receiver);
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(this, ex.getMessage());
                    }
                }
            }
            this.menu.loadMenu2();
            this.setVisible(false);
            this.dispose();

        }


    }//GEN-LAST:event_saveEventMouseClicked

    private void AddPackageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddPackageButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddPackageButtonActionPerformed

    private void AddReceiverButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddReceiverButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddReceiverButtonActionPerformed

    private void disableAddPkgAndReceiver() {
        this.AddPackageButton.setVisible(false);
        this.saveEvent.setVisible(false);
        this.AddReceiverButton.setVisible(false);
        this.jScrollPane1.setVisible(false);
        this.jScrollPane2.setVisible(false);
        this.packageTable.setVisible(false);
        this.receiverTable.setVisible(false);
    }

    private void enableAddPkgAndReceiver() {
        this.AddPackageButton.setVisible(true);
        this.saveEvent.setVisible(true);
        this.AddReceiverButton.setVisible(true);
        this.jScrollPane1.setVisible(true);
        this.jScrollPane2.setVisible(true);
        this.packageTable.setVisible(true);
        this.receiverTable.setVisible(true);
    }

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
            java.util.logging.Logger.getLogger(CreateEvent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreateEvent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreateEvent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreateEvent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CreateEvent().setVisible(true);
            }
        });
    }

    private Menu menu;
    public int lastRowPkg;
    private String username;
    public int lastRowReceiver;
    private final ArrayList<String> dataTablePkg;
    private final ArrayList<String> dataTableReceiver;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddPackageButton;
    private javax.swing.JButton AddReceiverButton;
    private javax.swing.JButton backButton;
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
