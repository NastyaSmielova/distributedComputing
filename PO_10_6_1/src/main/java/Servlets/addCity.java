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
import javafx.util.Pair;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pc
 */
public class addCity extends HttpServlet {

    private Connection con = null;
    private Statement stmt = null; 
    
    private Boolean addCity(String id, String name,String idCo, String count, String isCap){
        String sql = "INSERT INTO CITIES (ID_CI, ID_CO, NAME, COUNT, ISCAPITAL) "+
        "VALUES ("+id + " , "+idCo+", '"+name+"' , "+count+ ", " + isCap+")";
        try {
            stmt.executeUpdate(sql);
            return true;
        } catch (SQLException e)
        {
            System.out.println(e);
            return false;
        }
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
               connect();
        
        addCity((request.getParameter("idCI").toString()),
               request.getParameter("name").toString() ,(request.getParameter("idCO").toString()),
                (request.getParameter("count").toString()),(request.getParameter("isCap").toString()));
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
            request.getRequestDispatcher("/pages/addCity.jsp").forward(request, response);

    }

    
}
