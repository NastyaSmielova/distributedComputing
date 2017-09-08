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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pc
 */
public class countries extends HttpServlet {

        private Connection con = null;
    private Statement stmt = null; 
    ArrayList<countryDTO> countries = null;
    
    private void getCities(){
        countries.clear();
        String sql = "SELECT * FROM countries ";
        try {
            StringBuilder ans = new StringBuilder();

            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("get counties ");
            while (rs.next()) {
                String name = rs.getString("NAME");
                Integer idCountry = rs.getInt("ID_CO");
//
//                ans.append(" >> "+ name +" "+name2);
//                ans.append("\n");
                countries.add(new countryDTO(idCountry,name));
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
    

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
               connect();
        countries = new ArrayList<>();
        getCities();
        request.setAttribute("countries", countries);
        request.getRequestDispatcher("/pages/countries.jsp").forward(request, response);

    }
}
