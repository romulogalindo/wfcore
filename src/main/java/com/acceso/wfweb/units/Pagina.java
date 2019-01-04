package com.acceso.wfweb.units;

import java.io.Serializable;
import java.util.LinkedHashMap;

public class Pagina extends HTMLRenderer implements Serializable {

    int co_pagina;
    String no_pagtit;
    LinkedHashMap<String, Fila> ultraFilas;

    public Pagina(int co_pagina, String no_pagtit, LinkedHashMap<String, Fila> ultraFilas) {
        this.co_pagina = co_pagina;
        this.no_pagtit = no_pagtit;
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

        html += "<table id=PAG" + co_pagina + " class=\"w3-table-all w3-tiny w3-hoverable w3-card-4\">";
        html += "<thead>";
        html += "<tr>";
        html += "<th colspan=2 >";
        html += "<h3>" + no_pagtit + "</h3>";
        html += "</th>";
        html += "<tr>";
        html += "</tr>";
        html += "</thead>";
        html += "<tbody>";

        for (Fila fila : ultraFilas.values()) {
            html += fila.toHTML();
        }

        html += "</tbody>";
        html += "</table>";

        return html;
    }
}
