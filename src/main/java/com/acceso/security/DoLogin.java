package com.acceso.security;

import com.acceso.security.daos.SecurityDAO;
import com.acceso.security.dtos.PermisbloDTO;
import com.acceso.security.dtos.RegsesiniDTO;
import com.acceso.wfcore.listerners.WFCoreListener;
import com.acceso.wfcore.utils.Security;
import com.acceso.wfcore.utils.Values;
import com.acceso.wfweb.units.Usuario;
import com.acceso.wfweb.utils.RequestManager;
import com.acceso.wfweb.web.*;

import java.util.ArrayList;
import java.util.List;

/*

 * */
public class DoLogin {

    public static final int LOGIN_OK = 200;
    public static final int LOGIN_FAIL_USUARI = 404;
    public static final int LOGIN_FAIL_PASSWORD = 403;

    protected RegsesiniDTO regsesiniDTO;
    protected List<PermisbloDTO> permisbloDTO;

    public boolean SecurityLogin(RequestManager requestManager) throws Exception {

        String username = requestManager.getParam(WFCoreListener.APP.getLoginCTRL().getLogin_param_username());
        String password = requestManager.getParam(WFCoreListener.APP.getLoginCTRL().getLogin_param_password());
        String remoteip = requestManager.getIp();

        SecurityDAO securityDAO = new SecurityDAO();
        regsesiniDTO = securityDAO.regsesini(username, Security.toMD5(password), remoteip);
//        permisbloDTO = securityDAO.getListBloq(regsesiniDTO.getCo_usuari());
        securityDAO.close();

        //[FED]
        regsesiniDTO.setIp_remoto(remoteip);
        System.out.println("regsesiniDTO = " + regsesiniDTO);
        //Logica de seguridad
        return regsesiniDTO.getCo_mensaj() == LOGIN_OK;
    }

    public Usuario getUsuario() {
        //security
        Usuario usuario = new Usuario();
        usuario.setId_sesion(regsesiniDTO.getId_sesion());
        usuario.setCo_usuari(regsesiniDTO.getCo_usuari());
        usuario.setNo_usulog(regsesiniDTO.getNo_usulog());
        usuario.setNo_usuari(regsesiniDTO.getNo_usuari());
        usuario.setNo_imgusu(regsesiniDTO.getNo_imgusu());

        usuario.setCo_sistem(regsesiniDTO.getCo_sistem());
        usuario.setCo_subsis(regsesiniDTO.getCo_subsis());
        usuario.setCo_paquet(regsesiniDTO.getCo_paquet());
        usuario.setIl_schema(true);

        SecurityDAO securityDAO = new SecurityDAO();
        permisbloDTO = securityDAO.getListBloq((int) regsesiniDTO.getCo_usuari());
        securityDAO.close();

        SecurityDAO securityfDAO = new SecurityDAO(WFCoreListener.dataSourceService.getManager("wfacr").getNativeSession());
        securityfDAO.regsesinif(regsesiniDTO.getId_sesion(), regsesiniDTO.getCo_usuari(), regsesiniDTO.getIp_remoto());
        securityfDAO.close();

        //elementos que han sido capados por la capa de securidad ->>> desde la cache
        Root root = (Root) WFCoreListener.APP.getCacheService().getZeroDawnCache().getSpace(Values.CACHE_MAIN_MENUTREE).get("ROOT_TREE");
        System.out.println("root = " + root);
        MainMenu mainMenu;// = ((Root) WFCoreListener.APP.getCacheService().getZeroDawnCache().getSpace(Values.CACHE_MAIN_MENUTREE).get("ROOT_TREE")).getSistemas().get(0).getSubsistemas().get(0).getPaquetes().get(0).getMmenu();

        List<PermisbloDTO> permisSistema = getPermisBy("SISTEMA");
        List<PermisbloDTO> permisSubSistema = getPermisBy("SUBSISTEMA");
        List<PermisbloDTO> permisPaquete = getPermisBy("PAQUETE");

        List<PermisbloDTO> permisRoot = getPermisBy("MOD-PADRE");
        List<PermisbloDTO> permisMenu1 = getPermisBy("MOD-NVL1");
        List<PermisbloDTO> permisMenu2 = getPermisBy("MOD-NVL2");
        List<PermisbloDTO> permisMenu3 = getPermisBy("MOD-NVL3");
        List<PermisbloDTO> permisMenu4 = getPermisBy("MOD-NVL4");
        List<PermisbloDTO> permisMenu5 = getPermisBy("MOD-NVL5");

        for (int a = root.getSistemas().size(); a > 0; a--) {
            Sistema sistema = root.getSistemas().get(a - 1);
            boolean renderer = evaluar(sistema.getCo_sistem(), permisSistema);
            System.out.println("sistema = " + sistema.getCo_sistem() + "({" + renderer + "})");

            if (!renderer) {

                root.getSistemas().remove(a - 1);
            } else {

                for (int b = sistema.getSubsistemas().size(); b > 0; b--) {
                    Subsistema subsistema = sistema.getSubsistemas().get(b - 1);
                    renderer = evaluar(subsistema.getCo_subsis(), permisSubSistema);

                    if (!renderer) {

                        sistema.getSubsistemas().remove(b - 1);
                    } else {

                        for (int c = subsistema.getPaquetes().size(); c > 0; c--) {
                            Paquete paquete = subsistema.getPaquetes().get(c - 1);
                            renderer = evaluar(paquete.getCo_paquet(), permisPaquete);

                            if (!renderer) {

                                subsistema.getPaquetes().remove(c - 1);
                            } else {

                                MainMenu _mainMenu = paquete.getMmenu();
                                for (int d = _mainMenu.getMenu().size(); d > 0; d--) {
                                    Menu menu = _mainMenu.getMenu().get(d - 1);
                                    renderer = true;

                                    if (!renderer) {

                                        _mainMenu.getMenu().remove(d);
                                    } else {
                                        if (menu.getSub() != null) {
                                            for (int e = menu.getSub().size(); e > 0; e--) {
                                                MenuItem menuItem = menu.getSub().get(e - 1);
                                                renderer = true;

                                                if (!renderer) {

                                                    menu.getSub().remove(e - 1);
                                                } else {

                                                    //bis
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

        usuario.setRoot(root);

        /*GET MAIN MENU*/
        Object obj = root.getSistemas().stream().filter(s -> s.getCo_sistem() == usuario.getCo_sistem()).findAny().orElse(null);
        if (obj != null) {
            obj = ((Sistema) obj).getSubsistemas().stream().filter(s -> s.getCo_subsis() == usuario.getCo_subsis()).findAny().orElse(null);
            if (obj != null) {
                obj = ((Subsistema) obj).getPaquetes().stream().filter(s -> s.getCo_paquet() == usuario.getCo_paquet()).findAny().orElse(null);
                if (obj != null) {
                    usuario.setMainMenu(((Paquete) obj).getMmenu());
                }
            }
        }

        System.out.println("usuario = " + usuario);

        return usuario;
    }

    public List<PermisbloDTO> getPermisBy(String criteria) {
        List<PermisbloDTO> permisbloDTOS = new ArrayList<>();
        for (PermisbloDTO permisbloDTO : permisbloDTO) {
            if (permisbloDTO.getCo_elemen().contentEquals(criteria)) {
                permisbloDTOS.add(permisbloDTO);
            }
        }
        return permisbloDTOS;
    }

    public boolean evaluar(int codigo, List<PermisbloDTO> permisbloDTOS) {
        boolean r = true;

        for (int i = 0; i < permisbloDTOS.size(); i++) {
            PermisbloDTO permisbloDTO = permisbloDTOS.get(i);

            if (permisbloDTO.getId_elemen() == codigo) {
                r = false;
                i = 100000;
            }
        }

        return r;
    }

    public String getMessage() {
        return regsesiniDTO.getDe_mensaj();
    }
}
