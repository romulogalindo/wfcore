package com.acceso.wfweb.utils;

import java.io.Serializable;

/**
 *
 * @author Rómulo Galindo
 */
public class JsonSocketMessage implements Serializable {

    String type;
    String title;
    String body;
    String position;
    String conten;
    boolean clear;

    public JsonSocketMessage(String type, String title, String body, String position, String contenedor, Boolean clear) {
        this.type = type;
        this.title = title;
        this.body = body;
        this.position = position;
        this.conten = contenedor;
        this.clear = clear == null ? false : clear;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getConten() {
        return conten;
    }

    public void setConten(String conten) {
        this.conten = conten;
    }

    public boolean getClear() {
        return clear;
    }

    public void setClear(boolean clear) {
        this.clear = clear;
    }

    @Override
    public String toString() {
        return "JsonSocketMessage{" + "type=" + type + ", title=" + title + ", body=" + body + ", position=" + position + ", conten=" + conten + ", clear=" + clear + '}';
    }

}
