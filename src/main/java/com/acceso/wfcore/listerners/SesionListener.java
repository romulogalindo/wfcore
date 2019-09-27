package com.acceso.wfcore.listerners;

import com.acceso.wfcore.kernel.WFIOAPP;
import com.acceso.wfcore.utils.Values;
import com.acceso.wfweb.units.Usuario;
import com.acceso.wfweb.utils.JsonSocketMessage;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SesionListener implements HttpSessionListener {
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("se ha creado una session leyendo info de la base de datos");
        HttpSession session = se.getSession();
        WFIOAPP.APP.getCacheService().getZeroDawnCache().getSpace(Values.CACHE_MAIN_SESSIONS).put(session.getId(), session);
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        Usuario usuario = (Usuario) se.getSession().getAttribute("US");
        WFIOAPP.APP.messageService.sendMessageToUser(Long.parseLong("" + usuario.getCo_usuari()), new JsonSocketMessage("SESSION", "AIO_WS_EXIT", "Tu session ha finalizado.", "MSG_POSITION_TOPRIGHT", "-1", false, -1));
    }
}
