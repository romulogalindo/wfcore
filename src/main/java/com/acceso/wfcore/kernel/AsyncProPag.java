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

import org.apache.commons.fileupload.FileItem;

public class AsyncProPag extends AsyncProcessor {

    public AsyncProPag(AsyncContext asyncCtx, int secs) {
        super(asyncCtx, secs);
    }

    @Override
    public void run() {
        try {
            PrintWriter out = this.asyncContext.getResponse().getWriter();

            HttpServletRequest httpServletRequest = (HttpServletRequest) asyncContext.getRequest();
//            RequestManager requestManager = new RequestManager(httpServletRequest, null);
            RequestManager requestManager = new RequestManager((HttpServletRequest) asyncContext.getRequest(), null);

            int co_conten = Util.toInt(requestManager.getParam("co_conten"), -1);
            int co_pagina = Util.toInt(requestManager.getParam("co_pagina"), -1);
            long id_frawor = Util.toLong(requestManager.getParam("id_frawor"), -1);
            short co_botone = Util.toShort(requestManager.getParam("co_botone"), (short) -1);
            boolean il_proces = Util.toBoolean(requestManager.getParam("il_proces"), false);

            String valpag_js = "";

            Frawor4DAO dao = new Frawor4DAO();

//            valpag_js = dao.getVPJS(co_pagina);
            //valpag_js = "return API_DATA.JSON_PROPAG(API_DATA.SQL_LEGACY('wfacr', 'select * from frawor2.pfpropag(\'+CO_PAGINA+\', \'+ID_FRAWOR+\', \'+CO_CONTEN+\',\'+CO_PAGBOT+\')'));";
            valpag_js = "return API_DATA.SQL('wfacr', 'select 1 as pfpropag from frawor2.pfpropag(\'+CO_PAGINA+\', \'+ID_FRAWOR+\', \'+CO_CONTEN+\', cast(\'+CO_PAGBOT+\' as smallint))');";
            dao.close();


            String jsText = Util.getText(asyncContext.getRequest().getServletContext().getRealPath("/") + "WEB-INF/classes/js/shell_propag.js");
            jsText = jsText.replace("USUARI_DATA_JS_TEXT", valpag_js);
            System.out.println("jsText = " + jsText);

            Object object = (Object) WFCoreListener.APP.getJavaScriptService().doPropag64(jsText, "do_propag", co_pagina, id_frawor, co_conten, co_botone);

            System.out.println("[1]object = " + object);
//            String
//            ValpagJson valpagJson = ApplicationManager.buildNValPag(resultado_valpag);
//            System.out.println("[2]valpagJson = " + object);

            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.setStatus("OK");
            jsonResponse.setResult(object);

            //Gson gson = new Gson();
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(RegJson.class, new RegJsonAdapter())
                    .create();

//            ultraJS = gson.toJson(iValPag);
            String urpta = gson.toJson(jsonResponse);

            System.out.println("propag? = " + urpta);

            out.write(urpta);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //complete the processing
        asyncContext.complete();
    }

}
