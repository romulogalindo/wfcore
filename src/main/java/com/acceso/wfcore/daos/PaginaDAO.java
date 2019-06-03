package com.acceso.wfcore.daos;

import com.acceso.wfcore.dtos.*;
import com.acceso.wfcore.kernel.WFIOAPP;
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
        this.session = WFIOAPP.APP.dataSourceService.getMainManager().getNativeSession();
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
            nQuery.setString("p_js_compag", paginaDTO.getJs_compag());
            nQuery.setString("p_js_dinpag", paginaDTO.getJs_dinpag());

            System.out.println("[PaginaDAO:save] Q = " + nQuery.getQueryString());
            paginaDTO = (PaginaDTO) nQuery.uniqueResult();
            System.out.println("[PaginaDAO:save] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");

        } catch (Exception ep) {
            System.out.println("[PaginaDAO:save] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return paginaDTO;
    }

    public PaginaconDTO save(PaginaconDTO paginaconDTO) {
        NQuery nQuery = new NQuery();

        try {
            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_SAVE_PAGINACON));

            nQuery.setInteger("p_co_pagina", paginaconDTO.getCo_pagina());
            nQuery.setInteger("p_co_conten", paginaconDTO.getCo_conten());
            nQuery.setString("p_no_pagtit", paginaconDTO.getNo_pagtit());
            nQuery.setString("p_ti_pagina", paginaconDTO.getTi_pagina());
            nQuery.setString("p_no_pagdes", paginaconDTO.getNo_pagdes());

            System.out.println("[PaginaDAO:save] Q = " + nQuery.getQueryString());
            paginaconDTO = (PaginaconDTO) nQuery.uniqueResult();
            System.out.println("[PaginaDAO:save] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");

        } catch (Exception ep) {
            System.out.println("[PaginaDAO:save] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return paginaconDTO;
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

    public List<BotonDTO> getButtons(int p_co_conten, int p_co_pagina) {
        List<BotonDTO> buttons = new ArrayList<>();
        List<PagconDTO> pagcons = new ArrayList<>();
        NQuery nQuery = new NQuery();

        try {

            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_SELECT_BUTTONS));
            nQuery.setInteger("p_co_pagina", p_co_pagina);

            System.out.println("[PaginaDAO:getButtons] Q = " + nQuery.getQueryString());
            buttons = nQuery.list();
            System.out.println("[PaginaDAO:getButtons] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");

            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_SELECT_PAGCON));
            nQuery.setInteger("p_co_conten", p_co_conten);
            nQuery.setInteger("p_co_pagina", p_co_pagina);

            System.out.println("[PaginaDAO:getButtons] Q = " + nQuery.getQueryString());
            pagcons = nQuery.list();
            System.out.println("[PaginaDAO:getButtons] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");

        } catch (Exception ep) {
            System.out.println("[PaginaDAO:getButtons] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        //enlace de!
        for (BotonDTO botonDTO : buttons) {
            if (pagcons != null) {
                for (PagconDTO pagconDTO : pagcons) {
                    if (botonDTO.getCo_pagbot() == pagconDTO.getCo_pagbot()) {
                        botonDTO.setPagconDTO(pagconDTO);
                    }
                }
            }
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

    public PaginaDTO getPagina(int p_co_pagina) {
        NQuery nQuery = new NQuery();
        PaginaDTO paginaDTO = null;

        try {
            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_GET_PAGINA));

            nQuery.setInteger("p_co_pagina", p_co_pagina);

            System.out.println("[PaginaDAO:save] Q = " + nQuery.getQueryString());
            paginaDTO = (PaginaDTO) nQuery.uniqueResult();
            System.out.println("[PaginaDAO:save] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");

        } catch (Exception ep) {
            System.out.println("[PaginaDAO:save] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return paginaDTO;
    }

    public PaginaconDTO getPaginacon(int p_co_conten, int p_co_pagina) {
        NQuery nQuery = new NQuery();
        PaginaconDTO paginaDTO = null;

        try {
            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_GET_PAGINACON));

            nQuery.setInteger("p_co_conten", p_co_conten);
            nQuery.setInteger("p_co_pagina", p_co_pagina);

            System.out.println("[PaginaDAO:save] Q = " + nQuery.getQueryString());
            paginaDTO = (PaginaconDTO) nQuery.uniqueResult();
            System.out.println("[PaginaDAO:save] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");

        } catch (Exception ep) {
            System.out.println("[PaginaDAO:save] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return paginaDTO;
    }

    public List<PaginaconDTO> getPaginascon(int p_co_conten) {
        NQuery nQuery = new NQuery();
        List<PaginaconDTO> paginasDTO = null;

        try {
            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_GET_PAGINASCON));

            nQuery.setInteger("p_co_conten", p_co_conten);

            System.out.println("[PaginaDAO:getPaginascon] Q = " + nQuery.getQueryString());
            paginasDTO = nQuery.list();
            System.out.println("[PaginaDAO:getPaginascon] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");

        } catch (Exception ep) {
            System.out.println("[PaginaDAO:getPaginascon] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return paginasDTO;
    }

    public BotonDTO saveButton(int p_co_pagina, BotonDTO botonDTO) {
        NQuery nQuery = new NQuery();

        try {
            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_SAVE_BUTTON));
            nQuery.setInteger("p_co_pagina", p_co_pagina);
            nQuery.setShort("p_co_pagbot", (short) botonDTO.getCo_pagbot());
            nQuery.setString("p_no_pagbot", botonDTO.getNo_pagbot());
            nQuery.setShort("p_or_pagbot", botonDTO.getOr_pagbot());
            nQuery.setString("p_ti_pagbot", botonDTO.getTi_pagbot());
            nQuery.setBoolean("p_il_proces", botonDTO.isIl_proces());
            nQuery.setBoolean("p_il_confir", botonDTO.isIl_confir());
            nQuery.setString("p_no_confir", botonDTO.getNo_confir());
            nQuery.setBoolean("p_il_autent", botonDTO.isIl_autent());
            nQuery.setBoolean("p_il_peresc", botonDTO.isIl_peresc());

            nQuery.setString("p_no_icobot", botonDTO.getNo_icobot());
            nQuery.setString("p_no_icopos", botonDTO.getNo_icopos());

            System.out.println("[PaginaDAO:save] Q = " + nQuery.getQueryString());
            botonDTO = (BotonDTO) nQuery.uniqueResult();
            System.out.println("[PaginaDAO:save] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");

        } catch (Exception ep) {
            System.out.println("[PaginaDAO:save] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return botonDTO;
    }

    public BotonDTO saveButton(int p_co_conten, int p_co_pagina, BotonDTO botonDTO) {
        NQuery nQuery = new NQuery();
        Integer p_co_condes = botonDTO.getCo_condes();
        try {
            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_SAVE_BUTTON));
            nQuery.setInteger("p_co_pagina", p_co_pagina);
            nQuery.setShort("p_co_pagbot", (short) botonDTO.getCo_pagbot());
            nQuery.setString("p_no_pagbot", botonDTO.getNo_pagbot());
            nQuery.setShort("p_or_pagbot", botonDTO.getOr_pagbot());
            nQuery.setString("p_ti_pagbot", botonDTO.getTi_pagbot());
            nQuery.setBoolean("p_il_proces", botonDTO.isIl_proces());
            nQuery.setBoolean("p_il_confir", botonDTO.isIl_confir());
            nQuery.setString("p_no_confir", botonDTO.getNo_confir());
            nQuery.setBoolean("p_il_autent", botonDTO.isIl_autent());
            nQuery.setBoolean("p_il_peresc", botonDTO.isIl_peresc());
//            nQuery.setInteger("co_condes", botonDTO.getCo_condes());

            System.out.println("[PaginaDAO:saveButton] Q = " + nQuery.getQueryString());
            botonDTO = (BotonDTO) nQuery.uniqueResult();
            System.out.println("[PaginaDAO:saveButton] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");

            System.out.println("botonDTO:" + botonDTO);
            System.out.println("botonDTO:" + botonDTO.getCo_condes());

            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_SAVE_PAGCON));
            nQuery.setInteger("p_co_conten", p_co_conten);
            nQuery.setInteger("p_co_pagina", p_co_pagina);
            nQuery.setShort("p_co_pagbot", (short) botonDTO.getCo_pagbot());
            nQuery.setInteger("p_co_condes", p_co_condes == null ? -1 : p_co_condes);

            System.out.println("[PaginaDAO:saveButton] Q = " + nQuery.getQueryString());
            EmptyDTO emptyDTO = (EmptyDTO) nQuery.uniqueResult();
            System.out.println("[PaginaDAO:saveButton] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");

            System.out.println("EmptyDTO = " + emptyDTO);

        } catch (Exception ep) {
            System.out.println("[PaginaDAO:save] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return botonDTO;
    }

    public List<PagtitDTO> getTitulos(int p_co_pagina) {
        List<PagtitDTO> pagtits = new ArrayList<>();
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

        return pagtits;
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

    public List<ElementoconDTO> getElementos(int p_co_conten, int p_co_pagina) {
        List<PagtitconDTO> pagtitcons = new ArrayList<>();
        List<PagregconDTO> pagregcons = new ArrayList<>();
        List<ElementoconDTO> elements = new ArrayList<>();

        NQuery nQuery = new NQuery();

        try {

            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_SELECT_PAGTITCON));
            nQuery.setInteger("p_co_pagina", p_co_pagina);
            nQuery.setInteger("p_co_conten", p_co_conten);

            System.out.println("[PaginaDAO:getElementos] Q = " + nQuery.getQueryString());
            pagtitcons = nQuery.list();
            System.out.println("[PaginaDAO:getElementos] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");

        } catch (Exception ep) {
            System.out.println("[PaginaDAO:getElementos] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        //de registro
        try {

            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_SELECT_PAGREGCON));
            nQuery.setInteger("p_co_conten", p_co_conten);
            nQuery.setInteger("p_co_pagina", p_co_pagina);

            System.out.println("[PaginaDAO:getElementos] Q = " + nQuery.getQueryString());
            pagregcons = nQuery.list();
            System.out.println("[PaginaDAO:getElementos] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");

        } catch (Exception ep) {
            System.out.println("[PaginaDAO:getElementos] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        for (PagtitconDTO pagtitDTO : pagtitcons) {
            ElementoconDTO elementoDTO = new ElementoconDTO();
            elementoDTO.setCo_elemen(p_co_pagina * 1000 + pagtitDTO.getCo_pagtit());
            elementoDTO.setTi_elemen(ElementoDTO.TYPE_TITLE);
            elementoDTO.setNo_elemen(pagtitDTO.getNo_pagtit());
            elementoDTO.setPagtitDTO(pagtitDTO);
            elements.add(elementoDTO);

            for (PagregconDTO pagregDTO : pagregcons) {
                if (pagregDTO.getCo_pagtit() == pagtitDTO.getCo_pagtit()) {
                    ElementoconDTO elementoDTO2 = new ElementoconDTO();
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
                if (transa != null) {
                    transa.rollback();
                }
            } catch (Exception ep2) {
            }
        }
        System.out.println("[deleteButton]result = " + result);
//        return paginaDTO;
    }

    public PagtitDTO saveTitle(int p_co_pagina, PagtitDTO tituloDTO) {
        NQuery nQuery = new NQuery();

        try {
            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_SAVE_PAGTIT));
            nQuery.setInteger("p_co_pagina", p_co_pagina);
            nQuery.setShort("p_co_pagtit", (short) tituloDTO.getCo_pagtit());
            nQuery.setString("p_no_pagtit", tituloDTO.getNo_pagtit());
            nQuery.setShort("p_or_pagtit", tituloDTO.getOr_pagtit());

            System.out.println("[PaginaDAO:saveTitle] Q = " + nQuery.getQueryString());
            tituloDTO = (PagtitDTO) nQuery.uniqueResult();
            System.out.println("[PaginaDAO:saveTitle] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");

        } catch (Exception ep) {
            System.out.println("[PaginaDAO:save] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return tituloDTO;
    }

    public PagtitconDTO saveTitle(int p_co_conten, int p_co_pagina, PagtitconDTO tituloDTO) {
        NQuery nQuery = new NQuery();

        try {
            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_SAVE_PAGTITCON));
            nQuery.setInteger("p_co_conten", p_co_conten);
            nQuery.setInteger("p_co_pagina", p_co_pagina);
            nQuery.setShort("p_co_pagtit", (short) tituloDTO.getCo_pagtit());
            nQuery.setString("p_no_pagtit", tituloDTO.getNo_pagtit());
            nQuery.setShort("p_or_pagtit", tituloDTO.getOr_pagtit());

            System.out.println("[PaginaDAO:saveTitle] Q = " + nQuery.getQueryString());
            tituloDTO = (PagtitconDTO) nQuery.uniqueResult();
            System.out.println("[PaginaDAO:saveTitle] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");

        } catch (Exception ep) {
            System.out.println("[PaginaDAO:save] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return tituloDTO;
    }

    public PagregDTO saveRegist(int p_co_pagina, PagregDTO pagregDTO) {
        NQuery nQuery = new NQuery();

        try {
            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_SAVE_PAGREG));
            nQuery.setInteger("p_co_pagina", p_co_pagina);
            nQuery.setShort("p_co_pagreg", (short) pagregDTO.getCo_pagreg());
            nQuery.setString("p_no_pagreg", pagregDTO.getNo_pagreg());
            nQuery.setShort("p_co_pagtit", (short) pagregDTO.getCo_pagtit());
            nQuery.setShort("p_or_pagreg", pagregDTO.getOr_pagreg());
            nQuery.setShort("p_ti_pagreg", pagregDTO.getTi_pagreg());
            nQuery.setString("p_ti_estreg", pagregDTO.getTi_estreg());
            nQuery.setString("p_va_alireg", pagregDTO.getVa_alireg());
            nQuery.setString("p_no_desreg", pagregDTO.getNo_desreg());
            nQuery.setShort("p_ca_carcol", pagregDTO.getCa_carcol());
            nQuery.setShort("p_ca_carrow", pagregDTO.getCa_carrow());
            nQuery.setString("p_ti_nowrap", pagregDTO.getTi_nowrap());
            nQuery.setBoolean("p_il_onchan", pagregDTO.getIl_onchan());
            nQuery.setString("p_va_valign", pagregDTO.getVa_valign());
            nQuery.setBoolean("p_il_guareg", pagregDTO.getIl_guareg());
            nQuery.setShort("p_ca_caract", pagregDTO.getCa_caract());

            System.out.println("[PaginaDAO:saveRegist] Q = " + nQuery.getQueryString());
            pagregDTO = (PagregDTO) nQuery.uniqueResult();
            System.out.println("[PaginaDAO:saveRegist] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");

        } catch (Exception ep) {
            System.out.println("[PaginaDAO:save] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return pagregDTO;
    }

    public PagregconDTO saveRegist(int p_co_conten, int p_co_pagina, PagregconDTO pagregDTO) {
        NQuery nQuery = new NQuery();

        try {
            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_SAVE_PAGREGCON));
            nQuery.setInteger("p_co_conten", p_co_conten);
            nQuery.setInteger("p_co_pagina", p_co_pagina);
            nQuery.setShort("p_co_pagreg", (short) pagregDTO.getCo_pagreg());
            nQuery.setString("p_no_pagreg", pagregDTO.getNo_pagreg());
            nQuery.setShort("p_co_pagtit", (short) pagregDTO.getCo_pagtit());
            nQuery.setShort("p_or_pagreg", pagregDTO.getOr_pagreg());
            nQuery.setShort("p_ti_pagreg", pagregDTO.getTi_pagreg());
            nQuery.setString("p_ti_estreg", pagregDTO.getTi_estreg());
            nQuery.setString("p_va_alireg", pagregDTO.getVa_alireg());
            nQuery.setString("p_no_desreg", pagregDTO.getNo_desreg());
            nQuery.setShort("p_ca_carcol", pagregDTO.getCa_carcol());
            nQuery.setShort("p_ca_carrow", pagregDTO.getCa_carrow());
            nQuery.setString("p_ti_nowrap", pagregDTO.getTi_nowrap());
            nQuery.setBoolean("p_il_onchan", pagregDTO.getIl_onchan());
            nQuery.setString("p_va_valign", pagregDTO.getVa_valign());
            nQuery.setBoolean("p_il_guareg", pagregDTO.getIl_guareg());
            nQuery.setShort("p_ca_caract", pagregDTO.getCa_caract());

            System.out.println("[PaginaDAO:saveRegist] Q = " + nQuery.getQueryString());
            pagregDTO = (PagregconDTO) nQuery.uniqueResult();
            System.out.println("[PaginaDAO:saveRegist] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");

        } catch (Exception ep) {
            System.out.println("[PaginaDAO:save] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return pagregDTO;
    }

    public void deleteTitle(int p_co_pagina, short p_co_pagtit) {
        NQuery nQuery = new NQuery();
        int result = -1;
        Transaction transa = null;
        try {
            transa = session.beginTransaction();
            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_DELETE_PAGTIT));

            nQuery.setInteger("p_co_pagina", p_co_pagina);
            nQuery.setInteger("p_co_pagtit", p_co_pagtit);

            System.out.println("[PaginaDAO:deleteTitle] Q = " + nQuery.getQueryString());
            result = nQuery.executeUpdate();
            System.out.println("[PaginaDAO:deleteTitle] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");
            transa.commit();
        } catch (Exception ep) {
            System.out.println("[PaginaDAO:deleteTitle] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
            try {
                if (transa != null) {
                    transa.rollback();
                }
            } catch (Exception ep2) {
            }
        }
        System.out.println("[deleteTitle]result = " + result);
    }

    public void deleteRegist(int p_co_pagina, short p_co_pagreg) {
        NQuery nQuery = new NQuery();
        int result = -1;
        Transaction transa = null;
        try {
            transa = session.beginTransaction();
            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_DELETE_PAGREG));

            nQuery.setInteger("p_co_pagina", p_co_pagina);
            nQuery.setInteger("p_co_pagreg", p_co_pagreg);

            System.out.println("[PaginaDAO:deleteRegist] Q = " + nQuery.getQueryString());
            result = nQuery.executeUpdate();
            System.out.println("[PaginaDAO:deleteRegist] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");
            transa.commit();
        } catch (Exception ep) {
            System.out.println("[PaginaDAO:deleteRegist] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
            try {
                if (transa != null) {
                    transa.rollback();
                }
            } catch (Exception ep2) {
            }
        }
        System.out.println("[deleteRegist]result = " + result);
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
