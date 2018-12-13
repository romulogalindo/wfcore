package com.acceso.wfweb.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestManager {
    public static String REQUEST_METHOD_GET="GET";
    public static String REQUEST_METHOD_POST="POST";

    private HttpServletRequest request;
    private HttpServletResponse response;

    public RequestManager(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public String getPath(){
        return this.request.getPathInfo();
    }

    public String getMethod(){
        return this.request.getMethod();
    }

    public String param(String paranName){
        return this.request.getParameter(paranName);
    }
}
