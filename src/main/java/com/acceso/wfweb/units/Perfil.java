package com.acceso.wfweb.units;

import com.acceso.wfweb.web.MainMenu;
import com.acceso.wfweb.web.Root;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public class Perfil implements Serializable {

    int co_perfil;
    String no_perfil;

    public Perfil() {
    }

    public int getCo_perfil() {
        return co_perfil;
    }

    public void setCo_perfil(int co_perfil) {
        this.co_perfil = co_perfil;
    }

    public String getNo_perfil() {
        return no_perfil;
    }

    public void setNo_perfil(String no_perfil) {
        this.no_perfil = no_perfil;
    }
}
