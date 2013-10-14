<%-- 
    Document   : mailpopup
    Created on : Jun 6, 2011, 7:11:46 PM
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
        <script type="text/javascript" src="/resources-equations/scripts/jquery.corner.js"></script>
        <script type="text/javascript" src="/resources-equations/scripts/menu_js/borders_js.js"></script>
        <script type="text/javascript" src ="/resources-equations/scripts/js/jquery.validate.min.js"></script>
        <script type="text/javascript" language="javascript" src="/resources/js/tinymce/tiny_mce.js"></script>
        <link rel="stylesheet" type="text/css" href="/resources-equations/scripts/css/jquery-ui-1.8.1.custom.css" />
        <link rel="stylesheet" type="text/css" href="/resources/css/grid.css" />
       
    </head>
    <body bgcolor="#e8e8e8">
       <s:url id="jQuery" value="/resources/"></s:url>
        <sj:head jqueryui="true" 
                 scriptPath="%{jQuery}" compressed="true" />
        <s:form action="/mail/sendMailAction.action">
             
                <div class="left_content">
                    <table width="80%" >
                        <thead>
                            <tr>
                                <th>Send Email </th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>TO:<input type="text"  id="to" name="toEmails" value="<%=request.getParameter("toEmails")%>" /> </td>
                             </tr>
                             <tr>
                                 <td>Subject:<input type="text"  id="subject" name="subject" /></td>
                             </tr>
                            <tr>
                                <td>
                                    Message :
                                    </td>
                            </tr>
                            <tr align="left">
                                <td align="left">
                                    <sjr:tinymce
                                        id="richtextTinymceAdvancedEditor"
                                        name="msgBody"
                                        cssClass="{margin-top: 6em}"
                                        rows="15"
                                        cols="60"
                                        width="200"
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
                                    <s:submit name="Send" value="Send"  align="center"/>
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

    </body>
</html>
