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
                        '\t"user": "' + co_usuari() + '",\n' +
                        '\t"xact": "",\n' +
                        '\t"mact": ""\n' +
                        '}';
                MSG_toWS(loginjson);
            } else {
                // console.log("WebSocket MESSAGE: " + wsMsg);

                console.log('retorno:' + wsMsg);
                var xmsg = JSON.parse(wsMsg);
                toascfg(xmsg.position);
//                pushMessage('info', 'AIO2', wsMsg)
                pushMessage(xmsg.type, xmsg.title, xmsg.body);
                // alert('MSG:' + wsMsg);
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
        "positionClass": "md-" + position,
        "preventDuplicates": false,
        "showDuration": -1,
        "hideDuration": 1000,
        "timeOut": -1,
        "extendedTimeOut": 1000,
        "showEasing": "swing",
        "hideEasing": "linear",
        "showMethod": "fadeIn",
        "hideMethod": "fadeOut"
    };
}

function pushMessage(type, title, message) {
    toastr[type](message, title);
}