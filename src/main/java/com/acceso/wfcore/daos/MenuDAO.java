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
            //nQuery.setInteger("co_paquet", co_paquet == null ? -1 : co_paquet);
            //nQuery.setInteger("co_menpad", co_menpad == null ? -1 : co_menpad);

            System.out.println("[MenuDAO:getMenus] Q = " + nQuery.getQueryString());
            menus = nQuery.list();
            System.out.println("[MenuDAO:getMenus] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");

        } catch (Exception ep) {
            System.out.println("[MenuDAO:getMenus] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return menus;
    }

    public List<MenuDTO> getMenus_SubSistemas(Integer co_sistem) {

        List<MenuDTO> menus = new ArrayList<>();
        NQuery nQuery = new NQuery();

        try {

            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_SELECT_MENU_SUBSISTEMA));
            nQuery.setInteger("co_sistem", co_sistem == null ? -1 : co_sistem);

            System.out.println("[MenuDAO:getMenus] Q = " + nQuery.getQueryString());
            menus = nQuery.list();
            System.out.println("[MenuDAO:getMenus] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");

        } catch (Exception ep) {
            System.out.println("[MenuDAO:getMenus] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return menus;
    }

    public List<MenuDTO> getMenus_Paquete(Integer co_subsis) {

        List<MenuDTO> menus = new ArrayList<>();
        NQuery nQuery = new NQuery();

        try {

            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_SELECT_MENU_PAQUETE));
            nQuery.setInteger("co_sistem", co_subsis == null ? -1 : co_subsis);

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
