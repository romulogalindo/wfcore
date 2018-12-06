
package com.acceso.wfcore.daos;

import com.acceso.wfcore.dtos.ConexionDTO;
import com.wf.utils.RankanaUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;

/**
 *
 * @author Mario Huillca <mario.huillca@acceso.com.pe>
 * Created on 30 nov. 2018, 15:14:24
 */
public class ConexionDAO {
    public ConexionDAO() {
    }

    public List<ConexionDTO> getConexiones() {

        StatelessSession session = RankanaUtil.getSessionFactory().openStatelessSession();
        List<ConexionDTO> conexiones = null;

        try {
            System.out.println("com.wf.daos.ConexionDAO.getConexiones() --> Inicio");
            String sql_name = "wfcore.cnx_test";

            Query query = session.getNamedQuery(sql_name);

            conexiones = query.list();
            System.out.println("com.wf.daos.ConexionDAO.getConexiones() --> Fin");
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return conexiones;
    }
    
    public ConexionDTO insertConexion(ConexionDTO conexion) {

        StatelessSession session = RankanaUtil.getSessionFactory().openStatelessSession();
        ConexionDTO conexiones = null;

        try {
            System.out.println("com.wf.daos.ConexionDAO.insertConexion() --> Inicio");
            String sql_name = "wfcore.cnx_insert";
            System.out.println("conexion = " + conexion);
            Query query = session.getNamedQuery(sql_name);
            query.setString("no_conexi", conexion.getNo_conexi());
            query.setInteger("nu_maxpoo", conexion.getNu_maxpoo());
            query.setInteger("nu_timout", conexion.getNu_timout());
            query.setString("no_usuari", conexion.getNo_usuari());
            query.setString("pw_usuari", conexion.getPw_usuari());
            query.setString("ur_domini", conexion.getUr_domini());
            query.setInteger("nu_puerto", conexion.getNu_puerto());
            query.setString("no_datbas", conexion.getNo_datbas());
            
            conexiones = (ConexionDTO) query.list().get(0);
            System.out.println("com.wf.daos.ConexionDAO.insertConexion() --> Fin");
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return conexiones;
    }
    public ConexionDTO updateConexion(ConexionDTO conexion) {

        StatelessSession session = RankanaUtil.getSessionFactory().openStatelessSession();
        ConexionDTO conexiones = null;

        try {
            System.out.println("com.wf.daos.ConexionDAO.updateConexion() --> Inicio");
            String sql_name = "wfcore.cnx_update";
            System.out.println("conexion = " + conexion);
            Query query = session.getNamedQuery(sql_name);
            
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
            System.out.println("com.wf.daos.ConexionDAO.updateConexion() --> Fin");
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return conexiones;
    }
    
    public String deleteConexion(ConexionDTO conexion) {

        StatelessSession session = RankanaUtil.getSessionFactory().openStatelessSession();
        String resultado = null;

        try {
            System.out.println("com.wf.daos.ConexionDAO.deleteConexion() --> Inicio");
            String sql_name = "wfcore.cnx_delete";
            System.out.println("conexion = " + conexion);
            Query query = session.getNamedQuery(sql_name);
            
            query.setInteger("co_conexi", conexion.getCo_conexi());
            
            resultado = query.list().toString();
            System.out.println("com.wf.daos.ConexionDAO.deleteConexion() --> Fin");
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }
    
}
