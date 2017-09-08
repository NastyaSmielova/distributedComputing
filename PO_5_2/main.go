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
	isEqual := true
	for i := 0; i < N; i++{
		for j:= i+1; j< N; j++{
			if pointers[i]!= pointers[j]{
				if isEqual{
					isEqual = false
				}else{
					return  false
				}
			}
		}
	}
	return  true
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

func Array(id int,pointers*[]int,mutex *sync.Mutex){
	array,sum := createArray()
	(*pointers)[id] = sum
	for{
		mutex.Lock()
		if isEqual(*pointers) {
			fmt.Println("equal!!!!!!!")
			mutex.Unlock()
			return
		}
		array,sum = replace(array,sum)
		fmt.Println(id, " : ",array," ",sum)
		(*pointers)[id] = sum
		if isEqual(*pointers) {
			fmt.Println("equal!!!!!!!")
			return
		}
		mutex.Unlock()
		time.Sleep(10)
	}
}

func main() {

	array := make([]int,N)
	var sem = sync.Mutex{}

	for i:=0;i<N;i++{
		array[i] = i
	}
	for i:=0;i<N;i++{
		go Array(i,&array,&sem)
	}
	var input string
	fmt.Scanln(&input)

}
