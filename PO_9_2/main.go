package main

import (
//	"runtime"
	"fmt"
	"math/rand"
	"time"
)
const N = 1000


func calc(i, n int, c chan int,A,B,C *[N*N]int) {
	for ; i < n; i++ {
		for j:=0; j < N; j++{
			sum := 0
			for k := 0; k < N; k++{
				sum += (*A)[i*N+k]*(*B)[k*N+j]
			}
			(*C)[i*N+j] = sum
		}
	}
	c <- 1
}

func print(A * [N * N] int){
	for i := 0; i < N; i++{
		for j:=0; j < N; j++{
			fmt.Print(A[i * N + j]," ")
		}
		fmt.Print("\n ")
	}
	fmt.Print("\n ")
}

func main() {

//	var numCPU = runtime.NumCPU()
	c := make(chan int, N)
	var A [N*N]int
	var B [N*N]int
	var C [N*N]int

	r := rand.New(rand.NewSource(99))
	for i:=0;i<N*N;i++{
		A[i] = r.Intn(5)
		B[i] = r.Intn(5)
		C[i] = 0
	}
	fmt.Print("\n ")
	now := time.Now()
	for i := 0; i < N; i++ {
	go calc(i  , (i + 1) , c,&A,&B,&C)
	}
	for i := 0; i < N; i++ {
	<-c
	}
	now2 := time.Now()
	fmt.Print("time = ",now2.Sub(now).Seconds())
	/*print(&A)
	print(&B)
	print(&C)
*/
}
