package com.acceso.wfcore.beans;

import com.acceso.wfcore.daos.SistemaDAO;
import com.acceso.wfcore.daos.SubSistemaDAO;
import com.acceso.wfcore.dtos.SubSistemaDTO;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;

/**
 * @author Mario Huillca <mario.huillca@acceso.com.pe>
 * Created on 30 nov. 2018, 15:54:46
 */
@ManagedBean
@ViewScoped
public class SubSistemaLSBean extends MainBean implements Serializable, DefaultMaintenceWeb {

    private static final String URL_LISTA = "/admin/jsf_exec/pagex/subsistema/paginaSubSistemas.xhtml";
    private static final String URL_DETALLE = "/admin/jsf_exec/pagex/subsistema/paginaSubSistemas.xhtml";
    private static final String URL_EDITAR = "/admin/jsf_exec/pagex/subsistema/paginaRegSubSistema.xhtml";
    private static final String URL_NEW = "/admin/jsf_exec/pagex/subsistema/paginaRegSubSistema.xhtml";

    public static final String BEAN_NAME = "subSistemaLSBean";

    private List<SubSistemaDTO> subsistemas;
    private SubSistemaDTO subsistema;

    private boolean isregEditable;

    public SubSistemaLSBean() {
        this.beanName = BEAN_NAME;
        this.titleName = "Sub Sistema";
        this.subsistema = new SubSistemaDTO();
        this.isregEditable = true;
    }

    /*
   ACCION DE CARGA==>NO TOCAR
    */
    @PostConstruct
    public void loadModule() {
        SubSistemaDAO dao = new SubSistemaDAO();
        this.subsistemas = dao.getSubSistemas();
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
        this.subsistema = new SubSistemaDTO();
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
        String idSubTransa = "X64SUB";
        FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(idSubTransa, subsistema);

        return URL_EDITAR;
    }

    @Override
    public String deleteRegist() {
//        deleteDto();

        return URL_LISTA;
    }

    @Override
    public String saveRegist() {
//        saveDto();
        return URL_LISTA;
    }

//    @Override
//    public void selectDto() {
//        SubSistemaDAO dao = new SubSistemaDAO();
//        this.subsistemas = dao.getSubSistemas();
//        dao.close();
//    }
//
//    @Override
//    public void saveDto() {
//        SubSistemaDAO dao = new SubSistemaDAO();
//        this.subsistema = dao.grabarSubSistema(subsistema);
//        this.subsistemas = dao.getSubSistemas();
////      System.out.println("SubSistemaBean actualizarSubSistema = " + this.subsistema);
//        dao.close();
//    }
//
//    @Override
//    public void updateDto() {
//        SubSistemaDAO dao = new SubSistemaDAO();
//        this.subsistema = dao.grabarSubSistema(subsistema);
//        this.subsistemas = dao.getSubSistemas();
////      System.out.println("SubSistemaBean actualizarSubSistema = " + this.subsistema);
//        dao.close();
//    }
//
//    @Override
//    public void deleteDto() {
//        SubSistemaDAO dao = new SubSistemaDAO();
//        String resultado = dao.deleteSubSistema(subsistema);
//        this.subsistemas = dao.getSubSistemas();
//        dao.close();
//    }

    public List<SubSistemaDTO> getSubsistemas() {
        return subsistemas;
    }

    public void setSubsistemas(List<SubSistemaDTO> subsistemas) {
        this.subsistemas = subsistemas;
    }

    public SubSistemaDTO getSubsistema() {
        return subsistema;
    }

    public void setSubsistema(SubSistemaDTO subsistema) {
        this.subsistema = subsistema;
    }

    public boolean isIsregEditable() {
        return isregEditable;
    }

    public void setIsregEditable(boolean isregEditable) {
        this.isregEditable = isregEditable;
    }

}
