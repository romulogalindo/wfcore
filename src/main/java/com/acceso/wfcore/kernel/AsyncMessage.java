package com.acceso.wfcore.kernel;

import com.acceso.wfcore.utils.Util;
import com.acceso.wfcore.utils.Values;
import com.acceso.wfweb.daos.Frawor4DAO;
import com.acceso.wfweb.utils.JsonResponse;
import com.acceso.wfweb.utils.RequestManager;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.Map;

public class AsyncMessage extends AsyncProcessor {

    public AsyncMessage(AsyncContext asyncCtx, int secs, int type) {
        super(asyncCtx, secs, type);
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
                String ls_regist = "{ ";

                Frawor4DAO dao = new Frawor4DAO();
//                Frawor4DAO dao2 = new Frawor4DAO(WFIOAPP.APP.dataSourceService.getManager("wfacr").getNativeSession());

                dao.deletePagreg(id_frawor, co_pagina, true);
//                dao2.deletePagreg(id_frawor, co_pagina, false);

                for (Map.Entry<Integer, String> pagreg : requestManager.getPagregs().entrySet()) {
                    dao.insertPagreg(id_frawor, co_pagina, pagreg.getKey().shortValue(), (short) 1, pagreg.getValue(), true);
//                    dao2.insertPagreg(id_frawor, co_pagina, pagreg.getKey().shortValue(), (short) 1, pagreg.getValue(), false);
                    ls_regist = "\"co_regist_" + pagreg.getKey() + "\":\"" + pagreg.getValue() + "\",";
                }
                ls_regist = ls_regist.substring(0, ls_regist.length() - 1) + "}";

                dao.close();
//                dao2.close();

                //ejecutar propag
                String propag_js = (String) WFIOAPP.APP.getCacheService().getZeroDawnCache().getSpace(Values.CACHE_MAIN_PROPAGJS).get(co_pagina);
                if (propag_js == null) {
//                    Frawor4DAO dao3 = new Frawor4DAO();
//                    propag_js = dao3.getPaginaDTO(co_pagina).getJs_valpag();
//                    dao3.close();

                    if (propag_js == null) {
                        propag_js = "PROPAGJS = DATA.SQL('wfacr', 'select true as pfpropag from frawor2.pfpropag(\'+CO_PAGINA+\', \'+ID_FRAWOR+\', \'+CO_CONTEN+\', cast(\'+CO_PAGBOT+\' as smallint))');";
                    }

                    WFIOAPP.APP.getCacheService().getZeroDawnCache().getSpace(Values.CACHE_MAIN_PROPAGJS).put(co_pagina, propag_js);
                }

                propag_js = Util.getText(WFIOAPP.APP.PROPAGJS).replace("USUARI_DATA_JS_TEXT", propag_js);

                Object object = il_proces ? WFIOAPP.APP.getJavaScriptService().doPropag64(propag_js, "do_propag", co_pagina, id_frawor, co_conten, co_botone, ls_regist, requestManager.getUser().getCo_usuari()) : "{}";

                out.write(object.toString());

            } catch (Exception ep) {
                out.write(Util.toJSON(JsonResponse.defultJsonResponseERROR(Util.getError(ep))));
            }
        }
        //complete the processing
        asyncContext.complete();
    }

}
