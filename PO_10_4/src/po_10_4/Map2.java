package po_10_4;

import java.sql.*;
import java.util.ArrayList;
import javafx.util.Pair;
public class Map2{
    private Connection con = null;
    private Statement stmt = null; 
    
    public Map2(String DBName, String ip, int port)throws Exception{
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        String url = "jdbc:mysql://"+ip+":"+port+"/"+DBName;
        con = DriverManager.getConnection(url, "root", "root");
        stmt = con.createStatement();
        
    }
    public void stop() throws SQLException{
       con.close();
    }
    
    
    public Pair<Boolean,String> addCountry(String id, String name){
        String sql = "INSERT INTO COUNTRIES (ID_CO, NAME)" +
        "VALUES ("+id+", '"+name+"')";
        try {
            stmt.executeUpdate(sql);
            return new Pair(true,"country "+name+" has been  added successfully!");
        } catch (SQLException e)
        {
            String ans = "Error! country "+name+" has NOT been added!";
            ans += " >> "+e.getMessage();
            return new Pair(false,ans);
        }
    }
    
    public Pair<Boolean,String> addCity(String id, String name,String idCo, String count, String isCap){
        String sql = "INSERT INTO CITIES (ID_CI, ID_CO, NAME, COUNT, ISCAPITAL) "+
        "VALUES ("+id + ", "+idCo+", '"+name+"', "+count+ ", " + isCap+")";
        try {
            stmt.executeUpdate(sql);
            return new Pair(true,"city "+name+" has been added successfully!");
        } catch (SQLException e)
        {
            StringBuilder ans = new StringBuilder();
            ans.append("Error! city ");
            ans.append(name);
            ans.append(" has NOT been added! >> ");
            ans.append(e.getMessage());
            return new Pair(false,ans.toString());
        }
    }
    public String deleteCity(String id) {
        String sql = "DELETE FROM CITIES WHERE ID_CI = " + id;
        try {
        int c = stmt.executeUpdate(sql);
        if (c>0){
            return "1";
             
        } else{
            return ("0#city with id "+id +" was NOT found!");
             
            }
        } catch (SQLException e)  {
           return ("0#ERROR while deleting city with id "+id+"  >> "+e.getMessage());
        }
    }
    
    public String deleteCountry(String id) {
        String sql = "DELETE FROM COUNTRIES WHERE ID_CO = " + id;
        try {
        int c = stmt.executeUpdate(sql);
        if (c > 0){
            return ("1");
        } else{
           return ("0#country with id " + id +" was NOT found!");
        }
        } catch (SQLException e)  {
           return ("0#ERROR while deleting country with id "+id+ " >> "+e.getMessage());
           
        }
    }
    
        public Pair<Boolean,String> changeCountryName(String id, String name){
        String sql =  "UPDATE COUNTRIES SET NAME = '"+name +"' WHERE ID_CO = "+id +";";
        try {
            stmt.executeUpdate(sql);
            return new Pair(true,"country id "+id +" has new name "+ name);
        } catch (SQLException e)
        {
            return new Pair(false,"Error! country id "+id +"DOESN'T have new name "+ name +"\n   >> "+e.getMessage());
        }
    }
    public Pair<Boolean,String> changeCityInfo(String id, String name,String idCo, String count, String isCap){
        String sql = "UPDATE CITIES SET NAME = '"+name +"', ID_CO = " +idCo +", COUNT = " +count +
                " , ISCAPITAL = "+ isCap +" WHERE ID_CI = "+id +";";
        try {
            stmt.executeUpdate(sql);
            return new Pair(true,"city  id "+id+" has been changed the info successfully!");
        } catch (SQLException e)
        {
            return new Pair(false,"Error! city id "+id+" has NOT been changed the info successfully!"+ "\n   >> "+e.getMessage());
        }
    }
    
    public String showCountries(){
        String sql = "SELECT ID_CO, NAME FROM COUNTRIES";
        try {
            StringBuilder ans = new StringBuilder();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("ID_CO");
                String name = rs.getString("NAME");
                ans.append("#").append(id).append("#").append(name);
            }
            rs.close();
            return ("1"+ans.toString());
        } catch (SQLException e) {
            return ("0#ERROR during geting list of COUNTRIES : "+e.getMessage());
        }
    }
    public String showCities(){
        String sql = "SELECT * FROM CITIES";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            StringBuilder ans = new StringBuilder();
            while (rs.next()){
                int id = rs.getInt("ID_CI");
                int idCo = rs.getInt("ID_CO");
                String name = rs.getString("NAME");
                int count = rs.getInt("COUNT");
                int isCap = rs.getInt("ISCAPITAL");
                ans.append("#").append(id).append("#").append(name).append("#").append(idCo).append("#").append(count).append("#").append(isCap);
            }
            rs.close();
            return ("1"+ans.toString());
        } catch (SQLException e) {
            return ("0#ERROR during geting list of cities >> "+e.getMessage());
        }
    }
    
    public Pair<Boolean,String> getCity(String id){
        String sql = "SELECT * FROM CITIES WHERE ID_CI = "+ id + ";";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            StringBuilder ans = new StringBuilder();
            while (rs.next()){
                Integer idCo = rs.getInt("ID_CO");
                String name = rs.getString("NAME");
                Integer count = rs.getInt("COUNT");
                Integer isCap = rs.getInt("ISCAPITAL");
                ans.append(id);
                ans.append("#");
                ans.append(name);
                ans.append("#");
                ans.append(idCo.toString());
                ans.append("#");
                ans.append(count.toString()); 
                ans.append("#");
                ans.append(isCap.toString());
            }
            rs.close();
            return new Pair(true,ans.toString());
        } catch (SQLException e) {
            return new Pair(false,"ERROR during geting list of cities"+ "\n   >> "+e.getMessage());
        }
    }
    
    public String arrayCountries(){
        String sql = "SELECT * FROM COUNTRIES";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            StringBuilder ans = new StringBuilder();
            while (rs.next()){
                String name = rs.getString("NAME");
                int id = rs.getInt("ID_CO");
                ans.append("#").append(id);
            }
            rs.close();
            return  ("1"+ans.toString());
        } catch (SQLException e) {
            return "0#ERROR during geting list of countries >> "+e.getMessage();
        }
    }
    public Pair<Boolean,String> getCountryName(String id){
        String sql = "SELECT * FROM COUNTRIES WHERE ID_CO = "+ id+";";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            String name ="";

            ArrayList<String> ans = new ArrayList();
            while (rs.next()){
                name = rs.getString("NAME");
            }
            rs.close();
            return new Pair(true,name);
        } catch (SQLException e) {
            return new Pair(false,"ERROR during geting list of countries"+ "   >> "+e.getMessage());
        }
    }
    
    public Pair<Boolean,String> arrayCities(){
        String sql = "SELECT * FROM CITIES";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            StringBuilder ans = new StringBuilder();
            while (rs.next()){
                String name = rs.getString("NAME");
                int id = rs.getInt("ID_CI");
                ans.append("#").append(id);
            }
            rs.close();
            return new Pair(true,ans.toString());
        } catch (SQLException e) {
            return new Pair(false,"ERROR during geting list of cities"+ "  >> "+e.getMessage());
        }
    }

    
    
    public String showCitiesFromCountry(String idCo){
        String sql = "SELECT * FROM CITIES WHERE ID_CO = " + idCo;
        try {
            Integer amount = 0;
            StringBuilder ans = new StringBuilder();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                amount++;
                int id = rs.getInt("ID_CI");
                String name = rs.getString("NAME");
                int count = rs.getInt("COUNT");
                int isCap = rs.getInt("ISCAPITAL");
                ans.append("#").append(id).append("#").append(name).append("#").append(idCo).append("#").append(count).append("#").append(isCap);
            }
            rs.close();
            ans.append("#total number : ").append(amount);
            return "1"+ans.toString();
        } catch (SQLException e) {
            return "0#ERROR during geting list of cities >> "+e.getMessage();
        }
    }
    public Pair<Boolean,String> showCapitals(){
        String sql = "SELECT * FROM CITIES WHERE ISCAPITAL = 1;" ;
        try {
            StringBuilder ans = new StringBuilder();
            ResultSet rs = stmt.executeQuery(sql);
            ans.append("CITIES which are capitals "  +" : \n");
            while (rs.next()){
                int id = rs.getInt("ID_CI");
                String name = rs.getString("NAME");
                int idCo = rs.getInt("ID_CO");
                int count = rs.getInt("COUNT");
                int isCap = rs.getInt("ISCAPITAL");
                ans.append("#").append(id).append("#").append(name).append("#").append(idCo).append("#").append(count).append("#").append(isCap).append("\n");
            }
            rs.close();
            return new Pair(true, ans.toString());
        } catch (SQLException e) {
            return new Pair(false,"ERROR during geting list of cities"+ "\n   >> "+e.getMessage());
        }
    }
 }