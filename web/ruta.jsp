<%-- 
    Document   : ruta
    Created on : 15 abr. 2021, 16:12:38
    Author     : Moi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/parciales/head.jsp" %>
        <title>Rutas</title>
        <script>
            const websocket = new WebSocket('ws://localhost:8080/Aerolinea/ruta');

            websocket.addEventListener('message', function (event) {
                var mensaje = JSON.parse(event.data);
                switch (mensaje.codigo) {
                    case 1:
                    {
                        var select_origen = $("#select_origen");
                        var select_destino = $("#select_destino");
                        renderSelectUbicacion(select_origen, mensaje.lista);
                        renderSelectUbicacion(select_destino, mensaje.lista);
                        break;
                    }
                    case 2:
                    {
                        $("dtRuta > tbody").html("");
                        renderListaRuta(mensaje.lista);
                        $('#dtRuta').DataTable();
                        $('.dataTables_length').addClass('bs-select');
                        break;
                    }
                }
            });
            function renderListaRuta(listado) {
                listado.forEach((item) => {
                    var tr = $("<tr />");
                    //----Campos de tr----
                    var td_id = $("<td />");
                    td_id.html(item.id);
                    var td_duracion = $("<td />");
                    td_duracion.html(item.duracion.hour + ":" + checkTime(item.duracion.minute));
                    var td_origen = $("<td />");
                    td_origen.html(item.origen.codigo + "--" + item.origen.descripcion);
                    var td_destino = $("<td />");
                    td_destino.html(item.destino.codigo + "--" + item.destino.descripcion);
                    // ----Agregar campos a tr----
                    tr.append(td_id);
                    tr.append(td_duracion);
                    tr.append(td_origen);
                    tr.append(td_destino);
                    $("#dtRuta > tbody").append(tr);
                });
            }

            function checkTime(i) {
                return (i < 10) ? "0" + i : i;
            }

            function renderSelectUbicacion(select, lista) {
                select.html("");
                lista.forEach((item) => {
                    select.append("<option value=\"" + item.codigo + "\">" + item.descripcion + "</option>");
                });
            }

            function agregarUbicacion() {
                var input_codigo = $("#input_codigo").val();
                var input_descripcion = $("#input_descripcion").val();
                if (input_codigo.length > 0 && input_descripcion.length > 0) {
                    var ubicacion = {codigo: input_codigo.replace(/\s+/g, "_"), descripcion: input_descripcion.replace(/\s+/g, "_")};
                    var message = {action: "agregarUbicacion", data: ubicacion};
                    websocket.send(JSON.stringify(message));
                } else {
                    alert("Debe llenar los campos");
                }
            }

            function agregarRuta() {
                var input_duracionHora = $("#input_duracionHora").val();
                var input_duracionMinuto = $("#input_duracionMinuto").val();
                if (input_duracionHora.length == 0 || input_duracionMinuto.length == 0 || (input_duracionHora == 0 && input_duracionMinuto == 0)) {
                    alert("Ingrese una duración válida");
                    return;
                }
                var select_origen = $("#select_origen option:selected").val();
                var select_destino = $("#select_destino option:selected").val();
                if (select_origen == select_destino) {
                    alert("Origen y destino deben ser distintos");
                    return;
                }
                var input_Duracion = (input_duracionHora + ":" + input_duracionMinuto + ":00").toString();
                var laRuta = {origen: {codigo: select_origen}, destino: {codigo: select_destino}};
                var message = {action: "agregarRuta", data: laRuta, duracion: input_Duracion};
                websocket.send(JSON.stringify(message));
            }
            //CODIGOS RECEPCIÓN MENSAJE:
            //  *1: enviarListaUbicacion
            //  *2: enviarListaRuta
        </script>
    </head>
    <body>
        <%@include file="/parciales/navbar.jsp" %>
        <div class="container">
            <h2>Gestión de Rutas</h2>
            <br>
            <!-- Nav tabs -->
            <ul class="nav nav-tabs" role="tablist">
                <li class="nav-item">
                    <a class="nav-link active" data-toggle="tab" href="#tab_newLocation">Agregar Ubicación</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" data-toggle="tab" href="#tab_newRoute">Agregar Ruta</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" data-toggle="tab" href="#tab_routeList">Ver Ruta</a>
                </li>
            </ul>

            <!-- Tab panes -->
            <div class="tab-content">
                <div id="tab_newLocation" class="container tab-pane active"><br>
                    <form class="form-horizontal">
                        <fieldset>

                            <!-- Form Name -->
                            <h3>Agregar Nueva Ubicación</h3>

                            <!-- Text input-->
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="input_codigo">Código</label>  
                                <div class="col-md-4">
                                    <input id="input_codigo" name="input_codigo" type="text" placeholder="Código" class="form-control input-md" required="">

                                </div>
                            </div>

                            <!-- Text input-->
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="input_descripcion">Descripción</label>  
                                <div class="col-md-8">
                                    <input id="input_descripcion" name="input_descripcion" type="text" placeholder="Descripción" class="form-control input-md" required="">

                                </div>
                            </div>

                            <!-- Button -->
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="button_agregarUbicacion"></label>
                                <div class="col-md-4">
                                    <button id="button_agregarUbicacion" name="button_agregarUbicacion" class="btn btn-success" onclick="agregarUbicacion()">Agregar</button>
                                </div>
                            </div>

                        </fieldset>
                    </form>

                </div>
                <!-- Tab Nueva Ruta-->
                <div id="tab_newRoute" class="container tab-pane fade"><br>
                    <form class="form-horizontal">
                        <fieldset>

                            <!-- Form Name -->
                            <h3>Agregar Ruta</h3>

                            <!-- Text input-->
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="input_duracionHora">Horas</label>  
                                <div class="col-md-2">
                                    <input id="input_duracionHora" name="input_duracionHora" type="number" min="0" max="23" step="1" placeholder="Horas" class="form-control input-md" required="">    
                                </div>
                                <label class="col-md-4 control-label" for="input_duracionMinuto">Minutos</label>  
                                <div class="col-md-2">
                                    <input id="input_duracionMinuto" name="input_duracionMinuto" type="number" min="0" max="59" step="1"placeholder="Minutos" class="form-control input-md" required="">    
                                </div>
                            </div>

                            <!-- Select Basic -->
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="select_origen">Origen</label>
                                <div class="col-md-4">
                                    <select id="select_origen" name="select_origen" class="form-control">
                                    </select>
                                </div>
                            </div>

                            <!-- Select Basic -->
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="select_destino">Destino</label>
                                <div class="col-md-4">
                                    <select id="select_destino" name="select_destino" class="form-control">
                                    </select>
                                </div>
                            </div>

                            <!-- Button -->
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="button_nuevaRuta"></label>
                                <div class="col-md-4">
                                    <button id="button_nuevaRuta" name="button_nuevaRuta" class="btn btn-success" onclick="agregarRuta()">Agregar</button>
                                </div>
                            </div>

                        </fieldset>
                    </form>
                </div>
                <div id="tab_routeList" class="container tab-pane fade"><br>
                    <h3>Ver Rutas</h3>
                    <div class="table-responsive">
                        <table id="dtRuta" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    <th class="th-sm">Id

                                    </th>
                                    <th class="th-sm">Duración

                                    </th>
                                    <th class="th-sm">Origen

                                    </th>
                                    <th class="th-sm">Destino

                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <th>Id
                                    </th>
                                    <th>Duración
                                    </th>
                                    <th>Origen
                                    </th>
                                    <th>Destino
                                    </th>
                                </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="/parciales/footer.jsp" %>
    </body>
</html>
