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
            name = Values.QUERYS_WEB_SELECT_JOIN_TRANSA_FRAWOR,
            query = "select * from frawor4.pfins_tbfrawor(:p_id_frawor, :p_id_sesion, :p_id_fraant, :p_co_conten)",
            resultClass = JoinDTO.class),
        @NamedNativeQuery(
                name = Values.QUERYS_WEB_SELECT_JOIN_TRANSA_FRAWOR2,
                query = "select * from frawor2.pfins_tbfrawor(:p_id_frawor, :p_id_sesion, :p_id_fraant, :p_co_conten)",
                resultClass = JoinDTO.class)
})
public class JoinDTO implements Serializable {

    @Id
    String pfins_tbfrawor;

    public JoinDTO() {
    }

    public String getPfins_tbfrawor() {
        return pfins_tbfrawor;
    }

    public void setPfins_tbfrawor(String pfins_tbfrawor) {
        this.pfins_tbfrawor = pfins_tbfrawor;
    }
}
