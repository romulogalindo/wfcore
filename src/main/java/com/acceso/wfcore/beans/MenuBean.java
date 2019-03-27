package com.acceso.wfcore.beans;

import com.acceso.wfcore.daos.MenuDAO;
import com.acceso.wfcore.daos.ModuloDAO;
import com.acceso.wfcore.dtos.MenuDTO;

import com.acceso.wfcore.dtos.ModuloDTO;
import org.primefaces.context.PrimeRequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import java.io.Serializable;
import java.util.ArrayList;
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

    public static final String BEAN_NAME = "menuBean";

    private List<MenuDTO> menus;
    private MenuDTO menu;

    private boolean isregEditable;

    private List<SelectItem> lstModulobyPaquete;

    private TreeNode root;
    private TreeNode selectedNode;
    private TreeNode nodeAction;
    private List<ModuloDTO> modulos;

    public MenuBean() {
        this.beanName = BEAN_NAME;
        this.titleName = "Menus";
        this.menu = new MenuDTO();
        this.isregEditable = true;
    }

    public void createTreeNode() {
        this.root = new DefaultTreeNode("Root Node", null);

        MenuDAO menuDAO = new MenuDAO();
        List<MenuDTO> lstsistemas = menuDAO.getMenus_Sistemas();
        List<MenuDTO> lstsubsis = menuDAO.getMenus_SubSistemas();
        List<MenuDTO> lstpaquetes = menuDAO.getMenus_Paquete();
        List<MenuDTO> lstmodpad = menuDAO.getMenus_ModPad();
        List<MenuDTO> lstsub1 = menuDAO.getMenus_SubMod();

        for (MenuDTO menusisDTO : lstsistemas) {
            TreeNode nodesistema = new DefaultTreeNode(menusisDTO, this.root);

            for (MenuDTO menusubDTO : lstsubsis) {
                if (menusubDTO.getCo_sistem().intValue() == menusisDTO.getCo_elemen().intValue()) {
                    TreeNode nodesubsis = new DefaultTreeNode(menusubDTO, nodesistema);

                    for (MenuDTO menupaqDTO : lstpaquetes) {
                        if (menupaqDTO.getCo_subsis().intValue() == menusubDTO.getCo_elemen().intValue()) {
                            TreeNode nodepaquete = new DefaultTreeNode(menupaqDTO, nodesubsis);

                            for (MenuDTO menupadDTO : lstmodpad) {
                                if (menupadDTO.getCo_paquet().intValue() == menupaqDTO.getCo_elemen().intValue()) {
                                    TreeNode nodemodpad = new DefaultTreeNode(menupadDTO, nodepaquete);

                                    for (MenuDTO menusb1DTO : lstsub1) {
                                        if (menusb1DTO.getCo_menpad().intValue() == menupadDTO.getCo_elemen().intValue()) {
                                            TreeNode nodesub1 = new DefaultTreeNode(menusb1DTO, nodemodpad);

                                            for (MenuDTO menusb2DTO : lstsub1) {
                                                if (menusb2DTO.getCo_menpad().intValue() == menusb1DTO.getCo_elemen().intValue()) {
                                                    TreeNode nodesub2 = new DefaultTreeNode(menusb2DTO, nodesub1);

                                                    for (MenuDTO menusb3DTO : lstsub1) {
                                                        if (menusb3DTO.getCo_menpad().intValue() == menusb2DTO.getCo_elemen().intValue()) {
                                                            TreeNode nodesub3 = new DefaultTreeNode(menusb3DTO, nodesub2);

                                                            for (MenuDTO menusb4DTO : lstsub1) {
                                                                if (menusb4DTO.getCo_menpad().intValue() == menusb3DTO.getCo_elemen().intValue()) {
                                                                    TreeNode nodesub4 = new DefaultTreeNode(menusb4DTO, nodesub3);

                                                                    for (MenuDTO menusb5DTO : lstsub1) {
                                                                        if (menusb5DTO.getCo_menpad().intValue() == menusb4DTO.getCo_elemen().intValue()) {
                                                                            TreeNode nodesub5 = new DefaultTreeNode(menusb5DTO, nodesub4);
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
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

    public List<SelectItem> getComboModulos(MenuDTO menuv) {
        this.lstModulobyPaquete = new ArrayList<>();

        if (menuv.getCo_identi().equals("MS") || menuv.getCo_identi().equals("MP")) {
            for (ModuloDTO mod : modulos) {
                if (mod.getCo_paquet() == menuv.getCo_paquet()) {
                    SelectItem item = new SelectItem();
                    item.setLabel(mod.getNo_modulo());
                    item.setDescription(mod.getNo_modulo());
                    item.setValue(mod.getCo_modulo());
                    this.lstModulobyPaquete.add(item);
                }
            }
        }
        return this.lstModulobyPaquete;
    }

    public void cargarListaModulos() {
        ModuloDAO dao = new ModuloDAO();
        this.modulos = dao.getModulosMenu();
        dao.close();
    }

    public void addChildNodeAction(TreeNode nodeActionSelect, MenuDTO menuSelect) {

        MenuDTO nuevoMenu = new MenuDTO();
        nuevoMenu.setCo_sistem(menuSelect.getCo_sistem());
        nuevoMenu.setCo_subsis(menuSelect.getCo_subsis());
        nuevoMenu.setCo_paquet(menuSelect.getCo_paquet());
        nuevoMenu.setCo_menpad(menuSelect.getCo_elemen());
        nuevoMenu.setCo_identi("MS");
        nuevoMenu.setVa_colele("#f5b7b1");

        for (TreeNode node : nodeActionSelect.getChildren()) {
            if (((MenuDTO) node.getData()).getCo_elemen() == null) {
                FacesMessage msg = new FacesMessage("Alerta", "Solo se puede agregar una fila");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                return;
            }
        }

        nodeActionSelect.setExpanded(true);
        TreeNode nueva = new DefaultTreeNode(nuevoMenu, nodeActionSelect);
        // nueva.setSelected(true);

        System.out.println("addChildNodeAction: " + "jQuery('#form\\\\:lista\\\\:" + nueva.getRowKey() + "\\\\:Edit').find('span.ui-icon-pencil').each(function(){jQuery(this).click()})");
//      RequestContext.getCurrentInstance().execute("jQuery('#form\\\\:lista\\\\:" + nueva.getRowKey() + "\\\\:Edit').find('span.ui-icon-pencil').each(function(){jQuery(this).click()})");

        return;
    }

    public void onRowCancel(RowEditEvent event) {
        TreeNode nodeActionSelect = ((TreeNode) event.getObject());
        MenuDTO selectMenu = (MenuDTO) nodeActionSelect.getData();

        FacesMessage msg = new FacesMessage("Edición Cancelada", "No se realizo ninguna modificación");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowEdit(RowEditEvent event) {
        TreeNode nodeActionSelect = ((TreeNode) event.getObject());
        MenuDTO selectMenu = (MenuDTO) nodeActionSelect.getData();
        //System.out.println("Termino: " + selectMenu.getCo_elemen() + " - " + selectMenu.getNo_elemen());
        MenuDAO dao = new MenuDAO();
        this.menu = dao.grabarMenu(selectMenu);
        dao.close();

        if (selectMenu.getCo_elemen() == null) {
            TreeNode parent = nodeActionSelect.getParent();
            parent.getChildren().remove(nodeActionSelect);
            TreeNode nueva = new DefaultTreeNode(this.menu, parent);
        }

        FacesMessage msg = new FacesMessage("Edición", "Se realizó la modificación");
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

    public void onRowDelete(TreeNode nodeActionSelect) {
        MenuDTO selectMenu = (MenuDTO) nodeActionSelect.getData();

        if (selectMenu.getCo_elemen() != null) {
            MenuDAO dao = new MenuDAO();
            dao.deleteMenu(selectMenu);
            dao.close();
        }

        TreeNode parent = nodeActionSelect.getParent();
        parent.getChildren().remove(nodeActionSelect);

        FacesMessage msg = new FacesMessage("Eliminación", "Se eliminó correctamente");
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

    public boolean isEditable(MenuDTO menuv) {
        Boolean isEditable = false;

        if (menuv.getCo_identi().equals("MS") || menuv.getCo_identi().equals("MP")) {
            isEditable = true;
        }

        return isEditable;
    }

    public boolean isDelete(MenuDTO menuv) {
        Boolean isEditable = false;

        if ((menuv.getCo_identi().equals("MS") || menuv.getCo_identi().equals("MP"))) {
            isEditable = true;
        }

        return isEditable;
    }

    public boolean isAdd(MenuDTO menuv) {
        Boolean isEditable = false;

        if (menuv.getCo_elemen() != null && (menuv.getCo_identi().equals("MS") || menuv.getCo_identi().equals("MP")) && menuv.getCo_modulo() == null) {
            isEditable = true;
        }

        return isEditable;
    }

    public boolean isSave(MenuDTO menuv) {
        Boolean isEditable = false;

        if (menuv.getCo_elemen() == null) {
            isEditable = true;
        }

        return isEditable;
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
        cargarListaModulos();

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

        //ManagerBean -> Open!
        ((ManagerBean) context.getApplication().getVariableResolver().resolveVariable(context, "managerBean")).setOpenedModule(this.beanName);

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
        this.menu = dao.grabarMenu(menu);
        System.out.println("MenuBean actualizarMenu = " + this.menu);
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
        String resultado = dao.deleteMenu(menu);
        System.out.println("resultado:" + resultado);
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

    public TreeNode getNodeAction() {
        return nodeAction;
    }

    public void setNodeAction(TreeNode nodeAction) {
        this.nodeAction = nodeAction;
    }
}
