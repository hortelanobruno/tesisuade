<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*,java.io.*, java.util.*" errorPage="" %>
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
	String usuario = session.getAttribute("usuario").toString();
	DBManager manager = new DBManager();
	List<Integer> boletas = manager.obtenerBoletasPendientes(usuario);
	if(boletas.size() == 0){
		response.sendRedirect("nohayboletas.jsp");
	}
	String motivo = request.getParameter("motivo");
	String boleta = request.getParameter("boletas");
	if(motivo != null){
		String resultado = manager.anularBoleta(boleta,motivo);
		if(resultado != null){
			if(resultado == "true"){
				response.sendRedirect("boletacargadaok.jsp");	
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
          <td class="pageName"><h1>Boleta extraviada</h1>
            <p>&nbsp;</p></td>
		</tr>

		<tr>
          <td class="bodyText">
          <form method="post" action="boletaextraviada.jsp" id="form1" target="_parent">
          <table width="100%" cellpadding="1" cellspacing="5">
          <tr>
          <td colspan="3" align="center" bgcolor="#4D6FAC">
          <h3 style="color:#FFFFFF">Datos</h3>
          </td>
          </tr>
          <tr height="20px"><td></td>
          </tr>
          <tr><td>
          <table width="100%" cellpadding="1" cellspacing="8">
          <tr>
          <td valign="top">Numero de recibo</td>
          <td><select name="boletas" style="width:180px" id="boletas">
           <%
				for(int i=0 ; i < boletas.size(); i++){
					out.print("<option value="+boletas.get(i)+">"+boletas.get(i)+"</option>");
				}
			%>
          </select>&nbsp;&nbsp;&nbsp;&nbsp;<label class="error" id="boleta" style="visibility:hidden">Debe seleccionar un campo</label></td>
          </tr>
          <tr>
          <td valign="top">Motivo</td>
          <td><textarea name="motivo" cols="24" rows="10"></textarea>&nbsp;&nbsp;&nbsp;&nbsp;<label class="error" id="motivo" style="visibility:hidden">Debe completar el campo</label></td>
          </tr>
          <tr >
          <td colspan="2" height="30px"></td>
          </tr>
          <tr >
            <td height="30px" colspan="2" align="center">
            <input name="cargar" type="button" value="Cargar boleta" style="width:100px" onClick="validarAnularBoleta()"/>	
            </td>
          </tr>
          </table>
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
