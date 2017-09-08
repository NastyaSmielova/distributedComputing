/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myrmi;

import java.rmi.*;
public interface MapService extends Remote {
    public String showAllCountries()throws RemoteException;
    public String arrayCountries()throws RemoteException;
    public String deleteCity(String o)throws RemoteException;
    public String addCountry(String o)throws RemoteException;
    public String addCity(String o)throws RemoteException;
    public String arrayCities()throws RemoteException;
    public String countryName(String o)throws RemoteException;
    public String changeCountryName(String o) throws RemoteException;
    public String getCity(String o)throws RemoteException;
    public String changeCityInfo(String o) throws RemoteException;
    public String deleteCountry(String o)throws RemoteException;
     public String showAllCities()throws RemoteException;
    public String showCitiesFromCountry(String o) throws RemoteException;
}
