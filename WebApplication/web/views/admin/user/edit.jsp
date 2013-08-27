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

            <c:choose>
                <c:when test="${param.id ne null}">
                    <c:set var="actionValue" value="/admin/user/update.form"/>
                </c:when>
                <c:otherwise>
                    <c:set var="actionValue" value="/admin/user/save.form"/>
                </c:otherwise>
            </c:choose>

            <form:form method="POST" commandName="eqUser" id="createUser" action="${actionValue}">
                <form:errors path="*" cssClass="errorblock" element="div"/>
                <table width="100%">
                    <thead>
                        <tr>
                            <th>Create/Update User Form</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${param.keyString ne null}">
                            <form:input type="hidden" name="keyString" path="keyString" value = "${eqUser.keyString}" />
                        </c:if>

                        <tr>
                            <td>Type</td>
                            <td>  
                                <form:select name="roleId" path="roleId">
                                    <form:options items= "${availableRoles}" itemLabel="name" itemValue="key.name" />
                                </form:select>                                
                            </td>
                        </tr>
                        <tr>
                            <td>Id No.</td>
                            <td>                                 
                                <form:input type="text" id="idNo" name="idNo" path="idNo" value="${eqUser.idNo}" />
                            </td>
                        </tr>
                        <tr>
                            <td>Courses</td>
                            <td>
                                <form:select name="coursesKey" path="coursesKey">
                                    <form:options items="${courses}" itemLabel="name" itemValue="key" />
                                </form:select>
                            </td>
                        </tr>
                        <tr>
                            <td>Email</td>
                            <td>
                                <form:input type="text" id="email" name="email" path="email" value="${eqUser.email.email}" />
                            </td>                           
                        </tr>
                        <tr>
                            <td>Name</td>
                            <td>
                                <form:input type="text" id="name" name="name" path="name" class="name required" value="${eqUser.name}" />
                            </td>
                        </tr>
                        <tr>
                            <td>Correspondence Address</td>
                            <td>
                                <form:input type="text" id="correspondenceAddress" name="correspondenceAddress" path="correspondenceAddress" value="${eqUser.correspondenceAddress.value}" />
                            </td>
                        </tr>
                        <tr>
                            <td>Permanent Address</td>
                            <td>
                                <form:input type="text" id="permanentAddress" name="permanentAddress" path="permanentAddress" value="${eqUser.permanentAddress.value}" />
                            </td>
                        </tr>
                        <tr>
                            <td>Primary Contact Number</td>
                            <td>
                                <form:input type="text" id="primaryContactNumber" name="primaryContactNumber" path="primaryContactNumber" value="${eqUser.primaryContactNumber.number}" />
                            </td>
                        <tr>
                            <td>Alternate Contact Number</td>
                            <td>
                                <form:input type="text" id="alternateContactNumber" name="alternateContactNumber" path="alternateContactNumber" value="${eqUser.alternateContactNumber.number}" />
                            </td>
                        </tr>
                        <tr>
                            <td>SMS Notification</td>
                            <td>
                                <form:checkbox id="smsNotification" name="smsNotification" path="smsNotification" value="${eqUser.smsNotification}" />
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
                $("#createUser").validate();
            });
        </script>
        <jsp:include page = "/includes/analytics.html" />
    </body>
</html>