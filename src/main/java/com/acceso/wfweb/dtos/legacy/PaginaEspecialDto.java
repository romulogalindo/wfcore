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
 * @author edavalos
 */

@Entity
@NamedNativeQueries({
        @NamedNativeQuery(
                name = Values.QUERYS_WEB_SELECT_PBGETDOCUME,
                query = "select * from docume.tbdocume where co_docume = :p_co_docume",
                resultClass = PaginaEspecialDto.class
        )
})
public class PaginaEspecialDto implements Serializable {

    @Id
    @Column(name = "co_docume")
    private int co_docume;

    @Column(name = "no_docume")
    private String no_docume;

    @Column(name = "no_descri")
    private String no_descri;

    /**
     * @return the co_docume
     */
    public int getCo_docume() {
        return co_docume;
    }

    /**
     * @param co_docume the co_docume to set
     */
    public void setCo_docume(int co_docume) {
        this.co_docume = co_docume;
    }

    /**
     * @return the no_docume
     */
    public String getNo_docume() {
        return no_docume;
    }

    /**
     * @param no_docume the no_docume to set
     */
    public void setNo_docume(String no_docume) {
        this.no_docume = no_docume;
    }

    /**
     * @return the no_descri
     */
    public String getNo_descri() {
        return no_descri;
    }

    /**
     * @param no_descri the no_descri to set
     */
    public void setNo_descri(String no_descri) {
        this.no_descri = no_descri;
    }
}
