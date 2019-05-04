package com.acceso.wfcore.services;

import com.acceso.wfcore.utils.Util;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JavaScriptService extends Service {

    ScriptEngineManager manager;
    //    ScriptEngine engine_nativejs;
    ScriptEngine engine_nashornjs;
//    ScriptEngine engine_graalvmjs;

    public JavaScriptService(String serviceName) {
        super(serviceName);

        //Levantar el motor de JS
        manager = new ScriptEngineManager();
//        engine_nativejs = manager.getEngineByName("javascript");
        engine_nashornjs = manager.getEngineByName("nashorn");
//        engine_graalvmjs = manager.getEngineByName("graal.js");
//        List<ScriptEngineFactory> scriptEngineFactories = manager.getEngineFactories();
//        for (ScriptEngineFactory scriptEngineFactory : scriptEngineFactories) {
//            System.out.println("scriptEngineFactory = " + scriptEngineFactory);
//        }
    }

    @Override
    public void start() {
        String script_Base = "(function() { return this; })";

        try {
            Object fn = engine_nashornjs.eval(script_Base);
            Invocable inv = (Invocable) engine_nashornjs;
            Object result = inv.invokeMethod(fn, "call", fn);
//            System.out.println("(ENGINE2)result = " + result);
        } catch (Exception ep) {
            System.out.println("ENGINE 2 (error)");
            ep.printStackTrace();
        }
    }

    @Override
    public void stop() {
        engine_nashornjs = null;
    }

    //    function exec valpag
    public Object doValpag64(String JSText, String JSFunction, long id_frawor, int co_conten, int co_pagina, String ls_conpar, long id_sesion, long co_usuari, int id_fraant) throws Exception {
        Object result = null;
//        try {
        engine_nashornjs.eval(JSText);
        Invocable inv = (Invocable) engine_nashornjs;

        result = inv.invokeFunction(JSFunction, id_frawor, co_conten, co_pagina, ls_conpar, id_sesion, co_usuari, id_fraant);
//            System.out.println("(valpag[" + co_conten + ":" + co_pagina + "])result = " + result);
        return result;
//        } catch (Exception ep) {
//            System.out.println("(doValpag64[" + co_conten + ":" + co_pagina + "])ep = " + ep.getMessage());
//            ep.printStackTrace();
////            ep.printStackTrace();
//            throw ep;
//        }


    }


    //    function exec valpag
    public Map<Short, Object> doCompag64(String JSText, String JSFunction, long id_frawor, int co_conten, int co_pagina, String[] co_pagregs, String ls_conpar, long co_usuari, int id_fraant) throws Exception {
//        Object result = null;
        Map<Short, Object> result = new HashMap<>();

//        try {
        engine_nashornjs.eval(JSText);
        Invocable inv = (Invocable) engine_nashornjs;
        for (String co_pagreg : co_pagregs) {
            System.out.println("JSFunction = " + JSFunction);
            Object rslt = inv.invokeFunction(JSFunction, id_frawor, co_conten, co_pagina, co_pagreg, ls_conpar, co_usuari, id_fraant);
            System.out.println("[" + co_pagreg + "]rslt = " + rslt);
            result.put(Util.toShort(co_pagreg, (short) -1), rslt);
        }

        System.out.println("(compag[" + co_conten + ":" + co_pagina + ":" + co_pagregs + "])result = " + result);
//        } catch (Exception ep) {
//            System.out.println("(compag[" + co_conten + ":" + co_pagina + "])ep = " + ep.getMessage());
//            ep.printStackTrace();
//        }

        return result;
    }

    public Object doPropag64(String JSText, String JSFunction, int co_pagina, long id_frawor, int co_conten, short co_pagbot, String ls_regist, long co_usuari) throws Exception {
        engine_nashornjs.eval(JSText);
        Invocable inv = (Invocable) engine_nashornjs;
        return inv.invokeFunction(JSFunction, id_frawor, co_conten, co_pagina, co_pagbot, ls_regist, co_usuari);
    }

    public Object dopvpj(String JSFunction) {
//        engine_nashornjs.eval(JSText);
//        Invocable inv = (Invocable) engine_nashornjs;
//        return inv.invokeFunction(JSFunction);

        Object result = null;
        try {
//            engine_nashornjs.eval(JSText);
            Invocable inv = (Invocable) engine_nashornjs;

            result = inv.invokeFunction(JSFunction);
//            System.out.println("(valpag[" + co_conten + ":" + co_pagina + "])result = " + result);
        } catch (Exception ep) {
            System.out.println("(valpag[" + "])ep = " + ep.getMessage());
            ep.printStackTrace();
        }

        return result;
    }
}
