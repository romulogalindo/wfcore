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
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 *
 * @author edavalos
 */
@Entity
@NamedNativeQueries({
    @NamedNativeQuery(
            name = "get_repsol",
            query = "select * from expedi.pbcooperativa_pacifico(:p_co_expedi)",
//            query = "select\n"
//            + "   pp.co_expedi,\n"
//            + "   pp.no_apepat, pp.no_apemat, pp.no_nombre, pp.co_docide,\n"
//            + "   pp.fe_nacimi, pp.ti_genero, pp.ti_estciv, na.no_nacion,\n"
//            + "   pp.no_corele, pp.nu_telefo as nu_telcel, ed.no_nivedu,\n"
//            + "   coalesce(pd.no_direcc, '') || ' ' || coalesce(pd.no_urbani, '') as no_dirdom, dd.nu_camino as nu_camdom, dd.nu_manzan as nu_mandom, dd.nu_lotiza as nu_lotdom, dd.ti_interi as ti_intdom, dd.nu_interi as nu_intdom,\n"
//            + "   pd.no_refere as no_refdom,\n"
//            + "   ud.no_distri as no_disdom, ud.no_provin as no_prodom, ud.no_depart as no_depdom,\n"
//            + "   pd.nu_telefo as nu_teldom,\n"
//            + "   pl.no_cenlab, pl.co_doctri, pl.no_cargo, pl.ti_deplab,\n"
//            + "   pl.fe_ingres, pl.no_acteco,\n"
//            + "   pl.no_direcc || ' ' || pl.no_urbani as no_dirlab, dl.nu_camino as nu_camlab, dl.nu_manzan as nu_manlab, dl.nu_lotiza as nu_lotlab, dl.ti_interi as ti_intlab, dl.nu_interi as nu_intlab,\n"
//            + "   pl.no_refere as no_reflab,\n"
//            + "   ul.no_distri as no_dislab, ul.no_provin as no_prolab, ul.no_depart as no_deplab,\n"
//            + "   pl.nu_telefo as nu_tellab\n"
//            + "from\n"
//            + "   person.tbperson pp\n"
//            + "   left join person.tbperdom pd on pp.co_expedi = pd.co_expedi and pp.ti_solici = pd.ti_solici and pd.nu_perdom = 1\n"
//            + "   left join direcc.tbdirecc dd on pd.co_direcc = dd.co_direcc\n"
//            + "   left join person.tbperlab pl on pp.co_expedi = pl.co_expedi and pp.ti_solici = pl.ti_solici and pl.nu_perlab = 1\n"
//            + "   left join direcc.tbdirecc dl on pl.co_direcc = dl.co_direcc\n"
//            + "   left join public.tcubigeo ud on pd.co_ubigeo = ud.co_ubigeo\n"
//            + "   left join public.tcubigeo ul on pl.co_ubigeo = ul.co_ubigeo\n"
//            + "   left join person.tcnacion na on pp.co_nacion = na.co_nacion\n"
//            + "   left join person.tcnivedu ed on pp.ti_nivedu = ed.ti_nivedu\n"
//            + "where pp.co_expedi = :p_co_expedi\n"
//            + "and pp.ti_solici = 'TI';",
            resultClass = Reporte_SolicitudDto.class)
})
public class Reporte_SolicitudDto implements java.io.Serializable {

    @Id
    @Column(name = "co_expedi")
    private int co_Expedi;
    @Column(name = "no_apepat")
    private String no_apepat;
    @Column(name = "no_apemat")
    private String no_apemat;
    @Column(name = "no_nombre")
    private String no_nombre;
    @Column(name = "co_docide")
    private String co_docide;
    @Column(name = "fe_nacimi")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fe_nacimi;
    @Column(name = "ti_genero")
    private String ti_genero;
    @Column(name = "ti_estciv")
    private String ti_estciv;
    @Column(name = "no_nacion")
    private String no_nacion;
    @Column(name = "no_corele")
    private String no_corele;
    @Column(name = "nu_telcel")
    private String nu_telcel;
    @Column(name = "no_nivedu")
    private String no_nivedu;
    @Column(name = "no_dirdom")
    private String no_dirdom;
    @Column(name = "nu_camdom")
    private String nu_camdom;
    @Column(name = "nu_mandom")
    private String nu_mandom;
    @Column(name = "nu_lotdom")
    private String nu_lotdom;
    @Column(name = "ti_intdom")
    private Integer ti_intdom;
    @Column(name = "nu_intdom")
    private String nu_intdom;
    @Column(name = "no_refdom")
    private String no_refdom;
    @Column(name = "no_disdom")
    private String no_disdom;
    @Column(name = "no_prodom")
    private String no_prodom;
    @Column(name = "no_depdom")
    private String no_depdom;
    @Column(name = "nu_teldom")
    private String nu_teldom;
    @Column(name = "no_cenlab")
    private String no_cenlab;
    @Column(name = "co_doctri")
    private String co_doctri;
    @Column(name = "no_cargo")
    private String no_cargo;
    @Column(name = "ti_deplab")
    private String ti_deplab;
    @Column(name = "fe_ingres")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fe_ingres;
    @Column(name = "no_acteco")
    private String no_acteco;
    @Column(name = "no_dirlab")
    private String no_dirlab;
    @Column(name = "nu_camlab")
    private String nu_camlab;
    @Column(name = "nu_manlab")
    private String nu_manlab;
    @Column(name = "nu_lotlab")
    private String nu_lotlab;
    @Column(name = "ti_intlab")
    private Integer ti_intlab;
    @Column(name = "nu_intlab")
    private String nu_intlab;
    @Column(name = "no_reflab")
    private String no_reflab;
    @Column(name = "no_dislab")
    private String no_dislab;
    @Column(name = "no_prolab")
    private String no_prolab;
    @Column(name = "no_deplab")
    private String no_deplab;
    @Column(name = "nu_tellab")
    private String nu_tellab;

    public int getCo_Expedi() {
        return co_Expedi;
    }

    public void setCo_Expedi(int co_Expedi) {
        this.co_Expedi = co_Expedi;
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

    public String getTi_estciv() {
        return ti_estciv;
    }

    public void setTi_estciv(String ti_estciv) {
        this.ti_estciv = ti_estciv;
    }

    public String getNo_nacion() {
        return no_nacion;
    }

    public void setNo_nacion(String no_nacion) {
        this.no_nacion = no_nacion;
    }

    public String getNo_corele() {
        return no_corele;
    }

    public void setNo_corele(String no_corele) {
        this.no_corele = no_corele;
    }

    public String getNu_telcel() {
        return nu_telcel;
    }

    public void setNu_telcel(String nu_telcel) {
        this.nu_telcel = nu_telcel;
    }

    public String getNo_nivedu() {
        return no_nivedu;
    }

    public void setNo_nivedu(String no_nivedu) {
        this.no_nivedu = no_nivedu;
    }

    public String getNo_dirdom() {
        return no_dirdom;
    }

    public void setNo_dirdom(String no_dirdom) {
        this.no_dirdom = no_dirdom;
    }

    public String getNu_camdom() {
        return nu_camdom;
    }

    public void setNu_camdom(String nu_camdom) {
        this.nu_camdom = nu_camdom;
    }

    public String getNu_mandom() {
        return nu_mandom;
    }

    public void setNu_mandom(String nu_mandom) {
        this.nu_mandom = nu_mandom;
    }

    public String getNu_lotdom() {
        return nu_lotdom;
    }

    public void setNu_lotdom(String nu_lotdom) {
        this.nu_lotdom = nu_lotdom;
    }

    public Integer getTi_intdom() {
        return ti_intdom;
    }

    public void setTi_intdom(Integer ti_intdom) {
        this.ti_intdom = ti_intdom;
    }

    public String getNu_intdom() {
        return nu_intdom;
    }

    public void setNu_intdom(String nu_intdom) {
        this.nu_intdom = nu_intdom;
    }

    public String getNo_refdom() {
        return no_refdom;
    }

    public void setNo_refdom(String no_refdom) {
        this.no_refdom = no_refdom;
    }

    public String getNo_disdom() {
        return no_disdom;
    }

    public void setNo_disdom(String no_disdom) {
        this.no_disdom = no_disdom;
    }

    public String getNo_prodom() {
        return no_prodom;
    }

    public void setNo_prodom(String no_prodom) {
        this.no_prodom = no_prodom;
    }

    public String getNo_depdom() {
        return no_depdom;
    }

    public void setNo_depdom(String no_depdom) {
        this.no_depdom = no_depdom;
    }

    public String getNu_teldom() {
        return nu_teldom;
    }

    public void setNu_teldom(String nu_teldom) {
        this.nu_teldom = nu_teldom;
    }

    public String getNo_cenlab() {
        return no_cenlab;
    }

    public void setNo_cenlab(String no_cenlab) {
        this.no_cenlab = no_cenlab;
    }

    public String getCo_doctri() {
        return co_doctri;
    }

    public void setCo_doctri(String co_doctri) {
        this.co_doctri = co_doctri;
    }

    public String getNo_cargo() {
        return no_cargo;
    }

    public void setNo_cargo(String no_cargo) {
        this.no_cargo = no_cargo;
    }

    public String getTi_deplab() {
        return ti_deplab;
    }

    public void setTi_deplab(String ti_deplab) {
        this.ti_deplab = ti_deplab;
    }

    public Date getFe_ingres() {
        return fe_ingres;
    }

    public void setFe_ingres(Date fe_ingres) {
        this.fe_ingres = fe_ingres;
    }

    public String getNo_acteco() {
        return no_acteco;
    }

    public void setNo_acteco(String no_acteco) {
        this.no_acteco = no_acteco;
    }

    public String getNo_dirlab() {
        return no_dirlab;
    }

    public void setNo_dirlab(String no_dirlab) {
        this.no_dirlab = no_dirlab;
    }

    public String getNu_camlab() {
        return nu_camlab;
    }

    public void setNu_camlab(String nu_camlab) {
        this.nu_camlab = nu_camlab;
    }

    public String getNu_manlab() {
        return nu_manlab;
    }

    public void setNu_manlab(String nu_manlab) {
        this.nu_manlab = nu_manlab;
    }

    public String getNu_lotlab() {
        return nu_lotlab;
    }

    public void setNu_lotlab(String nu_lotlab) {
        this.nu_lotlab = nu_lotlab;
    }

    public Integer getTi_intlab() {
        return ti_intlab;
    }

    public void setTi_intlab(Integer ti_intlab) {
        this.ti_intlab = ti_intlab;
    }

    public String getNu_intlab() {
        return nu_intlab;
    }

    public void setNu_intlab(String nu_intlab) {
        this.nu_intlab = nu_intlab;
    }

    public String getNo_reflab() {
        return no_reflab;
    }

    public void setNo_reflab(String no_reflab) {
        this.no_reflab = no_reflab;
    }

    public String getNo_dislab() {
        return no_dislab;
    }

    public void setNo_dislab(String no_dislab) {
        this.no_dislab = no_dislab;
    }

    public String getNo_prolab() {
        return no_prolab;
    }

    public void setNo_prolab(String no_prolab) {
        this.no_prolab = no_prolab;
    }

    public String getNo_deplab() {
        return no_deplab;
    }

    public void setNo_deplab(String no_deplab) {
        this.no_deplab = no_deplab;
    }

    public String getNu_tellab() {
        return nu_tellab;
    }

    public void setNu_tellab(String nu_tellab) {
        this.nu_tellab = nu_tellab;
    }
}