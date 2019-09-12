package com.acceso.wfweb.web;

import java.io.Serializable;
import java.util.List;

public class MenuItem implements Serializable {

    int co_mensis;
    String name;
    String url;
    List<MenuItem> sub;

    public MenuItem() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<MenuItem> getSub() {
        return sub;
    }

    public void setSub(List<MenuItem> sub) {
        this.sub = sub;
    }

    public int getCo_mensis() {
        return co_mensis;
    }

    public void setCo_mensis(int co_mensis) {
        this.co_mensis = co_mensis;
    }

    @Override
    public String toString() {
        return "MenuItem{" + "name=" + name + ", url=" + url + ", sub=" + sub + '}';
    }


}
