<%@ page contentType="text/html; charset=ISO-8859-1" language="java" import="java.sql.*,java.io.*, java.util.*" errorPage="" %>
<%@ page import="db.DBManager,vo.CategoriaVO,vo.MercaderiaVO" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%
DBManager manager = DBManager.getInstance();
List<MercaderiaVO> categorias = manager.getMercaderias();
String mercaderia = request.getParameter("mercaderia");
if (mercaderia != null) {
    String resultado = manager.removeMercaderia(mercaderia);
    if (resultado != null) {
        if (resultado == "ok") {
            response.sendRedirect("eliminarmercaderiaok.jsp");
        }else{
            response.sendRedirect("error.jsp");
        }
    }else{
        response.sendRedirect("error.jsp");
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
        <script src="../SpryAssets/SpryValidationSelect.js" type="text/javascript"></script>
        <script type="text/javascript">
            $(function() {
                $("#1, #2, #3").lavaLamp({
                    fx: "backout",
                    speed: 700,
                    click: function(event, menuItem) {
                        return true;
                    }
                });
            });
        </script>
        <link href="../SpryAssets/SpryValidationSelect.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <%@ include file="top.jsp" %>

        <div id="content">
            <div class="post">
                <h1 class="title">Eliminar mercaderia</h1>
                <form name="form1" method="post" action="eliminarmercaderia.jsp">
                    <table>
                        <tr>
                            <td>Mercaderia:</td>
                            <td>
                                <span id="spryMercaderia">
                                    <label>
                                        <select name="mercaderia" id="mercaderia">
                                        <%
                                        for (int i = 0; i < categorias.size(); i++) {
                                            out.print("<option value=" + categorias.get(i).getId() + ">" + categorias.get(i).getTipo() + "</option>");
                                        }
                                        %>
                                        </select>
                                    </label>
                                <span class="selectInvalidMsg">Please select a valid item.</span>                                <span class="selectRequiredMsg">Please select an item.</span></span>
                            </td>
                        </tr>
                        <tr>
                            <td height="50" colspan="2" valign="bottom"><input name="cargar" type="submit" value="Eliminar Mercaderia">
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>

        <%@ include file="left.jsp" %>

        <%@ include file="botton.jsp" %>


        <script type="text/javascript">
            <!--
            var spryselect1 = new Spry.Widget.ValidationSelect("spryMercaderia", {validateOn:["change", "blur"], invalidValue:"-1"});
            //-->
        </script>
    </body>
</html>
