
package com.wf.daos;

import com.wf.dtos.ConexionDTO;
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
    
}
