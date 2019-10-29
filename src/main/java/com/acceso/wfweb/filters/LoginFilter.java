package com.acceso.wfweb.filters;

import com.acceso.wfweb.servlets.LoginServlet;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Rómulo Galindo Tanta
 */
public class LoginFilter implements Filter {

    public LoginFilter() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        System.out.println("[LoginFilter]getServletPath = " + req.getServletPath() + ", getRequestURL = " + req.getRequestURL());

        /*Se valida si existe el atributo de session*/
        if (req.getServletPath().contains(LoginServlet.LOGINSERVLET_LOGOUT64)) {
//            System.out.println("*LoginServlet.securidad = " + req.getServletPath());

//            if (req.getSession().getAttribute("US") != null) {
//                res.sendRedirect("/wf?co_conten=444");
//            } else {
//                chain.doFilter(req, res);
//            }

            System.out.println("**LoginServlet.Evaluando path = " + req.getServletPath());

            chain.doFilter(req, res);
        } else if (req.getServletPath().contains(LoginServlet.LOGINSERVLET_UPPWD64)) {
            System.out.println("**LoginServlet.Evaluando path = " + req.getServletPath());
            chain.doFilter(req, res);
        } else {
            System.out.println("***LoginServlet.Evaluando path = " + req.getServletPath());
//
//            chain.doFilter(req, res);
            if (req.getSession().getAttribute("US") != null) {
                res.sendRedirect("/wf?co_conten=444");
            } else {
                chain.doFilter(req, res);
            }
        }

    }

    @Override
    public void init(FilterConfig fc) throws ServletException {
    }

    @Override
    public void destroy() {
    }


}
