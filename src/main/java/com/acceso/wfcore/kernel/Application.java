package com.acceso.wfcore.kernel;

import com.acceso.wfcore.daos.SystemDAO;
import com.acceso.wfcore.dtos.SystemTreeDTO;
import com.acceso.wfcore.services.CacheService;
import com.acceso.wfcore.services.DataSourceService;
import com.acceso.wfcore.services.JavaScriptService;
import com.acceso.wfcore.utils.Values;
import com.acceso.wfweb.controls.LoginCTRL;
import com.acceso.wfweb.web.Root;

public class Application {
    public static final String APPLICATION_PROJECT_NAME = "ZERO DAWN";
    public static final String APPLICATION_NAME = "Workflow AIO2";
    public static final String APPLICATION_DEFAULT_LOCALE = "ES_PE";

    private LoginCTRL loginCTRL;

    //se creara el servicio aqui momentaneamente
    public CacheService cacheService;
    public DataSourceService dataSourceService;
    public JavaScriptService javaScriptService;


    public Application() {
        loginCTRL = ApplicationManager.getLoginCTRL();
        cacheService = new CacheService("AIOCACHE");
        dataSourceService = new DataSourceService("DataSourceService");
        javaScriptService = new JavaScriptService("JSEngine");
    }

    public void run() {
        cacheService.start();
        dataSourceService.start();
        javaScriptService.start();

        //creamos la cache del menu - LVL1
        cacheService.getZeroDawnCache().createSpace(Values.CACHE_MAIN_MENUTREE, String.class, Object.class);

        //creamos la cache de contenedores - LVL2
        cacheService.getZeroDawnCache().createSpace(Values.CACHE_MAIN_CONTAINER, Integer.class, Object.class);

        //construiremos el objeto cache(no es renderer)
        SystemTreeDTO systemTreeDTO;

        //extyraer data del dao
        SystemDAO systemDAO = new SystemDAO();
        systemTreeDTO = systemDAO.getSystemTreeDTO();
        systemDAO.close();

        //el dto poner en modo super obj
        Root mainTree = ApplicationManager.buildRootTree(systemTreeDTO);

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

    public void destroy(){
        cacheService.stop();
        dataSourceService.stop();
        javaScriptService.stop();
    }
}
