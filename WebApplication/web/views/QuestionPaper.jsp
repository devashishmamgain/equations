<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@  taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<jsp:include page = "/includes/resources_top.html" />
<script type="text/javascript" src="../jquery-1.10.1.js"></script>
<script type="text/javascript"
	src="http://tinymce.cachefly.net/4.0/tinymce.min.js"></script>
<script type="text/javascript" language="javascript"
	src="../tinymce/tiny_mce.js"></script>
<script type="text/javascript" src="../jQuery-session.js"></script>
<script type="text/javascript">
	function addDate() {
		date = new Date();
		var month = date.getMonth() + 1;
		var day = date.getDate();
		var year = date.getFullYear();
		var hours = date.getHours();
		var minutes = date.getMinutes();
		if (minutes < 10) {
			minutes = "0" + minutes
		}
		if (document.getElementById('createdDate').value == '') {
			document.getElementById('createdDate').value = day + '-' + month
					+ '-' + year + ' ' + hours + ':' + minutes;
		}
	}

	$(document)
			.ready(
					function() {
						tinyMCE.init({
							theme : "advanced",
							mode : "textareas"
						});

						$("input[type=checkbox]")
								.click(
										function() {

											var isChecked = $(this).is(
													':checked');
											if (isChecked) {
												var cls = $(this).attr('class');
												var option = $(
														"input:text[id=" + cls
																+ "]").val();
												if (option == '') {
													alert("Please Enter The Option First");
													$("." + cls).prop(
															'checked', false);
												} else {
													$("." + cls).val(option);

												}
											} else {
												$("." + cls).val('');
											}
										});

						$("input:submit")
								.click(
										function() {
											$.session.set('submitVal', $(this)
													.val());
											var subjectName = $('#subjectName')
													.val();
											var examName = $('#examName').val();

											var time = $('#time').val();
											var sectionName = $('#sectionName')
													.val();
											var question = tinyMCE.get(
													'questionsId').getContent();
											var option1 = $('#option1').val();
											var option2 = $('#option2').val();
											var option3 = $('#option3').val();
											var option4 = $('#option4').val();
											var errMsg = '';
											var isAddQuestion = ($(this).val() === "Add Question");
											if (isAddQuestion) {

												if (question == "") {
													errMsg = errMsg
															+ '\nPlease Enter Question';
												}

												if (option1 == "") {
													errMsg = errMsg
															+ '\nPlease Enter Option1';
												}
												if (option2 == "") {
													errMsg = errMsg
															+ '\nPlease Enter Option2';
												}
												if (option3 == "") {
													errMsg = errMsg
															+ '\nPlease Enter Option3';
												}
												if (option4 == "") {
													errMsg = errMsg
															+ '\nPlease Enter Option4';
												}
											}
											if (time == "") {
												errMsg = errMsg
														+ '\nPlease Enter Time';
											}
											if (sectionName == "") {
												errMsg = errMsg
														+ '\nPlease Enter SectionName';
											}
											if (examName == "") {
												errMsg = errMsg
														+ '\nPlease Enter examName';
											}
											if (subjectName == "") {
												errMsg = errMsg
														+ '\nPlease Enter subjectName';
											}

											if (errMsg !== '') {
												alert(errMsg);
												return false;
											}
											if (!jQuery.isNumeric(time)) {
												alert("Please Enter valid Time");
												return false;
											}
											if (isAddQuestion) {
												var isChecked = $(
														"input[type=checkbox]")
														.is(':checked');
												if (isChecked) {
													return true;
												} else {
													alert("Please Select correct Answer(s).");
													return false;
												}
											}

										});

						$("#errMessage")
								.fadeOut(
										1000,
										function() {
											var msg = $("#errMessage").text();
											var submitVal = $.session
													.get('submitVal');
											var my_editor_id = 'questionsId';
											tinymce.get(my_editor_id)
													.setContent('');
											if (msg.indexOf('succesfully') > -1
													&& submitVal === "Add More Section") {
												$("#sectionName").val("");
												$("#time").val("");

											}
										});

					});
</script>
<style type="text/css">
.error {
	color: red;
}
</style>
</head>
 <body onload="addDate();">
     <jsp:include page = "/includes/header.jsp" />
     <div id="main_content">
    
	<%
		String message = (String) request.getAttribute("message");
		if (message != null) {
	%>
	<h2 id="errMessage" style="color: red" onsubmit="return ">
		<%=message%></h2>
	<%
		}
	%>
	<form:form class="springForm" action="createpaper.form" method="post"
		commandName="createQuestions">
		<table>
			<tr>
				<td>ExamName:</td>
				<td><form:input path="examName" id="examName" /></td>
			</tr>

			<tr>
				<td>SubjectName:</td>
				<td><form:select path="subjectName" id="subjectName">
						<form:option value="" label="select" />
						<form:option value="maths" label="maths" />
						<form:option value="sience" label="sience" />
						<form:option value="commerce" label="commerce" />


					</form:select></td>
			</tr>
			<tr>
				<td>SectionName:</td>
				<td><form:input path="sectionName" id="sectionName"
						value="${sectionName}" /></td>


				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Time&nbsp;(in
					minutes)</td>
				<td><form:input path="time" style="width: 40px;" id="time" /></td>
			</tr>
			<tr>
				<td>Question:</td>

				<td style="width: 290px; height: 100px;"><form:textarea
						id="questionsId" path="question" rows="3" cols="10"
						style="width: 20px;" /></td>

			</tr>
			<tr>
				<td>Options:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
					class="option1" type="checkbox" name="answer" id="answer"></td>

				<td><input type="text" name="option" placeholder="option1"
					id="option1" /></td>
			</tr>
			<tr>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
					class="option2" type="checkbox" name="answer" id="answer"></td>

				<td><input type="text" name="option" placeholder="option2"
					id="option2" /></td>
			</tr>
			<tr>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
					class="option3" type="checkbox" name="answer" id="answer"></td>

				<td><input type="text" name="option" placeholder="option3"
					id="option3" /></td>

			</tr>
			<tr>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
					class="option4" type="checkbox" name="answer" id="answer"></td>

				<td><input type="text" name="option" placeholder="option4"
					id="option4" /></td>

			</tr>
			<tr>
				<td><input class="addquestion" type="submit"
					value="Add Question" /></td>

				<td><input id="addmoresection" type="submit"
					value="Add More Section" style="margin-left: 140px;" /></td>
				<td><input id="reset" type="reset" value="reset" /></td>
				<td><a id="" href="availableexams.form"
					style="text-decoration: none; height: 120px; background-color: #C0C0C0;color: black;width: 200px; ">Back</a></td>
			</tr>
		</table>

	</form:form>
     </div>
</body>
</html>