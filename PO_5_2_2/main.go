package main

import (
"math/rand"
"time"
"sync"
"fmt"
)

const SIZE = 5
var r1 = rand.New(rand.NewSource(time.Now().UnixNano()))
const N = 4


func isEqual(pointers[] int) bool{
	isNOTEqual := 0
	for i := 0; i < N; i++{
		for j:= i+1; j< N; j++{
			if pointers[i]!= pointers[j]{
				isNOTEqual ++
			}
		}
	}
	if isNOTEqual <= 3 {
		return true
	}else{
		return  false
	}
}

func createArray()([]string, int){
	var letters = "ABCD"
	arr := make([]string,SIZE)
	var s = 0
	for i:=0;i<SIZE;i++{
		index := r1.Intn(4)
		arr[i] = string(letters[index])
		if index < 2 {
			s++
		}
	}
	//fmt.Println(arr," ",s)
	return arr,s
}

func replace(array []string,sum int)([]string, int){
	ind := rand.Intn(SIZE)
	switch string(array[ind]){
	case "A":{
		array[ind] = "C"
		return  array,sum - 1
	}
	case "B":{
		array[ind] = "D"
		return array, sum - 1
	}
	case "C":{
		array[ind] = "A"
		return array, sum + 1
	}
	case "D":{
		array[ind] = "B"
		return array, sum + 1
	}
	}
	return  array,sum
}

func Array(id int,pointers*[]int,wg1,wg2 *sync.WaitGroup,first,second *int,mutex *sync.Mutex){
	array,sum := createArray()
	(*pointers)[id] = sum
	for{

		array,sum = replace(array,sum)
		fmt.Println(id, " : ",array," ",sum)
		(*pointers)[id] = sum
		wg1.Done()
		wg1.Wait()
		//fmt.Print("_________\n")
		time.Sleep(10)
		mutex.Lock()
		if (*first == N){
			fmt.Print("_________\n")
			wg2.Add(N)
		}
		*first --

		if *first == 0{
			*first = N
		}
		mutex.Unlock()
		//wg.Add(1)
		if isEqual(*pointers) {
			fmt.Println("equal!!!!!!!")
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
	for i:=0;i<N;i++{
		array[i] = i
	}
	sem.Add(N)
	//sem2.Add(N)
	first := N
	second := N

	for i:=0;i<N;i++{
		go Array(i,&array,&sem,&sem2,&first,&second,&m)
	}
	var input string
	fmt.Scanln(&input)

}
