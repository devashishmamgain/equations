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

<div id="header">
    <div id="main_logo"></div>
    <div id="main_menu_right">
        <form action="http://www.google.com/cse" id="cse-search-box">
            <div>
                <input type="hidden" name="cx" value="017139675173037099934:db9qkbjtnkq" />
                <input type="hidden" name="ie" value="UTF-8" />
                <input type="text" name="q" size="31" />
                <input type="submit" name="sa" value="Search" />
            </div>
        </form>
        <script type="text/javascript" src="http://www.google.com/cse/brand?form=cse-search-box&lang=en"></script>
    </div>
</div>
<div id="main_menu_left" style="float: right; display: inline">
    <a href='/views/home.jsp' class="menu_home">Home</a>
    <a href='/views/courses.jsp' class="menu_courses">Courses</a>
    <a href='/views/faculty.jsp' class="menu_faculty">Faculty</a>
    <a href="/views/mission.jsp" class="menu_mission">Mission</a>
    <a href="/views/teaching.jsp" class="menu_teaching">Teaching Methodology</a>
    <a href="/enquiry/enquiry.form" class="menu_enquiry">Enquiry/Feedback</a>
    
    <a href="/views/notification/latestjobs.jsp" class="menu_latestjobs">Latest Jobs</a>
    <a href="/views/notification/examresults.jsp" class="menu_examresults">Exam Results</a>
    <a href='/views/contactus.jsp' class="menu_contactus">Contact us</a>
    <a href='/views/aboutus.jsp' class="menu_aboutus">About Us</a>
    <c:if test="${loggedIn == 'true'}">        
        <a href='/admin/user/create.form' class="">Add User</a>
        <a href='/views/admin/user/grid.jsp' class="">Users List</a>
        <a href="/views/admin/enquiry/grid.jsp" class="">Enquiry List</a>
        <a href="/admin/sms/create.form" class="">SMS</a>
        <a href="/admin/notification/create.form" class="">Notification</a>
        <a href="/admin/note/create.form" class="">Create Notes </a>
        <a href="/views/notification/grid.jsp" class="">Notification List</a>
        <a href="/views/admin/notificationType/grid.jsp" class="">Notification Type List </a>
        <a href="/views/admin/course/grid.jsp" class="">Course List </a>        
        <a href="/views/admin/noteCategory/grid.jsp" class="">Note Category List </a>
        <a href="/createpaper.form" class="createpaper">Create Exam</a>
        <a href="/availableexams.form" class="availableexams">Exam Papers</a>
        <a href="/views/logout.page" style="float:right">Sign out</a>
        <span style="float:right;padding-right:10px;padding-top: 7px ">${sessionScope.userSession.eqUser.email.email}</span>
    </c:if>    
</div>