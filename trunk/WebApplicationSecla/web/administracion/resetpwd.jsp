<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="db.DBManager" %> 
<%
	Object connectado = session.getAttribute("conectado");
	if(connectado != null){
		if(connectado.toString().equals("no")){
			response.sendRedirect("../index.jsp");
		}
	}else{
		response.sendRedirect("../index.jsp");
	}
	String usuario = request.getParameter("listaUsuarios");
	String password1 = request.getParameter("password1");
	String password2 = request.getParameter("password2");
	String resultado = new String();
	if(usuario != null){
		DBManager manager = new DBManager();
		resultado = manager.resetPassword(usuario,password1);
		if(resultado != null){
			if(resultado == "ok"){
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
    <td width="595" colspan="4" valign="top">	<p>&nbsp;</p>
      <table border="0" cellspacing="0" cellpadding="0" width="595">
        <tr>
          <td class="pageName"><h1>Resetear Contrase&ntilde;a</h1></td>
		</tr>

		<tr>
          <td class="bodyText">
          <form method="get" target="_parent" action="resetpwdok.jsp" name="form1">
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
                <td>&nbsp;</td>
                <td><select name="listaUsuarios" size="10" style="width:300px" id="listaUsuarios">
                <%
					DBManager manager = new DBManager();
					String usu[] = manager.operatorList();
					for(int i=0 ; i < usu.length; i++){
						out.print("<option value="+usu[i]+">"+usu[i]+"</option>");
					}
				%>
                  </select> 
                  <br><label class="error" id="menu" style="visibility:hidden">Debe seleccionar un campo</label>
                </td>
            </tr>
            <tr >
                <td colspan="3" height="30px"></td>
            </tr>
            <tr>
                <td>Contrase&ntilde;a nueva</td>
                <td>&nbsp;</td>
                <td><input name="password1" type="password" size="30" />&nbsp;&nbsp;&nbsp;&nbsp;<label class="error" id="pwd1" style="visibility:hidden">Debe completar el campo</label></td>
            </tr>
            <tr>
                <td>Repetir contrase&ntilde;a</td>
                <td>&nbsp;</td>
                <td><input name="password2" type="password" size="30" />&nbsp;&nbsp;&nbsp;&nbsp;<label class="error" id="pwd2" style="visibility:hidden">Debe completar el campo</label></td>
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
