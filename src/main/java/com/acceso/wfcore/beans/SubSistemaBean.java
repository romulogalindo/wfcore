package com.acceso.wfcore.beans;

import com.acceso.wfcore.daos.SubSistemaDAO;
import com.acceso.wfcore.dtos.SubSistemaDTO;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;

/**
 * @author Mario Huillca <mario.huillca@acceso.com.pe>
 * Created on 30 nov. 2018, 15:54:46
 */
@ManagedBean
@SessionScoped
public class SubSistemaBean extends MainBean implements Serializable, DefaultMaintenceWeb, DefaultMaintenceDao {

   private static final String URL_LISTA = "/admin/jsf_exec/pagex/subsistema/paginaSubSistema.xhtml";
   private static final String URL_DETALLE = "/admin/jsf_exec/pagex/subsistema/paginaSubSistema.xhtml";
   private static final String URL_EDITAR = "/admin/jsf_exec/pagex/subsistema/paginaRegSubSistema.xhtml";
   private static final String URL_NEW = "/admin/jsf_exec/pagex/subsistema/paginaRegSubSistema.xhtml";


   private List<SubSistemaDTO> conexiones;
   private SubSistemaDTO conexion;

   private boolean isregEditable;


   public SubSistemaBean() {
      this.beanName = "SubSistema";
      this.conexion = new SubSistemaDTO();
      this.isregEditable = true;
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
      this.conexion = new SubSistemaDTO();
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
      SubSistemaDAO dao = new SubSistemaDAO();
      this.conexiones = dao.getSubSistema();
      dao.close();
   }

   @Override
   public void saveDto() {
      SubSistemaDAO dao = new SubSistemaDAO();
      this.conexion = dao.grabarSubSistema(conexion);
      this.conexiones = dao.getSubSistema();
//      System.out.println("SubSistemaBean actualizarSubSistema = " + this.conexion);
      dao.close();
   }

   @Override
   public void updateDto() {
      SubSistemaDAO dao = new SubSistemaDAO();
      this.conexion = dao.grabarSubSistema(conexion);
      this.conexiones = dao.getSubSistema();
//      System.out.println("SubSistemaBean actualizarSubSistema = " + this.conexion);
      dao.close();
   }

   @Override
   public void deleteDto() {
      SubSistemaDAO dao = new SubSistemaDAO();
      String resultado = dao.deleteSubSistema(conexion);
      this.conexiones = dao.getSubSistema();
      dao.close();
   }

   public SubSistemaDTO getSubSistema() {
      return conexion;
   }

   public void setSubSistema(SubSistemaDTO conexion) {
      this.conexion = conexion;
   }

   public List<SubSistemaDTO> getSubSistema() {
      return conexiones;
   }

   public void setSubSistema(List<SubSistemaDTO> conexiones) {
      this.conexiones = conexiones;
   }

   public boolean isIsregEditable() {
      return isregEditable;
   }

   public void setIsregEditable(boolean isregEditable) {
      this.isregEditable = isregEditable;
   }
}
