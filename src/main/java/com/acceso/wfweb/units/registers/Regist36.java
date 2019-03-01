package com.acceso.wfweb.units.registers;

import com.acceso.wfweb.dtos.RegistroDTO;
import com.acceso.wfweb.units.HTMLRenderer;

public class Regist36 extends HTMLRenderer {
    RegistroDTO registroDTO;
    String id;

    public Regist36(String id, RegistroDTO registroDTO) {
        this.id = id;
        this.registroDTO = registroDTO;
    }

    @Override
    public String toHTML() {
        String html = "";
        switch (registroDTO.getTi_estreg()) {
            case "O": {
                html += "<tr name=" + id + " style=\"display:none;\" co_pagtit=\"" + registroDTO.getCo_pagtit() + "\">";
                html += "   <td name=" + id + "K class=wf_f_titreg>";
                html += registroDTO.getNo_pagreg();
                html += "   </td>";

                html += "   <td class=wf_f_valreg>";
                html += "       <input type=hidden id='" + id + "V' class=\"pagreg\" name='" + id + "V' value=>";
                html += "   </td>";
                html += "</tr>";
                break;
            }
            case "E": {
                html += "<tr name=" + id + " style=\"display:none;\" co_pagtit=\"" + registroDTO.getCo_pagtit() + "\">";
                html += "   <td name=" + id + "K class=wf_f_titreg>";
                html += registroDTO.getNo_pagreg();
                html += "   </td>";

                html += "   <td class=wf_f_valreg>";
                html += "       <span id='" + id + "V' name='" + id + "V' ti_pagreg=\"36\" >" +
                        "   <a  href=\"#\" class=\"pagreg\" onclick=\"return doupload('" + id + "V')\" va_pagreg=\"\">" +
                        "<i class=\"fa fa-upload\" aria-hidden=\"true\"></i> <span>Subir archivo<span></a>" +
                        "   <iframe src=\"/jsp_exec/ocelot/upload.jsp?id=" + id + "'\" style=\"display:none;\"></iframe>" +
                        "</span>";
                html += "   </td>";
                html += "</tr>";
                break;
            }
            case "L": {
                html += "<tr name=" + id + " style=\"display:none;\" co_pagtit=\"" + registroDTO.getCo_pagtit() + "\">";
                html += "   <td name=" + id + "K class=wf_f_titreg>";
                html += registroDTO.getNo_pagreg();
                html += "   </td>";

                html += "   <td class=wf_f_valreg>";
                html += "       <span id='" + id + "V' name='" + id + "V' ti_pagreg=\"13\" >" +
                        "   [<a  target=\"_blank\" href=\"#\" class=\"pagreg\"  ><i class=\"fa fa-picture-o\" aria-hidden=\"true\"></i> Descargar</a>]" +
                        "</span>";
                html += "   </td>";
                html += "</tr>";
                break;
            }
        }

        return html;
    }
}
