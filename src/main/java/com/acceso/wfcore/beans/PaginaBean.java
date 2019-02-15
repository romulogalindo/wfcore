package com.acceso.wfcore.beans;

import com.acceso.wfcore.daos.PaginaDAO;
import com.acceso.wfcore.daos.RegistroDAO;
import com.acceso.wfcore.dtos.PaginaDTO;
import com.acceso.wfcore.dtos.RegistroDTO;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mario Huillca <mario.huillca@acceso.com.pe>
 * Created on 30 nov. 2018, 15:54:46
 */
@ManagedBean
@SessionScoped
public class PaginaBean extends MainBean implements Serializable, DefaultMaintenceWeb, DefaultMaintenceDao {

   private static final String URL_LISTA = "/admin/jsf_exec/pagex/pagina/paginaPaginas.xhtml";
   private static final String URL_DETALLE = "/admin/jsf_exec/pagex/pagina/paginaPaginas.xhtml";
   private static final String URL_EDITAR = "/admin/jsf_exec/pagex/pagina/paginaRegPagina.xhtml";
   private static final String URL_NEW = "/admin/jsf_exec/pagex/pagina/paginaRegPagina.xhtml";


   private List<PaginaDTO> paginas;
   private PaginaDTO pagina;
   private List<PaginaDTO> filtroPagina;

   private boolean isregEditable;

   private DualListModel<RegistroDTO> registrosPick;
   private List<RegistroDTO> registroSourceTemp;


   public PaginaBean() {
      this.beanName = "Paginas";
//      this.pagina = new PaginaDTO();
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
//      this.pagina = new PaginaDTO();
      FacesContext context = FacesContext.getCurrentInstance();
      ((ManagerBean) context.getApplication().getVariableResolver().resolveVariable(context, "managerBean")).setRenderedCommandButton(false);
      ((ManagerBean) context.getApplication().getVariableResolver().resolveVariable(context, "managerBean")).updateBreadCumBar("Registro", URL_EDITAR);

      //Cargar Pick List
      loadPickList(0);

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
      PaginaDAO dao = new PaginaDAO();
      this.paginas = dao.getPaginas();
      dao.close();
   }

   @Override
   public void saveDto() {
//      PaginaDAO dao = new PaginaDAO();
//      this.pagina = dao.grabarPagina(pagina);
//      this.paginas = dao.getPaginas();
////      Sistema.out.println("PaginaBean actualizarPagina = " + this.pagina);
//      dao.close();
   }

   @Override
   public void updateDto() {
//      PaginaDAO dao = new PaginaDAO();
//      this.pagina = dao.grabarPagina(pagina);
//      this.paginas = dao.getPaginas();
////      Sistema.out.println("PaginaBean actualizarPagina = " + this.pagina);
//      dao.close();
   }

   @Override
   public void deleteDto() {
//      PaginaDAO dao = new PaginaDAO();
//      String resultado = dao.deletePagina(pagina);
//      this.paginas = dao.getPaginas();
//      dao.close();
   }

   public void onTransfer(TransferEvent event) {
//      StringBuilder builder = new StringBuilder();
//      for(Object item : event.getItems()) {
//         builder.append(((Theme) item).getName()).append("<br />");
//      }
//
//      FacesMessage msg = new FacesMessage();
//      msg.setSeverity(FacesMessage.SEVERITY_INFO);
//      msg.setSummary("Items Transferred");
//      msg.setDetail(builder.toString());
//
//      FacesContext.getCurrentInstance().addMessage(null, msg);

      registrosPick.setSource(registroSourceTemp);

      System.out.println("Source" + registrosPick.getSource().toString());
      System.out.println("Target" + registrosPick.getTarget().toString());

   }

   public void onSelect(SelectEvent event) {
//      FacesContext context = FacesContext.getCurrentInstance();
//      context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Selected", event.getObject().toString()));
   }

   public void onUnselect(UnselectEvent event) {
//      FacesContext context = FacesContext.getCurrentInstance();
//      context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Unselected", event.getObject().toString()));
   }

   public void onReorder() {
//      FacesContext context = FacesContext.getCurrentInstance();
//      context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "List Reordered", null));
   }

   public void loadPickList (Integer co_pagina){
      RegistroDAO dao = new RegistroDAO();

      List<RegistroDTO> registroSource = dao.getRegistros();
      List<RegistroDTO> registroTarget = new ArrayList<RegistroDTO>();

      registroSourceTemp = registroSource;

      registrosPick = new DualListModel<RegistroDTO>(registroSource, registroTarget);
   }

   public PaginaDTO getPagina() {
      return pagina;
   }

   public void setPagina(PaginaDTO pagina) {
      this.pagina = pagina;
   }

   public List<PaginaDTO> getPaginas() {
      return paginas;
   }

   public void setPaginas(List<PaginaDTO> paginas) {
      this.paginas = paginas;
   }

   public boolean isIsregEditable() {
      return isregEditable;
   }

   public void setIsregEditable(boolean isregEditable) {
      this.isregEditable = isregEditable;
   }

   public List<PaginaDTO> getFiltroPagina() {
      return filtroPagina;
   }

   public void setFiltroPagina(List<PaginaDTO> filtroPagina) {
      this.filtroPagina = filtroPagina;
   }

   public DualListModel<RegistroDTO> getRegistrosPick() {
      return registrosPick;
   }

   public void setRegistrosPick(DualListModel<RegistroDTO> registrosPick) {
      this.registrosPick = registrosPick;
   }

   public List<RegistroDTO> getRegistroSourceTemp() {
      return registroSourceTemp;
   }

   public void setRegistroSourceTemp(List<RegistroDTO> registroSourceTemp) {
      this.registroSourceTemp = registroSourceTemp;
   }
}
