<%--
  User: Rómulo Galindo
  Date: 27/12/18
  Time: 17:11
--%>
<jsp:useBean id="paginaBean" class="com.acceso.wfweb.beans.PaginaBean"/>
<%@ page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" language="java" %>
<html co_conten="${paginaBean.do64(pageContext.request)}">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
    <title>UNNAMED</title>
    <!--        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
            <link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-amber.css">
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
            <link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">-->

    <!--CSS-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jsp_exec/css/workflow.css?a=8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jsp_exec/js/jscalendar/calendar-win2k-cold-1.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jsp_exec/css/mdb_acr.css?a=1">
    <%--<link href="https://mdbootstrap.com/wp-content/themes/mdbootstrap4/css/compiled-4.7.4.min.css" rel="stylesheet">--%>

    <!--JS -->
    <script src="${pageContext.request.contextPath}/jsp_exec/js/pagina.js?a=35" charset="ISO-8859-1"></script>
    <script src="${pageContext.request.contextPath}/jsp_exec/js/wfajax.js?a=35" charset="ISO-8859-1"></script>
    <script src="${pageContext.request.contextPath}/jsp_exec/js/jscalendar/calendar.js"></script>
    <script src="${pageContext.request.contextPath}/jsp_exec/js/jscalendar/calendar-setup.js"></script>
    <script src="${pageContext.request.contextPath}/jsp_exec/js/jscalendar/lang/calendar-es.js"></script>

    <style>
        body {
            background-color: unset;
        }

        table td {
            border-top: unset !important;
        }

        table tbody td {
            /*border-bottom: 1px solid #dee2e6 !important;*/
            border-bottom: none !important;
        }

        .btn {
            margin: 0px;
            padding: .30rem 1.02rem;
        }

        #mainpagina select {
            display: unset !important;
            width: 95%;
        }
    </style>
</head>
<body style="padding: 20px;" onload="pagina();">
<script>
    var height_table = 0;
</script>
<input type="hidden" id="height_table" value="">
<input type="hidden" id="id_frawor" value="${param.id_frawor}">
<input type="hidden" id="co_conten" value="${param.co_conten}">
<input type="hidden" id="co_pagina" value="${param.co_pagina}">

<div id="mainpagina" class="card card-cascade narrower">
    <!--Card image-->
    <div class="view view-cascade gradient-card-header default-color narrower py-2 mx-4 mb-3 d-flex justify-content-between align-items-center">

        <!--                <div>
                            <button type="button" class="btn btn-outline-white btn-rounded btn-sm px-2">
                                <i class="fas fa-th-large mt-0"></i>
                            </button>
                            <button type="button" class="btn btn-outline-white btn-rounded btn-sm px-2">
                                <i class="fas fa-columns mt-0"></i>
                            </button>
                        </div>-->

        <span style="width: 100%;" class="white-text mx-3">${paginaBean.pagina.no_pagtit}</span>

        <!--                <div>
                            <button type="button" class="btn btn-outline-white btn-rounded btn-sm px-2">
                                <i class="fas fa-pencil-alt mt-0"></i>
                            </button>
                            <button type="button" class="btn btn-outline-white btn-rounded btn-sm px-2">
                                <i class="far fa-trash-alt mt-0"></i>
                            </button>
                            <button type="button" class="btn btn-outline-white btn-rounded btn-sm px-2">
                                <i class="fas fa-info-circle mt-0"></i>
                            </button>
                        </div>-->

    </div>
    <!--/Card image-->

    <div class="px-4">

        <div class="table-wrapper">
            ${paginaBean.pagina.toHTML()}
        </div>

    </div>

</div>

<div id="loader"
     style="position:fixed; width:100%;height:300px;top: 0;left: 0;background-color: rgba(238,238,238,0.7);">

    <table id="content_table_loader" style="width: 100%;height: 100%">
        <tr>
            <td style="vertical-align:bottom;text-align: center;height: 50%;color: darkgray; border:none !important;">
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
            </td>
        </tr>
        <tr>
            <td style="height: 50%; vertical-align: top;text-align: center;font-size: 1.2em;font-weight: bold;">
                Cargando página...
            </td>
        </tr>
    </table>

    <div id="card_error" class="card" style="display: none;margin: 5% 10%;">
        <div class="card-body">
            <h5 id="title_error" class="card-title" style="color:red;">Error - </h5>
            <p id="detail_error" class="card-text" style="font-size: 0.8em;">Some quick example text to build on the panel title and make up the
                bulk of the panel's content.</p>
            <%--<a id="linka_error" class="card-link">Card link</a>--%>
            <%--<a class="card-link">Another link</a>--%>
        </div>
    </div>
</div>

<%--<script type="text/javascript" src="https://mdbootstrap.com/wp-content/themes/mdbootstrap4/js/compiled-4.7.4.js"></script>--%>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp_exec/js/mdb_acr.js"></script>
</body>
</html>
