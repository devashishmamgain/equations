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
        <!-- Responsive Navbar Part 2: Place all navbar contents you want collapsed within .navbar-collapse.collapse. -->
        <div class="nav-collapse collapse">   
            <!-- Read about Bootstrap dropdowns at http://twbs.github.com/bootstrap/javascript.html#dropdowns -->
      <img id ="image" class="pull-left" src="/resources/images/logos/logo1.png" height="24px" width="140px"/>
  
            <ul class="nav">

                <li class="active"><a href='/views/home.jsp'>Home</a></li>
                <li class="courses"><a href='/views/courses.jsp'>Courses</a></li>
                <li class="faculty"><a href='/views/faculty.jsp'>Faculty</a></li>
                <li class="mission"><a href='/views/mission.jsp'>Mission</a></li>
                <li class="teaching"><a href="">Teaching Methodology</a></li>
                <li class="enq"><a href="#">Enquiry/Feedback</a></li>
                <li class="latst"><a href="#">Latest Jobs</a></li>
                <li class="exam"><a href="#">Exam Result</a></li>
                <li class="about"><a href="#about">About</a></li>
                <li class="contact"><a href="#contact">Contact</a></li>




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

            <ul class="pull-right"> 
                <li><input class="btn btn-primary" type="submit" id="logn" value="Login" onclick="window.location = '_ah/login?continue0=%2FloginSubmit.page';" style="margin-right: 10px;"></li>
            </ul>

        </div><!--/.nav-collapse -->
    </div><!-- /.navbar-inner -->
</div><!-- /.navbar -->           
