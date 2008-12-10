<%@ page import="net.sf.jasperreports.engine.*" %>
<%@ page import="net.sf.jasperreports.engine.design.*" %>
<%@ page import="net.sf.jasperreports.engine.data.*"%>
<%@ page import="net.sf.jasperreports.engine.export.*"%>
<%@ page import="net.sf.jasperreports.engine.util.*"%>
<%@ page import="net.sf.jasperreports.view.*"%>
<%@ page import="net.sf.jasperreports.view.save.*"%>
<%@ page import="net.sf.jasperreports.j2ee.servlets.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="db.*" %>
<%@ page import="ar.com.fdvs.dj.core.DJConstants,ar.com.fdvs.dj.core.DynamicJasperHelper,
ar.com.fdvs.dj.core.layout.ClassicLayoutManager,ar.com.fdvs.dj.domain.DynamicReport,ar.com.fdvs.dj.domain.builders.FastReportBuilder,
net.sf.jasperreports.view.JasperViewer" %>
<%@ page contentType="text/html; charset=ISO-8859-1" language="java" import="java.sql.*,java.io.*, java.util.*" errorPage="" %>
<%@ page import="db.*,java.util.HashMap,java.util.Map,report.ReportesDinamicos" %>
<%
        Object connectado = session.getAttribute("conectado");
        if (connectado != null) {
            if (connectado.toString().equals("no")) {
                response.sendRedirect("../index.jsp");
            }
        } else {
            response.sendRedirect("../index.jsp");
        }
        DBManager manager = DBManager.getInstance();
        if (!manager.isConnected()) {
            response.sendRedirect("../index.jsp");
        }
%>
<html>
    <head>
    <script language="JavaScript">
        function regresar(){
            history.back();
        }
        function error(){
            alert("Error encontrando procesando el periodo: " + periodo);
            history.back();
        }
    </script>
    <head>
    <%
        try {
            String reporte = request.getParameter("reporte");
            Map params = new HashMap();
            String usuario = session.getAttribute("usuario").toString();
            List<String> datos = manager.datosUsuario(usuario);
            params.put("usuario", usuario);
            params.put("funcion", datos.get(3));
            params.put("secretaria", datos.get(2));
            params.put("responsable", datos.get(1));
            Conexion con = new Conexion();
            Conexion.driverOdbc();            
            if (con.abrirConexion()) {
                Connection conn = con.getCon();
                DynamicReport dr = ReportesDinamicos.generarReporteOperador();
                if (reporte.equalsIgnoreCase("pdf")) {
                    JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), conn, params);
                    JRPdfExporter exporter = new JRPdfExporter();
                    OutputStream ouputStream = response.getOutputStream();
                    request.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jp);
                    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
                    exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
                    exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, request.getContextPath() + "/jasperImages?image=");
                    exporter.exportReport();
                } else {
                    JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), conn, params);
                    JRHtmlExporter exporter = new JRHtmlExporter();
                    OutputStream ouputStream = response.getOutputStream();
                    request.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jp);
                    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
                    exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
                    exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, request.getContextPath() + "/jasperImages?image=");
                    exporter.exportReport();
                }
            }
        } catch (JRException e) {
        } catch (Exception e) {
        }
    %>
    <body >
    </body>
</html>