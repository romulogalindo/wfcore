package com.acceso.wfweb.servlets;

import com.acceso.wfcore.kernel.AsyncMenu;
import com.acceso.wfcore.kernel.AsyncProPag;
import com.acceso.wfcore.kernel.AsyncValPag;
import com.acceso.wfcore.kernel.WFIOAPP;
import com.acceso.wfcore.utils.Values;
import com.acceso.wfweb.units.Contenedor;
import com.acceso.wfweb.units.Usuario;
import com.acceso.wfweb.web.Menu;
import com.acceso.wfweb.web.MenuItem;
import com.acceso.wfweb.web.Root;
import com.acceso.wfweb.web.Sistema;

import java.io.IOException;
import java.net.URLEncoder;
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
        response.setContentType("aplication/json;charset=ISO-8859-1");

        AsyncContext asyncCtx = request.startAsync();
        asyncCtx.setTimeout(100000);//1 Seg

        WFIOAPP.APP.getExecutor().execute(new AsyncProPag(asyncCtx, 10000, 1));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("ISO-8859-1");
        response.setContentType("aplication/json;charset=ISO-8859-1");

        int timeoutAsync = 180 * 1000;//3minutos
        AsyncContext asyncCtx = request.startAsync();
        asyncCtx.setTimeout(timeoutAsync);

        switch (request.getServletPath()) {
            case "/pangolin": {
                //valpag
                WFIOAPP.APP.getExecutor().execute(new AsyncValPag(asyncCtx, 10000, 1));
                break;
            }
            case "/ocelot": {
                //valpag
                WFIOAPP.APP.getExecutor().execute(new AsyncValPag(asyncCtx, 10000, 2));
                break;
            }
            case "/beaver": {
                //propag
                WFIOAPP.APP.getExecutor().execute(new AsyncProPag(asyncCtx, 10000, 1));
                break;
            }
            case "/dingo": {
                //propag
                WFIOAPP.APP.getExecutor().execute(new AsyncProPag(asyncCtx, 10000, 2));
                break;
            }
            case "/uxtion": {
                WFIOAPP.APP.getExecutor().execute(new AsyncMenu(asyncCtx, 10000, 2));
                break;
            }
            case "/salamander": {
                //propag
                String co_conten = request.getParameter("co_conten");
                String id_frawor = request.getParameter("id_frawor");
                String no_conpar = request.getParameter("no_conpar");
                String va_conpar = request.getParameter("va_conpar");
                System.out.println("va_conpar = " + va_conpar);

                try {
                    if (va_conpar.contains("{") | va_conpar.contains("[")) {
                        va_conpar = URLEncoder.encode(va_conpar, "UTF-8");
                    }
//                        String encodeURL = URLEncoder.encode(va_conpar, "UTF-8");
                    System.out.println("va_conpar = " + va_conpar);
                } catch (Exception ep) {
                    ep.printStackTrace();
                }

                ((Contenedor) ((HttpServletRequest) asyncCtx.getRequest()).getSession().getAttribute("CNT" + co_conten + ":" + id_frawor)).put_conpar(no_conpar, va_conpar);

                asyncCtx.complete();
                break;
            }
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
