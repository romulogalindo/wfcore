package com.acceso.wfcore.utils;

/**
 * @author rgalindo
 */
public class RegJson {

    int co_pagreg;
    String va_pagreg;
    String tx_pagreg;
    String no_pagreg;
    Integer ti_pagreg;
    String ti_estreg;
    String ur_pagreg;

    public RegJson() {
    }

//    public RegJson(int co_pagreg, String va_pagreg, String tx_pagreg){
//        this.co_pagreg = co_pagreg;
//        this.va_pagreg = va_pagreg;
//        this.tx_pagreg = tx_pagreg;
//    }

    public RegJson(int co_pagreg, String va_pagreg, String tx_pagreg, String no_pagreg, Integer ti_pagreg, String ti_estreg, String ur_pagreg) {
        this.co_pagreg = co_pagreg;
        this.va_pagreg = va_pagreg;
        this.tx_pagreg = tx_pagreg;
        this.no_pagreg = no_pagreg;
        this.ti_pagreg = ti_pagreg;
        this.ti_estreg = ti_estreg;
        this.ur_pagreg = ur_pagreg;
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

    public String getNo_pagreg() {
        return no_pagreg;
    }

    public void setNo_pagreg(String no_pagreg) {
        this.no_pagreg = no_pagreg;
    }

    public Integer getTi_pagreg() {
        return ti_pagreg;
    }

    public void setTi_pagreg(Integer ti_pagreg) {
        this.ti_pagreg = ti_pagreg;
    }

    public String getTi_estreg() {
        return ti_estreg;
    }

    public void setTi_estreg(String ti_estreg) {
        this.ti_estreg = ti_estreg;
    }

    public String getUr_pagreg() {
        return ur_pagreg;
    }

    public void setUr_pagreg(String ur_pagreg) {
        this.ur_pagreg = ur_pagreg;
    }
}
