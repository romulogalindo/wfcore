package com.acceso.security;

import com.acceso.security.daos.SecurityDAO;
import com.acceso.security.daos.SecurityLDAO;
import com.acceso.security.dtos.PermisbloDTO;
import com.acceso.security.dtos.RegsesiniDTO;
import com.acceso.wfcore.kernel.WFIOAPP;
import com.acceso.wfcore.utils.Security;
import com.acceso.wfcore.utils.Util;
import com.acceso.wfcore.utils.Values;
import com.acceso.wfweb.units.Usuario;
import com.acceso.wfweb.units.UsuarioLDAP;
import com.acceso.wfweb.utils.RequestManager;
import com.acceso.wfweb.web.*;
import org.apache.commons.lang3.SerializationUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*

 * */
public class DoLogin {

    public static final int LOGIN_OK = 200;
    public static final int LOGIN_FAIL_USUARI = 404;
    public static final int LOGIN_FAIL_PASSWORD = 403;

    protected RegsesiniDTO regsesiniDTO;
    protected UsuarioLDAP usuarioLDAP;
    protected List<PermisbloDTO> permisbloDTO;

    public boolean SecurityLogin(RequestManager requestManager) throws Exception {

        String username = requestManager.getParam(WFIOAPP.APP.getLoginCTRL().getLogin_param_username());
        String password = requestManager.getParam(WFIOAPP.APP.getLoginCTRL().getLogin_param_password());
        String remoteip = requestManager.getIp();

        //LOGIN--A/1
        boolean ENABLED_LOGIN_LDAP = Util.toBoolean(WFIOAPP.APP.getDataSourceService().getValueOfKey("ENABLED_LOGIN_LDAP"), false);
        System.out.println("ENABLED_LOGIN_LDAP = " + ENABLED_LOGIN_LDAP);

        //LOGIN--A/2
        if (ENABLED_LOGIN_LDAP) {
            //autenticacion anexa
//            SecurityLDAO ldao = new SecurityLDAO("ldap://192.168.44.82:389", "cn=USER,cn=groups,ou=people,cn=admin,dc=acceso,dc=com,dc=pe");//ORIGINAL
//            SecurityLDAO ldao = new SecurityLDAO("ldap://10.3.3.111:389", "cn=admins,dc=correocrediticio,dc=pe");//NUEVO
            SecurityLDAO ldao = new SecurityLDAO("ldap://192.168.4.138:389", "cn=admin,dc=acceso,dc=com,dc=pe");//NUEVO
//            usuarioLDAP = ldao.connect(username, password);
            usuarioLDAP = ldao.connect("acceso", "xxB6gUBmGd");
            System.out.println("usuarioLDAP = " + usuarioLDAP);
            if (usuarioLDAP.isIl_conect()) {
                SecurityDAO securityDAO = new SecurityDAO();
                regsesiniDTO = securityDAO.regsesini(username, Security.toMD5(password), remoteip);
//        permisbloDTO = securityDAO.getListBloq(regsesiniDTO.getCo_usuari());
                securityDAO.close();
                //[FED]
                regsesiniDTO.setIp_remoto(remoteip);
                System.out.println("regsesiniDTO = " + regsesiniDTO);
            } else {
                regsesiniDTO = new RegsesiniDTO();
                regsesiniDTO.setCo_mensaj(404);
                regsesiniDTO.setDe_mensaj("LDAP-Credenciales invalidas.");
            }
        } else {
            //autenticacion tradicional
            SecurityDAO securityDAO = new SecurityDAO();
            regsesiniDTO = securityDAO.regsesini(username, Security.toMD5(password), remoteip);
//        permisbloDTO = securityDAO.getListBloq(regsesiniDTO.getCo_usuari());
            securityDAO.close();

            //[FED]
            regsesiniDTO.setIp_remoto(remoteip);
            System.out.println("regsesiniDTO = " + regsesiniDTO);
        }

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
        usuario.setNo_imgusu(regsesiniDTO.getAr_imgusu());

        usuario.setCo_sistem(regsesiniDTO.getCo_sistem());
        usuario.setCo_subsis(regsesiniDTO.getCo_subsis());
        usuario.setNo_correo(regsesiniDTO.getNo_correo());
        usuario.setNu_docide(regsesiniDTO.getNu_docide());
        usuario.setIp_remoto(regsesiniDTO.getIp_remoto());
//        usuario.setCo_paquet(regsesiniDTO.getCo_paquet());
        usuario.setLdap(regsesiniDTO.getLdap());
        usuario.setIl_schema(true);
        usuario.setFe_updpas(regsesiniDTO.getFe_updpas());

        SecurityDAO securityDAO = new SecurityDAO();
        permisbloDTO = securityDAO.getListBloq((int) regsesiniDTO.getCo_usuari());
        securityDAO.close();

        SecurityDAO securityfDAO = new SecurityDAO(WFIOAPP.APP.dataSourceService.getManager("wfacr").getNativeSession());
        securityfDAO.regsesinif(regsesiniDTO.getId_sesion(), regsesiniDTO.getCo_usuari(), regsesiniDTO.getIp_remoto());
        securityfDAO.close();

        //elementos que han sido capados por la capa de securidad ->>> desde la cache
        Root root = SerializationUtils.clone((Root) WFIOAPP.APP.getCacheService().getZeroDawnCache().getSpace(Values.CACHE_MAIN_MENUTREE).get("ROOT_TREE"));
//        System.out.println("root = " + root);
//        root = SerializationUtils.clone(root);
        MainMenu mainMenu;// = ((Root) WFIOAPP.APP.getCacheService().getZeroDawnCache().getSpace(Values.CACHE_MAIN_MENUTREE).get("ROOT_TREE")).getSistemas().get(0).getSubsistemas().get(0).getPaquetes().get(0).getMmenu();

        List<PermisbloDTO> permisSistema = getPermisBy("SISTEMA");
        List<PermisbloDTO> permisSubSistema = getPermisBy("SUBSISTEMA");
//        List<PermisbloDTO> permisPaquete = getPermisBy("PAQUETE");

        List<PermisbloDTO> permisRoot = getPermisBy("MOD-PADRE");
        List<PermisbloDTO> permisMenu1 = getPermisBy("MOD-NVL1");
//        List<PermisbloDTO> permisMenu2 = getPermisBy("MOD-NVL2");
//        List<PermisbloDTO> permisMenu3 = getPermisBy("MOD-NVL3");
//        List<PermisbloDTO> permisMenu4 = getPermisBy("MOD-NVL4");
//        List<PermisbloDTO> permisMenu5 = getPermisBy("MOD-NVL5");
        Map<String, MainMenu> mainMenus = new HashMap<>();

        Map<Integer, Object> ls_mensis = new HashMap<>();

        for (int a = root.getSistemas().size(); a > 0; a--) {
            Sistema sistema = root.getSistemas().get(a - 1);
            boolean renderer = evaluar(sistema.getCo_sistem(), permisSistema);
            System.out.println("*[" + sistema.getCo_sistem() + "::" + sistema.getNo_sistem() + "](" + (renderer ? "VISIBLE" : "OCULTO") + ")");

            if (!renderer) {
                root.getSistemas().remove(a - 1);

            } else {
                //verificar si es un sistema foraneo.. sino no llamar
                if (!sistema.getIl_sisfor()) {
                    for (int b = sistema.getSubsistemas().size(); b > 0; b--) {
                        Subsistema subsistema = sistema.getSubsistemas().get(b - 1);
                        renderer = evaluar(subsistema.getCo_subsis(), permisSubSistema);

                        System.out.println(" |----*[" + subsistema.getCo_subsis() + ":: " + subsistema.getNo_subsis() + "] (" + (renderer ? "VISIBLE" : "OCULTO") + ")");
                        if (!renderer) {

                            sistema.getSubsistemas().remove(b - 1);
                        } else {
                            MainMenu _mainMenu = subsistema.getMmenu();
                            mainMenus.put(sistema.getCo_sistem() + "" + subsistema.getCo_subsis(), _mainMenu);
                            System.out.println(" |--------*[" + (sistema.getCo_sistem() + "" + subsistema.getCo_subsis()) + ":MENU](" + (renderer ? "VISIBLE" : "OCULTO") + ")");

                            for (int d = _mainMenu.getMenu().size(); d > 0; d--) {
                                Menu menu = _mainMenu.getMenu().get(d - 1);
                                renderer = evaluar(menu.getCo_mensis(), permisRoot);

                                if (!renderer) {

                                    _mainMenu.getMenu().remove(d - 1);
                                } else {

                                    System.out.println(" |------------[" + menu.getCo_mensis() + ":" + menu.getName() + "] => " + (menu.getSub()) + "({" + renderer + "})");
                                    if (menu.getSub() != null) {
                                        for (int e = menu.getSub().size(); e > 0; e--) {
                                            MenuItem menuItem = menu.getSub().get(e - 1);
                                            renderer = evaluar(menuItem.getCo_mensis(), permisMenu1);

                                            if (!renderer) {

                                                menu.getSub().remove(e - 1);
                                            } else {
                                                if (getConten(menuItem.getUrl()) > -1) {
                                                    ls_mensis.put(getConten(menuItem.getUrl()), menuItem.getUrl());
                                                }

                                                System.out.println(" |-------------------[" + menuItem.getCo_mensis() + "::" + (menuItem.getName()) + "::" + menuItem.getUrl() + "]({" + (renderer ? "VISIBLE" : "OCULTO") + "})");
                                            }
                                        }
                                    } else if (menu.getUrl() != null) {
                                        if (getConten(menu.getUrl()) > -1) {
                                            ls_mensis.put(getConten(menu.getUrl()), menu.getUrl());
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
        usuario.setMainMenus(mainMenus);
        usuario.setLs_mensis(ls_mensis);
        System.out.println("mainMenus = " + mainMenus.size());

        /*GET MAIN MENU*/
        Object obj = root.getSistemas().stream().filter(s -> s.getCo_sistem() == usuario.getCo_sistem()).findAny().orElse(null);
        if (obj != null) {
            obj = ((Sistema) obj).getSubsistemas().stream().filter(s -> s.getCo_subsis() == usuario.getCo_subsis()).findAny().orElse(null);
//            if (obj != null) {
//                obj = ((Subsistema) obj).getPaquetes().stream().filter(s -> s.getCo_paquet() == usuario.getCo_paquet()).findAny().orElse(null);
            if (obj != null) {
                usuario.setMainMenu(((Subsistema) obj).getMmenu());
            }
//            }
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

    public Integer getConten(String url) {
        //wf?co_conten=20031
        System.out.println("Consultando =>>> " + url);
        String rpta = ("" + url).split("co_conten=")[1].split("&")[0];
        Integer co_conten = Util.toInt(rpta, -1);
        return co_conten;
    }

    public String getMessage() {
        return regsesiniDTO.getDe_mensaj();
    }
}
