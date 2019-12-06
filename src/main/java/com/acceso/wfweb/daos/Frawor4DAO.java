package com.acceso.wfweb.daos;

import com.acceso.wfcore.daos.DAO;
import com.acceso.wfcore.kernel.WFIOAPP;
import com.acceso.wfcore.utils.NQuery;
import com.acceso.wfweb.dtos.*;
import com.acceso.wfweb.utils.Values;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mario Huillca <mario.huillca@acceso.com.pe>
 * Created on 30 nov. 2018, 15:14:24
 */
public class Frawor4DAO extends DAO {

    public static String TAG = "FRAWOR";
    public StatelessSession session;

    public Frawor4DAO() {
        this.session = WFIOAPP.APP.dataSourceService.getMainManager().getNativeSession();
    }

    public Frawor4DAO(StatelessSession session) {
        this.session = session;
    }

    public WContenedorDTO getContenedorDTO(int co_conten) {

        WContenedorDTO contenedorDTO = null;
        NQuery nQuery = new NQuery(TAG + ":CONTEN");

        try {

            nQuery.work(session.getNamedQuery(Values.QUERYS_WEB_SELECT_PFCONTEN), true, true);
            nQuery.setInteger("p_co_conten", co_conten);
            contenedorDTO = (WContenedorDTO) nQuery.uniqueResult();

        } catch (Exception ep) {
            System.out.println("[Frawor4DAO] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return contenedorDTO;
    }

    public List<WContabDTO> getContabDTO(int co_conten) {

        List<WContabDTO> contabDTOs = null;
        NQuery nQuery = new NQuery(TAG + ":CONTEN");

        try {

            nQuery.work(session.getNamedQuery(Values.QUERYS_WEB_SELECT_PFCONTAB), true, true);
            nQuery.setInteger("p_co_conten", co_conten);
            contabDTOs = nQuery.list();

        } catch (Exception ep) {
            System.out.println("[Frawor4DAO] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return contabDTOs;
    }

    public List<WPaginaDTO> getPaginaDTO(int p_co_conten, long p_id_frawor) {

        List<WPaginaDTO> paginaDTOS = new ArrayList<>();
        NQuery nQuery = new NQuery(TAG + ":PAGINA");

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

    public WScriptDTO getJS_Valpag(int p_co_pagina) {

        WScriptDTO paginaDTO = null;
        NQuery nQuery = new NQuery(TAG + ":PAGINA");

        try {

            nQuery.work(session.getNamedQuery(Values.QUERYS_WEB_SELECT_SCRIPT_VALPAG), true, true);
            nQuery.setLong("p_co_pagina", p_co_pagina);
            paginaDTO = (WScriptDTO) nQuery.uniqueResult();

        } catch (Exception ep) {
            System.out.println("[Frawor4DAO] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return paginaDTO;
    }

    public WScriptDTO getJS_Compag(int p_co_pagina) {

        WScriptDTO paginaDTO = null;
        NQuery nQuery = new NQuery(TAG + ":PAGINA");

        try {

            nQuery.work(session.getNamedQuery(Values.QUERYS_WEB_SELECT_SCRIPT_COMPAG), true, true);
            nQuery.setLong("p_co_pagina", p_co_pagina);
            paginaDTO = (WScriptDTO) nQuery.uniqueResult();

        } catch (Exception ep) {
            System.out.println("[Frawor4DAO] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return paginaDTO;
    }

    public WScriptDTO getJS_Propag(int p_co_pagina) {

        WScriptDTO paginaDTO = null;
        NQuery nQuery = new NQuery(TAG + ":PAGINA");

        try {

            nQuery.work(session.getNamedQuery(Values.QUERYS_WEB_SELECT_SCRIPT_PROPAG), true, true);
            nQuery.setLong("p_co_pagina", p_co_pagina);
            paginaDTO = (WScriptDTO) nQuery.uniqueResult();

        } catch (Exception ep) {
            System.out.println("[Frawor4DAO] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return paginaDTO;
    }

    public WScriptDTO getJS_Dinpag(int p_co_pagina) {

        WScriptDTO paginaDTO = null;
        NQuery nQuery = new NQuery(TAG + ":PAGINA");

        try {

            nQuery.work(session.getNamedQuery(Values.QUERYS_WEB_SELECT_SCRIPT_DINPAG), true, true);
            nQuery.setLong("p_co_pagina", p_co_pagina);
            paginaDTO = (WScriptDTO) nQuery.uniqueResult();

        } catch (Exception ep) {
            System.out.println("[Frawor4DAO] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return paginaDTO;
    }

    public List<TituloDTO> getTituloDTO(int p_co_pagina, int p_co_conten, long p_id_frawor) {

        List<TituloDTO> tituloDTOS = new ArrayList<>();
        NQuery nQuery = new NQuery(TAG + ":TITULO");

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

    public List<WRegistroDTO> getRegistroDTO(int p_co_pagina, int p_co_conten, long p_id_frawor) {

        List<WRegistroDTO> registroDTOS = null;
        NQuery nQuery = new NQuery(TAG + ":REGIST");

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

    public List<WBotonDTO> getButonDTO(int p_co_pagina, int p_co_conten, long p_id_frawor) {
        List<WBotonDTO> botonDTOS = null;
        NQuery nQuery = new NQuery(TAG + ":BOTONE");

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

//    public List<ValpagDTO> getValPag_legacy(int p_co_pagina, int p_co_conten, long p_id_frawor) {
//        List<ValpagDTO> valpagDTOS = null;
//        NQuery nQuery = new NQuery(this);
//
//        try {
//
//            nQuery.work(session.getNamedQuery(Values.QUERYS_WEB_SELECT_PFVALPAG), true, true);
//            nQuery.setInteger("p_co_conten", p_co_conten);
//            nQuery.setLong("p_id_frawor", p_id_frawor);
//            nQuery.setInteger("p_co_pagina", p_co_pagina);
//            valpagDTOS = nQuery.list();
//
//        } catch (Exception ep) {
//            System.out.println("[Frawor4DAO] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
//            ep.printStackTrace();
//        }
//
//        return valpagDTOS;
//    }
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
        NQuery nQuery = new NQuery(TAG + ":FRAWOR");

        try {

            nQuery.work(session.getNamedQuery(Values.QUERYS_WEB_SELECT_IDFRAWOR), true, true);
            idfraworDTO = (IdfraworDTO) nQuery.uniqueResult();

        } catch (Exception ep) {
            System.out.println("[Frawor4DAO] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return idfraworDTO;
    }

    @Deprecated
    public ProcesoDTO saveCompar(long p_id_frawor, int p_co_conten, int p_co_conpar, String p_va_conpar, boolean islocal) {
        ProcesoDTO procesoDTO = null;
        NQuery nQuery = new NQuery(TAG + ":CONPAR");
        Transaction transaction = null;

        try {
            //Codigo que hace explicito la transaccion
            transaction = session.beginTransaction();
            nQuery.work(session.getNamedQuery(islocal ? Values.QUERYS_WEB_SELECT_PFCONPAR : Values.QUERYS_WEB_SELECT_PFCONPAR2), true, true);
            nQuery.setLong("p_id_frawor", p_id_frawor);
            nQuery.setInteger("p_co_conten", p_co_conten);
            nQuery.setShort("p_co_conpar", (short) p_co_conpar);
            nQuery.setString("p_va_conpar", p_va_conpar);

            procesoDTO = (ProcesoDTO) nQuery.uniqueResult();
            transaction.commit();
            //CIerra la transaccion explicita!
        } catch (Exception ep) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("[Frawor4DAO] Q = " + nQuery.getQueryString() + ", E = " + ep.getMessage());
//            ep.printStackTrace();
        }

        return procesoDTO;
    }

    public ProcesoDTO saveCompar(long p_id_frawor, int p_co_conten, String p_co_conpar, String p_va_conpar, boolean islocal) {
        ProcesoDTO procesoDTO = null;
        NQuery nQuery = new NQuery(TAG + ":CONPAR");
        Transaction transaction = null;

        try {
            //Codigo que hace explicito la transaccion
            transaction = session.beginTransaction();
            nQuery.work(session.getNamedQuery(islocal ? Values.QUERYS_WEB_SELECT_PFCONPAR : Values.QUERYS_WEB_SELECT_PFCONPAR2), true, true);
            nQuery.setLong("p_id_frawor", p_id_frawor);
            nQuery.setInteger("p_co_conten", p_co_conten);
            nQuery.setShort("p_co_conpar", (short) Short.parseShort(p_co_conpar.replace("co_conpar_", "")));
            //ESTO SERA EL FUTURO
            //nQuery.setString("p_no_conpar", p_co_conpar);
            nQuery.setString("p_va_conpar", p_va_conpar);

            procesoDTO = (ProcesoDTO) nQuery.uniqueResult();
            transaction.commit();
            //CIerra la transaccion explicita!
        } catch (Exception ep) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("[Frawor4DAO] Q = " + nQuery.getQueryString() + ", E = " + ep.getMessage());
//            ep.printStackTrace();
        }

        return procesoDTO;
    }

    public List<WParametroDTO> getParams(int p_co_conten, int p_co_pagina, short p_co_pagbot) {
        List<WParametroDTO> parametroDTOS = null;
        NQuery nQuery = new NQuery(TAG + ":PAGPAR");

        try {
            nQuery.work(session.getNamedQuery(Values.QUERYS_WEB_SELECT_PFPAGPAR), true, true);
            nQuery.setInteger("p_co_conten", p_co_conten);
            nQuery.setInteger("p_co_pagina", p_co_pagina);
            nQuery.setShort("p_co_pagbot", p_co_pagbot);
            parametroDTOS = nQuery.list();
        } catch (Exception ep) {
            System.out.println("[Frawor4DAO] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return parametroDTOS;
    }

    public ArcadjDTO getArcadj(int p_id_arcadj) {
        ArcadjDTO arcadjDTO = null;
        NQuery nQuery = new NQuery(TAG + ":PAGINA");

        try {
            nQuery.work(session.getNamedQuery(Values.QUERYS_WEB_ARCADJ), true, true);
            nQuery.setInteger("p_id_arcadj", p_id_arcadj);
            arcadjDTO = (ArcadjDTO) nQuery.uniqueResult();
        } catch (Exception ep) {
            System.out.println("[Frawor4DAO] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return arcadjDTO;
    }

    public ArchivDTO getArchiv(int p_co_archiv) {
        ArchivDTO archivDTO = null;
        NQuery nQuery = new NQuery(TAG + ":PAGINA");

        try {
            nQuery.work(session.getNamedQuery(Values.QUERYS_WEB_ARCHIV_READ), true, true);
            nQuery.setInteger("p_co_archiv", p_co_archiv);
            archivDTO = (ArchivDTO) nQuery.uniqueResult();
        } catch (Exception ep) {
            System.out.println("[Frawor4DAO] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return archivDTO;
    }

    public ArchivDTO setArchiv(String p_no_archiv) {
        ArchivDTO archivDTO = null;
        NQuery nQuery = new NQuery(TAG + ":PAGINA");

        try {
            nQuery.work(session.getNamedQuery(Values.QUERYS_WEB_ARCHIV_INS), true, true);
            nQuery.setString("p_no_archiv", p_no_archiv);
            archivDTO = (ArchivDTO) nQuery.uniqueResult();
        } catch (Exception ep) {
            System.out.println("[Frawor4DAO] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return archivDTO;
    }

    public void joinTF(long p_id_sesion, long p_id_frawor, long p_id_fraant, int p_co_conten, boolean islocal) {
        JoinDTO joinDTO = null;
        NQuery nQuery = new NQuery(TAG + ":PAGINA");
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            nQuery.work(session.getNamedQuery(islocal ? Values.QUERYS_WEB_SELECT_JOIN_TRANSA_FRAWOR : Values.QUERYS_WEB_SELECT_JOIN_TRANSA_FRAWOR2), true, true);
            nQuery.setInteger("p_id_sesion", (int) p_id_sesion);
            nQuery.setInteger("p_id_frawor", (int) p_id_frawor);
            nQuery.setInteger("p_id_fraant", (int) p_id_fraant);
            nQuery.setInteger("p_co_conten", p_co_conten);
            joinDTO = (JoinDTO) nQuery.uniqueResult();
            transaction.commit();
        } catch (Exception ep) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("[Frawor4DAO] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

    }

    public void deletePagreg(long p_id_frawor, int p_co_pagina, boolean islocal) {
        WPagregDTO pagregDTO = null;
        NQuery nQuery = new NQuery(TAG + ":PAGINA");
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            nQuery.work(session.getNamedQuery(islocal ? Values.QUERYS_WEB_SELECT_DELTBPAGREG_FRAWOR4 : Values.QUERYS_WEB_SELECT_DELTBPAGREG_FRAWOR2), true, true);
            nQuery.setLong("p_id_frawor", p_id_frawor);
            nQuery.setInteger("p_co_pagina", (int) p_co_pagina);
            pagregDTO = (WPagregDTO) nQuery.uniqueResult();
            transaction.commit();
        } catch (Exception ep) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("[Frawor4DAO] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

    }

    public void insertPagreg(long p_id_frawor, int p_co_pagina, short p_co_pagreg, short p_nu_pagfil, String p_va_pagreg, boolean islocal) {
        WPagregDTO pagregDTO = null;
        NQuery nQuery = new NQuery(TAG + ":PAGINA");
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            nQuery.work(session.getNamedQuery(islocal ? Values.QUERYS_WEB_SELECT_INSTBPAGREG_FRAWOR4 : Values.QUERYS_WEB_SELECT_INSTBPAGREG_FRAWOR2), true, true);
            nQuery.setLong("p_id_frawor", p_id_frawor);
            nQuery.setInteger("p_co_pagina", (int) p_co_pagina);
            nQuery.setShort("p_co_pagreg", p_co_pagreg);
            nQuery.setShort("p_nu_pagfil", p_nu_pagfil);
            nQuery.setString("p_va_pagreg", p_va_pagreg);
            pagregDTO = (WPagregDTO) nQuery.uniqueResult();
            transaction.commit();
        } catch (Exception ep) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("[Frawor4DAO] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }
    }

    public List<ComboDTO> getCombo(int p_co_pagina, long p_id_frawor, int p_co_conten, short p_co_regist) {
        List<ComboDTO> comboDTOS = null;
        NQuery nQuery = new NQuery(TAG + ":PAGPAR");

        try {

            nQuery.work(session.getNamedQuery(Values.QUERYS_WEB_SELECT_PFCOMPAG), true, true);
            nQuery.setInteger("p_co_pagina", p_co_pagina);
            nQuery.setLong("p_id_frawor", p_id_frawor);
            nQuery.setInteger("p_co_conten", p_co_conten);
            nQuery.setShort("p_co_regist", p_co_regist);
            comboDTOS = nQuery.list();

        } catch (Exception ep) {
            System.out.println("[Frawor4DAO] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return comboDTOS;
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
