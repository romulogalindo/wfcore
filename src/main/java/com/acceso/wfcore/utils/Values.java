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

    public static final String QUERYS_NATIVE_SELECT_CNX = "wfcore.cnx_select";
    public static final String QUERYS_NATIVE_INSERT_CNX = "wfcore.cnx_insert";
    public static final String QUERYS_NATIVE_UPDATE_CNX = "wfcore.cnx_update";
    public static final String QUERYS_NATIVE_DELETE_CNX = "wfcore.cnx_delete";
}
