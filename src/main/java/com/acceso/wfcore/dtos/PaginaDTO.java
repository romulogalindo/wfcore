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
            name = Values.QUERYS_NATIVE_SELECT_PAGINA,
            query = "select co_pagina, no_pagtit, de_pagina, db_progra, fe_regist "
                  + "from wfsistem.pbpagina_list()",
            resultClass = PaginaDTO.class)
})
public class PaginaDTO implements Serializable {

   @Id
   Integer co_pagina;
   String no_pagtit;
   String de_pagina;
   String db_progra;
   Date fe_regist;

   public Integer getCo_pagina() {
      return co_pagina;
   }

   public void setCo_pagina(Integer co_pagina) {
      this.co_pagina = co_pagina;
   }

   public String getNo_pagtit() {
      return no_pagtit;
   }

   public void setNo_pagtit(String no_pagtit) {
      this.no_pagtit = no_pagtit;
   }

   public String getDe_pagina() {
      return de_pagina;
   }

   public void setDe_pagina(String de_pagina) {
      this.de_pagina = de_pagina;
   }

   public String getDb_progra() {
      return db_progra;
   }

   public void setDb_progra(String db_progra) {
      this.db_progra = db_progra;
   }

   public Date getFe_regist() {
      return fe_regist;
   }

   public void setFe_regist(Date fe_regist) {
      this.fe_regist = fe_regist;
   }

   @Override
   public String toString() {
      return "PaginaDTO{" +
            "co_pagina=" + co_pagina +
            ", no_pagtit='" + no_pagtit + '\'' +
            ", de_pagina='" + de_pagina + '\'' +
            ", db_progra='" + db_progra + '\'' +
            ", fe_regist=" + fe_regist +
            '}';
   }
}
