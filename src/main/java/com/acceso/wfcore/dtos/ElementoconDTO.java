/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acceso.wfcore.dtos;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * @author Romulo Galindo
 */
@Entity
public class ElementoconDTO implements Serializable {

    public static final int TYPE_TITLE = 1;
    public static final int TYPE_REGIST = 2;
    @Id
    Integer co_elemen;

    Integer ti_elemen;
    String no_elemen;

    @Transient
    PagtitconDTO pagtitDTO;

    @Transient
    PagregconDTO pagregDTO;

    public ElementoconDTO() {
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

    public PagtitconDTO getPagtitDTO() {
        return pagtitDTO;
    }

    public void setPagtitDTO(PagtitconDTO pagtitDTO) {
        this.pagtitDTO = pagtitDTO;
    }

    public PagregconDTO getPagregDTO() {
        return pagregDTO;
    }

    public void setPagregDTO(PagregconDTO pagregDTO) {
        this.pagregDTO = pagregDTO;
    }

    @Override
    public String toString() {
        return "ElementoconDTO{" +
                "co_elemen=" + co_elemen +
                ", ti_elemen=" + ti_elemen +
                ", no_elemen='" + no_elemen + '\'' +
                ", pagtitDTO=" + pagtitDTO +
                ", pagregDTO=" + pagregDTO +
                '}';
    }
}
