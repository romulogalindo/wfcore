package com.acceso.wfcore.beans;

import com.acceso.wfcore.daos.ContenedorDAO;
import com.acceso.wfcore.daos.PaginaDAO;
import com.acceso.wfcore.dtos.ConparDTO;
import com.acceso.wfcore.dtos.ContabDTO;
import com.acceso.wfcore.dtos.ContenedorDTO;
import com.acceso.wfcore.dtos.PaginaconDTO;
import com.acceso.wfcore.listerners.WFCoreListener;
import com.acceso.wfcore.utils.Util;
import com.acceso.wfcore.utils.Values;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mario Huillca <mario.huillca@acceso.com.pe>
 * Created on 30 nov. 2018, 15:54:46
 */
@ManagedBean
@SessionScoped
public class ContenedorBean extends MainBean implements Serializable, DefaultMaintenceWeb, DefaultMaintenceDao {

    private static final String URL_LISTA = "/admin/jsf_exec/pagex/contenedor/paginaContenedores.xhtml";
    private static final String URL_DETALLE = "/admin/jsf_exec/pagex/contenedor/paginaContenedores.xhtml";
    private static final String URL_EDITAR = "/admin/jsf_exec/pagex/contenedor/paginaRegContenedor.xhtml";
    private static final String URL_NEW = "/admin/jsf_exec/pagex/contenedor/paginaRegContenedor.xhtml";

    private static final String URL_CONTAB_NEW = "/admin/jsf_exec/pagex/contenedor/paginaRegContab.xhtml";
    private static final String URL_CONTAB_EDIT = "/admin/jsf_exec/pagex/contenedor/paginaRegContab.xhtml";

    private static final String URL_CONPAR_NEW = "/admin/jsf_exec/pagex/contenedor/paginaRegConpar.xhtml";
    private static final String URL_CONPAR_EDIT = "/admin/jsf_exec/pagex/contenedor/paginaRegConpar.xhtml";

    private static final String URL_MANAGER_PAGE = "/admin/jsf_exec/pagex/contenedor/paginaRegPagina.xhtml";

    public static final String BEAN_NAME = "contenedorBean";

    //    private ContenedorDAO contenedorDAO;
    private List<ContenedorDTO> contenedores;
    private ContenedorDTO contenedor;
    private List<ContenedorDTO> filtroContenedor;

    private boolean isregEditable;
    private boolean contabEditable;
    private boolean conparEditable;

    public int defaultTabIndex;

    /*ELEMENTOS TRANSACCIONALES*/
    ContabDTO contabSeleccionado;
    ConparDTO conparSeleccionado;
    private Map<Integer, Integer> hmap;

    /*SI*/
    public SelectItem[] ls_ti_alireg = Util.get_ls_ti_alireg();
    public SelectItem[] ls_ti_valign = Util.get_ls_ti_valign();

    @PostConstruct
    public void init() {
        hmap = new HashMap<>();
        hmap.put(1, 1);
    }

    public ContenedorBean() {
        this.beanName = BEAN_NAME;
        this.titleName = "Contenedores";
//      this.contenedor = new ContenedorDTO();
        this.isregEditable = true;
    }

    public void incrementFila() {
        if (hmap.size() < 11) {
            hmap.put(hmap.size() + 1, 1);
        }
    }

    public void decreaseFila() {
        if (hmap.size() > 1) {
            hmap.remove(hmap.size());
        }
    }

    public void incrementColumna(Integer fila) {
        Integer columna = hmap.get(fila);
        if (columna < 9) {
            columna = columna + 1;
            hmap.replace(fila, columna);
        }
    }

    public void decreaseColumna(Integer fila) {
        Integer columna = hmap.get(fila);
        if (columna > 1) {
            columna = columna - 1;
            hmap.replace(fila, columna);
        }
    }

    public List<Integer> getFilas() {
        List<Integer> filas = new ArrayList<>();
        for (int i = 1; i < (hmap.size() + 1); i++) {
            filas.add(i);
        }
        return filas;
    }

    public List<Integer> getColumnas(Integer fila) {
        Integer vaColumna = hmap.get(fila) + 1;
        List<Integer> columnas = new ArrayList<>();
        for (int i = 1; i < vaColumna; i++) {
            columnas.add(i);
        }
        return columnas;
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
//      this.contenedor = new ContenedorDTO();
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

        ContenedorDAO dao = new ContenedorDAO();
        contenedor.setLs_conpar(dao.getConpars(contenedor.getCo_conten()));
        contenedor.setLs_contab(dao.getContabs(contenedor.getCo_conten()));
        dao.close();

        PaginaDAO dao2 = new PaginaDAO();
        List<PaginaconDTO> pagscon = dao2.getPaginascon(contenedor.getCo_conten());
        dao2.close();

        for (ContabDTO contabDTO : contenedor.getLs_contab()) {
            if (contabDTO.getCo_pagina() != null) {
                for (PaginaconDTO paginaconDTO : pagscon) {
                    if (contabDTO.getCo_pagina() == paginaconDTO.getCo_pagina()) {
                        contabDTO.setNo_pagina(paginaconDTO.getNo_pagtit());
                        break;
                    }
                }
            }
        }

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
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Contenedor " + this.contenedor.getCo_conten(), "Datos actualizados."));
        return null;
//        return URL_LISTA;
    }

    public String saveRegistApply() {
        saveDto();
        apply();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Contenedor " + this.contenedor.getCo_conten(), "Datos actualizados y aplicacdos."));
        return null;
//        return URL_LISTA;
    }

    @Override
    public void selectDto() {
        ContenedorDAO dao = new ContenedorDAO();
        this.contenedores = dao.getContenedores();
        dao.close();
    }

    @Override
    public void saveDto() {
        ContenedorDAO dao = new ContenedorDAO();
//      this.contenedor = dao.grabarContenedor(contenedor);
        this.contenedores = dao.getContenedores();
//      Sistema.out.println("ContenedorBean actualizarContenedor = " + this.contenedor);
        dao.close();
    }

    @Override
    public void updateDto() {
//      ContenedorDAO dao = new ContenedorDAO();
//      this.contenedor = dao.grabarContenedor(contenedor);
//      this.contenedores = dao.getContenedores();
////      Sistema.out.println("ContenedorBean actualizarContenedor = " + this.contenedor);
//      dao.close();
    }

    @Override
    public void deleteDto() {
//      ContenedorDAO dao = new ContenedorDAO();
//      String resultado = dao.deleteContenedor(contenedor);
//      this.contenedores = dao.getContenedores();
//      dao.close();
    }

    public void apply() {
        WFCoreListener.APP.getCacheService().getZeroDawnCache().getSpace(Values.CACHE_MAIN_CONTAINER).clear();
        WFCoreListener.APP.getCacheService().getZeroDawnCache().getSpace(Values.CACHE_MAIN_VALPAGJS).clear();
        WFCoreListener.APP.getCacheService().getZeroDawnCache().getSpace(Values.CACHE_MAIN_COMPAGJS).clear();
        WFCoreListener.APP.getCacheService().getZeroDawnCache().getSpace(Values.CACHE_MAIN_PROPAGJS).clear();
    }

    public String contab_new() {
        contabSeleccionado = new ContabDTO();
        contabSeleccionado.setCo_contab((short) -1);
        contabSeleccionado.setCo_conten(contenedor.getCo_conten());

        return URL_CONTAB_NEW;
    }

    public String contab_edit() {
//        return URL_MANAGER_PAGE;
        return URL_CONTAB_EDIT;
    }


    public String contab_save() {
        ContenedorDAO dao = new ContenedorDAO();
        dao.saveContab(this.contabSeleccionado);
        contenedor.setLs_conpar(dao.getConpars(contenedor.getCo_conten()));
        contenedor.setLs_contab(dao.getContabs(contenedor.getCo_conten()));
        dao.close();

        PaginaDAO dao2 = new PaginaDAO();
        List<PaginaconDTO> pagscon = dao2.getPaginascon(contenedor.getCo_conten());
        dao2.close();

        for (ContabDTO contabDTO : contenedor.getLs_contab()) {
            if (contabDTO.getCo_pagina() != null) {
                for (PaginaconDTO paginaconDTO : pagscon) {
                    if (contabDTO.getCo_pagina() == paginaconDTO.getCo_pagina()) {
                        contabDTO.setNo_pagina(paginaconDTO.getNo_pagtit());
                        break;
                    }
                }
            }
        }

        return URL_EDITAR;
    }

    public String contab_delete() {
        ContenedorDAO dao = new ContenedorDAO();
        dao.deleteContab(this.contabSeleccionado);
        this.contenedor.setLs_conpar(dao.getConpars(this.contenedor.getCo_conten()));
        this.contenedor.setLs_contab(dao.getContabs(this.contenedor.getCo_conten()));
        dao.close();

        PaginaDAO dao2 = new PaginaDAO();
        List<PaginaconDTO> pagscon = dao2.getPaginascon(this.contenedor.getCo_conten());
        dao2.close();

        for (ContabDTO contabDTO : this.contenedor.getLs_contab()) {
            if (contabDTO.getCo_pagina() != null) {
                for (PaginaconDTO paginaconDTO : pagscon) {
                    if (contabDTO.getCo_pagina() == paginaconDTO.getCo_pagina()) {
                        contabDTO.setNo_pagina(paginaconDTO.getNo_pagtit());
                        break;
                    }
                }
            }
        }

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Contenedor " + this.contenedor.getCo_conten(), "Datos eliminados."));
        return null;
//        return URL_EDITAR;
    }

    public String conpag_edit() {
//        return URL_MANAGER_PAGE;
        return URL_MANAGER_PAGE;
    }

    public String contab_back() {
        return URL_EDITAR;
    }

    public String conpar_new() {
        conparSeleccionado = new ConparDTO();
        conparSeleccionado.setCo_conpar((short) -1);
        conparSeleccionado.setCo_conten(contenedor.getCo_conten());

        return URL_CONPAR_NEW;
    }

    public String conpar_edit() {
        return URL_CONPAR_EDIT;
    }

    public String conpar_save() {
        ContenedorDAO dao = new ContenedorDAO();
        dao.saveConpar(conparSeleccionado);
        contenedor.setLs_conpar(dao.getConpars(contenedor.getCo_conten()));
        contenedor.setLs_contab(dao.getContabs(contenedor.getCo_conten()));
        dao.close();

        PaginaDAO dao2 = new PaginaDAO();
        List<PaginaconDTO> pagscon = dao2.getPaginascon(contenedor.getCo_conten());
        dao2.close();

        for (ContabDTO contabDTO : contenedor.getLs_contab()) {
            if (contabDTO.getCo_pagina() != null) {
                for (PaginaconDTO paginaconDTO : pagscon) {
                    if (contabDTO.getCo_pagina() == paginaconDTO.getCo_pagina()) {
                        contabDTO.setNo_pagina(paginaconDTO.getNo_pagtit());
                        break;
                    }
                }
            }
        }

        return URL_EDITAR;
    }

    public String conpar_delete() {
        ContenedorDAO dao = new ContenedorDAO();
        dao.deleteConpar(conparSeleccionado);
        contenedor.setLs_conpar(dao.getConpars(contenedor.getCo_conten()));
        contenedor.setLs_contab(dao.getContabs(contenedor.getCo_conten()));
        dao.close();

        PaginaDAO dao2 = new PaginaDAO();
        List<PaginaconDTO> pagscon = dao2.getPaginascon(contenedor.getCo_conten());
        dao2.close();

        for (ContabDTO contabDTO : contenedor.getLs_contab()) {
            if (contabDTO.getCo_pagina() != null) {
                for (PaginaconDTO paginaconDTO : pagscon) {
                    if (contabDTO.getCo_pagina() == paginaconDTO.getCo_pagina()) {
                        contabDTO.setNo_pagina(paginaconDTO.getNo_pagtit());
                        break;
                    }
                }
            }
        }

        return URL_EDITAR;
    }

    public String conpar_back() {
        return URL_EDITAR;
    }

    public ContenedorDTO getContenedor() {
        return contenedor;
    }

    public void setContenedor(ContenedorDTO contenedor) {
        this.contenedor = contenedor;
    }

    public List<ContenedorDTO> getContenedores() {
        return contenedores;
    }

    public void setContenedores(List<ContenedorDTO> contenedores) {
        this.contenedores = contenedores;
    }

    public boolean isIsregEditable() {
        return isregEditable;
    }

    public void setIsregEditable(boolean isregEditable) {
        this.isregEditable = isregEditable;
    }

    public boolean isContabEditable() {
        return contabEditable;
    }

    public void setContabEditable(boolean contabEditable) {
        this.contabEditable = contabEditable;
    }

    public Map<Integer, Integer> getHmap() {
        return hmap;
    }

    public void setHmap(Map<Integer, Integer> hmap) {
        this.hmap = hmap;
    }

    public List<ContenedorDTO> getFiltroContenedor() {
        return filtroContenedor;
    }

    public void setFiltroContenedor(List<ContenedorDTO> filtroContenedor) {
        this.filtroContenedor = filtroContenedor;
    }

    public ContabDTO getContabSeleccionado() {
        return contabSeleccionado;
    }

    public void setContabSeleccionado(ContabDTO contabSeleccionado) {
        this.contabSeleccionado = contabSeleccionado;
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

    public boolean isConparEditable() {
        return conparEditable;
    }

    public void setConparEditable(boolean conparEditable) {
        this.conparEditable = conparEditable;
    }

    public ConparDTO getConparSeleccionado() {
        return conparSeleccionado;
    }

    public void setConparSeleccionado(ConparDTO conparSeleccionado) {
        this.conparSeleccionado = conparSeleccionado;
    }

    public int getDefaultTabIndex() {
        return defaultTabIndex;
    }

    public void setDefaultTabIndex(int defaultTabIndex) {
        this.defaultTabIndex = defaultTabIndex;
    }
}
