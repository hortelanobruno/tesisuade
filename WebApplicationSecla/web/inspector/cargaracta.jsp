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
        String monto = request.getParameter("monto");
        if (monto != null) {
            String fechaConfeccion = request.getParameter("date");
            String razonSocial = request.getParameter("beneficiario");
            String numero = request.getParameter("recibos");
            String ncuota1 = request.getParameter("numerocuota1");
            String ncuota2 = request.getParameter("numerocuota2");
            String cuota = ncuota1+"/"+ncuota2;
            String tipopago = request.getParameter("tipopago");
            String numeroacta = request.getParameter("numeroacta");
            Recibo recibo = null;
            String resultado = null;
            if(tipopago.equalsIgnoreCase("efectivo")){
                recibo = new Recibo(Integer.parseInt(numero),fechaConfeccion,razonSocial,monto,cuota);
                recibo.setNumeroacta(numeroacta);
                resultado = manager.completarReciboEfectivoPorOperador(recibo);
            }else{
                String banco = request.getParameter("banco");
                String cheque = request.getParameter("numerocheque");
                String fechaVencimiento = request.getParameter("date2");
                recibo = new Recibo(Integer.parseInt(numero),fechaConfeccion,razonSocial,monto,cuota,banco,cheque,fechaVencimiento);
                recibo.setNumeroacta(numeroacta);
                resultado = manager.completarReciboChequePorInspector(recibo);
            }
            if (resultado != null) {
                if (resultado == "true") {
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
        <script language="javascript" type="text/javascript" src="../js/ajax.js"></script>
        <script language="javascript" type="text/javascript" src="../js/script.js"></script>
        <script language="javascript" type="text/javascript" src="../js/calendar.js" ></script>
        <script language="javascript" type="text/javascript" src="../js/calendar-es.js" ></script>
        <script language="javascript" type="text/javascript" src="../js/calendar-setup.js"></script>
        <script src="../SpryAssets/SpryValidationTextField.js" type="text/javascript"></script>
        <link href="../SpryAssets/SpryValidationTextField.css" rel="stylesheet" type="text/css" />
        </head>
    <body>
        <script type="text/javascript">
            window.onload = function() {
                Calendar.setup({
                    inputField: "fecha",
                    ifFormat:   "%d / %m / %Y",
                    button:     "selector"
                });
                pagoEfectivo();
            }
        </script>
        <%@ include file="top.jsp" %>

        <%@ include file="left.jsp" %>

        <td width="50"></td>
        <td width="595" colspan="4" valign="top">	<p>&nbsp;</p>
            <table border="0" cellspacing="0" cellpadding="0" width="595">
                <tr>
                    <td class="pageName"><h1>Cargar acta</h1>
                    <p>&nbsp;</p></td>
                </tr>

                <tr>
                    <td class="bodyText">
                        <form method="post" action="boletaacompletar.jsp" name="form1" target="_parent">
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
                                              <td width="100px">Numero acta</td>
                                                <td height="30px" align="center"><span id="tfNumeroActa">
                                                <label>
                                                <input type="text" name="numeroActa" id="numeroActa" />
                                                </label>
                                                <span class="textfieldRequiredMsg">A value is required.</span><span class="textfieldInvalidFormatMsg">Invalid format.</span></span> </td>
                                          </tr>
                                          <tr><td>Fecha inicio</td>
                                          <td></td>
                                          </tr>
                                          <tr><td>Codigo</td>
                                          <td><span id="tfCodigo">
                                          <label>
                                          <input type="text" name="codigo" id="codigo" />
                                          </label>
                                          <span class="textfieldRequiredMsg">A value is required.</span></span></td>
                                          </tr>
                                          <tr><td>Razon social</td>
                                        <td><span id="tfRazonSocial">
                                            <label>
                                            <input type="text" name="razonSocial" id="razonSocial" />
                                            </label>
                                            <span class="textfieldRequiredMsg">A value is required.</span></span></td>
                                          </tr>
                                          <tr><td>Domicilio</td>
                                          <td><span id="tfDomicilio">
                                            <label>
                                            <input type="text" name="domicilio" id="domicilio" />
                                            </label>
                                            <span class="textfieldRequiredMsg">A value is required.</span></span></td>
                                          </tr>
                                          <tr><td></td>
                                          <td></td>
                                          </tr>
                                    </table></td>
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
        <script type="text/javascript">
<!--
var sprytextfield1 = new Spry.Widget.ValidationTextField("tfNumeroActa", "integer", {validateOn:["blur", "change"]});
var sprytextfield2 = new Spry.Widget.ValidationTextField("tfCodigo", "none", {validateOn:["blur", "change"]});
var sprytextfield3 = new Spry.Widget.ValidationTextField("tfRazonSocial", "none", {validateOn:["blur", "change"]});
var sprytextfield4 = new Spry.Widget.ValidationTextField("tfDomicilio", "none", {validateOn:["blur", "change"]});
//-->
</script>
</body>
</html>
