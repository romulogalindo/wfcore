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
import java.util.Date;

/**
 * @author Mario Huillca <mario.huillca@acceso.com.pe>
 * Created on 30 nov. 2018, 15:11:45
 */

@Entity
@NamedNativeQueries({
        @NamedNativeQuery(
                name = Values.QUERYS_NATIVE_SELECT_USUARIO,
                query = "select  co_pagina, no_script, ti_script from wfsistem.pbget_script(:p_co_pagina)",
                resultClass = ScriptDTO.class)
})
public class ScriptDTO implements Serializable {

    @Id
    Integer co_pagina;

    String no_script;

    /*
    [1]=store procedure
    [2]=javascript shell
    * */
    int ti_script;

    public ScriptDTO() {
    }
}
