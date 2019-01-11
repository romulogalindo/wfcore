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
    // var jsonData = VPAsync();
    var jsonData = $D.getJSON('/pangolin?co_conten=' + co_conten() + '&co_pagina=' + co_pagina() + '&id_frawor=' + id_frawor());

    console.log('jsonData = ' + jsonData);
    console.log('jsonData = ' + jsonData.status);
    console.log('jsonData = ' + jsonData.result);

    var rows = [];
    rows = jsonData.result.rows;

    for (var i = 0; i < rows.length; i++) {
        var row = rows[i];
        for (var o = 0; o < row.regs.length; o++) {
            var reg = row.regs[o];
            console.log("Registro vivo>>" + reg);
        }
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

/*CORE JS*/
// async function VPAsync() {
//     // await response of fetch call
//     let response = await fetch('/pangolin?co_conten=' + co_conten() + '&co_pagina=' + co_pagina() + '&id_frawor=' + id_frawor());
//     // only proceed once promise is resolved
//     // let data = await response.text();
//      let data = await response.json();
//     // only proceed once second promise is resolved
//
//     // console.log('>>>>'+data);
//
//     return response.json();;
// }

// function VPAsync() {
//     var url = '/pangolin?co_conten=' + co_conten() + '&co_pagina=' + co_pagina() + '&id_frawor=' + id_frawor();
//     return fetch(url).then(response =>
//         response.json().then(data => ({
//                 data: data,
//                 status: response.status
//             })
//         ).then(res => {
//             console.log(res.status, res.data.title)
//         }));
// }