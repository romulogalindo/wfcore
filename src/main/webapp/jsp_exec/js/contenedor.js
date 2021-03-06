var $D = document;
var $MAP = {};
var current_pagina = -1;
var current_regist = -1;
var current_button = -1;
var mutable_date_picker;

var CO_CONTEN;
var ID_FRAWOR;
var CO_USUARI;

/*elmento que ejecutara para ver el menu*/
var el_actmen;

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
    try {
        document.getElementById("myOverlay").style.display = show == true ? "block" : "none";
    } catch (e) {
        console.log('overlay:' + e)
    }
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

function workflow(il_popup, il_firtim) {
    CO_CONTEN = document.getElementById('co_conten').value;
    ID_FRAWOR = document.getElementById('id_frawor').value;
    CO_USUARI = document.getElementById('co_usuari') != undefined ? document.getElementById('co_usuari').value : null;

    /*FRONT*/
    if (!il_popup) {
        $(".button-collapse").sideNav();
        Ps.initialize(document.querySelector('.custom-scrollbar'));

        console.log('HERE!2569');
        if (el_actmen != undefined) {
            document.getElementById(el_actmen).click();
        }
    }

    for (var iframe of document.getElementsByTagName('IFRAME')) {
        if (iframe.getAttribute('id').indexOf("PAG") == 0) {

            var iframeType = iframe.getAttribute('type');
            console.log("iframeType:" + iframeType + ", =>" + (iframeType == 'B'));
            if (iframeType == 'B') {
                console.log(">>>>>" + iframe.style.maxWidth);
                iframe.style.maxWidth = '100%';
                console.log(">>>>>" + iframe.style.maxWidth);
                iframe.setAttribute('class', 'wf4_iframe fullwidth');
            }

            iframe.src = '/karmic?co_conten=' + CO_CONTEN
                + '&co_pagina=' + iframe.getAttribute('id').replace('PAG', '')
                + '&id_frawor=' + ID_FRAWOR
                + '&il_popup=' + il_popup;
        }

    }

//--FRONT AND MODALª!
    $(document).ready(function () {
        //P1
        $('.mdb-select').material_select();

        //P2
        if (!il_popup) {
            //Activando WEB-SOCKET
            openWS();
            //SHOW SYSOUT FT
            if (il_firtim) view_all_system();
        }

        //if
        if ($('#slc_schema').attr('style') !== undefined && $('#slc_schema').attr('style').indeOf('firsttime') > -1) {
            $('#slc_schema').modal('show');
        }

        //LISTO
        console.log('Esto se cargara');
        console.log('Esto se cargara:' + $('#rankanadate'));
    });

    $("#modal").on('hide.bs.modal', function () {
        document.getElementsByTagName('MAIN')[0].setAttribute('style', '');
        document.getElementsByTagName('HEADER')[0].setAttribute('style', '');
    });

    $("#popup").on('hide.bs.modal', function () {
        document.getElementsByTagName('MAIN')[0].setAttribute('style', '');
        document.getElementsByTagName('HEADER')[0].setAttribute('style', '');
    });
}

function doformulariosubmit(keyEvent) {
    if (keyEvent.which == 10 || keyEvent.which == 13) {
        document.getElementsByClassName('wfbutton')[0].click();
    }
}

function iframe(iframe) {

    var input_height_table = (iframe.contentDocument || iframe.contentWindow.document).getElementById("height_table");

    if (input_height_table != undefined) {
        var height_table = (input_height_table != undefined ? parseInt(input_height_table.value) : 0) + 20;
        iframe.style.height = height_table + 'px';
    }

}

function iframe2(pagina, height_table) {
    var localframe = document.getElementById(pagina);
    //console.log('renueva iframe>>' + pagina + ', des he=' + height_table);


//    (iframe.contentDocument || iframe.contentWindow.document).getElementById(refid + '_ms').value = proms;
//    
    if (height_table == -1) {
        //console.log('Llego -14>->>' + height_table + ",>>>" + pagina);
        // var localframe = document.getElementById(pagina);
        localframe.style.display = 'none';
        localframe.parentNode.style.display = 'none';

        //ocultar al padre wfcol!

//        document.getElementById(pagina).parentNode.parentNode.style.display = 'none';
//        document.getElementById(pagina).parentNode.parentNode.setAttribute('style', 'display:none;');
    } else {
        localframe.parentNode.style.display = 'block';
        localframe.setAttribute('style', 'display:block; height:;');

        // var iframe = document.getElementById(pagina);
        localframe.contentWindow.size_of_pagina();
        var input_height_table = (localframe.contentDocument || iframe.contentWindow.document).getElementById("height_table");
        if (input_height_table != undefined) {
            var height_table = (input_height_table != undefined ? parseInt(input_height_table.value) : 0);
            if (height_table > 60) {
                height_table = height_table + 20;
                localframe.style.height = height_table + 'px';
            } else {
                localframe.contentWindow.size_of_pagina();
                height_table = (input_height_table != undefined ? parseInt(input_height_table.value) : 0);
                console.log('caso particula de resize:' + height_table);
            }
        }
    }

}

function readypagina(pag) {
    console.log('La pàgina ya cargo:' + pag);
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

function master_popup(co_pagina, urlpopup, ele, titulo) {
    current_pagina = co_pagina;
    current_regist = ele;
    console.log('current_pagina =' + current_pagina);
    console.log('urlpopup =' + urlpopup);
    console.log('ele =' + ele);
    console.log('titulo =' + titulo);

    document.getElementsByTagName('MAIN')[0].setAttribute('style', 'filter:blur(5px)');
    document.getElementsByTagName('HEADER')[0].setAttribute('style', 'filter:blur(5px)');

    // $('#popup').modal({backdrop: 'static', show: true});
    $('#popup').modal({show: true});
    $('#popup_body').attr('src', urlpopup);
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

/*BUTTON GO POPUP GO*/
function master_button_popup(co_pagina, co_button, urlpopup) {
    current_pagina = co_pagina;
    current_button = co_button;
    console.log('current_pagina =' + current_pagina);
    console.log('urlpopup =' + urlpopup);
    console.log('ele =' + co_button);
    // console.log('titulo =' + titulo);

    document.getElementsByTagName('MAIN')[0].setAttribute('style', 'filter:blur(5px)');
    document.getElementsByTagName('HEADER')[0].setAttribute('style', 'filter:blur(5px)');

    // $('#popup').modal({backdrop: 'static', show: true});
    $('#popup').modal({show: true});
    $('#popup_body').attr('src', urlpopup);
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

function showloading(action) {
    if (action)
        $('#mdb-preloader').show();
    else
        $('#mdb-preloader').hide();
}

function showMessageModal(text) {
    $('#messageModalBody').html(text);
    $('#messageModal').modal({show: true});
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

function page_to_master(ls_params, ls_pagina) {
    console.log('Recibimos ls_params desde la pagina:' + ls_params + ", ls_pagina=" + ls_pagina);
    window.parent.popup_to_master(ls_params, ls_pagina);
}

function popup_to_master(ls_params, ls_pagina) {
    // console.log('Recibimos ls_params modo->:' + ls_params);
    // console.log('Recibimos ls_pagina modo->:' + ls_pagina);
    // console.log('Recibimos== y a ??>>' + current_pagina);
    //ahora hacia el hijo
    var mframe = document.getElementById('PAG' + current_pagina);
    mframe.contentWindow.child_popup_update(current_regist, ls_params);

    // console.log('listo!>');
    $('#popup').modal('hide');
    document.getElementsByTagName('MAIN')[0].setAttribute('style', '');
    document.getElementsByTagName('HEADER')[0].setAttribute('style', '');

    //-------------------
    if (ls_pagina != undefined && ls_pagina.length > 0) {
        for (var i = 0; i < ls_pagina.length; i++) {
            var pagref = ls_pagina[i];
            dynamic_change_page('PAG' + pagref);
        }
    }
}

function dynamic_change_page(pag) {
    var mframe = document.getElementById(pag);
    mframe.contentWindow.pagina();
}

function container(co_pagina) {
    return $(document.getElementById('PAG' + co_pagina));
}


function master_open_popup_date(val, co_pagina) {

    console.log('[open_popup_date]la date:' + val);
    $('#rankanadate').val(val);
    $('#rankanadate').attr('data-value', val);

    mutable_date_picker = $('#rankanadate').pickadate({
        // min: new Date(1960, 1, 1)
    });
    // muble_date_picker = $('#rankanadate').datepicker({
    //     // min: new Date(1960, 1, 1)
    // });
    $("#rankanadate").unbind("focus");
    $("#rankanadate").off("focus");

//    $("#rankanadate").datepicker("destroy");

    $('#rankanadate').attr('onchange', 'master_close_popup_date(this,' + co_pagina + ')');
    $('#rankanadate').click();
}

function master_close_popup_date(objinp, co_pagina) {
    //PENDIENTE!
    var iframe = document.getElementById('PAG' + co_pagina);
    iframe.contentWindow.close_popup_date(objinp.value);

    $("#rankanadate").pickadate("destroy");
    // mutable_date_picker.close();
}

function view_all_system() {
    $('#slc_schema').modal('show');
    return false;
}

function changemodulo(co_sistem, co_subsis, valid) {
    var rpta = getJSON("/uxtion?co_sistem=" + co_sistem + "&co_subsis=" + co_subsis);
    console.log('rpta=' + rpta);
    console.log('rpta=' + rpta.result);
    document.getElementById("ls_module").innerHTML = rpta.result;
    $('#topanel555').click();
    $('#slc_schema').modal('hide');
    // location.reload();
}

function showmenu(e) {
    console.log(">>" + e);
    // var element = $(e.target);
    e = '#' + e;
    console.log(">>" + e);
    var element = $(e);
    console.log(">>element>>    " + element);

    if (isChildrenOfPanelHeader(element)) {
        element = getPanelHeader(element);
    }

    element.toggleClass('active');
    accordionOpen(element);
}

function isChildrenOfPanelHeader(object) {
    var panelHeader = getPanelHeader(object);
    return panelHeader.length > 0;
}

function getPanelHeader(object) {
    return object.closest('li > .collapsible-header');
}

function accordionOpen(object) {
    $panelHeaders = $.find('#ls_module ul > li > .collapsible-header');

    if (object.hasClass('active')) {
        object.parent().addClass('active');
    } else {
        object.parent().removeClass('active');
    }

    if (object.parent().hasClass('active')) {
        object.siblings('.collapsible-body').stop(true, false).slideDown({
            duration: 350,
            easing: 'easeOutQuart',
            queue: false,
            complete: function complete() {
                $(this).css('height', '');
            }
        });
    } else {
        object.siblings('.collapsible-body').stop(true, false).slideUp({
            duration: 350,
            easing: 'easeOutQuart',
            queue: false,
            complete: function complete() {
                $(this).css('height', '');
            }
        });
    }

    $('#ls_module ul > li > .collapsible-header').not(object).removeClass('active').parent().removeClass('active');
    $('#ls_module ul > li > .collapsible-header').not(object).parent().children('.collapsible-body').stop(true, false).slideUp({
        duration: 350,
        easing: 'easeOutQuart',
        queue: false,
        complete: function complete() {
            $(this).css('height', '');
        }
    });
}

/*LOGOUT*/
function logout() {
    $D.doLogoutJson();
}