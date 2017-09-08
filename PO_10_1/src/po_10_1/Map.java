/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package po_10_1;

import po_10_1.forms.MainForm;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class Map {
    private final ArrayList<Country> countries;    
    private final ArrayList<City> cities;    
    MainForm forma = new  MainForm();
    
    public void saveToFile(String filename) throws ParserConfigurationException, TransformerException{
         DocumentBuilderFactory dbf = null;
         DocumentBuilder db = null;        
         Document doc = null;         
         dbf = DocumentBuilderFactory.newInstance();     
         db = dbf.newDocumentBuilder();      
         doc = db.newDocument();         
         Element root = doc.createElement("map");     
         doc.appendChild(root); 
        for  (Country country: countries ){
            Element country1 = doc.createElement("country");     
            country1.setAttribute("id", country.getID().toString());        
            Element nameCountry = doc.createElement("nameC");
            nameCountry.appendChild(doc.createTextNode(country.getName()));
            country1.appendChild(nameCountry);

            root.appendChild(country1); 
            ArrayList<City> curCities = country.getCities();
            for  (City city: curCities ){
                Element city1 = doc.createElement("city"); 
                Element nameCity = doc.createElement("name");
                nameCity.appendChild(doc.createTextNode(city.getName()));
                city1.appendChild(nameCity);
                city1.setAttribute("id", city.getID().toString());   
                Element isCap = doc.createElement("iscap");
                isCap.appendChild(doc.createTextNode(city.getCapital().toString()));
                city1.appendChild(isCap);   
                Element count = doc.createElement("count");
                count.appendChild(doc.createTextNode(city.getPeople().toString()));
                city1.appendChild(count);
                country1.appendChild(city1); 
            }
        } 
                        
        
                 
      Source domSource = new DOMSource(doc); 
      Result fileResult = new StreamResult(new File(filename));
      TransformerFactory factory = TransformerFactory.newInstance(); 
        Transformer transformer = factory.newTransformer(); 
        DOMImplementation domImpl = doc.getImplementation();
        DocumentType doctype = domImpl.createDocumentType("doctype","map","map.dtd");
        
    transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, doctype.getPublicId());
    transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, doctype.getSystemId());
        transformer.setOutputProperty(OutputKeys.ENCODING,"WINDOWS-1251"); 
        transformer.transform(domSource, fileResult);  
    }
    
    
    public void printAll(){
        for (Country country:countries){
             System.out.println(country.toString());
             country.printCities();
             
         }
         System.out.println();
         for (City city: cities){
             System.out.println(city.toString());
             
         }
           
         
    }
    
    
    
    public String getInfo(){
       StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for (Country country: countries){
             sb.append(country.getInfo());
             sb.append("\n");
         }
        return sb.toString();
    }
    
    
    public void loadFromFile(String filename) throws SAXException, ParserConfigurationException, IOException{
       File inputFile = new File(filename);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        
        builder.setErrorHandler(new SimpleErrorHandler());   
        Document doc = builder.parse(inputFile);
     
         NodeList nodeList = doc.getElementsByTagName("country");
                  for (int temp = 0; temp < nodeList.getLength(); temp++) {

         Country country;
            City c;
            Element node = (Element) nodeList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
               Element element = (Element) node;
              Integer countryID = Integer.parseInt(element.getAttribute("id"));  
                String countryName = element.getElementsByTagName("nameC").item(0).getTextContent(); 
                country = new Country(countryID,countryName);
                NodeList listCities = node.getElementsByTagName("city");
                countries.add(country);
            for (int j=0; j<listCities.getLength(); j++){
                Element city = (Element)listCities.item(j);            
                Integer cityID = Integer.parseInt(city.getAttribute("id"));  
                String cityName = city.getElementsByTagName("name").item(0).getTextContent(); 
                Boolean cityIsCap = Boolean.parseBoolean(city.getElementsByTagName("iscap").item(0).getTextContent());
                Integer numPeople = Integer.parseInt(city.getElementsByTagName("count").item(0).getTextContent());
               
              c = new City(cityName,cityID,numPeople,cityIsCap,countryID);
              cities.add(c);
              country.addCity(c);
            }   
               
            }
                  } 
        
        
    }      
    public String addCountry(int id, String name){
        for  (Country country: countries ){
            if (country.getID() == id){
                return "have such an ID";
            }
        }
        Country nC = new Country (id,name);
        countries.add(nC);
        return "ok";
    }       
    public String deleteCountry(int id){
        for  (Country country: countries ){
            
            if (country.getID() == id){
                
                for (Iterator<City> iterator = cities.iterator(); iterator.hasNext(); ) {
                    City value = iterator.next();
                    if (value.getCountry() == id) {
                        iterator.remove();
                    }
                }

                countries.remove(country);
                
                return "ok";
            }
        }
        
        return "don't have such an ID";        
        
    }     
    
    public String deleteCity(int id){
        for  (City city: cities ){
            if (city.getID() == id){
                Integer countryId = city.getCountry();
                for  (Country country: countries ){
                    if (Objects.equals(country.getID(), countryId)){
                        country.deleteCity(city);
                        break;
                    }
                }
                cities.remove(city);
                return "ok";
            }
        }
        return "don't have such an ID";        
        
    }     
    
    public String addCity(int id, String name, boolean isCapital, int count, int nameCountry){ 
        for  (City city: cities ){
            if (city.getID() == id){
                return "have such an ID";
            }
        }
        Boolean haveCountry = false;
        Country curr = null;
        for  (Country country: countries ){
            if (country.getID() == nameCountry){
                curr = country;
                haveCountry = true;
                break;
            }
        }
        if (haveCountry)     {
            City nC = new City (name,id,count,isCapital,nameCountry);
            curr.addCity(nC);
            cities.add(nC);
            return "ok";
        }else{
            return "don't have such  ID Country";
        }
    } 
    
    public Map(){
        countries = new ArrayList();
        cities = new ArrayList();
        forma.setVisible(true);
        forma.setMap(this);
    }
    public ArrayList<Country> getCountries(){
        return countries;
    }
    
     public ArrayList<City> getCities(){
        return cities;
    }
     
    private Boolean haveCountry(Integer id){
        for  (Country country: countries ){
                if (Objects.equals(country.getID(), id)){
                  return true;
                }
            }
        return false;
    } 
    private Boolean haveCity(Integer id){
        for  (City city: cities ){
                if (Objects.equals(city.getID(), id)){
                  return true;
                }
            }
        return false;
    } 
     
    public String changeCity(City prev, City newCity){
        Boolean haveCity =  haveCity(newCity.getID());
         Boolean haveCountry =  haveCountry(newCity.getCountry());
         if (!haveCountry) return "don't have such an ID for country";
        if (Objects.equals(prev.getID(), newCity.getID()) || !haveCity){
            for  (Country country: countries ){
                if (Objects.equals(country.getID(), prev.getCountry())){
                   country.deleteCity(prev);
                   break;
                }
            }

            cities.remove(prev);
             for  (Country country: countries ){
                if (Objects.equals(country.getID(), newCity.getCountry())){
                   country.addCity(newCity);
                   break;
                }
            }
             cities.add(newCity);
             return "ok";
        }else{
            return "already have such an ID for city";
        }
    }
     
    
    public String changeCountry(Country prev, Country newCountry){
        if (Objects.equals(prev.getID(), newCountry.getID())){
            if (prev.getName().equals(newCountry.getName()))return "ok";
            else{
                for  (Country country: countries ){
                    if (Objects.equals(country.getID(), prev.getID())){
                       country.setName(newCountry.getName());
                    }
                }
                return "ok";
            }
        }
        else{
            if (haveCountry(newCountry.getID())) return  "already have such an ID for country";
            for  (Country country: countries ){
                    if (Objects.equals(country.getID(), prev.getID())){
                       country.setName(newCountry.getName());
                       break;
                    }
                }
            for  (City city: cities ){
                if (Objects.equals(city.getCountry(), prev.getID())){
                    city.setCountry(newCountry.getID());
                }
            }
            return "ok";
        }
        
        
    }
}
