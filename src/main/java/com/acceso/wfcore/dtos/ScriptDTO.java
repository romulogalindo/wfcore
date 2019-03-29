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
                name = Values.QUERYS_NATIVE_GET_SCRIPT,
                query = "select  :p_co_pagina as co_pagina, tx_funwf2 as no_script, 1 as ti_script from pfvalpag(:p_co_pagina) where no_funwf2 ilike 'pfvalpag%'",
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

    public Integer getCo_pagina() {
        return co_pagina;
    }

    public void setCo_pagina(Integer co_pagina) {
        this.co_pagina = co_pagina;
    }

    public String getNo_script() {
        return no_script;
    }

    public void setNo_script(String no_script) {
        this.no_script = no_script;
    }

    public int getTi_script() {
        return ti_script;
    }

    public void setTi_script(int ti_script) {
        this.ti_script = ti_script;
    }
}
