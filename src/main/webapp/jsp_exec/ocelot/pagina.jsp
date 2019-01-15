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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jsp_exec/css/workflow.css?a=3">

    <!--JS -->
    <script src="${pageContext.request.contextPath}/jsp_exec/js/workflow.js?a=4"></script>
    <script src="${pageContext.request.contextPath}/jsp_exec/js/wfajax.js?a=4"></script>
</head>
<body style="padding: 5px 10px 20px;" onload="pagina()">
<script>
    var height_table = 0;
</script>
<input type="hidden" id="height_table" value="">
<input type="hidden" id="id_frawor" value="${param.id_frawor}">
<input type="hidden" id="co_conten" value="${param.co_conten}">
<input type="hidden" id="co_pagina" value="${param.co_pagina}">

${paginaBean.pagina.toHTML()}
</body>
</html>
