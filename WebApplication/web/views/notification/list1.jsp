<%-- 
    Document   : list1
    Created on : Feb 24, 2011, 10:52:16 PM
    Author     : Adarsh
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <s:url id="jQuery" value="/resources/"></s:url>
        <sj:head jqueryui="true" jquerytheme="ui-lightness" 
        scriptPath="%{jQuery}" compressed="true" />
       <jsp:include page = "/includes/resources_top.html" />
        <title>Equations</title>
    </head>
    <body>
       <jsp:include page = "/includes/header.jsp" />
     <div id="main_content" >
        <div id="notificationtype">
            <table bgcolor="#4C787E" width="90%" >
                <tr>
                <s:form>

                    <s:select list="notificationTypes" name="notificationTypeId"
                             listKey="key.id" listValue="name"  label="Select Notification Type" >
                     </s:select> 
                    </tr>
                    <tr align="right" >
                    <s:submit  action="notificationList" ></s:submit>
                </s:form>
                </tr>
            </table>
       </div>
     </div>
     
     <!-- Add Logic for Json plugin --->
    