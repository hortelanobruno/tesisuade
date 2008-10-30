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
	String[] responsable = manager.responsableList();
        String[] sector = manager.sectorList();
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
              <tr height="20">
                  <td align="center" bgcolor="#4D6FAC" width="200">
                  <h3 style="color:#FFFFFF">Formato</h3>
                  </td>
                  <td align="center" bgcolor="#4D6FAC" width="200">
                  <h3 style="color:#FFFFFF">Tipo</h3>
                  </td>
                  <td align="center" bgcolor="#4D6FAC" width="200">
                  <h3 style="color:#FFFFFF">Recibo</h3>
                  </td>
              </tr>
              <tr height="30">
                  <td colspan="3">
                      
                  </td>
              </tr>
              <tr>
                  <td>
                      HTML:<input name="reporte" type="radio" value="html" />
                  </td>
                  <td>
                      Responsables
                  </td>
                  <td>
                      <input type="checkbox" name="recibo" checked>Completados
                  </td>
              </tr>
              <tr>
                  <td>   
                      PDF:<input name="reporte" type="radio" value="pdf" checked />
                  </td>
                  <td>
                      <select multiple size="5"  name="responsables" style="width:180px" id="usuario" onchange="document.form1.sectores.selectedIndex = 0">
                          <option value="Ninguno">Ninguno</option>
                          <option value="Todos">Todos</option>
                           <%
                                    for(int i=0 ; i < responsable.length; i++){
                                            out.print("<option value="+responsable[i]+">"+responsable[i]+"</option>");
                                    }
                            %>
                      </select>
                      <label class="error" id="res1" style="visibility:hidden"></label>
                  </td>
                  <td>
                      <input type="checkbox" name="recibo">Anulados
                  </td>
              </tr>
              <tr>
                  <td>
                      
                  </td>
                  <td>
                      Sectores:
                  </td>
                  <td>
                      <input type="checkbox" name="recibo">Extraviados
                  </td>
              </tr>
              <tr>
                  <td>
                      
                  </td>
                  <td>
                      <select multiple size="5" name="sectores" style="width:180px" id="usuario" onchange="document.form1.responsables.selectedIndex = 0">
                      <option value="Ninguno">Ninguno</option>
                      <option value="Todos">Todos</option>
                       <%
                                for(int i=0 ; i < sector.length; i++){
                                        out.print("<option value="+sector[i]+">"+sector[i]+"</option>");
                                }
                        %>
                    </select>
                    <label class="error" id="sec1" style="visibility:hidden"></label>
                  </td>
                  <td>
                      <label class="error" id="recibo1" style="visibility:hidden"></label>
                  </td>
              </tr>
              <tr height="30">
                  <td colspan="3">
                      
                  </td>
              </tr>
              <tr>
              	  <td colspan="3" align="center">
                    <input value="Generar Reporte" size="10" type="button" onclick="valirdarGenerarReportes()"/>
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
