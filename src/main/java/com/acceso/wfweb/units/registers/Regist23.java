package com.acceso.wfweb.units.registers;

import com.acceso.wfweb.dtos.WRegistroDTO;
import com.acceso.wfweb.units.HTMLRenderer;

public class Regist23 extends HTMLRenderer {
    WRegistroDTO registroDTO;
    String id;

    public Regist23(String id, WRegistroDTO registroDTO) {
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
                html += "   <span id='" + id + "V' name='" + id + "V' ti_pagreg='23' class='hidden xaction pagreg' >";
                html += "       <div class='md-form mt-0' style='margin-bottom: 0px;'>";
                html += "           <input type='text' class='w3-input w3-border form-control " + (registroDTO.isIl_onchan() ? "dynpag" : "") + "' " + (registroDTO.isIl_onchan() ? "onblur='dinpag(this," + registroDTO.getCo_pagreg() + ")'" : "") + " value=''>";
                html += "       </div>";
                html += "   </span>";

                html += "</td>";
                html += "</tr>";
                break;
            }
            case "E": {
                html += "<tr name=" + id + " style='display:none;' co_pagtit='" + registroDTO.getCo_pagtit() + "'>";
                html += "<td name=" + id + "K class=wf_f_titreg>";
                html += registroDTO.getNo_pagreg();
                html += "<span style='border-right:2px solid #00477e;padding-left: 5px;'></span>";
                html += "</td>";

                html += "<td class=wf_f_valreg>";
                html += "   <span id='" + id + "V' name='" + id + "V' ti_pagreg='23' class='writer xaction pagreg' >";
                html += "       <div class='md-form mt-0' style='margin-bottom: 0px;'>";
                html += "           <input type='text' class='w3-input w3-border form-control " + (registroDTO.isIl_onchan() ? "dynpag" : "") + "' " + (registroDTO.isIl_onchan() ? "onblur='extractNumber(this, -1, true);dinpag(this," + registroDTO.getCo_pagreg() + ")'" : "") + " onkeyup='extractNumber(this, -1, true);' onkeypress='return blockNonNumbers(this, event, true, true);' style='text-align:center;' value=''>";
                html += "       </div>";
                html += "   </span>";
                html += "</td>";
                html += "</tr>";
                break;
            }
            case "L": {
                html += "<tr name=" + id + " style='display:none;' co_pagtit='" + registroDTO.getCo_pagtit() + "'>";
                html += "<td name=" + id + "K class=wf_f_titreg>";
                html += registroDTO.getNo_pagreg();
                html += "<span style='border-right:2px solid #00477e;padding-left: 5px;'></span>";
                html += "</td>";

                html += "<td class=wf_f_valreg>";
                html += "<span id='" + id + "V' class='reader pagreg' name='" + id + "V' va_pagreg='' ti_pagreg='22'></span>";
                html += "</td>";
                html += "</tr>";
                break;
            }
        }

        return html;
    }
}
