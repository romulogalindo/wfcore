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

function pagina() {
    //Seteamos el height para que lo absorva el iframe!<main.jsp
    height_table = document.getElementsByTagName('BODY')[0].offsetHeight
    document.getElementById('height_table').value = '' + height_table;

    //pedimos que ejecute el valpag y que nos de solo el contenido
    // var jsonData = VPAsync();
    var jsonData = $D.getJSON('/pangolin?co_conten=' + co_conten() + '&co_pagina=' + co_pagina() + '&id_frawor=' + id_frawor());

    console.log('jsonData = ' + jsonData);
    console.log('jsonData.status = ' + jsonData.status);
    console.log('jsonData.result = ' + jsonData.result);

    var rows = [];
    rows = jsonData.result.rows;

    for (var i = 0; i < rows.length; i++) {
        console.log('------>>>' + rows[i]);
        console.log('------>>>' + rows[i].regs);
        // try {
        //     console.log('------>>>' + rows[i].regs.xx);
        //     var row;
        //     for (var x = 0; i < row.regs.length(); x++) {
        //         var reg = row.regs[x];
        //         document.getElementsByName(reg.id + 'V').innerText = reg.value;
        //     }
        // } catch (e) {
        // }
        // up64(rows[i]);
        if (ti_pagina() == 'F')
            loadFormulario64(rows[i]);
        else
            loadReporte64(rows[i]);
    }
}

function iframe(iframe) {
    //var iframe = document.getElementById('iframeId');
    var innerDoc = iframe.contentDocument || iframe.contentWindow.document;
    var height_table = parseInt(innerDoc.getElementById("height_table").value) + 20;
    console.log('[3]recibido desde load iframe = ' + height_table);
    iframe.style.height = height_table + 'px';
}

function readypagina(pag) {
    console.log('La pàgina ya cargo:' + pag);
}


function loadFormulario64(row) {
    console.log('Cargando tipo Formulario->ex reporte');
    for (var x = 0; x < row.regs.length; x++) {
        var reg = row.regs[x];
        console.log('reg.regist =' + reg.regist + ',reg.value = ' + reg.value);
        // document.getElementsByName('P' + co_pagina() + 'T1R' + reg.regist + 'V')[0].innerHTML = reg.value;
        document.getElementsByName('P' + co_pagina() + 'T1R' + reg.regist + 'V')[0].innerHTML = reg.front == undefined ? (reg.value == undefined ? '' : reg.value) : reg.front;
    }
}

function loadReporte64(row) {
    console.log('Cargando tipo Reporte->ex tabla');
    var n_itr = itr;
    // var all_tr = '';

    for (var x = 0; x < row.regs.length; x++) {
        var reg = row.regs[x];
        console.log('reg.regist =' + reg.regist + ',reg.value = ' + reg.value);
        // document.getElementsByName('P' + co_pagina() + 'T1R' + reg.regist + 'V')[0].innerHTML = reg.value;
        document.getElementsByName('P' + co_pagina() + 'T1R' + reg.regist + 'V')[0].innerHTML = reg.value;
        n_itr = n_itr.replace('reg' + reg.regist + 'val', '' + (reg.front == undefined ? (reg.value == undefined ? '' : reg.value) : reg.front));
    }
    document.getElementById('PAG' + co_pagina()).getElementsByTagName('TBODY')[0].appendChild(document.createElement(n_itr));
}