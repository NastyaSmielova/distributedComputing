/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package po_10_3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import javafx.util.Pair;



public class Server {
    Map2 map = null;

    
    private ServerSocket server = null;
    private Socket sock = null;
    private PrintWriter out = null;
    private BufferedReader in = null;
    
    public void start(int port) throws IOException, Exception {
        server = new ServerSocket(port);
        System.out.println("opened  server");
        map = new Map2("map","localhost",3306);
        
        while (true)  {
            
            sock = server.accept();
            in = new BufferedReader(
            new InputStreamReader(sock.getInputStream( )));
            out = new PrintWriter(sock.getOutputStream(), true);
            while (processQuery());
        }
      //  map.stop();

    }
    private boolean processQuery() {
        System.out.println("receive \n");
        try{
            String query = in.readLine();
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
                
                
                default:{
                    out.println(query);
                }
            }
            out.println(response);

                    System.out.println("send " + response + " \n");
                    
            return true;
        }
        catch(IOException e){
          return false;
        }
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
