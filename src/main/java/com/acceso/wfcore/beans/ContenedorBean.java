package com.acceso.wfcore.beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mario Huillca <mario.huillca@acceso.com.pe>
 * Created on 30 nov. 2018, 15:54:46
 */
@ManagedBean
@SessionScoped
public class ContenedorBean extends MainBean implements Serializable, DefaultMaintenceWeb, DefaultMaintenceDao {

   private static final String URL_LISTA = "/admin/jsf_exec/pagex/contenedor/paginaContenedores.xhtml";
   private static final String URL_DETALLE = "/admin/jsf_exec/pagex/contenedor/paginaContenedores.xhtml";
   private static final String URL_EDITAR = "/admin/jsf_exec/pagex/contenedor/paginaRegContenedor.xhtml";
   private static final String URL_NEW = "/admin/jsf_exec/pagex/contenedor/paginaRegContenedor.xhtml";


//   private List<ContenedorDTO> contenedores;
//   private ContenedorDTO contenedor;

   private boolean isregEditable;

   private Map<Integer, Integer> hmap;

   @PostConstruct
   public void init() {
      hmap = new HashMap<>();
      hmap.put(1,1);
   }

   public ContenedorBean() {
      this.beanName = "Contenedores";
//      this.contenedor = new ContenedorDTO();
      this.isregEditable = true;

   }

   public void incrementFila() {
      if (hmap.size() < 11) {
         hmap.put(hmap.size() + 1,1);
      }
   }

   public void decreaseFila() {
      if (hmap.size() > 1) {
         hmap.remove(hmap.size());
      }
   }

   public void incrementColumna(Integer fila) {
      Integer columna = hmap.get(fila);
      if (columna < 13) {
         columna = columna + 1;
         hmap.replace(fila, columna);
      }
   }

   public void decreaseColumna(Integer fila) {
      Integer columna = hmap.get(fila);
      if (columna > 1) {
         columna = columna - 1;
         hmap.replace(fila, columna);
      }
   }

   public List<Integer> getFilas(){
      List<Integer> filas = new ArrayList<>();
      for (int i = 1; i <(hmap.size()+1); i++) {
         filas.add(i);
      }
      return filas;
   }

   public List<Integer> getColumnas(Integer fila){
      Integer vaColumna = hmap.get(fila) + 1;
      List<Integer> columnas = new ArrayList<>();
      for (int i = 1; i < vaColumna; i++) {
         columnas.add(i);
      }
      return columnas;
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
//      this.contenedor = new ContenedorDTO();
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
//      ContenedorDAO dao = new ContenedorDAO();
//      this.contenedores = dao.getContenedores();
//      dao.close();
   }

   @Override
   public void saveDto() {
//      ContenedorDAO dao = new ContenedorDAO();
//      this.contenedor = dao.grabarContenedor(contenedor);
//      this.contenedores = dao.getContenedores();
////      Sistema.out.println("ContenedorBean actualizarContenedor = " + this.contenedor);
//      dao.close();
   }

   @Override
   public void updateDto() {
//      ContenedorDAO dao = new ContenedorDAO();
//      this.contenedor = dao.grabarContenedor(contenedor);
//      this.contenedores = dao.getContenedores();
////      Sistema.out.println("ContenedorBean actualizarContenedor = " + this.contenedor);
//      dao.close();
   }

   @Override
   public void deleteDto() {
//      ContenedorDAO dao = new ContenedorDAO();
//      String resultado = dao.deleteContenedor(contenedor);
//      this.contenedores = dao.getContenedores();
//      dao.close();
   }

//   public ContenedorDTO getContenedor() {
//      return contenedor;
//   }
//
//   public void setContenedor(ContenedorDTO contenedor) {
//      this.contenedor = contenedor;
//   }
//
//   public List<ContenedorDTO> getContenedores() {
//      return contenedores;
//   }
//
//   public void setContenedores(List<ContenedorDTO> contenedores) {
//      this.contenedores = contenedores;
//   }

   public boolean isIsregEditable() {
      return isregEditable;
   }

   public void setIsregEditable(boolean isregEditable) {
      this.isregEditable = isregEditable;
   }

   public Map<Integer, Integer> getHmap() {
      return hmap;
   }

   public void setHmap(Map<Integer, Integer> hmap) {
      this.hmap = hmap;
   }
}
