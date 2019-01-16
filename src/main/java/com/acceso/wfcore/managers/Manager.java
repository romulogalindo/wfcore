package com.acceso.wfcore.managers;

import com.acceso.wfcore.utils.WFProperties;

/**
 * @author Rómulo Galindo<romulogalindo@gmail.com>
 * Created on 1 dic. 2018, 18:26:46
 */
public abstract class Manager {

    WFProperties properties;
    String hibcfgfile;
    String name;

    public Manager(String name, String hibcfgfile, WFProperties properties) {
        this.properties = properties;
        this.hibcfgfile = hibcfgfile;
        this.name = name;
    }

    public abstract void init();
}
