package com.acceso.wfweb.dtos;

import com.acceso.wfweb.utils.Values;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries({
        @NamedNativeQuery(
                name = Values.QUERYS_WEB_SELECT_PFCOMPAG,
                query = "select * from frawor2.pfcompag(:p_co_pagina,:p_id_frawor,:p_co_conten,:p_co_regist)",
                resultClass = ComboDTO.class),
        @NamedNativeQuery(
                name = Values.QUERYS_WEB_SELECT_PFCOMPAGPOS,
                query = "select * from frawor2.pfcompagpos(:p_co_pagina, :p_id_frawor, :p_co_conten, :p_co_regist, :p_co_pagregori, :p_va_pagregori)",
                resultClass = ComboDTO.class)
})
public class ComboDTO implements Serializable {

    @Id
    private String co_compag;

    private String no_compag;

    public ComboDTO() {
    }

    public ComboDTO(String co_compag, String no_compag) {
        this.co_compag = co_compag;
        this.no_compag = no_compag;
    }

    public String getCo_compag() {
        return co_compag;
    }

    public void setCo_compag(String co_compag) {
        this.co_compag = co_compag;
    }

    public String getNo_compag() {
        return no_compag;
    }

    public void setNo_compag(String no_compag) {
        this.no_compag = no_compag;
    }
}
