package com.acceso.wfcore.daos;

import com.acceso.wfcore.dtos.ConparDTO;
import com.acceso.wfcore.dtos.ContabDTO;
import com.acceso.wfcore.dtos.ContenedorDTO;
import com.acceso.wfcore.dtos.EmptyDTO;
import com.acceso.wfcore.dtos.PagconparDTO;
import com.acceso.wfcore.listerners.WFCoreListener;
import com.acceso.wfcore.utils.NQuery;
import com.acceso.wfcore.utils.Values;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mario Huillca <mario.huillca@acceso.com.pe>
 * Created on 30 nov. 2018, 15:14:24
 */

public class ContenedorDAO {
    StatelessSession session;

    public ContenedorDAO() {
        session = WFCoreListener.dataSourceService.getMainManager().getNativeSession();
    }

    public List<ContenedorDTO> getContenedores() {

        List<ContenedorDTO> contenedores = new ArrayList<>();
        NQuery nQuery = new NQuery();

        try {
            System.out.println("session = " + session);
            System.out.println("Values.QUERYS_NATIVE_SELECT_CONTENEDOR = " + Values.QUERYS_NATIVE_SELECT_CONTENEDOR);
            System.out.println("session.getNamedQuery(Values.QUERYS_NATIVE_SELECT_CONTENEDOR) = " + session.getNamedQuery(Values.QUERYS_NATIVE_SELECT_CONTENEDOR));

            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_SELECT_CONTENEDOR));

            System.out.println("[ContenedorDAO:getContenedores] Q = " + nQuery.getQueryString());
            contenedores = nQuery.list();
            System.out.println("[ContenedorDAO:getContenedores] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");

        } catch (Exception ep) {
            System.out.println("[ContenedorDAO:getContenedores] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return contenedores;
    }

    public ContenedorDTO saveContent(ContenedorDTO contenedorDTO) {

//        NQuery nQuery = new NQuery();
//
//        try {
//            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_GRABAR_CONTENEDOR));
//
//            nQuery.setInteger("co_usuari", usuario.getCo_usuari() == null ? -1 : usuario.getCo_usuari());
//            nQuery.setString("co_usulog", usuario.getCo_usulog());
//            nQuery.setString("no_usuari", usuario.getNo_usuari());
//            nQuery.setString("pw_usuari", usuario.getPw_usuari());
//            nQuery.setString("ti_usuari", usuario.getTi_usuari());
//            nQuery.setInteger("co_person", usuario.getCo_person() == null ? -1 : usuario.getCo_person());
//            nQuery.setInteger("co_sistem", usuario.getCo_sistem());
//            nQuery.setInteger("co_subsis", usuario.getCo_subsis());
//
//
//            System.out.println("[ContenedorDAO:grabarContenedor] Q = " + nQuery.getQueryString());
//
//            usuario = (ContenedorDTO) nQuery.list().get(0);
//
//            System.out.println("[ContenedorDAO:grabarContenedor] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");
//        } catch (Exception ep) {
//            System.out.println("[ContenedorDAO:grabarContenedor] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
//            ep.printStackTrace();
//        }
//
//        return usuario;
        return null;
    }

    public void saveContab(ContabDTO contabDTO) {

        NQuery nQuery = new NQuery();

        try {
            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_SAVE_CONTAB));

            nQuery.setInteger("p_co_conten", contabDTO.getCo_conten());
            nQuery.setShort("p_co_contab", contabDTO.getCo_contab());
            nQuery.setShort("p_nu_rowspa", contabDTO.getNu_rowspa());
            nQuery.setShort("p_nu_colspa", contabDTO.getNu_colspa());
            nQuery.setShort("p_or_numrow", contabDTO.getOr_numrow());
            nQuery.setShort("p_or_numcol", contabDTO.getOr_numcol());
            nQuery.setString("p_ti_haling", contabDTO.getTi_haling());
            nQuery.setString("p_ti_valing", contabDTO.getTi_valing());
            nQuery.setShort("p_ca_tamcel", (short) 505);

            nQuery.setInteger("p_co_pagina", contabDTO.getCo_pagina() == null ? -1 : contabDTO.getCo_pagina());


            System.out.println("[ContenedorDAO:grabarContenedor] Q = " + nQuery.getQueryString());

            EmptyDTO rpta = (EmptyDTO) nQuery.uniqueResult();

            System.out.println("rpta = " + rpta);
            System.out.println("[ContenedorDAO:contabDTO] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");
        } catch (Exception ep) {
            System.out.println("[ContenedorDAO:contabDTO] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

//        return contabDTO;
    }

    public void saveConpar(ConparDTO conparDTO) {

        NQuery nQuery = new NQuery();

        try {
            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_SAVE_CONPAR));

            nQuery.setInteger("p_co_conten", conparDTO.getCo_conten());
            nQuery.setShort("p_co_conpar", conparDTO.getCo_conpar());
            nQuery.setString("p_no_conpar", conparDTO.getNo_conpar());

            System.out.println("[ContenedorDAO:saveConpar] Q = " + nQuery.getQueryString());

            EmptyDTO rpta = (EmptyDTO) nQuery.uniqueResult();

            System.out.println("rpta = " + rpta);
            System.out.println("[ContenedorDAO:saveConpar] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");
        } catch (Exception ep) {
            System.out.println("[ContenedorDAO:saveConpar] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }
    }


    public void deleteConpar(ConparDTO conparDTO) {

        NQuery nQuery = new NQuery();

        int result = -1;
        Transaction transa = null;
        try {
            transa = session.beginTransaction();
            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_DELETE_CONPAR));

            nQuery.setInteger("p_co_conten", conparDTO.getCo_conten());
            nQuery.setShort("p_co_conpar", conparDTO.getCo_conpar());

            System.out.println("[ContenedorDAO:deleteConpar] Q = " + nQuery.getQueryString());

//            EmptyDTO rpta = (EmptyDTO) nQuery.uniqueResult();
            result = nQuery.executeUpdate();

            System.out.println("rpta = " + result);
            System.out.println("[ContenedorDAO:deleteConpar] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");
            transa.commit();
        } catch (Exception ep) {
            System.out.println("[ContenedorDAO:deleteConpar] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
            try {
                if (transa != null) transa.rollback();
            } catch (Exception ep2) {
            }
        }
    }


    public List<ConparDTO> getConpars(int p_co_conten) {

        List<ConparDTO> conpars = new ArrayList<>();
        NQuery nQuery = new NQuery();

        try {
            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_SELECT_CONPAR));
            nQuery.setInteger("p_co_conten", p_co_conten);
            System.out.println("[ContenedorDAO:getConpar] Q = " + nQuery.getQueryString());
            conpars = nQuery.list();
            System.out.println("[ContenedorDAO:getConpar] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");

        } catch (Exception ep) {
            System.out.println("[ContenedorDAO:getConpar] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return conpars;
    }

    public List<ContabDTO> getContabs(int p_co_conten) {

        List<ContabDTO> contabs = new ArrayList<>();
        NQuery nQuery = new NQuery();

        try {
            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_SELECT_CONTAB));
            nQuery.setInteger("p_co_conten", p_co_conten);

            System.out.println("[ContenedorDAO:getContab] Q = " + nQuery.getQueryString());
            contabs = nQuery.list();
            System.out.println("[ContenedorDAO:getContab] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");

        } catch (Exception ep) {
            System.out.println("[ContenedorDAO:getContab] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return contabs;
    }

    public List<PagconparDTO> getPagconpars(int p_co_pagina, short p_co_pagbot, int p_co_conori) {

        List<PagconparDTO> pagconpars = new ArrayList<>();
        NQuery nQuery = new NQuery();

        try {
            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_SELECT_PAGCONPAR));
            nQuery.setInteger("p_co_conori", p_co_conori);
            nQuery.setInteger("p_co_pagina", p_co_pagina);
            nQuery.setShort("p_co_pagbot", p_co_pagbot);
            System.out.println("[ContenedorDAO:getPagconpars] Q = " + nQuery.getQueryString());
            pagconpars = nQuery.list();
            System.out.println("[ContenedorDAO:getPagconpars] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");

        } catch (Exception ep) {
            System.out.println("[ContenedorDAO:getPagconpars] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return pagconpars;
    }
    //
//    public String deleteContenedor(ContenedorDTO usuario) {
//
//        NQuery nQuery = new NQuery();
//
//        String resultado = null;
//
//        try {
//            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_DELETE_CONTENEDOR));
//
//            nQuery.setInteger("co_usuari", usuario.getCo_usuari());
//
//            System.out.println("[ContenedorDAO:deleteContenedor] Q = " + nQuery.getQueryString());
//
//            resultado = nQuery.list().toString();
//
//            System.out.println("[ContenedorDAO:deleteContenedor] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");
//            System.out.println("[ContenedorDAO:deleteContenedor] Q = " + nQuery.getQueryString() + " R = " + resultado);
//
//        } catch (Exception ep) {
//            System.out.println("[ContenedorDAO:deleteContenedor] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
//            ep.printStackTrace();
//        }
//        return resultado;
//    }
//
    public void close() {
        try {
            session.close();
        } catch (Exception ep) {
            session = null;
        }
    }

}
