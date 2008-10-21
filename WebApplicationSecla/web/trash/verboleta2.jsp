<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*,java.io.*, java.util.*" errorPage="" %>
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
	String validar = request.getParameter("validar");
	if(validar != null){
		List<Integer> lista = new ArrayList<Integer>();	
		Integer aux1 = Integer.parseInt(request.getParameter("validar"));
		Integer aux2 = Integer.parseInt(request.getParameter("validar2"));
		for(Integer i = aux1 ; i < (aux1+aux2) ; i++){
			Object aux = request.getParameter(i.toString());
			if(aux != null){
				if (aux.equals("checked")) {
					lista.add(i);
				}
			}			
		}
		manager.confirmarRecibos(lista);
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
</head>

<body>

<%@ include file="top.jsp" %>

<%@ include file="left.jsp" %>

    <td width="50"></td>
    <td width="595" colspan="4" valign="top">	<p>&nbsp;</p>
      <table border="0" cellspacing="0" cellpadding="0" width="789">
        <tr>
          <td width="789" class="pageName"><h1>Ver boletas</h1>
          <p>&nbsp;</p></td>
		</tr>

		<tr>
          <td class="bodyText">
          <form method="post" action="verboleta2.jsp" name="form1" target="_parent">
          <table width="100%" cellpadding="1" cellspacing="5">
          <tr>
          <td colspan="3" align="center" bgcolor="#4D6FAC">
          <h3 style="color:#FFFFFF">Datos</h3>
          </td>
          </tr>
          <tr height="20px"><td></td>
          </tr>
          <%
		  String user = request.getParameter("usuarios");
		  List numeros = new ArrayList();
			if(user != null){
				List<Recibo> boletas = manager.obtenerRecibosAConfirmar(user);		
				if(boletas.isEmpty()){
					out.print("<tr><td colspan='3' align='center'>No hay boletas por confirmar</td></tr>");
				}else{
					out.print("<tr><td colspan='3' align='center'>");
					out.print("<table width='100%' border='1' cellpadding='1' cellspacing='0' bordercolor='#4D6FAC'>");
					out.print("<tr><td align='center'>Numero</td><td align='center'>Estado</td><td align='center'>Fecha rendicion</td><td align='center'>Beneficiario</td><td align='center'>Monto</td><td align='center'>Motivo</td><td align='center'>Confirmar</td></tr>");
					for(int i=0 ; i < boletas.size() ; i++){
						Recibo boleta = boletas.get(i);
						numeros.add(boleta.getNumero());
						if(boleta.getMotivo().isEmpty()){
							out.print("<tr><td align='center'>"+boleta.getNumero()+"</td><td align='center'>"+boleta.getEstadorecibo()+"</td><td align='center'>"+boleta.getFecharendicion()+"</td><td align='center'>"+boleta.getBeneficiario()+"</td><td align='center'>"+boleta.getMonto()+"</td><td></td><td align='center'><input id='"+boleta.getNumero()+"' name='"+boleta.getNumero()+"' type='checkbox' value='' /></td></tr>");
						}else{
							out.print("<tr><td align='center'>"+boleta.getNumero()+"</td><td align='center'>"+boleta.getEstadorecibo()+"</td><td></td><td></td><td></td><td align='center'>"+boleta.getMotivo()+"</td><td align='center'><input name='"+boleta.getNumero()+"' id='"+boleta.getNumero()+"' type='checkbox' value='' /></td></tr>");
						}
					}
					out.print("</table>");
					out.print("</td></tr>");
				}
    		}
          %>
          <tr height="20px"><td></td>
          </tr>
          <tr>
                <td height="30px" colspan="3" align="center">
                <%
                    user = request.getParameter("usuarios");
                    if(user != null){
                            if(!numeros.isEmpty()){
                                    out.print("<input name='confirmar' type='button' value='Confirmar' onclick='confirmarRecibos()' />");
                                    out.print("<input name='validar' type='hidden' id='validar' value='"+numeros.get(0)+"' />	");
                                    out.print("<input name='validar2' type='hidden' id='validar2' value='"+numeros.size()+"' />	");
                            }					
                    }
                %>
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
