<%@ taglib prefix="s" uri="/struts-tags" %>
<s:head theme="ajax" />
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form id="Prueba" name="Prueba" onsubmit="return true;" action="/ExampleStruts2/Prueba.action" method="post">
        <table class="wwFormTable">
            <tr>
                <td class="tdLabel"><label for="Prueba_username" class="label">User Name<span class="required">*</span>:</label></td>
                <td><input type="text" name="username" value="" id="Prueba_username"/></td>
            </tr>
            <tr>
                <td class="tdLabel"><label for="Prueba_password" class="label">Password<span class="required">*</span>:</label></td>
                <td><input type="password" name="password" id="Prueba_password"/></td>
            </tr>
            <tr>
                <td class="tdLabel"><label for="Prueba_precio" class="label">Precio<span class="required">*</span>:</label></td>
                <td><input type="text" name="precio" value="" id="Prueba_precio"/></td>
            </tr>
            <tr>
                <td class="tdLabel"><label for="Prueba_fecha1" class="label">Fecha<span class="required">*</span>:</label></td>
                <td><script type="text/javascript">
                    dojo.require("dojo.widget.DatePicker");

                    </script>
                <div dojoType="dropdowndatepicker"    id="Prueba_fecha1"    name="dojo.fecha1"    inputName="fecha1"    displayFormat="MM/dd/yyyy"  saveFormat="rfc"></div></td>
            </tr>
            <tr>
                <td colspan="2"><div align="right"><input type="submit" id="Prueba_0" value="Submit"/>
                </div></td>
            </tr>
        </table></form>
    </body>
</html>
