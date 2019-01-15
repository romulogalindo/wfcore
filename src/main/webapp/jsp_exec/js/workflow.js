var $D = document;

function w3_open() {
    document.getElementById("mySidebar").style.display = "block";
    document.getElementById("myOverlay").style.display = "block";
}

function w3_close() {
    document.getElementById("mySidebar").style.display = "none";
    document.getElementById("myOverlay").style.display = "none";
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

function workflow() {
    var iframes = document.getElementsByTagName('IFRAME');

    for (var i = 0; i < iframes.length; i++) {
        var iframe = iframes[i];
        var paginaId = iframe.getAttribute('id');
        iframe.src = '/karmic?co_conten=' + co_conten() + '&co_pagina=' + paginaId.replace('PAG', '') + '&id_frawor=' + id_frawor();
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
    $D.doPagJson('/pangolin?co_conten=' + co_conten() + '&co_pagina=' + co_pagina() + '&id_frawor=' + id_frawor());

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
                loadFormulario64(rows[i]);
            else
                loadReporte64(rows[i]);
        }

        //devuevo actualizar el height;
        size_of_pagina();
        // var miframe = window;//.parent.document.getElementById('#target');
        // window.parent.document.iframe2('PAG' + co_pagina(), height_table);
        window.parent.iframe2('PAG' + co_pagina(), height_table);

    } else {
        console.log('[pagina@\' + co_pagina() + \']rows unde fined!!= ' + jsonData.result);
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
    console.log('renueva iframe>>' + pagina + ', des he=' + height_table);
    document.getElementById(pagina).style.height = height_table + 'px';
}

function readypagina(pag) {
    console.log('La pàgina ya cargo:' + pag);
}


function loadFormulario64(row) {
    // console.log('[loadFormulario64@' + co_pagina() + ']Cargando tipo Formulario->ex reporte' + row);
    // console.log('[loadFormulario64@' + co_pagina() + ']Cargando tipo Formulario->ex reporte' + row);
    // console.log('[loadFormulario64@' + co_pagina() + ']Cargando tipo Formulario->ex reporte>regs' + row.regs);
    // console.log('[loadFormulario64@' + co_pagina() + ']Cargando tipo Formulario->ex reporte>regs' + row.regs.length);

    for (var x = 0; x < row.regs.length; x++) {
        var reg = row.regs[x];
        // console.log('[loadFormulario64@' + co_pagina() + ']reg.regist =' + reg.regist + ',reg.value = ' + reg.value);
        // document.getElementsByName('P' + co_pagina() + 'T1R' + reg.regist + 'V')[0].innerHTML = reg.value;
        document.getElementsByName('P' + co_pagina() + 'T1R' + reg.regist + 'V')[0].innerHTML = reg.front == undefined ? (reg.value == undefined ? '' : reg.value) : reg.front;
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