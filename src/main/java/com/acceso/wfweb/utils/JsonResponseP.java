package com.acceso.wfweb.utils;

import com.acceso.wfcore.utils.ErrorMessage;
import com.acceso.wfcore.utils.Param;
import com.acceso.wfweb.dtos.ComboDTO;

import java.io.Serializable;
import java.util.List;

public class JsonResponseP implements Serializable {

    public static final String OK = "OK";
    public static final String ERROR = "ERROR";

    String status;
    String action;
    List<Param> params;

    public JsonResponseP(String status, String action, List<Param> params) {
        this.status = status;
        this.action = action;
        this.params = params;
    }

    public JsonResponseP() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public List<Param> getParams() {
        return params;
    }

    public void setParams(List<Param> params) {
        this.params = params;
    }
}
