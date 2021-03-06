package com.acceso.wfweb.units;

import com.acceso.wfweb.web.MainMenu;
import com.acceso.wfweb.web.Root;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Usuario implements Serializable {

    long co_usuari;
    long id_sesion;

    String no_usulog;
    String no_usuari;
    String no_imgusu;

    Integer co_sistem;
    Integer co_subsis;
    //    Integer co_paquet;
    Integer co_modulo;

    boolean il_schema;

    String no_correo;
    String nu_docide;
    String ip_remoto;
    String ti_usuari;

    Root root;
    MainMenu mainMenu;
    Map<String, MainMenu> mainMenus;

    boolean il_firtim = false;
    UsuarioLDAP ldap;
    String no_temdef;
    Date fe_updpas;
    String sesion_tomcat_id;
    Object perfil;

    Map<Integer, Object> ls_mensis;

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

    //    public Integer getCo_paquet() {
//        return co_paquet;
//    }
//
//    public void setCo_paquet(Integer co_paquet) {
//        this.co_paquet = co_paquet;
//    }
    public Integer getCo_modulo() {
        return co_modulo;
    }

    public void setCo_modulo(Integer co_modulo) {
        this.co_modulo = co_modulo;
    }

    public boolean isIl_schema() {
        return il_schema;
    }

    public void setIl_schema(boolean il_schema) {
        this.il_schema = il_schema;
    }

    public Map<String, MainMenu> getMainMenus() {
        return mainMenus;
    }

    public void setMainMenus(Map<String, MainMenu> mainMenus) {
        this.mainMenus = mainMenus;
    }

    public String getNo_correo() {
        return no_correo;
    }

    public void setNo_correo(String no_correo) {
        this.no_correo = no_correo;
    }

    public String getNu_docide() {
        return nu_docide;
    }

    public void setNu_docide(String nu_docide) {
        this.nu_docide = nu_docide;
    }

    public String getIp_remoto() {
        return ip_remoto;
    }

    public void setIp_remoto(String ip_remoto) {
        this.ip_remoto = ip_remoto;
    }

    public boolean isIl_firtim() {
        return il_firtim;
    }

    public void setIl_firtim(boolean il_firtim) {
        this.il_firtim = il_firtim;
    }

    public boolean do_firtim() {
        System.out.println("this.il_firtim = " + this.il_firtim);
        if (!this.il_firtim) {
            this.il_firtim = true;
            return true;
        } else {
            return false;
        }

    }

    public UsuarioLDAP getLdap() {
        return ldap;
    }

    public void setLdap(UsuarioLDAP ldap) {
        this.ldap = ldap;
    }

    public String getNo_temdef() {
        return no_temdef;
    }

    public void setNo_temdef(String no_temdef) {
        this.no_temdef = no_temdef;
    }

    public Map<Integer, Object> getLs_mensis() {
        return ls_mensis;
    }

    public void setLs_mensis(Map<Integer, Object> ls_mensis) {
        this.ls_mensis = ls_mensis;
    }

    public Date getFe_updpas() {
        return fe_updpas;
    }

    public void setFe_updpas(Date fe_updpas) {
        this.fe_updpas = fe_updpas;
    }

    public String getSesion_tomcat_id() {
        return sesion_tomcat_id;
    }

    public void setSesion_tomcat_id(String sesion_tomcat_id) {
        this.sesion_tomcat_id = sesion_tomcat_id;
    }

    public String getTi_usuari() {
        return ti_usuari;
    }

    public void setTi_usuari(String ti_usuari) {
        this.ti_usuari = ti_usuari;
    }

    public Object getPerfil() {
        return perfil;
    }

    public void setPerfil(Object perfil) {
        this.perfil = perfil;
    }

    @Override
    public String toString() {
        return "Usuario{" + "co_usuari=" + co_usuari + ", id_sesion=" + id_sesion + ", no_usulog=" + no_usulog + ", no_usuari=" + no_usuari + ", no_imgusu=" + no_imgusu + ", co_sistem=" + co_sistem + ", co_subsis=" + co_subsis + ", co_modulo=" + co_modulo + ", root=" + root + ", mainMenu=" + mainMenu + '}';
    }

}
