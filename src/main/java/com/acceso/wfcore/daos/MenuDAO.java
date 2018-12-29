package com.acceso.wfcore.daos;

import com.acceso.wfcore.dtos.MenuDTO;
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

            System.out.println("[MenuDAO:getMenus] Q = " + nQuery.getQueryString());
            menus = nQuery.list();
            System.out.println("[MenuDAO:getMenus] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");

        } catch (Exception ep) {
            System.out.println("[MenuDAO:getMenus] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return menus;
    }

    public List<MenuDTO> getMenus_SubSistemas() {

        List<MenuDTO> menus = new ArrayList<>();
        NQuery nQuery = new NQuery();

        try {

            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_SELECT_MENU_SUBSISTEMA));

            System.out.println("[MenuDAO:getMenus] Q = " + nQuery.getQueryString());
            menus = nQuery.list();
            System.out.println("[MenuDAO:getMenus] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");

        } catch (Exception ep) {
            System.out.println("[MenuDAO:getMenus] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return menus;
    }

    public List<MenuDTO> getMenus_Paquete() {

        List<MenuDTO> menus = new ArrayList<>();
        NQuery nQuery = new NQuery();

        try {

            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_SELECT_MENU_PAQUETE));

            System.out.println("[MenuDAO:getMenus] Q = " + nQuery.getQueryString());
            menus = nQuery.list();
            System.out.println("[MenuDAO:getMenus] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");

        } catch (Exception ep) {
            System.out.println("[MenuDAO:getMenus] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return menus;
    }

    public List<MenuDTO> getMenus_ModPad() {

        List<MenuDTO> menus = new ArrayList<>();
        NQuery nQuery = new NQuery();

        try {

            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_SELECT_MENU_MODPAD));

            System.out.println("[MenuDAO:getMenus] Q = " + nQuery.getQueryString());
            menus = nQuery.list();
            System.out.println("[MenuDAO:getMenus] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");

        } catch (Exception ep) {
            System.out.println("[MenuDAO:getMenus] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return menus;
    }
    public List<MenuDTO> getMenus_SubMod() {

        List<MenuDTO> menus = new ArrayList<>();
        NQuery nQuery = new NQuery();

        try {

            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_SELECT_MENU_SUBMOD));

            System.out.println("[MenuDAO:getMenus] Q = " + nQuery.getQueryString());
            menus = nQuery.list();
            System.out.println("[MenuDAO:getMenus] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");

        } catch (Exception ep) {
            System.out.println("[MenuDAO:getMenus] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return menus;
    }

    public void close() {
        try {
            session.close();
        } catch (Exception ep) {
            session = null;
        }
    }

}
