package com.acceso.wfweb.beans;

import java.io.Serializable;
import java.util.Enumeration;

import com.acceso.wfcore.kernel.Application;
import com.acceso.wfcore.kernel.ApplicationManager;
import com.acceso.wfcore.listerners.WFCoreListener;
import com.acceso.wfcore.utils.Util;
import com.acceso.wfcore.utils.Values;
import com.acceso.wfweb.units.Contenedor;
import org.ocpsoft.rewrite.servlet.impl.HttpRewriteWrappedRequest;

import javax.servlet.http.HttpServletRequest;

public class ContenedorBean implements Serializable {
    Contenedor contenedor;

    public ContenedorBean() {
    }

    public void do64(HttpRewriteWrappedRequest httpRewriteWrappedRequest) {
        //Esto debe ser eliminado
        Enumeration<String> parameterNames = httpRewriteWrappedRequest.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String key = parameterNames.nextElement();
            String value = httpRewriteWrappedRequest.getParameter(key);
            System.out.println("key = " + key + ",value = " + value);
        }

        HttpServletRequest request = (HttpServletRequest) httpRewriteWrappedRequest.getRequest();

        //construir el conenedor!!
        Integer co_conten = Util.toInt(httpRewriteWrappedRequest.getParameter("co_conten"), -1);

        //preguntar a la cache si tienen este contenedor
        contenedor = (Contenedor) WFCoreListener.APP.cacheService.getZeroDawnCache().getSpace(Values.CACHE_MAIN_CONTAINER).get(co_conten);
        System.out.println(">>>>co_conten = " + co_conten);
        httpRewriteWrappedRequest.getRequest();
        if (contenedor == null) {

            //crear el contenedor
            contenedor = ApplicationManager.buildContainer(co_conten);
            System.out.println("{a->1}co_conten = " + co_conten);
            //almancenar el contenedor
            WFCoreListener.APP.cacheService.getZeroDawnCache().getSpace(Values.CACHE_MAIN_CONTAINER).put(co_conten, contenedor);
        }

        request.getSession().setAttribute("" + contenedor.getCo_conten(), contenedor);
    }

    public Contenedor getContenedor() {
        return contenedor;
    }

    public void setContenedor(Contenedor contenedor) {
        this.contenedor = contenedor;
    }
}
