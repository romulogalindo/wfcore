package com.acceso.wfcore.managers;

import com.acceso.wfcore.utils.Values;
import com.acceso.wfcore.utils.WFProperties;
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
    int status = DataBasePowerfull.INACTIVE;

    public DataManager(WFProperties properties) {
        super(properties);
    }

    @Override
    public void init() {
        //conectarme a la DB y tener todos los objetos listos
        try {
            //get xml!
            StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml");

            //recorremos todos los key y obtenemos el value!
            properties.getKeys().stream().forEach(k -> standardServiceRegistryBuilder.applySetting(k, properties.get(k)));

            //Se genera el registro
            StandardServiceRegistry standardRegistry = standardServiceRegistryBuilder
                    .build();

            //se registra el meta
            Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().build();

            //objeto creado
            sessionFactory = metadata.getSessionFactoryBuilder().build();

            //en este punto se supono todo esta bien
        } catch (Throwable ex) {
            sessionFactory = null;
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        if (sessionFactory != null) {
            status = DataBasePowerfull.ACTIVE;
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

    @Override
    public int getStatus() {
        return status;
    }

}
