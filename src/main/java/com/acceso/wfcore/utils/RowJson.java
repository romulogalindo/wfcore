package com.acceso.wfcore.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rgalindo
 */
public class RowJson {

    List<RegJson> regs;

    public RowJson() {
        regs = new ArrayList<>();
    }

    public List<RegJson> getRegs() {
        return regs;
    }

    public void setRegs(List<RegJson> regs) {
        this.regs = regs;
    }

}
