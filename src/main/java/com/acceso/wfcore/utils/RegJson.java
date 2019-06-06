package com.acceso.wfcore.utils;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

/**
 * @author rgalindo
 */
public class RegJson {

    int co_pagreg;
    String va_pagreg;
    String tx_pagreg;
    String no_pagreg;
    Integer ti_pagreg;
    String ti_estreg;
    String ur_pagreg;
    Object ob_dindat;

    public RegJson(Object obj) {
        ScriptObjectMirror opts = (ScriptObjectMirror) obj;
        co_pagreg = opts.get("co_pagreg") == null ? -1 : (int) opts.get("co_pagreg");
        if (opts.get("va_pagreg") != null) {
            va_pagreg = "" + opts.get("va_pagreg");
        }

        if (opts.get("tx_pagreg") != null) {
            tx_pagreg = "" + opts.get("tx_pagreg");
        }

        if (opts.get("no_pagreg") != null) {
            no_pagreg = "" + opts.get("no_pagreg");
        }

        ti_pagreg = opts.get("ti_pagreg") == null ? -1 : (int) opts.get("ti_pagreg");

        if (opts.get("ti_estreg") != null) {
            ti_estreg = "" + opts.get("ti_estreg");
        }

        if (opts.get("ur_pagreg") != null) {
            ur_pagreg = "" + opts.get("ur_pagreg");
        }

        ob_dindat = opts.get("ob_dindat");
    }

    public RegJson(int co_pagreg) {
        this(co_pagreg, null, null, null, null, null, null, null);
    }

    public RegJson(int co_pagreg, String va_pagreg) {
        this(co_pagreg, va_pagreg, null, null, null, null, null, null);
    }

    public RegJson(int co_pagreg, String va_pagreg, String tx_pagreg) {
        this(co_pagreg, va_pagreg, tx_pagreg, null, null, null, null, null);
    }

    public RegJson(int co_pagreg, String va_pagreg, String tx_pagreg, String no_pagreg, Integer ti_pagreg, String ti_estreg, String ur_pagreg) {
        this(co_pagreg, va_pagreg, tx_pagreg, no_pagreg, ti_pagreg, ti_estreg, ur_pagreg, null);
    }

    public RegJson(int co_pagreg, String va_pagreg, String tx_pagreg, String no_pagreg, Integer ti_pagreg, String ti_estreg, String ur_pagreg, Object ob_dindat) {
        this.co_pagreg = co_pagreg;
        this.va_pagreg = va_pagreg;
        this.tx_pagreg = tx_pagreg;
        this.no_pagreg = no_pagreg;
        this.ti_pagreg = ti_pagreg;
        this.ti_estreg = ti_estreg;
        this.ur_pagreg = ur_pagreg;
        this.ob_dindat = ob_dindat;
    }

    public int getCo_pagreg() {
        return co_pagreg;
    }

    public void setCo_pagreg(int co_pagreg) {
        this.co_pagreg = co_pagreg;
    }

    public String getVa_pagreg() {
        return va_pagreg;
    }

    public void setVa_pagreg(String va_pagreg) {
        this.va_pagreg = va_pagreg;
    }

    public String getTx_pagreg() {
        return tx_pagreg;
    }

    public void setTx_pagreg(String tx_pagreg) {
        this.tx_pagreg = tx_pagreg;
    }

    public String getNo_pagreg() {
        return no_pagreg;
    }

    public void setNo_pagreg(String no_pagreg) {
        this.no_pagreg = no_pagreg;
    }

    public Integer getTi_pagreg() {
        return ti_pagreg;
    }

    public void setTi_pagreg(Integer ti_pagreg) {
        this.ti_pagreg = ti_pagreg;
    }

    public String getTi_estreg() {
        return ti_estreg;
    }

    public void setTi_estreg(String ti_estreg) {
        this.ti_estreg = ti_estreg;
    }

    public String getUr_pagreg() {
        return ur_pagreg;
    }

    public void setUr_pagreg(String ur_pagreg) {
        this.ur_pagreg = ur_pagreg;
    }

    public Object getOb_dindat() {
        return ob_dindat;
    }

    public void setOb_dindat(Object ob_dindat) {
        this.ob_dindat = ob_dindat;
    }

}
