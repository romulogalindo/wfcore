package com.acceso.wfcore.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rgalindo
 */
public class ValpagJson implements Serializable {

    List<RowJson> rows;

    public ValpagJson() {
    }

    public ValpagJson(List<RowJson> rows) {
        this.rows = rows;
    }

    public List<RowJson> getRows() {
        return rows;
    }

    public void setRows(List<RowJson> rows) {
        this.rows = rows;
    }

    public void addRow(RowJson rowJson) {
        if (this.rows == null) {
            this.rows = new ArrayList<>();
        }
        this.rows.add(rowJson);
    }
}
