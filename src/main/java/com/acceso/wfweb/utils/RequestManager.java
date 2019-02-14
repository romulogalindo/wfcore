package com.acceso.wfweb.utils;

import com.acceso.wfcore.utils.Util;
import com.acceso.wfweb.units.Usuario;
import org.ocpsoft.rewrite.servlet.impl.HttpRewriteWrappedRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;

public class RequestManager {
    public static String REQUEST_METHOD_GET = "GET";
    public static String REQUEST_METHOD_POST = "POST";

    private HttpServletRequest request;
    private HttpServletResponse response;

    public RequestManager(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public RequestManager(HttpRewriteWrappedRequest httpRewriteWrappedRequest, HttpServletResponse response) {
        this.request = (HttpServletRequest) httpRewriteWrappedRequest;
        this.response = response;
    }

    public String getPath() {
//        return this.request.getPathInfo();
        return this.request.getServletPath();

    }

    public String getMethod() {
        return this.request.getMethod();
    }

    public String getIp() {
        return this.request.getRemoteHost();
    }

    public String getParam(String paranName) {
        return this.request.getParameter(paranName);
    }

    public void save_over_session(String objectKey, Object objectValue) {
        this.request.getSession().setAttribute(objectKey, objectValue);
    }

    public void save_over_request(String objectKey, Object objectValue) {
        this.request.setAttribute(objectKey, objectValue);
    }

    public void redirect(String url) throws Exception {
        System.out.println("redirect to>" + url);
        this.response.sendRedirect(url);
    }

    public HashMap<Integer, String> getConpars() {
        HashMap<Integer, String> conpars = new HashMap<>();

        Enumeration<String> parametersNames = this.request.getParameterNames();
        while (parametersNames.hasMoreElements()) {
            String paramKey = parametersNames.nextElement();
            if (paramKey.contains("co_conpar_")) {
                conpars.put(Util.toInt(paramKey.replace("co_conpar_", "")), this.request.getParameter(paramKey));
            }
        }

        return conpars;
    }

    public Usuario getUser() {
        return (Usuario) this.request.getSession().getAttribute("US");
    }
}
