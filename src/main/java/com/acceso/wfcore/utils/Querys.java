package com.acceso.wfcore.utils;

import com.acceso.wfcore.dtos.ConexionDTO;
import com.acceso.wfcore.kernel.WFIOAPP;
import com.acceso.wfcore.listerners.WFCoreListener;
import com.acceso.wfcore.log.Log;
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
        long execution_time;

        try {
            nQuery.work(statelessSession.getNamedQuery(Values.SYSQUERYS_NATIVE_GET_ALLCNX));
            if (WFIOAPP.APP.SHOW_PREQUERY) {
                Log.info("Q = " + nQuery.getQueryString());
            }
            execution_time = System.currentTimeMillis();
            list = nQuery.list();
            execution_time = System.currentTimeMillis() - execution_time;

            Log.info("Q = " + nQuery.getQueryString() + " T = " + execution_time + "ms");
        } catch (Exception ep) {
            Log.error("getManagers@Querys :" + ep.getMessage());
            if (WFIOAPP.APP.THROWS_EXCEPTION) {
                ep.printStackTrace();
            }
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
