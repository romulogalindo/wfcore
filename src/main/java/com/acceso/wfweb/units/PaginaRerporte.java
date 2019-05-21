package com.acceso.wfweb.units;

import com.acceso.wfweb.dtos.WBotonDTO;
import com.acceso.wfweb.dtos.WParametroDTO;

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
        String css = "<style>";

        LinkedList<Fila> titlelvl1 = new LinkedList<>();
        int colspan = 0;
        int colcount = 1;
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
                    if (fila.getRegistroDTO().getTi_pagreg() == 6) {
                        html += "<span>" + fila.getRegistroDTO().getNo_pagreg();
                        html += "<div class=\"custom-control custom-checkbox\">\n"
                                + "    <input type=\"checkbox\" class=\"custom-control-input\" id=\"reg" + fila.getRegistroDTO().getCo_pagreg() + "\" onchange=\"docheckall(this," + fila.getRegistroDTO().getCo_pagreg() + ")\">\n"
                                + "    <label class=\"custom-control-label\" for=\"reg" + fila.getRegistroDTO().getCo_pagreg() + "\"></label>\n"
                                + "</div>";
                        html += "</span>";
                    } else {
                        html += "<span>" + fila.getRegistroDTO().getNo_pagreg() + "</span>";
                    }
                    html += "</th>";

                }

                //OVER CSS
                css += "table#PAG" + co_pagina + " tbody tr td:nth-child(" + colcount + "){\ntext-align:" + fila.getRegistroDTO().getVa_alireg() + ";\nvertical-align:" + fila.getRegistroDTO().getVa_valign() + ";\n}\n";
                colcount++;

//                System.out.println("fila = " + fila.getRegistroDTO() + "?>" + fila.getRegistroDTO().getTi_pagreg() + "?>" + fila.getRegistroDTO().getTi_estreg());
                switch (fila.getRegistroDTO().getTi_pagreg()) {
                    case 1: {
                        if (fila.getRegistroDTO().getTi_estreg().contentEquals("O")) {
//                            System.out.println("fila = REDERER OK!");
                            itr += "<input type=hidden id=\"X64UIR" + fila.getRegistroDTO().getCo_pagreg() + "\" " + (fila.getRegistroDTO().isIl_guareg() ? "class=\"x64\"" : "") + " value=\"\" />";
                        } else if (fila.getRegistroDTO().getTi_estreg().contentEquals("L")) {
//                            System.out.println("fila = REDERER OK!");
                            itr += "<td>";
                            itr += "<span id=\"X64UIR" + fila.getRegistroDTO().getCo_pagreg() + "\" " + (fila.getRegistroDTO().isIl_guareg() ? "class=\"x64\"" : "") + " name=regist" + fila.getRegistroDTO().getCo_pagreg() + " ti_pagreg=1  co_regist=" + fila.getRegistroDTO().getCo_pagreg() + ">";
                            itr += "</span>";
                            itr += "</td>";
                        } else if (fila.getRegistroDTO().getTi_estreg().contentEquals("E")) {
//                            System.out.println("fila = REDERER OK!");
                            itr += "<td>";
                            itr += "<span id=\"X64UIR" + fila.getRegistroDTO().getCo_pagreg() + "\" " + (fila.getRegistroDTO().isIl_guareg() ? "class=\"x64\"" : "") + " name=regist" + fila.getRegistroDTO().getCo_pagreg() + " ti_pagreg=1  co_regist=" + fila.getRegistroDTO().getCo_pagreg() + ">";
                            itr += "<input id=\"\" type=\"text\" value=\"\">";
                            itr += "</span>";
                            itr += "</td>";
                        }
                        break;
                    }
                    case 2: {
//                        if (!fila.getRegistroDTO().getTi_estreg().contentEquals("O")) {
//                        System.out.println("fila = REDERER OK!");
                        itr += "<td>";
                        itr += "<span id=\"X64UIR" + fila.getRegistroDTO().getCo_pagreg() + "\" ti_pagreg=2  " + (fila.getRegistroDTO().isIl_guareg() ? "class=\"x64\"" : "") + " co_regist=" + fila.getRegistroDTO().getCo_pagreg() + ">";
                        itr += "</span>";
                        itr += "</td>";
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
//                        itr += "<td class=\"ti_pag_reg2 text-center\"><span name=regist" + fila.getRegistroDTO().getCo_pagreg() + "><input type=checkbox class=\"w3-input w3-border\" checked=\"regist" + fila.getRegistroDTO().getCo_pagreg() + "val\" /></span></td>";
                        itr += "<td class=\"ti_pag_reg2 text-center\">"
                                + "<span id=\"X64UIR" + fila.getRegistroDTO().getCo_pagreg() + "\" name=regist" + fila.getRegistroDTO().getCo_pagreg() + " ti_pagreg=6  co_regist=" + fila.getRegistroDTO().getCo_pagreg() + ">"
                                + "<div class=\"custom-control custom-checkbox\">"
                                + "<input id=\"X64UIR" + fila.getRegistroDTO().getCo_pagreg() + "_check\" type=checkbox class=\"w3-input custom-control-input check " + (fila.getRegistroDTO().isIl_guareg() ? "x64" : "") + "\" />"
                                + "<label class=\"custom-control-label\" for=\"X64UIR" + fila.getRegistroDTO().getCo_pagreg() + "_check\"></label>"
                                + "</div>"
                                + "</span>"
                                + "</td>";
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

        boolean hvg = false;

        for (Fila fila : ultraFilas.values()) {
            if (fila.getBotonDTOS() != null && fila.getBotonDTOS().size() > 0) {
                hvg = true;
                break;
            }
        }

        if (hvg) {
            html += "<tfoot>";
            html += "<tr>";
            html += "<td colspan='" + colspan + "'>";
            String id = "BTXG";
            for (Fila fila : ultraFilas.values()) {
                if (fila.getBotonDTOS() != null && fila.getBotonDTOS().size() > 0) {
                    for (WBotonDTO botonDTO : fila.getBotonDTOS()) {
                        if (botonDTO.getTi_pagbot().contentEquals("G")) {
                            html += "<script> var " + id + botonDTO.getCo_pagbot() + "P=[];";
                            for (WParametroDTO parametroDTO : botonDTO.getParametros()) {
                                html += "" + id + botonDTO.getCo_pagbot() + "P[" + id + botonDTO.getCo_pagbot() + "P.length] = new Parameter(" + parametroDTO.getCo_pagreg() + "," + parametroDTO.getCo_conpar() + ");";
                            }
                            html += "</script>";

                            html += "<button name=" + id + botonDTO.getCo_pagbot() + " class=\"btn btn-default\" onclick=\"propagg(\'C1\'," + botonDTO.getCo_pagbot() + "," + botonDTO.isIl_proces() + ", " + botonDTO.getCo_condes() + ")\" >"
                                    + "<i class=\"fa fa-hand-pointer-o\" aria-hidden=\"true\"></i>\n"
                                    + botonDTO.getNo_pagbot()
                                    + "</button>";
                        }
                    }
                }
            }
            html += "</td>";
            html += "</tr>";
            html += "</tfoot>";
        }

        html += "</table>";
        css += "</style>";
        html += "<script>var itr='" + itr + "';</script>";
        html = css + html;

        return html;
    }
}
