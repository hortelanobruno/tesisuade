<%@ page contentType="text/html; charset=ISO-8859-1" language="java" import="java.sql.*,java.io.*, java.util.*" errorPage="" %>
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
	Object usuario = session.getAttribute("usuario");
	DBManager manager = new DBManager();
        if(!manager.isConnected()){
            response.sendRedirect("../index.jsp");
        }
	List<String> datos = manager.datosUsuario(usuario);
	String password2 = request.getParameter("password3");
	String passwordViejo = request.getParameter("password1");
	String password1 = request.getParameter("password2");
	String resultado = new String();
	if(password1 != null){
		resultado = manager.resetPassword(usuario.toString(),password1);
		if(resultado != null){
			if(resultado == "ok"){
				response.sendRedirect("cambiarpasswordok.jsp");	
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
          <td class="pageName"><h1>Cambiar Contrase&ntilde;a</h1></td>
		</tr>
		<tr>
          <td class="bodyText">
          <form method="post" target="_parent" action="cambiarpassword.jsp" name="form1" >
          <table width="100%" cellpadding="1" cellspacing="5">
              <tr>
                <td width="147"><input name="password4" type="hidden" size="30" value="<%=datos.get(0)%>"/>&nbsp;</td>
                <td width="37">&nbsp;</td>
                <td width="383">&nbsp;</td>
          	  </tr>
              <tr >
                <td colspan="3" align="center" bgcolor="#4D6FAC"><h3 style="color:#FFFFFF">Datos Usuario</h3></td>
              </tr>
              <tr >
                <td colspan="3" height="30px"></td>
              </tr>
              <tr>
                <td>Responsable encargado</td>
                <td>&nbsp;</td>
                <td>
                <%= datos.get(1) %>
                </td>
              </tr>
              <tr>
                <td>Sede</td>
                <td>&nbsp;</td>
                <td>
                <%= datos.get(2) %>
                </td>
              </tr>
              <tr>
                <td>Sector</td>
                <td>&nbsp;</td>
                <td>
                <%= datos.get(3) %>
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
                <td>
                <%= usuario %>
                </td>
              </tr>
              <tr>
                <td>Contrase&ntilde;a actual</td>
                <td>&nbsp;</td>
                <td><input name="password1" type="password" size="30" />&nbsp;&nbsp;&nbsp;&nbsp;<label class="error" id="pwd1" style="visibility:hidden"></label>
                </td>
              </tr>
              <tr>
                <td>Contrase&ntilde;a nueva</td>
                <td>&nbsp;</td>
                <td><input name="password2" type="password" size="30" />&nbsp;&nbsp;&nbsp;&nbsp;<label class="error" id="pwd2" style="visibility:hidden"></label>
                </td>
              </tr>
              <tr>
                <td>Repetir contrase&ntilde;a nueva</td>
                <td>&nbsp;</td>
                <td><input name="password3" type="password" size="30" />&nbsp;&nbsp;&nbsp;&nbsp;<label class="error" id="pwd3" style="visibility:hidden"></label>
                </td>
              </tr>
              <tr >
                <td colspan="3"><label class="error" id="pwds" style="visibility:hidden">Los passwords son diferentes</label></td>
              </tr>
              <tr>
                <td colspan="3" height="30px"></td>
              </tr>
              <tr>
                <td height="30px" colspan="3" align="center">
                <input name="actualizar" type="button" value="Actualizar"  onclick="validarCambioContrasenia()" />	
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
