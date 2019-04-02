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
                name = Values.QUERYS_WEB_SELECT_SCRIPT_VALPAG,
                query = "select co_pagina, js_valpag as script from frawor4.tcpagina where co_pagina = :p_co_pagina",
                resultClass = WScriptDTO.class),
        @NamedNativeQuery(
                name = Values.QUERYS_WEB_SELECT_SCRIPT_PROPAG,
                query = "select co_pagina, js_propag as script from frawor4.tcpagina where co_pagina = :p_co_pagina",
                resultClass = WScriptDTO.class)
})
public class WScriptDTO implements Serializable {

    @Id
    int co_pagina;

    String script;

    public WScriptDTO() {
    }

    public int getCo_pagina() {
        return co_pagina;
    }

    public void setCo_pagina(int co_pagina) {
        this.co_pagina = co_pagina;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }
}
