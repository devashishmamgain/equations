
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page
	import="in.co.equations.modules.questionpaper.dataaccess.model.SectionBean"%>
<%@  taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EXAM PAPERS</title>
<jsp:include page = "/includes/resources_top.html" />
<style type="text/css" title="currentStyle">
@import "../css/demo_page.css";

@import "../css/demo_table.css";

@import "../css/themes/base/jquery-ui.css";

@import "../css/themes/smoothness/jquery-ui-1.7.2.custom.css";
</style>



<title>DataTables example</title>

<script type="text/javascript" language="javascript"
	src="../jquery-1.10.1.js"></script>
<script type="text/javascript" language="javascript"
	src="../jquery.dataTables.js"></script>
<script type="text/javascript" language="javascript"
	src="../jquery-ui-tabs.js"></script>
<script type="text/javascript" language="javascript"
	src="../AutoFill.js"></script>
<script type="text/javascript" language="javascript"
	src="../moment.min.js"></script>
<script src="../jquery.dataTables.columnFilter.js"
	type="text/javascript"></script>
<script src="../jquery.dataTables.min.js" type="text/javascript"></script>

<script src="../jquery-ui.js" type="text/javascript"></script>
<script class="jsbin"
	src="http://datatables.net/download/build/jquery.dataTables.nightly.js"></script>
<script src="resources/bootstrap/js/bootstrap.js"></script>

<script type="text/javascript">
	window.history.forward();
	function noBack() {
		window.history.forward();
	}
	function disableF5(e) {
		if ((e.which || e.keyCode) == 116)
			e.preventDefault();
	};
	$(document).ready(function() {
		$('#example').dataTable({

			"bScrollCollapse" : true,
			"bPaginate" : true,
			"bSort" : true,
			"bScrollInfinite" : true,
			"bScrollCollapse" : true,
			"bLengthChange" : true,
			"bJQueryUI" : true,

			"aoColumnDefs" : [ {
				"sType" : "html",
				"aTargets" : [ 2 ]
			} ]
		})

	});
</script>
</head>
<body onload="noBack();" onpageshow="if (event.persisted) noBack();"
	onunload="">
    <jsp:include page = "/includes/header.jsp" />
    <a href="createpaper.form" style="float:left;"><input type="button" value="CREATE PAPER" /></a>


	<form:form>

		<table cellpadding="0" cellspacing="4" border="0" class="display"
			id="example">
			<thead>
				<tr>
					<th>Exam</th>
					<th>Subject</th>
					<th>PaperName</th>
					<th>CreatedDate</th>
				</tr>


			</thead>
			<tbody>
				<c:forEach var="project" items="${results}" varStatus="i">
					<tr class="odd_gradeX" id="2">

						<td class="read_only" id="examName">${project.examName}</td>

						<td>${project.subjectName}</td>

						<td><a
							href="startExam.form?sectionName=${project.sectionName}"
							style="text-decoration: none">${project.sectionName}</a></td>


						<td id="createdDate" class="center">${project.createdDate}</td>

					</tr>
				</c:forEach>






			</tbody>
		</table>
	</form:form>

</body>
</html>