package com.acceso.wfcore.services;

import com.acceso.wfcore.kernel.ApplicationManager;
import com.acceso.wfcore.managers.DataManager;
import com.acceso.wfcore.utils.Querys;
import com.acceso.wfcore.utils.Values;
import com.acceso.wfcore.utils.WFProperties;

import java.util.HashMap;

/**
 * @author Rómulo Galindo<romulogalindo@gmail.com>
 * Created on 1 dic. 2018, 17:09:39
 */
public class DataSourceService extends Service {

    DataManager mainManager;
    HashMap<String, DataManager> managers;

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
        System.out.println("Iniciando Servicio:" + this.getClass().getCanonicalName().toUpperCase());

        //levantar la database princiapl de donde se cogeran los datos!!!
        mainManager.init();
        System.out.println("Se inicializo la DB-Nativa");

        //se generan los manager y se agregan al hashmap
        Querys.getManagers(mainManager.getNativeSession()).stream().forEach(dto -> managers.put(dto.getNo_conexi(), new DataManager(dto.getNo_conexi(), "hibdata.cfg.xml", ApplicationManager.buildDefaultProperties(dto))));
//        Querys.getManagers(mainManager.getNativeSession()).stream().forEach(dto -> System.out.println("dto::" + dto));

        //luego levantar la lista de manager que manejaran las otras DB's
        managers.forEach((k, v) -> v.init());
    }

    public DataManager getManager(String managerName) {
        return managers.get(managerName);
    }

    @Override
    public void stop() {

    }

    public DataManager getMainManager() {
        return mainManager;
    }

    public void setMainManager(DataManager mainManager) {
        this.mainManager = mainManager;
    }

}
