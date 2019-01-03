/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acceso.wfcore.dtos;

import com.acceso.wfcore.utils.Values;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import java.io.Serializable;

/**
 *
 * @author Mario Huillca <mario.huillca@acceso.com.pe>
 * Created on 30 nov. 2018, 15:11:45
 */
@Entity
@NamedNativeQueries({
    @NamedNativeQuery(
            name = Values.QUERYS_NATIVE_SELECT_MENU_SISTEMA,
            query = "select co_elemen, no_elemen, co_menpad, co_modulo, co_sistem, co_subsis, co_paquet, co_identi, no_modulo, or_elemen, va_colele "
            + "from wfsistem.pbgetmenu_sistem()",
            resultClass = MenuDTO.class),
      @NamedNativeQuery(
            name = Values.QUERYS_NATIVE_SELECT_MENU_SUBSISTEMA,
            query = "select co_elemen, no_elemen, co_menpad, co_modulo, co_sistem, co_subsis, co_paquet, co_identi, no_modulo, or_elemen, va_colele "
                  + "from wfsistem.pbgetmenu_subsistem()",
            resultClass = MenuDTO.class),
      @NamedNativeQuery(
            name = Values.QUERYS_NATIVE_SELECT_MENU_PAQUETE,
            query = "select co_elemen, no_elemen, co_menpad, co_modulo, co_sistem, co_subsis, co_paquet, co_identi, no_modulo, or_elemen, va_colele "
                  + "from wfsistem.pbgetmenu_paquete()",
            resultClass = MenuDTO.class),
      @NamedNativeQuery(
            name = Values.QUERYS_NATIVE_SELECT_MENU_MODPAD,
            query = "select co_elemen, no_elemen, co_menpad, co_modulo, co_sistem, co_subsis, co_paquet, co_identi, no_modulo, or_elemen, va_colele "
                  + "from wfsistem.pbgetmenu_modpad()",
            resultClass = MenuDTO.class),
      @NamedNativeQuery(
            name = Values.QUERYS_NATIVE_SELECT_MENU_SUBMOD,
            query = "select co_elemen, no_elemen, co_menpad, co_modulo, co_sistem, co_subsis, co_paquet, co_identi, no_modulo, or_elemen, va_colele "
                  + "from wfsistem.pbgetmenu_submod()",
            resultClass = MenuDTO.class)
})
public class MenuDTO implements Serializable {

    @Id
    Integer co_elemen;

    String no_elemen;
    Integer co_menpad;
    Integer co_modulo;
    Integer co_sistem;
    Integer co_subsis;
    Integer co_paquet;
    String co_identi;
    String no_modulo;
    Integer or_elemen;
    String va_colele;

    public MenuDTO() {

    }

    public Integer getCo_elemen() {
        return co_elemen;
    }

    public void setCo_elemen(Integer co_elemen) {
        this.co_elemen = co_elemen;
    }

    public String getNo_elemen() {
        return no_elemen;
    }

    public void setNo_elemen(String no_elemen) {
        this.no_elemen = no_elemen;
    }

    public Integer getCo_menpad() {
        return co_menpad;
    }

    public void setCo_menpad(Integer co_menpad) {
        this.co_menpad = co_menpad;
    }

    public Integer getCo_modulo() {
        return co_modulo;
    }

    public void setCo_modulo(Integer co_modulo) {
        this.co_modulo = co_modulo;
    }

    public Integer getCo_sistem() {
        return co_sistem;
    }

    public void setCo_sistem(Integer co_sistem) {
        this.co_sistem = co_sistem;
    }

    public Integer getCo_subsis() {
        return co_subsis;
    }

    public void setCo_subsis(Integer co_subsis) {
        this.co_subsis = co_subsis;
    }

    public Integer getCo_paquet() {
        return co_paquet;
    }

    public void setCo_paquet(Integer co_paquet) {
        this.co_paquet = co_paquet;
    }

    public String getCo_identi() {
        return co_identi;
    }

    public void setCo_identi(String co_identi) {
        this.co_identi = co_identi;
    }

    public String getNo_modulo() {
        return no_modulo;
    }

    public void setNo_modulo(String no_modulo) {
        this.no_modulo = no_modulo;
    }

    public Integer getOr_elemen() {
        return or_elemen;
    }

    public void setOr_elemen(Integer or_elemen) {
        this.or_elemen = or_elemen;
    }

    public String getVa_colele() {
        return va_colele;
    }

    public void setVa_colele(String va_colele) {
        this.va_colele = va_colele;
    }

    @Override
    public String toString() {
        return "MenuDTO{" +
              "co_elemen=" + co_elemen +
              ", no_elemen='" + no_elemen + '\'' +
              ", co_menpad=" + co_menpad +
              ", co_modulo=" + co_modulo +
              ", co_sistem=" + co_sistem +
              ", co_subsis=" + co_subsis +
              ", co_paquet=" + co_paquet +
              ", co_identi='" + co_identi + '\'' +
              ", no_modulo='" + no_modulo + '\'' +
              ", or_elemen=" + or_elemen +
              ", va_colele='" + va_colele + '\'' +
              '}';
    }
}
