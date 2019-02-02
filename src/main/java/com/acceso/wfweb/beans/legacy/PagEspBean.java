/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acceso.wfweb.beans.legacy;

import com.acceso.wfweb.daos.FraworLegacyDAO;
//import com.wf3.dao.AQuery;
//import wf.dto.pagesp.PaginaEspecialDto;
//import com.wf3.dao.AccesoHibernate;
//import acceso.util.Escritor;
import java.util.ArrayList;

import com.acceso.wfweb.dtos.legacy.PaginaEspecialDto;
import org.hibernate.StatelessSession;

/**
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

    //    public String docume(int p_co_docume, ArrayList<String> p, int co_conexi) {
    public PaginaEspecialDto docume(int p_co_docume, ArrayList<String> p, int co_conexi) {

        PaginaEspecialDto paginaEspecialDto;
        String no_docume;
        String aux = "";

        FraworLegacyDAO dao = new FraworLegacyDAO();
        paginaEspecialDto = dao.getPaginaEspecialDto(p_co_docume);//.getNo_docume();
        dao.close();

        no_docume = paginaEspecialDto.getNo_docume();

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
        paginaEspecialDto.setNo_docume(no_docume);

        return paginaEspecialDto;
    }
}
