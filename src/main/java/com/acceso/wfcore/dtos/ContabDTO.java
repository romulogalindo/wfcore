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
 * @author Rómulo Galindo
 */

@Entity
@NamedNativeQueries({
        @NamedNativeQuery(
                name = Values.QUERYS_NATIVE_SELECT_PARAMETRO,
                query = "select co_conten, co_conpar, no_conpar "
                        + "from wfsistem.pbparametro_list()",
                resultClass = ContabDTO.class),
        @NamedNativeQuery(
                name = Values.QUERYS_NATIVE_SELECT_PARAMETRO_CONTEN,
                query = "select co_conten, co_conpar, no_conpar "
                        + "from wfsistem.pbparametro_list(:co_conten)",
                resultClass = ContabDTO.class)
})
public class ContabDTO implements Serializable {

    @Id
    Integer co_contab;

    Integer co_conten;
    Short nu_rowspa;
    Short nu_colspa;
    Short or_numrow;
    Short or_numcol;
    String ti_haling;
    String ti_valing;
    Short ca_tamcel;

    /*De los tab-> con su pagina*/
    Integer co_pagina;

    public ContabDTO() {
    }

    public Integer getCo_contab() {
        return co_contab;
    }

    public void setCo_contab(Integer co_contab) {
        this.co_contab = co_contab;
    }

    public Integer getCo_conten() {
        return co_conten;
    }

    public void setCo_conten(Integer co_conten) {
        this.co_conten = co_conten;
    }

    public Short getNu_rowspa() {
        return nu_rowspa;
    }

    public void setNu_rowspa(Short nu_rowspa) {
        this.nu_rowspa = nu_rowspa;
    }

    public Short getNu_colspa() {
        return nu_colspa;
    }

    public void setNu_colspa(Short nu_colspa) {
        this.nu_colspa = nu_colspa;
    }

    public Short getOr_numrow() {
        return or_numrow;
    }

    public void setOr_numrow(Short or_numrow) {
        this.or_numrow = or_numrow;
    }

    public Short getOr_numcol() {
        return or_numcol;
    }

    public void setOr_numcol(Short or_numcol) {
        this.or_numcol = or_numcol;
    }

    public String getTi_haling() {
        return ti_haling;
    }

    public void setTi_haling(String ti_haling) {
        this.ti_haling = ti_haling;
    }

    public String getTi_valing() {
        return ti_valing;
    }

    public void setTi_valing(String ti_valing) {
        this.ti_valing = ti_valing;
    }

    public Short getCa_tamcel() {
        return ca_tamcel;
    }

    public void setCa_tamcel(Short ca_tamcel) {
        this.ca_tamcel = ca_tamcel;
    }

    public Integer getCo_pagina() {
        return co_pagina;
    }

    public void setCo_pagina(Integer co_pagina) {
        this.co_pagina = co_pagina;
    }

    @Override
    public String toString() {
        return "ContabDTO{" +
                "co_contab=" + co_contab +
                ", co_conten=" + co_conten +
                ", nu_rowspa=" + nu_rowspa +
                ", nu_colspa=" + nu_colspa +
                ", or_numrow=" + or_numrow +
                ", or_numcol=" + or_numcol +
                ", ti_haling='" + ti_haling + '\'' +
                ", ti_valing='" + ti_valing + '\'' +
                ", ca_tamcel=" + ca_tamcel +
                ", co_pagina='" + co_pagina + '\'' +
                '}';
    }
}
