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
	String[] usuario = manager.operatorList();
        String[] recibos = request.getParameterValues("recibo");
        if(recibos != null){
            List<Integer> confirmar = new ArrayList<Integer>();
            for(int i=0 ; i < recibos.length ; i++){
                confirmar.add(Integer.parseInt(recibos[i]));
            }
            manager.confirmarRecibos(confirmar);
            response.sendRedirect("boletasconfirmadas.jsp");
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
      <table border="0" cellspacing="0" cellpadding="0" width="789">
        <tr>
          <td width="789" class="pageName"><h1>Ver recibos</h1>
          <p>&nbsp;</p></td>
		</tr>

		<tr>
          <td class="bodyText">
          <form method="post" action="verboleta.jsp" name="form1" target="_parent">
          <table width="100%" cellpadding="1" cellspacing="5">
          <tr>
          <td colspan="3" align="center" bgcolor="#4D6FAC">
          <h3 style="color:#FFFFFF">Datos</h3>
          </td>
          </tr>
          <tr height="20px"><td></td>
          </tr>
          <tr>
          <td> Usuario </td>
          <td>
          <select name="usuarios" style="width:180px" id="usuario" onchange="verRecibo()">
              <option value="">&nbsp;</option>
           <%
                    for(int i=0 ; i < usuario.length; i++){
                            out.print("<option value='"+usuario[i]+"'>"+usuario[i]+"</option>");
                    }
            %>
          </select>&nbsp;&nbsp;&nbsp;&nbsp;
          </td>
          </tr>
          <tr height="20px"><td></td>
          </tr>
          </tr>
          <tr><td colspan="3">
          <div id="recibos"></div>
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
