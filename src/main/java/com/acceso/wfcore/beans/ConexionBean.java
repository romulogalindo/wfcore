package com.acceso.wfcore.beans;

import com.acceso.wfcore.daos.ConexionDAO;
import com.acceso.wfcore.dtos.ConexionDTO;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 * @author Mario Huillca <mario.huillca@acceso.com.pe>
 * Created on 30 nov. 2018, 15:54:46
 */
@ManagedBean
@SessionScoped
public class ConexionBean extends MainBean implements Serializable, DefaultMaintenceWeb, DefaultMaintenceDao {

   private static final String URL_LISTA = "/admin/jsf_exec/pagex/conexion/paginaConexiones.xhtml";
   private static final String URL_DETALLE = "/admin/jsf_exec/pagex/conexion/paginaConexiones.xhtml";
   private static final String URL_EDITAR = "/admin/jsf_exec/pagex/conexion/paginaRegConexion.xhtml";
   private static final String URL_NEW = "/admin/jsf_exec/pagex/conexion/paginaRegConexion.xhtml";


   private List<ConexionDTO> conexiones;
   private ConexionDTO conexion;

   private boolean isregEditable;


   public ConexionBean() {
      this.beanName = "Conexiones";
      this.conexion = new ConexionDTO();
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
      this.conexion = new ConexionDTO();
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
      ConexionDAO dao = new ConexionDAO();
      this.conexiones = dao.getConexiones();
      dao.close();
   }

   @Override
   public void saveDto() {
      ConexionDAO dao = new ConexionDAO();
      this.conexion = dao.grabarConexion(conexion);
      this.conexiones = dao.getConexiones();
//      System.out.println("ConexionBean actualizarConexion = " + this.conexion);
      dao.close();
   }

   @Override
   public void updateDto() {
      ConexionDAO dao = new ConexionDAO();
      this.conexion = dao.grabarConexion(conexion);
      this.conexiones = dao.getConexiones();
//      System.out.println("ConexionBean actualizarConexion = " + this.conexion);
      dao.close();
   }

   @Override
   public void deleteDto() {
      ConexionDAO dao = new ConexionDAO();
      String resultado = dao.deleteConexion(conexion);
      this.conexiones = dao.getConexiones();
      dao.close();
   }

   public ConexionDTO getConexion() {
      return conexion;
   }

   public void setConexion(ConexionDTO conexion) {
      this.conexion = conexion;
   }

   public List<ConexionDTO> getConexiones() {
      return conexiones;
   }

   public void setConexiones(List<ConexionDTO> conexiones) {
      this.conexiones = conexiones;
   }

   public boolean isIsregEditable() {
      return isregEditable;
   }

   public void setIsregEditable(boolean isregEditable) {
      this.isregEditable = isregEditable;
   }
}
