/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wf.dtos;

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
            name = "wfcore.cnx_test",
            query = "select co_conexi, no_conexi from wfsistem.tsconexi",
            resultClass = ConexionDTO.class)
})
public class ConexionDTO {

    @Id
    int co_conexi;

    String no_conexi;

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

}
