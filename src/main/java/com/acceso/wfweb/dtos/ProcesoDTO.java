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
                name = Values.QUERYS_WEB_SELECT_PFCONPAR,
                query = "select pfins_tbconpar as proceso from frawor4.pfins_tbconpar(:p_id_frawor , :p_co_conten, :p_co_conpar, :p_va_conpar )",
                resultClass = ProcesoDTO.class),
        @NamedNativeQuery(
                name = Values.QUERYS_WEB_SELECT_PFCONPAR2,
                query = "select pfins_tbconpar as proceso from frawor2.pfins_tbconpar(:p_id_frawor , :p_co_conten, :p_co_conpar, :p_va_conpar )",
                resultClass = ProcesoDTO.class)
})
public class ProcesoDTO implements Serializable {

    @Id
    String proceso;

    public ProcesoDTO() {
    }

    public String getProceso() {
        return proceso;
    }

    public void setProceso(String proceso) {
        this.proceso = proceso;
    }

    @Override
    public String toString() {
        return "ProcesoDTO{" +
                "proceso='" + proceso + '\'' +
                '}';
    }
}
