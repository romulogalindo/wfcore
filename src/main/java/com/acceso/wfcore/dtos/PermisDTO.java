/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acceso.wfcore.dtos;

import com.acceso.wfcore.utils.Values;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Mario Huillca <mario.huillca@acceso.com.pe>
 * Created on 30 nov. 2018, 15:11:45
 */

@Entity
@NamedNativeQueries({
      @NamedNativeQuery(
            name = Values.QUERYS_NATIVE_SELECT_PERMIS,
            query = "select co_usuari, co_modulo "
                  + "from wfsistem.pbpermis_list(:co_usuari)",
            resultClass = PermisDTO.class),
      @NamedNativeQuery(
            name = Values.QUERYS_NATIVE_GRABAR_PERMIS,
            query = "select co_usuari, co_modulo "
                  + "from wfsistem.pbpermis_save(:co_usuari, :co_modulo)",
            resultClass = PermisDTO.class)
})
public class PermisDTO implements Serializable {

   @Id
   Integer co_usuari;

   Integer co_modulo;

   public Integer getCo_usuari() {
      return co_usuari;
   }

   public void setCo_usuari(Integer co_usuari) {
      this.co_usuari = co_usuari;
   }

   public Integer getCo_modulo() {
      return co_modulo;
   }

   public void setCo_modulo(Integer co_modulo) {
      this.co_modulo = co_modulo;
   }

   @Override
   public String toString() {
      return "PermisDTO{" +
            "co_usuari=" + co_usuari +
            ", co_modulo=" + co_modulo +
            '}';
   }
}
