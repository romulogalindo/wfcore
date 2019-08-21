package com.acceso.wfweb.web;

import java.io.Serializable;
import java.util.List;

public class Sistema implements Serializable {

    int co_sistem;
    String no_sistem;
    String de_sistem;
    String no_temdef;
    String ar_logsis;
    Boolean il_sisfor;
    String ur_sistem;
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

    public String getNo_temdef() {
        return no_temdef;
    }

    public void setNo_temdef(String no_temdef) {
        this.no_temdef = no_temdef;
    }

    public String getAr_logsis() {
        return ar_logsis;
    }

    public void setAr_logsis(String ar_logsis) {
        this.ar_logsis = ar_logsis;
    }

    public Boolean getIl_sisfor() {
        return il_sisfor;
    }

    public void setIl_sisfor(Boolean il_sisfor) {
        this.il_sisfor = il_sisfor;
    }

    public String getUr_sistem() {
        return ur_sistem;
    }

    public void setUr_sistem(String ur_sistem) {
        this.ur_sistem = ur_sistem;
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

    public String getDe_sistem() {
        return de_sistem;
    }

    public void setDe_sistem(String de_sistem) {
        this.de_sistem = de_sistem;
    }

    @Override
    public String toString() {
        return "Sistema{" + "co_subsis=" + co_sistem + ", no_subsis=" + no_sistem + ", paquetes=" + subsistemas + '}';
    }

}
