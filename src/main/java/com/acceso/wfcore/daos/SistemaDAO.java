package com.acceso.wfcore.daos;

import com.acceso.wfcore.dtos.SistemaDTO;
import com.acceso.wfcore.listerners.WFCoreListener;
import com.acceso.wfcore.utils.NQuery;
import com.acceso.wfcore.utils.Values;
import org.hibernate.StatelessSession;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mario Huillca <mario.huillca@acceso.com.pe>
 * Created on 30 nov. 2018, 15:14:24
 */

public class SistemaDAO {
    StatelessSession session;

    public SistemaDAO() {
        session = WFCoreListener.dataSourceService.getMainManager().getNativeSession();
    }

    public List<SistemaDTO> getSistemas() {

        List<SistemaDTO> sistemas = new ArrayList<>();
        NQuery nQuery = new NQuery();

        try {

            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_SELECT_SISTEMA));

            System.out.println("[SistemaDAO:getSistemas] Q = " + nQuery.getQueryString());
            sistemas = nQuery.list();
            System.out.println("[SistemaDAO:getSistemas] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");

        } catch (Exception ep) {
            System.out.println("[SistemaDAO:getSistemas] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return sistemas;
    }

    public SistemaDTO grabarSistema(SistemaDTO sistema) {

        NQuery nQuery = new NQuery();

        try {
            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_GRABAR_SISTEMA));

            nQuery.setInteger("co_sistem", sistema.getCo_sistem() == null ? -1 : sistema.getCo_sistem());
            nQuery.setString("no_sistem", sistema.getNo_sistem());
            nQuery.setString("de_sistem", sistema.getDe_sistem());
            nQuery.setString("ur_logsis", sistema.getUr_logsis());
            nQuery.setString("no_temdef", sistema.getNo_temdef());


            System.out.println("[SistemaDAO:grabarSistema] Q = " + nQuery.getQueryString());

            sistema = (SistemaDTO) nQuery.list().get(0);

            System.out.println("[SistemaDAO:grabarSistema] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");
        } catch (Exception ep) {
            System.out.println("[SistemaDAO:grabarSistema] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return sistema;
    }

    public String deleteSistema(SistemaDTO sistema) {

        NQuery nQuery = new NQuery();

        String resultado = null;

        try {
            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_DELETE_SISTEMA));

            nQuery.setInteger("co_sistem", sistema.getCo_sistem());

            System.out.println("[SistemaDAO:deleteSistema] Q = " + nQuery.getQueryString());

            resultado = nQuery.list().toString();

            System.out.println("[SistemaDAO:deleteSistema] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");
            System.out.println("[SistemaDAO:deleteSistema] Q = " + nQuery.getQueryString() + " R = " + resultado);

        } catch (Exception ep) {
            System.out.println("[SistemaDAO:deleteSistema] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }
        return resultado;
    }

    public void close() {
        try {
            session.close();
        } catch (Exception ep) {
            session = null;
        }
    }

}
