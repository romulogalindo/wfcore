package com.acceso.wfweb.units.registers;

import com.acceso.wfweb.dtos.WRegistroDTO;
import com.acceso.wfweb.units.HTMLRenderer;

public class Regist5 extends HTMLRenderer {

    WRegistroDTO registroDTO;
    String id;

    public Regist5(String id, WRegistroDTO registroDTO) {
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
                html += "   <span id='" + id + "V' name='" + id + "V' ti_pagreg=\"5\" class=\"reader " + (registroDTO.isIl_onchan() ? "xaction" : "") + " pagreg\" >";
                html += "       <input type=hidden id=\"" + id + "V_rb\" value=\"PAGREG5\"/>";
                html += "   </span>";
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
                html += "   <span id='" + id + "V' name='" + id + "V' ti_pagreg=\"5\" class=\"writer " + (registroDTO.isIl_onchan() ? "xaction" : "") + " pagreg\" >";
                html += "       <input type=hidden id=\"" + id + "V_rb\" value=\"PAGREG5\"/>";
                html += "   </span>";
                html += "</td>";
                html += "</tr>";
                break;
            }
        }

        return html;
    }
}
