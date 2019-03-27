package com.acceso.wfweb.dtos;

import com.acceso.wfweb.utils.Values;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import java.io.Serializable;

/**
 * @author Rómulo Galindo<romulogalindo@gmail.com>
 * Created on 13 dic. 2018, 19:06:46
 */
@Entity
@NamedNativeQueries({
        @NamedNativeQuery(
                name = Values.QUERYS_WEB_SELECT_PFCONPAG,
                query = "select * from frawor4.pfconpag(:p_id_frawor , :p_co_conten )",
                resultClass = WPaginaDTO.class)
})
public class WPaginaDTO implements Serializable {

    @Id
    int co_pagina;

    String no_pagtit;

    String ti_pagina;

    int nu_rowspa;

    int nu_colspa;

    int or_numrow;

    int or_numcol;

    int co_contab;

    boolean il_autinc;

    int va_autref;

    String no_autinc;

    String ca_tamano;

    public WPaginaDTO() {
    }

    public int getCo_pagina() {
        return co_pagina;
    }

    public void setCo_pagina(int co_pagina) {
        this.co_pagina = co_pagina;
    }

    public String getNo_pagtit() {
        return no_pagtit;
    }

    public void setNo_pagtit(String no_pagtit) {
        this.no_pagtit = no_pagtit;
    }

    public String getTi_pagina() {
        return ti_pagina;
    }

    public void setTi_pagina(String ti_pagina) {
        this.ti_pagina = ti_pagina;
    }

    public int getNu_rowspa() {
        return nu_rowspa;
    }

    public void setNu_rowspa(int nu_rowspa) {
        this.nu_rowspa = nu_rowspa;
    }

    public int getNu_colspa() {
        return nu_colspa;
    }

    public void setNu_colspa(int nu_colspa) {
        this.nu_colspa = nu_colspa;
    }

    public int getOr_numrow() {
        return or_numrow;
    }

    public void setOr_numrow(int or_numrow) {
        this.or_numrow = or_numrow;
    }

    public int getOr_numcol() {
        return or_numcol;
    }

    public void setOr_numcol(int or_numcol) {
        this.or_numcol = or_numcol;
    }

    public int getCo_contab() {
        return co_contab;
    }

    public void setCo_contab(int co_contab) {
        this.co_contab = co_contab;
    }

    public boolean isIl_autinc() {
        return il_autinc;
    }

    public void setIl_autinc(boolean il_autinc) {
        this.il_autinc = il_autinc;
    }

    public int getVa_autref() {
        return va_autref;
    }

    public void setVa_autref(int va_autref) {
        this.va_autref = va_autref;
    }

    public String getNo_autinc() {
        return no_autinc;
    }

    public void setNo_autinc(String no_autinc) {
        this.no_autinc = no_autinc;
    }

    public String getCa_tamano() {
        return ca_tamano;
    }

    public void setCa_tamano(String ca_tamano) {
        this.ca_tamano = ca_tamano;
    }
}
