/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.myrmi.Client;

/**
 *
 * @author pc
 */
public class MainForm extends javax.swing.JFrame {

    /**
     * Creates new form MainForm
     */
    
    Client client = null;
    public void setMap(Client client){
        this.client = client;
    }
    
    public MainForm() {
           try { 
               UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"); 
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) { 
                       System.err.println(ex);

        }
        initComponents();
        ImageIcon newIcon = new ImageIcon("map1.jpg");
        newIcon.getImage().flush();
        jLabel1.setIcon(newIcon);
        this.setResizable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        showButton = new javax.swing.JButton();
        addCountryButton = new javax.swing.JButton();
        addCityButton = new javax.swing.JButton();
        deleteCityButton = new javax.swing.JButton();
        deleteCountryButton = new javax.swing.JButton();
        modCountry = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        shCount = new javax.swing.JButton();
        shCityFromCountry = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        showButton.setText("show cities");
        showButton.setActionCommand("show cities");
        showButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                showButtonMousePressed(evt);
            }
        });

        addCountryButton.setText("addCountry");
        addCountryButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                addCountryButtonMousePressed(evt);
            }
        });

        addCityButton.setText("addCity");
        addCityButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                addCityButtonMousePressed(evt);
            }
        });

        deleteCityButton.setText("deleteCity");
        deleteCityButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                deleteCityButtonMousePressed(evt);
            }
        });

        deleteCountryButton.setText("deleteCountry");
        deleteCountryButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                deleteCountryButtonMousePressed(evt);
            }
        });

        modCountry.setText("modify");
        modCountry.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                modCountryMousePressed(evt);
            }
        });

        shCount.setText(" show country");
        shCount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                shCountMousePressed(evt);
            }
        });

        shCityFromCountry.setText("city fr County");
        shCityFromCountry.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                shCityFromCountryMousePressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(showButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addCountryButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addCityButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(deleteCityButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(deleteCountryButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(modCountry, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(shCount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(shCityFromCountry, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {addCityButton, addCountryButton, deleteCityButton, deleteCountryButton, modCountry, shCityFromCountry, shCount, showButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addCountryButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addCityButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteCityButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteCountryButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(modCountry)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(shCount, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(shCityFromCountry, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {addCityButton, addCountryButton, deleteCityButton, deleteCountryButton, modCountry, shCityFromCountry, shCount, showButton});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void showButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showButtonMousePressed
        ShowForm sf = new ShowForm();
        sf.setVisible(true);
        sf.SetText(client.showAllCities());
    }//GEN-LAST:event_showButtonMousePressed

    private void addCountryButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addCountryButtonMousePressed
        addCountry add = new addCountry();
        add.setMap(client);
        add.setVisible(true);
    }//GEN-LAST:event_addCountryButtonMousePressed

    private void addCityButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addCityButtonMousePressed
        addCity add = new addCity();
        add.setMap(client);
        add.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_addCityButtonMousePressed

    private void deleteCityButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteCityButtonMousePressed
        // TODO add your handling code here:
        delete del = new delete();
        del.setMap(client);
        del.setDelete(true);
        del.setVisible(true);
    }//GEN-LAST:event_deleteCityButtonMousePressed

    private void deleteCountryButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteCountryButtonMousePressed
       delete del = new delete();
        del.setMap(client);
        del.setDelete(false);
        del.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteCountryButtonMousePressed

    private void modCountryMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modCountryMousePressed
        modify mod = new modify();
        mod.setMap(client);
        mod.setVisible(true);
    }//GEN-LAST:event_modCountryMousePressed

    private void shCountMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_shCountMousePressed
        ShowForm sf = new ShowForm();
        sf.setVisible(true);
        sf.SetText(client.showAllCountries());
    }//GEN-LAST:event_shCountMousePressed

    private void shCityFromCountryMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_shCityFromCountryMousePressed
        chooseCountry add = new chooseCountry();
        add.setMap(client);
        add.setVisible(true);
    }//GEN-LAST:event_shCityFromCountryMousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
           try { 
    UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"); 
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) { 
           System.err.println(ex);
        }
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
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addCityButton;
    private javax.swing.JButton addCountryButton;
    private javax.swing.JButton deleteCityButton;
    private javax.swing.JButton deleteCountryButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton modCountry;
    private javax.swing.JButton shCityFromCountry;
    private javax.swing.JButton shCount;
    private javax.swing.JButton showButton;
    // End of variables declaration//GEN-END:variables
}
