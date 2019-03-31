package com.acceso.wfweb.servlets;

import com.acceso.wfcore.kernel.AsyncMessage;
import com.acceso.wfcore.kernel.AsyncProPag;
import com.acceso.wfcore.kernel.AsyncValPag;
import com.acceso.wfcore.listerners.WFCoreListener;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rómulo Galindo
 */
public class OpenServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("ISO-8859-1");
        response.setContentType("text/html;charset=ISO-8859-1");

        AsyncContext asyncCtx = request.startAsync();
        asyncCtx.setTimeout(100000);//1 Seg

        WFCoreListener.APP.getExecutor().execute(new AsyncMessage(asyncCtx, 10000));
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("ISO-8859-1");
        response.setContentType("text/html;charset=ISO-8859-1");

        AsyncContext asyncCtx = request.startAsync();
        asyncCtx.setTimeout(100000);//10 Seg

        switch (request.getServletPath()) {
            case "/pangolinx": {
                //valpag
                WFCoreListener.APP.getExecutor().execute(new AsyncMessage(asyncCtx, 10000));
                break;
            }
            case "/beaverx": {
                //propag
                WFCoreListener.APP.getExecutor().execute(new AsyncMessage(asyncCtx, 10000));
                break;
            }
        }
    }

}
