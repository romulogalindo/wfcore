package com.acceso.wfcore.daos;

import com.acceso.wfcore.dtos.RegistroDTO;
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
public class RegistroDAO {

    StatelessSession session;

    public RegistroDAO() {
        session = WFIOAPP.APP.dataSourceService.getMainManager().getNativeSession();
    }

    public List<RegistroDTO> getRegistros() {

        List<RegistroDTO> registros = new ArrayList<>();
        NQuery nQuery = new NQuery();

        try {

            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_SELECT_REGISTRO));

//            System.out.println("[RegistroDAO:getRegistros] Q = " + nQuery.getQueryString());
            registros = nQuery.list();
//            System.out.println("[RegistroDAO:getRegistros] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");

        } catch (Exception ep) {
            System.out.println("[RegistroDAO:getRegistros] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return registros;
    }

    public void close() {
        try {
            session.close();
        } catch (Exception ep) {
            session = null;
        }
    }

}
