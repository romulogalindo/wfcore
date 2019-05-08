/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acceso.wfcore.dtos;

import com.acceso.wfcore.utils.Values;

import javax.persistence.*;
import java.io.Serializable;


/**
 * @author Rómulo Galindo
 */

@Entity
@NamedNativeQueries({
        @NamedNativeQuery(
                name = Values.QUERYS_NATIVE_SELECT_CONTAB,
                query = "select ct.co_conten, ct.co_contab, ct.nu_rowspa, ct.nu_colspa, ct.or_numrow, ct.or_numcol, ct.ti_haling, ct.ti_valing, ct.ca_tamcel, cp.co_pagina\n" +
                        "from frawor4.tccontab ct left join frawor4.tcconpag cp on ct.co_conten = cp.co_conten and ct.co_contab=cp.co_contab  where ct.co_conten= :p_co_conten",
                resultClass = ContabDTO.class),
        @NamedNativeQuery(
                name = Values.QUERYS_NATIVE_SAVE_CONTAB,
                query = "select pbcontab_save as empty from wfsistem.pbcontab_save(:p_co_conten, :p_co_contab, :p_nu_rowspa, :p_nu_colspa, :p_or_numrow, :p_or_numcol, :p_ti_haling, :p_ti_valing, :p_ca_tamcel, :p_co_pagina)",
                resultClass = EmptyDTO.class),
        @NamedNativeQuery(
                name = Values.QUERYS_NATIVE_DELETE_CONTAB,
                query = "select pbcontab_delete as empty from wfsistem.pbcontab_delete(:p_co_conten, :p_co_contab)",
                resultClass = EmptyDTO.class)
})
public class ContabDTO implements Serializable {

    @Id
    Short co_contab;

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

    @Transient
    String no_pagina;

    public ContabDTO() {
    }

    public Short getCo_contab() {
        return co_contab;
    }

    public void setCo_contab(Short co_contab) {
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

    public String getNo_pagina() {
        return no_pagina;
    }

    public void setNo_pagina(String no_pagina) {
        this.no_pagina = no_pagina;
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
