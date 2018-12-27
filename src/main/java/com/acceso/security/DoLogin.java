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

    public boolean SecurityLogin(RequestManager requestManager) {

        System.out.println("WFCoreListener.APP.getLoginCTRL().getLogin_param_username() = " + WFCoreListener.APP);
        System.out.println("WFCoreListener.APP.getLoginCTRL().getLogin_param_username() = " + WFCoreListener.APP.getLoginCTRL());
        System.out.println("WFCoreListener.APP.getLoginCTRL().getLogin_param_username() = " + WFCoreListener.APP.getLoginCTRL().getLogin_param_username());
        String username = requestManager.getParam(WFCoreListener.APP.getLoginCTRL().getLogin_param_username());
        String password = requestManager.getParam(WFCoreListener.APP.getLoginCTRL().getLogin_param_password());
        String remoteip = requestManager.getIp();

        System.out.println("username = " + username);
        System.out.println("password = " + password);
        System.out.println("remoteip = " + remoteip);

        SecurityDAO securityDAO = new SecurityDAO();
        regsesiniDTO = securityDAO.regsesini(username, Security.toMD5(password), remoteip);
//        permisbloDTO = securityDAO.getListBloq(regsesiniDTO.getCo_usuari());
        securityDAO.close();

        //Logica de seguridad
        if (regsesiniDTO.getCo_mensaj() == LOGIN_OK) {

            return true;
        } else {

            return false;
        }
    }

    public Usuario getUsuario() {
        //security
        Usuario usuario = new Usuario();
        usuario.setId_sesion(regsesiniDTO.getId_sesion());
        usuario.setCo_usuari(regsesiniDTO.getCo_usuari());
        usuario.setNo_usulog(regsesiniDTO.getNo_usulog());
        usuario.setNo_usuari(regsesiniDTO.getNo_usuari());
        usuario.setNo_imgusu(regsesiniDTO.getNo_imgusu());

        SecurityDAO securityDAO = new SecurityDAO();
        permisbloDTO = securityDAO.getListBloq((int) regsesiniDTO.getCo_usuari());
        securityDAO.close();

        //elementos que han sido capados por la capa de securidad ->>> desde la cache
        Root root = (Root) WFCoreListener.APP.getCacheService().getZeroDawnCache().getSpace(Values.CACHE_MAIN_MENUTREE).get("ROOT_TREE");

        MainMenu mainMenu = ((Root) WFCoreListener.APP.getCacheService().getZeroDawnCache().getSpace(Values.CACHE_MAIN_MENUTREE).get("ROOT_TREE")).getSistemas().get(0).getSubsistemas().get(0).getPaquetes().get(0).getMmenu();

        List<PermisbloDTO> permisSistema = getPermisBy("SISTEMA");
        List<PermisbloDTO> permisSubSistema = getPermisBy("SUBSISTEMA");
        List<PermisbloDTO> permisPaquete = getPermisBy("PAQUETE");

        List<PermisbloDTO> permisRoot = getPermisBy("MOD-PADRE");
        List<PermisbloDTO> permisMenu1 = getPermisBy("MOD-NVL1");
        List<PermisbloDTO> permisMenu2 = getPermisBy("MOD-NVL2");
        List<PermisbloDTO> permisMenu3 = getPermisBy("MOD-NVL3");
        List<PermisbloDTO> permisMenu4 = getPermisBy("MOD-NVL4");
        List<PermisbloDTO> permisMenu5 = getPermisBy("MOD-NVL5");

        for (Sistema sistema : root.getSistemas()) {
            boolean renderer = evaluar(sistema.getCo_sistem(), permisSistema);
            sistema.setRenderer(renderer);

            if (renderer) {
                for (Subsistema subsistema : sistema.getSubsistemas()) {
                    renderer = evaluar(subsistema.getCo_subsis(), permisSubSistema);
                    subsistema.setRenderer(renderer);

                    if (renderer) {
                        for (Paquete paquete : subsistema.getPaquetes()) {
                            renderer = evaluar(paquete.getCo_paquet(), permisPaquete);
                            paquete.setRenderer(renderer);
                        }
                    }
                }
            }
        }
        usuario.setRoot(root);
        usuario.setMainMenu(mainMenu);

        return usuario;
    }

    public List<PermisbloDTO> getPermisBy(String criteria) {
        List<PermisbloDTO> permisbloDTOS = new ArrayList<>();
        for (PermisbloDTO permisbloDTO : permisbloDTO) {
            if (permisbloDTO.getCo_elemen().contentEquals(criteria))
                permisbloDTOS.add(permisbloDTO);
        }
        return permisbloDTOS;
    }

    public boolean evaluar(int codigo, List<PermisbloDTO> permisbloDTOS) {
        boolean r = false;
        for (PermisbloDTO permisbloDTO : permisbloDTOS) {
            if (permisbloDTO.getId_elemen() == codigo) {
                r = true;
                break;
            }
        }
        return r;
    }

    public String getMessage() {
        return regsesiniDTO.getDe_mensaj();
    }
}
