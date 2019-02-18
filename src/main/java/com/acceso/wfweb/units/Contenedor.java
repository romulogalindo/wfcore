package com.acceso.wfweb.units;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class Contenedor extends HTMLRenderer implements Serializable {

    //objeto de distribucion(ahora solo vertical)

    LinkedHashMap<Integer, Pagina> paginas;
    int co_conten;
    long id_frawor;
    String co_contit;
    boolean il_header;

    public Contenedor(int co_conten, long id_frawor, String co_contit) {
        this.co_conten = co_conten;
        this.id_frawor = id_frawor;
        this.co_contit = co_contit;

//        this.paginas = new ArrayList<>();
        this.paginas = new LinkedHashMap<>();
    }

    public void addPagina(Pagina pagina) {
        paginas.put(pagina.getCo_pagina(), pagina);
    }

    public Pagina getPagina(int co_pagina) {
        return paginas.get(co_pagina);
    }

    public int getCo_conten() {
        return co_conten;
    }

    public void setCo_conten(int co_conten) {
        this.co_conten = co_conten;
    }

    public long getId_frawor() {
        return id_frawor;
    }

    public void setId_frawor(long id_frawor) {
        this.id_frawor = id_frawor;
    }

    public String getCo_contit() {
        return co_contit;
    }

    public void setCo_contit(String co_contit) {
        this.co_contit = co_contit;
    }

    public boolean isIl_header() {
        return il_header;
    }

    public void setIl_header(boolean il_header) {
        this.il_header = il_header;
    }

    @Override
    public String toHTML() {
        String HTML = "";

        HTML += "";

        for (Pagina pagina : paginas.values()) {
            HTML += "<div class=\"w3-row\">" +
                    "<div class=\"w3-quarter\" style=\"height: auto;color:#ededed\">1/4</div>";

            HTML += "   <div class=\"w3-half\" style=\"height: auto;\">" + "<iframe class=\"wf4_iframe\" id=\"PAG" + pagina.co_pagina + "\" onload=\"iframe(this)\" frameborder=0></iframe>" + "</div>";

            HTML += "<div class=\"w3-quarter\" style=\"height: auto;color:#ededed;\">1/4</div>" +
                    "</div>";
        }

        return HTML;
    }

}
