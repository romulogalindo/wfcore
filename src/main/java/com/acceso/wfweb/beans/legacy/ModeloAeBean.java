/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acceso.wfweb.beans.legacy;

import com.acceso.wfcore.dtos.legacy.Modelo_AeDto;
//import com.wf3.dao.AccesoHibernate;
//import acceso.util.Escritor;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.StatelessSession;
//import wf.dto.pagesp.Modelo_AeDto;
//import wf.dto.pagesp.Reporte_SolicitudDto;

/**
 *
 * @author edsonsuarez
 */
public class ModeloAeBean {

    int p_id_frawor;
    Modelo_AeDto modelo_ae;

    public int getP_id_frawor() {
        return p_id_frawor;
    }

    public void setP_id_frawor(int p_id_frawor) {
        setModelo_ae(imp(p_id_frawor));
    }

    public void setP_id_frawor(int p_id_frawor, int co_conexi) {
        setModelo_ae(imp(p_id_frawor, co_conexi));
    }

    public Modelo_AeDto getModelo_ae() {
        return modelo_ae;
    }

    public void setModelo_ae(Modelo_AeDto modelo_ae) {
        this.modelo_ae = modelo_ae;
    }

    public Modelo_AeDto imp(int p_id_frawor) {
        Modelo_AeDto mod = null;
        StatelessSession HSESSION = null;
        try {
            HSESSION = AccesoHibernate.new_session();
            Query HQUERY = HSESSION.getNamedQuery("get_modAe");
            HQUERY.setInteger("p_id_frawor", (p_id_frawor));
//            Escritor.escribe_frawor(HQUERY.getQueryString().replaceAll(":p_id_frawor", "" + p_id_frawor));
            mod = (Modelo_AeDto) HQUERY.uniqueResult();

        } catch (HibernateException e) {
            throw e;
        } finally {
            try {
                if (HSESSION != null) {
                    if (!HSESSION.connection().isClosed()) {
                        HSESSION.close();
                    }
                }
            } catch (Exception ep) {
                HSESSION = null;
            }
        }

        return mod;
    }

    public Modelo_AeDto imp(int p_id_frawor, int co_conexi) {
        Modelo_AeDto mod = null;
        StatelessSession HSESSION = null;
        try {
            HSESSION = AccesoHibernate.new_session();;
            Query HQUERY = HSESSION.getNamedQuery("get_modAe");
            HQUERY.setInteger("p_id_frawor", (p_id_frawor));
//            Escritor.escribe_frawor(HQUERY.getQueryString().replaceAll(":p_id_frawor", "" + p_id_frawor));
            mod = (Modelo_AeDto) HQUERY.uniqueResult();

        } catch (HibernateException e) {
            throw e;
        } finally {
            try {
                if (HSESSION != null) {
                    if (!HSESSION.connection().isClosed()) {
                        HSESSION.close();
                    }
                }
            } catch (Exception ep) {
                HSESSION = null;
            }
        }

        return mod;
    }

}
