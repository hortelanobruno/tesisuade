<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
        <title>jdsharp.us &raquo; The Personal Site of Jonathan Sharp </title>
        <link rel="shortcut icon" href="/favicon.ico" />
        <link rel="stylesheet" href="../styles/style.css" type="text/css" />
        <link rel="stylesheet" href="../styles/jdMenu.css" type="text/css" />
        <link rel="stylesheet" href="../styles/jdMenu.jdsharp.css" type="text/css" />

        <script type="text/javascript" src="../scripts/jquery-1.1.2.js"></script>
        <script type="text/javascript" src="../scripts/jquery.bgiframe.js"></script>
        <script type="text/javascript" src="../scripts/jquery.dimensions.js"></script>
        <script type="text/javascript" src="../scripts/jquery.jdMenu.js"></script>
        <script type="text/javascript" src="../scripts/jdsharp.js"></script>
    </head>

    <body>
        <div id="antiFooter">
            <div id="header">
                <div id="logo">
                    <img id="jdsharp" src="../images/site-jdsharp.png" style="width: 439px; height: 36px;" />
                </div>
                <ul class="jd_menu jd_menu_jdsharp">
                    <li><a href="/">Home</a></li>
                    <li><a href="#" class="accessible">My jQuery Plugins</a>
                        <ul>
                            <li><a href="/jQuery/plugins/AutoScroll/">AutoScroll</a></li>
                            <li><a href="/jQuery/plugins/jdMenu/">jdMenu</a></li>
                            <li><a href="/jQuery/plugins/jdNewsScroll/">jdNewsScroll</a></li>
                        </ul>
                  </li>
                    <li><a href="#" class="accessible">jQuery Minute&trade;</a>
                        <ul>
                            <li><a href="http://jqueryminute.com/blog/">jQuery Minute Blog</a></li>
                            <li><a href="/jQuery/minute/calculate-scrollbar-width.php">Calculate Scrollbar Width</a></li>
                        </ul>
                  </li>
                    <li><a href="/contact.php">Contact</a></li>
                </ul>
            </div>
            <div id="contentWrap" align="center">
                <div id="content">
                    <h1>Welcome to jdsharp.us!</h1>
                    <script type="text/javascript">
                        $(document).ready(function(){
                            $(".agregar").slideToggle("normal");
                        });
                        function agregarRow(){
                            if ($(".agregar").is(":hidden")) {
                                $(".agregar").empty();
                                $(".agregar").append("<h2>Agregar row</h2><input type='text' id='col1' title='Type something' /><br>\n\
                                    <input type='text' id='col2' /><br>\n\
                                <input type='button' value='OK' onclick='aceptarRow()'>\n\
                                <input type='button' value='Cancel' onclick='cancelRow()'>");
                                $(".agregar").slideToggle("normal");
                            }else{
                                $(".agregar").empty();
                                $(".agregar").append("<h2>Agregar row</h2><input type='text' id='col1' /><br>\n\
                                    <input type='text' id='col2' /><br>\n\
                                <input type='button' value='OK' onclick='aceptarRow()'>\n\
                                <input type='button' value='Cancel' onclick='cancelRow()'>");
                            }
                        }
                        function modificarRow(){
                            if ($(".agregar").is(":hidden")) {
                                $(".agregar").empty();
                                var col1 =$("input:checked").parent().parent().children("td").get(1).innerHTML;
                                var col2 =$("input:checked").parent().parent().children("td").get(2).innerHTML;
                                $(".agregar").append("<h2>Modificar row</h2><input type='text' id='col1'value='"+col1+"' /><br>\n\
                                <input type='text' id='col2' value='"+col2+"' /><br>\n\
                            <input type='button' value='OK' onclick='aceptarModRow()'>\n\
                            <input type='button' value='Cancel' onclick='cancelRow()'>");
                                $(".agregar").slideToggle("normal");
                            }else{
                                if($("input").is(":checked")){
                                    $(".agregar").empty();
                                    var col1 =$("input:checked").parent().parent().children("td").get(1).innerHTML;
                                    var col2 =$("input:checked").parent().parent().children("td").get(2).innerHTML;
                                    $(".agregar").append("<h2>Modificar row</h2><input type='text' id='col1'value='"+col1+"' /><br>\n\
                                    <input type='text' id='col2' value='"+col2+"' /><br>\n\
                                    <input type='button' value='OK' onclick='aceptarModRow()'>\n\
                                    <input type='button' value='Cancel' onclick='cancelRow()'>");
                                }else{
                                    cancelRow();
                                }
                            }
                        }
                        function removerRow(){
                            $("input:checked").parent().parent().remove();
                        }
                        function aceptarRow(){
                            var a1 = $("#col1").val();
                            var a2 = $("#col2").val();
                            $("tbody").append("<tr class='even_row'><td><input type='radio' name='checked' /></td><td>"+a1+"</td><td>"+a2+"</td></tr>");
                            cancelRow();
                        }
                        function aceptarModRow(){
                            var a1 = $("#col1").val();
                            var a2 = $("#col2").val();
                            $("input:checked").parent().parent().children("td").get(1).innerHTML = a1;
                            $("input:checked").parent().parent().children("td").get(2).innerHTML = a2;
                            cancelRow();
                        }
                        function cancelRow(){
                            $(".agregar").slideToggle("normal");
                            $(".agregar").empty();
                        }
                    </script>
                    <input type="button" value="Agregar" onclick="agregarRow()">
                    <input type="button" value="Modificar" onclick="modificarRow()">
                    <input type="button" value="Remover" onclick="removerRow()">
                    <br>
                    <br>
                    <div class="agregar" id="about">
                    </div>
                    <br>
                    <table width="300px" border="0" cellspacing="0" cellpadding="0" class="jdsharp_filetable">
                        <thead>
                            <tr>
                                <th></th>
                                <th>Columna 1</th>
                                <th>Columna 2</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr class="even_row">
                                <td><input type="radio" name="checked" /></td>
                                <td>Bruno 1</td>
                                <td>Bruno 11</td>
                            </tr>
                            <tr class="even_row">
                                <td><input type="radio" name="checked" /></td>
                                <td>Bruno 2</td>
                                <td>Bruno 21</td>
                            </tr>
                            <tr class="even_row">
                                <td><input type="radio" name="checked" /></td>
                                <td>Bruno 3</td>
                                <td>Bruno 31</td>
                            </tr>
                        </tbody>
                    </table>
                </div>

            </div>

        </div>
        <div id="footer">
            <div class="copy">
                <div class="right">
                    <!--
            Page Last Modified: 2007-03-22
            -->
                </div>
                &copy;2005-2007 Jonathan Sharp - All Rights Reserved.            </div>
        </div>
</body>
</html>
