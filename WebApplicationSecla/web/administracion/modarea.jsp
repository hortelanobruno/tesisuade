<%@ page contentType="text/html; charset=ISO-8859-1" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="db.DBManager,varios.Usuario" %>
<%
        Object connectado = session.getAttribute("conectado");
        if (connectado != null) {
            if (connectado.toString().equals("no")) {
                response.sendRedirect("../index.jsp");
            }
        } else {
            response.sendRedirect("../index.jsp");
        }
        String responsable = request.getParameter("responsable");
        String sector = request.getParameter("sector");
        String sede = request.getParameter("sede");
        String digarea = request.getParameter("digarea");
        String digresp = request.getParameter("digresp");
        String usuario = request.getParameter("listaUsuarios");
        String resultado = new String();
        DBManager manager = DBManager.getInstance();
        if (!manager.isConnected()) {
            response.sendRedirect("../index.jsp");
        }
        if (responsable != null) {
            Usuario usu = new Usuario(usuario, responsable, sector, sede, digarea, digresp);
            resultado = manager.actualizarArea(usu);
            if (resultado != null) {
                if (resultado == "ok") {
                    response.sendRedirect("modareaok.jsp");
                }
            }
        }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link rel="stylesheet" href="../estilo/estilo.css" type="text/css" />
        <title>Sistema de control de recibos</title>
        <script language="javascript" type="text/javascript" src="../js/script.js"></script>
        <script language="javascript" type="text/javascript" src="../js/ajax.js"></script>
    </head>

    <body>

        <%@ include file="top.jsp" %>

        <%@ include file="left.jsp" %>

        <td width="50"></td>
        <td width="595" colspan="4" valign="top">	<p>&nbsp;</p>
            <table border="0" cellspacing="0" cellpadding="0" width="595">
                <tr>
                    <td class="pageName"><h1>Modificar area</h1></td>
                </tr>

                <tr>
                    <td class="bodyText">
                        <form method="get" target="_parent" action="modarea.jsp" name="form1">
                            <table width="100%" cellpadding="1" cellspacing="5">
                                <tr>
                                    <td colspan="3" width="20px">&nbsp;</td>
                                </tr>
                                <tr >
                                    <td colspan="3" align="center" bgcolor="#4D6FAC"><h3 style="color:#FFFFFF">Elegir usuario</h3></td>
                                </tr>
                                <tr >
                                    <td colspan="3" height="30px"></td>
                                </tr>
                                <tr>
                                    <td valign="top">Usuarios</td>
                                    <td colspan="2" ><select name="listaUsuarios" size="10" style="width:200px" id="listaUsuarios" onchange="cargarDatosUsuarioAMod()">
                                            <%
        String usu[] = manager.operatorInspectorCajeroList();
        for (int i = 0; i < usu.length; i++) {
            out.print("<option value='" + usu[i] + "'>" + usu[i] + "</option>");
        }
                                            %>
                                        </select>
                                    <br><label class="error" id="menu" style="visibility:hidden"></label></td>
                                </tr>
                                <tr >
                                    <td colspan="3" height="30px"></td>
                                </tr>
                                <tr>
                                    <td>Responsable</td>
                                    <td colspan="2"><div id="res"></div></td>
                                    <%
                            if (resultado == "responsable") {
                                out.print("<tr align='center'><td colspan='3'><p class='error'>El responsable EXISTE en el sistema</p></td></tr>");
                            }
                        %>
                                </tr>
                                <tr>
                                    <td>Sede</td>
                                    <td colspan="2"><div id="sede"></div></td>
                                </tr>
                                <tr>
                                    <td>Sector</td>
                                    <td colspan="2"><div id="sect"></div></td>
                                </tr>
                                <tr>
                                    <td>Digitos Area</td>
                                    <td colspan="2"><div id="diga"></div></td>
                                    <%
        if (resultado == "digitos") {
            out.print("<tr align='center'><td colspan='3'><p class='error'>Los digitos EXISTE en el sistema</p></td></tr>");
        }
                                    %>
                                </tr>
                                <tr>
                                    <td>Digitos Responsable</td>
                                    <td colspan="2"><div id="digr"></div></td>
                                </tr>
                                <tr >
                                    <td colspan="3" height="30px"></td>
                                </tr>
                                <tr>
                                    <td height="30px" colspan="3" align="center">
                                        <div id="but"></div>
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </td>
                </tr>
        </table>    </td>
        <td width="50">
        </td>
        </tr>
        <tr>
            <td width="165" bgcolor="#DAE3F1">&nbsp;</td>
            <td width="50">&nbsp;</td>
            <td width="167">&nbsp;</td>
            <td width="138">&nbsp;</td>
            <td width="50">&nbsp;</td>
            <td width="190">&nbsp;</td>
            <td width="100%">&nbsp;</td>
        </tr>
        </table>
    </body>
</html>
