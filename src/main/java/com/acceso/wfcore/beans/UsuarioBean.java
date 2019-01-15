package com.acceso.wfcore.beans;

import com.acceso.wfcore.daos.MenuDAO;
import com.acceso.wfcore.daos.PermisDAO;
import com.acceso.wfcore.daos.SubSistemaDAO;
import com.acceso.wfcore.daos.UsuarioDAO;
import com.acceso.wfcore.dtos.MenuDTO;
import com.acceso.wfcore.dtos.PermisDTO;
import com.acceso.wfcore.dtos.SubSistemaDTO;
import com.acceso.wfcore.dtos.UsuarioDTO;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import javax.faces.application.FacesMessage;
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
   private TreeNode root;

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

   // REDIRECCION A LISTA DE PERMISOS POR USUSARIO
   public String urlPermisos() {
      FacesContext context = FacesContext.getCurrentInstance();
      ((ManagerBean) context.getApplication().getVariableResolver().resolveVariable(context, "managerBean")).updateBreadCumBar("Permisos", URL_PERMISO);
      ((ManagerBean) context.getApplication().getVariableResolver().resolveVariable(context, "managerBean")).updateBreadCumBar(usuario.getNo_usuari(), URL_PERMISO);
      ((ManagerBean) context.getApplication().getVariableResolver().resolveVariable(context, "managerBean")).setRenderedCommandButton(false);
      ((ManagerBean) context.getApplication().getVariableResolver().resolveVariable(context, "managerBean")).setRenderedMenuButton(false);

      createTreeNode();

      return URL_PERMISO;
   }

   public String saveSelectedMultiple(TreeNode[] nodes) {
      if (nodes != null && nodes.length > 0) {
         List<Integer> modulos = new ArrayList<>();
         for (TreeNode node : nodes) {
            // NODO SELECCIONADO
            if (node.isLeaf()) {
               MenuDTO menu = (MenuDTO) node.getData();
               if (menu.getCo_modulo() != null) {
                  modulos.add(menu.getCo_modulo());
               }
            }
         }
         System.out.println("SelectMultiple = " + modulos.toString());

         PermisDAO dao = new PermisDAO();
         dao.grabarPermis(usuario.getCo_usuari(), modulos.toString());
         dao.close();
      }

      FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardado", "Se realizó la acción correctamente");
      FacesContext.getCurrentInstance().addMessage(null, message);


      return URL_PERMISO;
   }

   public void createTreeNode() {
      this.root = new DefaultTreeNode("Root Node", null);

      MenuDAO menuDAO = new MenuDAO();
      List<MenuDTO> lstsistemas = menuDAO.getMenus_Sistemas();
      List<MenuDTO> lstsubsis = menuDAO.getMenus_SubSistemas();
      List<MenuDTO> lstpaquetes = menuDAO.getMenus_Paquete();
      List<MenuDTO> lstmodpad = menuDAO.getMenus_ModPad();
      List<MenuDTO> lstsub1 = menuDAO.getMenus_SubMod();

      PermisDAO permisDAO = new PermisDAO();
      List<PermisDTO> lstPermis = permisDAO.getPermis(usuario.getCo_usuari());

      for (MenuDTO menusisDTO : lstsistemas) {
         TreeNode nodesistema = new DefaultTreeNode(menusisDTO, this.root);

         for (MenuDTO menusubDTO : lstsubsis) {
            if (menusubDTO.getCo_sistem().intValue() == menusisDTO.getCo_elemen().intValue()) {
               TreeNode nodesubsis = new DefaultTreeNode(menusubDTO, nodesistema);

               for (MenuDTO menupaqDTO : lstpaquetes) {
                  if (menupaqDTO.getCo_subsis().intValue() == menusubDTO.getCo_elemen().intValue()) {
                     TreeNode nodepaquete = new DefaultTreeNode(menupaqDTO, nodesubsis);

                     for (MenuDTO menupadDTO : lstmodpad) {
                        if (menupadDTO.getCo_paquet().intValue() == menupaqDTO.getCo_elemen().intValue()) {
                           TreeNode nodemodpad = new DefaultTreeNode(menupadDTO, nodepaquete);
                           if (isModuloSelect(lstPermis, menupadDTO.getCo_modulo())){
                              nodemodpad.setSelected(true);
                           }

                           for (MenuDTO menusb1DTO : lstsub1) {
                              if (menusb1DTO.getCo_menpad().intValue() == menupadDTO.getCo_elemen().intValue()) {
                                 TreeNode nodesub1 = new DefaultTreeNode(menusb1DTO, nodemodpad);
                                 if (isModuloSelect(lstPermis, menusb1DTO.getCo_modulo())){
                                    nodesub1.setSelected(true);
                                 }

                                 for (MenuDTO menusb2DTO : lstsub1) {
                                    if (menusb2DTO.getCo_menpad().intValue() == menusb1DTO.getCo_elemen().intValue()) {
                                       TreeNode nodesub2 = new DefaultTreeNode(menusb2DTO, nodesub1);
                                       if (isModuloSelect(lstPermis, menusb2DTO.getCo_modulo())){
                                          nodesub2.setSelected(true);
                                       }

                                       for (MenuDTO menusb3DTO : lstsub1) {
                                          if (menusb3DTO.getCo_menpad().intValue() == menusb2DTO.getCo_elemen().intValue()) {
                                             TreeNode nodesub3 = new DefaultTreeNode(menusb3DTO, nodesub2);
                                             if (isModuloSelect(lstPermis, menusb3DTO.getCo_modulo())){
                                                nodesub3.setSelected(true);
                                             }

                                             for (MenuDTO menusb4DTO : lstsub1) {
                                                if (menusb4DTO.getCo_menpad().intValue() == menusb3DTO.getCo_elemen().intValue()) {
                                                   TreeNode nodesub4 = new DefaultTreeNode(menusb4DTO, nodesub3);
                                                   if (isModuloSelect(lstPermis, menusb4DTO.getCo_modulo())){
                                                      nodesub4.setSelected(true);
                                                   }

                                                   for (MenuDTO menusb5DTO : lstsub1) {
                                                      if (menusb5DTO.getCo_menpad().intValue() == menusb4DTO.getCo_elemen().intValue()) {
                                                         TreeNode nodesub5 = new DefaultTreeNode(menusb5DTO, nodesub4);
                                                         if (isModuloSelect(lstPermis, menusb5DTO.getCo_modulo())){
                                                            nodesub5.setSelected(true);
                                                         }
                                                      }
                                                   }
                                                }
                                             }
                                          }
                                       }
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

      }
      menuDAO.close();
      permisDAO.close();
      System.out.println("Final Bean - UsuarioBean:" + this.root.getData().toString());
   }

   public boolean isModuloSelect(List<PermisDTO> lstPermis, Integer codModulo){
      if (codModulo == null){
         return false;
      }
      for (PermisDTO permis : lstPermis){
         if (permis.getCo_modulo().intValue() == codModulo.intValue()){
            // System.out.println("Final Bean - isModuloSelect:" + codModulo.intValue());

            return true;
         }
      }


      return false;
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

   public TreeNode getRoot() {
      return root;
   }

   public void setRoot(TreeNode root) {
      this.root = root;
   }
}
