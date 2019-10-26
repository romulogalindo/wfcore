<%@ page import="java.io.StringWriter" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.Enumeration" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: rgalindo
  Date: 17/12/18
  Time: 18:59
  To change this template use File | Settings | File Templates.
--%>

<%@ page isErrorPage="true" contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1" language="java" %>
<%--<jsp:useBean id="mainBean" class="com.acceso.wfweb.beans.MainBean"/>--%>
<%--<jsp:useBean id="contenedorBean" class="com.acceso.wfweb.beans.ContenedorBean"/>--%>

<html>
<head>
    <title>${APP.loginCTRL.login_page_title} - Acceso Corp.</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"-->

    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
    <%--    <script src="/jsp_exec/js/aniplex/three.r92.js"></script>--%>
    <%--    <script src="/jsp_exec/js/aniplex/vanta.net.js"></script>--%>
    <script>

    </script>
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/jsp_exec/css/mdb/bootstrap.min.css" rel="stylesheet">
    <!-- Material Design Bootstrap -->
    <link href="${pageContext.request.contextPath}/jsp_exec/css/mdb/mdb.min.css" rel="stylesheet">
    <!-- Your custom styles (optional) -->
    <link href="${pageContext.request.contextPath}/jsp_exec/css/mdb/style.css" rel="stylesheet">


    <link rel='shortcut icon' type='image/x-icon'
          href='${pageContext.request.contextPath}/jsp_exec/imgs/defaults/favicon.ico'/>

    <script>
        function do64() {
            document.getElementsByName("username")[0].focus();
        }
    </script>
    <style>
        body, html {
            height: 100%;
        }

        body {
            /* The image used */
            /*background: linear-gradient(rgba(255, 255, 255, .5), rgba(255, 255, 255, .5)), url("https://mdbootstrap.com/img/Photos/Horizontal/Nature/full page/img(11).jpg");*/
            background: linear-gradient(rgba(255, 255, 255, .5), rgba(255, 255, 255, .5)), url("https://i.ytimg.com/vi/FgxJ1etZHGo/maxresdefault.jpg");

            /* Half height */
            height: 50%;

            /* Center and scale the image nicely */
            background-position: center;
            background-repeat: no-repeat;
            background-size: cover;
        }
    </style>
</head>
<!--body class="w3-theme-l4" onload="do64()"-->
<body id="body" onload="do64()">

<!--div class="bg"></div-->

<div style="height: 100vh">
    <div class="flex-center flex-column">

        <!-- Material form login -->
        <div class="card">

            <h5 class="card-header info-color white-text text-center py-4">
                <strong>Cambio de contrase&ntilde;a</strong>
            </h5>


            <!--Card content-->
            <div class="card-body px-lg-5 pt-0">

                <c:if test="${not empty login_error}">
                    <div class="alert alert-danger" role="alert"
                         style="font-size: .7em;max-width: 300px;margin-top: 20px;text-align: justify;">
                        Cambio de contrase&nacute;a
                    </div>
                </c:if>
                <c:set value="" var="login_error" scope="session"/>

                <!-- Form -->
                <form class="text-center" style="color: #757575;" action="${APP.loginCTRL.login_action}" method="post">

                    <!-- Email -->
                    <%--                    <div class="md-form">--%>
                    <%--                        <input type="text" id="username" name="username" class="form-control">--%>
                    <%--                        <label for="username">${APP.loginCTRL.login_lbl_username}</label>--%>
                    <%--                    </div>--%>

                    <!-- Current Password -${NEED_CHANGE_PASSWORD}-->
                    <c:if test="${NEED_CHANGE_PASSWORD == 'TYPE1'}">
                        <div class="md-form" style="display: block;height: 20px;">
                            <label>Hola ${US.no_usuari}</label>
                        </div>
                    </c:if>

                    <c:if test="${NEED_CHANGE_PASSWORD == 'TYPE2'}">
                        <div class="md-form">
                            <input type="password" id="current_password" name="password" class="form-control">
                            <label for="current_password">Contrase&ntilde;a actual</label>
                        </div>
                    </c:if>



                    <div class="md-form">
                        <input type="password" id="new_password" name="password" class="form-control">
                        <label for="new_password">Contrase&ntilde;a nueva</label>
                    </div>

                    <div class="md-form">
                        <input type="password" id="new_password2" name="password" class="form-control">
                        <label for="new_password">Repite la contrase&ntilde;a nueva</label>
                    </div>

<%--                    <div class="form-group text-left" style="font-size: .8rem;">--%>
<%--                        La nueva contrase&nacute;a nueva debe tener de cumplir:--%>
<%--                    </div>--%>

                    <div class="alert alert-info text-left">
                        <label class="form-check-label" style="font-size: .8rem">
                            La nueva contrase&ntilde;a nueva debe tener de cumplir:
                        </label>
                        <br/>
                        <label class="form-check-label">
                            <ul class="mb-0">
                                <li class="text-left mb-1" style="font-size: .7rem;">Mayor de 6 caracteres</li>
                                <li class="text-left mb-1" style="font-size: .7rem;">Contener minimo un caracter en
                                    Mayuscula
                                </li>
                                <li class="text-left mb-1" style="font-size: .7rem;">Contener minimo un caracter en
                                    Minuscula
                                </li>
                                <li class="text-left mb-1" style="font-size: .7rem;">Contener minimo un caracter
                                    especial <strong>|\#*/·$%&/()?¿'¡!.</strong></li>
                            </ul>
                        </label>
                    </div>

                    <!-- Sign in button -->
                    <button class="btn btn-primary btn-lg btn-block waves-effect z-depth-0" disabled>Actualizar
                    </button>

                    <hr>

                    <!-- Terms of service -->
                    <p>Si presenta algun error llamar a
                        <a href="" target="_blank">helpdesk</a>
                    </p>
                </form>
                <!-- Form -->

            </div>

        </div>
        <!-- Material form login -->
    </div>

</div>

<footer class="page-footer font-small info-color position-fixed w-100" style="font-size: .8rem;">

    <!-- Footer Text -->
    <div class="footer-copyright text-center p-1">

        <!-- Grid row -->
        <div class="row">

            <!-- Grid column -->
            <div class="col-md-4 mt-md-0 text-left pt-1">

                <!-- Content -->
                <img src="/jsp_exec/imgs/defaults/logo_acceso2.png" height="12px;">

            </div>
            <!-- Grid column -->

            <%--            <hr class="clearfix w-100 d-md-none pb-3">--%>

            <!-- Grid column -->
            <div class="col-md-4 mb-md-0">

                <!-- Content -->
                &copy; 2019 Copyright:
                <a href="https://acceso.com.pe/"> acceso.com.pe</a>

            </div>

            <%--            <hr class="clearfix w-100 d-md-none pb-3">--%>

            <!-- Grid column -->
            <div class="col-md-4 mb-md-0 text-right text-white">

                <!-- Content [version].[Kernel].[modulo].[funciones]-->
                Ver 1.0.0.64

            </div>
            <!-- Grid column -->

        </div>
        <!-- Grid row -->

    </div>
    <!-- Footer Text -->

</footer>
<!-- Footer -->

<!-- SCRIPTS -->
<!-- JQuery -->
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp_exec/js/mdb/jquery-3.3.1.min.js"></script>
<!-- Bootstrap tooltips -->
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp_exec/js/mdb/popper.min.js"></script>
<!-- Bootstrap core JavaScript -->
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp_exec/js/mdb/bootstrap.min.js"></script>
<!-- MDB core JavaScript -->
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp_exec/js/mdb/mdb.js"></script>
</body>
</html>
