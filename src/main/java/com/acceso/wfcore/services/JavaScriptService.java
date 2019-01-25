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

    //    public Object doJS64(String JSText, String JSFunction) {
    public Object doJS64(String JSText, String JSFunction, long id_frawor, int co_conten, int co_pagina) {
        Object result = null;
        try {
//            Object fn = engine_nashornjs.eval(JSText);
            engine_nashornjs.eval(JSText);
            Invocable inv = (Invocable) engine_nashornjs;
//            Invocable inv = (Invocable) engine_nashornjs;
            result = inv.invokeFunction(JSFunction, id_frawor, co_conten, co_pagina);
            System.out.println("(ENGINE2)result = " + result);
        } catch (Exception ep) {
            System.out.println("ENGINE 2 (error)");
            ep.printStackTrace();
        }
        return result;
    }
}
