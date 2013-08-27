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
            <div id="left_content">
                <div class="left_content">
                    Thank you for submitting your ${enquiry.type}
                    <table>
                        <tr>
                            <td>Type :</td>
                            <td>${enquiry.type}</td>
                        </tr>
                        <tr>
                            <td>Email :</td>
                            <td>${enquiry.email.email}</td>
                        </tr>
                        <tr>
                            <td>Name :</td>
                            <td>${enquiry.name}</td>
                        </tr>
                        <tr>
                            <td>Subject :</td>
                            <td>${enquiry.subject}</td>
                        </tr>
                        <tr>
                            <td>Comment</td>
                            <td>${enquiry.comment.value}</td>
                        </tr>
                        <tr>
                            <td>Contact No.</td>
                            <td>${enquiry.contactNumber.number}</td>
                        </tr>
                    </table>
                </div>
            </div>

            <jsp:include page = "/includes/rightContent.html" />
            <jsp:include page = "/includes/footer_small.jsp">
                <jsp:param name="menu" value="menu_enquiry" />
            </jsp:include>
        </div>
        <jsp:include page = "/includes/resources_bottom.html" />
        <jsp:include page = "/includes/analytics.html" />
    </body>
</html>