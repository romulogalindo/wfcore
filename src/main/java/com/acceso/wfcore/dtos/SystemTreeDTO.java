package com.acceso.wfcore.dtos;

import com.acceso.wfcore.utils.Values;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import java.io.Serializable;

@Entity
@NamedNativeQueries({
        @NamedNativeQuery(
                name = Values.QUERY_MAINTREE,
                query = "select * from wfsistem.ppmenugeneral_web() as tree",
                resultClass = SystemTreeDTO.class)
})
public class SystemTreeDTO implements Serializable {
    @Id
    String tree;

    public SystemTreeDTO() {
    }

    public String getTree() {
        return tree;
    }

    public void setTree(String tree) {
        this.tree = tree;
    }
}
