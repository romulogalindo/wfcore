/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acceso.wfweb.beans.legacy;

import java.io.Serializable;

/**
 *
 * @author Rómulo Galindo Tanta
 */
public class TablaBean implements Serializable{
    public int row = 0;
    public int row_ant = 0; 
    public int col = 0;
    public int col_ant = 0;
    public int t = 0;
    public int p = 0;
    public boolean cmp = false;
    public boolean rst = false;

    public TablaBean() {
    }

    
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getRow_ant() {
        return row_ant;
    }

    public void setRow_ant(int row_ant) {
        this.row_ant = row_ant;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getCol_ant() {
        return col_ant;
    }

    public void setCol_ant(int col_ant) {
        this.col_ant = col_ant;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public int getT() {
        return t;
    }

    public void setT(int t) {
        this.t = t;
    }

    public boolean isCmp() {
        return cmp;
    }

    public void setCmp(boolean cmp) {
        //this.cmp = cmp;
        this.cmp = comparar();
    }
    
    public boolean comparar(){ 
        if(this.t == this.p) return true;
        else return false;
    }

    public boolean isRst() {
        return rst;
    }

    public void setRst(boolean rst) {
        this.rst = rst;
        reset();
    }
    
    public void reset(){
        row = 0;
        row_ant = 0;
        col = 0;
        col_ant = 0;;
        t = 0;
        p = 0;
    }
    
}