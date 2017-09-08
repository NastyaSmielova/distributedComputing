
package po_6_2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

public class NewJFrame extends javax.swing.JFrame {
    private int  [][] array ;
    private final int width,height;
    private final int div;
    private final Graphics grap;
    private final WorkQueue wq;
    private final int numThreads = 5;
    private int [][] arrayAdd;
    private final int divArray;
    private AtomicInteger hasFinished;
    public NewJFrame() {
        initComponents();
        setVisible(true);
        wq = new WorkQueue(numThreads);

        jPanel1.setBackground(Color.DARK_GRAY);
        jPanel1.setVisible(true);
        jPanel1.setSize(800,600);
        jPanel1.setLocation(new Point(0,0));
        this.setBackground(Color.DARK_GRAY);
        this.setResizable(false);
 
        grap = jPanel1.getGraphics();
        width = jPanel1.getWidth();
        height = jPanel1.getHeight() ;
        div = 5;

        divArray = width / numThreads;
        array = new int[width/div][height/div];
        arrayAdd = new int[width/div][height/div];
        jPanel1.setBackground(Color.DARK_GRAY);
        initColony();
       updateColony();
        repaint();


    }
    private void initColony(){
        Random rand = new Random();
        int color;
        for (int i = 0; i< width/div;i++)
            for (int j = 0; j< height/div;j++){
                int v = rand.nextInt(20);
                if (v % 5 == 0 ) v = 0;
                if (v % 2 == 0 ) v = 0;
                if (v % 3 == 0 ) v = 0;
                if (v % 7 == 0 ) v = 0;
                if (v % 11 == 0 ) v = 0;
                if (v % 13 == 0 ) v = 0;
             // if (v % 17 == 0 ) v = 0;
               // if (v % 19 == 0 ) v = 0;

                if (v != 0){
                  //  System.out.print(v+" ");
                    color = rand.nextInt(2);
                    if (color == 0) v = 1;
                    else
                    v = 2;

                }
                array[i][j] = v;
            }
    }

    class updateLine implements Runnable{
        private int numb;
        public updateLine(int numThread){
            numb = numThread;
        }
        @Override
         public void run(){
              System.out.println("start " + numb);
             for (int i = numb * divArray; i < width/div && i < divArray * ( numb + 1); i++)
                 
            for (int j = 0; j< height/div;j++){
                int sumFirst = 0; 
                int sumSecond = 0;
                if (i > 0){
                    if (j > 0) { if (array[i-1][j-1] == 1) sumFirst++;}
                    if (array[i-1][j] == 1) sumFirst++;
                    if (j < height/div - 1) { if (array[i-1][j+1] == 1) sumFirst++;}
                    
                    if (j > 0) { if (array[i-1][j-1] == 2) sumSecond++;}
                    if (array[i-1][j] == 2) sumSecond++;
                    if (j < height/div - 1) { if (array[i-1][j+1] == 2) sumSecond++;}
                }
                if (i < width/div - 1 ){
                   if (j > 0) { if (array[i+1][j-1]  == 1) sumFirst++;}
                    if (array[i+1][j]  == 1) sumFirst++;
                    if (j < height/div - 1) { if (array[i+1][j+1]  == 1) sumFirst++;} 
                    
                    if (j > 0) { if (array[i+1][j-1]  == 2) sumSecond++;}
                    if (array[i+1][j]  == 2) sumSecond++;
                    if (j < height/div - 1) { if (array[i+1][j+1]  == 2) sumSecond++;} 
                }
                if (j > 0) { if (array[i][j-1]  == 1) sumFirst++;}
                if (j < height/div - 1) { if (array[i][j+1]  == 1) sumFirst++;} 
                
                if (j > 0) { if (array[i][j-1]  == 2) sumSecond++;}
                if (j < height/div - 1) { if (array[i][j+1]  == 2) sumSecond++;} 
            
                arrayAdd[i][j] = 0;
                if (sumFirst == 3 )arrayAdd[i][j] = 1;
                if (sumFirst == 2 &&  array[i][j] == 1)arrayAdd[i][j] = 1;
                if (sumSecond == 3 )arrayAdd[i][j] = 2;
                if (sumSecond == 2 &&  array[i][j] == 2)arrayAdd[i][j] = 2;
            }
                           System.out.println("stop " + numb);
                           hasFinished.incrementAndGet();

         }
    }
    
    
    private void updateColony(){
        hasFinished = new AtomicInteger(0);
        for (int i = 0; i < numThreads; i++) wq.execute(new updateLine(i));
        while (hasFinished.get()!= numThreads){}
        System.out.println("stop doing ");         
    }
    private void drawColony(){
            grap.setColor(Color.BLUE);
            for (int i = 0; i < width/div;i++)
                for (int j = 0; j< height/div;j++){
                    if (arrayAdd[i][j] == 1){
                        grap.setColor(Color.BLUE);
                        grap.fillRect(i*div, j*div,div, div);
                    }
                    if (arrayAdd[i][j] == 2){
                        grap.setColor(Color.RED);
                        grap.fillRect(i*div, j*div,div, div);
                    }
                     array[i][j] = arrayAdd[i][j];
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 820, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
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

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
        // jPanel1.setBackground(Color.red);
         System.out.println("jkbk");
       
       // add(rects);
        //rects.setVisible(true);
        
    }//GEN-LAST:event_formMouseClicked
    @Override
    public void repaint(){
        
        while(true){
          //  super.repaint();
            drawColony();
                        updateColony();

            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
             grap.clearRect(0, 0, width, height);
        }
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
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
