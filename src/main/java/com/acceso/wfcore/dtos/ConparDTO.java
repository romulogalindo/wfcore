/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acceso.wfcore.dtos;

import com.acceso.wfcore.utils.Values;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Mario Huillca <mario.huillca@acceso.com.pe>
 * Created on 30 nov. 2018, 15:11:45
 */

@Entity
@SqlResultSetMapping(name = "deleteconpardto", columns = {@ColumnResult(name = "count")})
@NamedNativeQueries({
        @NamedNativeQuery(
                name = Values.QUERYS_NATIVE_SELECT_CONPAR,
                query = "select * from frawor4.tcconpar where co_conten=:p_co_conten order by co_conpar;",
                resultClass = ConparDTO.class),
        @NamedNativeQuery(
                name = Values.QUERYS_NATIVE_SAVE_CONPAR,
                query = "select pbconpar_save as empty from wfsistem.pbconpar_save(:p_co_conten, :p_co_conpar, :p_no_conpar)",
                resultClass = EmptyDTO.class),
        @NamedNativeQuery(
                name = Values.QUERYS_NATIVE_DELETE_CONPAR,
                query = "delete from frawor4.tcconpar where co_conten = :p_co_conten and co_conpar = :p_co_conpar",
                resultSetMapping = "deleteconpardto")
})
public class ConparDTO implements Serializable {

    @Id
    Short co_conpar;

    Integer co_conten;
    String no_conpar;

    public Integer getCo_conten() {
        return co_conten;
    }

    public void setCo_conten(Integer co_conten) {
        this.co_conten = co_conten;
    }

    public Short getCo_conpar() {
        return co_conpar;
    }

    public void setCo_conpar(Short co_conpar) {
        this.co_conpar = co_conpar;
    }

    public String getNo_conpar() {
        return no_conpar;
    }

    public void setNo_conpar(String no_conpar) {
        this.no_conpar = no_conpar;
    }

    @Override
    public String toString() {
        return "ParametroDTO{" +
                "co_conten=" + co_conten +
                ", co_conpar=" + co_conpar +
                ", no_conpar='" + no_conpar + '\'' +
                '}';
    }
}
