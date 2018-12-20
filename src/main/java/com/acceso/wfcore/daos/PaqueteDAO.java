package com.acceso.wfcore.daos;

import com.acceso.wfcore.dtos.PaqueteDTO;
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

public class PaqueteDAO {
    StatelessSession session;

    public PaqueteDAO() {
        session = WFCoreListener.dataSourceService.getMainManager().getNativeSession();
    }

    public List<PaqueteDTO> getPaquetes() {

        List<PaqueteDTO> paquetes = new ArrayList<>();
        NQuery nQuery = new NQuery();

        try {

            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_SELECT_PAQUETE));

            System.out.println("[PaqueteDAO:getPaquetes] Q = " + nQuery.getQueryString());
            paquetes = nQuery.list();
            System.out.println("[PaqueteDAO:getPaquetes] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");

        } catch (Exception ep) {
            System.out.println("[PaqueteDAO:getPaquetes] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return paquetes;
    }

    public PaqueteDTO grabarPaquete(PaqueteDTO paquete) {

        NQuery nQuery = new NQuery();

        try {
            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_GRABAR_PAQUETE));
            Integer co_paquet;

            nQuery.setInteger("co_paquet", paquete.getCo_paquet() == null ? -1 : paquete.getCo_paquet());
            nQuery.setString("no_paquet", paquete.getNo_paquet());
            nQuery.setInteger("co_subsis", paquete.getCo_subsis());
            nQuery.setInteger("or_paquet", paquete.getOr_paquet());
            nQuery.setString("no_prefij", paquete.getNo_prefij());
            nQuery.setString("ur_defaul", paquete.getUr_defaul());
            nQuery.setString("ur_arcadj", paquete.getUr_arcadj());
            nQuery.setString("no_coldef", paquete.getNo_coldef());

            System.out.println("[PaqueteDAO:grabarPaquete] Q = " + nQuery.getQueryString());

            paquete = (PaqueteDTO) nQuery.list().get(0);

            System.out.println("[PaqueteDAO:grabarPaquete] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");
        } catch (Exception ep) {
            System.out.println("[PaqueteDAO:grabarPaquete] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return paquete;
    }

    public String deletePaquete(PaqueteDTO paquete) {

        NQuery nQuery = new NQuery();

        String resultado = null;

        try {
            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_DELETE_PAQUETE));

            nQuery.setInteger("co_paquet", paquete.getCo_paquet());

            System.out.println("[PaqueteDAO:deletePaquete] Q = " + nQuery.getQueryString());

            resultado = nQuery.list().toString();

            System.out.println("[PaqueteDAO:deletePaquete] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");
            System.out.println("[PaqueteDAO:deletePaquete] Q = " + nQuery.getQueryString() + " R = " + resultado);

        } catch (Exception ep) {
            System.out.println("[PaqueteDAO:deletePaquete] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
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
