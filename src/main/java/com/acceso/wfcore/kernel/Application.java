package com.acceso.wfcore.kernel;

import com.acceso.wfcore.daos.SystemDAO;
import com.acceso.wfcore.dtos.SystemTreeDTO;
import com.acceso.wfcore.services.CacheService;
import com.acceso.wfcore.services.DataSourceService;
import com.acceso.wfcore.services.JavaScriptService;
import com.acceso.wfcore.utils.Values;
import com.acceso.wfweb.controls.LoginCTRL;
import com.acceso.wfweb.web.Root;

import javax.servlet.ServletContextEvent;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Application {
    public static final String APPLICATION_PROJECT_NAME = "ZERO DAWN";
    public static final String APPLICATION_NAME = "Workflow AIO2";
    public static final String APPLICATION_DEFAULT_LOCALE = "ES_PE";

    private LoginCTRL loginCTRL;

    //se creara el servicio aqui momentaneamente
    public CacheService cacheService;
    public DataSourceService dataSourceService;
    public JavaScriptService javaScriptService;

    //Variables
    public String VALPAGJS = "";
    public String PROPAGJS = "";

    //Executor!!!!
    ThreadPoolExecutor executor;


    public Application() {
        this.loginCTRL = ApplicationManager.getLoginCTRL();
        this.cacheService = new CacheService("AIOCACHE");
        this.dataSourceService = new DataSourceService("DataSourceService");
        this.javaScriptService = new JavaScriptService("JSEngine");
        this.executor = new ThreadPoolExecutor(100, 200, 50000L,
                TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(100));
    }

    public void run(ServletContextEvent sce) {
        /*Obtenemos variables del arranque!*/
        VALPAGJS = sce.getServletContext().getRealPath("/") + "WEB-INF/classes/js/shell_valpag.js";
        PROPAGJS = sce.getServletContext().getRealPath("/") + "WEB-INF/classes/js/shell_valpag.js";

                /*SERVICES */
        cacheService.start();
        dataSourceService.start();
        javaScriptService.start();

        //creamos la cache del menu - LVL1
        cacheService.getZeroDawnCache().createSpace(Values.CACHE_MAIN_MENUTREE, String.class, Object.class, -1);

        //creamos la cache de contenedores - LVL2
        cacheService.getZeroDawnCache().createSpace(Values.CACHE_MAIN_CONTAINER, Integer.class, Object.class, -1);

        //creamos la cache de script - LVL2 -->Posiblemente sea CNT:PAG
        cacheService.getZeroDawnCache().createSpace(Values.CACHE_MAIN_PAGINA, Integer.class, Object.class, -1);

        //creamos la cache de archivos - LVL2
        cacheService.getZeroDawnCache().createSpace(Values.CACHE_MAIN_FILEX, String.class, Object.class, 1200);

        //construiremos el objeto cache(no es renderer)
        SystemTreeDTO systemTreeDTO;

        //extyraer data del dao
        SystemDAO systemDAO = new SystemDAO();
        systemTreeDTO = systemDAO.getSystemTreeDTO();
        systemDAO.close();

        //====>Crear el Menu!
        Root mainTree;
        try {
            mainTree = ApplicationManager.buildRootTree(systemTreeDTO);
        } catch (Exception ep) {
            System.out.println("Error al crear el arbol principal:" + ep.getMessage());
            mainTree = new Root();
        }

        //poner rootTree en la cache
        cacheService.getZeroDawnCache().getSpace(Values.CACHE_MAIN_MENUTREE).put("ROOT_TREE", mainTree);

        System.out.println("mainTree = " + mainTree);

    }


    public LoginCTRL getLoginCTRL() {
        return this.loginCTRL;
    }

    public void setLoginCTRL(LoginCTRL loginCTRL) {
        this.loginCTRL = loginCTRL;
    }

    public CacheService getCacheService() {
        return cacheService;
    }

    public void setCacheService(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    public DataSourceService getDataSourceService() {
        return dataSourceService;
    }

    public void setDataSourceService(DataSourceService dataSourceService) {
        this.dataSourceService = dataSourceService;
    }

    public ThreadPoolExecutor getExecutor() {
        return executor;
    }

    public void setExecutor(ThreadPoolExecutor executor) {
        this.executor = executor;
    }

    public JavaScriptService getJavaScriptService() {
        return javaScriptService;
    }

    public void setJavaScriptService(JavaScriptService javaScriptService) {
        this.javaScriptService = javaScriptService;
    }

    public void destroy() {
        cacheService.stop();
        dataSourceService.stop();
        javaScriptService.stop();
        executor.shutdown();
    }
}
