/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acceso.wfweb.beans.legacy;

import acceso.dashboard.WorkflowImageUtil;
import com.wf3.dao.AQuery;
import com.wf3.dao.AccesoHibernate;
import acceso.servlet.Transa_Engine;
import acceso.util.WorkflowUtil;
import java.io.Serializable;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.hibernate.StatelessSession;
import org.jfree.chart.JFreeChart;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import wf.dto.dashboard.Valpagi;
import wf3.kernel.TransaManager;

/**
 *
 * @author romulogalindo
 */
    public class DashboardBean implements Serializable {

    Integer transa;
    short p_co_pagina;
    long p_id_frawor;
    int p_co_conten;
    short p_co_regist;
    String ip;
    String auxiliar;
    boolean pintado = true;
    Exception exception = null;
    boolean bloq = true;
    int img_width = 0;
    int img_height = 0;
    String no_title = "";
    String IMG_XML;
    Document DOCUMENT;
    WorkflowImageUtil wf_util;
    StatelessSession HSESSION;
    String reset;
    boolean REGISTRAR_TRANSACCIONES = true;
    JFreeChart grafico = null;

    public DashboardBean() {
    }

    public String getAuxiliar() {
        return auxiliar;
    }

    public void setAuxiliar(String auxiliar) throws ParserConfigurationException, Exception {
        this.auxiliar = auxiliar;
        //Metodo donde ira y dispara toda la logica.
//        System.out.println("Aqui toda la logica del valpagi");
        try {
            this.HSESSION = AccesoHibernate.new_session();
            try {
                if (this.REGISTRAR_TRANSACCIONES) {
                    throw new Exception("Falta implementacion de conexion aqui!!!!");
//                    Transa_Engine.Trapag_inicio(this.HSESSION, getTransa().intValue(), Integer.parseInt("" + this.p_id_frawor), this.p_co_pagina);
                }
            } catch (Exception ep) {
            }

            AQuery IHQUERY = new AQuery(this.HSESSION.getNamedQuery("frawor2.pfvalpagi"));
            IHQUERY.setInteger("p_co_pagina", p_co_pagina);
            IHQUERY.setInteger("p_co_conten", p_co_conten);
            IHQUERY.setLong("p_id_frawor", p_id_frawor);
            Valpagi result = (Valpagi) IHQUERY.getMquery2(WorkflowUtil.LOG_FRAWOR).uniqueResult();

            if (result != null && !result.getPfvalpagi().contentEquals("")) {
                IMG_XML = result.getPfvalpagi();
                try {
                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); //un factory
                    DocumentBuilder builder = factory.newDocumentBuilder(); //el documento
                    Document DOCUMENT = builder.parse(new InputSource(new StringReader(IMG_XML)));
                    wf_util = new WorkflowImageUtil(DOCUMENT, p_id_frawor, p_co_pagina, p_co_conten);
                } catch (Exception ep) {
                    setPintado(false);
                    setException(ep);
                    throw ep;
                }
            } else {
                setPintado(false);
            }

        } catch (Exception ep) {
            setPintado(false);
            setException(ep);
            throw ep;
        } finally {
            try {
                if (this.REGISTRAR_TRANSACCIONES) {
                    throw new Exception("Falta implementacion de conexion aqui!!!!");
//                    Transa_Engine.Trapag_fin(this.HSESSION, getTransa().intValue(), getP_co_pagina());
                }
            } catch (Exception ep) {
            }
            this.HSESSION.close();
        }
    }

    public Integer getTransa() {
        return transa;
    }

    public void setTransa(Integer transa) {
        this.transa = transa;
    }

    public short getP_co_pagina() {
        return p_co_pagina;
    }

    public void setP_co_pagina(short p_co_pagina) {
        this.p_co_pagina = p_co_pagina;
//        System.out.println(">>[p_co_pagina:+" + p_co_pagina + "]");
    }

    public long getP_id_frawor() {
        return p_id_frawor;
    }

    public void setP_id_frawor(long p_id_frawor) {
        this.p_id_frawor = p_id_frawor;
//        System.out.println(">>[p_id_frawor:+" + p_id_frawor + "]");
    }

    public int getP_co_conten() {
        return p_co_conten;
    }

    public void setP_co_conten(int p_co_conten) {
        this.p_co_conten = p_co_conten;
//        System.out.println(">>[p_co_conten:+" + p_co_conten + "]");
    }

    public short getP_co_regist() {
        return p_co_regist;
    }

    public void setP_co_regist(short p_co_regist) {
        this.p_co_regist = p_co_regist;
    }

    public boolean isPintado() {
        return pintado;
    }

    public void setPintado(boolean pintado) {
        this.pintado = pintado;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        if (this.bloq) {
            this.exception = exception;
            this.bloq = false;
            this.exception.printStackTrace();
        }
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getImg_width() {
        return img_width;
    }

    public void setImg_width(int img_width) {
        this.img_width = img_width;
    }

    public int getImg_height() {
        return img_height;
    }

    public void setImg_height(int img_height) {
        this.img_height = img_height;
    }

    public String getNo_title() {
        return wf_util.getTITLE();
    }

    public WorkflowImageUtil getWf_util() {
        return wf_util;
    }

    public void setWf_util(WorkflowImageUtil wf_util) {
        this.wf_util = wf_util;
    }

    public String getReset() {
        return reset;
    }

    public void setReset(String reset) {
        this.reset = reset;
        wf_util = null;
        p_co_pagina = 0;
        p_id_frawor = 0;
        p_co_conten = 0;
        System.out.println("reseteo de dashboard");
    }

    public JFreeChart getGrafico() {
        return grafico;
    }

    public void setGrafico(JFreeChart grafico) {
        this.grafico = grafico;
    }

    public void procesar_wf_util() {
        try {
            grafico = wf_util.proces();
        } catch (Exception ex) {
            grafico = null;
        }
    }

}
