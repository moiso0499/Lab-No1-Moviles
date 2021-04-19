<%-- 
    Document   : compras_usuario
    Created on : 18 abr. 2021, 21:38:21
    Author     : Moi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/parciales/head.jsp" %>
        <title>Ver compras</title>
    </head>
    <body>
        <%@include file="/parciales/navbar.jsp" %>
        <div class="container">
            <h1>Consultar Compras</h1>
            <div class="table-responsive">
                <table id="dtCompra" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
                    <thead>
                        <tr>
                            <th class="th-sm">Id

                            </th>
                            <th class="th-sm">Fecha

                            </th>
                            <th class="th-sm">Ruta

                            </th>
                            <th class="th-sm">Cantidad Asientos

                            </th>
                            <th class="th-sm">Total

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
                            <th>Ruta
                            </th>
                            <th>Cantidad Asientos
                            </th>
                            <th>Total
                            </th>
                        </tr>
                    </tfoot>
                </table>
            </div>
        </div>
        <%@include file="/parciales/footer.jsp" %>
    </body>
    <script>
        const websocket = new WebSocket('ws://localhost:8080/Aerolinea/registro_compra');

        websocket.addEventListener('open', function (event) {
            var usuario = JSON.parse(sessionStorage.getItem('logueado'));
            var mensaje = {action: "obtenerListaCompra", data: usuario.id};
            websocket.send(JSON.stringify(mensaje));
        });

        websocket.addEventListener('message', function (event) {
            var mensaje = JSON.parse(event.data);
            switch (mensaje.codigo) {
                case 1:
                {
                    renderListaCompra(mensaje.lista);
                    $('#dtCompra').DataTable();
                    $('.dataTables_length').addClass('bs-select');
                    break;
                }
            }
        });

        function renderListaCompra(lista) {
            $("#dtCompra > tbody").html("");
            lista.forEach((item) => {
                var tr = $("<tr />");
                var td_id = $("<td />");
                td_id.html(item.id);
                var td_fecha = $("<td />");
                td_fecha.html(item.vuelo_id.fecha.year + "/" + item.vuelo_id.fecha.month + "/" + item.vuelo_id.fecha.day);
                var td_ruta = $("<td />");
                td_ruta.html(item.vuelo_id.avion_id.ruta_id.origen.descripcion + "-->" + item.vuelo_id.avion_id.ruta_id.destino.descripcion);
                var td_cantidad_asientos = $("<td />");
                td_cantidad_asientos.html(item.tiquetes.length);
                var td_total = $("<td />");
                td_total.html(item.tiquetes.length * item.vuelo_id.precio);
                tr.append(td_id);
                tr.append(td_fecha);
                tr.append(td_ruta);
                tr.append(td_cantidad_asientos);
                tr.append(td_total);
                $("#dtCompra > tbody").append(tr);
            });
        }
    </script>
</html>
