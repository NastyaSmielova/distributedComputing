/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package po_10_5_1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import javafx.util.Pair;
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
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import static po_10_5_1.PO_10_5_1.Connected;



public class Server {
    Map2 map = null;

    private static ActiveMQConnectionFactory connectionFactory=null; //управляемый объект от ApacheMQ
//служащий для создания объекта Connection.
    
    private static Connection connection=null; //сам Connection.
    
    private static Session session; //контекст для посылки и приема сообщений.
    
    private static Destination destination; //буфер отправки и получения сообщений.
    
    private static String queueRequest=null; 
private static String queueResponse=null; 
    private ServerSocket server = null;
    private Socket sock = null;
    private PrintWriter out = null;
    private BufferedReader in = null;
    
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
    
     private static ActiveMQConnectionFactory getConnectionFactory(){
        return new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, 
                                             ActiveMQConnection.DEFAULT_PASSWORD, 
                                             "failover://tcp://localhost:61616");
    }
    
    
     public static Boolean Connected(){
        try {
            if (connection==null){
                connectionFactory=getConnectionFactory();
                connection=connectionFactory.createConnection(); 
                connection.start(); 
                session =connection.createSession(false, Session.AUTO_ACKNOWLEDGE); 
                      
            }else{
                connection.start();
            }
            return true;
        } catch (JMSException ex) {
            return false;
        }
    }

    
    
    public void start(int port) throws IOException, Exception {
        server = new ServerSocket(port);
        System.out.println("opened  server");
        map = new Map2("map","localhost",3306);
        
        while (true)  {
            
//            sock = server.accept();
//            in = new BufferedReader(
//            new InputStreamReader(sock.getInputStream( )));
//            out = new PrintWriter(sock.getOutputStream(), true);
            while (processQuery());
        }
      //  map.stop();

    }
    private boolean processQuery() {
     //   System.out.println("receive \n");
        String query = null;
            //String query = in.readLine();
            queueRequest="RequestQueue";
        if (Connected()){
                destination=getDestinationQueueRequest();
               
           if (destination!=null){
                try {
                    MessageConsumer consumer=session.createConsumer(destination);
                    MyMessageListener listener = new MyMessageListener();
                    consumer.setMessageListener(listener);
                    query = listener.getResponse();
                    
                } catch (JMSException ex) {
                }
           }
        }
        
        
        
        
            
            if (query==null) return false;
            System.out.println(query);

            String[] fields = query.split("#");
            String operation = fields[0];
            String response = null;
            switch (operation){
                case "SHOWALLCOUNTRIES": {
                    response = map.showCountries();
                    break;
                }
                case "SHOWALLCITIES":{
                    response = map.showCities();
                    break;
                    
                }
                case "SHOWCITYFROMCOUNTRY":{
                    response = map.showCitiesFromCountry( fields[1]);
                    break;
                    
                }
                case "ARRAYCOUNTRIES":{
                    response = map.arrayCountries();
                    break;
                    
                }
                case "DELETECITY":{
                    response = map.deleteCity( fields[1]);
                    break;
                    
                }
                case "DELETECOUNTRY":{
                    response = map.deleteCountry(fields[1]);
                    break;
                    
                }
                case "ADDCOUNTRY":{
                    Pair<Boolean,String> ans = map.addCountry(fields[1],fields[2]);
                    if (ans.getKey())response= "1";
                    else response = "0#" + ans.toString();
                    break;
                    
                }
                case "ARRAYCITES":{
                    Pair<Boolean,String> ans = map.arrayCities();
                    if (ans.getKey())response= "1" + ans.getValue();
                    else response = "0#" + ans.toString();
                    break;
                    
                }
                case "MODIFYCITY":{
                    Pair<Boolean,String> ans = map.changeCityInfo(fields[1],fields[2],fields[3],fields[4],fields[5]);
                    if (ans.getKey())response= "1";
                    else response = "0#" + ans.toString();
                    break;
                    
                }
                case "GETCITY":{
                    Pair<Boolean,String> ans = map.getCity(fields[1]);
                    if (ans.getKey())response= "1#" + ans.getValue();
                    else response = "0#" + ans.getValue();
                    break;
                    
                }
                case "GETCOUNTRY":{
                    Pair<Boolean, String> ans = map.getCountryName(fields[1]);
                    if (ans.getKey())response= "1#"+ans.getValue();
                    else response = "0#" + ans.toString();
                    break;
                    
                }
                case "MODIFYCOUNTRY":{
                    Pair<Boolean,String> ans = map.changeCountryName(fields[1],fields[2]);
                    if (ans.getKey())response= "1";
                    else response = "0#" + ans.toString();
                    break;
                    
                }
                        
                case "ADDCITY":{
                    Pair<Boolean,String> ans = map.addCity(fields[1],fields[2],fields[3],fields[4],fields[5]);
                    if (ans.getKey())response= "1";
                    else response = "0#" + ans.toString();
                    break;
                    
                }
                
                
            }
            queueResponse="ResponseQueue";
             if (Connected()){
                destination=getDestinationQueueResponse();
           
            if (destination!=null){
                try {
                    MessageProducer producer = session.createProducer(destination);
                    producer.setDeliveryMode(DeliveryMode.PERSISTENT);
                    TextMessage message =session.createTextMessage(response);
                    producer.send(message);
                } catch (JMSException ex) {
                }
            }

                    System.out.println("send " + response + " \n");
                    
            return true;
        }
            return false;
    }
    public static void main(String[] args) throws Exception{
        try {
            Server srv = new Server();
            srv.start(8888);
        }
        catch(IOException e) {
             System.out.println("error has occured");
        }
    }
}
