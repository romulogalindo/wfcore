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
            nQuery.work(statelessSession.getNamedQuery(Values.SYSQUERYS_NATIVE_GET_ALLCNX));
            System.out.println("Q:" + nQuery.getQueryString());
            list = nQuery.list();
        } catch (Exception ep) {
            System.out.println("Error al ejecutar la query.");
            ep.printStackTrace();
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
