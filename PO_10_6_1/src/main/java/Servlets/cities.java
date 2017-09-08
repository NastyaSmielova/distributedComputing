/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pc
 */
public class cities extends HttpServlet {
    private Connection con = null;
    private Statement stmt = null; 
    ArrayList<cityDTO> cities = null;
    
    private void getCities(){
        cities.clear();
        String sql = "SELECT * FROM CITIES ";
        try {
            StringBuilder ans = new StringBuilder();

            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("CITIES:");
            while (rs.next()) {
                String name = rs.getString("NAME");
                Integer count = rs.getInt("COUNT");
                Integer isCap = rs.getInt("ISCAPITAL");
                Integer idCity = rs.getInt("ID_CI");
                Integer idCountry = rs.getInt("ID_CO");
//
//                ans.append(" >> "+ name +" "+name2);
//                ans.append("\n");
                cities.add(new cityDTO(name,idCity,count,isCap,idCountry));
            }
            rs.close();
                        System.out.println(ans.toString());

        } catch (SQLException e) {
            System.out.println("ERROR during geting list of COUNTRIES"+ "\n   >> "+e.getMessage());
        }
    }
    
    private void connect(){
            try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://"+"localhost"+":"+3306+"/"+"map";
            con = (Connection) DriverManager.getConnection(url, "root", "root");
            stmt = (Statement) con.createStatement();

            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
        }
    }
    
    public cities(){
        cities = new ArrayList<>();
        connect();
        getCities();
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
               connect();
        cities = new ArrayList<>();
        getCities();
        request.setAttribute("cities", cities);
        request.setAttribute("mess", "vdvdfvdf");
        request.getRequestDispatcher("/pages/cities.jsp").forward(request, response);

    }
}
