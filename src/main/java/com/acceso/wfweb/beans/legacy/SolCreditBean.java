/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acceso.wfweb.beans.legacy;

//import wf.dto.pagesp.Reporte_SolicitudDto;
//import com.wf3.dao.AccesoHibernate;
//import acceso.util.Escritor;
//import java.lang.reflect.Method;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//import org.hibernate.HibernateException;
import com.acceso.wfweb.dtos.legacy.Solicitud_creditoDto;
import com.acceso.wfweb.dtos.legacy.Solicitud_credito_datos_soliciDto;
import com.acceso.wfweb.daos.FraworLegacyDAO;
//import org.postgresql.jdbc2.optional.SimpleDataSource;
//import wf.dto.pagesp.Solicitud_creditoDto;
//import wf.dto.pagesp.Solicitud_credito_datos_soliciDto;

/**
 *
 * @author edavalos
 */
public class SolCreditBean {

    int co_expedi;
    Solicitud_creditoDto solicitud_creditoDto;
    Solicitud_credito_datos_soliciDto solicitud_credito_TIDto;
    Solicitud_credito_datos_soliciDto solicitud_credito_CODto;
    Solicitud_credito_datos_soliciDto solicitud_credito_GADto;
    Solicitud_credito_datos_soliciDto solicitud_credito_CGDto;

    public int getCo_expedi() {
        return co_expedi;
    }

    public void setCo_expedi(int co_expedi) {
        this.co_expedi = co_expedi;
        proces();
    }

//    public void setCo_expedi(int co_expedi, int co_conexi) {
//        this.co_expedi = co_expedi;
//        proces(co_conexi);
//    }

    public Solicitud_creditoDto getSolicitud_creditoDto() {
        return solicitud_creditoDto;
    }

    public void setSolicitud_creditoDto(Solicitud_creditoDto solicitud_creditoDto) {
        this.solicitud_creditoDto = solicitud_creditoDto;
    }

    public Solicitud_credito_datos_soliciDto getSolicitud_credito_TIDto() {
        return solicitud_credito_TIDto;
    }

    public void setSolicitud_credito_TIDto(Solicitud_credito_datos_soliciDto solicitud_credito_TIDto) {
        this.solicitud_credito_TIDto = solicitud_credito_TIDto;
    }

    public Solicitud_credito_datos_soliciDto getSolicitud_credito_CODto() {
        return solicitud_credito_CODto;
    }

    public void setSolicitud_credito_CODto(Solicitud_credito_datos_soliciDto solicitud_credito_CODto) {
        this.solicitud_credito_CODto = solicitud_credito_CODto;
    }

    public Solicitud_credito_datos_soliciDto getSolicitud_credito_GADto() {
        return solicitud_credito_GADto;
    }

    public void setSolicitud_credito_GADto(Solicitud_credito_datos_soliciDto solicitud_credito_GADto) {
        this.solicitud_credito_GADto = solicitud_credito_GADto;
    }

    public Solicitud_credito_datos_soliciDto getSolicitud_credito_CGDto() {
        return solicitud_credito_CGDto;
    }

    public void setSolicitud_credito_CGDto(Solicitud_credito_datos_soliciDto solicitud_credito_CGDto) {
        this.solicitud_credito_CGDto = solicitud_credito_CGDto;
    }

    public void proces() {

        FraworLegacyDAO dao = new FraworLegacyDAO();
        this.solicitud_creditoDto = dao.getSolicitudCreditoDto(co_expedi);
        dao.close();

        if (this.solicitud_creditoDto.getTi_cuotas().equals("M")) {
            this.solicitud_creditoDto.setTi_plazo("Meses");
        } else if (this.solicitud_creditoDto.getTi_cuotas().equals("S")) {
            this.solicitud_creditoDto.setTi_plazo("Semanas");
        }

        dao = new FraworLegacyDAO();
        this.solicitud_credito_TIDto = dao.getSolicitudCreditoDatosSoliciDtos(co_expedi,"TI");
        this.solicitud_credito_CODto = dao.getSolicitudCreditoDatosSoliciDtos(co_expedi,"CO");
        this.solicitud_credito_GADto = dao.getSolicitudCreditoDatosSoliciDtos(co_expedi,"GA");
        this.solicitud_credito_CGDto = dao.getSolicitudCreditoDatosSoliciDtos(co_expedi,"CG");
        dao.close();

//        StatelessSession HSESSION = null;
//        try {
//            HSESSION = AccesoHibernate.new_session();
//
//            Query HQUERY = HSESSION.getNamedQuery("get_solcre");
////            HQUERY.setInteger("p_co_expedi", (co_expedi));
//////            Escritor.escribe_frawor(HQUERY.getQueryString().replaceAll(":p_co_expedi", "" + co_expedi));
////            this.solicitud_creditoDto = (Solicitud_creditoDto) HQUERY.uniqueResult();
////
////            if (this.solicitud_creditoDto.getTi_cuotas().equals("M")) {
////                this.solicitud_creditoDto.setTi_plazo("Meses");
////            } else if (this.solicitud_creditoDto.getTi_cuotas().equals("S")) {
////                this.solicitud_creditoDto.setTi_plazo("Semanas");
////            }
//
////            HQUERY = HSESSION.getNamedQuery("get_solcredatos_solici");
////            HQUERY.setInteger("p_co_expedi", (co_expedi));
////            HQUERY.setString("p_ti_solici", "TI");
//////            Escritor.escribe_frawor(HQUERY.getQueryString().replaceAll(":p_co_expedi", "" + co_expedi).replaceAll(":p_ti_solici", "" + "TI"));
////            this.solicitud_credito_TIDto = (Solicitud_credito_datos_soliciDto) HQUERY.uniqueResult();
//
////            HQUERY = HSESSION.getNamedQuery("get_solcredatos_solici");
////            HQUERY.setInteger("p_co_expedi", (co_expedi));
////            HQUERY.setString("p_ti_solici", "CO");
//////            Escritor.escribe_frawor(HQUERY.getQueryString().replaceAll(":p_co_expedi", "" + co_expedi).replaceAll(":p_ti_solici", "" + "CO"));
////            this.solicitud_credito_CODto = (Solicitud_credito_datos_soliciDto) HQUERY.uniqueResult();
//            HQUERY = HSESSION.getNamedQuery("get_solcredatos_solici");
//
//            HQUERY.setInteger("p_co_expedi", (co_expedi));
//            HQUERY.setString("p_ti_solici", "GA");
////            Escritor.escribe_frawor(HQUERY.getQueryString().replaceAll(":p_co_expedi", "" + co_expedi).replaceAll(":p_ti_solici", "" + "GA"));
//            this.solicitud_credito_GADto = (Solicitud_credito_datos_soliciDto) HQUERY.uniqueResult();
//
//            HQUERY.setInteger("p_co_expedi", (co_expedi));
//            HQUERY.setString("p_ti_solici", "CG");
////            Escritor.escribe_frawor(HQUERY.getQueryString().replaceAll(":p_co_expedi", "" + co_expedi).replaceAll(":p_ti_solici", "" + "CG"));
//            this.solicitud_credito_CGDto = (Solicitud_credito_datos_soliciDto) HQUERY.uniqueResult();
//        } catch (Exception e) {
//            throw e;
//        } finally {
//            try {
//                if (HSESSION != null) {
//                    if (!HSESSION.connection().isClosed()) {
//                        HSESSION.close();
//                    }
//                }
//            } catch (Exception ep) {
//                HSESSION = null;
//            }
//        }

    }

//    public void proces(int co_conexi) {
//        StatelessSession HSESSION = null;
//        try {
//            HSESSION = AccesoHibernate.new_session();;
//
//            Query HQUERY = HSESSION.getNamedQuery("get_solcre");
//            HQUERY.setInteger("p_co_expedi", (co_expedi));
////            Escritor.escribe_frawor(HQUERY.getQueryString().replaceAll(":p_co_expedi", "" + co_expedi));
//            this.solicitud_creditoDto = (Solicitud_creditoDto) HQUERY.uniqueResult();
//
//            if (this.solicitud_creditoDto.getTi_cuotas().equals("M")) {
//                this.solicitud_creditoDto.setTi_plazo("Meses");
//            } else if (this.solicitud_creditoDto.getTi_cuotas().equals("S")) {
//                this.solicitud_creditoDto.setTi_plazo("Semanas");
//            }
//
//            HQUERY = HSESSION.getNamedQuery("get_solcredatos_solici");
//            HQUERY.setInteger("p_co_expedi", (co_expedi));
//            HQUERY.setString("p_ti_solici", "TI");
////            Escritor.escribe_frawor(HQUERY.getQueryString().replaceAll(":p_co_expedi", "" + co_expedi).replaceAll(":p_ti_solici", "" + "TI"));
//            this.solicitud_credito_TIDto = (Solicitud_credito_datos_soliciDto) HQUERY.uniqueResult();
//
//            HQUERY = HSESSION.getNamedQuery("get_solcredatos_solici");
//            HQUERY.setInteger("p_co_expedi", (co_expedi));
//            HQUERY.setString("p_ti_solici", "CO");
////            Escritor.escribe_frawor(HQUERY.getQueryString().replaceAll(":p_co_expedi", "" + co_expedi).replaceAll(":p_ti_solici", "" + "CO"));
//            this.solicitud_credito_CODto = (Solicitud_credito_datos_soliciDto) HQUERY.uniqueResult();
//            HQUERY = HSESSION.getNamedQuery("get_solcredatos_solici");
//
//            HQUERY.setInteger("p_co_expedi", (co_expedi));
//            HQUERY.setString("p_ti_solici", "GA");
////            Escritor.escribe_frawor(HQUERY.getQueryString().replaceAll(":p_co_expedi", "" + co_expedi).replaceAll(":p_ti_solici", "" + "GA"));
//            this.solicitud_credito_GADto = (Solicitud_credito_datos_soliciDto) HQUERY.uniqueResult();
//
//            HQUERY.setInteger("p_co_expedi", (co_expedi));
//            HQUERY.setString("p_ti_solici", "CG");
////            Escritor.escribe_frawor(HQUERY.getQueryString().replaceAll(":p_co_expedi", "" + co_expedi).replaceAll(":p_ti_solici", "" + "CG"));
//            this.solicitud_credito_CGDto = (Solicitud_credito_datos_soliciDto) HQUERY.uniqueResult();
//        } catch (Exception e) {
//            throw e;
//        } finally {
//            try {
//                if (HSESSION != null) {
//                    if (!HSESSION.connection().isClosed()) {
//                        HSESSION.close();
//                    }
//                }
//            } catch (Exception ep) {
//                HSESSION = null;
//            }
//        }
//
//    }

}
