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
 *
 * @author Mario Huillca <mario.huillca@acceso.com.pe>
 * Created on 30 nov. 2018, 15:11:45
 */
@Entity
@NamedNativeQueries({
    @NamedNativeQuery(
            name = Values.QUERYS_NATIVE_SELECT_SISTEMA,
            query = "select co_sistem, no_sistem, de_sistem, ur_logsis, no_temdef "
            + "from wfsistem.pbsistema_list()",
            resultClass = SistemaDTO.class),
    @NamedNativeQuery(
            name = Values.QUERYS_NATIVE_GRABAR_SISTEMA,
            query = "select co_sistem, no_sistem, de_sistem, ur_logsis, no_temdef "
            + "from wfsistem.pbsistema_save(:co_sistem, :no_sistem, :de_sistem, :ur_logsis, :no_temdef)",
            resultClass = SistemaDTO.class)
})
public class SistemaDTO implements Serializable {

    @Id
    Integer co_sistem;

    String no_sistem;
    String de_sistem;
    String ur_logsis;
    String no_temdef;

    public Integer getCo_sistem() {
        return co_sistem;
    }

    public void setCo_sistem(Integer co_sistem) {
        this.co_sistem = co_sistem;
    }

    public String getNo_sistem() {
        return no_sistem;
    }

    public void setNo_sistem(String no_sistem) {
        this.no_sistem = no_sistem;
    }

    public String getDe_sistem() {
        return de_sistem;
    }

    public void setDe_sistem(String de_sistem) {
        this.de_sistem = de_sistem;
    }

    public String getUr_logsis() {
        return ur_logsis;
    }

    public void setUr_logsis(String ur_logsis) {
        this.ur_logsis = ur_logsis;
    }

    public String getNo_temdef() {
        return no_temdef;
    }

    public void setNo_temdef(String no_temdef) {
        this.no_temdef = no_temdef;
    }

    @Override
    public String toString() {
        return "SistemaDTO{" +
              "co_sistem=" + co_sistem +
              ", no_sistem='" + no_sistem + '\'' +
              ", de_sistem='" + de_sistem + '\'' +
              ", ur_logsis='" + ur_logsis + '\'' +
              ", no_temdef='" + no_temdef + '\'' +
              '}';
    }
}
