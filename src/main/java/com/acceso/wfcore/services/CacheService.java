package com.acceso.wfcore.services;

import com.acceso.wfcore.cache.ZeroDawnCache;

public class CacheService extends Service {
    ZeroDawnCache zeroDawnCache;

    public CacheService(String serviceName) {
        super(serviceName);
        //valores default cache
        zeroDawnCache = new ZeroDawnCache();
    }

    @Override
    public void start() {
        zeroDawnCache.init();
    }

    @Override
    public void stop() {
        zeroDawnCache.destroy();
    }

    public ZeroDawnCache getZeroDawnCache() {
        return zeroDawnCache;
    }

    public void setZeroDawnCache(ZeroDawnCache zeroDawnCache) {
        this.zeroDawnCache = zeroDawnCache;
    }
}
