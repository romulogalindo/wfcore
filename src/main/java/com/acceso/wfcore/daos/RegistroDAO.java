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

            System.out.println("[RegistroDAO:getRegistros] Q = " + nQuery.getQueryString());
            registros = nQuery.list();
            System.out.println("[RegistroDAO:getRegistros] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");

        } catch (Exception ep) {
            System.out.println("[RegistroDAO:getRegistros] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return registros;
    }

    //    public RegistroDTO grabarRegistro(RegistroDTO pagina) {
//
//        NQuery nQuery = new NQuery();
//
//        try {
//            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_GRABAR_CONTENEDOR));
//
//            nQuery.setInteger("co_usuari", pagina.getCo_usuari() == null ? -1 : pagina.getCo_usuari());
//            nQuery.setString("co_usulog", pagina.getCo_usulog());
//            nQuery.setString("no_usuari", pagina.getNo_usuari());
//            nQuery.setString("pw_usuari", pagina.getPw_usuari());
//            nQuery.setString("ti_usuari", pagina.getTi_usuari());
//            nQuery.setInteger("co_person", pagina.getCo_person() == null ? -1 : pagina.getCo_person());
//            nQuery.setInteger("co_sistem", pagina.getCo_sistem());
//            nQuery.setInteger("co_subsis", pagina.getCo_subsis());
//
//
//            System.out.println("[RegistroDAO:grabarRegistro] Q = " + nQuery.getQueryString());
//
//            pagina = (RegistroDTO) nQuery.list().get(0);
//
//            System.out.println("[RegistroDAO:grabarRegistro] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");
//        } catch (Exception ep) {
//            System.out.println("[RegistroDAO:grabarRegistro] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
//            ep.printStackTrace();
//        }
//
//        return pagina;
//    }
//
//    public String deleteRegistro(RegistroDTO pagina) {
//
//        NQuery nQuery = new NQuery();
//
//        String resultado = null;
//
//        try {
//            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_DELETE_CONTENEDOR));
//
//            nQuery.setInteger("co_usuari", pagina.getCo_usuari());
//
//            System.out.println("[RegistroDAO:deleteRegistro] Q = " + nQuery.getQueryString());
//
//            resultado = nQuery.list().toString();
//
//            System.out.println("[RegistroDAO:deleteRegistro] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");
//            System.out.println("[RegistroDAO:deleteRegistro] Q = " + nQuery.getQueryString() + " R = " + resultado);
//
//        } catch (Exception ep) {
//            System.out.println("[RegistroDAO:deleteRegistro] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
//            ep.printStackTrace();
//        }
//        return resultado;
//    }
//
    public void close() {
        try {
            session.close();
        } catch (Exception ep) {
            session = null;
        }
    }

}
