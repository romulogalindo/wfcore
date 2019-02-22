package com.acceso.wfweb.utils;

import com.acceso.wfcore.utils.ErrorMessage;

import java.io.Serializable;

public class JsonResponse implements Serializable {
    public static final String OK = "OK";
    public static final String ERROR = "ERROR";

    String status;
    Object result;
    String message;
    ErrorMessage error;

    public JsonResponse() {
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
}
