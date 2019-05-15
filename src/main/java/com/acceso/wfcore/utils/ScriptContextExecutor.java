package com.acceso.wfcore.utils;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.HashMap;
import java.util.Map;

public class ScriptContextExecutor {

    ScriptEngine engine_nashornjs;

    public ScriptContextExecutor(ScriptEngineManager manager) {
        this.engine_nashornjs = manager.getEngineByName("nashorn");
    }

    public ScriptContextExecutor(ScriptEngineManager manager, String JSText) {
        this.engine_nashornjs = manager.getEngineByName("nashorn");
        try {
            engine_nashornjs.eval(JSText);
        } catch (Exception ep) {
        }
    }

    //    function exec valpag
    public Object doValpag64(long id_frawor, int co_conten, int co_pagina, String ls_conpar, long id_sesion, long co_usuari, int id_fraant) throws Exception {
        Invocable inv = (Invocable) engine_nashornjs;
        return inv.invokeFunction("do_valpag", id_frawor, co_conten, co_pagina, ls_conpar, id_sesion, co_usuari, id_fraant);
    }

    //    function exec valpag
    public Map<Short, Object> doCompag64(long id_frawor, int co_conten, int co_pagina, String[] co_pagregs, String ls_conpar, long id_sesion, long co_usuari, int id_fraant) throws Exception {
        Map<Short, Object> result = new HashMap<>();
        Invocable inv = (Invocable) engine_nashornjs;
        for (String co_pagreg : co_pagregs) {
            Object rslt = inv.invokeFunction("do_compag", id_frawor, co_conten, co_pagina, co_pagreg, ls_conpar, id_sesion, co_usuari, id_fraant);
            result.put(Util.toShort(co_pagreg, (short) -1), rslt);
        }

        return result;
    }

    public Object doPropag64(int co_pagina, long id_frawor, int co_conten, short co_pagbot, String ls_regist, long id_sesion, long co_usuari) throws Exception {
        Invocable inv = (Invocable) engine_nashornjs;
        return inv.invokeFunction("do_propag", id_frawor, co_conten, co_pagina, co_pagbot, ls_regist, id_sesion, co_usuari);
    }

    public String dopvpj(String JSFunction) {
        String result = "";
        try {
            Invocable inv = (Invocable) engine_nashornjs;
            result = "" + inv.invokeFunction(JSFunction);
        } catch (Exception ep) {
            System.out.println("(valpag[" + "])ep = " + ep.getMessage());
            ep.printStackTrace();
        }

//        System.out.println("[JavaScriptService:dopvpj] ====>" + result);
        return result;
    }
}
