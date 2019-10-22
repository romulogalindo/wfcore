package com.acceso.wfcore.kernel;

import com.acceso.wfcore.utils.*;
import com.acceso.wfweb.daos.Frawor4DAO;
import com.acceso.wfweb.dtos.ComboDTO;
import com.acceso.wfweb.units.Contenedor;
import com.acceso.wfweb.units.Usuario;
import com.acceso.wfweb.utils.JsonResponse;
import com.acceso.wfweb.utils.RequestManager;

import java.io.PrintWriter;
import java.util.*;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;

public class AsyncValPag extends AsyncProcessor {

    public AsyncValPag(AsyncContext asyncCtx, int secs, int type) {
        super(asyncCtx, secs, type);
    }

    @Override
    public void run() {
        JsonResponse jsonResponse = JsonResponse.defultJsonResponseOK("");
        PrintWriter out = null;
        Integer co_conten = null;
        Integer co_pagina = null;

        try {
            out = this.asyncContext.getResponse().getWriter();
            co_conten = Util.toInt(asyncContext.getRequest().getParameter("co_conten"), -1);
            co_pagina = Util.toInt(asyncContext.getRequest().getParameter("co_pagina"), -1);
            //
            Integer co_pagreg = Util.toInt(asyncContext.getRequest().getParameter("co_pagreg"), -1);
            String va_pagreg = asyncContext.getRequest().getParameter("va_pagreg");
            String ls_allreg = asyncContext.getRequest().getParameter("ls_allreg");

            //---
            RequestManager requestManager = new RequestManager((HttpServletRequest) asyncContext.getRequest(), null);
            ls_allreg = requestManager.getParam("ls_allreg");
            Long id_frawor = Util.toLong(asyncContext.getRequest().getParameter("id_frawor"), -1);
            Usuario usuario = ((Usuario) ((HttpServletRequest) asyncContext.getRequest()).getSession().getAttribute("US"));
            String ls_conpar = ((Contenedor) ((HttpServletRequest) asyncContext.getRequest()).getSession().getAttribute("CNT" + co_conten + ":" + id_frawor)).getLs_conpar();
            System.setProperty("nashorn.args", "--language=es6");
            ScriptContextExecutor script;

            String LOG = "[U" + usuario.getCo_usuari() + "][S" + usuario.getId_sesion() + "][F" + id_frawor + "][C" + co_conten + "][P" + co_pagina + "]";

            if (WFIOAPP.APP.getCacheService().getZeroDawnCache().getSpace(Values.CACHE_MAIN_PAGEJS).get(co_conten + "" + co_pagina) == null) {
                Frawor4DAO dao = new Frawor4DAO();
                String valpag2_js = "" + dao.getJS_Valpag(co_pagina).getScript();
                String propag2_js = "" + dao.getJS_Propag(co_pagina).getScript();
                String dinpag2_js = "" + dao.getJS_Dinpag(co_pagina).getScript();
                dao.close();
                Integer in_valpag2_js = valpag2_js.split("\r\n").length;
                Integer in_propag2_js = propag2_js.split("\r\n").length;
                Integer in_dinpag2_js = dinpag2_js.split("\r\n").length;

                String scriptpage = Util.getText(WFIOAPP.APP.PAGEJS)
                        .replace("USUARI_DATA_VALPAG", valpag2_js == null ? "" : valpag2_js)
                        .replace("USUARI_DATA_PROPAG", propag2_js == null ? "" : propag2_js)
                        .replace("USUARI_DATA_DINPAG", dinpag2_js == null ? "" : dinpag2_js);

                script = WFIOAPP.APP.getJavaScriptService().newContext(scriptpage);

                WFIOAPP.APP.getCacheService().getZeroDawnCache().getSpace(Values.CACHE_MAIN_PAGEJS).put(co_conten + "" + co_pagina, script);
                WFIOAPP.APP.getCacheService().getZeroDawnCache().getSpace(Values.CACHE_MAIN_PAGEJS).put(co_conten + "" + co_pagina + ":VALPAG", in_valpag2_js);
                WFIOAPP.APP.getCacheService().getZeroDawnCache().getSpace(Values.CACHE_MAIN_PAGEJS).put(co_conten + "" + co_pagina + ":PROPAG", in_propag2_js);
                WFIOAPP.APP.getCacheService().getZeroDawnCache().getSpace(Values.CACHE_MAIN_PAGEJS).put(co_conten + "" + co_pagina + ":DINPAG", in_dinpag2_js);
            } else {
                script = (ScriptContextExecutor) WFIOAPP.APP.getCacheService().getZeroDawnCache().getSpace(Values.CACHE_MAIN_PAGEJS).get(co_conten + "" + co_pagina);
            }

            /*EXE VALPAGJS*/
            ValpagJson valpagJson = null;
            Object valpagx = null;
            if (type == 1) {
                valpagx = script.doValpag64(id_frawor, co_conten, co_pagina, ls_conpar, usuario.getId_sesion(), usuario, 1);
                jsonResponse.setResult(valpagx);
                jsonResponse.setFnpost(script.dopvpj("GET_DO_POST_LOAD_DATA"));
            } else {
                valpagJson = (ValpagJson) script.doDinpag64(id_frawor, co_conten, co_pagina, co_pagreg, va_pagreg, ls_conpar, ls_allreg, ls_allreg, usuario.getId_sesion(), usuario, 1);
                jsonResponse.setResult(valpagJson);
            }
            out.write(Util.toJSON2(jsonResponse));
        } catch (Exception ep) {
//            System.out.println("[[ERROR!--------------------------------------------//////?????]]?>>" + ep.getMessage());
            ErrorMessage em = Util.getError(ep, 90);
            jsonResponse.setError(em);
            jsonResponse.setStatus(JsonResponse.ERROR);
            out.write(Util.toJSON2(jsonResponse));

            if (WFIOAPP.APP.THROWS_EXCEPTION) {
                ep.printStackTrace();
            }
        }

        out.flush();
        out.close();
        asyncContext.complete();
    }

}
