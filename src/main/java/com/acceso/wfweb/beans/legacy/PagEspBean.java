/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acceso.wfweb.beans.legacy;

import com.acceso.wfcore.dtos.legacy.PaginaEspecialDto;
import com.wf3.dao.AQuery;
//import wf.dto.pagesp.PaginaEspecialDto;
//import com.wf3.dao.AccesoHibernate;
//import acceso.util.Escritor;
import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.StatelessSession;

/**
 *
 * @author Esteban Dávalos
 */
public class PagEspBean {

    private int co_docume;
    private ArrayList<String> ls_parame;

    public int getCo_docume() {
        return co_docume;
    }

    public void setCo_docume(int co_docume) {
        this.co_docume = co_docume;
    }

    public ArrayList<String> getLs_parame() {
        return ls_parame;
    }

    public void setLs_parame(ArrayList<String> ls_parame) {
        this.ls_parame = ls_parame;
    }

    public String docume(int co_docume, ArrayList<String> p, int co_conexi) {

        StatelessSession HSESSION = null;

        String no_docume = "";
        String aux = "";

        try {
            HSESSION = AccesoHibernate.new_session();;
            AQuery query = new AQuery(HSESSION.getNamedQuery("get_docume"), co_conexi);
            query.setInteger("p_co_docume", (co_docume));
            PaginaEspecialDto pag = (PaginaEspecialDto) query.uniqueResult();

            no_docume = pag.getNo_docume();

            for (int i = 1; i < p.size(); i++) {
                if (p.get(i) != null) {
                    if (i < 10) {
                        aux = no_docume.replace("$$00" + i, p.get(i));
                    } else if (i >= 10 && i < 100) {
                        aux = no_docume.replace("$$0" + i, p.get(i));
                    } else if (i >= 100) {
                        aux = no_docume.replace("$$" + i, p.get(i));
                    }
                }
                no_docume = aux;
            }
        } catch (HibernateException ep) {
            throw ep;
        } finally {
            try {
                if (HSESSION != null) {
                    if (!HSESSION.connection().isClosed()) {
                        HSESSION.close();
                    }
                }
            } catch (Exception ep) {
                HSESSION = null;
            }
        }

        return no_docume;
    }
}
