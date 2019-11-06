package com.acceso.wfcore.utils;

public class ColumnConfigJson {

    int index;
    String align;
    String color;
    boolean bold;
    String bgcolor;
    boolean hwrap;
    boolean vwrap;
    String type;
    String format;
    int width = 40;
    String borderTop;
    String borderRight;
    String borderBottom;
    String borderLeft;

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

    public boolean isHwrap() {
        return hwrap;
    }

    public void setHwrap(boolean hwrap) {
        this.hwrap = hwrap;
    }

    public boolean isVwrap() {
        return vwrap;
    }

    public void setVwrap(boolean vwrap) {
        this.vwrap = vwrap;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getBorderTop() {
        return borderTop;
    }

    public void setBorderTop(String borderTop) {
        this.borderTop = borderTop;
    }

    public String getBorderRight() {
        return borderRight;
    }

    public void setBorderRight(String borderRight) {
        this.borderRight = borderRight;
    }

    public String getBorderBottom() {
        return borderBottom;
    }

    public void setBorderBottom(String borderBottom) {
        this.borderBottom = borderBottom;
    }

    public String getBorderLeft() {
        return borderLeft;
    }

    public void setBorderLeft(String borderLeft) {
        this.borderLeft = borderLeft;
    }

    @Override
    public String toString() {
        return "ColumnConfigJson{" +
                "index=" + index +
                ", align='" + align + '\'' +
                ", color='" + color + '\'' +
                ", bold=" + bold +
                ", bgcolor='" + bgcolor + '\'' +
                ", hwrap=" + hwrap +
                ", vwrap=" + vwrap +
                ", type='" + type + '\'' +
                ", format='" + format + '\'' +
                '}';
    }
}
