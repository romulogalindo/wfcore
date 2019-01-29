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
            name = Values.QUERYS_NATIVE_SELECT_USUARIO,
            query = "select co_usuari, co_usulog, no_usuari, fe_regist, fe_bloque, fe_ultlog, ti_usuari, co_person, co_sistem, co_subsis, pw_usuari, no_sistem, no_subsis "
                  + "from wfsistem.pbusuario_list()",
            resultClass = ContenedorDTO.class)
})
public class ContenedorDTO implements Serializable {

   @Id
   Integer co_usuari;

   String co_usulog;
   String no_usuari;
   String pw_usuari;
   Date fe_regist;
   Date fe_bloque;
   Date fe_ultlog;
   String ti_usuari;
   Integer co_person;
   Integer co_sistem;
   Integer co_subsis;
   String no_sistem;
   String no_subsis;

   public Integer getCo_usuari() {
      return co_usuari;
   }

   public void setCo_usuari(Integer co_usuari) {
      this.co_usuari = co_usuari;
   }

   public String getCo_usulog() {
      return co_usulog;
   }

   public void setCo_usulog(String co_usulog) {
      this.co_usulog = co_usulog;
   }

   public String getNo_usuari() {
      return no_usuari;
   }

   public void setNo_usuari(String no_usuari) {
      this.no_usuari = no_usuari;
   }

   public String getPw_usuari() {
      return pw_usuari;
   }

   public void setPw_usuari(String pw_usuari) {
      this.pw_usuari = pw_usuari;
   }

   public Date getFe_regist() {
      return fe_regist;
   }

   public void setFe_regist(Date fe_regist) {
      this.fe_regist = fe_regist;
   }

   public Date getFe_bloque() {
      return fe_bloque;
   }

   public void setFe_bloque(Date fe_bloque) {
      this.fe_bloque = fe_bloque;
   }

   public Date getFe_ultlog() {
      return fe_ultlog;
   }

   public void setFe_ultlog(Date fe_ultlog) {
      this.fe_ultlog = fe_ultlog;
   }

   public String getTi_usuari() {
      return ti_usuari;
   }

   public void setTi_usuari(String ti_usuari) {
      this.ti_usuari = ti_usuari;
   }

   public Integer getCo_person() {
      return co_person;
   }

   public void setCo_person(Integer co_person) {
      this.co_person = co_person;
   }

   public Integer getCo_sistem() {
      return co_sistem;
   }

   public void setCo_sistem(Integer co_sistem) {
      this.co_sistem = co_sistem;
   }

   public Integer getCo_subsis() {
      return co_subsis;
   }

   public void setCo_subsis(Integer co_subsis) {
      this.co_subsis = co_subsis;
   }

   public String getNo_sistem() {
      return no_sistem;
   }

   public void setNo_sistem(String no_sistem) {
      this.no_sistem = no_sistem;
   }

   public String getNo_subsis() {
      return no_subsis;
   }

   public void setNo_subsis(String no_subsis) {
      this.no_subsis = no_subsis;
   }

   @Override
   public String toString() {
      return "UsuarioDTO{" +
            "co_usuari=" + co_usuari +
            ", co_usulog='" + co_usulog + '\'' +
            ", no_usuari='" + no_usuari + '\'' +
            ", pw_usuari='" + pw_usuari + '\'' +
            ", fe_regist=" + fe_regist +
            ", fe_bloque=" + fe_bloque +
            ", fe_ultlog=" + fe_ultlog +
            ", ti_usuari='" + ti_usuari + '\'' +
            ", co_person=" + co_person +
            ", co_sistem=" + co_sistem +
            ", co_subsis=" + co_subsis +
            ", no_sistem='" + no_sistem + '\'' +
            ", no_subsis='" + no_subsis + '\'' +
            '}';
   }
}
