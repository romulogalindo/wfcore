/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acceso.wfcore.dtos.legacy;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Temporal;

/**
 *
 * @author john
 */
@Entity
@NamedNativeQueries({
    @NamedNativeQuery(
            name = "pfabaco",
            query = "select * from pbabaco(:p_co_expedi)",
            resultClass = ModeloAbacoDto.class)
})
public class ModeloAbacoDto implements Serializable {

    @Id
    @Column(name = "no_nombre")
    private String no_nombre;
    @Column(name = "no_apelli")
    private String no_apelli;
    @Column(name = "no_nacion")
    private String no_nacion;
    @Column(name = "fe_nacimi")
    private String fe_nacimi;
    @Column(name = "no_nivedu")
    private String no_nivedu;
    @Column(name = "co_docide")
    private String co_docide;
    @Column(name = "no_direcc")
    private String no_direcc;
    @Column(name = "no_propie")
    private String no_propie;
    @Column(name = "no_estciv")
    private String no_estciv;
    @Column(name = "no_distridom")
    private String no_distridom;
    @Column(name = "nu_telefo")
    private String nu_telefo;
    @Column(name = "nu_telcel")
    private String nu_telcel;
    @Column(name = "no_cenlab")
    private String no_cenlab;
    @Column(name = "no_dirlab")
    private String no_dirlab;
    @Column(name = "nu_tellab")
    private String nu_tellab;
    @Column(name = "fe_ingres")
    private String fe_ingres;
    @Column(name = "no_cargotit")
    private String no_cargotit;
    @Column(name = "no_ocupac")
    private String no_ocupac;
    @Column(name = "no_distrilab")
    private String no_distrilab;
    @Column(name = "im_ingnet")
    private String im_ingnet;
    @Column(name = "ti_estciv")
    private String ti_estciv;
    @Column(name = "no_proloc")
    private String no_proloc;
    @Column(name = "no_nomcon")
    private String no_nomcon;
    @Column(name = "no_apecon")
    private String no_apecon;
    @Column(name = "no_naccon")
    private String no_naccon;
    @Column(name = "fe_naccon")
    private String fe_naccon;
    @Column(name = "no_niveducon")
    private String no_niveducon;
    @Column(name = "co_doccon")
    private String co_doccon;
    @Column(name = "no_cenlabcon")
    private String no_cenlabcon;
    @Column(name = "no_dirlabcon")
    private String no_dirlabcon;
    @Column(name = "nu_telcon")
    private String nu_telcon;
    @Column(name = "no_ocupaccon")
    private String no_ocupaccon;
    @Column(name = "no_distrilabcon")
    private String no_distrilabcon;
    @Column(name = "no_cargocon")
    private String no_cargocon;
    @Column(name = "no_entida1")
    private String no_entida1;
    @Column(name = "im_cuenta1")
    private String im_cuenta1;
    @Column(name = "no_entida2")
    private String no_entida2;
    @Column(name = "im_cuenta2")
    private String im_cuenta2;
    @Column(name = "no_entida3")
    private String no_entida3;
    @Column(name = "im_cuenta3")
    private String im_cuenta3;
    @Column(name = "no_nomhij1")
    private String no_nomhij1;
    @Column(name = "fe_nachij1")
    private String fe_nachij1;
    @Column(name = "no_nomhij2")
    private String no_nomhij2;
    @Column(name = "fe_nachij2")
    private String fe_nachij2;
    @Column(name = "no_nomhij3")
    private String no_nomhij3;
    @Column(name = "fe_nachij3")
    private String fe_nachij3;
    @Column(name = "no_nomhij4")
    private String no_nomhij4;
    @Column(name = "fe_nachij4")
    private String fe_nachij4;
    @Column(name = "no_nomhij5")
    private String no_nomhij5;
    @Column(name = "fe_nachij5")
    private String fe_nachij5;
    @Column(name = "fe_caraba")
    private String fe_caraba;
    @Column(name = "no_dircor")
    private String no_dircor;
//    @Column(name = "nu_anoact")
//    private String nu_anoact;

    public String getNo_nombre() {
        return no_nombre;
    }

    public void setNo_nombre(String no_nombre) {
        this.no_nombre = no_nombre;
    }

    public String getNo_apelli() {
        return no_apelli;
    }

    public void setNo_apelli(String no_apelli) {
        this.no_apelli = no_apelli;
    }

    public String getNo_nacion() {
        return no_nacion;
    }

    public void setNo_nacion(String no_nacion) {
        this.no_nacion = no_nacion;
    }

    public String getFe_nacimi() {
        return fe_nacimi;
    }

    public void setFe_nacimi(String fe_nacimi) {
        this.fe_nacimi = fe_nacimi;
    }

    public String getNo_nivedu() {
        return no_nivedu;
    }

    public void setNo_nivedu(String no_nivedu) {
        this.no_nivedu = no_nivedu;
    }

    public String getCo_docide() {
        return co_docide;
    }

    public void setCo_docide(String co_docide) {
        this.co_docide = co_docide;
    }

    public String getNo_direcc() {
        return no_direcc;
    }

    public void setNo_direcc(String no_direcc) {
        this.no_direcc = no_direcc;
    }

    public String getNo_propie() {
        return no_propie;
    }

    public void setNo_propie(String no_propie) {
        this.no_propie = no_propie;
    }

    public String getNo_estciv() {
        return no_estciv;
    }

    public void setNo_estciv(String no_estciv) {
        this.no_estciv = no_estciv;
    }

    public String getNo_distridom() {
        return no_distridom;
    }

    public void setNo_distridom(String no_distridom) {
        this.no_distridom = no_distridom;
    }

    public String getNu_telefo() {
        return nu_telefo;
    }

    public void setNu_telefo(String nu_telefo) {
        this.nu_telefo = nu_telefo;
    }

    public String getNu_telcel() {
        return nu_telcel;
    }

    public void setNu_telcel(String nu_telcel) {
        this.nu_telcel = nu_telcel;
    }

    public String getNo_cenlab() {
        return no_cenlab;
    }

    public void setNo_cenlab(String no_cenlab) {
        this.no_cenlab = no_cenlab;
    }

    public String getNo_dirlab() {
        return no_dirlab;
    }

    public void setNo_dirlab(String no_dirlab) {
        this.no_dirlab = no_dirlab;
    }

    public String getNu_tellab() {
        return nu_tellab;
    }

    public void setNu_tellab(String nu_tellab) {
        this.nu_tellab = nu_tellab;
    }

    public String getFe_ingres() {
        return fe_ingres;
    }

    public void setFe_ingres(String fe_ingres) {
        this.fe_ingres = fe_ingres;
    }

    public String getNo_cargotit() {
        return no_cargotit;
    }

    public void setNo_cargotit(String no_cargotit) {
        this.no_cargotit = no_cargotit;
    }

    public String getNo_ocupac() {
        return no_ocupac;
    }

    public void setNo_ocupac(String no_ocupac) {
        this.no_ocupac = no_ocupac;
    }

    public String getNo_distrilab() {
        return no_distrilab;
    }

    public void setNo_distrilab(String no_distrilab) {
        this.no_distrilab = no_distrilab;
    }

    public String getIm_ingnet() {
        return im_ingnet;
    }

    public void setIm_ingnet(String im_ingnet) {
        this.im_ingnet = im_ingnet;
    }

    public String getTi_estciv() {
        return ti_estciv;
    }

    public void setTi_estciv(String ti_estciv) {
        this.ti_estciv = ti_estciv;
    }

    public String getNo_proloc() {
        return no_proloc;
    }

    public void setNo_proloc(String no_proloc) {
        this.no_proloc = no_proloc;
    }

    public String getNo_nomcon() {
        return no_nomcon;
    }

    public void setNo_nomcon(String no_nomcon) {
        this.no_nomcon = no_nomcon;
    }

    public String getNo_apecon() {
        return no_apecon;
    }

    public void setNo_apecon(String no_apecon) {
        this.no_apecon = no_apecon;
    }

    public String getNo_naccon() {
        return no_naccon;
    }

    public void setNo_naccon(String no_naccon) {
        this.no_naccon = no_naccon;
    }

    public String getFe_naccon() {
        return fe_naccon;
    }

    public void setFe_naccon(String fe_naccon) {
        this.fe_naccon = fe_naccon;
    }

    public String getNo_niveducon() {
        return no_niveducon;
    }

    public void setNo_niveducon(String no_niveducon) {
        this.no_niveducon = no_niveducon;
    }

    public String getCo_doccon() {
        return co_doccon;
    }

    public void setCo_doccon(String co_doccon) {
        this.co_doccon = co_doccon;
    }

    public String getNo_cenlabcon() {
        return no_cenlabcon;
    }

    public void setNo_cenlabcon(String no_cenlabcon) {
        this.no_cenlabcon = no_cenlabcon;
    }

    public String getNo_dirlabcon() {
        return no_dirlabcon;
    }

    public void setNo_dirlabcon(String no_dirlabcon) {
        this.no_dirlabcon = no_dirlabcon;
    }

    public String getNu_telcon() {
        return nu_telcon;
    }

    public void setNu_telcon(String nu_telcon) {
        this.nu_telcon = nu_telcon;
    }

    public String getNo_ocupaccon() {
        return no_ocupaccon;
    }

    public void setNo_ocupaccon(String no_ocupaccon) {
        this.no_ocupaccon = no_ocupaccon;
    }

    public String getNo_distrilabcon() {
        return no_distrilabcon;
    }

    public void setNo_distrilabcon(String no_distrilabcon) {
        this.no_distrilabcon = no_distrilabcon;
    }

    public String getNo_cargocon() {
        return no_cargocon;
    }

    public void setNo_cargocon(String no_cargocon) {
        this.no_cargocon = no_cargocon;
    }

    public String getNo_entida1() {
        return no_entida1;
    }

    public void setNo_entida1(String no_entida1) {
        this.no_entida1 = no_entida1;
    }

    public String getIm_cuenta1() {
        return im_cuenta1;
    }

    public void setIm_cuenta1(String im_cuenta1) {
        this.im_cuenta1 = im_cuenta1;
    }

    public String getNo_entida2() {
        return no_entida2;
    }

    public void setNo_entida2(String no_entida2) {
        this.no_entida2 = no_entida2;
    }

    public String getIm_cuenta2() {
        return im_cuenta2;
    }

    public void setIm_cuenta2(String im_cuenta2) {
        this.im_cuenta2 = im_cuenta2;
    }

    public String getNo_entida3() {
        return no_entida3;
    }

    public void setNo_entida3(String no_entida3) {
        this.no_entida3 = no_entida3;
    }

    public String getIm_cuenta3() {
        return im_cuenta3;
    }

    public void setIm_cuenta3(String im_cuenta3) {
        this.im_cuenta3 = im_cuenta3;
    }

    public String getNo_nomhij1() {
        return no_nomhij1;
    }

    public void setNo_nomhij1(String no_nomhij1) {
        this.no_nomhij1 = no_nomhij1;
    }

    public String getFe_nachij1() {
        return fe_nachij1;
    }

    public void setFe_nachij1(String fe_nachij1) {
        this.fe_nachij1 = fe_nachij1;
    }

    public String getNo_nomhij2() {
        return no_nomhij2;
    }

    public void setNo_nomhij2(String no_nomhij2) {
        this.no_nomhij2 = no_nomhij2;
    }

    public String getFe_nachij2() {
        return fe_nachij2;
    }

    public void setFe_nachij2(String fe_nachij2) {
        this.fe_nachij2 = fe_nachij2;
    }

    public String getNo_nomhij3() {
        return no_nomhij3;
    }

    public void setNo_nomhij3(String no_nomhij3) {
        this.no_nomhij3 = no_nomhij3;
    }

    public String getFe_nachij3() {
        return fe_nachij3;
    }

    public void setFe_nachij3(String fe_nachij3) {
        this.fe_nachij3 = fe_nachij3;
    }

    public String getNo_nomhij4() {
        return no_nomhij4;
    }

    public void setNo_nomhij4(String no_nomhij4) {
        this.no_nomhij4 = no_nomhij4;
    }

    public String getFe_nachij4() {
        return fe_nachij4;
    }

    public void setFe_nachij4(String fe_nachij4) {
        this.fe_nachij4 = fe_nachij4;
    }

    public String getNo_nomhij5() {
        return no_nomhij5;
    }

    public void setNo_nomhij5(String no_nomhij5) {
        this.no_nomhij5 = no_nomhij5;
    }

    public String getFe_nachij5() {
        return fe_nachij5;
    }

    public void setFe_nachij5(String fe_nachij5) {
        this.fe_nachij5 = fe_nachij5;
    }

    public String getFe_caraba() {
        return fe_caraba;
    }

    public void setFe_caraba(String fe_caraba) {
        this.fe_caraba = fe_caraba;
    }

    public String getNo_dircor() {
        return no_dircor;
    }

    public void setNo_dircor(String no_dircor) {
        this.no_dircor = no_dircor;
    }

//    public String getNu_anoact() {
//        return nu_anoact;
//    }
//
//    public void setNu_anoact(String nu_anoact) {
//        this.nu_anoact = nu_anoact;
//    }
}
