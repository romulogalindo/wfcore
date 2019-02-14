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
                name = Values.QUERYS_SECURITY_REGSESINI_FOREING,
                query = "select inises from sesion.ppregsesini_wfcore(:p_id_sesion, :p_co_usuari, :p_ip_remoto) as inises",
                resultClass = RegsesinifDTO.class)
})
public class RegsesinifDTO implements Serializable {

    @Id
    boolean inises;

    public RegsesinifDTO() {
    }

    public boolean isInises() {
        return inises;
    }

    public void setInises(boolean inises) {
        this.inises = inises;
    }
}
