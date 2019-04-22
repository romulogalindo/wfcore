package com.acceso.security.utils;

/*
QUERY = [SQCHEMA].[STORENAME]
QUERY = [SQCHEMA].[TABLE]
 */
public class Values {

    public static final String wfsistem_ppregsesini_KEY = "query_security_ppregsesini";
    public static final String wfsistem_ppregsesini_VALUE = "select * from wfsistem.ppregsesini(:p_username, :p_password, :p_remoteip,:p_sistema) as inises";

    public static final String wfsistem_ppregsesiniweb_KEY = "query_security_ppregsesini_web";
    public static final String wfsistem_ppregsesiniweb_VALUE = "select * from wfsistem.ppregsesini_fwweb(:p_username, :p_password, :p_remoteip) as inises";

//    public static final String QUERYS_SECURITY_REGSESINI = "query_security_ppregsesini";
    public static final String QUERYS_SECURITY_REGSESINI_FOREING = "query_security_ppregsesini_foreing";
//    public static final String QUERYS_SECURITY_REGSESINI_WEB = "query_security_ppregsesini_web";
    public static final String QUERYS_SECURITY_PERMISBLO_WEB = "query_security_pblist_permis_blo_web";
}
