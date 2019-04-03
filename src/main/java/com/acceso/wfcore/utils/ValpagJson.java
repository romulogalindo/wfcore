package com.acceso.wfcore.utils;

import java.util.List;

/**
 * @author rgalindo
 */
public class ValpagJson {

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

    @Override
    public String toString() {
        return "ValpagJson{"
                + "rows=" + rows
                + '}';
    }
}
