/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krispeklaric.virgohospital_gui;

/**
 *
 * @author Kris
 */
public class OutpatientModule extends javax.swing.JFrame {

    /**
     * Creates new form OutpatientModule
     */
    public OutpatientModule() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPaneMain = new javax.swing.JTabbedPane();
        jPanelPatients = new javax.swing.JPanel();
        jPanelPersonel = new javax.swing.JPanel();
        jPanelDoctors = new javax.swing.JPanel();
        jPanelAppointments = new javax.swing.JPanel();
        jPanelBills = new javax.swing.JPanel();
        jPanelReports = new javax.swing.JPanel();
        jPanelTitleBackground = new javax.swing.JPanel();
        Title = new javax.swing.JLabel();
        jButtonClose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(247, 252, 255));

        jTabbedPaneMain.setBackground(new java.awt.Color(241, 240, 240));
        jTabbedPaneMain.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPaneMain.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        jPanelPatients.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanelPatientsLayout = new javax.swing.GroupLayout(jPanelPatients);
        jPanelPatients.setLayout(jPanelPatientsLayout);
        jPanelPatientsLayout.setHorizontalGroup(
            jPanelPatientsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1103, Short.MAX_VALUE)
        );
        jPanelPatientsLayout.setVerticalGroup(
            jPanelPatientsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 665, Short.MAX_VALUE)
        );

        jTabbedPaneMain.addTab("Patients", jPanelPatients);

        jPanelPersonel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanelPersonelLayout = new javax.swing.GroupLayout(jPanelPersonel);
        jPanelPersonel.setLayout(jPanelPersonelLayout);
        jPanelPersonelLayout.setHorizontalGroup(
            jPanelPersonelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1103, Short.MAX_VALUE)
        );
        jPanelPersonelLayout.setVerticalGroup(
            jPanelPersonelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 665, Short.MAX_VALUE)
        );

        jTabbedPaneMain.addTab("Personel", jPanelPersonel);

        jPanelDoctors.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanelDoctorsLayout = new javax.swing.GroupLayout(jPanelDoctors);
        jPanelDoctors.setLayout(jPanelDoctorsLayout);
        jPanelDoctorsLayout.setHorizontalGroup(
            jPanelDoctorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1103, Short.MAX_VALUE)
        );
        jPanelDoctorsLayout.setVerticalGroup(
            jPanelDoctorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 665, Short.MAX_VALUE)
        );

        jTabbedPaneMain.addTab("Doctor", jPanelDoctors);

        javax.swing.GroupLayout jPanelAppointmentsLayout = new javax.swing.GroupLayout(jPanelAppointments);
        jPanelAppointments.setLayout(jPanelAppointmentsLayout);
        jPanelAppointmentsLayout.setHorizontalGroup(
            jPanelAppointmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1103, Short.MAX_VALUE)
        );
        jPanelAppointmentsLayout.setVerticalGroup(
            jPanelAppointmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 665, Short.MAX_VALUE)
        );

        jTabbedPaneMain.addTab("Appointments", jPanelAppointments);

        javax.swing.GroupLayout jPanelBillsLayout = new javax.swing.GroupLayout(jPanelBills);
        jPanelBills.setLayout(jPanelBillsLayout);
        jPanelBillsLayout.setHorizontalGroup(
            jPanelBillsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1103, Short.MAX_VALUE)
        );
        jPanelBillsLayout.setVerticalGroup(
            jPanelBillsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 665, Short.MAX_VALUE)
        );

        jTabbedPaneMain.addTab("Bills", jPanelBills);

        javax.swing.GroupLayout jPanelReportsLayout = new javax.swing.GroupLayout(jPanelReports);
        jPanelReports.setLayout(jPanelReportsLayout);
        jPanelReportsLayout.setHorizontalGroup(
            jPanelReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1103, Short.MAX_VALUE)
        );
        jPanelReportsLayout.setVerticalGroup(
            jPanelReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 665, Short.MAX_VALUE)
        );

        jTabbedPaneMain.addTab("Reports", jPanelReports);

        jPanelTitleBackground.setBackground(new java.awt.Color(153, 204, 255));

        Title.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
        Title.setForeground(new java.awt.Color(0, 51, 51));
        Title.setText("VIRGO HOSPITAL - Outpatient module");

        jButtonClose.setBackground(new java.awt.Color(255, 153, 153));
        jButtonClose.setText("Close");
        jButtonClose.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButtonClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonCloseMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelTitleBackgroundLayout = new javax.swing.GroupLayout(jPanelTitleBackground);
        jPanelTitleBackground.setLayout(jPanelTitleBackgroundLayout);
        jPanelTitleBackgroundLayout.setHorizontalGroup(
            jPanelTitleBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTitleBackgroundLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonClose, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        jPanelTitleBackgroundLayout.setVerticalGroup(
            jPanelTitleBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTitleBackgroundLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanelTitleBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonClose, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelTitleBackground, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPaneMain)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanelTitleBackground, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPaneMain, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonCloseMouseClicked
        System.exit(0);
    }//GEN-LAST:event_jButtonCloseMouseClicked

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
            java.util.logging.Logger.getLogger(OutpatientModule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OutpatientModule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OutpatientModule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OutpatientModule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OutpatientModule().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Title;
    private javax.swing.JButton jButtonClose;
    private javax.swing.JPanel jPanelAppointments;
    private javax.swing.JPanel jPanelBills;
    private javax.swing.JPanel jPanelDoctors;
    private javax.swing.JPanel jPanelPatients;
    private javax.swing.JPanel jPanelPersonel;
    private javax.swing.JPanel jPanelReports;
    private javax.swing.JPanel jPanelTitleBackground;
    private javax.swing.JTabbedPane jTabbedPaneMain;
    // End of variables declaration//GEN-END:variables
}