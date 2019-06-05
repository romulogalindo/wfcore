package com.acceso.wfcore.apis;

import com.acceso.wfcore.kernel.ApplicationManager;
import com.acceso.wfcore.kernel.WFIOAPP;
import com.acceso.wfcore.log.Log;
import com.acceso.wfcore.transa.Transactional;
import com.acceso.wfcore.utils.Converter;
import com.acceso.wfcore.utils.ErrorMessage;
import com.acceso.wfcore.utils.ExcelJson;
import com.acceso.wfcore.utils.Util;
import com.acceso.wfcore.utils.ValpagJson;
import com.acceso.wfcore.utils.Values;
import com.acceso.wfweb.dtos.ValpagDTO;
import com.acceso.wfweb.utils.JsonResponse;
import org.apache.commons.io.FilenameUtils;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataAPI extends GenericAPI {

    String co_usuari;
    String id_sesion;
    String id_frawor;
    String co_conten;
    String co_pagina;
    String no_escena;

    public String getId_frawor() {
        return id_frawor;
    }

    public void setId_frawor(String id_frawor) {
        this.id_frawor = id_frawor;
    }

    public String getCo_pagina() {
        return co_pagina;
    }

    public void setCo_pagina(String co_pagina) {
        this.co_pagina = co_pagina;
    }

    public String getCo_conten() {
        return co_conten;
    }

    public void setCo_conten(String co_conten) {
        this.co_conten = co_conten;
    }

    public String getCo_usuari() {
        return co_usuari;
    }

    public void setCo_usuari(String co_usuari) {
        this.co_usuari = co_usuari;
    }

    public String getId_sesion() {
        return id_sesion;
    }

    public void setId_sesion(String id_sesion) {
        this.id_sesion = id_sesion;
    }

    public String getNo_escena() {
        return no_escena;
    }

    public void setNo_escena(String no_escena) {
        this.no_escena = no_escena;
    }

    /**
     * SQL
     */
    public JsonResponse SQL(String conectionName, String sqlQuery) {
        return SQL(conectionName, sqlQuery, 30);
    }

    /**
     * SQL
     */
    public JsonResponse SQL(String conectionName, String sqlQuery, int timeoutseg) {
        long execution_time;
        JsonResponse jsonResponse = new JsonResponse();
        List<Map<String, Object>> valReturn;
        StatelessSession session = null;
        Transaction transaction = null;
        execution_time = System.currentTimeMillis();

        try {
            session = WFIOAPP.APP.getDataSourceService().getManager(conectionName).getNativeSession();
            transaction = session.beginTransaction();
            transaction.setTimeout(timeoutseg);

            Query sql = session.createNativeQuery(sqlQuery);
            sql.setTimeout(timeoutseg);
            sql.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);

            if (WFIOAPP.APP.SHOW_PREQUERY) {
                Log.info("[U" + getCo_usuari() + "][S" + getId_sesion() + "][F" + getId_frawor() + "][C" + getCo_conten() + "][P" + getCo_pagina() + "][" + getNo_escena() + "] Q = " + sqlQuery);
            }

            Long midt = Transactional.insert(1, Long.parseLong(getCo_usuari()), sqlQuery);
            valReturn = sql.getResultList();

            Log.info("[U" + getCo_usuari() + "][S" + getId_sesion() + "][F" + getId_frawor() + "][C" + getCo_conten() + "][P" + getCo_pagina() + "][" + getNo_escena() + "] Q = " + sqlQuery + " T = " + (System.currentTimeMillis() - execution_time) + "ms");
            transaction.commit();
            session.close();

            Transactional.update(midt);
            jsonResponse.setStatus(JsonResponse.OK);
            jsonResponse.setResult(valReturn);

        } catch (Exception ep) {
            Log.error("[U" + getCo_usuari() + "][S" + getId_sesion() + "][F" + getId_frawor() + "][C" + getCo_conten() + "][P" + getCo_pagina() + "][" + getNo_escena() + "] E = " + ep);

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

            Log.error("[@" + conectionName + "] Q = " + sqlQuery + "e=" + jsonResponse.getError().getMessage() + ": E1 = " + ep.getMessage() + "");

            if (WFIOAPP.APP.THROWS_EXCEPTION) {
                ep.printStackTrace();
            }
        }

        return jsonResponse;
    }

    public ValpagJson VALPAG_LEGACY(String conectionName, String sqlQuery) throws Exception {

        long execution_time = System.currentTimeMillis();
        List<ValpagDTO> valReturn;
        StatelessSession session = null;

        try {
            session = WFIOAPP.APP.getDataSourceService().getManager(conectionName).getNativeSession();

            Log.info("[VALPAG_LEGACY@" + conectionName + "] Q = " + sqlQuery);

            NativeQuery sql = session.createNativeQuery(sqlQuery).addEntity(ValpagDTO.class);
            valReturn = sql.getResultList();

            Log.info("[VALPAG_LEGACY@" + conectionName + "] Q = " + sqlQuery + " T = " + (System.currentTimeMillis() - execution_time) + "ms");

            session.close();

            return ApplicationManager.buildNValPag(valReturn);
        } catch (Exception ep) {
            valReturn = new ArrayList<>();

            if (session != null) {
                session = null;
            }

//            System.out.println("[VALPAG_LEGACY@" + conectionName + "] Q = " + sqlQuery + " E = " + ep.getMessage() + "");
            throw ep;
        }

    }

    /**
     *
     */
    public ExcelJson READ_FROM_FILE(long co_archiv) {
        System.out.println("co_archiv = " + co_archiv);
        File file = (File) WFIOAPP.APP.getCacheService().getZeroDawnCache().getSpace(Values.CACHE_MAIN_FILEX).get("" + co_archiv);
        System.out.println("file = " + file);
        ExcelJson excelJson = null;

        try {
//            file = new File(path);
            String extension = FilenameUtils.getExtension(file.getName()).toUpperCase();
            switch (extension) {
//                case "XLS": {
//                    jsonObject = new Converter(file).XLS_TO_JSON();
//                    break;
//                }
                case "XLSX": {
                    excelJson = new Converter(file).XLSX_TO_JSON();
                    break;
                }
            }
        } catch (Exception ep) {
            return null;
        }

        return excelJson;
    }

    public File CREATE_FILE(String filename, Object result) {
        File file = null;
        return file;
    }

}

//    public String SQLX(String conectionName, String sqlQuery, int timeoutseg) {
//        long execution_time;
//        JsonResponse jsonResponse = new JsonResponse();
//        List<Map<String, Object>> valReturn;
//        StatelessSession session = null;
//        Transaction transaction = null;
//        execution_time = System.currentTimeMillis();
//
//        try {
//            session = WFIOAPP.APP.getDataSourceService().getManager(conectionName).getNativeSession();
//            transaction = session.beginTransaction();
//            transaction.setTimeout(timeoutseg);
//
//            Query sql = session.createNativeQuery(sqlQuery);
//            sql.setTimeout(timeoutseg);
//            sql.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
//
//            if (WFIOAPP.APP.SHOW_PREQUERY) {
//                Log.info("[U" + getCo_usuari() + "][S" + getId_sesion() + "][F" + getId_frawor() + "][C" + getCo_conten() + "][P" + getCo_pagina() + "][" + getNo_escena() + "] Q = " + sqlQuery);
//            }
//
//            valReturn = sql.getResultList();
//
//            Log.info("[U" + getCo_usuari() + "][S" + getId_sesion() + "][F" + getId_frawor() + "][C" + getCo_conten() + "][P" + getCo_pagina() + "][" + getNo_escena() + "] Q = " + sqlQuery + " T = " + (System.currentTimeMillis() - execution_time) + "ms");
//            transaction.commit();
//            session.close();
//            jsonResponse.setStatus(JsonResponse.OK);
//            jsonResponse.setResult(valReturn);
//
//        } catch (Exception ep) {
//            System.out.println("[1]ep = " + ep);
//
//            try {
//                if (transaction != null) {
//                    transaction.rollback();
//                }
//            } catch (Exception ep2) {
//                transaction = null;
//            }
//
//            if (session != null) {
//                session = null;
//            }
//
//            ErrorMessage errormessage = Util.getError(ep);
//            if (errormessage == null) {
//                jsonResponse.setStatus(JsonResponse.OK);
//                jsonResponse.setResult("[]");
//                jsonResponse.setError(null);
//            } else {
//                jsonResponse.setStatus(JsonResponse.ERROR);
//                jsonResponse.setResult(null);
//                jsonResponse.setError(Util.getError(ep));
//            }
//
//            System.out.println("[@" + conectionName + "] Q = " + sqlQuery + "e=" + jsonResponse.getError().getMessage() + ": E1 = " + ep.getMessage() + "");
//
//            if (WFIOAPP.APP.THROWS_EXCEPTION) {
//                ep.printStackTrace();
//            }
//        }
//
//        return new Gson().toJson(jsonResponse);
//    }
//    public String SQLVOID(String conectionName, String sqlQuery, int timeoutseg) {
//        long execution_time;
//        JsonResponse jsonResponse = new JsonResponse();
//        String valReturn;
//        StatelessSession session = null;
//        Transaction transaction = null;
//        execution_time = System.currentTimeMillis();
//
//        try {
//            session = WFIOAPP.APP.getDataSourceService().getManager(conectionName).getNativeSession();
//            transaction = session.beginTransaction();
//            transaction.setTimeout(timeoutseg);
//
//            Query sql = session.createNativeQuery(sqlQuery);
//            sql.setTimeout(timeoutseg);
//            sql.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
//
//            System.out.println("[@" + conectionName + "] Q = " + sqlQuery);
//
//            valReturn = "" + sql.executeUpdate();
//
//            System.out.println("[@" + conectionName + "] Q = " + sqlQuery + " T = " + (System.currentTimeMillis() - execution_time) + "ms");
//            transaction.commit();
//            session.close();
//            jsonResponse.setStatus(JsonResponse.OK);
//            jsonResponse.setResult(valReturn);
//
//        } catch (Exception ep) {
//            System.out.println("[1]ep = " + ep);
//
//            try {
//                if (transaction != null) {
//                    transaction.rollback();
//                }
//            } catch (Exception ep2) {
//                transaction = null;
//            }
//
//            if (session != null) {
//                session = null;
//            }
//
//            ErrorMessage errormessage = Util.getError(ep);
//            if (errormessage == null) {
//                jsonResponse.setStatus(JsonResponse.OK);
//                jsonResponse.setResult("[]");
//                jsonResponse.setError(null);
//            } else {
//                jsonResponse.setStatus(JsonResponse.ERROR);
//                jsonResponse.setResult(null);
//                jsonResponse.setError(Util.getError(ep));
//            }
//
//            System.out.println("[@" + conectionName + "] Q = " + sqlQuery + "e=" + jsonResponse.getError().getMessage() + ": E1 = " + ep.getMessage() + "");
//
//            if (WFIOAPP.APP.THROWS_EXCEPTION) {
//                ep.printStackTrace();
//            }
//        }
//
//        return new Gson().toJson(jsonResponse);
//    }
