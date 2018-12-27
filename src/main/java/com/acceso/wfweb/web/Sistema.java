package com.acceso.wfweb.web;

import java.io.Serializable;
import java.util.List;

public class Sistema implements Serializable {

    int co_sistem;
    String no_sistem;
    List<Subsistema> subsistemas;
    boolean renderer;

    public Sistema() {
    }

    public int getCo_sistem() {
        return co_sistem;
    }

    public void setCo_sistem(int co_sistem) {
        this.co_sistem = co_sistem;
    }

    public String getNo_sistem() {
        return no_sistem;
    }

    public void setNo_sistem(String no_sistem) {
        this.no_sistem = no_sistem;
    }

    public List<Subsistema> getSubsistemas() {
        return subsistemas;
    }

    public void setSubsistemas(List<Subsistema> subsistemas) {
        this.subsistemas = subsistemas;
    }

    public boolean isRenderer() {
        return renderer;
    }

    public void setRenderer(boolean renderer) {
        this.renderer = renderer;
    }

    @Override
    public String toString() {
        return "Sistema{" + "co_subsis=" + co_sistem + ", no_subsis=" + no_sistem + ", paquetes=" + subsistemas + '}';
    }


}
