package com.acceso.wfcore.utils;

import java.io.Serializable;
import java.util.Properties;

/**
 *
 * @author Rómulo Galindo<romulogalindo@gmail.com>
 * Created on 1 dic. 2018, 18:51:18
 */
public class WFProperties {

    java.util.Properties properties;

    public WFProperties(Properties properties) {
        this.properties = properties;
    }

    public void update(Properties properties) {
        this.properties = properties;
    }

    public String get(String key) {
        return this.properties.getProperty(key);
    }
}
