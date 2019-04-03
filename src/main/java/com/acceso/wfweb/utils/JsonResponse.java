package com.acceso.wfweb.utils;

import com.acceso.wfcore.utils.ErrorMessage;

import java.io.Serializable;

public class JsonResponse implements Serializable {

    public static final String OK = "OK";
    public static final String ERROR = "ERROR";

    String status;
    Object result;
    Object aditional;
    String message;
    ErrorMessage error;

    public JsonResponse() {
    }

    public JsonResponse(String status, Object result, Object aditional) {
        this.status = status;
        this.result = result;
        this.aditional = aditional;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Object getAditional() {
        return aditional;
    }

    public void setAditional(Object aditional) {
        this.aditional = aditional;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorMessage getError() {
        return error;
    }

    public void setError(ErrorMessage error) {
        this.error = error;
    }

    public static JsonResponse defultJsonResponseOK(Object result) {
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setStatus(JsonResponse.OK);
        jsonResponse.setResult(result);
        return jsonResponse;
    }

    public static JsonResponse defultJsonResponseERROR(ErrorMessage error) {
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setStatus(JsonResponse.ERROR);
        jsonResponse.setError(error);
        return jsonResponse;
    }
}
