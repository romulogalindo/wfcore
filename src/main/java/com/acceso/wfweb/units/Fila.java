package com.acceso.wfweb.units;

import com.acceso.wfweb.dtos.BotonDTO;
import com.acceso.wfweb.dtos.RegistroDTO;
import com.acceso.wfweb.dtos.TituloDTO;
import com.acceso.wfweb.units.registers.Regist1;
import com.acceso.wfweb.units.registers.Regist22;

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

        if (tituloDTO != null) {
            html += "<tr name=" + id + ">";
            html += "<th colspan=2 class=\"wf_f_stitle w3-highway-blue\" >";
            html += tituloDTO.getNo_pagtit();
            html += "</th>";
            html += "</tr>";

        } else if (registroDTO != null) {
            switch (registroDTO.getTi_pagreg()){
                case 1:{
                    html += new Regist1(id, registroDTO).toHTML();
                    break;}
                case 22:{
                    html += new Regist22(id, registroDTO).toHTML();
                    break;}
            }

        } else if (botonDTOS != null) {
            html += "<tr name=" + id + ">";
            html += "<td name=" + id + "K class=wf_f_titreg></td>";

            html += "<td class=wf_f_valreg>";
            for (BotonDTO botonDTO : botonDTOS) {
                html += "<botton name=" + id + "B class=\"w3-button w3-ripple w3-tiny w3-teal\" onclick=\"propag(" + botonDTO.getCo_pagbot() + "," + botonDTO.isIl_proces() + ", " + botonDTO.getCo_condes() + ")\" >" +
                        "<i class=\"fa fa-hand-pointer-o\" aria-hidden=\"true\"></i>\n" +
                        botonDTO.getNo_pagbot() +
                        "</botton>";
            }

            html += "</td>";
            html += "</tr>";
        }

        return html;
    }
}
