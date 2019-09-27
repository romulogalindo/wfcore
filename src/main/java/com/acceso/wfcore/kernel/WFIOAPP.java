package com.acceso.wfcore.kernel;

import com.acceso.wfcore.daos.SystemDAO;
import com.acceso.wfcore.dtos.SystemTreeDTO;
import com.acceso.wfcore.log.Log;
import com.acceso.wfcore.services.CacheService;
import com.acceso.wfcore.services.DataSourceService;
import com.acceso.wfcore.services.JavaScriptService;
import com.acceso.wfcore.services.MessageService;
import com.acceso.wfcore.utils.Util;
import com.acceso.wfcore.utils.Values;
import com.acceso.wfweb.controls.LoginCTRL;
import com.acceso.wfweb.web.Root;

import javax.servlet.ServletContextEvent;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class WFIOAPP {

    public static final String APPLICATION_PROJECT_NAME = "ZERO DAWN";
    public static final String APPLICATION_NAME = "Workflow AIO2";
    public static final String APPLICATION_DEFAULT_LOCALE = "ES_PE";

    private LoginCTRL loginCTRL;

    //se creara el servicio aqui momentaneamente
    public CacheService cacheService;
    public DataSourceService dataSourceService;
    public JavaScriptService javaScriptService;
    public MessageService messageService;

    //Variables
    public String VALPAGJS = "";
    public String COMPAGJS = "";
    public String PROPAGJS = "";
    public String PAGEJS = "";

    public boolean THROWS_EXCEPTION = false;
    public boolean SHOW_PREQUERY = false;

    //Executor!!!!
    ThreadPoolExecutor executor;
    public static final WFIOAPP APP;

    /*
     *FIRST RUN!
     */
    static {
        APP = new WFIOAPP();
    }

    public WFIOAPP() {
        this.loginCTRL = ApplicationManager.getLoginCTRL();
        this.cacheService = new CacheService("AIOCACHE");
        this.dataSourceService = new DataSourceService("DataSourceService");
        this.javaScriptService = new JavaScriptService("JSEngine");
        this.messageService = new MessageService("MessageService");

        this.executor = new ThreadPoolExecutor(1000, 8000, 50000L,
                TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(1000));
    }

    public void run(ServletContextEvent sce) {
        /*INIT PARAMS*/
        Log.info("--[[\n"
                + " .----------------.  .----------------.  .----------------.  .----------------. \n"
                + "| .--------------. || .--------------. || .--------------. || .--------------. |\n"
                + "| |      __      | || |     _____    | || |     ____     | || |    _____     | |\n"
                + "| |     /  \\     | || |    |_   _|   | || |   .'    `.   | || |   / ___ `.   | |\n"
                + "| |    / /\\ \\    | || |      | |     | || |  /  .--.  \\  | || |  |_/___) |   | |\n"
                + "| |   / ____ \\   | || |      | |     | || |  | |    | |  | || |   .'____.'   | |\n"
                + "| | _/ /    \\ \\_ | || |     _| |_    | || |  \\  `--'  /  | || |  / /____     | |\n"
                + "| ||____|  |____|| || |    |_____|   | || |   `.____.'   | || |  |_______|   | |\n"
                + "| |              | || |              | || |              | || |              | |\n"
                + "| '--------------' || '--------------' || '--------------' || '--------------' |\n"
                + " '----------------'  '----------------'  '----------------'  '----------------' \n"
                + "--]]");
        THROWS_EXCEPTION = Util.toBoolean(sce.getServletContext().getInitParameter("THROWS_EXCEPTION"), false);
        SHOW_PREQUERY = Util.toBoolean(sce.getServletContext().getInitParameter("SHOW_PREQUERY"), false);
        Log.info("╔===============================================╗");
        Log.info("║Parametros**\t\t\t\t║");
        Log.info("║THROWS_EXCEPTION:" + THROWS_EXCEPTION + "\t\t\t\t║");
        Log.info("║SHOW_PREQUERY:" + SHOW_PREQUERY + "\t\t\t\t║");
        Log.info("╠===============================================╣");

        /*Obtenemos variables del arranque!*/
        PAGEJS = sce.getServletContext().getRealPath("/") + "WEB-INF/classes/js/shell_page.js";

        Log.info("║Script base**\t\t\t\t║");
        Log.info("║PAGEJS:" + sce.getServletContext().getRealPath("/") + "WEB-INF/classes/js/shell_page.js" + "\t\t\t\t║");
        Log.info("╠===============================================╣");
        Log.info("║Servicios**\t\t\t\t║");

        /*SERVICES */
        cacheService.start();
        Log.info("║" + cacheService.getNameService() + "**\t\t\t\t║");
        Log.info("╟-----------------------------------------------╢");
        dataSourceService.start();
        Log.info("║" + dataSourceService.getNameService() + "**\t\t\t\t║");
        Log.info("╟-----------------------------------------------╢");
        javaScriptService.start();
        Log.info("║" + javaScriptService.getNameService() + "**\t\t\t\t║");
        Log.info("╟-----------------------------------------------╢");
        messageService.start();
        Log.info("║" + messageService.getNameService() + "**\t\t\t\t║");
        Log.info("╚===============================================╝");

        //creamos la cache del menu - LVL1
        cacheService.getZeroDawnCache().createSpace(Values.CACHE_MAIN_MENUTREE, String.class, Object.class, -1);
        //Crear cache de sessiones!posible uso futuro pendiente!!
        cacheService.getZeroDawnCache().createSpace(Values.CACHE_MAIN_SESSIONS, String.class, Object.class, 60);
        //contenedores
        cacheService.getZeroDawnCache().createSpace(Values.CACHE_MAIN_CONTENTS, String.class, Object.class, 60);

        //creamos la cache de contenedores - LVL2
        cacheService.getZeroDawnCache().createSpace(Values.CACHE_MAIN_CONTAINER, Integer.class, Object.class, -1);

        //creamos la cache de script - LVL2 -->Posiblemente sea CNT:VALPAG!
        cacheService.getZeroDawnCache().createSpace(Values.CACHE_MAIN_PAGEJS, String.class, Object.class, -1);

        //creamos la cache de archivos - LVL2
        cacheService.getZeroDawnCache().createSpace(Values.CACHE_MAIN_FILEX, String.class, Object.class, 60 * 6);

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
