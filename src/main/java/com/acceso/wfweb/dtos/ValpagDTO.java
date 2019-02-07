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
            name = Values.QUERYS_WEB_SELECT_PFVALPAG,
            query = "select * from frawor4.pfvalpag( :p_co_pagina , :p_id_frawor , :p_co_conten )",
            resultClass = ValpagDTO.class)
})
public class ValpagDTO implements Serializable {

    @Id
    int id_valpag;
    /*innecesario*/
    int co_pagina;

    int co_pagreg;

    String va_pagreg;

    String no_pagreg;

    Integer ti_pagreg;

    String ti_estreg;

    String tx_pagreg;

    String ur_pagreg;

    public ValpagDTO() {
    }

    public int getId_valpag() {
        return id_valpag;
    }

    public void setId_valpag(int id_valpag) {
        this.id_valpag = id_valpag;
    }

    public int getCo_pagina() {
        return co_pagina;
    }

    public void setCo_pagina(int co_pagina) {
        this.co_pagina = co_pagina;
    }

    public int getCo_pagreg() {
        return co_pagreg;
    }

    public void setCo_pagreg(int co_pagreg) {
        this.co_pagreg = co_pagreg;
    }

    public String getVa_pagreg() {
        return va_pagreg;
    }

    public void setVa_pagreg(String va_pagreg) {
        this.va_pagreg = va_pagreg;
    }

    public String getNo_pagreg() {
        return no_pagreg;
    }

    public void setNo_pagreg(String no_pagreg) {
        this.no_pagreg = no_pagreg;
    }

    public Integer getTi_pagreg() {
        return ti_pagreg;
    }

    public void setTi_pagreg(Integer ti_pagreg) {
        this.ti_pagreg = ti_pagreg;
    }

    public String getTi_estreg() {
        return ti_estreg;
    }

    public void setTi_estreg(String ti_estreg) {
        this.ti_estreg = ti_estreg;
    }

    public String getTx_pagreg() {
        return tx_pagreg;
    }

    public void setTx_pagreg(String tx_pagreg) {
        this.tx_pagreg = tx_pagreg;
    }

    public String getUr_pagreg() {
        return ur_pagreg;
    }

    public void setUr_pagreg(String ur_pagreg) {
        this.ur_pagreg = ur_pagreg;
    }

}
