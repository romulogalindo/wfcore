package com.acceso.wfcore.services;

/**
 *
 * @author Rómulo Galindo<romulogalindo@gmail.com>
 * Created on 1 dic. 2018, 16:27:33
 */
public abstract class Service {

    String serviceName;

    public Service(String serviceName) {
        this.serviceName = serviceName;
    }

    public abstract void start();

    public abstract void stop();

    public String getNameService() {
        return serviceName;
    }
}
