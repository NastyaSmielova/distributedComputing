/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package po_7_2;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class MouseClick implements MouseListener{
        private final frame1 frame;
        public MouseClick(frame1 fr){
            frame = fr;
        }
        @Override
        public void mouseClicked(MouseEvent e) {
            Point point = new Point(e.getPoint());
            frame.setPoint(point);
            System.out.println("here was a click ! " + point.x + " " +point.y);
                    new Ball(point,frame).start();
        }

        @Override
        public void mousePressed(MouseEvent e) {
            
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
        
    }