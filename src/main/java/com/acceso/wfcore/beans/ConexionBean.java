package com.acceso.wfcore.beans;

import com.acceso.wfcore.daos.ConexionDAO;
import com.acceso.wfcore.dtos.ConexionDTO;
import com.acceso.wfcore.kernel.ApplicationManager;
import com.acceso.wfcore.kernel.WFIOAPP;
import com.acceso.wfcore.managers.DataManager;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 * @author Mario Huillca <mario.huillca@acceso.com.pe>
 * Created on 30 nov. 2018, 15:54:46
 */
@ManagedBean
@SessionScoped
public class ConexionBean extends MainBean implements Serializable, DefaultMaintenceWeb, DefaultMaintenceDao {

    private static final String URL_LISTA = "/admin/jsf_exec/pagex/conexion/paginaConexiones.xhtml";
    private static final String URL_DETALLE = "/admin/jsf_exec/pagex/conexion/paginaConexiones.xhtml";
    private static final String URL_EDITAR = "/admin/jsf_exec/pagex/conexion/paginaRegConexion.xhtml";
    private static final String URL_NEW = "/admin/jsf_exec/pagex/conexion/paginaRegConexion.xhtml";

    public static final String BEAN_NAME = "conexionBean";

    private List<ConexionDTO> conexiones;
    private ConexionDTO conexion;

    private boolean isregEditable;

    private SelectItem[] ls_ti_conexi;

    public ConexionBean() {
        this.beanName = BEAN_NAME;
        this.titleName = "Conexiones";
        this.conexion = new ConexionDTO();
        this.isregEditable = true;

        //crear y cargar la lista de tipo de conexiones
        ls_ti_conexi = new SelectItem[4];
        ls_ti_conexi[0] = new SelectItem("","");
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
        this.conexion = new ConexionDTO();
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
        this.conexiones = dao.getConexiones();
        dao.close();
    }

    @Override
    public void saveDto() {
        ConexionDAO dao = new ConexionDAO();
        this.conexion = dao.grabarConexion(conexion);
        this.conexiones = dao.getConexiones();
        dao.close();
    }

    @Override
    public void updateDto() {
        ConexionDAO dao = new ConexionDAO();
        this.conexion = dao.grabarConexion(conexion);
        this.conexiones = dao.getConexiones();
        dao.close();
    }

    @Override
    public void deleteDto() {
        ConexionDAO dao = new ConexionDAO();
        String resultado = dao.deleteConexion(conexion);
        this.conexiones = dao.getConexiones();
        dao.close();
    }

    /*Prueba la conexión a la base de datos*/
    public void testConexion() {
        boolean valid = false;
        String message = "TEST OK";

        try {

            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://" + conexion.getUr_domini() + ":" + conexion.getNu_puerto() + "/" + conexion.getNo_datbas(), conexion.getNo_usuari(), conexion.getPw_usuari());

            valid = connection.isValid(1000 * 10);

        } catch (Exception ep) {
            if (ep instanceof java.sql.SQLException) {
                message = "TEST FALLÓ: [" + ((java.sql.SQLException) ep).getErrorCode() + "] " + ((java.sql.SQLException) ep).getMessage();
            } else {
                message = "TEST FALLÓ: " + ep.getMessage();
            }
        }

        FacesContext context = FacesContext.getCurrentInstance();
        if (valid) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK!", message));
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "FALLO!", message));
        }

    }

    public void aplicarCambios() {
        /*con todo el input*/
        System.out.println("Creando datamanager!");
        DataManager dataManager = new DataManager(conexion.getNo_conexi(), "hibdata.cfg.xml", ApplicationManager.buildDefaultProperties(conexion));

        System.out.println("empieza el cambio");
        WFIOAPP.APP.getDataSourceService().getManager(conexion.getNo_conexi()).terminate();
        System.out.println("cerrando antigua conexion");
        WFIOAPP.APP.getDataSourceService().setManager(conexion.getNo_conexi(), dataManager);
        System.out.println("poniendo nueva conexion");
        dataManager.init();
        System.out.println("conexion inicializada");
    }

    public ConexionDTO getConexion() {
        return conexion;
    }

    public void setConexion(ConexionDTO conexion) {
        this.conexion = conexion;
    }

    public List<ConexionDTO> getConexiones() {
        for (ConexionDTO conexionDTO : conexiones) {
//            Statistics statistics = WFIOAPP.APP.dataSourceService.getManager(conexionDTO.getNo_conexi()).getFactory().getStatistics();
//            System.out.println("statistics.isStatisticsEnabled() = " + statistics.isStatisticsEnabled());
//            System.out.println("statistics.getSessionOpenCount() = " + statistics.getSessionOpenCount());
//            System.out.println("statistics.getSessionCloseCount() = " + statistics.getSessionCloseCount());
//            System.out.println("statistics.getQueries() = " + statistics.getQueries());
//            System.out.println("statistics.getConnectCount() = " + statistics.getConnectCount());
            if (WFIOAPP.APP.dataSourceService.getManager(conexionDTO.getNo_conexi()) == null) {
                //crear el manager
                conexionDTO.setIl_conexi(false);
            } else {
                try {
                    conexionDTO.setIl_conexi(WFIOAPP.APP.dataSourceService.getManager(conexionDTO.getNo_conexi()).getStatus() == DataManager.ACTIVE);
                } catch (Exception ep) {
                    System.out.println("GET:CNX-->" + ep.getMessage());
                    ep.printStackTrace();
                }
            }

        }

        return conexiones;
    }

    public void setConexiones(List<ConexionDTO> conexiones) {
        this.conexiones = conexiones;
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
