package com.acceso.wfcore.apis;

import com.acceso.wfcore.listerners.WFCoreListener;

public class MessageAPI extends GenericAPI {

    public void PUSH_MSG_TO(String api_to, Object api_toid, String api_mact) throws Exception {
        if (api_to.contentEquals("user")) {
            WFCoreListener.APP.messageService.sendMessageToUser(Long.parseLong("" + api_toid), api_mact);
        } else {
            //tipo mensaje de difusion
        }

    }


    public void PUSH_TO_USER(Object api_toid, String api_mact) throws Exception {
        WFCoreListener.APP.messageService.sendMessageToUser(Long.parseLong("" + api_toid), api_mact);

    }

    public void PUSH_LOG(String api_to, String api_toid, String api_mact) {
        if (api_to.contentEquals("user")) {
            WFCoreListener.APP.messageService.sendMessageToUser(Long.parseLong(api_toid), api_mact);
        } else {
            //tipo mensaje de difusion
        }
    }
}
