/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msb.app.management.system.bansos.screen;

import com.msb.app.management.system.bansos.helper.HibernateSessionFactory;
import com.msb.app.management.system.bansos.model.BansosDaoImpl;
import com.msb.app.management.system.bansos.model.BansosEntity;
import java.awt.Color;
import java.sql.SQLException;
import javax.swing.JOptionPane;

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
        sMenu2 = new javax.swing.JPanel();
        sMenu1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        sMenu3 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        sMenu4 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        sMenu5 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        baseMenu = new javax.swing.JPanel();
        baseMenu1 = new javax.swing.JPanel();
        eventLabel = new javax.swing.JLabel();
        eventName = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        baseMenu2 = new javax.swing.JPanel();
        baseMenu3 = new javax.swing.JPanel();
        baseMenu4 = new javax.swing.JPanel();
        badgeLogo = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        sMenu2.setBackground(new java.awt.Color(255, 204, 51));
        sMenu2.setPreferredSize(new java.awt.Dimension(190, 700));

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

        sMenu3.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        sMenu3.setText("Home");
        sMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sMenu3MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sMenu3MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sMenu3MouseEntered(evt);
            }
        });

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        sMenu4.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        sMenu4.setText("Home");
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

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));

        sMenu5.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        sMenu5.setText("Home");
        sMenu5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sMenu5MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sMenu5MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sMenu5MouseEntered(evt);
            }
        });

        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout sMenu2Layout = new javax.swing.GroupLayout(sMenu2);
        sMenu2.setLayout(sMenu2Layout);
        sMenu2Layout.setHorizontalGroup(
            sMenu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sMenu2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sMenu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sMenu1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addComponent(sMenu3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                    .addComponent(jSeparator2)
                    .addComponent(sMenu4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                    .addComponent(jSeparator3)
                    .addComponent(sMenu5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                    .addComponent(jSeparator4))
                .addContainerGap())
        );
        sMenu2Layout.setVerticalGroup(
            sMenu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sMenu2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sMenu1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sMenu3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sMenu4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sMenu5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(472, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 204, 102));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        baseMenu.setBackground(new java.awt.Color(255, 255, 255));

        baseMenu1.setBackground(new java.awt.Color(204, 255, 255));

        eventLabel.setText("Event :");

        jLabel1.setText("Start :");

        jLabel2.setText("End :");

        javax.swing.GroupLayout baseMenu1Layout = new javax.swing.GroupLayout(baseMenu1);
        baseMenu1.setLayout(baseMenu1Layout);
        baseMenu1Layout.setHorizontalGroup(
            baseMenu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(baseMenu1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(baseMenu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(baseMenu1Layout.createSequentialGroup()
                        .addGroup(baseMenu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(eventLabel)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(eventName)))
                .addContainerGap(942, Short.MAX_VALUE))
        );
        baseMenu1Layout.setVerticalGroup(
            baseMenu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(baseMenu1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(baseMenu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eventLabel)
                    .addComponent(eventName))
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addContainerGap(590, Short.MAX_VALUE))
        );

        baseMenu2.setBackground(new java.awt.Color(255, 102, 255));

        javax.swing.GroupLayout baseMenu2Layout = new javax.swing.GroupLayout(baseMenu2);
        baseMenu2.setLayout(baseMenu2Layout);
        baseMenu2Layout.setHorizontalGroup(
            baseMenu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 986, Short.MAX_VALUE)
        );
        baseMenu2Layout.setVerticalGroup(
            baseMenu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 695, Short.MAX_VALUE)
        );

        baseMenu3.setBackground(new java.awt.Color(255, 0, 51));

        javax.swing.GroupLayout baseMenu3Layout = new javax.swing.GroupLayout(baseMenu3);
        baseMenu3.setLayout(baseMenu3Layout);
        baseMenu3Layout.setHorizontalGroup(
            baseMenu3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 998, Short.MAX_VALUE)
        );
        baseMenu3Layout.setVerticalGroup(
            baseMenu3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 694, Short.MAX_VALUE)
        );

        baseMenu4.setBackground(new java.awt.Color(0, 255, 51));

        javax.swing.GroupLayout baseMenu4Layout = new javax.swing.GroupLayout(baseMenu4);
        baseMenu4.setLayout(baseMenu4Layout);
        baseMenu4Layout.setHorizontalGroup(
            baseMenu4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 998, Short.MAX_VALUE)
        );
        baseMenu4Layout.setVerticalGroup(
            baseMenu4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 694, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout baseMenuLayout = new javax.swing.GroupLayout(baseMenu);
        baseMenu.setLayout(baseMenuLayout);
        baseMenuLayout.setHorizontalGroup(
            baseMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(baseMenu1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(baseMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(baseMenuLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(baseMenu2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(baseMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(baseMenuLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(baseMenu3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(baseMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(baseMenuLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(baseMenu4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        baseMenuLayout.setVerticalGroup(
            baseMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(baseMenu1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(baseMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(baseMenuLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(baseMenu2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(baseMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(baseMenuLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(baseMenu3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(7, Short.MAX_VALUE)))
            .addGroup(baseMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(baseMenuLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(baseMenu4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(7, Short.MAX_VALUE)))
        );

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
                    .addComponent(sMenu2, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                    .addComponent(badgeLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(baseMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(badgeLogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sMenu2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(baseMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 699, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void sMenu3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sMenu3MouseExited
        sMenu3.setBackground(new Color(255, 204, 51));
    }//GEN-LAST:event_sMenu3MouseExited

    private void sMenu3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sMenu3MouseEntered
        sMenu3.setBackground(Color.WHITE);
        sMenu3.setOpaque(true);
    }//GEN-LAST:event_sMenu3MouseEntered

    private void sMenu4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sMenu4MouseExited
        sMenu4.setBackground(new Color(255, 204, 51));
    }//GEN-LAST:event_sMenu4MouseExited

    private void sMenu4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sMenu4MouseEntered
        sMenu4.setBackground(Color.WHITE);
        sMenu4.setOpaque(true);
    }//GEN-LAST:event_sMenu4MouseEntered

    private void sMenu5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sMenu5MouseExited
        sMenu5.setBackground(new Color(255, 204, 51));
    }//GEN-LAST:event_sMenu5MouseExited

    private void sMenu5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sMenu5MouseEntered
        sMenu5.setBackground(Color.WHITE);
        sMenu5.setOpaque(true);
    }//GEN-LAST:event_sMenu5MouseEntered

    private void sMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sMenu1MouseClicked
        baseMenu1.setVisible(true);
        baseMenu2.setVisible(false);
        baseMenu3.setVisible(false);
        baseMenu4.setVisible(false);
    }//GEN-LAST:event_sMenu1MouseClicked

    private void sMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sMenu3MouseClicked
        baseMenu2.setVisible(true);
        baseMenu1.setVisible(false);
        baseMenu3.setVisible(false);
        baseMenu4.setVisible(false);
    }//GEN-LAST:event_sMenu3MouseClicked

    private void sMenu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sMenu4MouseClicked
        baseMenu3.setVisible(true);
        baseMenu1.setVisible(false);
        baseMenu2.setVisible(false);
        baseMenu4.setVisible(false);
    }//GEN-LAST:event_sMenu4MouseClicked

    private void sMenu5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sMenu5MouseClicked
        baseMenu4.setVisible(true);
        baseMenu1.setVisible(false);
        baseMenu2.setVisible(false);
        baseMenu3.setVisible(false);
    }//GEN-LAST:event_sMenu5MouseClicked

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
               Menu menu =  new Menu();
               menu.setVisible(true);
               menu.baseMenu.setVisible(true);
               menu.baseMenu1.setVisible(true);
               menu.baseMenu2.setVisible(false);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel badgeLogo;
    private javax.swing.JPanel baseMenu;
    private javax.swing.JPanel baseMenu1;
    private javax.swing.JPanel baseMenu2;
    private javax.swing.JPanel baseMenu3;
    private javax.swing.JPanel baseMenu4;
    private javax.swing.JLabel eventLabel;
    private javax.swing.JLabel eventName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel sMenu1;
    private javax.swing.JPanel sMenu2;
    private javax.swing.JLabel sMenu3;
    private javax.swing.JLabel sMenu4;
    private javax.swing.JLabel sMenu5;
    // End of variables declaration//GEN-END:variables
}
