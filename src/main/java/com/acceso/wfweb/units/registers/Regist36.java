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
                html += "       <span id='" + id + "V' name='" + id + "V' class=\"writer " + (registroDTO.isIl_onchan() ? "dynpag" : "") + " pagreg alert alert-primary\" ti_pagreg=\"36\" style=\"padding: 0px 2px 0px 10px;border-color:#83bdfc\" >"
                        + "         <a  href=\"#\"  onclick=\"return false\" style=\"text-decoration: none !important;\" va_pagreg=\"\">"
                        + "             <i id=\""+ id +"_img\" class=\"\" aria-hidden=\"true\"></i>"
                        + "             <span id=\""+ id +"_text\" title=\"\" data-toggle=\"tooltip\">Sube tu archivo<span>"
                        + "          </a>"

                        + "          <span style=\"border-right: 1px solid #83bdfc; margin: 0 5px;\"></span>"

                        + "         <button id=\""+ id +"_upload\"onclick=\"return doupload64('" + id + "V')\" class=\"wf-button-transparent\" title=\"Selecciona tu archivo\" data-toggle=\"tooltip\">"
                        + "             <i class=\"fas fa-cloud-upload-alt\" aria-hidden=\"true\" ></i>"
                        + "          </button>"

                        + "          <span style=\"border-right: 1px solid #83bdfc; margin: 0 5px 0px 0px;\"></span>"

                        + "         <button id=\""+id+"_delete\"onclick=\"return doclean64('" + id + "V')\" class=\"wf-button-transparent\" title=\"Borrar el archivo actual\" data-toggle=\"tooltip\" disabled>"
                        + "             <i class=\"far fa-trash-alt\" aria-hidden=\"true\" ></i>"
                        + "         </button>"

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
