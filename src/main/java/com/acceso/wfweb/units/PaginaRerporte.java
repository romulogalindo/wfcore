package com.acceso.wfweb.units;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class PaginaRerporte extends Pagina implements Serializable {

//    int co_pagina;
//    String no_pagtit;
//    LinkedHashMap<String, Fila> ultraFilas;

    public PaginaRerporte(int co_pagina, String no_pagtit, LinkedHashMap<String, Fila> ultraFilas) {
        this.co_pagina = co_pagina;
        this.no_pagtit = no_pagtit;
        this.ultraFilas = ultraFilas;
    }

    public int getCo_pagina() {
        return this.co_pagina;
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

        LinkedList<Fila> titlelvl1 = new LinkedList<>();

        int colspan = 0;
        for (Fila fila : ultraFilas.values()) {
            //html += fila.toHTML();->thead->lvl1
            if (fila.getTituloDTO() != null) {
//                System.out.println("Titulo>fila = " + fila);
                //es un titulo
                titlelvl1.add(fila);
                colspan = 0;
            } else {
                colspan++;
                titlelvl1.get(titlelvl1.size() - 1).setColspan(colspan);
            }
        }
        html += "<script>var estrategia =null;</script>";


        html += "<table id=PAG" + co_pagina + " class=\"w3-table-all w3-tiny w3-hoverable w3-card-4\">";
        html += "<thead>";
        html += "<tr>";
        html += "<th colspan=" + ultraFilas.size() + " >";
        html += "<h3>" + no_pagtit + "</h3>";
        html += "</th>";
        html += "</tr>";

        html += "<tr>";
        for (Fila fila : titlelvl1) {

            html += "<th colspan=" + fila.getColspan() + " class=\"wf_t_stitle w3-highway-blue\" >";
            html += fila.getTituloDTO().getNo_pagtit();
            html += "</th>";
        }

        html += "</tr>";

        html += "<tr>";
        for (Fila fila : ultraFilas.values()) {

            if (fila.getRegistroDTO() != null) {
                html += "<th>";
                html += "<span>" + fila.getRegistroDTO().getNo_pagreg() + "</span>";
                html += "</th>";
            }
        }
        html += "</tr>";

        html += "</thead>";
        html += "<tbody>";

        html += "</tbody>";
        html += "</table>";

        return html;
    }
}
