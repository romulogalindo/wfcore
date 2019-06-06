package com.acceso.wfweb.utils;

import com.acceso.wfcore.utils.ErrorMessage;
import com.acceso.wfcore.utils.Param;
import com.acceso.wfweb.dtos.ComboDTO;
import jdk.nashorn.api.scripting.ScriptObjectMirror;

import java.io.Serializable;
import java.util.List;

public class JsonResponseP implements Serializable {

    public static final String OK = "OK";
    public static final String ERROR = "ERROR";

    public static final String REDIRECT = "REDIRECT";
    public static final String POPUP = "POPUP";
    public static final String REFRESH = "REFRESH";

    String status = "OK";
    String no_action = "REDIRECT";
    String co_condes;
    List<Param> ls_params;
    List<String> ls_pagina;
    String ur_archiv;

    public JsonResponseP(String no_action, String co_condes, List<Param> ls_params, List<String> ls_pagina) {
        this(no_action, co_condes, ls_params, ls_pagina, null);
    }

    public JsonResponseP(String no_action, String co_condes, List<Param> ls_params, List<String> ls_pagina, String ur_archiv) {
        this.status = OK;
        this.no_action = no_action;
        this.co_condes = co_condes;
        this.ls_params = ls_params;
        this.ls_pagina = ls_pagina;
        this.ur_archiv = ur_archiv;
    }

    public JsonResponseP(Object obj) {
        System.out.println("obj = " + obj);
        ScriptObjectMirror opts = (ScriptObjectMirror) obj;
        no_action = opts.get("no_action") == null ? "REDIRECT" : opts.get("no_action").toString();
        co_condes = opts.get("co_condes") == null ? null : opts.get("co_condes").toString();

        if (opts.get("ls_params") != null) {
            ls_params = (List<Param>) opts.get("ls_params");
        }

        if (opts.get("ls_pagina") != null) {
            ls_pagina = (List<String>) opts.get("ls_pagina");
        }

        if (opts.get("ur_archiv") != null) {
            ur_archiv = opts.get("ur_archiv").toString();
        }
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

    public String getUr_archiv() {
        return ur_archiv;
    }

    public void setUr_archiv(String ur_archiv) {
        this.ur_archiv = ur_archiv;
    }

    public static JsonResponseP defultJsonResponseOK(Object result) {
        JsonResponseP jsonResponse = new JsonResponseP(JsonResponseP.REDIRECT, null, null, null);
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
