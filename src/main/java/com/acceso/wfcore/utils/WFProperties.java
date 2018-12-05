package com.acceso.wfcore.utils;

import com.acceso.wfcore.dtos.ConexionDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author Rómulo Galindo<romulogalindo@gmail.com>
 * Created on 1 dic. 2018, 18:51:18
 */
public class WFProperties {

    java.util.Properties properties;

    public WFProperties(ConexionDTO conexionDTO) {
        //un dto se convierte en propiedades
    }

    public WFProperties(Properties properties) {
        this.properties = properties;
    }

    public WFProperties() {
        this(new Properties());
    }

    public void update(Properties properties) {
        this.properties = properties;
    }

    public void add(String key, String value) {
        this.properties.setProperty(key, value);
    }

    public String get(String key) {
        return this.properties.getProperty(key);
    }

    public List<String> getKeys() {
        List<String> keys = new ArrayList<>();
        Enumeration<Object> keyEnumeration = this.properties.keys();
        while (keyEnumeration.hasMoreElements()) {
            keys.add((String) keyEnumeration.nextElement());
        }

        return keys;
    }
}
