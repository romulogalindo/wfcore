package com.acceso.wfcore.utils;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Rómulo Galindo Tanta
 */
public class CfgJson {
    String ti_object;
    String co_object;

    //config ooptions
    String disabled;

    public CfgJson(Object obj) {
        ScriptObjectMirror opts = (ScriptObjectMirror) obj;
        ti_object = opts.get("ti_object") == null ? "button" : "" + opts.get("ti_object");
        co_object = opts.get("co_object") == null ? "1" : "" + opts.get("co_object");
        disabled = opts.get("disabled") == null ? "1" : "" + opts.get("disabled");
    }

    public String getTi_object() {
        return ti_object;
    }

    public void setTi_object(String ti_object) {
        this.ti_object = ti_object;
    }

    public String getCo_object() {
        return co_object;
    }

    public void setCo_object(String co_object) {
        this.co_object = co_object;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }
}
