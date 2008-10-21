<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*,java.io.*, java.util.*" errorPage="" %>
<%@ page import="javax.servlet.http.*,javax.faces.context.*,javax.naming.*,javax.sql.DataSource,net.sf.jasperreports.engine.*,db.DBManager,java.util.HashMap,java.util.Map" %> 
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
              Map params = new HashMap();
              params.put("usuarioId", session.getAttribute("usuario"));
              

              ExternalContext econtext = getExternalContext();
              InputStream inputStream = application.getResourceAsStream("/travelreport/viajes.jasper");
              if (inputStream == null) {
                  throw new ClassNotFoundException("Archivo viajes.jasper no se encontró");
              }
              FacesContext fcontext = FacesContext.getCurrentInstance();
              try {
                  JRExporter exporter = null;
                  Context  ctx = new InitialContext();
                  String dataSourceName = "Secla";
                  DataSource ds=(DataSource) ctx.lookup(dataSourceName);
                  Connection conn=ds.getConnection();

                  JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, params, conn);
                  HttpServletResponse response = (HttpServletResponse) econtext.getResponse();
                  HttpServletRequest request = (HttpServletRequest) econtext.getRequest();
                  response.setContentType(tipo);
                  if ("application/pdf".equals(tipo)) {
                      exporter = new JRPdfExporter();
                      exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                      exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
                  } else if ("text/html".equals(tipo)) {
                      exporter = new JRHtmlExporter();
                      exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                      exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, response.getWriter());
                      exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, request.getContextPath() + "/image?image=");
                  }
                  if (exporter != null) {
                      exporter.exportReport();
                  }
              } catch (Exception ex) {
                  Logger.getLogger(ApplicationBean1.class.getName()).log(Level.SEVERE, null, ex);
                  throw new FacesException(ex);
              }
              fcontext.responseComplete();
              
              
              
              
              
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
