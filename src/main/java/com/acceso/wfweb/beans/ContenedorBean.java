package com.acceso.wfweb.beans;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Map;

import com.acceso.wfcore.kernel.Application;
import com.acceso.wfcore.kernel.ApplicationManager;
import com.acceso.wfcore.listerners.WFCoreListener;
import com.acceso.wfcore.utils.Util;
import com.acceso.wfcore.utils.Values;
import com.acceso.wfweb.daos.Frawor4DAO;
import com.acceso.wfweb.dtos.ProcesoDTO;
import com.acceso.wfweb.units.Contenedor;
import com.acceso.wfweb.utils.RequestManager;
import org.ocpsoft.rewrite.servlet.impl.HttpRewriteWrappedRequest;

import javax.servlet.http.HttpServletRequest;

public class ContenedorBean implements Serializable {
    Contenedor contenedor;

    public ContenedorBean() {
    }

    public void do64(HttpRewriteWrappedRequest httpRewriteWrappedRequest) {
        //Esto debe ser eliminado

        //HttpServletRequest request = (HttpServletRequest) httpRewriteWrappedRequest.getRequest();
        RequestManager requestManager = new RequestManager(httpRewriteWrappedRequest, null);
        long id_frawor;

        Integer co_conten = Util.toInt(httpRewriteWrappedRequest.getParameter("co_conten"), -1);

        //construir el conenedor!!
        Frawor4DAO dao = new Frawor4DAO();
        Frawor4DAO dao_fdb = new Frawor4DAO(WFCoreListener.dataSourceService.getManager("wfacr").getNativeSession());

        id_frawor = dao.getIdfraworDTO().getId_frawor();
        for (Map.Entry<Integer, String> entry : requestManager.getConpars().entrySet()) {
            System.out.println("Item : " + entry.getKey() + " Count : " + entry.getValue());
            ProcesoDTO procesoDTO = dao.saveCompar(id_frawor, co_conten, entry.getKey(), entry.getValue(), true);
            ProcesoDTO procesoDTO2 = dao_fdb.saveCompar(id_frawor, co_conten, entry.getKey(), entry.getValue(), false);

            System.out.println("procesoDTO = " + procesoDTO);
            System.out.println("procesoDTO2 = " + procesoDTO2);
        }

        dao.close();
        dao_fdb.close();


        //preguntar a la cache si tienen este contenedor
        contenedor = (Contenedor) WFCoreListener.APP.cacheService.getZeroDawnCache().getSpace(Values.CACHE_MAIN_CONTAINER).get(co_conten);
        System.out.println(">>>>co_conten = " + co_conten);

        //httpRewriteWrappedRequest.getRequest();

        if (contenedor == null) {

            //crear el contenedor
            contenedor = ApplicationManager.buildContainer(co_conten, id_frawor);
            System.out.println("{a->1}co_conten = " + co_conten);
            //almancenar el contenedor
            WFCoreListener.APP.cacheService.getZeroDawnCache().getSpace(Values.CACHE_MAIN_CONTAINER).put(co_conten, contenedor);
        } else {
            contenedor.setId_frawor(id_frawor);
        }

        requestManager.save_over_session("" + contenedor.getCo_conten(), contenedor);
    }

    public Contenedor getContenedor() {
        return contenedor;
    }

    public void setContenedor(Contenedor contenedor) {
        this.contenedor = contenedor;
    }
}
