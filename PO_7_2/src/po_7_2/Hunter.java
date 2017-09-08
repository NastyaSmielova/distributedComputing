/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package po_7_2;

import java.awt.Point;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

class Hunter extends Thread{
        private final frame1 frame;
        private int speed;
        private JLabel label;
        Point position;
        private Boolean isInc;
        Random rand = new  Random();

        public Hunter(Point pos,frame1 fr){
            label = new JLabel();
            isInc  = true;
            ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("ball.gif"));
            int h = imageIcon.getIconHeight();
            int w = imageIcon.getIconWidth();
            position = new Point(0,fr.getHeight() - 2*h);
            label.setBounds(position.x, position.y,w, h);
            label.setIcon(imageIcon);
            label.setVisible(true);
            frame = fr;
            frame.add(label);
        }
        @Override
        public void run() {
            while (true){
                if (isInc){ position.x++;}
                else position.x--;
                if (position.x == frame.getWidth()){
                    isInc = false;
                    position.x --;
                }
                if (position.x == 0){
                    isInc = true;
                    position.x ++;
                }
                label.setLocation(position);
                frame.setHunterPoint(position);
                try {
                    Thread.sleep(5);

                } catch (InterruptedException ex) {
                }
            }
            
        }
        
    }