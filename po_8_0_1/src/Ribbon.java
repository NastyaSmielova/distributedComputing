
import mpi.MPI;

import java.util.Random;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Ribbon {

    static public void initMatrix(int A[][]) {
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                A[i][j] = new Random().nextInt(2);
            }
        }
    }

    static public void print(int A[][],int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(A[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("____________________________________________");

    }
    static public void print(int A[],int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(A[i * size + j] + " ");
            }
            System.out.println();
        }
    }


    static double startTime = 0;

    public static void main(String args[]) throws Exception {
        MPI.Init(args);

        int rank = MPI.COMM_WORLD.Rank();
        int worldSize = MPI.COMM_WORLD.Size();
        int size = 100;
        int A [][] = new int[size][size];

        int B [][] = new int[size][size];

        if (rank == 0) {
            initMatrix(A);
            initMatrix(B);
           //  print(A,size);
             //print(B,size);
            startTime = System.currentTimeMillis();
        }

        MatrixMultiplication(A, B);
        MPI.Finalize();
    }


    static void MatrixMultiplication(int A[][], int B[][]) {
        int ProcNum = MPI.COMM_WORLD.Size();
        int ProcRank = MPI.COMM_WORLD.Rank();
        int procPartA = (A.length / ProcNum);
        int procPartB = (B.length / ProcNum);
        int partA = procPartA * A.length;
        int partB = procPartB * B.length;


        int PortionA[] = new int[A.length * A.length];
        int PortionB[] = new int[B.length * B.length];
        int PortionC[] = new int[A.length * B.length];

        int C[][] = new int[A.length][B.length];

        for (int i = 0, k = 0; i < A.length; i++)
            for (int j = 0; j < A[i].length; j++) {
                PortionA[k++] = A[i][j];
            }

        for (int i = 0, k = 0; i < B.length; i++)
            for (int j = 0; j < B[i].length; j++) {
                PortionB[k++] = B[j][i];
            }

        int buf_A[] = new int[partA];
        int buf_B[] = new int[partB];
        int buf_C[] = new int[procPartA * B.length];


        MPI.COMM_WORLD.Scatter(PortionA, ProcRank * procPartA, partA, MPI.INT, buf_A, 0, partA, MPI.INT, 0);
        MPI.COMM_WORLD.Scatter(PortionB, ProcRank * procPartB, partB, MPI.INT, buf_B, 0, partB, MPI.INT, 0);

        /*System.out.println("Rank " + ProcRank+" A");
        for (int aBuf_A : buf_A)
            System.out.println(aBuf_A+" ");

        System.out.println("Rank " + ProcRank+" B");
        for (int aBuf_B : buf_B)
            System.out.println(aBuf_B+" ");
        System.out.println();*/

        int tmp = 0;
        for (int i = 0; i < procPartA; i++)
            for (int j = 0; j < procPartB; j++) {
                tmp = 0;
                for (int k = 0; k < A.length; k++)
                    tmp += buf_A[i * A.length + k] * buf_B[j * A.length + k];
                buf_C[i * B.length + j + procPartA * ProcRank] = tmp;
            }
        /*System.out.println("Rank " + ProcRank);
        for (int aBuf_C : buf_C)
            System.out.println(aBuf_C+" ");*/

        int NextProc;
        int PrevProc;
        NextProc = ProcRank + 1;
        if (ProcRank == ProcNum - 1) NextProc = 0;
        PrevProc = ProcRank - 1;
        if (ProcRank == 0) PrevProc = ProcNum - 1;

        int index = 0;
        for(int n_process = 1; n_process < ProcNum; n_process++)
        {
            MPI.COMM_WORLD.Sendrecv_replace(buf_B, 0,partB, MPI.INT, NextProc, 0, PrevProc, 0);   // передача-принятие единого типа сообщений (рассылка B)
            tmp = 0;
            for (int i = 0; i < procPartA; i++)
            {
                for(int j = 0; j < procPartB; j++)
                {
                    tmp = 0;
                    for(int k = 0; k <  A.length; k++)
                        tmp += buf_A[i * A.length + k] * buf_B[j * A.length + k];
                    if (ProcRank - n_process >= 0)
                        index = ProcRank - n_process;
                    else
                        index =(ProcNum - n_process + ProcRank);
                    buf_C[i * B.length + j + index * procPartA] = tmp;
                }
            }
        }
       /* System.out.println("Rank " + ProcRank);
        for (int aBuf_C : buf_C)
            System.out.println(aBuf_C+" ");*/

        MPI.COMM_WORLD.Gather(buf_C,0, procPartA * B.length, MPI.INT, PortionC,ProcNum*procPartA, procPartA * B.length, MPI.INT, 0);


        if(ProcRank == 0){
            /*for (int aPortionC : PortionC) {
                System.out.print(aPortionC + " ");
            }*/
        //    print(PortionC, A.length);
            System.out.println(System.currentTimeMillis() - startTime);
        }
    }



}
