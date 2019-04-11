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
 * @author Mario Huillca <mario.huillca@acceso.com.pe>
 * Created on 30 nov. 2018, 15:11:45
 */
@Entity
@SqlResultSetMapping(name = "deletetitulodto", columns = {@ColumnResult(name = "count")})
@NamedNativeQueries({
        @NamedNativeQuery(
                name = Values.QUERYS_NATIVE_SELECT_PAGTIT,
                query = "select * from frawor4.tcpagtit where co_pagina = :p_co_pagina order by or_pagtit",
                resultClass = PagtitDTO.class),
        @NamedNativeQuery(
                name = Values.QUERYS_NATIVE_SAVE_PAGTIT,
                query = "select * from wfsistem.pbpagtit_save(:p_co_pagina, :p_co_pagtit, :p_no_pagtit, :p_or_pagtit )",
                resultClass = PagtitDTO.class),
        @NamedNativeQuery(
                name = Values.QUERYS_NATIVE_DELETE_PAGTIT,
                query = "delete from frawor4.tcpagtit where co_pagina = :p_co_pagina and co_pagtit = :p_co_pagtit",
                resultSetMapping = "deletetitulodto")
})
public class PagtitDTO implements Serializable {

    @Id
    int co_pagtit;

    int co_pagina;
    String no_pagtit;
    short or_pagtit;

    public PagtitDTO() {
    }

    public int getCo_pagtit() {
        return co_pagtit;
    }

    public void setCo_pagtit(int co_pagtit) {
        this.co_pagtit = co_pagtit;
    }

    public int getCo_pagina() {
        return co_pagina;
    }

    public void setCo_pagina(int co_pagina) {
        this.co_pagina = co_pagina;
    }

    public String getNo_pagtit() {
        return no_pagtit;
    }

    public void setNo_pagtit(String no_pagtit) {
        this.no_pagtit = no_pagtit;
    }

    public short getOr_pagtit() {
        return or_pagtit;
    }

    public void setOr_pagtit(short or_pagtit) {
        this.or_pagtit = or_pagtit;
    }
}
