package com.acceso.wfcore.listerners;

import com.acceso.wfcore.kernel.Application;
import com.acceso.wfcore.log.Log;
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
        APP = new Application();
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
//        System.out.println("Iniciando WF AIO2");
        Log.info("==========[[[Iniciando WF AIO2]]]===========");
        dataSourceService.start();

        APP.run(sce);
        sce.getServletContext().setAttribute("APP", APP);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        dataSourceService.stop();
    }
}
