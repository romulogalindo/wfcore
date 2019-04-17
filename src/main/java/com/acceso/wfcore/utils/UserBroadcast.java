package com.acceso.wfcore.utils;

import java.util.HashMap;
import java.util.Map;
import javax.websocket.Session;

/**
 * @author Rómulo Galindo
 */
public class UserBroadcast {
    Long user;
    Map<String, Session> sessions;

    public UserBroadcast(Long user) {
        this.user = user;
        sessions = new HashMap<>();
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Map<String, Session> getSessions() {
        return sessions;
    }

    public void setSessions(Map<String, Session> sessions) {
        this.sessions = sessions;
    }

    public void putSession(Session session) {
        this.sessions.put(session.getId(), session);
    }

}
