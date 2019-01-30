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
    @NamedNativeQuery(package com.acceso.wfcore.dtos.legacy;
            name = "pbcarta_retiro_abaco",
            query = "select * from pbcarta_retiro_abaco(:p_co_expedi)",
            resultClass = CartaRetiroDto.class)
})
public class CartaRetiroDto implements Serializable {

    @Id
    @Column(name = "no_diafec")
    private String no_diafec;
    @Column(name = "no_mesfec")
    private String no_mesfec;
    @Column(name = "no_anofec")
    private String no_anofec;
    @Column(name = "no_nombre")
    private String no_nombre;
    @Column(name = "co_docide")
    private String co_docide;

    public String getNo_diafec() {
        return no_diafec;
    }

    public void setNo_diafec(String no_diafec) {
        this.no_diafec = no_diafec;
    }

    public String getNo_mesfec() {
        return no_mesfec;
    }

    public void setNo_mesfec(String no_mesfec) {
        this.no_mesfec = no_mesfec;
    }

    public String getNo_anofec() {
        return no_anofec;
    }

    public void setNo_anofec(String no_anofec) {
        this.no_anofec = no_anofec;
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
}
