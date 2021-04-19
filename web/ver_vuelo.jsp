<%-- 
    Document   : ver_vuelo
    Created on : 18 abr. 2021, 12:47:49
    Author     : Moi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/parciales/head.jsp" %>
        <title>Ver vuelos</title>
    </head>
    <body>
        <%@include file="/parciales/navbar.jsp" %>
        <div class="container">
            <h1>Revisar Vuelos</h1>
            <form class="form-inline">

                <!-- Form Name -->
                <h3>Filtrado</h3>

                <!-- Select Basic -->
                <div class="form-group" display = "inline-block">
                    <label class="col-md-4 control-label" for="select_origen">Origen</label>
                    <div class="col-md-4">
                        <select id="select_origen" name="select_origen" class="form-control">
                        </select>
                    </div>
                </div>

                <!-- Select Basic -->
                <div class="form-group" display = "inline-block">
                    <label class="col-md-4 control-label" for="select_destino">Destino</label>
                    <div class="col-md-4">
                        <select id="select_destino" name="select_destino" class="form-control">
                        </select>
                    </div>
                </div>

                <!-- Text input-->
                <div class="form-group" display = "inline-block">
                    <label class="col-md-4 control-label" for="input_fecha">Fecha</label>  
                    <div class="col-md-4">
                        <input id="input_fecha" name="input_fecha" type="date" placeholder="" class="form-control input-md" required="">

                    </div>
                </div>

                <!-- Button -->
                <div class="form-group" display = "inline-block">
                    <label class="col-md-4 control-label" for="button_buscar"></label>
                    <div class="col-md-4">
                        <button type="button" id="button_buscar" name="button_buscar" class="btn btn-info" onclick="filtrarVuelo()">Filtrar</button>
                    </div>
                </div>
            </form>
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
                            <th class="th-sm">...

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
                            <th>
                            </th>
                        </tr>
                    </tfoot>
                </table>
            </div>
        </div>

        <!-- Modal -->
        <div id="modal_booking" class="modal fade" role="dialog">
            <div class="modal-dialog  modal-xl">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title" id="modal_booking_title">Modal Header</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>   
                    </div>
                    <div class="modal-body">
                        <div class="table-responsive">
                            <table id="dt_asientos" class="table-borderless" cellspacing="0" width="100%">
                                <tbody>

                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-info" data-dismiss="modal" onclick="agregarCompra()">Confirmar Compra</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>

            </div>
        </div>
        <%@include file="/parciales/footer.jsp" %>
    </body>
    <script>
        const websocket = new WebSocket('ws://localhost:8080/Aerolinea/compra');

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
                    alert(mensaje.lista.length + " vuelo(s) encontrado(s)");
                    $("#dtVuelo > tbody").html("");
                    renderListaVuelo(mensaje.lista);
                    $('#dtVuelo').DataTable();
                    $('.dataTables_length').addClass('bs-select');
                    break;
                }
                case 3:
                {
                    sessionStorage.setItem('listaTiquetes', JSON.stringify(mensaje.lista));
                }
            }
        });
        
        function agregarCompra(){
            alert("Aquí va la compra");
        }

        function filtrarVuelo() {
            var select_origen = $("#select_origen option:selected").val();
            var select_destino = $("#select_destino option:selected").val();
            var input_fecha = $("#input_fecha").val();
            if (select_origen == select_destino) {
                alert("No es posible filtrar vuelos con la ruta señalada");
                return;
            }
            var ruta = {origen: {codigo: select_origen}, destino: {codigo: select_destino}};
            var mensaje = {action: "filtrarVuelo", data: ruta, date: input_fecha};
            websocket.send(JSON.stringify(mensaje));
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
                // ----Botón comprar ----------
                var td_comprar = $("<td />");
                $(td_comprar).addClass("tdComprar");
                td_comprar.html('<i class="fa fa-shopping-cart"></i>');
                $(td_comprar).attr("data-toggle", "modal");
                $(td_comprar).attr("data-target", "#modal_booking");
                td_comprar.on("click", () => {
                    var mensaje = {action: "obtenerTiquetesVuelo", data: item.id};
                    websocket.send(JSON.stringify(mensaje));
                    var listaTiquetes = JSON.parse(sessionStorage.getItem('listaTiquetes'));
                    renderAsientosAvion(listaTiquetes, item);
                });
                // ---------------------------

                tr.append(td_id);
                tr.append(td_fecha);
                tr.append(td_horaSalida);
                tr.append(td_horaLlegada);
                tr.append(td_precio);
                tr.append(td_ruta);
                tr.append(td_comprar);
                // ----
                $("#dtVuelo > tbody").append(tr);
            });
        }

        function renderAsientosAvion(tiquetes, vuelo) {
            $("#modal_booking_title").html("Asientos -- " + vuelo.fecha.year + "/" + vuelo.fecha.month + "/" + vuelo.fecha.day + " -- " + vuelo.avion_id.ruta_id.origen.codigo + "-->" + vuelo.avion_id.ruta_id.destino.codigo);
            var asientos_ocupados = [];
            tiquetes.forEach((item) => {
                asientos_ocupados.push(item.asiento);
            });
            var filas = vuelo.avion_id.tipoAvion_id.filas;
            var asientos_fila = vuelo.avion_id.tipoAvion_id.asientos_fila;
            $("#dt_asientos > tbody").html("");
            var numero_asiento = 0;
            for (i = 1; i <= filas; i++) {
                var tr_fila = $("<tr />");
                for (j = 1; j <= asientos_fila; j++) {
                    numero_asiento = numero_asiento + 1;
                    var td_asiento = $("<td />");
                    var label = $("<label />");
                    $(label).addClass("btn btn-success");
                    var input = $("<input />");
                    $(input).attr("type", "checkbox");
                    label.append(input);
                    label.append(i + "-" + j);
                    if (asientos_ocupados.includes(numero_asiento)) {
                        //input.disabled = true;
                        $(label).addClass("btn-danger");
                        $(input).attr('disabled', 'disabled');
                    }
                    else{
                        $(label).addClass("btn-success");
                    }
                    /*<label class="btn btn-secondary active">
                     <input type="checkbox" checked autocomplete="off"> Active
                     </label>*/
                    td_asiento.html(label);
                    tr_fila.append(td_asiento);
                }
                $("#dt_asientos > tbody").append(tr_fila);
            }
        }
        function renderSelectUbicacion(select, lista) {
            select.html("");
            lista.forEach((item) => {
                select.append("<option value=\"" + item.codigo + "\">" + item.descripcion + "</option>");
            });
        }

        function checkTime(i) {
            return (i < 10) ? "0" + i : i;
        }

        $("document").ready(() => {
            var today = new Date().toISOString().split('T')[0];
            $("#input_fecha").val(today);
            $("#input_fecha").attr("min", today);
        });
    </script>
</html>
