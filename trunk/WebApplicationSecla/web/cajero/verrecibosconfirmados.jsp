<%@ page contentType="text/html; charset=ISO-8859-1" language="java" import="java.sql.*,java.io.*, java.util.*" errorPage="" %>
<%@ page import="db.DBManager,varios.Recibo" %> 
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
<link rel="stylesheet" href="../estilo/calendar-blue.css" type="text/css" />
<title>Sistema de control de recibos</title>
<script language="javascript" type="text/javascript" src="../js/script.js"></script>
<script language="javascript" type="text/javascript" src="../js/calendar.js" ></script>
<script language="javascript" type="text/javascript" src="../js/calendar-es.js" ></script>
<script language="javascript" type="text/javascript" src="../js/calendar-setup.js"></script>
</head>

<body>
<script type="text/javascript">
window.onload = function() {
  Calendar.setup({
    inputField: "fecha1",
    ifFormat:   "%d / %m / %Y",
    button:     "selector1"
  });
  Calendar.setup({
    inputField: "fecha2",
    ifFormat:   "%d / %m / %Y",
    button:     "selector2"
  });
}
</script>
<%@ include file="top.jsp" %>

<%@ include file="left.jsp" %>

    <td width="50"></td>
    <td width="595" colspan="4" valign="top">	<p>&nbsp;</p>
      <table border="0" cellspacing="0" cellpadding="0" width="789">
        <tr>
          <td width="789" class="pageName"><h1>Ver recibos confirmados</h1>
          <p>&nbsp;</p></td>
		</tr>

		<tr>
          <td class="bodyText">
          <form method="post" action="scriptlet2.jsp" name="form1" target="blank">
          <table width="100%" cellpadding="1" cellspacing="5">
          <tr>
          <td colspan="3" align="center" bgcolor="#4D6FAC">
          <h3 style="color:#FFFFFF">Recibos confirmados</h3>
          </td>
          </tr>
          <tr height="20px"><td></td>
          </tr>
          <tr>
          <td>
                  <tr heigth="20px"></tr>
                  <tr heigth="20px"></tr>
                  <tr><td colspan="2" align="left">
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
                      </table>
                  </td></tr>
                  <tr heigth="20px"></tr>
                  <tr heigth="20px"></tr>
                  <tr><td>Rango de recibos</td><td>
                      <input id="numMin" name="numMin"  type="text" size="5" />
                                &nbsp;al &nbsp;
                        <input id="numMax" name="numMax" type="text" size="5" />
                  </td></tr>
                  <tr><td colspan="2"><label class="error" id="rangob" style="visibility:hidden"></label></td></tr>
                  <tr heigth="20px"></tr>
                  <tr>
                      <td>Rango de fecha de rendicion:
                      </td>
                      <td><input name="fecha1" type="text" size="15" id="fecha1" readonly="readonly"/>
                            <img src="../img/calendario.png"  width="20" height="20" id="selector1" />
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <input name="fecha2" type="text" size="15" id="fecha2" readonly="readonly"/>
                            <img src="../img/calendario.png"  width="20" height="20" id="selector2" />
                            &nbsp;&nbsp;&nbsp;&nbsp;
                      </td>
                 </tr>
                 <tr><td><label class="error" id="rangof" style="visibility:hidden"></label></td></tr>
                 <tr heigth="20px"></tr>
                 <tr><td><input value="Generar Reporte" size="10" type="button" onclick="valirdarVerRecibosEntregados()"/></td></tr>
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