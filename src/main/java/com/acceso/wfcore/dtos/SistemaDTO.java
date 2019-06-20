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
                name = Values.QUERYS_NATIVE_SELECT_SISTEMA,
                query = "select * from wfsistem.pbsistema_list()",
                resultClass = SistemaDTO.class),
        @NamedNativeQuery(
                name = Values.QUERYS_NATIVE_GRABAR_SISTEMA,
                query = "select * from wfsistem.pbsistema_save(:co_sistem, :no_sistem, :de_sistem, :ar_logsis, :no_temdef, :il_sisfor, :ur_sistem)",
                resultClass = SistemaDTO.class)
})
public class SistemaDTO implements Serializable {

    @Id
    Integer co_sistem;

    String no_sistem;
    String de_sistem;
    Long ar_logsis;
    String no_temdef;
    String ur_sistem;
    Boolean il_sisfor;

    public SistemaDTO() {
    }

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

    public Long getAr_logsis() {
        return ar_logsis;
    }

    public void setAr_logsis(Long ar_logsis) {
        this.ar_logsis = ar_logsis;
    }

    public String getNo_temdef() {
        return no_temdef;
    }

    public void setNo_temdef(String no_temdef) {
        this.no_temdef = no_temdef;
    }

    public String getUr_sistem() {
        return ur_sistem;
    }

    public void setUr_sistem(String ur_sistem) {
        this.ur_sistem = ur_sistem;
    }

    public Boolean getIl_sisfor() {
        return il_sisfor;
    }

    public void setIl_sisfor(Boolean il_sisfor) {
        this.il_sisfor = il_sisfor;
    }
}
