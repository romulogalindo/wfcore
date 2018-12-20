package com.acceso.wfweb.web;

import java.io.Serializable;
import java.util.List;

public class MainMenu implements Serializable {

    List<Menu> menu;

    public MainMenu() {
    }

    public List<Menu> getMenu() {
        return menu;
    }

    public void setMenu(List<Menu> menu) {
        this.menu = menu;
    }

    @Override
    public String toString() {
        return "MainMenu{menu=" + menu + '}';
    }


}
