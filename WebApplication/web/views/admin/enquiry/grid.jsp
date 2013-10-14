<%-- 
    Document   : grid.jsp
    Created on : Apr 11, 2011, 12:06:00 AM
    Author     : Adarsh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>Enquiry Grid</title>

        <script type="text/javascript" src="/resources/js/lib/jquery.corner.js"></script>
        
        
        <script type="text/javascript" src ="/resources/js/lib/jquery.validate.min.js"></script>
        <link rel="stylesheet" type="text/css" href="/resources/css/main.css" />
        <link rel="stylesheet" type="text/css" href="/resources/js/lib/jquery-ui-1.9.2.custom/css/smoothness/jquery-ui-1.9.2.custom.min.css" />
        <link rel="stylesheet" type="text/css" href="/resources/css/grid.css" />
    </head>
    <body>
        <jsp:include page = "/includes/header.jsp" />
        <div id="main_content">
            <s:url id="jQuery" value="/resources/"></s:url>
            <sj:head jqueryui="true" jquerytheme="ui-lightness"
                     scriptPath="%{jQuery}" compressed="true" />
            <s:url id="findAll" value="/enquiry/list.action" />
            <s:url id="editCourse" value="/admin/enquiry/crud.action" />
            <sjg:grid
                id="gridtable"
                caption="Enquiry List"
                dataType="json"
                href="%{findAll}"
                pager="true"
                rowList="10,20,25"
                rowNum="20"
                gridModel="results"
                editurl="%{editCourse}"
                editinline="true"
                viewrecords="true"
                hidegrid="true"
                autowidth="true"
                sortname="dated"
                sortorder="desc"
                prmNames="{page:'pagination.pageId',rows:'pagination.pageSize',sort:'pagination.sort',order:'pagination.order',search:'search',nd:'nd',oper:'operator',editoper:'UPDATE',addoper:'CREATE',deloper:'DELETE', id:'keyString'}"
                navigator="true"
                navigatorSearchOptions="{sopt:['eq','ne','lt','le','gt','ge','bw','in','ew','cn'], multipleSearch:true}"
                navigatorAddOptions="{reloadAfterSubmit:true}"
                navigatorEditOptions="{reloadAfterSubmit:false}"
                navigatorDeleteOptions="{reloadAfterSubmit:true}"
                navigatorExtraButtons="{
    		seperator: {
    			title : 'seperator'
    		},
    		hide : {
	    		title : 'Show/Hide',
	    		icon: 'ui-icon-wrench',
	    		topic: 'showcolumns'
    		},
    		alert : {
	    		title : 'Alert',
	    		onclick: function(){ alert('Grid Button clicked!') }
    		}
    	}"
                >
                <sjg:gridColumn name="keyString" key="true"  editable="true" edittype="text" hidden="true" title="keyString" sortable="false" />
                <sjg:gridColumn name="type" editable="true" edittype="select" editoptions="{value:'Enquiry:Enquiry;FeedBack:FeedBack'}" index="type" title="Type" sortable="true" />
                <sjg:gridColumn name="name" edittype="text" editable="true" index="name" title="Name" sortable="true" />
                <sjg:gridColumn name="subject" edittype="text" editable="true" index="subject" title="Subject" sortable="true" />
                <sjg:gridColumn name="commentValue" edittype="text" editable="true" index="comment" title="Comment" sortable="true" />
                <sjg:gridColumn name="emailValue" editable="true" edittype="text" index="email" title="Email" sortable="true" />
                <sjg:gridColumn name="contactNumberValue" edittype="text" editable="true" index="contactNumber" title="Contact Number" sortable="true" />
                 <sjg:gridColumn name="dated" edittype="text" index="dated" title="Dated" sortable="true" />
            </sjg:grid>

        </div>
    </body>
</html>
