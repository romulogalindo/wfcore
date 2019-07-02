package com.acceso.wfweb.units.registers;

import com.acceso.wfweb.dtos.WRegistroDTO;
import com.acceso.wfweb.units.HTMLRenderer;

public class Regist36 extends HTMLRenderer {

    WRegistroDTO registroDTO;
    String id;

    public Regist36(String id, WRegistroDTO registroDTO) {
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
                html += "<span style=\"border-right:2px solid #00477e;padding-left: 5px;\"></span>";
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
                html += "<span style=\"border-right:2px solid #00477e;padding-left: 5px;\"></span>";
                html += "   </td>";

                html += "   <td class=wf_f_valreg>";
                html += "       <span id='" + id + "V' name='" + id + "V' class=\"writer " + (registroDTO.isIl_onchan() ? "dynpag" : "") + " pagreg\" ti_pagreg=\"36\" style=\"border: 1px solid #8a8888;border-radius: 3px;padding: 0px 10px;background: #cdcdcd;\" >"
                        + "         <a  href=\"#\"  onclick=\"return doupload('" + id + "V')\" va_pagreg=\"\">"
                        + "              <span>Sube tu archivo<span>"
//                        + "             <i class=\"fa fa-upload\" aria-hidden=\"true\"></i>"
                        + "          </a>"

//                        + "              <span>Selecciona Archivo<span>"
                        + "         <button onclick=\"return doupload36('" + id + "V')\" class=\"wf-button-transparent\" title=\"Selecciona tu archivo\">"
                        + "             <i class=\"fa fa-upload\" aria-hidden=\"true\" ></i>"
                        + "          </button>"

                        + "         <button onclick=\"return doclean36('" + id + "V')\" class=\"wf-button-transparent\" title=\"Borrar\">"
                        + "         <i class=\"fas fa-eraser\" aria-hidden=\"true\" ></i>"
//                        + "         <i class=\"fas fa-eraser\" aria-hidden=\"true\"></i> <span>Borrar<span>"
                        + "          </button>"

                        + "         <iframe src=\"/jsp_exec/ocelot/upload.jsp?id=" + id + "\" style=\"display:none;\"></iframe>"
                        + "     </span>";
                html += "   </td>";
                html += "</tr>";
                break;
            }
            case "L": {
                html += "<tr name=" + id + " style=\"display:none;\" co_pagtit=\"" + registroDTO.getCo_pagtit() + "\">";
                html += "   <td name=" + id + "K class=wf_f_titreg>";
                html += registroDTO.getNo_pagreg();
                html += "<span style=\"border-right:2px solid #00477e;padding-left: 5px;\"></span>";
                html += "   </td>";

                html += "   <td class=wf_f_valreg>";
                html += "       <span id='" + id + "V' name='" + id + "V' ti_pagreg=\"36\" class=\"reader " + (registroDTO.isIl_onchan() ? "dynpag" : "") + " pagreg\">"
                        + "   [<a  target=\"_blank\" href=\"#\" class=\"pagreg\"  ><i class=\"fa fa-picture-o\" aria-hidden=\"true\"></i> Descargar</a>]"
                        + "</span>";
                html += "   </td>";
                html += "</tr>";
                break;
            }
        }

        return html;
    }
}
