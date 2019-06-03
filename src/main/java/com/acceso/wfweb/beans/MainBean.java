package com.acceso.wfweb.beans;

import com.acceso.wfcore.kernel.WFIOAPP;
import com.acceso.wfcore.utils.Values;
import com.acceso.wfweb.web.MainMenu;
import com.acceso.wfweb.web.Root;

import java.io.Serializable;

public class MainBean implements Serializable {

    public MainBean() {
    }

    public Root getRoot() {
        System.out.println("root_tree:" + WFIOAPP.APP.getCacheService().getZeroDawnCache().getSpace(Values.CACHE_MAIN_MENUTREE).get("ROOT_TREE"));
        Root root = (Root) WFIOAPP.APP.getCacheService().getZeroDawnCache().getSpace(Values.CACHE_MAIN_MENUTREE).get("ROOT_TREE");

        //capa de seguridad
        return root;
    }

    public MainMenu getMenu() {
        System.out.println("Menu:" + ((Root) WFIOAPP.APP.getCacheService().getZeroDawnCache().getSpace(Values.CACHE_MAIN_MENUTREE).get("ROOT_TREE")).getSistemas().get(0).getSubsistemas().get(0).getPaquetes().get(0).getMmenu());
        MainMenu mainMenu = ((Root) WFIOAPP.APP.getCacheService().getZeroDawnCache().getSpace(Values.CACHE_MAIN_MENUTREE).get("ROOT_TREE")).getSistemas().get(0).getSubsistemas().get(0).getPaquetes().get(0).getMmenu();

        //capa de seguridad
        return mainMenu;
    }

}
