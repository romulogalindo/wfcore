package com.acceso.wfweb.servlets;

import com.acceso.wfcore.kernel.WFIOAPP;
import com.acceso.wfweb.utils.RequestManager;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Rómulo Galindo
 */
public class OpenServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doProcess(request, response);
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
        doProcess(request, response);
    }

    public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("ISO-8859-1");
        response.setContentType("text/html;charset=ISO-8859-1");

        switch (request.getServletPath()) {
            case "/tahr": {
                //on?-->tahr?type=send&to=user&toid=4654654654654654&xact=message&mact=holamundo
                RequestManager requestManager = new RequestManager(request, response);

                String api_type = requestManager.getParam("type");
                String api_to = requestManager.getParam("to");
                String api_toid = requestManager.getParam("toid");
                String api_xact = requestManager.getParam("xact");
                String api_mact = requestManager.getParam("mact");

                System.out.println("api_type = " + api_type);
                System.out.println("api_to = " + api_to);
                System.out.println("api_toid = " + api_toid);
                System.out.println("api_xact = " + api_xact);
                System.out.println("api_mact = " + api_mact);

                if (api_type.contains("send")) {
                    WFIOAPP.APP.messageService.sendMessageToUser(Long.parseLong(api_toid), api_mact);
                }
                OutputStream out = response.getOutputStream();
                ((ServletOutputStream) out).println("OK");
                out.close();
                break;
            }
        }
    }

    public static final String APITAHR_API_MESSAGE = "message";
    public static final String APITAHR_API_ACTION = "action";
}
