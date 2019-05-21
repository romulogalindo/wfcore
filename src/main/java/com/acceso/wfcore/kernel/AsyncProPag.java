package com.acceso.wfcore.kernel;

import com.acceso.wfcore.listerners.WFCoreListener;
import com.acceso.wfcore.utils.ErrorMessage;
import com.acceso.wfcore.utils.ScriptContextExecutor;
import com.acceso.wfcore.utils.Util;
import com.acceso.wfcore.utils.Values;
import com.acceso.wfweb.daos.Frawor4DAO;
import com.acceso.wfweb.units.Usuario;
import com.acceso.wfweb.utils.JsonResponse;
import com.acceso.wfweb.utils.JsonResponseP;
import com.acceso.wfweb.utils.RequestManager;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.Map;


public class AsyncProPag extends AsyncProcessor {

    public AsyncProPag(AsyncContext asyncCtx, int secs) {
        super(asyncCtx, secs);
    }

    @Override
    public void run() {
        PrintWriter out = null;

//        try {
//            out = this.asyncContext.getResponse().getWriter();
//        } catch (Exception ep) {
//        }

//        if (out != null) {
        try {
            out = this.asyncContext.getResponse().getWriter();
            RequestManager requestManager = new RequestManager((HttpServletRequest) asyncContext.getRequest(), null);

            int co_conten = Util.toInt(requestManager.getParam("co_conten"), -1);
            int co_pagina = Util.toInt(requestManager.getParam("co_pagina"), -1);
            long id_frawor = Util.toLong(requestManager.getParam("id_frawor"), -1);
            short co_botone = Util.toShort(requestManager.getParam("co_botone"), (short) -1);
            boolean il_proces = Util.toBoolean(requestManager.getParam("il_proces"), false);
            Usuario usuario = requestManager.getUser();
            String ls_regist = "{ ";

            Frawor4DAO dao = new Frawor4DAO();
            Frawor4DAO dao2 = new Frawor4DAO(WFCoreListener.dataSourceService.getManager("wfacr").getNativeSession());

            dao.deletePagreg(id_frawor, co_pagina, true);
            dao2.deletePagreg(id_frawor, co_pagina, false);

            for (Map.Entry<Integer, String> pagreg : requestManager.getPagregs().entrySet()) {
                dao.insertPagreg(id_frawor, co_pagina, pagreg.getKey().shortValue(), (short) 1, pagreg.getValue(), true);
                dao2.insertPagreg(id_frawor, co_pagina, pagreg.getKey().shortValue(), (short) 1, pagreg.getValue(), false);
                ls_regist += "\"co_regist_" + pagreg.getKey() + "\":\"" + pagreg.getValue() + "\",";
            }
            ls_regist = ls_regist.substring(0, ls_regist.length() - 1) + "}";
            System.out.println("ls_regist = " + ls_regist);
            dao.close();
            dao2.close();

            //new PROPAG
            ScriptContextExecutor script;

            if (WFCoreListener.APP.getCacheService().getZeroDawnCache().getSpace(Values.CACHE_MAIN_PAGEJS).get(co_conten + "" + co_pagina) == null) {
                dao = new Frawor4DAO();
                String valpag2_js = dao.getJS_Valpag(co_pagina).getScript();
                String propag2_js = dao.getJS_Propag(co_pagina).getScript();
                String compag2_js = dao.getJS_Compag(co_pagina).getScript();
                dao.close();

                String scriptpage = Util.getText(WFCoreListener.APP.PAGEJS)
                        .replace("USUARI_DATA_VALPAG", valpag2_js == null ? "" : valpag2_js)
                        .replace("USUARI_DATA_PROPAG", propag2_js == null ? "" : propag2_js)
                        .replace("USUARI_DATA_COMPAG", compag2_js == null ? "" : compag2_js);

                script = WFCoreListener.APP.getJavaScriptService().newContext(scriptpage);

                WFCoreListener.APP.getCacheService().getZeroDawnCache().getSpace(Values.CACHE_MAIN_PAGEJS).put(co_conten + "" + co_pagina, script);
            } else {
                script = (ScriptContextExecutor) WFCoreListener.APP.getCacheService().getZeroDawnCache().getSpace(Values.CACHE_MAIN_PAGEJS).get(co_conten + "" + co_pagina);
            }

            //ejecutar propag
//                String propag_js = (String) WFCoreListener.APP.getCacheService().getZeroDawnCache().getSpace(Values.CACHE_MAIN_PROPAGJS).get(co_pagina);
//                if (propag_js == null) {
//                    Frawor4DAO dao3 = new Frawor4DAO();
//                    propag_js = dao3.getJS_Propag(co_pagina).getScript();
//                    dao3.close();
//
//                    if (propag_js == null) {
//                        propag_js = "PROPAGJS = DATA.SQL('wfacr', 'select true as pfpropag from frawor2.pfpropag(\'+CO_PAGINA+\', \'+ID_FRAWOR+\', \'+CO_CONTEN+\', cast(\'+CO_PAGBOT+\' as smallint))');";
//                    }
//
//                    WFCoreListener.APP.getCacheService().getZeroDawnCache().getSpace(Values.CACHE_MAIN_PROPAGJS).put(co_pagina, propag_js);
//                }
//
//                propag_js = Util.getText(WFCoreListener.APP.PROPAGJS).replace("USUARI_DATA_JS_TEXT", propag_js);
//
//                Object object = il_proces ? WFCoreListener.APP.getJavaScriptService().doPropag64(propag_js, "do_propag", co_pagina, id_frawor, co_conten, co_botone, ls_regist, requestManager.getUser().getCo_usuari()) : "{}";

            Object object = il_proces ? script.doPropag64(co_pagina, id_frawor, co_conten, co_botone, null, ls_regist, usuario.getId_sesion(), usuario.getCo_usuari()) : "{}";
            System.out.println(">>object = " + object);
            System.out.println(">>object = " + object.getClass());
//                jdk.nashorn.api.scripting.ScriptObjectMirror a; a.
            if (object instanceof String) {
                if (object.toString().contains("X5964ERQ17")) {
                    object = object.toString().replace("X5964ERQ17", "");
                    out.write(Util.toJSON(JsonResponse.defultJsonResponseERROR(Util.gson_typeA.fromJson(object.toString(), ErrorMessage.class))));
                } else {
                    out.write(Util.toJSON(JsonResponseP.defultJsonResponseOK("OK")));
                }

            } else if (object instanceof JsonResponseP) {
                out.write(Util.toJSON(object));
            } else {
//                out.write(Util.toJSON(JsonResponse.defultJsonResponseOK("OK")));
                out.write(Util.toJSON(JsonResponseP.defultJsonResponseOK("OK")));
            }

//                if (object.toString().contains("X5964ERQ17")) {
//                    object = object.toString().replace("X5964ERQ17", "");
//                    out.write(Util.toJSON(JsonResponse.defultJsonResponseERROR(Util.gson_typeA.fromJson(object.toString(), ErrorMessage.class))));
//                } else if (object.toString().contains("X5964IUP17")) {
//                    object = object.toString().replace("X5964IUP17", "");
////                    object = Util.toJSON(JsonResponse.defultJsonResponseOK("X5964IUP17"));
//                    object = Util.toJSON(JsonResponse.defultJsonResponseOK("X5964IUP17")).replace("X5964IUP17", object.toString().replace("\\", ""));
//                    out.write(object + "");
//                } else {
//                    out.write(Util.toJSON(JsonResponse.defultJsonResponseOK("OK")));
//                }

        } catch (Exception ep) {
            out.write(Util.toJSON(JsonResponse.defultJsonResponseERROR(Util.getError(ep))));
            ep.printStackTrace();
        }
//        }
        //complete the processing
        out.flush();
        out.close();
        asyncContext.complete();
    }

}
