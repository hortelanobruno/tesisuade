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

<%@ page import="java.io.*,java.util.Date" %>

<%@ page import="db.*" %>

<%@ page contentType="text/html; charset=ISO-8859-1" language="java" import="java.sql.*,java.io.*, java.util.*" errorPage="" %>
<%@ page import="db.*,java.util.HashMap,java.util.Map" %> 
<%@ page import="net.sf.jasperreports.engine.*"%> 
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

    <head><title>Generando Reporte Nomina...............</title>

    <%

        System.out.println("Comienza el Reporte----------------------------");



    %>



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
            String nombreReporte = "verrecibosentregados";
            String reporte = request.getParameter("reporte");
            Map params = new HashMap();
            String num1 = request.getParameter("numMin");
            String num2 = request.getParameter("numMax");
            String fecha1 = request.getParameter("fecha1");
            String[] aux = fecha1.split("/");
            aux[0] = aux[0].trim();
            aux[1] = aux[1].trim();
            aux[2] = aux[2].trim();
            Date date1 = new Date();
            date1.setDate(Integer.parseInt(aux[0]));
            date1.setMonth(Integer.parseInt(aux[1]) - 1);
            date1.setYear(Integer.parseInt(aux[2]) - 1900);
            String fecha2 = request.getParameter("fecha2");
            aux = fecha2.split("/");
            aux[0] = aux[0].trim();
            aux[1] = aux[1].trim();
            aux[2] = aux[2].trim();
            Date date2 = new Date();
            date2.setDate(Integer.parseInt(aux[0]));
            date2.setMonth(Integer.parseInt(aux[1]) - 1);
            date2.setYear(Integer.parseInt(aux[2]) - 1900);
            params.put("fecha1", date1);
            params.put("fecha2", date2);
            params.put("num1", num1);
            params.put("num2", num2);
            Conexion con = new Conexion();
            Conexion.driverOdbc();
            /////////////////////////////////////////////

            System.out.println("********Compilamos pago_nomina.jrxml OK********");

            System.setProperty(
                    "jasper.reports.compile.class.path",
                    application.getRealPath("/WEB-INF/lib/jasperreports-3.0.1.jar") +
                    System.getProperty("path.separator") +
                    application.getRealPath("/WEB-INF/classes/"));

            System.out.println("*****Cargamos el jasperreports-0.6.0.jar OK*********");

            System.setProperty(
                    "jasper.reports.compile.temp",
                    application.getRealPath("/report/"));
            // application.getContextPath()+"/report/");



            JasperCompileManager.compileReportToFile(application.getRealPath("/report/" + nombreReporte + ".jrxml"));
            //JasperCompileManager.compileReportToFile(application.getContextPath()+"/report/prueba3.jrxml");


            System.out.println("******Fin de la Compilamos el archivos***********");

            /////////////////////////////////////////////



            File reportFile = new File(application.getRealPath("/report/" + nombreReporte + ".jasper"));
            //File reportFile = new File(application.getContextPath()+"/report/prueba3.jasper");

            if (con.abrirConexion()) {
                Connection conn = con.getCon();
                if (reporte.equalsIgnoreCase("pdf")) {
                    byte[] bytes =
                            JasperRunManager.runReportToPdf(
                            reportFile.getPath(),
                            params,
                            conn);

                    response.setContentType("application/pdf");
                    response.setContentLength(bytes.length);
                    ServletOutputStream ouputStream = response.getOutputStream();
                    ouputStream.write(bytes, 0, bytes.length);
                    ouputStream.flush();
                    ouputStream.close();
                } else {
                    //JasperRunManager.runReportToHtmlFile(reportFile.getPath(),params,conn);
                    JasperPrint jasperPrint = JasperFillManager.fillReport(reportFile.getPath(), params, conn);
                    JRHtmlExporter exporter = new JRHtmlExporter();
                    OutputStream ouputStream = response.getOutputStream();

                    request.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrint);
                    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                    exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
                    exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, request.getContextPath() + "/jasperImages?image=");
                    exporter.exportReport();
                }
            }

        } catch (JRException e) {
            System.out.println("Error:" + e.getMessage());
        } catch (Exception e) {

            e.printStackTrace();

            System.out.println("Error2:" + e.getMessage());

        }


    %>

    <body >

    </body>

</html>