package com.acceso.wfcore.utils;

public class FileServerItemJS {
    String co_archiv;
    String no_archiv;
    String fe_archiv;
    String no_archiv2;

    public FileServerItemJS() {
    }

    public String getCo_archiv() {
        return co_archiv;
    }

    public void setCo_archiv(String co_archiv) {
        this.co_archiv = co_archiv;
    }

    public String getNo_archiv() {
        return no_archiv;
    }

    public void setNo_archiv(String no_archiv) {
        this.no_archiv = no_archiv;
    }

    public String getFe_archiv() {
        return fe_archiv;
    }

    public void setFe_archiv(String fe_archiv) {
        this.fe_archiv = fe_archiv;
    }

    public String getNo_archiv2() {
        return no_archiv2;
    }

    public void setNo_archiv2(String no_archiv2) {
        this.no_archiv2 = no_archiv2;
    }

    @Override
    public String toString() {
        return "FileServerItemJS{" +
                "co_archiv='" + co_archiv + '\'' +
                ", no_archiv='" + no_archiv + '\'' +
                ", fe_archiv='" + fe_archiv + '\'' +
                ", no_archiv2='" + no_archiv2 + '\'' +
                '}';
    }
}
