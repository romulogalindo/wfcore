package com.acceso.wfweb.utils;

/**
 *
 * @author Rómulo Galindo
 */
public class JsonSocketMessage {

    String type;
    String title;
    String body;

    public JsonSocketMessage(String type, String title, String body) {
        this.type = type;
        this.title = title;
        this.body = body;
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

}
