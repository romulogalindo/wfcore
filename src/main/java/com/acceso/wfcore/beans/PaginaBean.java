package com.acceso.wfcore.beans;

import com.acceso.wfcore.daos.PaginaDAO;
import com.acceso.wfcore.dtos.PaginaDTO;

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
public class PaginaBean extends MainBean implements Serializable, DefaultMaintenceWeb, DefaultMaintenceDao {

   private static final String URL_LISTA = "/admin/jsf_exec/pagex/pagina/paginaPaginas.xhtml";
   private static final String URL_DETALLE = "/admin/jsf_exec/pagex/pagina/paginaPaginas.xhtml";
   private static final String URL_EDITAR = "/admin/jsf_exec/pagex/pagina/paginaRegPagina.xhtml";
   private static final String URL_NEW = "/admin/jsf_exec/pagex/pagina/paginaRegPagina.xhtml";


   private List<PaginaDTO> paginas;
   private PaginaDTO pagina;
   private List<PaginaDTO> filtroPagina;

   private boolean isregEditable;


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
}
