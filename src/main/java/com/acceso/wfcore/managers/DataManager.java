package com.acceso.wfcore.managers;

import com.acceso.wfcore.daos.SystemDAO;
import com.acceso.wfcore.dtos.EstadoDTO;
import com.acceso.wfcore.utils.WFProperties;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

/**
 * @author Rómulo Galindo<romulogalindo@gmail.com>
 * Created on 2 dic. 2018, 11:07:26
 */
public class DataManager extends Manager implements DataBasePowerfull {

    SessionFactory sessionFactory;
    int status = DataBasePowerfull.INACTIVE;

    public DataManager(String name, String hibcfgfile, WFProperties properties) {
        super(name, hibcfgfile, properties);
    }

    @Override
    public void init() {
        //conectarme a la DB y tener todos los objetos listos
        try {
            //get xml!
            System.out.println("configurando @hib = " + this.hibcfgfile);
            StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder()
                    .configure(this.hibcfgfile);

            //recorremos todos los key y obtenemos el value!
            properties.getKeys().stream().forEach(k -> standardServiceRegistryBuilder.applySetting(k, properties.get(k)));

            //Se genera el registro
//            StandardServiceRegistry standardRegistry = standardServiceRegistryBuilder.build();
            ServiceRegistry standardRegistry = standardServiceRegistryBuilder.build();
            //se registra el meta
            Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().build();

            //objeto creado
            sessionFactory = metadata.getSessionFactoryBuilder().build();

            //CHECK!
            SystemDAO dao = new SystemDAO(sessionFactory.openStatelessSession());
            EstadoDTO estadoDTO = dao.check();
            dao.close();

            System.out.println("@sessionFactory = [" + name + "]" + sessionFactory + ", estado:" + estadoDTO.getNo_estado());

            //en este punto se supono todo esta bien
//            Object result = sessionFactory.openStatelessSession().createSQLQuery("select 1").getSingleResult();
//            Sistema.out.println("update = " + update);
        } catch (Throwable ex) {
            sessionFactory = null;
            System.err.println("Initial SessionFactory creation failed." + ex);
            ex.printStackTrace();
            //throw new ExceptionInInitializerError(ex);
        }

        if (sessionFactory != null) {
            status = DataBasePowerfull.ACTIVE;
        }
    }

    @Override
    public void terminate() {
        if (sessionFactory != null) {
            try {
                sessionFactory.close();
            } catch (Exception ep) {
                sessionFactory = null;
            }
        }
    }

    @Override
    public StatelessSession getNativeSession() {
        return status == DataBasePowerfull.ACTIVE ? sessionFactory.openStatelessSession() : null;
    }

    @Override
    public Session getHibernateSession() {
        return sessionFactory.openSession();
    }

    @Override
    public SessionFactory getFactory() {
        return sessionFactory;
    }


    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public String getName() {
        return name;
    }
}
