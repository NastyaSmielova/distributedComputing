
package po_1_3;


public class Form extends javax.swing.JFrame {
    Boolean isRunning = false;
    Boolean stopInc = true;
    Boolean stopDec = true;
    Integer value = 0;
    Integer sem = new Integer(1);

    class Dec extends Thread {

        public Dec() {
        }
 
        @Override
        public void run() {

                    System.out.println("dec starts" );
                    while (true){
                        if (!stopDec){
                            value--;
                            setValue();
                            try {
                                Thread.sleep(1);
                            } catch (Exception e) {
                            }
                        }else {isRunning = false; break;}
                        }
                    System.out.println("dec  stop" );  
                    sem = 1;
                }
        }   
        
    
     

        class Inc extends Thread {
            public Inc() {
            }

            @Override
            public void run() {
                        System.out.println("inc  starts" );
                        while (true){
                            if (!stopInc){
                                value++;
                                setValue();
                                try {
                                    Thread.sleep(1);
                                } catch (Exception e) {
                                }
                            }else {isRunning = false; break;}
                            }
                        System.out.println("inc  stop" );  
                        sem = 1;
                    }
        }
    Thread inc;
    Thread dec;
     public synchronized void setValue() {
       if (value > 90) value = 90;
       if (value < 10) value = 10;
       jSlider1.setValue(value);
    }
    
    public Form() {
        initComponents();
        setVisible(true);
        value = new Integer(50);
        jSlider1.setMaximum(100);
        jSlider1.setMinimum(0);
        jSlider1.setValue(50); 
        jSlider1.updateUI();
         
        jSlider1.setMinorTickSpacing(10);
        jSlider1.setMajorTickSpacing(10);
        jSlider1.setPaintTicks(true);
        jSlider1.setPaintLabels(true);  
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Start1 = new javax.swing.JButton();
        jSlider1 = new javax.swing.JSlider();
        Stop1 = new javax.swing.JButton();
        Start2 = new javax.swing.JButton();
        Stop2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Start1.setText("Start1");
        Start1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Start1MouseClicked(evt);
            }
        });

        Stop1.setText("Stop1");
        Stop1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Stop1ActionPerformed(evt);
            }
        });

        Start2.setText("Start2");
        Start2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Start2ActionPerformed(evt);
            }
        });

        Stop2.setText("Stop2");
        Stop2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Stop2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSlider1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Start2)
                    .addComponent(Start1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 350, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Stop1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Stop2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(87, 87, 87))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Start1)
                    .addComponent(Stop1))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(Start2))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Stop2)))
                .addContainerGap(319, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void Start1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Start1MouseClicked
       if (sem == 1){
           sem = 0;
            System.out.println("Dec start working");
            stopDec = false;
            isRunning = true;
            dec = new Dec();
            dec.setPriority(Thread.MIN_PRIORITY);
            dec.start();
        }
       else {
        //   System.out.println("Inc is working. Can't create Dec");
       }      
    }//GEN-LAST:event_Start1MouseClicked

    private void Stop1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Stop1ActionPerformed
       if (!stopDec) {
            stopDec = true;
            System.out.println("Dec stop working");

        }
    }//GEN-LAST:event_Stop1ActionPerformed

    private void Stop2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Stop2ActionPerformed
        // TODO add your handling code here:
         if (!stopInc) {
            stopInc = true;
            System.out.println("Inc stop working");

        }
    }//GEN-LAST:event_Stop2ActionPerformed

    private void Start2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Start2ActionPerformed
        // TODO add your handling code here:
        if (sem == 1){
            sem = 0;
            System.out.println("Inc start working");
            stopInc = false;
            isRunning = true;
            inc = new Inc();
            inc.setPriority(Thread.MAX_PRIORITY);
            inc.start();
        }else {
           //System.out.println("Dec is working. Can't create Inc");
       }
    }//GEN-LAST:event_Start2ActionPerformed

                                        

        
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
            java.util.logging.Logger.getLogger(Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Start1;
    private javax.swing.JButton Start2;
    private javax.swing.JButton Stop1;
    private javax.swing.JButton Stop2;
    private javax.swing.JSlider jSlider1;
    // End of variables declaration//GEN-END:variables
}
