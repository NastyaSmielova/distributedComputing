/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package po_10_5_1;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

/**
 *
 * @author pc
 */
public class MyMessageListener implements javax.jms.MessageListener{

    static String response;

    @Override
    public void onMessage(Message msg) {
         TextMessage textmessage=(TextMessage)msg;
        try {
           // System.out.println("receive  "  + textmessage.getText());
            this.response =  textmessage.getText();
        } catch (JMSException ex) {
        }
    }
    public String getResponse(){
        System.out.println("getResponse "  + response);
        String ret = response;
        response = null;
        return ret;
    }
    
}
