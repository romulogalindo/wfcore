package com.acceso.wfcore.listerners;

import com.acceso.wfcore.services.DataSourceService;
import java.util.Hashtable;
import java.util.Iterator;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.naming.NamingContext;

/**
 * Web application lifecycle listener.
 *
 * @author Augusto Galindo
 */
public class WFCoreListener implements ServletContextListener {

    DataSourceService dataSourceService;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        dataSourceService = new DataSourceService("DataSourceService");
        dataSourceService.start();

        try {
            InitialContext initCtx = new InitialContext();
            System.out.println("--->" + initCtx.lookup("java:comp/env").getClass());
//            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            NamingContext envCtx = (NamingContext) initCtx.lookup("java:comp/env");
            System.out.println("envCtx=" + envCtx);

            Hashtable hash = envCtx.getEnvironment();
            System.out.println("hash=" + hash);
            if (!hash.isEmpty()) {
                Iterator it = hash.keys().asIterator();
                while (it.hasNext()) {
                    String key = (String) it.next();
                    System.out.println("::" + key + "->" + hash.get(key));
                }
            }
//            System.out.println("@NameInNamespace->" + envCtx.getNameInNamespace());
//initCtx.
            NamingEnumeration<NameClassPair> list = envCtx.list("");
            while (list.hasMore()) {
                NameClassPair classPair = list.next();
                System.out.println("classPair.toString()=" + classPair.toString());
                System.out.println("Name=" + classPair.getName());
//                System.out.println("NameInNamespace=" + classPair.getNameInNamespace());
                System.out.println("ClassName=" + classPair.getClassName());
                System.out.println("Relative=" + classPair.isRelative());
            }
//            envCtx.list("").
//envCtx.
            org.apache.naming.ResourceLinkRef ref;
            ref = new org.apache.naming.ResourceLinkRef("resourceClass", "globalName", "factory", "factoryLocation");
//            envCtx.

//            ResourceLinkFactory.
//            envCtx.
        } catch (Exception ep) {
            System.out.println("ep = " + ep);
            ep.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        dataSourceService.stop();
    }
}
