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
            name = Values.QUERYS_NATIVE_SELECT_MODULO,
            query = "select co_modulo, no_modulo, ur_modulo, co_modant, co_sistem, no_sistem, co_subsis, no_subsis, co_paquet, no_paquet, co_plataf, no_plataf "
            + "from wfsistem.pbmodulo_list()",
            resultClass = ModuloDTO.class),
    @NamedNativeQuery(
            name = Values.QUERYS_NATIVE_GRABAR_MODULO,
            query = "select co_modulo, no_modulo, ur_modulo, co_modant, co_sistem, no_sistem, co_subsis, no_subsis, co_paquet, no_paquet, co_plataf, no_plataf "
            + "from wfsistem.pbmodulo_save(:co_modulo, :no_modulo, :ur_modulo, :co_paquet, :co_modant, :co_subsis, :co_plataf)",
            resultClass = ModuloDTO.class),
      @NamedNativeQuery(
            name = Values.QUERYS_NATIVE_SELECT_MODULO_MENU,
            query = "select co_modulo, no_modulo, ur_modulo, co_modant, co_sistem, no_sistem, co_subsis, no_subsis, co_paquet, no_paquet, co_plataf, no_plataf "
                  + "from wfsistem.pbmodulo_list_menu()",
            resultClass = ModuloDTO.class)
})
public class ModuloDTO implements Serializable {

    @Id
    Integer co_modulo;

    String no_modulo;
    String ur_modulo;
    Integer co_modant;
    Integer co_sistem;
    String no_sistem;
    Integer co_subsis;
    String no_subsis;
    Integer co_paquet;
    String no_paquet;
    Integer co_plataf;
    String no_plataf;

    public Integer getCo_modulo() {
        return co_modulo;
    }

    public void setCo_modulo(Integer co_modulo) {
        this.co_modulo = co_modulo;
    }

    public String getNo_modulo() {
        return no_modulo;
    }

    public void setNo_modulo(String no_modulo) {
        this.no_modulo = no_modulo;
    }

    public String getUr_modulo() {
        return ur_modulo;
    }

    public void setUr_modulo(String ur_modulo) {
        this.ur_modulo = ur_modulo;
    }

    public Integer getCo_modant() {
        return co_modant;
    }

    public void setCo_modant(Integer co_modant) {
        this.co_modant = co_modant;
    }

    public Integer getCo_sistem() {
        return co_sistem;
    }

    public void setCo_sistem(Integer co_sistem) {
        this.co_sistem = co_sistem;
    }

    public String getNo_sistem() {
        return no_sistem;
    }

    public void setNo_sistem(String no_sistem) {
        this.no_sistem = no_sistem;
    }

    public Integer getCo_subsis() {
        return co_subsis;
    }

    public void setCo_subsis(Integer co_subsis) {
        this.co_subsis = co_subsis;
    }

    public String getNo_subsis() {
        return no_subsis;
    }

    public void setNo_subsis(String no_subsis) {
        this.no_subsis = no_subsis;
    }

    public Integer getCo_paquet() {
        return co_paquet;
    }

    public void setCo_paquet(Integer co_paquet) {
        this.co_paquet = co_paquet;
    }

    public String getNo_paquet() {
        return no_paquet;
    }

    public void setNo_paquet(String no_paquet) {
        this.no_paquet = no_paquet;
    }

    public Integer getCo_plataf() {
        return co_plataf;
    }

    public void setCo_plataf(Integer co_plataf) {
        this.co_plataf = co_plataf;
    }

    public String getNo_plataf() {
        return no_plataf;
    }

    public void setNo_plataf(String no_plataf) {
        this.no_plataf = no_plataf;
    }

    @Override
    public String toString() {
        return "ModuloDTO{" +
              "co_modulo=" + co_modulo +
              ", no_modulo='" + no_modulo + '\'' +
              ", ur_modulo='" + ur_modulo + '\'' +
              ", co_modant=" + co_modant +
              ", co_sistem=" + co_sistem +
              ", no_sistem='" + no_sistem + '\'' +
              ", co_subsis=" + co_subsis +
              ", no_subsis='" + no_subsis + '\'' +
              ", co_paquet=" + co_paquet +
              ", no_paquet='" + no_paquet + '\'' +
              ", co_plataf=" + co_plataf +
              ", no_plataf='" + no_plataf + '\'' +
              '}';
    }
}
