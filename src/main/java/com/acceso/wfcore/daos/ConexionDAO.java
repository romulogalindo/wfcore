package com.acceso.wfcore.daos;

import com.acceso.wfcore.dtos.ConexionDTO;
import com.acceso.wfcore.listerners.WFCoreListener;
import com.acceso.wfcore.utils.Values;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;

/**
 * @author Mario Huillca <mario.huillca@acceso.com.pe>
 * Created on 30 nov. 2018, 15:14:24
 */
public class ConexionDAO {
    StatelessSession session;

    public ConexionDAO() {
        session = WFCoreListener.dataSourceService.getMainManager().getNativeSession();
    }

    public List<ConexionDTO> getConexiones() {

        List<ConexionDTO> conexiones = new ArrayList<>();

        try {
            System.out.println("com.wf.daos.ConexionDAO.getConexiones() --> Inicio");

            Query query = session.getNamedQuery(Values.QUERYS_NATIVE_SELECT_CNX);

            conexiones = query.list();
            System.out.println("com.wf.daos.ConexionDAO.getConexiones() --> Fin");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return conexiones;
    }

    public ConexionDTO grabarConexion(ConexionDTO conexion) {

        StatelessSession session = WFCoreListener.dataSourceService.getMainManager().getNativeSession();
        ConexionDTO conexiones = null;

        try {
            System.out.println("ConexionDAO.grabarConexion() --> Inicio");

            System.out.println("conexion = " + conexion);
            Query query = session.getNamedQuery(Values.QUERYS_NATIVE_GRABAR_CNX);

            query.setInteger("co_conexi", conexion.getCo_conexi());
            query.setString("no_conexi", conexion.getNo_conexi());
            query.setInteger("nu_maxpoo", conexion.getNu_maxpoo());
            query.setInteger("nu_timout", conexion.getNu_timout());
            query.setString("no_usuari", conexion.getNo_usuari());
            query.setString("pw_usuari", conexion.getPw_usuari());
            query.setString("ur_domini", conexion.getUr_domini());
            query.setInteger("nu_puerto", conexion.getNu_puerto());
            query.setString("no_datbas", conexion.getNo_datbas());

            conexiones = (ConexionDTO) query.list().get(0);
            System.out.println("ConexionDAO.grabarConexion() --> Fin");
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return conexiones;
    }

    public String deleteConexion(ConexionDTO conexion) {

        StatelessSession session = WFCoreListener.dataSourceService.getMainManager().getNativeSession();
        String resultado = null;

        try {
            System.out.println("com.wf.daos.ConexionDAO.deleteConexion() --> Inicio");
            System.out.println("conexion = " + conexion);
            Query query = session.getNamedQuery(Values.QUERYS_NATIVE_DELETE_CNX);

            query.setInteger("co_conexi", conexion.getCo_conexi());

            resultado = query.list().toString();
            System.out.println("com.wf.daos.ConexionDAO.deleteConexion() --> Fin");
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }

    public void close() {
        try {
            session.close();
        } catch (Exception ep) {
            session = null;
        }
    }

}
