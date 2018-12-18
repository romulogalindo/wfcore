package com.acceso.wfweb.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestManager {
    public static String REQUEST_METHOD_GET = "GET";
    public static String REQUEST_METHOD_POST = "POST";

    private HttpServletRequest request;
    private HttpServletResponse response;

    public RequestManager(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
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
}
