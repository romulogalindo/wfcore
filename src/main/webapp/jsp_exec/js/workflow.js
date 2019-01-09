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
    var jsonData = VPAsync();
    console.log('jsonData = ' + jsonData);
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

/*CORE JS*/
async function VPAsync() {
    // await response of fetch call
    let response = await fetch('/pangolin?co_conten=' + co_conten() + '&co_pagina='+co_pagina() + '&id_fraword=' + id_frawor());
    // only proceed once promise is resolved
    let data = await response.json();
    // only proceed once second promise is resolved
    console.log('La data ya llego::'+data);
    return data;
}