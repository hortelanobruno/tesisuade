<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*,java.io.*, java.util.*" errorPage="" %>
<%@ page import="db.DBManager" %> 

<%  String usuario = request.getParameter("usu");
	String password = request.getParameter("pwd");
	if(usuario != null){
		DBManager manager = new DBManager();
		String cuenta = manager.loginUsuario(usuario,password);
		if(cuenta != null){
			session.setAttribute("usuario",usuario);
			session.setAttribute("password",password);
			session.setAttribute("conectado","si");
			session.setAttribute("cuenta",cuenta);
			if(cuenta.equals("administrador")){
				response.sendRedirect("administracion/index.jsp");					
			}else if(cuenta.equals("operador")){
				response.sendRedirect("operatoria/index.jsp");		
			}else if(cuenta.equals("cajero")){
				response.sendRedirect("cajero/index.jsp");		
			}else{
                            out.print("Cuenta desconocida");
                        }
		}
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Sistema de control de recibos</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="estilo/estilo.css" type="text/css" />
<script language="javascript" type="text/javascript" src="js/script.js"></script>
</head>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr bgcolor="#4D6FAC">

	<td width="382" colspan="3" rowspan="2"><embed src="img/banner_osecac.swf" quality="high"  wmode="transparent" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" width="442" height="80" ></embed></td>
    <td width="378" colspan="3" rowspan="2" align="center" valign="middle" nowrap="nowrap" id="logo"><span class="Estilo1">Sistema de control de recibos</span></td>
    <td width="100%" height="63">&nbsp;</td>
  </tr>
  <tr bgcolor="#3366CC">
    <td width="100%" height="64" bgcolor="#4D6FAC">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="7" bgcolor="#003366"><img src="mm_spacer.gif" alt="" width="1" height="1" border="0" /></td>
  </tr>

  <tr bgcolor="#7EADCF">
  	<td height="25" colspan="7" id="dateformat">&nbsp;&nbsp;
    <script language="JavaScript" type="text/javascript">
      document.write(retornarFecha());	</script>	</td>
  </tr>
  <tr>
    <td colspan="7" bgcolor="#003366"><img src="mm_spacer.gif" alt="" width="1" height="1" border="0" /></td>
  </tr>

  <tr>
    <td width="595" colspan="7" valign="top">	<p>&nbsp;</p></td>
  </tr>
  <tr>
  <td colspan="7" salign="center">
        <form method="post" target="_parent" action="index.jsp" id="form1">
		 <table align="center" cellpadding="1" cellspacing="1" class="tr_roundBox tr_primaryColor" style="width: 350px;">
             <tr  height="20px">
                  <td class="tr_tl"></td> 
                  <td colspan="3"></td> 
                  <td class="tr_tr"></td> 
             </tr>
             <tr align="center" valign="middle">
                  <td colspan="5" align="center"><h3>Ingresa al sistema</h3></td> 
             </tr>
             <tr height="30px">
                  <td colspan="5"></td> 
             </tr>
             <tr height="20px">
             	  <td></td>
                  <td align="left" valign="middle"><label>Usuario:</label>&nbsp;</td>
                  <td></td>
                  <td align="center"><input type="text" name="usu" size='15' onkeypress="iSubmitEnter(window.event, document.form1)"></td>
                  <td></td>
             </tr>
             <% 
			if(usuario == "") {
				out.print("<tr height='20px' align='center'>");
                out.print("<td colspan='5'><label style='color:#FF0000'>Este campo no puede dejarse en blanco</label></td>");
             	out.print("</tr>");
			}
			%>
             <tr height="10px">
                  <td colspan="5"></td> 
             </tr>
             <tr height="20px">
                  <td width="20px"></td>
                  <td align="left" valign="middle"><p><label>Contraseña: </label>&nbsp;</p></td>
                  <td></td>
                  <td align="center"><input type="password" name="pwd" size='15' onkeypress="iSubmitEnter(window.event, document.form1)"></td>
                  <td></td>
             </tr>
              <%  
				if(password == ""){
					out.print("<tr height='20px' align='center'>");
					out.print("<td colspan='5'><label style='color:#FF0000'>Este campo no puede dejarse en blanco</label></td>");
					out.print("</tr>");
				}else{
					if((usuario != null) && (password != null)){
						out.print("<tr height='20px' align='center'>");
						out.print("<td colspan='5'><label style='color:#FF0000'>El nombre de usuario y la contraseña no coinciden</label></td>");
						out.print("</tr>");
					}
				}
                                DBManager manager = new DBManager();
                                if(!manager.isConnected()){
                                    out.print("<tr height='20px' align='center'>");
                                    out.print("<td colspan='5'><label style='color:#FF0000'>Error al conectarse a la base</label></td>");
                                    out.print("</tr>");
                                }
				%>
             <tr height="10px">
                  <td colspan="5"></td> 
             </tr>
             <tr>
                  <td colspan="3"></td>
                  <td align="center"><input type="submit" name="button" id="button" value="Login"></td> 
                  <td></td>
             </tr>
             <tr  height="20px">
                  <td class="tr_bl"><p>&nbsp;</p></td> 
                  <td colspan="3"></td> 
                  <td class="tr_br"></td> 
             </tr>
         </table>
      </form>  </td>
  </tr>
</table>

</body>
</html>
