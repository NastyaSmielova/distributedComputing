/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package po_7_2;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author pc
 */
public class Listener implements KeyListener{

    
     private final frame1 frame;
        public Listener(frame1 fr){
            frame = fr;
        }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == ' '){
            frame.setPoint(frame.hunterPoint);

            System.out.println("piupiu!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            new Ball(frame.hunterPoint,frame).start();

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}
