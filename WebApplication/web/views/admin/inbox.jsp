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

            <table>
                <thead>
                <th>Dated</th>
                <th>Sender Name</th>
                <th>Sender Email</th>
                <th>Subject</th>
                <th>Contact Number</th>
                <th>Comment</th>
                </thead>

                <c:forEach var="r" items="${enquiryList}">
                    <tbody>
                        <tr>
                            <td width="10%">
                                ${r.dated}
                            </td>
                            <td width="10%">
                                ${r.name}
                            </td>
                            <td width="10%">
                                ${r.email.email}
                            </td>
                            <td width="10%">
                                ${r.subject}
                            </td>
                            <td width="10%">
                                ${r.contactNumber.number}
                            </td>
                            <td>
                                ${r.comment.value}
                            </td>
                        </tr>
                    </tbody>
                </c:forEach>
            </table>
            <!--
            <jsp:include page = "/includes/footer.jsp" />
            -->
        </div>
        <jsp:include page = "/includes/resources_bottom.html" />            
        <jsp:include page = "/includes/analytics.html" />
    </body>
</html>