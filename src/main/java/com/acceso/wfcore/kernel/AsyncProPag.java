package com.acceso.wfcore.kernel;

import com.acceso.wfcore.utils.ErrorMessage;
import com.acceso.wfcore.utils.ScriptContextExecutor;
import com.acceso.wfcore.utils.Util;
import com.acceso.wfcore.utils.Values;
import com.acceso.wfweb.daos.Frawor4DAO;
import com.acceso.wfweb.units.Contenedor;
import com.acceso.wfweb.units.Usuario;
import com.acceso.wfweb.utils.JsonResponse;
import com.acceso.wfweb.utils.JsonResponseP;
import com.acceso.wfweb.utils.RequestManager;
import com.google.gson.JsonObject;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.Map;

public class AsyncProPag extends AsyncProcessor {

    public AsyncProPag(AsyncContext asyncCtx, int secs, int type) {
        super(asyncCtx, secs, type);
    }

    @Override
    public void run() {
        PrintWriter out = null;
        int co_conten = 0;
        int co_pagina = 0;

        try {
            out = this.asyncContext.getResponse().getWriter();
            RequestManager requestManager = new RequestManager((HttpServletRequest) asyncContext.getRequest(), null);

            co_conten = Util.toInt(requestManager.getParam("co_conten"), -1);
            co_pagina = Util.toInt(requestManager.getParam("co_pagina"), -1);
            long id_frawor = Util.toLong(requestManager.getParam("id_frawor"), -1);
            short co_botone = Util.toShort(requestManager.getParam("co_botone"), (short) -1);
            boolean il_proces = Util.toBoolean(requestManager.getParam("il_proces"), false);
            String ls_allreg = requestManager.getParam("ls_allreg");

            String ls_conpar = ((Contenedor) ((HttpServletRequest) asyncContext.getRequest()).getSession().getAttribute("CNT" + co_conten + ":" + id_frawor)).getLs_conpar();

//            requestManager.getConpars().entrySet().stream().forEach(m -> {
//                System.out.println("key = " + m.getKey() + ", value = " + m.getValue());
//            });

            Usuario usuario = requestManager.getUser();
            String ls_regist = "";
            Frawor4DAO dao;

            if (type == 1) {
                dao = new Frawor4DAO();

                dao.deletePagreg(id_frawor, co_pagina, true);

                JsonObject gson = new JsonObject();

                for (Map.Entry<Integer, String> pagreg : requestManager.getPagregs().entrySet()) {
                    dao.insertPagreg(id_frawor, co_pagina, pagreg.getKey().shortValue(), (short) 1, pagreg.getValue(), true);
                    gson.addProperty("co_regist_" + pagreg.getKey(), pagreg.getValue() == null ? "" : new String(pagreg.getValue().getBytes("ISO-8859-1"), "UTF-8"));
                }

                ls_regist = "" + gson;
                dao.close();

            }

            //new PROPAG
            ScriptContextExecutor script;

            if (WFIOAPP.APP.getCacheService().getZeroDawnCache().getSpace(Values.CACHE_MAIN_PAGEJS).get(co_conten + "" + co_pagina) == null) {
                dao = new Frawor4DAO();
                String valpag2_js = dao.getJS_Valpag(co_pagina).getScript();
                String propag2_js = dao.getJS_Propag(co_pagina).getScript();
                String compag2_js = dao.getJS_Compag(co_pagina).getScript();
                dao.close();

                String scriptpage = Util.getText(WFIOAPP.APP.PAGEJS)
                        .replace("USUARI_DATA_VALPAG", valpag2_js == null ? "" : valpag2_js)
                        .replace("USUARI_DATA_PROPAG", propag2_js == null ? "" : propag2_js)
                        .replace("USUARI_DATA_COMPAG", compag2_js == null ? "" : compag2_js);

                script = WFIOAPP.APP.getJavaScriptService().newContext(scriptpage);

                WFIOAPP.APP.getCacheService().getZeroDawnCache().getSpace(Values.CACHE_MAIN_PAGEJS).put(co_conten + "" + co_pagina, script);
            } else {
                script = (ScriptContextExecutor) WFIOAPP.APP.getCacheService().getZeroDawnCache().getSpace(Values.CACHE_MAIN_PAGEJS).get(co_conten + "" + co_pagina);
            }

            //ejecutar propag
//            Object object = il_proces ? script.doPropag64(type, co_pagina, id_frawor, co_conten, co_botone, ls_conpar, type == 1 ? ls_regist : ls_allreg, usuario.getId_sesion(), usuario.getCo_usuari()) : "{}";
            Object object = il_proces ? script.doPropag64(type, co_pagina, id_frawor, co_conten, co_botone, ls_conpar, type == 1 ? ls_regist : ls_allreg, usuario.getId_sesion(), usuario) : "{}";

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
                out.write(Util.toJSON(JsonResponseP.defultJsonResponseOK("OK")));
            }

        } catch (Exception ep) {
            Integer indice_valpag = (Integer) WFIOAPP.APP.getCacheService().getZeroDawnCache().getSpace(Values.CACHE_MAIN_PAGEJS).get(co_conten + "" + co_pagina + ":VALPAG");
            System.out.println("indice_valpag = " + indice_valpag);
            System.out.println("indice_valpag2 = " + ((89 + indice_valpag + 18)));

            out.write(Util.toJSON(JsonResponse.defultJsonResponseERROR(Util.getError(ep, (89 + indice_valpag + 18)))));
            ep.printStackTrace();
        }

        out.flush();
        out.close();
        asyncContext.complete();
    }

}
