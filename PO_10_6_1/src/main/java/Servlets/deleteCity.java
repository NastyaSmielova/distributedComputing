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
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pc
 */
public class deleteCity extends HttpServlet {

    
    private Connection con = null;
    private Statement stmt = null; 
    
    private Boolean deleteCity(String id) {
        String sql = "DELETE FROM CITIES WHERE ID_CI = " + id;
        try {
        int c = stmt.executeUpdate(sql);
            return true;
        } catch (SQLException e)  {
           return false;
        }
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
               connect();
        deleteCity((request.getParameter("idCI").toString()));
        request.getRequestDispatcher("/pages/main.jsp").forward(request, response);
        return;

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
            request.getRequestDispatcher("/pages/deleteCity.jsp").forward(request, response);

    }
}
