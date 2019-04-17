package com.acceso.wfweb.servlets;

import com.acceso.wfcore.listerners.WFCoreListener;
import com.acceso.wfcore.utils.Util;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author Rómulo Galindo
 */
@ServerEndpoint("/ws/main")
public class MainWS {

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("onOpen::" + session.getId());
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("onClose::" + session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("onMessage::From=" + session.getId());
        if (message.startsWith("LOGIN")) {
            long co_usuari = Util.toLong(message.replace("LOGIN:", ""));
//            String id_session = session.getId();
            WFCoreListener.APP.messageService.putBroadCast(co_usuari,session);
        }else{
            
        }

//        try {
//            session.getBasicRemote().sendText(message.toUpperCase());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
    

    @OnError
    public void onError(Throwable t) {
        System.out.println("onError::" + t.getMessage());
    }
}
