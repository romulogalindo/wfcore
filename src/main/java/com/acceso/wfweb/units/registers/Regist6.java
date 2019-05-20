package com.acceso.wfweb.units.registers;

import com.acceso.wfweb.dtos.WRegistroDTO;
import com.acceso.wfweb.units.HTMLRenderer;

public class Regist6 extends HTMLRenderer {

    WRegistroDTO registroDTO;
    String id;

    public Regist6(String id, WRegistroDTO registroDTO) {
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
                html += "<span id='" + id + "V' name='" + id + "V' class='pagreg' ti_pagreg='6' >";
                html += "<div class='custom-control custom-switch'>";
                html += "<input id='" + id + "VD' type=checkbox class='w3-input  custom-control-input' checked/>";
                html += "<label class='custom-control-label' for='" + id + "VD'></label>";
                html += "</div>";
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
                html += "<span id='" + id + "V' name='" + id + "V' class='pagreg' ti_pagreg='6' >";
                html += "<div class='custom-control custom-switch'>";
                html += "<input id='" + id + "VD' type=checkbox class='w3-input  custom-control-input' checked disabled/>";
                html += "<label class='custom-control-label' for='" + id + "VD'></label>";
                html += "</div>";
                html += "</span>";
                html += "</td>";
                html += "</tr>";
                break;
            }
        }

        return html;
    }
}
