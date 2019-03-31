package com.acceso.wfcore.apis;

import com.acceso.wfcore.kernel.ApplicationManager;
import com.acceso.wfcore.listerners.WFCoreListener;
import com.acceso.wfcore.utils.ErrorMessage;
import com.acceso.wfcore.utils.Util;
import com.acceso.wfcore.utils.ValpagJson;
import com.acceso.wfweb.dtos.ValpagDTO;
import com.acceso.wfweb.utils.JsonResponse;
import com.google.gson.Gson;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataAPI extends GenericAPI {

    public String SQL(String conectionName, String sqlQuery) {
        return SQL(conectionName, sqlQuery, 30);
    }

    public String SQL(String conectionName, String sqlQuery, int timeoutseg) {
        long execution_time;
        JsonResponse jsonResponse = new JsonResponse();
        List<Map<String, Object>> valReturn;
        StatelessSession session = null;
        Transaction transaction = null;
        execution_time = System.currentTimeMillis();

        try {
            session = WFCoreListener.APP.getDataSourceService().getManager(conectionName).getNativeSession();
            transaction = session.beginTransaction();
            transaction.setTimeout(timeoutseg);

            Query sql = session.createNativeQuery(sqlQuery);
            sql.setTimeout(timeoutseg);
            sql.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);

            System.out.println("[@" + conectionName + "] Q = " + sqlQuery);

            valReturn = sql.getResultList();

            System.out.println("[@" + conectionName + "] Q = " + sqlQuery + " T = " + (System.currentTimeMillis() - execution_time) + "ms");
            transaction.commit();
            session.close();
            jsonResponse.setStatus(JsonResponse.OK);
            jsonResponse.setResult(valReturn);

        } catch (Exception ep) {
            System.out.println("[1]ep = " + ep);

            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (Exception ep2) {
                transaction = null;
            }

            if (session != null) {
                session = null;
            }

            ErrorMessage errormessage = Util.getError(ep);
            if (errormessage == null) {
                jsonResponse.setStatus(JsonResponse.OK);
                jsonResponse.setResult("[]");
                jsonResponse.setError(null);
            } else {
                jsonResponse.setStatus(JsonResponse.ERROR);
                jsonResponse.setResult(null);
                jsonResponse.setError(Util.getError(ep));
            }

            System.out.println("[@" + conectionName + "] Q = " + sqlQuery + "e=" + jsonResponse.getMessage() + ": E1 = " + ep.getMessage() + "");

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
