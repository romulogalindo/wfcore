package com.acceso.wfweb.dtos;

import com.acceso.wfweb.utils.Values;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import java.io.Serializable;

/**
 * @author Rómulo Galindo<romulogalindo@gmail.com>
 * Created on 13 dic. 2018, 19:06:46
 */
@Entity
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "frawor2.pfins_tbpagreg",
                query = "select * from frawor2.pfins_tbpagreg(:p_id_frawor,:p_co_pagina,:p_co_pagreg,:p_nu_pagfil,:p_va_pagreg)",
                resultClass = PagregDTO.class)
})
public class PagregDTO implements Serializable {

    @Id
    String pfins_tbpagreg;

    //@Id
    public String getPfins_tbpagreg() {
        return pfins_tbpagreg;
    }

    public void setPfins_tbpagreg(String pfins_tbpagreg) {
        this.pfins_tbpagreg = pfins_tbpagreg;
    }
}

