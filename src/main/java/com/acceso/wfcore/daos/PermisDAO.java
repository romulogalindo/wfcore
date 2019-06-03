package com.acceso.wfcore.daos;

import com.acceso.wfcore.dtos.PermisDTO;
import com.acceso.wfcore.kernel.WFIOAPP;
import com.acceso.wfcore.utils.NQuery;
import com.acceso.wfcore.utils.Values;
import org.hibernate.StatelessSession;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mario Huillca <mario.huillca@acceso.com.pe>
 * Created on 30 nov. 2018, 15:14:24
 */
public class PermisDAO {

    StatelessSession session;

    public PermisDAO() {
        session = WFIOAPP.APP.dataSourceService.getMainManager().getNativeSession();
    }

    public List<PermisDTO> getPermis(Integer codUsuario) {

        List<PermisDTO> lstPermis = new ArrayList<>();
        NQuery nQuery = new NQuery();

        try {

            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_SELECT_PERMIS));
            nQuery.setInteger("co_usuari", codUsuario == null ? -1 : codUsuario);

            System.out.println("[PermisDAO:getPermis] Q = " + nQuery.getQueryString());
            lstPermis = nQuery.list();
            System.out.println("[PermisDAO:getPermis] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");

        } catch (Exception ep) {
            System.out.println("[PermisDAO:getPermis] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return lstPermis;
    }

    public void grabarPermis(Integer codUsuario, String listModulos) {

        NQuery nQuery = new NQuery();

        try {
            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_GRABAR_PERMIS));

            nQuery.setInteger("co_usuari", codUsuario == null ? -1 : codUsuario);
            nQuery.setString("co_modulo", listModulos);

            System.out.println("[PermisDAO:grabarPermisPermiso] Q = " + nQuery.getQueryString());

            nQuery.list().get(0);

            System.out.println("[PermisDAO:grabarPermisPermiso] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");
        } catch (Exception ep) {
            System.out.println("[PermisDAO:grabarPermisPermiso] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return;
    }

    public void close() {
        try {
            session.close();
        } catch (Exception ep) {
            session = null;
        }
    }

}
