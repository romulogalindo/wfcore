package com.acceso.wfweb.servlets;

import com.acceso.wfweb.utils.RequestManager;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author rgalindo
 */
public class PaginaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher("/jsp_exec/ocelot/pagina.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher("/jsp_exec/ocelot/pagina.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
