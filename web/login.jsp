<%-- 
    Document   : index
    Created on : 3 abr. 2021, 20:16:51
    Author     : Moi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/parciales/head.jsp" %>
        <title>Inicio de sesión</title>
    </head>
    <body>
        <%@include file="/parciales/navbar.jsp" %>

        <div class="myForm">
            <h1 class="myTitle">Inicio de sesión</h1>
            <form>
                <div class="form-group">
                    <label form="id">Identificación</label>
                    <input type="number" class="form-control" id="id" placeholder="Identificación">
                </div>

                <div class="form-group">
                    <label form="password">Contraseña</label>
                    <input type="password" class="form-control" id="password" placeholder="Contraseña">
                </div>

                <button id="loginButton" type="submit" class="btn btn-primary" onclick="onLogin()">Ingresar</button>
                <button id="registerButton" type="submit" class="btn btn-success">Registrarse</button>
            </form>      
        </div>
        <%@include file="/parciales/footer.jsp" %>
    </body>
    <script>
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

        function onLogin() {
            var i = $("#id").val();
            var p = $("#password").val();
            var data = {id: i, clave: p};
            websocket.send(JSON.stringify(data));
        }

    </script>    
</html>
