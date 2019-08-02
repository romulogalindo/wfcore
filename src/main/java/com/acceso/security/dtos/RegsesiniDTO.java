package com.acceso.security.dtos;

import com.acceso.security.utils.Values;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Transient;
//import org.hibernate.annotations.NamedNativeQueries;
//import org.hibernate.annotations.NamedNativeQuery;

/*
"select * from wfsistem.ppregsesini(:p_username, :p_password, :p_remoteip,:p_sistema) as inises"

    @NamedNativeQuery(
            name = Values.QUERYS_SECURITY_REGSESINI_WEB,
            query = "select * from wfsistem.ppregsesini_fwweb(:p_username, :p_password, :p_remoteip) as inises",
            resultClass = RegsesiniDTO.class)
 */
@Entity
@NamedNativeQueries({
        @NamedNativeQuery(
                name = Values.wfsistem_ppregsesini_KEY,
                query = Values.wfsistem_ppregsesini_VALUE,
                resultClass = RegsesiniDTO.class),
        @NamedNativeQuery(
                name = Values.wfsistem_ppregsesiniweb_KEY,
                query = Values.wfsistem_ppregsesiniweb_VALUE,
                resultClass = RegsesiniDTO.class)
})
public class RegsesiniDTO implements Serializable {

    @Id
    long co_usuari;
    long id_sesion;

    String no_usulog;
    String no_usuari;
    String ar_imgusu;

    Integer co_sistem;
    Integer co_subsis;
    //    Integer co_paquet;
    Integer co_modulo;

    int co_mensaj;
    String de_mensaj;
    String no_correo;

//    @Transient
    String ip_remoto;

    @Transient
    String il_prilog;

    public RegsesiniDTO() {
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

    public String getAr_imgusu() {
        return ar_imgusu;
    }

    public void setAr_imgusu(String ar_imgusu) {
        this.ar_imgusu = ar_imgusu;
    }

    public int getCo_sistem() {
        return co_sistem;
    }

    public void setCo_sistem(int co_sistem) {
        this.co_sistem = co_sistem;
    }

    public int getCo_subsis() {
        return co_subsis;
    }

    public void setCo_subsis(int co_subsis) {
        this.co_subsis = co_subsis;
    }

//    public int getCo_paquet() {
//        return co_paquet;
//    }
//
//    public void setCo_paquet(int co_paquet) {
//        this.co_paquet = co_paquet;
//    }

    public int getCo_modulo() {
        return co_modulo;
    }

    public void setCo_modulo(int co_modulo) {
        this.co_modulo = co_modulo;
    }

    public int getCo_mensaj() {
        return co_mensaj;
    }

    public void setCo_mensaj(int co_mensaj) {
        this.co_mensaj = co_mensaj;
    }

    public String getDe_mensaj() {
        return de_mensaj;
    }

    public void setDe_mensaj(String de_mensaj) {
        this.de_mensaj = de_mensaj;
    }

    public String getIp_remoto() {
        return ip_remoto;
    }

    public void setIp_remoto(String ip_remoto) {
        this.ip_remoto = ip_remoto;
    }

    public String getIl_prilog() {
        return il_prilog;
    }

    public void setIl_prilog(String il_prilog) {
        this.il_prilog = il_prilog;
    }

    public String getNo_correo() {
        return no_correo;
    }

    public void setNo_correo(String no_correo) {
        this.no_correo = no_correo;
    }

    @Override
    public String toString() {
        return "RegsesiniDTO{" + "co_usuari=" + co_usuari + ", id_sesion=" + id_sesion + ", no_usulog=" + no_usulog + ", no_usuari=" + no_usuari + ", ar_imgusu=" + ar_imgusu + ", co_sistem=" + co_sistem + ", co_subsis=" + co_subsis + ", co_modulo=" + co_modulo + ", co_mensaj=" + co_mensaj + ", de_mensaj=" + de_mensaj + ", ip_remoto=" + ip_remoto + ", il_prilog=" + il_prilog + '}';
    }

}
