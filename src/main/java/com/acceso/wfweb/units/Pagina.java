package com.acceso.wfweb.units;

import java.io.Serializable;

public class Pagina extends HTMLRenderer implements Serializable {
    int co_pagina;
    String no_pagtit;

    public Pagina(int co_pagina, String no_pagtit) {
        this.co_pagina = co_pagina;
        this.no_pagtit = no_pagtit;
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
        return "";
    }
}
