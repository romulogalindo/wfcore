package com.acceso.wfweb.servlets;

import com.acceso.wfcore.apis.HttpAPI;
import com.acceso.wfcore.utils.ErrorMessage;
import com.acceso.wfcore.utils.Util;
import com.acceso.wfweb.utils.JsonResponse;
import com.google.gson.Gson;

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

                il_havuse = true;
                /*BCAR LDAP USER*/

                if (il_havuse) {
                    String sesion = request.getParameter("id_session").toUpperCase();
                    String joinco = Util.getRandomNumberString();

                    request.getSession().setAttribute(sesion + "_CODE", joinco);
                    request.getSession().setAttribute(sesion + "_DATE", new Date());
                    request.getSession().setAttribute(sesion + "_USER", null);

                    Map<String, String> params = new HashMap<>();
                    params.put("telefono", numero);
                    params.put("mensaje", "Tu codigo de verificacion es:" + joinco);
                    HttpAPI api = new HttpAPI();
                    JsonResponse jsonResponse = api.POST("https://sd1.accesocrediticio.com/private/v1.0/smsDirecto", null, params, 20);
//                response.getWriter().println(new Gson().toJson(jsonResponse));
//                response.getWriter().close();

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
                System.out.println("CODE = " + CODE);
                if (CODE.contentEquals(XCODE)) {
                    System.out.println("Los codigos son iguales ");
                }

//                String numero = request.getParameter("numero").toUpperCase();
//                String joinco = Util.getRandomNumberString();
//
//                Map<String, String> params = new HashMap<>();
//                params.put("telefono", numero);
//                params.put("mensaje", "Tu codigo de verificacion es:" + joinco);
//                HttpAPI api = new HttpAPI();
//                JsonResponse jsonResponse = api.POST("https://sd1.accesocrediticio.com/private/v1.0/smsDirecto", null, params, 20);
////                response.getWriter().println(new Gson().toJson(jsonResponse));
////                response.getWriter().close();
//
//                System.out.println("jsonResponse = " + jsonResponse);
//                System.out.println("jsonResponse = " + jsonResponse.getStatus());
//                System.out.println("jsonResponse = " + jsonResponse.getResult());
                OutputStream out = response.getOutputStream();
                ((ServletOutputStream) out).println("GO");
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
