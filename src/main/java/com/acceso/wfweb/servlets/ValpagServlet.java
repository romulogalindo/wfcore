/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acceso.wfweb.servlets;

import com.acceso.wfcore.kernel.AsyncProPag;
import com.acceso.wfcore.kernel.AsyncProcessor;
import com.acceso.wfcore.kernel.AsyncValPag;
import com.acceso.wfcore.listerners.WFCoreListener;
import com.acceso.wfweb.utils.RequestManager;

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
//
//    public void do64(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("ISO-8859-1");
//        response.setContentType("text/html;charset=ISO-8859-1");
//
//        AsyncContext asyncCtx = request.startAsync();
//        asyncCtx.setTimeout(100000);//1 Seg
//
//        WFCoreListener.APP.getExecutor().execute(new AsyncValPag(asyncCtx, 10000));
//
//    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //do64(request, response);
        request.setCharacterEncoding("ISO-8859-1");
        response.setContentType("text/html;charset=ISO-8859-1");

        AsyncContext asyncCtx = request.startAsync();
        asyncCtx.setTimeout(100000);//1 Seg

        WFCoreListener.APP.getExecutor().execute(new AsyncProPag(asyncCtx, 10000));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //do64(request, response);
        request.setCharacterEncoding("ISO-8859-1");
        response.setContentType("text/html;charset=ISO-8859-1");

        AsyncContext asyncCtx = request.startAsync();
        asyncCtx.setTimeout(100000);//1 Seg

        RequestManager requestManager = new RequestManager(request, response);
        switch (requestManager.getPath()) {
            case "/pangolin": {
                //valpag
                WFCoreListener.APP.getExecutor().execute(new AsyncValPag(asyncCtx, 10000));
                break;
            }
            case "/beaver": {
                //propag
                WFCoreListener.APP.getExecutor().execute(new AsyncProPag(asyncCtx, 10000));
                break;
            }
        }


        //WFCoreListener.APP.getExecutor().execute(asyncProcessor);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
