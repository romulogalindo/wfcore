package com.acceso.wfweb.dtos;

import com.acceso.wfweb.utils.Values;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import java.io.Serializable;
import java.util.Date;

@Entity
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "wfarchiv.pbarchiv_ins",
                query = "select * from wfarchiv.pbarchiv(:p_no_archiv)",
                resultClass = ArchivDTO.class
        ),
        @NamedNativeQuery(
                name = "wfarchiv.pbarchiv_read",
                query = "select * from wfarchiv.pbarchiv(:p_co_archiv)",
                resultClass = ArchivDTO.class
        )
})
public class ArchivDTO implements Serializable {

    @Id
    private long co_archiv;

    private String no_archiv;

    private Date fe_archiv;

    public ArchivDTO() {
    }

    public long getCo_archiv() {
        return co_archiv;
    }

    public void setCo_archiv(long co_archiv) {
        this.co_archiv = co_archiv;
    }

    public String getNo_archiv() {
        return no_archiv;
    }

    public void setNo_archiv(String no_archiv) {
        this.no_archiv = no_archiv;
    }

    public Date getFe_archiv() {
        return fe_archiv;
    }

    public void setFe_archiv(Date fe_archiv) {
        this.fe_archiv = fe_archiv;
    }

}