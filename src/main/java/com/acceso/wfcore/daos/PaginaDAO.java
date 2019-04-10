package com.acceso.wfcore.daos;

import com.acceso.wfcore.dtos.*;
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

public class PaginaDAO {
    StatelessSession session;

    public PaginaDAO() {
        this.session = WFCoreListener.dataSourceService.getMainManager().getNativeSession();
    }

    public PaginaDAO(StatelessSession session) {
        this.session = session;
    }

    public List<PaginaDTO> getPaginas() {

        List<PaginaDTO> paginas = new ArrayList<>();
        NQuery nQuery = new NQuery();

        try {

            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_SELECT_PAGINA));

            System.out.println("[PaginaDAO:getPaginas] Q = " + nQuery.getQueryString());
            paginas = nQuery.list();
            System.out.println("[PaginaDAO:getPaginas] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");

        } catch (Exception ep) {
            System.out.println("[PaginaDAO:getPaginas] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return paginas;
    }

    public PaginaDTO save(PaginaDTO paginaDTO) {
        NQuery nQuery = new NQuery();

        try {
            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_SAVE_PAGINA));

            nQuery.setInteger("p_co_pagina", paginaDTO.getCo_pagina());
            nQuery.setString("p_no_pagtit", paginaDTO.getNo_pagtit());
            nQuery.setString("p_de_pagina", paginaDTO.getDe_pagina());
            nQuery.setString("p_js_valpag", paginaDTO.getJs_valpag());
            nQuery.setString("p_js_propag", paginaDTO.getJs_propag());


            System.out.println("[PaginaDAO:save] Q = " + nQuery.getQueryString());
            paginaDTO = (PaginaDTO) nQuery.uniqueResult();
            System.out.println("[PaginaDAO:save] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");

        } catch (Exception ep) {
            System.out.println("[PaginaDAO:save] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return paginaDTO;
    }

    public List<BotonDTO> getButtons(int p_co_pagina) {
        List<BotonDTO> buttons = new ArrayList<>();
        NQuery nQuery = new NQuery();

        try {

            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_SELECT_BUTTONS));
            nQuery.setInteger("p_co_pagina", p_co_pagina);

            System.out.println("[PaginaDAO:getButtons] Q = " + nQuery.getQueryString());
            buttons = nQuery.list();
            System.out.println("[PaginaDAO:getButtons] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");

        } catch (Exception ep) {
            System.out.println("[PaginaDAO:getButtons] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return buttons;
    }

    public ScriptDTO getScript(int p_co_pagina) {

        ScriptDTO scriptDTO = null;
        NQuery nQuery = new NQuery();

        try {

            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_GET_SCRIPT));
            nQuery.setInteger("p_co_pagina", p_co_pagina);

            System.out.println("[ScriptDTO:getScript] Q = " + nQuery.getQueryString());
            scriptDTO = (ScriptDTO) nQuery.uniqueResult();
            System.out.println("[ScriptDTO:getScript] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");

        } catch (Exception ep) {
            System.out.println("[ScriptDTO:getScript] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return scriptDTO;
    }


    public BotonDTO saveButton(int p_co_pagina, BotonDTO botonDTO) {
        NQuery nQuery = new NQuery();

        try {
            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_SAVE_BUTTON));
            nQuery.setInteger("p_co_pagina", p_co_pagina);
            nQuery.setShort("co_pagbot", (short) botonDTO.getCo_pagbot());
            nQuery.setString("no_pagbot", botonDTO.getNo_pagbot());
            nQuery.setShort("or_pagbot", botonDTO.getOr_pagbot());
            nQuery.setString("ti_pagbot", botonDTO.getTi_pagbot());
            nQuery.setBoolean("il_proces", botonDTO.isIl_proces());
            nQuery.setBoolean("il_confir", botonDTO.isIl_confir());
            nQuery.setString("no_confir", botonDTO.getNo_confir());
            nQuery.setBoolean("il_autent", botonDTO.isIl_autent());
            nQuery.setBoolean("il_peresc", botonDTO.isIl_peresc());


            System.out.println("[PaginaDAO:save] Q = " + nQuery.getQueryString());
            botonDTO = (BotonDTO) nQuery.uniqueResult();
            System.out.println("[PaginaDAO:save] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");

        } catch (Exception ep) {
            System.out.println("[PaginaDAO:save] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return botonDTO;
    }

    public List<ElementoDTO> getElementos(int p_co_pagina) {
        List<PagtitDTO> pagtits = new ArrayList<>();
        List<PagregDTO> pagregs = new ArrayList<>();
        List<ElementoDTO> elements = new ArrayList<>();

        NQuery nQuery = new NQuery();

        try {

            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_SELECT_PAGTIT));
            nQuery.setInteger("p_co_pagina", p_co_pagina);

            System.out.println("[PaginaDAO:getElementos] Q = " + nQuery.getQueryString());
            pagtits = nQuery.list();
            System.out.println("[PaginaDAO:getElementos] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");

        } catch (Exception ep) {
            System.out.println("[PaginaDAO:getElementos] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }
        //de registro
        try {

            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_SELECT_PAGREG));
            nQuery.setInteger("p_co_pagina", p_co_pagina);

            System.out.println("[PaginaDAO:getElementos] Q = " + nQuery.getQueryString());
            pagregs = nQuery.list();
            System.out.println("[PaginaDAO:getElementos] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");

        } catch (Exception ep) {
            System.out.println("[PaginaDAO:getElementos] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        for (PagtitDTO pagtitDTO : pagtits) {
            ElementoDTO elementoDTO = new ElementoDTO();
            elementoDTO.setCo_elemen(p_co_pagina * 1000 + pagtitDTO.getCo_pagtit());
            elementoDTO.setTi_elemen(ElementoDTO.TYPE_TITLE);
            elementoDTO.setNo_elemen(pagtitDTO.getNo_pagtit());
            elementoDTO.setPagtitDTO(pagtitDTO);
            elements.add(elementoDTO);

            for (PagregDTO pagregDTO : pagregs) {
                if (pagregDTO.getCo_pagtit() == pagtitDTO.getCo_pagtit()) {
                    ElementoDTO elementoDTO2 = new ElementoDTO();
                    elementoDTO2.setCo_elemen(p_co_pagina * 1000 + pagregDTO.getCo_pagreg());
                    elementoDTO2.setTi_elemen(ElementoDTO.TYPE_REGIST);
                    elementoDTO2.setNo_elemen(pagregDTO.getNo_pagreg());
                    elementoDTO2.setPagregDTO(pagregDTO);
                    elements.add(elementoDTO2);
                }
            }

        }

        return elements;
    }

    public void deleteButton(int p_co_pagina, short p_co_pagbot) {
        NQuery nQuery = new NQuery();
        int result = -1;
        Transaction transa = null;
        try {
            transa = session.beginTransaction();
            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_DELETE_BUTTON));

            nQuery.setInteger("p_co_pagina", p_co_pagina);
            nQuery.setInteger("p_co_pagbot", p_co_pagbot);


            System.out.println("[PaginaDAO:save] Q = " + nQuery.getQueryString());
            result = nQuery.executeUpdate();
            System.out.println("[PaginaDAO:save] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");
            transa.commit();
        } catch (Exception ep) {
            System.out.println("[PaginaDAO:save] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
            try {
                if (transa != null) transa.rollback();
            } catch (Exception ep2) {
            }
        }
        System.out.println("[deleteButton]result = " + result);
//        return paginaDTO;
    }

    //    public PaginaDTO grabarPagina(PaginaDTO pagina) {
//
//        NQuery nQuery = new NQuery();
//
//        try {
//            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_GRABAR_CONTENEDOR));
//
//            nQuery.setInteger("co_usuari", pagina.getCo_usuari() == null ? -1 : pagina.getCo_usuari());
//            nQuery.setString("co_usulog", pagina.getCo_usulog());
//            nQuery.setString("no_usuari", pagina.getNo_usuari());
//            nQuery.setString("pw_usuari", pagina.getPw_usuari());
//            nQuery.setString("ti_usuari", pagina.getTi_usuari());
//            nQuery.setInteger("co_person", pagina.getCo_person() == null ? -1 : pagina.getCo_person());
//            nQuery.setInteger("co_sistem", pagina.getCo_sistem());
//            nQuery.setInteger("co_subsis", pagina.getCo_subsis());
//
//
//            System.out.println("[PaginaDAO:grabarPagina] Q = " + nQuery.getQueryString());
//
//            pagina = (PaginaDTO) nQuery.list().get(0);
//
//            System.out.println("[PaginaDAO:grabarPagina] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");
//        } catch (Exception ep) {
//            System.out.println("[PaginaDAO:grabarPagina] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
//            ep.printStackTrace();
//        }
//
//        return pagina;
//    }
//
//    public String deletePagina(PaginaDTO pagina) {
//
//        NQuery nQuery = new NQuery();
//
//        String resultado = null;
//
//        try {
//            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_DELETE_CONTENEDOR));
//
//            nQuery.setInteger("co_usuari", pagina.getCo_usuari());
//
//            System.out.println("[PaginaDAO:deletePagina] Q = " + nQuery.getQueryString());
//
//            resultado = nQuery.list().toString();
//
//            System.out.println("[PaginaDAO:deletePagina] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");
//            System.out.println("[PaginaDAO:deletePagina] Q = " + nQuery.getQueryString() + " R = " + resultado);
//
//        } catch (Exception ep) {
//            System.out.println("[PaginaDAO:deletePagina] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
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
