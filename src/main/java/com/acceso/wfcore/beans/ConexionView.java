package com.acceso.wfcore.beans;

import com.acceso.wfcore.daos.ConexionDAO;
import com.acceso.wfcore.dtos.ConexionDTO;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Mario Huillca <mario.huillca@acceso.com.pe>
 * Created on 30 nov. 2018, 15:54:46
 */
@ManagedBean(name = "dtBasicView")
@SessionScoped
public class ConexionView implements Serializable {

    private List<ConexionDTO> conexiones;

    private ConexionDTO conexion;

    public ConexionView() {
        conexion = new ConexionDTO();
    }
    
    @PostConstruct
    public void init() {
        conexiones = listarConexiones();
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
        System.out.println("dao = " + this.conexiones);

        return this.conexiones;
    }

    public void grabarConexion() {
        ConexionDAO dao = new ConexionDAO();
        this.conexion = dao.insertConexion(conexion);
        System.out.println("dao = " + this.conexion);
        this.conexion = new ConexionDTO();
    }

    public void limpiarRegistro() {
        System.out.println("limpiarRegistro = " + this.conexion);
        this.conexion = new ConexionDTO();
    }
}
