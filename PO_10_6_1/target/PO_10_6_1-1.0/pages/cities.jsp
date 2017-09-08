<%-- 
    Document   : cities
    Created on : May 28, 2017, 12:29:33 PM
    Author     : pc
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
        <style>
            table, th, td {
                border: 1px solid black;
                border-collapse: collapse;
            }
        </style>
        
    </head>
    <body>
        
        <form action="/PO_10_6_1/cities" method = "get">
            <table >
            <c:forEach items="${cities}" var="item">
                <tr>
               <td> ${item.name} </td> 
                <td> ${item.idCI} </td> 
                <td> ${item.idCO} </td> 
                <td>  ${item.count} </td> 
                </tr>
            </c:forEach>
            </table>
        </form>
                         <jsp:include page="cities" flush="true" />

    </body>
</html>
