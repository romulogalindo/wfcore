package com.acceso.wfweb.utils;

import com.acceso.wfcore.utils.Param;

import java.util.List;

public class PropagAction {
    String no_action = "REDIRECT";
    String co_condes;
    List<Param> ls_params;
    List<String> ls_pagina;
    String ur_archiv;
    String ur_popup;

    public PropagAction() {
    }

    public PropagAction(String no_action, String co_condes, List<Param> ls_params, List<String> ls_pagina, String ur_archiv) {
        this.no_action = no_action;
        this.co_condes = co_condes;
        this.ls_params = ls_params;
        this.ls_pagina = ls_pagina;
        this.ur_archiv = ur_archiv;
    }

    public String getNo_action() {
        return no_action;
    }

    public void setNo_action(String no_action) {
        this.no_action = no_action;
    }

    public String getCo_condes() {
        return co_condes;
    }

    public void setCo_condes(String co_condes) {
        this.co_condes = co_condes;
    }

    public List<Param> getLs_params() {
        return ls_params;
    }

    public void setLs_params(List<Param> ls_params) {
        this.ls_params = ls_params;
    }

    public List<String> getLs_pagina() {
        return ls_pagina;
    }

    public void setLs_pagina(List<String> ls_pagina) {
        this.ls_pagina = ls_pagina;
    }

    public String getUr_archiv() {
        return ur_archiv;
    }

    public void setUr_archiv(String ur_archiv) {
        this.ur_archiv = ur_archiv;
    }

    public String getUr_popup() {
        return ur_popup;
    }

    public void setUr_popup(String ur_popup) {
        this.ur_popup = ur_popup;
    }
}
