package com.acceso.wfweb.units;

import com.acceso.wfweb.web.MainMenu;
import com.acceso.wfweb.web.Root;

import java.io.Serializable;

public class Usuario implements Serializable {

    long co_usuari;
    long id_sesion;

    String no_usulog;
    String no_usuari;
    String no_imgusu;

    Integer co_sistem;
    Integer co_subsis;
    Integer co_paquet;
    Integer co_modulo;

    Root root;
    MainMenu mainMenu;

    public Usuario() {
    }

    public long getCo_usuari() {
        return co_usuari;
    }

    public void setCo_usuari(long co_usuari) {
        this.co_usuari = co_usuari;
    }

    public long getId_sesion() {
        return id_sesion;
    }

    public void setId_sesion(long id_sesion) {
        this.id_sesion = id_sesion;
    }

    public String getNo_usulog() {
        return no_usulog;
    }

    public void setNo_usulog(String no_usulog) {
        this.no_usulog = no_usulog;
    }

    public String getNo_usuari() {
        return no_usuari;
    }

    public void setNo_usuari(String no_usuari) {
        this.no_usuari = no_usuari;
    }

    public String getNo_imgusu() {
        return (no_imgusu == null ? "" : no_imgusu).length() == 0 ? "/jsp_exec/imgs/defaults/avatar_default.png" : no_imgusu;
    }

    public void setNo_imgusu(String no_imgusu) {
        this.no_imgusu = no_imgusu;
    }

    public Root getRoot() {
        return root;
    }

    public void setRoot(Root root) {
        this.root = root;
    }

    public MainMenu getMainMenu() {
        return mainMenu;
    }

    public void setMainMenu(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    public Integer getCo_sistem() {
        return co_sistem;
    }

    public void setCo_sistem(Integer co_sistem) {
        this.co_sistem = co_sistem;
    }

    public Integer getCo_subsis() {
        return co_subsis;
    }

    public void setCo_subsis(Integer co_subsis) {
        this.co_subsis = co_subsis;
    }

    public Integer getCo_paquet() {
        return co_paquet;
    }

    public void setCo_paquet(Integer co_paquet) {
        this.co_paquet = co_paquet;
    }

    public Integer getCo_modulo() {
        return co_modulo;
    }

    public void setCo_modulo(Integer co_modulo) {
        this.co_modulo = co_modulo;
    }

    @Override
    public String toString() {
        return "Usuario{" + "co_usuari=" + co_usuari + ", id_sesion=" + id_sesion + ", no_usulog=" + no_usulog + ", no_usuari=" + no_usuari + ", no_imgusu=" + no_imgusu + ", co_sistem=" + co_sistem + ", co_subsis=" + co_subsis + ", co_paquet=" + co_paquet + ", co_modulo=" + co_modulo + ", root=" + root + ", mainMenu=" + mainMenu + '}';
    }

}