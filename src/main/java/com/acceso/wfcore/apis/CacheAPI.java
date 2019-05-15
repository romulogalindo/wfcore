package com.acceso.wfcore.apis;

import com.acceso.wfcore.listerners.WFCoreListener;

public class CacheAPI extends GenericAPI {

    public void CREATE(String namespace, int timeout) {
        WFCoreListener.APP.cacheService.getZeroDawnCache().createSpace(namespace, String.class, Object.class, timeout);
    }

    public void CREATEIF(String namespace, int timeout) {
        if (WFCoreListener.APP.cacheService.getZeroDawnCache().getSpace(namespace) != null) {
            WFCoreListener.APP.cacheService.getZeroDawnCache().createSpace(namespace, String.class, Object.class, timeout);
        }
    }

    public void PUT(String namespace, String key, Object value) {
        WFCoreListener.APP.cacheService.getZeroDawnCache().getSpace(namespace).put(key, value);
    }

    public Object GET(String namespace, String key) {
        return WFCoreListener.APP.cacheService.getZeroDawnCache().getSpace(namespace).get(key);
    }

}
