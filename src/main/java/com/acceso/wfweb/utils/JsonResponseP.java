package com.acceso.wfweb.utils;

import com.acceso.wfcore.utils.ErrorMessage;
import com.acceso.wfcore.utils.Param;
import com.acceso.wfweb.dtos.ComboDTO;
import jdk.nashorn.api.scripting.ScriptObjectMirror;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;

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
    List<PropagAction> ls_action;

    public JsonResponseP(String no_action, String co_condes, List<Param> ls_params, List<String> ls_pagina) {
        this(no_action, co_condes, ls_params, ls_pagina, null);
    }

    public JsonResponseP(String no_action, String co_condes, List<Param> ls_params, List<String> ls_pagina, String ur_archiv) {
        ls_action = new ArrayList<>();
        this.status = OK;

        PropagAction propagAction = new PropagAction();
        propagAction.setNo_action(no_action);
        propagAction.setCo_condes(co_condes);
        propagAction.setLs_params(ls_params);
        propagAction.setLs_pagina(ls_pagina);
        propagAction.setUr_archiv(ur_archiv);

        ls_action.add(propagAction);

        this.no_action = no_action;
        this.co_condes = co_condes;
        this.ls_params = ls_params;
        this.ls_pagina = ls_pagina;
        this.ur_archiv = ur_archiv;
    }

    public JsonResponseP(Object obj) {
        ls_action = new ArrayList<>();
        System.out.println("JsonResponseP:obj = " + obj);
        ScriptObjectMirror opts = (ScriptObjectMirror) obj;

        if (opts.getClassName().contentEquals("Array")) {
            //acciones anidadas
            Iterator it = ((jdk.nashorn.api.scripting.ScriptObjectMirror) opts).values().iterator();
            while (it.hasNext()) {
                ScriptObjectMirror opt = (ScriptObjectMirror) it.next();
                System.out.println("opt = " + opt + ",@@" + opt.getClass() + "::" + opt.getClassName());
                PropagAction propagAction = new PropagAction();
                propagAction.setNo_action(opt.get("no_action") == null ? "REDIRECT" : opt.get("no_action").toString());
                propagAction.setCo_condes(opt.get("co_condes") == null ? null : opt.get("co_condes").toString());
                if (opt.get("ls_params") != null) {
                    propagAction.setLs_params((List<Param>) opt.get("ls_params"));
                }

                if (opt.get("ls_pagina") != null) {
                    propagAction.setLs_pagina((List<String>) opt.get("ls_pagina"));
                }

                if (opt.get("ur_archiv") != null) {
                    propagAction.setUr_archiv(opt.get("ur_archiv").toString());
                }

                if (opt.get("ur_popup") != null) {
                    propagAction.setUr_popup(opt.get("ur_popup").toString());
                }

                ls_action.add(propagAction);
            }
        } else {
            PropagAction propagAction = new PropagAction();
            propagAction.setNo_action(opts.get("no_action") == null ? "REDIRECT" : opts.get("no_action").toString());
            propagAction.setCo_condes(opts.get("co_condes") == null ? null : opts.get("co_condes").toString());

            if (opts.get("ls_params") != null) {
                propagAction.setLs_params((List<Param>) opts.get("ls_params"));
            }

            if (opts.get("ls_pagina") != null) {
                propagAction.setLs_pagina((List<String>) opts.get("ls_pagina"));
            }

            if (opts.get("ur_archiv") != null) {
                propagAction.setUr_archiv(opts.get("ur_archiv").toString());
            }

            if (opts.get("ur_popup") != null) {
                propagAction.setUr_popup(opts.get("ur_popup").toString());
            }

            ls_action.add(propagAction);
        }

        System.out.println("JsonResponseP:obj = " + opts.getClass() + "::" + opts.getClassName());

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

    public List<PropagAction> getLs_action() {
        return ls_action;
    }

    public void setLs_action(List<PropagAction> ls_action) {
        this.ls_action = ls_action;
    }

    public static JsonResponseP defultJsonResponseOK(Object result) {
        JsonResponseP jsonResponse = new JsonResponseP(JsonResponseP.REDIRECT, null, null, null);
//        jsonResponse.setStatus(JsonResponse.OK);
//        jsonResponse.setNo_action(JsonResponseP.REDIRECT);
        return jsonResponse;
    }

}
