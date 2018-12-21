package com.acceso.wfcore.beans;

import com.acceso.wfcore.daos.MenuDAO;
import com.acceso.wfcore.daos.PaqueteDAO;
import com.acceso.wfcore.daos.SistemaDAO;
import com.acceso.wfcore.daos.SubSistemaDAO;
import com.acceso.wfcore.dtos.MenuDTO;
import com.acceso.wfcore.dtos.PaqueteDTO;
import com.acceso.wfcore.dtos.SistemaDTO;
import com.acceso.wfcore.dtos.SubSistemaDTO;
import com.acceso.wfcore.utils.Document;
import org.primefaces.model.DefaultTreeNode;
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
public class MenuBean extends MainBean implements Serializable, DefaultMaintenceWeb, DefaultMaintenceDao {

   private static final String URL_LISTA = "/admin/jsf_exec/pagex/menu/paginaMenus.xhtml";
   private static final String URL_DETALLE = "/admin/jsf_exec/pagex/menu/paginaMenus.xhtml";
   private static final String URL_EDITAR = "/admin/jsf_exec/pagex/menu/paginaRegMenu.xhtml";
   private static final String URL_NEW = "/admin/jsf_exec/pagex/menu/paginaRegMenu.xhtml";


   private List<MenuDTO> menus;
   private MenuDTO menu;

   private boolean isregEditable;

   private List<SelectItem> lstSubSistemabySistema;
   private List<SelectItem> lstPaquetebySubSistema;

   TreeNode root;

   public MenuBean() {
      this.beanName = "Menus";
      this.menu = new MenuDTO();
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

   public TreeNode createDocuments() {
      this.root = new DefaultTreeNode(new Document("", null, null, -1), null);

      List<SistemaDTO> lstsistemas;
      SistemaDAO sisDAO = new SistemaDAO();
      lstsistemas = sisDAO.getSistemas();
      for (SistemaDTO sisDTO : lstsistemas) {
         TreeNode nodesistema = new DefaultTreeNode(new Document(sisDTO.getNo_sistem(), null, null, 0),  this.root);

      }

      return root;
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
      this.menu = new MenuDTO();
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
      MenuDAO dao = new MenuDAO();
      this.menus = dao.getMenus(-1, -1);
      dao.close();
   }

   @Override
   public void saveDto() {
      MenuDAO dao = new MenuDAO();
      //this.menu = dao.grabarMenu(menu);
      this.menus = dao.getMenus(-1, -1);
//      System.out.println("MenuBean actualizarMenu = " + this.menu);
      dao.close();
   }

   @Override
   public void updateDto() {
      MenuDAO dao = new MenuDAO();
      //this.menu = dao.grabarMenu(menu);
      this.menus = dao.getMenus(-1, -1);
//      System.out.println("MenuBean actualizarMenu = " + this.menu);
      dao.close();
   }

   @Override
   public void deleteDto() {
      MenuDAO dao = new MenuDAO();
      //String resultado = dao.deleteMenu(menu);
      this.menus = dao.getMenus(-1, -1);
      dao.close();
   }

   public List<MenuDTO> getMenus() {
      return menus;
   }

   public void setMenus(List<MenuDTO> menus) {
      this.menus = menus;
   }

   public MenuDTO getMenu() {
      return menu;
   }

   public void setMenu(MenuDTO menu) {
      this.menu = menu;
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
