/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acceso.wfcore.dtos;

import com.acceso.wfcore.utils.Values;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Mario Huillca <mario.huillca@acceso.com.pe>
 * Created on 30 nov. 2018, 15:11:45
 */

@Entity
@NamedNativeQueries({
        @NamedNativeQuery(
                name = Values.QUERYS_NATIVE_SELECT_CONTENEDOR,
                query = "select co_conten, no_contit, de_conten, il_sesion, db_progra, fe_regist "
                        + "from wfsistem.pbcontenedor_list()",
                resultClass = ContenedorDTO.class)
})
public class ContenedorDTO implements Serializable {

    @Id
    Integer co_conten;

    String no_contit;
    String de_conten;
    Boolean il_sesion;
    String db_progra;
    Date fe_regist;

    @Transient
    List<ContabDTO> ls_contab;

    @Transient
    List<ConparDTO> ls_conpar;

    public Integer getCo_conten() {
        return co_conten;
    }

    public void setCo_conten(Integer co_conten) {
        this.co_conten = co_conten;
    }

    public String getNo_contit() {
        return no_contit;
    }

    public void setNo_contit(String no_contit) {
        this.no_contit = no_contit;
    }

    public String getDe_conten() {
        return de_conten;
    }

    public void setDe_conten(String de_conten) {
        this.de_conten = de_conten;
    }

    public Boolean getIl_sesion() {
        return il_sesion;
    }

    public void setIl_sesion(Boolean il_sesion) {
        this.il_sesion = il_sesion;
    }

    public String getDb_progra() {
        return db_progra;
    }

    public void setDb_progra(String db_progra) {
        this.db_progra = db_progra;
    }

    public Date getFe_regist() {
        return fe_regist;
    }

    public void setFe_regist(Date fe_regist) {
        this.fe_regist = fe_regist;
    }

    public List<ContabDTO> getLs_contab() {
        return ls_contab;
    }

    public void setLs_contab(List<ContabDTO> ls_contab) {
        this.ls_contab = ls_contab;
    }

    public List<ConparDTO> getLs_conpar() {
        return ls_conpar;
    }

    public void setLs_conpar(List<ConparDTO> ls_conpar) {
        this.ls_conpar = ls_conpar;
    }

    @Override
    public String toString() {
        return "ContenedorDTO{" +
                "co_conten=" + co_conten +
                ", no_contit='" + no_contit + '\'' +
                ", de_conten='" + de_conten + '\'' +
                ", il_sesion=" + il_sesion +
                ", db_progra='" + db_progra + '\'' +
                ", fe_regist=" + fe_regist +
                '}';
    }
}
