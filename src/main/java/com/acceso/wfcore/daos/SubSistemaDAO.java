package com.acceso.wfcore.daos;

import com.acceso.wfcore.dtos.SubSistemaDTO;
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

public class SubSistemaDAO {
    StatelessSession session;

    public SubSistemaDAO() {
        session = WFCoreListener.dataSourceService.getMainManager().getNativeSession();
    }

    public List<SubSistemaDTO> getSubSistemas() {

        List<SubSistemaDTO> subsistemas = new ArrayList<>();
        NQuery nQuery = new NQuery();

        try {

            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_SELECT_SUBSISTEMA));

            System.out.println("[SistemaDAO:getSubSistemas] Q = " + nQuery.getQueryString());
            subsistemas = nQuery.list();
            System.out.println("[SistemaDAO:getSubSistemas] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");

        } catch (Exception ep) {
            System.out.println("[SistemaDAO:getSubSistemas] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return subsistemas;
    }

    public SubSistemaDTO grabarSubSistema(SubSistemaDTO subsistema) {

        NQuery nQuery = new NQuery();

        try {
            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_GRABAR_SUBSISTEMA));

            nQuery.setInteger("co_subsis", subsistema.getCo_subsis() == null ? -1 : subsistema.getCo_subsis());
            nQuery.setString("no_subsis", subsistema.getNo_subsis());
            nQuery.setInteger("co_sistem", subsistema.getCo_sistem());
            nQuery.setString("ur_logsub", subsistema.getUr_logsub());

            System.out.println("[SistemaDAO:grabarSubSistema] Q = " + nQuery.getQueryString());

            subsistema = (SubSistemaDTO) nQuery.list().get(0);

            System.out.println("[SistemaDAO:grabarSubSistema] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");
        } catch (Exception ep) {
            System.out.println("[SistemaDAO:grabarSubSistema] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return subsistema;
    }

    public String deleteSubSistema(SubSistemaDTO subsistema) {

        NQuery nQuery = new NQuery();

        String resultado = null;

        try {
            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_DELETE_SUBSISTEMA));

            nQuery.setInteger("co_subsis", subsistema.getCo_subsis());

            System.out.println("[SistemaDAO:deleteSubSistema] Q = " + nQuery.getQueryString());

            resultado = nQuery.list().toString();

            System.out.println("[SistemaDAO:deleteSubSistema] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");
            System.out.println("[SistemaDAO:deleteSubSistema] Q = " + nQuery.getQueryString() + " R = " + resultado);

        } catch (Exception ep) {
            System.out.println("[SistemaDAO:deleteSubSistema] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
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
