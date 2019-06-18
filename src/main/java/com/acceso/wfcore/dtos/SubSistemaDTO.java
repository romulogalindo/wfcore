/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acceso.wfcore.dtos;

import com.acceso.wfcore.utils.Values;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import java.io.Serializable;

/**
 * @author Mario Huillca <mario.huillca@acceso.com.pe>
 * Created on 30 nov. 2018, 15:11:45
 */
@Entity
@NamedNativeQueries({
        @NamedNativeQuery(
                name = Values.QUERYS_NATIVE_SELECT_SUBSISTEMA,
                query = "select * from wfsistem.pbsubsistema_list()",
                resultClass = SubSistemaDTO.class),
        @NamedNativeQuery(
                name = Values.QUERYS_NATIVE_GRABAR_SUBSISTEMA,
                query = "select co_subsis, no_subsis, co_sistem, ur_logsub, no_sistem"
                        + "from wfsistem.pbsubsistema_save(:co_subsis, :no_subsis, :co_sistem, :ur_logsub)",
                resultClass = SubSistemaDTO.class)
})
public class SubSistemaDTO implements Serializable {

    @Id
    Integer co_subsis;

    String no_subsis;
    Integer co_sistem;
    String ar_logsub;
    String no_sistem;
    String no_temdef;


    public Integer getCo_subsis() {
        return co_subsis;
    }

    public void setCo_subsis(Integer co_subsis) {
        this.co_subsis = co_subsis;
    }

    public String getNo_subsis() {
        return no_subsis;
    }

    public void setNo_subsis(String no_subsis) {
        this.no_subsis = no_subsis;
    }

    public Integer getCo_sistem() {
        return co_sistem;
    }

    public void setCo_sistem(Integer co_sistem) {
        this.co_sistem = co_sistem;
    }

    public String getAr_logsub() {
        return ar_logsub;
    }

    public void setAr_logsub(String ar_logsub) {
        this.ar_logsub = ar_logsub;
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

    @Override
    public String toString() {
        return "SubSistemaDTO{" +
                "co_subsis=" + co_subsis +
                ", no_subsis='" + no_subsis + '\'' +
                ", co_sistem=" + co_sistem +
                ", ar_logsub='" + ar_logsub + '\'' +
                ", no_sistem='" + no_sistem + '\'' +
                '}';
    }
}
