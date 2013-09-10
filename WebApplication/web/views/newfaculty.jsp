<%--
    Document   : newhome
    Created on : Sep 10, 2013, 10:02:04 AM
    Author     : AASHISH
--%>

<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="/resources-equations/css/content/main_page.css" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>

<script type="text/javascript" src="/resources-equations/scripts/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="/resources-equations/scripts/js/jquery-ui-1.8.1.custom.min.js"></script>
<script type="text/javascript" src="/resources-equations/scripts/jquery.corner.js"></script>
<script type="text/javascript" src="/resources-equations/scripts/menu_js/borders_js.js"></script>
<script type="text/javascript" src="/resources-equations/scripts/custom_js/main_content.js"></script>
<script type="text/javascript" src ="/resources-equations/scripts/js/jquery.validate.min.js"></script>

        <title>Equations</title>
    </head>
    <body>
        <jsp:include page = "/includes/bootstrap_header.jsp" />
        <div id="main_content">
            <jsp:include page = "/includes/faculty.html" />
            <jsp:include page = "/includes/rightContent.html" />  
            <jsp:include page = "/includes/footer.jsp">
                <jsp:param name="menu" value="menu_faculty" />
            </jsp:include>
        </div>
        <jsp:include page = "/includes/resources_bottom.html" />
        <jsp:include page = "/includes/analytics.html" />
    </body>
</html>