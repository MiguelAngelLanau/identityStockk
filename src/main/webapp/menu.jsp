<%-- 
    Document   : menu
    Created on : 29 dic. 2020, 17:10:30
    Author     : Michi
--%>

<%@page import="stockkms.identity_stockk.common.RESTstock"%>
<%@page import="stockkms.identity_stockk.common.RESTportfolio"%>
<%@page import="stockkms.identity_stockk.rest.client.PortfolioRESTclient"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    String token = request.getParameter("token");
    
    PortfolioRESTclient portfolioProxy = new PortfolioRESTclient();
    RESTportfolio portfolio = portfolioProxy.getPortfolio(RESTportfolio.class, token);
    portfolioProxy.close();
    
%>    


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>xxxxxxxx</h1>
        
        El token es: <%=token%>
        <br>
        Y su portfolio es: <br>
        <u1>
        <% for (RESTstock s: portfolio.getStocks()){ %>
            <li><%=s.getTicker()%>: <%=s.getNumShares()%></li>
        <% }//for %>
        </u1>
    </body>
</html>
