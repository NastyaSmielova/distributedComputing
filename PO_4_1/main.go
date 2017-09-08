package main

import (
	"sync"
	"fmt"
	"time"
	"os"
	"strings"
	"strconv"
	"math/rand"
)

type  Cont struct {
	name string
	phone int
}

func findByPhone(mutex *sync.RWMutex,phone int){

	mutex.RLock()
	file, err := os.Open("input.txt")
	if err != nil {
		return
	}
	defer file.Close()

	stat, err := file.Stat()
	if err != nil {
		return
	}
	bs := make([]byte, stat.Size())
	_, err = file.Read(bs)
	if err != nil {
		return
	}

	var str string
	str = string(bs)
	var phoneSTR = strconv.Itoa(phone)
	index := strings.Index(str,phoneSTR)
	if index != -1 {

		index += len(phoneSTR)
		index++

		var st string
		st = string(str[index])
		var name string
		for st != " " && st != "\n" {
			st = string(str[index])
			name += st
			index++
			if index >= len(str) {
				break
			}
		}
		fmt.Println(" phone ", phone, " name = ", name, "________\n")
	}else{
		fmt.Println(" phone ", phone, " has no name ________\n")
	}
	mutex.RUnlock()
}


func  createNew() (int, string){
	var seed = rand.NewSource(time.Now().UnixNano())
	var random = rand.New(seed)

	var letters string
	letters = "abcdefghijklmnopqrstuvwxyz"
	var phone = 0
	var name string
	name = " "
	for j:=0;j<2;j++{
		phone += random.Intn(8)+1;
		phone *= 10;
		name += string(letters[random.Intn(len(letters))])
	}
	phone += random.Intn(8)+1;
	name += string(letters[random.Intn(len(letters))])
	return  phone, name

}

func changeInfo(mutex *sync.RWMutex){

	mutex.Lock()
	var add bool
	if rand.Intn(100)%2==0 {
		add = true
	}else{
		add = false;
	}
	if add {
		f, _ := os.OpenFile("input.txt",  os.O_APPEND|os.O_WRONLY, os.ModeAppend)
		phone,name:= createNew();
		newLine := strconv.Itoa(phone) + name + "\n"
		f.WriteString(newLine)
		f.Close()

	} else {
		file, err := os.OpenFile("input.txt",os.O_RDWR,0644)
		file1,err := os.Create("out.txt")
		if err != nil {
			return
		}

		stat, err := file.Stat()
		if err != nil {
			return
		}
		bs := make([]byte, stat.Size())
		_, err = file.Read(bs)
		if err != nil {
			return
		}

		var str string
		str = string(bs)
		file.WriteString(str)
		strHave := ""
		isFirst:= true
		for i:=0;i<len(str);i++{
			if (string(str[i])!="\n"){
				if !isFirst {
					strHave += string(str[i])
				}
			}else{
				if !isFirst {
					strHave += string(str[i])
				}
				isFirst = false

			}
		}



		file1.WriteString(strHave)
		file1.Close()
		fmt.Println("try to del")
		file.Close()
		os.Remove("intput.txt")
		os.Rename("out.txt", "input.txt")
	}
	mutex.Unlock()

}

func findByName(mutex *sync.RWMutex,name string){
	mutex.RLock()
	file, err := os.Open("input.txt")
	if err != nil {
		return
	}
	defer file.Close()

	stat, err := file.Stat()
	if err != nil {
		return
	}
	bs := make([]byte, stat.Size())
	_, err = file.Read(bs)
	if err != nil {
		return
	}

	var str string
	str = string(bs)
	index := strings.Index(str,name)
	if index != -1 {
		index -= 1

		var st string
		st = string(str[index])
		var phone string
		for st != " " && st != "\n" {
			st = string(str[index])
			phone = st + phone

			index--
			if index < 0 {
				break
			}
		}
		fmt.Println(" phone ", phone, " name = ", name, "________\n")
	}else{
		fmt.Println(" no phone ", " name = ", name, "________\n")
	}
	mutex.RUnlock()
}
func main() {
	var mutex sync.RWMutex
	//for i:=1;i<10;i++{
		//go changeInfo(&mutex,i)


	for i:=0; i<10;  i++ {
		phone,name := createNew()
		go findByPhone(&mutex, phone)
		go findByName(&mutex, name)
		time.Sleep(3)
		changeInfo(&mutex)
	}
	go findByName(&mutex, "xwk")
	var input string
	fmt.Scanln(&input)
}
