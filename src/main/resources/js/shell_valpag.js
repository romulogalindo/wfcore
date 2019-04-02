/*MAIN JS FILE TO RUN! ALL!*/
var main_api_acr_data_api = Java.type('com.acceso.wfcore.apis.DataAPI');
var main_api_acr_http_api = Java.type('com.acceso.wfcore.apis.HttpAPI');
var main_api_acr_shell_api = Java.type('com.acceso.wfcore.apis.ShellAPI');
var main_api_acr_cache_api = Java.type('com.acceso.wfcore.apis.CacheAPI');
var DATA = new main_api_acr_data_api();
var HTTP = new main_api_acr_http_api();
var SHELL = new main_api_acr_shell_api();
var CACHE = new main_api_acr_cache_api();

function Row() {
    this.regs = [];

    this.getRegs = function () {
        return this.registros
    };

    this.setRegs = function () {
        return this.registros
    };

    this.addReg = function (registro) {
        this.registros[this.registros.length] = registro;
    };
}

function Reg(id_valpag, co_pagina, co_pagreg, va_pagreg, tx_pagreg, ti_pagreg, no_pagreg, ti_estreg, ur_pagreg) {
    this.id_valpag = -1;
    this.co_pagina = -1;
    this.co_pagreg = -1;
    this.va_pagreg = "";
    this.tx_pagreg = "";
    this.ti_pagreg = -1;
    this.no_pagreg = "";
    this.ti_estreg = "";
    this.ur_pagreg = "";

    this.setId_valpag = function (id_valpag) {
        this.id_valpag = id_valpag;
    };

    this.getId_valpag = function () {
        return this.id_valpag;
    };

    this.init = function (id_valpag, co_pagina, co_pagreg, va_pagreg, tx_pagreg, ti_pagreg, no_pagreg, ti_estreg, ur_pagreg) {
        this.id_valpag = id_valpag;
        this.co_pagina = co_pagina;
        this.co_pagreg = co_pagreg;
        this.va_pagreg = va_pagreg;
        this.tx_pagreg = tx_pagreg;
        this.ti_pagreg = ti_pagreg;
        this.no_pagreg = no_pagreg;
        this.ti_estreg = ti_estreg;
        this.ur_pagreg = ur_pagreg;
    }

    init();
}

class JsonResponse {
    constructor(status, result, aditional) {
        this._height = status;
        this._result = result;
        this._aditional = aditional;
    }

    set status(value) {
        this._status = value;
    }

    set result(value) {
        this._result = value;
    }

    set aditional(value) {
        this._aditional = value;
    }
}


function do_valpag(ID_FRAWOR, CO_CONTEN, CO_PAGINA, CONPAR, CO_USUARI, ID_FRAANT) {
    var VALPAGJS = null;
    var LS_CONPAR = JSON.parse(CONPAR);

    // print("ID_FRAWOR:" + ID_FRAWOR);
    // print("CO_CONTEN:" + CO_CONTEN);
    // print("CO_PAGINA:" + CO_PAGINA);
    // print("LS_COMPAR:" + LS_CONPAR);
    // print("?1?LS_COMPAR:" + LS_CONPAR.co_conpar_1);
    // print("CO_USUARI:" + CO_USUARI);
    // print("ID_FRAANT:" + ID_FRAANT);

    USUARI_DATA_JS_TEXT

    return VALPAGJS;
}

