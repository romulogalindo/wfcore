package com.acceso.wfweb.filters;

import com.acceso.wfcore.utils.Util;
import com.acceso.wfweb.units.Usuario;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
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
public class MainFilter implements Filter {

    public MainFilter() {
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        System.out.println("[**]request = " + req.getContextPath());
        System.out.println("[**]request = " + req.getRequestURL());
        System.out.println("[**]request = " + req.getQueryString());
        System.out.println("[**]request = " + req.getParameter("co_conten"));

        Integer co_conten = Util.toInt(req.getParameter("co_conten"), -1);
        System.out.println("[**]co_conten = " + co_conten);

        Usuario usuario = (Usuario) req.getSession().getAttribute("US");
        System.out.println("[**]usuario = " + usuario);

        if (usuario != null) {
            System.out.println("!!!suario.getLs_mensis().get(co_conten) = " + usuario.getLs_mensis().get(co_conten));
//            if (co_conten == 444 | usuario.getLs_mensis().get(co_conten) != null) {
//                System.out.println("validado = ");
//                chain.doFilter(req, res);
//            } else {
//                System.out.println("Error escribir");
//                throw new ServletException("No tiene permiso de acceso. Comuniquese con la administraciòn");
//            }
            chain.doFilter(req, res);
            //existe osea si se puede loguear

        } else {
            System.out.println("validado = No!");
            res.sendRedirect("/");
        }

//        if (req.getSession(true).getAttribute("US") == null) {
//            res.sendRedirect("/");
//        } else {
//            chain.doFilter(req, res);
//        }
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

}
