package com.acceso.wfcore.cache;

import org.ehcache.Cache;

public abstract class MainCache {
    public abstract void init();

    public abstract void destroy();

    public abstract Cache createSpace(String name, Class classKeyType, Class classValueType, int timeExpire);

    public abstract Cache getSpace(String name);
}
