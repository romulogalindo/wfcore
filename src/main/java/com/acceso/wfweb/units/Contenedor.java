package com.acceso.wfweb.units;

import com.acceso.wfweb.dtos.WContabDTO;

import java.io.Serializable;
import java.util.*;

public class Contenedor extends HTMLRenderer implements Serializable, Cloneable {

    //objeto de distribucion(ahora solo vertical)
    LinkedHashMap<Integer, Pagina> paginas;
    List<WContabDTO> contabs;
    int co_conten;
    long id_frawor;
    String co_contit;
    boolean il_popup;
    String ls_conpar;
    Map<String, String> mp_conpar;

    public Contenedor(int co_conten, long id_frawor, String co_contit, List<WContabDTO> contabs) {
        this.co_conten = co_conten;
        this.id_frawor = id_frawor;
        this.co_contit = co_contit;

        this.paginas = new LinkedHashMap<>();
        this.contabs = contabs;
        this.mp_conpar = new HashMap<>();
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

    public boolean isIl_popup() {
        return il_popup;
    }

    public void setIl_popup(boolean il_popup) {
        this.il_popup = il_popup;
    }

    public List<WContabDTO> getContabs() {
        return contabs;
    }

    public void setContabs(List<WContabDTO> contabs) {
        this.contabs = contabs;
    }

    public String getLs_conpar() {
        ls_conpar = "{";
        ls_conpar = mp_conpar.entrySet().stream()
                .map((conpar) -> "\"" + conpar.getKey() + "\":\"" + conpar.getValue() + "\",")
                .reduce(ls_conpar, String::concat);
        ls_conpar = (ls_conpar.length() == 1 ? ls_conpar : ls_conpar.substring(0, ls_conpar.length() - 1)) + "}";

        return ls_conpar;
    }

    public void setLs_conpar(String ls_conpar) {
        this.ls_conpar = ls_conpar;
    }

    public Map<String, String> getMp_conpar() {
        return mp_conpar;
    }

    public void setMp_conpar(Map<String, String> mp_conpar) {
        this.mp_conpar = mp_conpar;
    }

    public void put_conpar(String key, String value) {
        System.out.println("key = " + key + ", value = " + value);
        this.mp_conpar.put(key, value);
    }

    @Override
    public String toHTML() {
        String HTML = "";

        HashMap<Integer, List<WContabDTO>> rows = new HashMap<>();

        List<WContabDTO> cols = new ArrayList<>();
//        System.out.println("contabs.size()=" + contabs.size());
        int curren_row = -1;
        boolean has_rowspan = false;
        for (int i = 0; i < contabs.size(); i++) {
            WContabDTO contab = contabs.get(i);
            System.out.println("eval>>[contab =" + contab + ":" + i + "]");

            if (!has_rowspan) {
                curren_row = contab.getOr_numrow();
            }
//            cols.add(contab);
            if (rows.get(curren_row) == null) {
                cols = new ArrayList<>();
                rows.put(curren_row, cols);
            }
            rows.get(curren_row).add(contab);

            if (contab.getNu_rowspa() > 1) {
                has_rowspan = true;
            } else {
                has_rowspan = false;
            }
        }

        for (Map.Entry<Integer, List<WContabDTO>> rowx : rows.entrySet()) {
            System.out.println("rowx->index = " + rowx.getKey());
            System.out.println("rowx->valores(" + rowx.getValue().size() + ") = " + rowx.getValue());
            HTML += "<div class=\"row wfrow\">";
            int filas = rowx.getValue().size();
//            String varcolwidth = filas == 1 ? "col-md-12" : (filas == 2 ? "col-md-6" : (filas == 3 ? "col-md-4" : "col-md-3"));
            String varcolwidth = filas == 1 ? "col-md-6" : (filas == 2 ? "col-md-6" : (filas == 3 ? "col-md-4" : "col-md-3"));
            if (rowx.getValue().size() < 3) {
                for (WContabDTO contab : rowx.getValue()) {
                    try {
                        System.out.println("{{}}{--->}pag.getTi_pagina() =>>> !1");
                        if (filas == 1) {
                            System.out.println("{{}}{--->}pag.getTi_pagina() =>>> !2");
                            for (int p = 0; p < paginas.size(); p++) {
                                Pagina pag = paginas.get(p);
                                System.out.println("pag = " + pag);
                                if (pag != null) {
                                    System.out.println("{{}}{--->}pag.getTi_pagina() =>>> !3[pag.getCo_contab():" + pag.getCo_contab() + "][contab.getCo_contab():" + contab.getCo_contab() + "]");
                                    if (pag.getCo_contab() == contab.getCo_contab()) {
                                        System.out.println("{{}}{--->}pag.getTi_pagina() =>>> " + pag.getTi_pagina());
                                        if (pag.getTi_pagina().contentEquals("F")) {
                                            varcolwidth = "col-md-6";
                                        }/*else{
                                        varcolwidth = "col-md-12";
                                    }*/
                                        p = 1000;
                                    }
                                }else{
                                    System.out.println("pag>>>>>>>>> = " + pag);
                                }
                            }
                        }

//                        Pagina pag = paginas.values().stream()
//                                .filter((pagina) -> (pagina.getCo_contab() == contab.getCo_contab())).findFirst().get();
//
//
//                        if (pag.getTi_pagina().contentEquals("F")) {
//                            varcolwidth = "col-md-6";
//                        } else {
//                            varcolwidth = "col-md-12";
//                        }
                    } catch (Exception ep) {
                        ep.printStackTrace();
                        varcolwidth = "col-md-6";
                    }

                    HTML += "<div class=\"" + varcolwidth + " wfcol\" style=\"height: 0 auto; margin: 0px auto;\">";

//                for (Pagina pagina : paginas.values()) {
////                    System.out.println("compara>>" + pagina.getCo_contab() + "::" + contab.getCo_contab());
//                    if (pagina.getCo_contab() == contab.getCo_contab()) {
//                        HTML += "<iframe class=\"wf4_iframe\" id=\"PAG" + pagina.getCo_pagina() + "\" onload=\"iframe(this)\" frameborder=0></iframe>";
//                    }
//                }
                    HTML = paginas.values().stream()
                            .filter((pagina) -> (pagina.getCo_contab() == contab.getCo_contab()))
                            .map((pagina) -> "<iframe class=\"wf4_iframe\" type=\"" + pagina.getTi_pagina() + "\" id=\"PAG" + pagina.getCo_pagina() + "\" onload=\"iframe(this)\" frameborder=0></iframe>")
                            .reduce(HTML, String::concat);

                    HTML += "</div>";
                }
            } else {
                //re-organizar columnas
                System.out.println("reorganizando columnas");
                List<WContabDTO> col1 = new ArrayList<>();
                List<WContabDTO> col2 = new ArrayList<>();
                List<WContabDTO> col3 = new ArrayList<>();
                List<WContabDTO> col4 = new ArrayList<>();

                for (WContabDTO contab : rowx.getValue()) {
                    System.out.println("contab = " + contab);
                    if (contab.getOr_numcol() == 1) {
                        col1.add(contab);
                        System.out.println("Agregando col1 = ");
                    } else if (contab.getOr_numcol() == 2) {
                        col2.add(contab);
                        System.out.println("Agregando col2 = ");
                    }
                }
                if (col1.size() > 0 & col2.size() > 0) {
                    HTML += "<div class=\"col-md-6 wfcol\" style=\"height: auto; margin: 0px auto;\">";
                    //contenido
                    HTML += "   <div class=\"row\">";
                    for (WContabDTO ctab : col1) {
                        System.out.println("Contenido COL1 = " + ctab);
                        Iterator<Pagina> it = paginas.values().iterator();
                        while (it.hasNext()) {
                            Pagina cpagina = it.next();
                            System.out.println("SI! (cpagina.getCo_contab():" + cpagina.getCo_contab() + ")=(ctab.getCo_contab():" + ctab.getCo_contab() + ")");
                            if (cpagina.getCo_contab() == ctab.getCo_contab()) {
                                String ifr = "<iframe class=\"wf4_iframe\" type=\"" + cpagina.getTi_pagina() + "\" id=\"PAG" + cpagina.getCo_pagina() + "\" onload=\"iframe(this)\" frameborder=0></iframe>";
                                HTML += ifr;
                            }
                        }
//                        HTML = paginas.values().stream()
//                                .filter((pagina) -> (pagina.getCo_contab() == ctab.getCo_contab()))
//                                .map((pagina) -> "<iframe class=\"wf4_iframe\" type=\"" + pagina.getTi_pagina() + "\" id=\"PAG" + pagina.getCo_pagina() + "\" onload=\"iframe(this)\" frameborder=0></iframe>")
//                                .reduce(HTML, String::concat);
                    }

                    HTML += "   </div>";
                    HTML += "</div>";

                    HTML += "<div class=\"col-md-6 wfcol\" style=\"height: auto; margin: 0px auto;\">";
                    //contenido
                    HTML += "   <div class=\"row\">";
                    for (WContabDTO ctab : col2) {
                        System.out.println("Contenido COL2 = " + ctab);
                        Iterator<Pagina> it = paginas.values().iterator();
                        while (it.hasNext()) {
                            Pagina cpagina = it.next();
                            System.out.println("SI! (cpagina.getCo_contab():" + cpagina.getCo_contab() + ")=(ctab.getCo_contab():" + ctab.getCo_contab() + ")");
                            if (cpagina.getCo_contab() == ctab.getCo_contab()) {
                                String ifr = "<iframe class=\"wf4_iframe\" type=\"" + cpagina.getTi_pagina() + "\" id=\"PAG" + cpagina.getCo_pagina() + "\" onload=\"iframe(this)\" frameborder=0></iframe>";
                                HTML += ifr;
                            }
                        }
//                        HTML = paginas.values().stream()
//                                .filter((pagina) -> (pagina.getCo_contab() == ctab.getCo_contab()))
//                                .map((pagina) -> "<iframe class=\"wf4_iframe\" type=\"" + pagina.getTi_pagina() + "\" id=\"PAG" + pagina.getCo_pagina() + "\" onload=\"iframe(this)\" frameborder=0></iframe>")
//                                .reduce(HTML, String::concat);
                    }

                    HTML += "   </div>";
                    HTML += "</div>";
                }
            }

            HTML += "</div>";
        }

        return HTML;
    }

    @Override
    public Object clone() {
        Object obj = null;
        try {
            obj = super.clone();
        } catch (CloneNotSupportedException ex) {
            System.out.println(" no se puede duplicar");
        }
        return obj;
    }
}
