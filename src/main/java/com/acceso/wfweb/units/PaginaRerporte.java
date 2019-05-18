package com.acceso.wfweb.units;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class PaginaRerporte extends Pagina implements Serializable {

//    int co_pagina;
//    String no_pagtit;
//    LinkedHashMap<String, Fila> ultraFilas;

    public PaginaRerporte(int co_pagina, String no_pagtit, String ti_pagina, int nu_rowspa, int nu_colspa, int or_numrow, int or_numcol, int co_contab, LinkedHashMap<String, Fila> ultraFilas) {
        this.co_pagina = co_pagina;
        this.no_pagtit = no_pagtit;
        this.ti_pagina = ti_pagina;
        this.nu_rowspa = nu_rowspa;
        this.nu_colspa = nu_colspa;
        this.or_numrow = or_numrow;
        this.or_numcol = or_numcol;
        this.co_contab = co_contab;
        this.ultraFilas = ultraFilas;
    }

    public int getCo_pagina() {
        return this.co_pagina;
    }

    public void setCo_pagina(int co_pagina) {
        this.co_pagina = co_pagina;
    }

    public String getNo_pagtit() {
        return no_pagtit;
    }

    public void setNo_pagtit(String no_pagtit) {
        this.no_pagtit = no_pagtit;
    }

    @Override
    public String toHTML() {
        String html = "";
        String itr = "";

        LinkedList<Fila> titlelvl1 = new LinkedList<>();

        int colspan = 0;
        for (Fila fila : ultraFilas.values()) {
            if (fila.getTituloDTO() != null) {
                //es un titulo
                titlelvl1.add(fila);
                colspan = 0;
            } else {
                colspan++;
                titlelvl1.get(titlelvl1.size() - 1).setColspan(colspan);
            }
        }

        html += "<input type=hidden id=ti_pagina value=R />";
        html += "<input type=hidden id=ls_hamoda value=\"" + getLs_hamoda() + "\" />";

        html += "<table id=PAG" + co_pagina + " class=\"wf-report table table-hover mb-0 table-responsive\">";
        html += "<thead>";

        html += "<tr>";
        for (Fila fila : titlelvl1) {

            html += "<th colspan=" + fila.getColspan() + " class=\"wf_t_stitle w3-highway-blue\" >";
            html += fila.getTituloDTO().getNo_pagtit();
            html += "</th>";
        }

        html += "</tr>";

        html += "<tr>";
        itr += "<tr>";
        for (Fila fila : ultraFilas.values()) {

            if (fila.getRegistroDTO() != null) {
                if (!fila.getRegistroDTO().getTi_estreg().contentEquals("O")) {
                    html += "<th>";
                    html += "<span>" + fila.getRegistroDTO().getNo_pagreg() + "</span>";
                    html += "</th>";
                }

//                System.out.println("fila = " + fila.getRegistroDTO() + "?>" + fila.getRegistroDTO().getTi_pagreg() + "?>" + fila.getRegistroDTO().getTi_estreg());

                switch (fila.getRegistroDTO().getTi_pagreg()) {
                    case 1: {
                        if (fila.getRegistroDTO().getTi_estreg().contentEquals("O")) {
//                            System.out.println("fila = REDERER OK!");
                            itr += "<input type=hidden name=regist" + fila.getRegistroDTO().getCo_pagreg() + " value=regist" + fila.getRegistroDTO().getCo_pagreg() + "val />";
                        }
                        if (!fila.getRegistroDTO().getTi_estreg().contentEquals("O")) {
//                            System.out.println("fila = REDERER OK!");
                            itr += "<td><span name=regist" + fila.getRegistroDTO().getCo_pagreg() + ">regist" + fila.getRegistroDTO().getCo_pagreg() + "val</span></td>";
                        }
                        break;
                    }
                    case 2: {
//                        if (!fila.getRegistroDTO().getTi_estreg().contentEquals("O")) {
//                        System.out.println("fila = REDERER OK!");
                        itr += "<td class=\"ti_pag_reg2 text-center\"><span name=regist" + fila.getRegistroDTO().getCo_pagreg() + ">regist" + fila.getRegistroDTO().getCo_pagreg() + "val</span></td>";
//                        }
                        break;
                    }
                    case 3: {
//                        if (!fila.getRegistroDTO().getTi_estreg().contentEquals("O")) {
//                        System.out.println("fila = REDERER OK!");
                        itr += "<td class=\"ti_pag_reg2 text-center\"><span name=regist" + fila.getRegistroDTO().getCo_pagreg() + ">regist" + fila.getRegistroDTO().getCo_pagreg() + "val</span></td>";
//                        }
                        break;
                    }
                    case 4: {
//                        if (!fila.getRegistroDTO().getTi_estreg().contentEquals("O")) {
//                        System.out.println("fila = REDERER OK!");
                        itr += "<td class=\"ti_pag_reg2 text-center\"><span name=regist" + fila.getRegistroDTO().getCo_pagreg() + ">regist" + fila.getRegistroDTO().getCo_pagreg() + "val</span></td>";
//                        }
                        break;
                    }
                    case 5: {
//                        if (!fila.getRegistroDTO().getTi_estreg().contentEquals("O")) {
//                        System.out.println("fila = REDERER OK!");
                        itr += "<td class=\"ti_pag_reg2 text-center\"><span name=regist" + fila.getRegistroDTO().getCo_pagreg() + ">regist" + fila.getRegistroDTO().getCo_pagreg() + "val</span></td>";
//                        }
                        break;
                    }
                    case 6: {
//                        if (!fila.getRegistroDTO().getTi_estreg().contentEquals("O")) {
//                        System.out.println("fila = REDERER OK!");
                        itr += "<td class=\"ti_pag_reg2 text-center\"><span name=regist" + fila.getRegistroDTO().getCo_pagreg() + "><input type=checkbox class=\"w3-input w3-border\" checked=\"regist" + fila.getRegistroDTO().getCo_pagreg() + "val\" /></span></td>";
//                        }
                        break;
                    }
                }
            }
        }
        html += "</tr>";
        itr += "</tr>";

        html += "</thead>";
        html += "<tbody>";

        html += "</tbody>";
        html += "</table>";

        html += "<script>var itr='" + itr + "';</script>";

        return html;
    }
}
