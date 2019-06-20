package com.acceso.wfcore.beans;

import com.acceso.wfcore.daos.PaginaDAO;
import com.acceso.wfcore.daos.SistemaDAO;
import com.acceso.wfcore.dtos.SistemaDTO;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
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
@ViewScoped
public class SistemaLSBean extends MainBean implements Serializable, DefaultMaintenceWeb {

    private static final String URL_LISTA = "/admin/jsf_exec/pagex/sistema/paginaSistemas.xhtml";
    private static final String URL_DETALLE = "/admin/jsf_exec/pagex/sistema/paginaSistemas.xhtml";
    private static final String URL_EDITAR = "/admin/jsf_exec/pagex/sistema/paginaRegSistema.xhtml";
    private static final String URL_NEW = "/admin/jsf_exec/pagex/sistema/paginaRegSistema.xhtml";

    public static final String BEAN_NAME = "sistemaBean";

    private List<SistemaDTO> sistemas;
    private SistemaDTO sistema;

    private boolean isregEditable;

    public SistemaLSBean() {
        this.beanName = BEAN_NAME;
        this.titleName = "Sistemas";
        this.sistema = new SistemaDTO();
        this.isregEditable = true;
    }

    public List<SelectItem> getComboSistema() {
        List<SelectItem> res = new ArrayList<>();

        for (SistemaDTO sis : sistemas) {
            SelectItem item = new SelectItem();
            item.setLabel(sis.getNo_sistem());
            item.setDescription(sis.getDe_sistem());
            item.setValue(sis.getCo_sistem());
            res.add(item);
        }
        return res;
    }

    /*
    ACCION DE CARGA==>NO TOCAR
     */
    @PostConstruct
    public void loadModule() {
        doListener();
        System.out.println("* do listener= >>");
        //------------------------
        SistemaDAO dao = new SistemaDAO();
        this.sistemas = dao.getSistemas();
        dao.close();
    }


    @Override
    public String load() {
        System.out.println("load()");
        this.isregEditable = true;
        // LLENAR LOS BOTONES SECUNDARIOS
        //doListener();
        //CARGA INICIAL!!
//        selectDto();

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

        //ManagerBean -> Open!
        ((ManagerBean) context.getApplication().getVariableResolver().resolveVariable(context, "managerBean")).setOpenedModule(this.beanName);

        System.out.println("listener()");
    }

    @Override
    public String defaultAction() {
        // Para el nuevo registro
        this.sistema = new SistemaDTO();
        FacesContext context = FacesContext.getCurrentInstance();
        ((ManagerBean) context.getApplication().getVariableResolver().resolveVariable(context, "managerBean")).setRenderedCommandButton(false);
        ((ManagerBean) context.getApplication().getVariableResolver().resolveVariable(context, "managerBean")).updateBreadCumBar("Registro", URL_EDITAR);
        //varias cosas para editar
        return URL_NEW;
    }

    @Override
    public String newRegist() {
        return URL_EDITAR;
    }

    @Override
    public String updateRegist() {
        FacesContext context = FacesContext.getCurrentInstance();
        ((ManagerBean) context.getApplication().getVariableResolver().resolveVariable(context, "managerBean")).updateBreadCumBar("Editar", URL_EDITAR);
        ((ManagerBean) context.getApplication().getVariableResolver().resolveVariable(context, "managerBean")).setRenderedCommandButton(false);

        /*EXP-->TRANSFER*/
        String idSisTransa = "X64SIS";
        FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(idSisTransa, sistema);
        return URL_EDITAR;
    }

    @Override
    public String deleteRegist() {
        SistemaDAO dao = new SistemaDAO();
        String resultado = dao.deleteSistema(sistema);
        this.sistemas = dao.getSistemas();
        dao.close();

        return URL_LISTA;
    }

    @Override
    public String saveRegist() {
        //NO IMPLEMENTADO AQUI
        return URL_LISTA;
    }

    public List<SistemaDTO> getSistemas() {
        return sistemas;
    }

    public void setSistemas(List<SistemaDTO> sistemas) {
        this.sistemas = sistemas;
    }

    public SistemaDTO getSistema() {
        return sistema;
    }

    public void setSistema(SistemaDTO sistema) {
        this.sistema = sistema;
    }

    public boolean isIsregEditable() {
        return isregEditable;
    }

    public void setIsregEditable(boolean isregEditable) {
        this.isregEditable = isregEditable;
    }

}
