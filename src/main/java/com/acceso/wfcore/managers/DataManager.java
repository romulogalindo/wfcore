package com.acceso.wfcore.managers;

import com.acceso.wfcore.daos.SystemDAO;
import com.acceso.wfcore.dtos.EstadoDTO;
import com.acceso.wfcore.kernel.WFIOAPP;
import com.acceso.wfcore.log.Log;
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
    StatelessSession testSession;
    int status = DataBasePowerfull.INACTIVE;

    public DataManager(String name, String hibcfgfile, WFProperties properties) {
        super(name, hibcfgfile, properties);
    }

    @Override
    public void init() {
        try {
            Log.info("CONFIG [**" + this.getName() + "**]@" + this.hibcfgfile);
            StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder()
                    .configure(this.hibcfgfile);

            //recorremos todos los key y obtenemos el value!
            properties.getKeys().stream().forEach(k -> standardServiceRegistryBuilder.applySetting(k, properties.get(k)));

            ServiceRegistry standardRegistry = standardServiceRegistryBuilder.build();
            Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().build();
            sessionFactory = metadata.getSessionFactoryBuilder().build();
//            Log.info(sessionFactory.isOpen());
            //CHECK!
            testSession = sessionFactory.openStatelessSession();
            SystemDAO dao = new SystemDAO(testSession);
            EstadoDTO estadoDTO = dao.check();
            dao.close();

            if (estadoDTO.getNo_estado().contentEquals("OK")) {
                status = DataBasePowerfull.ACTIVE;
            }
            Log.info("@sessionFactory = [" + name + "]" + sessionFactory + ", estado:" + estadoDTO.getNo_estado());

//        } catch (Throwable ex) {
        } catch (Exception ex) {
            try {
                if (testSession != null) {
                    testSession.close();
                }
            } catch (Exception ep2) {
            }
            sessionFactory = null;
            Log.error("Initial SessionFactory creation failed." + ex);
            if (WFIOAPP.APP.THROWS_EXCEPTION) {
                ex.printStackTrace();
            }
        }

//        if (sessionFactory != null) {
//            status = DataBasePowerfull.ACTIVE;
//        }
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
