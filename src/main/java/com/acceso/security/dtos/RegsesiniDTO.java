package com.acceso.security.dtos;

import com.acceso.security.utils.Values;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import java.io.Serializable;

@Entity
@NamedNativeQueries({
        @NamedNativeQuery(
                name = Values.QUERYS_SECURITY_REGSESINI,
                query = "select * from wfsistem.ppregsesini(:p_username, :p_password, :p_remoteip,:p_sistema) as inises",
                resultClass = RegsesiniDTO.class),
        @NamedNativeQuery(
                name = Values.QUERYS_SECURITY_REGSESINI_WEB,
                query = "select * from wfsistem.ppregsesini_fwweb(:p_username, :p_password, :p_remoteip) as inises",
                resultClass = RegsesiniDTO.class)
})
public class RegsesiniDTO implements Serializable {
    @Id
    String inises;

    public RegsesiniDTO() {
    }

    public String getInises() {
        return inises;
    }

    public void setInises(String inises) {
        this.inises = inises;
    }
}
