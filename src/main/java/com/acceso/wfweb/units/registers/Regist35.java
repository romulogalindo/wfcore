package com.acceso.wfweb.units.registers;

import com.acceso.wfweb.dtos.WRegistroDTO;
import com.acceso.wfweb.units.HTMLRenderer;

public class Regist35 extends HTMLRenderer {
    WRegistroDTO registroDTO;
    String id;

    public Regist35(String id, WRegistroDTO registroDTO) {
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
                html += "   <td name=" + id + "K class=wf_f_titreg>";
                html += registroDTO.getNo_pagreg();
                html += "   </td>";

                html += "   <td class=wf_f_valreg>";
                html += "       <span id='" + id + "V' name='" + id + "V' ti_pagreg=\"35\" >" +
//                        "           <textarea ></textarea>+" +
                            "           <iframe class=\"q f\" src=\"/jsp_exec/ocelot/code.jsp\" style=\"display: block; height: 305px; width: 100%;\" marginheight=\"0\" marginwidth=\"0\" scrolling=\"none\"  ></iframe>";
//                html += "       &nbsp;&nbsp;";
//                html += "[      <a id='" + id + "V' target=\"_blank\" href=\"#\" class=\"pagreg\" name='" + id + "V' ti_pagreg=\"38\"><i class=\"fa fa-picture-o\" aria-hidden=\"true\"></i> Descargar</a>]";
                html += "       </span>";
                html += "   </td>";

                html += "</tr>";
                break;
            }
//            case "L": {
//                html += "<tr name=" + id + " style=\"display:none;\" co_pagtit=\"" + registroDTO.getCo_pagtit() + "\">";
//                html += "   <td name=" + id + "K class=wf_f_titreg>";
//                html += registroDTO.getNo_pagreg();
//                html += "   </td>";
//
//                html += "   <td class=wf_f_valreg>";
//                html += "       <span id='" + id + "V' name='" + id + "V' ti_pagreg=\"34\" >" +
//                        "           <span valpag=\"\"></span>+" +
//                        "           <button class=\"wf-button-transparent\" onclick=\"child_popup(ur_pagreg,'" + id + "',co_conten(),'titulo','" + registroDTO.getNo_pagreg() + "')\" title=\"Abrir\"><i class=\"fa fa-window-restore\" aria-hidden=\"true\"></i></button>";
//                html += "       </span>";
//                html += "   </td>";
//
//                html += "</tr>";
//                break;
//            }
        }

        return html;
    }
}
