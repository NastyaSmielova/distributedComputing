/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package po_10_3;
import forms.MainForm;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import javafx.util.Pair;
import static po_10_3.operation.*;


public class Client {
    
    
    
    private Socket sock = null;
    private PrintWriter out = null;
    private BufferedReader in = null;
    public Client(String ip, int port) throws IOException  {
        System.out.println("connected to server");
        sock = new Socket(ip,port);
        in = new BufferedReader( new InputStreamReader(sock.getInputStream( )));
        out = new PrintWriter(sock.getOutputStream(), true);
        MainForm form = new MainForm();
        form.setMap(this);
        form.setVisible(true);
        form.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
//                try {
//                  //  disconnect();
//                } catch (IOException ex) {
//                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
//                }
            }
        });
        
    }
    public ArrayList<String> arrayCountries(){

        ArrayList<String> ans = new ArrayList();

        try {
            String response =  sendQuery(ARRAYCOUNTRIES,new ArrayList());
            String[] fields = response.split("#");
            if (fields.length <= 1) return new ArrayList<>();
            if(Integer.parseInt(fields[0]) == 0) new ArrayList<>();
            for(int i = 1;i < fields.length;){
                ans.add(fields[i++]);
            }
                
        } catch (IOException | NumberFormatException ex) {
            System.err.println(ex);
        }
        return ans;
    }
    public String showAllCountries(){

        StringBuilder ans = new StringBuilder();
        try {
            String response =  sendQuery(SHOWALLCOUNTRIES,new ArrayList());
            String[] fields = response.split("#");
            if (fields.length <= 1) return "incorect response";
            if(Integer.parseInt(fields[0]) == 0) return "error "+ fields[1];
            for(int i = 1;i < fields.length;){
                ans.append(fields[i++]);
                ans.append( " " + fields[i++]);
                ans.append("\n");
            }
                
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return ans.toString();
    }
    
    public String deleteCity(Integer id){
        StringBuilder ans = new StringBuilder();
        try {
            ArrayList<String> params = new ArrayList();
            params.add(id.toString());
            String response =  sendQuery(DELETECITY,(params));
            String[] fields = response.split("#");
            if (fields.length <= 0) return "incorect response";
            if(Integer.parseInt(fields[0]) == 0) return "error "+ fields[1];
            ans.append("ok");
                
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return ans.toString();
    }
    
    public Pair<Boolean,String> addCountry(String id,String name){
        StringBuilder ans = new StringBuilder();
        try {
            ArrayList<String> params = new ArrayList();
            params.add(id);
            params.add(name);
            String response =  sendQuery(ADDCOUNTRY,(params));
            String[] fields = response.split("#");
            if (fields.length <= 0) return new Pair(false,"incorect response");
            if(Integer.parseInt(fields[0]) == 0) return new Pair(false,"error "+ fields[1]);
            ans.append("ok");
                
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return new Pair(true,ans.toString());
    }
    public Pair<Boolean,String> addCity(Integer id,String name,Integer idCo, Integer count, Integer isCap){
        StringBuilder ans = new StringBuilder();
        try {
            ArrayList<String> params = new ArrayList();
            params.add(id.toString());
            params.add(name);
            params.add(idCo.toString());
            params.add(count.toString());
            params.add(isCap.toString());
            String response =  sendQuery(ADDCITY,(params));
            String[] fields = response.split("#");
            if (fields.length <= 0) return new Pair(false,"incorect response");
            if(Integer.parseInt(fields[0]) == 0) return new Pair(false,"error "+ fields[1]);
            ans.append("ok");
                
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return new Pair(true,ans.toString());
    }
    public Pair<Boolean, ArrayList<String>> arrayCities() {
        ArrayList<String> ans = new ArrayList();
        try {
            ArrayList<String> params = new ArrayList();
            String response =  sendQuery(ARRAYCITES,(params));
            String[] fields = response.split("#");
            if (fields.length <= 0) {ans .add("incorect response");return new Pair(false,ans);}
            if(Integer.parseInt(fields[0]) == 0) {ans .add("error "+ fields[1]);return new Pair(false,ans);}
            for (int i = 1; i < fields.length; i++){
                ans.add(fields[i]);
            }
                
        } catch (IOException | NumberFormatException ex) {
            System.err.println(ex);
        }
        return new Pair(true,ans);
        
    }
    public Pair<Boolean, String> countryName(Integer id) {
        StringBuilder ans = new StringBuilder();
        try {
            ArrayList<String> params = new ArrayList();
            params.add(id.toString());
            String response =  sendQuery(GETCOUNTRY,(params));
            String[] fields = response.split("#");
            if (fields.length <= 0) return new Pair(false,"incorect response");
            if(Integer.parseInt(fields[0]) == 0) return new Pair(false,"error "+ fields[1]);
            ans.append(fields[1]);
                
        } catch (IOException | NumberFormatException ex) {
            System.err.println(ex);
        }
        return new Pair(true,ans.toString());    }

    public Pair<Boolean, String> changeCountryName(Integer id, String text) {
        StringBuilder ans = new StringBuilder();
        try {
            ArrayList<String> params = new ArrayList();
            params.add(id.toString());
            params.add(text);
            String response =  sendQuery(MODIFYCOUNTRY,(params));
            String[] fields = response.split("#");
            if (fields.length <= 0) return new Pair(false,"incorect response");
            if(Integer.parseInt(fields[0]) == 0) return new Pair(false,"error "+ fields[1]);
            ans.append("ok");
                
        } catch (IOException | NumberFormatException ex) {
            System.err.println(ex);
        }
        return new Pair(true,ans.toString());
    }
    
    
    
    public Pair<Boolean, ArrayList<String>> getCity(Integer id) {
        ArrayList<String> ans = new ArrayList<>();
        try {
            ArrayList<String> params = new ArrayList();
            params.add(id.toString());
            String response =  sendQuery(GETCITY,(params));
            String[] fields = response.split("#");
            if (fields.length <= 0) return new Pair(false,"incorect response");
            if(Integer.parseInt(fields[0]) == 0) return new Pair(false,"error "+ fields[1]);
            for (int i = 1; i < fields.length; i++){
                ans.add(fields[i]);
            }
                
        } catch (IOException | NumberFormatException ex) {
            System.err.println(ex);
        }
        return new Pair(true,ans);
    }

    public Pair<Boolean, String> changeCityInfo(Integer id, String text, Integer idCountry, Integer count, Integer cap) {
        StringBuilder ans = new StringBuilder();
        try {
            ArrayList<String> params = new ArrayList();
            params.add(id.toString());
            params.add(text);
            params.add(idCountry.toString());
            params.add(count.toString());
            params.add(cap.toString());
            String response =  sendQuery(MODIFYCITY,(params));
            String[] fields = response.split("#");
            if (fields.length <= 0) return new Pair(false,"incorect response");
            if(Integer.parseInt(fields[0]) == 0) return new Pair(false,"error "+ fields[1]);
            ans.append("ok");
                
        } catch (IOException | NumberFormatException ex) {
            System.err.println(ex);
        }
        return new Pair(true,ans.toString());
    }
    
    public String deleteCountry(Integer id){
        StringBuilder ans = new StringBuilder();
        try {
            ArrayList<String> params = new ArrayList();
            params.add(id.toString());
            String response =  sendQuery(DELETECOUNTRY,(params));
            String[] fields = response.split("#");
            if (fields.length <= 0) return "incorect response";
            if(Integer.parseInt(fields[0]) == 0) return "error "+ fields[1];
            ans.append("ok");
                
        } catch (IOException | NumberFormatException ex) {
            System.err.println(ex);
        }
        return ans.toString();
    
    }
    
    
    public String showAllCities(){
        StringBuilder ans = new StringBuilder();
        try {
            String response =  sendQuery(SHOWALLCITIES,new ArrayList());
            String[] fields = response.split("#");
            if (fields.length <= 1) return "incorect response";
            if(Integer.parseInt(fields[0]) == 0) return "error "+ fields[1];
            for(int i = 1;i < fields.length;i+=5){
                for (int j = 0; j < 5; j++){
                    ans.append(fields[i+j]).append(" ");
                }
                ans.append("\n");
            }
                
        } catch (IOException | NumberFormatException ex) {
            System.err.println(ex);
        }
        return ans.toString();
    }
    public String showCitiesFromCountry(Integer id){

        StringBuilder ans = new StringBuilder();
        try {
            ArrayList<String> params = new ArrayList();
            params.add(id.toString());
            String response =  sendQuery(SHOWCITYFROMCOUNTRY,(params));
            String[] fields = response.split("#");
            if (fields.length <= 0) return "incorect response";
            if(Integer.parseInt(fields[0]) == 0) return "error "+ fields[1];
            for(int i = 1;i < fields.length - 1;i+=5){
                for (int j = 0; j < 5; j++){
                    ans.append(fields[i+j]).append(" ");
                }
                ans.append("\n");

            }
            ans.append("\n");
            ans.append(fields[fields.length - 1]);

                
        } catch (IOException | NumberFormatException ex) {
            System.err.println(ex);
        }
        return ans.toString();
    }
    
    private String sendQuery(operation oper, ArrayList<String> values) throws IOException  {
        StringBuilder builder = new StringBuilder();
        builder.append(oper);
        for (int i = 0; i < values.size();i++){
            builder.append("#");
            builder.append(values.get(i));
        }
        String query = builder.toString();
        out.println(query);
        String response = in.readLine();
        return response;
    }

    public void disconnect() throws IOException{
      sock.close();
    }
    public static void main(String[] args) {
        try{
            Client client = new Client("localhost",8888);

        }
        catch(IOException e) {
            System.out.println("error has occured");
            System.err.println(e);
        }
    }

    

 

   
}