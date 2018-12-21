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
            name = Values.QUERYS_NATIVE_SELECT_MENU,
            query = "select co_mensis, no_mensis, co_menpad, co_modulo, or_mensis, co_subsis, co_paquet "
            + "from wfsistem.pbmodulo_list()",
            resultClass = MenuDTO.class)
})
public class MenuDTO implements Serializable {

    @Id
    Integer co_mensis;

    String no_mensis;
    Integer co_menpad;
    Integer co_modulo;
    Integer or_mensis;
    Integer co_subsis;
    Integer co_paquet;

    public Integer getCo_mensis() {
        return co_mensis;
    }

    public void setCo_mensis(Integer co_mensis) {
        this.co_mensis = co_mensis;
    }

    public String getNo_mensis() {
        return no_mensis;
    }

    public void setNo_mensis(String no_mensis) {
        this.no_mensis = no_mensis;
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

    public Integer getOr_mensis() {
        return or_mensis;
    }

    public void setOr_mensis(Integer or_mensis) {
        this.or_mensis = or_mensis;
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

    @Override
    public String toString() {
        return "MenuDTO{" +
              "co_mensis=" + co_mensis +
              ", no_mensis='" + no_mensis + '\'' +
              ", co_menpad=" + co_menpad +
              ", co_modulo=" + co_modulo +
              ", or_mensis=" + or_mensis +
              ", co_subsis=" + co_subsis +
              ", co_paquet=" + co_paquet +
              '}';
    }
}
