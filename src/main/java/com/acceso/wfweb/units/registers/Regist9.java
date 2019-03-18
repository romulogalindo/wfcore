package com.acceso.wfweb.units.registers;

import com.acceso.wfweb.dtos.RegistroDTO;
import com.acceso.wfweb.units.HTMLRenderer;

public class Regist9 extends HTMLRenderer {
    RegistroDTO registroDTO;
    String id;

    public Regist9(String id, RegistroDTO registroDTO) {
        this.id = id;
        this.registroDTO = registroDTO;
    }

    @Override
    public String toHTML() {
        String html = "";
        switch (registroDTO.getTi_estreg()) {
            case "O": {
                html += "<tr name=" + id + " style=\"display:none;\" co_pagtit=\"" + registroDTO.getCo_pagtit() + "\">";
                html += "<td name=" + id + "K class=wf_f_titreg>";
                html += registroDTO.getNo_pagreg();
                html += "</td>";

                html += "<td class=wf_f_valreg>";
                html += "<input type=hidden id='" + id + "V' class=\"pagreg\" name='" + id + "V' value=>";
                html += "</td>";
                html += "</tr>";
                break;
            }
            case "E": {
                html += "<tr name=" + id + " style=\"display:none;\" co_pagtit=\"" + registroDTO.getCo_pagtit() + "\">";
                html += "<td name=" + id + "K class=wf_f_titreg>";
                html += registroDTO.getNo_pagreg();
                html += "</td>";

                html += "<td class=wf_f_valreg>";
                html += "<span id='" + id + "V' name='" + id + "V' class=\"pagreg\" ti_pagreg=\"9\" >";
                html += "<textarea class=\"w3-input w3-border\" rows=\"" + registroDTO.getCa_carrow() + "\" cols=\"" + registroDTO.getCa_carcol() + "\"></textarea>";
                html += "</span>";
                html += "</td>";
                html += "</tr>";
                break;
            }
            case "L": {
                html += "<tr name=" + id + " style=\"display:none;\" co_pagtit=\"" + registroDTO.getCo_pagtit() + "\">";
                html += "<td name=" + id + "K class=wf_f_titreg>";
                html += registroDTO.getNo_pagreg();
                html += "</td>";

                html += "<td class=wf_f_valreg>";
                html += "<span id='" + id + "V' name='" + id + "V' class=\"pagreg\" ti_pagreg=\"9\" >";
                html += "<textarea class=\"pagreg w3-input w3-border\" rows=\"" + registroDTO.getCa_carrow() + "\" cols=\"" + registroDTO.getCa_carcol() + "\"></texarea>";
                html += "</span>";
                html += "</td>";
                html += "</tr>";
                break;
            }
        }

        return html;
    }
}
