/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package po_7_1_1;

import com.sun.corba.se.impl.orbutil.concurrent.Mutex;
import java.awt.Point;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;



class Duck extends Thread{
        private final int ID;
        private final frame1 frame;
        private int speed;
        private JLabel label;
        Point pressed;
        Point position;
        private Boolean isAlive;
        private Mutex mutex = new Mutex();
         private Random rand = new Random() ;    

        
        private void createDuck(){
            speed = rand.nextInt(10) + 1;
            position = new Point(0,ID*130);
            isAlive = true;
            ImageIcon imageIcon = new ImageIcon(frame.getClass().getResource("duck.gif"));
            int h = imageIcon.getIconHeight();
            int w = imageIcon.getIconWidth();
            label.setBounds(position.x,position.y, w, h);
            label.setIcon(imageIcon);
            label.setVisible(true);
            System.out.println(ID + "   " + position.x + " " + position.y + " " + (position.x + w) + " " + (position.y + h));
            
        }
        public Duck(int num,frame1 fr){
            ID = num;
            frame = fr;
            label = new JLabel();
                        frame.add(label);

            pressed = new Point();
            createDuck();
            clearPoint();
        }
        private void clearPoint(){
            pressed = new Point(-1,-1);
        }
        
        public void setPoint(Point p_){
            pressed = p_;
            System.out.println(ID + " receive " + p_.x + " "+p_.y);
            haveToDie();
            clearPoint();
          
        }
        private void haveToDie(){
            try {
                mutex.acquire();
            } catch (InterruptedException ex) {
            }
            if (pressed.x < (position.x+label.getWidth()) && pressed.x > (position.x)){
                if (pressed.y > (position.y) && pressed.y < (position.y+label.getHeight())){
                    isAlive = false;
                }
            }
            mutex.release();
            //isAlive = true;
        }
        
        @Override
        public void run() {
            
           System.out.println("create " + ID);
           int i = 0;
           while (true){
               
               if (isAlive){
                position.x = position.x + speed * i++;
                if (position.x > frame.getWidth()+label.getWidth()) {
                    position.x = 0;
                    i = 0;
                }
               label.setLocation(position.x,position.y );
                try {
                    Thread.sleep(150);

                } catch (InterruptedException ex) {
                    Logger.getLogger(frame1.class.getName()).log(Level.SEVERE, null, ex);
                }
               }else{
                   System.out.println("died "+ID);
                   label.setVisible(false);
                   try {
                    Thread.sleep(2000);

                } catch (InterruptedException ex) {
                }
                   createDuck();
                   i = 0;
               }
           }
           
           
           
        }
}