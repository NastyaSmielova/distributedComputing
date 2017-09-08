/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package po_3_2_2;

import java.util.Random;
import java.util.Vector;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;



class Client extends Thread{  
    private final Integer ID;  
    public Client(Integer id){  
        this.ID = id;  
    }  
     public void run() {  
        System.out.println(" Start client = "+ID);  
        try {
            Thread.sleep(20);
        } catch (InterruptedException ex) {
        }
        System.out.println(" End client = "+ID);  
    }  
 
}
class Worker extends Thread{
    private  Vector<Client> clients = new Vector();
    Semaphore sem = new Semaphore(1);
    public Worker(){
    }
     void add(Client cl){
        clients.add(cl);
        sem.release();
    }
    @Override 
    public void run(){
        Client client;
        
        while(true){
          try {
                     if(clients.isEmpty())
                         sem.acquire();
                } catch (InterruptedException ex) {
                }
            while (!clients.isEmpty()){
                client = clients.remove(0);
                client.start();
                try {
                    client.join();
                } catch (InterruptedException ex) {
                }
                
            }
                System.out.println("is sleeping ");
                try {
                   if(clients.isEmpty()) sem.acquire();
                } catch (InterruptedException ex) {
                }
            
        }
    }
}


class Creator extends Thread{
    private static Random rand = new Random();
    Integer time;
    Worker worker;
    public Creator(Worker w){
        worker = w;
    }
    
    @Override 
    public void run(){
        int id = 0;
        while (true){
            time = rand.nextInt(1000);
            try {
                Thread.sleep(time);
            } catch (InterruptedException ex) {
                Logger.getLogger(Creator.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("add client (id = " + id + " ) " );
            worker.add(new Client(id++));
        }
    }
}
public class PO_3_2_2 {

    public static void main(String[] args) {
    
        Worker w = new Worker();
        w.start();
        Creator cr = new Creator(w);
        cr.run();
        
    }
    
}
