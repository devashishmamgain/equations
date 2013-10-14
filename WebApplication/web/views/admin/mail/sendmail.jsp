<%-- 
    Document   : sendMail
    Created on : May 14, 2011, 10:47:06 PM
    Author     : Adarsh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<%@ taglib prefix="sjr" uri="/struts-jquery-richtext-tags"%>
<%@page import="in.co.equations.modules.session.UserSession" %>
<%@page  import="in.co.equations.modules.user.EqUser" %>
<%@ page import="com.google.appengine.api.datastore.Email" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Send Email</title>
        <script language="JavaScript" type="text/javascript" src="/resources/jquery-1.1.4.js"></script>
        <script type="text/javascript" src="/resources/js/lib/jquery.corner.js"></script>
        
        
        <script type="text/javascript" src ="/resources/js/lib/jquery.validate.min.js"></script>
        <script type="text/javascript"   language="javascript" src="/resources/js/tinymce/tiny_mce.js"></script>
        <link rel="stylesheet" type="text/css" href="/resources/js/lib/jquery-ui-1.9.2.custom/css/smoothness/jquery-ui-1.9.2.custom.min.css" />
        <link rel="stylesheet" type="text/css" href="/resources/css/grid.css" />
        <link rel="stylesheet" type="text/css" href="/resources/css/main.css" />
    </head>
    <body>
        <jsp:include page = "/includes/header.jsp" />
        <s:url id="jQuery" value="/resources/"></s:url>
        <sj:head jqueryui="true" jquerytheme="ui-lightness"
                 scriptPath="%{jQuery}" compressed="true" />


        <s:form action="/mail/sendMailAction.action">
            <div id="main_content">
                <div class="left_content">
                    <table width="100%">
                        <thead>
                            <tr>
                                <th>Send Email </th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>TO:<s:textfield id="to" name="toEmails" /> </td>
                                <td>Subject:<s:textfield id="subject" name="subject" /></td>
                            </tr>
                            <tr>
                                <td>
                                    Message :
                                    <sjr:tinymce
                                        id="richtextTinymceAdvancedEditor"
                                        name="msgBody"
                                        rows="20"
                                        cols="80"
                                        width="800"
                                        editorTheme="advanced"
                                        editorSkin="default"
                                        toolbarAlign="left"
                                        toolbarLocation="top"
                                        plugins="safari,spellchecker,pagebreak,style,layer,table,save,advhr,advimage,advlink,iespell,inlinepopups,insertdatetime,preview,media,searchreplace,print,contextmenu,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,template"
                                        toolbarButtonsRow1="newdocument,|,bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,|,styleselect,formatselect,fontselect,fontsizeselect"
                                        toolbarButtonsRow2="cut,copy,paste,pastetext,pasteword,|,search,replace,|,bullist,numlist,|,outdent,indent,blockquote,|,undo,redo,|,link,unlink,anchor,image,cleanup,help,code,|,insertdate,inserttime,preview,|,forecolor,backcolor"
                                        toolbarButtonsRow3="tablecontrols,|,hr,removeformat,visualaid,|,sub,sup,|,charmap,iespell,media,advhr,|,print,|,ltr,rtl,|,fullscreen"
                                        toolbarButtonsRow4="insertlayer,moveforward,movebackward,absolute,|,styleprops,spellchecker,|,cite,abbr,acronym,del,ins,attribs,|,visualchars,nonbreaking,template,blockquote,pagebreak,|,insertfile,insertimage"
                                      />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <s:submit name="Send" value="Send" align="center"/>
                                </td>
                            </tr>
                            <%
                                UserSession userSession = (UserSession) session.getAttribute("userSession");
                                EqUser eqUser = userSession.getEqUser();
                                Email email = eqUser.getEmail();
                             %>
                             <input type=hidden id ="fromEmail" name="fromEmail" value="<%=email.getEmail()%>" />
                       </tbody>
                    </table>
                </s:form>
            </div>
        </div>
    </body>
</html>
