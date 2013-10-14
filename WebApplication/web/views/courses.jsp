<%--
    Document   : newhome
    Created on : Sep 10, 2013, 10:02:04 AM
    Author     : AASHISH
--%>

<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="/resources/css/content/main_page.css" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <jsp:include page = "/includes/resources_top.html" />  
        <title>Equations Courses</title>
    </head>
    <body>
        <jsp:include page = "/includes/header.jsp" />
        <div id="main_content">
            <jsp:include page = "/includes/courses.html" />
            <jsp:include page = "/includes/footer.jsp">
                <jsp:param name="menu" value="menu_home" />
            </jsp:include>
        </div>
        <jsp:include page = "/includes/resources_bottom.html" />
        <jsp:include page = "/includes/analytics.html" />
    </body>
</html>