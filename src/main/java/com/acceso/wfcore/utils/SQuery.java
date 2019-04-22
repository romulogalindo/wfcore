package com.acceso.wfcore.utils;

import java.lang.annotation.Annotation;
import org.hibernate.annotations.CacheModeType;
import org.hibernate.annotations.FlushModeType;
import org.hibernate.annotations.NamedNativeQuery;

/**
 *
 * @author Rómulo Galindo
 */
public class SQuery implements NamedNativeQuery {

    String name;

    String query;

    Class resultClass;

    String resultSetMapping = "";

    FlushModeType flushMode = FlushModeType.PERSISTENCE_CONTEXT;

    boolean cacheable = false;

    String cacheRegion = "";

    int fetchSize = -1;

    int timeout = -1;

    boolean callable = false;

    String comment = "";

    CacheModeType cacheMode = CacheModeType.NORMAL;

    boolean readOnly = false;

    public SQuery(String name, String query, Class resultClass) {
        this.name = name;
        this.query = query;
        this.resultClass = resultClass;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public String query() {
        return this.query;
    }

    @Override
    public Class resultClass() {
        return this.resultClass;
    }

    @Override
    public String resultSetMapping() {
        return this.resultSetMapping;
    }

    @Override
    public FlushModeType flushMode() {
        return this.flushMode;
    }

    @Override
    public boolean cacheable() {
        return this.cacheable;
    }

    @Override
    public String cacheRegion() {
        return this.cacheRegion;
    }

    @Override
    public int fetchSize() {
        return this.fetchSize;
    }

    @Override
    public int timeout() {
        return this.timeout;
    }

    @Override
    public boolean callable() {
        return this.callable;
    }

    @Override
    public String comment() {
        return this.comment;
    }

    @Override
    public CacheModeType cacheMode() {
        return this.cacheMode;
    }

    @Override
    public boolean readOnly() {
        return this.readOnly;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return this.getClass();
    }
}
