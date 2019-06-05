package com.acceso.wfcore.dtos;

import com.acceso.wfcore.utils.Values;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Mario Huillca <mario.huillca@acceso.com.pe>
 * Created on 30 nov. 2018, 15:11:45
 */
@Entity
@SqlResultSetMapping(name = "deletepropertiesdto", columns = {
        @ColumnResult(name = "count")})
@NamedNativeQueries({
        @NamedNativeQuery(
                name = Values.QUERYS_NATIVE_SELECT_PROPERTIES,
                query = "select * from wfsistem.tsparame;",
                resultClass = PropertyDTO.class)
})
public class PropertyDTO implements Serializable {

    @Id
    int co_parame;
    String no_parame;
    String va_parame;

    public PropertyDTO() {
    }

    public int getCo_parame() {
        return co_parame;
    }

    public void setCo_parame(int co_parame) {
        this.co_parame = co_parame;
    }

    public String getNo_parame() {
        return no_parame;
    }

    public void setNo_parame(String no_parame) {
        this.no_parame = no_parame;
    }

    public String getVa_parame() {
        return va_parame;
    }

    public void setVa_parame(String va_parame) {
        this.va_parame = va_parame;
    }
}
