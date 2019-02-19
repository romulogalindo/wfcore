package com.acceso.wfcore.apis;

import com.acceso.wfcore.kernel.ApplicationManager;
import com.acceso.wfcore.listerners.WFCoreListener;
import com.acceso.wfcore.utils.ValpagJson;
import com.acceso.wfweb.dtos.PropagDTO;
import com.acceso.wfweb.dtos.ValpagDTO;
import com.google.gson.Gson;
import org.hibernate.StatelessSession;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import java.util.*;

public class DataAPI extends GenericAPI {

    public List<Map<String, Object>> SQL(String conectionName, String sqlQuery) {
        long execution_time;
        List<Map<String, Object>> valReturn = new ArrayList<>();
        StatelessSession session = null;

        execution_time = System.currentTimeMillis();
        try {
            session = WFCoreListener.APP.getDataSourceService().getManager(conectionName).getNativeSession();

            NativeQuery sql = session.createNativeQuery(sqlQuery);
            sql.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);

            valReturn = sql.getResultList();

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

    //public List<ValpagDTO> VALPAG_LEGACY(String conectionName, String sqlQuery) throws Exception {
    public ValpagJson VALPAG_LEGACY(String conectionName, String sqlQuery) throws Exception {

        long execution_time;
        List<ValpagDTO> valReturn = new ArrayList<>();
        StatelessSession session = null;

        execution_time = System.currentTimeMillis();
        try {
            session = WFCoreListener.APP.getDataSourceService().getManager(conectionName).getNativeSession();

            System.out.println("[@" + conectionName + "] Q = " + sqlQuery);

            NativeQuery sql = session.createNativeQuery(sqlQuery).addEntity(ValpagDTO.class);

            valReturn = sql.getResultList();
            session.close();
        } catch (Exception ep) {

            if (session != null) {
                session = null;
            }

            System.out.println("[@" + conectionName + "] Q = " + sqlQuery + " E = " + ep.getMessage() + "");
            throw ep;
        }

        return ApplicationManager.buildNValPag(valReturn);
    }

//    public ValpagJson JSON_VALPAG(List<ValpagDTO> valpagDTOS) {
//        ValpagJson valpagJson = ApplicationManager.buildNValPag(valpagDTOS);
//        return valpagJson;
//    }

    //    public PropagDTO SQL_LEGACY(){
//
//    }
//    public PropagJson JSON_PROPAG() {
//
//    }
}
