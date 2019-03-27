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
                name = Values.QUERYS_WEB_SELECT_PFPAGPAR,
                query = "select * from frawor4.pfpagpar (:p_co_conten, :p_co_pagina , :p_co_pagbot )",
                resultClass = WParametroDTO.class)
})
public class WParametroDTO implements Serializable {

    @Id
    int co_pagreg;

    int co_condes;

    int co_conpar;

    public WParametroDTO() {
    }

    public int getCo_pagreg() {
        return co_pagreg;
    }

    public void setCo_pagreg(int co_pagreg) {
        this.co_pagreg = co_pagreg;
    }

    public int getCo_condes() {
        return co_condes;
    }

    public void setCo_condes(int co_condes) {
        this.co_condes = co_condes;
    }

    public int getCo_conpar() {
        return co_conpar;
    }

    public void setCo_conpar(int co_conpar) {
        this.co_conpar = co_conpar;
    }
}
