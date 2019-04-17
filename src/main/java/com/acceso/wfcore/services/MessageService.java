package com.acceso.wfcore.services;

import com.acceso.wfcore.utils.UserBroadcast;

import java.util.HashMap;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.websocket.Session;

public class MessageService extends Service {

    HashMap<Long, UserBroadcast> users;
    HashMap<String, Long> aclus;

    public MessageService(String serviceName) {
        super(serviceName);

    }

    @Override
    public void start() {
        users = new HashMap<>();
        aclus = new HashMap<>();
    }

    @Override
    public void stop() {
        if (users != null) {
            users.clear();
        }

        if (aclus != null) {
            aclus.clear();
        }
    }

    public void login(Session session) {
        aclus.put(session.getId(), -1L);
    }

    public void putBroadCast(Long user, Session session) {
        UserBroadcast ub = users.get(user);
        if (ub == null) {
            ub = new UserBroadcast(user);
        }

        ub.putSession(session);
        users.put(user, ub);
        aclus.put(session.getId(), user);
    }

    public void sendMessageToUser(Long user, String message) {
        UserBroadcast userBroadcast = users.get(user);
        System.out.println("userBroadcast = " + userBroadcast);
        for (Session session : userBroadcast.getSessions().values()) {
            try {
                session.getBasicRemote().sendText(message);
            } catch (Exception ep) {
                System.out.println("ep = " + ep);
                ep.printStackTrace();
            }
        }
    }

    public void processBroadCast() {
    }
}
