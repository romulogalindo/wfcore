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
 * @author Mario Huillca <mario.huillca@acceso.com.pe>
 * Created on 30 nov. 2018, 15:11:45
 */
@Entity
@NamedNativeQueries({
        @NamedNativeQuery(
                name = Values.QUERYS_NATIVE_SELECT_PAGREG,
                query = "select * from frawor4.tcpagreg where co_pagina = :p_co_pagina order by or_pagreg",
                resultClass = PagregDTO.class)
})
public class PagregDTO implements Serializable {

    @Id
    int co_pagreg;

    String no_pagreg;
    int co_pagtit;
    short or_pagreg;
    short ti_pagreg;
    String ti_estreg;
    String va_alireg;
    String no_desreg;

    @Transient
    String ti_pagreg_desc;

    public PagregDTO() {
    }

    public int getCo_pagreg() {
        return co_pagreg;
    }

    public void setCo_pagreg(int co_pagreg) {
        this.co_pagreg = co_pagreg;
    }

    public String getNo_pagreg() {
        return no_pagreg;
    }

    public void setNo_pagreg(String no_pagreg) {
        this.no_pagreg = no_pagreg;
    }

    public int getCo_pagtit() {
        return co_pagtit;
    }

    public void setCo_pagtit(int co_pagtit) {
        this.co_pagtit = co_pagtit;
    }

    public short getOr_pagreg() {
        return or_pagreg;
    }

    public void setOr_pagreg(short or_pagreg) {
        this.or_pagreg = or_pagreg;
    }

    public short getTi_pagreg() {
        return ti_pagreg;
    }

    public void setTi_pagreg(short ti_pagreg) {
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

    public String getTi_pagreg_desc() {
        String des = "Tipo ";
        switch (ti_pagreg) {
            case 1: {
                des += "Texto";
                break;
            }
            case 3: {
                des += "Combobox";
                break;
            }
            case 4: {
                des += "Combobox(1er item en blanco)";
                break;
            }
            case 7: {
                des += "Fecha";
                break;
            }
            default:
                des += "Sin especificar";
        }
        return des;
    }

    public void setTi_pagreg_desc(String ti_pagreg_desc) {
        this.ti_pagreg_desc = ti_pagreg_desc;
    }
}
