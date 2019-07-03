var $D = document;
var $MAP = {};
var CO_PAGINA;
var TI_PAGINA;
var CO_CONTEN;
var ID_FRAWOR;
var IL_POPUP;
var TMP_INPUT_DATE;
var HTML_PAGINA;
var CALLED = false;
/*variables  de funcionalidad*/
var DYNAMIC = false;
var DYNAMIC_LS_REGIST = [];

//Google chart
var GDATA;

//constantes
const DATA_STATUS_OK = 'OK';
const DATA_STATUS_ERROR = 'ERROR';
const PAGE_TYPE_FORM = 'F';
const PAGE_TYPE_REPORT = 'R';
const PAGE_TYPE_CHART = 'C';
const PAGE_TYPE_MAPS = 'G';

function Parameter(co_pagreg, co_conpar) {
    this.conpar = co_conpar;
    this.pagreg = co_pagreg;

    this.getConpar = function () {
        return this.conpar;
    };

    this.getPagreg = function () {
        return this.pagreg;
    };
}

function overlay(show) {
    document.getElementById("myOverlay").style.display = show == true ? "block" : "none";
}

function w3_open() {
    document.getElementById("mySidebar").style.display = "block";
    overlay(true);
}

function w3_close() {
    document.getElementById("mySidebar").style.display = "none";
    overlay(false);
}

function viewtab(evt, cityName) {
    var i, x, tablinks;
    x = document.getElementsByClassName("menubloq");

    for (i = 0; i < x.length; i++) {
        x[i].style.display = "none";
    }

    tablinks = document.getElementsByClassName("tablink");
    for (i = 0; i < x.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" w3-red", "");
    }

    document.getElementById(cityName).style.display = "block";
    evt.currentTarget.className += " w3-red";
}

/*Codigo que sera heliminado*/
//function ls_hamoda() {
//    return document.getElementById('ls_hamoda') != undefined ? document.getElementById('ls_hamoda').value : null;
//}

function ifnull(val, def) {
    if (val == null | val == undefined) {
        return def
    } else
        return val;
}

function size_of_pagina() {
    height_table = document.getElementById('mainpagina').offsetHeight + 60;
    document.getElementById('height_table').value = '' + height_table;
}

function pagina() {
    //Seteamos el height para que lo absorva el iframe!<main.jsp
    size_of_pagina();
    /*SETEO DE VARIABLES*/
    ID_FRAWOR = document.getElementById('id_frawor').value;
    CO_CONTEN = document.getElementById('co_conten').value;
    CO_PAGINA = document.getElementById('co_pagina').value;
    IL_POPUP = document.getElementById('il_popup').value;
    TI_PAGINA = document.getElementById('ti_pagina').value;
    HTML_PAGINA = document.getElementById('PAG' + CO_PAGINA);

    doPagJson('/pangolin?co_conten=' + CO_CONTEN + '&co_pagina=' + CO_PAGINA + '&id_frawor=' + ID_FRAWOR);

    //despues de pintar la pagina
    /*
     var input_cansubmit = document.getElementsByTagName('INPUT');
     
     for (var i = 0; i < input_cansubmit.length; i++) {
     var input_submit = input_cansubmit[i];
     if (input_submit.getAttribute('type').toUpperCase() == 'TEXT') {
     input_submit.addEventListener('keyup', doformulariosubmit);
     }
     }
     */
}

function doformulariosubmit(keyEvent) {
    if (keyEvent.which == 10 || keyEvent.which == 13) {
        document.getElementsByClassName('wfbutton')[0].click();
    }
}

function pagina_onload(_data) {
    if (!CALLED) {
        CALLED = true;
    }

    if (_data.status == DATA_STATUS_OK) {
        if (_data.result != undefined && _data.result.rows != undefined && _data.result.rows.length > 0) {
            //prueba especial de carga?!
            HTML_PAGINA.removeAttribute('style');
            var rows = _data.result.rows;
            var dom2 = document.getElementById("row1") != undefined ? document.getElementById("row1").innerHTML : '';
            var tbody64;

            const ID_PAGINA = 'PAG' + CO_PAGINA;
            console.log('TI_PAGINA:' + TI_PAGINA + ', PAGE_TYPE_REPORT:' + PAGE_TYPE_REPORT + ", ===>" + (TI_PAGINA == PAGE_TYPE_REPORT));
            if (TI_PAGINA == PAGE_TYPE_REPORT) {
                tbody64 = HTML_PAGINA.getElementsByTagName('TBODY')[0];
                tbody64.innerHTML = '';
                itr = itr.replace('<tr>', '').replace('</tr>', '');
            }

            for (var i = 0; i < rows.length; i++) {
                if (TI_PAGINA == PAGE_TYPE_FORM)
                    loadFormulario64((i + 1), rows[i], _data.aditional, dom2);
                else
                    loadReporte64((i + 1), tbody64, rows[i]);
            }

            /*ON END LOAD*/
            /*SELECT*/
            var all_selects = document.getElementsByClassName('mdb-select');
//    console.log('ALL>SELECT>[' + CO_PAGINA + ']-*>>' + all_selects);
//    console.log('ALL>SELECT>[' + CO_PAGINA + ']-*>>' + all_selects.length);
            for (var i = 0; i < all_selects.length; i++) {
                var me_select = all_selects[i];
                if (me_select.tagName == 'SELECT' & me_select.getAttribute('class').indexOf('initialized') == -1) {
                    $(me_select).materialSelect();
//            console.log('Se ejecuto la create!');
                }
            }

            /*BEFORE VIEW*/
//            alert('antes de view!');
            eval('try{function xc() {this.newjspex = ' + _data.fnpost + ';} new xc().newjspex();}catch(e){console.log(\'WFAIO:\'+e)}');

            /*UPDATE HEIGHT*/
            size_of_pagina();
            if (CALLED) {
                window.parent.iframe2('PAG' + CO_PAGINA, height_table);
            }
        } else if (TI_PAGINA == PAGE_TYPE_CHART) {
            //validar el objeto de retorno llamado gdata
            //poner esto en el objeto gdata
            var GDATAX = [];
            GDATA = _data.result;
            for (var x = 0; x < GDATA.length; x++) {
                // console.log('GDATA[' + x + ']=' + GDATA[x]);
                var MDATA = [];
                for (var z = 0; z < GDATA[x].length; z++) {
                    // console.log('GDATA[' + x + '][' + z + ']=' + GDATA[x][z]);
                    MDATA[MDATA.length] = GDATA[x][z];
                }
                GDATAX[GDATAX.length] = MDATA;
            }
            GDATA = GDATAX;

            /*BEFORE VIEW*/
            eval('try{function xc() {this.newjspex = ' + _data.fnpost + ';} new xc().newjspex();}catch(e){console.log(\'WFAIO:\'+e)}');
            //devuevo actualizar el height;
            size_of_pagina();

            window.parent.iframe2('PAG' + CO_PAGINA, height_table);
        } else {
            document.getElementById('PAG' + CO_PAGINA).style.display = 'none';
            window.parent.iframe2('PAG' + CO_PAGINA, -1);
//            console.log('[pagina@\' + CO_PAGINA + \']rows unde fined!!= ' + _data.result);
        }

        document.getElementById('loader').style.display = 'none';
    } else {
        document.getElementById('content_table_loader').style.display = 'none';
        //seteo de valores
        document.getElementById('title_error').innerHTML = 'Error: P&aacute;gina ' + CO_PAGINA;
        document.getElementById('detail_error').innerHTML = _data.error.message;

        document.getElementById('card_error').style.display = 'block';
    }

    /*TOOLTIP*/
    $('[data-toggle="tooltip"]').tooltip();


}

function pagina_onload2(_data, rowid) {
    if (!CALLED) {
        CALLED = true;
    }

    if (_data.status == DATA_STATUS_OK) {
        if (_data.result != undefined && _data.result.rows != undefined && _data.result.rows.length == 1) {
            //prueba especial de carga?!
            HTML_PAGINA.removeAttribute('style');
            var rows = _data.result.rows;
            var dom2 = document.getElementById("row1") != undefined ? document.getElementById("row1").innerHTML : '';
            // var tbody64;

            const ID_PAGINA = 'PAG' + CO_PAGINA;
            console.log('TI_PAGINA:' + TI_PAGINA + ', PAGE_TYPE_REPORT:' + PAGE_TYPE_REPORT + ", ===>" + (TI_PAGINA == PAGE_TYPE_REPORT));

            // for (var i = 0; i < rows.length; i++) {
            // loadReporte64((i + 1), tbody64, rows[0]);
            var row = rows[0];
//            var rowid = rowid_over_table_temp;
            console.log('rowid:::>>>>>>>>>>' + rowid_over_table_temp);
            //-------------------
            for (var x = 0; x < row.regs.length; x++) {
                var reg = row.regs[x];
                // console.log('reg.regist =' + reg.regist + ',reg.value = ' + reg.value);
                // document.getElementsByName('P' + CO_PAGINA + 'T1R' + reg.regist + 'V')[0].innerHTML = reg.value;
                // document.getElementsByName('P' + CO_PAGINA + 'T1R' + reg.regist + 'V')[0].innerHTML = reg.value;
                var txtval = reg.text == undefined ? reg.value : reg.text;
                txtval = txtval == undefined ? '' : txtval;
                //n_itr = n_itr.replace('reg' + reg.regist + 'val', '' + (reg.front == undefined ? (reg.value == undefined ? '' : reg.value) : reg.front));
                // console.log('>[' + 'regist' + reg.regist + 'val' + '] >por> ' + txtval);
                // n_itr = n_itr.replace('regist' + reg.regist + 'val', '' + txtval);
                // n_itr.replace('<tr>','').replace('</tr>','')
                // console.log('[loadReporte64:update!]>>>:' + n_itr);

                // console.log('ELE:' + 'C' + rowid + 'R' + reg.regist);
                // alert('XCD');
//        console.log('===>C' +S rowid + 'R' + reg.regist + 'V');

                var erid = 'P' + CO_PAGINA + 'C' + rowid + 'R' + reg.regist + 'V';
                var ereg = document.getElementById(erid);
                console.log('erid:' + erid + ', ereg:' + ereg);
                // console.log('ereg:' + ereg + ",?>" + ereg.getAttribute('ti_pagreg'));
                if (ereg != null) {
                    var local_ti_pagreg = ereg.getAttribute('ti_pagreg') == undefined ? 1 : ereg.getAttribute('ti_pagreg');
                    var nuevo_ti_pagreg = (reg.type == undefined) ? local_ti_pagreg : reg.type;
                    var local_ti_estreg = TI_ESTREG(ereg.getAttribute('class'));
                    var nuevo_ti_estreg = (reg.state == undefined) ? local_ti_estreg : reg.state;

                    /*FORMA*/
                    console.log('local_ti_pagreg:' + local_ti_pagreg + ', nuevo_ti_pagreg: ' + nuevo_ti_pagreg);
                    if ((local_ti_pagreg != nuevo_ti_pagreg) | (local_ti_estreg != nuevo_ti_estreg)) {
                        console.log("Los elementos son diferentes");
                        var _html = builderTyper2(local_ti_pagreg, nuevo_ti_pagreg, nuevo_ti_estreg, erid, ereg);
                        console.log('TAG CREADO!>>' + _html);
                        ereg.parentNode.innerHTML = _html;
                    } else {
                        console.log("Los elementos son iguales");
                    }
                    ereg = document.getElementById('P' + CO_PAGINA + 'C' + rowid + 'R' + reg.regist + 'V');
                }

                /*DATA*/
                if (ereg !== null) {
                    // console.log('ereg:' + ereg + ",?>" + ereg.getAttribute('ti_pagreg'));
                    // for (var i = 0; i < eregs.length; i++) {
                    //     var ereg = eregs[i];
                    if (ereg.getAttribute('ti_pagreg') == null) {
                        ereg.value = txtval;
                    } else if (ereg.getAttribute('ti_pagreg') == '1' | ereg.getAttribute('ti_pagreg') == '22' | ereg.getAttribute('ti_pagreg') == '23') {
                        if (ereg.getElementsByTagName('INPUT').length > 0) {
                            ereg.getElementsByTagName('INPUT')[0].setAttribute('id', 'C' + rowid + '' + reg.regist + 'R');
                            ereg.getElementsByTagName('INPUT')[0].value = txtval;
                        } else {
                            ereg.innerHTML = txtval;
                            ereg.setAttribute('va_pagreg', reg.value);
                        }

                    } else if (ereg.getAttribute('ti_pagreg') == '2') {
//                ereg.getElementsByTagName('INPUT')[0].setAttribute('id', 'C' + rowid + '' + reg.regist + 'R');
                        ereg.innerHTML = rowid;

                    } else if (ereg.getAttribute('ti_pagreg') == '3') {
//                ereg.getElementsByTagName('INPUT')[0].setAttribute('id', 'C' + rowid + '' + reg.regist + 'R');
                        //*ereg.innerHTML = rowid;
                        //TEMPORAL====================
                        var ls_compag;
                        try {
                            ls_compag = aditional[reg.regist];
                            console.log('(COMBO)ls_compag?:' + ls_compag);
                            if (ls_compag == undefined) {
                                try {
                                    ls_compag = JSON.parse(reg.data);
                                } catch (e) {
                                    console.log('(COMBO)el combo value!:' + ls_compag);
                                }
                            }
                        } catch (e) {
                            console.log('(COMBO)el combo value!:' + reg.data);
                            ls_compag = JSON.parse(reg.data);
                            console.log('(COMBO)el combo value!:' + ls_compag);
                        }
                        //=========================
                        var dom_compag = "";

                        if (ls_compag) {
                            for (var i = 0; i < ls_compag.length; i++) {
                                compag = ls_compag[i];

                                dom_compag += "<option value=\"" + compag.co_compag + "\" " + (reg.value == compag.co_compag ? "selected" : "") + ">" + compag.no_compag + "</option>";
                            }
                            ereg.getElementsByTagName("SELECT")[0].innerHTML = dom_compag;
                        }

                    } else if (ereg.getAttribute('ti_pagreg') == '4') {
//                ereg.getElementsByTagName('INPUT')[0].setAttribute('id', 'C' + rowid + '' + reg.regist + 'R');
                        //*ereg.innerHTML = rowid;
                        //TEMPORAL====================
                        var ls_compag;
                        try {
                            ls_compag = aditional[reg.regist];
                            console.log('(COMBO)ls_compag?:' + ls_compag);
                            if (ls_compag == undefined) {
                                try {
                                    ls_compag = JSON.parse(reg.data);
                                } catch (e) {
                                    console.log('(COMBO)el combo value!:' + ls_compag);
                                }
                            }
                        } catch (e) {
                            console.log('(COMBO)el combo value!:' + reg.data);
                            ls_compag = JSON.parse(reg.data);
                            console.log('(COMBO)el combo value!:' + ls_compag);
                        }
                        //=========================
                        var dom_compag = "<option value=\"\"></option>";

                        if (ls_compag) {
                            for (var i = 0; i < ls_compag.length; i++) {
                                compag = ls_compag[i];

                                dom_compag += "<option value=\"" + compag.co_compag + "\" " + (reg.value == compag.co_compag ? "selected" : "") + ">" + compag.no_compag + "</option>";
                            }

                            ereg.getElementsByTagName("SELECT")[0].innerHTML = dom_compag;
                        }

                    } else if (ereg.getAttribute('ti_pagreg') == '6') {
                        console.log('tip:' + ereg.getAttribute('ti_pagreg'));
                        // ereg.getElementsByTagName('INPUT')[0].setAttribute('id', 'C' + rowid + 'R' + reg.regist + '_check');
                        // ereg.getElementsByTagName('LABEL')[0].setAttribute('for', 'C' + rowid + 'R' + reg.regist + '_check');

                        console.log('?tip:' + ereg.getAttribute('ti_pagreg') + '::' + reg.regist);

                        if (reg.value == 't' | reg.value == 'T' | reg.value == 'true' | reg.value == 'TRUE' | reg.value == 'verdadero') {
                            ereg.getElementsByTagName('INPUT')[0].setAttribute('checked', 'checked');
                            ereg.getElementsByTagName('INPUT')[0].checked = true;
                        } else {
                            ereg.getElementsByTagName('INPUT')[0].removeAttribute('checked');
                            ereg.getElementsByTagName('INPUT')[0].checked = false;
                        }

                        // console.log('to add?:' + ' check' + reg.regist);
                        // ereg.getElementsByTagName('INPUT')[0].setAttribute('class', ereg.getElementsByTagName('INPUT')[0].getAttribute('class') + ' check' + reg.regist);
                    } else if (ereg.getAttribute('ti_pagreg') == '36') {
                        console.log('tip:' + ereg.getAttribute('ti_pagreg'));
                        // ereg.getElementsByTagName('INPUT')[0].setAttribute('id', 'C' + rowid + 'R' + reg.regist + '_check');
                        // ereg.getElementsByTagName('LABEL')[0].setAttribute('for', 'C' + rowid + 'R' + reg.regist + '_check');

                        console.log('?tip:' + ereg.getAttribute('ti_pagreg') + '::' + reg.regist);
                        ereg.getElementsByTagName('A')[0].setAttribute('href', '/jsp_exec/ocelot/viewer.jsp?file=' + txtval);

                        // console.log('to add?:' + ' check' + reg.regist);
                        // ereg.getElementsByTagName('INPUT')[0].setAttribute('class', ereg.getElementsByTagName('INPUT')[0].getAttribute('class') + ' check' + reg.regist);
                    } else {
                    }
                }
            }
            // }

            /*UPDATE HEIGHT*/
            size_of_pagina();
        }

        // document.getElementById('loader').style.display = 'none';
    }

    /*TOOLTIP*/
    $('[data-toggle="tooltip"]').tooltip();
    /*SELECT*/
    var all_selects = document.getElementsByClassName('mdb-select');
    console.log('ALL>SELECT>[' + CO_PAGINA + ']-*>>' + all_selects);
    console.log('ALL>SELECT>[' + CO_PAGINA + ']-*>>' + all_selects.length);
    for (var i = 0; i < all_selects.length; i++) {
        var me_select = all_selects[i];
        if (me_select.tagName == 'SELECT' & me_select.getAttribute('class').indexOf('initialized') == -1) {
            $(me_select).materialSelect();
            console.log('Se ejecuto la create!');
        }
    }

}

function readypagina(pag) {
    console.log('La pàgina ya cargo:' + pag);
}

function loadFormulario64(index, row, aditional, dom2) {
    if (index > 1) {
        var mpage = document.getElementById('PAG' + CO_PAGINA);
        dom2 = dom2.replace('row1', 'row' + index);
        dom2 = dom2.replace(/C1/g, 'C' + index);
        var nele = document.createElement('TBODY');//.innerHTML = dom24603321043944324;
        nele.setAttribute('id', 'row' + index);
        mpage.appendChild(nele);
        document.getElementById("row" + index).innerHTML = dom2;
    }

    for (const reg of row.regs) {
        //IDs
        var ultraid = 'P' + CO_PAGINA + 'C' + index + 'R' + reg.regist + 'V';
        var ultraid2 = 'P' + CO_PAGINA + 'C' + index + 'R' + reg.regist + 'K';

        var eledom = document.getElementsByName(ultraid)[0];
        var labdom = document.getElementsByName(ultraid2)[0];
        if (reg.label != undefined) {
            labdom.innerHTML = reg.label + '<span style="border-right:2px solid #00477e;padding-left: 5px;"></span>';
        }

        var valdom = reg.text == undefined ? (reg.value == undefined ? '' : reg.value) : reg.text;
        console.log('(' + CO_PAGINA + ')>>eledom=[' + eledom + ']');
        // console.log('>>eledom=[' + eledom + ':' + eledom.tagName + ', valdom=[' + valdom + ']');
        if (eledom) {
            console.log("(" + CO_PAGINA + ")=======EVAL DATA TYPE=>" + ultraid + ",=>?" + eledom.tagName + "[" + ti_estreg + "]");
            //EVALUACION DE TIPO DE DATO
            switch (eledom.tagName) {
                case "SPAN": {
                    var ti_pagreg = eledom.getAttribute('ti_pagreg');
                    console.log("[ti_pagreg:" + ti_pagreg + "]?[reg.type:" + reg.type + "]=>[(reg.type != undefined ):" + (reg.type != undefined) + "].[reg.type > -1):" + (reg.type > -1) + "]?==>" + (reg.type != undefined & reg.type > -1));

                    console.log("[ti_pagreg:" + ti_pagreg + "]");
                    var td = eledom.parentNode;
                    var eleid = eledom.getAttribute('id');
//                    var co_regist = eleid.substring(eleid.indexOf('R') + 1, eleid.length - 1);
                    var co_regist = reg.regist;
                    var ti_estreg = '';
                    if (eledom.getAttribute('class').indexOf('writer') > -1) {
                        ti_estreg = 'E';
                    } else if (eledom.getAttribute('class').indexOf('reader') > -1) {
                        ti_estreg = 'L';
                    } else {
                        ti_estreg = 'O';
                    }

                    var il_onchag = eledom.getAttribute('class').indexOf('xaction') > -1 ? true : false;
                    var ur_pagreg = reg.link == undefined ? '' : reg.link;

                    console.log('(' + CO_PAGINA + ')EVALUACION!> [de:' + ti_pagreg + '][a:' + reg.type + '][' + (reg.state != undefined) + '][' + (reg.state != undefined && ti_pagreg != reg.state) + '[ur_pagreg:' + ur_pagreg + ']');
                    // if ((reg.type != undefined & reg.type > -1) && ti_pagreg != reg.type && reg.type > 0) {
                    if ((reg.type != undefined & reg.type > -1) && ti_pagreg != reg.type) {
                        console.log('(' + CO_PAGINA + ')[' + reg.regist + ']Es un cambio de tipo:[de:' + ti_pagreg + '][a:' + reg.type + ']');
                        //de cualquier tipo(en span) a!

                        console.log('[' + reg.regist + ']eledom.getAttribute(\'class\')=' + eledom.getAttribute('class'));
                        console.log('[' + reg.regist + ']eledom.getAttribute(\'class\').indexof=' + eledom.getAttribute('class').indexOf('writer'));

                        ti_estreg = (reg.state != undefined) ? reg.state : ti_estreg;

                        td.innerHTML = builderType(reg.type, ti_estreg, co_regist, ur_pagreg, il_onchag, eleid);
                        console.log("*[" + reg.regist + "](" + CO_PAGINA + ") es el td:" + td);
                        //?
                        eledom = document.getElementsByName('P' + CO_PAGINA + 'C' + index + 'R' + reg.regist + 'V')[0];
                    }

                    //Si el elemento es lo mismo solo que con diferente estado para saltar la limitante del tipo de dato
                    if (reg.state != undefined && reg.state != ti_estreg) {
                        ti_estreg = (reg.state != undefined) ? reg.state : ti_estreg;
                        ti_pagreg = (reg.type != undefined & reg.type > -1) ? reg.type : ti_pagreg;

                        // td.innerHTML = builderType(reg.type, ti_estreg, co_regist, ur_pagreg, il_onchag, eleid);
                        td.innerHTML = builderType(ti_pagreg, ti_estreg, co_regist, ur_pagreg, il_onchag, eleid);
                        console.log("**[" + reg.regist + "](" + CO_PAGINA + ") es el td:" + td);
                        //?
                        eledom = document.getElementsByName('P' + CO_PAGINA + 'C' + index + 'R' + reg.regist + 'V')[0];
                    }

                    if (reg.state != undefined && reg.state == ti_estreg) {
                        ti_estreg = (reg.state != undefined) ? reg.state : ti_estreg;
                        ti_pagreg = (reg.type != undefined & reg.type > -1) ? reg.type : ti_pagreg;
                        td.innerHTML = builderType(ti_pagreg, ti_estreg, co_regist, ur_pagreg, il_onchag, eleid);
                        eledom = document.getElementsByName('P' + CO_PAGINA + 'C' + index + 'R' + reg.regist + 'V')[0];
                    }
                }
                case "INPUT": {

                    break;
                }
            }

            eledom = document.getElementsByName(ultraid)[0];

            console.log("=======ASIGN DATA=>" + ultraid + ",=>?" + eledom + ",extra:" + eledom.tagName + "::" + valdom + "::==>>" + eledom.getAttribute("type") + "<==[" + ti_estreg + "]");
            //ASIGNACION DE DATA
            switch (eledom.tagName) {
                case "INPUT": {
                    eledom.value = valdom;
                    if (eledom.getAttribute("type") !== "hidden") {
                        domtr(eledom).removeAttribute('style');
                    }
                    break;
                }
                case "SPAN": {
                    var ti_pagreg = eledom.getAttribute('ti_pagreg');
                    //console.log("ti_pagreg=" + ti_pagreg + "->" + (ti_pagreg == '13'));
                    if (ti_pagreg == '1' | ti_pagreg == '22' | ti_pagreg == '23') {
//                    eledom.getElementsByTagName("INPUT")[0].value = reg.value;
                        console.log('---->eledom:' + eledom + ', valdom:' + valdom);
                        if (eledom.getElementsByTagName("INPUT").length > 0) {
                            var tm_input = eledom.getElementsByTagName("INPUT")[0];
                            tm_input.value = valdom;
                            console.log('reg.va_style:' + reg.style);
                            if (reg.style != undefined) {
//                                reg.style = JSON.parse(reg.style);
                                var allcss = "";
                                for (const css of JSON.parse(reg.style)) {
                                    allcss += css + ";";
                                    console.log('css:' + css);
                                }
                                tm_input.setAttribute('style', allcss);
                            }
                        } else {
                            eledom.innerHTML = valdom;
                            if (reg.value != undefined)
                                eledom.setAttribute('va_pagreg', reg.value);
                        }

//                    eledom.setAttribute("va_pagreg", reg.value);
//                    eledom.innerHTML = valdom;
                    } else if (ti_pagreg == '3') {

                        //TEMPORAL====================
                        var ls_compag;
                        try {
                            ls_compag = aditional[reg.regist];
                            // console.log('(COMBO)ls_compag?:' + ls_compag);
                            if (ls_compag == undefined) {
                                try {
                                    ls_compag = JSON.parse(reg.data);
                                } catch (e) {
                                    console.log('(COMBO)el combo value!:' + ls_compag);
                                }
                            }
                        } catch (e) {
                            // console.log('(COMBO)el combo value!:' + reg.data);
                            if (reg.data != undefined) {
                                ls_compag = JSON.parse(reg.data);
                            } else {
                                false
                            }
                            // console.log('(COMBO)el combo value!:' + ls_compag);
                        }
                        //=========================
                        var dom_compag = "";

                        if (ls_compag) {
                            var ls_comgru = [];
                            var tm_comgru = '';
                            for (var i = 0; i < ls_compag.length; i++) {
                                compag = ls_compag[i];
                                if (compag.co_comgru != undefined) {
                                    if (tm_comgru !== compag.co_comgru) {
                                        ls_comgru[ls_comgru.length] = compag.co_comgru;
                                    }
                                }
                            }

                            if (ls_comgru.length > 0) {
                                for (var z = 0; z < ls_comgru.length; z++) {
                                    dom_compag += "<optgroup label=\"" + ls_comgru[z] + "\">";
                                    for (var i = 0; i < ls_compag.length; i++) {
                                        compag = ls_compag[i];
                                        if (ls_comgru[z] == compag.co_comgru) {
                                            dom_compag += "<option value=\"" + compag.co_compag + "\" " + (reg.value == compag.co_compag ? "selected" : "") + " " + (compag.ur_comima == undefined ? "" : " data-icon=\"" + compag.ur_comima + "\" ") + " class=\"rounded-circle\">" + compag.no_compag + "</option>";
                                        }
                                    }
                                    dom_compag += "</optgroup>";
                                }

                            } else {
                                for (var i = 0; i < ls_compag.length; i++) {
                                    compag = ls_compag[i];
                                    dom_compag += "<option value=\"" + compag.co_compag + "\" " + (reg.value == compag.co_compag ? "selected" : "") + " class=\"rounded-circle\">" + compag.no_compag + "</option>";
                                }
                            }

                            eledom.getElementsByTagName("SELECT")[0].innerHTML = dom_compag;
                        }
                    } else if (ti_pagreg == '4') {
                        //TEMPORAL====================
                        var ls_compag;
                        try {
                            ls_compag = aditional[reg.regist];
                            console.log('(COMBO)ls_compag?:' + ls_compag);
                            if (ls_compag == undefined) {
                                try {
                                    ls_compag = JSON.parse(reg.data);
                                } catch (e) {
                                    console.log('(COMBO)el combo value!:' + ls_compag);
                                }
                            }
                        } catch (e) {
                            console.log('(COMBO)el combo value!:' + reg.data);
//                            ls_compag = JSON.parse(reg.data);
                            if (reg.data != undefined) {
                                ls_compag = JSON.parse(reg.data);
                            } else {
                                false
                            }
                            console.log('(COMBO)el combo value!:' + ls_compag);
                        }
                        //=========================
                        var dom_compag = "<option value=\"\"></option>";

                        if (ls_compag) {
                            var ls_comgru = [];
                            var tm_comgru = '';
                            for (var i = 0; i < ls_compag.length; i++) {
                                compag = ls_compag[i];
                                console.log('evaluando=' + compag.co_comgru);
                                if (compag.co_comgru != undefined & compag.co_comgru != tm_comgru) {
//                                    if (tm_comgru !== compag.co_comgru) {
                                    ls_comgru[ls_comgru.length] = compag.co_comgru;
                                    tm_comgru = compag.co_comgru;
//                                    }
                                }
                            }
                            console.log('ls_comgru>' + ls_comgru.length);
                            if (ls_comgru.length > 0) {
                                for (var z = 0; z < ls_comgru.length; z++) {
                                    dom_compag += "<optgroup label=\"" + ls_comgru[z] + "\">";
                                    for (var i = 0; i < ls_compag.length; i++) {
                                        compag = ls_compag[i];
                                        if (ls_comgru[z] == compag.co_comgru) {
                                            dom_compag += "<option value=\"" + compag.co_compag + "\" " + (reg.value == compag.co_compag ? "selected" : "") + " " + (compag.ur_comima == undefined ? "" : " data-icon=\"" + compag.ur_comima + "\" ") + " class=\"rounded-circle\">" + compag.no_compag + "</option>";
                                        }
                                    }
                                    dom_compag += "</optgroup>";
                                }

                            } else {
                                for (var i = 0; i < ls_compag.length; i++) {
                                    compag = ls_compag[i];
                                    dom_compag += "<option value=\"" + compag.co_compag + "\" " + (reg.value == compag.co_compag ? "selected" : "") + " class=\"rounded-circle\">" + compag.no_compag + "</option>";
                                }
                            }

                            eledom.getElementsByTagName("SELECT")[0].innerHTML = dom_compag;
                        }
                    } else if (ti_pagreg == '5') {
                        //valdom = valdom.replace('../reportes/paginaEspecial.jsp?', '/doc?ti_docume=E&');
                        // eledom.getElementsByTagName("CONPAG")[0].setAttribute('href', valdom);
                        //reg.regist
                        var ls_compag = aditional[reg.regist];
                        ls_compag = ls_compag == null ? [] : ls_compag;
                        ls_compag = ls_compag == undefined ? [] : ls_compag;
                        var dom_compag = "";

                        // if (ls_compag != undefined & ls_compag.length > 0) {
                        for (var i = 0; i < ls_compag.length; i++) {
                            compag = ls_compag[i];
                            var funstr = "rb_change(this,'" + eledom.getAttribute('id') + "')";
                            dom_compag += "<span ><input id='" + eledom.getAttribute('id') + "_" + compag.co_compag + "' name='" + eledom.getAttribute('id') + "' type='radio' value='" + compag.co_compag + "' " + (reg.value == compag.co_compag ? "checked" : "") + " onchange=\"" + funstr + "\" ><label for='" + eledom.getAttribute('id') + "_" + compag.co_compag + "'>" + compag.no_compag + "</label></span>";
                        }
                        // }
                        eledom.innerHTML = dom_compag + eledom.innerHTML.replace('PAGREG5', valdom);

                    } else if (ti_pagreg == '6') {
                        // eledom.setAttribute("va_pagreg", reg.value);
                        eledom.getElementsByTagName("INPUT")[0].checked = reg.value;
                    } else if (ti_pagreg == '7') {
                        eledom.getElementsByTagName("INPUT")[0].value = reg.value == undefined ? '' : reg.value;
                    } else if (ti_pagreg == '8') {
                        //valdom = valdom.replace('../reportes/paginaEspecial.jsp?', '/doc?ti_docume=E&');
                        // eledom.getElementsByTagName("CONPAG")[0].setAttribute('href', valdom);
                        //reg.regist
                        $MAP[eledom.getAttribute('id')] = aditional[reg.regist];

                        document.getElementById(eledom.getAttribute('id') + '_ms').value = valdom;
                        document.getElementById(eledom.getAttribute('id') + '_btn').onclick = function () {
                            // do_open_multiselect(eledom.getAttribute('id'));
                            do_open_multiselect(this);
                        }

                        console.log('valdom=' + valdom + ',==>' + reg.value);
                        load_multiselect(eledom.getAttribute('id'), valdom)

                    } else if (ti_pagreg == '9') {
                        // eledom.setAttribute("va_pagreg", reg.value);
                        eledom.getElementsByTagName("TEXTAREA")[0].innerHTML = valdom;
                    } else if (ti_pagreg == '13') {
                        valdom = valdom.replace('../reportes/paginaEspecial.jsp?', '/doc?ti_docume=E&');
                        eledom.getElementsByTagName("A")[0].setAttribute('href', valdom);
                    } else if (ti_pagreg == '36') {

                        // var vafile = eledom.getElementsByTagName("IFRAME")[0].contentWindow.document.getElementById("vafile");
                        eledom.getElementsByTagName("IFRAME")[0].contentWindow.document.getElementById("vafile").onchange = function () {
                            onchange_vafile(this);
                        };

                    } else if (ti_pagreg == '34') {
                        //valdom = valdom.replace('../reportes/paginaEspecial.jsp?', '/doc?ti_docume=E&');
                        //eledom.getElementsByTagName("A")[0].setAttribute('href', valdom);
                        eledom.getElementsByTagName("SPAN")[0].setAttribute("valpag", (reg.value == undefined ? '' : reg.value));
                        var onclicktext = eledom.getElementsByTagName("BUTTON")[0].getAttribute("onclick").replace("ur_pagreg", "'" + (reg.link == undefined ? '' : (reg.link)) + "'");
                        console.log('onclicktext = ' + onclicktext);
                        eledom.getElementsByTagName("BUTTON")[0].setAttribute("onclick", onclicktext);
                        eledom.getElementsByTagName("SPAN")[0].innerHTML = valdom;
                    } else if (ti_pagreg == '35') {
                        //valdom = valdom.replace('../reportes/paginaEspecial.jsp?', '/doc?ti_docume=E&');
                        //eledom.getElementsByTagName("A")[0].setAttribute('href', valdom);

                        // .contentWindow.document.getElementById("form_data");

                        // eledom.getElementsByTagName("SPAN")[0].setAttribute("valpag", (reg.value == undefined ? '' : reg.value));
                        // var onclicktext = eledom.getElementsByTagName("BUTTON")[0].getAttribute("onclick").replace("ur_pagreg", "'" + (reg.link == undefined ? '' : (reg.link)) + "'");
                        // console.log('onclicktext = ' + onclicktext);
                        eledom.getElementsByTagName("IFRAME")[0].contentWindow.setCode(valdom);
                        // eledom.getElementsByTagName("SPAN")[0].innerHTML = valdom;
                    } else if (ti_pagreg == '38') {
                        //valdom = valdom.replace('../reportes/paginaEspecial.jsp?', '/doc?ti_docume=E&');
                        eledom.getElementsByTagName("A")[0].setAttribute('href', valdom);

                    } else {
                        //hacer algo diferente para el tipo 38
                        eledom.innerHTML = valdom;
                    }
                    domtr(eledom).removeAttribute('style');
                    break;
                }
                case "A" : {
                    var ti_pagreg = eledom.getAttribute('ti_pagreg');
                    if (ti_pagreg == '13') {
                        valdom = valdom.replace('../reportes/paginaEspecial.jsp?', '/doc?ti_docume=E&');
                        eledom.setAttribute('href', valdom);
                        domtr(eledom).removeAttribute('style');
                    } else if (ti_pagreg) {
                        //hacer algo diferente para el tipo 38
                    }

                    break;
                }
                default: {
                    eledom.innerHTML = valdom;
                    domtr(eledom).removeAttribute('style');
                }

            }


//            console.log("=======UPDATE STATE");
            //EVALUACION DE TIPO DE ESTADO
            console.log('=======UPDATE STATE!=>' + ultraid + ', [reg.state!:' + reg.state + '][eledom:' + eledom + '][ti_estreg:' + ti_estreg + '][domtr(eledom):' + domtr(eledom) + ']');
            switch (ti_estreg) {
                case 'O': {
                    domtr(eledom).setAttribute('style', 'display:none;');
                    break;
                }
                case 'E': {
                    domtr(eledom).removeAttribute('style');
                    //-------
                    var domtitle = document.getElementsByName("P" + CO_PAGINA + "C" + index + "T" + domtr(eledom).getAttribute("co_pagtit"))[0];
                    domtitle.setAttribute("style", "");

                    //SOlo si es dinpag
                    var il_onchag = eledom.getAttribute('class').indexOf('xaction') > -1 ? true : false;
                    var ti_pagreg = eledom.getAttribute('ti_pagreg');
                    if (ti_pagreg == '3' | ti_pagreg == '4') {
                        console.log('Es un tipo de dato:' + ti_pagreg);
                        var doc = eledom.getElementsByTagName("SELECT")[0];
                        // console.log('SELECT:' + doc);
                        // doc.fireEvent("onchange");
                        console.log('doc.getAttribute("onchange"):' + doc.getAttribute("onchange"));
                        if (doc.getAttribute("onchange") != null) {
                            console.log('ejecutando???°');
                            dinpag(doc, reg.regist);
                        }
                    }
                    break;
                }
                case 'L': {
                    domtr(eledom).getAttribute("co_pagtit");
                    domtr(eledom).removeAttribute('style');
                    //------
                    var domtitle = document.getElementsByName("P" + CO_PAGINA + "C" + index + "T" + domtr(eledom).getAttribute("co_pagtit"))[0];
                    domtitle.setAttribute("style", "");
                    break;
                }
            }

        }
// document.getElementsByName('P' + CO_PAGINA + '' + reg.regist + 'V')[0].innerHTML = reg.front == undefined ? (reg.value == undefined ? '' : reg.value) : reg.front;
    }

    $('.datepicker').pickadate({
        onFocus: function () {
            console.log('Hello there :)');
            return null;
        }
    });
//si los elementos no provienen del valpag->evaluarlos para que el padre!(titulo) se oculte
}

function loadReporte64(rowid, tbody64, row) {
    var n_itr = itr;
    var nr = document.createElement('tr');
    var nrc = 'C' + rowid;
    n_itr = n_itr.replace(/X64UI/g, 'P' + CO_PAGINA + nrc);
    n_itr = n_itr.replace(/%3C/g, '<');
    n_itr = n_itr.replace(/%3E/g, '>');
    n_itr = n_itr.replace(/X32UIR/g, '\'' + nrc + '\'');

    nr.innerHTML = n_itr;
    nr.setAttribute('id', 'row' + rowid);
    tbody64.appendChild(nr);

    for (var x = 0; x < row.regs.length; x++) {
        var reg = row.regs[x];
        // console.log('reg.regist =' + reg.regist + ',reg.value = ' + reg.value);
        // document.getElementsByName('P' + CO_PAGINA + 'T1R' + reg.regist + 'V')[0].innerHTML = reg.value;
        // document.getElementsByName('P' + CO_PAGINA + 'T1R' + reg.regist + 'V')[0].innerHTML = reg.value;
        var txtval = reg.text == undefined ? reg.value : reg.text;
        txtval = txtval == undefined ? '' : txtval;
        //n_itr = n_itr.replace('reg' + reg.regist + 'val', '' + (reg.front == undefined ? (reg.value == undefined ? '' : reg.value) : reg.front));
        // console.log('>[' + 'regist' + reg.regist + 'val' + '] >por> ' + txtval);
        // n_itr = n_itr.replace('regist' + reg.regist + 'val', '' + txtval);
        // n_itr.replace('<tr>','').replace('</tr>','')
        // console.log('[loadReporte64:update!]>>>:' + n_itr);

        // console.log('ELE:' + 'C' + rowid + 'R' + reg.regist);
        // alert('XCD');
//        console.log('===>C' +S rowid + 'R' + reg.regist + 'V');

        var erid = 'P' + CO_PAGINA + 'C' + rowid + 'R' + reg.regist + 'V';
        var ereg = document.getElementById(erid);
        console.log('erid:' + erid + ', ereg:' + ereg);
        // console.log('ereg:' + ereg + ",?>" + ereg.getAttribute('ti_pagreg'));
        if (ereg != null) {
            var local_ti_pagreg = ereg.getAttribute('ti_pagreg') == undefined ? 1 : ereg.getAttribute('ti_pagreg');
            var nuevo_ti_pagreg = (reg.type == undefined) ? local_ti_pagreg : reg.type;
            var local_ti_estreg = TI_ESTREG(ereg.getAttribute('class'));
            var nuevo_ti_estreg = (reg.state == undefined) ? local_ti_estreg : reg.state;

            /*FORMA*/
            console.log('local_ti_pagreg:' + local_ti_pagreg + ', nuevo_ti_pagreg: ' + nuevo_ti_pagreg);
            if ((local_ti_pagreg != nuevo_ti_pagreg) | (local_ti_estreg != nuevo_ti_estreg)) {
                console.log("Los elementos son diferentes");
                var _html = builderTyper2(local_ti_pagreg, nuevo_ti_pagreg, nuevo_ti_estreg, erid, ereg);
                ereg.parentNode.innerHTML = _html;
            } else {
                console.log("Los elementos son iguales");
            }
            ereg = document.getElementById('P' + CO_PAGINA + 'C' + rowid + 'R' + reg.regist + 'V');
        }

        /*DATA*/
        if (ereg !== null) {
            // console.log('ereg:' + ereg + ",?>" + ereg.getAttribute('ti_pagreg'));
            // for (var i = 0; i < eregs.length; i++) {
            //     var ereg = eregs[i];
            if (ereg.getAttribute('ti_pagreg') == null) {
                ereg.value = txtval;
            } else if (ereg.getAttribute('ti_pagreg') == '1' | ereg.getAttribute('ti_pagreg') == '22' | ereg.getAttribute('ti_pagreg') == '23') {
                if (ereg.getElementsByTagName('INPUT').length > 0) {
                    ereg.getElementsByTagName('INPUT')[0].setAttribute('id', 'C' + rowid + '' + reg.regist + 'R');
                    ereg.getElementsByTagName('INPUT')[0].value = txtval;
                } else {
                    ereg.innerHTML = txtval;
                    ereg.setAttribute('va_pagreg', reg.value);
                }

            } else if (ereg.getAttribute('ti_pagreg') == '2') {
//                ereg.getElementsByTagName('INPUT')[0].setAttribute('id', 'C' + rowid + '' + reg.regist + 'R');
                ereg.innerHTML = rowid;

            } else if (ereg.getAttribute('ti_pagreg') == '3') {
//                ereg.getElementsByTagName('INPUT')[0].setAttribute('id', 'C' + rowid + '' + reg.regist + 'R');
                //*ereg.innerHTML = rowid;
                //TEMPORAL====================
                var ls_compag;
                try {
                    ls_compag = aditional[reg.regist];
                    console.log('(COMBO)ls_compag?:' + ls_compag);
                    if (ls_compag == undefined) {
                        try {
                            ls_compag = JSON.parse(reg.data);
                        } catch (e) {
                            console.log('(COMBO)el combo value!:' + ls_compag);
                        }
                    }
                } catch (e) {
                    console.log('(COMBO)el combo value!:' + reg.data);
                    ls_compag = JSON.parse(reg.data);
                    console.log('(COMBO)el combo value!:' + ls_compag);
                }
                //=========================
                var dom_compag = "";

                if (ls_compag) {
                    for (var i = 0; i < ls_compag.length; i++) {
                        compag = ls_compag[i];

                        dom_compag += "<option value=\"" + compag.co_compag + "\" " + (reg.value == compag.co_compag ? "selected" : "") + ">" + compag.no_compag + "</option>";
                    }
                    ereg.getElementsByTagName("SELECT")[0].innerHTML = dom_compag;
                }

            } else if (ereg.getAttribute('ti_pagreg') == '4') {
//                ereg.getElementsByTagName('INPUT')[0].setAttribute('id', 'C' + rowid + '' + reg.regist + 'R');
                //*ereg.innerHTML = rowid;
                //TEMPORAL====================
                var ls_compag;
                try {
                    ls_compag = aditional[reg.regist];
                    console.log('(COMBO)ls_compag?:' + ls_compag);
                    if (ls_compag == undefined) {
                        try {
                            ls_compag = JSON.parse(reg.data);
                        } catch (e) {
                            console.log('(COMBO)el combo value!:' + ls_compag);
                        }
                    }
                } catch (e) {
                    console.log('(COMBO)el combo value!:' + reg.data);
                    ls_compag = JSON.parse(reg.data);
                    console.log('(COMBO)el combo value!:' + ls_compag);
                }
                //=========================
                var dom_compag = "<option value=\"\"></option>";

                if (ls_compag) {
                    for (var i = 0; i < ls_compag.length; i++) {
                        compag = ls_compag[i];

                        dom_compag += "<option value=\"" + compag.co_compag + "\" " + (reg.value == compag.co_compag ? "selected" : "") + ">" + compag.no_compag + "</option>";
                    }

                    ereg.getElementsByTagName("SELECT")[0].innerHTML = dom_compag;
                }

            } else if (ereg.getAttribute('ti_pagreg') == '6') {
                console.log('tip:' + ereg.getAttribute('ti_pagreg'));
                // ereg.getElementsByTagName('INPUT')[0].setAttribute('id', 'C' + rowid + 'R' + reg.regist + '_check');
                // ereg.getElementsByTagName('LABEL')[0].setAttribute('for', 'C' + rowid + 'R' + reg.regist + '_check');

                console.log('?tip:' + ereg.getAttribute('ti_pagreg') + '::' + reg.regist);

                if (reg.value == 't' | reg.value == 'T' | reg.value == 'true' | reg.value == 'TRUE' | reg.value == 'verdadero') {
                    ereg.getElementsByTagName('INPUT')[0].setAttribute('checked', 'checked');
                    ereg.getElementsByTagName('INPUT')[0].checked = true;
                } else {
                    ereg.getElementsByTagName('INPUT')[0].removeAttribute('checked');
                    ereg.getElementsByTagName('INPUT')[0].checked = false;
                }

                // console.log('to add?:' + ' check' + reg.regist);
                // ereg.getElementsByTagName('INPUT')[0].setAttribute('class', ereg.getElementsByTagName('INPUT')[0].getAttribute('class') + ' check' + reg.regist);
            } else if (ereg.getAttribute('ti_pagreg') == '36') {
                console.log('tip:' + ereg.getAttribute('ti_pagreg'));
                // ereg.getElementsByTagName('INPUT')[0].setAttribute('id', 'C' + rowid + 'R' + reg.regist + '_check');
                // ereg.getElementsByTagName('LABEL')[0].setAttribute('for', 'C' + rowid + 'R' + reg.regist + '_check');

                console.log('?tip:' + ereg.getAttribute('ti_pagreg') + '::' + reg.regist);
                ereg.getElementsByTagName('A')[0].setAttribute('href', '/jsp_exec/ocelot/viewer.jsp?file=' + txtval);

                // console.log('to add?:' + ' check' + reg.regist);
                // ereg.getElementsByTagName('INPUT')[0].setAttribute('class', ereg.getElementsByTagName('INPUT')[0].getAttribute('class') + ' check' + reg.regist);
            } else {
            }
        }
    }


    // var nr = document.createElement('tr');
    // nr.innerHTML = n_itr;

    //tbody64.appendChild(nr); //ADDRECIENTE
    // console.log('[loadReporte64]Cargando tipo Reporte->itr(2):' + n_itr);

    // var dom_pag = document.getElementById('PAG' + CO_PAGINA);
    // var new_tr = dom_pag.getElementsByTagName('TBODY')[0].innerHTML + n_itr;
    // console.log('[loadReporte64]Cargando tipo Reporte->itr(3):' + new_tr);
    // dom_pag.getElementsByTagName('TBODY')[0].innerHTML = new_tr;
    //
    // //do-for
    // var alltd = document.getElementById('PAG' + CO_PAGINA).getElementsByClassName('ti_pag_Reg2');
    // for (var i = 0; i < alltd.length; i++) {
    //     alltd[i].getElementsByTagName('SPAN')[0].innerHTML = '' + (i + 1);
    //     // alltd[i].innerHTML = '<span>' + (i + 1) + '</span>span>';
    // }
}

function propag(cycle, co_button, il_proces, co_condes) {
    /*POPUP ON*/
    window.parent.showloading(true);

    /*LOGIC*/
    var data = new FormData();
    data.append('id_frawor', '' + ID_FRAWOR);
    data.append('co_pagina', '' + CO_PAGINA);
    data.append('co_conten', '' + CO_CONTEN);
    data.append('co_botone', '' + co_button);
    data.append('il_proces', '' + il_proces);
    var ls_conpar = "{";


    // alert(("" + window.location) + "|" + (parent.location));


    var urls = ("" + parent.location).split('&');
    for (var i = 0; i < urls.length; i++) {
        var url = urls[i];
        console.log('url=' + url + "--->" + (url.indexOf('co_conpar') > -1));
        if (url.indexOf('co_conpar') > -1) {
            if (url.split('=').length = 2) {
                ls_conpar += "\"" + url.split('=')[0] + "\":\"" + url.split('=')[1] + "\",";
                // ls_conpar[ls_conpar.length] = new PARAM(url.split('=')[0], url.split('=')[1]);
            }
        }
    }
    ls_conpar = ls_conpar.length > 1 ? ls_conpar.substring(0, ls_conpar.length - 1) : ls_conpar;
    ls_conpar += "}";
    console.log("ls_conpar:" + ls_conpar);
    data.append('ls_conpar', ls_conpar);


    var preimg = [];

    for (var eledom of document.getElementsByClassName('pagreg')) {
        console.log('[' + eledom.id + ']=?[' + 'P' + CO_PAGINA + cycle + 'R' + ']??>??' + eledom.id.indexOf('P' + CO_PAGINA + cycle + 'R'));
        if (eledom.id.indexOf('P' + CO_PAGINA + cycle + 'R') > -1) {
// var id = eledom.id.substring(eledom.id.indexOf('R') + 1, eledom.id.indexOf('V'));
            var id = eledom.id.substring(eledom.id.indexOf('R') + 1, eledom.id.indexOf('V'));
            var val = null;

            switch (eledom.tagName) {
                case "INPUT": {
                    val = eledom.value;
                    break;
                }
                case "SPAN": {
                    var ti_pagreg = eledom.getAttribute('ti_pagreg');
                    console.log("ti_pagreg=" + ti_pagreg);
                    if (ti_pagreg == '1' | ti_pagreg == '22' | ti_pagreg == '23') {
                        // val = eledom.getElementsByTagName("INPUT")[0].value; //eledom.getAttribute("va_pagreg");
                        // var dom_select = eledom.getElementsByTagName("INPUT")[0];
                        console.log('VLIS:' + eledom.innerHTML);
                        // if (eledom.innerHTML.length > 0) {
                        try {
                            if (eledom.getElementsByTagName("INPUT").length > 0) {
                                val = eledom.getElementsByTagName("INPUT")[0].value;
                            } else {
                                val = eledom.getAttribute("va_pagreg");
                                if (val.length == 0)
                                    val = eledom.innerHTML;
                            }
                        } catch (e) {
                            val = eledom.innerHTML;
                        }
                        // }
                    } else if (ti_pagreg == '3') {
                        // valdom = valdom.replace('../reportes/paginaEspecial.jsp?', '/doc?ti_docume=E&');
                        var dom_select = eledom.getElementsByTagName("SELECT")[0];
                        try {
                            val = dom_select.options[dom_select.selectedIndex].value;
                        } catch (e) {
                            val = '';
                        }
                    } else if (ti_pagreg == '4') {
                        // valdom = valdom.replace('../reportes/paginaEspecial.jsp?', '/doc?ti_docume=E&');
                        var dom_select = eledom.getElementsByTagName("SELECT")[0];
                        try {
                            val = dom_select.options[dom_select.selectedIndex].value;
                        } catch (e) {
                            val = '';
                        }
                    } else if (ti_pagreg == '5') {
                        val = eledom.getElementById(eledom.id + '_rb').value;
                    } else if (ti_pagreg == '6') {
                        console.log('??>>>???>>eledom.id:' + eledom.id);
                        val = document.getElementById(eledom.id + 'D').checked;
                    } else if (ti_pagreg == '7') {
                        // console.log('@@>>' + eledom);
                        // console.log('@@>>' + eledom.id + '_date');
                        // console.log('@@>>' + eledom.id + '_date');
                        // console.log('@@>>' + eledom.getElementById(eledom.id + '_date'));
                        // val = eledom.getElementById(eledom.id + '_date').value;
                        val = eledom.getElementsByTagName("INPUT")[0].value;
//                        val = document.getElementById(eledom.id + '_date').value;
                    } else if (ti_pagreg == '8') {
                        val = eledom.getElementById(eledom.id + '_ms').value;
                    } else if (ti_pagreg == '9') {
                        // valdom = valdom.replace('../reportes/paginaEspecial.jsp?', '/doc?ti_docume=E&');
                        var dom_select = eledom.getElementsByTagName("TEXTAREA")[0];
                        val = dom_select.value;
                    } else if (ti_pagreg == '13') {
                        // valdom = valdom.replace('../reportes/paginaEspecial.jsp?', '/doc?ti_docume=E&');
                        // eledom.getElementsByTagName("A")[0].setAttribute('href', valdom);
                    } else if (ti_pagreg == '34') {
                        //PWNDIENTE MARIO!!!
                        val = eledom.getElementsByTagName("SPAN")[0].getAttribute("valpag");
                        console.log('ti_pagreg=>' + val);
                        // var onclicktext = eledom.getElementsByTagName("BUTTON")[0].getAttribute("onclick").replace("ur_pagreg", "'" + (reg.link == undefined ? '' : (reg.link)) + "'");
                        //
                        // eledom.getElementsByTagName("BUTTON")[0].setAttribute("onclick", onclicktext);
                        // eledom.getElementsByTagName("SPAN")[0].innerHTML = valdom;
                    } else if (ti_pagreg == '35') {
                        //PWNDIENTE MARIO!!!
                        // eledom.getElementsByTagName("SPAN")[0].setAttribute("valpag", (reg.value == undefined ? '' : reg.value));
                        // var onclicktext = eledom.getElementsByTagName("BUTTON")[0].getAttribute("onclick").replace("ur_pagreg", "'" + (reg.link == undefined ? '' : (reg.link)) + "'");
                        //
                        // eledom.getElementsByTagName("BUTTON")[0].setAttribute("onclick", onclicktext);
                        // eledom.getElementsByTagName("SPAN")[0].innerHTML = valdom;
                        val = eledom.getElementsByTagName("IFRAME")[0].contentWindow.getCode();
                    } else if (ti_pagreg == '36') {
                        var vaform = eledom.getElementsByTagName("IFRAME")[0].contentWindow.document.getElementById("form_data");

                        if (vaform != undefined) {
                            var vafile = eledom.getElementsByTagName("IFRAME")[0].contentWindow.document.getElementById("vafile");
                            if (vafile.files.length > 0) {
                                preimg[preimg.length] = eledom.getAttribute('id');
                            }
                            val = null;
                        }
                    } else if (ti_pagreg == '38') {

                        // eledom.getElementsByTagName("A")[0].setAttribute('href', valdom);

                    } else {
                        //hacer algo diferente para el tipo 38
                        // eledom.innerHTML = valdom;
                    }

                    break;
                }
                case "A": {
                    var ti_pagreg = eledom.getAttribute('ti_pagreg');
                    if (ti_pagreg == '13') {
                        valdom = valdom.replace('../reportes/paginaEspecial.jsp?', '/doc?ti_docume=E&');
                        eledom.setAttribute('href', valdom);
                        domtr(eledom).removeAttribute('style');
                    } else if (ti_pagreg) {
                        //hacer algo diferente para el tipo 38
                    }

                    break;
                }
            }

            data.append('co_regist' + id, val);
        }

    }

    if (preimg.length == 0) {
        prepair_parameters_propag64(cycle, co_button, il_proces, co_condes, data);
    } else {
        document.getElementById(preimg[0]).getElementsByTagName("IFRAME")[0].contentWindow.document.getElementsByTagName("FORM")[0].submit();
        setTimeout(function () {
            preproces_propag64(cycle, co_button, il_proces, co_condes, preimg, preimg[0], data)
        }, 10);
    }
}

function preproces_propag64(cycle, co_button, il_proces, co_condes, preimg, proimg, data) {
    // console.log('[' + new Date() + '][co_button=' + co_button + '][il_proces=' + il_proces + '][co_condes=' + co_condes + '][preimg=' + preimg + '][proimg=' + proimg + '][data=' + data + ']');

    if (preimg.length == 0 & proimg.length == 0) {
        prepair_parameters_propag64(cycle, co_button, il_proces, co_condes, data);
    } else if (proimg.length > 0) {
        var vaframe = document.getElementById(proimg).getElementsByTagName("IFRAME")[0];
        var futureJson;
        var waitfor = false;

        try {
            futureJson = vaframe.contentWindow.document.getElementsByTagName("BODY")[0].innerHTML;
            futureJson = futureJson.replace('<pre>', '').replace('</pre>', '');
            console.log('futureJson=' + futureJson)
            futureJson = JSON.parse(futureJson);

            if (futureJson.status = 'OK') {
                var eledom = document.getElementById(proimg);
                id = eledom.id.substring(eledom.id.indexOf('R') + 1, eledom.id.indexOf('V'));

                data.set('co_regist' + id, '' + futureJson.result[0].co_archiv);
            }

            proimg = '';
            preimg.shift();

            if (preimg.length > 0) {
                proimg = preimg[0];
                vaframe = document.getElementById(proimg).getElementsByTagName("IFRAME")[0].contentWindow.document.getElementsByTagName("FORM")[0];
                vaframe.submit();
                waitfor = true;
            }
            //si es un json
        } catch (e) {
            console.log('error subtimi::::::' + e);
            //no hacer nada y seguir esperando
            waitfor = true;
        }

        if (waitfor)
            setTimeout(function () {
                preproces_propag64(cycle, co_button, il_proces, co_condes, preimg, proimg, data)
            }, 1000);
        else
            preproces_propag64(cycle, co_button, il_proces, co_condes, preimg, proimg, data);
    }

}

function prepair_parameters_propag64(cycle, co_button, il_proces, co_condes, data) {
    console.log('prepair_parameters_propag64:[' + cycle + "][" + co_button + "][" + il_proces + "][" + co_condes + "][" + data + "]");
    var parametros = [];
    var btnpar = [];

    try {
        btnpar = eval('BTN' + co_button + 'P');
    } catch (e) {
        console.error('[prepair_parameters_propag64]e:' + e)
        //P9213C1R1P
        try {
            console.log('----------------->' + 'P' + CO_PAGINA + cycle + 'BTN' + co_button + 'P');
            //btnpar = eval('P' + CO_PAGINA + cycle + 'BTN' + co_button + 'P');
            eval(document.getElementById('P' + CO_PAGINA + cycle + 'BTN' + co_button + 'PS').innerHTML);
            btnpar = eval('P' + CO_PAGINA + cycle + 'BTN' + co_button + 'P');
        } catch (e2) {
            console.error('[prepair_parameters_propag64]e2:' + e2);
            btnpar = [];
        }
        //
    }

    // for (var i = 0; i < eval('BTN' + co_button + 'P').length; i++) {
    for (var i = 0; i < btnpar.length; i++) {
        // var param = eval('BTN' + co_button + 'P')[i];
        var param = btnpar[i];
        var spagreg = param.getPagreg();
        var sconpar = param.getConpar();

        var eledom = document.getElementById('P' + CO_PAGINA + cycle + 'R' + spagreg + 'V');
        var valdom = '';
        switch (eledom.tagName) {
            case "INPUT": {
                valdom = eledom.value;
                break;
            }
            case "SPAN": {
                var ti_pagreg = eledom.getAttribute('ti_pagreg');
                //console.log("ti_pagreg=" + ti_pagreg + "->" + (ti_pagreg == '13'));
//                if (ti_pagreg == '1') {
                if (ti_pagreg == '1' | ti_pagreg == '22' | ti_pagreg == '23') {
//                    valdom = eledom.getAttribute("va_pagreg");
//                     valdom = eledom.getElementsByTagName("INPUT")[0].value; //eledom.getAttribute("va_pagreg");
                    console.log('VLIS:' + eledom.innerHTML);
                    try {
                        if (eledom.getElementsByTagName("INPUT").length > 0) {
                            valdom = eledom.getElementsByTagName("INPUT")[0].value;
                        } else {
                            valdom = eledom.innerHTML;
                        }
                    } catch (e) {
                        valdom = eledom.innerHTML;
                    }
                } else if (ti_pagreg == '3') {
                    var dom_select = eledom.getElementsByTagName("SELECT")[0];
                    try {
                        valdom = dom_select.options[dom_select.selectedIndex].value;
                    } catch (e) {
                        valdom = '';
                    }
                } else if (ti_pagreg == '4') {
                    var dom_select = eledom.getElementsByTagName("SELECT")[0];
                    try {
                        valdom = dom_select.options[dom_select.selectedIndex].value;
                    } catch (e) {
                        valdom = '';
                    }
                } else if (ti_pagreg == '6') {
                    valdom = document.getElementById(eledom.id + 'D').checked;
                } else if (ti_pagreg == '7') {
                    // val = document.getElementById(eledom.id + '_date').value;
                    // var dom_select = eledom.getElementById("SELECT")[0];
                    valdom = eledom.getElementsByTagName("INPUT")[0].value;
                    // valdom = document.getElementById(eledom.id + '_date').value;
                } else if (ti_pagreg == '34') {
                    //PWNDIENTE MARIO!!!
                    valdom = eledom.getElementsByTagName("SPAN")[0].getAttribute("valpag");
                } else if (ti_pagreg == '36') {
                    var vaframe = eledom.getElementsByTagName("IFRAME")[0];


                    futureJson = vaframe.contentWindow.document.getElementsByTagName("BODY")[0].innerHTML;
                    futureJson = futureJson.replace('<pre>', '').replace('</pre>', '');
                    console.log('(**)futureJson=' + futureJson)
                    futureJson = JSON.parse(futureJson);

                    if (futureJson.status = 'OK') {
                        // var eledom = document.getElementById(proimg);
                        // id = eledom.id.substring(eledom.id.indexOf('R') + 1, eledom.id.indexOf('V'));
                        console.log('REGISTERO36>>');
                        valdom = futureJson.result[0].co_archiv;
                    }
                }
                break;
            }
            default: {
                valdom = eledom.innerHTML;
            }
        }

        valdom = ('' + valdom).replace('undefined', '');
        parametros[parametros.length] = 'co_conpar_' + sconpar + '=' + valdom;
    }

    doPropag('//' + window.location.host + '/wf?co_conten=' + co_condes, parametros, data);
}


function domtr(eledom) {
    if (eledom.tagName == 'TR')
        return eledom;

    eledom = eledom.parentNode;

    if (eledom.tagName == 'TR')
        return eledom;


    eledom = eledom.parentNode;

    if (eledom.tagName == 'TR')
        return eledom;
}

function load_multiselect(valid, valdom) {
    var allids = valdom.split(',');

    //eliminar los elementos
    var eled = document.getElementById(valid);
    var allitems = eled.getElementsByClassName('friedrichhabetler');

    console.log('allitems=' + allitems);
    console.log('allitems=' + allitems.length);

    for (var w = allitems.length - 1; w > -1; w--) {
        console.log('elimando:' + allitems[w]);
        console.log('elimando.id:' + allitems[w].id);
        eled.removeChild(allitems[w]);
    }

    //-------------
    for (var i = 0; i < allids.length; i++) {
        var compag = null;
        for (var o = 0; o < $MAP[valid].length; o++) {
            // console.log('allids[i]=' + allids[i] + ',$MAP[eledom.getAttribute(\'id\')][o]=' + $MAP[eledom.getAttribute('id')][o] + ',=>' + (allids[i] == $MAP[eledom.getAttribute('id')][o]));
            if (allids[i] == $MAP[valid][o].co_compag) {
                compag = $MAP[valid][o];
                o = $MAP[valid].length + 100;
            }
        }
        console.log('>>compag=' + compag + ',??>>' + allids[i]);
        //--
        var dele = document.createElement('SPAN');
        dele.setAttribute('class', 'friedrichhabetler item');
        dele.setAttribute('id', valid + '_' + compag.co_compag);
        dele.innerHTML = '<span class="close" onclick="quitar(\'' + valid + '\',\'' + compag.co_compag + '\')"><i class="fa fa-times" aria-hidden="true"></i></span><span va_pagreg="' + compag.co_compag + '">' + compag.no_compag + '</span>';
        // eledom.insertAdjacentElement('afterbegin', dele);
        // eledom.insertBefore(dele, eledom.firstChild);
        document.getElementById(valid).insertBefore(dele, document.getElementById(valid).firstChild);
    }
}

function clean_popup(eleid) {
    console.log('eleid:' + eleid);
    document.getElementById(eleid + 'V').getElementsByTagName('SPAN')[0].innerHTML = "";
    document.getElementById(eleid + 'V').getElementsByTagName('SPAN')[0].setAttribute('valpag', '');
}

function child_popup(u, eleid, c, tit1, tit2) {
    var urlpopup = window.location.origin + "/" + u.replace("../wfl", "wf"); //[*]Esto debe de cambiar
    if (eleid != null & eleid != undefined) {
        eleid = eleid.replace('V', '') + 'V';
        var co_conpar_1 = document.getElementById(eleid).getElementsByTagName('SPAN')[0].getAttribute('valpag');
        var co_conpar_2 = document.getElementById(eleid).getElementsByTagName('SPAN')[0].innerHTML;

        urlpopup = urlpopup + "" + "&co_conpar_1=" + encodeURIComponent(co_conpar_1) + "&co_conpar_2=" + encodeURIComponent(co_conpar_2);
    }

    urlpopup = urlpopup + "&co_conpad=" + c + "&il_popup=true";
    window.parent.master_popup(CO_PAGINA, urlpopup, eleid, tit1 + "  >  <b>" + tit2 + "</b>");
}

function child_popup_update(regist, ls_params) {
    // regist = regist + 'V';
    console.log('chiºld_popup_update!::' + regist);
    console.log('child_popup_update!::' + ls_params);
    for (var i = 0; i < ls_params.length; i++) {
        if (ls_params[i].no_param == 'co_conpar_1') {
            document.getElementById(regist).getElementsByTagName('SPAN')[0].setAttribute('valpag', decodeURIComponent(ls_params[i].va_param));
        } else {
            document.getElementById(regist).getElementsByTagName('SPAN')[0].innerHTML = decodeURIComponent(ls_params[i].va_param);
        }
    }
    //resize pagian
    console.log('EXE lo!');
    size_of_pagina();
    window.parent.iframe2('PAG' + CO_PAGINA, height_table);
}

function master_popup_close() {
    document.getElementById("popup").style.display = "none";
    overlay(false);
    document.getElementById("popup_body").src = '';
}

function master_popup_close2() {
    document.getElementById("popup2").style.display = "none";
    overlay(false);
    // document.getElementById("popup_body").src = '';
}

function doupload(ele) {
    var valdom = document.getElementById(ele);
    var vafile = valdom.getElementsByTagName("IFRAME")[0].contentWindow.document.getElementById("vafile");
    vafile.click();

    return false;
}

function doupload64(ele) {
    var valdom = document.getElementById(ele);
    var vafile = valdom.getElementsByTagName("IFRAME")[0].contentWindow.document.getElementById("vafile");
    vafile.click();

    return false;
}

function doupload2(ele) {
    var valdom = document.getElementById(ele);
    var vafile = valdom.getElementsByTagName("IFRAME")[0].contentWindow.document.getElementById("vafile");
    vafile.click();

    return false;
}

function rb_change(rb, id) {
    console.log('rb=' + rb + ',@=' + rb.value + ',id=' + id);
    console.log('-->' + id + '_rb==>' + document.getElementById(id + '_rb'));
    document.getElementById(id + '_rb').value = rb.value;
}

function quitar(father, item) {
    var elechild = document.getElementById(father + '_' + item);
    document.getElementById(father).removeChild(elechild);

    var ror = document.getElementById(father + '_ms').value.split(',');
    var nror = ''
    for (var i = 0; i < ror.length; i++) {
        if (ror[i] != item) {
            nror += ror[i] + ',';
        }
    }

    nror = nror.substring(0, nror.length - 1);
    document.getElementById(father + '_ms').value.split(',');
}


function onchange_vafile(vafile) {
    //var label = document.getElementById(vafile.getAttribute('domid').replace('V', '') + 'V').getElementsByTagName("SPAN")[0];
    var label = document.getElementById(vafile.getAttribute('domid').replace('_text', '') + 'V');
    var icon = document.getElementById(vafile.getAttribute('domid').replace('_text', '') + 'V');
    if (vafile.files.length > 0) {
        var file = vafile.files[0].name;
        var extension = file.split('.').pop().toUpperCase();
        var faicon = "";
        switch (extension) {
            case "PNG":
            case "JPG":
            case"JEPG": {
                faicon="far fa-image";
                break;
            }
            case "XLS":
            case "XLSX": {
                faicon="far fa-file-excel";
                break;
            }
            case "DOC":
            case "DOCX": {
                faicon="far fa-file-word";
                break;
            }
            case "PDF": {
                faicon="far fa-file-pdf";
                break;
            }
            default: {

            }
        }
        label.innerHTML = vafile.files[0].name == undefined || vafile.files[0].name == '' ? 'Subir archivo' : vafile.files[0].name;
    }
}

function onchange_vafile2(vafile) {
    // console.log("vafile=" + vafile + " && lbfile=" + lbfile + " && ");
    console.log("vafile=" + vafile.innerHTML);
    console.log("vafile=" + vafile.files);
    // console.log("vafile=" + vafile.files.length);
    // console.log("vafile=" + vafile.files.length);

    console.log("this!=" + this);
    console.log("this!=" + this.files);
    // console.log("this!=" + this.files.length);
    // console.log("lbfile!=" + lbfile);
    console.log("2lbfile!=" + vafile.getAttribute('domid') + 'V');
    console.log("3lbfile!=" + document.getElementById(vafile.getAttribute('domid').replace('V', '') + 'V'));
    // var label = document.getElementById(lbfile).getElementsByTagName("SPAN")[0];
    var label = document.getElementById(vafile.getAttribute('domid').replace('V', '') + 'V').getElementsByTagName("SPAN")[0];
    if (vafile.files.length > 0) {
        console.log("vafile.files[0].name=" + vafile.files[0].name);
        console.log(">>vafile.files[0].name=" + (vafile.files[0].name == undefined || vafile.files[0].name == '' ? 'Subir archivo' : vafile.files[0].name));
        // console.log("vafile.files[0].name="+vafile.files[0].name);
        // lbfile.innerHTML = vafile.files[0].name == undefined || vafile.files[0].name == '' ? 'Subir archivo' : vafile.files[0].name;
        label.innerHTML = vafile.files[0].name == undefined || vafile.files[0].name == '' ? 'Subir archivo' : vafile.files[0].name;
    }

}

function do_open_multiselect(eleid) {
    console.log('eleid=' + eleid);
    console.log('eleid=' + eleid.id);
    console.log('ele=' + document.getElementById(eleid + '_ms'));
    //--
    // var proms = '';
    // var allmsi = document.getElementsByClassName('friedrichhabetler');
    //
    // for (var i = 0; i < allmsi.length; i++) {
    //     if (allmsi[i].checked == true)
    //         proms += allmsi[i].value + ','
    // }
    //
    // proms = proms.substring(0, proms.length - 1);
    //--
    var mid = eleid.id.replace('_btn', '');
    // document.getElementById(mid + '_ms').value = proms;
    // window.parent.master_open_multiselect('PAG' + CO_PAGINA, mid, $MAP[mid], document.getElementById(mid + '_ms').value);
    window.parent.master_open_multiselect('PAG' + CO_PAGINA, mid, $MAP[mid], document.getElementById(mid + '_ms').value);
}

function master_open_multiselect(pagid, refid, data, val) {
    console.log('refid=' + refid + ', data-refid=' + refid + ',data=' + data + ', val=' + val);
    overlay(true);
    document.getElementById("popup2").style.display = "block";

    var neo_dom = '<table style="display: inline-block">';
    neo_dom += '<tr><th colspan="2">Selecciona</th></tr>';

    for (var i = 0; i < data.length; i++) {
        var compag = data[i];
        neo_dom += '<tr><td><input type="checkbox" id="friedrichhabetler' + compag.co_compag + '" class="friedrichhabetler" value="' + compag.co_compag + '"></td><td><label for="friedrichhabetler' + compag.co_compag + '">' + compag.no_compag + '</label></td></tr>';
    }

    neo_dom += '</table>';

    document.getElementById("popup2_body").innerHTML = neo_dom;
    document.getElementById("val_ms").value = val;
    document.getElementById("popup2_btn_ok").setAttribute('onclick', 'master_process_multiselect(\'' + pagid + '\', \'' + refid + '\')');

    //seleccionar los elementos
    var mval = val.split(',');
    for (var i = 0; i < mval.length; i++) {
        document.getElementById('friedrichhabetler' + mval[i]).checked = true;
    }
}

function master_process_multiselect(pagid, refid) {
    var proms = '';
    var allmsi = document.getElementsByClassName('friedrichhabetler');

    for (var i = 0; i < allmsi.length; i++) {
        if (allmsi[i].checked == true)
            proms += allmsi[i].value + ','
    }

    proms = proms.substring(0, proms.length - 1);

    console.log('lo seleccionado=' + proms);
    var iframe = document.getElementById(pagid);
    iframe.contentWindow.load_multiselect(refid, proms);
    (iframe.contentDocument || iframe.contentWindow.document).getElementById(refid + '_ms').value = proms;

    document.getElementById("popup2").style.display = "none";
    overlay(false);
}

function open_popup_date(objinp) {
    console.log();
    TMP_INPUT_DATE = objinp;
    console.log('[open_popup_date]la date:' + objinp.value);
    window.parent.master_open_popup_date(objinp.value, CO_PAGINA);
}

function close_popup_date(objinp) {
    TMP_INPUT_DATE.value = objinp;
//    window.parent.master_open_popup_date(objinp.value, CO_PAGINA);
}

function docheckall(chk, reg) {
    console.log('this:' + chk);
    // console.log('this:' + chk.checked);
    // console.log('reg:' + reg);
    var ls_checks = document.getElementsByClassName('check');
    // var ls_checks = document.getElementsByClassName('check' + reg);
    // console.log('sie:' + ls_checks.length);
    if (chk.checked) {
        for (var i = 0; i < ls_checks.length; i++) {
            console.log('[CHECK]ls_checks[i].id:' + ls_checks[i].id);
            console.log('[CHECK]\'R\' + reg + \'_check\':' + ('R' + reg + '_check'));
            console.log('[CHECK]\'R\' + reg + \'_check\'?INDEX:' + ls_checks[i].id.indexOf('R' + reg + '_check'));
            if (ls_checks[i].id.indexOf('R' + reg + 'V_check') > -1) {
                // console.log("r:" + ls_checks[i]);
                ls_checks[i].setAttribute('checked', 'checked');
                ls_checks[i].checked = true;
            }
        }
    } else {
        for (var i = 0; i < ls_checks.length; i++) {
            if (ls_checks[i].id.indexOf('R' + reg + 'V_check') > -1) {
                // console.log("r:" + ls_checks[i]);
                ls_checks[i].removeAttribute('checked');
                ls_checks[i].checked = false;
            }
        }
    }
}

function Row() {
    this.ls_regist = [];
    this.getLs_regist = function () {
        return this.ls_regist;
    }

    this.setLs_regist = function (r) {
        this.ls_regist = r;
    }

    this.add = function (e) {
        this.ls_regist[this.ls_regist.length] = e;
    }
}

function Reg(key, value) {
    this._key;
    this._value;
    this.init = function (k, v) {
        this._key = k;
        this._value = v;
    }

    this.setKey = function (k) {
        this._key = k;
    }
    this.getKey = function () {
        return this._key
    }

    this.setValue = function (v) {
        this._value = v;
    }
    this.getValue = function () {
        return this._value;
    }

    this.init(key, value);
}

/*PROPAG GENERAL*/
function propagg(cycle, co_button, il_proces, co_condes) {
    /*POPUP ON*/
    window.parent.showloading(true);

    /*LOGIC*/
    var data = new FormData();
    data.append('id_frawor', '' + ID_FRAWOR);
    data.append('co_pagina', '' + CO_PAGINA);
    data.append('co_conten', '' + CO_CONTEN);
    data.append('co_botone', '' + co_button);
    data.append('il_proces', '' + il_proces);

    var ls_pagreg = document.getElementsByClassName('x64');
    var ls_rows = [];

    var all_regs = "[";

    // console.log("'PAG' + CO_PAGINA:" + 'PAG' + CO_PAGINA);
    // console.log("'PAG' + CO_PAGINA.length!:" + document.getElementById('PAG' + CO_PAGINA).tBodies[0].rows);

    for (var i = 0; i < document.getElementById('PAG' + CO_PAGINA).tBodies[0].rows.length; i++) {
        var x64s = document.getElementById('PAG' + CO_PAGINA).tBodies[0].rows[i].getElementsByClassName('pagreg');
        if (x64s.length > 0) {

            all_regs += "{";

            for (var o = 0; o < x64s.length; o++) {
                var x64 = x64s[o];
                var x64id = x64.getAttribute('id');
                var rowuid = x64id.substring(x64id.indexOf('C') + 1, x64id.indexOf('R'));
                var reguid = x64id.substring(x64id.indexOf('R') + 1, x64id.indexOf('_') == -1 ? x64id.length : x64id.indexOf('_'));
                reguid = reguid.replace('V', '');
                var regval = '';

                // var rows_row = ls_rows[parseInt(rowuid) - 1];
                // console.log("x64:" + x64 + ", x64id:" + x64id + ", rowuid:" + rowuid + ", reguid:" + reguid);

                all_regs += "\"co_regist_" + reguid + "\":";

                if (x64.tagName == 'INPUT') {
                    console.log('?INPUT?=>' + x64.getAttribute('TYPE'));
                    if (x64.getAttribute('TYPE') == 'text') {
                        regval = x64.value;
                        all_regs += "\"" + encodeURIComponent(regval) + "\",";
                    } else if (x64.getAttribute('TYPE') == 'hidden') {
                        regval = x64.value;
                        all_regs += "\"" + encodeURIComponent(regval) + "\",";
                    } /*else if (x64.getAttribute('TYPE') == 'checkbox') {
                     regval = x64.checked == undefined ? 'false' : '' + x64.checked;
                     all_regs += "\""+regval + "\",";
                     }*/
                } else if (x64.tagName == 'SELECT') {
                    console.log('is select');
                } else if (x64.tagName == 'SPAN') {
                    console.log('?INPUT?=>' + x64.getAttribute('ti_pagreg'));
                    var ti_pagreg = x64.getAttribute('ti_pagreg');
                    switch (ti_pagreg) {
                        case '1': {
                            console.log('FSP:' + x64.getElementsByTagName('INPUT'));
                            console.log('FSP:' + x64.getElementsByTagName('INPUT').length);
                            if (x64.getElementsByTagName('INPUT').length == 0) {
                                //es de tipo lectura
                                regval = (x64.getAttribute('va_pagreg') == undefined | x64.getAttribute('va_pagreg') == 'undefined') ? '' : x64.getAttribute('va_pagreg');
                                all_regs += "\"" + encodeURIComponent(regval) + "\",";
                            } else {
                                var iinput = x64.getElementsByTagName('INPUT')[0];
                                console.log('iinput===>' + iinput);
                                console.log('es del tipo 1 entonces?' + iinput.getAttribute('TYPE'));
                                if (iinput.getAttribute('TYPE') == 'checkbox') {
                                    regval = iinput.checked;
                                    all_regs += regval + ",";
                                } else if (iinput.getAttribute('TYPE') == 'text') {
                                    regval = iinput.value;
                                    all_regs += "\"" + regval + "\",";
                                }
                            }

                            break;
                        }
                        case '2': {
                            all_regs += "\"" + x64.innerHTML + "\",";
                            break;
                        }
                        case '3': {
                            var iselect = x64.getElementsByTagName('SELECT')[0];
                            console.log("iselect:" + iselect);
                            regval = iselect.options[iselect.selectedIndex].value;
                            console.log("regval:" + regval);
                            all_regs += "\"" + regval + "\",";
                            break;
                        }
                        case '6': {
                            var iinput = x64.getElementsByTagName('INPUT')[0];
                            console.log('iinput===>' + iinput);
                            console.log('es del tipo 1 entonces?' + iinput.getAttribute('TYPE'));
                            if (iinput.getAttribute('TYPE') == 'checkbox') {
                                regval = iinput.checked == undefined ? 'false' : iinput.checked;
                                all_regs += regval + ",";
                            }
                            break;
                        }
                        case '36': {
                            // var iselect = x64.getElementsByTagName('SELECT')[0];
                            // console.log("iselect:" + iselect);
                            // regval = iselect.options[iselect.selectedIndex].value;
                            // console.log("regval:" + regval);

                            // all_regs += "\"" + regval + "\",";

                            //-----
                            // var vaframe = document.getElementById(proimg).getElementsByTagName("IFRAME")[0];
                            var vaframe = x64.getElementsByTagName("IFRAME")[0];
                            var futureJson;
                            // var waitfor = false;

                            try {
                                futureJson = vaframe.contentWindow.document.getElementsByTagName("BODY")[0].innerHTML;
                                futureJson = futureJson.replace('<pre>', '').replace('</pre>', '');
                                console.log('futureJson=' + futureJson)
                                futureJson = JSON.parse(futureJson);

                                if (futureJson.status = 'OK') {
                                    // var eledom = document.getElementById(proimg);
                                    // id = eledom.id.substring(eledom.id.indexOf('R') + 1, eledom.id.indexOf('V'));

                                    // data.set('co_regist' + id, '' + futureJson.result[0].co_archiv);
                                    all_regs += "\"" + futureJson.result[0].co_archiv + "\",";
                                } else {
                                    all_regs += "\"\",";
                                }
                            } catch (e) {
                                all_regs += "\"\",";
                            }
                            //-----
                            break;
                        }
                        default: {
                            all_regs += '\"\"' + ",";
                        }

                    }
                    console.log('?is select:' + ti_pagreg);
                } else {
                    all_regs += "\"\",";
                }
            }
            all_regs = all_regs.substring(0, all_regs.length - 1);
            all_regs += "},";
        }
    }
    all_regs = all_regs.substring(0, all_regs.length - 1);
    all_regs += "]";

    data.append('ls_allreg', '' + all_regs);
    doPropagg('//' + window.location.host + '/wf?co_conten=' + co_condes, null, data);

}

function dinpag(obj, co_pagreg) {
    //preguntar al core que hacer?
    var data = new FormData();
    data.append('id_frawor', '' + ID_FRAWOR);
    data.append('co_pagina', '' + CO_PAGINA);
    data.append('co_conten', '' + CO_CONTEN);

    //VA_PAGREG
    var va_pagreg = '';
    if (obj.tagName.toLowerCase() == 'select') {
        try {
            va_pagreg = obj.options[obj.selectedIndex].value;
        } catch (e) {

        }
    } else if (obj.tagName.toLowerCase() == 'input') {
        if (obj.getAttribute('type').toLowerCase() == 'text') {
            va_pagreg = obj.value;
        } else if (obj.getAttribute('type').toLowerCase() == 'checkbox') {
            va_pagreg = obj.checked;
        }
    }


    data.append('va_pagreg', '' + va_pagreg);

    //    data.append('co_botone', '' + co_button);
//    data.append('il_proces', '' + il_proces);
    var ls_conpar = "{";


    // alert(("" + window.location) + "|" + (parent.location));


    var urls = ("" + parent.location).split('&');
    for (var i = 0; i < urls.length; i++) {
        var url = urls[i];
        console.log('url=' + url + "--->" + (url.indexOf('co_conpar') > -1));
        if (url.indexOf('co_conpar') > -1) {
            if (url.split('=').length = 2) {
                ls_conpar += "\"" + url.split('=')[0] + "\":\"" + url.split('=')[1] + "\",";
                // ls_conpar[ls_conpar.length] = new PARAM(url.split('=')[0], url.split('=')[1]);
            }
        }
    }
    ls_conpar = ls_conpar.length == 1 ? ls_conpar : ls_conpar.substring(0, ls_conpar.length - 1);
    ls_conpar += "}";
    console.log("ls_conpar:" + ls_conpar);
    data.append('ls_conpar', ls_conpar);
    doDinJson('/ocelot?co_conten=' + CO_CONTEN + '&co_pagina=' + CO_PAGINA + '&co_pagreg=' + co_pagreg + '&va_pagreg=' + va_pagreg + '&id_frawor=' + ID_FRAWOR, data);
}

var rowid_over_table_temp;

function dinpag2(obj, co_pagreg) {
    console.log('dinpag2>>>::' + obj);
    console.log('co_pagreg2>>>::' + co_pagreg);
    //preguntar al core que hacer?
    var data = new FormData();
    data.append('id_frawor', '' + ID_FRAWOR);
    data.append('co_pagina', '' + CO_PAGINA);
    data.append('co_conten', '' + CO_CONTEN);

    //VA_PAGREG
    var va_pagreg = '';
    if (obj.tagName.toLowerCase() == 'select') {
        try {
            va_pagreg = obj.options[obj.selectedIndex].value;
        } catch (e) {

        }
    } else if (obj.tagName.toLowerCase() == 'input') {
        if (obj.getAttribute('type').toLowerCase() == 'text') {
            va_pagreg = obj.value;
        } else if (obj.getAttribute('type').toLowerCase() == 'checkbox') {
            va_pagreg = obj.checked;
        }
    }


    data.append('va_pagreg', '' + va_pagreg);

    var ls_conpar = "{";

    var urls = ("" + parent.location).split('&');
    for (var i = 0; i < urls.length; i++) {
        var url = urls[i];
//        console.log('url=' + url + "--->" + (url.indexOf('co_conpar') > -1));
        if (url.indexOf('co_conpar') > -1) {
            if (url.split('=').length = 2) {
                ls_conpar += "\"" + url.split('=')[0] + "\":\"" + url.split('=')[1] + "\",";
                // ls_conpar[ls_conpar.length] = new PARAM(url.split('=')[0], url.split('=')[1]);
            }
        }
    }
    ls_conpar = ls_conpar.length == 1 ? ls_conpar : ls_conpar.substring(0, ls_conpar.length - 1);
    ls_conpar += "}";
    data.append('ls_conpar', ls_conpar);


    rowid_over_table_temp = obj.parentNode;
    rowid_over_table_temp = rowid_over_table_temp.parentNode;
    rowid_over_table_temp = rowid_over_table_temp.id;
    rowid_over_table_temp = rowid_over_table_temp.substring(rowid_over_table_temp.indexOf('C') + 1, rowid_over_table_temp.indexOf('R'));
    console.log('rowid_over_table_temp:' + rowid_over_table_temp);
    //---------------------
    //estructura momentanea
    var all_regs = "[";

    // console.log("'PAG' + CO_PAGINA:" + 'PAG' + CO_PAGINA);
    // console.log("'PAG' + CO_PAGINA.length!:" + document.getElementById('PAG' + CO_PAGINA).tBodies[0].rows);

    for (var i = 0; i < document.getElementById('PAG' + CO_PAGINA).tBodies[0].rows.length; i++) {
        if (rowid_over_table_temp == (i + 1)) {
            var x64s = document.getElementById('PAG' + CO_PAGINA).tBodies[0].rows[i].getElementsByClassName('pagreg');
            if (x64s.length > 0) {

                all_regs += "{";

                for (var o = 0; o < x64s.length; o++) {
                    var x64 = x64s[o];
                    var x64id = x64.getAttribute('id');
                    var rowuid = x64id.substring(x64id.indexOf('C') + 1, x64id.indexOf('R'));
                    var reguid = x64id.substring(x64id.indexOf('R') + 1, x64id.indexOf('_') == -1 ? x64id.length : x64id.indexOf('_'));
                    reguid = reguid.replace('V', '');
                    var regval = '';

                    // var rows_row = ls_rows[parseInt(rowuid) - 1];
                    // console.log("x64:" + x64 + ", x64id:" + x64id + ", rowuid:" + rowuid + ", reguid:" + reguid);

                    all_regs += "\"co_regist_" + reguid + "\":";

                    if (x64.tagName == 'INPUT') {
                        console.log('?INPUT?=>' + x64.getAttribute('TYPE'));
                        if (x64.getAttribute('TYPE') == 'text') {
                            regval = x64.value;
                            all_regs += "\"" + encodeURIComponent(regval) + "\",";
                        } else if (x64.getAttribute('TYPE') == 'hidden') {
                            regval = x64.value;
                            all_regs += "\"" + encodeURIComponent(regval) + "\",";
                        } /*else if (x64.getAttribute('TYPE') == 'checkbox') {
                         regval = x64.checked == undefined ? 'false' : '' + x64.checked;
                         all_regs += "\""+regval + "\",";
                         }*/
                    } else if (x64.tagName == 'SELECT') {
                        console.log('is select');
                    } else if (x64.tagName == 'SPAN') {
                        console.log('?INPUT?=>' + x64.getAttribute('ti_pagreg'));
                        var ti_pagreg = x64.getAttribute('ti_pagreg');
                        switch (ti_pagreg) {
                            case '1': {
                                console.log('FSP:' + x64.getElementsByTagName('INPUT'));
                                console.log('FSP:' + x64.getElementsByTagName('INPUT').length);
                                if (x64.getElementsByTagName('INPUT').length == 0) {
                                    //es de tipo lectura
                                    regval = (x64.getAttribute('va_pagreg') == undefined | x64.getAttribute('va_pagreg') == 'undefined') ? '' : x64.getAttribute('va_pagreg');
                                    all_regs += "\"" + encodeURIComponent(regval) + "\",";
                                } else {
                                    var iinput = x64.getElementsByTagName('INPUT')[0];
                                    console.log('iinput===>' + iinput);
                                    console.log('es del tipo 1 entonces?' + iinput.getAttribute('TYPE'));
                                    if (iinput.getAttribute('TYPE') == 'checkbox') {
                                        regval = iinput.checked;
                                        all_regs += regval + ",";
                                    } else if (iinput.getAttribute('TYPE') == 'text') {
                                        regval = iinput.value;
                                        all_regs += "\"" + regval + "\",";
                                    }
                                }

                                break;
                            }
                            case '2': {
                                all_regs += "\"" + x64.innerHTML + "\",";
                                break;
                            }
                            case '3': {
                                var iselect = x64.getElementsByTagName('SELECT')[0];
                                console.log("iselect:" + iselect);
                                regval = iselect.options[iselect.selectedIndex].value;
                                console.log("regval:" + regval);
                                all_regs += "\"" + regval + "\",";
                                break;
                            }
                            case '6': {
                                var iinput = x64.getElementsByTagName('INPUT')[0];
                                console.log('iinput===>' + iinput);
                                console.log('es del tipo 1 entonces?' + iinput.getAttribute('TYPE'));
                                if (iinput.getAttribute('TYPE') == 'checkbox') {
                                    regval = iinput.checked == undefined ? 'false' : iinput.checked;
                                    all_regs += regval + ",";
                                }
                                break;
                            }
                            case '36': {
                                // var iselect = x64.getElementsByTagName('SELECT')[0];
                                // console.log("iselect:" + iselect);
                                // regval = iselect.options[iselect.selectedIndex].value;
                                // console.log("regval:" + regval);

                                // all_regs += "\"" + regval + "\",";

                                //-----
                                // var vaframe = document.getElementById(proimg).getElementsByTagName("IFRAME")[0];
                                var vaframe = x64.getElementsByTagName("IFRAME")[0];
                                var futureJson;
                                // var waitfor = false;

                                try {
                                    futureJson = vaframe.contentWindow.document.getElementsByTagName("BODY")[0].innerHTML;
                                    futureJson = futureJson.replace('<pre>', '').replace('</pre>', '');
                                    console.log('futureJson=' + futureJson)
                                    futureJson = JSON.parse(futureJson);

                                    if (futureJson.status = 'OK') {
                                        // var eledom = document.getElementById(proimg);
                                        // id = eledom.id.substring(eledom.id.indexOf('R') + 1, eledom.id.indexOf('V'));

                                        // data.set('co_regist' + id, '' + futureJson.result[0].co_archiv);
                                        all_regs += "\"" + futureJson.result[0].co_archiv + "\",";
                                    } else {
                                        all_regs += "\"\",";
                                    }
                                } catch (e) {
                                    all_regs += "\"\",";
                                }
                                //-----
                                break;
                            }
                            default: {
                                all_regs += '\"\"' + ",";
                            }

                        }
                        console.log('?is select:' + ti_pagreg);
                    } else {
                        all_regs += "\"\",";
                    }
                }
                all_regs = all_regs.substring(0, all_regs.length - 1);
                all_regs += "},";
            }
        }
    }
    all_regs = all_regs.substring(0, all_regs.length - 1);
    all_regs += "]";

    data.append('ls_allreg', '' + all_regs);
    console.log('toda la dtaa readey!!!>>' + data);

    doDinJson2('/ocelot?co_conten=' + CO_CONTEN + '&co_pagina=' + CO_PAGINA + '&co_pagreg=' + co_pagreg + '&va_pagreg=' + va_pagreg + '&id_frawor=' + ID_FRAWOR, data, rowid_over_table_temp);
}

function PRINTABLE(opt) {
    document.getElementById('pagopt_print').style.visibility = 'visible';
}

function SHOWINFO(opt) {
    document.getElementById('pagopt_info').style.visibility = 'visible';
}

//function AUTOINCREMENT(opt) {
function OPTION_ADD(opt) {
    document.getElementById('pagopt_plus').style.visibility = 'visible';
    child_popup(opt, null, CO_CONTEN, null, null);
}

/*
 TRUE: se ejecuta automaticamente el valpagpos
 * @param {type} opt
 * @returns {undefined} */
function AUTODYNAMIC(opt, ls_regist) {
    console.log('?function AUTODYNAMIC:[opt:' + opt + '],[ls_regist:' + ls_regist + ']');
    DYNAMIC = opt;
    DYNAMIC_LS_REGIST = ls_regist;
    //--------------------------------
    //ultimo ver si se debe ejecutar el dinpage;
    console.log('DYNAMIC:' + DYNAMIC);
    if (DYNAMIC) {
        var dynpags = document.getElementsByClassName('dynpag');
        console.log('DYNAMIC->dynpags:' + dynpags + ',(*):' + dynpags.length);
        for (var i = 0; i < dynpags.length; i++) {
            var dynpag = dynpags[i];
            console.log('DYNAMIC->dynpags->dynpag:' + dynpag);
            console.log('DYNAMIC->dynpags->dynpag->dynpag.tagName:' + dynpag.tagName);
            if (dynpag.tagName == 'INPUT') {
                dynpag.onchange();
            } else if (dynpag.tagName == 'SELECT') {
                console.log('DYNAMIC->dynpags->dynpag->dynpag.tagName?onchange');
                dynpag.onchange();
            }
        }
    }
}

/*BUILDER*/
function builderType(type, ti_estreg, co_regist, ur_pagreg, il_onchag, id) {
    var html = "";
    if (type == 1) {
        if (ti_estreg == 'E') {
            html += "   <span id='" + id + "' name='" + id + "' ti_pagreg=\"1\" class=\"writer pagreg\" >";
            html += "       <div class=\"md-form mt-0\" style=\"margin-bottom: 0px;\">";
            html += "           <input type=text class=\"w3-input w3-border form-control\" value=\"\" " + (il_onchag ? "onchange=dinpag(this," + co_regist + ")" : "") + ">";
            html += "       </div>";
            html += "   </span>";
        } else if (ti_estreg == 'L') {
            html += "<span id='" + id + "' class=\"reader pagreg\" name='" + id + "' va_pagreg=\"\" ti_pagreg=\"1\"></span>";
        } else if (ti_estreg == 'O') {
            html += "<span id='" + id + "' class=\"reader pagreg\" name='" + id + "' va_pagreg=\"\" ti_pagreg=\"1\"></span>";
        }
    } else if (type == 3) {
        if (ti_estreg == 'E') {
            html += "   <span id='" + id + "' name='" + id + "' ti_pagreg=\"3\" class=\"writer " + (il_onchag ? "xaction" : "") + " pagreg\" >";
            html += "       <select class=\"mdb-select md-formx " + (il_onchag ? "dynpag" : "") + "\" " + (il_onchag ? "onchange=dinpag(this," + co_regist + ")" : "") + "></select>";
            html += "   </span>";
        } else if (ti_estreg == 'L') {
            html += "   <span id='" + id + "' name='" + id + "' ti_pagreg=\"3\" class=\"reader " + (il_onchag ? "xaction" : "") + " pagreg\" >";
            html += "       <select class=\"mdb-select md-formx " + (il_onchag ? "dynpag" : "") + "\" " + (il_onchag ? "onchange=dinpag(this," + co_regist + ")" : "") + "disabled></select>";
            html += "   </span>";
        } else if (ti_estreg == 'O') {
            html += "   <span id='" + id + "' name='" + id + "' ti_pagreg=\"3\" class=\"reader " + (il_onchag ? "xaction" : "") + " pagreg\" >";
            html += "       <select class=\"mdb-select md-formx " + (il_onchag ? "dynpag" : "") + "\" " + (il_onchag ? "onchange=dinpag(this," + co_regist + ")" : "") + "disabled></select>";
            html += "   </span>";
        }
    } else if (type == 4) {
        if (ti_estreg == 'E') {
            html += "   <span id='" + id + "' name='" + id + "' ti_pagreg=\"4\" class=\"writer " + (il_onchag ? "xaction" : "") + " pagreg\" >";
            html += "       <select class=\"mdb-select md-formx " + (il_onchag ? "dynpag" : "") + "\" " + (il_onchag ? "onchange=dinpag(this," + co_regist + ")" : "") + "><option value=\"\"></option></select>";
            html += "   </span>";
        } else if (ti_estreg == 'L') {
            html += "   <span id='" + id + "' name='" + id + "' ti_pagreg=\"4\" class=\"reader " + (il_onchag ? "xaction" : "") + " pagreg\" >";
            html += "       <select class=\"mdb-select md-formx " + (il_onchag ? "dynpag" : "") + "\" " + (il_onchag ? "onchange=dinpag(this," + co_regist + ")" : "") + "disabled value=\"\"><option></option</select>";
            html += "   </span>";
        } else if (ti_estreg == 'O') {
            html += "   <span id='" + id + "' name='" + id + "' ti_pagreg=\"4\" class=\"reader " + (il_onchag ? "xaction" : "") + " pagreg\" >";
            html += "       <select class=\"mdb-select md-formx " + (il_onchag ? "dynpag" : "") + "\" " + (il_onchag ? "onchange=dinpag(this," + co_regist + ")" : "") + "disabled value=\"\"><option></option</select>";
            html += "   </span>";
        }
    } else if (type == 6) {
        if (ti_estreg == 'E') {
            html += "   <span id='" + id + "' name='" + id + "' class='writer " + (il_onchag ? "dynpag" : "") + " pagreg' ti_pagreg='6' >";
            html += "       <div class='custom-control custom-checkbox'>";
            html += "           <input id='" + id + "D' type='checkbox' class='w3-input " + (il_onchag ? "dynpag" : "") + " custom-control-input' " + (il_onchag ? "onchange='dinpag(this," + co_regist + ")'" : "") + " checked/>";
            html += "           <label class='custom-control-label' for='" + id + "D'></label>";
            html += "       </div>";
            html += "   </span>";
        } else if (ti_estreg == 'L') {
            html += "   <span id='" + id + "' name='" + id + "' class='reader " + (il_onchag ? "dynpag" : "") + " pagreg' ti_pagreg='6' >";
            html += "       <div class='custom-control custom-checkbox'>";
            html += "           <input id='" + id + "D' type='checkbox' class='w3-input " + (il_onchag ? "dynpag" : "") + " custom-control-input' " + (il_onchag ? "onchange='dinpag(this," + co_regist + ")'" : "") + " checked disabled/>";
            html += "           <label class='custom-control-label' for='" + id + "D'></label>";
            html += "       </div>";
            html += "   </span>";
        } else if (ti_estreg == 'O') {
            html += "   <span id='" + id + "' name='" + id + "' class='reader " + (il_onchag ? "dynpag" : "") + " pagreg' ti_pagreg='6' >";
            html += "       <div class='custom-control custom-checkbox'>";
            html += "           <input id='" + id + "D' type='checkbox' class='w3-input " + (il_onchag ? "dynpag" : "") + " custom-control-input' " + (il_onchag ? "onchange='dinpag(this," + co_regist + ")'" : "") + " checked disabled/>";
            html += "           <label class='custom-control-label' for='" + id + "D'></label>";
            html += "       </div>";
            html += "   </span>";
        }
    } else if (type == 7) {
        if (ti_estreg == 'E') {
            html += "<span id='" + id + "' name='" + id + "' class=\"writer " + (il_onchag ? "xaction" : "") + " pagreg\" ti_pagreg=\"7\" >";
            html += "       <div class=\"md-form mt-0\" style=\"margin-bottom: 0px;\">";
            html += "           <input type=text class=\"w3-input w3-border form-control " + (il_onchag ? "dynpag" : "") + "\" " + (il_onchag ? "onblur=dinpag(this," + co_regist + ")" : "") + " onclick=\"open_popup_date(this);\" value=>";
            html += "       </div>";
            html += "</span>";
        } else if (ti_estreg == 'L') {
            html += "<span id='" + id + "' name='" + id + "' class=\"writer " + (il_onchag ? "xaction" : "") + " pagreg\" ti_pagreg=\"7\" >";
            html += "       <div class=\"md-form mt-0\" style=\"margin-bottom: 0px;\">";
            html += "           <input type=text class=\"w3-input w3-border form-control " + (il_onchag ? "dynpag" : "") + "\" " + (il_onchag ? "onblur=dinpag(this," + co_regist + ")" : "") + " onclick=\"open_popup_date(this);\" readonly disabled value=>";
            html += "       </div>";
            html += "</span>";
        } else if (ti_estreg == 'O') {
            html += "<span id='" + id + "' name='" + id + "' class=\"writer " + (il_onchag ? "xaction" : "") + " pagreg\" ti_pagreg=\"7\" >";
            html += "       <div class=\"md-form mt-0\" style=\"margin-bottom: 0px;\">";
            html += "           <input type=text class=\"w3-input w3-border form-control " + (il_onchag ? "dynpag" : "") + "\" " + (il_onchag ? "onblur=dinpag(this," + co_regist + ")" : "") + " onclick=\"open_popup_date(this);\" readonly disabled value=>";
            html += "       </div>";
            html += "</span>";
        }
    } else if (type == 9) {
        if (ti_estreg == 'E') {
            html += "   <span id='" + id + "' name='" + id + "' ti_pagreg=\"9\" class=\"writer pagreg\" >";
            html += "       <textarea class=\"w3-input w3-border\" rows=\"5\"></textarea>";
            html += "   </span>";
        } else if (ti_estreg == 'L') {
            html += "   <span id='" + id + "' name='" + id + "' ti_pagreg=\"9\" class=\"writer pagreg\" >";
            html += "       <textarea class=\"w3-input w3-border\" rows=\"5\"></textarea>";
            html += "   </span>";
        } else if (ti_estreg == 'O') {
            html += "<span id='" + id + "' class=\"reader pagreg\" name='" + id + "' va_pagreg=\"\" ti_pagreg=\"1\"></span>";
        }
    } else if (type == 22) {
        if (ti_estreg == 'E') {
            html += "   <span id='" + id + "' name='" + id + "' ti_pagreg=\"22\" class=\"writer pagreg\" >";
            html += "       <div class=\"md-form mt-0\" style=\"margin-bottom: 0px;\">";
            html += "           <input type='text' class='w3-input w3-border form-control' value='' " + (il_onchag ? "onchange='extractNumber(this, 0, true);dinpag(this," + co_regist + ")'" : "") + " onkeyup='extractNumber(this, 0, true);' onkeypress='return blockNonNumbers(this, event, false, true);' style='text-align:center;' >";
            html += "       </div>";
            html += "   </span>";
        } else if (ti_estreg == 'L') {
            html += "<span id='" + id + "' class='reader pagreg' name='" + id + "' va_pagreg='' ti_pagreg='22'></span>";
        } else if (ti_estreg == 'O') {
            html += "<span id='" + id + "' class='reader pagreg' name='" + id + "' va_pagreg='' ti_pagreg='22'></span>";
        }
    } else if (type == 23) {
        if (ti_estreg == 'E') {
            html += "   <span id='" + id + "' name='" + id + "' ti_pagreg=\"23\" class='writer pagreg' >";
            html += "       <div class='md-form mt-0' style='margin-bottom: 0px;'>";
            html += "           <input type='text' class='w3-input w3-border form-control' value='' " + (il_onchag ? "onchange='extractNumber(this, -1, true);dinpag(this," + co_regist + ")'" : "") + " onkeyup='extractNumber(this, -1, true);' onkeypress='return blockNonNumbers(this, event, true, true);' style='text-align:center;' >";
            html += "       </div>";
            html += "   </span>";
        } else if (ti_estreg == 'L') {
            html += "<span id='" + id + "' class='reader pagreg' name='" + id + "' va_pagreg='' ti_pagreg='23'></span>";
        } else if (ti_estreg == 'O') {
            html += "<span id='" + id + "' class='reader pagreg' name='" + id + "' va_pagreg='' ti_pagreg='23'></span>";
        }
    } else if (type == 34) {
        console.log('inside type=>ur_pagreg:' + ur_pagreg);
        if (ti_estreg == 'E') {
            html += "       <span id='" + id + "' name='" + id + "' ti_pagreg=\"34\" class=\"writer " + (il_onchag ? "dynpag" : "") + " pagreg\">"
                + "           <span valpag=\"\"></span>"
                + "           <button class='wf-button-transparent' onclick=\"child_popup(ur_pagreg, '" + id + "', CO_CONTEN,'titulo','')\" title='Abrir'><i class='fa fa-window-restore' aria-hidden='true'></i>Abrir</button>"
                + "           <button class='wf-button-transparent' onclick=\"clean_popup('" + id + "')\" title='Limpiar'><i class=\"fa fa-eraser\" aria-hidden=\"true\"></i>Limpiar</button>";
            html += "       </span>";
        } else if (ti_estreg == 'L') {
            html += "       <span id='" + id + "' name='" + id + "' ti_pagreg=\"34\" class=\"reader " + (il_onchag ? "dynpag" : "") + " pagreg\" >"
                + "           <span valpag=\"\"></span>"
                + "           <button class=\"wf-button-transparent\" onclick=\"child_popup(ur_pagreg,'" + id + "',CO_CONTEN,'titulo','')\" title=\"Abrir\"><i class=\"fa fa-window-restore\" aria-hidden=\"true\"></i>Abrir</button>"
                + "           <button class=\"wf-button-transparent\" onclick=\"clean_popup('" + id + "')\" title=\"Limpiar\"><i class=\"fa fa-eraser\" aria-hidden=\"true\"></i>Limpiar</button>";
            html += "       </span>";
        } else if (ti_estreg == 'O') {
            html += "       <span id='" + id + "' name='" + id + "' ti_pagreg=\"34\" class=\"reader " + (il_onchag ? "dynpag" : "") + " pagreg\" >"
                + "           <span valpag=\"\"></span>"
                + "           <button class=\"wf-button-transparent\" onclick=\"child_popup(ur_pagreg,'" + id + "',CO_CONTEN,'titulo','')\" title=\"Abrir\"><i class=\"fa fa-window-restore\" aria-hidden=\"true\"></i>Abrir</button>"
                + "           <button class=\"wf-button-transparent\" onclick=\"clean_popup('" + id + "')\" title=\"Limpiar\"><i class=\"fa fa-eraser\" aria-hidden=\"true\"></i>Limpiar</button>";
            html += "       </span>";
        }
    }

    return html;
}

function builderTyper2(old_type, new_type, ti_estreg, id, eledom) {
    console.log('old_type=' + old_type + ', new_type=' + new_type + ', ti_estreg=' + ti_estreg + ', eledom=' + eledom);
    var htmlreturn = "";
    if (new_type == 1) {
        if (ti_estreg == 'E') {
            htmlreturn += "<span id=\"" + id + "\" class=\"whiter pagreg\" name=\"" + id + "\" ti_pagreg=\"1\" co_regist=\"" + eledom.getAttribute('co_regist') + "\" va_pagreg=\"\"></span>";
            htmlreturn += "        <div class=\"md-form mt-0\" style=\"margin-bottom: 0px;\">";
            htmlreturn += "            <input type=\"text\" value=\"\" size=\"" + fila.getRegistroDTO().getCa_carcol() + "\" class=\"w3-input w3-border form-control \">";
            htmlreturn += "        </div>";
            htmlreturn += "</span>";
        } else {
            htmlreturn += "<span id=\"" + id + "\" class=\"whiter pagreg\" name=\"" + id + "\" ti_pagreg=\"1\" co_regist=\"" + eledom.getAttribute('co_regist') + "\" va_pagreg=\"\"></span>";
        }
    } else if (new_type == 3) {
        if (ti_estreg == 'E') {
            htmlreturn += "<span id=\"" + id + "\" class=\"whiter pagreg\" name=\"" + id + "\" ti_pagreg=\"3\" co_regist=\"" + eledom.getAttribute('co_regist') + "\" va_pagreg=\"\">";
            htmlreturn += "        <select name=\"regist" + 90 + "\" class=\"mdb-select md-formx " + (false ? "dynpag" : "") + "\" " + (false ? "onchange=dinpag2(this," + fila.getRegistroDTO().getCo_pagreg() + ")" : "") + "></select>";
            htmlreturn += "</span>";
        } else {
            htmlreturn += "<span id=\"" + id + "\" class=\"whiter pagreg\" name=\"" + id + "\" ti_pagreg=\"3\" co_regist=\"" + eledom.getAttribute('co_regist') + "\" va_pagreg=\"\">";
        }
    } else if (new_type == 4) {
        if (ti_estreg == 'E') {
            htmlreturn += "<span id=\"" + id + "\" class=\"whiter pagreg\" name=\"" + id + "\" ti_pagreg=\"4\" co_regist=\"" + eledom.getAttribute('co_regist') + "\" va_pagreg=\"\">";
            htmlreturn += "        <select name=\"regist" + 90 + "\" class=\"mdb-select md-formx " + (false ? "dynpag" : "") + "\" " + (false ? "onchange=dinpag2(this," + fila.getRegistroDTO().getCo_pagreg() + ")" : "") + "></select>";
            htmlreturn += "</span>";
        } else {
            htmlreturn += "<span id=\"" + id + "\" class=\"whiter pagreg\" name=\"" + id + "\" ti_pagreg=\"4\" co_regist=\"" + eledom.getAttribute('co_regist') + "\" va_pagreg=\"\">";
        }
    }
    return htmlreturn;
}

/*EVENTS*/
function extractNumber(obj, decimalPlaces, allowNegative) {
    var temp = obj.value;
    // avoid changing things if already formatted correctly
    var reg0Str = '[0-9]*';
    if (decimalPlaces > 0) {
        reg0Str += '\\.?[0-9]{0,' + decimalPlaces + '}';
    } else if (decimalPlaces < 0) {
        reg0Str += '\\.?[0-9]*';
    }
    reg0Str = allowNegative ? '^-?' + reg0Str : '^' + reg0Str;
    reg0Str = reg0Str + '$';
    var reg0 = new RegExp(reg0Str);
    if (reg0.test(temp))
        return true;
    // first replace all non numbers
    var reg1Str = '[^0-9' + (decimalPlaces != 0 ? '.' : '') + (allowNegative ? '-' : '') + ']';
    var reg1 = new RegExp(reg1Str, 'g');
    temp = temp.replace(reg1, '');
    if (allowNegative) {
// replace extra negative
        var hasNegative = temp.length > 0 && temp.charAt(0) == '-';
        var reg2 = /-/g;
        temp = temp.replace(reg2, '');
        if (hasNegative)
            temp = '-' + temp;
    }
    if (decimalPlaces != 0) {
        var reg3 = /\./g;
        var reg3Array = reg3.exec(temp);
        if (reg3Array != null) {
// keep only first occurrence of .
// and the number of places specified by decimalPlaces or the entire string if decimalPlaces < 0
            var reg3Right = temp.substring(reg3Array.index + reg3Array[0].length);
            reg3Right = reg3Right.replace(reg3, '');
            reg3Right = decimalPlaces > 0 ? reg3Right.substring(0, decimalPlaces) : reg3Right;
            temp = temp.substring(0, reg3Array.index) + '.' + reg3Right;
        }
    }
    obj.value = temp;
}

function blockNonNumbers(obj, e, allowDecimal, allowNegative) {
    var key;
    var isCtrl = false;
    var keychar;
    var reg;
    if (window.event) {
        key = e.keyCode;
        isCtrl = window.event.ctrlKey
    } else if (e.which) {
        key = e.which;
        isCtrl = e.ctrlKey;
    }
    if (isNaN(key))
        return true;
    keychar = String.fromCharCode(key);
    // check for backspace or delete, or if Ctrl was pressed
    if (key == 8 || isCtrl) {
        return true;
    }
    reg = /\d/;
    var isFirstN = allowNegative ? keychar == '-' && obj.value.indexOf('-') == -1 : false;
    var isFirstD = allowDecimal ? keychar == '.' && obj.value.indexOf('.') == -1 : false;
    return isFirstN || isFirstD || reg.test(keychar);
}

function checkEmail(I, L) {
    if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test($D.g_ID(I).value)) {
        $D.g_ID(L).innerHTML = "";
    } else {
        $D.g_ID(L).innerHTML = "<font color=red>E-mail no v&#225;lido</font>";
    }
}

function TI_ESTREG(className) {
    if (className.indexOf('reader') > -1) {
        return 'L';
    } else if (className.indexOf('writer') > -1) {
        return 'E';
    } else {
        return 'O';
    }
}