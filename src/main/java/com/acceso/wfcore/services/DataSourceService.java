package com.acceso.wfcore.services;

import com.acceso.wfcore.managers.DataManager;
import com.acceso.wfcore.utils.Querys;
import com.acceso.wfcore.utils.Values;
import com.acceso.wfcore.utils.WFProperties;
import java.util.HashMap;

/**
 *
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

        mainManager = new DataManager(nativeConexionProperties);
        managers = new HashMap<>();
    }

    @Override
    public void start() {
        System.out.println("Iniciando Servicio:" + this.getClass().getCanonicalName().toUpperCase());

        //levantar la database princiapl de donde se cogeran los datos!!!
        mainManager.init();

        //se generan los manager y se agregan al hashmap
        Querys.getManagers(mainManager.getNativeSession()).stream().forEach(dto -> managers.put(dto.getNo_conexi(), new DataManager(new WFProperties(dto))));

        //luego levantar la lista de manager que manejaran las otras DB's
        managers.forEach((k, v) -> v.init());
    }

    public DataManager getManager(String managerName) {
        return managers.get(managerName);
    }

    @Override
    public void stop() {

    }

}
