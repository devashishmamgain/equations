<%-- 
    Document   : grid
    Created on : Mar 31, 2011, 12:55:32 AM
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
        <title>User List</title>
        <script language="JavaScript" type="text/javascript" src="/resources/jquery-1.1.4.js"></script>
        <script type="text/javascript" src="/resources-equations/scripts/jquery.corner.js"></script>
        <script type="text/javascript" src="/resources-equations/scripts/menu_js/borders_js.js"></script>
        <script type="text/javascript" src="/resources-equations/scripts/custom_js/main_content.js"></script>
        <script type="text/javascript" src ="/resources-equations/scripts/js/jquery.validate.min.js"></script>
        <link rel="stylesheet" type="text/css" href="/resources-equations/scripts/css/jquery-ui-1.8.1.custom.css" />
        <link rel="stylesheet" type="text/css" href="/resources-equations/css/grid.css" />
        <link rel="stylesheet" type="text/css" href="/resources-equations/css/main.css" />

    </head>
    <body>
        <script type="text/javascript">
            function popup(url, name, width, height)
            {
                settings=
                    "toolbar=no,location=no,directories=no,"+
                    "status=no,menubar=no,scrollbars=yes,"+
                    "resizable=yes,width="+width+",height="+height;
                    //url=this.ResolveClientUrl("~/"+url);
                MyNewWindow=window.open(url,name,settings);

            }
        </script>
        <jsp:include page = "/includes/header.jsp" />
        <div id="main_content">
            <s:url id="jQuery" value="/resources/"></s:url>
            <sj:head jqueryui="true" jquerytheme="ui-lightness"
                     scriptPath="%{jQuery}" compressed="true" />
            <script type="text/javascript">
                var selectedEmailsid="";
                var TesteditOptions="";
                $(document).ready(function(){
                    $.ajax({
                        url:"/course/list.action",
                        async: false,
                        dataType: 'json',
                        success:function(data){
                            var options= "";
                            $.each(data.results, function(i,results){
                                options = options + results.keyString+":"+ results.name;
                                if(i < (data.count-1) ){
                                    options =options +";";
                                }
                            });
                            TesteditOptions=options;
                        }
                    });
                });
                function setEditoptions(){
                    return TesteditOptions;

                }
            </script>
            <s:url id="findAll" value="/user/list.action" />
            <s:url id="editUser" value="/admin/user/crud.action" />
            <sjg:grid
                id="gridtable"
                caption="User List"
                dataType="json"
                href="%{findAll}"
                editurl="%{editUser}"
                pager="true"
                rowList="10,20,25,50"
                rowNum="25"
                gridModel="results"
                editinline="true"
                viewrecords="true"
                hidegrid="true"
                autowidth="true"
                onSelectRowTopics="rowselect"
                prmNames="{page:'pagination.pageId',rows:'pagination.pageSize',sort:'pagination.sort',order:'pagination.order',search:'search',nd:'nd',oper:'operator',editoper:'UPDATE',addoper:'CREATE',deloper:'DELETE', id:'keyString'}"
                navigator="true"
                multiselect="true"
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
                <sjg:gridColumn name="idNo" editable="true" edittype="text" index="idNo" title="Id No" sortable="true" />
                <sjg:gridColumn name="name" editable="true" edittype="text" index="name" title="name" sortable="true" />
                <sjg:gridColumn name="emailValue" edittype="text" editable="true" index="email" title="email" sortable="true" />
                <sjg:gridColumn  name="roleId" edittype="select" editable="true" editoptions="{value:'1:Admin;2:Faculty;3:Student'}" index="roleId" title="role" sortable="true" />
                <sjg:gridColumn name="primaryContactNumberValue" editable="true" edittype="text" index="primaryContactNumber" title="primaryContactNumber" sortable="true" />
                <sjg:gridColumn name="permanentAddressValue" edittype="text" editable="true" index="permanentAddress" title="permanentAddress" sortable="true" />
                <sjg:gridColumn name="coursesNames" edittype="select" editoptions= "{value:setEditoptions(),multiple:'multiple',size:4}" editable="true"  title="courses" sortable="false" />
                <sjg:gridColumn name="smsNotification" edittype="select" editable="true" editoptions="{value:'1:true;0:false'}"  index="smsNotification" title="SMS Notification" sortable="true" />

            </sjg:grid>

        </div>
    </body>
</html>