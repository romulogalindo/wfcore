package com.acceso.wfweb.beans;

import java.io.Serializable;
import com.acceso.wfcore.kernel.ApplicationManager;
import com.acceso.wfcore.kernel.WFIOAPP;
import com.acceso.wfcore.utils.Util;
import com.acceso.wfcore.utils.Values;
import com.acceso.wfweb.daos.Frawor4DAO;
import com.acceso.wfweb.dtos.ProcesoDTO;
import com.acceso.wfweb.units.Contenedor;
import com.acceso.wfweb.utils.RequestManager;
import org.apache.commons.lang3.SerializationUtils;
import org.ocpsoft.rewrite.servlet.impl.HttpRewriteWrappedRequest;

public class ContenedorBean implements Serializable {

    Contenedor contenedor;

    public ContenedorBean() {
    }

    public void do64(HttpRewriteWrappedRequest httpRewriteWrappedRequest) {
        //Esto debe ser eliminado
        RequestManager requestManager = new RequestManager(httpRewriteWrappedRequest, null);
//        Integer co_conten = Util.toInt(requestManager.getParam("co_conten"), -1);
        Integer co_conten = requestManager.getCo_conten();

        long id_frawor;

        //construir el conenedor!!
        Frawor4DAO dao = new Frawor4DAO();

        id_frawor = dao.getIdfraworDTO().getId_frawor();

        //unir la session con el idfrawor
        dao.joinTF(requestManager.getUser().getId_sesion(), id_frawor, -1, co_conten, true);

        //Grabando co_compar!
        requestManager.getConpars().entrySet().stream().forEach((conpar) -> {
            ProcesoDTO procesoDTO = dao.saveCompar(id_frawor, co_conten, conpar.getKey(), conpar.getValue(), true);
        });
        dao.close();

//        String ls_conpar = "{ ";
//        ls_conpar = requestManager.getConpars().entrySet().stream()
//                .map((conpar) -> "\"co_conpar_" + conpar.getKey() + "\":\"" + conpar.getValue() + "\",")
//                .reduce(ls_conpar, String::concat);
        //preguntar a la cache si tienen este contenedor
        contenedor = (Contenedor) WFIOAPP.APP.cacheService.getZeroDawnCache().getSpace(Values.CACHE_MAIN_CONTAINER).get(co_conten);

        if (contenedor == null) {
            contenedor = ApplicationManager.buildContainer(co_conten, id_frawor);
            WFIOAPP.APP.cacheService.getZeroDawnCache().getSpace(Values.CACHE_MAIN_CONTAINER).put(co_conten, contenedor);
        } else {
            contenedor.setId_frawor(id_frawor);
        }

        //ES POPUP?
        contenedor = SerializationUtils.clone(contenedor);
        contenedor.setIl_popup(Util.toBoolean(requestManager.getParam("il_popup"), false));

        //SE LE ASIGNA LOS PARAMETROS
//        ls_conpar = requestManager.getConpars().entrySet().stream()
//                .map((conpar) -> "\"" + conpar.getKey() + "\":\"" + conpar.getValue() + "\",")
//                .reduce(ls_conpar, String::concat);
//
//        ls_conpar = (ls_conpar.length() == 1 ? "" : ls_conpar.substring(0, ls_conpar.length() - 1)) + "}";
//        System.out.println("ls_conpar => " + ls_conpar);
        requestManager.getConpars().entrySet().stream().forEach(m -> {
            contenedor.put_conpar(m.getKey(), m.getValue());
        });
//        contenedor.setLs_conpar(ls_conpar);

        requestManager.save_over_session("CNT" + contenedor.getCo_conten() + ":" + id_frawor, contenedor);
        
        //poner tambien el contenedor como referencia en la cache de duracion 
        WFIOAPP.APP.getCacheService().getZeroDawnCache().getSpace(Values.CACHE_MAIN_CONTENTS).put("CNT" + contenedor.getCo_conten() + ":" + id_frawor, contenedor);
    }

    public Contenedor getContenedor() {
        return contenedor;
    }

    public void setContenedor(Contenedor contenedor) {
        this.contenedor = contenedor;
    }
}
