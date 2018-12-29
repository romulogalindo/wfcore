package com.acceso.wfweb.beans;

import java.io.Serializable;
import java.util.Enumeration;

import com.acceso.wfcore.kernel.Application;
import com.acceso.wfcore.kernel.ApplicationManager;
import com.acceso.wfcore.listerners.WFCoreListener;
import com.acceso.wfcore.utils.Util;
import com.acceso.wfcore.utils.Values;
import com.acceso.wfweb.units.Contenedor;
import com.sun.xml.fastinfoset.algorithm.BuiltInEncodingAlgorithm;
import org.ocpsoft.rewrite.servlet.impl.HttpRewriteWrappedRequest;

public class ContenedorBean implements Serializable {
    Contenedor contenedor;

    public ContenedorBean() {
    }

    public String do64(HttpRewriteWrappedRequest httpRewriteWrappedRequest) {
        //Esto debe ser eliminado
        Enumeration<String> parameterNames = httpRewriteWrappedRequest.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String key = parameterNames.nextElement();
            String value = httpRewriteWrappedRequest.getParameter(key);
            System.out.println("key = " + key + ",value = " + value);
        }

        //construir el conenedor!!
        Integer co_conten = Util.toInt(httpRewriteWrappedRequest.getParameter("co_conten"));

        //preguntar a la cache si tienen este contenedor
        contenedor = (Contenedor) WFCoreListener.APP.cacheService.getZeroDawnCache().getSpace(Values.CACHE_MAIN_CONTAINER).get(co_conten);
        if (contenedor == null) {
            contenedor = ApplicationManager.buildContainer(co_conten);
        }

        return "x";
    }
}
