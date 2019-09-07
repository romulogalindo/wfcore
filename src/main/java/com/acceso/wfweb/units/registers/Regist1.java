package com.acceso.wfweb.units.registers;

import com.acceso.wfweb.dtos.WRegistroDTO;
import com.acceso.wfweb.units.HTMLRegistRenderer;
import com.acceso.wfweb.units.HTMLRenderer;

public class Regist1 extends HTMLRegistRenderer {

    public Regist1(String id, WRegistroDTO registroDTO) {
        super(id, registroDTO);
    }

    @Override
    public String toHTML() {
        String html = "";
        switch (this.registroDTO.getTi_estreg()) {
            case "O": {
                html += "<tr name=\"" + id + "\" style=\"display:none;\" co_pagtit=\"" + registroDTO.getCo_pagtit() + "\">";
                html += "   <td name=\"" + id + "K\" class=\"wf_f_titreg\">";
                html += registroDTO.getNo_pagreg();
//                html += "       <span class=\"w3-bar\" style=\"border-right:2px solid #00477e;padding-left: 5px;\"></span>";
                html += "       <span class=\"w3-bar\"></span>";
                html += "   </td>";

                html += "   <td class=wf_f_valreg>";
                html += "       <span id='" + id + "V' name='" + id + "V' ti_pagreg=\"1\" class=\"hidden xaction pagreg\" >";
                html += "           <div class=\"md-form mt-0\" style=\"margin-bottom: 0px;\">";
                html += "               <input type=\"text\" class=\"w3-input w3-border form-control " + (registroDTO.isIl_onchan() ? "dynpag" : "") + "\" " + (registroDTO.isIl_onchan() ? "onblur=\"dinpag(this," + registroDTO.getCo_pagreg() + ")\"" : "") + " value=\"\">";
                html += "           </div>";
                html += "       </span>";

                html += "   </td>";
                html += "</tr>";
                break;
            }
            case "E": {
                html += "<tr name=" + id + " style=\"display:none;\" co_pagtit=\"" + registroDTO.getCo_pagtit() + "\">";
                html += "   <td name=" + id + "K class=wf_f_titreg>";
                html += registroDTO.getNo_pagreg();
                html += "       <span style=\"border-right:2px solid #00477e;padding-left: 5px;\"></span>";
                html += "   </td>";

                html += "   <td class=wf_f_valreg>";
                html += "       <span id='" + id + "V' name='" + id + "V' ti_pagreg=\"1\" class=\"writer xaction pagreg\" >";
                html += "           <div class=\"md-form mt-0\" style=\"margin-bottom: 0px;\">";
                html += "               <input type=text class=\"w3-input w3-border form-control " + (registroDTO.isIl_onchan() ? "dynpag" : "") + "\" " + (registroDTO.isIl_onchan() ? "onblur=dinpag(this," + registroDTO.getCo_pagreg() + ")" : "") + " value='' maxlength='" + registroDTO.getCa_caract() + "'>";
                html += "           </div>";
                html += "       </span>";
                html += "   </td>";
                html += "</tr>";
                break;
            }
            case "L": {
                html += "<tr name=" + id + " style=\"display:none;\" co_pagtit=\"" + registroDTO.getCo_pagtit() + "\">";
                html += "   <td name=" + id + "K class=wf_f_titreg>";
                html += registroDTO.getNo_pagreg();
                html += "       <span style=\"border-right:2px solid #00477e;padding-left: 5px;\"></span>";
                html += "   </td>";

                html += "   <td class=wf_f_valreg>";
                html += "       <span id='" + id + "V' class=\"reader pagreg\" name='" + id + "V' va_pagreg=\"\" ti_pagreg=\"1\"></span>";
                html += "   </td>";
                html += "</tr>";
                break;
            }
        }

        return html;
    }

    @Override
    public String HTMLforForm() {
        String html = "";
        switch (registroDTO.getTi_estreg()) {
            case HTMLRegistRenderer.TYPE_HIDDEN: {
                html += "<tr name=\"" + id + "\" style=\"display:none;\" co_pagtit=\"" + registroDTO.getCo_pagtit() + "\">";
                html += "   <td name=\"" + id + "K\" class=\"wf_f_titreg\">";
                html += registroDTO.getNo_pagreg();
                html += "       <span style=\"border-right:2px solid #00477e;padding-left: 5px;\"></span>";
                html += "   </td>";

                html += "   <td class=wf_f_valreg>";
                html += "       <span id='" + id + "V' name='" + id + "V' ti_pagreg=\"1\" class=\"hidden xaction pagreg\" >";
                html += "           <div class=\"md-form mt-0\" style=\"margin-bottom: 0px;\">";
                html += "               <input type=\"text\" class=\"w3-input w3-border form-control " + (registroDTO.isIl_onchan() ? "dynpag" : "") + "\" " + (registroDTO.isIl_onchan() ? "onblur=\"dinpag(this," + registroDTO.getCo_pagreg() + ")\"" : "") + " value=\"\">";
                html += "           </div>";
                html += "       </span>";

                html += "   </td>";
                html += "</tr>";
                break;
            }
            case HTMLRegistRenderer.TYPE_WRITER: {
                html += "<tr name=" + id + " style=\"display:none;\" co_pagtit=\"" + registroDTO.getCo_pagtit() + "\">";
                html += "   <td name=" + id + "K class=wf_f_titreg>";
                html += registroDTO.getNo_pagreg();
                html += "       <span style=\"border-right:2px solid #00477e;padding-left: 5px;\"></span>";
                html += "   </td>";

                html += "   <td class=wf_f_valreg>";
                html += "       <span id='" + id + "V' name='" + id + "V' ti_pagreg=\"1\" class=\"writer xaction pagreg\" >";
                html += "           <div class=\"md-form mt-0\" style=\"margin-bottom: 0px;\">";
                html += "               <input type=text class=\"w3-input w3-border form-control " + (registroDTO.isIl_onchan() ? "dynpag" : "") + "\" " + (registroDTO.isIl_onchan() ? "onblur=dinpag(this," + registroDTO.getCo_pagreg() + ")" : "") + " value=>";
                html += "           </div>";
                html += "       </span>";
                html += "   </td>";
                html += "</tr>";
                break;
            }
            case HTMLRegistRenderer.TYPE_READER: {
                html += "<tr name=" + id + " style=\"display:none;\" co_pagtit=\"" + registroDTO.getCo_pagtit() + "\">";
                html += "   <td name=" + id + "K class=wf_f_titreg>";
                html += registroDTO.getNo_pagreg();
                html += "       <span style=\"border-right:2px solid #00477e;padding-left: 5px;\"></span>";
                html += "   </td>";

                html += "   <td class=wf_f_valreg>";
                html += "       <span id='" + id + "V' class=\"reader pagreg\" name='" + id + "V' va_pagreg=\"\" ti_pagreg=\"1\"></span>";
                html += "   </td>";
                html += "</tr>";
                break;
            }
        }

        return html;
    }

    @Override
    public String HTMLforReport() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
