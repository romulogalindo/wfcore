package com.acceso.wfcore.utils;

public class ColumnConfigJson {

    int index;
    String align;
    String valign;
    String color;
    boolean bold;
    boolean italic;
    boolean underline;
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
    Integer size;
    String font;

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

    public boolean isItalic() {
        return italic;
    }

    public void setItalic(boolean italic) {
        this.italic = italic;
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

    public boolean isUnderline() {
        return underline;
    }

    public void setUnderline(boolean underline) {
        this.underline = underline;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public String getValign() {
        return valign;
    }

    public void setValign(String valign) {
        this.valign = valign;
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
