package main

import (
	"sync"
	"fmt"
	"time"
	"math/rand"

)
var r1 = rand.New(rand.NewSource(time.Now().UnixNano()))
var div = 50
type person struct{
	value int
}

func print(array *[]person){
	for i:=0;i<len(*array);i++{
		fmt.Print((*array)[i].value , " ")
	}
	fmt.Println("\n")
}

func hasBalance(array []person)bool{
	for i:=1;i<len(array);i++{
		if (array)[i-1].value == 1 && 0 == (array)[i].value {
			return  false
		}
	}
	return  true
}

func first(mutex *sync.Mutex,array *[]person,IDPart,N,parts int,wg1,wg2 *sync.WaitGroup,first,second *int){
	var end int
	if (IDPart+1)*div > N {
		end = N
	}else{
		end = div * (IDPart + 1)
	}
	for  {

		for i := div*IDPart ; i < end; i++ {
			if i > 0 {
				if i == IDPart{ mutex.Lock()}
				if (*array)[i-1].value == 1 && 0 == (*array)[i].value {
					(*array)[i].value ++
					(*array)[i].value %= 2
					(*array)[i-1].value++
					(*array)[i-1].value %= 2
				}
				if i == IDPart{ mutex.Unlock()}

			}
			if i < N - 1 {
				if i == end - 1{ mutex.Lock()}
				if (*array)[i+1].value == 0 && 1 == (*array)[i].value {
					(*array)[i].value ++
					(*array)[i].value %= 2
					(*array)[i+1].value++
					(*array)[i+1].value %= 2
				}
				if i == end - 1{ mutex.Unlock()}
			}

		}
		wg1.Done()
		wg1.Wait()
		mutex.Lock()
		time.Sleep(10)
		if (*first == parts){
			fmt.Print("_________\n")
			wg2.Add(parts)
		}
		*first --

		if *first == 0{
			*first = parts
		}
		mutex.Unlock()

		if hasBalance(*array){
			wg2.Done()
			return
		}
		wg2.Done()
		wg2.Wait()
		time.Sleep(10)
		mutex.Lock()

		if (*second == parts){
			wg1.Add(parts)
			print(array)

		}
		*second --
		if *second == 0{
			*second = parts
		}
		mutex.Unlock()


	}

}


func main() {

	var N int
	fmt.Scanln(&N)
	if N >= 100 {
		array := make([]person, N)

		for i := 0; i < N; i++ {
			array[i] = person{r1.Intn(2)}
		}
		print(&array)
		var parts int
		parts = N / div
		parts ++
		var sem= sync.WaitGroup{}
		var m= sync.Mutex{}
		var sem2= sync.WaitGroup{}
		sem.Add(parts)
		//sem2.Add(N)
		f := parts
		s := parts
		for i := 0; i < parts; i++ {
			go first(&m, &array, i, N, parts, &sem, &sem2, &f, &s)
		}
		var input string
		fmt.Scanln(&input)
		print(&array)
	}

}
