package com.acceso.wfweb.units;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Contenedor extends HTMLRenderer implements Serializable {

    //objeto de distribucion(ahora solo vertical)
    //referencia a paginas modo simple
    List<Pagina> paginas;
    int co_conten;
    long id_frawor;
    String co_contit;

    public Contenedor(int co_conten, long id_frawor, String co_contit) {
        this.co_conten = co_conten;
        this.id_frawor = id_frawor;
        this.co_contit = co_contit;

        this.paginas = new ArrayList<>();
    }

    public void addPagina(Pagina pagina) {
        paginas.add(pagina);
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

    @Override
    public String toHTML() {
        String HTML = "";
        for (Pagina pagina : paginas) {
            HTML += "<script>var listconten={100628,103884,105054,100724,105748,100629,101956}</script>";
            HTML += "<div class=\"w3-row\"><div class=\"w3-quarter\" style=\"background:green;height: 200px;\">1/4</div>";

            HTML += "   <div class=\"w3-half\" style=\"background:red;height: 200px;\">" + "<iframe class=\"wf4_iframe\" id=\"PAG" + pagina.co_pagina + "\"></iframe>" + "</div>";

            HTML += "<div class=\"w3-quarter\" style=\"background:green;height: 200px;\">1/4</div></div>";
        }
        return HTML;
    }

}
