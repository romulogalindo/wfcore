package com.acceso.wfcore.services;

import com.acceso.wfcore.kernel.ApplicationManager;
import com.acceso.wfcore.log.Log;
import com.acceso.wfcore.managers.DataManager;
import com.acceso.wfcore.transa.Transactional;
import com.acceso.wfcore.utils.Querys;
import com.acceso.wfcore.utils.Values;
import com.acceso.wfcore.utils.WFProperties;

import java.util.HashMap;
import java.util.Map;

import org.h2.tools.Server;

/**
 * @author Rómulo Galindo<romulogalindo@gmail.com>
 * Created on 1 dic. 2018, 17:09:39
 */
public class DataSourceService extends Service {

    DataManager mainManager;
    HashMap<String, DataManager> managers;
    Server AIO_DB_SERVER;
    Map<String, String> PROPS;

    public DataSourceService(String serviceName) {
        super(serviceName);

        //hacer algo que solo se deba setear al crear.. sino dejarlo al emtodo start
        //Creamos el properties para esta primera conexion
        WFProperties nativeConexionProperties = new WFProperties();
        nativeConexionProperties.add(Values.PROP_NATIVE_CONEXION_NAME_KEY, Values.PROP_NATIVE_CONEXION_NAME_VALUE);
        nativeConexionProperties.add(Values.PROP_NATIVE_DRIVER_NAME_KEY, Values.PROP_NATIVE_DRIVER_NAME_VALUE);

        // Maximum waiting time for a connection from the pool
        nativeConexionProperties.add("hibernate.hikari.connectionTimeout", "20000");
        // Minimum number of ideal connections in the pool
        nativeConexionProperties.add("hibernate.hikari.minimumIdle", "20");
        // Maximum number of actual connection in the pool
        nativeConexionProperties.add("hibernate.hikari.maximumPoolSize", "50");
        // Maximum time that a connection is allowed to sit ideal in the pool
        nativeConexionProperties.add("hibernate.hikari.idleTimeout", "300000");

        mainManager = new DataManager("SYSTEM", "hibernate.cfg.xml", nativeConexionProperties);
        managers = new HashMap<>();
    }

    @Override
    public void start() {
        Log.info("Iniciando Servicio:" + this.getClass().getCanonicalName().toUpperCase());

        //levantar la database princiapl de donde se cogeran los datos!!!
        mainManager.init();
//        System.out.println("Se inicializo la DB-Nativa");

        //se generan los manager y se agregan al hashmap
        Querys.getManagers(mainManager.getNativeSession()).stream().forEach(dto -> managers.put(dto.getNo_conexi(), new DataManager(dto.getNo_conexi(), "hibdata.cfg.xml", ApplicationManager.buildDefaultProperties(dto))));

        //Cargar los properties del SYS
        PROPS = Querys.getProperties(mainManager.getNativeSession());

        //luego levantar la lista de manager que manejaran las otras DB's
        managers.forEach((k, v) -> v.init());

        //Crear DB in memory para grabar data
        try {
            AIO_DB_SERVER = Server.createTcpServer("-tcp", "-tcpPort", "9092", "-tcpAllowOthers").start();
            System.out.println("[h2]getStatus = " + AIO_DB_SERVER.getStatus());
            System.out.println("[h2]getURL = " + AIO_DB_SERVER.getURL());
            System.out.println("[h2]getService.getType = " + AIO_DB_SERVER.getService().getType());
            System.out.println("[h2]getService.isDaemon = " + AIO_DB_SERVER.getService().isDaemon());

            Class.forName("org.h2.Driver");
            java.sql.Connection conn = java.sql.DriverManager.
                    getConnection("jdbc:h2:mem:testdb", "sa", "");
            if (!conn.isClosed()) {
                //crear cllass/hilo de transacciones
//                Transactional.create();
                //close
                conn.close();
            }

//            System.out.println("Connection Established: "
//                    + conn.getMetaData().getDatabaseProductName() + "/" + conn.getCatalog());
        } catch (Exception ep) {
            ep.printStackTrace();
        }
    }

    public DataManager getManager(String managerName) {
        return managers.get(managerName);
    }

    public DataManager setManager(String managerName, DataManager dataManager) {
        return managers.put(managerName, dataManager);
    }

    @Override
    public void stop() {
        try {
            AIO_DB_SERVER.stop();
            AIO_DB_SERVER.shutdown();
        } catch (Exception ep) {
            ep.printStackTrace();
        }
    }

    public DataManager getMainManager() {
        return mainManager;
    }

    public void setMainManager(DataManager mainManager) {
        this.mainManager = mainManager;
    }

    public String getValueOfKey(String key) {
        key = "" + key.toUpperCase();
        switch (key) {
            case "AIO_DATA_FILE": {
                key = PROPS.get(key) == null ? "/" : PROPS.get(key);
                break;
            }
            default: {
                key = PROPS.get(key);
            }
        }

        return key;
    }
}
