package com.acceso.security.daos;

import com.acceso.security.dtos.PermisbloDTO;
import com.acceso.security.dtos.RegsesiniDTO;
import com.acceso.wfcore.daos.DAO;
import com.acceso.wfcore.listerners.WFCoreListener;
import com.acceso.wfcore.utils.NQuery;
import com.acceso.security.utils.Values;

import java.util.List;

public class SecurityDAO extends DAO {

    public SecurityDAO() {

        this.session = WFCoreListener.dataSourceService.getMainManager().getNativeSession();

    }

    public RegsesiniDTO regsesini(String p_username, String p_password, String p_remoteip) {
        RegsesiniDTO regsesiniDTO = null;

        NQuery nQuery = new NQuery(this);

        try {
            nQuery.work(this.session.getNamedQuery(Values.QUERYS_SECURITY_REGSESINI_WEB), true, true);

            nQuery.setString("p_username", p_username);
            nQuery.setString("p_password", p_password);
            nQuery.setString("p_remoteip", p_remoteip);

            regsesiniDTO = (RegsesiniDTO) nQuery.list().get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return regsesiniDTO;
    }

    public List<PermisbloDTO> getListBloq(int p_co_usuari) {
        List<PermisbloDTO> regsesiniDTO = null;

        NQuery nQuery = new NQuery(this);

        try {
            nQuery.work(this.session.getNamedQuery(Values.QUERYS_SECURITY_PERMISBLO_WEB), true, true);

            nQuery.setInteger("p_co_usuari", p_co_usuari);

            regsesiniDTO = nQuery.list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return regsesiniDTO;
    }
}
