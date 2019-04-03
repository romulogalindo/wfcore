/*MAIN JS FILE TO RUN! ALL!*/
var ValpagJson = Java.type('com.acceso.wfcore.utils.ValpagJson');
var Row = Java.type('com.acceso.wfcore.utils.RowJson');
var Reg = Java.type('com.acceso.wfcore.utils.RegJson');
var JsonResponse = Java.type('com.acceso.wfweb.utils.JsonResponse');
var ArrayList = Java.type('java.util.ArrayList');

var main_api_acr_data_api = Java.type('com.acceso.wfcore.apis.DataAPI');
var main_api_acr_http_api = Java.type('com.acceso.wfcore.apis.HttpAPI');
var main_api_acr_shell_api = Java.type('com.acceso.wfcore.apis.ShellAPI');
var main_api_acr_cache_api = Java.type('com.acceso.wfcore.apis.CacheAPI');
var DATA = new main_api_acr_data_api();
var HTTP = new main_api_acr_http_api();
var SHELL = new main_api_acr_shell_api();
var CACHE = new main_api_acr_cache_api();

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

function do_valpag(ID_FRAWOR, CO_CONTEN, CO_PAGINA, CONPAR, CO_USUARI, ID_FRAANT) {
    var VALPAGJS = null;
    print('CONPAR:' + CONPAR)
    var LS_CONPAR = JSON.parse(CONPAR);

    USUARI_DATA_JS_TEXT

    return VALPAGJS;
}