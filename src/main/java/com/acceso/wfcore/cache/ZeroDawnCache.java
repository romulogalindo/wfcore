package com.acceso.wfcore.cache;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import java.time.Duration;
import java.util.HashMap;

public class ZeroDawnCache extends MainCache {

    CacheManagerBuilder cacheManagerBuilder;
    CacheManager cacheManager;
    String alias;
    HashMap<String, Cache> caches;

    public ZeroDawnCache() {
        cacheManagerBuilder = CacheManagerBuilder.newCacheManagerBuilder();
        caches = new HashMap<>();
        alias = "zeroDawnCache";
    }

    @Override
    public void init() {
        //iniciar el cache manager
        cacheManager = cacheManagerBuilder.withCache(alias,
                CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class,
                        ResourcePoolsBuilder.heap(100)).build())
                .build(true);
    }

    @Override
    public void destroy() {
        cacheManager.close();
    }

    @Override
    public Cache createSpace(String name, Class objectKeyType, Class objectValueType, int timeExpire) {
        //Se genera la configuracion
        CacheConfiguration cacheConfiguration = null;
        if (timeExpire == -1) {
            cacheConfiguration = CacheConfigurationBuilder.newCacheConfigurationBuilder(objectKeyType, objectValueType, ResourcePoolsBuilder.heap(100)).build();
        } else {
            cacheConfiguration = CacheConfigurationBuilder.newCacheConfigurationBuilder(objectKeyType, objectValueType, ResourcePoolsBuilder.heap(100))
                    .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(timeExpire)))
                    .build();
        }

        //se agrega la configuracion al manager
        Cache cache = cacheManager.createCache(name, cacheConfiguration);

        //se agrega al map para una busqueda ràpida
        caches.put(name, cache);

        return cache;
    }

    @Override
    public Cache getSpace(String name) {
        return caches.get(name);
    }
}
