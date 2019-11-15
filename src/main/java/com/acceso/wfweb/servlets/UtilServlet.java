package com.acceso.wfweb.servlets;

import com.acceso.security.daos.SecurityDAO;
import com.acceso.security.daos.SecurityLDAO;
import com.acceso.wfcore.apis.HttpAPI;
import com.acceso.wfcore.kernel.WFIOAPP;
import com.acceso.wfcore.utils.ErrorMessage;
import com.acceso.wfcore.utils.Util;
import com.acceso.wfweb.utils.JsonResponse;
import com.acceso.wfweb.utils.RecoveryUnit;
import com.google.gson.Gson;
import org.h2.tools.Recover;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UtilServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }

    public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("ISO-8859-1");
        response.setContentType("text/html;charset=ISO-8859-1");
        String xaction = request.getParameter("xaction").toUpperCase();
        switch (xaction) {
            case "SENDCODE": {
                String numero = request.getParameter("numero").toUpperCase();
                boolean il_havuse = false;
                String uid = null;
                Map<String, String> values;
                il_havuse = true;
                /*BUCAR LDAP USER*/
                SecurityLDAO ldao = new SecurityLDAO(WFIOAPP.APP.getDataSourceService().getValueOfKey("URL_LDAP_ADMIN"));
                ldao.connect();
                values = ldao.search(numero);
                ldao.close();

//                if (il_havuse) {/
                if (values.size() == 2) {
                    String sesion = request.getParameter("id_session").toUpperCase();
                    String joinco = Util.getRandomNumberString();

                    request.getSession().setAttribute(sesion + "_CODE", joinco);
                    request.getSession().setAttribute(sesion + "_DATE", new Date());
                    request.getSession().setAttribute(sesion + "_DAT", values);

                    Map<String, String> params = new HashMap<>();
                    params.put("telefono", numero);
                    params.put("mensaje", "Tu codigo de verificacion es:" + joinco);
                    HttpAPI api = new HttpAPI();
                    JsonResponse jsonResponse = api.POST("https://sd1.accesocrediticio.com/private/v1.0/smsDirecto", null, params, 20);

                    System.out.println("jsonResponse = " + jsonResponse);
                    System.out.println("jsonResponse = " + jsonResponse.getStatus());
                    System.out.println("jsonResponse = " + jsonResponse.getResult());
                    OutputStream out = response.getOutputStream();
                    ((ServletOutputStream) out).println(new Gson().toJson(jsonResponse));
                    out.close();
                } else {
                    JsonResponse jsonResponse = JsonResponse.defultJsonResponseERROR(new ErrorMessage("USER", "Usuario no existe."));
                    OutputStream out = response.getOutputStream();
                    ((ServletOutputStream) out).println(new Gson().toJson(jsonResponse));
                    out.close();
                }

                break;
            }
            case "VALIDCODE": {
                String sesion = request.getParameter("id_session").toUpperCase();
                String XCODE = "" + request.getParameter("nu_valcod");
                System.out.println("XCODE = " + XCODE);
                String CODE = request.getSession().getAttribute(sesion + "_CODE").toString();
                Date DATE = (Date) request.getSession().getAttribute(sesion + "_DATE");
                //SESSION TIME
                int seconds_waitfor = Util.toInt(WFIOAPP.APP.getDataSourceService().getValueOfKey("RECOVERY_PASSWORD_TIME_WAITFOR"), 60);
                System.out.println("seconds_waitfor = " + seconds_waitfor);
                JsonResponse jsonResponse;
                if (seconds_waitfor >= ((new Date().getTime() - DATE.getTime()) / 1000)) {
                    //estas en el tiempo
                    System.out.println("CODE = " + CODE);
                    if (CODE.contentEquals(XCODE)) {
                        System.out.println("Los codigos son iguales. ");
                        Map<String, String> values = (Map<String, String>) request.getSession().getAttribute(sesion + "_DAT");
                        jsonResponse = JsonResponse.defultJsonResponseOK(new RecoveryUnit(true, "Validación correcta.", values.get("givenName"), values.get("uid")));
                        //Se vuelve a setear el tiempo de espera.
                        request.getSession().setAttribute(sesion + "_DATE", new Date());
                    } else {
                        //los codigos son difertentes
                        jsonResponse = JsonResponse.defultJsonResponseERROR(new ErrorMessage(ErrorMessage.ERROR_TYPE_USER, "Códigos diferente."));
                    }
                } else {
                    //ha expirdo la session
                    request.getSession().invalidate();
                    jsonResponse = JsonResponse.defultJsonResponseERROR(new ErrorMessage(ErrorMessage.ERROR_TYPE_USER, "La sesión ha caducado."));
                }

                OutputStream out = response.getOutputStream();
                ((ServletOutputStream) out).println(new Gson().toJson(jsonResponse));
                out.close();
                break;
            }
            case "DOCHANGE": {
                String sesion = request.getParameter("id_session").toUpperCase();
                String no_curpwd = request.getParameter("no_curpwd").toUpperCase();
                String co_usuari = request.getParameter("co_usuari").toUpperCase();
//                String XCODE = "" + request.getParameter("nu_valcod");
//                System.out.println("XCODE = " + XCODE);
//                String CODE = request.getSession().getAttribute(sesion + "_CODE").toString();
                Date DATE = (Date) request.getSession().getAttribute(sesion + "_DATE");
                //SESSION TIME
                int seconds_waitfor = Util.toInt(WFIOAPP.APP.getDataSourceService().getValueOfKey("RECOVERY_PASSWORD_TIME_WAITFOR"), 60);
                System.out.println("seconds_waitfor = " + seconds_waitfor);
                JsonResponse jsonResponse;
                if (seconds_waitfor >= ((new Date().getTime() - DATE.getTime()) / 1000)) {
                    //estas en el tiempo
                    SecurityLDAO ldao = new SecurityLDAO(WFIOAPP.APP.getDataSourceService().getValueOfKey("URL_LDAP_ADMIN"));
                    ldao.connect();
                    int changepwdact = ldao.changepwd(co_usuari, no_curpwd);
                    ldao.close();
                    if (changepwdact == 1) {
                        jsonResponse = JsonResponse.defultJsonResponseOK("{\"message\":\"Datos actualizados con exito.\" , \"goto\":\"/\"}");
                    } else {
                        jsonResponse = JsonResponse.defultJsonResponseERROR(new ErrorMessage(ErrorMessage.ERROR_TYPE_USER, "LDAP-Error: No se pudo actualizar."));
                    }
                } else {
                    //ha expirdo la session
                    request.getSession().invalidate();
                    jsonResponse = JsonResponse.defultJsonResponseERROR(new ErrorMessage(ErrorMessage.ERROR_TYPE_USER, "La sesión ha caducado."));
                }

                OutputStream out = response.getOutputStream();
                ((ServletOutputStream) out).println(new Gson().toJson(jsonResponse));
                out.close();
                break;
            }
            default: {
                response.getWriter().close();
                break;
            }
        }
    }
}
