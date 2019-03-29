package com.acceso.wfweb.units;

import com.acceso.wfweb.dtos.ContabDTO;

import java.io.Serializable;
import java.util.*;

public class Contenedor extends HTMLRenderer implements Serializable {

    //objeto de distribucion(ahora solo vertical)

    LinkedHashMap<Integer, Pagina> paginas;
    List<ContabDTO> contabs;
    int co_conten;
    long id_frawor;
    String co_contit;
    boolean il_header;
    String ls_conpar;

    //    public Contenedor(int co_conten, long id_frawor, String co_contit) {
    public Contenedor(int co_conten, long id_frawor, String co_contit, List<ContabDTO> contabs) {
        this.co_conten = co_conten;
        this.id_frawor = id_frawor;
        this.co_contit = co_contit;

        this.paginas = new LinkedHashMap<>();
        this.contabs = contabs;
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

    public List<ContabDTO> getContabs() {
        return contabs;
    }

    public void setContabs(List<ContabDTO> contabs) {
        this.contabs = contabs;
    }

    public String getLs_conpar() {
        return ls_conpar;
    }

    public void setLs_conpar(String ls_conpar) {
        this.ls_conpar = ls_conpar;
    }

    @Override
    public String toHTML() {
        String HTML = "";

        HashMap<Integer, List<ContabDTO>> rows = new HashMap<>();

        List<ContabDTO> cols = new ArrayList<>();
//        System.out.println("contabs.size()=" + contabs.size());
        for (int i = 0; i < contabs.size(); i++) {
            ContabDTO contab = contabs.get(i);
//            System.out.println("eval>>[contab =" + contab + ":" + i + "]");

            if (i == 0) {
                cols.add(contab);
            } else {
//                System.out.println("[cols.get(cols.size() - 1).getOr_numrow()=" + (cols.get(cols.size() - 1).getOr_numrow()) + "]!=[contab.getOr_numrow()=" + contab.getOr_numrow() + "]");
                if (cols.get(cols.size() - 1).getOr_numrow() != contab.getOr_numrow()) {
                    rows.put(cols.get(cols.size() - 1).getCo_contab(), cols);
                    cols = new ArrayList<>();
                }

                cols.add(contab);
            }


            if ((contabs.size() - 1) == (i)) {
//                System.out.println("ultimo elemento>" + i);
                rows.put(cols.get(cols.size() - 1).getCo_contab(), cols);
            }

        }

        for (Map.Entry<Integer, List<ContabDTO>> rowx : rows.entrySet()) {
            HTML += "<div class=\"row\">";
            int filas = rowx.getValue().size();
            String varcolwidth = filas == 1 ? "col-md-12" : (filas == 2 ? "col-md-6" : (filas == 3 ? "col-md-4" : "col-md-3"));

            for (ContabDTO contab : rowx.getValue()) {
                HTML += "<div class=\"" + varcolwidth + "\" style=\"height: auto;\">";

//                for (Pagina pagina : paginas.values()) {
////                    System.out.println("compara>>" + pagina.getCo_contab() + "::" + contab.getCo_contab());
//                    if (pagina.getCo_contab() == contab.getCo_contab()) {
//                        HTML += "<iframe class=\"wf4_iframe\" id=\"PAG" + pagina.getCo_pagina() + "\" onload=\"iframe(this)\" frameborder=0></iframe>";
//                    }
//                }

                HTML = paginas.values().stream()
                        .filter((pagina) -> (pagina.getCo_contab() == contab.getCo_contab()))
                        .map((pagina) -> "<iframe class=\"wf4_iframe\" id=\"PAG" + pagina.getCo_pagina() + "\" onload=\"iframe(this)\" frameborder=0></iframe>")
                        .reduce(HTML, String::concat);

                HTML += "</div>";
            }
            HTML += "</div>";
        }

        return HTML;
    }

}
