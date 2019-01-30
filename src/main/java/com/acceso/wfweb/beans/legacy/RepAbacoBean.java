/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acceso.wfweb.beans.legacy;

import com.acceso.wfcore.dtos.legacy.ModeloAbacoDto;
import com.wf3.dao.AccesoHibernate;
//import acceso.util.Escritor;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.StatelessSession;
//import wf.dto.pagesp.ModeloAbacoDto;

/**
 *
 * @author edavalos
 */
public class RepAbacoBean {

    String co_expedi;
    ModeloAbacoDto modelo_abaco_dto;

    public String getCo_expedi() {
        return co_expedi;
    }

    public void setCo_expedi(String co_expedi) {
        setModelo_abaco_dto(repor(Integer.parseInt(co_expedi)));
    }

    public void setCo_expedi(String co_expedi, int co_conexi) {
        setModelo_abaco_dto(repor(Integer.parseInt(co_expedi), co_conexi));
    }

    public ModeloAbacoDto getModelo_abaco_dto() {
        return modelo_abaco_dto;
    }

    public void setModelo_abaco_dto(ModeloAbacoDto modelo_abaco_dto) {
        this.modelo_abaco_dto = modelo_abaco_dto;
    }

    public ModeloAbacoDto repor(int co_expedi) {
        ModeloAbacoDto mod = null;
        StatelessSession HSESSION = null;
        try {

            HSESSION = AccesoHibernate.new_session();
            Query HQUERY = HSESSION.getNamedQuery("pfabaco");
            HQUERY.setInteger("p_co_expedi", (co_expedi));
//            Escritor.escribe_frawor(HQUERY.getQueryString().replaceAll(":p_co_expedi", "" + co_expedi));
            mod = (ModeloAbacoDto) HQUERY.uniqueResult();

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

    public ModeloAbacoDto repor(int co_expedi, int co_conexi) {
        ModeloAbacoDto mod = null;
        StatelessSession HSESSION = null;
        try {

            HSESSION = AccesoHibernate.new_session();;
            Query HQUERY = HSESSION.getNamedQuery("pfabaco");
            HQUERY.setInteger("p_co_expedi", (co_expedi));
//            Escritor.escribe_frawor(HQUERY.getQueryString().replaceAll(":p_co_expedi", "" + co_expedi));
            mod = (ModeloAbacoDto) HQUERY.uniqueResult();

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
