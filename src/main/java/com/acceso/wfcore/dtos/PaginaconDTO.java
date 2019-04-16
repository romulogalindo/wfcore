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
 * @author Ròmulo Galindo
 */

@Entity
@NamedNativeQueries({
        @NamedNativeQuery(
                name = Values.QUERYS_NATIVE_GET_PAGINACON,
                query = "select * from frawor4.tcpaginacon where co_conten = :p_co_conten and co_pagina = :p_co_pagina",
                resultClass = PaginaconDTO.class),
        @NamedNativeQuery(
                name = Values.QUERYS_NATIVE_SAVE_PAGINACON,
                query = "select * from wfsistem.pbpaginacon_save(:p_co_pagina,:p_co_conten, :p_no_pagtit,:p_ti_pagina, :p_no_pagdes)",
                resultClass = PaginaconDTO.class)
})
public class PaginaconDTO implements Serializable {

    @Id
    int co_pagina;
    int co_conten;
    String no_pagtit;
    String ti_pagina;
    String no_pagdes;

    @Transient
    List<BotonDTO> ls_botone;

    @Transient
    List<ElementoconDTO> ls_elemen;

    public PaginaconDTO() {
    }

    public int getCo_pagina() {
        return co_pagina;
    }

    public void setCo_pagina(int co_pagina) {
        this.co_pagina = co_pagina;
    }

    public int getCo_conten() {
        return co_conten;
    }

    public void setCo_conten(int co_conten) {
        this.co_conten = co_conten;
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

    public String getNo_pagdes() {
        return no_pagdes;
    }

    public void setNo_pagdes(String no_pagdes) {
        this.no_pagdes = no_pagdes;
    }

    public List<BotonDTO> getLs_botone() {
        return ls_botone;
    }

    public void setLs_botone(List<BotonDTO> ls_botone) {
        this.ls_botone = ls_botone;
    }

    public List<ElementoconDTO> getLs_elemen() {
        return ls_elemen;
    }

    public void setLs_elemen(List<ElementoconDTO> ls_elemen) {
        this.ls_elemen = ls_elemen;
    }

    @Override
    public String toString() {
        return "PaginaconDTO{" +
                "co_pagina=" + co_pagina +
                ", co_conten=" + co_conten +
                ", no_pagtit='" + no_pagtit + '\'' +
                ", ti_pagina='" + ti_pagina + '\'' +
                ", no_pagdes='" + no_pagdes + '\'' +
                ", ls_botone=" + ls_botone +
                ", ls_elemen=" + ls_elemen +
                '}';
    }
}
