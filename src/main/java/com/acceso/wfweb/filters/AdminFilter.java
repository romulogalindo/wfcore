package com.acceso.wfweb.filters;

import com.acceso.wfweb.servlets.LoginServlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Rómulo Galindo Tanta
 */
public class AdminFilter implements Filter {

    public AdminFilter() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String requesturl = ((HttpServletRequest) request).getRequestURI();

        System.out.println("el admin getServletPath:" + ((HttpServletRequest) request).getServletPath());
        System.out.println("el admin getRequestURI :" + ((HttpServletRequest) request).getRequestURI());
        System.out.println("el admin getContextPath:" + ((HttpServletRequest) request).getContextPath());
        System.out.println("el admin getRequestURL:" + ((HttpServletRequest) request).getRequestURL());
        System.out.println("-----------------------------------");

        /*Se valida si existe el atributo de session*/
        if (requesturl.contentEquals("/admin")) {
            res.sendRedirect("/admin/login");
        } else if (requesturl.contentEquals("/admin/login")) {
            chain.doFilter(req, res);
        } else if (requesturl.contentEquals("/admin/main")) {
            chain.doFilter(req, res);
        } else if (requesturl.endsWith("css") | requesturl.endsWith("js")) {
            chain.doFilter(req, res);
        } else if ((requesturl.startsWith("/admin") | requesturl.startsWith("/admin/jsf_exec")) & !requesturl.endsWith("css")) {
            if (req.getSession().getAttribute("SYS_US") == null) {
                res.sendRedirect("/admin/login");
            } else {
                chain.doFilter(req, res);
            }
        } else {
            chain.doFilter(req, res);
        }
//        if (!((HttpServletRequest) request).getServletPath().contains(LoginServlet.LOGINSERVLET_LOGOUT64)) {
//            //System.out.println("LoginServlet.securidad = " + ((HttpServletRequest) request).getServletPath());
//
//            if (req.getSession().getAttribute("US") != null) {
//                res.sendRedirect("/wf?co_conten=444");
//            } else {
//                chain.doFilter(req, res);
//            }
//        } else {


//        }

    }

    @Override
    public void init(FilterConfig fc) throws ServletException {
    }

    @Override
    public void destroy() {
    }


}
