<%@ page contentType="text/html; charset=ISO-8859-1" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="db.DBManager" %> 
<%
            Object connectado = session.getAttribute("conectado");
            if (connectado != null) {
                if (connectado.toString().equals("no")) {
                    response.sendRedirect("../index.jsp");
                }
            } else {
                response.sendRedirect("../index.jsp");
            }
            DBManager manager = new DBManager();
            if(!manager.isConnected()){
                response.sendRedirect("../index.jsp");
            }
            String responsable = request.getParameter("responsable");
            String sede = request.getParameter("sede");
            String sector = request.getParameter("sector");
            String digarea = request.getParameter("digarea");
            String digresp = request.getParameter("digresp");
            String usuario = request.getParameter("usuario");
            String password1 = request.getParameter("password1");
            String password2 = request.getParameter("password2");
            String resultado = new String();
            if (responsable != null) {
                resultado = manager.addArea(responsable, sede, sector, digarea, digresp, usuario, password1);
                if (resultado != null) {
                    if (resultado == "ok") {
                        response.sendRedirect("altaareaok.jsp");
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
</head>

<body>

<%@ include file="top.jsp" %>

<%@ include file="left.jsp" %>

<td width="50"></td>
<td width="595" colspan="4" valign="top" align="center">	<p>&nbsp;</p>
    <table border="0" cellspacing="0" cellpadding="0" width="595">
    <tr>
        <td class="pageName" align="center"><h1>Alta de Area</h1></td>
    </tr>
    <tr height="20px">
    </tr>
    <tr>
        <td class="bodyText">
            <form method="post" target="_parent" id="form1" action="altaarea.jsp">
            <table width="100%" cellpadding="1" cellspacing="5">
                <tr>
                    <td>&nbsp;</td>
                    <td width="20px">&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
                <tr >
                    <td colspan="3" align="center" bgcolor="#4D6FAC"><h3 style="color:#FFFFFF">Datos Usuario</h3></td>
                </tr>
                <tr>
                    <td colspan="3" height="30px"></td>
                </tr>
                <tr>
                    <td>Responsable encargado</td>
                    <td>&nbsp;</td>
                    <td><input name="responsable" type="text" size="30" onkeypress="if(event.keyCode == 13) validarAltaArea()"/>&nbsp;&nbsp;&nbsp;&nbsp;<label class="error" id="resp" style="visibility:hidden"></label>
                        <%
                            if (resultado == "responsable") {
                                out.print("<tr align='center'><td colspan='3'><p class='error'>El responsable EXISTE en el sistema</p></td></tr>");
                            }
                        %>
                    </td>
                </tr>
                <tr>
                <td>Sede</td>
                <td>&nbsp;</td>
                <td><input name="sede" type="text" size="30" onkeypress="if(event.keyCode == 13) validarAltaArea()"/>&nbsp;&nbsp;&nbsp;&nbsp;<label class="error" id="sed" style="visibility:hidden"></label>
                </td>
                </tr>
                <tr>
                <td>Sector</td>
                <td>&nbsp;</td>
                <td><input name="sector" type="text" size="30" onkeypress="if(event.keyCode == 13) validarAltaArea()"/>&nbsp;&nbsp;&nbsp;&nbsp;<label class="error" id="sec" style="visibility:hidden"></label>
                </td>
                </tr>
                <tr>
                <td>Digito area</td>
                <td>&nbsp;</td>
                <td><input name="digarea" type="text" size="30" onkeypress="if(event.keyCode == 13) validarAltaArea()"/>&nbsp;&nbsp;&nbsp;&nbsp;<label class="error" id="dig1" style="visibility:hidden"></label>
                <%
                    if (resultado == "digitos") {
                        out.print("<tr align='center'><td colspan='3'><p class='error'>Los digitos EXISTE en el sistema</p></td></tr>");
                    }
                %>
                </td>
                </tr>
                <tr>
                <td>Digito responsable</td>
                <td>&nbsp;</td>
                <td><input name="digresp" type="text" size="30" onkeypress="if(event.keyCode == 13) validarAltaArea()"/>&nbsp;&nbsp;&nbsp;&nbsp;<label class="error" id="dig2" style="visibility:hidden"></label>
                </td>
                </tr>
                <tr >
                    <td colspan="3" height="30px"></td>
                </tr>
                <tr >
                    <td colspan="3" align="center" bgcolor="#4D6FAC"><h3 style="color:#FFFFFF">Datos Cuenta</h3></td>
                </tr>
                <tr >
                    <td colspan="3" height="30px"></td>
                </tr>
                <tr>
                    <td>Nombre de usuario</td>
                    <td>&nbsp;</td>
                    <td><input name="usuario" type="text" size="30" onkeypress="if(event.keyCode == 13) validarAltaArea()"/>&nbsp;&nbsp;&nbsp;&nbsp;<label class="error" id="usu" style="visibility:hidden"></label>
                        <%
                            if (resultado == "usuario") {
                                out.print("<tr align='center'><td colspan='3'><p class='error'>El usuario EXISTE en el sistema</p></td></tr>");
                            }
                        %>
                    </td>
                </tr>
                <tr>
                    <td>Contrase&ntilde;a</td>
                    <td>&nbsp;</td>
                    <td><input name="password1" type="password" size="30" onkeypress="if(event.keyCode == 13) validarAltaArea()"/>&nbsp;&nbsp;&nbsp;&nbsp;<label class="error" id="pwd1" style="visibility:hidden"></label>
                    </td>
                </tr>
                <tr>
                    <td>Repetir contrase&ntilde;a</td>
                    <td>&nbsp;</td>
                    <td><input name="password2" type="password" size="30" onkeypress="if(event.keyCode == 13) validarAltaArea()"/>&nbsp;&nbsp;&nbsp;&nbsp;<label class="error" id="pwd2" style="visibility:hidden"></label>
                    </td>
                </tr>
                <tr >
                    <td colspan="3"><label class="error" id="pwds" style="visibility:hidden">Los passwords son diferentes</label></td>
                </tr>
                <tr >
                    <td colspan="3" height="30px"></td>
                </tr>
                <tr >
                    <td height="30px" colspan="3" align="center">
                        <input name="cargar" type="button" value="Alta Area" style="width:100px" onClick="validarAltaArea()"/>	
                    </td>
                </tr>
            </table>
</form>
</td>
</tr>
</table>    
</td>
<td width="50">
</td>
</tr>
<tr height="100">
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
