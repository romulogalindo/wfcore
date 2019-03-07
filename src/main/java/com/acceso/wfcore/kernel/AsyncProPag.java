package com.acceso.wfcore.kernel;

import com.acceso.wfcore.listerners.WFCoreListener;
import com.acceso.wfcore.utils.RegJson;
import com.acceso.wfcore.utils.RegJsonAdapter;
import com.acceso.wfcore.utils.Util;
import com.acceso.wfcore.utils.ValpagJson;
import com.acceso.wfweb.daos.Frawor4DAO;
import com.acceso.wfweb.dtos.ValpagDTO;
import com.acceso.wfweb.utils.JsonResponse;
import com.acceso.wfweb.utils.RequestManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;

public class AsyncProPag extends AsyncProcessor {

    public AsyncProPag(AsyncContext asyncCtx, int secs) {
        super(asyncCtx, secs);
    }

    @Override
    public void run() {
        PrintWriter out = null;

        try {
            out = this.asyncContext.getResponse().getWriter();
        } catch (Exception ep) {
        }

        if (out != null) {
            try {
                RequestManager requestManager = new RequestManager((HttpServletRequest) asyncContext.getRequest(), null);

                int co_conten = Util.toInt(requestManager.getParam("co_conten"), -1);
                int co_pagina = Util.toInt(requestManager.getParam("co_pagina"), -1);
                long id_frawor = Util.toLong(requestManager.getParam("id_frawor"), -1);
                short co_botone = Util.toShort(requestManager.getParam("co_botone"), (short) -1);
                boolean il_proces = Util.toBoolean(requestManager.getParam("il_proces"), false);

                String valpag_js = "";

                Frawor4DAO dao = new Frawor4DAO();
                Frawor4DAO dao2 = new Frawor4DAO(WFCoreListener.dataSourceService.getManager("wfacr").getNativeSession());

                dao.deletePagreg(id_frawor, co_pagina, true);
                dao2.deletePagreg(id_frawor, co_pagina, false);

                for (Map.Entry<Integer, String> pagreg : requestManager.getPagregs().entrySet()) {
                    dao.insertPagreg(id_frawor, co_pagina, pagreg.getKey().shortValue(), (short) 1, pagreg.getValue(), true);
                    dao2.insertPagreg(id_frawor, co_pagina, pagreg.getKey().shortValue(), (short) 1, pagreg.getValue(), false);
                }

                // valpag_js = dao.getVPJS(co_pagina);
//                valpag_js = "return API_DATA.SQL('wfacr', 'select 1 as pfpropag from frawor2.pfpropag(\'+CO_PAGINA+\', \'+ID_FRAWOR+\', \'+CO_CONTEN+\', cast(\'+CO_PAGBOT+\' as smallint))');";

                valpag_js = "return API_DATA.SQL('wfacr', 'select true as pfpropag from frawor2.pfpropag(\'+CO_PAGINA+\', \'+ID_FRAWOR+\', \'+CO_CONTEN+\', cast(\'+CO_PAGBOT+\' as smallint))');";
                dao.close();
                dao2.close();


                String jsText = Util.getText(asyncContext.getRequest().getServletContext().getRealPath("/") + "WEB-INF/classes/js/shell_propag.js");
                jsText = jsText.replace("USUARI_DATA_JS_TEXT", valpag_js);
                System.out.println("jsText = " + jsText);

                Object object = il_proces ? WFCoreListener.APP.getJavaScriptService().doPropag64(jsText, "do_propag", co_pagina, id_frawor, co_conten, co_botone) : "{}";

//                System.out.println("[1]PROPAG devolvio = " + object);

                out.write(object.toString());

            } catch (Exception ep) {
//                ep.printStackTrace();
                //Si algo no se puede controlar directamente en este flujo se debe devolver el objeto
                JsonResponse jsonResponse = new JsonResponse();
                jsonResponse.setStatus("OK");
                jsonResponse.setResult(null);
                jsonResponse.setError(Util.getError(ep));
                out.write(new Gson().toJson(jsonResponse));
            }
        }
        //complete the processing
        asyncContext.complete();
    }

}
