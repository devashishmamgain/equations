<%-- 
    Document   : home-new
    Created on : Sep 24, 2013, 10:44:44 AM
    Author     : AASHISH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
    </head>
    <body style="margin: 0;padding: 0;">
        <div style="width:90%; height:170px; margin:auto; position:relative;">
            <jsp:include page = "/includes/header.jsp" />
        </div>
        <div style="width:90%; height:auto; margin:auto; position:relative;">
            <jsp:include page = "/includes/home.html" />

        </div>
        <div style="width:90%; height:120px; margin:auto; position:relative;">
            <jsp:include page = "/includes/footer.jsp" />

        </div>
    </body>
</html>
