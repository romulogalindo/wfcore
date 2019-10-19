<%--
  User: Rómulo Galindo
  Date: 27/12/18
  Time: 17:11
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="paginaBean" class="com.acceso.wfweb.beans.PaginaBean"/>
<%@ page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" language="java" %>
<html co_conten="${paginaBean.do64(pageContext.request)}">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
    <title>UNNAMED</title>
    <!--CSS${paginaBean.pagina.ti_pagina}-->
    <%--    <c:if test="${paginaBean.pagina.ti_pagina != 'Y'}">--%>
    <link id="optionalcss" rel="stylesheet" href="${pageContext.request.contextPath}/jsp_exec/css/workflow.css?a=16">
    <%--    </c:if>--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jsp_exec/js/jscalendar/calendar-win2k-cold-1.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jsp_exec/css/mdb_acr.css?a=9    ">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jsp_exec/css/themes/theme-${US.no_temdef}.css?a=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jsp_exec/css/mdb/addons/datatables.css?a=5">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jsp_exec/css/mdb/addons/datatables-select.css">
    <%--<link href="https://mdbootstrap.com/wp-content/themes/mdbootstrap4/css/compiled-4.7.4.min.css" rel="stylesheet">--%>

    <!--JS -->
    <script src="${pageContext.request.contextPath}/jsp_exec/js/pagina.js?b=${paginaBean.sys()}"
            charset="ISO-8859-1"></script>
    <script src="${pageContext.request.contextPath}/jsp_exec/js/wfajax.js?a=${paginaBean.sys()}"
            charset="ISO-8859-1"></script>
    <script src="${pageContext.request.contextPath}/jsp_exec/js/jscalendar/calendar.js"></script>
    <script src="${pageContext.request.contextPath}/jsp_exec/js/jscalendar/calendar-setup.js"></script>
    <script src="${pageContext.request.contextPath}/jsp_exec/js/jscalendar/lang/calendar-es.js"></script>

    <style>
        body {
            background-color: unset;
            overflow: hidden;
        }

        table td {
            border-top: unset !important;
        }

        table tbody td {
            border-bottom: none !important;
        }

    </style>
</head>
<body style="padding: 16px 5px;" onload="pagina();">
<script>
    var height_table = 0;
</script>
<input type="hidden" id="height_table" value="">
<input type="hidden" id="id_frawor" value="${param.id_frawor}">
<input type="hidden" id="co_conten" value="${param.co_conten}">
<input type="hidden" id="co_pagina" value="${param.co_pagina}">
<input type="hidden" id="il_popup" value="${param.il_popup}">

<c:if test="${paginaBean.pagina.ti_pagina == 'B'}">
    <div id="mainpagina">
            ${paginaBean.pagina.toHTML()}
    </div>
</c:if>
<c:if test="${paginaBean.pagina.ti_pagina != 'B'}">
    <div id="mainpagina" class="card card-cascade narrower">
        <div class="view view-cascade gradient-card-header default-color narrower py-2 mx-4 mb-3 d-flex justify-content-between align-items-center">

            <div style="display: inherit;">
                <button id="pagopt_info" type="button" title="Información de la página."
                        class="btn btn-outline-white btn-rounded btn-sm px-2"
                        style="visibility: hidden; width: 25px;padding-right: 0.2rem !important;padding-left: 0.15rem !important;height: 23px;padding-top: 0.1rem !important;padding-bottom: 0.1rem !important;text-align: center;margin: 0px !important;"
                        onclick="alert('Página: ' + CO_PAGINA);">
                    <i class="fas fa-info-circle mt-0" style="font-size: 0.9rem;"></i>
                </button>
                <button id="pagopt_plus" type="button" title="Nuevo registro"
                        class="btn btn-outline-white btn-rounded btn-sm px-2"
                        style="visibility: hidden;width: 25px;padding-right: 0.2rem !important;padding-left: 0.15rem !important;height: 23px;padding-top: 0.1rem !important;padding-bottom: 0.1rem !important;text-align: center;margin: 0px !important;">
                    <i class="fas fa-plus-circle mt-0" style="font-size: 0.9rem;"></i>
                </button>
            </div>

            <span style="width: 100%;padding: 0px !important;"
                  class="maintitle white-text mx-3">${paginaBean.pagina.no_pagtit}</span>

            <div style="display: inherit;">
                    <%--                <button type="button" class="btn btn-outline-white btn-rounded btn-sm px-2"--%>
                    <%--                        style="visibility: hidden; width: 25px;padding-right: 0.2rem !important;padding-left: 0.15rem !important;height: 23px;padding-top: 0.1rem !important;padding-bottom: 0.1rem !important;text-align: center;margin: 0px !important;">--%>
                    <%--                    <i class="far fa-trash-alt mt-0" style="font-size: 0.9rem;"></i>--%>
                    <%--                </button>--%>
                <button id="pagopt_xls" type="button" title="Exportar archivo XLS"
                        class="btn btn-outline-white btn-rounded btn-sm px-2"
                        style="visibility: hidden; width: 25px;padding-right: 0.2rem !important;padding-left: 0.15rem !important;height: 23px;padding-top: 0.1rem !important;padding-bottom: 0.1rem !important;text-align: center;margin: 0px !important;"
                >
                    <i class="fas fa-file-excel mt-0" style="font-size: 0.9rem;"></i>
                </button>
                <button id="pagopt_print" type="button" title="Imprimir"
                        class="btn btn-outline-white btn-rounded btn-sm px-2"
                        style="visibility: hidden; width: 25px;padding-right: 0.2rem !important;padding-left: 0.15rem !important;height: 23px;padding-top: 0.1rem !important;padding-bottom: 0.1rem !important;text-align: center;margin: 0px !important;"
                        onclick="print()">
                    <i class="fas fa-print mt-0" style="font-size: 0.9rem;"></i>
                </button>
            </div>

        </div>

        <div id="mainconten" class="px-4">

            <div class="table-wrapper" style="margin-bottom: 12px;">
                    ${paginaBean.pagina.toHTML()}
            </div>
            <script>
                if (document.getElementById('ti_pagina').value == 'Y') {
                    document.getElementById('optionalcss').setAttribute('href', '//');
                }
            </script>
            <style>
                #PAG9215{

                }
            </style>
            <div id="context-menu" class="dropdown-menu dropdown-menu-sm context-menu-list css-title context-menu-root">
            </div>
        </div>

    </div>

</c:if>


<div id="loader" style="position:fixed; width:100%;height:300px;top: 0;left: 0;background-color: rgba(238,238,238,1);">

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
            <p id="detail_error" class="card-text" style="font-size: 0.8em;">Some quick example text to build on the
                panel title and make up the
                bulk of the panel's content.</p>
        </div>
    </div>
</div>

<iframe id="downloader" url="" style="display: none;">
</iframe>
<%--<script type="text/javascript" src="https://mdbootstrap.com/wp-content/themes/mdbootstrap4/js/compiled-4.7.4.js"></script>--%>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp_exec/js/mdb_acr.js?a=1"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp_exec/js/mdb/popper.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/jsp_exec/js/mdb/addons/datatables.js?a=3"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp_exec/js/FileSaver.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp_exec/js/shim.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp_exec/js/exportxlsx.js"></script>

<c:if test="${paginaBean.pagina.ti_pagina == 'C'}">
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script>
        google.charts.load("current", {packages: ["corechart"]});
    </script>
</c:if>
</body>
</html>
