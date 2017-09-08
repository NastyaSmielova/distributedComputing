package main

import(
	"fmt"
	"time"
	"math/rand"
)

func first(c1,cCon,term chan bool) {
	for {
		select {
		case _ = <-c1:
			time.Sleep(10)
			fmt.Println("first smokes")
			cCon <- true
		case _ = <-term:
			return
		}
	}

}
func second(c2,cCon,term chan bool) {
	for {
		select {
		case _ = <-c2:
			time.Sleep(10)
			fmt.Println("second smokes")
			cCon <- true
		case _ = <-term:
			return
		}
	}
}
func third(c3,cCon,term chan bool) {
	for {
		select {
		case _ = <-c3:
			time.Sleep(10)
			fmt.Println("third smokes")
			cCon <- true
		case _ = <-term:
			return
		}
	}
}

func prod(c1,c2,c3,cCon,term chan bool) {
	for i:=0; i < 10; i++{
		number := rand.Intn(3)
		fmt.Print(number)
		switch number {
		case 0:
			c1 <- true
		case 1:
			c2<- true

		case 2:
			c3<-true
		}
		select {
		case _= <-cCon:
			fmt.Println("prod returns")
		}
	}
	term<-true
}

func main() {
	var c1 = make(chan bool)
	var c2 = make(chan bool)
	var c3 = make(chan bool)
	var cCon = make(chan bool)
	var term = make(chan bool)

	go prod(c1,c2,c3,cCon,term)
	go first(c1,cCon,term)
	go second(c2,cCon,term)
	go third(c3,cCon,term)
	var input string
	fmt.Scanln(&input)
}