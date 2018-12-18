package com.acceso.wfcore.daos;

import com.acceso.wfcore.dtos.SistemaDTO;
import com.acceso.wfcore.dtos.UsuarioDTO;
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

public class UsuarioDAO {
    StatelessSession session;

    public UsuarioDAO() {
        session = WFCoreListener.dataSourceService.getMainManager().getNativeSession();
    }

    public List<UsuarioDTO> getUsuarios() {

        List<UsuarioDTO> usuarios = new ArrayList<>();
        NQuery nQuery = new NQuery();

        try {

            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_SELECT_USUARIO));

            System.out.println("[UsuarioDAO:getUsuarios] Q = " + nQuery.getQueryString());
            usuarios = nQuery.list();
            System.out.println("[UsuarioDAO:getUsuarios] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");

        } catch (Exception ep) {
            System.out.println("[UsuarioDAO:getUsuarios] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return usuarios;
    }

    public UsuarioDTO grabarUsuario(UsuarioDTO usuario) {

        NQuery nQuery = new NQuery();

        try {
            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_GRABAR_USUARIO));

            nQuery.setInteger("co_usuari", usuario.getCo_usuari() == null ? -1 : usuario.getCo_usuari());
            nQuery.setString("co_usulog", usuario.getCo_usulog());
            nQuery.setString("no_usuari", usuario.getNo_usuari());
            nQuery.setString("pw_usuari", usuario.getPw_usuari());
            nQuery.setInteger("ca_pwdinc", usuario.getCa_pwdinc());
            nQuery.setString("ti_usuari", usuario.getTi_usuari());
            nQuery.setInteger("co_person", usuario.getCo_person());
            nQuery.setString("pw_usuant", usuario.getPw_usuant());
            nQuery.setInteger("co_sistem", usuario.getCo_sistem());
            nQuery.setInteger("co_subsis", usuario.getCo_subsis());


            System.out.println("[UsuarioDAO:grabarUsuario] Q = " + nQuery.getQueryString());

            usuario = (UsuarioDTO) nQuery.list().get(0);

            System.out.println("[UsuarioDAO:grabarUsuario] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");
        } catch (Exception ep) {
            System.out.println("[UsuarioDAO:grabarUsuario] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return usuario;
    }

    public String deleteUsuario(UsuarioDTO usuario) {

        NQuery nQuery = new NQuery();

        String resultado = null;

        try {
            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_DELETE_USUARIO));

            nQuery.setInteger("co_usuari", usuario.getCo_usuari());

            System.out.println("[UsuarioDAO:deleteUsuario] Q = " + nQuery.getQueryString());

            resultado = nQuery.list().toString();

            System.out.println("[UsuarioDAO:deleteUsuario] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");
            System.out.println("[UsuarioDAO:deleteUsuario] Q = " + nQuery.getQueryString() + " R = " + resultado);

        } catch (Exception ep) {
            System.out.println("[UsuarioDAO:deleteUsuario] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
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
