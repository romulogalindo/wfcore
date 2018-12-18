package com.acceso.wfcore.kernel;

import com.acceso.wfcore.services.CacheService;
import com.acceso.wfcore.services.DataSourceService;
import com.acceso.wfcore.utils.Values;
import com.acceso.wfweb.controls.LoginCTRL;

public class Application {
    public static final String APPLICATION_PROJECT_NAME = "ZERO DAWN";
    public static final String APPLICATION_NAME = "Workfloa AIO2";
    public static final String APPLICATION_DEFAULT_LOCALE = "ES_PE";

    private LoginCTRL loginCTRL;

    //se creara el servicio aqui momentaneamente
    public CacheService cacheService;
    public DataSourceService dataSourceService;

    public Application() {
        loginCTRL = ApplicationManager.getLoginCTRL();
        cacheService = new CacheService("AIOCACHE");
        dataSourceService = new DataSourceService("DataSourceService");
    }

    public void run() {
        cacheService.start();
        dataSourceService.start();

        //creamos la cache del menu
        cacheService.getZeroDawnCache().createSpace(Values.CACHE_MAIN_MENUTREE, String.class, Object.class);

        //construiremos el objeto cache(no es renderer)

    }

    public void buildSystemTree(String jsonTreeRoot) {

    }

    public LoginCTRL getLoginCTRL() {
        return this.loginCTRL;
    }

    public void setLoginCTRL(LoginCTRL loginCTRL) {
        this.loginCTRL = loginCTRL;
    }


}
