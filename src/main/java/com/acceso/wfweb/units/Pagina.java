package com.acceso.wfweb.units;

import com.acceso.wfweb.dtos.RegistroDTO;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class Pagina extends HTMLRenderer implements Serializable {

    int co_pagina;
    String no_pagtit;
    String ti_pagina;
    int nu_rowspa;
    int nu_colspa;
    int or_numrow;
    int or_numcol;
    int co_contab;
    LinkedHashMap<String, Fila> ultraFilas;

    public int getCo_pagina() {
        return co_pagina;
    }

    public void setCo_pagina(int co_pagina) {
        this.co_pagina = co_pagina;
    }

    public String getNo_pagtit() {
        return no_pagtit;
    }

    public void setNo_pagtit(String no_pagtit) {
        this.no_pagtit = no_pagtit;
    }

    public String getTi_pagina() {
        return ti_pagina;
    }

    public void setTi_pagina(String ti_pagina) {
        this.ti_pagina = ti_pagina;
    }

    public int getNu_rowspa() {
        return nu_rowspa;
    }

    public void setNu_rowspa(int nu_rowspa) {
        this.nu_rowspa = nu_rowspa;
    }

    public int getNu_colspa() {
        return nu_colspa;
    }

    public void setNu_colspa(int nu_colspa) {
        this.nu_colspa = nu_colspa;
    }

    public int getOr_numrow() {
        return or_numrow;
    }

    public void setOr_numrow(int or_numrow) {
        this.or_numrow = or_numrow;
    }

    public int getOr_numcol() {
        return or_numcol;
    }

    public void setOr_numcol(int or_numcol) {
        this.or_numcol = or_numcol;
    }

    public int getCo_contab() {
        return co_contab;
    }

    public void setCo_contab(int co_contab) {
        this.co_contab = co_contab;
    }

    public String getLs_hamoda() {
        String ls_hamoda = "";

        for (Map.Entry<String, Fila> entry : ultraFilas.entrySet()) {
//            System.out.println("entry = " + entry);
//            System.out.println("entry = " + entry.getValue());
//            System.out.println("entry = " + entry.getValue().getRegistroDTO());

            if (entry.getValue().getRegistroDTO() != null) {
                RegistroDTO registroDTO = entry.getValue().getRegistroDTO();
//                int co_pagreg = entry.getValue().getRegistroDTO().getCo_pagreg();
                if (registroDTO.getTi_pagreg() == 3 | registroDTO.getTi_pagreg() == 4 | registroDTO.getTi_pagreg() == 5 | registroDTO.getTi_pagreg() == 8) {
                    ls_hamoda = ls_hamoda + registroDTO.getCo_pagreg() + ",";
                }
            }

        }

        if (ls_hamoda.length() > 0) {
            ls_hamoda = ls_hamoda.substring(0, ls_hamoda.length() - 1);
        }

        return ls_hamoda;
    }

}
