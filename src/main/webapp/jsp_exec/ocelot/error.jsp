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
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
    <title>Ups - AIO2</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-amber.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">

    <link rel='shortcut icon' type='image/x-icon'
          href='${pageContext.request.contextPath}/jsp_exec/imgs/defaults/favicon.ico'/>

    <!--CSS-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jsp_exec/css/workflow.css">

    <!--JS -->
    <script src="${pageContext.request.contextPath}/jsp_exec/js/workflow.js?a=6"></script>
    <script src="${pageContext.request.contextPath}/jsp_exec/js/wfajax.js?a=6"></script>
    <style>
        h3 {
            color: black;
            margin: 0px;
            padding: 0px;
            line-height: 20px;
            color:#616161;
        }
        h1 {
            color: black;
        }
    </style>
</head>
<body style="background: #ededed;">

<!-- Sidebar -->
<div class="w3-row w3-padding w3-teal w3-xlarge">
    <div class="w3-col" style="width: 70%;padding: auto 0px;">
        <table>
            <tr>
                <%--<td>--%>
                <%--<button class="w3-button w3-teal w3-xlarge" onclick="w3_open()">--%>
                <%--<i class="fa fa-bars" aria-hidden="true"></i>--%>
                <%--</button>--%>
                <%--</td>--%>
                <td>
                    <div><h1 class="main_title_module">Upss</h1></div>
                    <div class="main_title_breadcrumbs">Al parecer algo salio mal!</div>
                </td>
            </tr>
        </table>
    </div>
</div>

<div class="w3-content ">
    <div class="main_margin_etop" style="background:#ededed;color:white;">

        <div class="w3-row" style="height: 30px;color:black;"></div>
        <%
            String error_message = "" + request.getAttribute("javax.servlet.error.message");
            String servlet_name = "" + request.getAttribute("javax.servlet.error.servlet_name");
            String error_code = "" + request.getAttribute("javax.servlet.error.status_code");
            String error_uri = "" + request.getAttribute("javax.servlet.error.request_uri");
        %>
        <h3>Código:</h3>
        <h1><%=error_code%> - <%=servlet_name%>
        </h1>

        <hr/>

        <h3>Mensaje:</h3>
        <h1>
            <%
                switch (error_code) {
                    case "404":{
                        out.println("No se encontro la ruta solicitada");
                        break;
                    }
                    default: {
                        out.println(error_message);
                        break;
                    }
                }

            %>
        </h1>

        <hr/>

        <h3>Ruta:</h3>
        <h1><%=error_uri%>
        </h1>:
        <%
//            Enumeration<String> attrs = request.getAttributeNames();
//            while (attrs.hasMoreElements()) {
//                out.println("<h1>" + attrs.nextElement() + "</h1>\n");
//            }
//            StringWriter stringWriter = new StringWriter();
//            PrintWriter printWriter = new PrintWriter(stringWriter);
//            exception.printStackTrace(printWriter);
//            out.println(stringWriter);
//            printWriter.close();
//            stringWriter.close();
        %>

    </div>
</div>
</body>
</html>
