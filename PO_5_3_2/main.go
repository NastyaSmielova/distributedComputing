package main

import (
	"math/rand"
	"time"
	"sync"
	"fmt"
)

const SIZE = 5
const  N  = 3
var r1 = rand.New(rand.NewSource(time.Now().UnixNano()))

func printArray(array []int){
	for i:=0;i<SIZE;i++{
		fmt.Print(array[i],"  ")
	}
	fmt.Println()
}
func createArray()(array []int,sum int){
	arr := make([]int,SIZE)
	var s = 0
	for i:=0;i<SIZE;i++{
		arr[i] = r1.Intn(2)
		s += arr[i]
	}
	return arr,s
}
func createRand()(index int,toIncrease bool){
	ind := rand.Intn(SIZE)
	var toInc bool
	if rand.Intn(10)%2 == 0{
		toInc = true
	}else{
		toInc = false
	}
	return  ind,toInc
}

func replace(array []int,sum int)([]int, int){
	index,toInc := createRand()
	if toInc{
		array[index] ++
		sum++
	}else{
		array[index] --
		sum--
	}
	return  array,sum
}


func isEqual(pointers[] int) bool{
	for i := 0; i < N ; i++{
		for j:= i+1; j < N; j++{
			if pointers[i]!= pointers[j]{
				return  false
			}
		}
	}
	return  true
}

func firstArray(id int,pointers*[]int,wg1,wg2 *sync.WaitGroup,first,second *int,mutex *sync.Mutex){

	array,sum := createArray()
	(*pointers)[id] = sum
	for{
		array,sum = replace(array,sum)

		fmt.Println(id," array : ")
		printArray(array)
		fmt.Println("sum  array : ",sum)
		(*pointers)[id] = sum
		wg1.Done()
		wg1.Wait()
		mutex.Lock()
		//time.Sleep(10)
		if (*first == N){
			fmt.Print("_________\n")
			wg2.Add(N)
		}
		*first --

		if *first == 0{
			*first = N
		}
		mutex.Unlock()

		if isEqual(*pointers){
				fmt.Println("equal!!!!!!!!!")
				wg2.Done()
				return
			}
		wg2.Done()
		wg2.Wait()
		time.Sleep(10)
		mutex.Lock()

		if (*second == N){
			wg1.Add(N)
		}
		*second --
		if *second == 0{
			*second = N
		}
		mutex.Unlock()
	}
}

func main() {
	array := make([]int,N)
	var sem = sync.WaitGroup{}
	var m = sync.Mutex{}
	var sem2 = sync.WaitGroup{}
	sem.Add(N)
	//sem2.Add(N)
	first := N
	second := N

	for i:=0;i<N;i++{
		go firstArray(i,&array,&sem,&sem2,&first,&second,&m)
	}
	var input string
	fmt.Scanln(&input)

}
