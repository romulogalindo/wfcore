package com.acceso.wfcore.utils;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author rgalindo
 */
public class CellJson extends StandarRegisterJson {

    String no_addres;
    String va_pagreg;
    Object ls_styles;
    Integer nu_colspan;
    Integer nu_rowspan;
    ColumnConfigJson columnConfigJson;

    public CellJson(Object obj) {
        ScriptObjectMirror opts = (ScriptObjectMirror) obj;
        no_addres = opts.get("no_addres") == null ? "A1" : opts.get("no_addres").toString();

        if (opts.get("va_pagreg") != null) {
            va_pagreg = "" + opts.get("va_pagreg");
        }

        if (opts.get("nu_colspan") != null) {
            nu_colspan = Util.toInt(opts.get("nu_colspan"), -1);
        }

        if (opts.get("nu_rowspan") != null) {
            nu_rowspan = Util.toInt(opts.get("nu_rowspan"), -1);
        }

//        System.out.println("ls_styles::" + opts.get("ls_styles"));
        if (opts.get("ls_styles") != null) {
            ScriptObjectMirror ls_st = (ScriptObjectMirror) opts.get("ls_styles");

            ColumnConfigJson colcfg = new ColumnConfigJson();
            if (ls_st.get("align") != null) {
                colcfg.setAlign(ls_st.get("align") == null ? "LEFT" : ls_st.get("align").toString());
            }

            if (ls_st.get("valign") != null) {
                colcfg.setValign(ls_st.get("valign") == null ? "CENTER" : ls_st.get("valign").toString());
            }

            if (ls_st.get("bold") != null) {
                colcfg.setBold(ls_st.get("bold") == null ? false : Boolean.parseBoolean(ls_st.get("bold").toString()));
            }

            if (ls_st.get("italic") != null) {
                colcfg.setItalic(ls_st.get("italic") == null ? false : Boolean.parseBoolean(ls_st.get("italic").toString()));
            }

            if (ls_st.get("underline") != null) {
                colcfg.setUnderline(ls_st.get("underline") == null ? false : Boolean.parseBoolean(ls_st.get("underline").toString()));
            }

            if (ls_st.get("size") != null) {
                colcfg.setSize(ls_st.get("size") == null ? null : Integer.parseInt(ls_st.get("size").toString()));
            }

            if (ls_st.get("font") != null) {
                colcfg.setFont(ls_st.get("font") == null ? null : ls_st.get("font").toString());
            }

//            if (ls_st.get("alig") != null) {
//                colcfg.setHwrap(o1.get("hwrap") == null ? false : Boolean.parseBoolean(o1.get("hwrap").toString()));
//            }

            colcfg.setWidth(ls_st.get("width") == null ? -1 : Integer.parseInt(ls_st.get("width").toString()));
//            colcfg.setVwrap(o1.get("vwrap") == null ? false : Boolean.parseBoolean(o1.get("vwrap").toString()));

            if (ls_st.get("color") != null) {
                colcfg.setColor(ls_st.get("color") == null ? null : ls_st.get("color").toString());
            }

            if (ls_st.get("bgcolor") != null) {
                colcfg.setBgcolor(ls_st.get("bgcolor") == null ? null : ls_st.get("color").toString());
            }

            if (ls_st.get("borderTop") != null) {
                colcfg.setBorderTop(ls_st.get("borderTop") == null ? "THIN" : ls_st.get("align").toString());
            }
            if (ls_st.get("borderRight") != null) {
                colcfg.setBorderTop(ls_st.get("borderRight") == null ? "THIN" : ls_st.get("borderRight").toString());
            }
            if (ls_st.get("borderBottom") != null) {
                colcfg.setBorderTop(ls_st.get("borderBottom") == null ? "THIN" : ls_st.get("borderBottom").toString());
            }
            if (ls_st.get("borderLeft") != null) {
                colcfg.setBorderTop(ls_st.get("borderLeft") == null ? "THIN" : ls_st.get("borderLeft").toString());
            }

//            colcfg.setType(o1.get("type") == null ? null : o1.get("type").toString());
//            colcfg.setFormat(o1.get("format") == null ? "" : o1.get("format").toString());

            columnConfigJson = colcfg;
        }


    }

    public String getNo_addres() {
        return no_addres;
    }

    public void setNo_addres(String no_addres) {
        this.no_addres = no_addres;
    }

    public String getVa_pagreg() {
        return va_pagreg;
    }

    public void setVa_pagreg(String va_pagreg) {
        this.va_pagreg = va_pagreg;
    }

    public Integer getNu_colspan() {
        return nu_colspan;
    }

    public void setNu_colspan(Integer nu_colspan) {
        this.nu_colspan = nu_colspan;
    }

    public Integer getNu_rowspan() {
        return nu_rowspan;
    }

    public void setNu_rowspan(Integer nu_rowspan) {
        this.nu_rowspan = nu_rowspan;
    }

    public static CellJson NEW(Object obj) {
        return new CellJson(obj);
    }

    public Object getLs_styles() {
        return ls_styles;
    }

    public void setLs_styles(Object ls_styles) {
        this.ls_styles = ls_styles;
    }

    public ColumnConfigJson getColumnConfigJson() {
        return columnConfigJson;
    }

    public void setColumnConfigJson(ColumnConfigJson columnConfigJson) {
        this.columnConfigJson = columnConfigJson;
    }

    @Override
    public String toString() {
        return "CellJson{" +
                "no_addres='" + no_addres + '\'' +
                ", va_pagreg='" + va_pagreg + '\'' +
                ", ls_styles=" + ls_styles +
                ", nu_colspan=" + nu_colspan +
                ", nu_rowspan=" + nu_rowspan +
                ", columnConfigJson=" + columnConfigJson +
                '}';
    }
}
