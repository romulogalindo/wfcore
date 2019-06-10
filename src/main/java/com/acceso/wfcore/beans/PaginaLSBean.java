package com.acceso.wfcore.beans;

import com.acceso.wfcore.daos.PaginaDAO;
import com.acceso.wfcore.daos.RegistroDAO;
import com.acceso.wfcore.dtos.*;
import com.acceso.wfcore.kernel.WFIOAPP;
import com.acceso.wfcore.log.Log;
import com.acceso.wfcore.utils.Util;
import com.acceso.wfcore.utils.Values;
import org.primefaces.event.DragDropEvent;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

/**
 * @author Mario Huillca <mario.huillca@acceso.com.pe>
 * Created on 30 nov. 2018, 15:54:46
 */
@ManagedBean
@ViewScoped
public class PaginaLSBean extends MainBean implements Serializable, DefaultMaintenceWeb {

    private static final String URL_LISTA = "/admin/jsf_exec/pagex/pagina/paginaPaginas.xhtml";
//    private static final String URL_DETALLE = "/admin/jsf_exec/pagex/pagina/paginaPaginas.xhtml";
    private static final String URL_EDITAR = "/admin/jsf_exec/pagex/pagina/paginaRegPagina.xhtml";
    private static final String URL_NEW = "/admin/jsf_exec/pagex/pagina/paginaRegPagina.xhtml";

    public static final String BEAN_NAME = "paginaLSBean";

    private List<PaginaDTO> paginas;
    private PaginaDTO pagina;
//    private BotonDTO botonSeleccionado;
//    private ElementoDTO elementoSeleccionado;
//    private List<PaginaDTO> filtroPagina;

    private boolean thisEditable = true;
    private boolean btnEditable = true;
    private boolean regEditable = true;

    public int defaultTabIndex;

//    private List<RegistroDTO> registros;
//    private List<RegistroDTO> registrosCargados;
//    public ScriptDTO script;

    /*UTIL*/
//    public SelectItem[] ls_ti_estreg = Util.get_ls_ti_estreg();
//    public SelectItem[] ls_ti_pagreg = Util.get_ls_ti_pagreg();
//    public SelectItem[] ls_ti_boton = Util.get_ls_ti_boton();
//    public SelectItem[] ls_ti_alireg = Util.get_ls_ti_alireg();
//    public SelectItem[] ls_ti_valign = Util.get_ls_ti_valign();
//    public SelectItem[] ls_ti_nowrap = Util.get_ls_ti_nowrap();
//    public SelectItem[] ls_no_icobot = Util.get_ls_ti_icobot();
//    public SelectItem[] ls_no_icopos = Util.get_ls_ti_icopos();
//    public SelectItem[] ls_dt_pagina;
    public PaginaLSBean() {
        this.beanName = BEAN_NAME;
        this.titleName = "Paginas";
        this.thisEditable = true;
//        this.registrosCargados = new ArrayList<>();
    }

    public void onRegistroSeleccionado(DragDropEvent ddEvent) {
        System.out.println("-->> onRegistroSeleccionado - INICIO" + ddEvent.getData());
        RegistroDTO registro = ((RegistroDTO) ddEvent.getData());
        System.out.println("-->> onRegistroSeleccionado - 1");

//        registrosCargados.add(registro);
        System.out.println("-->> onRegistroSeleccionado - 2");
//        registros.remove(registro);

        System.out.println("-->> onRegistroSeleccionado - FIN");
    }

    @Override
    public String load() {
        System.out.println("load()");
        this.thisEditable = true;

//        selectDto();
        //Carga de listado de registros
//        cargarRegistros();
        return URL_LISTA;
    }

    /*
    PRIMERA ACCION
     */
    public String firstAction() {
        this.thisEditable = true;

        return URL_EDITAR;
    }

    /*
    ACCION DE APERTURA DE MODULO ==>NO TOCAR
     */
    public String openModule() {
//        this.thisEditable = true;
        return URL_LISTA;
    }

    /*
    ACCION DE CARGA==>NO TOCAR
     */
    @PostConstruct
    public void loadModule() {
//        Log.info("[PaginaLSBean]->PostConstruct:" + getWindowID());
        Log.info("[PaginaLSBean]->PostConstruct:");
        /*EXP-->TRANSFER*/
//        String idPagTransa = "X64PAG" + getWindowID().getId();
        String idPagTransa = "X64PAG";
        Object obj = FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get(idPagTransa);
        Log.info("[PaginaLSBean]obj = " + obj);

        PaginaDAO dao = new PaginaDAO();
        this.paginas = dao.getPaginas();
        dao.close();

        Log.info("[PaginaLSBean]obj = " + this.paginas == null ? 0 : this.paginas.size());
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
        setOpen(true);

        Log.info("[PaginaLSBean]listener()");
    }

    @Override
    public String defaultAction() {
        // Para el nuevo registro
//      this.pagina = new PaginaDTO();
        FacesContext context = FacesContext.getCurrentInstance();
        ((ManagerBean) context.getApplication().getVariableResolver().resolveVariable(context, "managerBean")).setRenderedCommandButton(false);
        ((ManagerBean) context.getApplication().getVariableResolver().resolveVariable(context, "managerBean")).updateBreadCumBar("Registro", URL_EDITAR);

        //Cargar Pick List
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

        Log.info("[updateRegist]pagina:" + pagina);
        PaginaDAO dao = new PaginaDAO();
        pagina.setLs_botone(dao.getButtons(pagina.getCo_pagina()));
        pagina.setLs_elemen(dao.getElementos(pagina.getCo_pagina()));
        dao.close();

        defaultTabIndex = 0;

        /*EXP-->TRANSFER*/
//        String idPagTransa = "X64PAG" + getWindowID().getId();
        String idPagTransa = "X64PAG";
        FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(idPagTransa, pagina);
        return URL_EDITAR;
    }

    public void eventupdateRegist() throws Exception {
        updateRegist();
        FacesContext.getCurrentInstance().getExternalContext().redirect(URL_EDITAR);
    }

//    public void eventupdateRegistorTitle() throws Exception {
//        pagreg_edit();
//        FacesContext.getCurrentInstance().getExternalContext().redirect(pag_tr_edit());
//    }
    @Override
    public String deleteRegist() {
//        deleteDto();

        return URL_LISTA;
    }

    @Override
    public String saveRegist() {
//        saveDto();
//        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Página " + this.pagina.getCo_pagina(), "Datos actualizados."));
        return null;
    }

//    @Override
    public PaginaDTO getPagina() {
        return pagina;
    }

    public void setPagina(PaginaDTO pagina) {
        Log.info("Seteando pagina>>" + pagina);
        this.pagina = pagina;
    }

    public List<PaginaDTO> getPaginas() {
//        Log.info("[PaginaLSBean:getPaginas]paginas:" + paginas);
        return paginas;
    }

    public void setPaginas(List<PaginaDTO> paginas) {
        this.paginas = paginas;
    }

    public boolean isThisEditable() {
        return thisEditable;
    }

    public void setThisEditable(boolean thisEditable) {
        this.thisEditable = thisEditable;
    }

    public boolean isBtnEditable() {
        return btnEditable;
    }

    public void setBtnEditable(boolean btnEditable) {
        this.btnEditable = btnEditable;
    }

    public boolean isRegEditable() {
        return regEditable;
    }

    public void setRegEditable(boolean regEditable) {
        this.regEditable = regEditable;
    }

    public int getDefaultTabIndex() {
        return defaultTabIndex;
    }

    public void setDefaultTabIndex(int defaultTabIndex) {
        this.defaultTabIndex = defaultTabIndex;
    }

}
