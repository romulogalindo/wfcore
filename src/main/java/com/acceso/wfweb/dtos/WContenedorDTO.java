package com.acceso.wfweb.dtos;

import com.acceso.wfweb.utils.Values;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import java.io.Serializable;

/**
 * @author Rómulo Galindo<romulogalindo@gmail.com>
 * Created on 13 dic. 2018, 19:06:46
 */
@Entity
@NamedNativeQueries({
        @NamedNativeQuery(
                name = Values.QUERYS_WEB_SELECT_PFCONTEN,
                query = "select * from frawor4.pfconten( :p_co_conten )",
                resultClass = WContenedorDTO.class)
})
public class WContenedorDTO implements Serializable {

    @Id
    int co_conten;

    String no_contit;

    boolean il_sesion;

    boolean il_icoatr;

    boolean il_icoref;

    boolean il_icoxls;

    boolean il_icopdf;

    boolean il_icoclo;

    boolean il_icohlp;

    boolean il_icoimp;

    boolean il_icolog;

    boolean il_deterr;

    public WContenedorDTO() {
    }

    public int getCo_conten() {
        return co_conten;
    }

    public void setCo_conten(int co_conten) {
        this.co_conten = co_conten;
    }

    public String getNo_contit() {
        return no_contit;
    }

    public void setNo_contit(String no_contit) {
        this.no_contit = no_contit;
    }

    public boolean isIl_sesion() {
        return il_sesion;
    }

    public void setIl_sesion(boolean il_sesion) {
        this.il_sesion = il_sesion;
    }

    public boolean isIl_icoatr() {
        return il_icoatr;
    }

    public void setIl_icoatr(boolean il_icoatr) {
        this.il_icoatr = il_icoatr;
    }

    public boolean isIl_icoref() {
        return il_icoref;
    }

    public void setIl_icoref(boolean il_icoref) {
        this.il_icoref = il_icoref;
    }

    public boolean isIl_icoxls() {
        return il_icoxls;
    }

    public void setIl_icoxls(boolean il_icoxls) {
        this.il_icoxls = il_icoxls;
    }

    public boolean isIl_icopdf() {
        return il_icopdf;
    }

    public void setIl_icopdf(boolean il_icopdf) {
        this.il_icopdf = il_icopdf;
    }

    public boolean isIl_icoclo() {
        return il_icoclo;
    }

    public void setIl_icoclo(boolean il_icoclo) {
        this.il_icoclo = il_icoclo;
    }

    public boolean isIl_icohlp() {
        return il_icohlp;
    }

    public void setIl_icohlp(boolean il_icohlp) {
        this.il_icohlp = il_icohlp;
    }

    public boolean isIl_icoimp() {
        return il_icoimp;
    }

    public void setIl_icoimp(boolean il_icoimp) {
        this.il_icoimp = il_icoimp;
    }

    public boolean isIl_icolog() {
        return il_icolog;
    }

    public void setIl_icolog(boolean il_icolog) {
        this.il_icolog = il_icolog;
    }

    public boolean isIl_deterr() {
        return il_deterr;
    }

    public void setIl_deterr(boolean il_deterr) {
        this.il_deterr = il_deterr;
    }
}
