package com.acceso.wfcore.utils;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author rgalindo
 */
public class TabJson extends StandarRegisterJson {

    int co_pagtab;
    String va_pagtab;
    String ic_pagtab;
    Integer ti_pagtab;
    String ti_esttab;
    String ur_pagtab;

    public TabJson(Object obj) {
        ScriptObjectMirror opts = (ScriptObjectMirror) obj;
        co_pagtab = opts.get("co_pagtab") == null ? -1 : (int) opts.get("co_pagtab");

        if (opts.get("va_pagtab") != null) {
            va_pagtab = "" + opts.get("va_pagtab");
        }

        if (opts.get("ic_pagtab") != null) {
            ic_pagtab = "" + opts.get("ic_pagtab");
        }

        ti_pagtab = opts.get("ti_pagtab") == null ? -1 : (int) opts.get("ti_pagtab");

        if (opts.get("ti_esttab") != null) {
            ti_esttab = "" + opts.get("ti_esttab");
        }

        if (opts.get("ur_pagtab") != null) {
            ur_pagtab = "" + opts.get("ur_pagtab");
        }
    }

//
//    public TabJson(int co_pagreg) {
//        this(co_pagreg, null, null, null, null, null, null, null);
//    }
//
//    public TabJson(int co_pagreg, String va_pagreg) {
//        this(co_pagreg, va_pagreg, null, null, null, null, null, null);
//    }
//
//    public TabJson(int co_pagreg, String va_pagreg, String tx_pagreg) {
//        this(co_pagreg, va_pagreg, tx_pagreg, null, null, null, null, null);
//    }
//
//    public TabJson(int co_pagreg, String va_pagreg, String tx_pagreg, String no_pagreg, Integer ti_pagreg, String ti_estreg, String ur_pagreg) {
//        this(co_pagreg, va_pagreg, tx_pagreg, no_pagreg, ti_pagreg, ti_estreg, ur_pagreg, null);
//    }

    public int getCo_pagtab() {
        return co_pagtab;
    }

    public void setCo_pagtab(int co_pagtab) {
        this.co_pagtab = co_pagtab;
    }

    public String getVa_pagtab() {
        return va_pagtab;
    }

    public void setVa_pagtab(String va_pagtab) {
        this.va_pagtab = va_pagtab;
    }

    public String getIc_pagtab() {
        return ic_pagtab;
    }

    public void setIc_pagtab(String ic_pagtab) {
        this.ic_pagtab = ic_pagtab;
    }

    public Integer getTi_pagtab() {
        return ti_pagtab;
    }

    public void setTi_pagtab(Integer ti_pagtab) {
        this.ti_pagtab = ti_pagtab;
    }

    public String getTi_esttab() {
        return ti_esttab;
    }

    public void setTi_esttab(String ti_esttab) {
        this.ti_esttab = ti_esttab;
    }

    public String getUr_pagtab() {
        return ur_pagtab;
    }

    public void setUr_pagtab(String ur_pagtab) {
        this.ur_pagtab = ur_pagtab;
    }

    public static TabJson NEW(Object obj) {
        return new TabJson(obj);
    }
}
