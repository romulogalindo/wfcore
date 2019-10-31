package com.acceso.wfweb.servlets;

import com.acceso.security.DoLogin;
import com.acceso.security.daos.SecurityDAO;
import com.acceso.security.daos.SecurityLDAO;
import com.acceso.wfcore.kernel.WFIOAPP;
import com.acceso.wfcore.utils.Security;
import com.acceso.wfcore.utils.Util;
import com.acceso.wfweb.units.Usuario;
import com.acceso.wfweb.utils.RequestManager;
import org.h2.mvstore.tx.TransactionStore;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author rgalindo En teoria esto debe ser reconocido como un webservice
 */
public class LoginServlet extends HttpServlet {

    public static String LOGINSERVLET_UPPWD64 = "/updpwd64";
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
        String msgRsp = null;

        System.out.println("requestManager.getPath() = " + requestManager.getPath());
        if (requestManager.getPath().contains(LOGINSERVLET_LOGIN64) || requestManager.getPath().contentEquals("/")) {
            try {
                System.out.println("1* => " + 1);
                if (doLogin.SecurityLogin(requestManager)) {
                    System.out.println("Logueo exitoso!");

                    Usuario usuario = doLogin.getUsuario();
                    usuario.setSesion_tomcat_id(request.getSession().getId());
                    //LOGEO EXITOSO

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
                    System.out.println("Logueo Fallido!");
//                    goToUrl = "/wf?co_conten=444";
                    System.out.println("goToUrl = " + goToUrl);
//                    System.out.println("doLogin.getUsuario() = " + doLogin.getUsuario());

                    System.out.println("1** => " + 1 + ",==>" + doLogin.getRegsesiniDTO().getCo_mensaj());
                    System.out.println("1** => " + 1 + ",==>" + doLogin.getRegsesiniDTO().getCo_mensaj());
                    if (doLogin.getRegsesiniDTO().getCo_mensaj() == 404) {
                        //redireccionar al cambio de contrseña TIP_NUEVA CONTRSEÑA
                        request.getSession().invalidate();

                        request.getSession(true);
                        request.getSession().setAttribute("login_error", doLogin.getRegsesiniDTO().getDe_mensaj());
                        System.out.println("(!!*)CHANGE PASSOWERD = " + goToUrl);
                        requestManager.save_over_request("goto", "go!");
                        goToUrl = "/";

                        //tiempo de session por default
//                        int SESSION_TIMEOUT = Integer.parseInt(WFIOAPP.APP.getDataSourceService().getValueOfKey("SESSION_TIMEOUT"), 10);
//                        SESSION_TIMEOUT = SESSION_TIMEOUT * 60;
//                        request.getSession().setMaxInactiveInterval(SESSION_TIMEOUT);
//                        request.getSession().setAttribute("expired_session", SESSION_TIMEOUT);
//                        System.out.println("[LOGIN!]Duraccion de session:" + request.getSession().getMaxInactiveInterval());
//
//                        //deberia darme una linea por default>>>>ejeurl-->444
//                        goToUrl = "/password";
//                        System.out.println("goToUrl = " + goToUrl);
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
        } else if (requestManager.getPath().contains(LOGINSERVLET_UPPWD64)) {
            //procesando cambio de clave
            String p_co_usuari = requestManager.getParam("co_usuari");
            String p_no_correo = requestManager.getParam("p_no_correo");
            String p_ti_cambio = requestManager.getParam("type");
            String p_no_curpwd = requestManager.getParam("current_password");
            String p_no_passwo = requestManager.getParam("new_password");
//            System.out.println("p_co_usuari = " + p_co_usuari);
//            System.out.println("p_no_correo = " + p_no_correo);
//            System.out.println("p_ti_cambio = " + p_ti_cambio);
//            System.out.println("p_no_curpwd = " + p_no_curpwd);
//            System.out.println("p_no_passwo = " + p_no_passwo);

            SecurityLDAO ldao = new SecurityLDAO("cn=admin,dc=acceso,dc=com,dc=pe:4cc3s02019#@192.168.44.138:389");
            SecurityDAO dao = new SecurityDAO();

            if (p_ti_cambio.contentEquals("TYPE1")) {
                //cambio de password forzado(o algo asi)
//                System.out.println("TransactionStore.Change");
                //validar y cambiar pwd en la db
                if (dao.update_password(Util.toInt(p_co_usuari), Security.toMD5(p_no_passwo)) == Security.PASSWORD_CHANGE_OK) {
                    if (ldao.changepwd(p_no_correo, p_no_passwo) == Security.PASSWORD_CHANGE_OK) {
                        msgRsp = "OK";
                        requestManager.save_over_request("goto", "go!");
                        requestManager.save_over_session("updpwd_error", null);
                        requestManager.save_over_session("updpwd_ok", "OK");
                    } else {
                        msgRsp = "LDAP.ERROR. No se pudo cambiar la constraseña.";
                        requestManager.save_over_request("goto", "go!");
                        requestManager.save_over_session("updpwd_error", "LDAP.ERROR. No se pudo cambiar la constraseña.");
                    }
                } else {
                    msgRsp = "ERROR. Contraseña utilizada recientemente. Utilice otra.";
                    requestManager.save_over_request("goto", "go!");
                    requestManager.save_over_session("updpwd_error", "LDAP.ERROR. No se pudo cambiar la constraseña.");
                }

            } else {
                //cambio de clave por que desea hacerlo

            }
//            request.getSession().invalidate();
            goToUrl = "/password";
        } else if (requestManager.getPath().contains(LOGINSERVLET_LOGOUT64)) {
            request.getSession().invalidate();
            goToUrl = "/";
        }

        try {
            System.out.println("Nos vamos a!goToUrl = " + goToUrl);
            if (goToUrl != null) {
                requestManager.redirect(goToUrl);
            } else {
                response.getWriter().println(msgRsp);
                response.flushBuffer();
            }
        } catch (Exception ep) {
            ep.printStackTrace();
        }
    }

}
