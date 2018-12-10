package com.acceso.wfcore.utils;

//import com.sun.mail.util.QDecoderStream;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author Rómulo Galindo<romulogalindo@gmail.com>
 * Created on 4 dic. 2018, 19:19:59
 */
public class NQuery {

    private Query query = null;
    private String queryString = "";
    private long execution_time;

    public void work(Query query) {
        this.query = query;
        this.queryString = this.query.getQueryString();
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

    public void setString(String param_name, String param_value) {
        query.setString(param_name, param_value);
        queryString = queryString.replaceFirst(":" + param_name, "\'" + String.valueOf(param_value) + "\'");
    }

    public void setDate(String param_name, Date param_value) {
        query.setDate(param_name, param_value);
        queryString = queryString.replaceFirst(":" + param_name, String.valueOf(param_value));
    }

    public void setDouble(String param_name, Double param_value) {
        query.setDouble(param_name, param_value);
        queryString = queryString.replaceFirst(":" + param_name, String.valueOf(param_value));
    }

    /**
     *
     * @return
     */
    public Object uniqueResult() {
        execution_time = System.currentTimeMillis();
        Object object = query.uniqueResult();
        execution_time = System.currentTimeMillis() - execution_time;

        return object;
    }

    public List list() {
        return query.list();
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
