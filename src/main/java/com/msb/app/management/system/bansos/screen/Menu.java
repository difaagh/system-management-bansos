/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msb.app.management.system.bansos.screen;

import com.msb.app.management.system.bansos.model.CashEntity;
import com.msb.app.management.system.bansos.model.CashReportEntity;
import com.msb.app.management.system.bansos.service.event.EventDaoImpl;
import com.msb.app.management.system.bansos.service.receiver.ReceiverDaoImpl;
import com.msb.app.management.system.bansos.model.EventEntity;
import com.msb.app.management.system.bansos.model.ReceiverEntity;
import com.msb.app.management.system.bansos.service.cash.CashDaoImpl;
import com.msb.app.management.system.bansos.service.cash.CashReportDaoImpl;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 *
 * @author difaagh
 */
public class Menu extends javax.swing.JFrame {

    /**
     * Creates new form menu
     */
    public Menu() {
        initComponents();
        this.isLoadMenu1 = true;
        disableMenuExcept("menu1");
        TableColumnModel tcm = this.tableReceiver.getColumnModel();
        this.usernameVal = "";
        TableColumn column = tcm.getColumn(4);
        column.setMinWidth(0);
        column.setMaxWidth(0);
        column.setPreferredWidth(0);
        TableColumnModel tcm2 = this.tableEvent.getColumnModel();
        TableColumn column2 = tcm2.getColumn(5);
        column2.setMinWidth(0);
        column2.setMaxWidth(0);
        column2.setPreferredWidth(0);
        loadMenu1();
    }

    public void setUsername(String username) {
        this.usernameVal = username;
        this.username.setText(this.usernameVal);
    }

    public void setRole(String role) {
        this.role = role;
    }

    private void disableMenuExcept(String menu) {
        if ("menu1".equals(menu)) {
            this.menu2.setVisible(false);
        }
        if ("menu2".equals(menu)) {
            this.menu1.setVisible(false);
        }
    }

    private void hideOrShowAddCash(boolean con) {
        if (con) {
            this.addCashButton.setVisible(false);
            this.cancelCash.setVisible(true);
            this.saveCash.setVisible(true);
            this.addCashValue.setVisible(true);
            this.rpLabel.setVisible(true);
            ((AbstractDocument) this.addCashValue.getDocument()).setDocumentFilter(new DocumentFilter() {
                Pattern regEx = Pattern.compile("\\d*");

                @Override
                public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                    Matcher matcher = regEx.matcher(text);
                    if (!matcher.matches()) {
                        return;
                    }
                    super.replace(fb, offset, length, text, attrs);
                }
            });
            return;
        }
        this.addCashButton.setVisible(true);
        this.cancelCash.setVisible(false);
        this.saveCash.setVisible(false);
        this.addCashValue.setVisible(false);
        this.rpLabel.setVisible(false);
    }

    private void showLabelStartedEvent(boolean param) {
        if (!param) {
            this.eventLabel.setVisible(false);
            this.eventValue.setVisible(false);
            this.noStartLabel.setVisible(true);
            this.amountLabel.setVisible(false);
            this.amountValue.setVisible(false);
            this.startLabel.setVisible(false);
            this.startValue.setVisible(false);
            this.endLabel.setVisible(false);
            this.endValue.setVisible(false);
        } else {
            this.eventLabel.setVisible(true);
            this.eventValue.setVisible(true);
            this.noStartLabel.setVisible(false);
            this.noStartLabel.setVisible(true);
            this.amountLabel.setVisible(true);
            this.amountValue.setVisible(true);
            this.startLabel.setVisible(true);
            this.startValue.setVisible(true);
            this.endLabel.setVisible(true);
            this.endValue.setVisible(true);
        }
    }

    private void loadMenu1() {
        EventEntity event = null;
        if (!isLoadMenu1) {
            return;
        }
        EventDaoImpl eventService = new EventDaoImpl();
        try {
            event = eventService.getLatest();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        } finally {
            if (event == null) {
                this.showLabelStartedEvent(false);
                return;
            }
            this.showLabelStartedEvent(true);
            SimpleDateFormat changeFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date startTmp = null;
            java.util.Date endTmp = null;
            try {
                startTmp = changeFormat.parse(event.getStartDate() + "");
                endTmp = changeFormat.parse(event.getEndDate() + "");
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }

            String start = changeFormat.format(startTmp);
            String end = changeFormat.format(endTmp);

            this.eventValue.setText(event.getName());
            this.endValue.setText(end);
            this.startValue.setText(start);
            this.amountValue.setText(event.getAmount().toString());
            Collection<ReceiverEntity> listReceiver = null;
            ReceiverDaoImpl receiverService = new ReceiverDaoImpl();
            int colTable = 0;
            try {
                listReceiver = receiverService.getAllByEventId(event.getId());
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            } finally {
                if (listReceiver.isEmpty()) {
                    return;
                }
                DefaultTableModel newTableModel = (DefaultTableModel) this.tableReceiver.getModel();
                newTableModel.setRowCount(listReceiver.size());
                this.tableReceiver.setModel(newTableModel);
                for (ReceiverEntity receiver : listReceiver) {
                    String isApproved = receiver.approved() ? "Approved" : "Approve";
                    this.tableReceiver.setValueAt(colTable + 1, colTable, 0);
                    this.tableReceiver.setValueAt(receiver.getName(), colTable, 1);
                    this.tableReceiver.setValueAt(receiver.getCode(), colTable, 2);
                    this.tableReceiver.setValueAt(isApproved, colTable, 3);
                    String str = receiver.getId() + "," + receiver.getEventId();
                    this.tableReceiver.setValueAt(str, colTable, 4);
                    colTable++;
                }
            }

        }

    }

    public void loadMenu2() {
        ArrayList<CashEntity> cashList = new ArrayList();
        if (!isLoadMenu2) {
            return;
        }
        CashDaoImpl cashService = new CashDaoImpl();
        try {
            cashList = (ArrayList<CashEntity>) cashService.getAll();
        } catch (SQLException e) {

        } finally {
            if (cashList.size() > 0) {
                CashEntity cash = cashList.get(0);
                this.cashValue.setText(String.valueOf(cash.getTotalAmount()));
            }

            EventDaoImpl eventService = new EventDaoImpl();
            ArrayList<EventEntity> eventList = new ArrayList();
            try {
                eventList = (ArrayList<EventEntity>) eventService.getAll();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            } finally {
                if (eventList.size() > 0) {
                    int loop = 0;
                    for (EventEntity e : eventList) {
                        this.tableEvent.setValueAt(loop + 1, loop, 0);
                        this.tableEvent.setValueAt(e.getName(), loop, 1);
                        this.tableEvent.setValueAt(e.getAmount(), loop, 2);
                        SimpleDateFormat changeFormat = new SimpleDateFormat("yyyy-MM-dd");
                        java.util.Date startTmp = null;
                        java.util.Date endTmp = null;
                        try {
                            startTmp = changeFormat.parse(e.getStartDate() + "");
                            endTmp = changeFormat.parse(e.getEndDate() + "");
                        } catch (ParseException ex) {
                            JOptionPane.showMessageDialog(this, ex.getMessage());
                        }

                        String start = changeFormat.format(startTmp);
                        String end = changeFormat.format(endTmp);
                        this.tableEvent.setValueAt(start, loop, 3);
                        this.tableEvent.setValueAt(end, loop, 4);
                        this.tableEvent.setValueAt(String.valueOf(e.getId()), loop, 5);
                        loop++;
                    }
                }
            }

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
        subMenu = new javax.swing.JPanel();
        sMenu1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        sMenu2 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        sMenu3 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        sMenu4 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        header = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        username = new javax.swing.JLabel();
        layer = new javax.swing.JLayeredPane();
        menu1 = new javax.swing.JPanel();
        eventLabel = new javax.swing.JLabel();
        startLabel = new javax.swing.JLabel();
        endLabel = new javax.swing.JLabel();
        eventValue = new javax.swing.JLabel();
        startValue = new javax.swing.JLabel();
        endValue = new javax.swing.JLabel();
        amountValue = new javax.swing.JLabel();
        amountLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableReceiver = new javax.swing.JTable();
        noStartLabel = new javax.swing.JLabel();
        menu2 = new javax.swing.JPanel();
        cashLabel = new javax.swing.JLabel();
        rpLabel = new javax.swing.JLabel();
        cashValue = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableEvent = new javax.swing.JTable();
        addCashValue = new javax.swing.JTextField();
        createEventButton = new javax.swing.JButton();
        addCashButton = new javax.swing.JButton();
        saveCash = new javax.swing.JButton();
        cancelCash = new javax.swing.JButton();
        badgeLogo = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(1200, 800));

        subMenu.setBackground(new java.awt.Color(255, 204, 51));
        subMenu.setPreferredSize(new java.awt.Dimension(190, 700));

        sMenu1.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        sMenu1.setText("Home");
        sMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sMenu1MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sMenu1MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sMenu1MouseEntered(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        sMenu2.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        sMenu2.setText("Events");
        sMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sMenu2MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sMenu2MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sMenu2MouseEntered(evt);
            }
        });

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        sMenu3.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        sMenu3.setText("Report");
        sMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sMenu3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sMenu3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sMenu3MouseExited(evt);
            }
        });

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));

        sMenu4.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        sMenu4.setText("User");
        sMenu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sMenu4MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sMenu4MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sMenu4MouseEntered(evt);
            }
        });

        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout subMenuLayout = new javax.swing.GroupLayout(subMenu);
        subMenu.setLayout(subMenuLayout);
        subMenuLayout.setHorizontalGroup(
            subMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(subMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sMenu1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addComponent(sMenu2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                    .addComponent(jSeparator2)
                    .addComponent(sMenu3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                    .addComponent(jSeparator3)
                    .addComponent(sMenu4, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                    .addComponent(jSeparator4))
                .addContainerGap())
        );
        subMenuLayout.setVerticalGroup(
            subMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sMenu1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sMenu2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sMenu3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sMenu4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(472, Short.MAX_VALUE))
        );

        header.setBackground(new java.awt.Color(255, 204, 102));

        jLabel1.setText("Hello,");

        username.setText("username");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(username)
                .addContainerGap(1081, Short.MAX_VALUE))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(username))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        menu1.setBackground(new java.awt.Color(255, 255, 255));

        eventLabel.setText("Event           :");

        startLabel.setText("Start            :");

        endLabel.setText("End              :");

        eventValue.setText("null");

        startValue.setText("null");

        endValue.setText("null");

        amountValue.setText("null");

        amountLabel.setText("Jumlah dana : ");

        tableReceiver.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "No", "Name", "Code", "Status", "hidden"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableReceiver.setMaximumSize(new java.awt.Dimension(900, 64));
        tableReceiver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableReceiverMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableReceiver);

        noStartLabel.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        noStartLabel.setText("No Started Event");

        javax.swing.GroupLayout menu1Layout = new javax.swing.GroupLayout(menu1);
        menu1.setLayout(menu1Layout);
        menu1Layout.setHorizontalGroup(
            menu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(eventLabel)
                    .addComponent(startLabel)
                    .addComponent(endLabel)
                    .addComponent(amountLabel))
                .addGap(19, 19, 19)
                .addGroup(menu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(amountValue)
                    .addGroup(menu1Layout.createSequentialGroup()
                        .addGroup(menu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(endValue)
                            .addComponent(startValue)
                            .addComponent(eventValue))
                        .addGap(245, 245, 245)
                        .addComponent(noStartLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(menu1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        menu1Layout.setVerticalGroup(
            menu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu1Layout.createSequentialGroup()
                .addGroup(menu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(menu1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(menu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eventLabel)
                            .addComponent(eventValue))
                        .addGap(26, 26, 26)
                        .addGroup(menu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(startLabel)
                            .addComponent(startValue))
                        .addGap(18, 18, 18)
                        .addGroup(menu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(endLabel)
                            .addComponent(endValue)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menu1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(noStartLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
                .addGroup(menu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(amountValue)
                    .addComponent(amountLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 529, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        menu2.setBackground(new java.awt.Color(255, 255, 255));

        cashLabel.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        cashLabel.setText("Cash : Rp");

        rpLabel.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        rpLabel.setText("Rp");

        cashValue.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        cashValue.setText("0");

        tableEvent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "No", "Name", "Amount", "Start", "End", "Hiden"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableEvent.setMaximumSize(new java.awt.Dimension(500, 64));
        tableEvent.setPreferredSize(new java.awt.Dimension(200, 64));
        tableEvent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableEventMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tableEvent);

        addCashValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCashValueActionPerformed(evt);
            }
        });

        createEventButton.setText("Create Event");
        createEventButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                createEventButtonMouseClicked(evt);
            }
        });

        addCashButton.setText("Add Cash");
        addCashButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addCashButtonMouseClicked(evt);
            }
        });

        saveCash.setText("save");
        saveCash.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveCashMouseClicked(evt);
            }
        });
        saveCash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveCashActionPerformed(evt);
            }
        });

        cancelCash.setText("cancel");
        cancelCash.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelCashMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout menu2Layout = new javax.swing.GroupLayout(menu2);
        menu2.setLayout(menu2Layout);
        menu2Layout.setHorizontalGroup(
            menu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu2Layout.createSequentialGroup()
                .addGroup(menu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(menu2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(addCashButton, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(createEventButton, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(menu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(menu2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(menu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(menu2Layout.createSequentialGroup()
                                    .addComponent(cashLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cashValue))
                                .addGroup(menu2Layout.createSequentialGroup()
                                    .addComponent(rpLabel)
                                    .addGap(18, 18, 18)
                                    .addComponent(addCashValue, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(menu2Layout.createSequentialGroup()
                            .addGap(57, 57, 57)
                            .addComponent(cancelCash)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(saveCash))
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 989, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(202, Short.MAX_VALUE))
        );
        menu2Layout.setVerticalGroup(
            menu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(menu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cashLabel)
                    .addComponent(cashValue))
                .addGap(35, 35, 35)
                .addGroup(menu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rpLabel)
                    .addComponent(addCashValue, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(menu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveCash)
                    .addComponent(cancelCash))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addGroup(menu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addCashButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createEventButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        layer.setLayer(menu1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layer.setLayer(menu2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layerLayout = new javax.swing.GroupLayout(layer);
        layer.setLayout(layerLayout);
        layerLayout.setHorizontalGroup(
            layerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menu1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menu2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 3, Short.MAX_VALUE))
        );
        layerLayout.setVerticalGroup(
            layerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layerLayout.createSequentialGroup()
                .addComponent(menu1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menu2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        menu1.getAccessibleContext().setAccessibleParent(sMenu1);

        badgeLogo.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout badgeLogoLayout = new javax.swing.GroupLayout(badgeLogo);
        badgeLogo.setLayout(badgeLogoLayout);
        badgeLogoLayout.setHorizontalGroup(
            badgeLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        badgeLogoLayout.setVerticalGroup(
            badgeLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(subMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                    .addComponent(badgeLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(layer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(badgeLogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(subMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(layer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1404, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 801, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void sMenu1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sMenu1MouseEntered
        sMenu1.setBackground(Color.WHITE);
        sMenu1.setOpaque(true);

    }//GEN-LAST:event_sMenu1MouseEntered

    private void sMenu1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sMenu1MouseExited
        sMenu1.setBackground(new Color(255, 204, 51));
    }//GEN-LAST:event_sMenu1MouseExited

    private void sMenu2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sMenu2MouseExited
        sMenu2.setBackground(new Color(255, 204, 51));
    }//GEN-LAST:event_sMenu2MouseExited

    private void sMenu2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sMenu2MouseEntered
        sMenu2.setBackground(Color.WHITE);
        sMenu2.setOpaque(true);
    }//GEN-LAST:event_sMenu2MouseEntered

    private void sMenu3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sMenu3MouseExited
        sMenu3.setBackground(new Color(255, 204, 51));
    }//GEN-LAST:event_sMenu3MouseExited

    private void sMenu3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sMenu3MouseEntered
        sMenu3.setBackground(Color.WHITE);
        sMenu3.setOpaque(true);
    }//GEN-LAST:event_sMenu3MouseEntered

    private void sMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sMenu1MouseClicked
        this.menu1.setVisible(true);
        this.loadMenu1();
        disableMenuExcept("menu1");

    }//GEN-LAST:event_sMenu1MouseClicked

    private void sMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sMenu2MouseClicked
        this.menu2.setVisible(true);
        this.isLoadMenu2 = true;
        hideOrShowAddCash(false);
        this.loadMenu2();
        disableMenuExcept("menu2");
    }//GEN-LAST:event_sMenu2MouseClicked

    private void sMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sMenu3MouseClicked
        CashReport cash = new CashReport();
        cash.setVisible(true);
    }//GEN-LAST:event_sMenu3MouseClicked

    private void addCashValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCashValueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addCashValueActionPerformed

    private void addCashButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addCashButtonMouseClicked
        hideOrShowAddCash(true);
    }//GEN-LAST:event_addCashButtonMouseClicked

    private void cancelCashMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelCashMouseClicked
        hideOrShowAddCash(false);
    }//GEN-LAST:event_cancelCashMouseClicked

    private void saveCashMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveCashMouseClicked
        // String val = this.addCashValue
    }//GEN-LAST:event_saveCashMouseClicked

    private void saveCashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveCashActionPerformed
        int amount = Integer.parseInt(this.cashValue.getText());
        int amount2 = Integer.parseInt(this.addCashValue.getText());
        int total = amount + amount2;
        CashDaoImpl cashService = new CashDaoImpl();

        try {
            Collection<CashEntity> cashCheck = null;
            cashCheck = (Collection<CashEntity>) cashService.getAll();
            System.out.println("cek tur" + cashCheck);
            if (cashCheck.size() == 0) {
                CashEntity cash = new CashEntity();
                cash.setTotalAmount(String.valueOf(total));
                try {
                    cashService.create(cash);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
                this.cashValue.setText(cash.getTotalAmount());
            } else {
                cashCheck.forEach(c -> {
                    c.setTotalAmount(String.valueOf(total));
                    try {
                        cashService.update(c);

                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(this, ex.getMessage());
                    }
                    this.cashValue.setText(c.getTotalAmount());
                });

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }

        CashReportDaoImpl cashReportService = new CashReportDaoImpl();
        CashReportEntity cashReport = new CashReportEntity();
        cashReport.setPrevCash(String.valueOf(amount));
        cashReport.setTransaction(String.valueOf(amount2));
        cashReport.setType("ADD_CASH");
        cashReport.setAfterCash(String.valueOf(total));
        cashReport.setUser(this.username.getText());
        try {
            cashReportService.create(cashReport);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        } finally {
            hideOrShowAddCash(false);
        }
    }//GEN-LAST:event_saveCashActionPerformed

    private void tableReceiverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableReceiverMouseClicked
        if (evt.getClickCount() == 2 && evt.getButton() == MouseEvent.BUTTON1) {
            int column = tableReceiver.columnAtPoint(evt.getPoint());
            if (column == 3) {
                int row = tableReceiver.rowAtPoint(evt.getPoint());
                boolean isApproved = tableReceiver.getValueAt(row, column) == "Approved" ? true : false;
                if (!isApproved) {
                    int dialogApprove = JOptionPane.YES_NO_OPTION;
                    int dialogResult = JOptionPane.showConfirmDialog(null, "Do you wanna approve this one ?", "Warning", dialogApprove);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        ReceiverEntity receiver = new ReceiverEntity();
                        String name = (String) tableReceiver.getValueAt(row, 1);
                        String code = (String) tableReceiver.getValueAt(row, 2);
                        String temp = (String) tableReceiver.getValueAt(row, 4);
                        String id = temp.split(",")[0];
                        String eventId = temp.split(",")[1];
                        receiver.setId(Integer.parseInt(id));
                        receiver.setApproved(true);
                        receiver.setName(name);
                        receiver.setCode(code);
                        receiver.setEventId(Integer.parseInt(eventId));

                        ReceiverDaoImpl receiverService = new ReceiverDaoImpl();
                        try {
                            receiverService.update(receiver);
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(this, e.getMessage());
                        } finally {
                            loadMenu1();
                        }
                    }

                }
            }
        }
    }//GEN-LAST:event_tableReceiverMouseClicked

    private void createEventButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_createEventButtonMouseClicked
        CreateEvent createEvent = new CreateEvent();
        createEvent.setTotalCash(this.cashValue.getText());
        createEvent.setUsername(this.usernameVal);
        createEvent.setMenu(this);
        createEvent.setVisible(true);
    }//GEN-LAST:event_createEventButtonMouseClicked

    private void tableEventMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableEventMouseClicked
        if (evt.getClickCount() == 2 && evt.getButton() == MouseEvent.BUTTON1) {
            int row = tableEvent.rowAtPoint(evt.getPoint());
            String id = (String) this.tableEvent.getValueAt(row, 5);
            EventDetail eventDetail = new EventDetail();
            eventDetail.setEvent(id, this.role, this.cashValue.getText());
            eventDetail.renderAll();
            eventDetail.setVisible(true);
        }
    }//GEN-LAST:event_tableEventMouseClicked

    private void sMenu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sMenu4MouseClicked
        // TODO add your handling code here:
        User userScreen = new User();
        userScreen.setVisible(true);

    }//GEN-LAST:event_sMenu4MouseClicked

    private void sMenu4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sMenu4MouseExited
        sMenu4.setBackground(new Color(255, 204, 51));
    }//GEN-LAST:event_sMenu4MouseExited

    private void sMenu4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sMenu4MouseEntered
        sMenu4.setBackground(Color.WHITE);
        sMenu4.setOpaque(true);
    }//GEN-LAST:event_sMenu4MouseEntered

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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Menu menu = new Menu();
                menu.setVisible(true);
            }
        });
    }
    private String usernameVal;
    private String role;
    private boolean isLoadMenu1;
    private boolean isLoadMenu2;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addCashButton;
    private javax.swing.JTextField addCashValue;
    private javax.swing.JLabel amountLabel;
    private javax.swing.JLabel amountValue;
    private javax.swing.JPanel badgeLogo;
    private javax.swing.JButton cancelCash;
    private javax.swing.JLabel cashLabel;
    private javax.swing.JLabel cashValue;
    private javax.swing.JButton createEventButton;
    private javax.swing.JLabel endLabel;
    private javax.swing.JLabel endValue;
    private javax.swing.JLabel eventLabel;
    private javax.swing.JLabel eventValue;
    private javax.swing.JPanel header;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLayeredPane layer;
    private javax.swing.JPanel menu1;
    private javax.swing.JPanel menu2;
    private javax.swing.JLabel noStartLabel;
    private javax.swing.JLabel rpLabel;
    private javax.swing.JLabel sMenu1;
    private javax.swing.JLabel sMenu2;
    private javax.swing.JLabel sMenu3;
    private javax.swing.JLabel sMenu4;
    private javax.swing.JButton saveCash;
    private javax.swing.JLabel startLabel;
    private javax.swing.JLabel startValue;
    private javax.swing.JPanel subMenu;
    private javax.swing.JTable tableEvent;
    private javax.swing.JTable tableReceiver;
    private javax.swing.JLabel username;
    // End of variables declaration//GEN-END:variables
}
