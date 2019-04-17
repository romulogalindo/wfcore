package com.acceso.wfcore.utils;

import java.io.Serializable;

public class WSMessage implements Serializable {
    String status;
    String type;
    String user;
    String xact;
    String mact;

    public WSMessage() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getXact() {
        return xact;
    }

    public void setXact(String xact) {
        this.xact = xact;
    }

    public String getMact() {
        return mact;
    }

    public void setMact(String mact) {
        this.mact = mact;
    }

    @Override
    public String toString() {
        return "WSMessage{" +
                "status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", user='" + user + '\'' +
                ", xact='" + xact + '\'' +
                ", mact='" + mact + '\'' +
                '}';
    }
}
