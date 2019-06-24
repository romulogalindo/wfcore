package com.acceso.wfcore.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rómulo Galindo Tanta
 */
public class ValpagJson implements Serializable {

    List<RowJson> rows;

    public ValpagJson() {
        this.rows = new ArrayList<>();
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
        this.rows.add(rowJson);
    }

    public void add(RowJson rowJson) {
        this.rows.add(rowJson);
    }

    public static ValpagJson NEW() {
        return new ValpagJson();
    }
}
