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
function do_valpag(ID_FRAWOR, CO_CONTEN, CO_PAGINA) {

    /*USU*/
    //return API_DATA.JSON_VALPAG(API_DATA.SQL_LEAGCY('WFACR_CONX', 'select * from frawor2.pfvalpag(CO_CONTEN, ID_FRAWOR, CO_PAGINA)'));
    //cruces
    //sql1
    //sql2
    //CONPAR.getConpar("co_conpar_1");

    // var filas = [];
    // for (var i = 0; i < 1; i++) {
    //     var fila = new Fila();
    //     //logica para llenar los registros
    //
    //     var registro1 = new Registro(10);
    //     var registro2 = new Registro(20);
    //     var registro3 = new Registro(30);
    //     var registro4 = new Registro(40);
    //
    //     fila.addRegistro(registro1);
    //     fila.addRegistro(registro2);
    //     fila.addRegistro(registro3);
    //     fila.addRegistro(registro4);
    //
    //     filas[filas.length] = fila;
    // }
    //
    // return rows;
    //return API_DATA.VALPAG_LEGACY('WFACR_CONX', 'select * from frawor2.pfvalpag(CO_CONTEN, ID_FRAWOR, CO_PAGINA)');
    //USUARI_DATA_JS_TEXT
}

