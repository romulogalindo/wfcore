package com.acceso.security;

import com.acceso.security.daos.SecurityDAO;
import com.acceso.security.dtos.RegsesiniDTO;
import com.acceso.wfcore.listerners.WFCoreListener;
import com.acceso.wfweb.utils.RequestManager;

/*

 * */
public class DoLogin {
    protected RegsesiniDTO regsesiniDTO;

    public boolean SecurityLogin(RequestManager requestManager) {
        System.out.println("WFCoreListener.APP.getLoginCTRL().getLogin_param_username() = " + WFCoreListener.APP);
        System.out.println("WFCoreListener.APP.getLoginCTRL().getLogin_param_username() = " + WFCoreListener.APP.getLoginCTRL());
        System.out.println("WFCoreListener.APP.getLoginCTRL().getLogin_param_username() = " + WFCoreListener.APP.getLoginCTRL().getLogin_param_username());
        String username = requestManager.getParam(WFCoreListener.APP.getLoginCTRL().getLogin_param_username());
        String password = requestManager.getParam(WFCoreListener.APP.getLoginCTRL().getLogin_param_password());
        String remoteip = requestManager.getIp();

        System.out.println("username = " + username);
        System.out.println("password = " + password);
        System.out.println("remoteip = " + remoteip);
        SecurityDAO securityDAO = new SecurityDAO();
        regsesiniDTO = securityDAO.regsesini(username, password, remoteip);
        securityDAO.close();

        return true;
    }

    public RegsesiniDTO getRegsesiniDTO() {
        return regsesiniDTO;
    }

    public void setRegsesiniDTO(RegsesiniDTO regsesiniDTO) {
        this.regsesiniDTO = regsesiniDTO;
    }
}
