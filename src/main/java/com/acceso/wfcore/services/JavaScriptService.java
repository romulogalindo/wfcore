package com.acceso.wfcore.services;

import com.acceso.wfcore.utils.ScriptContextExecutor;
import com.acceso.wfcore.utils.Util;

import javax.script.*;
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

    public ScriptContextExecutor newContext(String JSTEXT) {
        ScriptContextExecutor scriptContextExecutor = new ScriptContextExecutor(manager, JSTEXT);
        return scriptContextExecutor;
    }

    //    function exec valpag
    public Object doValpag64(String JSText, String JSFunction, long id_frawor, int co_conten, int co_pagina, String ls_conpar, long id_sesion, long co_usuari, int id_fraant) throws Exception {

        Object result = null;
//        try {
        ScriptEngine me_engine_nashornjs = manager.getEngineByName("nashorn");
//        me_engine_nashornjs.eval(JSText, new SimpleScriptContext());
//        me_engine_nashornjs.eval(JSText, new SimpleBindings());
        me_engine_nashornjs.eval(JSText);
        Invocable inv = (Invocable) me_engine_nashornjs;

        result = inv.invokeFunction(JSFunction, id_frawor, co_conten, co_pagina, ls_conpar, id_sesion, co_usuari, id_fraant);
//            System.out.println("(valpag[" + co_conten + ":" + co_pagina + "])result = " + result);
        return result;

        //---------------------------------------------
//        // create an engine and a ScriptContext, but don't set it as default
//        ScriptContext scriptContext = new SimpleScriptContext();
//
//        // Set some value in the context
////        scriptContext.setAttribute("myString", "foo", ScriptContext.ENGINE_SCOPE);
//
//        // Evaluate script with custom context and get back a function
////        final String script = "function (c) { return myString.indexOf(c); }";
//        final CompiledScript compiledScript = ((Compilable) engine_nashornjs).compile(JSText);
//        final Object func = compiledScript.eval(scriptContext);
//        System.out.println("func = " + func);
//        // Invoked function should be able to see context it was evaluated with
////        final Object result = ((Invocable) engine_nashornjs).invokeMethod(func, "call", func, "o", null);
//        final Object result2 = ((Invocable) engine_nashornjs).invokeFunction(JSFunction, id_frawor, co_conten, co_pagina, ls_conpar, id_sesion, co_usuari, id_fraant);
////        assertTrue(((Number)result).intValue() == 1);
//        return result2;
    }


    //    function exec valpag
    public Map<Short, Object> doCompag64(String JSText, String JSFunction, long id_frawor, int co_conten, int co_pagina, String[] co_pagregs, String ls_conpar, long id_sesion, long co_usuari, int id_fraant) throws Exception {
//        Object result = null;
        Map<Short, Object> result = new HashMap<>();

//        try {
        engine_nashornjs.eval(JSText);
        Invocable inv = (Invocable) engine_nashornjs;
        for (String co_pagreg : co_pagregs) {
//            System.out.println("JSFunction = " + JSFunction);
            Object rslt = inv.invokeFunction(JSFunction, id_frawor, co_conten, co_pagina, co_pagreg, ls_conpar, id_sesion, co_usuari, id_fraant);
//            System.out.println("[" + co_pagreg + "]rslt = " + rslt);
            result.put(Util.toShort(co_pagreg, (short) -1), rslt);
        }

//        System.out.println("(compag[" + co_conten + ":" + co_pagina + ":" + co_pagregs + "])result = " + result);
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

    public String dopvpj(String JSFunction) {
//        engine_nashornjs.eval(JSText);
//        Invocable inv = (Invocable) engine_nashornjs;
//        return inv.invokeFunction(JSFunction);

        String result = "";
        try {
//            engine_nashornjs.eval(JSText);
            Invocable inv = (Invocable) engine_nashornjs;

            result = "" + inv.invokeFunction(JSFunction);
//            System.out.println("(valpag[" + co_conten + ":" + co_pagina + "])result = " + result);
        } catch (Exception ep) {
            System.out.println("(valpag[" + "])ep = " + ep.getMessage());
            ep.printStackTrace();
        }

//        System.out.println("[JavaScriptService:dopvpj] ====>" + result);

        return result;
    }
}
