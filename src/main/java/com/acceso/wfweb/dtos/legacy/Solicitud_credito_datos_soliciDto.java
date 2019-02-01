/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acceso.wfweb.dtos.legacy;

import com.acceso.wfweb.utils.Values;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Temporal;

/**
 *
 * @author edavalos
 */
@Entity
@NamedNativeQueries({
    @NamedNativeQuery(
            name = Values.QUERYS_WEB_SELECT_SOLCRE_SOLICI,
            query = "select * from expedi.pbdatosdelcliente(:p_co_expedi,:p_ti_solici)",
            resultClass = Solicitud_credito_datos_soliciDto.class)
})
public class Solicitud_credito_datos_soliciDto implements java.io.Serializable {

    @Id
    @Column(name = "no_apepat")
    private String no_apepat;
    @Column(name = "no_apemat")
    private String no_apemat;
    @Column(name = "no_nombre")
    private String no_nombre;
    @Column(name = "ti_docide")
    private String ti_docide;
    @Column(name = "co_docide")
    private String co_docide;
    @Column(name = "fe_nacimi")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fe_nacimi;
    @Column(name = "ti_genero")
    private String ti_genero;
    @Column(name = "no_nacion")
    private String no_nacion;
    @Column(name = "no_estciv")
    private String no_estciv;
    @Column(name = "no_direcc")
    private String no_direcc;
    @Column(name = "no_urbani")
    private String no_urbani;
    @Column(name = "no_distri")
    private String no_distri;
    @Column(name = "no_provin")
    private String no_provin;
    @Column(name = "no_depart")
    private String no_depart;
    @Column(name = "no_refere")
    private String no_refere;
    @Column(name = "no_corele")
    private String no_corele;
    @Column(name = "nu_telefo")
    private String nu_telefo;
    @Column(name = "ti_sitlab")
    private String ti_sitlab;
    @Column(name = "no_carlab")
    private String no_carlab;
    @Column(name = "no_dirlab")
    private String no_dirlab;
    @Column(name = "nu_ruclab")
    private String nu_ruclab;
    @Column(name = "no_acteco")
    private String no_acteco;
    @Column(name = "nu_tellab")
    private String nu_tellab;
    @Column(name = "fe_ingres")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fe_ingres;
    @Column(name = "im_ingnet")
    private Double im_ingnet;

    public Solicitud_credito_datos_soliciDto(String no_apepat, String no_apemat, String no_nombre, String ti_docide, String co_docide, Date fe_nacimi, String ti_genero, String no_nacion, String no_estciv, String no_direcc, String no_urbani, String no_distri, String no_provin, String no_depart, String no_refere, String no_corele, String nu_telefo, String ti_sitlab, String no_carlab, String no_dirlab, String nu_ruclab, String no_acteco, String nu_tellab, Date fe_ingres, Double im_ingnet) {
        this.no_apepat = no_apepat;
        this.no_apemat = no_apemat;
        this.no_nombre = no_nombre;
        this.ti_docide = ti_docide;
        this.co_docide = co_docide;
        this.fe_nacimi = fe_nacimi;
        this.ti_genero = ti_genero;
        this.no_nacion = no_nacion;
        this.no_estciv = no_estciv;
        this.no_direcc = no_direcc;
        this.no_urbani = no_urbani;
        this.no_distri = no_distri;
        this.no_provin = no_provin;
        this.no_depart = no_depart;
        this.no_refere = no_refere;
        this.no_corele = no_corele;
        this.nu_telefo = nu_telefo;
        this.ti_sitlab = ti_sitlab;
        this.no_carlab = no_carlab;
        this.no_dirlab = no_dirlab;
        this.nu_ruclab = nu_ruclab;
        this.no_acteco = no_acteco;
        this.nu_tellab = nu_tellab;
        this.fe_ingres = fe_ingres;
        this.im_ingnet = im_ingnet;
    }

    public Solicitud_credito_datos_soliciDto() {
    }
    
    public String getNo_apepat() {
        return no_apepat;
    }

    public void setNo_apepat(String no_apepat) {
        this.no_apepat = no_apepat;
    }

    public String getNo_apemat() {
        return no_apemat;
    }

    public void setNo_apemat(String no_apemat) {
        this.no_apemat = no_apemat;
    }

    public String getNo_nombre() {
        return no_nombre;
    }

    public void setNo_nombre(String no_nombre) {
        this.no_nombre = no_nombre;
    }

    public String getTi_docide() {
        return ti_docide;
    }

    public void setTi_docide(String ti_docide) {
        this.ti_docide = ti_docide;
    }

    public String getCo_docide() {
        return co_docide;
    }

    public void setCo_docide(String co_docide) {
        this.co_docide = co_docide;
    }

    public Date getFe_nacimi() {
        return fe_nacimi;
    }

    public void setFe_nacimi(Date fe_nacimi) {
        this.fe_nacimi = fe_nacimi;
    }

    public String getTi_genero() {
        return ti_genero;
    }

    public void setTi_genero(String ti_genero) {
        this.ti_genero = ti_genero;
    }

    public String getNo_nacion() {
        return no_nacion;
    }

    public void setNo_nacion(String no_nacion) {
        this.no_nacion = no_nacion;
    }

    public String getNo_estciv() {
        return no_estciv;
    }

    public void setNo_estciv(String no_estciv) {
        this.no_estciv = no_estciv;
    }

    public String getNo_direcc() {
        return no_direcc;
    }

    public void setNo_direcc(String no_direcc) {
        this.no_direcc = no_direcc;
    }

    public String getNo_urbani() {
        return no_urbani;
    }

    public void setNo_urbani(String no_urbani) {
        this.no_urbani = no_urbani;
    }

    public String getNo_distri() {
        return no_distri;
    }

    public void setNo_distri(String no_distri) {
        this.no_distri = no_distri;
    }

    public String getNo_provin() {
        return no_provin;
    }

    public void setNo_provin(String no_provin) {
        this.no_provin = no_provin;
    }

    public String getNo_depart() {
        return no_depart;
    }

    public void setNo_depart(String no_depart) {
        this.no_depart = no_depart;
    }

    public String getNo_refere() {
        return no_refere;
    }

    public void setNo_refere(String no_refere) {
        this.no_refere = no_refere;
    }

    public String getNo_corele() {
        return no_corele;
    }

    public void setNo_corele(String no_corele) {
        this.no_corele = no_corele;
    }

    public String getNu_telefo() {
        return nu_telefo;
    }

    public void setNu_telefo(String nu_telefo) {
        this.nu_telefo = nu_telefo;
    }

    public String getTi_sitlab() {
        return ti_sitlab;
    }

    public void setTi_sitlab(String ti_sitlab) {
        this.ti_sitlab = ti_sitlab;
    }

    public String getNo_carlab() {
        return no_carlab;
    }

    public void setNo_carlab(String no_carlab) {
        this.no_carlab = no_carlab;
    }

    public String getNo_dirlab() {
        return no_dirlab;
    }

    public void setNo_dirlab(String no_dirlab) {
        this.no_dirlab = no_dirlab;
    }

    public String getNu_ruclab() {
        return nu_ruclab;
    }

    public void setNu_ruclab(String nu_ruclab) {
        this.nu_ruclab = nu_ruclab;
    }

    public String getNo_acteco() {
        return no_acteco;
    }

    public void setNo_acteco(String no_acteco) {
        this.no_acteco = no_acteco;
    }

    public String getNu_tellab() {
        return nu_tellab;
    }

    public void setNu_tellab(String nu_tellab) {
        this.nu_tellab = nu_tellab;
    }

    public Date getFe_ingres() {
        return fe_ingres;
    }

    public void setFe_ingres(Date fe_ingres) {
        this.fe_ingres = fe_ingres;
    }

    public Double getIm_ingnet() {
        return im_ingnet;
    }

    public void setIm_ingnet(Double im_ingnet) {
        this.im_ingnet = im_ingnet;
    }
    
    public List<String> listaMetodos(){
        List<String> variables = new ArrayList<>();
        
        variables.add("getNo_apepat");
        variables.add("getNo_apemat");
        variables.add("getNo_nombre");
        variables.add("getTi_docide");
        variables.add("getCo_docide");
        variables.add("getFe_nacimi");
        variables.add("getTi_genero");
        variables.add("getNo_nacion");
        variables.add("getNo_estciv");
        variables.add("getNo_direcc");
        variables.add("getNo_urbani");
        variables.add("getNo_distri");
        variables.add("getNo_provin");
        variables.add("getNo_depart");
        variables.add("getNo_refere");
        variables.add("getNo_corele");
        variables.add("getNu_telefo");
        variables.add("getTi_sitlab");
        variables.add("getNo_carlab");
        variables.add("getNo_dirlab");
        variables.add("getNu_ruclab");
        variables.add("getNo_acteco");
        variables.add("getNu_tellab");
        variables.add("getFe_ingres");
        variables.add("getIm_ingnet");
        
        return variables;
    }
}