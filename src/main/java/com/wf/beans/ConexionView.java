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

    public ConexionView() {

    }

    public List<ConexionDTO> listarConexiones() {
        ConexionDAO dao = new ConexionDAO();
        this.conexiones = dao.getConexiones();
        System.out.println("dao = " + this.conexiones);

        return this.conexiones;
    }
}
