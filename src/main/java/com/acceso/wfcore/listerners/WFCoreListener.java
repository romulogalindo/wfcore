package com.acceso.wfcore.listerners;

import com.acceso.wfcore.kernel.WFIOAPP;
import com.acceso.wfcore.log.Log;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author Augusto Galindo
 */
public class WFCoreListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Log.info("==========[[[Iniciando WF AIO2]]]===========");
        WFIOAPP.APP.run(sce);
        sce.getServletContext().setAttribute("APP", WFIOAPP.APP);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        WFIOAPP.APP.destroy();
    }
}
