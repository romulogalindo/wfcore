var $D = document;
var $MAP = {};
var CO_PAGINA;
var CO_CONTEN;
var ID_FRAWOR;

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

function pagina() {
    //Seteamos el height para que lo absorva el iframe!<main.jsp
    size_of_pagina();
    /*SET INI*/
    CO_PAGINA = co_pagina();
    CO_CONTEN = co_conten();

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
    // try {
    //     console.log('[paginaload@' + co_pagina() + ']jsonData.result = ' + jsonData.result.rows);
    // } catch (e) {
    //     console.log('[paginaload@' + co_pagina() + ']jsonData.result  rows no existen= ');
    // }

    if (jsonData.result.rows != undefined) {
        var rows = [];
        rows = jsonData.result.rows;

        // var dom2= document.getElementsByClassName("row1")[0].cloneNode(true);
        // var dom2 = '<tbody>' + document.getElementById("row1").innerHTML + '</tbody>'
        var dom2 = document.getElementById("row1") != undefined ? document.getElementById("row1").innerHTML : '';

        for (var i = 0; i < rows.length; i++) {
            // console.log('[paginaload@' + co_pagina() + ']rows= ' + rows[i]);
            // console.log('[paginaload@' + co_pagina() + ']rows.regs = ' + rows[i].regs);
            // console.log('[paginaload@' + co_pagina() + ']ti_pagina()  = ' + ti_pagina());
            // console.log('[paginaload@' + co_pagina() + ']ti_pagina()?  = ' + (ti_pagina() == 'F'));

            if (ti_pagina() == 'F')
                loadFormulario64((i + 1), rows[i], jsonData.aditional, dom2);
            else
                loadReporte64(rows[i]);
        }

        /*BEFORE VIEW*/
        var fnpost = 'try{function xc() {this.newjspex = ' + jsonData.fnpost + ';} new xc().newjspex();}catch(e){console.log(\'WFAIO:\'+e)}';
        console.log('>>' + fnpost);
        eval(fnpost);

        //devuevo actualizar el height;
        size_of_pagina();
        // var miframe = window;//.parent.document.getElementById('#target');
        // window.parent.document.iframe2('PAG' + co_pagina(), height_table);
        window.parent.iframe2('PAG' + co_pagina(), height_table);

    } else {
        document.getElementById('PAG' + co_pagina()).style.display = 'none';
        window.parent.iframe2('PAG' + co_pagina(), -1);
        console.log('[pagina@\' + co_pagina() + \']rows unde fined!!= ' + jsonData.result);
    }

    document.getElementById('loader').style.display = 'none';
}

//function iframe(iframe) {
//
//    var input_height_table = (iframe.contentDocument || iframe.contentWindow.document).getElementById("height_table");
//
//    if (input_height_table != undefined) {
//        var height_table = (input_height_table != undefined ? parseInt(input_height_table.value) : 0) + 20;
//        iframe.style.height = height_table + 'px';
//    }
//
//}

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

    for (var x = 0; x < row.regs.length; x++) {
        var reg = row.regs[x];
        var eledom = document.getElementsByName('P' + co_pagina() + 'C' + index + 'R' + reg.regist + 'V')[0];
        var valdom = reg.text == undefined ? (reg.value == undefined ? '' : reg.value) : reg.text;
        console.log('>>eledom=[' + eledom + ']');
        // console.log('>>eledom=[' + eledom + ':' + eledom.tagName + ', valdom=[' + valdom + ']');
        if (eledom){
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
                        //valdom = valdom.replace('../reportes/paginaEspecial.jsp?', '/doc?ti_docume=E&');
                        // eledom.getElementsByTagName("CONPAG")[0].setAttribute('href', valdom);
                        //reg.regist
                        var ls_compag = aditional[reg.regist];
                        var dom_compag = "<option value=\"\"></option>";

                        for (var i = 0; i < ls_compag.length; i++) {
                            compag = ls_compag[i];

                            dom_compag += "<option value=\"" + compag.co_compag + "\" " + (reg.value == compag.co_compag ? "selected" : "") + ">" + compag.no_compag + "</option>";
                        }
                        eledom.getElementsByTagName("SELECT")[0].innerHTML = dom_compag;

                    } else if (ti_pagreg == '4') {
                        var ls_compag = aditional[reg.regist];
                        var dom_compag = "";

                        for (var i = 0; i < ls_compag.length; i++) {
                            compag = ls_compag[i];

                            dom_compag += "<option value=\"" + compag.co_compag + "\" " + (reg.value == compag.co_compag ? "selected" : "") + ">" + compag.no_compag + "</option>";
                        }
                        eledom.getElementsByTagName("SELECT")[0].innerHTML = dom_compag;
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

//muestra al padre
        console.log("DOMELE: " + "P" + co_pagina() + "C" + index + "T" + eledom.getAttribute("co_pagtit"));
        var domtitle = document.getElementsByName("P" + co_pagina() + "C" + index + "T" + domtr(eledom).getAttribute("co_pagtit"))[0];
        domtitle.setAttribute("style", "");
    }
// document.getElementsByName('P' + co_pagina() + '' + reg.regist + 'V')[0].innerHTML = reg.front == undefined ? (reg.value == undefined ? '' : reg.value) : reg.front;
    }

//si los elementos no provienen del valpag->evaluarlos para que el padre!(titulo) se oculte
}

function loadReporte64(row) {
    // console.log('[loadReporte64]Cargando tipo Reporte->ex tabla:' + row);
    // console.log('[loadReporte64]Cargando tipo Reporte->regs:' + row.regs);

    var n_itr = itr;
    // var all_tr = '';
    // console.log('[loadReporte64]Cargando tipo Reporte->itr:' + n_itr);
    for (var x = 0; x < row.regs.length; x++) {
        var reg = row.regs[x];
        // console.log('reg.regist =' + reg.regist + ',reg.value = ' + reg.value);
        // document.getElementsByName('P' + co_pagina() + 'T1R' + reg.regist + 'V')[0].innerHTML = reg.value;
        // document.getElementsByName('P' + co_pagina() + 'T1R' + reg.regist + 'V')[0].innerHTML = reg.value;
        var txtval = reg.text == undefined ? reg.value : reg.text;
        txtval = txtval == undefined ? '' : txtval;
        //n_itr = n_itr.replace('reg' + reg.regist + 'val', '' + (reg.front == undefined ? (reg.value == undefined ? '' : reg.value) : reg.front));
        // console.log('>[' + 'regist' + reg.regist + 'val' + '] >por> ' + txtval);
        n_itr = n_itr.replace('regist' + reg.regist + 'val', '' + txtval);
        // console.log('[loadReporte64:update!]>>>:' + n_itr);
    }
    // console.log('[loadReporte64]Cargando tipo Reporte->itr(2):' + n_itr);

    var dom_pag = document.getElementById('PAG' + co_pagina());
    var new_tr = dom_pag.getElementsByTagName('TBODY')[0].innerHTML + n_itr;
    dom_pag.getElementsByTagName('TBODY')[0].innerHTML = new_tr;

    //do-for
    var alltd = document.getElementById('PAG' + co_pagina()).getElementsByClassName('ti_pag_Reg2');
    for (var i = 0; i < alltd.length; i++) {
        alltd[i].getElementsByTagName('SPAN')[0].innerHTML = '' + (i + 1);
        // alltd[i].innerHTML = '<span>' + (i + 1) + '</span>span>';
    }
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

    var preimg = [];

    for (var eledom of document.getElementsByClassName('pagreg')) {

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
                //console.log("ti_pagreg=" + ti_pagreg + "->" + (ti_pagreg == '13'));
                if (ti_pagreg == '1') {
                    val = eledom.getElementsByTagName("INPUT")[0].value; //eledom.getAttribute("va_pagreg");
                } else if (ti_pagreg == '3') {
                    // valdom = valdom.replace('../reportes/paginaEspecial.jsp?', '/doc?ti_docume=E&');
                    var dom_select = eledom.getElementsByTagName("SELECT")[0];
                    val = dom_select.options[dom_select.selectedIndex].value;
                } else if (ti_pagreg == '4') {
                    // valdom = valdom.replace('../reportes/paginaEspecial.jsp?', '/doc?ti_docume=E&');
                    var dom_select = eledom.getElementsByTagName("SELECT")[0];
                    val = dom_select.options[dom_select.selectedIndex].value;
                } else if (ti_pagreg == '5') {
                    val = eledom.getElementById(eledom.id + '_rb').value;
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
                    // eledom.getElementsByTagName("SPAN")[0].setAttribute("valpag", (reg.value == undefined ? '' : reg.value));
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
    var parametros = '';

    for (var i = 0; i < eval('BTN' + co_button + 'P').length; i++) {
        var param = eval('BTN' + co_button + 'P')[i];
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
                    valdom = eledom.getElementsByTagName("INPUT")[0].value; //eledom.getAttribute("va_pagreg");
                } else if (ti_pagreg == '3') {
                    var dom_select = eledom.getElementsByTagName("SELECT")[0];
                    valdom = dom_select.options[dom_select.selectedIndex].value;
                } else if (ti_pagreg == '4') {
                    var dom_select = eledom.getElementsByTagName("SELECT")[0];
                    valdom = dom_select.options[dom_select.selectedIndex].value;
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

        parametros = parametros + '&co_conpar_' + sconpar + '=' + valdom;
    }

    doPropag('//' + window.location.host + '/wf?co_conten=' + co_condes + parametros, data);
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

    var eledom = document.getElementsByName(eleid)[0];

    if (eledom.tagName == "LABEL") {
//        console.log("co_conpar_1:" + escape(l.getAttribute('valpag')));
        //console.log("co_conpar_1:" + encodeURIComponent(l.getAttribute('valpag')));
        //console.log("co_conpar_2:" + encodeURIComponent(l.innerHTML));
        urlpopup = urlpopup + "" + "&co_conpar_1=" + encodeURIComponent(eledom.getAttribute('valpag')) + "&co_conpar_2=" + encodeURIComponent(eledom.innerHTML) + "&co_conpar_3=" + u + "&co_conpad=" + c + "&popup=1";
    } else {
        //console.log("co_conpar_1:" + encodeURIComponent(l.value));
        //console.log("co_conpar_2:" + encodeURIComponent(l.value));
        urlpopup = urlpopup + "" + "&co_conpar_1=" + encodeURIComponent(eledom.value) + "&co_conpar_2=" + encodeURIComponent(eledom.value) + "&co_conpar_3=" + u + "&co_conpad=" + c + "&popup=1";
    }

    urlpopup = urlpopup + "&il_header=false";
    console.log("vp:" + urlpopup);

    // $D.g_ID("popup_head").innerHTML = tit1 + "  >  <b>" + tit2 + "</b>";
    // // $D.g_ID("popup_body").onload = popup_resize;
    // $D.g_ID("popup_body").setAttribute("ele", ele);
    // $D.g_ID("popup_body").src = urlpopup;
    // $D.lock();
    // $D.g_ID('popup').style.display = "block";

    window.parent.master_popup(urlpopup, eleid, tit1 + "  >  <b>" + tit2 + "</b>");
}

function master_popup(urlpopup, ele, titulo) {
    overlay(true);
    document.getElementById("popup").style.display = "block";
    document.getElementById("popup_body").src = urlpopup;
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

// function onchange_vafile(vafile, lbfile) {
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

/*LOGOUT*/
function logout() {
    $D.doLogoutJson();
}