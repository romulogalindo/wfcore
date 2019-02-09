package com.acceso.wfweb.dtos;

import com.acceso.wfweb.utils.Values;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@NamedNativeQueries({
        @NamedNativeQuery(
                name = Values.QUERYS_WEB_ARCADJ,
                query = "select * from arcadj.pbarcadj(:p_id_arcadj)",
                resultClass = ArcadjDTO.class
        )
})
public class ArcadjDTO implements Serializable {

    @Id
    private long id_arcadj;

    private String no_arcadj;

    private Date fe_arcadj;

    public ArcadjDTO() {
    }

    public long getId_arcadj() {
        return id_arcadj;
    }

    public void setId_arcadj(long id_arcadj) {
        this.id_arcadj = id_arcadj;
    }

    /**
     * @return the fe_arcadj
     */
    public Date getFe_arcadj() {
        return fe_arcadj;
    }

    /**
     * @param fe_arcadj the fe_arcadj to set
     */
    public void setFe_arcadj(Date fe_arcadj) {
        this.fe_arcadj = fe_arcadj;
    }

    /**
     * @return the no_arcadj
     */
    public String getNo_arcadj() {
        return no_arcadj;
    }

    /**
     * @param no_arcadj the no_arcadj to set
     */
    public void setNo_arcadj(String no_arcadj) {
        this.no_arcadj = no_arcadj;
    }


}
