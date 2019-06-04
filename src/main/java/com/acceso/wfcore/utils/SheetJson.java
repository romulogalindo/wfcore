package com.acceso.wfcore.utils;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Rómulo Galindo
 */
public class SheetJson implements Serializable {

    String name;
    List<RowSheetJson> rows;

    public SheetJson() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RowSheetJson> getRows() {
        return rows;
    }

    public void setRows(List<RowSheetJson> rows) {
        this.rows = rows;
    }

}
