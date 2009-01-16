<%@ taglib prefix="s" uri="/struts-tags" %>
<s:head theme="ajax" />
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Test Brunoli</h1>
        <s:form action="Prueba" >
            <s:textfield label="User Name" name="username" required="true" requiredposition="right" />
            <s:password label="Password" name="password" required="true" requiredposition="right" />
            <s:textfield label="Precio" name="precio" required="true" requiredposition="right" />
            <s:datetimepicker name="fecha1" label="Fecha" displayFormat="MM/dd/yyyy" required="true" requiredposition="right" />
            <s:submit/>
        </s:form>
        <s:property value="username" />
    </body>
</html>
