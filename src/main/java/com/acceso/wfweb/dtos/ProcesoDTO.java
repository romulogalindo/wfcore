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
                query = "select select pfins_tbconpar as proceso from frawor2.pfins_tbconpar(:p_id_frawor ,  :p_co_conten, :p_co_conpar, :p_va_conpar )",
                resultClass = ProcesoDTO.class)
})
public class ProcesoDTO implements Serializable {

    @Id
    int co_pagtit;

    String no_pagtit;

    int or_pagtit;

    int ca_pagreg;

    public ProcesoDTO() {
    }

    public int getCo_pagtit() {
        return co_pagtit;
    }

    public void setCo_pagtit(int co_pagtit) {
        this.co_pagtit = co_pagtit;
    }

    public String getNo_pagtit() {
        return no_pagtit;
    }

    public void setNo_pagtit(String no_pagtit) {
        this.no_pagtit = no_pagtit;
    }

    public int getOr_pagtit() {
        return or_pagtit;
    }

    public void setOr_pagtit(int or_pagtit) {
        this.or_pagtit = or_pagtit;
    }

    public int getCa_pagreg() {
        return ca_pagreg;
    }

    public void setCa_pagreg(int ca_pagreg) {
        this.ca_pagreg = ca_pagreg;
    }
}
