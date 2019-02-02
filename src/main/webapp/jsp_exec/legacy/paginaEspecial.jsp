<%@page import="java.nio.charset.Charset" %>
<%@page contentType="text/html; charset=iso-8859-1" pageEncoding="ISO-8859-1" %>
<!--DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"-->
<%@page import="java.util.Enumeration" %>
<%@page import="java.util.ArrayList" %>
<%@ page import="com.acceso.wfweb.beans.legacy.PagEspBean" %>
<%@ page import="com.acceso.wfcore.utils.Util" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<%
    request.setCharacterEncoding("ISO-8859-1");
    response.setContentType("text/html;charset=ISO-8859-1");

//    System.out.println("request.getQueryString() = " + request.getQueryString());
    String[] sparams;
    try {
        sparams = java.net.URLDecoder.decode(request.getQueryString(), "ISO-8859-1").split("&");
    } catch (Exception ep) {
        sparams = new String[0];
        ep.printStackTrace();
    }

    String no_docume = "";
    int nu = -1;
    int max = 0;
    ArrayList<String> p = new ArrayList<>();

    try {
        Integer co_conexi = -1;//request.getAttribute("co_conexi") != null ? Integer.parseInt(request.getAttribute("co_conexi") + "") : paq.getCo_conexi();
//        System.out.println("request.getAttribute(\"pag\") = > " + request.getAttribute("pag"));

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

//            System.out.println("LA>p = " + p);

            PagEspBean pag = new PagEspBean();
//                Escritor.escribe_debug("docume:" + WorkflowUtil.convertir_entero(request.getParameter("co_docume")) + "->" + co_conexi);
            no_docume = pag.docume(Util.toInt(request.getParameter("co_docume")), p, co_conexi);
//            System.out.println("no_docume = " + no_docume);
        }
    } catch (Exception ep) {
//        System.out.println("ep = " + ep);
        ep.printStackTrace();
    }
%>
<%=no_docume%>
</html>