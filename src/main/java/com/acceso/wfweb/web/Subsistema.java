package com.acceso.wfweb.web;

import java.io.Serializable;
import java.util.List;

public class Subsistema implements Serializable {

    int co_subsis;
    String no_subsis;
    String ar_logsis;
    String no_temdef;
    MainMenu mmenu;
    
    /*Es deshabilitado temporalmente*/
    List<Paquete> paquetes;
    boolean renderer;

    public Subsistema() {
    }

    public int getCo_subsis() {
        return co_subsis;
    }

    public void setCo_subsis(int co_subsis) {
        this.co_subsis = co_subsis;
    }

    public String getNo_subsis() {
        return no_subsis;
    }

    public void setNo_subsis(String no_subsis) {
        this.no_subsis = no_subsis;
    }

    public String getAr_logsis() {
        return ar_logsis;
    }

    public void setAr_logsis(String ar_logsis) {
        this.ar_logsis = ar_logsis;
    }

    public String getNo_temdef() {
        return no_temdef;
    }

    public void setNo_temdef(String no_temdef) {
        this.no_temdef = no_temdef;
    }

    public MainMenu getMmenu() {
        return mmenu;
    }

    public void setMmenu(MainMenu mmenu) {
        this.mmenu = mmenu;
    }

    public List<Paquete> getPaquetes() {
        return paquetes;
    }

    public void setPaquetes(List<Paquete> paquetes) {
        this.paquetes = paquetes;
    }

    public boolean isRenderer() {
        return renderer;
    }

    public void setRenderer(boolean renderer) {
        this.renderer = renderer;
    }

    @Override
    public String toString() {
        return "Subsistema{" + "co_subsis=" + co_subsis + ", no_subsis=" + no_subsis + ", ar_logsis=" + ar_logsis + ", no_temdef=" + no_temdef + ", mmenu=" + mmenu + ", paquetes=" + paquetes + ", renderer=" + renderer + '}';
    }

    
}
