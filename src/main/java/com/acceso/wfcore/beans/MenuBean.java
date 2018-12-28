package com.acceso.wfcore.beans;

import com.acceso.wfcore.daos.MenuDAO;
import com.acceso.wfcore.dtos.MenuDTO;

import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import java.io.Serializable;
import java.util.List;

/**
 * @author Mario Huillca <mario.huillca@acceso.com.pe>
 * Created on 30 nov. 2018, 15:54:46
 */
@ManagedBean
@SessionScoped
public class MenuBean extends MainBean implements Serializable, DefaultMaintenceWeb, DefaultMaintenceDao {

    private static final String URL_LISTA = "/admin/jsf_exec/pagex/menu/paginaMenus.xhtml";
    private static final String URL_DETALLE = "/admin/jsf_exec/pagex/menu/paginaMenus.xhtml";
    private static final String URL_EDITAR = "/admin/jsf_exec/pagex/menu/paginaRegMenu.xhtml";
    private static final String URL_NEW = "/admin/jsf_exec/pagex/menu/paginaRegMenu.xhtml";


    private List<MenuDTO> menus;
    private MenuDTO menu;

    private boolean isregEditable;

    private List<SelectItem> lstSubSistemabySistema;
    private List<SelectItem> lstPaquetebySubSistema;

    private TreeNode root;
    private TreeNode selectedNode;

    public MenuBean() {
        this.beanName = "Menus";
        this.menu = new MenuDTO();
        this.isregEditable = true;
    }

    public void createTreeNode() {
        this.root = new DefaultTreeNode("Root Node", null);

        List<MenuDTO> lstsistemas;
        MenuDAO menuDAO = new MenuDAO();
        lstsistemas = menuDAO.getMenus_Sistemas();

        for (MenuDTO menusisDTO : lstsistemas) {
            TreeNode nodesistema = new DefaultTreeNode(menusisDTO ,  this.root);
            List<MenuDTO> lstsubsis = menuDAO.getMenus_SubSistemas(menusisDTO.getCo_elemen());

            for (MenuDTO menusubDTO : lstsubsis) {
                TreeNode nodesubsis = new DefaultTreeNode(menusubDTO ,  nodesistema);
                List<MenuDTO> lstpaquetes = menuDAO.getMenus_Paquete(menusubDTO.getCo_elemen());

                for (MenuDTO menupaqDTO : lstpaquetes) {
                    TreeNode nodepaquete = new DefaultTreeNode(menupaqDTO ,  nodesubsis);
                    List<MenuDTO> lstmodpad = menuDAO.getMenus_ModPad(menupaqDTO.getCo_elemen());

                    for (MenuDTO menupadDTO : lstmodpad) {
                        TreeNode nodemodpad = new DefaultTreeNode(menupadDTO ,  nodepaquete);
                        List<MenuDTO> lstsub1 = menuDAO.getMenus_SubMod(menupadDTO.getCo_elemen());

                        for (MenuDTO menusb1DTO : lstsub1) {
                            TreeNode nodesub1 = new DefaultTreeNode(menusb1DTO ,  nodemodpad);
                            List<MenuDTO> lstsub2 = menuDAO.getMenus_SubMod(menusb1DTO.getCo_elemen());

                            for (MenuDTO menusb2DTO : lstsub2) {
                                TreeNode nodesub2 = new DefaultTreeNode(menusb2DTO ,  nodesub1);
                                List<MenuDTO> lstsub3 = menuDAO.getMenus_SubMod(menusb2DTO.getCo_elemen());

                                for (MenuDTO menusb3DTO : lstsub3) {
                                    TreeNode nodesub3 = new DefaultTreeNode(menusb3DTO ,  nodesub2);
                                    List<MenuDTO> lstsub4 = menuDAO.getMenus_SubMod(menusb3DTO.getCo_elemen());

                                    for (MenuDTO menusb4DTO : lstsub4) {
                                        TreeNode nodesub4 = new DefaultTreeNode(menusb4DTO ,  nodesub3);
                                        List<MenuDTO> lstsub5 = menuDAO.getMenus_SubMod(menusb4DTO.getCo_elemen());

                                        for (MenuDTO menusb5DTO : lstsub5) {
                                            TreeNode nodesub5 = new DefaultTreeNode(menusb5DTO ,  nodesub4);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }
        menuDAO.close();
        System.out.println("Final Bean:" + this.root.getData().toString());
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((TreeNode) event.getObject()).toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    @Override
    public String getBeanName() {
        return beanName;
    }

    @Override
    public String load() {
        System.out.println("load()");
        this.isregEditable = true;
        // LLENAR LOS BOTONES SECUNDARIOS
        //doListener();
        //CARGA INICIAL!!
        //selectDto();
        createTreeNode();

        return URL_LISTA;
    }

    @Override
    public void doListener() {
        //acceder al manager y decirle toma
        FacesContext context = FacesContext.getCurrentInstance();
        ((ManagerBean) context.getApplication().getVariableResolver().resolveVariable(context, "managerBean")).setRenderedMenuButton(false);
        ((ManagerBean) context.getApplication().getVariableResolver().resolveVariable(context, "managerBean")).initBreadCumBar();
        ((ManagerBean) context.getApplication().getVariableResolver().resolveVariable(context, "managerBean")).updateBreadCumBar(beanName, URL_LISTA);
        ((ManagerBean) context.getApplication().getVariableResolver().resolveVariable(context, "managerBean")).setRenderedCommandButton(true);
        ((ManagerBean) context.getApplication().getVariableResolver().resolveVariable(context, "managerBean")).setCurrentBean(this);
        ((ManagerBean) context.getApplication().getVariableResolver().resolveVariable(context, "managerBean")).setDefaultActionNameButton("NUEVO");

        System.out.println("listener()");
    }

    @Override
    public String defaultAction() {
        // Para el nuevo registro
        this.menu = new MenuDTO();
        FacesContext context = FacesContext.getCurrentInstance();
        ((ManagerBean) context.getApplication().getVariableResolver().resolveVariable(context, "managerBean")).setRenderedCommandButton(false);
        ((ManagerBean) context.getApplication().getVariableResolver().resolveVariable(context, "managerBean")).updateBreadCumBar("Registro", URL_EDITAR);
        //varias cosas para editar
        return URL_NEW;
    }

    @Override
    public String newRegist() {
        //Data la causistica el metodo nuevo esta encapsuado en defaultAction
        return null;
    }

    @Override
    public String updateRegist() {
        FacesContext context = FacesContext.getCurrentInstance();
        ((ManagerBean) context.getApplication().getVariableResolver().resolveVariable(context, "managerBean")).updateBreadCumBar("Editar", URL_EDITAR);
        ((ManagerBean) context.getApplication().getVariableResolver().resolveVariable(context, "managerBean")).setRenderedCommandButton(false);

        return URL_EDITAR;
    }

    @Override
    public String deleteRegist() {
        deleteDto();

        return URL_LISTA;
    }

    @Override
    public String saveRegist() {
        saveDto();
        return URL_LISTA;
    }

    @Override
    public void selectDto() {
        MenuDAO dao = new MenuDAO();
        // this.menus = dao.getMenus(-1, -1);
        dao.close();
    }

    @Override
    public void saveDto() {
        MenuDAO dao = new MenuDAO();
        // this.menu = dao.grabarMenu(menu);
        // this.menus = dao.getMenus(-1, -1);
//      System.out.println("MenuBean actualizarMenu = " + this.menu);
        dao.close();
    }

    @Override
    public void updateDto() {
        MenuDAO dao = new MenuDAO();
        //this.menu = dao.grabarMenu(menu);
        // this.menus = dao.getMenus(-1, -1);
//      System.out.println("MenuBean actualizarMenu = " + this.menu);
        dao.close();
    }

    @Override
    public void deleteDto() {
        MenuDAO dao = new MenuDAO();
        //String resultado = dao.deleteMenu(menu);
        // this.menus = dao.getMenus(-1, -1);
        dao.close();
    }

    public List<MenuDTO> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuDTO> menus) {
        this.menus = menus;
    }

    public MenuDTO getMenu() {
        return menu;
    }

    public void setMenu(MenuDTO menu) {
        this.menu = menu;
    }

    public boolean isIsregEditable() {
        return isregEditable;
    }

    public void setIsregEditable(boolean isregEditable) {
        this.isregEditable = isregEditable;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }
}
