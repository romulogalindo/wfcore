package com.acceso.wfweb.beans;

import com.acceso.wfcore.listerners.WFCoreListener;
import com.acceso.wfcore.utils.Values;
import com.acceso.wfweb.web.Root;

import java.io.Serializable;

public class MainBean implements Serializable {
    public MainBean() {
    }

    public Root getRoot() {
        System.out.println("tenemos:" + WFCoreListener.APP.getCacheService().getZeroDawnCache().getSpace(Values.CACHE_MAIN_MENUTREE).get("ROOT_TREE"));
        return (Root) WFCoreListener.APP.getCacheService().getZeroDawnCache().getSpace(Values.CACHE_MAIN_MENUTREE).get("ROOT_TREE");
    }

}
