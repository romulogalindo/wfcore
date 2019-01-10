package com.acceso.wfcore.daos;

import com.acceso.wfcore.dtos.MenuDTO;
import com.acceso.wfcore.dtos.ModuloDTO;
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

public class MenuDAO {
    StatelessSession session;

    public MenuDAO() {
        session = WFCoreListener.dataSourceService.getMainManager().getNativeSession();
    }

    public List<MenuDTO> getMenus_Sistemas() {

        List<MenuDTO> menus = new ArrayList<>();
        NQuery nQuery = new NQuery();

        try {

            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_SELECT_MENU_SISTEMA));

            System.out.println("[MenuDAO:getMenus_Sistemas] Q = " + nQuery.getQueryString());
            menus = nQuery.list();
            System.out.println("[MenuDAO:getMenus_Sistemas] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");

        } catch (Exception ep) {
            System.out.println("[MenuDAO:getMenus_Sistemas] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return menus;
    }

    public List<MenuDTO> getMenus_SubSistemas() {

        List<MenuDTO> menus = new ArrayList<>();
        NQuery nQuery = new NQuery();

        try {

            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_SELECT_MENU_SUBSISTEMA));

            System.out.println("[MenuDAO:getMenus_SubSistemas] Q = " + nQuery.getQueryString());
            menus = nQuery.list();
            System.out.println("[MenuDAO:getMenus_SubSistemas] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");

        } catch (Exception ep) {
            System.out.println("[MenuDAO:getMenus_SubSistemas] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return menus;
    }

    public List<MenuDTO> getMenus_Paquete() {

        List<MenuDTO> menus = new ArrayList<>();
        NQuery nQuery = new NQuery();

        try {

            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_SELECT_MENU_PAQUETE));

            System.out.println("[MenuDAO:getMenus_Paquete] Q = " + nQuery.getQueryString());
            menus = nQuery.list();
            System.out.println("[MenuDAO:getMenus_Paquete] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");

        } catch (Exception ep) {
            System.out.println("[MenuDAO:getMenus_Paquete] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return menus;
    }

    public List<MenuDTO> getMenus_ModPad() {

        List<MenuDTO> menus = new ArrayList<>();
        NQuery nQuery = new NQuery();

        try {

            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_SELECT_MENU_MODPAD));

            System.out.println("[MenuDAO:getMenus_ModPad] Q = " + nQuery.getQueryString());
            menus = nQuery.list();
            System.out.println("[MenuDAO:getMenus_ModPad] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");

        } catch (Exception ep) {
            System.out.println("[MenuDAO:getMenus_ModPad] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return menus;
    }
    public List<MenuDTO> getMenus_SubMod() {

        List<MenuDTO> menus = new ArrayList<>();
        NQuery nQuery = new NQuery();

        try {

            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_SELECT_MENU_SUBMOD));

            System.out.println("[MenuDAO:getMenus_SubMod] Q = " + nQuery.getQueryString());
            menus = nQuery.list();
            System.out.println("[MenuDAO:getMenus_SubMod] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");

        } catch (Exception ep) {
            System.out.println("[MenuDAO:getMenus_SubMod] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return menus;
    }

    public String deleteMenu(MenuDTO menu) {

        NQuery nQuery = new NQuery();

        String resultado = null;

        try {
            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_DELETE_MENU));

            nQuery.setInteger("co_mensis", menu.getCo_elemen());

            System.out.println("[MenuDAO:deleteMenu] Q = " + nQuery.getQueryString());

            resultado = nQuery.uniqueResult().toString();

            System.out.println("[MenuDAO:deleteMenu] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");
            System.out.println("[MenuDAO:deleteMenu] Q = " + nQuery.getQueryString() + " R = " + resultado);

        } catch (Exception ep) {
            System.out.println("[MenuDAO:deleteMenu] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }
        return resultado;
    }

    public MenuDTO grabarMenu(MenuDTO menu) {

        NQuery nQuery = new NQuery();

        try {
            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_GRABAR_MENU));

            nQuery.setInteger("co_mensis", menu.getCo_elemen() == null ? -1 : menu.getCo_elemen());
            nQuery.setString("no_mensis", menu.getNo_elemen());
            nQuery.setInteger("co_menpad", menu.getCo_menpad());
            nQuery.setInteger("co_modulo", menu.getCo_modulo());
            nQuery.setInteger("co_paquet", menu.getCo_paquet());
            nQuery.setInteger("or_mensis", menu.getOr_elemen());
            nQuery.setInteger("co_subsis", menu.getCo_subsis());

            System.out.println("[MenuDAO:grabarMenu] Q = " + nQuery.getQueryString());

            menu = (MenuDTO) nQuery.list().get(0);

            System.out.println("[MenuDAO:grabarMenu] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");
        } catch (Exception ep) {
            System.out.println("[MenuDAO:grabarMenu] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return menu;
    }

    public void close() {
        try {
            session.close();
        } catch (Exception ep) {
            session = null;
        }
    }

}
