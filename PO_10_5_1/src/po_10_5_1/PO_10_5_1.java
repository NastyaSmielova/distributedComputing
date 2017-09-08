/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package po_10_5_1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.swing.JOptionPane;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 *
 * @author pc
 */
public class PO_10_5_1 {
    private static JMSWindow windowjms; // экземпляр нашего JFrame окна.
    
    private static ActiveMQConnectionFactory connectionFactory=null; //управляемый объект от ApacheMQ
//служащий для создания объекта Connection.
    
    private static Connection connection=null; //сам Connection.
    
    private static Session session; //контекст для посылки и приема сообщений.
    
    private static Destination destination; //буфер отправки и получения сообщений.
    
    private static String queueRequest=null; 
private static String queueResponse=null; //имя очереди или топика.
    /**
     * @param args the command line arguments
     */
      public static void clickSendButton(){
        //получаем имя очереди к которой необходимо подключитсья
        queueRequest=windowjms.getQueueSendName().equals("")?"RequestQueue":windowjms.getQueueSendName();
        if (Connected() && !windowjms.getMessageSendText().equals("")){
            windowjms.SendConnectedSucces();
            if (windowjms.isPtP()){
                destination=getDestinationQueueRequest();
            }else{
                destination=getDestinationTopic();
            }
            if (destination!=null){
                try {
                    MessageProducer producer = session.createProducer(destination);
                    producer.setDeliveryMode(DeliveryMode.PERSISTENT);//парметром PERSISTENT указываем что сообщение
                    //будет хранится до тех пор пока не будет доставлено адресату.
                    //Создаем текстовое сообщение.
                    TextMessage message =session.createTextMessage(windowjms.getMessageSendText());
                    producer.send(message);
                    windowjms.SendSuccess();
                } catch (JMSException ex) {
                    windowjms.SendError();
                }
            }else{ windowjms.SendError();}
        }else{windowjms.SendError();}
        
        
        queueRequest="ResponseQueue";
        if (Connected()){
                destination=getDestinationQueueResponse();
            
           if (destination!=null){
                try {
                    MessageConsumer consumer=session.createConsumer(destination);
                    consumer.setMessageListener(new MessageListener() {

                        @Override
                        public void onMessage(Message msg) {
                            TextMessage textmessage=(TextMessage)msg;
                            try {
                                        JOptionPane.showMessageDialog(null,textmessage.getText());
                            } catch (JMSException ex) {
                            }
                        }

                        
                    });
                } catch (JMSException ex) {
                }
           }
        }
        
        
    }
     public static void clickReceivedButton(){
        queueRequest=windowjms.getQueueSendName().equals("")?"RequestQueue":windowjms.getQueueSendName();
        if (Connected()){
           if (windowjms.isPtP()){
                destination=getDestinationQueueRequest();
            }else{
                destination=getDestinationTopic();
            }
           if (destination!=null){
                try {
                    MessageConsumer consumer=session.createConsumer(destination);
                    consumer.setMessageListener(new MessageListener() {

                        @Override
                        public void onMessage(Message msg) {
                            TextMessage textmessage=(TextMessage)msg;
                            try {
                                windowjms.setTextReceiver(textmessage.getText());
                            } catch (JMSException ex) {
                            }
                        }

                        
                    });
                    windowjms.ReceivedConnectedSucces();
                } catch (JMSException ex) {
                    windowjms.ReceivedConnectedClose();
                }
           }else{windowjms.ReceivedConnectedClose();}
        }else{windowjms.ReceivedConnectedClose();}
        
        
        queueResponse="ResponseQueue";
       
                destination=getDestinationQueueResponse();
           
            if (destination!=null){
                try {
                    MessageProducer producer = session.createProducer(destination);
                    producer.setDeliveryMode(DeliveryMode.PERSISTENT);//парметром PERSISTENT указываем что сообщение
                    //будет хранится до тех пор пока не будет доставлено адресату.
                    //Создаем текстовое сообщение.
                    TextMessage message =session.createTextMessage("hello   ");
                    producer.send(message);
                } catch (JMSException ex) {
                }
            }
        
        
        
    }
    
    private static ActiveMQConnectionFactory getConnectionFactory(){
        return new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, 
                                             ActiveMQConnection.DEFAULT_PASSWORD, 
                                             "failover://tcp://localhost:61616");
    }
    
    private static Destination getDestinationQueueRequest(){
        try {
            return session.createQueue(queueRequest);
        } catch (JMSException ex) {
            return null;
        }
    }
    private static Destination getDestinationQueueResponse(){
        try {
            return session.createQueue(queueResponse);
        } catch (JMSException ex) {
            return null;
        }
    }
    private static Destination getDestinationTopic(){
        try {
            return session.createTopic(queueRequest);
        } catch (JMSException ex) {
            return null;
        }
    }
    
    public static Boolean Connected(){
        try {
            if (connection==null){
                connectionFactory=getConnectionFactory();
                connection=connectionFactory.createConnection(); 
                //получаем экзмпляр класса подключения
                connection.start(); //стартуем 
                session =connection.createSession(false, Session.AUTO_ACKNOWLEDGE); 
                //создаем объект сессию без транзакций
                //параметром AUTO_ACKNOWLEDGE мы указали что отчет о доставке будет 
                //отправляться автоматически при получении сообщения.            
            }else{
                connection.start();
            }
            return true;
        } catch (JMSException ex) {
            return false;
        }
    }

    
    public static void main(String[] args) {
       windowjms = new JMSWindow();
windowjms.setVisible(true);
windowjms.jBSendMessage.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        clickSendButton();
                    }

           
                });
windowjms.jBConnectedListener.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        clickReceivedButton();
                    }
                });


                windowjms.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        if (connection!=null){
                            try {
                                connection.close();
                            } catch (JMSException ex) {
                                Logger.getLogger(PO_10_5_1.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                    
                });
                
    }
    
}
