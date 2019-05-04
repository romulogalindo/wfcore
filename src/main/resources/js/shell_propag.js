/*MAIN JS FILE TO RUN! ALL!*/
var main_api_acr_data_api = Java.type('com.acceso.wfcore.apis.DataAPI');
var main_api_acr_http_api = Java.type('com.acceso.wfcore.apis.HttpAPI');
var main_api_acr_shell_api = Java.type('com.acceso.wfcore.apis.ShellAPI');
var main_api_acr_cache_api = Java.type('com.acceso.wfcore.apis.CacheAPI');
var JsonResponseP = Java.type('com.acceso.wfweb.utils.JsonResponseP');
var PARAM = Java.type('com.acceso.wfcore.utils.Param');
var List = Java.type('java.util.ArrayList');
var DATA = new main_api_acr_data_api();
var HTTP = new main_api_acr_http_api();
var SHELL = new main_api_acr_shell_api();
var CACHE = new main_api_acr_cache_api();

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

function ERROR(msg) {
    return "X5964ERQ17{\"type\":\"USER\", \"message\":\"" + msg + "\"}";
}

// function PARAM(key, valor) {
//     this.key = key;
//     this.valor = valor;
// }

function OK(nparams) {
    // var rtn = "X5964IUP17{\"ACTION\":\"PROPAG\", \"PARAMS\":[";
    // print('>nparams:' + nparams + "?>>" + (nparams != undefined));
    // print('>nparams.length:' + nparams.length);
    // if (nparams != undefined) {
    //     for (var i=0; i < nparams.length; i++) {
    //         print('>>>param:' + nparams[i]);
    //         rtn += "{\"" + nparams[i].no_param + "\":\"" + nparams[i].va_param + "\"}";
    //     }
    // }
    // rtn += "]}";
    var nj= new JsonResponseP('OK', 'REDIRECT', nparams);
    return nj;
}

function do_propag(ID_FRAWOR, CO_CONTEN, CO_PAGINA, CO_PAGBOT, REGIST, CO_USUARI) {
    var PROPAGJS = null;
    var LS_REGIST = JSON.parse(REGIST);

    USUARI_DATA_JS_TEXT

    return PROPAGJS;
}
