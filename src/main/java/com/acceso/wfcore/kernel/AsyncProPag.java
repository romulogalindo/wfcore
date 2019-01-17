package com.acceso.wfcore.kernel;

import com.acceso.wfcore.listerners.WFCoreListener;
import com.acceso.wfcore.utils.RegJson;
import com.acceso.wfcore.utils.RegJsonAdapter;
import com.acceso.wfcore.utils.Util;
import com.acceso.wfcore.utils.ValpagJson;
import com.acceso.wfweb.daos.Frawor4DAO;
import com.acceso.wfweb.dtos.ValpagDTO;
import com.acceso.wfweb.utils.JsonResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class AsyncProPag extends AsyncProcessor {

    public AsyncProPag(AsyncContext asyncCtx, int secs) {
        super(asyncCtx, secs);
    }

    @Override
    public void run() {
        try {
            PrintWriter out = this.asyncContext.getResponse().getWriter();

            HttpServletRequest request = (HttpServletRequest) asyncContext.getRequest();//request.
            Integer co_conten = Util.toInt(asyncContext.getRequest().getParameter("co_conten"), -1);
            Integer co_pagina = Util.toInt(asyncContext.getRequest().getParameter("co_pagina"), -1);
            Long id_frawor = Util.toLong(asyncContext.getRequest().getParameter("id_frawor"), -1);
            Integer co_botone = Util.toInt(asyncContext.getRequest().getParameter("co_botone"), -1);

            //ejecuta eñ valpag
            //el list del valpag;
            //List<ValpagDTO> resultado_valpag = new ArrayList<>();
            String valpag_js = "";

            //Frawor4DAO dao = new Frawor4DAO();

//            valpag_js = dao.getVPJS(co_pagina);
            valpag_js = "return API_DATA.JSON_PROPAG(API_DATA.SQL_LEGACY('wfacr', 'select * from frawor2.pfvalpag(\'+CO_PAGINA+\', \'+ID_FRAWOR+\', \'+CO_CONTEN+\')'));";
            //dao.close();

//            new FileInputStream(asyncContext.getRequest().getServletContext().getRealPath("/") + "js/main_Acr_shell.js");

            String jsText = Util.getText(asyncContext.getRequest().getServletContext().getRealPath("/") + "WEB-INF/classes/js/shell_valpag.js");
            jsText = jsText.replace("USUARI_DATA_JS_TEXT", valpag_js);
            System.out.println("jsText = " + jsText);

//            ValpagJson valpagJson_ = (ValpagJson) WFCoreListener.APP.getJavaScriptService().doJS64(jsText, "do_valpag(" + id_frawor + "," + co_conten + "," + co_pagina + ")");
            ValpagJson valpagJson = (ValpagJson) WFCoreListener.APP.getJavaScriptService().doJS64(jsText, "do_valpag", id_frawor, co_conten, co_pagina);

//            System.out.println("[1]valpagJson_ = " + valpagJson_);
//            String
//            ValpagJson valpagJson = ApplicationManager.buildNValPag(resultado_valpag);
            System.out.println("[2]valpagJson = " + valpagJson);

            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.setStatus("OK");
            jsonResponse.setResult(valpagJson);

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
