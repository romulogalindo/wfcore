package com.acceso.wfweb.web;

import java.io.Serializable;
import java.util.List;

public class Subsistema implements Serializable {

    int co_subsis;
    String no_subsis;
    List<Paquete> paquetes;

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

    public List<Paquete> getPaquetes() {
        return paquetes;
    }

    public void setPaquetes(List<Paquete> paquetes) {
        this.paquetes = paquetes;
    }

    @Override
    public String toString() {
        return "Subsistema{" + "co_subsis=" + co_subsis + ", no_subsis=" + no_subsis + ", paquetes=" + paquetes + '}';
    }

}
