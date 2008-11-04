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
        DBManager manager = new DBManager();
        if(!manager.isConnected()){
            response.sendRedirect("../index.jsp");
        }
	String operador = request.getParameter("listaOperadores");
	String numMin = request.getParameter("numMin");
	String numMax = request.getParameter("numMax");
	String resultado = new String();
	if(operador != null){
		session.setAttribute("operador",operador);
		resultado = manager.cargarRecibos(operador,numMin,numMax);
		if(resultado != null){
			if(resultado == "true"){
				response.sendRedirect("entregarboletaok.jsp");	
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
<style type="text/css">
<!--
.style1 {color: #FFFFFF}
-->
</style>
</head>

<body>

<%@ include file="top.jsp" %>

<%@ include file="left.jsp" %>

    <td width="50"></td>
    <td width="595" colspan="4" valign="top">	<p>&nbsp;</p>
      <table border="0" cellspacing="0" cellpadding="0" width="595">
        <tr>
          <td class="pageName"><h1>Entregar recibos</h1></td>
		</tr>

		<tr>
          <td class="bodyText">
          <form method="post" target="_parent" action="entregarboleta.jsp" name="form1">
          <table width="100%" cellpadding="1" cellspacing="5">
          	<tr>
                <td colspan="3" height="20px">&nbsp;</td>
          	</tr>
          	<tr>
            	<td valign="top">
                <table width="100%" align="center" cellpadding="1" cellspacing="1">
                  <tr>
                    <td width="229" height="41" align="center" valign="middle" bgcolor="#4D6FAC"><h3 style="color:#FFFFFF">Responsable:</h3></td>
                  </tr>
                  <tr height="20">
                  </tr>
                  <tr>
                    <td><select name="listaOperadores" size="10" style="width:300px" id="listaUsuarios">
                      <%
					
					String usu[] = manager.responsableList();
					for(int i=0 ; i < usu.length; i++){
						out.print("<option>"+usu[i]+"</option>");
					}
				%>
                    </select></td>
                  </tr>
                  <tr>
                    <td><label class="error" id="menu" style="visibility:hidden">Debe seleccionar un campo</label></td>
                  </tr>
                </table>
                <br />
                <br />
                <br>
              <label class="error" id="menu" style="visibility:hidden"></label></td>
              <td colspan="2" valign="top">
                <table width="100%" align="center" cellpadding="1" cellspacing="1">
                  <tr>
                    <td width="229" height="41" align="center" valign="middle" bgcolor="#4D6FAC"><h3 style="color:#FFFFFF">Recibos:</h3></td>
                  </tr>
                    <tr height="50px"><td height="161" valign="top"><p>&nbsp;</p>
                      <p>Numero &nbsp; 
                        <input id="numMin" name="numMin"  type="text" size="5" />
  &nbsp;al &nbsp;
                        <input id="numMax" name="numMax" type="text" size="5" />
                        </p>
                        <br />
                        <label class="error" id="rango" style="visibility:hidden"></label>
                        <%
					if(resultado == "false"){
						out.print("<p class='error'>Error en el rango de los recibos</p>");
					}
					%>
                        </td>
                  </tr>
                    
                    <tr height="50px">
                    	<td align="center"><input name="entregar" type="button" value="Entregar" onClick="validarEntregarRecibo()" /></td>
                  </tr>
                </table>
              </td>
            </tr>  
            <tr>
            	<td colspan="3" height="20px">&nbsp;</td>
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
