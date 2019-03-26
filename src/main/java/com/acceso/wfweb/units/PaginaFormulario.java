package com.acceso.wfweb.units;

import java.io.Serializable;
import java.util.LinkedHashMap;

public class PaginaFormulario extends Pagina implements Serializable {

    public PaginaFormulario(int co_pagina, String no_pagtit, String ti_pagina, int nu_rowspa, int nu_colspa, int or_numrow, int or_numcol, int co_contab, LinkedHashMap<String, Fila> ultraFilas) {
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

    @Override
    public String toHTML() {
        String html = "";

        html += "<input type=hidden id=ti_pagina value=F />";
        html += "<input type=hidden id=ls_hamoda value=\"" + getLs_hamoda() + "\" />";

//        html += "<table id=PAG" + this.co_pagina + " class=\"w3-table-all w3-tiny w3-card-4\">";
        html += "<table id=PAG" + this.co_pagina + " class=\"table table-hover mb-0\">";
//        html += "<thead>";
//        html += "   <tr>";
//        html += "       <th colspan=2 >";
//        html += "           <h3>" + this.no_pagtit + "</h3>";
//        html += "       </th>";
//        html += "   </tr>";
//        html += "</thead>";
        html += "<tbody id=\"row1\">";

        html = this.ultraFilas.values().stream().map((fila) -> fila.toHTML()).reduce(html, String::concat);
        /*this.ultraFilas.values().stream().forEach(fila -> {
            html += fila.toHTML();
        });*/
//        html += "</tbody>";
        html += "</table>";

        return html;
    }
}
