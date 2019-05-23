package com.acceso.wfcore.kernel;

import com.acceso.wfcore.listerners.WFCoreListener;
import com.acceso.wfcore.utils.*;
import com.acceso.wfweb.daos.Frawor4DAO;
import com.acceso.wfweb.dtos.ComboDTO;
import com.acceso.wfweb.units.Contenedor;
import com.acceso.wfweb.units.Usuario;
import com.acceso.wfweb.utils.JsonResponse;
import com.acceso.wfweb.utils.JsonResponseC;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        try {
            out = this.asyncContext.getResponse().getWriter();

            Integer co_conten = Util.toInt(asyncContext.getRequest().getParameter("co_conten"), -1);
            Integer co_pagina = Util.toInt(asyncContext.getRequest().getParameter("co_pagina"), -1);
            Long id_frawor = Util.toLong(asyncContext.getRequest().getParameter("id_frawor"), -1);
            Usuario usuario = ((Usuario) ((HttpServletRequest) asyncContext.getRequest()).getSession().getAttribute("US"));
            String ls_hamoda = asyncContext.getRequest().getParameter("ls_hamoda");
            String ls_conpar = ((Contenedor) ((HttpServletRequest) asyncContext.getRequest()).getSession().getAttribute("CNT" + co_conten + ":" + id_frawor)).getLs_conpar();
            ScriptContextExecutor script;

            String LOG = "[U" + usuario.getCo_usuari() + "][S" + usuario.getId_sesion() + "][F" + id_frawor + "][C" + co_conten + "][P" + co_pagina + "]";

            if (WFCoreListener.APP.getCacheService().getZeroDawnCache().getSpace(Values.CACHE_MAIN_PAGEJS).get(co_conten + "" + co_pagina) == null) {
                Frawor4DAO dao = new Frawor4DAO();
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

            /*EXE VALPAGJS*/
            ValpagJson valpagJson = (ValpagJson) script.doValpag64(id_frawor, co_conten, co_pagina, ls_conpar, usuario.getId_sesion(), usuario.getCo_usuari(), 1);
            jsonResponse.setResult(valpagJson);
            jsonResponse.setFnpost(script.dopvpj("GET_DO_POST_LOAD_DATA"));

            if (valpagJson != null && (valpagJson.getRows() != null && !valpagJson.getRows().isEmpty()) && ls_hamoda.length() > 0) {
                HashMap<String, Object> map_hamodas = new HashMap<>();
                Map<Short, Object> compags = script.doCompag64(id_frawor, co_conten, co_pagina, ls_hamoda.split(","), ls_conpar, usuario.getId_sesion(), usuario.getCo_usuari(), 1);

                compags
                        .entrySet()
                        .stream()
                        .filter(o -> o.getValue() != null && ((JsonResponse) o.getValue()).getStatus().contentEquals("OK"))
                        .forEach(
                                entry -> {
                                    List<ComboDTO> combo = new ArrayList<>();
                                    for (Object rss : (List) ((JsonResponse) entry.getValue()).getResult()) {
                                        if (rss != null) {
                                            java.util.HashMap hashMap = (HashMap) rss;
                                            combo.add(new ComboDTO("" + hashMap.get("co_compag"), "" + hashMap.get("no_compag")));
                                        }
                                    }
                                    map_hamodas.put("" + entry.getKey(), combo);
                                }
                        );
//                compags.entrySet().forEach((entry) -> {
//                    List<ComboDTO> combo = new ArrayList<>();
//                    if (entry.getValue() != null && ((JsonResponse) entry.getValue()).getStatus().contentEquals("OK")) {
//                        for (Object rss : (List) ((JsonResponse) entry.getValue()).getResult()) {
//                            if (rss != null) {
//                                java.util.HashMap hashMap = (HashMap) rss;
//                                combo.add(new ComboDTO("" + hashMap.get("co_compag"), "" + hashMap.get("no_compag")));
//                            }
//                        }
//                        map_hamodas.put("" + entry.getKey(), combo);
//                    }
//                });

                jsonResponse.setAditional(map_hamodas);
            }

            out.write(Util.toJSON2(jsonResponse));
        } catch (Exception ep) {
//            System.out.println("[[ERROR!--------------------------------------------//////?????]]?>>" + ep.getMessage());
            ErrorMessage em = Util.getError(ep);
            jsonResponse.setError(em);
            jsonResponse.setStatus(JsonResponse.ERROR);
            out.write(Util.toJSON2(jsonResponse));

            if (WFCoreListener.APP.THROWS_EXCEPTION) {
                ep.printStackTrace();
            }
        }

        out.flush();
        out.close();
        asyncContext.complete();
    }

}
//ejecutar valpag
//            System.out.println(LOG + " INI-- ");
//            if (WFCoreListener.APP.getCacheService().getZeroDawnCache().getSpace(Values.CACHE_MAIN_VALPAGJS).get(co_conten + "" + co_pagina) == null) {
//
//                Frawor4DAO dao = new Frawor4DAO();
//                valpag_js = dao.getJS_Valpag(co_pagina).getScript();
//                dao.close();
//
//                valpag_js = valpag_js == null ? "VALPAGJS = DATA.VALPAG_LEGACY('wfacr', 'select * from frawor2.pfvalpag(\'+CO_PAGINA+\', \'+ID_FRAWOR+\', \'+CO_CONTEN+\')');" : valpag_js;
//                valpag_js = valpag_js.replace("P989115793P", "PAG" + co_pagina);
//
//                WFCoreListener.APP.getCacheService().getZeroDawnCache().getSpace(Values.CACHE_MAIN_VALPAGJS).put(co_conten + "" + co_pagina, valpag_js);
//            } else {
//                valpag_js = (String) WFCoreListener.APP.getCacheService().getZeroDawnCache().getSpace(Values.CACHE_MAIN_VALPAGJS).get(co_conten + "" + co_pagina);
//            }
//
//            valpag_js = Util.getText(WFCoreListener.APP.VALPAGJS).replace("USUARI_DATA_JS_TEXT", valpag_js);
//            System.out.println(LOG + " VPJS(" + valpag_js.length() + ")>:" + valpag_js);
//
//            /*EXE VALPAGJS*/
//            ValpagJson valpagJson = (ValpagJson) WFCoreListener.APP.getJavaScriptService().doValpag64(valpag_js, "do_valpag", id_frawor, co_conten, co_pagina, ls_conpar, usuario.getId_sesion(), usuario.getCo_usuari(), 1);
//
//            jsonResponse.setResult(valpagJson);


/* COMPAGJS */
//                String compag_js = (String) WFCoreListener.APP.getCacheService().getZeroDawnCache().getSpace(Values.CACHE_MAIN_COMPAGJS).get(co_pagina);
//                if (compag_js == null || compag_js.contentEquals("null")) {
//
//                    Frawor4DAO dao = new Frawor4DAO();
//                    compag_js = dao.getJS_Compag(co_pagina).getScript();
//                    dao.close();
//
//                    if (compag_js == null) {
//                        compag_js = "VALPAGJS = DATA.COMPAG_LEGACY('wfacr', 'select * from frawor2.pfcompag(\'+CO_PAGINA+\', \'+ID_FRAWOR+\', \'+CO_CONTEN+\')');";
//                    }
//
//                    WFCoreListener.APP.getCacheService().getZeroDawnCache().getSpace(Values.CACHE_MAIN_COMPAGJS).put(co_pagina, compag_js);
//                }
//
//                compag_js = Util.getText(WFCoreListener.APP.COMPAGJS).replace("USUARI_DATA_JS_TEXT", compag_js);
//
//                Map<Short, Object> compags = WFCoreListener.APP.getJavaScriptService().doCompag64(compag_js, "do_compag", id_frawor, co_conten, co_pagina, ls_hamoda.split(","), ls_conpar, usuario.getId_sesion(), usuario.getCo_usuari(), 1);
//            String urpta = Util.toJSON2(jsonResponse);
//
//            System.out.println(LOG + " VR? = " + urpta);
