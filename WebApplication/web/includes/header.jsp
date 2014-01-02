<%-- 
    Document   : bootstrap_header
    Created on : Aug 22, 2013, 2:16:18 PM
    Author     : AASHISH
--%>


<link rel="stylesheet" type="text/css" href="/resources/css/content/bottom.css" />
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

<div class="navbar navbar-inverse">
    <div class="navbar-inner" >
        
        <!--
        <a class="brand" href="#">Equations</a>
        -->
        <!-- Responsive Navbar Part 2: Place all navbar contents you want collapsed withing .navbar-collapse.collapse. -->
        <div class="nav-collapse collapse">   
            <!-- Read about Bootstrap dropdowns at http://twbs.github.com/bootstrap/javascript.html#dropdowns -->
           
            <ul class="nav">
                <li><img id ="image" src="/resources/images/logos/logo1.png" height="100" width="300"/></li>
                <li class="active"><a href='/views/home.jsp'>Home</a></li>
                <li><a href='/views/courses.jsp'>Courses</a></li>
                <li><a href='/views/faculty.jsp'>Faculty</a></li>
                <li><a href='/views/mission.jsp'>Mission</a></li>
                <li><a href="">Teaching Methodology</a></li>
                <li><a href="#">Enquiry/Feedback</a></li>
                <li><a href="#">Latest Jobs</a></li>
                <li><a href="#">Exam Result</a></li>
                <li><a href="#about">About</a></li>
                <li><a href="#contact">Contact</a></li>
                <li><input type ="submit" id="logn" value="Login" onClick ="window.location = '_ah/login?continue0=%2FloginSubmit.page'" style="margin-right: 10px;"/></li>
                
                
                
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
                </ul>
                    
            </c:if>
             
        </div><!--/.nav-collapse -->
    </div><!-- /.navbar-inner -->
</div><!-- /.navbar -->           
