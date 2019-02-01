/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acceso.wfweb.dtos.legacy;

import com.acceso.wfweb.utils.Values;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

/**
 *
 * @author edsonsuarez
 */
@Entity
@NamedNativeQueries({
    @NamedNativeQuery(
	name = Values.QUERYS_WEB_SELECT_PBMODAE,
    query = "select "
   +"max(case when co_pagreg = 1 then va_pagreg end) as ti_docume, "
   +"max(case when co_pagreg = 2 then va_pagreg end) as fe_docume, "
   +"max(case when co_pagreg = 3 then va_pagreg end) as no_usureg, "
   +"max(case when co_pagreg = 4 then va_pagreg end) as co_docreg, "
   +"max(case when co_pagreg = 5 then va_pagreg end) as no_cliasu, "
   +"max(case when co_pagreg = 6 then va_pagreg end) as co_docasu, "
   +"max(case when co_pagreg = 7 then va_pagreg end) as no_dirasu, "
   +"max(case when co_pagreg = 8 then va_pagreg end) as no_depasu, "
   +"max(case when co_pagreg = 9 then va_pagreg end) as no_proasu, "
   +"max(case when co_pagreg =10 then va_pagreg end) as no_disasu, "
   +"max(case when co_pagreg =11 then va_pagreg end) as no_client, "
   +"max(case when co_pagreg =12 then va_pagreg end) as co_contra, "
   +"max(case when co_pagreg =13 then va_pagreg end) as fe_activa, "
   +"max(case when co_pagreg =14 then va_pagreg end) as im_presta, "
   +"max(case when co_pagreg =15 then va_pagreg end) as im_saldeu, "
   +"max(case when co_pagreg =16 then va_pagreg end) as ca_cuoini, "
   +"max(case when co_pagreg =17 then va_pagreg end) as im_cuoini, "
   +"max(case when co_pagreg =18 then va_pagreg end) as fe_cuoini, "
   +"max(case when co_pagreg =19 then va_pagreg end) as ca_cuotas, "
   +"max(case when co_pagreg =20 then va_pagreg end) as im_cuotas, "
   +"max(case when co_pagreg =21 then va_pagreg end) as fe_cuoini, "
   +"max(case when co_pagreg =21 then va_pagreg end) as fe_cuofin "
+"from fratra.tbpagreg "
//+"where id_frawor = 10695797",
+"where id_frawor = :p_id_frawor",
    resultClass = Modelo_AeDto.class)
})
public class Modelo_AeDto implements Serializable{
    
    @Id
    @Column(name="ti_docume")
    private String ti_docume;
    
    @Column(name="fe_docume")
    private String fe_docume;
        
    @Column(name="no_usureg")
    private String no_usureg;
    
    @Column(name="co_docreg")
    private String co_docreg;
    
    @Column(name="no_cliasu")
    private String no_cliasu;
    
    @Column(name="co_docasu")
    private String co_docasu;

    @Column(name="no_dirasu")
    private String no_dirasu;
    
    @Column(name="no_depasu")
    private String no_depasu;
    
    @Column(name="no_proasu")
    private String no_proasu;
    
    @Column(name="no_disasu")
    private String no_disasu;
        
    @Column(name="no_client")
    private String no_client;
    
    @Column(name="co_contra")
    private String co_contra;
    
    @Column(name="fe_activa")
    private String fe_activa;
    
    @Column(name="im_presta")
    private String im_presta;
    
    @Column(name="im_saldeu")
    private String im_saldeu;
    
    @Column(name="ca_cuoini")
    private String ca_cuoini;
        
    @Column(name="im_cuoini")
    private String im_cuoini;
    
    @Column(name="ca_cuotas")
    private String ca_cuotas;
    
    @Column(name="im_cuotas")
    private String im_cuotas;
    
    @Column(name="fe_cuoini")
    private String fe_cuoini;
    
    @Column(name="fe_cuofin")
    private String fe_cuofin;

    public String getTi_docume() {
        return ti_docume;
    }

    public void setTi_docume(String ti_docume) {
        this.ti_docume = ti_docume;
    }

    public String getFe_docume() {
        return fe_docume;
    }

    public void setFe_docume(String fe_docume) {
        this.fe_docume = fe_docume;
    }

    public String getNo_usureg() {
        return no_usureg;
    }

    public void setNo_usureg(String no_usureg) {
        this.no_usureg = no_usureg;
    }

    public String getCo_docreg() {
        return co_docreg;
    }

    public void setCo_docreg(String co_docreg) {
        this.co_docreg = co_docreg;
    }

    public String getNo_cliasu() {
        return no_cliasu;
    }

    public void setNo_cliasu(String no_cliasu) {
        this.no_cliasu = no_cliasu;
    }

    public String getCo_docasu() {
        return co_docasu;
    }

    public void setCo_docasu(String co_docasu) {
        this.co_docasu = co_docasu;
    }

    public String getNo_dirasu() {
        return no_dirasu;
    }

    public void setNo_dirasu(String no_dirasu) {
        this.no_dirasu = no_dirasu;
    }

    public String getNo_depasu() {
        return no_depasu;
    }

    public void setNo_depasu(String no_depasu) {
        this.no_depasu = no_depasu;
    }

    public String getNo_proasu() {
        return no_proasu;
    }

    public void setNo_proasu(String no_proasu) {
        this.no_proasu = no_proasu;
    }

    public String getNo_disasu() {
        return no_disasu;
    }

    public void setNo_disasu(String no_disasu) {
        this.no_disasu = no_disasu;
    }

    public String getNo_client() {
        return no_client;
    }

    public void setNo_client(String no_client) {
        this.no_client = no_client;
    }

    public String getCo_contra() {
        return co_contra;
    }

    public void setCo_contra(String co_contra) {
        this.co_contra = co_contra;
    }

    public String getFe_activa() {
        return fe_activa;
    }

    public void setFe_activa(String fe_activa) {
        this.fe_activa = fe_activa;
    }

    public String getIm_presta() {
        return im_presta;
    }

    public void setIm_presta(String im_presta) {
        this.im_presta = im_presta;
    }

    public String getIm_saldeu() {
        return im_saldeu;
    }

    public void setIm_saldeu(String im_saldeu) {
        this.im_saldeu = im_saldeu;
    }

    public String getCa_cuoini() {
        return ca_cuoini;
    }

    public void setCa_cuoini(String ca_cuoini) {
        this.ca_cuoini = ca_cuoini;
    }

    public String getIm_cuoini() {
        return im_cuoini;
    }

    public void setIm_cuoini(String im_cuoini) {
        this.im_cuoini = im_cuoini;
    }

    public String getCa_cuotas() {
        return ca_cuotas;
    }

    public void setCa_cuotas(String ca_cuotas) {
        this.ca_cuotas = ca_cuotas;
    }

    public String getIm_cuotas() {
        return im_cuotas;
    }

    public void setIm_cuotas(String im_cuotas) {
        this.im_cuotas = im_cuotas;
    }

    public String getFe_cuoini() {
        return fe_cuoini;
    }

    public void setFe_cuoini(String fe_cuoini) {
        this.fe_cuoini = fe_cuoini;
    }

    public String getFe_cuofin() {
        return fe_cuofin;
    }

    public void setFe_cuofin(String fe_cuofin) {
        this.fe_cuofin = fe_cuofin;
    }   
}
