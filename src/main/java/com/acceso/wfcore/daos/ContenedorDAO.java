package com.acceso.wfcore.daos;

import com.acceso.wfcore.dtos.ContenedorDTO;
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

public class ContenedorDAO {
    StatelessSession session;

    public ContenedorDAO() {
        session = WFCoreListener.dataSourceService.getMainManager().getNativeSession();
    }

        public List<ContenedorDTO> getContenedores() {

        List<ContenedorDTO> contenedores = new ArrayList<>();
        NQuery nQuery = new NQuery();

        try {

            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_SELECT_CONTENEDOR));

            System.out.println("[ContenedorDAO:getContenedores] Q = " + nQuery.getQueryString());
            contenedores = nQuery.list();
            System.out.println("[ContenedorDAO:getContenedores] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");

        } catch (Exception ep) {
            System.out.println("[ContenedorDAO:getContenedores] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return contenedores;
    }

//    public ContenedorDTO grabarContenedor(ContenedorDTO usuario) {
//
//        NQuery nQuery = new NQuery();
//
//        try {
//            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_GRABAR_CONTENEDOR));
//
//            nQuery.setInteger("co_usuari", usuario.getCo_usuari() == null ? -1 : usuario.getCo_usuari());
//            nQuery.setString("co_usulog", usuario.getCo_usulog());
//            nQuery.setString("no_usuari", usuario.getNo_usuari());
//            nQuery.setString("pw_usuari", usuario.getPw_usuari());
//            nQuery.setString("ti_usuari", usuario.getTi_usuari());
//            nQuery.setInteger("co_person", usuario.getCo_person() == null ? -1 : usuario.getCo_person());
//            nQuery.setInteger("co_sistem", usuario.getCo_sistem());
//            nQuery.setInteger("co_subsis", usuario.getCo_subsis());
//
//
//            System.out.println("[ContenedorDAO:grabarContenedor] Q = " + nQuery.getQueryString());
//
//            usuario = (ContenedorDTO) nQuery.list().get(0);
//
//            System.out.println("[ContenedorDAO:grabarContenedor] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");
//        } catch (Exception ep) {
//            System.out.println("[ContenedorDAO:grabarContenedor] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
//            ep.printStackTrace();
//        }
//
//        return usuario;
//    }
//
//    public String deleteContenedor(ContenedorDTO usuario) {
//
//        NQuery nQuery = new NQuery();
//
//        String resultado = null;
//
//        try {
//            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_DELETE_CONTENEDOR));
//
//            nQuery.setInteger("co_usuari", usuario.getCo_usuari());
//
//            System.out.println("[ContenedorDAO:deleteContenedor] Q = " + nQuery.getQueryString());
//
//            resultado = nQuery.list().toString();
//
//            System.out.println("[ContenedorDAO:deleteContenedor] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");
//            System.out.println("[ContenedorDAO:deleteContenedor] Q = " + nQuery.getQueryString() + " R = " + resultado);
//
//        } catch (Exception ep) {
//            System.out.println("[ContenedorDAO:deleteContenedor] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
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
