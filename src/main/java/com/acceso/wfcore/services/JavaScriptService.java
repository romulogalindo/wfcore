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

        /*ENGINE 1*/
//        try {
//            Object fn = engine_nativejs.eval(script_Base);
//            Invocable inv = (Invocable) engine_nativejs;
//            Object result = inv.invokeMethod(fn, "call", fn);
//            System.out.println("(ENGINE1)result = " + result);
//        } catch (Exception ep) {
//            System.out.println("ENGINE 1 (error)");
//            ep.printStackTrace();
//        }

        /*ENGINE 2*/
        try {
            Object fn = engine_nashornjs.eval(script_Base);
            Invocable inv = (Invocable) engine_nashornjs;
            Object result = inv.invokeMethod(fn, "call", fn);
            System.out.println("(ENGINE2)result = " + result);
        } catch (Exception ep) {
            System.out.println("ENGINE 2 (error)");
            ep.printStackTrace();
        }

        /*ENGINE 3*/
//        org.graalvm.polyglot.Engine.
//        Context context = Context.create();
//        Value array = context.eval("js", "[1,2,42,4]");
//        int result2 = array.getArrayElement(2).asInt();

//        try {
//            Object fn = engine_graalvmjs.eval(script_Base);
//            Invocable inv = (Invocable) engine_graalvmjs;
//            Object result = inv.invokeMethod(fn, "call", fn);
//            System.out.println("(ENGINE3)result = " + result);
//        } catch (Exception ep) {
//            System.out.println("ENGINE 3 (error)");
//            ep.printStackTrace();
//        }
    }

    @Override
    public void stop() {
        engine_nashornjs = null;
    }
}
