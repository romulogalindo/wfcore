var webSocket = null;
var ws_protocol = null;
var ws_hostname = null;
var ws_port = null;
var ws_endpoint = null;

/**
 * Open a new WebSocket connection using the given parameters
 */
function openWS() {
    var wsurl = (location.protocol == 'http:' ? 'ws://' : 'wss://') + location.hostname + (location.port ? ':' + location.port : '') + '/ws/main';

    // console.log("openWSConnection::Connecting to: " + wsurl);
    try {
        webSocket = new WebSocket(wsurl);

        webSocket.onopen = function (openEvent) {
            // console.log("WebSocket OPEN: " + JSON.stringify(openEvent, null, 4));
        };

        webSocket.onclose = function (closeEvent) {
            // console.log("WebSocket CLOSE: " + JSON.stringify(closeEvent, null, 4));
        };

        webSocket.onerror = function (errorEvent) {
            console.log("WebSocket ERROR: " + JSON.stringify(errorEvent, null, 4));
        };

        webSocket.onmessage = function (messageEvent) {
            var wsMsg = messageEvent.data;
            if (wsMsg == 'AIO_WS_READY') {
                var loginjson = '{\n' +
                    '\t"status": "OK",\n' +
                    '\t"type": "login",\n' +
                    '\t"user": "' + CO_USUARI + '",\n' +
                    '\t"xact": "",\n' +
                    '\t"mact": ""\n' +
                    '}';
                MSG_toWS(loginjson);
            } else {
                // console.log("WebSocket MESSAGE: " + wsMsg);

                console.log('retorno:' + wsMsg);
                var xmsg = JSON.parse(wsMsg);
                toascfg(xmsg.position);
                pushMessage(xmsg.type, xmsg.title, xmsg.body, xmsg.conten, xmsg.clear, xmsg.timeout);
            }

        };
    } catch (exception) {
        console.error('<Tenemos el erro>>' + exception);
    }
}

/**
 * Event handler for clicking on button "Connect"
 */
function closeWS() {
    webSocket.close();
}

/**
 * Send a message to the WebSocket server
 */
function MSG_toWS(msg) {
    // console.log('webSocket:' + webSocket);
    // console.log('webSocket.readyState:' + webSocket.readyState);
    // console.log('WebSocket.OPEN:' + WebSocket.OPEN);
    if (webSocket.readyState != WebSocket.OPEN) {
        console.error("webSocket is not open: " + webSocket.readyState);
        return;
    }

    webSocket.send(msg);
}

function toascfg(position) {
    toastr.options = {
        "closeButton": true,
        "debug": false,
        "newestOnTop": false,
        "progressBar": true,
        "positionClass": position,
        "preventDuplicates": false,
        "showDuration": -1,
        "hideDuration": 1000,
        "timeOut": 3000,
        "extendedTimeOut": 1000,
        "showEasing": "swing",
        "hideEasing": "linear",
        "showMethod": "fadeIn",
        "hideMethod": "fadeOut"
    };
}

//"timeOut": -1,

function pushMessage(type, title, message, conten, clear, timeout) {
    console.log("EXE PUSH!" + timeout);
    if (timeout == -1) {
        toastr.options.timeOut = -1;
    } else {
        toastr.options.timeOut = (timeout * 1000);
    }

    if (clear != undefined) {
        if (clear == 'true')
            toastr.remove()
    }

    if (conten != undefined) {
        if (conten == CO_CONTEN) {
            toastr[type](message, title);
        }
    } else {
        toastr[type](message, title);
    }

}