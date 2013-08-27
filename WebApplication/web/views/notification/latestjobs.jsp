<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>Job Openings</title>
        <link rel="stylesheet" type="text/css" href="/resources-equations/css/main.css" />
        <link rel="stylesheet" type="text/css" href="/resources-equations/scripts/css/jquery-ui-1.8.1.custom.css" />
        <link rel="stylesheet" type="text/css" href="/resources-equations/css/grid.css" />

        <script type="text/javascript" src="/resources-equations/scripts/js/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="/resources-equations/scripts/js/jquery-ui-1.8.1.custom.min.js"></script>
        <script type="text/javascript" src="/resources-equations/scripts/jquery.corner.js"></script>
        <script type="text/javascript" src="/resources-equations/scripts/menu_js/borders_js.js"></script>
        <script type="text/javascript" src="/resources-equations/scripts/custom_js/main_content.js"></script>
        <script type="text/javascript" src ="/resources-equations/scripts/js/jquery.validate.min.js"></script>
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

            <s:url id="findAll" value="/notification/jobs/list.action" />
            <c:choose>
                <c:when test="${loggedIn == 'true'}">
                    <s:url id="editNotification" value="/admin/notification/crud.action?typeId=1" />
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
                caption="Job Openings"
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
                <sjg:gridColumn name="subject" hidden="true" editable="true" edittype="text" index="subject" title="subject" sortable="true" />
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
                        <sjg:gridColumn name="submissionDate"  editable="true" formatter="false"
                                        formatoptions="{newformat : 'm/d/Y', srcformat : 'Y-m-d H:i:s'}" editrules="{required:false, date:true}"
                                        editoptions="{size: 10, maxlength: 10, dataInit: function(element) {$(element).parent('td').append(element);$(element).datepicker({displayFormat: 'mm/dd/yy', dateFormat: 'mm/dd/yy'}); } }"
                                        index="submissionDate" title="Submission Date" sortable="true" />
                    </c:when>
                    <c:otherwise>
                        <sjg:gridColumn name="examDate" editable="true" formatter="date"
                                        formatoptions="{newformat : 'd M Y', srcformat : 'Y-m-d H:i:s'}"
                                        index="examDate" title="Exam Date" sortable="false" />
                        <sjg:gridColumn name="submissionDate"  editable="true" formatter="date"
                                        formatoptions="{newformat : 'd M Y', srcformat : 'Y-m-d H:i:s'}" editrules="{required:false, date:true}"
                                        index="submissionDate" title="Submission Date" sortable="true" />
                    </c:otherwise>
                </c:choose>


                <sjg:gridColumn name="applicationFee" editable="true" edittype="text" index="applicationFee" title="Fee" sortable="true" />

                <c:choose>
                    <c:when test="${navigatorValue == 'true'}">
                        <sjg:gridColumn name="links" editable="true" edittype="text" index="links" title="Links" sortable="true" />
                        <sjg:gridColumn name="challanLink" editable="true" edittype="text" index="challanLink" title="Challan Links" sortable="true" />
                    </c:when>
                    <c:otherwise>
                        <sjg:gridColumn name="links" formatter="formatLink" editable="true" edittype="text" index="links" title="Links" sortable="true" />
                        <sjg:gridColumn name="challanLink" formatter="formatLink" editable="true" edittype="text" index="challanLink" title="Challan Links" sortable="true" />
                    </c:otherwise>
                </c:choose>
            </sjg:grid>
        </div>

    </body>
</html>