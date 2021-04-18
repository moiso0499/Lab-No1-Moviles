/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

const websocket = new WebSocket('ws://localhost:8080/Aerolinea/login');

websocket.addEventListener('open', function (event) {
    //websocket.send('Servidor abierto'); 
});

websocket.addEventListener('message', function (event) {
    if (event.data !== null) {
        sessionStorage.setItem('logueado', event.data);
        location.replace('index.jsp');
    }
});

function registro(){
    location.replace('registro.jsp');
}

function onLogin() {
    var i = $("#id").val();
    var p = $("#password").val();
    var data = {id: i, clave: p};
    websocket.send(JSON.stringify(data));
}


