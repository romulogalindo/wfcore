package com.acceso.wfweb.servlets;

import com.acceso.security.DoLogin;
import com.acceso.wfweb.utils.RequestManager;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author rgalindo En teoria esto debe ser reconocido como un webservice
 */
public class LoginServlet extends HttpServlet {

    public static String LOGINSERVLET_LOGIN64 = "/login64";
    public static String LOGINSERVLET_LOGOUT64 = "/logout64";
    public static String LOGINSERVLET_GETPACKAGE = "/package64";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestManager requestManager = new RequestManager(request, response);

        if (requestManager.getPath().contentEquals(LOGINSERVLET_LOGIN64)) {
            //Validar parametros
        } else if (requestManager.getPath().contentEquals(LOGINSERVLET_LOGOUT64)) {
            request.getSession().invalidate();
            try {
                requestManager.redirect("/");
            } catch (Exception ep) {
                ep.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestManager requestManager = new RequestManager(request, response);
        DoLogin doLogin = new DoLogin();
        String goToUrl = "/";

        if (requestManager.getPath().contains(LOGINSERVLET_LOGIN64) || requestManager.getPath().contentEquals("/")) {
            try {
                System.out.println("1* => " + 1);
                if (doLogin.SecurityLogin(requestManager)) {
                    System.out.println("(*)goToUrl = " + goToUrl);
                    requestManager.save_over_request("goto", "go!");
                    requestManager.save_over_session("US", doLogin.getUsuario());
                    //deberia darme una linea por default>>>>ejeurl-->444
                    goToUrl = "/wf?co_conten=444";
                    System.out.println("goToUrl = " + goToUrl);
                    System.out.println("doLogin.getUsuario() = " + doLogin.getUsuario());
                } else {
                    System.out.println("1** => " + 1);
                    throw new Exception(doLogin.getMessage());
                }

            } catch (Exception ep) {
                requestManager.save_over_request("goto", "go!");
                requestManager.save_over_session("login_error", ep.getMessage());
                goToUrl = "/";
            }
        } else if (requestManager.getPath().contains(LOGINSERVLET_LOGOUT64)) {
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
