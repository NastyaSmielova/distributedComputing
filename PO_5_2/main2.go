package main2

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

func Array(id int,pointers*[]int,wg *sync.WaitGroup){
	array,sum := createArray()
	(*pointers)[id] = sum
	for{

		array,sum = replace(array,sum)
		fmt.Println(id, " : ",array," ",sum)
		(*pointers)[id] = sum
		wg.Done()
		fmt.Print("_________\n")
		if (id == 0){
			wg.Add(N)
		}
		if isEqual(*pointers) {
			fmt.Println("equal!!!!!!!")
			wg.Done()
			return
		}
		wg.Done()
		time.Sleep(10)
	}
}

func main() {

	array := make([]int,N)
	var sem = sync.WaitGroup{}

	for i:=0;i<N;i++{
		array[i] = i
	}
	sem.Add(N)
	for i:=0;i<N;i++{
		go Array(i,&array,&sem,)
	}
	var input string
	fmt.Scanln(&input)

}
