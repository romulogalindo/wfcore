package com.acceso.wfcore.utils;

import java.io.Serializable;
import java.util.Map;

/**
 *
 * @author Rómulo Galindo
 */
public class RowSheetJson implements Serializable {

    Map<String, Object> cells;

    public RowSheetJson() {
    }

    public Map<String, Object> getCells() {
        return cells;
    }

    public void setCells(Map<String, Object> cells) {
        this.cells = cells;
    }

}
