package com.acceso.wfcore.daos;

import com.acceso.wfcore.utils.Values;
import com.acceso.wfcore.dtos.SystemTreeDTO;
import com.acceso.wfcore.listerners.WFCoreListener;
import com.acceso.wfcore.utils.NQuery;

public class SystemDAO extends DAO {

    public SystemDAO() {
        this.session = WFCoreListener.dataSourceService.getMainManager().getNativeSession();
    }

    public SystemTreeDTO getSystemTreeDTO() {
        SystemTreeDTO systemTreeDTO = null;

        NQuery nQuery = new NQuery(this);

        try {
            nQuery.work(this.session.getNamedQuery(Values.QUERY_MAINTREE), true, true);

            systemTreeDTO = (SystemTreeDTO) nQuery.list().get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return systemTreeDTO;
    }

}
