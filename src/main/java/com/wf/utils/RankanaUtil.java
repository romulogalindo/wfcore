package com.wf.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 *
 * @author Mario Huillca <mario.huillca@acceso.com.pe>
 * Created on 30 nov. 2018, 15:24:03
 */
public class RankanaUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    // Hibernate 5:
    private static SessionFactory buildSessionFactory() {
        try {
            System.err.println("RankanaUtil ---> Inicio");
            // Create the ServiceRegistry from hibernate.cfg.xml
//            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()//
//                    .configure("hibernate.cfg.xml").build();
            StandardServiceRegistry standardRegistry
                    = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
            // Create a metadata sources using the specified service registry.
//            Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
            Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().build();
            System.err.println("RankanaUtil ---> Fin");
            return metadata.getSessionFactoryBuilder().build();

        } catch (Throwable ex) {

            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }

}
