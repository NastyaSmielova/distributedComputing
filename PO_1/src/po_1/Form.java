
package po_1;

public class Form extends javax.swing.JFrame {
Boolean isRunning = false;
Boolean canceled = false;


    class Dec extends Thread {

        public Dec() {
        }
 
        @Override
        public void run() {
                System.out.println("dec  prior" + Thread.currentThread().getPriority());
                while (true){
                    if (!canceled){
                        setValue(false);
                        try {
                            Thread.sleep(5);
                        } catch (Exception e) {
                        }
                    }else {isRunning = false;break;}
                    }
                   System.out.println("dec  stop" );  
        }
    }
     

        class Inc extends Thread {
            public Inc() {
            }

            @Override
            public void run() {

                  System.out.println("inc  prior" + Thread.currentThread().getPriority());

                    while (true){
                    if (!canceled){

                        setValue(true);
                        try {
                            Thread.sleep(5);
                        } catch (Exception e) {
                        }
                    }else {isRunning = false;break;}
                    }
               System.out.println("inc  stop" );  
            }
        }
    Thread inc;
    Thread dec;
     public  void setValue(Boolean isInc) {
        synchronized(jSlider1){
            int val = jSlider1.getValue();
            if (isInc){ 
                val++;
            }else {
                 val--;
            }
            if (val < 10) val = 10;
            if (val > 90) val = 90;
            jSlider1.setValue(val);
        }
    }
    
    public Form() {
        initComponents();
        setVisible(true);
        jSlider1.setMaximum(100);
        jSlider1.setMinimum(0);
        jSlider1.setValue(50); 
        jSlider1.updateUI();
         
        jSlider1.setMinorTickSpacing(10);
        jSlider1.setMajorTickSpacing(10);
        jSlider1.setPaintTicks(true);
        jSlider1.setPaintLabels(true); 

        jSlider2.setMinorTickSpacing(1);
        jSlider2.setMajorTickSpacing(1);
        jSlider2.setPaintTicks(true);
        jSlider2.setPaintLabels(true);

         
        jSlider2.setValue(5);
        jSlider2.setMaximum(10);
        jSlider2.setMinimum(0);
        
           
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jSlider1 = new javax.swing.JSlider();
        jButton2 = new javax.swing.JButton();
        jSlider2 = new javax.swing.JSlider();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Start");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jButton2.setText("Stop");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
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
                .addGap(119, 119, 119)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 352, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(87, 87, 87))
            .addGroup(layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addComponent(jSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(307, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
       if (!isRunning){
            System.out.println("start working");
            canceled = false;
            isRunning = true;
            inc = new Inc();
            dec = new Dec();
            int priorInc = jSlider2.getValue();
            if (priorInc % 10 != 0){
                inc.setPriority(priorInc);
                dec.setPriority(10 - priorInc);
            }
            else{
                if (priorInc == 0){
                    inc.setPriority(Thread.MIN_PRIORITY);
                    dec.setPriority(Thread.MAX_PRIORITY);
                }else{
                    dec.setPriority(Thread.MIN_PRIORITY);
                    inc.setPriority(Thread.MAX_PRIORITY);
                }
            }
            inc.start();
            dec.start();
        }
            
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       if (isRunning) {
            canceled = true;
            System.out.println("stop working");

        }
    }//GEN-LAST:event_jButton2ActionPerformed

                                        

        
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

        /* Create and display the form */
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JSlider jSlider2;
    // End of variables declaration//GEN-END:variables
}
