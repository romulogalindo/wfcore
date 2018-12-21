package com.acceso.wfcore.beans;

import com.acceso.wfcore.daos.ModuloDAO;
import com.acceso.wfcore.daos.PaqueteDAO;
import com.acceso.wfcore.daos.SubSistemaDAO;
import com.acceso.wfcore.dtos.ModuloDTO;
import com.acceso.wfcore.dtos.PaqueteDTO;
import com.acceso.wfcore.dtos.SubSistemaDTO;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mario Huillca <mario.huillca@acceso.com.pe>
 * Created on 30 nov. 2018, 15:54:46
 */
@ManagedBean
@SessionScoped
public class MenuBean extends MainBean implements Serializable, DefaultMaintenceWeb, DefaultMaintenceDao {

   private static final String URL_LISTA = "/admin/jsf_exec/pagex/modulo/paginaModulos.xhtml";
   private static final String URL_DETALLE = "/admin/jsf_exec/pagex/modulo/paginaModulos.xhtml";
   private static final String URL_EDITAR = "/admin/jsf_exec/pagex/modulo/paginaRegModulo.xhtml";
   private static final String URL_NEW = "/admin/jsf_exec/pagex/modulo/paginaRegModulo.xhtml";


   private List<ModuloDTO> modulos;
   private ModuloDTO modulo;

   private boolean isregEditable;

   private List<SelectItem> lstSubSistemabySistema;
   private List<SelectItem> lstPaquetebySubSistema;

   public MenuBean() {
      this.beanName = "Modulos";
      this.modulo = new ModuloDTO();
      this.isregEditable = true;
   }

   public void onSistemaChange(Integer co_sistem) {
      this.lstSubSistemabySistema = new ArrayList<>();
      List<SubSistemaDTO> subsistemas;
      SubSistemaDAO dao = new SubSistemaDAO();
      subsistemas = dao.getSubSistemas();
      if (co_sistem == 0) {
         for (SubSistemaDTO sis : subsistemas) {
               SelectItem item = new SelectItem();
               item.setLabel(sis.getNo_subsis());
               item.setDescription(sis.getNo_subsis());
               item.setValue(sis.getCo_subsis());
               this.lstSubSistemabySistema.add(item);
         }
      } else {
         for (SubSistemaDTO sis : subsistemas) {
            if (sis.getCo_sistem() == co_sistem) {
               SelectItem item = new SelectItem();
               item.setLabel(sis.getNo_subsis());
               item.setDescription(sis.getNo_subsis());
               item.setValue(sis.getCo_subsis());
               this.lstSubSistemabySistema.add(item);
            }
         }
      }
   }

   public void onSubSistemaChange(Integer co_subsis) {
      this.lstPaquetebySubSistema = new ArrayList<>();
      List<PaqueteDTO> paquetes;
      PaqueteDAO dao = new PaqueteDAO();
      paquetes = dao.getPaquetes();
      if (co_subsis == 0) {
         for (PaqueteDTO paq : paquetes) {
            SelectItem item = new SelectItem();
            item.setLabel(paq.getNo_paquet());
            item.setDescription(paq.getNo_paquet());
            item.setValue(paq.getCo_paquet());
            this.lstPaquetebySubSistema.add(item);
         }
      } else {
         for (PaqueteDTO paq : paquetes) {
            if (paq.getCo_subsis() == co_subsis) {
               SelectItem item = new SelectItem();
               item.setLabel(paq.getNo_paquet());
               item.setDescription(paq.getNo_paquet());
               item.setValue(paq.getCo_paquet());
               this.lstPaquetebySubSistema.add(item);
            }
         }
      }
   }

   @Override
   public String getBeanName() {
      return beanName;
   }

   @Override
   public String load() {
      System.out.println("load()");
      this.isregEditable = true;
      // LLENAR LOS BOTONES SECUNDARIOS
      //doListener();
      //CARGA INICIAL!!
      selectDto();

      return URL_LISTA;
   }

   @Override
   public void doListener() {
      //acceder al manager y decirle toma
      FacesContext context = FacesContext.getCurrentInstance();
      ((ManagerBean) context.getApplication().getVariableResolver().resolveVariable(context, "managerBean")).setRenderedMenuButton(false);
      ((ManagerBean) context.getApplication().getVariableResolver().resolveVariable(context, "managerBean")).initBreadCumBar();
      ((ManagerBean) context.getApplication().getVariableResolver().resolveVariable(context, "managerBean")).updateBreadCumBar(beanName, URL_LISTA);
      ((ManagerBean) context.getApplication().getVariableResolver().resolveVariable(context, "managerBean")).setRenderedCommandButton(true);
      ((ManagerBean) context.getApplication().getVariableResolver().resolveVariable(context, "managerBean")).setCurrentBean(this);
      ((ManagerBean) context.getApplication().getVariableResolver().resolveVariable(context, "managerBean")).setDefaultActionNameButton("NUEVO");

      System.out.println("listener()");
   }

   @Override
   public String defaultAction() {
      // Para el nuevo registro
      this.modulo = new ModuloDTO();
      FacesContext context = FacesContext.getCurrentInstance();
      ((ManagerBean) context.getApplication().getVariableResolver().resolveVariable(context, "managerBean")).setRenderedCommandButton(false);
      ((ManagerBean) context.getApplication().getVariableResolver().resolveVariable(context, "managerBean")).updateBreadCumBar("Registro", URL_EDITAR);
      //varias cosas para editar
      return URL_NEW;
   }

   @Override
   public String newRegist() {
      //Data la causistica el metodo nuevo esta encapsuado en defaultAction
      return null;
   }

   @Override
   public String updateRegist() {
      FacesContext context = FacesContext.getCurrentInstance();
      ((ManagerBean) context.getApplication().getVariableResolver().resolveVariable(context, "managerBean")).updateBreadCumBar("Editar", URL_EDITAR);
      ((ManagerBean) context.getApplication().getVariableResolver().resolveVariable(context, "managerBean")).setRenderedCommandButton(false);

      // CARGAMOS COMBOS DE SUBSISTEMA
      onSistemaChange(0);
      return URL_EDITAR;
   }

   @Override
   public String deleteRegist() {
      deleteDto();

      return URL_LISTA;
   }

   @Override
   public String saveRegist() {
      saveDto();
      return URL_LISTA;
   }

   @Override
   public void selectDto() {
      ModuloDAO dao = new ModuloDAO();
      this.modulos = dao.getModulos();
      dao.close();
   }

   @Override
   public void saveDto() {
      ModuloDAO dao = new ModuloDAO();
      this.modulo = dao.grabarModulo(modulo);
      this.modulos = dao.getModulos();
//      System.out.println("ModuloBean actualizarModulo = " + this.modulo);
      dao.close();
   }

   @Override
   public void updateDto() {
      ModuloDAO dao = new ModuloDAO();
      this.modulo = dao.grabarModulo(modulo);
      this.modulos = dao.getModulos();
//      System.out.println("ModuloBean actualizarModulo = " + this.modulo);
      dao.close();
   }

   @Override
   public void deleteDto() {
      ModuloDAO dao = new ModuloDAO();
      String resultado = dao.deleteModulo(modulo);
      this.modulos = dao.getModulos();
      dao.close();
   }

   public List<ModuloDTO> getModulos() {
      return modulos;
   }

   public void setModulos(List<ModuloDTO> modulos) {
      this.modulos = modulos;
   }

   public ModuloDTO getModulo() {
      return modulo;
   }

   public void setModulo(ModuloDTO modulo) {
      this.modulo = modulo;
   }

   public boolean isIsregEditable() {
      return isregEditable;
   }

   public void setIsregEditable(boolean isregEditable) {
      this.isregEditable = isregEditable;
   }

   public List<SelectItem> getLstSubSistemabySistema() {
      return lstSubSistemabySistema;
   }

   public void setLstSubSistemabySistema(List<SelectItem> lstSubSistemabySistema) {
      this.lstSubSistemabySistema = lstSubSistemabySistema;
   }

   public List<SelectItem> getLstPaquetebySubSistema() {
      return lstPaquetebySubSistema;
   }

   public void setLstPaquetebySubSistema(List<SelectItem> lstPaquetebySubSistema) {
      this.lstPaquetebySubSistema = lstPaquetebySubSistema;
   }
}
