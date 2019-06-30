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
@SqlResultSetMapping(name = "deletebotondto", columns = {
    @ColumnResult(name = "count")})
@NamedNativeQueries({
    @NamedNativeQuery(
            name = Values.QUERYS_NATIVE_SELECT_BUTTONS,
            query = "select * from frawor4.tcpagbot where co_pagina = :p_co_pagina order by or_pagbot;",
            resultClass = BotonDTO.class),
    @NamedNativeQuery(
            name = Values.QUERYS_NATIVE_SAVE_BUTTON,
            query = "select * from wfsistem.pbpagbot_save(:p_co_pagina, :p_co_pagbot, :p_no_pagbot, :p_or_pagbot, :p_ti_pagbot, :p_il_proces, :p_il_confir, :p_no_confir, :p_il_autent, :p_il_peresc, :p_no_icobot, :p_no_icopos, :p_va_colbot, :p_va_toltip);",
            resultClass = BotonDTO.class),
    @NamedNativeQuery(
            name = Values.QUERYS_NATIVE_DELETE_BUTTON,
            query = "delete from frawor4.tcpagbot where co_pagina = :p_co_pagina and co_pagbot = :p_co_pagbot",
            resultSetMapping = "deletebotondto")
})
public class BotonDTO implements Serializable {

    @Id
    int co_pagbot;

    String no_pagbot;
    short or_pagbot;
    String ti_pagbot;
    boolean il_proces;
    boolean il_confir;
    String no_confir;
    boolean il_autent;
    boolean il_peresc;
    String no_icobot;
    String no_icopos;
    String va_colbot;
    String va_toltip;

    @Transient
    PagconDTO pagconDTO;

    @Transient
    Integer co_condes;

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

    public short getOr_pagbot() {
        return or_pagbot;
    }

    public void setOr_pagbot(short or_pagbot) {
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

    public PagconDTO getPagconDTO() {
        return pagconDTO;
    }

    public void setPagconDTO(PagconDTO pagconDTO) {
        this.pagconDTO = pagconDTO;
    }

    public Integer getCo_condes() {
        return co_condes;
    }

    public void setCo_condes(Integer co_condes) {
        this.co_condes = co_condes;
    }

    public String getNo_icobot() {
        return no_icobot;
    }

    public void setNo_icobot(String no_icobot) {
        this.no_icobot = no_icobot;
    }

    public String getNo_icopos() {
        return no_icopos;
    }

    public void setNo_icopos(String no_icopos) {
        this.no_icopos = no_icopos;
    }

    public String getVa_colbot() {
        return va_colbot;
    }

    public void setVa_colbot(String va_colbot) {
        this.va_colbot = va_colbot;
    }

    public String getVa_toltip() {
        return va_toltip;
    }

    public void setVa_toltip(String va_toltip) {
        this.va_toltip = va_toltip;
    }

    @Override
    public String toString() {
        return "BotonDTO{"
                + "co_pagbot=" + co_pagbot
                + ", no_pagbot='" + no_pagbot + '\''
                + ", or_pagbot=" + or_pagbot
                + ", ti_pagbot='" + ti_pagbot + '\''
                + ", il_proces=" + il_proces
                + ", il_confir=" + il_confir
                + ", no_confir='" + no_confir + '\''
                + ", il_autent=" + il_autent
                + ", il_peresc=" + il_peresc
                + ", pagconDTO=" + pagconDTO
                + ", co_condes=" + co_condes
                + '}';
    }
}
