package com.acceso.wfweb.units.registers;

import com.acceso.wfweb.dtos.WRegistroDTO;
import com.acceso.wfweb.units.HTMLRenderer;

public class Regist7 extends HTMLRenderer {
    WRegistroDTO registroDTO;
    String id;

    public Regist7(String id, WRegistroDTO registroDTO) {
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
                html += "<span id='" + id + "V' name='" + id + "V' class=\"pagreg\" ti_pagreg=\"7\" >";
                html += "<input type=text id=\"" + id + "V_dd\" class=\"wf_box_length2 wf_inline w3-input w3-border\" placeholder=\"dd\"/>";
                html += "<span>/</span>";
                html += "<input type=text id=\"" + id + "V_mm\" class=\"wf_box_length2 wf_inline w3-input w3-border\" placeholder=\"mm\"/>";
                html += "<span>/</span>";
                html += "<input type=text id=\"" + id + "V_yyyy\" class=\"wf_box_length4 wf_inline w3-input w3-border\" placeholder=\"yyyy\"/>";
                html += "<span id=\"" + id + "V_btn\" class=\"wf-cal wf_inline\" title=\"Cambiar fecha\"><i class=\"fas fa-calendar-alt\"></i></span>";
                html += "<input type=hidden id=\"" + id + "V_date\" class=\"w3-input w3-border\" />";
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
                html += "<span id='" + id + "V' name='" + id + "V' class=\"pagreg\" ti_pagreg=\"6\" >";
                html += "<input type=checkbox class=\"w3-input w3-border\" disabled/>";
                html += "</span>";
                html += "</td>";
                html += "</tr>";
                break;
            }
        }

        return html;
    }
}
