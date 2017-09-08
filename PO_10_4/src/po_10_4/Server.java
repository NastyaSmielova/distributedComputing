/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package po_10_4;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Server {
    public static void main(String[] args) throws RemoteException, MalformedURLException, Exception {
       Map2 map = new Map2("map","localhost",3306);
       MapServiceImp helloImpl = new MapServiceImp(map);
       Registry registry = LocateRegistry.createRegistry(123);
       registry.rebind("Map", helloImpl);
       System.out.println("Server started!");
    }
}

