var url = window.location.host;
console.log('连接端口' + url);
if ('WebSocket' in window) {
    websocket = new WebSocket("ws://" + url + "/websocket/socketServer.do");
}
websocket.onopen = onOpen;
websocket.onmessage = onMessage;
websocket.onerror = onError;
websocket.onclose = onClose;
function onOpen(openEvt) {
    console.log('websocket已连接')
}
function onMessage(evt) {
    console.log('websocket接收' + evt.data)
    $('#online').text(evt.data);
}
function onError() {
    // alert("服务器错误")
    console.log('websocket服务器错误')
}
function onClose() {
    //   websocket = new WebSocket("ws://" + url + "/websocket/socketServer.do");
    //  // alert("服务器已断开连接")
    console.log('websocket服务器已断开连接');
}
function doSend() {
    if (websocket.readyState == websocket.OPEN) {
        var msg = document.getElementById("inputMsg").value;
        websocket.send(msg);//调用后台handleTextMessage方法
        alert("发送成功!");
    } else {
        alert("连接失败!");
    }
}

