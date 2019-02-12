<%--
  User: Rómulo Galindo
  Date: 27/12/18
  Time: 17:11
--%>
<jsp:useBean id="paginaBean" class="com.acceso.wfweb.beans.PaginaBean"/>
<%@ page contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1" language="java" %>
<html co_conten="${paginaBean.do64(pageContext.request)}">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
    <title>UNNAMED</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-amber.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">

    <!--CSS-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jsp_exec/css/workflow.css?a=4">

    <!--JS -->
    <script src="${pageContext.request.contextPath}/jsp_exec/js/workflow.js?a=14"></script>
    <script src="${pageContext.request.contextPath}/jsp_exec/js/wfajax.js?a=14"></script>
</head>
<body style="padding: 20px;" onload="pagina();">
<script>
    var height_table = 0;
</script>
<input type="hidden" id="height_table" value="">
<input type="hidden" id="id_frawor" value="${param.id_frawor}">
<input type="hidden" id="co_conten" value="${param.co_conten}">
<input type="hidden" id="co_pagina" value="${param.co_pagina}">

${paginaBean.pagina.toHTML()}

<div id="loader" style="position:fixed; width:100%;height:100%;top: 0;left: 0;background-color: rgba(255,255,255,0.5);">
    <table style="width: 100%;height: 100%">
        <tr>
            <td style="vertical-align:bottom;text-align: center;height: 50%;color: darkgray;">
                <i class="fa fa-cog fa-spin fa-3x fa-fw"></i>
            </td>
        </tr>
        <tr>
            <td style="height: 50%; vertical-align: top;text-align: center">
                Cargando página
            </td>
        </tr>
    </table>
</div>
</body>
</html>
