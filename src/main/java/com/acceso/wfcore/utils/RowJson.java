package com.acceso.wfcore.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rómulo Galindo Tanta
 */
public class RowJson {

    //    List<RegJson> regs;
    List<StandarRegisterJson> regs;
    List<CfgJson> cfgobjs;

    public RowJson() {
        regs = new ArrayList<>();
    }

//    public RowJson(List<RegJson> regs) {
//        this.regs = regs;
//    }
//
//    public List<RegJson> getRegs() {
//        return regs;
//    }

    public RowJson(List<StandarRegisterJson> regs) {
        this.regs = regs;
    }

    public List<StandarRegisterJson> getRegs() {
        return regs;
    }

//    public void setRegs(List<RegJson> regs) {
//        this.regs = regs;
//    }

    public void setRegs(List<StandarRegisterJson> regs) {
        this.regs = regs;
    }

    public void addReg(RegJson regJson) {
        regs.add(regJson);
    }

    /*ADD new RegJson*/
    public void add(RegJson regJson) {
        regs.add(regJson);
    }

    /*ADD new TabJson*/
    public void add(TabJson regJson) {
        regs.add(regJson);
    }

    /*SOLO TIPO TABLA(por ahora)*/
    public void cfg(List<CfgJson> cfgobjs) {
        System.out.println("cfgobjs = " + cfgobjs);
        this.cfgobjs = cfgobjs;
    }

    public static RowJson NEW() {
        return new RowJson();
    }

}
