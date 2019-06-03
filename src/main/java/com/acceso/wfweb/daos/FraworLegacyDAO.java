package com.acceso.wfweb.daos;

import com.acceso.wfcore.daos.DAO;
import com.acceso.wfcore.kernel.WFIOAPP;
import com.acceso.wfweb.dtos.legacy.*;
import com.acceso.wfcore.utils.NQuery;
import com.acceso.wfweb.utils.Values;
import org.hibernate.StatelessSession;

/**
 * @author Mario Huillca <mario.huillca@acceso.com.pe>
 * Created on 30 nov. 2018, 15:14:24
 */
public class FraworLegacyDAO extends DAO {

    public static String TAG = "FRAWOR";
    public StatelessSession session;

    public FraworLegacyDAO() {
//        this.session = WFCoreListener.dataSourceService.getMainManager().getNativeSession();
        this.session = WFIOAPP.APP.dataSourceService.getManager("wfacr").getNativeSession();
    }

    public FraworLegacyDAO(StatelessSession session) {
        this.session = session;
    }

    public CartaRetiroDto getCartaRetiroDto(int p_co_expedi) {

        CartaRetiroDto cartaRetiroDto = null;
        NQuery nQuery = new NQuery(TAG + ":CONTEN");

        try {

            nQuery.work(session.getNamedQuery(Values.QUERYS_WEB_SELECT_PBCARTA_RETIRO_ABACO), true, true);
            nQuery.setInteger("p_co_expedi", p_co_expedi);
            cartaRetiroDto = (CartaRetiroDto) nQuery.uniqueResult();

        } catch (Exception ep) {
            System.out.println("[Frawor4DAO] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return cartaRetiroDto;
    }

    public Modelo_AeDto getModelo_aeDto(int p_id_frawor) {

        Modelo_AeDto cartaRetiroDto = null;
        NQuery nQuery = new NQuery(TAG + ":CONTEN");

        try {

            nQuery.work(session.getNamedQuery(Values.QUERYS_WEB_SELECT_PBMODAE), true, true);
            nQuery.setInteger("p_id_frawor", p_id_frawor);
            cartaRetiroDto = (Modelo_AeDto) nQuery.uniqueResult();

        } catch (Exception ep) {
            System.out.println("[Frawor4DAO] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return cartaRetiroDto;
    }

    public PaginaEspecialDto getPaginaEspecialDto(int p_co_docume) {

        PaginaEspecialDto paginaEspecialDto = null;
        NQuery nQuery = new NQuery(TAG + ":CONTEN");

        try {

            nQuery.work(session.getNamedQuery(Values.QUERYS_WEB_SELECT_PBGETDOCUME), true, true);
            nQuery.setInteger("p_co_docume", p_co_docume);
            paginaEspecialDto = (PaginaEspecialDto) nQuery.uniqueResult();

        } catch (Exception ep) {
            System.out.println("[Frawor4DAO] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return paginaEspecialDto;
    }

    public ModeloAbacoDto getModeloAbacoDto(int p_co_expedi) {

        ModeloAbacoDto modeloAbacoDto = null;
        NQuery nQuery = new NQuery(TAG + ":CONTEN");

        try {

            nQuery.work(session.getNamedQuery(Values.QUERYS_WEB_SELECT_PFABACO), true, true);
            nQuery.setInteger("p_co_expedi", p_co_expedi);
            modeloAbacoDto = (ModeloAbacoDto) nQuery.uniqueResult();

        } catch (Exception ep) {
            System.out.println("[Frawor4DAO] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return modeloAbacoDto;
    }

    public Reporte_SolicitudDto getReporteSolicitudDto(int p_co_expedi) {

        Reporte_SolicitudDto reporteSolicitudDto = null;
        NQuery nQuery = new NQuery(TAG + ":CONTEN");

        try {

            nQuery.work(session.getNamedQuery(Values.QUERYS_WEB_SELECT_GETREPSOL), true, true);
            nQuery.setInteger("p_co_expedi", p_co_expedi);
            reporteSolicitudDto = (Reporte_SolicitudDto) nQuery.uniqueResult();

        } catch (Exception ep) {
            System.out.println("[Frawor4DAO] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return reporteSolicitudDto;
    }

    public Solicitud_creditoDto getSolicitudCreditoDto(int p_co_expedi) {

        Solicitud_creditoDto solicitudCreditoDto = null;
        NQuery nQuery = new NQuery(TAG + ":CONTEN");

        try {

            nQuery.work(session.getNamedQuery(Values.QUERYS_WEB_SELECT_SOLCRE), true, true);
            nQuery.setInteger("p_co_expedi", p_co_expedi);
            solicitudCreditoDto = (Solicitud_creditoDto) nQuery.uniqueResult();

        } catch (Exception ep) {
            System.out.println("[Frawor4DAO] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return solicitudCreditoDto;
    }

    public Solicitud_credito_datos_soliciDto getSolicitudCreditoDatosSoliciDtos(int p_co_expedi, String p_ti_solici) {

        Solicitud_credito_datos_soliciDto solicitudCreditoDatosSoliciDto = null;
        NQuery nQuery = new NQuery(TAG + ":CONTEN");

        try {

            nQuery.work(session.getNamedQuery(Values.QUERYS_WEB_SELECT_SOLCRE_SOLICI), true, true);
            nQuery.setInteger("p_co_expedi", p_co_expedi);
            nQuery.setString("p_ti_solici", p_ti_solici);
            solicitudCreditoDatosSoliciDto = (Solicitud_credito_datos_soliciDto) nQuery.uniqueResult();

        } catch (Exception ep) {
            System.out.println("[Frawor4DAO] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return solicitudCreditoDatosSoliciDto;
    }

    @Override
    public void close() {
        try {
            session.close();
        } catch (Exception ep) {
            session = null;
        }
    }

}
