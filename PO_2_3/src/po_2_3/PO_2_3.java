/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package po_2_3;

import java.io.File;
import java.io.FileNotFoundException;
import static java.lang.Math.max;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PO_2_3 {
    private static ArrayList<Pair<Integer,Integer>> array = new ArrayList();
    private static int read(){
        int i = 0;
        try {
            Scanner scanner = new Scanner(new File("in.txt"));
            while(scanner.hasNextInt()){
                array.add(new Pair(scanner.nextInt(),i++));
            }       } catch (FileNotFoundException ex) {
            Logger.getLogger(PO_2_3.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }
    
    public static void main(String[] args) {
        read();
        Tournament t = new Tournament(array);
    }
}

class Tournament{
    private static int counter = 0;
    private static ArrayList<Pair<Integer,Integer>> array;
    public Tournament(ArrayList<Pair<Integer,Integer>> _array){
        array = _array;
        Round tournament = new Round(array.size());
	ForkJoinPool pool = new ForkJoinPool();
	int i = pool.invoke(tournament);
	System.out.println("winner : " + array.get(i).getElement0() + "  " + i);
    }
        private static Semaphore sem = new Semaphore(1);

    class Round extends RecursiveTask<Integer>{
	final int num;
        
	Round(int num) { this.num = num; }
	@Override
	protected Integer compute() {
	     if (num <= 1){
                 try {
                     sem.acquire();
                     int ans = array.get(counter++).getElement1();
                     sem.release();
                     return ans;
                 } catch (InterruptedException ex) {
                 }
	     }
             int nextSize = num / 2;
	     Round fcal1 = new Round(nextSize);
	     fcal1.fork();
             if (num % 2 != 0) nextSize++;
	     Round fcal2 = new Round(nextSize);
             int f = fcal2.compute();
             int s = fcal1.join();
            try {
                sem.acquire();
            } catch (InterruptedException ex) {
            }
             int first = array.get(f).getElement0();
             int second = array.get(s).getElement0();
             sem.release();
             if (first > second) return array.get(f).getElement1();
             else return array.get(s).getElement1();
             
	}
}
}

