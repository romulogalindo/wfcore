package com.acceso.wfcore.services;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

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
            System.out.println("(ENGINE2)result = " + result);
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
    public Object doValpag64(String JSText, String JSFunction, long id_frawor, int co_conten, int co_pagina, String ls_conpar, long co_usuari, int id_fraant) {
        Object result = null;
        try {
            engine_nashornjs.eval(JSText);
            Invocable inv = (Invocable) engine_nashornjs;

            result = inv.invokeFunction(JSFunction, id_frawor, co_conten, co_pagina, ls_conpar, co_usuari, id_fraant);
            System.out.println("(valpag[" + co_conten + ":" + co_pagina + "])result = " + result);
        } catch (Exception ep) {
            System.out.println("(valpag[" + co_conten + ":" + co_pagina + "])ep = " + ep.getMessage());
            ep.printStackTrace();
        }

        return result;
    }

    public Object doPropag64(String JSText, String JSFunction, int co_pagina, long id_frawor, int co_conten, short co_pagbot, String ls_regist, long co_usuari) throws Exception {
        engine_nashornjs.eval(JSText);
        Invocable inv = (Invocable) engine_nashornjs;
        return inv.invokeFunction(JSFunction, id_frawor, co_conten, co_pagina, co_pagbot, ls_regist, co_usuari);
    }
}
