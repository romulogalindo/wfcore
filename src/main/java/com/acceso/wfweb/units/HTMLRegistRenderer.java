package com.acceso.wfweb.units;

import com.acceso.wfweb.dtos.WRegistroDTO;

public abstract class HTMLRegistRenderer extends HTMLRenderer {

    public static final String TYPE_HIDDEN = "O";
    public static final String TYPE_READER = "L";
    public static final String TYPE_WRITER = "E";

    protected WRegistroDTO registroDTO;
    protected String id;

    public HTMLRegistRenderer(String id, WRegistroDTO registroDTO) {
        this.registroDTO = registroDTO;
        this.id = id;
    }

    public abstract String HTMLforForm();

    public abstract String HTMLforReport();
}
