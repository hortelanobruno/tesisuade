<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <title>Hello World!</title>
    </head>
    <body>
        <h2><s:property value="message"/></h2>
        <h3>Languages</h3>
        <ul>
            <li>
                <s:url id="url" action="Welcome">
                    <s:param name="request_locale">en</s:param>
                </s:url>
                <s:a href="%{url}">English</s:a>
            </li>
            <li>
                <s:url id="url" action="Welcome">
                    <s:param name="request_locale">es</s:param>
                </s:url>
                <s:a href="%{url}">Espanol</s:a>
            </li>
        </ul>
    </body>
</html>