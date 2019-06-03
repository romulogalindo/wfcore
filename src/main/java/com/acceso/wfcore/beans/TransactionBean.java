package com.acceso.wfcore.beans;

import com.acceso.wfcore.daos.ConexionDAO;
import com.acceso.wfcore.dtos.ConexionDTO;
import com.acceso.wfcore.dtos.TransaDTO;
import com.acceso.wfcore.kernel.ApplicationManager;
import com.acceso.wfcore.kernel.WFIOAPP;
import com.acceso.wfcore.managers.DataManager;
import com.acceso.wfcore.transa.Transactional;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 * @author Mario Huillca <romulo.galindo@gmail.com>
 */
@ManagedBean
@SessionScoped
public class TransactionBean extends MainBean implements Serializable, DefaultMaintenceWeb, DefaultMaintenceDao {

    private static final String URL_LISTA = "/admin/jsf_exec/pagex/unicron/paginaTransax.xhtml";
    private static final String URL_DETALLE = "/admin/jsf_exec/pagex/conexion/paginaConexiones.xhtml";
    private static final String URL_EDITAR = "/admin/jsf_exec/pagex/conexion/paginaRegConexion.xhtml";
    private static final String URL_NEW = "/admin/jsf_exec/pagex/conexion/paginaRegConexion.xhtml";

    public static final String BEAN_NAME = "conexionBean";

    private List<TransaDTO> transax;
    private TransaDTO transa;

    private boolean isregEditable;

    public TransactionBean() {
        this.beanName = BEAN_NAME;
        this.titleName = "Conexiones";
        this.transa = new TransaDTO();
        this.isregEditable = true;
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
        ((ManagerBean) context.getApplication().getVariableResolver().resolveVariable(context, "managerBean")).updateBreadCumBar(this.titleName, URL_LISTA);
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
        this.transa = new TransaDTO();
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
        ConexionDAO dao = new ConexionDAO();
        this.transax = Transactional.get();
        dao.close();
    }

    @Override
    public void saveDto() {
        //NO IMPLEMENTADO
    }

    @Override
    public void updateDto() {
        //NO IMPLEMENTADO
    }

    @Override
    public void deleteDto() {
        //NO IMPLEMENTADO
    }

    public TransaDTO getTransa() {
        return transa;
    }

    public void setTransa(TransaDTO transa) {
        this.transa = transa;
    }

    public List<TransaDTO> getTransax() {
        return transax;
    }

    public void setTransax(List<TransaDTO> transax) {
        this.transax = transax;
    }

    public boolean isIsregEditable() {
        return isregEditable;
    }

    public void setIsregEditable(boolean isregEditable) {
        this.isregEditable = isregEditable;
    }

    @Override
    public boolean isOpen() {
        return this.open;
    }
}
