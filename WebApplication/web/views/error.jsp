<%-- 
    Document   : newjsp1
    Created on : Mar 30, 2012, 11:23:49 PM
    Author     : Adarsh
--%>

<%@page isErrorPage="true" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/resources/css/error.css"/>
        <title>Error Page</title>
    </head>
    <body class="errorBack">
        <h1><span style="color: #F2F2F2">Mobi</span><span style="color:#49AFCD;font-weight:bold;">Texter</span></h1>
        <p class="errorDetail">You Caught us tweaking with our server.<br><br>Please Try After Sometime.<br><br> Status Code : ${pageContext.errorData.statusCode}</p>
        <!-- Stack trace -->
    </body>
</html>
