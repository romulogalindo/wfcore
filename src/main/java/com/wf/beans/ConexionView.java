package com.wf.beans;

import com.wf.daos.ConexionDAO;
import com.wf.dtos.ConexionDTO;
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
public class ConexionView {
    
    private List<ConexionDTO> conexiones;
    private ConexionDTO conexion;

    public ConexionView() {
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
        System.out.println("dao = " + this.conexiones);

        return this.conexiones;
    }
    
    public void grabarConexion() {
        ConexionDAO dao = new ConexionDAO();
        this.conexion = dao.insertConexion(conexion);
        System.out.println("dao = " + this.conexion);
    }
    
    public void limpiarRegistro() {
        System.out.println("limpiarRegistro = " + this.conexion);
        this.conexion = new ConexionDTO();
    }
}
