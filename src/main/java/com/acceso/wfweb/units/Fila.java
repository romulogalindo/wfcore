package com.acceso.wfweb.units;

import com.acceso.wfweb.dtos.RegistroDTO;
import com.acceso.wfweb.dtos.TituloDTO;

public class Fila extends HTMLRenderer {
    TituloDTO tituloDTO;
    RegistroDTO registroDTO;

    public Fila(TituloDTO tituloDTO) {
        this.tituloDTO = tituloDTO;
    }

    public Fila(RegistroDTO registroDTO){
        this.registroDTO=registroDTO;
    }
    @Override
    public String toHTML() {
        String html = "";
        html += "<tr>";
        if (tituloDTO != null) {
            html += "<td colspan=2 class=wf_f_stitle>";
            html += tituloDTO.getNo_pagtit();
            html += "</td>";
        }else if(registroDTO !=null){
            html += "<td class=wf_f_titreg>";
            html += registroDTO.getNo_pagreg();
            html += "</td>";

            html += "<td class=wf_f_titreg>";
            html += registroDTO.getNo_pagreg();
            html += "</td>";
        }

        html += "</tr>";
        return html;
    }
}
