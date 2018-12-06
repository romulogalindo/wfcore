/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acceso.wfcore.dtos;

import com.acceso.wfcore.utils.Values;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

/**
 *
 * @author Mario Huillca <mario.huillca@acceso.com.pe>
 * Created on 30 nov. 2018, 15:11:45
 */
@Entity
@NamedNativeQueries({
    @NamedNativeQuery(
            name = "wfcore.cnx_delete",
            query = "select * from wfsistem.pbelimina_conexion(:co_conexi) as va_result",
            resultClass = DeleteDTO.class)
})
public class DeleteDTO implements Serializable {

    @Id
    String va_result;

    public String getVa_result() {
        return va_result;
    }

    public void setVa_result(String va_result) {
        this.va_result = va_result;
    }


}
