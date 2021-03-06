package com.acceso.wfweb.units.registers;

import com.acceso.wfweb.dtos.WRegistroDTO;
import com.acceso.wfweb.units.HTMLRenderer;

public class Regist4 extends HTMLRenderer {

    WRegistroDTO registroDTO;
    String id;

    public Regist4(String id, WRegistroDTO registroDTO) {
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
                html += "   <span id='" + id + "V' name='" + id + "V' ti_pagreg=\"4\" class=\"hidden " + (registroDTO.isIl_onchan() ? "xaction" : "") + " pagreg\" >";
                html += "       <select class=\"mdb-select md-formx " + (registroDTO.isIl_onchan() ? "dynpag" : "") + "\" " + (registroDTO.isIl_onchan() ? "onchange=dinpag(this," + registroDTO.getCo_pagreg() + ")" : "") + "></select>";
                html += "   </span>";
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
                html += "   <span id='" + id + "V' name='" + id + "V' ti_pagreg=\"4\" class=\"writer " + (registroDTO.isIl_onchan() ? "xaction" : "") + " pagreg\" >";
                html += "       <select class=\"mdb-select md-formx " + (registroDTO.isIl_onchan() ? "dynpag" : "") + "\" " + (registroDTO.isIl_onchan() ? "onchange=dinpag(this," + registroDTO.getCo_pagreg() + ")" : "") + " ></select>";
                html += "   </span>";
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
                html += "   <span id='" + id + "V' name='" + id + "V' ti_pagreg=\"4\" class=\"reader " + (registroDTO.isIl_onchan() ? "xaction" : "") + " pagreg\" >";
                html += "       <select class=\"mdb-select md-formx " + (registroDTO.isIl_onchan() ? "dynpag" : "") + "\" " + (registroDTO.isIl_onchan() ? "onchange=dinpag(this," + registroDTO.getCo_pagreg() + ")" : "") + " disabled></select>";
                html += "   </span>";
                html += "</td>";
                html += "</tr>";
                break;
            }
        }

        return html;
    }
}
