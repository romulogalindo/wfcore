package com.acceso.wfcore.utils;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author rgalindo
 */
public class CellJson extends StandarRegisterJson {

    //    int co_pagreg;
    String no_addres;
    String va_pagreg;
    String tx_pagreg;
    String no_pagreg;
    Integer ti_pagreg;
    String ti_estreg;
    String ur_pagreg;
    String tx_plahol;
    Object ob_dindat;
    Object ls_styles;
    Object ca_caract;
    Object cf_search;
    Object do_valida;
    Integer nu_colspan;
    Integer nu_rowspan;

    public CellJson(Object obj) {
        ScriptObjectMirror opts = (ScriptObjectMirror) obj;
        no_addres = opts.get("no_addres") == null ? "A1" : opts.get("no_addres").toString();

        if (opts.get("va_pagreg") != null) {
            va_pagreg = "" + opts.get("va_pagreg");
        }

        if (opts.get("nu_colspan") != null) {
            nu_colspan = Util.toInt(opts.get("nu_colspan"), -1);
        }

        if (opts.get("nu_rowspan") != null) {
            nu_rowspan = Util.toInt(opts.get("nu_rowspan"), -1);
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

        if (opts.get("ob_dindat") != null) {
            ob_dindat = opts.get("ob_dindat");
        }

        if (opts.get("ls_styles") != null) {
            Iterator col = ((ScriptObjectMirror) opts.get("ls_styles")).values().iterator();
            List<String> _styles = new ArrayList<>();
            while (col.hasNext()) {
                _styles.add((String) col.next());
            }
            ls_styles = _styles;//opts.get("ls_styles");
        }

        if (opts.get("ca_caract") != null) {
            ca_caract = opts.get("ca_caract");
        }
        System.out.println("opts.get(\"tx_plahol\")  = " + opts.get("tx_plahol"));
        if (opts.get("tx_plahol") != null) {
            tx_plahol = opts.get("tx_plahol").toString();
        }

        if (opts.get("cf_search") != null) {
            ScriptObjectMirror cfg_serach = (ScriptObjectMirror) opts.get("cf_search");
            cf_search = "{\"active\":" + cfg_serach.get("il_search") + ", \"text\":\"" + cfg_serach.get("tx_search") + "\"}";
        }


        if (opts.get("do_valida") != null) {
            ScriptObjectMirror cfg_serach = (ScriptObjectMirror) opts.get("do_valida");
            Iterator pagbots = ((ScriptObjectMirror) cfg_serach.get("lk_pagbots")).values().iterator();
            List<String> _styles = new ArrayList<>();
            while (pagbots.hasNext()) {
                _styles.add("" + pagbots.next());
            }

            do_valida = "{\"charset\":\"" + cfg_serach.get("ti_charset") + "\", \"regexp\":\"" + cfg_serach.get("va_regexp").toString().replace("\\", "\\") + "\", \"message\":\"" + cfg_serach.get("tx_invalid") + "\", \"pagbots\":[" + String.join(",", _styles) + "]}";
        }
    }

    public String getNo_addres() {
        return no_addres;
    }

    public void setNo_addres(String no_addres) {
        this.no_addres = no_addres;
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

    public Object getLs_styles() {
        return ls_styles;
    }

    public void setLs_styles(Object ls_styles) {
        this.ls_styles = ls_styles;
    }

    public Object getCa_caract() {
        return ca_caract;
    }

    public void setCa_caract(Object ca_caract) {
        this.ca_caract = ca_caract;
    }


    public Object getCf_search() {
        return cf_search;
    }

    public void setCf_search(Object cf_search) {
        this.cf_search = cf_search;
    }

    public Object getDo_valida() {
        return do_valida;
    }

    public void setDo_valreg(Object do_valida) {
        this.do_valida = do_valida;
    }

    public String getTx_plahol() {
        return tx_plahol;
    }

    public void setTx_plahol(String tx_plahol) {
        this.tx_plahol = tx_plahol;
    }

    public static CellJson NEW(Object obj) {
        return new CellJson(obj);
    }

    public Integer getNu_colspan() {
        return nu_colspan;
    }

    public void setNu_colspan(Integer nu_colspan) {
        this.nu_colspan = nu_colspan;
    }

    public Integer getNu_rowspan() {
        return nu_rowspan;
    }

    public void setNu_rowspan(Integer nu_rowspan) {
        this.nu_rowspan = nu_rowspan;
    }

    @Override
    public String toString() {
        return "CellJson{" +
                "no_addres='" + no_addres + '\'' +
                ", va_pagreg='" + va_pagreg + '\'' +
                ", tx_pagreg='" + tx_pagreg + '\'' +
                ", no_pagreg='" + no_pagreg + '\'' +
                ", ti_pagreg=" + ti_pagreg +
                ", ti_estreg='" + ti_estreg + '\'' +
                ", ur_pagreg='" + ur_pagreg + '\'' +
                ", tx_plahol='" + tx_plahol + '\'' +
                ", ob_dindat=" + ob_dindat +
                ", ls_styles=" + ls_styles +
                ", ca_caract=" + ca_caract +
                ", cf_search=" + cf_search +
                ", do_valida=" + do_valida +
                '}';
    }
}
