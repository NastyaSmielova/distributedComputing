/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package po_10_1;


public class City {
    private Boolean isCapital;
    private Integer numOfPeople;
    private String name;
    private Integer countryID;
    private Integer ID;
    public City(String name,Integer id,Integer num, Boolean isCap,Integer countryID){
            this.ID = id;
            this.isCapital = isCap;
            this.numOfPeople = num;
            this.countryID = countryID;
            this.name = name;
    }
    public void setCapital(Boolean isCap){
        this.isCapital = isCap;
    }
    public void setPeople(Integer num){
        this.numOfPeople = num;
    }
    public void setId(Integer id){
        this.ID = id;
    }
    public void tName(String name){
        this.name = name;
    }
    public void setCountry(Integer country){
        this.countryID = country;
    }
    public City(){
        
    }   
    public Boolean getCapital(){
        return isCapital;
    }
    public Integer getPeople(){
        return numOfPeople;
    }
    public Integer getID(){
        return ID;
    }
    public String getName(){
        return name;
    }
    public Integer getCountry(){
        return countryID;
    }
    @Override
    public String toString(){
        return "city "+ name + " id :" + ID + " isCap  " +isCapital +" num : " +numOfPeople + " country ID :" + countryID + "\n";
    }
}
