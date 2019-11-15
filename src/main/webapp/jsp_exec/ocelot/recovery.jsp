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

        function validNumber() {
            console.log('heres!');
            var regexp = new RegExp("^\\d{9}$");
            var cellnumber = '' + $('#cellnumber').val();

            // if (regexp.test(cellnumber)) {
            //     $('#btn_validcode').prop('disabled', false);
            // } else {
            //     $('#btn_validcode').prop('disabled', true);
            // }
            $('#btn_sencode').prop('disabled', !regexp.test(cellnumber));
        }

        function validCode() {
            console.log('heres!');
            var regexp = new RegExp("^\\d{6}$");
            var cellnumber = '' + $('#nu_valcod').val();

            // if (regexp.test(cellnumber)) {
            //     $('#btn_validcode').prop('disabled', false);
            // } else {
            //     $('#btn_validcode').prop('disabled', true);
            // }
            $('#btn_validcode').prop('disabled', !regexp.test(cellnumber));
        }

        function extractNumber(obj, decimalPlaces, allowNegative) {
            var temp = obj.value;
            // avoid changing things if already formatted correctly
            var reg0Str = '[0-9]*';
            if (decimalPlaces > 0) {
                reg0Str += '\\.?[0-9]{0,' + decimalPlaces + '}';
            } else if (decimalPlaces < 0) {
                reg0Str += '\\.?[0-9]*';
            }
            reg0Str = allowNegative ? '^-?' + reg0Str : '^' + reg0Str;
            reg0Str = reg0Str + '$';
            var reg0 = new RegExp(reg0Str);
            if (reg0.test(temp))
                return true;
            // first replace all non numbers
            var reg1Str = '[^0-9' + (decimalPlaces != 0 ? '.' : '') + (allowNegative ? '-' : '') + ']';
            var reg1 = new RegExp(reg1Str, 'g');
            temp = temp.replace(reg1, '');
            if (allowNegative) {
// replace extra negative
                var hasNegative = temp.length > 0 && temp.charAt(0) == '-';
                var reg2 = /-/g;
                temp = temp.replace(reg2, '');
                if (hasNegative)
                    temp = '-' + temp;
            }
            if (decimalPlaces != 0) {
                var reg3 = /\./g;
                var reg3Array = reg3.exec(temp);
                if (reg3Array != null) {
// keep only first occurrence of .
// and the number of places specified by decimalPlaces or the entire string if decimalPlaces < 0
                    var reg3Right = temp.substring(reg3Array.index + reg3Array[0].length);
                    reg3Right = reg3Right.replace(reg3, '');
                    reg3Right = decimalPlaces > 0 ? reg3Right.substring(0, decimalPlaces) : reg3Right;
                    temp = temp.substring(0, reg3Array.index) + '.' + reg3Right;
                }
            }
            obj.value = temp;
        }

        function blockNonNumbers(obj, e, allowDecimal, allowNegative) {
            var key;
            var isCtrl = false;
            var keychar;
            var reg;
            if (window.event) {
                key = e.keyCode;
                isCtrl = window.event.ctrlKey
            } else if (e.which) {
                key = e.which;
                isCtrl = e.ctrlKey;
            }
            if (isNaN(key))
                return true;
            keychar = String.fromCharCode(key);
            // check for backspace or delete, or if Ctrl was pressed
            if (key == 8 || isCtrl) {
                return true;
            }
            reg = /\d/;
            var isFirstN = allowNegative ? keychar == '-' && obj.value.indexOf('-') == -1 : false;
            var isFirstD = allowDecimal ? keychar == '.' && obj.value.indexOf('.') == -1 : false;
            return isFirstN || isFirstD || reg.test(keychar);
        }


        function sendcode() {
            //enviar codigo al usuario mediante servicio

            var il_sendcod = getJSON('/util?xaction=SENDCODE&numero=' + document.getElementById('cellnumber').value +
                '&id_session=' + document.getElementById('id_session').value);
            console.log('il_sendcod:' + il_sendcod);
            //POST!!!!SEND CODE!!!!
            if (true) {
                $('#form_validcode').show();
                $('#form_cellnumber').hide();
                //----
                $('#btn_validcode').show();
                $('#btn_sencode').hide();
                alert('Te hemos enviado un codigo para validar.');
            }
        }

        function processCode() {
            var validation = getJSON('/util?xaction=VALIDCODE&numero=' + document.getElementById('cellnumber').value +
                '&id_session=' + document.getElementById('id_session').value +
                '&nu_valcod=' + document.getElementById('nu_valcod').value);
            console.log('il_sendcod:' + validation);
            //POST!!!!SEND CODE!!!!
            if (validation.result.valid) {
                $('#card_valid').hide();
                //----rellenar valores
                $('#form_recovery_greeting').html("Hola " + validation.result.user + ",");
                $('#co_usuari').html("Hola " + validation.result.code + ",");

                $('#card_change').show();

            } else {
                alert('No se pudo validar lo solicitado.');
            }
        }

        function processChange() {
            var validation = getJSON('/util?xaction=CHANGEPWD&numero=' + document.getElementById('cellnumber').value +
                '&id_session=' + document.getElementById('id_session').value +
                '&nu_valcod=' + document.getElementById('nu_valcod').value);
            console.log('il_sendcod:' + validation);
            //POST!!!!SEND CODE!!!!
            if (validation.result.valid) {
                $('#card_valid').hide();
                //----rellenar valores
                $('#form_recovery_greeting').html("Hola " + validation.result.user + ",");
                $('#co_usuari').html("Hola " + validation.result.code + ",");

                $('#card_change').show();

            } else {
                alert('No se pudo validar lo solicitado.');
            }
        }

        function validpwd() {
            // console.log('heres!');
            var p1 = '' + $('#new_password').val();
            var p2 = '' + $('#new_password2').val();
            // console.log('p1:' + p1 + ', p2:' + p2);
            var p1l = p1.length;
            var p2l = p2.length;
            // console.log('p1l:' + p1l + ', p2l:' + p2l);
            var p1v = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})").test(p1);
            var p2v = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})").test(p2);
            var pv = (p1v & p2v);
            var pc = (p1 == p2);
            // console.log('p1v:' + p1v + ', p2v:' + p2v + ',pc:' + pc);

            if ((p1l == 0 & p2l == 0) | (p1l > 0 & p2l == 0) | (p1l == 0 & p2l > 0)) {
                $($('button').get(0)).prop('disabled', true);
                // console.log('@1');
                return;
            } else if (!pv) {
                //mensakje de que las ocntrasenas no cumplen la fuerz
                $($('button').get(0)).prop('disabled', true);
                $('#msg label').html('La/s contraseña/s ingresadas no cumplen con la complejidad requerida.');
                $('#msg').show();
                // console.log('@2');
            } else if (!pc) {
                //la contrasena no nos iguales
                $($('button').get(0)).prop('disabled', true);
                $('#msg label').html('Las contraseñas ingresadas no son iguales.');
                $('#msg').show();
                // console.log('@3');
            } else {
                //si todo sale bien
                $('#msg').hide();
                $('#msg label').html('Las contraseñas ingresadas no son iguales.');
                $("#changepwd").prop('disabled', false);
                // console.log('@4');
            }
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
        <div id="card_valid" class="card" style="width: 400px;">

            <h5 class="card-header info-color white-text text-center py-4">
                <strong>Recupera tu acceso</strong>
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
                <form class="text-center" style="color: #757575;" action="${APP.loginCTRL.updpwd_action}" method="post">

                    <div class="alert alert-info text-left mt-3">
                        <label class="form-check-label" style="font-size: .8rem">
                            Para recuperar el acceso a tu cuenta utilizaremos tu n&uacute;mero registrado. Te enviaremos
                            un codigo de verificacion para continuar con el proceso.
                        </label>
                    </div>


                    <!-- Current Password -${NEED_CHANGE_PASSWORD}-->
                    <input type="hidden" id="id_session" name="id_session" value="${pageContext.session.id}">
                    <%--                    <input type="hidden" name="co_usuari" value="${US.co_usuari}">--%>

                    <div id="form_cellnumber" class="md-form">
                        <input type="text" id="cellnumber" name="cellnumber" class="form-control"
                               onkeyup="extractNumber(this, 0, true);validNumber();"
                               onkeypress="return blockNonNumbers(this, event, false, true);"
                               onblur="extractNumber(this, 0, true); validNumber();" maxlength="9">
                        <label for="cellnumber">N&uacute;mero de celular</label>
                    </div>


                    <div id="form_validcode" class="md-form" style="display: none;">
                        <input type="text" id="nu_valcod" name="validcode" class="form-control"
                               onkeyup="extractNumber(this, 0, true);validCode();"
                               onkeypress="return blockNonNumbers(this, event, false, true);"
                               onblur="extractNumber(this, 0, true); validCode();" maxlength="6">
                        <label for="cellnumber">C&oacute;digo 6 digitos</label>
                    </div>


                    <!-- Enviar codigo-->
                    <button id="btn_sencode" type="button"
                            class="btn btn-primary btn-lg btn-block waves-effect z-depth-0"
                            onclick="sendcode();" disabled>
                        Enviame c&oacute;digo
                    </button>

                    <!--Validar codigo-->
                    <button id="btn_validcode" type="button"
                            class="btn btn-primary btn-lg btn-block waves-effect z-depth-0"
                            onclick="processCode();" style="display: none;" disabled>
                        Validar
                    </button>

                    <hr>
                </form>
                <!-- Form -->

            </div>

        </div>

        <!--CAMBIO DE CLAVE-->
        <div id="card_change" class="card" style="width: 400px;">

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
                <form class="text-center" style="color: #757575;" action="${APP.loginCTRL.updpwd_action}" method="post">

                    <!-- Email -->
                    <input type="hidden" id="co_usuari" name="co_usuari" value="">
                    <%--                    <input type="hidden" name="co_correo" value="{US.co_usuari}">--%>

                    <div class="md-form" style="display: block;height: 30px;">
                        <label id="form_recovery_greeting">Hola </label>
                    </div>

                    <%--                    <c:if test="${empty updpwd_ok}">--%>
                    <div class="md-form">
                        <input type="password" id="new_password" name="new_password" class="form-control"
                               onblur="validpwd()">
                        <label for="new_password">Contrase&ntilde;a nueva</label>
                    </div>

                    <div class="md-form">
                        <input type="password" id="new_password2" name="new_password2" class="form-control"
                               onblur="validpwd()">
                        <label for="new_password">Repite la contrase&ntilde;a nueva</label>
                    </div>

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
                    <%--                    </c:if>--%>

                    <div id="msg" class="alert alert-danger" style="display: none;">
                        <label class="form-check-label" style="font-size: .8rem">
                        </label>
                    </div>

                    <!-- Sign in button -->
                    <button id="changepwd" type="button" class="btn btn-primary btn-lg btn-block waves-effect z-depth-0"
                            onclick="change()" disabled>
                        Actualizar
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

<script type="text/javascript" src="${pageContext.request.contextPath}/jsp_exec/js/wfajax.js"></script>
</body>
</html>
