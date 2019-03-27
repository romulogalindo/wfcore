package com.acceso.wfweb.dtos;

import com.acceso.wfweb.utils.Values;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Rómulo Galindo<romulogalindo@gmail.com>
 * Created on 13 dic. 2018, 19:06:46
 */
@Entity
@NamedNativeQueries({
        @NamedNativeQuery(
                name = Values.QUERYS_WEB_SELECT_PFPAGREG,
                query = "select * from frawor4.pfpagreg( :p_co_pagina , :p_id_frawor , :p_co_conten )",
                resultClass = WRegistroDTO.class)
})
public class WRegistroDTO implements Serializable {

    @Id
    int co_pagreg;

    int co_pagtit;

    String no_pagreg;

    int or_pagreg;

    int ti_pagreg;

    String ti_estreg;

    String va_alireg;

    String no_desreg;

    int ca_carcol;

    int ca_carrow;

    String ti_nowrap;

    boolean il_onchan;

    String va_valign;

    boolean il_guareg;

    int ca_caract;

    @Transient
    boolean il_hamoda;

    public WRegistroDTO() {
    }

    public int getCo_pagreg() {
        return co_pagreg;
    }

    public void setCo_pagreg(int co_pagreg) {
        this.co_pagreg = co_pagreg;
    }

    public int getCo_pagtit() {
        return co_pagtit;
    }

    public void setCo_pagtit(int co_pagtit) {
        this.co_pagtit = co_pagtit;
    }

    public String getNo_pagreg() {
        return no_pagreg;
    }

    public void setNo_pagreg(String no_pagreg) {
        this.no_pagreg = no_pagreg;
    }

    public int getOr_pagreg() {
        return or_pagreg;
    }

    public void setOr_pagreg(int or_pagreg) {
        this.or_pagreg = or_pagreg;
    }

    public int getTi_pagreg() {
        return ti_pagreg;
    }

    public void setTi_pagreg(int ti_pagreg) {
        this.ti_pagreg = ti_pagreg;
    }

    public String getTi_estreg() {
        return ti_estreg;
    }

    public void setTi_estreg(String ti_estreg) {
        this.ti_estreg = ti_estreg;
    }

    public String getVa_alireg() {
        return va_alireg;
    }

    public void setVa_alireg(String va_alireg) {
        this.va_alireg = va_alireg;
    }

    public String getNo_desreg() {
        return no_desreg;
    }

    public void setNo_desreg(String no_desreg) {
        this.no_desreg = no_desreg;
    }

    public int getCa_carcol() {
        return ca_carcol;
    }

    public void setCa_carcol(int ca_carcol) {
        this.ca_carcol = ca_carcol;
    }

    public int getCa_carrow() {
        return ca_carrow;
    }

    public void setCa_carrow(int ca_carrow) {
        this.ca_carrow = ca_carrow;
    }

    public String getTi_nowrap() {
        return ti_nowrap;
    }

    public void setTi_nowrap(String ti_nowrap) {
        this.ti_nowrap = ti_nowrap;
    }

    public boolean isIl_onchan() {
        return il_onchan;
    }

    public void setIl_onchan(boolean il_onchan) {
        this.il_onchan = il_onchan;
    }

    public String getVa_valign() {
        return va_valign;
    }

    public void setVa_valign(String va_valign) {
        this.va_valign = va_valign;
    }

    public boolean isIl_guareg() {
        return il_guareg;
    }

    public void setIl_guareg(boolean il_guareg) {
        this.il_guareg = il_guareg;
    }

    public int getCa_caract() {
        return ca_caract;
    }

    public void setCa_caract(int ca_caract) {
        this.ca_caract = ca_caract;
    }

    public boolean isIl_hamoda() {
        return il_hamoda;
    }

    public void setIl_hamoda(boolean il_hamoda) {
        this.il_hamoda = il_hamoda;
    }
}
