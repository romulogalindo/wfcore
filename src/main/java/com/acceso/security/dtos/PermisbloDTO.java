package com.acceso.security.dtos;

import com.acceso.security.utils.Values;

import javax.persistence.Entity;
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
public class PermisbloDTO implements Serializable {

    int id_secuen;
    int co_elemen;
    int id_elemen;
    int il_bloque;

    public PermisbloDTO() {
    }

    public int getId_secuen() {
        return id_secuen;
    }

    public void setId_secuen(int id_secuen) {
        this.id_secuen = id_secuen;
    }

    public int getCo_elemen() {
        return co_elemen;
    }

    public void setCo_elemen(int co_elemen) {
        this.co_elemen = co_elemen;
    }

    public int getId_elemen() {
        return id_elemen;
    }

    public void setId_elemen(int id_elemen) {
        this.id_elemen = id_elemen;
    }

    public int getIl_bloque() {
        return il_bloque;
    }

    public void setIl_bloque(int il_bloque) {
        this.il_bloque = il_bloque;
    }
}
