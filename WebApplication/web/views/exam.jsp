<%@page
	import="in.co.equations.modules.questionpaper.dataaccess.model.OptionBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@  taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page
	import="in.co.equations.modules.questionpaper.dataaccess.model.QuestionBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.*"%>
<%@page import="java.lang.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<jsp:include page = "/includes/resources_top.html" />
<script type="text/javascript" src="../jquery-1.10.1.js"></script>
<script type="text/javascript" src="../date.js"></script>
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
		var sequenceNo = $("#sequenceNo").val();
		if (sequenceNo > 1) {
			$("#previous").show();
		}		
	});

	$(function() {
		var sequenceNo = $("#sequenceNo").val();
		var questionTime = $("#questionTime").val();
		if (sequenceNo != 1) {
			--questionTime;
		}
		var time = (new Date).clearTime().addSeconds(questionTime).toString(
				'H:mm:ss');
		$("#countDownTime").html("Time " + time);

		countdown = setInterval(function() {
			$("#questionTime").val(questionTime);
			time = (new Date).clearTime().addSeconds(questionTime - 1)
					.toString('H:mm:ss');
			//$("span#questionTime").empty();
			$("#countDownTime").html("Time " + time);
			if (questionTime == 0) {
				$("#countDownTime").hide();
				ans = confirm("The Time is Over ,Click Ok to continue");
				if (ans == true) {
					$(".showInput").trigger('click');
				}
			}
			questionTime--;

		}, 1000);

	});
	

	
</script>
</head>
<body onload="noBack();" onpageshow="if (event.persisted) noBack();"
	onunload="" oncontextmenu="return false">
         <jsp:include page = "/includes/header.jsp" />
	<form:form action="nextQuestion.form" method="post" commandName="exam">

		<table>
			<tr>
				<td align="left">${sequenceNo}.${description}</td>

				<td><form:hidden id="questionTime" path="time" value="${time}" />
					<span id="countDownTime" style="color: red;"> </span></td>
			<tr>


				<td>
					<%
						if (Boolean.TRUE.equals(request.getAttribute("isMultiChoice"))) {
					%> <form:checkboxes items="${options}" path="answer"  id="${options}"/> <%
 	} else {
 %> <form:radiobuttons items="${options}" path="answer" id="${options}"/> <%
 	}
 %> <form:hidden id="sequenceNo" path="sequenceNo" value="${sequenceNo}" />

					<form:hidden path="lastQuestion" value="${isLastQuestion}" />
				</td>
			</tr>
			<tr>
				<td>
						<input type="submit" name="previous" class="button" value="previous" id="previous" style="display: none;"/>
						<input id="submit" class="showInput"type="submit" value="Next" name="next">
						<input id="submitall" class="button" type="submit" value="EndExam" name="submitAll"></td>
			</tr>

		</table>
	</form:form>
	 
</body>
</html>