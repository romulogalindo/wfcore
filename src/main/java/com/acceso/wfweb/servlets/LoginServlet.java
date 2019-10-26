package com.acceso.wfweb.servlets;

import com.acceso.security.DoLogin;
import com.acceso.wfcore.kernel.WFIOAPP;
import com.acceso.wfcore.utils.Util;
import com.acceso.wfweb.units.Usuario;
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
                    Usuario usuario = doLogin.getUsuario();
                    usuario.setSesion_tomcat_id(request.getSession().getId());

                    if (Util.TODAYS_OVER_DATE(usuario.getFe_updpas(), Integer.parseInt(WFIOAPP.APP.getDataSourceService().getValueOfKey("SESSION_TIMEOUT"), 30))) {
                        System.out.println("(*)goToUrl = " + goToUrl);
                        requestManager.save_over_request("goto", "go!");
                        requestManager.save_over_session("US", doLogin.getUsuario());
                        requestManager.save_over_session("NEED_CHANGE_PASSWORD", false);

                        //tiempo de session por default
                        int SESSION_TIMEOUT = Integer.parseInt(WFIOAPP.APP.getDataSourceService().getValueOfKey("SESSION_TIMEOUT"), 10);
                        SESSION_TIMEOUT = SESSION_TIMEOUT * 60;
                        request.getSession().setMaxInactiveInterval(SESSION_TIMEOUT);
                        request.getSession().setAttribute("expired_session", SESSION_TIMEOUT);
                        System.out.println("[LOGIN!]Duraccion de session:" + request.getSession().getMaxInactiveInterval());

                        //deberia darme una linea por default>>>>ejeurl-->444
                        goToUrl = "/wf?co_conten=444";
                        System.out.println("goToUrl = " + goToUrl);
                        System.out.println("doLogin.getUsuario() = " + doLogin.getUsuario());
                    } else {
                        System.out.println("(*)CHANGE PASSOWERD = " + goToUrl);
                        requestManager.save_over_request("goto", "go!");
                        requestManager.save_over_session("US", doLogin.getUsuario());
                        requestManager.save_over_session("NEED_CHANGE_PASSWORD", true);

                        //tiempo de session por default
                        int SESSION_TIMEOUT = Integer.parseInt(WFIOAPP.APP.getDataSourceService().getValueOfKey("SESSION_TIMEOUT"), 10);
                        SESSION_TIMEOUT = SESSION_TIMEOUT * 60;
                        request.getSession().setMaxInactiveInterval(SESSION_TIMEOUT);
                        request.getSession().setAttribute("expired_session", SESSION_TIMEOUT);
                        System.out.println("[LOGIN!]Duraccion de session:" + request.getSession().getMaxInactiveInterval());

                        //deberia darme una linea por default>>>>ejeurl-->444
                        goToUrl = "/wf?co_conten=444";
                        System.out.println("goToUrl = " + goToUrl);
                        System.out.println("doLogin.getUsuario() = " + doLogin.getUsuario());
                    }


                } else {
                    System.out.println("1** => " + 1 + ",==>" + doLogin.getRegsesiniDTO().getCo_mensaj());
                    if (doLogin.getRegsesiniDTO().getCo_mensaj() == 404) {
                        //redireccionar al cambio de contrseña TIP_NUEVA CONTRSEÑA
                        System.out.println("(!!*)CHANGE PASSOWERD = " + goToUrl);
                        requestManager.save_over_request("goto", "go!");
//                        requestManager.save_over_session("US", doLogin.getUsuario());
                        requestManager.save_over_session("US", doLogin.getRegsesiniDTO());
                        requestManager.save_over_session("NEED_CHANGE_PASSWORD", "TYPE1");

                        //tiempo de session por default
                        int SESSION_TIMEOUT = Integer.parseInt(WFIOAPP.APP.getDataSourceService().getValueOfKey("SESSION_TIMEOUT"), 10);
                        SESSION_TIMEOUT = SESSION_TIMEOUT * 60;
                        request.getSession().setMaxInactiveInterval(SESSION_TIMEOUT);
                        request.getSession().setAttribute("expired_session", SESSION_TIMEOUT);
                        System.out.println("[LOGIN!]Duraccion de session:" + request.getSession().getMaxInactiveInterval());

                        //deberia darme una linea por default>>>>ejeurl-->444
                        goToUrl = "/password";
                        System.out.println("goToUrl = " + goToUrl);
//                        System.out.println("doLogin.getUsuario() = " + doLogin.getUsuario());
                    } else {
                        throw new Exception(doLogin.getMessage());
                    }

                }

            } catch (Exception ep) {
                System.out.println("ep = " + ep);
                ep.printStackTrace();
                requestManager.save_over_request("goto", "go!");
                requestManager.save_over_session("login_error", ep.getMessage());
                goToUrl = "/";
            }
        } else if (requestManager.getPath().contains(LOGINSERVLET_LOGOUT64)) {
            request.getSession().invalidate();
            goToUrl = "/";
        }

        try {
            System.out.println("Nos vamos a!goToUrl = " + goToUrl);
            requestManager.redirect(goToUrl);
        } catch (Exception ep) {
            ep.printStackTrace();
        }
    }

}
