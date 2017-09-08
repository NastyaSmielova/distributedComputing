/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package po_7_2;

import java.awt.Point;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

    class Ball extends Thread{
        private final frame1 frame;
        private int speed;
        private JLabel label;
        Point position;
        private Boolean isAlive;
        Random rand = new  Random();

        public Ball(Point pos,frame1 fr){
            speed = rand.nextInt(10) + 1;
            isAlive = true;
            label = new JLabel();
            ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("ball.gif"));
            int h = imageIcon.getIconHeight();
            int w = imageIcon.getIconWidth();
            position = new Point(pos.x,fr.getHeight() - 2*h);

            label.setBounds(position.x, position.y,w, h);
            label.setIcon(imageIcon);
            label.setVisible(true);
            frame = fr;
            frame.add(label);
        }
        @Override
        public void run() {
            while (position.y > 0){
                position.y --;
                label.setLocation(position);
                if(position.y <= 0) label.setVisible(false);
               frame.setPoint(position);
                try {
                    Thread.sleep(5);

                } catch (InterruptedException ex) {
                    Logger.getLogger(frame1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            frame.remove(label);
        }
        
    }