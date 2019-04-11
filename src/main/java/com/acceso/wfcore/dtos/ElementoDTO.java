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
public class ElementoDTO implements Serializable {

    public static final int TYPE_TITLE = 1;
    public static final int TYPE_REGIST = 2;
    @Id
    Integer co_elemen;

    Integer ti_elemen;
    String no_elemen;

    @Transient
    PagtitDTO pagtitDTO;

    @Transient
    PagregDTO pagregDTO;

    public ElementoDTO() {
    }

    public Integer getCo_elemen() {
        return co_elemen;
    }

    public void setCo_elemen(Integer co_elemen) {
        this.co_elemen = co_elemen;
    }

    public Integer getTi_elemen() {
        return ti_elemen;
    }

    public void setTi_elemen(Integer ti_elemen) {
        this.ti_elemen = ti_elemen;
    }

    public String getNo_elemen() {
        return no_elemen;
    }

    public void setNo_elemen(String no_elemen) {
        this.no_elemen = no_elemen;
    }

    public PagtitDTO getPagtitDTO() {
        return pagtitDTO;
    }

    public void setPagtitDTO(PagtitDTO pagtitDTO) {
        this.pagtitDTO = pagtitDTO;
    }

    public PagregDTO getPagregDTO() {
        return pagregDTO;
    }

    public void setPagregDTO(PagregDTO pagregDTO) {
        this.pagregDTO = pagregDTO;
    }

    @Override
    public String toString() {
        return "ElementoDTO{" +
                "co_elemen=" + co_elemen +
                ", ti_elemen=" + ti_elemen +
                ", no_elemen='" + no_elemen + '\'' +
                ", pagtitDTO=" + pagtitDTO +
                ", pagregDTO=" + pagregDTO +
                '}';
    }
}
