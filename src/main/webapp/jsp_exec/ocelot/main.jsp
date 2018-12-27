<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: rgalindo
  Date: 17/12/18
  Time: 18:59
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="mainBean" class="com.acceso.wfweb.beans.MainBean"/>
<html>
<head>
    <title>Main</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-amber.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">

    <!--ESTO DEBE IR EN UN CSS APARTE-->
    <style>
        * {
            font-family: "Ubuntu Light"
        }

        DIV.w3-teal {
            position: fixed;
            width: 100%;
        }

        DIV.wf_right {
            position: fixed;
            right: 0px;
            display: block;
            top: 5px;
        }

        .main_title_module {
            margin: 0px;
            color: white;
            font-size: 26px;
        }

        .main_title_breadcrumbs {
            margin: 0px;
            padding: 0px;
            color: white;
            font-size: 14px;
        }

        .main_margin_etop {
            padding-top: 70px;
        }

        @media (min-width: 993px) {
            .w3-modal-content {
                width: 900px
            }

            .w3-hide-large {
                display: none !important;
                /*display: unset;*/
            }

            .w3-sidebar.w3-collapse {
                /*display: block !important*/
                /*display: none !important*/
                display: unset;
            }
        }

        .wf4_iframe {
            width: 100%;
            height: 100%;
            display: block;
            border: none;
        }
    </style>

    <script>
        function w3_open() {
            document.getElementById("mySidebar").style.display = "block";
            document.getElementById("myOverlay").style.display = "block";
        }

        function w3_close() {
            document.getElementById("mySidebar").style.display = "none";
            document.getElementById("myOverlay").style.display = "none";
        }

        function viewtab(evt, cityName) {
            var i, x, tablinks;
            x = document.getElementsByClassName("menubloq");
            for (i = 0; i < x.length; i++) {
                x[i].style.display = "none";
            }
            tablinks = document.getElementsByClassName("tablink");
            for (i = 0; i < x.length; i++) {
                tablinks[i].className = tablinks[i].className.replace(" w3-red", "");
            }
            document.getElementById(cityName).style.display = "block";
            evt.currentTarget.className += " w3-red";
        }
    </script>

</head>
<body>

<!-- Sidebar -->
<%--<div class="w3-sidebar w3-collapse w3-white w3-animate-left w3-large" style="z-index:3;width:300px;" id="mySidebar">--%>
<div class="w3-sidebar w3-white w3-animate-left w3-large" style="z-index:3;width:300px; display: none;" id="mySidebar">

    <div class="w3-bar w3-black w3-center">
        <button class="w3-bar-item w3-button tablink w3-red" style="width:50%" onclick="viewtab(event,'menu64')">Menú
        </button>
        <button class="w3-bar-item w3-button tablink" style="width:50%" onclick="viewtab(event,'sistemas64')">Sistemas
        </button>
    </div>

    <div id="menu64" class="w3-bar-block menubloq">
        <a class="w3-bar-item w3-button w3-border-bottom w3-large" href="#">
            <%--<img src="https://www.w3schools.com/images/w3schools.png" style="width:80%;">--%>
        </a>

        <ul class="w3-ul w3-large">
            <c:forEach var="menu" items="${US.mainMenu.menu}">

                <li class="w3-padding-16" style="font-size: 14px;">

                <c:if test="${not empty menu.url}">
                    <a href="${menu.url}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${menu.name}</a>
                    </li>
                </c:if>

                <c:if test="${empty menu.url}">
                    <span style="color:green;">${menu.name}</span>
                    </li>

                    <c:forEach var="menuitem" items="${menu.sub}">
                        <li class="w3-padding-16" style="font-size: 14px;">

                        <c:if test="${not empty menuitem.url}">
                            <a href="${menuitem.url}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${menuitem.name}</a>
                            </li>
                        </c:if>

                        <c:if test="${empty menuitem.url}">
                            <span style="color: blue;">${menuitem.name}</span>
                            </li>

                            <c:forEach var="menuitem2" items="${menuitem.sub}">
                                <li class="w3-padding-16" style="font-size: 14px;">

                                <c:if test="${not empty menuitem2.url}">
                                    <a href="${menuitem2.url}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${menuitem2.name}</a>
                                    </li>
                                </c:if>

                                <c:if test="${empty menuitem2.url}">
                                    <span style="color: orange;">${menuitem2.name}</span>
                                    </li>
                                </c:if>
                            </c:forEach>
                        </c:if>

                    </c:forEach>
                </c:if>
            </c:forEach>
        </ul>
    </div>

    <div id="sistemas64" class="menubloq" style="display: none;">

        <ul class="w3-ul w3-large ${US}-${US.root}">
            <c:forEach var="sistema" items="${US.root.sistemas}">

                <li class="w3-padding-16" style="font-size: 14px;">
                        ${sistema.no_sistem}
                </li>

                <c:forEach var="sub_sistema" items="${sistema.subsistemas}">

                    <li class="w3-padding-16" style="font-size: 16px;color:red;">
                        &nbsp;&nbsp;&nbsp;${sub_sistema.no_subsis}
                    </li>

                    <c:forEach var="paquete" items="${sub_sistema.paquetes}">
                        <li class="w3-padding-16">
                            <a href="#" style="font-size: 14px;color:blue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;>${paquete.no_paquet}</a>
                        </li>
                    </c:forEach>

                </c:forEach>

            </c:forEach>
        </ul>
    </div>
</div>

<div class="w3-overlay " onclick="w3_close()" style="cursor:pointer" id="myOverlay"></div>

<div class="w3-row w3-padding w3-teal w3-xlarge">
    <div class="w3-full">
        <table>
            <tr>
                <td>
                    <button class="w3-button w3-teal w3-xlarge" onclick="w3_open()">☰</button>
                </td>
                <td>
                    <div><h1 class="main_title_module">Nombre del módulo</h1></div>
                    <div class="main_title_breadcrumbs">/ruta/completa/del/modulo</div>
                </td>
            </tr>
        </table>
    </div>


    <div class="wf_right w3-quarter">
        <div class="w3-bar w3-xlarge">
            <div class="w3-bar-item w3-button">${US.no_usulog}</div>
            <a href="#" class="w3-bar-item w3-button w3-right">
                <img class="w3-hide-small w3-circle" src="${US.no_imgusu}"
                     style="height:40px;"/>
            </a>
        </div>

    </div>
</div>

<div class="w3-content ">
    <div class="main_margin_etop" style="background:#ededed;color:white;">

        <div class="w3-row" style="height: 30px;"></div>

        <div class="w3-row">
            <div class="w3-quarter" style="background:red;height: 200px;">
                <iframe src="/jsp_exec/ocelot/pagina.jsp?rise=123456" class="wf4_iframe"></iframe>
            </div>
            <div class="w3-quarter" style="background:green;height: 200px;">1/4</div>
            <div class="w3-quarter" style="background:blue;height: 200px;">1/4</div>
            <div class="w3-quarter" style="background:gray;height: 200px;">1/4</div>
        </div>

        <div class="w3-row">
            <div class="w3-threequarter" style="background:red;height: 200px;">3/4</div>
            <div class="w3-quarter" style="background:blue;height: 200px;">1/4</div>
        </div>

        <div class="w3-row">
            <div class="w3-quarter" style="background:green;height: 200px;">1/4</div>
            <div class="w3-threequarter" style="background:gray;height: 200px;">3/4</div>
        </div>

        <div class="w3-row">
            <div class="w3-half" style="background:red;height: 200px;">2/4</div>
            <div class="w3-half" style="background:green;height: 200px;">2/4</div>
        </div>

        <div>Aqui va todo el contenido</div>

    </div>
</div>
</body>
</html>
