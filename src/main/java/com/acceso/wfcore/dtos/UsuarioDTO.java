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
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author Mario Huillca <mario.huillca@acceso.com.pe>
 * Created on 30 nov. 2018, 15:11:45
 */

@Entity
@NamedNativeQueries({
      @NamedNativeQuery(
            name = Values.QUERYS_NATIVE_SELECT_USUARIO,
            query = "select co_usuari, co_usulog, no_usuari, pw_usuari, fe_regist, fe_bloque, ca_pwdinc, ca_pwdmax, fe_ultlog, ti_usuari, co_person, pw_usuant, co_sistem, co_subsis"
                  + "from wfsistem.pbusuario_list()",
            resultClass = UsuarioDTO.class),
      @NamedNativeQuery(
            name = Values.QUERYS_NATIVE_GRABAR_USUARIO,
            query = "co_usuari, co_usulog, no_usuari, pw_usuari, fe_regist, fe_bloque, ca_pwdinc, ca_pwdmax, fe_ultlog, ti_usuari, co_person, pw_usuant, co_sistem, co_subsis"
                  + "from wfsistem.pbusuario_save(:co_usuari, :co_usulog, :no_usuari, :pw_usuari, :ca_pwdinc, :ca_pwdmax, :ti_usuari, :co_person, :pw_usuant, :co_sistem, :co_subsis)",
            resultClass = UsuarioDTO.class)
})
public class UsuarioDTO implements Serializable {

   @Id
   Integer co_usuari;

   String co_usulog;
   String no_usuari;
   String pw_usuari;
   Date fe_regist;
   Date fe_bloque;
   Integer ca_pwdinc;
   Integer ca_pwdmax;
   Date fe_ultlog;
   String ti_usuari;
   Integer co_person;
   String pw_usuant;
   Integer co_sistem;
   Integer co_subsis;

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

   public Integer getCa_pwdinc() {
      return ca_pwdinc;
   }

   public void setCa_pwdinc(Integer ca_pwdinc) {
      this.ca_pwdinc = ca_pwdinc;
   }

   public Integer getCa_pwdmax() {
      return ca_pwdmax;
   }

   public void setCa_pwdmax(Integer ca_pwdmax) {
      this.ca_pwdmax = ca_pwdmax;
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

   public String getPw_usuant() {
      return pw_usuant;
   }

   public void setPw_usuant(String pw_usuant) {
      this.pw_usuant = pw_usuant;
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

   @Override
   public String toString() {
      return "UsuarioDTO{" +
            "co_usuari=" + co_usuari +
            ", co_usulog='" + co_usulog + '\'' +
            ", no_usuari='" + no_usuari + '\'' +
            ", pw_usuari='" + pw_usuari + '\'' +
            ", fe_regist=" + fe_regist +
            ", fe_bloque=" + fe_bloque +
            ", ca_pwdinc=" + ca_pwdinc +
            ", ca_pwdmax=" + ca_pwdmax +
            ", fe_ultlog=" + fe_ultlog +
            ", ti_usuari='" + ti_usuari + '\'' +
            ", co_person=" + co_person +
            ", pw_usuant='" + pw_usuant + '\'' +
            ", co_sistem=" + co_sistem +
            ", co_subsis=" + co_subsis +
            '}';
   }
}
