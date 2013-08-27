<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <jsp:include page = "/includes/resources_top.html" />
        <title>Equations</title>
        <script type="text/javascript" language="javascript" src="/resources/js/tinymce/tiny_mce.js"></script>
        <script language="javascript" type="text/javascript">
            tinyMCE.init({
                theme : "advanced",
                mode : "textareas"
            });
        </script>
    </head>

    <body>
        <jsp:include page = "/includes/header.jsp" />
        <div id="main_content">

            <c:choose>
                <c:when test="${param.keyString ne null}">
                    <c:set var="actionValue" value="/admin/notification/update.form"/>
                </c:when>
                <c:otherwise>
                    <c:set var="actionValue" value="/admin/notification/save.form"/>
                </c:otherwise>
            </c:choose>

            <form:form method="POST" commandName="notification" id="createNotification" action="${actionValue}">
                <form:errors path="*" cssClass="errorblock" element="div"/>
                <table width="100%">
                    <thead>
                        <tr>
                            <th>Create/Update Notification Form</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${param.keyString ne null}">
                            <form:input type="hidden" name="keyString" path="keyString" value = "" />
                        </c:if>

                        <tr>
                            <td>Type</td>
                            <td>
                                <form:select name="notificationTypeKey" path="notificationTypeKey">
                                    <form:options items= "${notificationTypes}" itemLabel="name" itemValue="key" />
                                </form:select>
                            </td>
                        </tr>
                        <tr>
                            <td>Subject</td>
                            <td>                                
                                <form:input type="text" id="subject" name="subject" path="subject" value="" />
                            </td>
                        </tr>
                        <tr>
                            <td>Content</td>
                            <td>                               
                                <form:textarea id="content" name="content" path="content" cols="50" rows="15" value="" />
                            </td>
                        </tr>
                        <tr>
                            <td>Bank Name</td>
                            <td>
                                <form:input type="text" id="bankName" name="bankName" path="bankName" value="" />
                            </td>
                        </tr>
                        <tr>
                            <td>Vacancies</td>
                            <td>
                                <form:input type="text" id="vacancies" name="vacancies" path="vacancies" value="" />
                            </td>
                        </tr>
                        <tr>
                            <td>Exam</td>
                            <td>
                                <form:input type="text" id="exam" name="exam" path="exam" value="" />
                            </td>
                        </tr>
                        <tr>
                            <td>Exam Date</td>
                            <td>
                                <form:input type="text" id="examDate" name="examDate" path="examDate" value="" />
                            </td>
                        </tr>
                        <tr>
                            <td>Submission Date</td>
                            <td>
                                <form:input type="text" id="submissionDate" name="submissionDate" path="submissionDate" value="" />
                            </td>
                        </tr>
                        <tr>
                            <td>Application Fee</td>
                            <td>
                                <form:input type="text" id="applicationFee" name="applicationFee" path="applicationFee" value="" />
                            </td>
                        </tr
                        <tr>
                            <td>Links</td>
                            <td>
                                <form:input type="text" id="links" name="links" path="links" value="" />
                            </td>
                        </tr>
                         <tr>
                            <td>Challan Link</td>
                            <td>
                                <form:input type="text" id="challanLink" name="challanLink" path="challanLink" value="" />
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <input type="submit" value="Submit" />
                            </td>
                        </tr>
                </table>
            </form:form>
            <!--
            <jsp:include page = "/includes/footer.jsp" />
            -->
        </div>
        <jsp:include page = "/includes/resources_bottom.html" />
        <script type="text/javascript">
            $(function() {
                $("#createNotification").validate();
            });
        </script>
        <jsp:include page = "/includes/analytics.html" />
    </body>
</html>