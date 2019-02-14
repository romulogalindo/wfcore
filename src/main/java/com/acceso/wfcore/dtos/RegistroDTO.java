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

/**
 * @author Mario Huillca <mario.huillca@acceso.com.pe>
 * Created on 30 nov. 2018, 15:11:45
 */
@Entity
@NamedNativeQueries({
      @NamedNativeQuery(
            name = Values.QUERYS_NATIVE_SELECT_REGISTRO,
            query = "select co_tipreg, no_tipreg, de_tipreg, or_tipreg "
                  + "from wfsistem.pbegistro_list()",
            resultClass = RegistroDTO.class)
})
public class RegistroDTO implements Serializable {

   @Id
   Integer co_tipreg;

   String no_tipreg;
   String de_tipreg;
   Integer or_tipreg;

   public Integer getCo_tipreg() {
      return co_tipreg;
   }

   public void setCo_tipreg(Integer co_tipreg) {
      this.co_tipreg = co_tipreg;
   }

   public String getNo_tipreg() {
      return no_tipreg;
   }

   public void setNo_tipreg(String no_tipreg) {
      this.no_tipreg = no_tipreg;
   }

   public String getDe_tipreg() {
      return de_tipreg;
   }

   public void setDe_tipreg(String de_tipreg) {
      this.de_tipreg = de_tipreg;
   }

   public Integer getOr_tipreg() {
      return or_tipreg;
   }

   public void setOr_tipreg(Integer or_tipreg) {
      this.or_tipreg = or_tipreg;
   }

   @Override
   public String toString() {
      return "RegistroDTO{" +
            "co_tipreg=" + co_tipreg +
            ", no_tipreg='" + no_tipreg + '\'' +
            ", de_tipreg='" + de_tipreg + '\'' +
            ", or_tipreg=" + or_tipreg +
            '}';
   }
}
