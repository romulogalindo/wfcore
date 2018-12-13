package com.acceso.wfcore.beans;

import org.primefaces.component.menubutton.MenuButton;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import javax.faces.context.FacesContext;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class ManagerBean extends MainBean implements Serializable {
    private MenuModel model;

    private MenuModel menuButton;
    private boolean renderedMenuButton;
    private boolean renderedCommandButton;
    private String defaultActionNameButton;
    private String defaultActionIcoButton;
    private MainBean currentBean;

    public ManagerBean() {
        this.beanName = "Manager";
        this.renderedMenuButton = false;
        this.renderedCommandButton = false;
        this.defaultActionNameButton="Nuevo";
        this.defaultActionIcoButton="fa fa-plus";

        initBreadCumBar();
        initMenuButton();
    }

    public void initBreadCumBar() {
        this.model = new DefaultMenuModel();

        DefaultMenuItem item = new DefaultMenuItem("Home");
        item.setUrl("/WFCORE/pagex/paginaInicio.xhtml");
        //item.setIcon("ui-icon-home");
        item.setIcon("ui-icon-home");

        this.model.addElement(item);

    }

    public void initMenuButton() {
        this.menuButton = new DefaultMenuModel();
    }

//   public void updateMenuButton(String title, String action) {
//
////      DefaultMenuItem button = new DefaultMenuItem("Ajax Action");
////      button.setUrl("https://www.journaldev.com");
//
////      this.menuButton.addElement(button);
//   }

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

    public MenuModel getMenuButton() {
        return menuButton;
    }

    public void setMenuButton(MenuModel menuButton) {
        this.menuButton = menuButton;
    }

    public boolean isRenderedMenuButton() {
        return renderedMenuButton;
    }

    public void setRenderedMenuButton(boolean renderedMenuButton) {
        this.renderedMenuButton = renderedMenuButton;
    }

    public String getDefaultActionNameButton() {
        return defaultActionNameButton;
    }

    public void setDefaultActionNameButton(String defaultActionNameButton) {
        this.defaultActionNameButton = defaultActionNameButton;
    }

    public MainBean getCurrentBean() {
        return currentBean;
    }

    public void setCurrentBean(MainBean currentBean) {
        this.currentBean = currentBean;
    }

   public boolean isRenderedCommandButton() {
      return renderedCommandButton;
   }

   public void setRenderedCommandButton(boolean renderedCommandButton) {
      this.renderedCommandButton = renderedCommandButton;
   }

   public String getDefaultActionIcoButton() {
      return defaultActionIcoButton;
   }

   public void setDefaultActionIcoButton(String defaultActionIcoButton) {
      this.defaultActionIcoButton = defaultActionIcoButton;
   }

   @Override
    public String load() {
        return null;
    }

    @Override
    public String getBeanName() {
        return null;
    }

    @Override
    public String defaultAction() {
        return currentBean.defaultAction();
    }
}
