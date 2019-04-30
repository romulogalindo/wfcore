package com.acceso.wfcore.kernel;

import com.acceso.wfcore.listerners.WFCoreListener;
import com.acceso.wfcore.utils.*;
import com.acceso.wfweb.daos.Frawor4DAO;
import com.acceso.wfweb.dtos.ComboDTO;
import com.acceso.wfweb.units.Contenedor;
import com.acceso.wfweb.units.Usuario;
import com.acceso.wfweb.utils.JsonResponse;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

            Integer co_conten = Util.toInt(asyncContext.getRequest().getParameter("co_conten"), -1);
            Integer co_pagina = Util.toInt(asyncContext.getRequest().getParameter("co_pagina"), -1);
            Long id_frawor = Util.toLong(asyncContext.getRequest().getParameter("id_frawor"), -1);
            String ls_hamoda = asyncContext.getRequest().getParameter("ls_hamoda");
            String ls_conpar = ((Contenedor) ((HttpServletRequest) asyncContext.getRequest()).getSession().getAttribute("CNT" + co_conten + ":" + id_frawor)).getLs_conpar();

            //ejecutar valpag
            String valpag_js = (String) WFCoreListener.APP.getCacheService().getZeroDawnCache().getSpace(Values.CACHE_MAIN_VALPAGJS).get(co_pagina);

            if (valpag_js == null || valpag_js.contentEquals("null")) {
                Frawor4DAO dao = new Frawor4DAO();

                valpag_js = dao.getJS_Valpag(co_pagina).getScript();

                dao.close();

                if (valpag_js == null) {
                    valpag_js = "VALPAGJS = DATA.VALPAG_LEGACY('wfacr', 'select * from frawor2.pfvalpag(\'+CO_PAGINA+\', \'+ID_FRAWOR+\', \'+CO_CONTEN+\')');";
                }

                WFCoreListener.APP.getCacheService().getZeroDawnCache().getSpace(Values.CACHE_MAIN_VALPAGJS).put(co_pagina, valpag_js);
            }

            valpag_js = Util.getText(WFCoreListener.APP.VALPAGJS).replace("USUARI_DATA_JS_TEXT", valpag_js);

            ValpagJson valpagJson = (ValpagJson) WFCoreListener.APP.getJavaScriptService().doValpag64(valpag_js, "do_valpag", id_frawor, co_conten, co_pagina, ls_conpar, ((Usuario) ((HttpServletRequest) asyncContext.getRequest()).getSession().getAttribute("US")).getCo_usuari(), 1);

            String textjson = (String) WFCoreListener.APP.getJavaScriptService().dopvpj("GET_DO_POST_LOAD_DATA");
//            System.out.println("textjson = " + textjson);

            JsonResponse jsonResponse = JsonResponse.defultJsonResponseOK(valpagJson);
            jsonResponse.setFnpost(textjson);

            if ((valpagJson.getRows() != null && !valpagJson.getRows().isEmpty()) && ls_hamoda.length() > 0) {
                HashMap<String, Object> map_hamodas = new HashMap<>();

                /*
                COMPAGJS
                * */
                String compag_js = (String) WFCoreListener.APP.getCacheService().getZeroDawnCache().getSpace(Values.CACHE_MAIN_COMPAGJS).get(co_pagina);
                if (compag_js == null || compag_js.contentEquals("null")) {
                    Frawor4DAO dao = new Frawor4DAO();

                    compag_js = dao.getJS_Compag(co_pagina).getScript();

                    dao.close();

                    if (compag_js == null) {
                        compag_js = "VALPAGJS = DATA.COMPAG_LEGACY('wfacr', 'select * from frawor2.pfvalpag(\'+CO_PAGINA+\', \'+ID_FRAWOR+\', \'+CO_CONTEN+\')');";
                    }

                    WFCoreListener.APP.getCacheService().getZeroDawnCache().getSpace(Values.CACHE_MAIN_COMPAGJS).put(co_pagina, compag_js);
                }

                compag_js = Util.getText(WFCoreListener.APP.COMPAGJS).replace("USUARI_DATA_JS_TEXT", compag_js);

                System.out.println("compag_js = " + compag_js);

                String[] hamodas = ls_hamoda.split(",");

                Map<Short, Object> compags = (Map<Short, Object>) WFCoreListener.APP.getJavaScriptService().doCompag64(compag_js, "do_compag", id_frawor, co_conten, co_pagina, hamodas, ls_conpar, ((Usuario) ((HttpServletRequest) asyncContext.getRequest()).getSession().getAttribute("US")).getCo_usuari(), 1);

                for (Map.Entry<Short, Object> entry : compags.entrySet()) {


                    JsonResponse jsonResponsex = Util.gson_typeA.fromJson("" + entry.getValue(), JsonResponse.class);
                    System.out.println("jsonResponsex.getResult() = " + jsonResponsex.getResult());

                    Type listType = new TypeToken<ArrayList<ComboDTO>>() {
                    }.getType();
                    List<ComboDTO> lstcbx = Util.gson_typeA.fromJson("" + jsonResponsex.getResult(), listType);

                    System.out.println("entry(K,V) = " + entry.getKey() + "," + entry.getValue() + "===>" + lstcbx);
                    map_hamodas.put("" + entry.getKey(), lstcbx);
                }

//                for (int i = 0; i < hamodas.length; i++) {
//                    String hamoda = hamodas[i];
//                    List<ComboDTO> comboDTOS = new ArrayList<>();
//                    Frawor4DAO dao = new Frawor4DAO(WFCoreListener.dataSourceService.getManager("wfacr").getNativeSession());
//                    comboDTOS = dao.getCombo(co_pagina, id_frawor, co_conten, Util.toShort(hamoda, (short) -1));
//                    dao.close();
//
//                    map_hamodas.put(hamoda, comboDTOS);
//                }

                jsonResponse.setAditional(map_hamodas);
            }

            String urpta = Util.toJSON2(jsonResponse);

//            System.out.println("[" + co_pagina + "]valpag? = " + urpta);

            out.write(urpta);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //complete the processing
        asyncContext.complete();
    }

}
