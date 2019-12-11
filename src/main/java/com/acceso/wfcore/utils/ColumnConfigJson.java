package com.acceso.wfcore.utils;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

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

    public String getUXCode() {
        String UXCode = "";
        //----------------------------------------

        //ALINEAMIENTO
        if (getAlign() != null) {
            switch (getAlign()) {
                case "CENTER": {
                    UXCode = UXCode + HorizontalAlignment.CENTER;
                    break;
                }
                case "LEFT": {
                    UXCode = UXCode + HorizontalAlignment.LEFT;
                    break;
                }
                case "RIGHT": {
                    UXCode = UXCode + HorizontalAlignment.RIGHT;
                    break;
                }
                case "JUSTIFY": {
                    UXCode = UXCode + HorizontalAlignment.JUSTIFY;
                    break;
                }
            }
        } else {
            UXCode = UXCode + HorizontalAlignment.LEFT;
        }

        //ALINEAMIENTO VERTICAL
        if (getValign() != null) {
            switch (getValign()) {
                case "CENTER": {
                    UXCode = UXCode + "_" + VerticalAlignment.CENTER;
                    break;
                }
                case "TOP": {
                    UXCode = UXCode + "_" + VerticalAlignment.TOP;
                    break;
                }
                case "BOTTOM": {
                    UXCode = UXCode + "_" + VerticalAlignment.BOTTOM;
                    break;
                }
            }
        } else {
            UXCode = UXCode + "_" + VerticalAlignment.CENTER;
        }

        //COLOR DE TEXTO
        if (getColor() != null) {
            UXCode = UXCode + "_" + Util.getColor(getColor());
//            customStyle.setFillForegroundColor(Util.getColor(getColor()));
//            customFont.setColor(Util.getColor(configJson.getColor()));
        } else {
            UXCode = UXCode + "_1";
        }

        //COLOR DE FONTDO
        if (getBgcolor() != null) {
            UXCode = UXCode + "_" + Util.getColor(getColor());
        } else {
            UXCode = UXCode + "_0";
        }

        //WRAP
        if (isVwrap()) {
            UXCode = UXCode + "_1";
        } else {
            UXCode = UXCode + "_0";
        }

        //BORDE TOP
        if (getBorderTop() != null) {
            UXCode = UXCode + "_" + getBorderTop();
        } else {
            UXCode = UXCode + "_0";
        }

        //BORDE DERECHO
        if (getBorderRight() != null) {
            UXCode = UXCode + "_" + getBorderRight();
        } else {
            UXCode = UXCode + "_0";
        }

        //BORDE INFERIOR
        if (getBorderBottom() != null) {
            UXCode = UXCode + "_" + getBorderBottom();
        } else {
            UXCode = UXCode + "_0";
        }

        //BORDE IZQUIRDO
        if (getBorderLeft() != null) {
            UXCode = UXCode + "_" + getBorderLeft();
        } else {
            UXCode = UXCode + "_0";
        }


        //NEGRITA
        if (isBold()) {
            UXCode = UXCode + "_1";
        } else {
            UXCode = UXCode + "_0";
        }

        //ITAQLICA
        if (isItalic()) {
            UXCode = UXCode + "_1";
        } else {
            UXCode = UXCode + "_0";
        }

        //SUBRAYADO
        if (isUnderline()) {
            UXCode = UXCode + "_" + HSSFFont.U_SINGLE;
        } else {
            UXCode = UXCode + "_0";
        }

        //TAMANO DE LETRA
        if (getSize() != null) {
            UXCode = UXCode + "_" + getSize().shortValue();
        } else {
            UXCode = UXCode + "_10";
        }

        //font name
        if (getFont() != null) {
            UXCode = UXCode + "_" + getFont();
        } else {
            UXCode = UXCode + "_EMPTY";
        }

        return UXCode;
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
