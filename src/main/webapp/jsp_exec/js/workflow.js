var $D = document;

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
    return document.getElementById('ls_hamoda').value;
}

function workflow() {
    for (var iframe of document.getElementsByTagName('IFRAME')) {
        iframe.src = '/karmic?co_conten=' + co_conten() + '&co_pagina=' + iframe.getAttribute('id').replace('PAG', '') + '&id_frawor=' + id_frawor();
    }
}

function size_of_pagina() {
    height_table = document.getElementsByTagName('BODY')[0].offsetHeight;
    document.getElementById('height_table').value = '' + height_table;
}

function pagina() {
    //Seteamos el height para que lo absorva el iframe!<main.jsp
    size_of_pagina();

    // console.log('[pagina@' + co_pagina() + ']inut#height_table = ' + document.getElementById('height_table'));
    // console.log('[pagina@' + co_pagina() + ']inut#height_table.value = ' + document.getElementById('height_table').value);
    //pedimos que ejecute el valpag y que nos de solo el contenido

    // ASYNBC
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

        for (var i = 0; i < rows.length; i++) {
            // console.log('[paginaload@' + co_pagina() + ']rows= ' + rows[i]);
            // console.log('[paginaload@' + co_pagina() + ']rows.regs = ' + rows[i].regs);
            // console.log('[paginaload@' + co_pagina() + ']ti_pagina()  = ' + ti_pagina());
            // console.log('[paginaload@' + co_pagina() + ']ti_pagina()?  = ' + (ti_pagina() == 'F'));

            if (ti_pagina() == 'F')
                loadFormulario64(rows[i], jsonData.aditional);
            else
                loadReporte64(rows[i]);
        }

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

function iframe(iframe) {

    var input_height_table = (iframe.contentDocument || iframe.contentWindow.document).getElementById("height_table");

    if (input_height_table != undefined) {
        var height_table = (input_height_table != undefined ? parseInt(input_height_table.value) : 0) + 20;
        iframe.style.height = height_table + 'px';
    }

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

function loadFormulario64(row, aditional) {
    // console.log('[loadFormulario64@' + co_pagina() + ']Cargando tipo Formulario->ex reporte' + row);
    // console.log('[loadFormulario64@' + co_pagina() + ']Cargando tipo Formulario->ex reporte' + row);
    // console.log('[loadFormulario64@' + co_pagina() + ']Cargando tipo Formulario->ex reporte>regs' + row.regs);
    // console.log('[loadFormulario64@' + co_pagina() + ']Cargando tipo Formulario->ex reporte>regs' + row.regs.length);


    for (var x = 0; x < row.regs.length; x++) {
        var reg = row.regs[x];
        // console.log('::>>P' + co_pagina() + '' + reg.regist + 'V');
        console.log('::>>P' + co_pagina() + 'R' + reg.regist + 'V');
        console.log('::>>V>>' + (reg.text == undefined ? (reg.value == undefined ? '' : reg.value) : reg.text));
        // document.getElementsByName('P' + co_pagina() + 'T1R' + reg.regist + 'V')[0].innerHTML = reg.value;
        // document.getElementsByName('P' + co_pagina() + 'T1R' + reg.regist + 'V')[0].innerHTML = reg.front == undefined ? (reg.value == undefined ? '' : reg.value) : reg.front;
        var eledom = document.getElementsByName('P' + co_pagina() + 'R' + reg.regist + 'V')[0];

        // console.log('::>>eledom=[' + eledom + ':' + eledom.tagName);
        var valdom = reg.text == undefined ? (reg.value == undefined ? '' : reg.value) : reg.text;
        console.log('>>eledom=[' + eledom + ':' + eledom.tagName + ', valdom=[' + valdom + ']');

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
                    eledom.setAttribute("va_pagreg", reg.value);
                    eledom.innerHTML = valdom;
                } else if (ti_pagreg == '13') {
                    valdom = valdom.replace('../reportes/paginaEspecial.jsp?', '/doc?ti_docume=E&');
                    eledom.getElementsByTagName("A")[0].setAttribute('href', valdom);
                } else if (ti_pagreg == '36') {
                    //valdom = valdom.replace('../reportes/paginaEspecial.jsp?', '/doc?ti_docume=E&');
                    var vafile = eledom.getElementsByTagName("IFRAME")[0].contentWindow.document.getElementById("vafile");
                    var lbfile = eledom.getElementsByTagName("SPAN")[0];
                    // eledom.getElementsByTagName("IFRAME")[0].contentWindow.document.getElementById("vafile").setAttribute("onchange", onchange_vafile(vafile, lbfile))
                    eledom.getElementsByTagName("IFRAME")[0].contentWindow.document.getElementById("vafile").onchange = function () {
                        onchange_vafile(vafile, lbfile);
                    };

                    // eledom.getElementsByTagName("SPAN")[0].setAttribute("valpag", (reg.value == undefined ? '' : reg.value));
                    // var onclicktext = eledom.getElementsByTagName("BUTTON")[0].getAttribute("onclick").replace("ur_pagreg", "'" + (reg.link == undefined ? '' : (reg.link)) + "'");
                    // console.log('onclicktext = ' + onclicktext);
                    // eledom.getElementsByTagName("BUTTON")[0].setAttribute("onclick", onclicktext);
                    // eledom.getElementsByTagName("SPAN")[0].innerHTML = valdom;
                } else if (ti_pagreg == '34') {
                    //valdom = valdom.replace('../reportes/paginaEspecial.jsp?', '/doc?ti_docume=E&');
                    //eledom.getElementsByTagName("A")[0].setAttribute('href', valdom);
                    eledom.getElementsByTagName("SPAN")[0].setAttribute("valpag", (reg.value == undefined ? '' : reg.value));
                    var onclicktext = eledom.getElementsByTagName("BUTTON")[0].getAttribute("onclick").replace("ur_pagreg", "'" + (reg.link == undefined ? '' : (reg.link)) + "'");
                    console.log('onclicktext = ' + onclicktext);
                    eledom.getElementsByTagName("BUTTON")[0].setAttribute("onclick", onclicktext);
                    eledom.getElementsByTagName("SPAN")[0].innerHTML = valdom;
                } else if (ti_pagreg == '38') {
                    //valdom = valdom.replace('../reportes/paginaEspecial.jsp?', '/doc?ti_docume=E&');
                    eledom.getElementsByTagName("A")[0].setAttribute('href', valdom);

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
                    //valdom = valdom.replace('../reportes/paginaEspecial.jsp?', '/doc?ti_docume=E&');
                    // eledom.getElementsByTagName("CONPAG")[0].setAttribute('href', valdom);
                    //reg.regist
                    var ls_compag = aditional[reg.regist];
                    var dom_compag = "";

                    for (var i = 0; i < ls_compag.length; i++) {
                        compag = ls_compag[i];

                        dom_compag += "<option value=\"" + compag.co_compag + "\" " + (reg.value == compag.co_compag ? "selected" : "") + ">" + compag.no_compag + "</option>";
                    }
                    eledom.getElementsByTagName("SELECT")[0].innerHTML = dom_compag;

                } else {
                    //hacer algo diferente para el tipo 38
                    eledom.innerHTML = valdom;
                }
                domtr(eledom).removeAttribute('style');
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
            default: {
                eledom.innerHTML = valdom;
                domtr(eledom).removeAttribute('style');
            }

        }
        //muestra al padre
        console.log("DOMELE: " + "P" + co_pagina() + "T" + eledom.getAttribute("co_pagtit"));
        var domtitle = document.getElementsByName("P" + co_pagina() + "T" + domtr(eledom).getAttribute("co_pagtit"))[0];
        domtitle.setAttribute("style", "");

        // document.getElementsByName('P' + co_pagina() + '' + reg.regist + 'V')[0].innerHTML = reg.front == undefined ? (reg.value == undefined ? '' : reg.value) : reg.front;
    }
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
        var txtval = reg.front == undefined ? reg.value : reg.front;
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


}

function propag(co_button, il_proces, co_condes) {
    var idFrawor = id_frawor();
    var coPagina = co_pagina();
    var coConten = co_conten();

    var parametros = '';
    var data = new FormData();
    data.append('id_frawor', '' + id_frawor());
    data.append('co_pagina', '' + co_pagina());
    data.append('co_conten', '' + co_conten());
    data.append('co_botone', '' + co_button);
    data.append('il_proces', '' + il_proces);

    var ls_regist = document.getElementsByClassName('pagreg');
    console.log('ls_regist = ' + ls_regist);

    for (var i = 0; i < ls_regist.length; i++) {
        var eledom = ls_regist[i];
        var id = "";
        var val = "";

        //console.log('ele = '+ele);
        //alert('ele = ' + ele);

        // if (eledom.tagName == 'INPUT') {
        //     id = ele.id.substring(ele.id.indexOf('R') + 1, ele.id.indexOf('V'));
        //     val = ele.value;
        // }else{
        //
        // }

        switch (eledom.tagName) {
            case "INPUT": {
                id = eledom.id.substring(eledom.id.indexOf('R') + 1, eledom.id.indexOf('V'));
                val = eledom.value;

                break;
            }
            case "SPAN": {
                id = eledom.id.substring(eledom.id.indexOf('R') + 1, eledom.id.indexOf('V'));

                var ti_pagreg = eledom.getAttribute('ti_pagreg');
                //console.log("ti_pagreg=" + ti_pagreg + "->" + (ti_pagreg == '13'));
                if (ti_pagreg == '1') {
                    val = eledom.getAttribute("va_pagreg");
                } else if (ti_pagreg == '3') {
                    // valdom = valdom.replace('../reportes/paginaEspecial.jsp?', '/doc?ti_docume=E&');
                    var dom_select = eledom.getElementsByTagName("SELECT")[0];
                    val = dom_select.options[dom_select.selectedIndex].value;
                } else if (ti_pagreg == '4') {
                    // valdom = valdom.replace('../reportes/paginaEspecial.jsp?', '/doc?ti_docume=E&');
                    var dom_select = eledom.getElementsByTagName("SELECT")[0];
                    val = dom_select.options[dom_select.selectedIndex].value;
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
                } else if (ti_pagreg == '36') {
                    //PWNDIENTE MARIO!!!
                    //precarga
                    // var vafile = valdom.getElementsByTagName("IFRAME")[0].contentWindow.document.getElementById("vafile");
                    var vaform = valdom.getElementsByTagName("IFRAME")[0].contentWindow.document.getElementById("form_data");

                    if (vafile != undefined) {
                        //do-precarga
                        var vafile = valdom.getElementsByTagName("IFRAME")[0].contentWindow.document.getElementById("vafile");
                        if (vafile.files.length > 0) {
                            vaform.submit();
                            var jsonrptafile= dowaitover()
                        }
                    }

                    // eledom.getElementsByTagName("SPAN")[0].setAttribute("valpag", (reg.value == undefined ? '' : reg.value));
                    // var onclicktext = eledom.getElementsByTagName("BUTTON")[0].getAttribute("onclick").replace("ur_pagreg", "'" + (reg.link == undefined ? '' : (reg.link)) + "'");
                    //
                    // eledom.getElementsByTagName("BUTTON")[0].setAttribute("onclick", onclicktext);
                    // eledom.getElementsByTagName("SPAN")[0].innerHTML = valdom;
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
            default: {
                eledom.innerHTML = valdom;
                domtr(eledom).removeAttribute('style');
            }

        }

        //console.log('K=' + id + ',V=' + val);
        //alert('K=' + id + ',V=' + val);
        data.append('co_regist' + id, val);
    }

    //alert('>>>>' + eval('BTN' + co_button + 'P') + ',-->' + eval('BTN' + co_button + 'P').length);
    for (var i = 0; i < eval('BTN' + co_button + 'P').length; i++) {
        var param = eval('BTN' + co_button + 'P')[i];
        var spagreg = param.getPagreg();
        var sconpar = param.getConpar();

        var eledom = document.getElementById('P' + coPagina + 'R' + spagreg + 'V');
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
                    valdom = eledom.getAttribute("va_pagreg");
                } else if (ti_pagreg == '3') {
                    // valdom = valdom.replace('../reportes/paginaEspecial.jsp?', '/doc?ti_docume=E&');
                    var dom_select = eledom.getElementsByTagName("SELECT")[0];
                    valdom = dom_select.options[dom_select.selectedIndex].value;
                } else if (ti_pagreg == '4') {
                    // valdom = valdom.replace('../reportes/paginaEspecial.jsp?', '/doc?ti_docume=E&');
                    var dom_select = eledom.getElementsByTagName("SELECT")[0];
                    valdom = dom_select.options[dom_select.selectedIndex].value;
                }
                break;
            }
            default: {
                valdom = eledom.innerHTML;
            }
        }

        parametros = parametros + '&co_conpar_' + sconpar + '=' + valdom;
    }

    //alert('DO! ' + parametros);
    doPropag('//' + window.location.host + '/wf?co_conten=' + co_condes + parametros, data);
    //window.location.href = '//' + window.location.host + '/wf?co_conten=' + co_condes;
}


function domtr(eledom) {
    if (eledom.tagName == 'TR') return eledom;

    eledom = eledom.parentNode;

    if (eledom.tagName == 'TR') return eledom;


    eledom = eledom.parentNode;

    if (eledom.tagName == 'TR') return eledom;
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

function doupload(ele) {
    var valdom = document.getElementById(ele);
    var vafile = valdom.getElementsByTagName("IFRAME")[0].contentWindow.document.getElementById("vafile");
    vafile.click();

    return false;
}

// function inputuploadchange(ele) {
//     var valdom = document.getElementById(ele);
//     var vafile = valdom.getElementsByTagName("IFRAME")[0].contentWindow.document.getElementById("vafile");
//     valdom.getElementsByTagName("A")[0].innerHTML = vafile.value;
// }

function onchange_vafile(vafile, lbfile) {
    console.log("vafile=" + vafile + " && lbfile=" + lbfile + " && " + vafile.files.length);
    // console.log("vafile=" + vafile + "\nlbfile=" + lbfile);
    if (vafile.files.length > 0) {
        lbfile.innerHTML = vafile.files[0].name == undefined || vafile.files[0].name == '' ? 'Subir archivo' : vafile.files[0].name;
    }

}

/*LOGOUT*/
function logout() {
    $D.doLogoutJson();
}