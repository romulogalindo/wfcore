/*MAIN JS FILE TO RUN! ALL!*/
var main_api_acr_data_api = Java.type('com.acceso.wfcore.apis.DataAPI');
var API_DATA = new main_api_acr_data_api();

function Fila() {
    this.registros = [];

    this.getRegistros = function () {
        return this.registros
    };

    this.setRegistros = function () {
        return this.registros
    };

    this.addRegistro = function (registro) {
        this.registros[this.registros.length] = registro;
    };
}

function Registro(id_valpag, co_pagina, co_pagreg, va_pagreg, tx_pagreg, ti_pagreg, no_pagreg, ti_estreg, ur_pagreg) {
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

}

//function do_valpag(ID_FRAWOR, CO_CONTEN, CO_PAGINA, CONPAR, CO_USUARI, FRAANT) {
function do_valpag(ID_FRAWOR, CO_CONTEN, CO_PAGINA, LS_COMPAR, CO_USUARI, ID_FRAANT) {
    var VALPAGJS = null;

    print("ID_FRAWOR" + ID_FRAWOR);
    print("CO_CONTEN" + CO_CONTEN);
    print("CO_PAGINA" + CO_PAGINA);
    print("LS_COMPAR" + LS_COMPAR);
    print("CO_USUARI" + CO_USUARI);
    print("ID_FRAANT" + ID_FRAANT);

    USUARI_DATA_JS_TEXT

    return VALPAGJS;
}

