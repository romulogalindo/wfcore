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
                name = Values.QUERYS_SECURITY_PERMISBLO_WEB,
                query = "select * from wfsistem.pblist_permis_blo_web(:p_co_usuari) as inises",
                resultClass = PermisbloDTO.class)
})
public class PermisbloDTO implements Serializable {

    @Id
    int id_secuen;
    String co_elemen;
    int id_elemen;
    boolean il_activo;

    public PermisbloDTO() {
    }

    public int getId_secuen() {
        return id_secuen;
    }

    public void setId_secuen(int id_secuen) {
        this.id_secuen = id_secuen;
    }

    public String getCo_elemen() {
        return co_elemen;
    }

    public void setCo_elemen(String co_elemen) {
        this.co_elemen = co_elemen;
    }

    public int getId_elemen() {
        return id_elemen;
    }

    public void setId_elemen(int id_elemen) {
        this.id_elemen = id_elemen;
    }

    public boolean isIl_activo() {
        return il_activo;
    }

    public void setIl_activo(boolean il_activo) {
        this.il_activo = il_activo;
    }
}
