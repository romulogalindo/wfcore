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
                name = Values.QUERYS_WEB_SELECT_INSTBPAGREG_FRAWOR2,
                query = "select pfins_tbpagreg as pagreg from frawor2.pfins_tbpagreg(:p_id_frawor,:p_co_pagina,:p_co_pagreg,:p_nu_pagfil,:p_va_pagreg)",
                resultClass = WPagregDTO.class),
        @NamedNativeQuery(
                name = Values.QUERYS_WEB_SELECT_INSTBPAGREG_FRAWOR4,
                query = "select pfins_tbpagreg as pagreg from frawor4.pfins_tbpagreg(:p_id_frawor,:p_co_pagina,:p_co_pagreg,:p_nu_pagfil,:p_va_pagreg)",
                resultClass = WPagregDTO.class),
        @NamedNativeQuery(
                name = Values.QUERYS_WEB_SELECT_DELTBPAGREG_FRAWOR2,
                query = "select pfdel_tbpagreg as pagreg from frawor2.pfdel_tbpagreg(:p_id_frawor,:p_co_pagina)",
                resultClass = WPagregDTO.class),
        @NamedNativeQuery(
                name = Values.QUERYS_WEB_SELECT_DELTBPAGREG_FRAWOR4,
                query = "select pfdel_tbpagreg as pagreg from frawor4.pfdel_tbpagreg(:p_id_frawor,:p_co_pagina)",
                resultClass = WPagregDTO.class)
})
public class WPagregDTO implements Serializable {

    @Id
    String pagreg;

    public String getPagreg() {
        return pagreg;
    }

    public void setPagreg(String pagreg) {
        this.pagreg = pagreg;
    }
}

