<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  User: Ròmulo Galindo
  Date: 12/12/18
  Time: 17:38
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
            // VANTA.NET({
            //     el: "#body"
            // });
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
                <strong>${APP.loginCTRL.login_form_title}</strong>
            </h5>

            <c:if test='${APP.getDataSourceService().getValueOfKey("ENABLED_LOGIN_LDAP") == "TRUE" }'>
                <h6 class="text-center mt-2">
                <span class="badge badge-primary p-2">Entorno seguro LDAP&nbsp; <i class="fas fa-shield-alt"></i>
                </span>
                </h6>
            </c:if>

            <!--Card content-->
            <div class="card-body px-lg-5 pt-0">

                <c:if test="${not empty login_error}">
                    <div class="alert alert-danger" role="alert"
                         style="font-size: .7em;max-width: 300px;margin-top: 20px;text-align: justify;">
                            ${login_error}
                    </div>
                </c:if>
                <c:set value="" var="login_error" scope="session"/>

                <!-- Form -->
                <form class="text-center" style="color: #757575;" action="${APP.loginCTRL.login_action}" method="post">

                    <!-- Email -->
                    <div class="md-form">
                        <input type="text" id="username" name="username" class="form-control">
                        <label for="username">${APP.loginCTRL.login_lbl_username}</label>
                    </div>

                    <!-- Password -->
                    <div class="md-form">
                        <input type="password" id="password" name="password" class="form-control">
                        <label for="password">${APP.loginCTRL.login_lbl_password}</label>
                    </div>

                    <!--div-- class="d-flex justify-content-around">
                        <div>
                            <div class="form-check">
                                <input type="checkbox" class="form-check-input" id="materialLoginFormRemember">
                                <label class="form-check-label" for="materialLoginFormRemember">Remember me</label>
                            </div>
                        </div>
                        <div>
                            <a href="">Forgot password?</a>
                        </div>
                    </div-->

                    <!-- Sign in button -->
                    <button class="btn btn-outline-info btn-rounded btn-block my-4 waves-effect z-depth-0">${APP.loginCTRL.login_btn_login}</button>

                    <hr>

                    <!-- Terms of service -->
                    <p>
                        <a href="/recovery" target="_blank">¿Problemas para acceder?</a>
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
                Ver 1.0.0.69

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

