package com.acceso.wfcore.utils;

import com.acceso.wfweb.units.Usuario;

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

    public Object doValpag64(long id_frawor, int co_conten, int co_pagina, String ls_conpar, long id_sesion, Usuario usuari, int id_fraant) throws Exception {
        Invocable inv = (Invocable) engine_nashornjs;
        return inv.invokeFunction("do_valpag", id_frawor, co_conten, co_pagina, ls_conpar, id_sesion, usuari, id_fraant);
    }

    public Object doDinpag64(long id_frawor, int co_conten, int co_pagina, int co_pagreg, String va_pagreg, String ls_conpar, String ls_regist, String ls_allreg, long id_sesion, Usuario usuari, int id_fraant) throws Exception {
        Invocable inv = (Invocable) engine_nashornjs;
        return inv.invokeFunction("do_dinpag", id_frawor, co_conten, co_pagina, co_pagreg, va_pagreg, ls_conpar, ls_regist, ls_allreg, id_sesion, usuari, id_fraant);
    }

    //    function exec valpag
    public Map<Short, Object> doCompag64(long id_frawor, int co_conten, int co_pagina, String[] co_pagregs, String ls_conpar, long id_sesion, Usuario usuari, int id_fraant) throws Exception {
        Map<Short, Object> result = new HashMap<>();
        Invocable inv = (Invocable) engine_nashornjs;
        for (String co_pagreg : co_pagregs) {
            Object rslt = inv.invokeFunction("do_compag", id_frawor, co_conten, co_pagina, co_pagreg, ls_conpar, id_sesion, usuari, id_fraant);
            result.put(Util.toShort(co_pagreg, (short) -1), rslt);
        }

        return result;
    }

    public Object doPropag64(int type, int co_pagina, long id_frawor, int co_conten, short co_pagbot, String ls_conpar, String ls_regist, long id_sesion, Usuario usuari) throws Exception {
        Invocable inv = (Invocable) engine_nashornjs;
        if (type == 1) {
            return inv.invokeFunction("do_propag", id_frawor, co_conten, co_pagina, co_pagbot, ls_conpar, ls_regist, id_sesion, usuari);
        } else {
            return inv.invokeFunction("do_propagg", id_frawor, co_conten, co_pagina, co_pagbot, ls_conpar, ls_regist, id_sesion, usuari);
        }
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
