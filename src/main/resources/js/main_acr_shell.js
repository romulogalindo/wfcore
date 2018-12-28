/*
* MAIN JS FILE TO RUN! ALL!
* */
var main_api_acr_data_api = Java.type('com.acceso.wfcore.apis.DataAPI');
var API_DATA = new main_api_acr_data_api();

/*
Ha de recibir los parametros que daran la idea de herencia
ID_FRAWOR : indicador unico de proceso
US : unidad de usuario(co_usuari, nombre)
* */
function do_valpag(ID_FRAWOR, US){

}
API_DATA.SQL('WFACR','select * from frawor2.pfvalpag()');