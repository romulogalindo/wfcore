package com.acceso.wfcore.beans;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class ManagerBean extends MainBean implements Serializable {
   private MenuModel model;

   public ManagerBean() {
      this.beanName = "Manager";

      initBreadCumBar();
   }

   public void initBreadCumBar() {
      this.model = new DefaultMenuModel();

      DefaultMenuItem item = new DefaultMenuItem("Home");
      item.setUrl("/WFCORE/pagex/paginaInicio.xhtml");
      //item.setIcon("ui-icon-home");
      item.setIcon("ui-icon-home");

      this.model.addElement(item);
   }

   public void updateBreadCumBar(String title, String url) {
      //DefaultSubMenu firstSubmenu = new DefaultSubMenu("Dynamic Submenu");

      DefaultMenuItem item = new DefaultMenuItem(title);
      item.setUrl(url);
      item.setTitle(title);
      item.setValue(title);

      //firstSubmenu.addElement(item);
      this.model.addElement(item);

   }

   public MenuModel getModel() {
      return model;
   }

   public void setModel(MenuModel model) {
      this.model = model;
   }

   @Override
   public String load() {
      return null;
   }

   @Override
   public String getBeanName() {
      return null;
   }
}
