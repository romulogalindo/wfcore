package com.acceso.wfweb.servlets;

import com.acceso.wfcore.kernel.AsyncProPag;
import com.acceso.wfcore.kernel.AsyncValPag;
import com.acceso.wfcore.listerners.WFCoreListener;

import java.io.IOException;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Rómulo Galindo Tanta
 */
public class ValpagServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("ISO-8859-1");
//        response.setContentType("text/html;charset=ISO-8859-1");
        response.setContentType("aplication/json;charset=ISO-8859-1");

        AsyncContext asyncCtx = request.startAsync();
        asyncCtx.setTimeout(100000);//1 Seg

        WFCoreListener.APP.getExecutor().execute(new AsyncProPag(asyncCtx, 10000, 1));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("ISO-8859-1");
//        response.setContentType("text/html;charset=ISO-8859-1");
        response.setContentType("aplication/json;charset=ISO-8859-1");

        AsyncContext asyncCtx = request.startAsync();
        asyncCtx.setTimeout(100000);//10 Seg

        switch (request.getServletPath()) {
            case "/pangolin": {
                //valpag
                WFCoreListener.APP.getExecutor().execute(new AsyncValPag(asyncCtx, 10000, 1));
                break;
            }
            case "/ocelot": {
                //valpag
                WFCoreListener.APP.getExecutor().execute(new AsyncValPag(asyncCtx, 10000, 2));
                break;
            }
            case "/beaver": {
                //propag
                WFCoreListener.APP.getExecutor().execute(new AsyncProPag(asyncCtx, 10000, 1));
                break;
            }
            case "/dingo": {
                //propag
                WFCoreListener.APP.getExecutor().execute(new AsyncProPag(asyncCtx, 10000, 2));
                break;
            }
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
