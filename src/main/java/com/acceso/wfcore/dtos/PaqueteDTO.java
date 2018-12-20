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
            name = Values.QUERYS_NATIVE_SELECT_PAQUETE,
            query = "select co_paquet, no_paquet, co_subsis, or_paquet, no_prefij, ur_defaul, ur_arcadj, no_coldef, no_subsis, co_sistem, no_sistem "
            + "from wfsistem.pbpaquete_list()",
            resultClass = PaqueteDTO.class),
    @NamedNativeQuery(
            name = Values.QUERYS_NATIVE_GRABAR_PAQUETE,
            query = "select co_paquet, no_paquet, co_subsis, or_paquet, no_prefij, ur_defaul, ur_arcadj, no_coldef, no_subsis, co_sistem, no_sistem "
            + "from wfsistem.pbpaquete_save(:co_paquet, :no_paquet, :co_subsis, :or_paquet, :no_prefij, :ur_defaul, :ur_arcadj, :no_coldef)",
            resultClass = PaqueteDTO.class)
})
public class PaqueteDTO implements Serializable {

    @Id
    Integer co_paquet;

    String no_paquet;
    Integer co_subsis;
    Integer or_paquet;
    String no_prefij;
    String ur_defaul;
    String ur_arcadj;
    String no_coldef;

    String no_subsis;
    Integer co_sistem;
    String no_sistem;

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

    public Integer getCo_subsis() {
        return co_subsis;
    }

    public void setCo_subsis(Integer co_subsis) {
        this.co_subsis = co_subsis;
    }

    public Integer getOr_paquet() {
        return or_paquet;
    }

    public void setOr_paquet(Integer or_paquet) {
        this.or_paquet = or_paquet;
    }

    public String getNo_prefij() {
        return no_prefij;
    }

    public void setNo_prefij(String no_prefij) {
        this.no_prefij = no_prefij;
    }

    public String getUr_defaul() {
        return ur_defaul;
    }

    public void setUr_defaul(String ur_defaul) {
        this.ur_defaul = ur_defaul;
    }

    public String getUr_arcadj() {
        return ur_arcadj;
    }

    public void setUr_arcadj(String ur_arcadj) {
        this.ur_arcadj = ur_arcadj;
    }

    public String getNo_coldef() {
        return no_coldef;
    }

    public void setNo_coldef(String no_coldef) {
        this.no_coldef = no_coldef;
    }

    public String getNo_subsis() {
        return no_subsis;
    }

    public void setNo_subsis(String no_subsis) {
        this.no_subsis = no_subsis;
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

    @Override
    public String toString() {
        return "PaqueteDTO{" +
              "co_paquet=" + co_paquet +
              ", no_paquet='" + no_paquet + '\'' +
              ", co_subsis=" + co_subsis +
              ", or_paquet=" + or_paquet +
              ", no_prefij='" + no_prefij + '\'' +
              ", ur_defaul='" + ur_defaul + '\'' +
              ", ur_arcadj='" + ur_arcadj + '\'' +
              ", no_coldef='" + no_coldef + '\'' +
              ", no_subsis='" + no_subsis + '\'' +
              ", co_sistem=" + co_sistem +
              ", no_sistem='" + no_sistem + '\'' +
              '}';
    }
}
