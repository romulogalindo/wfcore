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

        //System.out.println("LoginServlet.LOGINSERVLET_LOGIN64 = " + LoginServlet.LOGINSERVLET_LOGOUT64);
        //System.out.println("LoginServlet.((HttpServletRequest) request).getServletPath() = " + ((HttpServletRequest) request).getServletPath());
        //ystem.out.println("LoginServlet.eval = " + (((HttpServletRequest) request).getServletPath().contains(LoginServlet.LOGINSERVLET_LOGOUT64)));
        //System.out.println("LoginServlet.eval2 = " + (!((HttpServletRequest) request).getServletPath().contains(LoginServlet.LOGINSERVLET_LOGOUT64)));

        /*Se valida si existe el atributo de session*/
        if (!((HttpServletRequest) request).getServletPath().contains(LoginServlet.LOGINSERVLET_LOGOUT64)) {
            //System.out.println("LoginServlet.securidad = " + ((HttpServletRequest) request).getServletPath());

            if (req.getSession().getAttribute("US") != null) {
                res.sendRedirect("/wf?co_conten=444");
            } else {
                chain.doFilter(req, res);
            }
        } else {
            //System.out.println("LoginServlet.Evaluando path = " + ((HttpServletRequest) request).getServletPath());

            chain.doFilter(req, res);
        }

    }

    @Override
    public void init(FilterConfig fc) throws ServletException {
    }

    @Override
    public void destroy() {
    }


}
