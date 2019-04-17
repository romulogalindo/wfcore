package com.acceso.wfweb.servlets;

import com.acceso.wfcore.listerners.WFCoreListener;
import com.acceso.wfcore.utils.Util;
import com.acceso.wfcore.utils.WSMessage;
import com.google.gson.Gson;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * @author Rómulo Galindo
 */
@ServerEndpoint("/ws/main")
public class MainWS {

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("onOpen::" + session.getId());
        //put session without userid
        try {
            session.getBasicRemote().sendText("AIO_WS_READY");
        } catch (Exception ep) {
            System.out.println("ep = " + ep);
        }
        WFCoreListener.APP.messageService.login(session);
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("onClose::" + session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("onMessage::From=" + session.getId());
        WSMessage wsMessage = new Gson().fromJson(message, WSMessage.class);
        System.out.println("wsMessage = " + wsMessage);
        switch (wsMessage.getType()) {
            case "login": {
                WFCoreListener.APP.messageService.putBroadCast(Long.parseLong(wsMessage.getUser()), session);
                break;
            }
            case "send": {
                break;
            }
        }
    }


    @OnError
    public void onError(Throwable t) {
        System.out.println("onError::" + t.getMessage());
    }
}
