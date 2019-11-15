package com.acceso.wfweb.utils;

public class RecoveryUnit {
    boolean valid;
    String message;
    String user;
    String code;

    public RecoveryUnit() {
    }

    public RecoveryUnit(boolean valid, String message, String user, String code) {
        this.valid = valid;
        this.message = message;
        this.user = user;
        this.code = code;
    }

    public boolean getValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
