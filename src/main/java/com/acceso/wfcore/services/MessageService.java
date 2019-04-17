package com.acceso.wfcore.services;

import com.acceso.wfcore.utils.UserBroadcast;
import java.util.HashMap;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.websocket.Session;

public class MessageService extends Service {

    ScriptEngineManager manager;
    //    ScriptEngine engine_nativejs;
    ScriptEngine engine_nashornjs;
//    ScriptEngine engine_graalvmjs;
    HashMap<Long, UserBroadcast> users;

    public MessageService(String serviceName) {
        super(serviceName);

    }

    @Override
    public void start() {
        users = new HashMap<>();        
    }

    @Override
    public void stop() {
        if (users != null) {
            users.clear();
        }
//        engine_nashornjs = null;
    }
    
    public void putBroadCast(Long user, Session session){
        UserBroadcast ub= users.get(user);
        if(ub==null){
            ub=new UserBroadcast(user);            
        }
        
        ub.putSession(session);
        users.put(user, ub);
    }
    
    public void processBroadCast(){}
}
