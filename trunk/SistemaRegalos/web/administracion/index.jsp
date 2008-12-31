<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Incorporated 1.0 by FreeCSSTemplates.org</title>
        <link rel="stylesheet" type="text/css" href="../styles/style.css" media="screen" />
        <link rel="stylesheet" href="../styles/lavalamp_test.css" type="text/css" media="screen">
        <script type="text/javascript" src="../scripts/jquery-1.2.3.min.js"></script>
        <script type="text/javascript" src="../scripts/jquery.easing.min.js"></script>
        <script type="text/javascript" src="../scripts/jquery.lavalamp.min.js"></script>
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
    </head>
    <body>
        <%@ include file="top.jsp" %>
        
            <div id="content">
                <div class="post">
                    <h1 class="title">Welcome to our website </h1>
                    <p class="byline"><small>Posted by FreeCssTemplates</small></p>
                    <div class="entry">
                        <p><strong>Incorporated 1.0</strong> is a free template from <a href="http://www.freecsstemplates.org/">Free CSS Templates</a> released under a <a href="http://creativecommons.org/licenses/by/2.5/">Creative Commons Attribution 2.5 License</a>. You're free to use this template for both commercial or personal use. I only ask that you link back to <a href="http://www.freecsstemplates.org/">my site</a> in some way. Enjoy :)</p>
                    </div>
                    <div class="meta">
                        <p class="links"><a href="#" class="comments">Comments (32)</a> &nbsp;&bull;&nbsp;&nbsp; <a href="#" class="more">Read full post &raquo;</a></p>
                    </div>
                </div>
            </div>

        <%@ include file="left.jsp" %>

        <%@ include file="botton.jsp" %>
            
        
    </body>
</html>
