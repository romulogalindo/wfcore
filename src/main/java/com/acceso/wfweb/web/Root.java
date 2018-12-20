package com.acceso.wfweb.web;

import java.io.Serializable;
import java.util.List;

public class Root implements Serializable {

    List<Sistema> sistemas;

    public Root() {
    }

    public List<Sistema> getSistemas() {
        return sistemas;
    }

    public void setSistemas(List<Sistema> sistemas) {
        this.sistemas = sistemas;
    }

    @Override
    public String toString() {
        return "Root{" + "sistemas=" + sistemas + '}';
    }

}
