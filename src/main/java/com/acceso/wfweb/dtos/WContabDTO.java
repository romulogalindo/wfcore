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
                name = Values.QUERYS_WEB_SELECT_PFCONTAB,
                query = "select T.* from frawor4.pfpagtab(:p_co_conten) T ",
                resultClass = WContabDTO.class)
})
public class WContabDTO implements Serializable {

    @Id
    int co_contab;

    int nu_rowspa;

    int nu_colspa;

    int or_numrow;

    int or_numcol;

    String ti_haling;

    String ti_valing;

    int ca_tamcel;

    public WContabDTO() {
    }

    public int getCo_contab() {
        return co_contab;
    }

    public void setCo_contab(int co_contab) {
        this.co_contab = co_contab;
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

    public int getCa_tamcel() {
        return ca_tamcel;
    }

    public void setCa_tamcel(int ca_tamcel) {
        this.ca_tamcel = ca_tamcel;
    }
}
