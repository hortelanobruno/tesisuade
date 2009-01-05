<%@ page contentType="text/html; charset=ISO-8859-1" language="java" import="java.sql.*,java.io.*, java.util.*" errorPage="" %>
<%@ page import="db.DBManager,vo.CategoriaVO,vo.EventoVO" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%
DBManager manager = DBManager.getInstance();
List<EventoVO> eventos = manager.getEventos();
out.println("<script type='text/javascript'>var keyAux ="+(eventos.size()+1)+"</script>");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Incorporated 1.0 by FreeCSSTemplates.org</title>
        <link rel="stylesheet" type="text/css" href="../styles/style.css" media="screen" />
       <!--   Estos cuatro son para la tabla -->
       <script type="text/javascript" src="../scripts/ajax/pre.js"></script>
        <script type="text/javascript" src="../scripts/jquery.pack.js"></script>
        <script type="text/javascript" src="../scripts/jquery.tableEditor.js"></script>
        <script type="text/javascript" src="../scripts/jquery.tablesorter.js"></script>
        <script type="text/javascript" src="../scripts/tableEditorValidate.js"></script>
        <link rel="stylesheet" type="text/css" href="../styles/tableEditor.css" />
    </head>
    <script type="text/javascript">
        $().ready(function() {
            $("#editableTable").tableSorter({
                sortClassAsc: 'ASC', 		// class name for ascending sorting action to header
                sortClassDesc: 'DESC',	// class name for descending sorting action to header
                headerClass: 'header', 				// class name for headers (th's)
                disableHeader: 0
            }).tableEditor({
                SAVE_HTML: '<img src="../images/yes.png">',
                EDIT_HTML: '<img src="../images/edit.png">',
                EVENT_LINK_SELECTOR: 'button.edit',
                COL_APPLYCLASS: true,
                ROW_KEY_SELECTOR: 'p.key',
                FUNC_POST_EDIT: 'postEdit',
                FUNC_UPDATE: 'update'
            });
            $('#addNew').click(function() {
                var options = {
                    CLASS: 'newRow',
                    KEY: keyAux,
                    VALUES: {
                        evento: 'Evento nuevo ...'
                    }
                };
                keyAux++;
                jQuery.tableEditor.lib.appendRow(options);
            });
        });
        var aux;
        // inject validation
        function postEdit(o) {
            var inputSelector = 'input.pvV';
            var submitSelector = '../td button.edit'
            PommoValidate.reset();
            PommoValidate.init(inputSelector, submitSelector, true, o.row);
            aux = o.row[0].childNodes[0].value;
        }
        function update(o) {
            alert(o.key);
            initRequest('url');
            var handlerFunction = getReadyStateHandler(req, processRequest);
            req.onreadystatechange = handlerFunction;
            req.open("POST", "../actualizarEvento", true);
            req.setRequestHeader("Content-Type",
                "application/x-www-form-urlencoded; charset=UTF-8");
            req.send("old="+aux+"&new="+o.row[0].innerHTML);

        }
        function processRequest(){
            
        }
    </script>
    <body>
        <%@ include file="top.jsp" %>

        <div id="content">
            <div class="post">
                <h1 class="title">Administrar eventos</h1>
                <br/><br/>
                <button id="addNew" class="b">Agregar evento</button>
                <table id="editableTable" border="0" cellspacing="0" cellpadding="0" class="dataTable">
                    <thead>
                        <tr>
                            <th name="evento" class="pvV pvEmpty">Evento</th>
                            <th name="ID"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                        for(int i=0 ; i < eventos.size() ; i++){
                            out.println("<tr>");
                            out.println("<td>"+eventos.get(i).getEvento()+"</td>");
                            out.println("<td><p class='key'>"+(i+1)+"</p><button class='edit'><img src='../images/edit.png'></td>");
                            out.println("</tr>");
                        }
                        %>
                    </tbody>
                </table> 
            </div>
        </div>

        <%@ include file="left.jsp" %>

        <%@ include file="botton.jsp" %>


    </body>
</html>
