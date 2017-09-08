<%-- 
    Document   : countries
    Created on : May 28, 2017, 1:19:36 PM
    Author     : pc
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <form action="/PO_10_6_1/countries" method = "get">
            <table >
            <c:forEach items="${countries}" var="item">
                <tr>
               <td> ${item.name} </td> 
                <td> ${item.idCO} </td> 
                </tr>
            </c:forEach>
            </table>
        </form>
                                         <jsp:include page="countries" flush="true" />

    </body>
</html>
