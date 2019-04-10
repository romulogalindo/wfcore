package com.acceso.wfcore.utils;

/**
 * @author Rómulo Galindo<romulogalindo@gmail.com>
 * Created on 3 dic. 2018, 19:06:46
 */
public class Values {

    public static final String PROP_NATIVE_CONEXION_NAME_KEY = "hibernate.connection.datasource";
    public static final String PROP_NATIVE_CONEXION_NAME_VALUE = "java:comp/env/poolPgWfcore";

    public static final String PROP_NATIVE_DRIVER_NAME_KEY = "hibernate.connection.driver_class";
    public static final String PROP_NATIVE_DRIVER_NAME_VALUE = "org.postgresql.Driver";

    public static final String SYSQUERYS_NATIVE_GET_ALLCNX = "wfcore.sys_get_all_cnx";

    public static final String SYSQUERYS_NATIVE_GET_STATUS = "get.status";

    public static final String QUERYS_NATIVE_SELECT_CNX = "wfcore.cnx_select";
    public static final String QUERYS_NATIVE_GRABAR_CNX = "wfcore.cnx_update";
    public static final String QUERYS_NATIVE_DELETE_CNX = "wfcore.cnx_delete";

    public static final String QUERYS_NATIVE_SELECT_SISTEMA = "wfcore.sistema_select";
    public static final String QUERYS_NATIVE_GRABAR_SISTEMA = "wfcore.sistema_update";
    public static final String QUERYS_NATIVE_DELETE_SISTEMA = "wfcore.sistema_delete";

    public static final String QUERYS_NATIVE_SELECT_PAGTIT = "frawor4.tcpagtit";
    public static final String QUERYS_NATIVE_SELECT_PAGREG = "frawor4.tcpagreg";

    public static final String QUERYS_NATIVE_SELECT_SUBSISTEMA = "wfcore.subsistema_select";
    public static final String QUERYS_NATIVE_GRABAR_SUBSISTEMA = "wfcore.subsistema_update";
    public static final String QUERYS_NATIVE_DELETE_SUBSISTEMA = "wfcore.subsistema_delete";

    public static final String QUERYS_NATIVE_SELECT_PAQUETE = "wfcore.paquete_select";
    public static final String QUERYS_NATIVE_GRABAR_PAQUETE = "wfcore.paquete_update";
    public static final String QUERYS_NATIVE_DELETE_PAQUETE = "wfcore.paquete_delete";

    public static final String QUERYS_NATIVE_SELECT_MODULO = "wfcore.modulo_select";
    public static final String QUERYS_NATIVE_SELECT_MODULO_MENU = "wfcore.modulo_select_menu";
    public static final String QUERYS_NATIVE_GRABAR_MODULO = "wfcore.modulo_update";
    public static final String QUERYS_NATIVE_DELETE_MODULO = "wfcore.modulo_delete";

    public static final String QUERYS_NATIVE_SELECT_USUARIO = "wfcore.usuario_select";
    public static final String QUERYS_NATIVE_GRABAR_USUARIO = "wfcore.usuario_update";
    public static final String QUERYS_NATIVE_DELETE_USUARIO = "wfcore.usuario_delete";

    public static final String QUERYS_NATIVE_GET_SCRIPT = "wfcore.get_Script";

    public static final String QUERYS_NATIVE_SELECT_PERMIS = "wfcore.permis_select";
    public static final String QUERYS_NATIVE_GRABAR_PERMIS = "wfcore.permis_update";

    public static final String QUERYS_NATIVE_SELECT_MENU_SISTEMA = "wfcore.menu_sistema";
    public static final String QUERYS_NATIVE_SELECT_MENU_SUBSISTEMA = "wfcore.menu_subsistema";
    public static final String QUERYS_NATIVE_SELECT_MENU_PAQUETE = "wfcore.menu_paquete";
    public static final String QUERYS_NATIVE_SELECT_MENU_MODPAD = "wfcore.menu_modpad";
    public static final String QUERYS_NATIVE_SELECT_MENU_SUBMOD = "wfcore.menu_submod";
    public static final String QUERYS_NATIVE_DELETE_MENU = "wfcore.menu_delete";
    public static final String QUERYS_NATIVE_GRABAR_MENU = "wfcore.menu_update";

    public static final String QUERYS_NATIVE_SELECT_CONTENEDOR = "wfcore.contenedor_select";

    public static final String QUERYS_NATIVE_SELECT_PAGINA = "wfcore.pagina_select";
//    public static final String QUERYS_NATIVE_SELECT_NEW_PAGINA = "wfcore.pagina_new";
//    public static final String QUERYS_NATIVE_SELECT_UPDATE_PAGINA = "wfcore.pagina_update";
    public static final String QUERYS_NATIVE_SAVE_PAGINA = "wfcore.pbpagina_save";

    public static final String QUERYS_NATIVE_SELECT_BUTTONS = "wfcore.pbboton_select";
    public static final String QUERYS_NATIVE_SAVE_BUTTON = "wfcore.pbboton_save";
    public static final String QUERYS_NATIVE_DELETE_BUTTON = "wfcore.pbboton_delete";

    public static final String QUERYS_NATIVE_SELECT_PARAMETRO = "wfcore.parametro_select";
    public static final String QUERYS_NATIVE_SELECT_PARAMETRO_CONTEN = "wfcore.parametro_select_conten";

    public static final String QUERYS_NATIVE_SELECT_REGISTRO = "wfcore.registro_select";

    public static final int QUERYS_SYSTEM_TIMEOUT = 10;
    public static final int QUERYS_USER_TIMEOUT = 10;
    public static final int HTTP_REQUEST_TIMEOUT = 10;
    public static final int SHELL_REQUEST_TIMEOUT = 10;

    public static final String WEBSOCKET_ENDPOINT_URL = "ws://127.0.0.1:8080/ws/main";

    /*===CACHE===*/
    public static final String CACHE_MAIN_MENUTREE = "CACHE_MAIN_MENUTREE";
    public static final String CACHE_MAIN_CONTAINER = "CACHE_MAIN_CONTAINER";
    public static final String CACHE_MAIN_VALPAGJS = "CACHE_MAIN_VALPAGJS";
    public static final String CACHE_MAIN_PROPAGJS = "CACHE_MAIN_PROPAGJS";
    public static final String CACHE_MAIN_FILEX = "CACHE_MAIN_FILEX";


    /*===KERNEL===*/
    public static final String QUERY_MAINTREE = "wfsistem.ppmenugeneral_web(";

    /*====PDF====*/
    public static final String PDF_ORIENTATION_PORTRAIT = "portrait";
    public static final String PDF_ORIENTATION_LANDSCAPE = "landscape";

    public static final int UPLOAD_FILE_MAXSIZE = 1024 * 1024 * 8;
}
