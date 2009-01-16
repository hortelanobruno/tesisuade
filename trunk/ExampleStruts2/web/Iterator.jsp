<%@ taglib prefix="s" uri="/struts-tags" %>
<s:head theme="ajax" />
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <p/>
        <table border="0" cellspacing="0" cellpadding="1">
            <tr>
                <th>Days of the week</th>
            </tr>
            <p/>
            <s:iterator value="days" status="rowstatus">
                <tr>
                    <s:if test="#rowstatus.odd == true">
                        <td style="background:gray"><s:property/></td>
                    </s:if>
                    <s:else>
                        <td><s:property/></td>
                    </s:else>
                </tr>
            </s:iterator>
        </table>

        
    </body>
</html>
