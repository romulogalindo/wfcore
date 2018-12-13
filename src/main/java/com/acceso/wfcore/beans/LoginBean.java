package com.acceso.wfcore.beans;

import org.primefaces.context.RequestContext;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class LoginBean extends MainBean implements Serializable {

    public static final String URL_CONTENMAIN = "/admin/jsf_exec/pagex/paginaInicio.xhtml?faces-redirect=true";
    public static final String URL_LOGIN = "/admin/login";

    private static final long serialVersionUID = -2152389656664659476L;
    private String nombre;
    private String clave;
    private boolean logeado = false;

    public boolean estaLogeado() {
        return logeado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String login() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = null;
        if (nombre != null && nombre.equals("admin") && clave != null
                && clave.equals("admin")) {
            logeado = true;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@", nombre);
        } else {
            logeado = false;
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error",
                    "Credenciales no válidas");
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
        //context.addCallbackParam("estaLogeado", logeado);
        System.out.println(">>>>>>>>>><logeado=" + logeado);
        if (logeado) {
            //context.addCallbackParam("view", "gauge.xhtml");

            return URL_CONTENMAIN;
        } else {
            return URL_LOGIN;
        }
    }

    public void logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        session.invalidate();
        logeado = false;
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
        return null;
    }
}
