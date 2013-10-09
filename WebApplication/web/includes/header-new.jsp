<%-- 
    Document   : header-new
    Created on : Sep 24, 2013, 10:53:22 AM
    Author     : AASHISH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="in.co.equations.modules.session.*" %>

<%
boolean loggedIn = false;
HttpSession httpSession = request.getSession();
UserSession userSession = (UserSession) httpSession.getAttribute("userSession");

if (userSession != null && userSession.getEqUser() != null) {
    loggedIn = true;
}

session.setAttribute("loggedIn", loggedIn);
%>
<link rel="stylesheet" href="/resources/bootstrap/css/bootstrap.min.css">
<script  type="text/javascript"  src="/resources/bootstrap/js/jquery-1.js"></script>
<script  type="text/javascript"  src="/resources/bootstrap/js/bootstrap.min.js"></script>

<div style="position: relative;width: 100%;margin:auto;height: 125px;background-repeat: repeat-x;background-color: #ffffff;
    background-image: url(/resources/images/menu/header.png);background-position: right;">
    <div style=" height: 120px;background-image: url(/resources/images/logos/logo1.png);width: 305px; background-repeat: no-repeat;
    float: left;    margin-top: 3px;    margin-left: 10px;"></div>
    <script type="text/javascript" src="http://www.google.com/cse/brand?form=cse-search-box&lang=en"></script>
</div>
<div class="navbar navbar-inverse">
          <div class="navbar-inner" >
            <!-- Responsive Navbar Part 1: Button for triggering responsive navbar (not covered in tutorial). Include responsive CSS to utilize. -->
            <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="brand" href="#">Equations</a>
            <!-- Responsive Navbar Part 2: Place all navbar contents you want collapsed withing .navbar-collapse.collapse. -->
            <div class="nav-collapse collapse">
                <!-- Read about Bootstrap dropdowns at http://twbs.github.com/bootstrap/javascript.html#dropdowns -->
                <ul class="nav">
                <li class="active"><a href='/views/newhome.jsp'>Home</a></li>
                <li><a href='/views/newcourses.jsp'>Courses</a></li>
		<li><a href='/views/newfaculty.jsp'>Faculty</a></li>
		<li><a href='/views/newmission.jsp'>Mission</a></li>
                <li><a href="">Teaching Methodology</a></li>
		<li><a href="#">Feedback</a></li>
		<li><a href="#">Latest Jobs</a></li>
		<li><a href="#">Exam Result</a></li>
                <li><a href="#about">About</a></li>
                <li><a href="#contact">Contact</a></li>
               <c:if test="${loggedIn == 'true'}">  
                <li class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
                  <ul class="dropdown-menu">
                    <li><a href="#">Add User</a></li>
                    <li><a href="#">User List</a></li>
                    <li><a href="#">Enquiry List</a></li>
                    <li><a href="#">SMS</a></li>
                    <li><a href="#">Notification</a></li>
                    <li><a href="#">Create Notes</a></li>
                    <li><a href="#">Notification List</a></li>
                    <li><a href="#">Notification Type List</a></li>
                    <li><a href="#">Course List</a></li>
                    <li><a href="#">Note Category List</a></li>
                    <li class="divider"></li>
                    <li><a href="#">User</a></li>
                    <li><a href="#">Sign Out</a></li>
                  </ul>
                </li>
               </c:if>
                </ul>
              
            </div><!--/.nav-collapse -->
          </div><!-- /.navbar-inner -->
        </div><!-- /.navbar -->
        
    
