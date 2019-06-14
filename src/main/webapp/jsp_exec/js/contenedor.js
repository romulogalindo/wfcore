var $D = document;
var $MAP = {};
var current_pagina = -1;
var current_regist = -1;
var mutable_date_picker;

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

function id_frawor() {
    return document.getElementById('id_frawor').value;
}

function co_conten() {
    return document.getElementById('co_conten').value;
}

// function co_pagina() {
//     return document.getElementById('co_pagina').value;
// }

// function ti_pagina() {
//     return document.getElementById('ti_pagina').value;
// }

function ls_hamoda() {
    return document.getElementById('ls_hamoda') != undefined ? document.getElementById('ls_hamoda').value : null;
}

function co_usuari() {
    return document.getElementById('co_usuari') != undefined ? document.getElementById('co_usuari').value : null;
}

function workflow(il_popup) {
    /*FRONT*/
    if (!il_popup) {
        $(".button-collapse").sideNav();
        Ps.initialize(document.querySelector('.custom-scrollbar'));
    }

    for (var iframe of document.getElementsByTagName('IFRAME')) {
        if (iframe.getAttribute('id').indexOf("PAG") == 0)
            iframe.src = '/karmic?co_conten=' + co_conten()
                + '&co_pagina=' + iframe.getAttribute('id').replace('PAG', '')
                + '&id_frawor=' + id_frawor()
                + '&il_popup=' + il_popup;
    }

//--FRONT AND MODALª!
    $(document).ready(function () {
        //P1
        $('.mdb-select').material_select();

        //P2
        if (!il_popup) {
            openWS();
        }
        //if
        $('#slc_schema').modal('show');
        //LISTO
        console.log('Esto se cargara');
        console.log('Esto se cargara:' + $('#rankanadate'));
//        $('#rankanadate').pickadate();
//        $("#rankanadate").unbind("focus");
//        $("#rankanadate").off("focus");
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

function size_of_pagina() {
    height_table = document.getElementsByTagName('BODY')[0].offsetHeight;
    document.getElementById('height_table').value = '' + height_table;
    document.getElementById('loader').style.height = '' + height_table + 'px';
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
    //console.log('renueva iframe>>' + pagina + ', des he=' + height_table);
    if (height_table == -1) {
        //console.log('Llego -14>->>' + height_table + ",>>>" + pagina);
        document.getElementById(pagina).style.display = 'none';

//        document.getElementById(pagina).parentNode.parentNode.style.display = 'none';
//        document.getElementById(pagina).parentNode.parentNode.setAttribute('style', 'display:none;');
    } else {
        document.getElementById(pagina).style.height = height_table + 'px';
        document.getElementById(pagina).style.display = 'block';
        // document.getElementById('PAG628').parentNode.parentNode
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
    // $("#popup").on('hide.bs.modal', function () {
    //     alert('modal is closed');
    //     document.getElementsByTagName('MAIN')[0].setAttribute('style', '');
    //     document.getElementsByTagName('HEADER')[0].setAttribute('style', '');
    // });
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

// function onchange_vafile(vafile, lbfile) {
// function onchange_vafile(vafile) {
//     // console.log("vafile=" + vafile + " && lbfile=" + lbfile + " && ");
//     console.log("vafile=" + vafile.innerHTML);
//     console.log("vafile=" + vafile.files);
//     // console.log("vafile=" + vafile.files.length);
//     // console.log("vafile=" + vafile.files.length);
//
//     console.log("this!=" + this);
//     console.log("this!=" + this.files);
//     // console.log("this!=" + this.files.length);
//     // console.log("lbfile!=" + lbfile);
//     console.log("2lbfile!=" + vafile.getAttribute('domid') + 'V');
//     console.log("3lbfile!=" + document.getElementById(vafile.getAttribute('domid') + 'V'));
//     // var label = document.getElementById(lbfile).getElementsByTagName("SPAN")[0];
//     var label = document.getElementById(vafile.getAttribute('domid') + 'V').getElementsByTagName("SPAN")[0];
//     if (vafile.files.length > 0) {
//         console.log("vafile.files[0].name=" + vafile.files[0].name);
//         console.log(">>vafile.files[0].name=" + (vafile.files[0].name == undefined || vafile.files[0].name == '' ? 'Subir archivo' : vafile.files[0].name));
//         // console.log("vafile.files[0].name="+vafile.files[0].name);
//         // lbfile.innerHTML = vafile.files[0].name == undefined || vafile.files[0].name == '' ? 'Subir archivo' : vafile.files[0].name;
//         label.innerHTML = vafile.files[0].name == undefined || vafile.files[0].name == '' ? 'Subir archivo' : vafile.files[0].name;
//     }
//
// }

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

function page_to_master(ls_params) {
    console.log('Recibimos ls_params desde la pagina:' + ls_params);
    window.parent.popup_to_master(ls_params);
}

function popup_to_master(ls_params) {
    console.log('Recibimos ls_params modo->:' + ls_params);
    console.log('Recibimos== y a ??>>' + current_pagina);
    //ahora hacia el hijo
    var mframe = document.getElementById('PAG' + current_pagina);
    // console.log('conseguimos la pagina1:' + mframe);
    // console.log('conseguimos la pagina2:' + mframe.contentDocument);
    // console.log('conseguimos la pagina3:' + mframe.contentWindow);
    // console.log('conseguimos la pagina4:' + mframe.contentWindow.document);
    // (mframe.contentDocument || mframe.contentWindow.document).child_popup_update(ls_params);
    // (mframe.contentDocument || mframe.contentWindow).child_popup_update(ls_params);
    mframe.contentWindow.child_popup_update(current_regist, ls_params);
    console.log('listo!>');
    $('#popup').modal('hide');
    document.getElementsByTagName('MAIN')[0].setAttribute('style', '');
    document.getElementsByTagName('HEADER')[0].setAttribute('style', '');

}

function dynamic_change_page(pag) {
    var mframe = document.getElementById(pag);
    mframe.contentWindow.pagina();
}

function container(co_pagina) {
    return $(document.getElementById('PAG' + co_pagina));
}


function master_open_popup_date(val, co_pagina) {
    // var currentDay = dateObj.getUTCDate();
    // var currentMonth = dateObj.getUTCMonth();
    // var currentYear = dateObj.getUTCFullYear();
    // var maxYear = currentYear - 18;
    // var minYear = currentYear - 100;

    console.log('[open_popup_date]la date:' + val);
    $('#rankanadate').val(val);
    $('#rankanadate').attr('data-value',val);

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

/*LOGOUT*/
function logout() {
    $D.doLogoutJson();
}