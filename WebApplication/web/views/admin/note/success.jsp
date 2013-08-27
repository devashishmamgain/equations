<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <jsp:include page = "/includes/resources_top.html" />
        <title>Equations</title>
    </head>

    <body>
        <jsp:include page = "/includes/header.jsp" />
        <div id="main_content">
           Notes submitted successfully <br/>

           Subject: ${note.subject} <br/>

           Content: ${note.noteContent.value}

            <!--
            <jsp:include page = "/includes/footer.jsp" />
            -->
        </div>
        <jsp:include page = "/includes/resources_bottom.html" />
        <jsp:include page = "/includes/analytics.html" />
    </body>
</html>
