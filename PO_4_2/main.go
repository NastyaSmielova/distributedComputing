package main

import (
	"sync"
	"fmt"
	"time"

	"math/rand"
	"os"
	"strconv"
)
const size = 5


func Water(mutex *sync.RWMutex,Garden *[size][size]bool){
	for {
		mutex.Lock()
		fmt.Println("water")
		for i := range Garden{
			for j := range Garden[i]{
				if Garden[i][j]== true {
					Garden[i][j] = false
				}
			}
		}

		mutex.Unlock()
		time.Sleep(170 * time.Millisecond)
	}
}

func Rand(mutex *sync.RWMutex,Garden *[size][size]bool){
	for {
		mutex.Lock()

		fmt.Println("rand")

		for i := range Garden{
			for j := range Garden[i]{
				var b bool
				if rand.Intn(2)%2 == 0 {
					b = true
				} else {
					b = false
				}
				Garden[i][j] = b
			}
		}

		mutex.Unlock()
		time.Sleep(170 * time.Millisecond)

	}
}

func printToFile(mutex *sync.RWMutex,Garden *[size][size]bool){
	f, _ := os.OpenFile("output.txt", os.O_APPEND|os.O_WRONLY, os.ModeAppend)

	for {
		fmt.Println(" wants to print to file")

		mutex.RLock()

		fmt.Println(" Print to file")
		var str = ""
		for i := range Garden{
			for j := range Garden[i]{
				str +=  strconv.FormatBool(Garden[i][j]) + " "
			}
			str += "\n\n\n"
		}
		f.WriteString(str + "\n\n\n")
		f.WriteString("*********************************")
		mutex.RUnlock()
		time.Sleep(150 * time.Millisecond)
	}
	f.Close()
}

func show(mutex *sync.RWMutex,Garden *[size][size]bool){
	for {
		fmt.Println(" wants to show on the screen")

		mutex.RLock()

		for i := range Garden{
			for j := range Garden[i]{
				fmt.Print(Garden[i][j]," ")
			}
			fmt.Println("")
		}

		mutex.RUnlock()
		time.Sleep(150 * time.Millisecond)
	}
}

func main() {
	var mutex  sync.RWMutex
	var Garden  [size][size]bool

	for i:=0;i<size;i++{
		for j:=0;j<size;j++ {

			var b bool
			if rand.Intn(2)%2==0 {
				b = true
			}else{
				b = false
			}
			Garden[i][j] = b
		}
	}

	go show(&mutex,&Garden)
	go printToFile(&mutex,&Garden)
	go Rand(&mutex,&Garden)
	go Water(&mutex,&Garden)
	//time.Sleep(3)

	var input string
	fmt.Scanln(&input)
}
