package com.acceso.wfweb.beans.legacy;

import com.acceso.wfcore.dtos.legacy.CartaRetiroDto;
//import com.wf3.dao.AccesoHibernate;
//import acceso.util.Escritor;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.StatelessSession;
//import wf.dto.pagesp.CartaRetiroDto;
//import wf.dto.pagesp.ModeloAbacoDto;

/**
 *
 * @author edavalos
 */
public class CartaRetiroBean {

    String co_expedi;
    CartaRetiroDto carta_retiro_dto;

    public String getCo_expedi() {
        return co_expedi;
    }

    public void setCo_expedi(String co_expedi) {
        setCarta_retiro_dto(repor(Integer.parseInt(co_expedi)));
    }

    public void setCo_expedi(String co_expedi, int co_conexi) {
        setCarta_retiro_dto(repor(Integer.parseInt(co_expedi), co_conexi));
    }

    public CartaRetiroDto getCarta_retiro_dto() {
        return carta_retiro_dto;
    }

    public void setCarta_retiro_dto(CartaRetiroDto carta_retiro_dto) {
        this.carta_retiro_dto = carta_retiro_dto;
    }

    public CartaRetiroDto repor(int co_expedi) {
        CartaRetiroDto cr = null;
        StatelessSession HSESSION = null;
        try {

            HSESSION = AccesoHibernate.new_session();
            Query HQUERY = HSESSION.getNamedQuery("pbcarta_retiro_abaco");
            HQUERY.setInteger("p_co_expedi", (co_expedi));
//            Escritor.escribe_frawor(HQUERY.getQueryString().replaceAll(":p_co_expedi", "" + co_expedi));
            cr = (CartaRetiroDto) HQUERY.uniqueResult();

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

        return cr;
    }

    public CartaRetiroDto repor(int co_expedi, int co_conexi) {
        CartaRetiroDto cr = null;
        StatelessSession HSESSION = null;
        try {

            HSESSION = AccesoHibernate.new_session();;
            Query HQUERY = HSESSION.getNamedQuery("pbcarta_retiro_abaco");
            HQUERY.setInteger("p_co_expedi", (co_expedi));
//            Escritor.escribe_frawor(HQUERY.getQueryString().replaceAll(":p_co_expedi", "" + co_expedi));
            cr = (CartaRetiroDto) HQUERY.uniqueResult();

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

        return cr;
    }

}
