<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <jsp:include page = "/includes/resources_top.html" />
        <title>Equations</title>

        <!--CSS file (default YUI Sam Skin) -->
        <link type="text/css" rel="stylesheet" href="http://yui.yahooapis.com/2.8.2r1/build/datatable/assets/skins/sam/datatable.css">

        <!-- Dependencies -->
        <script src="http://yui.yahooapis.com/2.8.2r1/build/yahoo-dom-event/yahoo-dom-event.js"></script>
        <script src="http://yui.yahooapis.com/2.8.2r1/build/element/element-min.js"></script>
        <script src="http://yui.yahooapis.com/2.8.2r1/build/datasource/datasource-min.js"></script>

        <!-- OPTIONAL: JSON Utility (for DataSource) -->
        <script src="http://yui.yahooapis.com/2.8.2r1/build/json/json-min.js"></script>

        <!-- OPTIONAL: Connection Manager (enables XHR for DataSource) -->
        <script src="http://yui.yahooapis.com/2.8.2r1/build/connection/connection-min.js"></script>

        <!-- OPTIONAL: Get Utility (enables dynamic script nodes for DataSource) -->
        <script src="http://yui.yahooapis.com/2.8.2r1/build/get/get-min.js"></script>

        <!-- OPTIONAL: Drag Drop (enables resizeable or reorderable columns) -->
        <script src="http://yui.yahooapis.com/2.8.2r1/build/dragdrop/dragdrop-min.js"></script>

        <!-- OPTIONAL: Calendar (enables calendar editors) -->
        <script src="http://yui.yahooapis.com/2.8.2r1/build/calendar/calendar-min.js"></script>

        <!-- Source files -->
        <script src="http://yui.yahooapis.com/2.8.2r1/build/datatable/datatable-min.js"></script>
        <script type="text/javascript">
            YAHOO.example.Data = {
                eqUsers: [
            <c:forEach var="r" items="${eqUserList}">
                        {
                            "keyString":"${r.keyString}",
                            "Edit":"<a href='/admin/user/edit.form?keyString=${r.keyString}'>Edit</a>",
                            "Id No":"${r.idNo}",
                            "Email":"${r.email.email}",
                            "Role":"${r.roleId}",
                            "Name":"${r.name}",
                         //   "Correspondence Address":"${r.correspondenceAddress.value}",
                            "Permanent Address": "${r.permanentAddress.value}",
                            "Primary Contact Number": "${r.primaryContactNumber.number}",
                            //  AlternateContactNumber: "${r.alternateContactNumber.number}",
                            "SMS Notification": "${r.smsNotification}"
                        },
                                
            </c:forEach>
                    ]
                }

                YAHOO.util.Event.addListener(window, "load", function() {
                    YAHOO.example.Basic = function() {
                        var myColumnDefs = [
                            {key:"keyString", label:"keyString", hidden:true, sortable:true, resizeable:true, parser:"string"},
                            {key:"Edit", label:"Edit", resizeable: true},
                            {key:"Id No", label:"Id No.", sortable:true, resizeable:true, parser:"number"},
                            {key:"Email", label:"Email", editor: new YAHOO.widget.TextboxCellEditor({disableBtns:false, isActive:true,LABEL_SAVE:'SAVE', LABEL_CANCEL:'CANCEL'}), sortable:true},
                            {key:"Role", label:"Role", editor: new YAHOO.widget.DropdownCellEditor({multiple:false,dropdownOptions:["Admin","Faculty","Student"]}), sortable:true, resizeable:true},
                            {key:"Name", label:"Name", editor: new YAHOO.widget.TextboxCellEditor({disableBtns:true}), sortable:true, resizeable:true},
                          //  {key:"Correspondence Address", editor: new YAHOO.widget.TextboxCellEditor({disableBtns:true}), sortable:true, resizeable:true},
                            {key:"Permanent Address", editor: new YAHOO.widget.TextboxCellEditor({disableBtns:true}), sortable:true, resizeable:true},
                            {key:"Primary Contact Number", editor: new YAHOO.widget.TextboxCellEditor({disableBtns:true}), sortable:true, resizeable:true, parser:"number"},
                            //   {key:"AlternateContactNumber", sortable:true, resizeable:true},                         
                            {key:"SMS Notification", editor: new YAHOO.widget.RadioCellEditor({radioOptions:["true","false"],disableBtns:true}), sortable:true, resizeable:true},
                        ];

                        var myDataSource = new YAHOO.util.DataSource(YAHOO.example.Data.eqUsers);
                        myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
                        myDataSource.responseSchema = {
                            fields: ["keyString", "Edit", "Id No","Email","Role","Name",
                                //"Correspondence Address",
                                "Permanent Address",
                                "Primary Contact Number",
                                //"AlternateContactNumber",
                                "SMS Notification"]
                        };
                        
                        var myDataTable = new YAHOO.widget.DataTable("basic",
                        myColumnDefs, myDataSource, {caption:"Users List"});
                       
                        // Set up editing flow
                        var highlightEditableCell = function(oArgs) {
                            var elCell = oArgs.target;
                            if(YAHOO.util.Dom.hasClass(elCell, "yui-dt-editable")) {
                                this.highlightCell(elCell);
                            }
                        };

                        var saveRow = function(oArgs){
                            $("#msg").html("Saving.....");
                            console.log(oArgs);
                            var newData = oArgs.newData;
                            var column = oArgs.editor._oColumn.key;
                            var keyString = oArgs.editor._oRecord._oData.keyString;
                            $.ajax({
                                type: "POST",
                                url: "/admin/user/update.ajax",
                                data: "keyString=" + keyString +"&column=" + column + "&value=" + newData ,
                                success: function(msg){
                                    if (msg == "success") {
                                        $("#msg").html("Saved");
                                    } else {
                                        $("#msg").html("Some Error occurred. Please try again later");
                                    }                                   
                                }
                            });
                        }
                     
                        myDataTable.subscribe("cellMouseoverEvent", highlightEditableCell);
                        myDataTable.subscribe("editorSaveEvent", saveRow);
                        myDataTable.subscribe("cellClickEvent", myDataTable.onEventShowCellEditor);

                        return {
                            oDS: myDataSource,
                            oDT: myDataTable
                        };
                    }();

                });
        </script>
    </head>

    <body class="yui-skin-sam">
        <jsp:include page = "/includes/header.jsp" />        
        <div id="main_content">
            <!--
            <jsp:include page="/includes/prev_next_template.jsp">            
                <jsp:param name = "max" value = "${max}" />
                <jsp:param name = "count" value = "${count}" />
                <jsp:param name="pageName" value = "/admin/user/list.page?" />
            </jsp:include>
            -->
            <div id="msg"></div>
            <div id="basic"></div>
            <!--
            <jsp:include page = "/includes/footer.jsp" />
            -->
        </div>
        <jsp:include page = "/includes/resources_bottom.html" />
        <jsp:include page = "/includes/analytics.html" />
    </body>
</html>