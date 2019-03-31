package com.acceso.wfcore.services;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class MessageService extends Service {

    ScriptEngineManager manager;
    //    ScriptEngine engine_nativejs;
    ScriptEngine engine_nashornjs;
//    ScriptEngine engine_graalvmjs;

    public MessageService(String serviceName) {
        super(serviceName);

    }

    @Override
    public void start() {
        String script_Base = "(function() { return this; })";

//        try {
//            Object fn = engine_nashornjs.eval(script_Base);
//            Invocable inv = (Invocable) engine_nashornjs;
//            Object result = inv.invokeMethod(fn, "call", fn);
//            System.out.println("(ENGINE2)result = " + result);
//        } catch (Exception ep) {
//            System.out.println("ENGINE 2 (error)");
//            ep.printStackTrace();
//        }
    }

    @Override
    public void stop() {
        engine_nashornjs = null;
    }
}
