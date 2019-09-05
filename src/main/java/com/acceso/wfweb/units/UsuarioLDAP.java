package com.acceso.wfweb.units;

import com.acceso.wfweb.web.MainMenu;
import com.acceso.wfweb.web.Root;

import java.io.Serializable;
import java.util.Map;

public class UsuarioLDAP implements Serializable {

    String user;

    //    long co_usuari;
//    long id_sesion;
//
//    String no_usulog;
//    String no_usuari;
//    String no_imgusu;
//
//    Integer co_sistem;
//    Integer co_subsis;
//    //    Integer co_paquet;
//    Integer co_modulo;
//
//    boolean il_schema;
//
//    String no_correo;
//    String ip_remoto;
//
//    Root root;
//    MainMenu mainMenu;
//    Map<String, MainMenu> mainMenus;
//
//    boolean il_firtim = false;
    boolean il_conect;

    public UsuarioLDAP() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public boolean isIl_conect() {
        return il_conect;
    }

    public void setIl_conect(boolean il_conect) {
        this.il_conect = il_conect;
    }

    @Override
    public String toString() {
        return "UsuarioLDAP{" +
                "user='" + user + '\'' +
                ", il_conect=" + il_conect +
                '}';
    }
}
