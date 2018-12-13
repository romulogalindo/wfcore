package com.acceso.wfcore.beans;

import com.acceso.wfcore.daos.ConexionDAO;
import com.acceso.wfcore.dtos.ConexionDTO;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 * @author Mario Huillca <mario.huillca@acceso.com.pe>
 * Created on 30 nov. 2018, 15:54:46
 */
@ManagedBean
@SessionScoped
public class ConexionBean extends MainBean implements Serializable {

    private static final String URL_LISTA = "/admin/jsf_exec/pagex/paginaConexiones.xhtml";
    private static final String URL_DETALLE = "/admin/jsf_exec/pagex/paginaConexiones.xhtml";
    private static final String URL_EDITAR = "/admin/jsf_exec/pagex/paginaUpdConexion.xhtml";
    private static final String URL_NEW = "/admin/jsf_exec/pagex/paginaRegConexion.xhtml";


    private List<ConexionDTO> conexiones;

    private ConexionDTO conexion;


    public ConexionBean() {
        this.beanName = "Conexiones";
        this.conexion = new ConexionDTO();
    }

    public ConexionDTO getConexion() {
        return conexion;
    }

    public void setConexion(ConexionDTO conexion) {
        this.conexion = conexion;
    }

    public List<ConexionDTO> getConexiones() {
        return conexiones;
    }

    public void setConexiones(List<ConexionDTO> conexiones) {
        this.conexiones = conexiones;
    }

    public String grabarConexion() {
        ConexionDAO dao = new ConexionDAO();
        this.conexion = dao.insertConexion(conexion);
        System.out.println("dao grabarConexion = " + this.conexion);
        this.conexion = new ConexionDTO();

        return URL_LISTA;
    }

    public String loadActualizarConexion() {
        //acceder al manager y decirle toma
        FacesContext context = FacesContext.getCurrentInstance();
        ((ManagerBean) context.getApplication().getVariableResolver().resolveVariable(context, "managerBean")).updateBreadCumBar("Editar", URL_EDITAR);

        return URL_EDITAR;
    }

    public String actualizarConexion() {
        ConexionDAO dao = new ConexionDAO();
        this.conexion = dao.updateConexion(conexion);
        this.conexiones = dao.getConexiones();
        System.out.println("dao actualizarConexion = " + this.conexion);

        return URL_LISTA;
    }

    public String eliminarConexion() {
        ConexionDAO dao = new ConexionDAO();
        String resultado = new String();
        resultado = dao.deleteConexion(conexion);
        this.conexiones = dao.getConexiones();
        System.out.println("dao eliminarConexion = " + resultado);

        return URL_LISTA;
    }

    public void limpiarRegistro() {
        System.out.println("limpiarRegistro limpiarRegistro= " + this.conexion);
        this.conexion = new ConexionDTO();
    }

    public void loadlista() {
        ConexionDAO dao = new ConexionDAO();
        this.conexiones = dao.getConexiones();
        dao.close();

    }

    public void action() {

        System.out.println("action()");
    }

    public void listener() {
        //acceder al manager y decirle toma
        FacesContext context = FacesContext.getCurrentInstance();
        ((ManagerBean) context.getApplication().getVariableResolver().resolveVariable(context, "managerBean")).setRenderedMenuButton(true);
        ((ManagerBean) context.getApplication().getVariableResolver().resolveVariable(context, "managerBean")).initBreadCumBar();
        ((ManagerBean) context.getApplication().getVariableResolver().resolveVariable(context, "managerBean")).setCurrentBean(this);
        ((ManagerBean) context.getApplication().getVariableResolver().resolveVariable(context, "managerBean")).updateBreadCumBar(beanName, URL_LISTA);

        System.out.println("listener()");
    }


    @Override
    public String load() {
        System.out.println("load()");
        listener();
        //CARGA INICIAL!!
        loadlista();

        return URL_LISTA;
    }

    @Override
    public String getBeanName() {
        return beanName;
    }

    @Override
    public String defaultAction() {
        //varias cosas para editar
        return URL_NEW;
    }
}
