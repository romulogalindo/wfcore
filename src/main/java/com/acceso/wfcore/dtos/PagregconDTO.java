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
                name = Values.QUERYS_NATIVE_SELECT_PAGREGCON,
                query = "select * from frawor4.tcpagregcon where co_conten = :p_co_conten and co_pagina = :p_co_pagina order by or_pagreg",
                resultClass = PagregconDTO.class),
        @NamedNativeQuery(
                name = Values.QUERYS_NATIVE_SAVE_PAGREGCON,
                query = "select * from wfsistem.pbpagregcon_save(:p_co_pagreg, :p_co_conten, :p_co_pagina, :pn_co_pagreg, :p_no_pagreg, :p_co_pagtit, :p_or_pagreg, :p_ti_pagreg, :p_ti_estreg, :p_va_alireg, :p_no_desreg, :p_ca_carcol, :p_ca_carrow, :p_ti_nowrap, :p_il_onchan, :p_va_valign, :p_il_guareg, :p_ca_caract)",
                resultClass = PagregconDTO.class)
})
public class PagregconDTO implements Serializable {

    @Id
    int co_pagreg;

    @Transient
    int co_pagreg2;

    int co_pagina;
    String no_pagreg;
    int co_pagtit;
    Short or_pagreg;
    Short ti_pagreg;
    String ti_estreg;
    String va_alireg;
    String no_desreg;
    Short ca_carcol;
    Short ca_carrow;
    String ti_nowrap;
    Boolean il_onchan;
    String va_valign;
    Boolean il_guareg;
    Short ca_caract;

    @Transient
    String ti_pagreg_desc;

    public PagregconDTO() {
    }

    public int getCo_pagreg() {
        return co_pagreg;
    }

    public void setCo_pagreg(int co_pagreg) {
        this.co_pagreg = co_pagreg;
    }

    public int getCo_pagreg2() {
        return co_pagreg2;
    }

    public void setCo_pagreg2(int co_pagreg2) {
        this.co_pagreg2 = co_pagreg2;
    }

    public int getCo_pagina() {
        return co_pagina;
    }

    public void setCo_pagina(int co_pagina) {
        this.co_pagina = co_pagina;
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

    public void setOr_pagreg(Short or_pagreg) {
        this.or_pagreg = or_pagreg;
    }

    public Short getOr_pagreg() {
        return or_pagreg;
    }

    public void setTi_pagreg(Short ti_pagreg) {
        this.ti_pagreg = ti_pagreg;
    }

    public Short getTi_pagreg() {
        return ti_pagreg;
    }

    public Short getCa_carcol() {
        return ca_carcol;
    }

    public void setCa_carcol(Short ca_carcol) {
        this.ca_carcol = ca_carcol;
    }

    public Short getCa_carrow() {
        return ca_carrow;
    }

    public void setCa_carrow(Short ca_carrow) {
        this.ca_carrow = ca_carrow;
    }

    public String getTi_nowrap() {
        return ti_nowrap;
    }

    public void setTi_nowrap(String ti_nowrap) {
        this.ti_nowrap = ti_nowrap;
    }

    public Boolean getIl_onchan() {
        return il_onchan;
    }

    public void setIl_onchan(Boolean il_onchan) {
        this.il_onchan = il_onchan;
    }

    public String getVa_valign() {
        return va_valign;
    }

    public void setVa_valign(String va_valign) {
        this.va_valign = va_valign;
    }

    public Boolean getIl_guareg() {
        return il_guareg==null?false:il_guareg;
    }

    public void setIl_guareg(Boolean il_guareg) {
        this.il_guareg = il_guareg;
    }

    public Short getCa_caract() {
        return ca_caract;
    }

    public void setCa_caract(Short ca_caract) {
        this.ca_caract = ca_caract;
    }

    public void setTi_pagreg_desc(String ti_pagreg_desc) {
        this.ti_pagreg_desc = ti_pagreg_desc;
    }
}
