<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>Exam Results</title>
        <link rel="stylesheet" type="text/css" href="/resources/css/main.css" />
        <link rel="stylesheet" type="text/css" href="/resources/js/lib/jquery-ui-1.9.2.custom/css/smoothness/jquery-ui-1.9.2.custom.min.css" />
        <link rel="stylesheet" type="text/css" href="/resources/css/grid.css" />

        <script type="text/javascript" src="/resources/js/lib/jquery-1.10.2.min.js"></script>
        <script type="text/javascript" src="/resources/js/lib/jquery-ui-1.9.2.custom/js/jquery-ui-1.9.2.custom.min.js"></script>
        <script type="text/javascript" src="/resources/js/lib/jquery.corner.js"></script>
        
        
        <script type="text/javascript" src ="/resources/js/lib/jquery.validate.min.js"></script>
        <script type="text/javascript" src ="/resources/js/base/jquery.ui.widget.min.js"></script>

        <script type="text/javascript">
            function formatLink(cellvalue, options, rowObject) {
                if (cellvalue == null) {
                    return "";
                }
                return "<a href='"+cellvalue +"'>"+cellvalue+"</a>";
            }
        </script>

    </head>
    <s:url id="jQuery" value="/resources/"></s:url>

    <body>
        <jsp:include page = "/includes/header.jsp" />
        <div id="main_content">           

            <sj:head jqueryui="true" jquerytheme="ui-lightness"
                     scriptPath="%{jQuery}" compressed="true" />
            <script type="text/javascript" src ="/resources/js/base/jquery.ui.widget.min.js"></script>
            <script type="text/javascript" src ="/resources/js/base/jquery.ui.datepicker.min.js"></script>
            
            <div id="notificationList"></div>

            <s:url id="findAll" value="/notification/results/list.action" />
            <c:choose>
                <c:when test="${loggedIn == 'true'}">
                    <s:url id="editNotification" value="/admin/notification/crud.action?typeId=2" />
                    <s:set name="navigatorValue" value="true" />
                </c:when>
                <c:otherwise>
                    <s:url id="editNotification" value="" />
                    <s:set name="navigatorValue" value="false" />
                </c:otherwise>
            </c:choose>

            <sjg:grid
                id="notificationGrid"
                name="notificationGrid"
                caption="Exam Results"
                dataType="json"
                href="%{findAll}"
                pager="true"
                rowList="10,20,30"
                rowNum="10"
                gridModel="results"
                editurl="%{editNotification}"
                editinline="true"
                viewrecords="true"
                sortname="submissionDate"
                sortorder="desc"
                hidegrid="true"
                autowidth="true"
                prmNames="{page:'pagination.pageId',rows:'pagination.pageSize',sort:'pagination.sort',order:'pagination.order',search:'search',nd:'nd',oper:'operator',editoper:'UPDATE',addoper:'CREATE',deloper:'DELETE', id:'keyString'}"
                navigator="%{navigatorValue}"
                navigatorSearchOptions="{sopt:['eq','ne','lt','le','gt','ge','bw','in','ew','cn'], multipleSearch:true}"
                navigatorAddOptions="{reloadAfterSubmit:true}"
                navigatorEditOptions="{reloadAfterSubmit:false}"
                navigatorDeleteOptions="{reloadAfterSubmit:true}"
                navigatorRefresh="true"
                >
                <sjg:gridColumn name="keyString" key="true" hidden="true" editable="true" edittype="text" index="id" title="keyString" sortable="false" />
                <sjg:gridColumn name="bankName" editable="true" edittype="text" index="bankName" title="Bank Name" sortable="true" />
                <sjg:gridColumn name="contentValue" hidden="true" editable="true" edittype="text" index="contentValue" title="Details" sortable="true" />
                <sjg:gridColumn name="vacancies" editable="true" edittype="text" index="vacancies" title="Vacancies" sortable="true" />
                <sjg:gridColumn name="exam" editable="true" edittype="text" index="exam" title="Exam" sortable="true" />

                <c:choose>
                    <c:when test="${navigatorValue == 'true'}">
                        <sjg:gridColumn name="examDate" editable="true" formatter="date"
                                        formatoptions="{newformat : 'm/d/Y', srcformat : 'Y-m-d H:i:s'}" editrules="{required:false, date:true}"
                                        editoptions="{size: 10, maxlength: 10, dataInit: function(element) {$(element).parent('td').append(element);$(element).datepicker({displayFormat: 'mm/dd/yy', dateFormat: 'mm/dd/yy'}); } }"
                                        index="examDate" title="Exam Date" sortable="true" />
                    </c:when>
                    <c:otherwise>
                        <sjg:gridColumn name="examDate" editable="true" formatter="date"
                                        formatoptions="{newformat : 'd M Y', srcformat : 'Y-m-d H:i:s'}"
                                        index="examDate" title="Exam Date" sortable="false" />
                    </c:otherwise>
                </c:choose>

                <c:choose>
                    <c:when test="${navigatorValue == 'true'}">
                        <sjg:gridColumn name="links" editable="true" edittype="text" index="links" title="Result Link" sortable="true" />
                    </c:when>
                    <c:otherwise>
                        <sjg:gridColumn name="links" formatter="formatLink" editable="true" edittype="text" index="links" title="Result Link" sortable="true" />
                    </c:otherwise>
                </c:choose>
            </sjg:grid>
        </div>

    </body>
</html>