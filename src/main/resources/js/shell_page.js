/*(P989115793P)MAIN JS FILE TO RUN! ALL!*/
var ValpagJson = Java.type('com.acceso.wfcore.utils.ValpagJson');
var Row = Java.type('com.acceso.wfcore.utils.RowJson');
var Reg = Java.type('com.acceso.wfcore.utils.RegJson');
var JsonResponse = Java.type('com.acceso.wfweb.utils.JsonResponse');
var OK64 = Java.type('com.acceso.wfweb.utils.JsonResponseP');
var PROPAG = Java.type('com.acceso.wfweb.utils.JsonResponseP');
var List = Java.type('java.util.ArrayList');
var Map = Java.type('java.util.HashMap');
var StringUtils = Java.type('org.apache.commons.lang3.StringUtils');
var gson_api = Java.type('com.google.gson.Gson');

//APIs
var main_api_acr_data_api = Java.type('com.acceso.wfcore.apis.DataAPI');
var main_api_acr_http_api = Java.type('com.acceso.wfcore.apis.HttpAPI');
var main_api_acr_shell_api = Java.type('com.acceso.wfcore.apis.ShellAPI');
var main_api_acr_cache_api = Java.type('com.acceso.wfcore.apis.CacheAPI');
var main_api_acr_messa_api = Java.type('com.acceso.wfcore.apis.MessageAPI');
var DATA = new main_api_acr_data_api();
var HTTP = new main_api_acr_http_api();
var SHELL = new main_api_acr_shell_api();
var CACHE = new main_api_acr_cache_api();
var MSG = new main_api_acr_messa_api();
var PARAM = Java.type('com.acceso.wfcore.utils.Param');
var GSON = new gson_api();

var RETORNO_OK = "{}";
var MSG_TYPE_INFO = "info";
var MSG_TYPE_SUCCESS = "success";
var MSG_TYPE_ERROR = "error";
var MSG_TYPE_WARNING = "warning";

var MSG_POSITION_TOPRIGHT = "md-toast-top-right";
var MSG_POSITION_TOPLEFT = "md-toast-top-left";
var MSG_POSITION_BOTTOMRIGHT = "md-toast-bottom-right";
var MSG_POSITION_BOTTOMLEFT = "md-toast-bottom-left";
var DO_POST_LOAD_DATA;

function COALESCE(obj, def) {
    return obj ? obj : def;
}

function NULLIF(obj1, obj2) {
    return (obj1 == obj2) ? null : obj1;
}

function VALID(obj, defobj) {
    if (obj === undefined) {
        return (defobj === undefined) ? null : defobj;
    } else {
        return obj;
    }
}

function UPPER(object) {
    return object.toUpperCase();
}

function LOWER(object) {
    return object.toLowerCase();
}

function GET_DO_POST_LOAD_DATA() {
    return DO_POST_LOAD_DATA;
}

function ERROR(msg) {
    return "X5964ERQ17{\"type\":\"USER\", \"message\":\"" + msg + "\"}";
}

function OK(no_accion, co_condes, ls_params, ls_pagina, ur_file) {
    return new PROPAG(no_accion, co_condes, ls_params, ls_pagina, ur_file);
}

function OK2(opts) {
    return new OK64(opts);
}

// function do_valpag(ID_FRAWOR, CO_CONTEN, CO_PAGINA, CONPAR, ID_SESION, CO_USUARI, ID_FRAANT) {
function do_valpag(ID_FRAWOR, CO_CONTEN, CO_PAGINA, CONPAR, ID_SESION, USUARI, ID_FRAANT) {
    /*SET*/
    DATA.setCo_usuari(USUARI.co_usuari);
    DATA.setId_sesion(ID_SESION);
    DATA.setId_frawor(ID_FRAWOR);
    DATA.setCo_pagina(CO_PAGINA);
    DATA.setNo_escena('VALPAG');

    var VALPAGJS = null;
    var LS_CONPAR = JSON.parse(CONPAR);

    USUARI_DATA_VALPAG

    DO_POST_LOAD_DATA = '' + DO_POST_LOAD_DATA;
    return VALPAGJS;
}

// function do_propag(ID_FRAWOR, CO_CONTEN, CO_PAGINA, CO_PAGBOT, CONPAR, REGIST, ID_SESION, CO_USUARI) {
function do_propag(ID_FRAWOR, CO_CONTEN, CO_PAGINA, CO_PAGBOT, CONPAR, REGIST, ID_SESION, USUARI) {
    /*SET*/
    DATA.setCo_usuari(USUARI.co_usuari);
    DATA.setId_sesion(ID_SESION);
    DATA.setId_frawor(ID_FRAWOR);
    DATA.setCo_conten(CO_CONTEN);
    DATA.setCo_pagina(CO_PAGINA);
    DATA.setNo_escena('PROPAG');

    var PROPAGJS = null;
    var LS_REGIST = JSON.parse(REGIST);
    var LS_CONPAR = JSON.parse(CONPAR);

    USUARI_DATA_PROPAG

    return PROPAGJS;
}

function do_propagg(ID_FRAWOR, CO_CONTEN, CO_PAGINA, CO_PAGBOT, CONPAR, ALLREG, ID_SESION, USUARI) {
    /*SET*/
    DATA.setCo_usuari(USUARI.co_usuari);
    DATA.setId_sesion(ID_SESION);
    DATA.setId_frawor(ID_FRAWOR);
    DATA.setCo_conten(CO_CONTEN);
    DATA.setCo_pagina(CO_PAGINA);
    DATA.setNo_escena('PROPAG');

    var PROPAGJS = null;
    var LS_ALLREG = JSON.parse(ALLREG);
    var LS_CONPAR = JSON.parse(CONPAR);

    USUARI_DATA_PROPAG

    return PROPAGJS;
}

function do_compag(ID_FRAWOR, CO_CONTEN, CO_PAGINA, CO_PAGREG, CONPAR, ID_SESION, USUARI, ID_FRAANT) {
    /*SET*/
    DATA.setCo_usuari(USUARI.co_usuari);
    DATA.setId_sesion(ID_SESION);
    DATA.setId_frawor(ID_FRAWOR);
    DATA.setCo_conten(CO_CONTEN);
    DATA.setCo_pagina(CO_PAGINA);
    DATA.setNo_escena('COMPAG');

    var COMPAGJS = null;
    var LS_CONPAR = JSON.parse(CONPAR);

    /*MAIN LOGIC TO RUN*/
    USUARI_DATA_COMPAG

    return COMPAGJS;
}

// function do_dinpag(ID_FRAWOR, CO_CONTEN, CO_PAGINA, CO_PAGREG, VA_PAGREG, CONPAR, REGIST, ID_SESION, CO_USUARI, ID_FRAANT) {
function do_dinpag(ID_FRAWOR, CO_CONTEN, CO_PAGINA, CO_PAGREG, VA_PAGREG, CONPAR, REGIST, ALLREG, ID_SESION, USUARI, ID_FRAANT) {
    /*SET*/
    print('here');
    DATA.setCo_usuari(USUARI.co_usuari);
    DATA.setId_sesion(ID_SESION);
    DATA.setId_frawor(ID_FRAWOR);
    DATA.setCo_conten(CO_CONTEN);
    DATA.setCo_pagina(CO_PAGINA);
    DATA.setNo_escena('DINPAG');

    var PROPAGJS = null;
    var LS_REGIST = JSON.parse(REGIST);
    var LS_ALLREG = null;
    try {
        LS_ALLREG = JSON.parse(ALLREG);
        print('existe>' + LS_ALLREG);
        print('existe>' + LS_ALLREG.length);
        LS_ALLREG = LS_ALLREG[0];
        print('*existe>' + LS_ALLREG);
    } catch (e) {
    }
    var LS_CONPAR = JSON.parse(CONPAR);

    USUARI_DATA_DINPAG

    return PROPAGJS;
}

function DECODE(object) {
    return decodeURIComponent(object);
}