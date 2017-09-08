package main

import (
	"sync"
	//"time"
	"fmt"
	"time"
)

var honey = 0
var loop = 3
func bear(c,term chan bool, lockEat * sync.Mutex) {
	for {
		select {
		case _ = <-c:
			fmt.Println("bear has eaten all honey and sleeps now")
			time.Sleep(10)
			lockEat.Unlock()
		case _ = <-term:
			return
		}
	}
}


func bee(con,term chan bool,h,ID int, lockHoney,lockEat *sync.Mutex ){

	for {
		lockHoney.Lock()
		honey++
		fmt.Println(ID+1,"add +1 now = ",honey )
		if honey == h {
			honey = 0
			lockEat.Lock()
			con <- true
			lockEat.Lock()
			lockEat.Unlock()
			loop--
			if loop == 0{
				term<- true
				return
			}
		}
		lockHoney.Unlock()
		time.Sleep(5)
	}
}


func main() {
	var lockHoney sync.Mutex
	var lockEat  sync.Mutex
	var connection = make(chan bool)
	var term = make(chan bool)
	var n,h int
	fmt.Scanln(&n,&h)
	for i:= 0;i<n;i++ {
		go bee(connection, term,h,i,&lockHoney,&lockEat)
	}
	go bear(connection,term,&lockEat)

	var input string
	fmt.Scanln(&input)

}