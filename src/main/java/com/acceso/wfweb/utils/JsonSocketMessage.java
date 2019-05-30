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

    public JsonSocketMessage(String type, String title, String body, String position) {
        this.type = type;
        this.title = title;
        this.body = body;
        this.position = position;
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

}
