<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*,java.io.*, java.util.*" errorPage="" %>
<%@ page import="db.*,java.util.HashMap,java.util.Map" %> 
<%@ page import="net.sf.jasperreports.engine.*"%> 
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
            <td class="pageName"><h1>Generar reporte</h1><br />
          <br /></td>
	</tr>
        <tr>
          <td class="bodyText">
          <form action="scriptlet.jsp" name="form1" method="post">
          <table>
              <tr>
                  <td colspan="2">
                      Formato:
                  </td>
              </tr>
              <tr>
                  <td>
                      HTML:
                  </td>
                  <td>
                      <input name="reporte" type="radio" value="html" />
                  </td>
              </tr>
              <tr>
                  <td>
                      PDF:
                  </td>
                  <td>
                      <input name="reporte" type="radio" value="pdf" checked />
                  </td>
              </tr>
              <tr>
              		<td colspan="2">
                    <input name="Generar" type="submit"/>
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
