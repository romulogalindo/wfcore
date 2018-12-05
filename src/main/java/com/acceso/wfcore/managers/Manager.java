package com.acceso.wfcore.managers;

import com.acceso.wfcore.utils.WFProperties;

/**
 *
 * @author Rómulo Galindo<romulogalindo@gmail.com>
 * Created on 1 dic. 2018, 18:26:46
 */
public abstract class Manager {

    WFProperties properties;

    public Manager(WFProperties properties) {
        this.properties = properties;
    }

    public abstract void init();
}
