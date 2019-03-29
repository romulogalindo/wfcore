package com.acceso.wfcore.kernel;

import com.acceso.wfcore.listerners.WFCoreListener;
import com.acceso.wfcore.utils.RegJson;
import com.acceso.wfcore.utils.RegJsonAdapter;
import com.acceso.wfcore.utils.Util;
import com.acceso.wfcore.utils.ValpagJson;
import com.acceso.wfweb.daos.Frawor4DAO;
import com.acceso.wfweb.dtos.ComboDTO;
import com.acceso.wfweb.dtos.ValpagDTO;
import com.acceso.wfweb.units.Usuario;
import com.acceso.wfweb.utils.JsonResponse;
import com.acceso.wfweb.utils.RequestManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;

public class AsyncValPag extends AsyncProcessor {

    public AsyncValPag(AsyncContext asyncCtx, int secs) {
        super(asyncCtx, secs);
    }

    @Override
    public void run() {
        try {
            PrintWriter out = this.asyncContext.getResponse().getWriter();

            RequestManager requestManager = new RequestManager((HttpServletRequest) asyncContext.getRequest(), null);

//            HttpServletRequest request = (HttpServletRequest) asyncContext.getRequest();//request.
//            Integer co_conten = Util.toInt(asyncContext.getRequest().getParameter("co_conten"), -1);
//            Integer co_pagina = Util.toInt(asyncContext.getRequest().getParameter("co_pagina"), -1);
//            Long id_frawor = Util.toLong(asyncContext.getRequest().getParameter("id_frawor"), -1);
//            String ls_hamoda = asyncContext.getRequest().getParameter("ls_hamoda");
//            String ls_conpar = asyncContext.getRequest().getParameter("ls_conpar");

            Integer co_conten = Util.toInt(requestManager.getParam("co_conten"), -1);
            Integer co_pagina = Util.toInt(requestManager.getParam("co_pagina"), -1);
            Long id_frawor = Util.toLong(requestManager.getParam("id_frawor"), -1);
            String ls_hamoda = requestManager.getParam("ls_hamoda");
            String ls_conpar = "\"" + requestManager.getParam("ls_conpar") + "\"";

            System.out.println("[@AsyncValPag]co_conten = " + co_conten);
            System.out.println("[@AsyncValPag]ls_conpar = " + ls_conpar);

            //ejecuta eñ valpag
            String valpag_js = "";

            Frawor4DAO dao = new Frawor4DAO();
//            valpag_js = dao.getVPJS(co_pagina);
            //valpag_js = "return API_DATA.JSON_VALPAG(API_DATA.SQL_LEGACY('wfacr', 'select * from frawor2.pfvalpag(\'+CO_PAGINA+\', \'+ID_FRAWOR+\', \'+CO_CONTEN+\')'));";
            valpag_js = "return API_DATA.VALPAG_LEGACY('wfacr', 'select * from frawor2.pfvalpag(\'+CO_PAGINA+\', \'+ID_FRAWOR+\', \'+CO_CONTEN+\')');";
            dao.close();

            String jsText = Util.getText(asyncContext.getRequest().getServletContext().getRealPath("/") + "WEB-INF/classes/js/shell_valpag.js");
            jsText = jsText.replace("USUARI_DATA_JS_TEXT", valpag_js);
            // System.out.println("jsText = " + jsText);

            ValpagJson valpagJson = (ValpagJson) WFCoreListener.APP.getJavaScriptService().doValpag64(jsText, "do_valpag", id_frawor, co_conten, co_pagina, ls_conpar, requestManager.getUser().getCo_usuari(), 1);

            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.setStatus("OK");
            jsonResponse.setResult(valpagJson);


            System.out.println("[" + co_pagina + "]valpagJson0 = " + valpagJson);
            System.out.println("[" + co_pagina + "]valpagJson1 = " + valpagJson.getRows());
            System.out.println("[" + co_pagina + "]ls_hamoda = " + ls_hamoda);
//            System.out.println("[X]valpagJson2 = " + (valpagJson.getRows() != null || !valpagJson.getRows().isEmpty()));

            if ((valpagJson.getRows() != null && !valpagJson.getRows().isEmpty()) && ls_hamoda.length() > 0) {
//                if (!valpagJson.getRows().isEmpty()) {
                System.out.println("[" + co_pagina + "]==>" + valpagJson);
                HashMap<String, Object> map_hamodas = new HashMap<>();

                String[] hamodas = ls_hamoda.split(",");
                for (int i = 0; i < hamodas.length; i++) {
                    String hamoda = hamodas[i];
                    List<ComboDTO> comboDTOS = new ArrayList<>();
                    dao = new Frawor4DAO(WFCoreListener.dataSourceService.getManager("wfacr").getNativeSession());
                    comboDTOS = dao.getCombo(co_pagina, id_frawor, co_conten, Util.toShort(hamoda, (short) -1));
                    dao.close();
                    map_hamodas.put(hamoda, comboDTOS);
                }

                jsonResponse.setAditional(map_hamodas);
//                }
            }

            //Gson gson = new Gson();
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(RegJson.class, new RegJsonAdapter())
                    .create();

            String urpta = gson.toJson(jsonResponse);

            System.out.println("[" + co_pagina + "]valpag? = " + urpta);

            out.write(urpta);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //complete the processing
        asyncContext.complete();
    }

}
