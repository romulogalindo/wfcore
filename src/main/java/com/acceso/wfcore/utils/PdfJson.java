package com.acceso.wfcore.utils;

import java.io.Serializable;
import java.util.List;

/**
 * @author rgalindo
 */
public class PdfJson implements Serializable {

    String orientation;
    List<PdfRegistJson> regists;

    public PdfJson() {
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public List<PdfRegistJson> getRegists() {
        return regists;
    }

    public void setRegists(List<PdfRegistJson> regists) {
        this.regists = regists;
    }
}
