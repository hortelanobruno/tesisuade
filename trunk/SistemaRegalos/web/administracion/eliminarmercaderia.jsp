<%@ page contentType="text/html; charset=ISO-8859-1" language="java" import="java.sql.*,java.io.*, java.util.*" errorPage="" %>
<%@ page import="db.DBManager,vo.CategoriaVO,vo.MercaderiaVO" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%
        DBManager manager = DBManager.getInstance();
        List<MercaderiaVO> categorias = manager.getMercaderias();
        String mercaderia = request.getParameter("mercaderia");
        if (mercaderia != null) {
            String resultado = manager.removeMercaderia(mercaderia);
            if (resultado != null) {
                if (resultado == "ok") {
                    response.sendRedirect("eliminarmercaderiaok.jsp");
                } else {
                    response.sendRedirect("error.jsp");
                }
            } else {
                response.sendRedirect("error.jsp");
            }
        }
%>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta http-equiv="content-style-type" content="text/css">
        <meta http-equiv="content-language" content="en-gb">
        <meta http-equiv="imagetoolbar" content="no" />
        <title>Incorporated 1.0 by FreeCSSTemplates.org</title>
        <link rel="stylesheet" type="text/css" href="../styles/style.css" media="screen" />
        <link rel="stylesheet" href="../styles/lavalamp_test.css" type="text/css" media="screen">
        <script type="text/javascript" src="../scripts/jquery-1.2.3.min.js"></script>
        <script type="text/javascript" src="../scripts/jquery.easing.min.js"></script>
        <script type="text/javascript" src="../scripts/jquery.lavalamp.min.js"></script>
        <link rel="stylesheet" href="../styles/dataTable.css" media="screen">
        <style type="text/css">
            table.dataTable tr.marked {
                background-color:lime;
            }
        </style>
        <script type="text/javascript" src="../scripts/jquery-1.2.3.pack.js"></script>
        <script type="text/javascript" src="../scripts/tableRowCheckboxToggle.js"></script>
    </head>
    <body>
        <%@ include file="top.jsp" %>

        <div id="content">
            <div class="post">
                <h1 class="title">Eliminar mercaderia</h1>
                <table border="0" cellspacing="0" cellpadding="0" class="dataTable">
                    <thead>
                        <tr>
                            <th class="dataTableHeader"></th>
                            <th class="dataTableHeader">Error Summary</th>
                            <th class="dataTableHeader">Error Date</th>
                            <th class="dataTableHeader" style="white-space: nowrap">Failed XML Message</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr class="odd_row">
                            <td><input type="checkbox" id="checkme1" /></td>
                            <td>java.lang.NullPointerException<input type="checkbox" class="testClass" /></td>
                            <td style="white-space: nowrap">02/15/2008 15:23</td>
                            <td><a href="#">View &amp; Edit Message</a></td>
                        </tr>
                        <tr class="even_row">
                            <td><input type="checkbox" id="checkme2" /></td>
                            <td>Error Retrieving Agent Information</td>

                            <td style="white-space: nowrap">02/14/2008 07:01<input type="checkbox" checked="true" id="checkme101" /></td>
                            <td><a href="#">View &amp; Edit Message</a></td>
                        </tr>
                        <tr class="odd_row">
                            <td><input type="checkbox" id="checkme3" /></td>
                            <td>Error occurred when retrieving agent information for agent number blah blah</td>
                            <td style="white-space: nowrap">02/14/2008 07:01</td>
                            <td><a href="#">View &amp; Edit Message</a><input type="checkbox" checked="true" id="checkme100" disabled /></td>

                        </tr>
                        <tr class="even_row">
                            <td><input type="checkbox" id="checkme4" /></td>
                            <td>Error Retrieving Agent Information</td>
                            <td style="white-space: nowrap">02/13/2008 23:11</td>
                            <td><input type="checkbox" name="testName" /><a href="#">View &amp; Edit Message</a></td>
                        </tr>
                        <tr class="odd_row">
                            <td><input type="checkbox" checked="true" id="checkme5" /></td>
                            <td>Error Retrieving Agent Information</td>
                            <td style="white-space: nowrap">02/13/2008 23:11</td>
                            <td><input type="checkbox" name="somethingElse" /><a href="#">View &amp; Edit Message</a></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>

        <%@ include file="left.jsp" %>

        <%@ include file="botton.jsp" %>

    </body>
</html>
