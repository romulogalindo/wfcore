package com.acceso.wfcore.services;

import com.acceso.wfcore.managers.DataManager;
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
        mainManager = new DataManager();
        managers = new HashMap<>();
    }

    @Override
    public void start() {
        System.out.println("Iniciando Servicio:" + this.getClass().getCanonicalName().toUpperCase());

        //levantar la database princiapl de donde se cogeran los datos!!!
        mainManager.init();

        //con la mainManager conseguir la lista de items para levantarlos
        
        //luego levantar la lista de manager que manejaran las otras DB's
        managers.forEach((k, v) -> v.init());
    }

    @Override
    public void stop() {

    }

}
