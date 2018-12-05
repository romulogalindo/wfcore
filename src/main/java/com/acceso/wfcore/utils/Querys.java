package com.acceso.wfcore.utils;

import com.acceso.wfcore.dtos.ConexionDTO;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.StatelessSession;

/**
 *
 * @author Rómulo Galindo<romulogalindo@gmail.com>
 * Created on 4 dic. 2018, 19:14:56
 */
public class Querys {

    public static List<ConexionDTO> getManagers(StatelessSession statelessSession) {
        List<ConexionDTO> list = new ArrayList<>();
        NQuery nQuery = new NQuery();

        try {
            nQuery.work(statelessSession.getNamedQuery(Values.QUERYS_NATIVE_GET_ALLCNX));
            list = nQuery.list();
        } catch (Exception ep) {
            System.out.println("Error al ejecutar la query.");
        } finally {
            try {
                statelessSession.close();
                statelessSession = null;
            } catch (Exception ep) {
                System.out.println("Error cerrando conexion forzando null al objeto");
            }
        }

        return list;
    }
}
