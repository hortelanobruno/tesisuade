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
	String usuario = session.getAttribute("usuario").toString();
	DBManager manager = new DBManager();
        if(!manager.isConnected()){
            response.sendRedirect("../index.jsp");
        }
	List<Integer> recibos = manager.obtenerRecibosPendientes(usuario);
	if(recibos.size() == 0){
		response.sendRedirect("nohayboletas.jsp");
	}
	String motivo = request.getParameter("motivo");
	String recibo = request.getParameter("recibos");
        String fecha = request.getParameter("date");
	if(motivo != null){
		String resultado = manager.reciboExtraviada(recibo,motivo,fecha);
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
    inputField: "fecha",
    ifFormat:   "%d / %m / %Y",
    button:     "selector"
  });
}
</script>
<%@ include file="top.jsp" %>

<%@ include file="left.jsp" %>

    <td width="50"></td>
    <td width="595" colspan="4" valign="top">	<p>&nbsp;</p>
      <table border="0" cellspacing="0" cellpadding="0" width="595">
        <tr>
          <td class="pageName"><h1>Recibo extraviado</h1>
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
          <td><select name="recibos" style="width:180px" id="recibos">
           <%
				for(int i=0 ; i < recibos.size(); i++){
					out.print("<option value="+recibos.get(i)+">"+recibos.get(i)+"</option>");
				}
			%>
          </select>&nbsp;&nbsp;&nbsp;&nbsp;<label class="error" id="recibo" style="visibility:hidden">Debe seleccionar un campo</label></td>
          </tr>
          <tr>
          <td>Fecha</td>
          <td><input name="date" type="text" size="30" id="fecha" readonly="readonly"/>
			<img src="../img/calendario.png"  width="20" height="20" id="selector" />
          &nbsp;&nbsp;&nbsp;&nbsp;<label class="error" id="fecha2" style="visibility:hidden"></label></td>
          </tr>
          <tr>
          <td valign="top">Motivo</td>
          <td><textarea name="motivo" cols="24" rows="10"></textarea>&nbsp;&nbsp;&nbsp;&nbsp;<label class="error" id="motivo1" style="visibility:hidden">Debe completar el campo</label></td>
          </tr>
          <tr >
          <td colspan="2" height="30px"></td>
          </tr>
          <tr >
            <td height="30px" colspan="2" align="center">
            <input name="cargar" type="button" value="Cargar recibo" style="width:100px" onClick="validarAnularRecibo()"/>	
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
