package com.acceso.wfcore.apis;

import com.acceso.wfcore.listerners.WFCoreListener;
import com.acceso.wfweb.utils.JsonSocketMessage;

public class MessageAPI extends GenericAPI {

    public static final String MSG_TYPE_INFO = "info";
    public static final String MSG_TYPE_SUCCESS = "success";
    public static final String MSG_TYPE_ERROR = "error";
    public static final String MSG_TYPE_WARNING = "warning";

    public void PUSH_MSG_TO(String api_to, Object api_toid, String api_mact) throws Exception {
        if (api_to.contentEquals("user")) {
            WFCoreListener.APP.messageService.sendMessageToUser(Long.parseLong("" + api_toid), api_mact);
        } else {
            //tipo mensaje de difusion
        }

    }

    public void PUSH_TO_USER(Object api_toid, String api_mact) throws Exception {
//        WFCoreListener.APP.messageService.sendMessageToUser(Long.parseLong("" + api_toid), api_mact);
        WFCoreListener.APP.messageService.sendMessageToUser(Long.parseLong("" + api_toid), new JsonSocketMessage(MSG_TYPE_INFO, "AIO", api_mact));
    }

    public void PUSH_TO_USER(Object api_toid, String msg_type, String msg_title, String msg_body) throws Exception {
        WFCoreListener.APP.messageService.sendMessageToUser(Long.parseLong("" + api_toid), new JsonSocketMessage(msg_type, msg_title, msg_body));
    }

    public void PUSH_LOG(String api_to, String api_toid, String api_mact) {
        if (api_to.contentEquals("user")) {
            WFCoreListener.APP.messageService.sendMessageToUser(Long.parseLong(api_toid), api_mact);
        } else {
            //tipo mensaje de difusion
        }
    }
}
