package com.acceso.wfweb.units.registers;

import com.acceso.wfweb.dtos.WRegistroDTO;
import com.acceso.wfweb.units.HTMLRenderer;

public class Regist34 extends HTMLRenderer {

    WRegistroDTO registroDTO;
    String id;

    public Regist34(String id, WRegistroDTO registroDTO) {
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
                html += "       <span id='" + id + "V' name='" + id + "V' ti_pagreg=\"34\" class=\"reader " + (registroDTO.isIl_onchan() ? "dynpag" : "") + " pagreg\" >"
                        + "           <span valpag=\"\"></span>"
                        + "           <button class=\"wf-button-transparent\" onclick=\"child_popup(ur_pagreg,'" + id + "', CO_CONTEN,'titulo','" + registroDTO.getNo_pagreg() + "')\" title=\"Abrir\"><i class=\"fa fa-window-restore\" aria-hidden=\"true\"></i>Abrir</button>"
                        + "           <button class=\"wf-button-transparent\" onclick=\"clean_popup('" + id + "')\" title=\"Limpiar\"><i class=\"fa fa-eraser\" aria-hidden=\"true\"></i>Limpiar</button>";
                html += "       </span>";
                html += "</td>";
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
                html += "       <span id='" + id + "V' name='" + id + "V' ti_pagreg=\"34\" class=\"writer " + (registroDTO.isIl_onchan() ? "dynpag" : "") + " pagreg\">"
                        + "           <span valpag=\"\"></span>"
                        + "           <button class=\"wf-button-transparent\" onclick=\"child_popup(ur_pagreg, '" + id + "', CO_CONTEN,'titulo','" + registroDTO.getNo_pagreg() + "')\" title=\"Abrir\"><i class=\"fa fa-window-restore\" aria-hidden=\"true\"></i>Abrir</button>"
                        + "           <button class=\"wf-button-transparent\" onclick=\"clean_popup('" + id + "')\" title=\"Limpiar\"><i class=\"fa fa-eraser\" aria-hidden=\"true\"></i>Limpiar</button>";
                html += "       </span>";
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
                html += "       <span id='" + id + "V' name='" + id + "V' ti_pagreg=\"34\" class=\"reader " + (registroDTO.isIl_onchan() ? "dynpag" : "") + " pagreg\" >"
                        + "           <span valpag=\"\"></span>"
                        + "           <button class=\"wf-button-transparent\" onclick=\"child_popup(ur_pagreg,'" + id + "',CO_CONTEN,'titulo','" + registroDTO.getNo_pagreg() + "')\" title=\"Abrir\"><i class=\"fa fa-window-restore\" aria-hidden=\"true\"></i>Abrir</button>"
                        + "           <button class=\"wf-button-transparent\" onclick=\"clean_popup('" + id + "')\" title=\"Limpiar\"><i class=\"fa fa-eraser\" aria-hidden=\"true\"></i>Limpiar</button>";
                html += "       </span>";
                html += "   </td>";
                html += "</tr>";
                break;
            }
        }

        return html;
    }
}
