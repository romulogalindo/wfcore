package com.acceso.wfcore.cache;

import org.ehcache.Cache;

public abstract class MainCache {
    public abstract void init();

    public abstract void destroy();

    public abstract void createSpace(String name, Class classKeyType, Class classValueType);

    public abstract Cache getSpace(String name);
}
