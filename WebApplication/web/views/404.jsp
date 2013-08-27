<%-- 
    Document   : 404
    Created on : Mar 26, 2013, 11:12:50 PM
    Author     : Anuranjit
--%>

<%@page  isErrorPage="true" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/resources/css/error.css"/>
        <title>Page Not Found</title>
    </head>
    <body class="errorBack">
        <h1><span style="color: #F2F2F2">Equations</span></h1>
        <p class="errorDetail">Status Code : ${pageContext.errorData.statusCode} <br><br> We were unable to find the page you requested for <b><i>${pageContext.errorData.requestURI}</i></b><br><br>Go to <a href="/">Home Page</a></p>
    </body>
</html>
