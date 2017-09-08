package main


import (
	"fmt"
	"time"
)
var bPosX,bPosY int
var isFound bool

func find(xF,yF,xS,yS int,cFind, cNotFind chan bool) {
	time.Sleep(10)

	if (bPosX <= xS && bPosY <= yS && bPosX >=xF && bPosY >= yF){
		isFound = true;
		fmt.Print("x,y = ",xF,"  ",yF," ",xS,"  ",yS,"find!\n ")
		cFind <- true
	}else {
		fmt.Print("x,y = ", xF, "  ", yF, " ", xS, "  ", yS, "don't find\n ")
		cNotFind <- false
	}


}

func main() {
	var c1 = make(chan bool)
	var c2 = make(chan bool)
	isFound = false;
	fmt.Scanln(&bPosX,&bPosY)

	var x,y int
	fmt.Scanln(&x,&y)
	fmt.Print("bear position ",bPosX,"  ", bPosY,"\n");
	fmt.Print("area = ", x, "  ", y, " \n")
	//var xPos = 0
	var yPos = 0
	var div = 10;
	for i:= 0;i<2;i++ {
		var nyPos= yPos + y/div
		go find(0, yPos, x, nyPos, c1, c2);
		yPos = nyPos
		time.Sleep(20)
	}
	for isFound == false{
			select {
			case msg1 := <- c1:
				fmt.Println(msg1)
				isFound = true;
			case msg2 := <- c2:
				fmt.Println(msg2)
				var nyPos = yPos + y/div
				go find(0,yPos,x,nyPos,c1,c2);
				yPos = nyPos
				if yPos> y{
					isFound = true;
					fmt.Print("bbear wasn't found")
				}
			}

	}

}