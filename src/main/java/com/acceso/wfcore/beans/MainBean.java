package com.acceso.wfcore.beans;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.lifecycle.ClientWindow;

public abstract class MainBean {

    protected String beanName = "MainBean";
    protected String titleName = "Bean";
    protected boolean open = false;

    /**
     * Carga inicial...
     *
     * @return
     */
    public abstract String load();

    public String getBeanName() {
        return beanName;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isOpen() {
        return open;
    }

    public abstract String defaultAction();

    public ClientWindow getWindowID() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        ClientWindow clientWindow = externalContext.getClientWindow();

        if (clientWindow != null) {
            System.out.println("client window id: " + clientWindow.getId());
        } else {
            System.out.println("client window cannot be determined!");
        }

        return clientWindow;
    }
}
