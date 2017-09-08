/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package po_10_1;

import java.util.ArrayList;


public class Country {
    private Integer ID;
    private String name;
    private ArrayList<City> cities;
    public Country(){
        cities = new ArrayList();
    }
    
    public ArrayList<City> getCities(){
        return cities;
    }
    public String getInfo(){
        StringBuilder sb = new StringBuilder();
        sb.append("Country : ").append(name).append(" ").append(ID);
        sb.append("\n");
        for (City city: cities){
             sb.append(city.toString());
                     sb.append("\n");

         }
        return sb.toString();
    }
    
    public void printCities(){
        System.out.println("Country : " +name  + " " +ID);
         for (City city: cities){
             System.out.println(city.toString());
             
         }
    }
    
    public void addCity(City city){
        cities.add(city);
    }
    public void deleteCity(City city){
        cities.remove(city);
    }
    public Country(Integer id,String name){
        cities = new ArrayList();
        ID = id;
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public Integer getID(){
        return ID;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setID(Integer id){
        this.ID = id;
    }
    @Override
    public String toString(){
        return "country "+ name + " " + ID + " \n";
    }
    
    
}
