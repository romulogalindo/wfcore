package com.acceso.wfcore.utils;

import java.io.Serializable;

public class Param implements Serializable {
    String no_param;
    String va_param;

    public Param(String no_param, String va_param) {
        this.no_param = no_param;
        this.va_param = va_param;
    }

    public String getNo_param() {
        return no_param;
    }

    public void setNo_param(String no_param) {
        this.no_param = no_param;
    }

    public String getVa_param() {
        return va_param;
    }

    public void setVa_param(String va_param) {
        this.va_param = va_param;
    }
}
