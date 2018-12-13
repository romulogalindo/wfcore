package com.acceso.wfweb.dtos;

import com.acceso.wfweb.dtos.LoginDTO;
import com.acceso.wfweb.utils.Values;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

/**
 *
 * @author Rómulo Galindo<romulogalindo@gmail.com>
 * Created on 13 dic. 2018, 19:06:46
 */
@Entity
@NamedNativeQueries({
        @NamedNativeQuery(
                name = Values.QUERYS_WEB_SELECT_INISES,
                query = "select co_conexi, no_conexi, nu_maxpoo, nu_timout, no_usuari, pw_usuari, ur_domini, nu_puerto, no_datbas "
                        + "from wfsistem.tsconexi order by co_conexi, no_conexi",
                resultClass = LoginDTO.class)
})
public class LoginDTO implements Serializable{


}
