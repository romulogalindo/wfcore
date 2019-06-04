package com.acceso.wfcore.utils;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Rómulo Galindo
 */
public class ExcelJson implements Serializable {

    String filename;
    List<SheetJson> sheets;

    public ExcelJson() {
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public List<SheetJson> getSheets() {
        return sheets;
    }

    public void setSheets(List<SheetJson> sheets) {
        this.sheets = sheets;
    }

}
