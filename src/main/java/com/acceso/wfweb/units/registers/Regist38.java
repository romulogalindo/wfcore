package com.acceso.wfweb.units.registers;

import com.acceso.wfweb.dtos.RegistroDTO;
import com.acceso.wfweb.units.HTMLRenderer;

public class Regist38 extends HTMLRenderer {
    RegistroDTO registroDTO;
    String id;

    public Regist38(String id, RegistroDTO registroDTO) {
        this.id = id;
        this.registroDTO = registroDTO;
    }

    @Override
    public String toHTML() {
        String html = "";
        switch (registroDTO.getTi_estreg()) {
            case "O": {
                html += "<tr name=" + id + " style=\"display:none;\">";
                html += "<td name=" + id + "K class=wf_f_titreg>";
                html += registroDTO.getNo_pagreg();
                html += "</td>";

                html += "<td class=wf_f_valreg>";
                html += "<input type=hidden id='" + id + "V' class=\"pagreg\" name='" + id + "V' value=>";
                html += "</td>";
                html += "</tr>";
                break;
            }

            case "E":
            case "L": {
                html += "<tr name=" + id + " style=\"display:none;\">";
                html += "   <td name=" + id + "K class=wf_f_titreg>";
                html += registroDTO.getNo_pagreg();
                html += "   </td>";

                html += "   <td class=wf_f_valreg>";
                html += "       <span id='" + id + "V' name='" + id + "V' ti_pagreg=\"38\" >" +
                        "           [<a target=\"_blank\" href=\"#\" class=\"pagreg\" ><i class=\"fa fa-picture-o\" aria-hidden=\"true\"></i> Ver</a>]";
//                html += "       &nbsp;&nbsp;";
//                html += "[      <a id='" + id + "V' target=\"_blank\" href=\"#\" class=\"pagreg\" name='" + id + "V' ti_pagreg=\"38\"><i class=\"fa fa-picture-o\" aria-hidden=\"true\"></i> Descargar</a>]";
                html += "       </span>";
                html += "   </td>";

                html += "</tr>";
                break;
            }
        }

        return html;
    }
}
