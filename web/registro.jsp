<%-- 
    Document   : registro
    Created on : 18 abr. 2021, 01:15:02
    Author     : Moi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/parciales/head.jsp" %>
        <title>Registro de Usuario</title>
    </head>
    <body>
        <%@include file="/parciales/navbar.jsp" %>
        <div class="container">
            <form class="form-horizontal">
                <fieldset>

                    <!-- Form Name -->
                    <h3>Registro de Usuario</h3>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="input_id">ID</label>  
                        <div class="col-md-4">
                            <input id="input_id" name="input_id" type="text" placeholder="Id" class="form-control input-md" required="">

                        </div>
                    </div>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="input_nombre">Nombre</label>  
                        <div class="col-md-4">
                            <input id="input_nombre" name="input_nombre" type="text" placeholder="Nombre" class="form-control input-md" required="">

                        </div>
                    </div>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="input_apellido">Apellido</label>  
                        <div class="col-md-4">
                            <input id="input_apellido" name="input_apellido" type="text" placeholder="Apellido" class="form-control input-md" required="">

                        </div>
                    </div>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="input_correo">Correo Electrónico</label>  
                        <div class="col-md-4">
                            <input id="input_correo" name="input_correo" type="email" placeholder="Correo" class="form-control input-md">

                        </div>
                    </div>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="input_direccion">Dirección</label>  
                        <div class="col-md-4">
                            <input id="input_direccion" name="input_direccion" type="text" placeholder="Dirección" class="form-control input-md">

                        </div>
                    </div>

                    <!-- Password input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="input_clave">Clave</label>
                        <div class="col-md-4">
                            <input id="input_clave" name="input_clave" type="password" placeholder="" class="form-control input-md" required="">

                        </div>
                    </div>

                    <!-- Password input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="input_confirmarClave">Confirmar Clave</label>
                        <div class="col-md-4">
                            <input id="input_confirmarClave" name="input_confirmarClave" type="password" placeholder="" class="form-control input-md" required="">

                        </div>
                    </div>

                    <!-- Button -->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="button_registro"></label>
                        <div class="col-md-4">
                            <button type="button" id="button_registro" name="button_registro" class="btn btn-success" onclick="registrarUsuario()">Registro</button>
                        </div>
                    </div>

                </fieldset>
            </form>
        </div>
        <%@include file="/parciales/footer.jsp" %>
    </body>
    <script>
        //Websocket usuario
        const websocket = new WebSocket('ws://localhost:8080/Aerolinea/usuario');

        websocket.addEventListener('message', function (event) {
            var mensaje = JSON.parse(event.data);
            switch (mensaje.codigo) {
                case 1:
                {
                    alert("Usuario registrado correctamente");
                    sessionStorage.setItem('logueado', JSON.stringify(mensaje.usuario));
                    location.replace("index.jsp");
                    break;
                }
                case 2:
                {
                    alert("No se ha podido registrar al usuario");
                    break;
                }
            }
        });

        function registrarUsuario() {
            var input_id = $("#input_id").val();
            var input_nombre = $("#input_nombre").val();
            var input_apellido = $("#input_apellido").val();
            if (input_id.length == 0 || input_nombre.length == 0 || input_apellido.length == 0) {
                alert("Debe llenar los campos que corresponden");
                return;
            }
            var input_correo = $("#input_correo").val();
            var input_direccion = $("#input_direccion").val();
            var input_clave = $("#input_clave").val();
            var input_confirmarClave = $("#input_confirmarClave").val();
            if (input_clave.length == 0) {
                alert("Debe ingresar una clave");
                return;
            }
            if (input_confirmarClave.length == 0) {
                alert("Confirme la clave");
                return;
            }
            if (input_clave != input_confirmarClave) {
                alert("Confirme nuevamente la clave");
                return;
            }
            if(input_correo.length == 0){
                input_correo = "--";
            }
            if(input_direccion.length == 0){
                input_direccion = "--";
            }
            var usuario = {id: input_id.replace(/\s+/g, "_"), nombre: input_nombre.replace(/\s+/g, "_"), apellido: input_apellido.replace(/\s+/g, "_"), clave: input_clave.replace(/\s+/g, "_"), correo: input_correo.replace(/\s+/g, "_"), direccion: input_direccion.replace(/\s+/g, "_"), admin: false};
            var message = {action: "ingresarUsuario", data: usuario};
            websocket.send(JSON.stringify(message));
        }

    </script>
</html>
