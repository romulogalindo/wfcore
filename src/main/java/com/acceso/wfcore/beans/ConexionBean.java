package com.acceso.wfcore.beans;

import com.acceso.wfcore.daos.ConexionDAO;
import com.acceso.wfcore.dtos.ConexionDTO;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Mario Huillca <mario.huillca@acceso.com.pe>
 * Created on 30 nov. 2018, 15:54:46
 */
@ManagedBean//(name = "dtBasicView")
@SessionScoped
public class ConexionBean implements Serializable {

    private List<ConexionDTO> conexiones;

    private ConexionDTO conexion;

    private static final String urlLista = "/pagex/paginaConexiones.xhtml";

    public ConexionBean() {
        conexion = new ConexionDTO();
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

    public List<ConexionDTO> listarConexiones() {
        ConexionDAO dao = new ConexionDAO();
        this.conexiones = dao.getConexiones();
        System.out.println("dao listarConexiones= " + this.conexiones);

        return this.conexiones;
    }

    public String grabarConexion() {
        ConexionDAO dao = new ConexionDAO();
        this.conexion = dao.insertConexion(conexion);
        System.out.println("dao grabarConexion = " + this.conexion);
        this.conexion = new ConexionDTO();

        return urlLista;
    }

    public String actualizarConexion() {
        ConexionDAO dao = new ConexionDAO();
        this.conexion = dao.updateConexion(conexion);
        this.conexiones = dao.getConexiones();
        System.out.println("dao actualizarConexion = " + this.conexion);

        return urlLista;
    }

    public String eliminarConexion() {
        ConexionDAO dao = new ConexionDAO();
        String resultado = new String();
        resultado = dao.deleteConexion(conexion);
        this.conexiones = dao.getConexiones();
        System.out.println("dao eliminarConexion = " + resultado);

        return urlLista;
    }

    public void limpiarRegistro() {
        System.out.println("limpiarRegistro limpiarRegistro= " + this.conexion);
        this.conexion = new ConexionDTO();
    }
}
