/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acceso.wfweb.beans.legacy;

import wf.dto.georef.pflispun3;
//import com.wf3.dao.AccesoHibernate;
//import acceso.util.Escritor;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.StatelessSession;

/**
 *
 * @author romulogalindo
 */
public class GeorefBean {
    String data;
    String xdata;
    boolean data_or_xdata = false;
    Exception exception = null;
    
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
        //System.out.println(">>la data:"+data);
        String respuesta = "";
        String[] puntos = this.data.split(",");
        //System.out.println("Cantidad de puntos : "+puntos.length);
        if(puntos.length == 1){
            respuesta = procesar(puntos[0]);
            setXdata(respuesta);
            setData_or_xdata(false);
        }else if(puntos.length > 2){
            respuesta = procesar2(puntos);
            setXdata(respuesta);
            setData_or_xdata(true);
        }
        
        //System.out.println(">>"+respuesta);
    }

    public String getXdata() {
        return xdata;
    }

    public void setXdata(String xdata) {
        this.xdata = xdata;
    }

    public boolean isData_or_xdata() {
        return data_or_xdata;
    }

    public void setData_or_xdata(boolean data_or_xdata) {
        this.data_or_xdata = data_or_xdata;
    }
    
    public String procesar(String ID)throws HibernateException{
        String procesar = "";
        StatelessSession HSESSION = null;
        try{
            HSESSION = AccesoHibernate.new_session();
            Query HQUERY = HSESSION.getNamedQuery("georef.pflispun3");
            HQUERY.setInteger("p_id_pungeo", Integer.parseInt(ID));
            
//            Escritor.escribe_frawor(HQUERY.getQueryString().replace(":p_id_pungeo", "" + ID));
            
            pflispun3 punto = (pflispun3)HQUERY.uniqueResult();
            procesar = "{["+punto.getNu_geolat()+","+punto.getNu_geolon()+"]}";
        }catch(HibernateException ep){
            setException(ep);
            throw ep;
        }finally{
            try {            
                if (HSESSION != null) {
                    if(!HSESSION.connection().isClosed()) HSESSION.close();
                }
            }catch (Exception ep) {HSESSION = null;}
        }
        return procesar;
    }
    
    public String procesar2(String[] IDs)throws HibernateException{
        String procesar = "{";
        StatelessSession HSESSION = null;
        try{
            HSESSION = AccesoHibernate.new_session();
            
            for(int i = 0 ; i < IDs.length ; i++){
                Query HQUERY = HSESSION.getNamedQuery("georef.pflispun3");
                HQUERY.setInteger("p_id_pungeo", Integer.parseInt(IDs[i]));

//                Escritor.escribe_frawor(HQUERY.getQueryString().replace(":p_id_pungeo", "" + IDs[i]));

                pflispun3 punto = (pflispun3)HQUERY.uniqueResult();
                procesar = procesar+ "["+punto.getNu_geolat()+","+punto.getNu_geolon()+"];";
            }
            procesar = procesar.substring(0, procesar.length()-1)+"}";
            
        }catch(HibernateException ep){ 
            setException(ep);
            throw ep;
        }finally{
            try {            
                if (HSESSION != null) {
                    if(!HSESSION.connection().isClosed()) HSESSION.close();
                }
            }catch (Exception ep) {HSESSION = null;}
        } 
        
        return procesar;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
    
}
