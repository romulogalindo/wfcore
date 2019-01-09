package com.acceso.wfweb.units;

import java.io.Serializable;
import java.util.LinkedHashMap;

public abstract class Pagina extends HTMLRenderer implements Serializable {

    int co_pagina;
    String no_pagtit;
    LinkedHashMap<String, Fila> ultraFilas;

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

}
