package com.acceso.wfweb.web;

import java.io.Serializable;
import java.util.List;

public class Paquete implements Serializable {

    int co_paquet;
    String no_paquet;
    String no_prefij;
    //String ur_default;
    String ur_arcadj;
    MainMenu mmenu;
    boolean renderer;

    public Paquete() {
    }

    public int getCo_paquet() {
        return co_paquet;
    }

    public void setCo_paquet(int co_paquet) {
        this.co_paquet = co_paquet;
    }

    public String getNo_paquet() {
        return no_paquet;
    }

    public void setNo_paquet(String no_paquet) {
        this.no_paquet = no_paquet;
    }

    public String getNo_prefij() {
        return no_prefij;
    }

    public void setNo_prefij(String no_prefij) {
        this.no_prefij = no_prefij;
    }
//
//    public String getUr_default() {
//        return ur_default;
//    }
//
//    public void setUr_default(String ur_default) {
//        this.ur_default = ur_default;
//    }

    public String getUr_arcadj() {
        return ur_arcadj;
    }

    public void setUr_arcadj(String ur_arcadj) {
        this.ur_arcadj = ur_arcadj;
    }

    public MainMenu getMmenu() {
        return mmenu;
    }

    public void setMmenu(MainMenu mmenu) {
        this.mmenu = mmenu;
    }

    public boolean isRenderer() {
        return renderer;
    }

    public void setRenderer(boolean renderer) {
        this.renderer = renderer;
    }

    @Override
    public String toString() {
        return "Paquete{" + "co_paquet=" + co_paquet + ", no_paquet=" + no_paquet + ", no_prefij=" + no_prefij + ", ur_arcadj=" + ur_arcadj + ", mmenu=" + mmenu + '}';
    }


}
