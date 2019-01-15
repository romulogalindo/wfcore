package com.acceso.wfcore.beans;

import com.acceso.wfcore.daos.SubSistemaDAO;
import com.acceso.wfcore.daos.UsuarioDAO;
import com.acceso.wfcore.dtos.SubSistemaDTO;
import com.acceso.wfcore.dtos.UsuarioDTO;
import org.primefaces.model.TreeNode;

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
public class UsuarioBean extends MainBean implements Serializable, DefaultMaintenceWeb, DefaultMaintenceDao {

   private static final String URL_LISTA = "/admin/jsf_exec/pagex/usuario/paginaUsuarios.xhtml";
   private static final String URL_DETALLE = "/admin/jsf_exec/pagex/usuario/paginaUsuarios.xhtml";
   private static final String URL_EDITAR = "/admin/jsf_exec/pagex/usuario/paginaRegUsuario.xhtml";
   private static final String URL_NEW = "/admin/jsf_exec/pagex/usuario/paginaRegUsuario.xhtml";
   private static final String URL_PERMISO = "/admin/jsf_exec/pagex/usuario/paginaPermisos.xhtml";


   private List<UsuarioDTO> usuarios;
   private UsuarioDTO usuario;

   private boolean isregEditable;
   private List<SelectItem> lstSubSistemabySistema;
   private TreeNode[] selectedNodes;

   public UsuarioBean() {
      this.beanName = "Usuarios";
      this.usuario = new UsuarioDTO();
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

   public  String urlPermisos(){
      return  URL_PERMISO;
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
      this.usuario = new UsuarioDTO();
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
      UsuarioDAO dao = new UsuarioDAO();
      this.usuarios = dao.getUsuarios();
      dao.close();
   }

   @Override
   public void saveDto() {
      UsuarioDAO dao = new UsuarioDAO();
      this.usuario = dao.grabarUsuario(usuario);
      this.usuarios = dao.getUsuarios();
//      Sistema.out.println("UsuarioBean actualizarUsuario = " + this.usuario);
      dao.close();
   }

   @Override
   public void updateDto() {
      UsuarioDAO dao = new UsuarioDAO();
      this.usuario = dao.grabarUsuario(usuario);
      this.usuarios = dao.getUsuarios();
//      Sistema.out.println("UsuarioBean actualizarUsuario = " + this.usuario);
      dao.close();
   }

   @Override
   public void deleteDto() {
      UsuarioDAO dao = new UsuarioDAO();
      String resultado = dao.deleteUsuario(usuario);
      this.usuarios = dao.getUsuarios();
      dao.close();
   }

   public UsuarioDTO getUsuario() {
      return usuario;
   }

   public void setUsuario(UsuarioDTO usuario) {
      this.usuario = usuario;
   }

   public List<UsuarioDTO> getUsuarios() {
      return usuarios;
   }

   public void setUsuarios(List<UsuarioDTO> usuarios) {
      this.usuarios = usuarios;
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

   public TreeNode[] getSelectedNodes() {
      return selectedNodes;
   }

   public void setSelectedNodes(TreeNode[] selectedNodes) {
      this.selectedNodes = selectedNodes;
   }
}
