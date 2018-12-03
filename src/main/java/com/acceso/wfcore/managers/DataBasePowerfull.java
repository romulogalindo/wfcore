package com.acceso.wfcore.managers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;

/**
 *
 * @author Rómulo Galindo<romulogalindo@gmail.com>
 * Created on 3 dic. 2018, 15:44:58
 */
public interface DataBasePowerfull {

    /**
     * Este método devolverá un objeto que permite las consultas directas sin
     * cache
     *
     * @return
     */
    public StatelessSession getNativeSession();

    /**
     * Este método devolverá un objeto que permite las consultas con cache
     *
     * @return
     */
    public Session getHibernateSession();
}
