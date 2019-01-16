package com.acceso.wfweb.units;

import com.acceso.wfweb.dtos.BotonDTO;
import com.acceso.wfweb.dtos.RegistroDTO;
import com.acceso.wfweb.dtos.TituloDTO;

import java.util.List;

public class Fila extends HTMLRenderer {

    TituloDTO tituloDTO;
    RegistroDTO registroDTO;
    List<BotonDTO> botonDTOS;
    String id;
    //SOlo para tipo tabla
    int colspan;


    public Fila(TituloDTO tituloDTO, String id) {
        this.tituloDTO = tituloDTO;
        this.id = id;
    }

    public Fila(RegistroDTO registroDTO, String id) {
        this.registroDTO = registroDTO;
        this.id = id;
    }

    public Fila(List<BotonDTO> botonDTOS, String id) {
        this.botonDTOS = botonDTOS;
        this.id = id;
    }

    public TituloDTO getTituloDTO() {
        return tituloDTO;
    }

    public void setTituloDTO(TituloDTO tituloDTO) {
        this.tituloDTO = tituloDTO;
    }

    public RegistroDTO getRegistroDTO() {
        return registroDTO;
    }

    public void setRegistroDTO(RegistroDTO registroDTO) {
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
        html += "<tr name=" + id + ">";

        if (tituloDTO != null) {
            html += "<th colspan=2 class=\"wf_f_stitle w3-highway-blue\" >";
            html += tituloDTO.getNo_pagtit();
            html += "</th>";

        } else if (registroDTO != null) {
            html += "<td name=" + id + "K class=wf_f_titreg>";
            html += registroDTO.getNo_pagreg();
            html += "</td>";

            html += "<td class=wf_f_valreg>";
            switch (registroDTO.getTi_pagreg()) {
                case 1: {
                    html += "<span name=" + id + "V></span>";
                    break;
                }
            }

            html += "</td>";
        } else if (botonDTOS != null) {
            html += "<td name=" + id + "K class=wf_f_titreg></td>";

            html += "<td class=wf_f_valreg>";
            for (BotonDTO botonDTO : botonDTOS) {
                html += "<botton name=" + id + "B class=\"w3-button w3-tiny w3-teal\" onclick=propag(" + botonDTO.getCo_pagbot() + "," + botonDTO.isIl_proces() + ", " + botonDTO.getCo_condes() + ") >" + botonDTO.getNo_pagbot() + "</span>";
            }

            html += "</td>";
        }

        html += "</tr>";
        return html;
    }
}
