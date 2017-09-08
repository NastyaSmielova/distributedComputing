/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package po_10_2;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import forms.MainForm;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;
public class Map2{
    private Connection con = null;
    private Statement stmt = null; 
    
    public Map2(String DBName, String ip, int port)throws Exception{
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        String url = "jdbc:mysql://"+ip+":"+port+"/"+DBName;
        con = DriverManager.getConnection(url, "root", "root");
        stmt = con.createStatement();
        MainForm form = new MainForm();
        form.setMap(this);
        form.setVisible(true);
        form.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    stop();
                } catch (SQLException ex) {
                    Logger.getLogger(Map2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    public void stop() throws SQLException{
       con.close();
    }
    
    
    public Pair<Boolean,String> addCountry(int id, String name){
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
    
    public Pair<Boolean,String> addCity(int id, String name,int idCo, int count, int isCap){
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
            ans.append(" has NOT been added! \n   >> ");
            ans.append(e.getMessage());
            return new Pair(false,ans.toString());
        }
    }
    public Pair<Boolean,String> deleteCity(int id) {
        String sql = "DELETE FROM CITIES WHERE ID_CI = " + id;
        try {
        int c = stmt.executeUpdate(sql);
        if (c>0){
            return new Pair(true,"city with id " + id +" was removed successfully !");
             
        } else{
            return new Pair(false,"city with id "+id +" was NOT found!");
             
            }
        } catch (SQLException e)  {
           return new Pair(false,"ERROR while deleting city with id "+id+"\n   >> "+e.getMessage());
        }
    }
    
    public Pair<Boolean,String> deleteCountry(int id) {
        String sql = "DELETE FROM COUNTRIES WHERE ID_CO = " + id;
        try {
        int c = stmt.executeUpdate(sql);
        if (c>0){
            return new Pair(true,"country with id "+ id +" was removed successfully !");
        } else{
           return new Pair(false,"country with id " + id +" was NOT found!");
        }
        } catch (SQLException e)  {
           return new Pair(false,"ERROR while deleting country with id "+id+ "\n   >> "+e.getMessage());
           
        }
    }
    
        public Pair<Boolean,String> changeCountryName(int id, String name){
        String sql =  "UPDATE COUNTRIES SET NAME = '"+name +"' WHERE ID_CO = "+id +";";
        try {
            stmt.executeUpdate(sql);
            return new Pair(true,"country id "+id +" has new name "+ name);
        } catch (SQLException e)
        {
            return new Pair(false,"Error! country id "+id +"DOESN'T have new name "+ name +"\n   >> "+e.getMessage());
        }
    }
    public Pair<Boolean,String> changeCityInfo(int id, String name,int idCo, int count, int isCap){
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
    
    public Pair<Boolean,String> showCountries(){
        String sql = "SELECT ID_CO, NAME FROM COUNTRIES";
        try {
            StringBuilder ans = new StringBuilder();

            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("COUNTRIES:");
            while (rs.next()) {
                int id = rs.getInt("ID_CO");
                String name = rs.getString("NAME");
                ans.append(" >> "+ id + " - " + name);
                ans.append("\n");
            }
            rs.close();
            return new Pair(true,ans.toString());
        } catch (SQLException e) {
            return new Pair(false,("ERROR during geting list of COUNTRIES")+ "\n   >> "+e.getMessage());
        }
    }
    public Pair<Boolean,String> showCities(){
        String sql = "SELECT * FROM CITIES";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            StringBuilder ans = new StringBuilder();
            ans.append("CITIES:\n");
            while (rs.next()){
                int id = rs.getInt("ID_CI");
                int idCo = rs.getInt("ID_CO");
                String name = rs.getString("NAME");
                int count = rs.getInt("COUNT");
                int isCap = rs.getInt("ISCAPITAL");
                ans.append(" >> "+ id + " - " + name + " - " + idCo + " - " + count + " - " + isCap + "\n");
            }
            rs.close();
            return new Pair(true,ans.toString());
        } catch (SQLException e) {
            return new Pair(false,"ERROR during geting list of cities"+ "\n   >> "+e.getMessage());
        }
    }
    
    public Pair<Boolean,ArrayList<String>> getCity(Integer id){
        String sql = "SELECT * FROM CITIES WHERE ID_CI = "+ id + ";";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            ArrayList ans = new ArrayList();
            while (rs.next()){
                Integer idCo = rs.getInt("ID_CO");
                String name = rs.getString("NAME");
                Integer count = rs.getInt("COUNT");
                Integer isCap = rs.getInt("ISCAPITAL");
                ans.add(id.toString());
                ans.add(name);
                ans.add(idCo.toString());
                ans.add(count.toString()); 
                ans.add(isCap.toString());
            }
            rs.close();
            return new Pair(true,ans);
        } catch (SQLException e) {
            return new Pair(false,"ERROR during geting list of cities"+ "\n   >> "+e.getMessage());
        }
    }
    
    public Pair<Boolean,ArrayList> arrayCountries(){
        String sql = "SELECT * FROM COUNTRIES";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            ArrayList<String> ans = new ArrayList();
            while (rs.next()){
                String name = rs.getString("NAME");
                int id = rs.getInt("ID_CO");
                ans.add(""+id );
            }
            rs.close();
            return new Pair(true,ans);
        } catch (SQLException e) {
            return new Pair(false,"ERROR during geting list of countries"+ "\n   >> "+e.getMessage());
        }
    }
    public Pair<Boolean,String> countryName(Integer id){
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
            return new Pair(false,"ERROR during geting list of countries"+ "\n   >> "+e.getMessage());
        }
    }
    
    public Pair<Boolean,ArrayList> arrayCities(){
        String sql = "SELECT * FROM CITIES";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            ArrayList<String> ans = new ArrayList();
            while (rs.next()){
                String name = rs.getString("NAME");
                int id = rs.getInt("ID_CI");
                ans.add(""+id );
            }
            rs.close();
            return new Pair(true,ans);
        } catch (SQLException e) {
            return new Pair(false,"ERROR during geting list of cities"+ "\n   >> "+e.getMessage());
        }
    }

    
    
    public Pair<Boolean,String> showCitiesFromCountry(int idCo){
        String sql = "SELECT * FROM CITIES WHERE ID_CO = " + idCo;
        try {
            Integer amount = 0;
            StringBuilder ans = new StringBuilder();
            ResultSet rs = stmt.executeQuery(sql);
            ans.append("CITIES from country with id " + idCo +" : \n");
            while (rs.next()){
                amount++;
                int id = rs.getInt("ID_CI");
                String name = rs.getString("NAME");
                int count = rs.getInt("COUNT");
                int isCap = rs.getInt("ISCAPITAL");
                ans.append(" >> "+ id + " - " + name + " - " + idCo + " - " + count + " - " + isCap+"\n");
            }
            ans.append("\n\namount = " + amount);
            rs.close();
            return new Pair(true, ans.toString());
        } catch (SQLException e) {
            return new Pair(false,"ERROR during geting list of cities \n   >> "+e.getMessage());
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
                ans.append(" >> "+ id + " - " + name + " - " + idCo + " - " + count + " - " + isCap+"\n");
            }
            rs.close();
            return new Pair(true, ans.toString());
        } catch (SQLException e) {
            return new Pair(false,"ERROR during geting list of cities"+ "\n   >> "+e.getMessage());
        }
    }
 }