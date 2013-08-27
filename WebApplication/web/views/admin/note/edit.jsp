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
                    <c:set var="actionValue" value="/admin/note/update.form"/>
                </c:when>
                <c:otherwise>
                    <c:set var="actionValue" value="/admin/note/create.form"/>
                </c:otherwise>
            </c:choose>

            <form:form method="POST" commandName="note" id="createNote" action="${actionValue}">
                <form:errors path="*" cssClass="errorblock" element="div"/>
                <table width="100%">
                    <thead>
                        <tr>
                            <th>Create Notes Form</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${param.keyString ne null}">
                            <form:input type="hidden" name="keyString" path="keyString" value = "" />
                        </c:if>

                        <tr>
                            <td>Note Category</td>
                            <td>
                                <form:select name="noteCategoryKey" path="noteCategoryKey">
                                    <form:options items= "${noteCategories}" itemLabel="name" itemValue="key" />
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
                            <td>Notes Content</td>
                            <td>
                                <form:textarea id="noteContents" name="noteContents" path="noteContents" cols="50" rows="15" value="" />
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
                $("#createNote").validate();
            });
        </script>
        <jsp:include page = "/includes/analytics.html" />
    </body>
</html>