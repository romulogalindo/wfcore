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

    public ManagerBean()    {
        this.beanName = "Manager";

        this.model = new DefaultMenuModel();

        //DefaultSubMenu firstSubmenu = new DefaultSubMenu("Dynamic Submenu");

        DefaultMenuItem item = new DefaultMenuItem("External");
        item.setUrl("http://www.primefaces.org");
        item.setIcon("ui-icon-home");

        //firstSubmenu.addElement(item);
        this.model.addElement(item);
    }

    public void updateBreadCumBar(String title, String subTitle){
        this.model = new DefaultMenuModel();

        //DefaultSubMenu firstSubmenu = new DefaultSubMenu("Dynamic Submenu");

        DefaultMenuItem item = new DefaultMenuItem(title);
        item.setUrl("http://www.primefaces.org");
        item.setIcon("ui-icon-home");

        //firstSubmenu.addElement(item);
        this.model.addElement(item);

        DefaultMenuItem item2 = new DefaultMenuItem(subTitle);
        item2.setUrl("http://www.primefaces.org");
        item2.setIcon("ui-icon-home");

        //firstSubmenu.addElement(item);
        this.model.addElement(item2);
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
