package com.acceso.wfcore.utils;

//import com.sun.mail.util.QDecoderStream;
import com.acceso.wfcore.kernel.WFIOAPP;
import com.acceso.wfcore.log.Log;
import com.acceso.wfcore.transa.Transactional;
import org.hibernate.Query;

import java.util.Date;
import java.util.List;
//import org.​hibernate

/**
 * @author Rómulo Galindo<romulogalindo@gmail.com>
 * Created on 4 dic. 2018, 19:19:59
 */
public class NQuery {

    private Query query = null;
    private String queryString = "";
    private long execution_time;
    private boolean show_info_log;
    private boolean show_debug_log;

    String LOG;

    public static final boolean INFO_LOG_SHOW = true;
    public static final boolean INFO_LOG_HIDE = false;
    public static final boolean DEBUG_LOG_SHOW = true;
    public static final boolean DEBUG_LOG_HIDE = true;

    public NQuery() {
        this(null);
    }

    public NQuery(Object object) {
        this.LOG = (object == null ? "" : "[" + object + "] ");
    }

    public void work(Query query) {
        work(query, false, false);
    }

    public void work(Query query, boolean show_info_log, boolean show_debug_log) {
        this.query = query;
        this.queryString = this.query.getQueryString();
        this.show_info_log = show_info_log;
        this.show_debug_log = show_debug_log;
    }

    public void setString(String param_name, String param_value) {
        query.setParameter(param_name, param_value, org.hibernate.type.StringType.INSTANCE);
        try {
            queryString = queryString.replaceFirst(":" + param_name, "\'" + String.valueOf(param_value) + "\'");
        }catch(Exception ep){
            System.out.println("ep = " + ep);
        }
    }

    /**
     * Asigna el valor int
     *
     * @param param_name
     * @param param_value
     */
    public void setInteger(String param_name, int param_value) {
        query.setInteger(param_name, param_value);
        queryString = queryString.replaceFirst(":" + param_name, String.valueOf(param_value));
    }

    public void setLong(String param_name, long param_value) {
        query.setLong(param_name, param_value);
        queryString = queryString.replaceFirst(":" + param_name, String.valueOf(param_value));
    }

    public void setDate(String param_name, Date param_value) {
        query.setDate(param_name, param_value);
        queryString = queryString.replaceFirst(":" + param_name, String.valueOf(param_value));
    }

    public void setDouble(String param_name, Double param_value) {
        query.setDouble(param_name, param_value);
        queryString = queryString.replaceFirst(":" + param_name, String.valueOf(param_value));
    }

    public void setShort(String param_name, Short param_value) {
        query.setShort(param_name, param_value);
        queryString = queryString.replaceFirst(":" + param_name, String.valueOf(param_value) + "::SMALLINT");
    }

    public void setBoolean(String param_name, Boolean param_value) {
        query.setBoolean(param_name, param_value);
        queryString = queryString.replaceFirst(":" + param_name, String.valueOf(param_value));
    }

    /**
     * @return
     */
    public Object uniqueResult() {
        if (WFIOAPP.APP.SHOW_PREQUERY) {
            Log.info(LOG + "Q = " + getQueryString());
        }

        execution_time = System.currentTimeMillis();
        Long idt = Transactional.insert(0, -1, getQueryString());
        Object object = query.uniqueResult();
        execution_time = System.currentTimeMillis() - execution_time;
        Transactional.update(idt);

//        if (this.show_debug_log) {
        Log.info(LOG + "Q = " + getQueryString() + " T = " + getExecutionTime() + "ms");
//        }

        return object;
    }

    /*
     * */
    public List list() {
        if (WFIOAPP.APP.SHOW_PREQUERY) {
            Log.info(LOG + "Q = " + getQueryString());
        }

        execution_time = System.currentTimeMillis();
        Long idt = Transactional.insert(0, -1, getQueryString());
        List list = query.list();
        execution_time = System.currentTimeMillis() - execution_time;
        Transactional.update(idt);

//        if (this.show_debug_log) {
        Log.info(LOG + "Q = " + getQueryString() + " T = " + execution_time + "ms");
//        }

        return list;
    }

    public int executeUpdate() {
        int updateResult = -1;
        if (this.show_info_log) {
            Log.info(LOG + "Q = " + getQueryString());
        }
        execution_time = System.currentTimeMillis();

        updateResult = query.executeUpdate();

        execution_time = System.currentTimeMillis() - execution_time;
//        if (this.show_debug_log) {
        Log.info(LOG + "Q = " + getQueryString() + " T = " + execution_time + "ms");
//        }

        return updateResult;
    }

    public long getExecutionTime() {
        return execution_time;
    }

    /**
     * Devuelve el query con sus valores subtituidos
     */
    public String getQueryString() {
        return queryString;
    }
}
