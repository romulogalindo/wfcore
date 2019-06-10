var $D = document;
var $MAP = {};
var CO_PAGINA;
var CO_CONTEN;
var ID_FRAWOR;
var IL_POPUP;

/*variables  de funcionalidad*/
var DYNAMIC = false;
var DYNAMIC_LS_REGIST = [];

function Parameter(co_pagreg, co_conpar) {
    this.conpar = co_conpar;
    this.pagreg = co_pagreg;

    this.getConpar = function () {
        return this.conpar
    };

    this.getPagreg = function () {
        return this.pagreg
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

function id_frawor() {
    return document.getElementById('id_frawor').value;
}

function co_conten() {
    return document.getElementById('co_conten').value;
}

function co_pagina() {
    return document.getElementById('co_pagina').value;
}

function ti_pagina() {
    return document.getElementById('ti_pagina').value;
}

function ls_hamoda() {
    return document.getElementById('ls_hamoda') != undefined ? document.getElementById('ls_hamoda').value : null;
}

function ifnull(val, def) {
    if (val == null | val == undefined) {
        return def
    } else
        return val;
}

function size_of_pagina() {
//    height_table = document.getElementsByTagName('BODY')[0].offsetHeight;
    height_table = document.getElementById('mainpagina').offsetHeight + 60;
//    alert('Set:height_table>>' + height_table);
//    alert('pagina=' + co_pagina() + ']=>' + height_table);
    document.getElementById('height_table').value = '' + height_table;
}

function il_popup() {
    return document.getElementById('il_popup').value;
}

function pagina() {
    //Seteamos el height para que lo absorva el iframe!<main.jsp
    size_of_pagina();
    /*SET INI*/
    CO_PAGINA = co_pagina();
    CO_CONTEN = co_conten();
    IL_POPUP = il_popup();

    doPagJson('/pangolin?co_conten=' + co_conten() + '&co_pagina=' + co_pagina() + '&id_frawor=' + id_frawor() + "&ls_hamoda=" + ls_hamoda());

    var input_cansubmit = document.getElementsByTagName('INPUT');

    for (var i = 0; i < input_cansubmit.length; i++) {
        var input_submit = input_cansubmit[i];
        if (input_submit.getAttribute('type').toUpperCase() == 'TEXT') {
            input_submit.addEventListener('keyup', doformulariosubmit);
        }
    }
}

function doformulariosubmit(keyEvent) {
    if (keyEvent.which == 10 || keyEvent.which == 13) {
        document.getElementsByClassName('wfbutton')[0].click();
    }
}

function pagina_onload(jsonData) {
    // console.log('[paginaload@' + co_pagina() + ']jsonData = ' + jsonData);
    // console.log('[paginaload@' + co_pagina() + ']jsonData.status = ' + jsonData.status);
    // console.log('[paginaload@' + co_pagina() + ']jsonData.result = ' + jsonData.result);
    //PAGINA SIEMPRE LISTA

    if (jsonData.status == 'OK') {
        if (jsonData.result.rows != undefined) {
            var rows = [];
            rows = jsonData.result.rows;

            // var dom2= document.getElementsByClassName("row1")[0].cloneNode(true);
            // var dom2 = '<tbody>' + document.getElementById("row1").innerHTML + '</tbody>'
            var dom2 = document.getElementById("row1") != undefined ? document.getElementById("row1").innerHTML : '';

            var tbody64;
            if (ti_pagina() == 'R') {
                tbody64 = document.getElementById('PAG' + co_pagina()).getElementsByTagName('TBODY')[0];
                tbody64.innerHTML = '';
                itr = itr.replace('<tr>', '').replace('</tr>', '');
            }

            for (var i = 0; i < rows.length; i++) {
                // console.log('[paginaload@' + co_pagina() + ']rows= ' + rows[i]);
                // console.log('[paginaload@' + co_pagina() + ']rows.regs = ' + rows[i].regs);
                // console.log('[paginaload@' + co_pagina() + ']ti_pagina()  = ' + ti_pagina());
                // console.log('[paginaload@' + co_pagina() + ']ti_pagina()?  = ' + (ti_pagina() == 'F'));

                if (ti_pagina() == 'F')
                    loadFormulario64((i + 1), rows[i], jsonData.aditional, dom2);
                else
                    loadReporte64((i + 1), tbody64, rows[i]);
            }

            /*BEFORE VIEW*/
            var fnpost = 'try{function xc() {this.newjspex = ' + jsonData.fnpost + ';} new xc().newjspex();}catch(e){console.log(\'WFAIO:\'+e)}';
            console.log('>>' + fnpost);
            eval(fnpost);
            //devuevo actualizar el height;
            size_of_pagina();

            window.parent.iframe2('PAG' + co_pagina(), height_table);

        } else {
            document.getElementById('PAG' + co_pagina()).style.display = 'none';
            window.parent.iframe2('PAG' + co_pagina(), -1);
            console.log('[pagina@\' + co_pagina() + \']rows unde fined!!= ' + jsonData.result);
        }
        document.getElementById('loader').style.display = 'none';
    } else {
        document.getElementById('content_table_loader').style.display = 'none';
        //seteo de valores
        document.getElementById('title_error').innerHTML = 'Error: P&aacute;gina ' + co_pagina();
        document.getElementById('detail_error').innerHTML = jsonData.error.message;

        document.getElementById('card_error').style.display = 'block';
    }

    /*TOOLTIP*/
    $('[data-toggle="tooltip"]').tooltip();
}

function iframe2(pagina, height_table) {
    console.log('renueva iframe>>' + pagina + ', des he=' + height_table);
    if (height_table == -1) {
        console.log('Llego -14>->>' + height_table + ",>>>" + pagina);
        document.getElementById(pagina).parentNode.parentNode.style.display = 'none';
        document.getElementById(pagina).parentNode.parentNode.setAttribute('style', 'display:none;');
    } else {
        document.getElementById(pagina).style.height = height_table + 'px';
        // document.getElementById('PAG628').parentNode.parentNode
    }

}

function readypagina(pag) {
    console.log('La pàgina ya cargo:' + pag);
}

function loadFormulario64(index, row, aditional, dom2) {
    if (index > 1) {
        var mpage = document.getElementById('PAG' + co_pagina());
        dom2 = dom2.replace('row1', 'row' + index);
        dom2 = dom2.replace(/C1/g, 'C' + index);
        var nele = document.createElement('TBODY');//.innerHTML = dom2;
        nele.setAttribute('id', 'row' + index)
        mpage.appendChild(nele);
        document.getElementById("row" + index).innerHTML = dom2;
    }

//    for (var x = 0; x < row.regs.length; x++) {
//        var reg = row.regs[x];
//    console.log('reg:' + row.regs);
//    var reg;
    for (const reg of row.regs) {
//        console.log('?' + reg);
        var ultraid = 'P' + co_pagina() + 'C' + index + 'R' + reg.regist + 'V';
        var eledom = document.getElementsByName(ultraid)[0];
        var valdom = reg.text == undefined ? (reg.value == undefined ? '' : reg.value) : reg.text;
        console.log('(' + co_pagina() + ')>>eledom=[' + eledom + ']');
        // console.log('>>eledom=[' + eledom + ':' + eledom.tagName + ', valdom=[' + valdom + ']');
        if (eledom) {
            console.log("(" + co_pagina() + ")==========EVAL DATA TYPE=>" + ultraid + ",=>?" + eledom.tagName);
            //EVALUACION DE TIPO DE DATO
            switch (eledom.tagName) {
                case "SPAN": {
                    var ti_pagreg = eledom.getAttribute('ti_pagreg');
                    console.log("[ti_pagreg:" + ti_pagreg + "]?[reg.type:" + reg.type + "]=>[(reg.type != undefined ):" + (reg.type != undefined) + "].[reg.type > -1):" + (reg.type > -1) + "]?===>" + (reg.type != undefined & reg.type > -1));

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

                    console.log('(' + co_pagina() + ')EVALUACION!> [de:' + ti_pagreg + '][a:' + reg.type + '][' + (reg.state != undefined) + '][' + (reg.state != undefined && ti_pagreg != reg.state) + ']');
                    // if ((reg.type != undefined & reg.type > -1) && ti_pagreg != reg.type && reg.type > 0) {
                    if ((reg.type != undefined & reg.type > -1) && ti_pagreg != reg.type) {
                        console.log('(' + co_pagina() + ')[' + reg.regist + ']Es un cambio de tipo:[de:' + ti_pagreg + '][a:' + reg.type + ']');
                        //de cualquier tipo(en span) a!

                        console.log('[' + reg.regist + ']eledom.getAttribute(\'class\')=' + eledom.getAttribute('class'));
                        console.log('[' + reg.regist + ']eledom.getAttribute(\'class\').indexof=' + eledom.getAttribute('class').indexOf('writer'));

                        ti_estreg = (reg.state != undefined) ? reg.state : ti_estreg;

                        td.innerHTML = builderType(reg.type, ti_estreg, co_regist, ur_pagreg, il_onchag, eleid);
                        console.log("*[" + reg.regist + "](" + co_pagina() + ") es el td:" + td);
                        //?
                        eledom = document.getElementsByName('P' + co_pagina() + 'C' + index + 'R' + reg.regist + 'V')[0];
                    }

                    //Si el elemento es lo mismo solo que con diferente estado para saltar la limitante del tipo de dato
                    if (reg.state != undefined && reg.state != ti_estreg) {
                        ti_estreg = (reg.state != undefined) ? reg.state : ti_estreg;
                        ti_pagreg = (reg.type != undefined & reg.type > -1) ? reg.type : ti_pagreg;

                        // td.innerHTML = builderType(reg.type, ti_estreg, co_regist, ur_pagreg, il_onchag, eleid);
                        td.innerHTML = builderType(ti_pagreg, ti_estreg, co_regist, ur_pagreg, il_onchag, eleid);
                        console.log("**[" + reg.regist + "](" + co_pagina() + ") es el td:" + td);
                        //?
                        eledom = document.getElementsByName('P' + co_pagina() + 'C' + index + 'R' + reg.regist + 'V')[0];
                    }
                }
            }

            eledom = document.getElementsByName(ultraid)[0];

            console.log("==========ASIGN DATA=>" + ultraid + ",=>?" + eledom);
            //ASIGNACION DE DATA
            switch (eledom.tagName) {
                case "INPUT": {
                    eledom.value = valdom;
                    if (eledom.getAttribute("type") != "hidden")
                        domtr(eledom).removeAttribute('style');
                    break;
                }
                case "SPAN": {
                    var ti_pagreg = eledom.getAttribute('ti_pagreg');
                    //console.log("ti_pagreg=" + ti_pagreg + "->" + (ti_pagreg == '13'));
                    if (ti_pagreg == '1') {
//                    eledom.getElementsByTagName("INPUT")[0].value = reg.value;
                        if (eledom.getElementsByTagName("INPUT").length > 0) {
                            eledom.getElementsByTagName("INPUT")[0].value = valdom;
                        } else {
                            eledom.innerHTML = valdom;
                        }

//                    eledom.setAttribute("va_pagreg", reg.value);
//                    eledom.innerHTML = valdom;
                    } else if (ti_pagreg == '3') {

                        //TEMPORAL=============================
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
                        //=====================================
                        var dom_compag = "";

                        if (ls_compag) {
                            for (var i = 0; i < ls_compag.length; i++) {
                                compag = ls_compag[i];

                                dom_compag += "<option value=\"" + compag.co_compag + "\" " + (reg.value == compag.co_compag ? "selected" : "") + ">" + compag.no_compag + "</option>";
                            }
                            eledom.getElementsByTagName("SELECT")[0].innerHTML = dom_compag;
                        }
                    } else if (ti_pagreg == '4') {
                        //TEMPORAL=============================
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
                        //=====================================
                        var dom_compag = "<option value=\"\"></option>";

                        if (ls_compag) {
                            for (var i = 0; i < ls_compag.length; i++) {
                                compag = ls_compag[i];

                                dom_compag += "<option value=\"" + compag.co_compag + "\" " + (reg.value == compag.co_compag ? "selected" : "") + ">" + compag.no_compag + "</option>";
                            }
                            eledom.getElementsByTagName("SELECT")[0].innerHTML = dom_compag;
                        }
                    } else if (ti_pagreg == '5') {
                        //valdom = valdom.replace('../reportes/paginaEspecial.jsp?', '/doc?ti_docume=E&');
                        // eledom.getElementsByTagName("CONPAG")[0].setAttribute('href', valdom);
                        //reg.regist
                        var ls_compag = aditional[reg.regist];
                        var dom_compag = "";

                        for (var i = 0; i < ls_compag.length; i++) {
                            compag = ls_compag[i];
                            var funstr = "rb_change(this,'" + eledom.getAttribute('id') + "')";
                            dom_compag += "<span ><input id='" + eledom.getAttribute('id') + "_" + compag.co_compag + "' name='" + eledom.getAttribute('id') + "' type='radio' value='" + compag.co_compag + "' " + (reg.value == compag.co_compag ? "checked" : "") + " onchange=\"" + funstr + "\" ><label for='" + eledom.getAttribute('id') + "_" + compag.co_compag + "'>" + compag.no_compag + "</label></span>";
                        }
                        eledom.innerHTML = dom_compag + eledom.innerHTML.replace('PAGREG5', valdom);

                    } else if (ti_pagreg == '6') {
                        // eledom.setAttribute("va_pagreg", reg.value);
                        eledom.getElementsByTagName("INPUT")[0].checked = reg.value;
                    } else if (ti_pagreg == '7') {
                        // eledom.setAttribute("va_pagreg", reg.value);
                        document.getElementById(eledom.getAttribute('id') + '_dd').value = ifnull(reg.value, '').substring(8, 10);
                        document.getElementById(eledom.getAttribute('id') + '_mm').value = ifnull(reg.value, '').substring(5, 7);
                        document.getElementById(eledom.getAttribute('id') + '_yyyy').value = ifnull(reg.value, '').substring(0, 4);
                        document.getElementById(eledom.getAttribute('id') + '_date').value = reg.value;
                        document.getElementById(eledom.getAttribute('id') + '_date').onchange = function () {
                            var fecha = this.value;
                            var id = this.getAttribute('id').replace('_date', '');
                            console.log('this.id=' + this.id);
                            document.getElementById(id + '_dd').value = fecha.substring(8, 10);
                            document.getElementById(id + '_mm').value = fecha.substring(5, 7);
                            document.getElementById(id + '_yyyy').value = fecha.substring(0, 4);
                        }

                        Calendar.setup({
                            inputField: eledom.getAttribute('id') + '_date', // id of the input field
                            ifFormat: "%Y-%m-%d", // format of the input field
                            button: eledom.getAttribute('id') + '_btn', // trigger for the calendar (button ID)
                            align: "cc", // alignment (defaults to "Bl")
                            singleClick: true
                        });
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
                case
                "A"
                : {
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


            console.log("==========UPDATE STATE");
            //EVALUACION DE TIPO DE ESTADO
            console.log('EVALUACION!> [reg.state!:' + reg.state + '][eledom:' + eledom + '][ti_estreg:' + ti_estreg + '][domtr(eledom):' + domtr(eledom) + ']');
            // if (reg.state != undefined) {
            // switch (ti_estreg) {
            //     case 'O': {
            //         domtr(eledom).setAttribute('style', 'display:none;');
            //         break;
            //     }
            //     case 'E': {
            //         domtr(eledom).removeAttribute('style');
            //         break;
            //     }
            //     case 'L': {
            //         domtr(eledom).getAttribute("co_pagtit").removeAttribute('style');
            //         break;
            //     }
            // }

            switch (ti_estreg) {
                case 'O': {
                    domtr(eledom).setAttribute('style', 'display:none;');
                    break;
                }
                case 'E': {
                    domtr(eledom).removeAttribute('style');
                    //-------
                    var domtitle = document.getElementsByName("P" + co_pagina() + "C" + index + "T" + domtr(eledom).getAttribute("co_pagtit"))[0];
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
                    var domtitle = document.getElementsByName("P" + co_pagina() + "C" + index + "T" + domtr(eledom).getAttribute("co_pagtit"))[0];
                    domtitle.setAttribute("style", "");
                    break;
                }
            }

            // if (reg.state != undefined) {
            /*if (reg.state == 'O') {
             domtr(eledom).setAttribute('style', 'display:none;');
             } else if (reg.state == 'E') {
             domtr(eledom).removeAttribute('style');
             } else if (reg.state == 'L') {
             domtr(eledom).getAttribute("co_pagtit").removeAttribute('style');
             }
             } else {*/


            //muestra al padre
            // console.log("(*)DOMELE: " + "P" + co_pagina() + "C" + index + "T" + eledom.getAttribute("co_pagtit"));
            // var domtitle = document.getElementsByName("P" + co_pagina() + "C" + index + "T" + domtr(eledom).getAttribute("co_pagtit"))[0];
            // domtitle.setAttribute("style", "");
            // }

        }
// document.getElementsByName('P' + co_pagina() + '' + reg.regist + 'V')[0].innerHTML = reg.front == undefined ? (reg.value == undefined ? '' : reg.value) : reg.front;
    }

//si los elementos no provienen del valpag->evaluarlos para que el padre!(titulo) se oculte
}

function loadReporte64(rowid, tbody64, row) {
    // console.log('[loadReporte64]Cargando tipo Reporte->ex tabla:' + row);
    // console.log('[loadReporte64]Cargando tipo Reporte->regs:' + row.regs);
    // var newRow = tbody64.insertRow(tbody64.rows.length);

    var n_itr = itr;
    // var all_tr = '';
    // console.log('[loadReporte64]Cargando tipo Reporte->itr:' + n_itr);

    var nr = document.createElement('tr');
    // nr.innerHTML = n_itr;
    var nrc = 'C' + rowid;
//    console.log('====>' + n_itr.replace(/X64UI/g, nrc));
//    console.log('====>' + n_itr.indexOf('X64UI'));
//    console.log('====>' + n_itr.indexOf("X64UI"));
    n_itr = n_itr.replace(/X64UI/g, 'P' + co_pagina() + nrc);
    n_itr = n_itr.replace(/%3C/g, '<');
    n_itr = n_itr.replace(/%3E/g, '>');
    n_itr = n_itr.replace(/X32UIR/g, '\'' + nrc + '\'');
//    console.log("_________________________________________________________-");
//    console.log(n_itr);
//    console.log("_________________________________________________________-");

    nr.innerHTML = n_itr;
    nr.setAttribute('id', 'row' + rowid);
    tbody64.appendChild(nr);
    //on post tr create and add!
    // nr = document.getElementById('row' + rowid);
    console.log('?>?nr:' + nr);

    for (var x = 0; x < row.regs.length; x++) {
        var reg = row.regs[x];
        // console.log('reg.regist =' + reg.regist + ',reg.value = ' + reg.value);
        // document.getElementsByName('P' + co_pagina() + 'T1R' + reg.regist + 'V')[0].innerHTML = reg.value;
        // document.getElementsByName('P' + co_pagina() + 'T1R' + reg.regist + 'V')[0].innerHTML = reg.value;
        var txtval = reg.text == undefined ? reg.value : reg.text;
        txtval = txtval == undefined ? '' : txtval;
        //n_itr = n_itr.replace('reg' + reg.regist + 'val', '' + (reg.front == undefined ? (reg.value == undefined ? '' : reg.value) : reg.front));
        // console.log('>[' + 'regist' + reg.regist + 'val' + '] >por> ' + txtval);
        // n_itr = n_itr.replace('regist' + reg.regist + 'val', '' + txtval);
        // n_itr.replace('<tr>','').replace('</tr>','')
        // console.log('[loadReporte64:update!]>>>:' + n_itr);

        // console.log('ELE:' + 'C' + rowid + 'R' + reg.regist);
        // alert('XCD');
//        console.log('====>C' +S rowid + 'R' + reg.regist + 'V');
        var ereg = document.getElementById('P' + co_pagina() + 'C' + rowid + 'R' + reg.regist + 'V');
        console.log('ereg:' + ereg);
        // console.log('ereg:' + ereg + ",?>" + ereg.getAttribute('ti_pagreg'));
        if (ereg != null) {
            // console.log('ereg:' + ereg + ",?>" + ereg.getAttribute('ti_pagreg'));
            // for (var i = 0; i < eregs.length; i++) {
            //     var ereg = eregs[i];
            if (ereg.getAttribute('ti_pagreg') == null) {
                ereg.value = txtval;
            } else if (ereg.getAttribute('ti_pagreg') == '1') {
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
                //TEMPORAL=============================
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
                //=====================================
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
                //TEMPORAL=============================
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
                //=====================================
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

    // var dom_pag = document.getElementById('PAG' + co_pagina());
    // var new_tr = dom_pag.getElementsByTagName('TBODY')[0].innerHTML + n_itr;
    // console.log('[loadReporte64]Cargando tipo Reporte->itr(3):' + new_tr);
    // dom_pag.getElementsByTagName('TBODY')[0].innerHTML = new_tr;
    //
    // //do-for
    // var alltd = document.getElementById('PAG' + co_pagina()).getElementsByClassName('ti_pag_Reg2');
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
    data.append('id_frawor', '' + id_frawor());
    data.append('co_pagina', '' + co_pagina());
    data.append('co_conten', '' + co_conten());
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
        console.log('[' + eledom.id + ']=?[' + 'P' + co_pagina() + cycle + 'R' + ']??>??' + eledom.id.indexOf('P' + co_pagina() + cycle + 'R'));
        if (eledom.id.indexOf('P' + co_pagina() + cycle + 'R') > -1) {
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
                    if (ti_pagreg == '1') {
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
                        val = document.getElementById(eledom.id + 'D').checked;
                    } else if (ti_pagreg == '7') {
                        // console.log('@@>>' + eledom);
                        // console.log('@@>>' + eledom.id + '_date');
                        // console.log('@@>>' + eledom.id + '_date');
                        // console.log('@@>>' + eledom.getElementById(eledom.id + '_date'));
                        // val = eledom.getElementById(eledom.id + '_date').value;
                        val = document.getElementById(eledom.id + '_date').value;
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
    var parametros = [];
    var btnpar = [];

    try {
        btnpar = eval('BTN' + co_button + 'P');
    } catch (e) {
        console.error('[prepair_parameters_propag64]e:' + e)
        //P9213C1R1P
        try {
            console.log('----------------->' + 'P' + co_pagina() + cycle + 'BTN' + co_button + 'P');
            //btnpar = eval('P' + co_pagina() + cycle + 'BTN' + co_button + 'P');
            eval(document.getElementById('P' + co_pagina() + cycle + 'BTN' + co_button + 'PS').innerHTML);
            btnpar = eval('P' + co_pagina() + cycle + 'BTN' + co_button + 'P');
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

        var eledom = document.getElementById('P' + co_pagina() + cycle + 'R' + spagreg + 'V');
        var valdom = '';
        switch (eledom.tagName) {
            case "INPUT": {
                valdom = eledom.value;
                break;
            }
            case "SPAN": {
                var ti_pagreg = eledom.getAttribute('ti_pagreg');
                //console.log("ti_pagreg=" + ti_pagreg + "->" + (ti_pagreg == '13'));
                if (ti_pagreg == '1') {
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
                    valdom = document.getElementById(eledom.id + '_date').value;
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

function child_popup(u, eleid, c, tit1, tit2) {
    var urlpopup = window.location.origin + "/" + u.replace("../wfl", "wf"); //[*]Esto debe de cambiar
    // console.log('child_popup->urlpopup:' + urlpopup);

    var co_conpar_1 = document.getElementById(eleid + 'V').getElementsByTagName('SPAN')[0].getAttribute('valpag');
    var co_conpar_2 = document.getElementById(eleid + 'V').getElementsByTagName('SPAN')[0].innerHTML;

    urlpopup = urlpopup + "" + "&co_conpar_1=" + encodeURIComponent(co_conpar_1) + "&co_conpar_2=" + encodeURIComponent(co_conpar_2) + "&co_conpad=" + c;

    // urlpopup = urlpopup + "&il_header=false";
    urlpopup = urlpopup + "&il_popup=true";
    // console.log("vp:" + urlpopup);

    window.parent.master_popup(CO_PAGINA, urlpopup, eleid, tit1 + "  >  <b>" + tit2 + "</b>");
}

function child_popup_update(regist, ls_params) {
    regist = regist + 'V';
    console.log('child_popup_update!::' + regist);
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
    window.parent.iframe2('PAG' + co_pagina(), height_table);
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

function rb_change(rb, id) {
    console.log('rb=' + rb + ',@=' + rb.value + ',id=' + id);
    console.log('-->' + id + '_rb==>' + document.getElementById(id + '_rb'));
    document.getElementById(id + '_rb').value = rb.value;
}

function onchange_vafile(vafile) {
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
    console.log("3lbfile!=" + document.getElementById(vafile.getAttribute('domid') + 'V'));
    // var label = document.getElementById(lbfile).getElementsByTagName("SPAN")[0];
    var label = document.getElementById(vafile.getAttribute('domid') + 'V').getElementsByTagName("SPAN")[0];
    if (vafile.files.length > 0) {
        console.log("vafile.files[0].name=" + vafile.files[0].name);
        console.log(">>vafile.files[0].name=" + (vafile.files[0].name == undefined || vafile.files[0].name == '' ? 'Subir archivo' : vafile.files[0].name));
        // console.log("vafile.files[0].name="+vafile.files[0].name);
        // lbfile.innerHTML = vafile.files[0].name == undefined || vafile.files[0].name == '' ? 'Subir archivo' : vafile.files[0].name;
        label.innerHTML = vafile.files[0].name == undefined || vafile.files[0].name == '' ? 'Subir archivo' : vafile.files[0].name;
    }

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
    // window.parent.master_open_multiselect('PAG' + co_pagina(), mid, $MAP[mid], document.getElementById(mid + '_ms').value);
    window.parent.master_open_multiselect('PAG' + co_pagina(), mid, $MAP[mid], document.getElementById(mid + '_ms').value);
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

function docheckall(chk, reg) {
    console.log('this:' + chk);
    // console.log('this:' + chk.checked);
    // console.log('reg:' + reg);
    var ls_checks = document.getElementsByClassName('check');
    // var ls_checks = document.getElementsByClassName('check' + reg);
    // console.log('sie:' + ls_checks.length);
    if (chk.checked) {
        for (var i = 0; i < ls_checks.length; i++) {
            if (ls_checks[i].id.indexOf('R' + reg + '_check') > -1) {
                // console.log("r:" + ls_checks[i]);
                ls_checks[i].setAttribute('checked', 'checked');
                ls_checks[i].checked = true;
            }
        }
    } else {
        for (var i = 0; i < ls_checks.length; i++) {
            if (ls_checks[i].id.indexOf('R' + reg + '_check') > -1) {
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
    data.append('id_frawor', '' + id_frawor());
    data.append('co_pagina', '' + co_pagina());
    data.append('co_conten', '' + co_conten());
    data.append('co_botone', '' + co_button);
    data.append('il_proces', '' + il_proces);

    var ls_pagreg = document.getElementsByClassName('x64');
    var ls_rows = [];

    var all_regs = "[";

    // console.log("'PAG' + co_pagina():" + 'PAG' + co_pagina());
    // console.log("'PAG' + co_pagina().length!:" + document.getElementById('PAG' + co_pagina()).tBodies[0].rows);

    for (var i = 0; i < document.getElementById('PAG' + co_pagina()).tBodies[0].rows.length; i++) {
        var x64s = document.getElementById('PAG' + co_pagina()).tBodies[0].rows[i].getElementsByClassName('pagreg');
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
                        all_regs += "\"" + regval + "\",";
                    } else if (x64.getAttribute('TYPE') == 'hidden') {
                        regval = x64.value;
                        all_regs += "\"" + regval + "\",";
                    } else if (x64.getAttribute('TYPE') == 'checkbox') {
                        regval = x64.checked;
                        all_regs += regval + ",";
                    }
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
                                regval = x64.getAttribute('va_pagreg');
                                all_regs += "\"" + regval + "\",";
                            } else {
                                var iinput = x64.getElementsByTagName('INPUT')[0];
                                console.log('iinput====>' + iinput);
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
    data.append('id_frawor', '' + id_frawor());
    data.append('co_pagina', '' + co_pagina());
    data.append('co_conten', '' + co_conten());

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

    doDinJson('/ocelot?co_conten=' + co_conten() + '&co_pagina=' + co_pagina() + '&co_pagreg=' + co_pagreg + '&va_pagreg=' + va_pagreg + '&id_frawor=' + id_frawor() + "&ls_hamoda=" + ls_hamoda(), data);
}

function PRINTABLE(opt) {
    document.getElementById('pagopt_print').style.visibility = 'visible';
}

function SHOWINFO(opt) {
    document.getElementById('pagopt_info').style.visibility = 'visible';
}

function AUTOINCREMENT(opt) {
    document.getElementById('pagopt_plus').style.visibility = 'visible';
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
            html += "   <span id='" + id + "' name='" + id + "' ti_pagreg=\"3\" class=\"writer pagreg\" >";
            html += "       <select class=\"mdb-select md-formx " + (il_onchag ? "dynpag" : "") + "\" " + (il_onchag ? "onchange=dinpag(this," + co_regist + ")" : "") + "></select>";
            html += "   </span>";
        } else if (ti_estreg == 'L') {
            html += "   <span id='" + id + "' name='" + id + "' ti_pagreg=\"3\" class=\"reader pagreg\" >";
            html += "       <select class=\"mdb-select md-formx " + (il_onchag ? "dynpag" : "") + "\" " + (il_onchag ? "onchange=dinpag(this," + co_regist + ")" : "") + "disabled></select>";
            html += "   </span>";
        } else if (ti_estreg == 'O') {
            html += "   <span id='" + id + "' name='" + id + "' ti_pagreg=\"3\" class=\"reader pagreg\" >";
            html += "       <select class=\"mdb-select md-formx " + (il_onchag ? "dynpag" : "") + "\" " + (il_onchag ? "onchange=dinpag(this," + co_regist + ")" : "") + "disabled></select>";
            html += "   </span>";
        }
    } else if (type == 4) {
        if (ti_estreg == 'E') {
            html += "   <span id='" + id + "' name='" + id + "' ti_pagreg=\"4\" class=\"writer pagreg\" >";
            html += "       <select class=\"mdb-select md-formx " + (il_onchag ? "dynpag" : "") + "\" " + (il_onchag ? "onchange=dinpag(this," + co_regist + ")" : "") + "><option value=\"\"></option></select>";
            html += "   </span>";
        } else if (ti_estreg == 'L') {
            html += "   <span id='" + id + "' name='" + id + "' ti_pagreg=\"4\" class=\"reader pagreg\" >";
            html += "       <select class=\"mdb-select md-formx " + (il_onchag ? "dynpag" : "") + "\" " + (il_onchag ? "onchange=dinpag(this," + co_regist + ")" : "") + "disabled value=\"\"><option></option</select>";
            html += "   </span>";
        } else if (ti_estreg == 'O') {
            html += "   <span id='" + id + "' name='" + id + "' ti_pagreg=\"4\" class=\"reader pagreg\" >";
            html += "       <select class=\"mdb-select md-formx " + (il_onchag ? "dynpag" : "") + "\" " + (il_onchag ? "onchange=dinpag(this," + co_regist + ")" : "") + "disabled value=\"\"><option></option</select>";
            html += "   </span>";
        }
    } else if (type == 6) {
        if (ti_estreg == 'E') {
            html += "   <span id='" + id + "' name='" + id + "' class='writer " + (il_onchag ? "dynpag" : "") + " pagreg' ti_pagreg='6' >";
            html += "       <div class='custom-control custom-checkbox'>";
            html += "           <input id='" + id + "D' type=checkbox class='w3-input " + (il_onchag ? "dynpag" : "") + " custom-control-input' " + (il_onchag ? "onchange=dinpag(this," + co_regist + ")" : "") + " checked/>";
            html += "           <label class='custom-control-label' for='" + id + "D'></label>";
            html += "       </div>";
            html += "   </span>";
        } else if (ti_estreg == 'L') {
            html += "   <span id='" + id + "' name='" + id + "' class='reader " + (il_onchag ? "dynpag" : "") + " pagreg' ti_pagreg='6' >";
            html += "       <div class='custom-control custom-checkbox'>";
            html += "           <input id='" + id + "D' type=checkbox class='w3-input " + (il_onchag ? "dynpag" : "") + " custom-control-input' " + (il_onchag ? "onchange=dinpag(this," + co_regist + ")" : "") + " checked disabled/>";
            html += "           <label class='custom-control-label' for='" + id + "D'></label>";
            html += "       </div>";
            html += "   </span>";
        } else if (ti_estreg == 'O') {
            html += "   <span id='" + id + "' name='" + id + "' class='reader " + (il_onchag ? "dynpag" : "") + " pagreg' ti_pagreg='6' >";
            html += "       <div class='custom-control custom-checkbox'>";
            html += "           <input id='" + id + "D' type=checkbox class='w3-input " + (il_onchag ? "dynpag" : "") + " custom-control-input' " + (il_onchag ? "onchange=dinpag(this," + co_regist + ")" : "") + " checked disabled/>";
            html += "           <label class='custom-control-label' for='" + id + "D'></label>";
            html += "       </div>";
            html += "   </span>";
        }
    } else if (type == 7) {
        if (ti_estreg == 'E') {
            html += "<span id='" + id + "' name='" + id + "' class=\"writer " + (il_onchag ? "xaction" : "") + " pagreg\" ti_pagreg=\"7\" >";
            html += "<input type=text id=\"" + id + "_dd\" class=\"wf_box_length2 wf_inline w3-input w3-border\" placeholder=\"dd\"/>";
            html += "<span>/</span>";
            html += "<input type=text id=\"" + id + "_mm\" class=\"wf_box_length2 wf_inline w3-input w3-border\" placeholder=\"mm\"/>";
            html += "<span>/</span>";
            html += "<input type=text id=\"" + id + "_yyyy\" class=\"wf_box_length4 wf_inline w3-input w3-border\" placeholder=\"yyyy\"/>";
            html += "<span id=\"" + id + "_btn\" class=\"wf-cal wf_inline\" title=\"Cambiar fecha\"><i class=\"fas fa-calendar-alt\"></i></span>";
            html += "<input type=hidden id=\"" + id + "_date\" class=\"w3-input w3-border\" />";
            html += "</span>";
        } else if (ti_estreg == 'L') {
            html += "<span id='" + id + "' name='" + id + "' class=\"writer " + (il_onchag ? "xaction" : "") + " pagreg\" ti_pagreg=\"7\" >";
            html += "<input type=text id=\"" + id + "_dd\" class=\"wf_box_length2 wf_inline w3-input w3-border\" disabled placeholder=\"dd\"/>";
            html += "<span>/</span>";
            html += "<input type=text id=\"" + id + "_mm\" class=\"wf_box_length2 wf_inline w3-input w3-border\" disabled placeholder=\"mm\"/>";
            html += "<span>/</span>";
            html += "<input type=text id=\"" + id + "_yyyy\" class=\"wf_box_length4 wf_inline w3-input w3-border\" disabled placeholder=\"yyyy\"/>";
            html += "<span id=\"" + id + "_btn\" class=\"wf-cal wf_inline\" title=\"Cambiar fecha\"><i class=\"fas fa-calendar-alt\"></i></span>";
            html += "<input type=hidden id=\"" + id + "_date\" class=\"w3-input w3-border\" />";
            html += "</span>";
        } else if (ti_estreg == 'O') {
            html += "<span id='" + id + "' name='" + id + "' class=\"writer " + (il_onchag ? "xaction" : "") + " pagreg\" ti_pagreg=\"7\" >";
            html += "<input type=text id=\"" + id + "_dd\" class=\"wf_box_length2 wf_inline w3-input w3-border\" placeholder=\"dd\"/>";
            html += "<span>/</span>";
            html += "<input type=text id=\"" + id + "_mm\" class=\"wf_box_length2 wf_inline w3-input w3-border\" placeholder=\"mm\"/>";
            html += "<span>/</span>";
            html += "<input type=text id=\"" + id + "_yyyy\" class=\"wf_box_length4 wf_inline w3-input w3-border\" placeholder=\"yyyy\"/>";
            html += "<span id=\"" + id + "_btn\" class=\"wf-cal wf_inline\" title=\"Cambiar fecha\"><i class=\"fas fa-calendar-alt\"></i></span>";
            html += "<input type=hidden id=\"" + id + "_date\" class=\"w3-input w3-border\" />";
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
    } else if (type == 34) {
        if (ti_estreg == 'E') {
            html += "       <span id='" + id + "' name='" + id + "' ti_pagreg=\"34\" class=\"writer " + (il_onchag ? "dynpag" : "") + " pagreg\">"
                + "           <span valpag=\"\"></span>+"
                + "           <button class=\"wf-button-transparent\" onclick=\"child_popup(ur_pagreg, '" + id + "', co_conten(),'titulo','')\" title=\"Abrir\"><i class=\"fa fa-window-restore\" aria-hidden=\"true\"></i></button>";
            html += "       </span>";
        } else if (ti_estreg == 'L') {
            html += "       <span id='" + id + "' name='" + id + "' ti_pagreg=\"34\" class=\"reader " + (il_onchag ? "dynpag" : "") + " pagreg\" >"
                + "           <span valpag=\"\"></span>+"
                + "           <button class=\"wf-button-transparent\" onclick=\"child_popup(ur_pagreg,'" + id + "',co_conten(),'titulo','')\" title=\"Abrir\"><i class=\"fa fa-window-restore\" aria-hidden=\"true\"></i></button>";
            html += "       </span>";
        } else if (ti_estreg == 'O') {
            html += "       <span id='" + id + "' name='" + id + "' ti_pagreg=\"34\" class=\"reader " + (il_onchag ? "dynpag" : "") + " pagreg\" >"
                + "           <span valpag=\"\"></span>+"
                + "           <button class=\"wf-button-transparent\" onclick=\"child_popup(ur_pagreg,'" + id + "',co_conten(),'titulo','')\" title=\"Abrir\"><i class=\"fa fa-window-restore\" aria-hidden=\"true\"></i></button>";
            html += "       </span>";
        }
    }

    return html;
}