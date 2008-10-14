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
          <td class="pageName"><h1>Generar reporte<br />
          <br /></td>
	</tr>
        <tr>
          <td class="bodyText">
            <% 
            JasperReport jr;
            JasperPrint jp;
            Map params = new HashMap();
            params.put("usuarioId", session.getAttribute("usuario"));
            Conexion con = new Conexion();
            Conexion.driverOdbc();
            System.setProperty(

                        "jasper.reports.compile.class.path",

                        application.getRealPath("/lib/jasperreports-3.0.1.jar") +

                        System.getProperty("path.separator") +

                        application.getRealPath("/WEB-INF/classes/")

                        );

            

            System.setProperty(

                        "jasper.reports.compile.temp",

                        /*application.getRealPath("/src/java/report/"*/
                        "C:/Documents and Settings/Administrador/Mis documentos/NetBeansProjects/WebApplicationSecla/src/java/report/"

                        );
            if(con.abrirConexion()){
                Connection conn = con.getCon();
                try{
                    jr = JasperCompileManager.compileReport(new FileInputStream("C:/Documents and Settings/Administrador/Mis documentos/NetBeansProjects/WebApplicationSecla/src/java/report/prueba.jrxml")); 
                    jp = JasperFillManager.fillReport(jr, params, conn);
                    //jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, con);
                   // net.sf.jasperreports.engine.JasperExportManager.exportReportToPdfFile(jp, "c:/DMS_Report1.pdf");
                    net.sf.jasperreports.engine.JasperExportManager.exportReportToHtmlFile(jp, "/reporte?image=");

                }catch(Exception e){
                out.println(e);
                }
                finally{

                }
            }
            %>
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
