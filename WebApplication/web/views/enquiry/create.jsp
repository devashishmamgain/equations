<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    pageContext.setAttribute("page", "enquiry");
%>

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
                    <form:form method="POST" commandName="enquiry" id="enquiryForm">
                        <form:errors path="*" cssClass="errorblock" element="div"/>
                        <table width="100%">
                            <thead>
                                <tr>
                                    <th>Feedback/Enquiry Form</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>Type</td>
                                    <td>
                                        <form:select name="type" path="type">
                                            <form:option value="Enquiry" label="Enquiry" />
                                            <form:option value="Feedback" label="Feedback" />
                                        </form:select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Email</td>
                                    <td>
                                        <form:input type="text" id="email" name="email" path="email" class="required email" value="" />
                                    </td>
                                    <td>
                                        <form:errors path="email" cssClass="error" />
                                    </td>
                                </tr>
                                <tr>
                                    <td>Name</td>
                                    <td>
                                        <form:input type="text" name="name" path="name" class="required" value="" />
                                    </td>
                                </tr>
                                <tr>
                                    <td>Subject</td>
                                    <td>
                                        <form:input type="text" name="subject" path="subject" class="required" value="" />
                                    </td>
                                </tr>
                                <tr>
                                    <td>Comment</td>
                                    <td>
                                        <form:textarea name="comment" path="comment" rows="6" cols="20" class="required" />
                                    </td>
                                </tr>
                                <tr>
                                    <td>Contact Number</td>
                                    <td>
                                        <form:input type="text" name="contactNumber" path="contactNumber" value="" />
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2">
                                        <input type="submit" value="Submit" />
                                    </td>
                                </tr>
                        </table>
                    </form:form>
                </div>
            </div>
            <jsp:include page = "/includes/rightContent.html" />
            <jsp:include page = "/includes/footer_small.jsp">
                <jsp:param name="menu" value="menu_enquiry" />
            </jsp:include>
        </div>
        <jsp:include page = "/includes/resources_bottom.html" />
        <jsp:include page = "/includes/analytics.html" />
        <script type="text/javascript">
            $(function() {
                $("#enquiryForm").validate();
            });
        </script>
</body>
</html>