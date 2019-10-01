/*
 * wfajax ver 1.0.0
 */

function Inet() {
    var xmlHttp = false;
    try {
        xmlHttp = new XMLHttpRequest();
    } catch (e) {
        // try {
        //     xmlHttp = new ActiveXObject("MSXML2.XMLHTTP");
        // } catch (e) {
        //     xmlHttp = false;
        // }
    }
    return xmlHttp;
}

//VALPAG
doPagJson = function (url) {
    var net = new Inet();
    net.open("POST", url, true); //false para que sea sincrono

    net.onreadystatechange = function () {
        if (net.readyState == 4 && net.status == 200) {
            var json = JSON.parse(net.responseText);
            pagina_onload(json);
        }
    };
    // net.send(null);
    net.send();
};

doLogoutJson = function () {
    var net = new inet();
    net.open("POST", "/logout64", true); //false para que sea sincrono

    net.onreadystatechange = function () {
        if (net.readyState == 4 && net.status == 200) {
            window.location.href = '//' + window.location.host + '/';
        }
    };

    net.send(null);
}

doPutParamForce = function (url) {
    console.log('url=' + url);
    var net = new Inet();
    net.open("POST", url, true); //false para que sea sincrono
    // net.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    net.onreadystatechange = function () {
        if (net.readyState == 4 && net.status == 200) {
            console.log(net.responseText);
        }
    }

    net.send();
}

doDinpag = function (url, regparams, data) {
    console.log('url=' + url);
//    console.log('regparams=' + regparams);
//    console.log('data=' + data);
    var net = new Inet();
    net.open("POST", "/ocelot", true); //false para que sea sincrono
    // net.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    net.onreadystatechange = function () {
        if (net.readyState == 4 && net.status == 200) {

            var rpta = JSON.parse(net.responseText);
            if (rpta.error) {
                window.parent.showloading(false);
                alert('' + rpta.error.message + '');
            } else {
                console.log("rpta.params===>" + rpta.ls_params);
                if (rpta.ls_params != undefined) {
                    for (var i = 0; i < rpta.ls_params.length; i++) {
                        console.log("rpta.params[i].no_param=" + rpta.ls_params[i].no_param);
                        for (var o = 0; o < regparams.length; o++) {
                            console.log("regparams[o] = " + regparams[o] + " &&&rpta.params[i].no_param = " + rpta.ls_params[i].no_param);
                            if (regparams[o].indexOf(rpta.ls_params[i].no_param) > -1) {
                                regparams[o] = rpta.ls_params[i].no_param + "=" + rpta.ls_params[i].va_param;
                            }
                        }
                    }
                }

                var urlpart = '';
                for (var o = 0; o < regparams.length; o++) {
                    urlpart += '&' + regparams[o];
                }

                if (rpta.no_action == 'REDIRECT') {
                    window.parent.location.href = url + urlpart;
                } else if (rpta.no_action == 'POPUP') {
                    // window.parent.page_to_master(regparams);
                    window.parent.page_to_master(rpta.ls_params, rpta.ls_pagina);
                } else if (rpta.no_action == 'REFRESH') {
                    for (var i = 0; i < ls_pagina.length; i++) {
                        //para todos los iframes que coincidan reload
                    }
                }

                // alert('URL>>>' + url + urlpart);
                // window.parent.location.href = url + urlpart;
            }

        }
    }

    net.send(data);


}

doPropag = function (url, regparams, data, co_button) {
    console.log('(1)url=' + url);
    console.log('(2)regparams=' + regparams);
    console.log('(3)data=' + data);
    var net = new Inet();
    net.open("POST", "/beaver", true); //false para que sea sincrono
    // net.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    net.onreadystatechange = function () {
        if (net.readyState == 4 && net.status == 200) {

            var rpta = JSON.parse(net.responseText);
            if (rpta.error) {
                window.parent.showloading(false);
                window.parent.showMessageModal(rpta.error.message);
//                alert('' + rpta.error.message + '');
            } else {
                console.log("rpta.params===>" + rpta.ls_params);
                if (rpta.ls_params != undefined) {
                    for (var i = 0; i < rpta.ls_params.length; i++) {
                        console.log("rpta.params[i].no_param=" + rpta.ls_params[i].no_param);
                        for (var o = 0; o < regparams.length; o++) {
                            console.log("regparams[o] = " + regparams[o] + " &&&rpta.params[i].no_param = " + rpta.ls_params[i].no_param);
                            if (regparams[o].indexOf(rpta.ls_params[i].no_param) > -1) {
                                regparams[o] = rpta.ls_params[i].no_param + "=" + rpta.ls_params[i].va_param;
                            }
                        }
                    }
                }

                var urlpart = '';
                for (var o = 0; o < regparams.length; o++) {
                    urlpart += '&' + regparams[o];
                }

                if (rpta.ls_params != undefined) {
                    for (var i = 0; i < rpta.ls_params.length; i++) {
                        console.log("rpta.params[i].no_param=" + rpta.ls_params[i].no_param);
                        if (urlpart.indexOf(rpta.ls_params[i].no_param) == -1) {
                            urlpart += "&" + rpta.ls_params[i].no_param + "=" + rpta.ls_params[i].va_param
                        }
                    }
                }

                for (var z = 0; z < rpta.ls_action.length; z++) {
                    var action = rpta.ls_action[z];
                    console.log('ACTION:' + action.no_action);

                    if (action.no_action == 'REDIRECT') {
                        //verificar co_condes
                        if (rpta.co_condes != undefined) {
                            url = url.substring(0, url.indexOf('=') + 1) + rpta.co_condes;
                            window.parent.location.href = url + urlpart + (IL_POPUP == 'true' ? '&il_popup=true' : '');
                        } else {
                            window.parent.location.href = url + urlpart + (IL_POPUP == 'true' ? '&il_popup=true' : '');
                        }
                    } else if (action.no_action == 'POPUP') {
                        // window.parent.page_to_master(regparams);
                        window.parent.page_to_master(action.ls_params, action.ls_pagina);
                    } else if (action.no_action == 'REFRESH') {
                        if (rpta.ls_params != undefined) {
                            for (var i = 0; i < rpta.ls_params.length; i++) {
                                doPutParamForce('/salamander?id_frawor=' + ID_FRAWOR + '&co_conten=' + CO_CONTEN + '&no_conpar=' + rpta.ls_params[i].no_param + '&va_conpar=' + encodeURIComponent(rpta.ls_params[i].va_param));
                            }
                        }

                        for (var i = 0; i < action.ls_pagina.length; i++) {
                            //para todos los iframes que coincidan reload
                            var irpta = action.ls_pagina[i];
                            //decirle al iframe padre que lo refresque
                            window.parent.dynamic_change_page('PAG' + irpta);
                        }
                        window.parent.showloading(false);
                    } else if (action.no_action == 'DOWNLOAD') {
                        var ff = action.ur_archiv;
                        document.getElementById("downloader").src = "/doc?ti_docume=DOWNLOAD&fileitem=" + ff;

                        window.parent.showloading(false);
                    } else if (action.no_action == 'OPENPOPUP') {
                        var ff = action.ur_archiv;
                        // document.getElementById("downloader").src = "/doc?ti_docume=DOWNLOAD&fileitem=" + ff;
                        window.parent.showloading(false);
                        btngo_popup(action.ur_popup, 'BTN' + co_button)
                    } else {
                        //se asume que es none
                        window.parent.showloading(false);
                    }
                }

                /*
                                if (rpta.no_action == 'REDIRECT') {
                                    //verificar co_condes
                                    if (rpta.co_condes != undefined) {
                                        url = url.substring(0, url.indexOf('=') + 1) + rpta.co_condes;
                                        window.parent.location.href = url + urlpart + (IL_POPUP == 'true' ? '&il_popup=true' : '');
                                    } else {
                                        window.parent.location.href = url + urlpart + (IL_POPUP == 'true' ? '&il_popup=true' : '');
                                    }

                                } else if (rpta.no_action == 'POPUP') {
                                    // window.parent.page_to_master(regparams);
                                    window.parent.page_to_master(rpta.ls_params);
                                } else if (rpta.no_action == 'REFRESH') {
                                    //SI HAY PARAMETROA A SOBRE-PONER
                                    if (rpta.ls_params != undefined) {
                                        for (var i = 0; i < rpta.ls_params.length; i++) {
                                            doPutParamForce('/salamander?id_frawor=' + ID_FRAWOR + '&co_conten=' + CO_CONTEN + '&no_conpar=' + rpta.ls_params[i].no_param + '&va_conpar=' + encodeURIComponent(rpta.ls_params[i].va_param));
                                        }
                                    }

                                    for (var i = 0; i < rpta.ls_pagina.length; i++) {
                                        //para todos los iframes que coincidan reload
                                        var irpta = rpta.ls_pagina[i];
                                        //decirle al iframe padre que lo refresque
                                        try {
                                            window.parent.dynamic_change_page('PAG' + irpta);
                                        } catch (e) {
                                            console.error("ERROR: La página " + irpta + " no existe.");
                                        }

                                    }

                                    window.parent.showloading(false);
                                } else if (rpta.no_action == 'DOWNLOAD') {
                                    var ff = rpta.ur_archiv;
                                    document.getElementById("downloader").src = "/doc?ti_docume=DOWNLOAD&fileitem=" + ff;

                                    window.parent.showloading(false);
                                } else {
                                    //se asume que es none
                                    window.parent.showloading(false);
                                }
                */
                // alert('URL>>>' + url + urlpart);
                // window.parent.location.href = url + urlpart;
            }

        }
    }

    net.send(data);
}

doPropagg = function (url, regparams, data) {
    console.log('url=' + url);
    console.log('regparams=' + regparams);
    console.log('data=' + data);
    var net = new Inet();
    net.open("POST", "/dingo", true); //false para que sea sincrono
    // net.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    net.onreadystatechange = function () {
        if (net.readyState == 4 && net.status == 200) {
            console.log('>>>>>>>>>>>>>' + net.responseText);
            var rpta = JSON.parse(net.responseText);
            if (rpta.error) {
                window.parent.showloading(false);
                alert('' + rpta.error.message + '');
            } else {
                console.log('NOHERE');
                // console.log("rpta.params===>" + rpta.ls_params);
                if (rpta.ls_params != undefined) {
                    for (var i = 0; i < rpta.ls_params.length; i++) {
                        console.log("rpta.params[i].no_param=" + rpta.ls_params[i].no_param);
                        if (regparams != null) {
                            for (var o = 0; o < regparams.length; o++) {
                                console.log("regparams[o] = " + regparams[o] + " &&&rpta.params[i].no_param = " + rpta.ls_params[i].no_param);
                                if (regparams[o].indexOf(rpta.ls_params[i].no_param) > -1) {
                                    regparams[o] = rpta.ls_params[i].no_param + "=" + rpta.ls_params[i].va_param;
                                }
                            }

                        }
                    }
                }

                var urlpart = '';
                if (regparams != null) {
                    for (var o = 0; o < regparams.length; o++) {
                        urlpart += '&' + regparams[o];
                    }
                }

                for (var z = 0; z < rpta.ls_action.length; z++) {
                    var action = rpta.ls_action[z];

                    if (action.no_action == 'REDIRECT') {
                        //verificar co_condes
                        if (rpta.co_condes != undefined) {
                            url = url.substring(0, url.indexOf('=') + 1) + rpta.co_condes;
                            window.parent.location.href = url + urlpart + (IL_POPUP == 'true' ? '&il_popup=true' : '');
                        } else {
                            window.parent.location.href = url + urlpart + (IL_POPUP == 'true' ? '&il_popup=true' : '');
                        }
                    } else if (action.no_action == 'POPUP') {
                        // window.parent.page_to_master(regparams);
                        window.parent.page_to_master(action.ls_params);
                    } else if (action.no_action == 'REFRESH') {
                        for (var i = 0; i < action.ls_pagina.length; i++) {
                            //para todos los iframes que coincidan reload
                            var irpta = action.ls_pagina[i];
                            //decirle al iframe padre que lo refresque
                            window.parent.dynamic_change_page('PAG' + irpta);
                        }
                        window.parent.showloading(false);
                    } else if (action.no_action == 'DOWNLOAD') {
                        var ff = action.ur_archiv;
                        document.getElementById("downloader").src = "/doc?ti_docume=DOWNLOAD&fileitem=" + ff;

                        window.parent.showloading(false);
                    } else if (action.no_action == 'OPENPOPUP') {
                        var ff = action.ur_archiv;
                        // document.getElementById("downloader").src = "/doc?ti_docume=DOWNLOAD&fileitem=" + ff;
                        window.parent.showloading(false);
                        child_popup(action.ur_popup, 'BTN' + co_button, CO_CONTEN, 'titulo', '')
                    } else {
                        //se asume que es none
                        window.parent.showloading(false);
                    }
                }

                /*
                if (rpta.no_action == 'REDIRECT') {
                    window.parent.location.href = url + urlpart;
                } else if (rpta.no_action == 'POPUP') {
                    // window.parent.page_to_master(regparams);
                    window.parent.page_to_master(rpta.ls_params);
                } else if (rpta.no_action == 'REFRESH') {
                    for (var i = 0; i < rpta.ls_pagina.length; i++) {
                        //para todos los iframes que coincidan reload
                        var irpta = rpta.ls_pagina[i];
                        //decirle al iframe padre que lo refresque
                        window.parent.dynamic_change_page('PAG' + irpta);
                    }
                    window.parent.showloading(false);
                } else if (rpta.no_action == 'DOWNLOAD') {
                    var ff = rpta.ur_archiv;
                    document.getElementById("downloader").src = "/doc?ti_docume=DOWNLOAD&fileitem=" + ff;

                    window.parent.showloading(false);
                } else {
                    //se asume que es none
                    window.parent.showloading(false);
                }
                * */

                // alert('URL>>>' + url + urlpart);
                // window.parent.location.href = url + urlpart;
            }

        }
    }

    net.send(data);


}

doDinJson = function (url, data) {
    var net = new Inet();
    net.open("POST", url, true); //false para que sea sincrono

    net.onreadystatechange = function () {
        if (net.readyState == 4 && net.status == 200) {
            // console.log('{NET(' + url + ')} ::::' + net.responseText)
            var json = JSON.parse(net.responseText);
            pagina_onload(json);
        }

    }
    // net.send(null);
    net.send(data);
}

doDinJson2 = function (url, data, rowuid) {
    var net = new Inet();
    net.open("POST", url, true); //false para que sea sincrono

    net.onreadystatechange = function () {
        if (net.readyState == 4 && net.status == 200) {
            // console.log('{NET(' + url + ')} ::::' + net.responseText)
            var json = JSON.parse(net.responseText);
            pagina_onload2(json, rowuid);
        }

    }
    // net.send(null);
    net.send(data);
}

getJSONE = function (url) {
    var net = new inet();
    net.open("POST", url, false); //false para que sea sincrono

    var json;
    net.onreadystatechange = function () {
        if (net.readyState == 4) {
            if (net.status == 200) {
                var m_res = net.responseText;
                m_res = m_res.substring(0, m_res.indexOf("}") + 1);
                json = m_res;
                try {
                    json = JSON.parse(m_res);
                } catch (e) {
                    json = undefined;
                }
            }
        }
    };
    net.send(null);
    return json;
};

getHTML = function (url) {
    var net = new Inet();
    // net.open("POST", url, false); //false para que sea sincrono

    var html;
    net.open("POST", url, false); //gettime will be the servlet name
    net.onreadystatechange = function () {
        if (net.readyState == 4) {
            if (net.status == 200) {
                console.log('------------------------------------------------');
                html = net.responseText;
                console.log('HTML>>' + html);
                console.log('------------------------------------------------');
            }
        }
    };
    net.send(null);
    console.log('rpta htm::' + html);
    return html;
};

getJSON = function (url) {
    var net = new Inet();
    // net.open("POST", url, false); //false para que sea sincrono

    var html;
    net.open("POST", url, false); //gettime will be the servlet name
    net.onreadystatechange = function () {
        if (net.readyState == 4) {
            if (net.status == 200) {
                console.log('------------------------------------------------');
                html = net.responseText;
                console.log('HTML>>' + html);
                console.log('------------------------------------------------');
            }
        }
    };
    net.send(null);
    console.log('rpta htm::' + html);
    return JSON.parse(html);
};

getHTML2 = function (url) {
    var net = new inet();
    net.open("POST", url, false); //false para que sea sincrono
    var html;
    net.open("GET", url, false); //gettime will be the servlet name
    var body = "<?xml version=\"1.0\"?><person><name>Romulo</name></person>";
    net.onreadystatechange = function () {
        if (net.readyState == 4) {
            if (net.status == 200) {
                html = net.responseXML;
            }
        }
    }
    net.send(null);
    return html;
};

getTEXT = function (url) {
    var net = new inet();
    net.open("POST", url, false); //false para que sea sincrono
    var html;
    net.open("POST", url, false); //false para que sea sincrono
    var t = navigator.userAgent;
    var t2 = t.indexOf("F");
    if (t2 > -1) {
        t2 = t.substring(t2, t.length);
        t2 = t2.replace("Firefox/", "");
    } else
        t2 = "";
    if (t2.indexOf("3") > -1) {
        net.onload = net.onerror = net.onabort = function () {
            html = net.responseText;
        };
    } else {
        net.onreadystatechange = function () {
            if (net.readyState == 4) {
                if (net.status == 200) {
                    html = net.responseText;
                }
            }
        }
    }
    net.send(null);
    return html;
}