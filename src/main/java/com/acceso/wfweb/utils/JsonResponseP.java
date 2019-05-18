package com.acceso.wfweb.utils;

import com.acceso.wfcore.utils.ErrorMessage;
import com.acceso.wfcore.utils.Param;
import com.acceso.wfweb.dtos.ComboDTO;

import java.io.Serializable;
import java.util.List;

public class JsonResponseP implements Serializable {

    public static final String OK = "OK";
    public static final String ERROR = "ERROR";

    public static final String REDIRECT = "REDIRECT";
    public static final String POPUP = "POPUP";
    public static final String REFRESH = "REFRESH";

    String status;
    String no_action;
    String co_condes;
    List<Param> ls_params;
    List<String> ls_pagina;

    public JsonResponseP(String no_action, String co_condes, List<Param> ls_params, List<String> ls_pagina) {
        this.status = OK;
        this.no_action = no_action;
        this.co_condes = co_condes;
        this.ls_params = ls_params;
        this.ls_pagina = ls_pagina;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNo_action() {
        return no_action;
    }

    public void setNo_action(String no_action) {
        this.no_action = no_action;
    }

    public String getCo_condes() {
        return co_condes;
    }

    public void setCo_condes(String co_condes) {
        this.co_condes = co_condes;
    }

    public List<Param> getLs_params() {
        return ls_params;
    }

    public void setLs_params(List<Param> ls_params) {
        this.ls_params = ls_params;
    }

    public List<String> getLs_pagina() {
        return ls_pagina;
    }

    public void setLs_pagina(List<String> ls_pagina) {
        this.ls_pagina = ls_pagina;
    }
    public static JsonResponseP defultJsonResponseOK(Object result) {
        JsonResponseP jsonResponse = new JsonResponseP(JsonResponseP.REDIRECT,null,null,null);
//        jsonResponse.setStatus(JsonResponse.OK);
//        jsonResponse.setNo_action(JsonResponseP.REDIRECT);
        return jsonResponse;
    }

//    public static JsonResponseP defultJsonResponseERROR(ErrorMessage error) {
//        JsonResponse jsonResponse = new JsonResponse();
//        jsonResponse.setStatus(JsonResponse.ERROR);
//        jsonResponse.setError(error);
//        return jsonResponse;
//    }
}
