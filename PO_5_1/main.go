package main

import (
	"sync"
	"fmt"
	"time"
	"math/rand"

)
var r1 = rand.New(rand.NewSource(time.Now().UnixNano()))
var div = 5
type person struct{
	value int
}

func print(array *[]person){
	for i:=0;i<len(*array);i++{
		fmt.Print((*array)[i].value , " ")
	}
	fmt.Println("\n")
}
func haveChanges(changes *[]bool)bool{
	for i:=0;i<len(*changes);i++{
		if (*changes)[i] == true{
			return true
		}
	}
	return false
}
func first(mutex *sync.Mutex,array *[]person,IDPart,N int,changes *[]bool){
		var end int
		if (IDPart+1)*div > N {
			end = N
		}else{
			end = div * (IDPart + 1)
		}
		for haveChanges(changes) {
			mutex.Lock()
		//	fmt.Print(IDPart,"\n")

			(*changes)[IDPart] = false
			for i := div*IDPart ; i < end; i++ {
				if i > 0 {
				//	fmt.Print((*array)[i-1].value," -> ",(*array)[i].value," ")
					if (*array)[i-1].value == 1 && 0 == (*array)[i].value {
						(*array)[i].value ++
						(*array)[i].value %= 2
						(*array)[i-1].value++
						(*array)[i-1].value %= 2
						(*changes)[IDPart] = true
						if (i ==div*IDPart ){
							(*changes)[IDPart - 1] = true
						}
					//	fmt.Print(i, " 1 !")
					}
				}
				if i < N - 1 {
					//fmt.Print((*array)[i].value," -->>",(*array)[i+1].value,"    ")

					if (*array)[i+1].value == 0 && 1 == (*array)[i].value {
						(*array)[i].value ++
						(*array)[i].value %= 2
						(*array)[i+1].value++
						(*array)[i+1].value %= 2
						(*changes)[IDPart] = true
						if (i ==end - 1 ){
							(*changes)[IDPart + 1] = true
						}
						//fmt.Print(i, " 2 !")

					}
				}

			}
			//print(array)

			mutex.Unlock()
			time.Sleep(10)
		}

}


func main() {

	var sem = sync.Mutex{}
	var N int
	fmt.Scanln(&N)
	array := make([]person,N)

	for i:=0;i<N;i++{
		array[i] = person{r1.Intn(2)}
	}
	print(&array)
	var parts int
	parts = N / div
	changes := make([]bool,parts+1)
	for i:=0;i<parts;i++{
		changes[i] = true
	}
	//fmt.Println(parts," <- parts")
	for i:=0;i<=parts;i++ {
		go first(&sem, &array, i,N,&changes)
	}
	var input string
	fmt.Scanln(&input)
	print(&array)

}
