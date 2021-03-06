package com.acceso.wfweb.units.registers;

import com.acceso.wfweb.dtos.WRegistroDTO;
import com.acceso.wfweb.units.HTMLRenderer;

public class Regist9 extends HTMLRenderer {

    WRegistroDTO registroDTO;
    String id;

    public Regist9(String id, WRegistroDTO registroDTO) {
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
                html += "<span style=\"border-right:2px solid #00477e;padding-left: 5px;\"></span>";
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
                html += "<span style=\"border-right:2px solid #00477e;padding-left: 5px;\"></span>";
                html += "</td>";

                html += "<td class=wf_f_valreg>";
                html += "<span id='" + id + "V' name='" + id + "V' class=\"writer " + (registroDTO.isIl_onchan() ? "xaction" : "") + " pagreg\" ti_pagreg=\"9\" >";
//                html += "<textarea class=\"w3-input w3-border\" rows=\"" + registroDTO.getCa_carrow() + "\" cols=\"" + registroDTO.getCa_carcol() + "\"></textarea>";
                html += "<textarea class=\"w3-input w3-border\" rows=\"" + registroDTO.getCa_carrow() + "\"></textarea>";
                html += "</span>";
                html += "</td>";
                html += "</tr>";
                break;
            }
            case "L": {
                html += "<tr name=" + id + " style=\"display:none;\" co_pagtit=\"" + registroDTO.getCo_pagtit() + "\">";
                html += "<td name=" + id + "K class=wf_f_titreg>";
                html += registroDTO.getNo_pagreg();
                html += "<span style=\"border-right:2px solid #00477e;padding-left: 5px;\"></span>";
                html += "</td>";

                html += "<td class=wf_f_valreg>";
                html += "<span id='" + id + "V' name='" + id + "V' class=\"reader " + (registroDTO.isIl_onchan() ? "xaction" : "") + " pagreg\" ti_pagreg=\"9\" >";
//                html += "<textarea class=\"pagreg w3-input w3-border\" rows=\"" + registroDTO.getCa_carrow() + "\" cols=\"" + registroDTO.getCa_carcol() + "\"></texarea>";
                html += "<textarea class=\"pagreg w3-input w3-border\" rows=\"" + registroDTO.getCa_carrow() + "\"></texarea>";
                html += "</span>";
                html += "</td>";
                html += "</tr>";
                break;
            }
        }

        return html;
    }
}
