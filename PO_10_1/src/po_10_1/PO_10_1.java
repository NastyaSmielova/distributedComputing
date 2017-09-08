/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package po_10_1;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;


public class PO_10_1 {

    
    public PO_10_1(){
        
    }
    
    public static void main(String[] args) {
        Map map = new Map();
     
        try {
            map.loadFromFile("map.xml");
            map.printAll();
        //    map.saveToFile("map2.xml");
            //PO_10_1 po_10_1 = new PO_10_1();
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(PO_10_1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
