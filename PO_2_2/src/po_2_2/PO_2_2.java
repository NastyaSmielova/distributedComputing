/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package po_2_2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

class GANG{
    public void stealSomething(){
        try {
            Query qF = new Query();
            Query qS = new Query();
            First Ivanov = new First(qF);
            Second Petrov = new Second(qF,qS);
            Third Nechuporuk = new Third(qS);
            Ivanov.start();
            Petrov.start();
            Nechuporuk.start();
            Ivanov.join();
            Petrov.join();
            Nechuporuk.join();
        } catch (InterruptedException ex) {
        }

    }
}

class Query{
    Integer n;
    Semaphore semProd = new Semaphore(1);
    Semaphore semCon = new Semaphore(0);
    public void add(Integer n){
        try {
            semProd.acquire();
            this.n = n;
            System.out.println("add " + n);
            semCon.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int get(){
        try {
            semCon.acquire();
            System.out.println("get " + n);
            semProd.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }    
}


class First extends Thread{
    public First(Query q){
        this.q = q;
    }
    private Query q;
    @Override
    public void run(){
        for (int i = 0; i < 10; i++ )q.add(i);
    }
}  
class Third extends Thread{
    public Third(Query q){
        this.q = q;
    }
    private Query q;
    @Override
    public void run(){
        for (int i = 0; i < 10; i++ )q.get();
    }
}  
class Second extends Thread{
    public Second(Query qF,Query qS){
        this.qF = qF;
        this.qS = qS;
    }
    private Query qF,qS;
    @Override
    public void run(){
        int elem;
        for (int i = 0; i < 10; i++ ){
            elem = qF.get();
            qS.add(elem);
        }
    }
}



public class PO_2_2 {

    public static void main(String[] args) throws IOException {
       GANG boys = new GANG();
       boys.stealSomething();
    }
    
}
