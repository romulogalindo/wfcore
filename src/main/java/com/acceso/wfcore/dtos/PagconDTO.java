/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acceso.wfcore.dtos;

import com.acceso.wfcore.utils.Values;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Romulo Galindo
 */

@Entity
@SqlResultSetMapping(name = "deletepagcondto", columns = {@ColumnResult(name = "count")})
@NamedNativeQueries({
        @NamedNativeQuery(
                name = Values.QUERYS_NATIVE_SELECT_PAGCON,
                query = "select * from frawor4.tcpagcon where co_conori = :p_co_conten and co_pagina = :p_co_pagina",
                resultClass = PagconDTO.class),
        @NamedNativeQuery(
                name = Values.QUERYS_NATIVE_SAVE_PAGCON,
                query = "select pbpagcon_save as empty from wfsistem.pbpagcon_save(:p_co_pagbot, :p_co_conten, :p_co_pagina, :p_co_condes)",
                resultClass = EmptyDTO.class),
        @NamedNativeQuery(
                name = Values.QUERYS_NATIVE_DELETE_PAGCON,
                query = "delete from frawor4.tcpagcon  where co_conori = :p_co_conten and co_pagina = :p_co_pagina and co_pagbot = :p_co_pagbot;",
                resultSetMapping = "deletepagcondto")
})
public class PagconDTO implements Serializable {

    @Id
    Short co_pagbot;

    int co_conori;
    int co_pagina;
    int co_condes;

    public PagconDTO() {
    }

    public Short getCo_pagbot() {
        return co_pagbot;
    }

    public void setCo_pagbot(Short co_pagbot) {
        this.co_pagbot = co_pagbot;
    }

    public int getCo_conori() {
        return co_conori;
    }

    public void setCo_conori(int co_conori) {
        this.co_conori = co_conori;
    }

    public int getCo_pagina() {
        return co_pagina;
    }

    public void setCo_pagina(int co_pagina) {
        this.co_pagina = co_pagina;
    }

    public int getCo_condes() {
        return co_condes;
    }

    public void setCo_condes(int co_condes) {
        this.co_condes = co_condes;
    }
}
