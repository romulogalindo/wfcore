package com.acceso.wfweb.units;

import java.io.Serializable;
import java.util.LinkedHashMap;

public class PaginaFormulario extends Pagina implements Serializable {

//    int co_pagina;
//    String no_pagtit;
//    LinkedHashMap<String, Fila> ultraFilas;

    public PaginaFormulario(int co_pagina, String no_pagtit, LinkedHashMap<String, Fila> ultraFilas) {
        this.co_pagina = co_pagina;
        this.no_pagtit = no_pagtit;
        this.ultraFilas = ultraFilas;
    }

    public int getCo_pagina() {
        return co_pagina;
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

//        html += "<script>\n" +
//                "function loadReporte64(row){\n";
//
//        html += "for (var x = 0; x < row.regs.length; x++) {\n" +
//                "                var reg = row.regs[x];\n" +
//                "                console.log('reg.regist =' + reg.regist + ',reg.value = ' + reg.value);;\n" +
//                "                document.getElementsByName('P'+co_pagina()+'T1R'+reg.regist + 'V')[0].innerHTML = reg.value;\n" +
//                "            }\n";
//
//        html += "}</script>";

        html += "<input type=hidden id=ti_pagina value=F />";


        html += "<table id=PAG" + this.co_pagina + " class=\"w3-table-all w3-tiny w3-card-4\">";
        html += "<thead>";
        html += "<tr>";
        html += "<th colspan=2 >";
        html += "<h3>" + this.no_pagtit + "</h3>";
        html += "</th>";
        html += "<tr>";
        html += "</tr>";
        html += "</thead>";
        html += "<tbody>";

        for (Fila fila : this.ultraFilas.values()) {
            html += fila.toHTML();
        }

        html += "</tbody>";
        html += "</table>";

        return html;
    }
}
