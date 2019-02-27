package com.acceso.wfweb.units;

import com.acceso.wfweb.dtos.RegistroDTO;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class Pagina extends HTMLRenderer implements Serializable {

    int co_pagina;
    String no_pagtit;
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

    public String getLs_hamoda() {
        String ls_hamoda = "";

        for (Map.Entry<String, Fila> entry : ultraFilas.entrySet()) {
//            System.out.println("entry = " + entry);
//            System.out.println("entry = " + entry.getValue());
//            System.out.println("entry = " + entry.getValue().getRegistroDTO());

            if (entry.getValue().getRegistroDTO() != null) {
                RegistroDTO registroDTO = entry.getValue().getRegistroDTO();
//                int co_pagreg = entry.getValue().getRegistroDTO().getCo_pagreg();
                if (registroDTO.getTi_pagreg() == 3 | registroDTO.getTi_pagreg() == 4) {
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
