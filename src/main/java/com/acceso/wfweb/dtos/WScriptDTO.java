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
                name = Values.QUERYS_WEB_SELECT_TCPAGINA,
                query = "select co_pagina, js_valpag from frawor4.tcpagina where co_pagina = :p_co_pagina",
                resultClass = WScriptDTO.class)
})
public class WScriptDTO implements Serializable {

    @Id
    int co_pagina;

    String js_valpag;

    public WScriptDTO() {
    }

    public int getCo_pagina() {
        return co_pagina;
    }

    public void setCo_pagina(int co_pagina) {
        this.co_pagina = co_pagina;
    }

    public String getJs_valpag() {
        return js_valpag;
    }

    public void setJs_valpag(String js_valpag) {
        this.js_valpag = js_valpag;
    }
}
