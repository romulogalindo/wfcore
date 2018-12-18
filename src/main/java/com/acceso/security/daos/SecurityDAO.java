package com.acceso.security.daos;

import com.acceso.security.dtos.RegsesiniDTO;
import com.acceso.wfcore.daos.DAO;
import com.acceso.wfcore.listerners.WFCoreListener;
import com.acceso.wfcore.utils.NQuery;
import com.acceso.security.utils.Values;

public class SecurityDAO extends DAO {

    public SecurityDAO() {

        this.session = WFCoreListener.dataSourceService.getMainManager().getNativeSession();

    }

    public RegsesiniDTO regsesini(String p_username, String p_password, String p_remoteip) {
        RegsesiniDTO regsesiniDTO = null;

        String LOG = "[" + this.getClass().getCanonicalName() + ":" + new Object() {
        }.getClass().getEnclosingMethod().getName() + "] ";

        NQuery nQuery = new NQuery();

        try {
            nQuery.work(this.session.getNamedQuery(Values.QUERYS_SECURITY_REGSESINI_WEB));

            nQuery.setString("p_username", p_username);
            nQuery.setString("p_password", p_password);
            nQuery.setString("p_remoteip", p_remoteip);

            new Object() {
            }.getClass().getEnclosingMethod().getName();

            System.out.println(LOG + "Q = " + nQuery.getQueryString());

            regsesiniDTO = (RegsesiniDTO) nQuery.list().get(0);

            System.out.println(LOG + "Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return regsesiniDTO;
    }

}
