package com.acceso.wfcore.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rómulo Galindo Tanta
 */
public class RowJson implements Serializable {

    List<StandarRegisterJson> regs;
    List<CfgJson> cfgobjs;

    public RowJson() {
        regs = new ArrayList<>();
    }

    public RowJson(List<StandarRegisterJson> regs) {
        this.regs = regs;
    }

    public List<StandarRegisterJson> getRegs() {
        return regs;
    }

    public void setRegs(List<StandarRegisterJson> regs) {
        this.regs = regs;
    }

    public void addReg(StandarRegisterJson regJson) {
        regs.add(regJson);
    }

    public void add(StandarRegisterJson reg) {
        regs.add(reg);
    }

    /*SOLO TIPO TABLA(por ahora)*/
    public void cfg(List<CfgJson> cfgobjs) {
//        System.out.println("cfgobjs = " + cfgobjs);
        this.cfgobjs = cfgobjs;
    }

    public static RowJson NEW() {
        return new RowJson();
    }

}
