/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acceso.wfcore.dtos;

import com.acceso.wfcore.utils.Values;

import java.io.Serializable;
import javax.persistence.*;

/**
 * @author Mario Huillca <mario.huillca@acceso.com.pe>
 * Created on 30 nov. 2018, 15:11:45
 */
@Entity

@NamedNativeQueries({
        @NamedNativeQuery(
                name = Values.QUERYS_NATIVE_DELETE_CNX,
                query = "select * from wfsistem.pbconexion_delete(:co_conexi) as va_result",
                resultClass = DeleteDTO.class),
        @NamedNativeQuery(
                name = Values.QUERYS_NATIVE_DELETE_SISTEMA,
                query = "select * from wfsistem.pbsistema_delete(:co_sistem) as va_result",
                resultClass = DeleteDTO.class),
        @NamedNativeQuery(
                name = Values.QUERYS_NATIVE_DELETE_SUBSISTEMA,
                query = "select * from wfsistem.pbsubsistema_delete(:co_subsis) as va_result",
                resultClass = DeleteDTO.class),
        @NamedNativeQuery(
                name = Values.QUERYS_NATIVE_DELETE_USUARIO,
                query = "select * from wfsistem.pbusuario_delete(:co_usuari) as va_result",
                resultClass = DeleteDTO.class),
        @NamedNativeQuery(
                name = Values.QUERYS_NATIVE_DELETE_PAQUETE,
                query = "select * from wfsistem.pbpaquete_delete(:co_paquet) as va_result",
                resultClass = DeleteDTO.class),
        @NamedNativeQuery(
                name = Values.QUERYS_NATIVE_DELETE_MODULO,
                query = "select * from wfsistem.pbmodulo_delete(:co_paquet) as va_result",
                resultClass = DeleteDTO.class),
        @NamedNativeQuery(
                name = Values.QUERYS_NATIVE_DELETE_MENU,
                query = "select * from wfsistem.pbmenu_delete(:co_mensis) as va_result",
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
