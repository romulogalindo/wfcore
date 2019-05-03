package com.acceso.wfweb.utils;

import com.acceso.wfcore.utils.ErrorMessage;
import com.acceso.wfweb.dtos.ComboDTO;

import java.io.Serializable;
import java.util.List;

public class JsonResponseC implements Serializable {

    public static final String OK = "OK";
    public static final String ERROR = "ERROR";

    String status;
    List<ComboDTO> result;
    Object fnpost;
    Object aditional;
    String message;
    ErrorMessage error;

    public JsonResponseC() {
    }

    public JsonResponseC(String status, List<ComboDTO> result, Object aditional) {
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

    public List<ComboDTO> getResult() {
        return result;
    }

    public void setResult(List<ComboDTO> result) {
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

    public Object getFnpost() {
        return fnpost;
    }

    public void setFnpost(Object fnpost) {
        this.fnpost = fnpost;
    }

    public ErrorMessage getError() {
        return error;
    }

    public void setError(ErrorMessage error) {
        this.error = error;
    }

    public static JsonResponseC defultJsonResponseOK(List<ComboDTO> result) {
        JsonResponseC jsonResponse = new JsonResponseC();
        jsonResponse.setStatus(JsonResponseC.OK);
        jsonResponse.setResult(result);
        return jsonResponse;
    }

    public static JsonResponseC defultJsonResponseERROR(ErrorMessage error) {
        JsonResponseC jsonResponse = new JsonResponseC();
        jsonResponse.setStatus(JsonResponseC.ERROR);
        jsonResponse.setError(error);
        return jsonResponse;
    }
}
