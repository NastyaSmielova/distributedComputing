package po_9_1;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;


public class PO_9_1 {
    
    final int N = 1000;
class Task extends RecursiveAction {

    private int[] A,B,C;
    private int ID;

    Task(int[] a, int[] b, int[] c) {
        this(a, b, c, -100);
    }
    void mult(int[] a, int[] b, int[] c, int num) {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                C[num * N + i] +=  A[num * N + j] * B[j * N + i];
    }
    
    @Override
    protected void compute() {
        if (ID < 0) {
            ArrayList <Task> tasks = new ArrayList();
            for (int i = 0; i < N; i++)    tasks.add(new Task(A, B, C, i));
            invokeAll(tasks);
        } else  mult(A, B, C, ID);
    }
    
    Task(int[] a, int[] b, int[] c, int id) {
        A = a;
        B = b;
        C = c;
        ID = id;
    }

}
    
    public void fill(int[]  A){
        for (int i = 0; i < N * N; i++)
            A[i] = rand.nextInt(4);
    }
    public void print(int[]  A){
        for (int i = 0; i < N;i++){
            for (int j = 0; j < N;j++)            System.out.print(A[i*N+j]+" ");
            System.out.print("\n");
        }
        System.out.print("\n");

    }
    private Random rand;
   
   public PO_9_1(){
       rand = new Random();
        int [] A = new int[N*N];
        int [] B = new int[N*N];
        int [] C = new int[N*N];
        fill(A);        fill(B);
        long start =  System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        forkJoinPool.invoke(new Task(A,B,C));
        long end = System.currentTimeMillis();

      //  print(A); print(B); print(C);
        System.out.println("time : " + (end-start)/1000);
   }
    public static void main(String[] args) {
        new PO_9_1();
    }
    
}
