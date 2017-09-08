/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myrmi;

import forms.MainForm;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import javafx.util.Pair;

public class Client {
    static MapService Q ;
    public static void main(String[] args)  throws NotBoundException, RemoteException, MalformedURLException {
        String url = "//localhost:123/Map";
        Q = (MapService) Naming.lookup(url);
        System.out.println("RMI object found");
        Client client = new Client();
        MainForm form = new MainForm();
        form.setMap(client);
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
            String response =  Q.arrayCountries(); 
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
            String response =  Q.showAllCountries();
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
            String response =  Q.deleteCity((createQuery(params)));
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
            String response =  Q.addCountry((createQuery(params)));
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
            String response =  Q.addCity((createQuery(params)));
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
            String response =  Q.arrayCities();
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
            String response =  Q.countryName((createQuery(params)));
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
            String response =  Q.changeCountryName((createQuery(params)));
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
            String response =  Q.getCity((createQuery(params)));
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
            String response =  Q.changeCityInfo((createQuery(params)));
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
            String response =  Q.deleteCountry((createQuery(params)));
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
            String response =  Q.showAllCities();
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
            String response =  Q.showCitiesFromCountry((createQuery(params)));
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
    
    private String createQuery(ArrayList<String> values) throws IOException  {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < values.size() - 1;i++){
            builder.append(values.get(i));
            builder.append("#");
        }
        builder.append(values.get(values.size() - 1));
        String query = builder.toString();
       
        return query;
    } 
    
}