package com.acceso.wfcore.utils;

import java.io.Serializable;

public class ErrorMessage implements Serializable {

    public static final String ERROR_TYPE_USER = "USER";
    public static final String ERROR_TYPE_SYSTEM = "SYSTEM";

    String type;
    String message;

    public ErrorMessage() {
    }

    public ErrorMessage(String type, String message) {
        this.type = type;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
