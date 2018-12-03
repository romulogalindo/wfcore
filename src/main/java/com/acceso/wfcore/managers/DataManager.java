package com.acceso.wfcore.managers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 *
 * @author Rómulo Galindo<romulogalindo@gmail.com>
 * Created on 2 dic. 2018, 11:07:26
 */
public class DataManager extends Manager implements DataBasePowerfull {

    SessionFactory sessionFactory;

    @Override
    public void init() {
        //conectarme a la DB y tener todos los objetos listos
        try {
            StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
            //separa para el log
            Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().build();
            //objeto creado
            sessionFactory = metadata.getSessionFactoryBuilder().build();
            //en este punto se supono todo esta bien
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    @Override
    public StatelessSession getNativeSession() {
        return sessionFactory.openStatelessSession();
    }

    @Override
    public Session getHibernateSession() {
        return sessionFactory.openSession();
    }

}
