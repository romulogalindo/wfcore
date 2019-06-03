package com.acceso.wfcore.daos;

import com.acceso.wfcore.dtos.ModuloDTO;
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
public class ModuloDAO {

    StatelessSession session;

    public ModuloDAO() {
        session = WFIOAPP.APP.dataSourceService.getMainManager().getNativeSession();
    }

    public List<ModuloDTO> getModulos() {

        List<ModuloDTO> modulos = new ArrayList<>();
        NQuery nQuery = new NQuery();

        try {

            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_SELECT_MODULO));

            System.out.println("[ModuloDAO:getModulos] Q = " + nQuery.getQueryString());
            modulos = nQuery.list();
            System.out.println("[ModuloDAO:getModulos] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");

        } catch (Exception ep) {
            System.out.println("[ModuloDAO:getModulos] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return modulos;
    }

    public List<ModuloDTO> getModulosMenu() {

        List<ModuloDTO> modulos = new ArrayList<>();
        NQuery nQuery = new NQuery();

        try {

            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_SELECT_MODULO_MENU));

            System.out.println("[ModuloDAO:getModulosMenu] Q = " + nQuery.getQueryString());
            modulos = nQuery.list();
            System.out.println("[ModuloDAO:getModulosMenu] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");

        } catch (Exception ep) {
            System.out.println("[ModuloDAO:getModulosMenu] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return modulos;
    }

    public ModuloDTO grabarModulo(ModuloDTO modulo) {

        NQuery nQuery = new NQuery();

        try {
            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_GRABAR_MODULO));

            nQuery.setInteger("co_modulo", modulo.getCo_modulo() == null ? -1 : modulo.getCo_modulo());
            nQuery.setString("no_modulo", modulo.getNo_modulo());
            nQuery.setString("ur_modulo", modulo.getUr_modulo());
            nQuery.setInteger("co_paquet", modulo.getCo_paquet());
            nQuery.setInteger("co_subsis", modulo.getCo_subsis());
            nQuery.setInteger("co_plataf", modulo.getCo_plataf() == null ? 1 : modulo.getCo_plataf());

            System.out.println("[ModuloDAO:grabarModulo] Q = " + nQuery.getQueryString());

            modulo = (ModuloDTO) nQuery.list().get(0);

            System.out.println("[ModuloDAO:grabarModulo] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");
        } catch (Exception ep) {
            System.out.println("[ModuloDAO:grabarModulo] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return modulo;
    }

    public String deleteModulo(ModuloDTO modulo) {

        NQuery nQuery = new NQuery();

        String resultado = null;

        try {
            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_DELETE_MODULO));

            nQuery.setInteger("co_modulo", modulo.getCo_modulo());

            System.out.println("[ModuloDAO:deleteModulo] Q = " + nQuery.getQueryString());

            resultado = nQuery.list().toString();

            System.out.println("[ModuloDAO:deleteModulo] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");
            System.out.println("[ModuloDAO:deleteModulo] Q = " + nQuery.getQueryString() + " R = " + resultado);

        } catch (Exception ep) {
            System.out.println("[ModuloDAO:deleteModulo] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
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
