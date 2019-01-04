package com.acceso.wfweb.units;

import com.acceso.wfweb.dtos.RegistroDTO;
import com.acceso.wfweb.dtos.TituloDTO;

public class Fila extends HTMLRenderer {

    TituloDTO tituloDTO;
    RegistroDTO registroDTO;
    String id;

    public Fila(TituloDTO tituloDTO, String id) {
        this.tituloDTO = tituloDTO;
        this.id = id;
    }

    public Fila(RegistroDTO registroDTO, String id) {
        this.registroDTO = registroDTO;
        this.id = id;
    }

    @Override
    public String toHTML() {
        String html = "";
        html += "<tr name=" + id + ">";

        if (tituloDTO != null) {
            html += "<th colspan=2 class=wf_f_stitle>";
            html += tituloDTO.getNo_pagtit();
            html += "</th>";

        } else if (registroDTO != null) {
            html += "<td name=" + id + "K class=wf_f_titreg>";
            html += registroDTO.getNo_pagreg();
            html += "</td>";

            html += "<td name=" + id + "V class=wf_f_valreg>";
            html += registroDTO.getNo_pagreg();
            html += "</td>";
        }

        html += "</tr>";
        return html;
    }
}
