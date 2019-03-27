package com.acceso.wfweb.dtos;

import com.acceso.wfweb.utils.Values;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author Rómulo Galindo<romulogalindo@gmail.com>
 * Created on 13 dic. 2018, 19:06:46
 */
@Entity
@NamedNativeQueries({
        @NamedNativeQuery(
                name = Values.QUERYS_WEB_SELECT_PFPAGBOT,
                query = "select * from frawor4.pfpagbot(:p_co_pagina, :p_id_frawor , :p_co_conten )",
                resultClass = BotonDTO.class)
})
public class BotonDTO implements Serializable {

    @Id
    int co_pagbot;

    String no_pagbot;

    int or_pagbot;

    String ti_pagbot;

    boolean il_proces;

    boolean il_confir;

    String no_confir;

    boolean il_autent;

    int co_condes;

    @Transient
    List<WParametroDTO> parametros;

    public BotonDTO() {
    }

    public int getCo_pagbot() {
        return co_pagbot;
    }

    public void setCo_pagbot(int co_pagbot) {
        this.co_pagbot = co_pagbot;
    }

    public String getNo_pagbot() {
        return no_pagbot;
    }

    public void setNo_pagbot(String no_pagbot) {
        this.no_pagbot = no_pagbot;
    }

    public int getOr_pagbot() {
        return or_pagbot;
    }

    public void setOr_pagbot(int or_pagbot) {
        this.or_pagbot = or_pagbot;
    }

    public String getTi_pagbot() {
        return ti_pagbot;
    }

    public void setTi_pagbot(String ti_pagbot) {
        this.ti_pagbot = ti_pagbot;
    }

    public boolean isIl_proces() {
        return il_proces;
    }

    public void setIl_proces(boolean il_proces) {
        this.il_proces = il_proces;
    }

    public boolean isIl_confir() {
        return il_confir;
    }

    public void setIl_confir(boolean il_confir) {
        this.il_confir = il_confir;
    }

    public String getNo_confir() {
        return no_confir;
    }

    public void setNo_confir(String no_confir) {
        this.no_confir = no_confir;
    }

    public boolean isIl_autent() {
        return il_autent;
    }

    public void setIl_autent(boolean il_autent) {
        this.il_autent = il_autent;
    }

    public int getCo_condes() {
        return co_condes;
    }

    public void setCo_condes(int co_condes) {
        this.co_condes = co_condes;
    }

    public List<WParametroDTO> getParametros() {
        return parametros;
    }

    public void setParametros(List<WParametroDTO> parametros) {
        this.parametros = parametros;
    }
}
