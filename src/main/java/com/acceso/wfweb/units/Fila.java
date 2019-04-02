package com.acceso.wfweb.units;

import com.acceso.wfweb.dtos.WBotonDTO;
import com.acceso.wfweb.dtos.WParametroDTO;
import com.acceso.wfweb.dtos.WRegistroDTO;
import com.acceso.wfweb.dtos.TituloDTO;
import com.acceso.wfweb.units.registers.*;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.List;

public class Fila extends HTMLRenderer implements Serializable {

    TituloDTO tituloDTO;
    WRegistroDTO registroDTO;
    List<WBotonDTO> botonDTOS;
    String id;
    //SOlo para tipo tabla
    int colspan;

    public Fila(TituloDTO tituloDTO, String id) {
        this.tituloDTO = tituloDTO;
        this.id = id;
    }

    public Fila(WRegistroDTO registroDTO, String id) {
        this.registroDTO = registroDTO;
        this.id = id;
    }

    public Fila(List<WBotonDTO> botonDTOS, String id) {
        this.botonDTOS = botonDTOS;
        this.id = id;
    }

    public TituloDTO getTituloDTO() {
        return tituloDTO;
    }

    public void setTituloDTO(TituloDTO tituloDTO) {
        this.tituloDTO = tituloDTO;
    }

    public WRegistroDTO getRegistroDTO() {
        return registroDTO;
    }

    public void setRegistroDTO(WRegistroDTO registroDTO) {
        this.registroDTO = registroDTO;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getColspan() {
        return colspan;
    }

    public void setColspan(int colspan) {
        this.colspan = colspan;
    }

    @Override
    public String toHTML() {
        String html = "";

        if (tituloDTO != null) {
            html += "<tr name=" + id + " style=\"display:none;\" co_pagtit=\"" + tituloDTO.getCo_pagtit() + "\">";
            html += "<th colspan=2 class=\"wf_f_stitle w3-highway-blue\" >";
            html += tituloDTO.getNo_pagtit();
            html += "</th>";
            html += "</tr>";

        } else if (registroDTO != null) {

            try {
                html += ((HTMLRenderer) Class.forName("com.acceso.wfweb.units.registers.Regist" + registroDTO.getTi_pagreg())
                        .getConstructor(String.class, WRegistroDTO.class)
                        .newInstance(id, registroDTO))
                        .toHTML();
            } catch (Exception ep) {
                html += new Regist1(id, registroDTO).toHTML();
            }

        } else if (botonDTOS != null) {
            html += "</tbody>";
            html += "<tfoot>";
            html += "<tr name=" + id + ">";
            html += "   <td name=" + id + "K class=wf_f_titreg></td>";

            html += "<td class=wf_f_valreg>";
            html += "<script> var cfila=1;</script>";

            for (WBotonDTO botonDTO : botonDTOS) {

                html += "<script> var " + id + botonDTO.getCo_pagbot() + "P=[];";
                for (WParametroDTO parametroDTO : botonDTO.getParametros()) {
                    html += "" + id + botonDTO.getCo_pagbot() + "P[" + id + botonDTO.getCo_pagbot() + "P.length] = new Parameter(" + parametroDTO.getCo_pagreg() + "," + parametroDTO.getCo_conpar() + ");";
                }
                html += "</script>";

                html += "<button name=" + id + botonDTO.getCo_pagbot() + " class=\"btn btn-default\" onclick=\"propag(\'C1\'," + botonDTO.getCo_pagbot() + "," + botonDTO.isIl_proces() + ", " + botonDTO.getCo_condes() + ")\" >"
                        + "<i class=\"fa fa-hand-pointer-o\" aria-hidden=\"true\"></i>\n"
                        + botonDTO.getNo_pagbot()
                        + "</button>";
            }

            html += "   </td>";
            html += "</tr>";
            html += "</tfoot>";
        }

        return html;
    }
}
