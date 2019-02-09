package com.acceso.wfcore.utils;

/**
 *
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


    /*===CACHE===*/
    public static final String CACHE_MAIN_MENUTREE = "CACHE_MAIN_MENUTREE";
    public static final String CACHE_MAIN_CONTAINER = "CACHE_MAIN_CONTAINER";


    /*===KERNEL===*/
    public static final String QUERY_MAINTREE = "wfsistem.ppmenugeneral_web(";

    /*====PDF====*/
    public static final String PDF_ORIENTATION_PORTRAIT="portrait";
    public static final String PDF_ORIENTATION_LANDSCAPE="landscape";
}
