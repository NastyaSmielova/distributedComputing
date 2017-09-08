/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package po_10_1;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

class SimpleErrorHandler implements ErrorHandler { 
    public void warning(SAXParseException e) throws SAXException 
    {         System.err.println("Строка " +e.getLineNumber() + ":");
    System.out.println(e.getMessage());
    }    

    @Override
    public void error(SAXParseException exception) throws SAXException {
        System.err.println("Строка " +exception.getLineNumber() + ":");      
        System.out.println(exception.getMessage());   
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
    System.err.println("Строка " + exception.getLineNumber() + ":");    
   System.out.println(exception.getMessage());    
    }
 }    

