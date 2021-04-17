<%-- 
    Document   : vuelo
    Created on : 17 abr. 2021, 00:34:04
    Author     : Moi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/parciales/head.jsp" %>
        <title>Vuelos</title>
    </head>
    <body>
        <%@include file="/parciales/navbar.jsp" %>
        <div class="container">
            <h2>Gestión de Vuelos</h2>
            <br>
            <!-- Nav tabs -->
            <ul class="nav nav-tabs" role="tablist">
                <li class="nav-item">
                    <a class="nav-link active" data-toggle="tab" href="#tab_newFlight">Agregar Vuelo</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" data-toggle="tab" href="#tab_Flight">Ver Vuelo</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" data-toggle="tab" href="#menu2">---</a>
                </li>
            </ul>

            <!-- Tab panes -->
            <div class="tab-content">
                <div id="tab_newFlight" class="container tab-pane active"><br>
                    <form class="form-horizontal">
                        <fieldset>

                            <!-- Form Name -->
                            <legend>Agregar Vuelo</legend>

                            <!-- Text input-->
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="input_fecha">Fecha</label>  
                                <div class="col-md-4">
                                    <input id="input_fecha" name="input_fecha" type="date" placeholder="Fecha" class="form-control input-md" required="">

                                </div>
                            </div>

                            <!-- Text input-->
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="input_hora">Hora</label>  
                                <div class="col-md-4">
                                    <input id="input_hora" name="input_hora" type="time" value="now" placeholder="Hora de salida" class="form-control input-md" required="">

                                </div>
                            </div>

                            <!-- Text input-->
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="input_precio">Precio por Tiquete</label>  
                                <div class="col-md-4">
                                    <input id="input_precio" name="input_precio" type="number" value="0.00" min="0" step="0.1" placeholder="" class="form-control input-md" required="">

                                </div>
                            </div>

                            <!-- Select Basic -->
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="select_avion">Avión Designado</label>
                                <div class="col-md-4">
                                    <select id="select_avion" name="select_avion" class="form-control">
                                    </select>
                                </div>
                            </div>

                            <!-- Button -->
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="button_agregarVuelo"></label>
                                <div class="col-md-4">
                                    <button id="button_agregarVuelo" name="button_agregarVuelo" class="btn btn-success" onclick="agregarVuelo()">Agregar</button>
                                </div>
                            </div>

                        </fieldset>
                    </form>
                </div>
                <div id="tab_Flight" class="container tab-pane fade"><br>
                    <h3>Ver Vuelos</h3>
                    <div class="table-responsive">
                        <table id="dtVuelo" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    <th class="th-sm">Id

                                    </th>
                                    <th class="th-sm">Fecha

                                    </th>
                                    <th class="th-sm">Hora Salida

                                    </th>
                                    <th class="th-sm">Hora Llegada

                                    </th>
                                    <th class="th-sm">Precio

                                    </th>
                                    <th class="th-sm">Ruta

                                    </th>
                                    <th class="th-sm">Asientos

                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <th>Id
                                    </th>
                                    <th>Fecha
                                    </th>
                                    <th>Hora Salida
                                    </th>
                                    <th>Hora Llegada
                                    </th>
                                    <th>Precio
                                    </th>
                                    <th>Ruta
                                    </th>
                                    <th>Asientos
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
    <script>
        const websocket = new WebSocket('ws://localhost:8080/Aerolinea/vuelo');

        websocket.addEventListener('message', function (event) {
            var mensaje = JSON.parse(event.data);
            switch (mensaje.codigo) {
                case 1:
                {
                    var select_avion = $("#select_avion");
                    renderSelectAvion(select_avion, mensaje.lista);
                }
                case 2:
                {
                    $("#dtVuelo > tbody").html("");
                    renderListaVuelo(mensaje.lista);
                    $('#dtVuelo').DataTable();
                    $('.dataTables_length').addClass('bs-select');
                }
            }
        });

        function agregarVuelo() {
            var input_fecha = $("#input_fecha").val();
            var input_hora = $("#input_hora").val();
            var input_precio = $("#input_precio").val();
            var select_avion = $("#select_avion option:selected").val();
            if (!(input_precio > 0)) {
                alert("Ingrese un precio válido");
                return;
            }
            if (input_fecha < Date.now()) {
                alert("Imposible agregar con la fecha designada");
                return;
            }
            var vuelo = {precio: input_precio, avion_id: {id: select_avion}};
            var message = {action: "agregarVuelo", data: vuelo, date: input_fecha, time: input_hora};
            websocket.send(JSON.stringify(message));
        }

        function renderListaVuelo(lista) {
            lista.forEach((item) => {
                var tr = $("<tr />");
                //----Campos de tr----
                var td_id = $("<td />");
                td_id.html(item.id);
                var td_fecha = $("<td />");
                td_fecha.html(item.fecha.year + "/" + item.fecha.month + "/" + item.fecha.day);
                var td_horaSalida = $("<td />");
                td_horaSalida.html(item.hora_salida.hour + ":" + checkTime(item.hora_salida.minute));
                var td_horaLlegada = $("<td />");
                var llegada = new Date(item.fecha.year, item.fecha.month, item.fecha.day, item.hora_salida.hour, item.hora_salida.minute);
                var hora_llegada = item.avion_id.ruta_id.duracion.hour;
                var minuto_llegada = item.avion_id.ruta_id.duracion.minute;
                
                llegada.setMinutes(llegada.getMinutes() + minuto_llegada);
                llegada.setHours(llegada.getHours() + hora_llegada);
                
                td_horaLlegada.html(llegada.toString());
                var td_precio = $("<td />");
                td_precio.html("$" + item.precio);
                var td_ruta = $("<td />");
                td_ruta.html(item.avion_id.ruta_id.origen.codigo + "-->" + item.avion_id.ruta_id.destino.codigo);
                var td_asientos = $("<td />");
                td_asientos.html(item.avion_id.tipoAvion_id.filas * item.avion_id.tipoAvion_id.asientos_fila);
                // ---------------------------
                tr.append(td_id);
                tr.append(td_fecha);
                tr.append(td_horaSalida);
                tr.append(td_horaLlegada);
                tr.append(td_precio);
                tr.append(td_ruta);
                tr.append(td_asientos);
                // ----
                $("#dtVuelo > tbody").append(tr);
            });
        }

        function renderSelectAvion(select, lista) {
            select.html("");
            lista.forEach((item) => {
                select.append("<option value=\"" + item.id + "\">" + item.tipoAvion_id.modelo + " | " + item.ruta_id.origen.codigo + "->" + item.ruta_id.destino.codigo + "</option>");
            });
        }
        $("document").ready(() => {
            var today = new Date().toISOString().split('T')[0];
            $("#input_fecha").val(today);

            $(function () {
                var d = new Date(),
                        h = d.getHours(),
                        m = d.getMinutes();
                if (h < 10)
                    h = '0' + h;
                if (m < 10)
                    m = '0' + m;
                $('input[type="time"][value="now"]').each(function () {
                    $(this).attr({'value': h + ':' + m});
                });
            });
        });

        function checkTime(i) {
            return (i < 10) ? "0" + i : i;
        }
    </script>
</html>
