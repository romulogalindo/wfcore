/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acceso.wfcore.dtos;

import com.acceso.wfcore.utils.Values;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

/**
 *
 * @author Mario Huillca <mario.huillca@acceso.com.pe>
 * Created on 30 nov. 2018, 15:11:45
 */
@Entity
@NamedNativeQueries({
    @NamedNativeQuery(
            name = Values.QUERYS_NATIVE_GET_ALLCNX,
            query = "select co_conexi, no_conexi, nu_maxpoo, nu_timout, no_usuari, pw_usuari, ur_domini, nu_puerto, no_datbas "
            + "from wfsistem.tsconexi",
            resultClass = ConexionDTO.class),
    @NamedNativeQuery(
            name = "wfcore.cnx_insert",
            query = "select co_conexi, no_conexi, nu_maxpoo, nu_timout, no_usuari, pw_usuari, ur_domini, nu_puerto, no_datbas "
            + "from wfsistem.pbregistra_conexion(:no_conexi, :nu_maxpoo, :nu_timout, :no_usuari, :pw_usuari, :ur_domini, :nu_puerto, :no_datbas)",
            resultClass = ConexionDTO.class),
    @NamedNativeQuery(
            name = "wfcore.cnx_update",
            query = "select co_conexi, no_conexi, nu_maxpoo, nu_timout, no_usuari, pw_usuari, ur_domini, nu_puerto, no_datbas "
            + "from wfsistem.pbactualiza_conexion(:co_conexi, :no_conexi, :nu_maxpoo, :nu_timout, :no_usuari, :pw_usuari, :ur_domini, :nu_puerto, :no_datbas)",
            resultClass = ConexionDTO.class)
})
public class ConexionDTO implements Serializable {

    @Id
    int co_conexi;

    String no_conexi;
    Integer nu_maxpoo;
    Integer nu_timout;
    String no_usuari;
    String pw_usuari;
    String ur_domini;
    Integer nu_puerto;
    String no_datbas;

    public int getCo_conexi() {
        return co_conexi;
    }

    public void setCo_conexi(int co_conexi) {
        this.co_conexi = co_conexi;
    }

    public String getNo_conexi() {
        return no_conexi;
    }

    public void setNo_conexi(String no_conexi) {
        this.no_conexi = no_conexi;
    }

    public Integer getNu_maxpoo() {
        return nu_maxpoo;
    }

    public void setNu_maxpoo(Integer nu_maxpoo) {
        this.nu_maxpoo = nu_maxpoo;
    }

    public Integer getNu_timout() {
        return nu_timout;
    }

    public void setNu_timout(Integer nu_timout) {
        this.nu_timout = nu_timout;
    }

    public String getNo_usuari() {
        return no_usuari;
    }

    public void setNo_usuari(String no_usuari) {
        this.no_usuari = no_usuari;
    }

    public String getPw_usuari() {
        return pw_usuari;
    }

    public void setPw_usuari(String pw_usuari) {
        this.pw_usuari = pw_usuari;
    }

    public String getUr_domini() {
        return ur_domini;
    }

    public void setUr_domini(String ur_domini) {
        this.ur_domini = ur_domini;
    }

    public Integer getNu_puerto() {
        return nu_puerto;
    }

    public void setNu_puerto(Integer nu_puerto) {
        this.nu_puerto = nu_puerto;
    }

    public String getNo_datbas() {
        return no_datbas;
    }

    public void setNo_datbas(String no_datbas) {
        this.no_datbas = no_datbas;
    }

    @Override
    public String toString() {
        return "ConexionDTO{" + "co_conexi=" + co_conexi + ", no_conexi=" + no_conexi + ", nu_maxpoo=" + nu_maxpoo + ", nu_timout=" + nu_timout + ", no_usuari=" + no_usuari + ", pw_usuari=" + pw_usuari + ", ur_domini=" + ur_domini + ", nu_puerto=" + nu_puerto + ", no_datbas=" + no_datbas + '}';
    }

}
