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

    public List<MenuDTO> getMenus(Integer co_paquet, Integer co_menpad) {

        List<MenuDTO> menus = new ArrayList<>();
        NQuery nQuery = new NQuery();

        try {

            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_SELECT_MENU));
            nQuery.setInteger("co_paquet", co_paquet == null ? -1 : co_paquet);
            nQuery.setInteger("co_menpad", co_menpad == null ? -1 : co_menpad);

            System.out.println("[MenuDAO:getMenus] Q = " + nQuery.getQueryString());
            menus = nQuery.list();
            System.out.println("[MenuDAO:getMenus] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");

        } catch (Exception ep) {
            System.out.println("[MenuDAO:getMenus] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
            ep.printStackTrace();
        }

        return menus;
    }
//
//    public MenuDTO grabarMenu(MenuDTO menu) {
//
//        NQuery nQuery = new NQuery();
//
//        try {
//            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_GRABAR_PAQUETE));
//
//            nQuery.setInteger("co_paquet", menu.getCo_paquet() == null ? -1 : menu.getCo_paquet());
//            nQuery.setString("no_paquet", menu.getNo_paquet());
//            nQuery.setInteger("co_subsis", menu.getCo_subsis());
//            nQuery.setInteger("or_paquet", menu.getOr_paquet());
//            nQuery.setString("no_prefij", menu.getNo_prefij());
//            nQuery.setString("ur_defaul", menu.getUr_defaul());
//            nQuery.setString("ur_arcadj", menu.getUr_arcadj());
//            nQuery.setString("no_coldef", menu.getNo_coldef());
//
//            System.out.println("[MenuDAO:grabarMenu] Q = " + nQuery.getQueryString());
//
//            menu = (MenuDTO) nQuery.list().get(0);
//
//            System.out.println("[MenuDAO:grabarMenu] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");
//        } catch (Exception ep) {
//            System.out.println("[MenuDAO:grabarMenu] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
//            ep.printStackTrace();
//        }
//
//        return menu;
//    }
//
//    public String deleteMenu(MenuDTO menu) {
//
//        NQuery nQuery = new NQuery();
//
//        String resultado = null;
//
//        try {
//            nQuery.work(session.getNamedQuery(Values.QUERYS_NATIVE_DELETE_PAQUETE));
//
//            nQuery.setInteger("co_paquet", menu.getCo_paquet());
//
//            System.out.println("[MenuDAO:deleteMenu] Q = " + nQuery.getQueryString());
//
//            resultado = nQuery.list().toString();
//
//            System.out.println("[MenuDAO:deleteMenu] Q = " + nQuery.getQueryString() + " T = " + nQuery.getExecutionTime() + "ms");
//            System.out.println("[MenuDAO:deleteMenu] Q = " + nQuery.getQueryString() + " R = " + resultado);
//
//        } catch (Exception ep) {
//            System.out.println("[MenuDAO:deleteMenu] Q = " + nQuery.getQueryString() + "E = " + ep.getMessage());
//            ep.printStackTrace();
//        }
//        return resultado;
//    }

    public void close() {
        try {
            session.close();
        } catch (Exception ep) {
            session = null;
        }
    }

}
