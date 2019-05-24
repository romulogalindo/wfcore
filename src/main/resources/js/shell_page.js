/*(P989115793P)MAIN JS FILE TO RUN! ALL!*/
var ValpagJson = Java.type('com.acceso.wfcore.utils.ValpagJson');
var Row = Java.type('com.acceso.wfcore.utils.RowJson');
var Reg = Java.type('com.acceso.wfcore.utils.RegJson');
var JsonResponse = Java.type('com.acceso.wfweb.utils.JsonResponse');
var PROPAG = Java.type('com.acceso.wfweb.utils.JsonResponseP');
var List = Java.type('java.util.ArrayList');
var StringUtils = Java.type('org.apache.commons.lang3.StringUtils');

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

var RETORNO_OK = "{}";

/*COALESCE(obj, def)*/
function COALESCE(obj, def) {
    if (obj) {
        return obj
    } else {
        return def;
    }
}

/*NULLIF(obj1, obj2)*/
function NULLIF(obj1, obj2) {
    if (obj1 == obj2) {
        return null;
    } else {
        return obj1;
    }
}

/*VAR:Function Ejecuta post*/
var DO_POST_LOAD_DATA;

function do_valpag(ID_FRAWOR, CO_CONTEN, CO_PAGINA, CONPAR, ID_SESION, CO_USUARI, ID_FRAANT) {
    /*SET*/
    DATA.setCo_usuari(CO_USUARI);
    DATA.setId_sesion(ID_SESION);
    DATA.setId_frawor(ID_FRAWOR);
    DATA.setCo_conten(CO_CONTEN);
    DATA.setCo_pagina(CO_PAGINA);
    DATA.setNo_escena('VALPAG');

    var VALPAGJS = null;
    var LS_CONPAR = JSON.parse(CONPAR);

    /*CAN USE DO_POST_LOAD_DATA*/

    /*MAIN LOGIC TO RUN*/
    USUARI_DATA_VALPAG

    DO_POST_LOAD_DATA = '' + DO_POST_LOAD_DATA;
    return VALPAGJS;
}

function GET_DO_POST_LOAD_DATA() {
    return DO_POST_LOAD_DATA;
}

function ERROR(msg) {
    return "X5964ERQ17{\"type\":\"USER\", \"message\":\"" + msg + "\"}";
}

function OK(no_accion, co_condes, ls_params, ls_pagina) {
    //PROPAG
    var nj = new PROPAG(no_accion, co_condes, ls_params, ls_pagina);
    return nj;
}

function do_propag(ID_FRAWOR, CO_CONTEN, CO_PAGINA, CO_PAGBOT, CONPAR, REGIST, ID_SESION, CO_USUARI) {
    /*SET*/
    DATA.setCo_usuari(CO_USUARI);
    DATA.setId_sesion(ID_SESION);
    DATA.setId_frawor(ID_FRAWOR);
    DATA.setCo_conten(CO_CONTEN);
    DATA.setCo_pagina(CO_PAGINA);
    DATA.setNo_escena('COMPAG');

    var PROPAGJS = null;
    var LS_REGIST = JSON.parse(REGIST);
    var LS_CONPAR = JSON.parse(CONPAR);

    USUARI_DATA_PROPAG

    return PROPAGJS;
}

function do_propagg(ID_FRAWOR, CO_CONTEN, CO_PAGINA, CO_PAGBOT, CONPAR, ALLREG, ID_SESION, CO_USUARI) {
    /*SET*/
    DATA.setCo_usuari(CO_USUARI);
    DATA.setId_sesion(ID_SESION);
    DATA.setId_frawor(ID_FRAWOR);
    DATA.setCo_conten(CO_CONTEN);
    DATA.setCo_pagina(CO_PAGINA);
    DATA.setNo_escena('COMPAG');

    var PROPAGJS = null;
    // var LS_REGIST = JSON.parse(REGIST);
    print('!ALLREG:' + ALLREG);
    var LS_ALLREG = JSON.parse(ALLREG);
    var LS_CONPAR = JSON.parse(CONPAR);

    USUARI_DATA_PROPAG

    return PROPAGJS;
}

function do_compag(ID_FRAWOR, CO_CONTEN, CO_PAGINA, CO_PAGREG, CONPAR, ID_SESION, CO_USUARI, ID_FRAANT) {
    //print('do_compag>>>>ID_FRAWOR=' + ID_FRAWOR + ', CO_CONTEN=' + CO_CONTEN + ', CO_PAGINA=' + CO_PAGINA + ', CO_PAGREG=' + CO_PAGREG + ', CONPAR=' + CONPAR + ', CO_USUARI=' + CO_USUARI + ', ID_FRAANT=' + ID_FRAANT + '');
    /*SET*/
    DATA.setCo_usuari(CO_USUARI);
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

function do_dinpag(){
    
}