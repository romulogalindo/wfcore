package com.acceso.wfcore.utils;

/**
 * @author rgalindo
 */
public class RegJson {

    int co_pagreg;
    String va_pagreg;
    String tx_pagreg;

    public RegJson() {
    }

    public RegJson(int co_pagreg, String va_pagreg, String tx_pagreg){
        this.co_pagreg = co_pagreg;
        this.va_pagreg = va_pagreg;
        this.tx_pagreg = tx_pagreg;
    }

    public int getCo_pagreg() {
        return co_pagreg;
    }

    public void setCo_pagreg(int co_pagreg) {
        this.co_pagreg = co_pagreg;
    }

    public String getVa_pagreg() {
        return va_pagreg;
    }

    public void setVa_pagreg(String va_pagreg) {
        this.va_pagreg = va_pagreg;
    }

    public String getTx_pagreg() {
        return tx_pagreg;
    }

    public void setTx_pagreg(String tx_pagreg) {
        this.tx_pagreg = tx_pagreg;
    }
}
