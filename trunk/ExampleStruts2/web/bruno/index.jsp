<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="SpryAssets/SpryValidationTextField.js" type="text/javascript"></script>
    <link href="SpryAssets/SpryValidationTextField.css" rel="stylesheet" type="text/css">
</head>
    <body>
    <h1>Test Brunoli</h1>
    <form name="form1" method="post" action="">
      <span id="sprytextfield1">
        <label>
          <input type="text" name="user" id="user">
          
      </label>
        <span class="textfieldRequiredMsg">A value is required.</span></span>
      <br>
        <span id="sprytextfield2">
        <label>
        <input type="password" name="pass" id="pass">
        </label>
        <span class="textfieldRequiredMsg">A value is required.</span></span>
    </form>
    <script type="text/javascript">
<!--
var sprytextfield1 = new Spry.Widget.ValidationTextField("sprytextfield1", "none", {validateOn:["blur", "change"]});
var sprytextfield2 = new Spry.Widget.ValidationTextField("sprytextfield2", "none", {validateOn:["blur", "change"]});
//-->
</script>
</body>
</html>
