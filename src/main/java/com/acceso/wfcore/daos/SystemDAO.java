package com.acceso.wfcore.daos;

import com.acceso.wfcore.dtos.EstadoDTO;
import com.acceso.wfcore.utils.Values;
import com.acceso.wfcore.dtos.SystemTreeDTO;
import com.acceso.wfcore.listerners.WFCoreListener;
import com.acceso.wfcore.log.Log;
import com.acceso.wfcore.utils.NQuery;
import org.hibernate.StatelessSession;

public class SystemDAO extends DAO {

    public SystemDAO() {
        this.session = WFCoreListener.dataSourceService.getMainManager().getNativeSession();
    }

    public SystemDAO(StatelessSession session) {
        this.session = session;
    }

    public SystemTreeDTO getSystemTreeDTO() {
        SystemTreeDTO systemTreeDTO = null;

        NQuery nQuery = new NQuery(this);

        try {
            nQuery.work(this.session.getNamedQuery(Values.QUERY_MAINTREE), true, true);

            systemTreeDTO = (SystemTreeDTO) nQuery.list().get(0);
        } catch (Exception ep) {
            Log.error("**" + ep.getMessage());
            if (WFCoreListener.APP.THROWS_EXCEPTION) {
                ep.printStackTrace();
            }
        }

        return systemTreeDTO;
    }

    public EstadoDTO check() {
        EstadoDTO estadoDTO = null;

        NQuery nQuery = new NQuery(this);

        try {
            nQuery.work(this.session.getNamedQuery(Values.SYSQUERYS_NATIVE_GET_STATUS), true, true);
            estadoDTO = (EstadoDTO) nQuery.uniqueResult();
        } catch (Exception ep) {
            estadoDTO = new EstadoDTO();
            estadoDTO.setNo_estado("FAIL:" + ep.getMessage());
            if (WFCoreListener.APP.THROWS_EXCEPTION) {
                ep.printStackTrace();
            }
        }

        return estadoDTO;
    }

}
