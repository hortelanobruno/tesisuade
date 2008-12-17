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
        String responsable = request.getParameter("listaUsuarios");
        String password1 = request.getParameter("password1");
        String resultado = new String();
        DBManager manager = DBManager.getInstance();
        if (!manager.isConnected()) {
            response.sendRedirect("../index.jsp");
        }
        if (responsable != null) {
            resultado = manager.resetPassword(responsable, password1);
            if (resultado != null) {
                if (resultado == "ok") {
                    response.sendRedirect("resetpwdok.jsp");
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
                    <td class="pageName"><h1>Resetear Contrase&ntilde;a</h1></td>
                </tr>

                <tr>
                    <td class="bodyText">
                        <form method="post" target="_parent" action="resetpwd.jsp" name="form1">
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
                                    <td valign="top">Responsables</td>
                                    <td><select name="listaUsuarios" size="10" style="width:200px" id="listaUsuarios" onchange="cargaraDatosUsuario()">
                                            <%
        String usu[] = manager.operatorInspectorCajeroList();
        for (int i = 0; i < usu.length; i++) {
            out.print("<option value='" + usu[i] + "'>" + usu[i] + "</option>");
        }
                                            %>
                                        </select>
                                    <br><label class="error" id="menu" style="visibility:hidden"></label></td>
                                    <td valign="top">
                                        <table>
                                            <tr><td>Tipo Cuenta : </td><td><label id="cue"></label></td>
                                            </tr>
                                            <tr><td>Responsable : </td><td><label id="res"></label></td>
                                            </tr>
                                            <tr><td>Sede: </td><td><label id="sede"></label></td>
                                            </tr>
                                            <tr><td>Sector : </td><td><label id="sec"></label></td>
                                            </tr>
                                            <tr><td>Digitos : </td><td><label id="dig"></label></td>
                                            </tr>
                                            <tr><td>Contrase&ntilde;a Actual : </td><td><label id="pwd"></label></td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                                <tr >
                                    <td colspan="3" height="30px"></td>
                                </tr>
                                <tr>
                                    <td>Contrase&ntilde;a nueva</td>
                                    <td colspan="2"><input name="password1" type="password" size="30" />&nbsp;&nbsp;&nbsp;&nbsp;<label class="error" id="pwd1" style="visibility:hidden"></label></td>
                                </tr>
                                <tr>
                                    <td>Repetir contrase&ntilde;a</td>
                                    <td colspan="2"><input name="password2" type="password" size="30" />&nbsp;&nbsp;&nbsp;&nbsp;<label class="error" id="pwd2" style="visibility:hidden"></label></td>
                                </tr>
                                <tr >
                                    <td colspan="3"><label class="error" id="pwds" style="visibility:hidden">Los passwords son diferentes</label></td>
                                </tr>
                                <tr >
                                    <td colspan="3" height="30px"></td>
                                </tr>
                                <tr>
                                    <td height="30px" colspan="3" align="center">
                                        <input name="cargar" type="button" value="Resetear" style="width:100px" onClick="validarResetPwd()" />
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
