package com.acceso.wfcore.apis;

import com.acceso.wfcore.kernel.ApplicationManager;
import com.acceso.wfcore.listerners.WFCoreListener;
import com.acceso.wfcore.utils.Util;
import com.acceso.wfcore.utils.ValpagJson;
import com.acceso.wfweb.dtos.PropagDTO;
import com.acceso.wfweb.dtos.ValpagDTO;
import com.acceso.wfweb.utils.JsonResponse;
import com.google.gson.Gson;
import org.hibernate.StatelessSession;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import java.sql.SQLException;
import java.util.*;

public class DataAPI extends GenericAPI {

    //public List<Map<String, Object>> SQL(String conectionName, String sqlQuery) {
    public String SQL(String conectionName, String sqlQuery) {
        long execution_time;
        JsonResponse jsonResponse = new JsonResponse();
        List<Map<String, Object>> valReturn = new ArrayList<>();
        StatelessSession session = null;

        execution_time = System.currentTimeMillis();
        try {
            session = WFCoreListener.APP.getDataSourceService().getManager(conectionName).getNativeSession();

//            NativeQuery sql = session.createNativeQuery(sqlQuery);
            Query sql = session.createNativeQuery(sqlQuery);
            sql.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);

            System.out.println("[@" + conectionName + "] Q = " + sqlQuery);

            valReturn = sql.getResultList();

            System.out.println("[@" + conectionName + "] Q = " + sqlQuery + " T = " + (System.currentTimeMillis() - execution_time) + "ms");

            session.close();
            jsonResponse.setStatus(JsonResponse.OK);
            jsonResponse.setResult(valReturn);

        } catch (Exception ep) {
            System.out.println("[1]ep = " + ep);

            if (session != null) {
                session = null;
            }

            jsonResponse.setStatus(JsonResponse.ERROR);
            jsonResponse.setResult(null);
            jsonResponse.setError(Util.getError(ep));

            System.out.println("[@" + conectionName + "] Q = " + sqlQuery + "e=" + jsonResponse.getMessage() + ": E1 = " + ep.getMessage() + "");
            //throw ep;
            ep.printStackTrace();
        }

        return new Gson().toJson(jsonResponse);
    }

    public ValpagJson VALPAG_LEGACY(String conectionName, String sqlQuery) throws Exception {

        long execution_time = System.currentTimeMillis();
        List<ValpagDTO> valReturn;
        StatelessSession session = null;

        try {
            session = WFCoreListener.APP.getDataSourceService().getManager(conectionName).getNativeSession();

            System.out.println("[@" + conectionName + "] Q = " + sqlQuery);

            NativeQuery sql = session.createNativeQuery(sqlQuery).addEntity(ValpagDTO.class);
            valReturn = sql.getResultList();

            System.out.println("[@" + conectionName + "] Q = " + sqlQuery + " T = " + (System.currentTimeMillis() - execution_time) + "ms");

            session.close();
        } catch (Exception ep) {
            valReturn = new ArrayList<>();

            if (session != null) {
                session = null;
            }

            System.out.println("[@" + conectionName + "] Q = " + sqlQuery + " E = " + ep.getMessage() + "");
            throw ep;
        }

        return ApplicationManager.buildNValPag(valReturn);
    }

}
