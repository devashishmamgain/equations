<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>Note Category List</title>
        <script language="JavaScript" type="text/javascript" src="/resources/jquery-1.1.4.js"></script>
        <script type="text/javascript" src="/resources-equations/scripts/jquery.corner.js"></script>
        <script type="text/javascript" src="/resources-equations/scripts/menu_js/borders_js.js"></script>
        <script type="text/javascript" src="/resources-equations/scripts/custom_js/main_content.js"></script>
        <script type="text/javascript" src ="/resources-equations/scripts/js/jquery.validate.min.js"></script>
        <link rel="stylesheet" type="text/css" href="/resources-equations/css/grid.css" />
        <link rel="stylesheet" type="text/css" href="/resources-equations/css/main.css" />
        <link rel="stylesheet" type="text/css" href="/resources-equations/scripts/css/jquery-ui-1.8.1.custom.css" />

    </head>
    <body>
         <jsp:include page = "/includes/header.jsp" />
        <div id="main_content">
         <s:url id="jQuery" value="/resources/"></s:url>
         <sj:head jqueryui="true" jquerytheme="ui-lightness" scriptPath="%{jQuery}" compressed="true" />

         <s:url id="findAll" value="/noteCategory/list.action" />
        <s:url id="editNoteCategory" value="/admin/noteCategory/crud.action" />

        <sjg:grid
            id="gridtable"
            caption="Note Category List"
            dataType="json"
            href="%{findAll}"
            pager="true"
            rowList="2,4,6"
            rowNum="4"
            gridModel="results"
            editurl="%{editNoteCategory}"
            editinline="true"
            viewrecords="true"
            hidegrid="true"
            autowidth="true"
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
            <sjg:gridColumn name="keyString" key="true" hidden="true" editable="true" edittype="text" index="id" title="keyString" sortable="false" />
            <sjg:gridColumn name="name" editable="true" edittype="text" index="name" title="name" sortable="true" />
            <sjg:gridColumn name="label" editable="true" edittype="text" index="label" title="label" sortable="true" />
            <sjg:gridColumn name="description" edittype="text" editable="true" index="description" title="description" sortable="true" />
        </sjg:grid>
    </div>
        <jsp:include page = "/includes/resources_bottom.html" />
        <jsp:include page = "/includes/analytics.html" />
    </body>
</html>