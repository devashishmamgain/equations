<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <jsp:include page = "/includes/resources_top.html" />
        <title>Equations</title>
    </head>
    <body>
        <jsp:include page = "/includes/header.jsp" />
        <div id="main_content">
            <jsp:include page = "/includes/aboutus.html" />
            <jsp:include page = "/includes/rightContent.html" />
            <jsp:include page = "/includes/footer.jsp">
                <jsp:param name="menu" value="menu_aboutus" />
            </jsp:include>
        </div>
        <jsp:include page = "/includes/resources_bottom.html" />
        <jsp:include page = "/includes/analytics.html" />
    </body>
</html>