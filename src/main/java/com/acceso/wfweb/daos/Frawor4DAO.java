package com.acceso.wfweb.daos;

import com.acceso.wfcore.daos.DAO;
import com.acceso.wfcore.listerners.WFCoreListener;
import com.acceso.wfcore.utils.NQuery;
import com.acceso.wfweb.dtos.*;
import com.acceso.wfweb.utils.Values;
import org.hibernate.StatelessSession;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mario Huillca <mario.huillca@acceso.com.pe>
 * Created on 30 nov. 2018, 15:14:24
 */

public class Frawor4DAO extends DAO {

    StatelessSession session;

    public Frawor4DAO() {
        this.session = WFCoreListener.dataSourceService.getMainManager().getNativeSession();
    }

    public Frawor4DAO(StatelessSession session){
        this.session = session;
    }

    public ContenedorDTO getContenedorDTO(int co_conten) {

        ContenedorDTO contenedorDTO = null;
        NQuery nQuery = new NQuery();

        try {

            nQuery.work(session.getNamedQuery(Values.QUERYS_WEB_SELECT_PFCONTEN), true, true);
            nQuery.setInteger("p_co_conten", co_conten);
            contenedorDTO = (ContenedorDTO) nQuery.uniqueResult();

        } catch (Exception ep) {
            System.out.println("[Frawor4DAO] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return contenedorDTO;
    }

    public List<PaginaDTO> getPaginaDTO(int p_co_conten, long p_id_frawor) {

        List<PaginaDTO> paginaDTOS = new ArrayList<>();
        NQuery nQuery = new NQuery();

        try {

            nQuery.work(session.getNamedQuery(Values.QUERYS_WEB_SELECT_PFCONPAG), true, true);
            nQuery.setInteger("p_co_conten", p_co_conten);
            nQuery.setLong("p_id_frawor", p_id_frawor);
            paginaDTOS = nQuery.list();

        } catch (Exception ep) {
            System.out.println("[Frawor4DAO] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return paginaDTOS;
    }

    public List<TituloDTO> getTituloDTO(int p_co_pagina, int p_co_conten, long p_id_frawor) {

        List<TituloDTO> tituloDTOS = new ArrayList<>();
        NQuery nQuery = new NQuery();

        try {

            nQuery.work(session.getNamedQuery(Values.QUERYS_WEB_SELECT_PFPAGTIT), true, true);
            nQuery.setInteger("p_co_conten", p_co_conten);
            nQuery.setLong("p_id_frawor", p_id_frawor);
            nQuery.setInteger("p_co_pagina", p_co_pagina);
            tituloDTOS = nQuery.list();

        } catch (Exception ep) {
            System.out.println("[Frawor4DAO] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return tituloDTOS;
    }

    public List<RegistroDTO> getRegistroDTO(int p_co_pagina, int p_co_conten, long p_id_frawor) {

        List<RegistroDTO> registroDTOS = null;
        NQuery nQuery = new NQuery();

        try {

            nQuery.work(session.getNamedQuery(Values.QUERYS_WEB_SELECT_PFPAGREG), true, true);
            nQuery.setInteger("p_co_conten", p_co_conten);
            nQuery.setLong("p_id_frawor", p_id_frawor);
            nQuery.setInteger("p_co_pagina", p_co_pagina);
            registroDTOS = nQuery.list();

        } catch (Exception ep) {
            System.out.println("[Frawor4DAO] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return registroDTOS;
    }

    public List<BotonDTO> getButonDTO(int p_co_pagina, int p_co_conten, long p_id_frawor) {
        List<BotonDTO> botonDTOS = null;
        NQuery nQuery = new NQuery();

        try {

            nQuery.work(session.getNamedQuery(Values.QUERYS_WEB_SELECT_PFPAGBOT), true, true);
            nQuery.setInteger("p_co_conten", p_co_conten);
            nQuery.setLong("p_id_frawor", p_id_frawor);
            nQuery.setInteger("p_co_pagina", p_co_pagina);
            botonDTOS = nQuery.list();

        } catch (Exception ep) {
            System.out.println("[Frawor4DAO] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return botonDTOS;
    }

    public List<ValpagDTO> getValPag_legacy(int p_co_pagina, int p_co_conten, long p_id_frawor) {
        List<ValpagDTO> valpagDTOS = null;
        NQuery nQuery = new NQuery();

        try {

            nQuery.work(session.getNamedQuery(Values.QUERYS_WEB_SELECT_PFVALPAG), true, true);
            nQuery.setInteger("p_co_conten", p_co_conten);
            nQuery.setLong("p_id_frawor", p_id_frawor);
            nQuery.setInteger("p_co_pagina", p_co_pagina);
            valpagDTOS = nQuery.list();

        } catch (Exception ep) {
            System.out.println("[Frawor4DAO] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return valpagDTOS;
    }

    public String getVPJS(int p_co_pagina) {
        String valpagJS = "";

        NQuery nQuery = new NQuery();

        try {

            nQuery.work(session.getNamedQuery(Values.QUERYS_WEB_SELECT_PFVALPAGJS), true, true);
            nQuery.setInteger("p_co_pagina", p_co_pagina);
            valpagJS = nQuery.uniqueResult().toString();

        } catch (Exception ep) {
            System.out.println("[Frawor4DAO] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return valpagJS;
    }

    public IdfraworDTO getIdfraworDTO() {

        IdfraworDTO idfraworDTO = null;
        NQuery nQuery = new NQuery();

        try {

            nQuery.work(session.getNamedQuery(Values.QUERYS_WEB_SELECT_IDFRAWOR), true, true);
            idfraworDTO = (IdfraworDTO) nQuery.uniqueResult();

        } catch (Exception ep) {
            System.out.println("[Frawor4DAO] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return idfraworDTO;
    }

    public void saveCompar(long p_id_frawor, int p_co_conten, int p_co_conpar, String p_va_conpar) {
        ProcesoDTO procesoDTO;
        NQuery nQuery = new NQuery();

        try {

            nQuery.work(session.getNamedQuery(Values.QUERYS_WEB_SELECT_PFCONPAR), true, true);
            nQuery.setLong("p_id_frawor", p_id_frawor);
            nQuery.setInteger("p_co_conten", p_co_conten);
            nQuery.setInteger("p_co_conpar", p_co_conpar);
            nQuery.setString("p_va_conpar", p_va_conpar);

            procesoDTO = (ProcesoDTO) nQuery.uniqueResult();

        } catch (Exception ep) {
            System.out.println("[Frawor4DAO] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }
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
