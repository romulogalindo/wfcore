package com.acceso.wfweb.units;

import java.io.Serializable;
import java.util.LinkedHashMap;

public class PaginaFreeForm extends Pagina implements Serializable {

    public PaginaFreeForm(int co_pagina, String no_pagtit, String ti_pagina, int nu_rowspa, int nu_colspa, int or_numrow, int or_numcol, int co_contab, LinkedHashMap<String, Fila> ultraFilas) {
        this.co_pagina = co_pagina;
        this.no_pagtit = no_pagtit;
        this.ti_pagina = ti_pagina;
        this.nu_rowspa = nu_rowspa;
        this.nu_colspa = nu_colspa;
        this.or_numrow = or_numrow;
        this.or_numcol = or_numcol;
        this.co_contab = co_contab;
        this.ultraFilas = ultraFilas;
    }

    @Override
    public String toHTML() {
        String html = "";

        html += "<input type=hidden id=ti_pagina value=X />";
//        html += "<input type=hidden id=ls_hamoda value=\"" + getLs_hamoda() + "\" />";

        html += "<table id=PAG" + this.co_pagina + " class=\"table table-hover mb-0\">";
        html += "<tbody id=\"row1\">";

        html = this.ultraFilas.values().stream().map((fila) -> fila.toHTML()).reduce(html, String::concat);

        html += "</table>";

        return html;
    }
}
