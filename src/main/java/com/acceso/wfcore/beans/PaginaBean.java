package com.acceso.wfcore.beans;

import com.acceso.wfcore.daos.PaginaDAO;
import com.acceso.wfcore.daos.RegistroDAO;
import com.acceso.wfcore.dtos.*;
import com.acceso.wfcore.listerners.WFCoreListener;
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

/**
 * @author Mario Huillca <mario.huillca@acceso.com.pe>
 * Created on 30 nov. 2018, 15:54:46
 */
@ManagedBean
@SessionScoped
public class PaginaBean extends MainBean implements Serializable, DefaultMaintenceWeb, DefaultMaintenceDao {

    private static final String URL_LISTA = "/admin/jsf_exec/pagex/pagina/paginaPaginas.xhtml";
    private static final String URL_DETALLE = "/admin/jsf_exec/pagex/pagina/paginaPaginas.xhtml";
    private static final String URL_EDITAR = "/admin/jsf_exec/pagex/pagina/paginaRegPagina.xhtml";
    private static final String URL_NEW = "/admin/jsf_exec/pagex/pagina/paginaRegPagina.xhtml";

    private static final String URL_BTN_EDITAR = "/admin/jsf_exec/pagex/pagina/paginaRegBoton.xhtml";
    private static final String URL_BTN_NEW = "/admin/jsf_exec/pagex/pagina/paginaRegBoton.xhtml";

    private static final String URL_REG_NEW = "/admin/jsf_exec/pagex/pagina/paginaRegRegistro.xhtml";
    private static final String URL_REG_EDIT = "/admin/jsf_exec/pagex/pagina/paginaRegRegistro.xhtml";

    private static final String URL_TIT_NEW = "/admin/jsf_exec/pagex/pagina/paginaRegTitulo.xhtml";
    private static final String URL_TIT_EDIT = "/admin/jsf_exec/pagex/pagina/paginaRegTitulo.xhtml";

    public static final String BEAN_NAME = "paginaBean";

    private List<PaginaDTO> paginas;
    private PaginaDTO pagina;
    private BotonDTO botonSeleccionado;
    private ElementoDTO elementoSeleccionado;
    private List<PaginaDTO> filtroPagina;

    private boolean thisEditable;
    private boolean btnEditable;
    private boolean regEditable;

    public int defaultTabIndex;

    private List<RegistroDTO> registros;
    private List<RegistroDTO> registrosCargados;

    public ScriptDTO script;

    /*UTIL*/
    public SelectItem[] ls_ti_estreg = Util.get_ls_ti_estreg();
    public SelectItem[] ls_ti_pagreg = Util.get_ls_ti_pagreg();
    public SelectItem[] ls_ti_boton = Util.get_ls_ti_boton();
    public SelectItem[] ls_ti_alireg = Util.get_ls_ti_alireg();
    public SelectItem[] ls_ti_valign = Util.get_ls_ti_valign();
    public SelectItem[] ls_ti_nowrap = Util.get_ls_ti_nowrap();

    public PaginaBean() {
        this.beanName = BEAN_NAME;
        this.titleName = "Paginas";
//      this.pagina = new PaginaDTO();
        this.thisEditable = true;
        this.registrosCargados = new ArrayList<>();
    }

    public void onRegistroSeleccionado(DragDropEvent ddEvent) {
        System.out.println("-->> onRegistroSeleccionado - INICIO" + ddEvent.getData());
        RegistroDTO registro = ((RegistroDTO) ddEvent.getData());
        System.out.println("-->> onRegistroSeleccionado - 1");

        registrosCargados.add(registro);
        System.out.println("-->> onRegistroSeleccionado - 2");
        registros.remove(registro);

        System.out.println("-->> onRegistroSeleccionado - FIN");
    }

    @Override
    public String load() {
        System.out.println("load()");
        this.thisEditable = true;

        selectDto();

        //Carga de listado de registros
        cargarRegistros();

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

        PaginaDAO dao = new PaginaDAO();
        pagina.setLs_botone(dao.getButtons(pagina.getCo_pagina()));
        pagina.setLs_elemen(dao.getElementos(pagina.getCo_pagina()));
        dao.close();

        defaultTabIndex = 0;

        return URL_EDITAR;
    }


    public void eventupdateRegist() throws Exception {
        updateRegist();
        FacesContext.getCurrentInstance().getExternalContext().redirect(URL_EDITAR);
    }

    @Override
    public String deleteRegist() {
        deleteDto();

        return URL_LISTA;
    }

    @Override
    public String saveRegist() {
        saveDto();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Página " + this.pagina.getCo_pagina(), "Datos actualizados."));
        return null;
//        return URL_LISTA;
    }

    public String saveRegistApply() {
        saveDto();
        apply();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Página " + this.pagina.getCo_pagina(), "Datos actualizados y aplicacdos."));
        return null;
//        return URL_LISTA;
    }

    @Override
    public void selectDto() {
        PaginaDAO dao = new PaginaDAO();
        this.paginas = dao.getPaginas();
        dao.close();

    }

    @Override
    public void saveDto() {
        PaginaDAO dao = new PaginaDAO();
        this.pagina = dao.save(this.pagina);
        this.pagina.setLs_botone(dao.getButtons(this.pagina.getCo_pagina()));
        this.pagina.setLs_elemen(dao.getElementos(this.pagina.getCo_pagina()));
        System.out.println("this.pagina = " + this.pagina);
        this.paginas = dao.getPaginas();
        dao.close();
    }

    @Override
    public void updateDto() {
//      PaginaDAO dao = new PaginaDAO();
//      this.pagina = dao.grabarPagina(pagina);
//      this.paginas = dao.getPaginas();
////      Sistema.out.println("PaginaBean actualizarPagina = " + this.pagina);
//      dao.close();
    }

    @Override
    public void deleteDto() {
//      PaginaDAO dao = new PaginaDAO();
//      String resultado = dao.deletePagina(pagina);
//      this.paginas = dao.getPaginas();
//      dao.close();
    }

    /*EVENTOS:VOID*/

    public void apply() {
        WFCoreListener.APP.getCacheService().getZeroDawnCache().getSpace(Values.CACHE_MAIN_CONTAINER).clear();
//        WFCoreListener.APP.getCacheService().getZeroDawnCache().getSpace(Values.CACHE_MAIN_VALPAGJS).clear();
//        WFCoreListener.APP.getCacheService().getZeroDawnCache().getSpace(Values.CACHE_MAIN_COMPAGJS).clear();
//        WFCoreListener.APP.getCacheService().getZeroDawnCache().getSpace(Values.CACHE_MAIN_PROPAGJS).clear();
        WFCoreListener.APP.getCacheService().getZeroDawnCache().getSpace(Values.CACHE_MAIN_PAGEJS).clear();
    }

//    public BotonDTO emptyButton() {
//        BotonDTO botonDTO = new BotonDTO();
//        botonDTO.setCo_pagbot(-1);
//        return botonDTO;
//    }

    public String pagbot_new() {
        botonSeleccionado = new BotonDTO();
        botonSeleccionado.setCo_pagbot(-1);

        return URL_BTN_NEW;
    }

    public String pagbot_view() {
        botonSeleccionado = new BotonDTO();
        botonSeleccionado.setCo_pagbot(-1);

        return URL_BTN_NEW;
    }

    public String pagbot_edit() {
        return URL_BTN_NEW;
    }


    public String pagbot_save() {
        PaginaDAO dao = new PaginaDAO();

        dao.saveButton(pagina.getCo_pagina(), botonSeleccionado);
        pagina.setLs_botone(dao.getButtons(pagina.getCo_pagina()));
        pagina.setLs_elemen(dao.getElementos(pagina.getCo_pagina()));

        dao.close();

        return URL_EDITAR;
    }


    public String pagbot_back() {
        return URL_EDITAR;
    }

    public void pagbot_delete() {
        PaginaDAO dao = new PaginaDAO();
        dao.deleteButton(pagina.getCo_pagina(), (short) botonSeleccionado.getCo_pagbot());
        pagina.setLs_botone(dao.getButtons(pagina.getCo_pagina()));
        pagina.setLs_elemen(dao.getElementos(pagina.getCo_pagina()));
        dao.close();
        //recargar lista de botones!!!!
    }

    public void cargarRegistros() {
        RegistroDAO dao = new RegistroDAO();
        registros = dao.getRegistros();
        dao.close();
    }

    public String pagreg_save() {
        System.out.println("elementoSeleccionado: = " + elementoSeleccionado);
        PaginaDAO dao = new PaginaDAO();
        dao.saveRegist(pagina.getCo_pagina(), elementoSeleccionado.getPagregDTO());

        pagina.setLs_botone(dao.getButtons(pagina.getCo_pagina()));
        pagina.setLs_elemen(dao.getElementos(pagina.getCo_pagina()));

        dao.close();

        return URL_EDITAR;
    }

    public String pagreg_new() {
        elementoSeleccionado = new ElementoDTO();
        elementoSeleccionado.setTi_elemen(2);
        PagregDTO pagregDTO = new PagregDTO();
        pagregDTO.setCo_pagreg2(-1);
        pagregDTO.setCo_pagina(pagina.getCo_pagina());

        elementoSeleccionado.setPagregDTO(pagregDTO);

        return URL_REG_EDIT;
    }


    public String pagreg_edit() {
        elementoSeleccionado.getPagregDTO().setCo_pagreg2(elementoSeleccionado.getPagregDTO().getCo_pagreg());

        return URL_REG_EDIT;
    }

    public String pagreg_delete() {
        PaginaDAO dao = new PaginaDAO();
        if (elementoSeleccionado.getTi_elemen() == 1)
            dao.deleteTitle(pagina.getCo_pagina(), (short) elementoSeleccionado.getPagtitDTO().getCo_pagtit());
        else
            dao.deleteRegist(pagina.getCo_pagina(), (short) elementoSeleccionado.getPagregDTO().getCo_pagreg());

        pagina.setLs_botone(dao.getButtons(pagina.getCo_pagina()));
        pagina.setLs_elemen(dao.getElementos(pagina.getCo_pagina()));
        dao.close();
        return URL_EDITAR;
    }

    public String pagreg_back() {
        return URL_EDITAR;
    }

    public String pag_tr_edit() {
        if (elementoSeleccionado.getTi_elemen() == 1)
            return URL_TIT_EDIT;
        else return URL_REG_EDIT;
    }

    public String pagtit_save() {
        System.out.println("elementoSeleccionado: = " + elementoSeleccionado);
        PaginaDAO dao = new PaginaDAO();
        if (elementoSeleccionado.getTi_elemen() == 1)
            dao.saveTitle(pagina.getCo_pagina(), elementoSeleccionado.getPagtitDTO());
        else
            dao.saveRegist(pagina.getCo_pagina(), elementoSeleccionado.getPagregDTO());

        pagina.setLs_botone(dao.getButtons(pagina.getCo_pagina()));
        pagina.setLs_elemen(dao.getElementos(pagina.getCo_pagina()));

        dao.close();

        return URL_EDITAR;
    }

    public String pagtit_back() {
        return URL_EDITAR;
    }


    public String pagtit_new() {
        elementoSeleccionado = new ElementoDTO();
        elementoSeleccionado.setTi_elemen(1);
        PagtitDTO pagtitDTO = new PagtitDTO();
        pagtitDTO.setCo_pagina(pagina.getCo_pagina());
        pagtitDTO.setCo_pagtit(-1);
        elementoSeleccionado.setPagtitDTO(pagtitDTO);

        return URL_TIT_EDIT;
    }

    public PaginaDTO getPagina() {
        return pagina;
    }

    public void setPagina(PaginaDTO pagina) {
        this.pagina = pagina;
    }

    public BotonDTO getBotonSeleccionado() {
        return botonSeleccionado;
    }

    public void setBotonSeleccionado(BotonDTO botonSeleccionado) {
        System.out.println("??>>botonSeleccionado = " + botonSeleccionado);
        this.botonSeleccionado = botonSeleccionado;
    }

    public ElementoDTO getElementoSeleccionado() {
        return elementoSeleccionado;
    }

    public void setElementoSeleccionado(ElementoDTO elementoSeleccionado) {
        this.elementoSeleccionado = elementoSeleccionado;
    }

    public List<PaginaDTO> getPaginas() {
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

    public List<PaginaDTO> getFiltroPagina() {
        return filtroPagina;
    }

    public void setFiltroPagina(List<PaginaDTO> filtroPagina) {
        this.filtroPagina = filtroPagina;
    }

    public List<RegistroDTO> getRegistros() {
        return registros;
    }

    public void setRegistros(List<RegistroDTO> registros) {
        this.registros = registros;
    }

    public List<RegistroDTO> getRegistrosCargados() {
        return registrosCargados;
    }

    public void setRegistrosCargados(List<RegistroDTO> registrosCargados) {
        this.registrosCargados = registrosCargados;
    }

    public int getDefaultTabIndex() {
        return defaultTabIndex;
    }

    public void setDefaultTabIndex(int defaultTabIndex) {
        this.defaultTabIndex = defaultTabIndex;
    }

    public ScriptDTO getScript() {
        return script;
    }

    public void setScript(ScriptDTO script) {
        this.script = script;
    }

    public SelectItem[] getLs_ti_estreg() {
        return ls_ti_estreg;
    }

    public void setLs_ti_estreg(SelectItem[] ls_ti_estreg) {
        this.ls_ti_estreg = ls_ti_estreg;
    }

    public SelectItem[] getLs_ti_pagreg() {
        return ls_ti_pagreg;
    }

    public void setLs_ti_pagreg(SelectItem[] ls_ti_pagreg) {
        this.ls_ti_pagreg = ls_ti_pagreg;
    }

    public SelectItem[] getLs_ti_boton() {
        return ls_ti_boton;
    }

    public void setLs_ti_boton(SelectItem[] ls_ti_boton) {
        this.ls_ti_boton = ls_ti_boton;
    }

    public SelectItem[] getLs_ti_alireg() {
        return ls_ti_alireg;
    }

    public void setLs_ti_alireg(SelectItem[] ls_ti_alireg) {
        this.ls_ti_alireg = ls_ti_alireg;
    }

    public SelectItem[] getLs_ti_valign() {
        return ls_ti_valign;
    }

    public void setLs_ti_valign(SelectItem[] ls_ti_valign) {
        this.ls_ti_valign = ls_ti_valign;
    }

    public SelectItem[] getLs_ti_nowrap() {
        return ls_ti_nowrap;
    }

    public void setLs_ti_nowrap(SelectItem[] ls_ti_nowrap) {
        this.ls_ti_nowrap = ls_ti_nowrap;
    }
}
