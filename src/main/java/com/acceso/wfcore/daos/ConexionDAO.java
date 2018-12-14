package com.acceso.wfcore.daos;

import com.acceso.wfcore.dtos.ConexionDTO;
import com.acceso.wfcore.listerners.WFCoreListener;
import com.acceso.wfcore.utils.NQuery;
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
        NQuery nQuery = new NQuery();

        try {

            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_SELECT_CNX));

            System.out.println("[ConexionDAO:getConexiones] Q = " + nQuery.getQueryString());
            conexiones = nQuery.list();
            System.out.println("[ConexionDAO:getConexiones] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");

        } catch (Exception ep) {
            System.out.println("[ConexionDAO:getConexiones] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return conexiones;
    }

    public ConexionDTO grabarConexion(ConexionDTO conexion) {

        NQuery nQuery = new NQuery();

        try {
            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_GRABAR_CNX));

            nQuery.setInteger("co_conexi", conexion.getCo_conexi() == null ? -1 : conexion.getCo_conexi());
            nQuery.setString("no_conexi", conexion.getNo_conexi());
            nQuery.setInteger("nu_maxpoo", conexion.getNu_maxpoo());
            nQuery.setInteger("nu_timout", conexion.getNu_timout());
            nQuery.setString("no_usuari", conexion.getNo_usuari());
            nQuery.setString("pw_usuari", conexion.getPw_usuari());
            nQuery.setString("ur_domini", conexion.getUr_domini());
            nQuery.setInteger("nu_puerto", conexion.getNu_puerto());
            nQuery.setString("no_datbas", conexion.getNo_datbas());

            System.out.println("[ConexionDAO:getConexiones] Q = " + nQuery.getQueryString());

            conexion = (ConexionDTO) nQuery.list().get(0);

            System.out.println("[ConexionDAO:getConexiones] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return conexion;
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
