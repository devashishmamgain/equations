<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <jsp:include page = "/includes/resources_top.html" />
        <title>Equations</title>
    </head>

    <body>
        <jsp:include page = "/includes/header.jsp" />
        <div id="main_content">
            <div id="left_content">
                <div class="left_content">
                    <c:forEach var="r" items="${notifications}">
                        <div class="notification">
                            <c:if test="${loggedIn == 'true'}">
                                <a href="/admin/notification/edit.form?keyString=${r.keyString}">Edit</a>
                                <a href="/admin/notification/delete.form?keyString=${r.keyString}">Delete</a>
                            </c:if>
                            <h1>${r.subject}</h1>
                            ${r.content.value}
                        </div>
                    </c:forEach>
                </div>
            </div>
            <jsp:include page = "/includes/rightContent.html" />
            <jsp:include page = "/includes/footer_small.jsp">
                <jsp:param name="menu" value="menu_notification" />
            </jsp:include>
        </div>
        <jsp:include page = "/includes/resources_bottom.html" />
        <jsp:include page = "/includes/analytics.html" />
    </body>
</html>