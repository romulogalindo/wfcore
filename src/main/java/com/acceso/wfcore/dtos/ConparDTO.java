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
            name = Values.QUERYS_NATIVE_SELECT_PARAMETRO,
            query = "select co_conten, co_conpar, no_conpar "
                  + "from wfsistem.pbparametro_list()",
            resultClass = ConparDTO.class),
      @NamedNativeQuery(
            name = Values.QUERYS_NATIVE_SELECT_PARAMETRO_CONTEN,
            query = "select co_conten, co_conpar, no_conpar "
                  + "from wfsistem.pbparametro_list(:co_conten)",
            resultClass = ConparDTO.class)
})
public class ConparDTO implements Serializable {

   @Id
   Integer co_conten;

   Integer co_conpar;
   String no_conpar;

   public Integer getCo_conten() {
      return co_conten;
   }

   public void setCo_conten(Integer co_conten) {
      this.co_conten = co_conten;
   }

   public Integer getCo_conpar() {
      return co_conpar;
   }

   public void setCo_conpar(Integer co_conpar) {
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
