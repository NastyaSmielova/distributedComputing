/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package po_10_2;
import java.sql.*;
public class Map{
    private Connection con = null;
    private Statement stmt = null; 
    
    public Map(String DBName, String ip, int port)throws Exception{
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        String url = "jdbc:mysql://"+ip+":"+port+"/"+DBName;
        con = DriverManager.getConnection(url, "root", "root");
        stmt = con.createStatement();
    }
    public void stop() throws SQLException{
       con.close();
    }
    
    
    public boolean addCountry(int id, String name){
        String sql = "INSERT INTO COUNTRIES (ID_CO, NAME)" +
        "VALUES ("+id+", '"+name+"')";
        try {
            stmt.executeUpdate(sql);
            System.out.println("country "+name+" has been  added successfully!");
            return true;
        } catch (SQLException e)
        {
            System.out.println("Error! country "+name+
            " has NOT been added!");
            System.out.println(" >> "+e.getMessage());
            return false;
        }
    }
    
    public boolean addCity(int id, String name,int idCo, int count, int isCap){
        String sql = "INSERT INTO CITIES (ID_CI, ID_CO, NAME, COUNT, ISCAPITAL) "+
        "VALUES ("+id + ", "+idCo+", '"+name+"', "+count+ ", " + isCap+")";
        try {
            stmt.executeUpdate(sql);
            System.out.println("city "+name+" has been added successfully!");
            return true;
        } catch (SQLException e)
        {
            System.out.println("Error! city "+name+
            " has NOT been added!");
            System.out.println(" >> "+e.getMessage());
            return false;
        }
    }
    public boolean deleteCity(int id) throws SQLException{
        String sql = "DELETE FROM CITIES WHERE ID_CI = " + id;
        try {
        int c = stmt.executeUpdate(sql);
        if (c>0){
            System.out.println("city with id "
            + id +" was removed successfully !");
            return true;
        } else{
            System.out.println("city with id "
            + id +" was NOT found!");
            return false;
            }
        } catch (SQLException e)  {
            System.out.println(
            "ERROR while deleting city with id "+id);
            System.out.println(" >> "+e.getMessage());
            return false;
        }
    }
    
    public boolean deleteCountry(int id) throws SQLException{
        String sql = "DELETE FROM COUNTRIES WHERE ID_CO = " + id;
        try {
        int c = stmt.executeUpdate(sql);
        if (c>0){
            System.out.println("country with id "
            + id +" was removed successfully !");
            return true;
        } else{
            System.out.println("country with id "
            + id +" was NOT found!");
            return false;
            }
        } catch (SQLException e)  {
            System.out.println(
            "ERROR while deleting country with id "+id);
            System.out.println(" >> "+e.getMessage());
            return false;
        }
    }
    public void showCountries(){
        String sql = "SELECT ID_CO, NAME FROM COUNTRIES";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("COUNTRIES:");
            while (rs.next())            {
            int id = rs.getInt("ID_CO");
            String name = rs.getString("NAME");
            System.out.println(" >> "+ id + " - " + name);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("ERROR during geting list of COUNTRIES");
            System.out.println(" >> "+e.getMessage());
        }
    }
    public void showCities(){
        String sql = "SELECT * FROM CITIES";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("CITIES:");
            while (rs.next()){
                int id = rs.getInt("ID_CI");
                int idCo = rs.getInt("ID_CO");
                String name = rs.getString("NAME");
                int count = rs.getInt("COUNT");
                int isCap = rs.getInt("ISCAPITAL");
                System.out.println(" >> "+ id + " - " + name + " - " + idCo + " - " + count + " - " + isCap);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("ERROR during geting list of cities");
            System.out.println(" >> "+e.getMessage());
        }
    }
    
    public void changeCountryName(int id, String name){
        String sql =  "UPDATE COUNTRIES SET NAME = '"+name +"' WHERE ID_CO = "+id +";";
        try {
            stmt.executeUpdate(sql);
            System.out.println("country id "+id +" has new name "+ name);
        } catch (SQLException e)
        {
            System.out.println("Error! country id "+id +"DOESN'T have new name "+ name);
            System.out.println(" >> "+e.getMessage());
        }
    }
    public void changeCityInfo(int id, String name,int idCo, int count, int isCap){
        String sql = "UPDATE CITIES SET NAME = '"+name +"', ID_CO = " +idCo +", COUNT = " +count +
                " , ISCAPITAL = "+ isCap +" WHERE ID_CI = "+id +";";
        try {
            stmt.executeUpdate(sql);
            System.out.println("city  id "+id+" has been changed the info successfully!");
        } catch (SQLException e)
        {
            System.out.println("Error! city id "+id+" has NOT been changed the info successfully!");
            System.out.println(" >> "+e.getMessage());
        }
    }
    
    
    public void showCitiesFromCountry(int idCo){
        String sql = "SELECT * FROM CITIES WHERE ID_CO = " + idCo;
        try {
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("CITIES from country with id " + idCo +" : ");
            while (rs.next()){
                int id = rs.getInt("ID_CI");
                String name = rs.getString("NAME");
                int count = rs.getInt("COUNT");
                int isCap = rs.getInt("ISCAPITAL");
                System.out.println(" >> "+ id + " - " + name + " - " + idCo + " - " + count + " - " + isCap);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("ERROR during geting list of cities");
            System.out.println(" >> "+e.getMessage());
        }
    }
    public void showCapitals(){
        String sql = "SELECT * FROM CITIES WHERE ISCAPITAL = 1;" ;
        try {
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("CITIES which are capitals "  +" : ");
            while (rs.next()){
                int id = rs.getInt("ID_CI");
                String name = rs.getString("NAME");
                int idCo = rs.getInt("ID_CO");
                int count = rs.getInt("COUNT");
                int isCap = rs.getInt("ISCAPITAL");
                System.out.println(" >> "+ id + " - " + name + " - " + idCo + " - " + count + " - " + isCap);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("ERROR during geting list of cities");
            System.out.println(" >> "+e.getMessage());
        }
    }
 }