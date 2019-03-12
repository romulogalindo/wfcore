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
    <title>${contenedorBean.contenedor.co_contit} - AIO2</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-amber.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">

    <!--link rel="icon" type="image/png" href="{pageContext.request.contextPath}/jsp_exec/imgs/defaults/favicon.png"-->
    <link rel='shortcut icon' type='image/x-icon'
          href='${pageContext.request.contextPath}/jsp_exec/imgs/defaults/favicon.ico'/>

    <!--CSS-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jsp_exec/css/workflow.css?a=6">

    <!--JS -->
    <script src="${pageContext.request.contextPath}/jsp_exec/js/workflow.js?a=27"></script>
    <script src="${pageContext.request.contextPath}/jsp_exec/js/wfajax.js?a=27"></script>

</head>
<body onload="workflow()" style="background: #ededed;">
<input type="hidden" value="${contenedorBean.contenedor.id_frawor}" id="id_frawor">
<input type="hidden" value="${contenedorBean.contenedor.co_conten}" id="co_conten">

<!-- Sidebar -->
<c:if test="${contenedorBean.contenedor.il_header}">
    <div class="w3-sidebar w3-white w3-animate-left w3-large" style="z-index:3;width:300px; display: none;"
         id="mySidebar">
        <div style="position: sticky;height: 43px;top: 0px;">
            <div class="w3-bar w3-black w3-center">
                <button class="w3-bar-item w3-button tablink w3-red" style="width:50%"
                        onclick="viewtab(event,'menu64')">
                    Menú
                </button>
                <button class="w3-bar-item w3-button tablink" style="width:50%" onclick="viewtab(event,'sistemas64')">
                    Sistemas
                </button>
            </div>
        </div>
        <!--div style="height: 40px;"></div-->

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

        <div style="position: sticky;height: 43px;bottom: 0px;">
            <div class="w3-bar w3-black w3-center">
                <button class="w3-bar-item w3-button tablink w3-gray" style="width:100%" onclick="logout()">
                    <i class="fa fa-power-off" aria-hidden="true"></i>
                    Cerrar sesión
                </button>
            </div>
        </div>
    </div>

    <div class="w3-overlay " onclick="w3_close()" style="cursor:pointer" id="myOverlay"></div>

    <div class="w3-row w3-padding w3-teal w3-xlarge">
        <div class="w3-col" style="width: 70%;padding: auto 0px;">
            <table>
                <tr>
                    <td>
                        <button class="w3-button w3-teal w3-xlarge" onclick="w3_open()">
                            <i class="fa fa-bars" aria-hidden="true"></i>
                        </button>
                    </td>
                    <td>
                        <div><h1 class="main_title_module">${contenedorBean.contenedor.co_contit}</h1></div>
                        <div class="main_title_breadcrumbs">/ruta/completa/del/modulo</div>
                    </td>
                </tr>
            </table>
        </div>


        <div class="w3-col" style="width: 30%">
            <div class="w3-bar w3-xlarge23">
                    <%--<div class="w3-bar-item w3-button">${US.no_usulog}</div>--%>
                <a href="#" class="w3-bar-item w3-button w3-right">
                        ${US.no_usulog}
                    <img class="w3-hide-small w3-circle" src="${US.no_imgusu}"
                         style="height:40px;"/>
                </a>
            </div>

        </div>
    </div>
</c:if>


<div id="popup" class="w3-modal">
    <div class="w3-modal-content w3-animate-top w3-card-4">
        <header class="w3-container w3-teal" style="height:auto;">
            <h5 id="popup_head">Ventana emergente</h5>
            <span onclick="master_popup_close()" class="w3-large w3-display-topright "
                  style="margin: 10px;font-size: 25px !important;cursor:pointer; ">
                <i class="fa fa-times" aria-hidden="true"></i>
            </span>
        </header>
        <div class="w3-container" style="padding: 0px;">
            <iframe id="popup_body" src="" style="width: 100%;height: 75%;" class="wf4_iframe">
            </iframe>
        </div>


    </div>
</div>

<div class="w3-content ">
    <div class="main_margin_etop" style="background:#ededed;color:white;">

        <div class="w3-row" style="height: 30px;"></div>

        <%--<div>Aqui va todo el contenido</div>--%>
        ${contenedorBean.contenedor.toHTML()}

    </div>
</div>
</body>
</html>
