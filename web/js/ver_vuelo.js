/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
            var vueloEscogido = JSON.parse(sessionStorage.getItem('vueloEscogido'));
            renderAsientosAvion(mensaje.lista, vueloEscogido);
            break;
        }
        case 4:
        {
            alert("Compra registrada correctamente");
            break;
        }
        case 5:
        {
            alert("Hubo un error al registrar la compra");
            break;
        }
    }
});


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
            sessionStorage.setItem('vueloEscogido', JSON.stringify(item));
            var mensaje = {action: "obtenerTiquetesVuelo", data: item.id};
            websocket.send(JSON.stringify(mensaje));
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
            $(input).attr('name', 'check_asiento');
            $(input).val(numero_asiento);
            label.append(input);
            label.append(i + "-" + j);
            if (asientos_ocupados.includes(numero_asiento)) {
                $(label).addClass("btn-danger");
                $(input).attr('disabled', 'disabled');
            } else {
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

function agregarCompra() {
    var vueloEscogido = JSON.parse(sessionStorage.getItem('vueloEscogido'));
    var asientos_seleccionados = [];
    $.each($("input[name='check_asiento']:checked"), function () {
        asientos_seleccionados.push($(this).val());
    });
    var modal_body_confirm_book = $("#modal-body-confirm-book");
    modal_body_confirm_book.html("");
    if (asientos_seleccionados.length == 0) {
        return;
    }
    sessionStorage.setItem('lista_asientos_compra_actual', JSON.stringify(asientos_seleccionados));
    var ul_datos = $("<ul />");
    ul_datos.addClass("list-group");
    ul_datos.append("<li class='list-group-item list-group-item-info'>Vuelo: " + vueloEscogido.fecha.year + "/" + vueloEscogido.fecha.month + "/" + vueloEscogido.fecha.day + " -- " + vueloEscogido.avion_id.ruta_id.origen.codigo + "-->" + vueloEscogido.avion_id.ruta_id.destino.codigo + "</li>");
    ul_datos.append("<li class='list-group-item list-group-item-info'>Asientos reservados: " + asientos_seleccionados.length + "</li>");
    asientos_seleccionados.forEach((item) => {
        ul_datos.append("<li class='list-group-item list-group-item-warning'>" + item + "</li>");
    });
    ul_datos.append("<li class='list-group-item list-group-item-success'>Total: $" + asientos_seleccionados.length * vueloEscogido.precio + "</li>");
    modal_body_confirm_book.html(ul_datos);
    $('#modal_confirm_book').modal('toggle');
}

function guardarCompra() {
    var vueloEscogido = JSON.parse(sessionStorage.getItem('vueloEscogido'));
    var asientosEscogidos = JSON.parse(sessionStorage.getItem('lista_asientos_compra_actual'));
    var usuario = JSON.parse(sessionStorage.getItem('logueado'));
    var compra = {usuario_id: {id: usuario.id}, vuelo_id: {id: vueloEscogido.id}};
    var tiquetes = [];
    asientosEscogidos.forEach((item) => {
        var tiquete = {asiento: item, vuelo_id: {id: vueloEscogido.id}};
        tiquetes.push(tiquete);
    });
    var mensaje = {action: "agregarCompra", data: compra, lista_tiquete: tiquetes};
    websocket.send(JSON.stringify(mensaje));
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
