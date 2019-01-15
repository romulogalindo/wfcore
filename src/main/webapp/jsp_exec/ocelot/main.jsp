<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: rgalindo
  Date: 17/12/18
  Time: 18:59
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1" language="java" %>
<jsp:useBean id="mainBean" class="com.acceso.wfweb.beans.MainBean"/>
<jsp:useBean id="contenedorBean" class="com.acceso.wfweb.beans.ContenedorBean"/>

<html co_conten="${contenedorBean.do64(pageContext.request)}">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
    <title>Main</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-amber.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">

    <!--CSS-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jsp_exec/css/workflow.css">

    <!--JS -->
    <script src="${pageContext.request.contextPath}/jsp_exec/js/workflow.js?a=3"></script>

</head>
<body onload="workflow()">

<!-- Sidebar -->
<div class="w3-sidebar w3-white w3-animate-left w3-large" style="z-index:3;width:300px; display: none;" id="mySidebar">

    <input type="hidden" value="${contenedorBean.contenedor.id_frawor}" id="id_frawor">
    <input type="hidden" value="${contenedorBean.contenedor.co_conten}" id="co_conten">

    <div class="w3-bar w3-black w3-center">
        <button class="w3-bar-item w3-button tablink w3-red" style="width:50%" onclick="viewtab(event,'menu64')">Menú
        </button>
        <button class="w3-bar-item w3-button tablink" style="width:50%" onclick="viewtab(event,'sistemas64')">Sistemas
        </button>
    </div>

    <div id="menu64" class="w3-bar-block menubloq">
        <%--<a class="w3-bar-item w3-button w3-border-bottom w3-large" href="#">--%>
            <%--<img src="https://www.w3schools.com/images/w3schools.png" style="width:80%;">--%>
        <%--</a>--%>

        <ul class="w3-ul w3-tiny" style="padding-top: 10px;">
            <c:forEach var="menu" items="${US.mainMenu.menu}">

                <li class="" style="font-weight: 500; padding-left: 10px;">

                <c:if test="${not empty menu.url}">
                    <i class="fa fa-window-maximize" aria-hidden="true"></i>
                    <a href="${menu.url}">${menu.name}</a>
                    </li>
                </c:if>

                <c:if test="${empty menu.url}">
                    <span style="color:green;">${menu.name}</span>
                    </li>

                    <c:forEach var="menuitem" items="${menu.sub}">
                        <li class="" style="font-weight: 500;padding-left: 20px;">

                        <c:if test="${not empty menuitem.url}">
                            <i class="fa fa-window-maximize" aria-hidden="true"></i>
                            <a href="${menuitem.url}">${menuitem.name}</a>
                            </li>
                        </c:if>

                        <c:if test="${empty menuitem.url}">
                            <span style="color: blue;">${menuitem.name}</span>
                            </li>

                            <c:forEach var="menuitem2" items="${menuitem.sub}">
                                <li class="" style="font-weight: 400;padding-left: 30px;">

                                <c:if test="${not empty menuitem2.url}">
                                    <i class="fa fa-window-maximize" aria-hidden="true"></i>
                                    <a href="${menuitem2.url}">${menuitem2.name}</a>
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

        <ul class="w3-ul w3-small" style="padding-top: 10px;">
            <c:forEach var="sistema" items="${US.root.sistemas}">

                <li class="w3-padding-small" style="font-weight: 500; padding-left: 5px;">
                        ${sistema.no_sistem}
                </li>

                <c:forEach var="sub_sistema" items="${sistema.subsistemas}">

                    <li class="w3-padding-small" style="padding-left: 15px !important;">
                        <i class="fa fa-cubes"></i>
                            ${sub_sistema.no_subsis}
                    </li>

                    <c:forEach var="paquete" items="${sub_sistema.paquetes}">
                        <li class="w3-padding-small" style="padding-left: 30px !important;">

                            <a href="#" style="">
                                <i class="fa fa-folder-open"></i>
                                    ${paquete.no_paquet}
                            </a>
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
            <%--<div class="w3-bar-item w3-button">${US.no_usulog}</div>--%>
            <a href="#" class="w3-bar-item w3-button w3-right">
                ${US.no_usulog}
                <img class="w3-hide-small w3-circle" src="${US.no_imgusu}"
                     style="height:40px;"/>
            </a>
        </div>

    </div>
</div>

<div class="w3-content ">
    <div class="main_margin_etop" style="background:#ededed;color:white;">

        <div class="w3-row" style="height: 30px;"></div>

        <%--<div class="w3-row">--%>
        <%--<div class="w3-quarter" style="background:red;height: 200px;">--%>
        <%--<iframe src="/jsp_exec/ocelot/pagina.jsp?rise=123456" class="wf4_iframe" onload="readypagina(this)" on></iframe>--%>
        <%--</div>--%>
        <%--<div class="w3-quarter" style="background:green;height: 200px;">1/4</div>--%>
        <%--<div class="w3-quarter" style="background:blue;height: 200px;">1/4</div>--%>
        <%--<div class="w3-quarter" style="background:gray;height: 200px;">1/4</div>--%>
        <%--</div>--%>

        <%--<div class="w3-row">--%>
        <%--<div class="w3-threequarter" style="background:red;height: 200px;">3/4</div>--%>
        <%--<div class="w3-quarter" style="background:blue;height: 200px;">1/4</div>--%>
        <%--</div>--%>

        <%--<div class="w3-row">--%>
        <%--<div class="w3-quarter" style="background:green;height: 200px;">1/4</div>--%>
        <%--<div class="w3-threequarter" style="background:gray;height: 200px;">3/4</div>--%>
        <%--</div>--%>

        <%--<div class="w3-row">--%>
        <%--<div class="w3-half" style="background:red;height: 200px;">2/4</div>--%>
        <%--<div class="w3-half" style="background:green;height: 200px;">2/4</div>--%>
        <%--</div>--%>

        <%--<div>Aqui va todo el contenido</div>--%>
        ${contenedorBean.contenedor.toHTML()}

    </div>
</div>
</body>
</html>
