package com.acceso.security.daos;

import com.acceso.security.dtos.PermisbloDTO;
import com.acceso.security.dtos.RegsesiniDTO;
import com.acceso.security.dtos.RegsesinifDTO;
import com.acceso.wfcore.daos.DAO;
import com.acceso.wfcore.utils.NQuery;
import com.acceso.security.utils.Values;
import com.acceso.wfcore.kernel.WFIOAPP;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;

import java.util.List;

public class SecurityDAO extends DAO {

    public static String TAG = "SECURE";

    public SecurityDAO() {
        this.session = WFIOAPP.APP.dataSourceService.getMainManager().getNativeSession();
    }

    public SecurityDAO(StatelessSession session) {
        this.session = session;
    }

    public RegsesiniDTO regsesini(String p_username, String p_password, String p_remoteip) throws Exception {
        RegsesiniDTO regsesiniDTO = null;

        NQuery nQuery = new NQuery(TAG + ":REGSES");

        nQuery.work(this.session.getNamedQuery(Values.wfsistem_ppregsesiniweb_KEY), true, true);
        nQuery.setString("p_username", p_username);
        nQuery.setString("p_password", p_password);
        nQuery.setString("p_remoteip", p_remoteip);

        regsesiniDTO = (RegsesiniDTO) nQuery.uniqueResult();
        return regsesiniDTO;
    }

    public RegsesinifDTO regsesinif(long p_id_sesion,
            long p_co_usuari,
            String p_ip_remoto) {

        RegsesinifDTO regsesinifDTO = null;
        NQuery nQuery = new NQuery(TAG + ":REGSES");
        Transaction transaction = null;

        try {
            transaction = this.session.beginTransaction();
            nQuery.work(this.session.getNamedQuery(Values.QUERYS_SECURITY_REGSESINI_FOREING), true, true);

            nQuery.setLong("p_id_sesion", p_id_sesion);
            nQuery.setInteger("p_co_usuari", (int) p_co_usuari);
            nQuery.setString("p_ip_remoto", p_ip_remoto);

            regsesinifDTO = (RegsesinifDTO) nQuery.list().get(0);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return regsesinifDTO;
    }

    public List<PermisbloDTO> getListBloq(int p_co_usuari) {
        List<PermisbloDTO> regsesiniDTO = null;

        NQuery nQuery = new NQuery(TAG + ":LISBLO");

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
