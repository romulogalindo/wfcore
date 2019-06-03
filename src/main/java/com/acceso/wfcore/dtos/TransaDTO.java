package com.acceso.wfcore.dtos;

import com.acceso.wfcore.utils.Values;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

public class TransaDTO implements Serializable {

    @Id
    long id;
    int type;
    long usuari;
    String query;
    Date start;
    Date finish;
    long caltime;

    public TransaDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getUsuari() {
        return usuari;
    }

    public void setUsuari(long usuari) {
        this.usuari = usuari;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getFinish() {
        return finish;
    }

    public void setFinish(Date finish) {
        this.finish = finish;
    }

    public long getCaltime() {
        return caltime;
    }

    public void setCaltime(long caltime) {
        this.caltime = caltime;
    }

}
