/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acceso.wfcore.dtos.legacy;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Transient;

/**
 *
 * @author edavalos
 */
@Entity
@NamedNativeQueries({
    @NamedNativeQuery(
            name = "get_solcre",
            query = "select * from expedi.pbdatosdelexpediente(:p_co_expedi)",
            resultClass = Solicitud_creditoDto.class)
})
public class Solicitud_creditoDto implements java.io.Serializable {

    @Id
    @Column(name = "co_expedi")
    private String co_expedi;
    @Column(name = "ti_credit")
    private String ti_credit;
    @Column(name = "im_financ")
    private String im_financ;
    @Column(name = "im_cuoini")
    private String im_cuoini;
    @Column(name = "ti_plagra")
    private String ti_plagra;
    @Column(name = "ca_plagra")
    private String ca_plagra;
    @Column(name = "ca_plamen")
    private String ca_plamen;
    @Column(name = "im_serban")
    private String im_serban;
    @Column(name = "im_cuotas")
    private String im_cuotas;
    @Column(name = "pr_tasmen")
    private String pr_tasmen;
    @Column(name = "ti_cuotas")
    private String ti_cuotas;
    @Column(name = "no_descre")
    private String no_descre;
    @Transient
    private String ti_plazo;

    public String getCo_expedi() {
        return co_expedi;
    }

    public void setCo_expedi(String co_expedi) {
        this.co_expedi = co_expedi;
    }

    public String getTi_credit() {
        return ti_credit;
    }

    public void setTi_credit(String ti_credit) {
        this.ti_credit = ti_credit;
    }

    public String getIm_financ() {
        return im_financ;
    }

    public void setIm_financ(String im_financ) {
        this.im_financ = im_financ;
    }

    public String getIm_cuoini() {
        return im_cuoini;
    }

    public void setIm_cuoini(String im_cuoini) {
        this.im_cuoini = im_cuoini;
    }

    public String getTi_plagra() {
        return ti_plagra;
    }

    public void setTi_plagra(String ti_plagra) {
        this.ti_plagra = ti_plagra;
    }

    public String getCa_plagra() {
        return ca_plagra;
    }

    public void setCa_plagra(String ca_plagra) {
        this.ca_plagra = ca_plagra;
    }

    public String getCa_plamen() {
        return ca_plamen;
    }

    public void setCa_plamen(String ca_plamen) {
        this.ca_plamen = ca_plamen;
    }

    public String getIm_serban() {
        return im_serban;
    }

    public void setIm_serban(String im_serban) {
        this.im_serban = im_serban;
    }

    public String getIm_cuotas() {
        return im_cuotas;
    }

    public void setIm_cuotas(String im_cuotas) {
        this.im_cuotas = im_cuotas;
    }

    public String getPr_tasmen() {
        return pr_tasmen;
    }

    public void setPr_tasmen(String pr_tasmen) {
        this.pr_tasmen = pr_tasmen;
    }

    public String getTi_cuotas() {
        return ti_cuotas;
    }

    public void setTi_cuotas(String ti_cuotas) {
        this.ti_cuotas = ti_cuotas;
    }

    public String getNo_descre() {
        return no_descre;
    }

    public void setNo_descre(String no_descre) {
        this.no_descre = no_descre;
    }

    public String getTi_plazo() {
        return ti_plazo;
    }

    public void setTi_plazo(String ti_plazo) {
        this.ti_plazo = ti_plazo;
    }
}