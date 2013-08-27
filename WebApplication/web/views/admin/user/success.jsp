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
            <table>
                <tr>
                    <td>Role :</td>
                    <td>${eqUser.role.name}</td>
                </tr>
                <tr>
                    <td>Id No :</td>
                    <td>${eqUser.idNo}</td>
                </tr>
                <tr>
                    <td>Email :</td>
                    <td>${eqUser.email.email}</td>
                </tr>
                <tr>
                    <td>Name :</td>
                    <td>${eqUser.name}</td>
                </tr>
                <tr>
                    <td>Courses:</td>
                    <td>
                        <c:forEach var="course" items="${eqUser.courses}">
                            ${course.name}<br/>
                        </c:forEach>
                    </td>
                </tr>
                <tr>
                    <td>Correspondence Address:</td>
                    <td>${eqUser.correspondenceAddress.value}</td>
                </tr>
                <tr>
                    <td>Permanent Address:</td>
                    <td>${eqUser.permanentAddress.value}</td>
                </tr>
                <tr>
                    <td>Primary Contact Number:</td>
                    <td>${eqUser.primaryContactNumber.number}</td>
                </tr>
                <tr>
                    <td>Alternate Contact Number:</td>
                    <td>${eqUser.alternateContactNumber.number}</td>
                </tr>
                <tr>
                    <td>SMS Notification</td>
                    <td>${eqUser.smsNotification}</td>
                </tr>
            </table>

            <!--            
            <jsp:include page = "/includes/footer.jsp" />
            -->
        </div>
        <jsp:include page = "/includes/resources_bottom.html" />
        <jsp:include page = "/includes/analytics.html" />
    </body>
</html>