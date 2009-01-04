<%@ page contentType="text/html; charset=ISO-8859-1" language="java" import="java.sql.*,java.io.*, java.util.*" errorPage="" %>
<%@ page import="db.DBManager,vo.CategoriaVO,vo.MercaderiaVO" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%
DBManager manager = DBManager.getInstance();
List<CategoriaVO> categorias = manager.getCategorias();
String stock = request.getParameter("stock");
if (stock != null) {
    String categoria = request.getParameter("categoria");
    String tipo = request.getParameter("tipo");
    String detalle = request.getParameter("detalle");
    MercaderiaVO mercaderia = new MercaderiaVO();
    mercaderia.setCategoria(Integer.parseInt(categoria));
    mercaderia.setDetalle(detalle);
    mercaderia.setStock(Integer.parseInt(stock));
    mercaderia.setTipo(tipo);
    String resultado = manager.createMercaderia(mercaderia);
    if (resultado != null) {
        if (resultado == "ok") {
            response.sendRedirect("nuevamercaderiaok.jsp");
        }else{
            response.sendRedirect("error.jsp");
        }
    }
}
%>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Incorporated 1.0 by FreeCSSTemplates.org</title>
        <link rel="stylesheet" type="text/css" href="../styles/style.css" media="screen" />
        <link rel="stylesheet" href="../styles/lavalamp_test.css" type="text/css" media="screen">
        <script type="text/javascript" src="../scripts/jquery-1.2.3.min.js"></script>
        <script type="text/javascript" src="../scripts/jquery.easing.min.js"></script>
        <script type="text/javascript" src="../scripts/jquery.lavalamp.min.js"></script>
        <script src="../SpryAssets/SpryValidationTextField.js" type="text/javascript"></script>
        <script src="../SpryAssets/SpryValidationSelect.js" type="text/javascript"></script>
        <link href="../SpryAssets/SpryValidationTextField.css" rel="stylesheet" type="text/css">
        <link href="../SpryAssets/SpryValidationSelect.css" rel="stylesheet" type="text/css">
</head>
    <body>
        <%@ include file="top.jsp" %>

        <div id="content">
            <div class="post">
                <h1 class="title">Nueva Mercaderia </h1>
                <form name="form1" method="post" action="nuevamercaderia.jsp">
                    <table>
                        <tr>
                            <td>Categoria:</td>
                            <td><span id="sprCategoria">
                                    <label>
                                        <select name="categoria" id="categoria">
                                            <%
                                            for (int i = 0; i < categorias.size(); i++) {
                                                out.print("<option value=" + categorias.get(i).getId() + ">" + categorias.get(i).getTipo() + "</option>");
                                            }
                                            %>
                                        </select>
                                    </label>
                            <span class="selectRequiredMsg">Debe seleccionar un item.</span></span></td>
                        </tr>
                        <tr>
                            <td>Tipo: </td>
                            <td><span id="spryTipo">
                                    <label>
                                        <input type="text" name="tipo" id="tipo">
                                    </label>
                            <span class="textfieldRequiredMsg">Un valor es requerido.</span></span></td>
                        </tr>
                        <tr>
                            <td>Detalle:</td>
                            <td><span id="spryDetalle">
                                    <label>
                                        <input type="text" name="detalle" id="detalle">
                                    </label>
                            <span class="textfieldRequiredMsg">Un valor es requerido.</span></span></td>
                        </tr>
                        <tr>
                        	<td>Stock:</td>
                        <td><span id="spryStock">
                        <label>
                        <input type="text" name="stock" id="stock">
                        </label>
                        <span class="textfieldRequiredMsg">Un valor es requerido.</span><span class="textfieldInvalidFormatMsg">Formato invalido.</span></span></td>
                      </tr>
                        <tr>
                            <td height="55" colspan="2" valign="bottom"><input name="cargar" type="submit" value="Cargar Mercaderia" /></td>
                      </tr>
                    </table>
                </form>
            </div>
        </div>

        <%@ include file="left.jsp" %>

        <%@ include file="botton.jsp" %>


        <script type="text/javascript">
<!--
var sprytextfield1 = new Spry.Widget.ValidationTextField("spryTipo", "none", {validateOn:["blur", "change"]});
            var sprytextfield2 = new Spry.Widget.ValidationTextField("spryDetalle", "none", {validateOn:["blur", "change"]});
            var spryselect1 = new Spry.Widget.ValidationSelect("sprCategoria", {validateOn:["change", "blur"]});
var sprytextfield3 = new Spry.Widget.ValidationTextField("spryStock", "integer", {validateOn:["blur", "change"]});
//-->
</script>
    </body>
</html>
