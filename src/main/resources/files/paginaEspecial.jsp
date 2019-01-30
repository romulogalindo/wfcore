<%@page import="java.nio.charset.Charset"%>
<%@page contentType="text/html; charset=iso-8859-1" pageEncoding="ISO-8859-1"%>
<!--DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"-->
<%@page import="pe.wf3.util.FrameworkUtil"%>
<%@page import="wf3.menu.Paquete"%>
<%@page import="acceso.util.WorkflowUtil"%>
<%@page import="java.util.Enumeration"%>
<%@page import="acceso.beans.PagEspBean"%>
<%@page import="java.util.ArrayList" %>
<%@page import="acceso.util.Escritor"%>
<%@page import="wf.dto.pagesp.PaginaEspecialDto"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
    <%
        request.setCharacterEncoding("ISO-8859-1");
        response.setContentType("text/html;charset=ISO-8859-1");
        
        String[] sparams = java.net.URLDecoder.decode(request.getQueryString(), "ISO-8859-1").split("&");
        String no_docume = "";
        int nu = -1;
        int max = 0;
        ArrayList<String> p = new ArrayList<String>();
        try {
            Paquete paq = FrameworkUtil.searchPackage(request);
            Integer co_conexi = request.getAttribute("co_conexi") != null ? Integer.parseInt(request.getAttribute("co_conexi") + "") : paq.getCo_conexi();
            if (request.getAttribute("pag") != null) {
                PagEspBean pag = (PagEspBean) request.getAttribute("pag");
                String del = "[~]+";
                String[] params = null;
                String[][] req = new String[pag.getLs_parame().size()][2];
                for (int i = 0; i < pag.getLs_parame().size(); i++) {
                    params = pag.getLs_parame().get(i).split(del, 2);
                    req[i][0] = params[0];
                    if (params.length > 1) {
                        req[i][1] = params[1];
                    } else {
                        req[i][1] = "";
                    }
                }
                for (int i = 0; i < req.length; i++) {
                    nu = Integer.parseInt(req[i][0].substring(1));
                    if (nu > max) {
                        max = nu;
                    }
                }
                int aux = 0;
                int pos;
                while (p.size() <= max) {
                    pos = -1;
                    for (int i = 0; i < req.length; i++) {
                        if (Integer.parseInt(req[i][0].substring(1)) == aux) {
                            pos = i;
                        }
                    }
                    if (pos != -1) {
                        p.add(req[pos][1]);
                    } else {
                        p.add(null);
                    }
                    aux = aux + 1;
                }
                no_docume = pag.docume(pag.getCo_docume(), p, co_conexi);
            } else {
                Enumeration en = request.getParameterNames();
                while (en.hasMoreElements()) {
                    String paramName = (String) en.nextElement();
                    if (paramName.startsWith("p")) {
                        nu = Integer.parseInt(paramName.substring(1));
                        if (nu > max) {
                            max = nu;
                        }
                    }
                }
                p.add(null);
                for (int i = 1; i <= max; i++) {
                    String m_value = null;
                    String m1 = "p" + i;
                    for (int o = 0; o < sparams.length; o++) {
                        String e0 = sparams[o];
                        if (e0.indexOf(m1) == 0) {
                            m_value = (e0.split("=").length == 2 ? e0.split("=")[1] : "");
                            o = 190;
                        }
                    }

                    String mp = null;
                    if (m_value != null) {
                        mp = m_value;
                    } else {
                        mp = request.getParameter(m1);
                    }

                    p.add(mp);
                }
                PagEspBean pag = new PagEspBean();
                Escritor.escribe_debug("docume:" + WorkflowUtil.convertir_entero(request.getParameter("co_docume")) + "->" + co_conexi);
                no_docume = pag.docume(WorkflowUtil.convertir_entero(request.getParameter("co_docume")), p, co_conexi);
            }
        } catch (Exception e) {
            Escritor.escribe_errors(e.toString());
            e.printStackTrace();
        }
    %>
    <%=no_docume%>
    <%--<c:if test="${param.imprimir == 1}">--%>
    <!--<script>window.print();</script>-->
    <%--</c:if>--%>
<!--    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <meta HTTP-EQUIV="Refresh" CONTENT="0.5; URL=http://workflow.accesocrediticio.com/acceso/reportes/paginaEspecial.jsp?co_docume=${param.co_docume}&p1=${param.p1}&p2=${param.p2}&p3=${param.p3}&p4=${param.p4}&p5=${param.p5}&p6=${param.p6}&p7=${param.p7}&p8=${param.p8}&p9=${param.p9}&p10=${param.p10}&p11=${param.p11}&p12=${param.p12}&p13=${param.p13}&p14=${param.p14}&p15=${param.p15}" />
    </head>
    <body>
    </body>-->
</html>