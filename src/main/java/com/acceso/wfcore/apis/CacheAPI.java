package com.acceso.wfcore.apis;

import com.acceso.wfcore.kernel.WFIOAPP;
import org.ehcache.Cache;

public class CacheAPI extends GenericAPI {

    public Cache CREATE(String namespace, int timeout) {
        return WFIOAPP.APP.cacheService.getZeroDawnCache().createSpace(namespace, String.class, Object.class, timeout);
    }

    public Cache CREATEIF(String namespace, int timeout) {
        Cache cache = WFIOAPP.APP.cacheService.getZeroDawnCache().getSpace(namespace);
        
        if (cache == null) {
            cache = WFIOAPP.APP.cacheService.getZeroDawnCache().createSpace(namespace, String.class, Object.class, timeout);
        }

        return cache;
    }

    public void PUT(String namespace, String key, Object value) {
        WFIOAPP.APP.cacheService.getZeroDawnCache().getSpace(namespace).put(key, value);
    }

    public Object GET(String namespace, String key) {
        return WFIOAPP.APP.cacheService.getZeroDawnCache().getSpace(namespace).get(key);
    }

}
