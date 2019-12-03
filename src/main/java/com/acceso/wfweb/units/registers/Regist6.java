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
                html += "       <span style=\"border-right:2px solid #00477e;padding-left: 5px;\"></span>";
                html += "   </td>";

                html += "   <td class=wf_f_valreg>";
                html += "       <span id='" + id + "V' name='" + id + "V' ti_pagreg='6' class='hidden xaction pagreg' >";
                html += "           <div class='md-form mt-0' style='margin-bottom: 0px;'>";
                html += "               <input id='" + id + "VD' type='checkbox' class='w3-input w3-border form-control " + (registroDTO.isIl_onchan() ? "dynpag" : "") + "' " + (registroDTO.isIl_onchan() ? "onblur='dinpag(this," + registroDTO.getCo_pagreg() + ")'" : "") + ">";
                html += "               <label class='custom-control-label' for='" + id + "VD'></label>";
                html += "           </div>";
                html += "       </span>";

                html += "   </td>";
                html += "</tr>";
                break;
            }
            case "E": {
                html += "<tr name=" + id + " style=\"display:none;\" co_pagtit=\"" + registroDTO.getCo_pagtit() + "\">";
                html += "<td name=" + id + "K class=wf_f_titreg>";
                html += registroDTO.getNo_pagreg();
                html += "       <span style=\"border-right:2px solid #00477e;padding-left: 5px;\"></span>";
                html += "</td>";

                html += "<td class=wf_f_valreg>";
                html += "   <span id='" + id + "V' name='" + id + "V' class='writer " + (registroDTO.isIl_onchan() ? "xaction" : "") + " pagreg' ti_pagreg='6' >";
                html += "       <div class='custom-control custom-checkbox'>";
                html += "           <input id='" + id + "VD' type='checkbox' class='w3-input " + (registroDTO.isIl_onchan() ? "dynpag" : "") + " custom-control-input' " + (registroDTO.isIl_onchan() ? "onchange=dinpag(this," + registroDTO.getCo_pagreg() + ")" : "") + " checked disabled/>";
                html += "           <label class='custom-control-label' for='" + id + "VD'></label>";
                html += "       </div>";
                html += "   </span>";
                html += "</td>";
                html += "</tr>";
                break;
            }
            case "L": {
                html += "<tr name=" + id + " style=\"display:none;\" co_pagtit=\"" + registroDTO.getCo_pagtit() + "\">";
                html += "<td name=" + id + "K class=wf_f_titreg>";
                html += registroDTO.getNo_pagreg();
                html += "       <span style=\"border-right:2px solid #00477e;padding-left: 5px;\"></span>";
                html += "</td>";

                html += "<td class=wf_f_valreg>";
                html += "   <span id='" + id + "V' name='" + id + "V' class='reader " + (registroDTO.isIl_onchan() ? "xaction" : "") + " pagreg' ti_pagreg='6' >";
                html += "       <div class='custom-control custom-checkbox'>";
                html += "           <input id='" + id + "VD' type=\"checkbox\" class='w3-input " + (registroDTO.isIl_onchan() ? "dynpag" : "") + " custom-control-input' " + (registroDTO.isIl_onchan() ? "onchange=dinpag(this," + registroDTO.getCo_pagreg() + ")" : "") + " checked disabled/>";
                html += "           <label class='custom-control-label' for='" + id + "VD'></label>";
                html += "       </div>";
                html += "   </span>";
                html += "</td>";
                html += "</tr>";
                break;
            }
        }

        return html;
    }
}
