<html>
<head>
<title>JSP WITH  MULTIPLE FORMS </title>
</head>
<body>

	<table width="100%" border="1px" bordercolor="#000080" cellpadding="0" cellspacing="0" bgcolor="#E2FEFD">
<tr>
	<td><b><font size="5" color="#333399">JSP WITH  MULTIPLE FORMS </font></b></td>
</TR>
<tr>
	<td height="47" valign="top" bgcolor="#E0EDFD">
			<br> 
			&nbsp;<b>
	         <% 
			 if(request.getParameter("FormName") != null)
				{
		  %>
			 You Clicked 
			 <%= request.getParameter("FormName") %>
		     <%
				}
		  %>
		</b>
		
&nbsp;		</td>
</tr>


<tr>
	<td>
			 <FORM NAME="form1" METHOD="POST">
				 <INPUT TYPE="HIDDEN" NAME="FormName" VALUE="Form 1">
				 <br>
				 <INPUT TYPE="image"  SRC="form1.gif">
			 </FORM>

			<FORM NAME="form2" METHOD="POST">
				 <INPUT TYPE="HIDDEN" NAME="FormName" VALUE="Form 2">
				 <INPUT TYPE="image"  SRC="form-2.gif">
			</FORM>

			 <FORM NAME="form3" METHOD="POST">
				 <INPUT TYPE="HIDDEN" NAME="FormName" VALUE="Form 3">
					<INPUT TYPE="image"  SRC="form-3.gif">
			 </FORM>
	</td>
</tr>
</table>
	
</body>

</html>