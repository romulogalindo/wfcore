package com.acceso.wfcore.dtos;

import com.acceso.wfcore.utils.Values;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Rómulo Galindo
 * Created on 30 nov. 2018, 15:11:45
 */
@Entity
public class EmptyDTO implements Serializable {
    @Id
    String empty;

    public EmptyDTO() {
    }

    public String getEmpty() {
        return empty;
    }

    public void setEmpty(String empty) {
        this.empty = empty;
    }

    @Override
    public String toString() {
        return "EmptyDTO{" +
                "empty='" + empty + '\'' +
                '}';
    }
}
