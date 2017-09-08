package main

import (
	"math/rand"
	"time"
	"sync"
	"fmt"
)

const SIZE = 5
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

func firstArray(first,second,third *int,mutex *sync.Mutex){

	array,sum := createArray()
	*first = sum
	for{
		mutex.Lock()
			if sum == *second{
				if sum == *third{
					return
				}
			}
			index,toInc := createRand()
			if toInc{
				array[index] ++
				sum++
			}else{
				array[index] --
				sum--
			}
		fmt.Println("first array : ")
		printArray(array)
		fmt.Println("sum first array : ",sum)

		*first = sum
		if sum == *second{
			if sum == *third{
				fmt.Println("equal!!!!!!!!!")
				return
			}
		}
		mutex.Unlock()
		time.Sleep(10)

	}
}

func secondArray(first,second,third *int,mutex *sync.Mutex){

	array,sum := createArray()
	*second = sum
	for{
		mutex.Lock()
		if sum == *first{
			if sum == *third{
				return
			}
		}
		index,toInc := createRand()
		if toInc{
			array[index] ++
			sum++
		}else{
			array[index] --
			sum--
		}
		fmt.Println("second array : ")
		printArray(array)
		fmt.Println("sum second array : ",sum)

		*second = sum
		if sum == *first{
			if sum == *third{
				fmt.Println("equal!!!!!!!!!")

				return
			}
		}
		mutex.Unlock()
		time.Sleep(10)

	}

}
func thirdArray(first,second,third *int,mutex *sync.Mutex){

	array,sum := createArray()
	*third = sum
	for{
		mutex.Lock()
		if sum == *second{
			if sum == *first{
				return
			}
		}
		index,toInc := createRand()
		if toInc{
			array[index] ++
			sum++
		}else{
			array[index] --
			sum--
		}
		fmt.Println("third array : ")
		printArray(array)
		fmt.Println("sum third array : ",sum)

		*third = sum
		if sum == *second{
			if sum == *first{
				fmt.Println("equal!!!!!!!!!")
				return
			}
		}
		mutex.Unlock()
		time.Sleep(10)
	}

}
func main() {
	var first,second,third int
	var sem = sync.Mutex{}
	go firstArray(&first,&second,&third,&sem)
	go secondArray(&first,&second,&third,&sem)
	go thirdArray(&first,&second,&third,&sem)

	var input string
	fmt.Scanln(&input)

}
