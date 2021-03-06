<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: rgalindo
  Date: 17/12/18
  Time: 18:59
--%>

<%@ page contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1" language="java" %>
<jsp:useBean id="mainBean" class="com.acceso.wfweb.beans.MainBean" scope="session"/>
<jsp:useBean id="cntBean" class="com.acceso.wfweb.beans.ContenedorBean" scope="session"/>

<html co_conten="${cntBean.do64(pageContext.request)}">
<head>
    <title>${cntBean.contenedor.co_contit} - AIO2</title>

    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jsp_exec/css/mdb_acr.css?a=2">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jsp_exec/css/themes/theme-${US.no_temdef}.css?a=1">

    <link rel='shortcut icon' type='image/x-icon'
          href='${pageContext.request.contextPath}/jsp_exec/imgs/defaults/favicon.ico'/>

    <!--CSS-->
    <link rel="stylesheet" href="/jsp_exec/css/workflow.css?a=11"/>

    <!--JS -->
    <script src="${pageContext.request.contextPath}/jsp_exec/js/contenedor.js?a=30"></script>
    <script src="${pageContext.request.contextPath}/jsp_exec/js/wfajax.js?a=42"></script>
    <script src="${pageContext.request.contextPath}/jsp_exec/js/websocket.js?a=24"></script>

    <style>
        .side-nav .logo-sn {
            padding-bottom: 1rem;
            padding-top: 1rem;
        }

        .side-nav .logo-sn img {
            height: 38px;
        }

        .side-nav .search-form input[type=text] {
            margin-top: 0;
            padding-top: 12px;
            padding-bottom: 12px;
            border-top: 1px solid rgba(255, 255, 255, 0.3);
            border-bottom: 1px solid rgba(255, 255, 255, 0.3);
        }

        body {
            background-color: #eee;
        }

        .accordion .card {
            margin-bottom: 1.2rem;
            box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.16), 0 2px 10px 0 rgba(0, 0, 0, 0.12);
        }

        .accordion .card .card-body {
            border-top: 1px solid #eee;
        }

        <c:if test="${cntBean.contenedor.il_popup}">
        body {
            background-color: rgba(255, 255, 255, .0);
        }

        </c:if>


    </style>
</head>
<body onload="workflow(${cntBean.contenedor.il_popup}, ${US.do_firtim()})"
      class="fixed-sn light-blue-skin ${!cntBean.contenedor.il_popup}">
<input type="hidden" value="${cntBean.contenedor.id_frawor}" id="id_frawor"/>
<input type="hidden" value="${cntBean.contenedor.co_conten}" id="co_conten"/>
<input type="hidden" value="${US.co_usuari}" id="co_usuari"/>

<textarea style="display:none;" id="ls_conpar">${cntBean.contenedor.ls_conpar}</textarea>

<c:if test="${!cntBean.contenedor.il_popup}">
    <!--Main Navigation-->
    <header>

        <!--Navbar-->
        <nav class="navbar navbar-expand-lg navbar-dark fixed-top scrolling-navbar double-nav default-color">

            <!-- SideNav slide-out button -->
            <div class="float-left">
                <a href="#" data-activates="slide-out" class="button-collapse">
                    <i class="fas fa-bars"></i>
                </a>
            </div>

            <!-- Breadcrumb-->
            <div class="breadcrumb-dn mr-auto white-text">
                <p>${cntBean.contenedor.co_contit}</p>
            </div>

            <!-- Links -->
            <ul class="nav navbar-nav nav-flex-icons ml-auto">
                <li class="nav-item">
                    <a class="nav-link">
                        <i class="fas fa-envelope"></i>
                        <span class="clearfix d-none d-sm-inline-block">Mensajes</span>
                    </a>
                </li>
                <!--                        <li class="nav-item">
                                            <a class="nav-link">
                                                <i class="fas fa-cog"></i>
                                                <span class="clearfix d-none d-sm-inline-block">Otros links</span>
                                            </a>
                                        </li>-->
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false">
                        <i class="fas fa-user"></i>
                        <span class="clearfix d-none d-sm-inline-block"
                              style="text-transform: uppercase">${US.no_usulog}</span>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownMenuLink">
                        <a class="dropdown-item" href="/main?co_conten=444"><i class="fas fa-cog"></i>&nbsp;Configuración</a>
                        <a class="dropdown-item" href="/logout64"><i class="fas fa-power-off"></i>&nbsp;Cerrar
                            sesión</a>
                    </div>
                </li>
            </ul>

        </nav>
        <!--/.Navbar-->

        <!-- Sidebar navigation -->
        <div id="slide-out" class="side-nav fixed">
            <ul class="custom-scrollbar">
                <!-- Logo -->
                <li class="logo-sn waves-effect">
                    <div class=" text-center">
                        <a href="#" class="pl-0">
                            <img src="/jsp_exec/imgs/defaults/logo_wfcore.png" alt="" style="height: 54px;"/>
                        </a>
                    </div>
                </li>
                <li class="">
                    <div class=" text-center">
                        <a id="main_system" href="#" onclick="return view_all_system()" style="color:white;"
                           class="pl-0"
                           style="color:white;height: 30px;line-height: 20px;">
                            SISTEMAS
                            <i class="fas fa-cogs"></i>
                        </a>
                    </div>
                </li>
                <!--/. Logo -->

                <!-- Nav tabs -->
                <ul class="nav nav-tabs md-tabs nav-justified primary-color"
                    style="padding-left: .5rem;padding-right: .5rem;" role="tablist">
                    <li class="nav-item">
                        <a id="topanel666" class="nav-link" data-toggle="tab" href="#panel666" role="tab"
                           style="line-height: 20px;padding: 0px !important;height:20px;">
                            Sistemas</a>
                    </li>
                    <li class="nav-item">
                        <a id="topanel555" class="nav-link active" data-toggle="tab" href="#panel555" role="tab"
                           style="line-height: 20px;padding: 0px !important;height:20px;">
                            Modulos</a>
                    </li>
                </ul>
                <!-- Nav tabs -->

                <!-- Tab panels -->
                <div class="tab-content">

                    <!-- Panel 1 -->
                    <div class="tab-pane fade in show active" id="panel555" role="tabpanel">

                        <!-- Nav tabs -->
                        <div class="row">
                            <ul style="width: 100%;">
                                <!--Search Form-->
                                <li>
                                    <form class="search-form" role="search">
                                        <div class="form-group md-form mt-0 pt-1 waves-light">
                                            <input type="text" class="form-control" placeholder="Search">
                                        </div>
                                    </form>
                                </li>
                                <!--/.Search Form-->

                                <!-- Side navigation links -->
                                <c:set scope="page" var="p_co_conten"
                                       value="${''+param['co_conten']}"/>
                                <li id="ls_module">
                                    <ul class="collapsible collapsible-accordion">
                                        <c:forEach var="menu" items="${US.mainMenu.menu}">
                                            <li>
                                                <c:if test="${empty menu.url and fn:length(menu.sub) > 0}">
                                                    <a id="menu${menu.co_mensis}"
                                                       class="collapsible-header waves-effect arrow-r">
                                                        <i class="fas fa-chevron-right"></i>
                                                            ${menu.name}
                                                        <i class="fas fa-angle-down rotate-icon"></i>
                                                    </a>
                                                    <div class="collapsible-body">
                                                        <ul>
                                                            <c:forEach var="menuitem" items="${menu.sub}">
                                                                <li class="${fn:indexOf(menuitem.url,p_co_conten) > -1 ?'current-menu-item':''}">
                                                                    <c:if test="${empty menuitem.url and fn:length(menuitem.sub) > 0}">
                                                                        <a class="collapsible-header waves-effect arrow-r">
                                                                            <i class="fas fa-chevron-right"></i>
                                                                                ${menuitem.name}
                                                                            <i class="fas fa-angle-down rotate-icon"></i>
                                                                        </a>
                                                                        <div class="collapsible-body">
                                                                            <ul>
                                                                                <c:forEach var="menuitem2"
                                                                                           items="${menuitem.sub}">
                                                                                    <c:if test="${not empty menuitem2.url and fn:length(menuitem2.sub) eq 0}">
                                                                                        <li class="${xcft}a ${param.co_conten} - ${fn:indexOf(menuitem2.url,xcft)} -${fn:indexOf(menuitem2.url,xcft) > -1 ?'current-menu-item':'x'}">
                                                                                            <a href="${menuitem2.url}"
                                                                                               class="waves-effect a">${menuitem2.name}</a>
                                                                                        </li>
                                                                                    </c:if>
                                                                                </c:forEach>
                                                                            </ul>
                                                                        </div>
                                                                    </c:if>
                                                                    <c:if test="${not empty menuitem.url and fn:length(menuitem.sub) eq 0}">
                                                                        <a href="${menuitem.url}"
                                                                           class="waves-effect">${menuitem.name}</a>
                                                                    </c:if>
                                                                    <c:if test="${fn:indexOf(menuitem.url,p_co_conten) > -1 }">
                                                                        <script>
                                                                            el_actmen = 'menu${menu.co_mensis}';
                                                                        </script>
                                                                    </c:if>
                                                                </li>
                                                            </c:forEach>
                                                        </ul>
                                                    </div>
                                                </c:if>
                                                <c:if test="${not empty menu.url }">
                                                    <a href="${menu.url}" class="waves-effect">${menu.name}</a>
                                                </c:if>

                                            </li>
                                        </c:forEach>
                                    </ul>
                                </li>
                                <!--/. Side navigation links -->
                            </ul>
                        </div>
                        <!-- Nav tabs -->

                    </div>
                    <!-- Panel 1 -->

                    <!-- Panel 2 -->
                    <div class="tab-pane fade" id="panel666" role="tabpanel">

                        <!-- Nav tabs -->
                        <div class="row">
                            <ul class="collapsible collapsible-accordion" style="width: 100%;">
                                <!-- Side navigation links -->
                                <c:forEach var="sistema" items="${US.root.sistemas}">
                                    <c:if test="${!sistema.il_sisfor}">
                                        <c:if test="${sistema.co_sistem == US.co_sistem}">
                                            <script>
                                                document.getElementById("main_system").innerHTML = '${sistema.no_sistem} ' + '<i class="fas fa-cogs"></i>';
                                            </script>
                                        </c:if>
                                        <li>
                                            <a class="collapsible-header waves-effect arrow-r">
                                                <i class="fas fa-chevron-right"></i>
                                                    ${sistema.no_sistem}
                                                <i class="fas fa-angle-down rotate-icon"></i>
                                            </a>
                                            <div class="collapsible-body">

                                                <ul class="collapsible collapsible-accordion" style="margin-top: 2px;">

                                                    <c:forEach var="sub_sistema" items="${sistema.subsistemas}">
                                                        <li>
                                                            <a href="#"
                                                               onclick="changemodulo(${sistema.co_sistem}, ${sub_sistema.co_subsis}, true);"
                                                               class="waves-effect">${sub_sistema.no_subsis}</a>
                                                        </li>
                                                    </c:forEach>
                                                </ul>
                                            </div>
                                        </li>
                                    </c:if>

                                </c:forEach>

                                <!--/. Side navigation links -->
                            </ul>
                        </div>
                        <!-- Nav tabs -->

                    </div>
                    <!-- Panel 2 -->

                </div>
                <!-- Tab panels -->


            </ul>
            <div class="sidenav-bg rgba-blue-strong"></div>
        </div>
        <!--/. Sidebar navigation -->

    </header>
    <!--Main Navigation-->
</c:if>

<div id="mdb-preloader" class="flex-center" style="display: none; background-color: rgba(0,0,0,.55)">
    <table style="width: 100%;height: 100%">
        <tr>
            <td class="align-middle">
                <div class="card" style="margin: auto;width: 250px;text-align: center;">
                    <div class="card-body">

                        <div class="preloader-wrapper big active">
                            <div class="spinner-layer spinner-blue">
                                <div class="circle-clipper left">
                                    <div class="circle"></div>
                                </div>
                                <div class="gap-patch">
                                    <div class="circle"></div>
                                </div>
                                <div class="circle-clipper right">
                                    <div class="circle"></div>
                                </div>
                            </div>
                            <div class="spinner-layer spinner-red">
                                <div class="circle-clipper left">
                                    <div class="circle"></div>
                                </div>
                                <div class="gap-patch">
                                    <div class="circle"></div>
                                </div>
                                <div class="circle-clipper right">
                                    <div class="circle"></div>
                                </div>
                            </div>
                            <div class="spinner-layer spinner-yellow">
                                <div class="circle-clipper left">
                                    <div class="circle"></div>
                                </div>
                                <div class="gap-patch">
                                    <div class="circle"></div>
                                </div>
                                <div class="circle-clipper right">
                                    <div class="circle"></div>
                                </div>
                            </div>
                            <div class="spinner-layer spinner-green">
                                <div class="circle-clipper left">
                                    <div class="circle"></div>
                                </div>
                                <div class="gap-patch">
                                    <div class="circle"></div>
                                </div>
                                <div class="circle-clipper right">
                                    <div class="circle"></div>
                                </div>
                            </div>
                        </div>

                        <h4 id="sys_loader_title" class="card-title" style="margin: 0px;">Procesando...</h4>
                        <span id="sys_loader_stitle" class="card-title" style="margin: 0px;"></span>
                    </div>
                </div>
            </td>
        </tr>
    </table>
</div>

<div class="modal fade" id="popup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog cascading-modal modal-xl" role="document">

        <!--Content-->
        <div class="modal-content"
             style="height: 90%; background-color: rgba(255,255,255,.0); box-shadow: none; bottom: 10%;">

            <!--Header-->
            <%--            <div class="modal-header darken-3 white-text" style="background-color: #2bbbad;border-radius: 0.5rem;">--%>
            <%--                <h4 class="title">--%>
            <%--                    Sistemas--%>
            <%--                </h4>--%>
            <%--            </div>--%>

            <!--Body-->
            <div class="modal-body">
                <iframe id="popup_body" src="" style="width: 100%;height: 75%;" class="wf4_iframe">
                </iframe>
            </div>
        </div>
    </div>
    <!--/.Content-->

</div>

<div class="modal fade" id="popup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog cascading-modal modal-xl" role="document">

        <!--Content-->
        <div class="modal-content">

            <!--Header-->
            <div class="modal-header darken-3 white-text" style="background-color: #2bbbad;border-radius: 0.5rem;">
                <h4 id="popup_head2" class="title">
                    Sistemas
                </h4>
            </div>

            <!--Body-->
            <div class="modal-body">
                <div id="popup2_body" class="w3-container" style="padding: 0px;">

                </div>
            </div>
            <footer>
                <input type="hidden" id="val_ms" value=""/>
                <buttton id="popup2_btn_cancel" class="w3-button" onclick="master_popup_close2()">Cancelar</buttton>
                <buttton id="popup2_btn_ok" class="w3-button" onclick="master_popup_close2()">Agregar</buttton>
            </footer>
        </div>
    </div>
    <!--/.Content-->

</div>

<!--div id="popup" class="w3-modal">
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

<div id="popup2" class="w3-modal">
    <div class="w3-modal-content w3-animate-top w3-card-4">
        <header class="w3-container w3-teal" style="height:auto;">
            <h5 id="popup_head2">Ventana emergente</h5>
            <span onclick="master_popup_close2()" class="w3-large w3-display-topright "
                  style="margin: 10px;font-size: 25px !important;cursor:pointer; ">
                <i class="fa fa-times" aria-hidden="true"></i>
            </span>
        </header>
        <div id="popup2_body" class="w3-container" style="padding: 0px;">

        </div>
        <footer>
            <input type="hidden" id="val_ms" value=""/>
            <buttton id="popup2_btn_cancel" class="w3-button" onclick="master_popup_close2()">Cancelar</buttton>
            <buttton id="popup2_btn_ok" class="w3-button" onclick="master_popup_close2()">Agregar</buttton>
        </footer>

    </div>
</div-->

<!--Main layout-->
<c:if test="${cntBean.contenedor.il_popup}">
<main style="margin-left:0;margin-right: 0px;padding-top: 2rem">
    </c:if>
    <c:if test="${!cntBean.contenedor.il_popup}">
    <main>
        </c:if>

        <div class="container-fluid">
            <section card card-cascade narrower mb-5>
                <input type="hidden" id="il_popup" name="il_popup" value="${cntBean.contenedor.il_popup}">
                ${cntBean.contenedor.toHTML()}
            </section>

        </div>
    </main>

    <!--MODAL-->
    <%--<c:if test="${US.il_schema}">--%>
    <!--Modal: modalSocial-->
    <div class="modal fade ${US.il_schema? 'firsttime':''}" id="slc_schema" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog cascading-modal modal-xl" role="document">

            <!--Content-->
            <div class="modal-content">

                <!--Header-->
                <div class="modal-header darken-3 white-text" style="background-color: #2bbbad;border-radius: 0.5rem;">
                    <h4 class="title">
                        Sistemas
                    </h4>
                </div>

                <!--Body-->
                <div class="modal-body">
                    <div class="container-fluid" style="height: auto;overflow-y: scroll;">

                        <c:set value="1" var="yxt"/>
                        <c:set value="0" var="clse"/>

                        <c:forEach var="sistema" items="${US.root.sistemas}">
                            <c:if test="${sistema.il_sisfor}">
                                <c:if test="${yxt==1}">
                                    <div class="row">
                                    <c:set value="0" var="clse"/>
                                </c:if>

                                <div class="card mb-4 col-md-3 yxt${yxt}"
                                     style="height: 130px;padding: 0px;margin: 0px auto;max-width: 23%;">

                                    <!-- Card image -->
                                    <div class="view overlay" style="padding: .5em .8em;">
                                        <c:if test="${empty sistema.ar_logsis}">
                                            <img class="card-img-top"
                                                 src="/jsp_exec/imgs/defaults/logo_acceso.png"
                                                 alt="Card image cap">
                                        </c:if>
                                        <c:if test="${not empty sistema.ar_logsis}">
                                            <img class="card-img-top"
                                                 src="/jsp_exec/ocelot/viewer.jsp?file=${sistema.ar_logsis}"
                                                 alt="Card image cap">
                                        </c:if>

                                            <%--                                        <img class="card-img-top card-img-bottom" src="/jsp_exec/ocelot/viewer.jsp?file=${sistema.ar_logsis}" >--%>
                                        <a href="${sistema.ur_sistem}" target="_blank">
                                            <div class="mask rgba-white-light"></div>
                                        </a>
                                    </div>

                                    <!-- Card content -->
                                    <div class="card-body" style="text-align: center;padding: .0rem 1rem .8rem;">
                                        <h5 class="card-title" style="margin-bottom: 0px;">${sistema.no_sistem}</h5>
                                    </div>

                                </div>


                                <c:if test="${yxt==4}">
                                    </div>
                                    <c:set value="0" var="yxt"/>
                                    <c:set value="1" var="clse"/>
                                </c:if>
                                <c:set value="${yxt+1}" var="yxt"/>
                                <%--</c:forEach>--%>
                                <%--</c:forEach>--%>
                            </c:if>
                        </c:forEach>
                        <c:if test="${clse==0}">
                    </div>
                    </c:if>
                    <c:set value="1" var="yxt"/>
                    <c:set value="0" var="clse"/>

                    <c:forEach var="sistema" items="${US.root.sistemas}">
                        <c:if test="${!sistema.il_sisfor}">
                            <%--                            <c:forEach var="sub_sistema" items="${sistema.subsistemas}">--%>
                            <c:if test="${yxt==1}">
                                <div class="row">
                                <c:set value="0" var="clse"/>
                            </c:if>

                            <div class="card mb-4 col-md-3 yxt${yxt}"
                                 style="height: 130px;padding: 0px;margin: 0px auto;max-width: 23%;">

                                <!-- Card image -->
                                <div class="view overlay" style="padding: .5em .8em;">
                                    <c:if test="${empty sistema.ar_logsis}">
                                        <img class="card-img-top"
                                             src="/jsp_exec/imgs/defaults/logo_acceso.png"
                                             alt="Card image cap">
                                    </c:if>
                                    <c:if test="${not empty sistema.ar_logsis}">
                                        <img class="card-img-top"
                                             src="/jsp_exec/ocelot/viewer.jsp?file=${sistema.ar_logsis}"
                                             alt="Card image cap">
                                    </c:if>

                                    <a href="#"
                                       onclick="return changemodulo(${sistema.co_sistem}, ${sistema.subsistemas[0].co_subsis}, true);">
                                        <div class="mask rgba-white-slight"></div>
                                    </a>
                                </div>

                                <!-- Card content -->
                                <div class="card-body" style="text-align: center;padding: .8rem 1rem;">

                                        <%--                                        <h5 class="card-title" style="margin-bottom: 0px;">${sub_sistema.no_subsis}</h5>--%>
                                    <h5 class="card-title" style="margin-bottom: 0px;">${sistema.no_sistem}</h5>
                                    <span class="card-text">${sistema.de_sistem}</span>
                                </div>

                            </div>


                            <c:if test="${yxt==4}">
                                </div>
                                <c:set value="0" var="yxt"/>
                                <c:set value="1" var="clse"/>
                            </c:if>
                            <c:set value="${yxt+1}" var="yxt"/>
                            <%--                            </c:forEach>--%>
                        </c:if>
                    </c:forEach>
                    <c:if test="${clse==0}">
                </div>
                </c:if>
            </div>
        </div>
    </div>
    <!--/.Content-->

    </div>
    </div>
    <!--Modal: modalSocial-->
    <!--${US.setIl_schema(false)}-->
    <%--</c:if>--%>

    <%--DEFAULT MSG--%>
    <div class="modal fade" id="messageModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
         aria-hidden="true">

        <!-- Add .modal-dialog-centered to .modal-dialog to vertically center the modal -->
        <div class="modal-dialog modal-dialog-centered" role="document">


            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle">Mensaje</h5>
                    <!-- <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>-->
                </div>
                <div id="messageModalBody" class="modal-body">
                    [TEXT]
                </div>
                <div class="modal-footer" style="text-align: center;display: block;">
                    <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>

    <%--DINPUAH--%>
    <input placeholder="Selected date" style="display:none;" type="text" id="rankanadate"
           class="form-control datepicker"
           onchange="close_popup_date(this);">

    <%--<c:set scope="session" var="US" property="il_schema" value="false"/>--%>
    <%--<script type="text/javascript" src="https://mdbootstrap.com/wp-content/themes/mdbootstrap4/js/compiled-4.7.4.js"></script>--%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/jsp_exec/js/mdb_acr.js?a=2"></script>
</body>
</html>
