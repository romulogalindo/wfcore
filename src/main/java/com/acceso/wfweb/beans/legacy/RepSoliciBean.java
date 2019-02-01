/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acceso.wfweb.beans.legacy;

import com.acceso.wfweb.dtos.legacy.Reporte_SolicitudDto;
//import wf.dto.pagesp.Reporte_SolicitudDto;
import com.acceso.wfweb.daos.FraworLegacyDAO;
//import com.wf3.dao.AccesoHibernate;
//import acceso.util.Escritor;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author edavalos
 */
public class RepSoliciBean {

    String co_expedi;
    Reporte_SolicitudDto reporte_solicitudDto;
    String dia;
    String mes;
    String anio;

    public String getCo_expedi() {
        return co_expedi;
    }

    public void setCo_expedi(String co_expedi) {
        setReporte_solicitudDto(repor(Integer.parseInt(co_expedi)));

        setDia(new SimpleDateFormat("dd").format(new Date()));
        setMes(new SimpleDateFormat("MM").format(new Date()));
        setAnio(new SimpleDateFormat("yyyy").format(new Date()));
    }

//    public void setCo_expedi(String co_expedi, int co_conexi) {
//        setReporte_solicitudDto(repor(Integer.parseInt(co_expedi), co_conexi));
//
//        setDia(new SimpleDateFormat("dd").format(new Date()));
//        setMes(new SimpleDateFormat("MM").format(new Date()));
//        setAnio(new SimpleDateFormat("yyyy").format(new Date()));
//    }

    public Reporte_SolicitudDto getReporte_solicitudDto() {
        return reporte_solicitudDto;
    }

    public void setReporte_solicitudDto(Reporte_SolicitudDto reporte_solicitudDto) {
        this.reporte_solicitudDto = reporte_solicitudDto;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public Reporte_SolicitudDto repor(int p_co_expedi) {
        Reporte_SolicitudDto rep = null;

        FraworLegacyDAO dao = new FraworLegacyDAO();
        rep = dao.getReporteSolicitudDto(p_co_expedi);
        dao.close();

//        StatelessSession HSESSION = null;
//        try {
//
//            HSESSION = AccesoHibernate.new_session();
//            Query HQUERY = HSESSION.getNamedQuery("get_repsol");
//            HQUERY.setInteger("p_co_expedi", (co_expedi));
////            Escritor.escribe_frawor(HQUERY.getQueryString().replaceAll(":p_co_expedi", "" + co_expedi));
//            rep = (Reporte_SolicitudDto) HQUERY.uniqueResult();
//
//        } catch (HibernateException e) {
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

        return rep;
    }

//    public Reporte_SolicitudDto repor(int co_expedi, int co_conexi) {
//        Reporte_SolicitudDto rep = null;
//        StatelessSession HSESSION = null;
//        try {
//
//            HSESSION = AccesoHibernate.new_session();
//            ;
//            Query HQUERY = HSESSION.getNamedQuery("get_repsol");
//            HQUERY.setInteger("p_co_expedi", (co_expedi));
////            Escritor.escribe_frawor(HQUERY.getQueryString().replaceAll(":p_co_expedi", "" + co_expedi));
//            rep = (Reporte_SolicitudDto) HQUERY.uniqueResult();
//
//        } catch (HibernateException e) {
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
//        return rep;
//    }

}
