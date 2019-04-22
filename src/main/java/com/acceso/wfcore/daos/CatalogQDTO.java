package com.acceso.wfcore.daos;

import com.acceso.security.dtos.RegsesiniDTO;
import com.acceso.security.utils.Values;
//import com.acceso.wfcore.utils.MQ;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;

/**
 *
 * @author Rómulo Galindo
 */
@Entity
//@NamedNativeQueries({
//    @NamedNativeQuery(
//            name = MQ.name(Values.QUERYS_SECURITY_REGSESINI),
//            query = "select * from wfsistem.ppregsesini(:p_username, :p_password, :p_remoteip,:p_sistema) as inises",
//            resultClass = RegsesiniDTO.class),
//    @NamedNativeQuery(
//            name = Values.QUERYS_SECURITY_REGSESINI_WEB,
//            query = Values.QUERYS_SECURITY_REGSESINI,
////            query = "select * from wfsistem.ppregsesini_fwweb(:p_username, :p_password, :p_remoteip) as inises",
//            resultClass = RegsesiniDTO.class)
//})
public class CatalogQDTO implements Serializable{
    @Id
    int uuid;

    public int getUuid() {
        return uuid;
    }

    public void setUuid(int uuid) {
        this.uuid = uuid;
    }
    
}
