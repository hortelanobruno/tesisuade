<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta http-equiv="Content-Style-Type" content="text/css">
        <meta http-equiv="Content-Script-Type" content="text/javascript">

        <title>jdsharp.us &raquo; The Personal Site of Jonathan Sharp </title>
        <link rel="stylesheet" href="../styles/style.css" type="text/css" />
        <link rel="stylesheet" href="../styles/jdMenu.css" type="text/css" />
        <link rel="stylesheet" href="../styles/jdMenu.jdsharp.css" type="text/css" />

        <script src="../scripts/jquery-1.2.6.js" type="text/javascript"></script>
        <script type="text/javascript" src="../scripts/jquery.bgiframe.js"></script>
        <script type="text/javascript" src="../scripts/jquery.dimensions.js"></script>
        <script type="text/javascript" src="../scripts/jquery.jdMenu.js"></script>
        <script type="text/javascript" src="../scripts/jdsharp.js"></script>


        <link rel="stylesheet" href="../styles/ui.tabs.css" type="text/css" media="print, projection, screen">

        <script src="../scripts/ui.core.js" type="text/javascript"></script>
        <script src="../scripts/ui.tabs.js" type="text/javascript"></script>
        <script type="text/javascript">
            $(function() {
                $('#container-1 > ul').tabs();
                $('#container-1 > ul').tabs("disable", 1);
            });
        </script>
        <style type="text/css" media="screen, projection">
            /* Not required for Tabs, just to make this demo look better... */
            body, h1, h2 {
                font-family: "Trebuchet MS", Trebuchet, Verdana, Helvetica, Arial, sans-serif;
            }
            h1 {
                margin: 1em 0 1.5em;
                font-size: 18px;
            }
            h2 {
                margin: 2em 0 1.5em;
                font-size: 16px;
            }
            p {
                margin: 0;
                padding: 0 0 .5em;
                max-width: 40em;
            }
        </style>
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
            <div id="contentWrap">
                <div id="sidebar">

                </div>
                <div id="content">
                    <div id="container-1">
                        <ul>
                            <li><a href="#fragment-1"><span>One</span></a></li>
                            <li><a href="#fragment-2"><span>Two</span></a></li>
                            <li><a href="#fragment-3"><span>Three</span></a></li>
                        </ul>
                        <div id="fragment-1">
                            <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.</p>
                        </div>
                        <div id="fragment-2">
                            <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.</p>

                            <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.</p>
                        </div>
                        <div id="fragment-3">
                            <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.</p>
                            <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.</p>
                            <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.</p>
                        </div>

                    </div>
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
