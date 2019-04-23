package com.acceso.wfcore.dtos;

import com.acceso.wfcore.utils.Values;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Romulo Galindo
 */
@Entity
@NamedNativeQueries({
    @NamedNativeQuery(
            name = Values.QUERYS_NATIVE_SELECT_PAGCONPAR,
            query = "select co_conpar, co_pagina, co_pagbot, co_pagreg, co_conori, co_condes from frawor4.tcpagconpar where co_pagina = :p_co_pagina and co_pagbot = :p_co_pagbot and co_conori = :p_co_conori",
            resultClass = PagconparDTO.class)
})
public class PagconparDTO implements Serializable {

    @Id
    Short co_conpar;

    Short co_pagreg;
    int co_pagina;
    short co_pagbot;
    int co_conori;
    int co_condes;

    public PagconparDTO() {
    }

    public PagconparDTO(Short co_conpar) {
        this.co_conpar = co_conpar;
    }
    public PagconparDTO(Short co_conpar, Short co_pagreg) {
        this.co_conpar = co_conpar;
        this.co_pagreg = co_pagreg;
    }

    public Short getCo_conpar() {
        return co_conpar;
    }

    public void setCo_conpar(Short co_conpar) {
        this.co_conpar = co_conpar;
    }

    public Short getCo_pagreg() {
        return co_pagreg;
    }

    public void setCo_pagreg(Short co_pagreg) {
        this.co_pagreg = co_pagreg;
    }

    public int getCo_pagina() {
        return co_pagina;
    }

    public void setCo_pagina(int co_pagina) {
        this.co_pagina = co_pagina;
    }

    public short getCo_pagbot() {
        return co_pagbot;
    }

    public void setCo_pagbot(short co_pagbot) {
        this.co_pagbot = co_pagbot;
    }

    public int getCo_conori() {
        return co_conori;
    }

    public void setCo_conori(int co_conori) {
        this.co_conori = co_conori;
    }

    public int getCo_condes() {
        return co_condes;
    }

    public void setCo_condes(int co_condes) {
        this.co_condes = co_condes;
    }

}
