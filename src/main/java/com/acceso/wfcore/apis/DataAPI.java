package com.acceso.wfcore.apis;

import com.acceso.wfcore.kernel.ApplicationManager;
import com.acceso.wfcore.listerners.WFCoreListener;
import com.acceso.wfcore.utils.ValpagJson;
import com.acceso.wfweb.dtos.ValpagDTO;
import com.google.gson.Gson;
import org.hibernate.StatelessSession;
import org.hibernate.query.NativeQuery;

import java.util.ArrayList;
import java.util.List;

public class DataAPI extends GenericAPI {

    public String SQL(String conectionName, String sqlQuery) {
        String sqlResult = "{}";
        Gson gson = new Gson();
        StatelessSession session = WFCoreListener.APP.getDataSourceService().getManager(conectionName).getNativeSession();
        NativeQuery sql = session.createNativeQuery(sqlQuery);
        List<Object[]> rows = sql.list();
        session.close();
        sqlResult = gson.toJson(rows);

        return sqlResult;
    }

    public List<ValpagDTO> SQL_LEGACY(String conectionName, String sqlQuery) throws Exception {
        long execution_time;
        List<ValpagDTO> valReturn = new ArrayList<>();
        StatelessSession session = null;

        execution_time = System.currentTimeMillis();
        try {
            session = WFCoreListener.APP.getDataSourceService().getManager(conectionName).getNativeSession();

            System.out.println("[@" + conectionName + "] Q = " + sqlQuery);

            NativeQuery sql = session.createNativeQuery(sqlQuery).addEntity(ValpagDTO.class);
            valReturn = sql.list();

            System.out.println("[@" + conectionName + "] Q = " + sqlQuery + " T = " + (System.currentTimeMillis() - execution_time) + "ms");

            session.close();
        } catch (Exception ep) {

            if (session != null) {
                session = null;
            }

            System.out.println("[@" + conectionName + "] Q = " + sqlQuery + " E = " + ep.getMessage() + "");
            throw ep;
        }

        return valReturn;
    }

    public ValpagJson JSON_VALPAG(List<ValpagDTO> valpagDTOS) {
        ValpagJson valpagJson = ApplicationManager.buildNValPag(valpagDTOS);
        return valpagJson;
    }
}
