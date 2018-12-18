package com.acceso.wfcore.listerners;

import com.acceso.wfcore.kernel.Application;
import com.acceso.wfcore.services.DataSourceService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author Augusto Galindo
 */
public class WFCoreListener implements ServletContextListener {

    public static final DataSourceService dataSourceService = new DataSourceService("DataSourceService");
    public static final Application APP;

    static {
        //La applicacion se carga al inicio de todo!
        APP = new Application();
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Iniciando WF AIO2");
        dataSourceService.start();

        //prototipo de run>>>>>>>>>>>
        //*carga de valores estaticos...
//        Application application = new Application();
        //en teoria debe estar todo el datasoruce aqui!
        sce.getServletContext().setAttribute("APP", APP);

        //levantamiento de estructura inicial

        //levantando cache
        //ingresando valores a cache
        //levanto servlet iniciales
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        dataSourceService.stop();
    }
}
