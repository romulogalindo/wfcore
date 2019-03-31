/*MAIN JS FILE TO RUN! ALL!*/
var main_api_acr_data_api = Java.type('com.acceso.wfcore.apis.DataAPI');
var main_api_acr_http_api = Java.type('com.acceso.wfcore.apis.HttpAPI');
var main_api_acr_shell_api = Java.type('com.acceso.wfcore.apis.ShellAPI');
var main_api_acr_cache_api = Java.type('com.acceso.wfcore.apis.CacheAPI');
var DATA = new main_api_acr_data_api();
var HTTP = new main_api_acr_http_api();
var SHELL = new main_api_acr_shell_api();
var CACHE = new main_api_acr_cache_api();

var RETORNO_OK = "{}";

function do_propag(ID_FRAWOR, CO_CONTEN, CO_PAGINA, CO_PAGBOT, REGIST, CO_USUARI) {
    var PROPAGJS = null;
    var LS_REGIST = JSON.parse(REGIST);

    USUARI_DATA_JS_TEXT

    return PROPAGJS;
}
