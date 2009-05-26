<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Incorporated 1.0 by FreeCSSTemplates.org</title>
        <link rel="stylesheet" type="text/css" href="styles/style.css" media="screen" />
        <link rel="stylesheet" href="styles/lavalamp_test.css" type="text/css" media="screen">
        <script type="text/javascript" src="scripts/jquery-1.2.3.min.js"></script>
        <script type="text/javascript" src="scripts/jquery.easing.min.js"></script>
        <script type="text/javascript" src="scripts/jquery.lavalamp.min.js"></script>
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
        <div id="header">
            <div id="logo">
                <h1><a href="#">Sistema Regalos</a></h1>
                <p>Secretaria La Mujer - SECLA</p>
            </div>
            <!-- end #logo -->
            <div id="menu">
              <ul class="lavaLampWithImage" id="1">
                <li class="current"><a href="index.jsp">Inicio</a></li>
                <li><a href="administracion" >Administracion</a></li>
                <li><a href="entrega">Entrega</a></li>
                <li><a href="confirmacion">Confirmacion</a></li>
              </ul>
            </div>
          <!-- end #menu -->
        </div>
        <!-- end #header -->
        <div id="page">
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
                <div class="post">
                    <h2 class="title">Lorem Ipsum Dolor Volutpat</h2>
                    <p class="byline"><small>Posted by FreeCssTemplates</small></p>
                    <div class="entry">
                        <p>Curabitur tellus. Phasellus tellus turpis, iaculis in, faucibus lobortis, posuere in, lorem. Donec a ante. Donec neque purus, adipiscing id, eleifend a, cursus vel, odio. Vivamus varius justo sit amet leo. Morbi sed libero. Vestibulum blandit augue at mi. Praesent fermentum lectus eget diam. Nam cursus, orci sit amet porttitor iaculis, ipsum massa aliquet nulla, non elementum mi elit a mauris.</p>
                        <ul>
                            <li><a href="#">Magna lacus bibendum mauris</a></li>
                            <li><a href="#">Velit semper nisi molestie</a></li>
                            <li><a href="#">Magna lacus bibendum mauris</a></li>
                            <li><a href="#">Velit semper nisi molestie</a></li>
                        </ul>
                    </div>
                    <div class="meta">
                        <p class="links"><a href="#" class="comments">Comments (32)</a> &nbsp;&bull;&nbsp;&nbsp; <a href="#" class="more">Read full post &raquo;</a></p>
                    </div>
                </div>
            </div>
            <!-- end #content -->
            <div id="sidebar">
                <div id="sidebar-bgtop"></div>
                <div id="sidebar-content">
                    <ul>
                        <li id="search">
                            <h2>Search</h2>
                            <form method="get" action="">
                                <fieldset>
                                    <input type="text" id="s" name="s" value="" />
                                    <input type="submit" id="x" value="Search" />
                                </fieldset>
                            </form>
                        </li>
                        <li>
                            <h2>Lorem Ipsum</h2>
                            <ul>
                                <li><a href="#">Fusce dui neque fringilla</a></li>
                                <li><a href="#">Eget tempor eget nonummy</a></li>
                                <li><a href="#">Magna lacus bibendum mauris</a></li>
                                <li><a href="#">Nec metus sed donec</a></li>
                                <li><a href="#">Magna lacus bibendum mauris</a></li>
                                <li><a href="#">Velit semper nisi molestie</a></li>
                                <li><a href="#">Eget tempor eget nonummy</a></li>
                            </ul>
                        </li>
                        <li>
                            <h2>Volutpat Dolore</h2>
                            <ul>
                                <li><a href="#">Nec metus sed donec</a></li>
                                <li><a href="#">Magna lacus bibendum mauris</a></li>
                                <li><a href="#">Velit semper nisi molestie</a></li>
                                <li><a href="#">Eget tempor eget nonummy</a></li>
                                <li><a href="#">Nec metus sed donec</a></li>
                                <li><a href="#">Magna lacus bibendum mauris</a></li>
                                <li><a href="#">Velit semper nisi molestie</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
                <div id="sidebar-bgbtm"></div>
            </div>
            <!-- end #sidebar -->
        </div>
        <!-- end #page -->
        <div id="footer">
            <p>&copy; 2008. All Rights Reserved. Design by <a href="http://www.freecsstemplates.org/">Free CSS Templates</a>.</p>
        </div>
        <!-- end #footer -->
    </body>
</html>