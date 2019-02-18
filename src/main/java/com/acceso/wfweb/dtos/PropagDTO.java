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
                name = Values.QUERYS_WEB_SELECT_PFPROPAG,
                query = "select * from frawor2.pfpropag(:p_co_pagina , :p_id_frawor , :p_co_conten, :p_co_pagbot)",
                resultClass = PropagDTO.class)
})
public class PropagDTO implements Serializable {

    @Id
    String pfpropag;

    public String getPfpropag() {
        return pfpropag;
    }

    public void setPfpropag(String pfpropag) {
        this.pfpropag = pfpropag;
    }
}