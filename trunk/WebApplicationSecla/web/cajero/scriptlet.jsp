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

<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*,java.io.*, java.util.*" errorPage="" %>
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
%>
<html>
    
    <head><title>Generando Reporte</title>
    
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

                
                //Map params = new HashMap();
                //DBManager manager = new DBManager();
                //String usuario = session.getAttribute("usuario").toString();
                //List<String> datos = manager.datosUsuario(usuario);
                //params.put("usuario", usuario);
                //params.put("funcion", datos.get(3));
                //params.put("secretaria", datos.get(2));
                //params.put("responsable", datos.get(1));
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
                String reporte = request.getParameter("reporte");
                String[] recibo = request.getParameterValues("recibo");
                String responsable = request.getParameter("responsables");
                String sector = request.getParameter("sectores");
                String fecha1 = request.getParameter("fecha1");
                String fecha2 = request.getParameter("fecha2");
                if((!sector.equalsIgnoreCase("Todos"))&&(recibo.length == 2)&&(!sector.equalsIgnoreCase("Ninguno"))){
                    Map params = new HashMap();
                    String[] aux = fecha1.split("/");
                    aux[0] = aux[0].trim();aux[1] = aux[1].trim();aux[2] = aux[2].trim();
                    fecha1 = aux[1]+"/"+aux[0]+"/"+aux[2];
                    aux = fecha2.split("/");
                    aux[0] = aux[0].trim();aux[1] = aux[1].trim();aux[2] = aux[2].trim();
                    fecha2 = aux[1]+"/"+aux[0]+"/"+aux[2];
                    params.put("fecha1", fecha1);
                    params.put("fecha2", fecha2);
                    params.put("sector",sector);
                    JasperCompileManager.compileReportToFile(application.getRealPath("/report/resprecibosall.jrxml"));
                    File reportFile = new File(application.getRealPath("/report/resprecibosall.jasper"));
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
                        }else{
                            //JasperRunManager.runReportToHtmlFile(reportFile.getPath(),params,conn);
                            JasperPrint jasperPrint = JasperFillManager.fillReport(reportFile.getPath(),params,conn);
                            JRHtmlExporter exporter = new JRHtmlExporter();
                            OutputStream ouputStream = response.getOutputStream();

                            request.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrint);
                            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
                            exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI,request.getContextPath()+"/jasperImages?image=");  
                            exporter.exportReport();
                        }
                    }
                }
                if(sector.equalsIgnoreCase("Todos")&&(recibo.length == 2)){
                    Map params = new HashMap();
                    String[] aux = fecha1.split("/");
                    aux[0] = aux[0].trim();aux[1] = aux[1].trim();aux[2] = aux[2].trim();
                    fecha1 = aux[1]+"/"+aux[0]+"/"+aux[2];
                    aux = fecha2.split("/");
                    aux[0] = aux[0].trim();aux[1] = aux[1].trim();aux[2] = aux[2].trim();
                    fecha2 = aux[1]+"/"+aux[0]+"/"+aux[2];
                    params.put("fecha1", fecha1);
                    params.put("fecha2", fecha2);
                    JasperCompileManager.compileReportToFile(application.getRealPath("/report/resoallrecibosall.jrxml"));
                    File reportFile = new File(application.getRealPath("/report/resoallrecibosall.jasper"));
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
                        }else{
                            //JasperRunManager.runReportToHtmlFile(reportFile.getPath(),params,conn);
                            JasperPrint jasperPrint = JasperFillManager.fillReport(reportFile.getPath(),params,conn);
                            JRHtmlExporter exporter = new JRHtmlExporter();
                            OutputStream ouputStream = response.getOutputStream();

                            request.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrint);
                            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
                            exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI,request.getContextPath()+"/jasperImages?image=");  
                            exporter.exportReport();
                        }
                    }
                }
                if((!sector.equalsIgnoreCase("Todos"))&&(recibo.length == 1)&&(!sector.equalsIgnoreCase("Ninguno"))){
                    Map params = new HashMap();
                    String[] aux = fecha1.split("/");
                    aux[0] = aux[0].trim();aux[1] = aux[1].trim();aux[2] = aux[2].trim();
                    fecha1 = aux[1]+"/"+aux[0]+"/"+aux[2];
                    aux = fecha2.split("/");
                    aux[0] = aux[0].trim();aux[1] = aux[1].trim();aux[2] = aux[2].trim();
                    fecha2 = aux[1]+"/"+aux[0]+"/"+aux[2];
                    params.put("recibo",recibo[0]);
                    params.put("fecha1", fecha1);
                    params.put("fecha2", fecha2);
                    params.put("sector", sector);
                    if(recibo[0].equalsIgnoreCase("Completados")){
                        JasperCompileManager.compileReportToFile(application.getRealPath("/report/respcompletados.jrxml"));
                        File reportFile = new File(application.getRealPath("/report/respcompletados.jasper"));
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
                            }else{
                                //JasperRunManager.runReportToHtmlFile(reportFile.getPath(),params,conn);
                                JasperPrint jasperPrint = JasperFillManager.fillReport(reportFile.getPath(),params,conn);
                                JRHtmlExporter exporter = new JRHtmlExporter();
                                OutputStream ouputStream = response.getOutputStream();

                                request.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrint);
                                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
                                exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI,request.getContextPath()+"/jasperImages?image=");  
                                exporter.exportReport();
                            }
                        }
                    }else{
                        JasperCompileManager.compileReportToFile(application.getRealPath("/report/respanulados.jrxml"));
                        File reportFile = new File(application.getRealPath("/report/respanulados.jasper"));
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
                            }else{
                                //JasperRunManager.runReportToHtmlFile(reportFile.getPath(),params,conn);
                                JasperPrint jasperPrint = JasperFillManager.fillReport(reportFile.getPath(),params,conn);
                                JRHtmlExporter exporter = new JRHtmlExporter();
                                OutputStream ouputStream = response.getOutputStream();

                                request.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrint);
                                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
                                exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI,request.getContextPath()+"/jasperImages?image=");  
                                exporter.exportReport();
                            }
                        }
                    }
                }
                if(sector.equalsIgnoreCase("Todos")&&(recibo.length == 1)){
                    Map params = new HashMap();
                    String[] aux = fecha1.split("/");
                    aux[0] = aux[0].trim();aux[1] = aux[1].trim();aux[2] = aux[2].trim();
                    fecha1 = aux[1]+"/"+aux[0]+"/"+aux[2];
                    aux = fecha2.split("/");
                    aux[0] = aux[0].trim();aux[1] = aux[1].trim();aux[2] = aux[2].trim();
                    fecha2 = aux[1]+"/"+aux[0]+"/"+aux[2];
                    params.put("recibo",recibo[0]);
                    params.put("fecha1", fecha1);
                    params.put("fecha2", fecha2);
                    if(recibo[0].equalsIgnoreCase("Completados")){
                        JasperCompileManager.compileReportToFile(application.getRealPath("/report/resoallcompletados.jrxml"));
                        File reportFile = new File(application.getRealPath("/report/resoallcompletados.jasper"));
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
                            }else{
                                //JasperRunManager.runReportToHtmlFile(reportFile.getPath(),params,conn);
                                JasperPrint jasperPrint = JasperFillManager.fillReport(reportFile.getPath(),params,conn);
                                JRHtmlExporter exporter = new JRHtmlExporter();
                                OutputStream ouputStream = response.getOutputStream();

                                request.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrint);
                                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
                                exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI,request.getContextPath()+"/jasperImages?image=");  
                                exporter.exportReport();
                            }
                        }
                    }else{
                        JasperCompileManager.compileReportToFile(application.getRealPath("/report/resoallanulados.jrxml"));
                        File reportFile = new File(application.getRealPath("/report/resoallanulados.jasper"));
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
                            }else{
                                //JasperRunManager.runReportToHtmlFile(reportFile.getPath(),params,conn);
                                JasperPrint jasperPrint = JasperFillManager.fillReport(reportFile.getPath(),params,conn);
                                JRHtmlExporter exporter = new JRHtmlExporter();
                                OutputStream ouputStream = response.getOutputStream();

                                request.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrint);
                                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
                                exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI,request.getContextPath()+"/jasperImages?image=");  
                                exporter.exportReport();
                            }
                        }
                    }
                }
                if((!responsable.equalsIgnoreCase("Todos"))&&(recibo.length == 1)&&(!responsable.equalsIgnoreCase("Ninguno"))){
                    Map params = new HashMap();
                    String[] aux = fecha1.split("/");
                    aux[0] = aux[0].trim();aux[1] = aux[1].trim();aux[2] = aux[2].trim();
                    fecha1 = aux[1]+"/"+aux[0]+"/"+aux[2];
                    aux = fecha2.split("/");
                    aux[0] = aux[0].trim();aux[1] = aux[1].trim();aux[2] = aux[2].trim();
                    fecha2 = aux[1]+"/"+aux[0]+"/"+aux[2];
                    params.put("recibo",recibo[0]);
                    params.put("fecha1", fecha1);
                    params.put("fecha2", fecha2);
                    params.put("responsable", responsable);
                    if(recibo[0].equalsIgnoreCase("Completados")){
                        JasperCompileManager.compileReportToFile(application.getRealPath("/report/respcompletados2.jrxml"));
                        File reportFile = new File(application.getRealPath("/report/respcompletados2.jasper"));
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
                            }else{
                                //JasperRunManager.runReportToHtmlFile(reportFile.getPath(),params,conn);
                                JasperPrint jasperPrint = JasperFillManager.fillReport(reportFile.getPath(),params,conn);
                                JRHtmlExporter exporter = new JRHtmlExporter();
                                OutputStream ouputStream = response.getOutputStream();

                                request.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrint);
                                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
                                exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI,request.getContextPath()+"/jasperImages?image=");  
                                exporter.exportReport();
                            }
                        }
                    }else{
                        JasperCompileManager.compileReportToFile(application.getRealPath("/report/respanulados2.jrxml"));
                        File reportFile = new File(application.getRealPath("/report/respanulados2.jasper"));
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
                            }else{
                                //JasperRunManager.runReportToHtmlFile(reportFile.getPath(),params,conn);
                                JasperPrint jasperPrint = JasperFillManager.fillReport(reportFile.getPath(),params,conn);
                                JRHtmlExporter exporter = new JRHtmlExporter();
                                OutputStream ouputStream = response.getOutputStream();

                                request.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrint);
                                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
                                exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI,request.getContextPath()+"/jasperImages?image=");  
                                exporter.exportReport();
                            }
                        }
                    }
                }
                if(responsable.equalsIgnoreCase("Todos")&&(recibo.length == 1)){
                    Map params = new HashMap();
                    String[] aux = fecha1.split("/");
                    aux[0] = aux[0].trim();aux[1] = aux[1].trim();aux[2] = aux[2].trim();
                    fecha1 = aux[1]+"/"+aux[0]+"/"+aux[2];
                    aux = fecha2.split("/");
                    aux[0] = aux[0].trim();aux[1] = aux[1].trim();aux[2] = aux[2].trim();
                    fecha2 = aux[1]+"/"+aux[0]+"/"+aux[2];
                    params.put("recibo",recibo[0]);
                    params.put("fecha1", fecha1);
                    params.put("fecha2", fecha2);
                    if(recibo[0].equalsIgnoreCase("Completados")){
                        JasperCompileManager.compileReportToFile(application.getRealPath("/report/resoallcompletados.jrxml"));
                        File reportFile = new File(application.getRealPath("/report/resoallcompletados.jasper"));
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
                            }else{
                                //JasperRunManager.runReportToHtmlFile(reportFile.getPath(),params,conn);
                                JasperPrint jasperPrint = JasperFillManager.fillReport(reportFile.getPath(),params,conn);
                                JRHtmlExporter exporter = new JRHtmlExporter();
                                OutputStream ouputStream = response.getOutputStream();

                                request.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrint);
                                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
                                exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI,request.getContextPath()+"/jasperImages?image=");  
                                exporter.exportReport();
                            }
                        }
                    }else{
                        JasperCompileManager.compileReportToFile(application.getRealPath("/report/resoallanulados.jrxml"));
                        File reportFile = new File(application.getRealPath("/report/resoallanulados.jasper"));
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
                            }else{
                                //JasperRunManager.runReportToHtmlFile(reportFile.getPath(),params,conn);
                                JasperPrint jasperPrint = JasperFillManager.fillReport(reportFile.getPath(),params,conn);
                                JRHtmlExporter exporter = new JRHtmlExporter();
                                OutputStream ouputStream = response.getOutputStream();

                                request.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrint);
                                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
                                exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI,request.getContextPath()+"/jasperImages?image=");  
                                exporter.exportReport();
                            }
                        }
                    }
                }
                if(responsable.equalsIgnoreCase("Todos")&&(recibo.length == 2)){
                    Map params = new HashMap();
                    String[] aux = fecha1.split("/");
                    aux[0] = aux[0].trim();aux[1] = aux[1].trim();aux[2] = aux[2].trim();
                    fecha1 = aux[1]+"/"+aux[0]+"/"+aux[2];
                    aux = fecha2.split("/");
                    aux[0] = aux[0].trim();aux[1] = aux[1].trim();aux[2] = aux[2].trim();
                    fecha2 = aux[1]+"/"+aux[0]+"/"+aux[2];
                    params.put("fecha1", fecha1);
                    params.put("fecha2", fecha2);
                    JasperCompileManager.compileReportToFile(application.getRealPath("/report/resoallrecibosall.jrxml"));
                    File reportFile = new File(application.getRealPath("/report/resoallrecibosall.jasper"));
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
                        }else{
                            //JasperRunManager.runReportToHtmlFile(reportFile.getPath(),params,conn);
                            JasperPrint jasperPrint = JasperFillManager.fillReport(reportFile.getPath(),params,conn);
                            JRHtmlExporter exporter = new JRHtmlExporter();
                            OutputStream ouputStream = response.getOutputStream();

                            request.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrint);
                            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
                            exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI,request.getContextPath()+"/jasperImages?image=");  
                            exporter.exportReport();
                        }
                    }
                }
                if((!responsable.equalsIgnoreCase("Todos"))&&(recibo.length == 2)&&(!responsable.equalsIgnoreCase("Ninguno"))){
                    Map params = new HashMap();
                    String[] aux = fecha1.split("/");
                    aux[0] = aux[0].trim();aux[1] = aux[1].trim();aux[2] = aux[2].trim();
                    fecha1 = aux[1]+"/"+aux[0]+"/"+aux[2];
                    aux = fecha2.split("/");
                    aux[0] = aux[0].trim();aux[1] = aux[1].trim();aux[2] = aux[2].trim();
                    fecha2 = aux[1]+"/"+aux[0]+"/"+aux[2];
                    params.put("fecha1", fecha1);
                    params.put("fecha2", fecha2);
                    params.put("responsable", responsable);
                    JasperCompileManager.compileReportToFile(application.getRealPath("/report/resprecibosall2.jrxml"));
                    File reportFile = new File(application.getRealPath("/report/resprecibosall2.jasper"));
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
                        }else{
                            //JasperRunManager.runReportToHtmlFile(reportFile.getPath(),params,conn);
                            JasperPrint jasperPrint = JasperFillManager.fillReport(reportFile.getPath(),params,conn);
                            JRHtmlExporter exporter = new JRHtmlExporter();
                            OutputStream ouputStream = response.getOutputStream();

                            request.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrint);
                            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
                            exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI,request.getContextPath()+"/jasperImages?image=");  
                            exporter.exportReport();
                        }
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