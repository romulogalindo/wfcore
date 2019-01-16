/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acceso.wfweb.servlets;

import com.acceso.security.DoLogin;
import com.acceso.wfweb.utils.RequestManager;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author rgalindo
 * En teoria esto debe ser reconocido como un webservice
 */
public class LoginServlet extends HttpServlet {
    public static String LOGINSERVLET_LOGIN64 = "/login64";
    public static String LOGINSERVLET_LOGOUT64 = "/logout64";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestManager requestManager = new RequestManager(request, response);

        if (requestManager.getPath().contentEquals(LOGINSERVLET_LOGIN64)) {
            //Validar parametros

        } else {
            //forzarlos a irse a la pagina del login
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestManager requestManager = new RequestManager(request, response);
        DoLogin doLogin = new DoLogin();
        String goToUrl = "/";

        System.out.println(">>>>" + requestManager.getPath() + "vs" + LOGINSERVLET_LOGIN64 + "--->" + requestManager.getPath().contains(LOGINSERVLET_LOGIN64));

        if (requestManager.getPath().contains(LOGINSERVLET_LOGIN64) || requestManager.getPath().contentEquals("/")) {
            //Validar parametros
            if (doLogin.SecurityLogin(requestManager)) {
                //todo bien y redirigir
                //crear objeto de usuario y guardar en session!!!
                //doLogin.getUsuario();
                requestManager.save_over_request("goto", "go!");
                requestManager.save_over_session("US", doLogin.getUsuario());
                //deberia darme una linea por default>>>>ejeurl-->444
                goToUrl = "/main?co_conten=444";
            } else {
                //redigir pero mensaje de error
                requestManager.save_over_request("goto", "go!");
                requestManager.save_over_session("login_error", doLogin.getMessage());

                goToUrl = "/";
            }
        }else if(requestManager.getPath().contains(LOGINSERVLET_LOGOUT64)){
            request.getSession().invalidate();
            goToUrl = "/";
        }

        try {
            requestManager.redirect(goToUrl);
        } catch (Exception ep) {
            ep.printStackTrace();
        }
    }


}
