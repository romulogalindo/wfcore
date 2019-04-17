var webSocket = null;
var ws_protocol = null;
var ws_hostname = null;
var ws_port = null;
var ws_endpoint = null;
/**
 * Event handler for clicking on button "Connect"
 */
//function onConnectClick() {
//    var ws_protocol = document.getElementById("protocol").value;
//    var ws_hostname = document.getElementById("hostname").value;
//    var ws_port = document.getElementById("port").value;
//    var ws_endpoint = document.getElementById("endpoint").value;
//    openWSConnection(ws_protocol, ws_hostname, ws_port, ws_endpoint);
//}
/**
 * Event handler for clicking on button "Disconnect"
 */
function closeWS() {
    webSocket.close();
}

/**
 * Open a new WebSocket connection using the given parameters
 */
function openWS(websocketurl) {
//    var webSocxketURL = null;
//    webSockxetURL = protocol + "://" + hostname + ":" + port + endpoint;
    console.log("openWSConnection::Connecting to: " + websocketurl);
    try {
        webSocket = new WebSocket(websocketurl);

        webSocket.onopen = function (openEvent) {
            console.log("WebSocket OPEN: " + JSON.stringify(openEvent, null, 4));
//            document.getElementById("btnSend").disabled = false;
//            document.getElementById("btnConnect").disabled = true;
//            document.getElementById("btnDisconnect").disabled = false;
        };

        webSocket.onclose = function (closeEvent) {
            console.log("WebSocket CLOSE: " + JSON.stringify(closeEvent, null, 4));
//            document.getElementById("btnSend").disabled = true;
//            document.getElementById("btnConnect").disabled = false;
//            document.getElementById("btnDisconnect").disabled = true;
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
                console.log("WebSocket MESSAGE: " + wsMsg);
                alert('MSG:' + wsMsg);
            }

            // if (wsMsg.indexOf("error") > 0) {
            //     document.getElementById("incomingMsgOutput").value += "error: " + wsMsg.error + "\r\n";
            // } else {
            //     document.getElementById("incomingMsgOutput").value += "message: " + wsMsg + "\r\n";
            // }
        };
    } catch (exception) {
        console.error('<Tenemos el erro>>' + exception);
    }
}

/**
 * Send a message to the WebSocket server
 */
function MSG_toWS(msg) {
    console.log('webSocket:' + webSocket);
    console.log('webSocket.readyState:' + webSocket.readyState);
    console.log('WebSocket.OPEN:' + WebSocket.OPEN);
    if (webSocket.readyState != WebSocket.OPEN) {
        console.error("webSocket is not open: " + webSocket.readyState);
        return;
    }
//    var msg = document.getElementById("message").value;
    webSocket.send(msg);
}