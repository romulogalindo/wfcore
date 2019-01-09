/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acceso.wfweb.servlets;

import com.acceso.wfcore.kernel.AsyncRequestProcessor;
import com.acceso.wfcore.listerners.WFCoreListener;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author rgalindo
 */
public class ValpagServlet extends HttpServlet {

    public void do64(HttpServletRequest request, HttpServletResponse response) {

        AsyncContext asyncCtx = request.startAsync();
//        asyncCtx.addListener(new AppAsyncListener());
        asyncCtx.setTimeout(1000);//1 Seg

        WFCoreListener.APP.getExecutor().execute(new AsyncRequestProcessor(asyncCtx, 10000));

        long endTime = System.currentTimeMillis();
        System.out.println("AsyncLongRunningServlet End::Name="
                + Thread.currentThread().getName() + "::ID="
                + Thread.currentThread().getId() + "::Time Taken=");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        do64(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        do64(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
