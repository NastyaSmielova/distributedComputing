/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * andxpen the template in the editor.
 */
package po_10_4;

import com.myrmi.MapService;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import javafx.util.Pair;
public class MapServiceImp extends UnicastRemoteObject implements MapService {
    Map2 map = null;
    public MapServiceImp(Map2 map ) throws RemoteException {
        super();
        this.map = map;
    }
    
    @Override
    public String deleteCity(String x) throws RemoteException {
        String[] fields =x.split("#");
        String response = null;
        response = map.deleteCity( fields[0]);
        return response;
    }
    @Override
    public String deleteCountry(String x) throws RemoteException {
        String[] fields =x.split("#");
        String response = null;
        response = map.deleteCountry(fields[0]);
        return response;
    }

    @Override
    public String addCountry(String x) throws RemoteException {
        String[] fields = x.split("#");
        String response = null;
        Pair<Boolean,String> ans = map.addCountry(fields[0],fields[1]);
        if (ans.getKey())response= "1";
            else response = "0#" + ans.toString();
        return response;
    }

    @Override
    public String addCity(String x) throws RemoteException {
        String[] fields = x.split("#");
        String response = null;
        Pair<Boolean,String> ans = map.addCity(fields[0],fields[1],fields[2],fields[3],fields[4]);
                    if (ans.getKey())response= "1";
                    else response = "0#" + ans.toString();
        return response;
    }

    @Override
    public String countryName(String x) throws RemoteException {
         String[] fields = x.split("#");
        String response = null;
         Pair<Boolean, String> ans = map.getCountryName(fields[0]);
                    if (ans.getKey())response= "1#"+ans.getValue();
                    else response = "0#" + ans.toString();
                return response;
            
    }

    @Override
    public String changeCountryName(String x) throws RemoteException {
        String[] fields = x.split("#");
        String response = null;
        Pair<Boolean,String> ans = map.changeCountryName(fields[0],fields[1]);
        if (ans.getKey())response= "1";
            else response = "0#" + ans.toString();
        return response;

    }

    @Override
    public String getCity(String x) throws RemoteException {
        String[] fields = x.split("#");
        String response = null;
        Pair<Boolean,String> ans = map.getCity(fields[0]);
                    if (ans.getKey())response= "1#" + ans.getValue();
                    else response = "0#" + ans.getValue();
        return response;
    }

    @Override
    public String changeCityInfo(String x) throws RemoteException {
        String[] fields = x.split("#");
        String response = null;
         Pair<Boolean,String> ans = map.changeCityInfo(fields[0],fields[1],fields[2],fields[3],fields[4]);
                    if (ans.getKey())response= "1";
                    else response = "0#" + ans.toString();
        return response;
                    
    }

    

    @Override
    public String showCitiesFromCountry(String x) throws RemoteException {
        String[] fields = x.split("#");
        String response = null;
         response = map.showCitiesFromCountry( fields[0]);
        return response;
         
    }

    @Override
    public String showAllCountries() throws RemoteException {
        return  map.showCountries();
    }

    @Override
    public String arrayCountries() throws RemoteException {
        return map.arrayCountries(); 
    }

    @Override
    public String arrayCities() throws RemoteException {
        Pair<Boolean,String> ans = map.arrayCities();
        String response;
                    if (ans.getKey())response= "1" + ans.getValue();
                    else response = "0#" + ans.toString();
        return response;
    }

    @Override
    public String showAllCities() throws RemoteException {
        return map.showCities();
    }
}