<%@ page contentType="text/html; charset=ISO-8859-1" language="java" import="java.sql.*,java.io.*, java.util.*" errorPage="" %>
<%@ page import="db.DBManager,varios.Recibo" %>
<%
        Object connectado = session.getAttribute("conectado");
        if (connectado != null) {
            if (connectado.toString().equals("no")) {
                response.sendRedirect("../index.jsp");
            }
        } else {
            response.sendRedirect("../index.jsp");
        }
        String usuario = session.getAttribute("usuario").toString();
        DBManager manager = DBManager.getInstance();
        if (!manager.isConnected()) {
            response.sendRedirect("../index.jsp");
        }
        List<Recibo> recibos = manager.obtenerRecibosAConfirmar(usuario);
        if (recibos.size() == 0) {
            response.sendRedirect("nohayboletas.jsp");
        }

        String monto = request.getParameter("monto");
        String fecha = request.getParameter("date");
        if (fecha != null) {
            if (monto != null) {
                //Recibo Completado
                String beneficiario = request.getParameter("beneficiario");
                String recibo = request.getParameter("recibos");
                String banco = request.getParameter("banco");
                String numerocheque = request.getParameter("numerocheque");
                String fechavencimiento = request.getParameter("date2");
                String numerocuota = request.getParameter("numerocuota1") + "/" + request.getParameter("numerocuota2");
                String numeroacta = request.getParameter("numeroacta");
                Recibo r1 = new Recibo();
                r1.setRazonSocial(beneficiario);
                r1.setNumero(Integer.parseInt(recibo));
                r1.setFechaConfeccion(fecha);
                r1.setMonto(monto);
                r1.setMotivo("");
                r1.setNumeroacta(numeroacta);
                if (banco == null) {
                    r1.setBanco("");
                    r1.setFechaDeVencimiento("");
                    r1.setNumeroCheque("");
                } else {
                    r1.setBanco(banco);
                    r1.setFechaDeVencimiento(fechavencimiento);
                    r1.setNumeroCheque(numerocheque);
                }
                r1.setNumeroCuota(numerocuota);
                String resultado = manager.actualizarRecibo(r1);
                if (resultado != null) {
                    if (resultado == "true") {
                        response.sendRedirect("modificarrecibook.jsp");
                    }
                }
            } else {
                //Recibo anulado
                Recibo r1 = new Recibo();
                String motivo = request.getParameter("motivo");
                String recibo = request.getParameter("recibos");
                r1.setMotivo(motivo);
                r1.setFechaConfeccion(fecha);
                r1.setNumero(Integer.parseInt(recibo));
                r1.setRazonSocial("");
                r1.setMonto("");
                r1.setBanco("");
                r1.setFechaDeVencimiento("");
                r1.setNumeroCheque("");
                r1.setNumeroCuota("");
                String resultado = manager.actualizarRecibo(r1);
                if (resultado != null) {
                    if (resultado == "true") {
                        response.sendRedirect("modificarrecibook.jsp");
                    }
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
        <script language="javascript" type="text/javascript" src="../js/ajax.js"></script>
        <script language="javascript" type="text/javascript" src="../js/script.js"></script>
        <script language="javascript" type="text/javascript" src="../js/calendar.js" ></script>
        <script language="javascript" type="text/javascript" src="../js/calendar-es.js" ></script>
        <script language="javascript" type="text/javascript" src="../js/calendar-setup.js"></script>
    </head>
    <body>
        <%@ include file="top.jsp" %>

        <%@ include file="left.jsp" %>

        <td width="50"></td>
        <td width="595" colspan="4" valign="top">	<p>&nbsp;</p>
            <table border="0" cellspacing="0" cellpadding="0" width="595">
                <tr>
                    <td class="pageName"><h1>Recibo a modificar</h1>
                    <p>&nbsp;</p></td>
                </tr>

                <tr>
                    <td class="bodyText">
                        <form method="post" action="modificarrecibo.jsp" name="form1" target="_parent">
                            <table width="100%" cellpadding="1" cellspacing="5">
                                <tr>
                                    <td colspan="3" align="center" bgcolor="#4D6FAC">
                                        <h3 style="color:#FFFFFF">Datos</h3>
                                    </td>
                                </tr>
                                <tr height="20px"><td></td>
                                </tr>
                                <tr><td>
                                        <table width="100%" cellpadding="1" cellspacing="5">
                                            <tr>
                                                <td>Numero de recibo</td>
                                                <td><select name="recibos" style="width:180px" id="recibos" onchange="verReciboAModificar()" >
                                                        <option value=""></option>
                                                        <%
        for (int i = 0; i < recibos.size(); i++) {
            out.print("<option value=" + recibos.get(i).getNumero() + ">" + recibos.get(i).getNumero() + "</option>");
        }
                                                        %>
                                                </select>&nbsp;&nbsp;&nbsp;&nbsp;<label class="error" id="recibo" style="visibility:hidden"></label></td>
                                            </tr>
                                        </table>
                                        <div id="rec"></div>
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