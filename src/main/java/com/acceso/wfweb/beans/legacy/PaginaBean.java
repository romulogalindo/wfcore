package com.acceso.wfweb.beans.legacy;

//import wf.dto.BottonDto;
//import wf.dto.PagparDto;
//import wf.dto.RegistroDto;
//import wf.dto.ValregDto;
import com.wf3.dao.AQuery;
import com.wf3.dao.AccesoHibernate;
import acceso.servlet.Transa_Engine;
//import acceso.util.Escritor;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.StatelessSession;
import wf.dto.PbpungeoDTO;
import wf3.kernel.TransaManager;
import wf3.menu.Paquete;

public class PaginaBean {

    StatelessSession HSESSION;
//    Integer transa;
    int id_transa;
    int caller;
    short p_co_pagina;
    long p_id_frawor;
    int p_co_conten;
    short p_co_regist;
    boolean p_il_autinc;
    short p_nu_offset;
    String auxiliar;
    String ip;
    List ltit = null;
    List lreg = null;
    List lval = null;
    List lbot = null;
    List lpar = null;
    List llis = null;
    boolean pintado;
    boolean botones_g = false;
    boolean botones_e = false;
    int vueltas = 0;
    int registros = 0;
    short co_botone;
    int ini = 0;
    int fin = 0;
    Exception exception = null;
    String reset;
    String close;
    boolean bloq = true;
    String opciones_gson;
    boolean REGISTRAR_TRANSACCIONES = true;
    String log;
    String cerrar_tracon;
    String css;
    Hashtable l_p_co_regist = new Hashtable();
    List v_pagreg = new ArrayList();
    String documento;
    String file_nombre;
    String file_contenido;
    String cerrar_sesion;
    //Datos - -georeg
    String va_georef;
    boolean georef_editable = false;
    int georef_type = 1;
    int georef_radio = 300;
    boolean georef_open = true;
    Paquete paq;

    public PaginaBean() {
    }

    public PaginaBean(boolean REGISTRAR_TRANSACCIONES) {
        this.REGISTRAR_TRANSACCIONES = REGISTRAR_TRANSACCIONES;
    }

    public void setReset(String reset) {
        this.vueltas = 0;
        this.registros = 0;

        this.co_botone = 0;

        this.ini = 0;
        this.fin = 0;

        this.ltit = null;
        this.lreg = null;
        this.lval = null;
        this.lbot = null;
        this.lpar = null;
        this.llis = null;

        this.p_co_pagina = 0;
        this.p_id_frawor = 0L;
        this.p_co_conten = 0;

        this.p_co_regist = 0;

        this.pintado = false;

        this.botones_g = false;
        this.botones_e = false;
    }

    public String getReset() {
        return this.reset;
    }

    public void setClose(String close) {
        try {
            if (this.HSESSION != null) {
                if (!this.HSESSION.connection().isClosed()) {
                    this.HSESSION.connection().rollback();
                    this.HSESSION.close();
                }
            }
        } catch (Exception ep) {
            try {
                this.HSESSION.connection().rollback();
            } catch (Exception ep2) {
            }
        }
    }

    public String getCerrar_tracon() {
        return this.cerrar_tracon;
    }

    public void setCerrar_tracon(String cerrar_tracon) throws HibernateException {
        try {
            this.cerrar_tracon = cerrar_tracon;
            if (this.HSESSION == null) {
                this.HSESSION = AccesoHibernate.new_session();
            }
            TransaManager.Tracon_fin(paq.getCo_conexi(), getId_transa());
        } catch (HibernateException ep) {
            System.out.println("lanzando exception ep>>>" + ep.getMessage());
            setException(ep);
            throw ep;
        } catch (Exception ep) {
            System.out.println("lanzando exception ep>>>" + ep.getMessage());
            setException(ep);
        }
    }

    public String getClose() {
        return this.close;
    }

    private List getListaTitulos(int p_co_pagina, long p_id_frawor, int p_co_conten) throws HibernateException {
        AQuery IHQUERY = new AQuery(this.HSESSION.getNamedQuery("frawor2.pfpagtit"));
        IHQUERY.setInteger("p_co_pagina", p_co_pagina);
        IHQUERY.setLong("p_id_frawor", p_id_frawor);
        IHQUERY.setInteger("p_co_conten", p_co_conten);
        Escritor.escribe_frawor(IHQUERY.getQuery());
        return IHQUERY.list();
    }

    private List getListaRegistros(int p_co_pagina, long p_id_frawor, int p_co_conten) throws HibernateException {
        AQuery IHQUERY = new AQuery(this.HSESSION.getNamedQuery("frawor2.pfpagreg"));

        IHQUERY.setInteger("p_co_pagina", p_co_pagina);
        IHQUERY.setLong("p_id_frawor", p_id_frawor);
        IHQUERY.setInteger("p_co_conten", p_co_conten);
        Escritor.escribe_frawor(IHQUERY.getQuery());
        return IHQUERY.list();
    }

    private List getListaValores(int p_co_pagina, long p_id_frawor, int p_co_conten, boolean p_il_autinc) throws HibernateException {
        AQuery IHQUERY = null;
        if (!p_il_autinc) {
            IHQUERY = new AQuery(this.HSESSION.getNamedQuery("frawor2.pfvalpag"));
            IHQUERY.setInteger("p_co_pagina", p_co_pagina);
            IHQUERY.setInteger("p_co_conten", p_co_conten);
            IHQUERY.setLong("p_id_frawor", p_id_frawor);
        } else {
            IHQUERY = new AQuery(this.HSESSION.getNamedQuery("frawor2.pfvalpag_autinc"));
            IHQUERY.setInteger("p_co_pagina", p_co_pagina);
            IHQUERY.setInteger("p_co_conten", p_co_conten);
            IHQUERY.setLong("p_id_frawor", p_id_frawor);
            IHQUERY.setShort("p_nu_offset", getP_nu_offset());
        }
        Escritor.escribe_frawor(IHQUERY.getQuery());
        return IHQUERY.list();
    }

    private List getListaBotones(short p_co_pagina, int p_co_conten, long id_frawor) throws HibernateException {
        AQuery IHQUERY = new AQuery(this.HSESSION.getNamedQuery("frawor2.pfpagbot"));
        IHQUERY.setInteger("p_co_conten", p_co_conten);
        IHQUERY.setShort("p_co_pagina", p_co_pagina);
        IHQUERY.setLong("p_id_frawor", id_frawor);
        Escritor.escribe_frawor(IHQUERY.getQuery());
        return IHQUERY.list();
    }

    private List getListaParametros(int p_co_conten, int p_co_pagina, short p_co_pagbot) throws HibernateException {
        AQuery IHQUERY = new AQuery(this.HSESSION.getNamedQuery("frawor2.pfpagpar"));
        IHQUERY.setInteger("p_co_conten", p_co_conten);
        IHQUERY.setInteger("p_co_pagina", p_co_pagina);
        IHQUERY.setShort("p_co_pagbot", p_co_pagbot);
        Escritor.escribe_frawor(IHQUERY.getQuery());
        return IHQUERY.list();
    }

    private List getListaOpciones(int p_co_pagina, short p_co_regist) throws HibernateException {
        AQuery IHQUERY = new AQuery(this.HSESSION.getNamedQuery("frawor2.pfcompag"));
        IHQUERY.setInteger("p_co_pagina", p_co_pagina);
        IHQUERY.setLong("p_id_frawor", getP_id_frawor());
        IHQUERY.setInteger("p_co_conten", getP_co_conten());
        IHQUERY.setShort("p_co_regist", p_co_regist);
        Escritor.escribe_frawor(IHQUERY.getQuery());
        return IHQUERY.list();
    }

    private PbpungeoDTO getLatLng(int id_pungeo) throws HibernateException {
        AQuery IHQUERY = new AQuery(this.HSESSION.createQuery("from PbpungeoDTO where id_pungeo = " + id_pungeo));
        Escritor.escribe_frawor(IHQUERY.getQuery());
        return (PbpungeoDTO) IHQUERY.uniqueResult();
    }

    public short getP_co_pagina() {
        return this.p_co_pagina;
    }

    public void setP_co_pagina(short p_co_pagina) {
        this.p_co_pagina = p_co_pagina;
    }

    public boolean getP_il_autinc() {
        return this.p_il_autinc;
    }

    public void setP_il_autinc(boolean p_il_autinc) {
        this.p_il_autinc = p_il_autinc;
    }

    public short getP_nu_offset() {
        return this.p_nu_offset;
    }

    public void setP_nu_offset(short p_nu_offset) {
        this.p_nu_offset = p_nu_offset;
    }

    public long getP_id_frawor() {
        return this.p_id_frawor;
    }

    public void setP_id_frawor(long p_id_frawor) {
        this.p_id_frawor = p_id_frawor;
    }

    public int getP_co_conten() {
        return this.p_co_conten;
    }

    public void setP_co_conten(int p_co_conten) {
        this.p_co_conten = p_co_conten;
    }

    public short getP_co_regist() {
        return this.p_co_regist;
    }

    public void setP_co_regist(short p_co_regist) throws Exception {
        this.p_co_regist = p_co_regist;
        this.llis = null;
        try {
            if (this.l_p_co_regist.get(this.p_co_pagina + "" + p_co_regist) == null) {
                this.llis = getListaOpciones(getP_co_pagina(), p_co_regist);
                setOpciones_gson(new Gson().toJson(this.llis));
                this.l_p_co_regist.put(this.p_co_pagina + "" + p_co_regist, this.llis);
            } else {
                this.llis = ((List) this.l_p_co_regist.get(this.p_co_pagina + "" + p_co_regist));
                setOpciones_gson(new Gson().toJson(this.llis));
            }
        } catch (Exception ep) {
            setException(ep);
            throw ep;
        }
    }
//>Georef

    public void setVa_georef(String va_georef) throws Exception {
        this.va_georef = va_georef;
        int id_pungeo = 0;
        PbpungeoDTO pungeo = null;
        try {
            String[] datos = this.va_georef.split(";");

            try {
                //lat/lng
                if (datos[0].indexOf("/") == -1) {
                    id_pungeo = Integer.parseInt(datos[0]);
                } else {
                    this.va_georef = datos[0];
                }
            } catch (Exception ep) {
            }

            try {
                //Editable
                if (Integer.parseInt(datos[1]) > 0) {
                    this.georef_type = Integer.parseInt(datos[1]);
                }
            } catch (Exception ep) {
            }

            try {
                //Radio
                if (Integer.parseInt(datos[2]) > 0) {
                    this.georef_radio = Integer.parseInt(datos[2]);
                }
            } catch (Exception ep) {
            }

            try {
                //PopUp
                if (datos[3].contentEquals("T") | datos[3].contentEquals("F")) {
                    this.georef_open = (datos[3].contentEquals("T") ? true : false);
                }
            } catch (Exception ep) {
            }
        } catch (Exception ep) {
            System.out.println("Error:" + ep.getMessage());
        }
        if (id_pungeo > 0) {
            try {
                pungeo = getLatLng(id_pungeo);
            } catch (Exception ep) {
            }
            if (pungeo != null) {
                this.va_georef = pungeo.getNu_geolat() + " / " + pungeo.getNu_geolon();
            }
        }

    }

    public String getVa_georef() {
        return va_georef;
    }

    public boolean isGeoref_editable() {
        return georef_editable;
    }

    public void setGeoref_editable(boolean georef_editable) {
        this.georef_editable = georef_editable;
    }

    public int getGeoref_type() {
        return georef_type;
    }

    public void setGeoref_type(int georef_type) {
        this.georef_type = georef_type;
    }

    public int getGeoref_radio() {
        return georef_radio;
    }

    public void setGeoref_radio(int georef_radio) {
        this.georef_radio = georef_radio;
    }

    public boolean isGeoref_open() {
        return georef_open;
    }

    public void setGeoref_open(boolean georef_open) {
        this.georef_open = georef_open;
    }

//----------------------------------------------------------------------------
    public String getDocumento() {
        return this.documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;

        setFile_nombre("archivo.xml");
        setFile_contenido("Texto de prueba");
    }

    public String getFile_contenido() {
        return this.file_contenido;
    }

    public void setFile_contenido(String file_contenido) {
        this.file_contenido = file_contenido;
    }

    public String getFile_nombre() {
        return this.file_nombre;
    }

    public void setFile_nombre(String file_nombre) {
        this.file_nombre = file_nombre;
    }

    public String getAuxiliar() throws Exception {
        return this.auxiliar;
    }

    public void setAuxiliar(String auxiliar) throws HibernateException {
        System.out.println("==Llamado desde un contexto erroneo==");
        this.auxiliar = auxiliar;
        try {
            this.HSESSION = AccesoHibernate.new_session();
            try {
                if (this.REGISTRAR_TRANSACCIONES) {
                    TransaManager.Trapag_inicio(paq.getCo_conexi(), getId_transa(), Integer.parseInt("" + this.p_id_frawor), this.p_co_pagina);
                }
            } catch (Exception ep) {
            }
            String csstext = "";
            String tdtext = "";
            int mcss = 1;

            this.lval = getListaValores(getP_co_pagina(), getP_id_frawor(), getP_co_conten(), getP_il_autinc());

            if (((this.lval != null ? 1 : 0) & (this.lval.size() > 0 ? 1 : 0)) != 0) {
                setPintado(true);
                this.ltit = getListaTitulos(getP_co_pagina(), getP_id_frawor(), getP_co_conten());
                this.lreg = getListaRegistros(getP_co_pagina(), getP_id_frawor(), getP_co_conten());
                List l_r = new ArrayList();
                for (int i = 0; i < this.lreg.size(); i++) {
                    RegistroDto m_reg = (RegistroDto) this.lreg.get(i);
                    boolean existe = false;
                    for (int o = 0; o < this.lreg.size(); o++) {
                        try {
                            ValregDto m_val = (ValregDto) this.lval.get(o);
                            if (m_val.getCo_pagreg() == m_reg.getId().getCo_pagreg()) {
                                o = this.lreg.size() - 1;
                                if (m_val.getTi_estreg() != "" && m_val.getTi_estreg() != null && m_val.getTi_estreg() != m_reg.getTi_estreg()) {
                                    m_reg.setTi_estreg(m_val.getTi_estreg());
                                }

                                if (!m_reg.getTi_estreg().contentEquals("O")) {
                                    csstext = csstext + "#tb" + getP_co_pagina() + " tr td:nth-child(" + mcss + "){vertical-align: " + m_reg.getVa_valign() + ";text-align: " + m_reg.getVa_alireg() + ";white-space: " + m_reg.getTi_nowrap() + ";}\n";
                                } else {
                                    csstext = csstext + "#tb" + getP_co_pagina() + " tr td:nth-child(" + mcss + "){}\n";
                                }
                                mcss++;
                                existe = true;
                                break;
                            }
                        } catch (Exception ep) {
                            o = this.lreg.size() - 1;
                            break;
                        }
                    }
                    if (!existe) {
                        l_r.add("" + i);
                    }
                }
                for (int i = l_r.size() - 1; i >= 0; i--) {
                    try {
                        this.lreg.remove(Integer.parseInt(l_r.get(i).toString()));
                    } catch (NumberFormatException ep) {
                        Escritor.escribe_errors("Error eliminando:" + ep.getMessage());
                    }
                }
                csstext = csstext + "#tb" + getP_co_pagina() + " tr td:nth-child(" + mcss + "){padding-left: 3px;text-align: center;white-space: nowrap;}\n";
                setCss(csstext);

                if (this.REGISTRAR_TRANSACCIONES) {
                    this.lbot = getListaBotones(getP_co_pagina(), getP_co_conten(), this.p_id_frawor);
                }

                setRegistros(this.lreg.size());
                if (this.lval.size() / this.lreg.size() == 0) {
                    setVueltas(1);
                } else {
                    setVueltas(this.lval.size() / this.lreg.size());
                }
                if (this.REGISTRAR_TRANSACCIONES) {
                    if (((this.lbot != null ? 1 : 0) & (this.lbot.size() > 0 ? 1 : 0)) != 0) {
                        for (int i = 0; i < this.lbot.size(); i++) {
                            BottonDto b = (BottonDto) this.lbot.get(i);
                            this.lpar = getListaParametros(getP_co_conten(), getP_co_pagina(), b.getId().getCo_pagbot());
                            String parametro = "";
                            if ((b.getTi_pagbot().contentEquals("E") | b.getTi_pagbot().contentEquals("") | b.getTi_pagbot().contentEquals("M"))) {
                                setBotones_e(true);
                                if (((this.lpar != null ? 1 : 0) & (this.lpar.size() > 0 ? 1 : 0)) != 0) {
                                    for (int o = 0; o < this.lpar.size(); o++) {
                                        parametro = parametro + "" + ((PagparDto) this.lpar.get(o)).getId().getCo_pagreg() + "," + ((PagparDto) this.lpar.get(o)).getCo_conpar() + ",";
                                    }
                                    parametro = parametro.substring(0, parametro.length() - 1) + "";
                                    ((BottonDto) this.lbot.get(i)).setParametro(parametro);
                                }
                            } else if (b.getTi_pagbot().contentEquals("G")) {
                                setBotones_g(true);
                                if (((this.lpar != null ? 1 : 0) & (this.lpar.size() > 0 ? 1 : 0)) != 0) {
                                    for (int o = 0; o < this.lpar.size(); o++) {
                                        parametro = parametro + "" + ((PagparDto) this.lpar.get(o)).getId().getCo_pagreg() + "," + ((PagparDto) this.lpar.get(o)).getCo_conpar() + ",";
                                    }
                                    parametro = parametro.substring(0, parametro.length() - 1) + "";
                                    ((BottonDto) this.lbot.get(i)).setParametro(parametro);
                                }
                            }
                        }
                    } else {
                        setBotones_e(false);
                        setBotones_g(false);
                    }
                }
            } else {
                setPintado(false);
            }
        } catch (HibernateException ep) {
            System.out.println("eror:" + ep.getMessage());
            setException(ep);
            ep.printStackTrace();
            throw ep;
        } finally {
            try {
                if (this.REGISTRAR_TRANSACCIONES) {
                    TransaManager.Trapag_fin(paq.getCo_conexi(), getId_transa(), getP_co_pagina());
                }
            } catch (Exception ep) {
            }
        }
        this.auxiliar = "";
    }

    public List getLtit() {
        return this.ltit;
    }

    public void setLtit(List ltit) {
        this.ltit = ltit;
    }

    public List getLreg() {
        return this.lreg;
    }

    public void setLreg(List lreg) {
        this.lreg = lreg;
    }

    public List getLval() {
        return this.lval;
    }

    public void setLval(List lval) {
        this.lval = lval;
    }

    public List getLbot() {
        return this.lbot;
    }

    public void setLbot(List lbot) {
        this.lbot = lbot;
    }

    public List getLpar() {
        return this.lpar;
    }

    public void setLpar(List lpar) {
        this.lpar = lpar;
    }

    public List getLlis() {
        return this.llis;
    }

    public void setLlis(List llis) {
        this.llis = llis;
    }

    public boolean isPintado() {
        return this.pintado;
    }

    public void setPintado(boolean pintado) {
        this.pintado = pintado;
    }

    public boolean isBotones_e() {
        return this.botones_e;
    }

    public void setBotones_e(boolean botones_e) {
        this.botones_e = botones_e;
    }

    public boolean isBotones_g() {
        return this.botones_g;
    }

    public void setBotones_g(boolean botones_g) {
        this.botones_g = botones_g;
    }

    public int getVueltas() {
        return this.vueltas;
    }

    public void setVueltas(int vueltas) {
        this.vueltas = vueltas;
    }

    public int getRegistros() {
        return this.registros;
    }

    public void setRegistros(int registros) {
        this.registros = registros;
    }

    public int getFin() {
        return this.fin;
    }

    public void setFin(int fin) {
        this.fin = fin;
    }

    public int getIni() {
        return this.ini;
    }

    public void setIni(int ini) {
        this.ini = ini;
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

//    public Integer getTransa() {
//        return this.transa;
//    }
//
//    public void setTransa(Integer transa) {
//        this.transa = transa;
//    }
    public void setId_transa(int id_transa) {
        Escritor.escribe_debug("--------------->" + "Se esta seteando el parametro transa:" + id_transa);
        this.id_transa = id_transa;
    }

    public int getId_transa() {
        return id_transa;
    }

    public Exception getException() {
        return this.exception;
    }

    public void setException(Exception exception) {
        if (this.bloq) {
            this.exception = exception;
            this.bloq = false;
            this.exception.printStackTrace();
        }
    }

    public String getOpciones_gson() {
        return this.opciones_gson;
    }

    public void setOpciones_gson(String opciones_gson) {
        this.opciones_gson = opciones_gson;
    }

    public int getCaller() {
        return this.caller;
    }

    public void setCaller(int caller) {
        this.caller = caller;
    }

    public String getLog() {
        return this.log;
    }

    public void setLog(String log) {
        this.log = log;
        Escritor.escribe_frawor(log);
    }

    public String getCss() {
        return this.css;
    }

    public void setCss(String css) {
        this.css = css;
    }

    public String getCerrar_sesion() {
        return cerrar_sesion;
    }

    public void setCerrar_sesion(String cerrar_sesion) {
        this.cerrar_sesion = cerrar_sesion;
        try {
            this.HSESSION.close();
            this.HSESSION = null;
        } catch (Exception ex) {
        }
    }

    public Paquete getPaq() {
        return paq;
    }

    public void setPaq(Paquete paq) {
        this.paq = paq;
    }

}
