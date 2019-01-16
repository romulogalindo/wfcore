/*
 * wfajax ver 1.0.0
 */

function inet() {
    var xmlHttp = false;
    // try {
    //     xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    // } catch (e) {
    try {
        xmlHttp = new XMLHttpRequest();
    } catch (e) {
        // try {
        //     xmlHttp = new ActiveXObject("MSXML2.XMLHTTP");
        // } catch (e) {
        //     xmlHttp = false;
        // }
    }
    // }
    return xmlHttp;
}

//$D.getJSON = function (url) {
$D.doPagJson = function (url) {
    var net = new inet();
    net.open("GET", url, true); //false para que sea sincrono

    net.onreadystatechange = function () {
        if (net.readyState == 4 && net.status == 200) {
            // console.log('{NET(' + url + ')} ::::' + net.responseText)
            var json = JSON.parse(net.responseText);
            pagina_onload(json);
        }

    }
    net.send(null);
}

$D.doLogoutJson = function () {
    var net = new inet();
    net.open("POST", "/logout64", true); //false para que sea sincrono

    net.onreadystatechange = function () {
        if (net.readyState == 4 && net.status == 200) {
            // console.log('{NET(' + url + ')} ::::' + net.responseText)
            console.log('al cerrar:' + window.location.host);
            window.location.href = '//' + window.location.host + '/';
        }
    }

    net.send(null);
}

$D.getJSONE = function (url) {
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

$D.getHTML = function (url) {
    var net = new inet();
    net.open("POST", url, false); //false para que sea sincrono

    var html;
    net.open("POST", url, false); //gettime will be the servlet name
    net.onreadystatechange = function () {
        if (net.readyState == 4) {
            if (net.status == 200) {
                html = net.responseBody;
            }
        }
    };
    net.send(null);
    return html;
};

$D.getHTML2 = function (url) {
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

$D.getTEXT = function (url) {
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