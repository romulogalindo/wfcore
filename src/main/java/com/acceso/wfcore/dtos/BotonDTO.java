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
 * @author Mario Huillca <mario.huillca@acceso.com.pe>
 * Created on 30 nov. 2018, 15:11:45
 */

@Entity
@NamedNativeQueries({
        @NamedNativeQuery(
                name = Values.QUERYS_NATIVE_SELECT_BUTTONS,
                query = "select * from frawor4.tcpagbot where co_pagina = :p_co_pagina order by or_pagbot;",
                resultClass = BotonDTO.class),
        @NamedNativeQuery(
                name = Values.QUERYS_NATIVE_SAVE_BUTTON,
                query = "select * from wfsistem.pbpagbot_save(:p_co_pagina, :co_pagbot, :no_pagbot, :or_pagbot, :ti_pagbot, :il_proces, :il_confir, :no_confir, :il_autent, :il_peresc);",
                resultClass = BotonDTO.class)
})
public class BotonDTO implements Serializable {

    @Id
    int co_pagbot;

    String no_pagbot;
    String or_pagbot;
    String ti_pagbot;
    boolean il_proces;
    boolean il_confir;
    String no_confir;
    boolean il_autent;
    boolean il_peresc;

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

    public String getOr_pagbot() {
        return or_pagbot;
    }

    public void setOr_pagbot(String or_pagbot) {
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

    public boolean isIl_peresc() {
        return il_peresc;
    }

    public void setIl_peresc(boolean il_peresc) {
        this.il_peresc = il_peresc;
    }
}
