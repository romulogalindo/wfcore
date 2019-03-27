package com.acceso.wfcore.beans;

import com.acceso.wfcore.daos.PaqueteDAO;
import com.acceso.wfcore.daos.SubSistemaDAO;
import com.acceso.wfcore.dtos.PaqueteDTO;
import com.acceso.wfcore.dtos.SubSistemaDTO;

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
public class PaqueteBean extends MainBean implements Serializable, DefaultMaintenceWeb, DefaultMaintenceDao {

    private static final String URL_LISTA = "/admin/jsf_exec/pagex/paquete/paginaPaquetes.xhtml";
    private static final String URL_DETALLE = "/admin/jsf_exec/pagex/paquete/paginaPaquetes.xhtml";
    private static final String URL_EDITAR = "/admin/jsf_exec/pagex/paquete/paginaRegPaquete.xhtml";
    private static final String URL_NEW = "/admin/jsf_exec/pagex/paquete/paginaRegPaquete.xhtml";

    public static final String BEAN_NAME = "paqueteBean";

    private List<PaqueteDTO> paquetes;
    private PaqueteDTO paquete;

    private boolean isregEditable;

    private List<SelectItem> lstSubSistemabySistema;

    public PaqueteBean() {
        this.beanName = BEAN_NAME;
        this.titleName = "Paquetes";
        this.paquete = new PaqueteDTO();
        this.isregEditable = true;
    }

    public void onSistemaChange(Integer co_sistem) {
        this.lstSubSistemabySistema = new ArrayList<>();
        List<SubSistemaDTO> subsistemas;
        SubSistemaDAO dao = new SubSistemaDAO();
        subsistemas = dao.getSubSistemas();
        if (co_sistem == 0) {
            for (SubSistemaDTO sis : subsistemas) {
                SelectItem item = new SelectItem();
                item.setLabel(sis.getNo_subsis());
                item.setDescription(sis.getNo_subsis());
                item.setValue(sis.getCo_subsis());
                this.lstSubSistemabySistema.add(item);
            }
        } else {
            for (SubSistemaDTO sis : subsistemas) {
                if (sis.getCo_sistem() == co_sistem) {
                    SelectItem item = new SelectItem();
                    item.setLabel(sis.getNo_subsis());
                    item.setDescription(sis.getNo_subsis());
                    item.setValue(sis.getCo_subsis());
                    this.lstSubSistemabySistema.add(item);
                }
            }
        }
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
        this.paquete = new PaqueteDTO();
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

        // CARGAMOS COMBOS DE SUBSISTEMA
        onSistemaChange(0);
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
        PaqueteDAO dao = new PaqueteDAO();
        this.paquetes = dao.getPaquetes();
        dao.close();
    }

    @Override
    public void saveDto() {
        PaqueteDAO dao = new PaqueteDAO();
        this.paquete = dao.grabarPaquete(paquete);
        this.paquetes = dao.getPaquetes();
//      System.out.println("PaqueteBean actualizarPaquete = " + this.paquete);
        dao.close();
    }

    @Override
    public void updateDto() {
        PaqueteDAO dao = new PaqueteDAO();
        this.paquete = dao.grabarPaquete(paquete);
        this.paquetes = dao.getPaquetes();
//      System.out.println("PaqueteBean actualizarPaquete = " + this.paquete);
        dao.close();
    }

    @Override
    public void deleteDto() {
        PaqueteDAO dao = new PaqueteDAO();
        String resultado = dao.deletePaquete(paquete);
        this.paquetes = dao.getPaquetes();
        dao.close();
    }

    public List<PaqueteDTO> getPaquetes() {
        return paquetes;
    }

    public void setPaquetes(List<PaqueteDTO> paquetes) {
        this.paquetes = paquetes;
    }

    public PaqueteDTO getPaquete() {
        return paquete;
    }

    public void setPaquete(PaqueteDTO paquete) {
        this.paquete = paquete;
    }

    public boolean isIsregEditable() {
        return isregEditable;
    }

    public void setIsregEditable(boolean isregEditable) {
        this.isregEditable = isregEditable;
    }

    public List<SelectItem> getLstSubSistemabySistema() {
        return lstSubSistemabySistema;
    }

    public void setLstSubSistemabySistema(List<SelectItem> lstSubSistemabySistema) {
        this.lstSubSistemabySistema = lstSubSistemabySistema;
    }
}
