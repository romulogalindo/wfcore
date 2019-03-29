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
import com.google.gson.Gson;
import org.ocpsoft.rewrite.servlet.impl.HttpRewriteWrappedRequest;

import javax.servlet.http.HttpServletRequest;

public class ContenedorBean implements Serializable {

    Contenedor contenedor;

    public ContenedorBean() {
    }

    public void do64(HttpRewriteWrappedRequest httpRewriteWrappedRequest) {
        //Esto debe ser eliminado
        RequestManager requestManager = new RequestManager(httpRewriteWrappedRequest, null);
        Integer co_conten = Util.toInt(requestManager.getParam("co_conten"), -1);

        long id_frawor;
        String ls_conpar = "{ ";

        //construir el conenedor!!
        Frawor4DAO dao = new Frawor4DAO();
        Frawor4DAO dao_fdb = new Frawor4DAO(WFCoreListener.dataSourceService.getManager("wfacr").getNativeSession());

        id_frawor = dao.getIdfraworDTO().getId_frawor();

        //unir la session con el idfrawor
        dao.joinTF(requestManager.getUser().getId_sesion(), id_frawor, -1, co_conten, true);
        dao_fdb.joinTF(requestManager.getUser().getId_sesion(), id_frawor, id_frawor, co_conten, false);

        //Grabando co_compar
        requestManager.getConpars().entrySet().stream().forEach((conpar) -> {
            ProcesoDTO procesoDTO = dao.saveCompar(id_frawor, co_conten, conpar.getKey(), conpar.getValue(), true);
            ProcesoDTO procesoDTO2 = dao_fdb.saveCompar(id_frawor, co_conten, conpar.getKey(), conpar.getValue(), false);
        });

        ls_conpar = requestManager.getConpars().entrySet().stream()
                .map((conpar) -> "\"co_conpar_" + conpar.getKey() + "\":\"" + conpar.getKey() + "\",")
                .reduce(ls_conpar, String::concat);

        ls_conpar = ls_conpar.substring(0, ls_conpar.length() - 1);
        ls_conpar += "}";

        dao.close();
        dao_fdb.close();

        //preguntar a la cache si tienen este contenedor
        contenedor = (Contenedor) WFCoreListener.APP.cacheService.getZeroDawnCache().getSpace(Values.CACHE_MAIN_CONTAINER).get(co_conten);

        if (contenedor == null) {

            contenedor = ApplicationManager.buildContainer(co_conten, id_frawor);
            WFCoreListener.APP.cacheService.getZeroDawnCache().getSpace(Values.CACHE_MAIN_CONTAINER).put(co_conten, contenedor);
        } else {

            contenedor.setId_frawor(id_frawor);
        }

        contenedor.setIl_header(Util.toBoolean(requestManager.getParam("il_header"), true));
        contenedor.setLs_conpar(ls_conpar);

        requestManager.save_over_session("CNT" + contenedor.getCo_conten() + ":" + id_frawor, contenedor);
    }

    public Contenedor getContenedor() {
        return contenedor;
    }

    public void setContenedor(Contenedor contenedor) {
        this.contenedor = contenedor;
    }
}
