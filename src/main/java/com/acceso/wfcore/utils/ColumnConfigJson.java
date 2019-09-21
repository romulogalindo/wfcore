package com.acceso.wfcore.utils;

public class ColumnConfigJson {

    int index;
    String align;
    String color;
    boolean bold;
    String bgcolor;
    boolean wrap;

    public ColumnConfigJson() {
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getAlign() {
        return align;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isBold() {
        return bold;
    }

    public void setBold(boolean bold) {
        this.bold = bold;
    }

    public String getBgcolor() {
        return bgcolor;
    }

    public void setBgcolor(String bgcolor) {
        this.bgcolor = bgcolor;
    }

    public boolean isWrap() {
        return wrap;
    }

    public void setWrap(boolean wrap) {
        this.wrap = wrap;
    }

    @Override
    public String toString() {
        return "ColumnConfigJson{" +
                "index=" + index +
                ", align='" + align + '\'' +
                ", color='" + color + '\'' +
                ", bold=" + bold +
                ", bgcolor='" + bgcolor + '\'' +
                ", wrap=" + wrap +
                '}';
    }
}
