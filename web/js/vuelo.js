/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
        // ----Botón eliminar ----------
        var td_eliminar = $("<td />");
        $(td_eliminar).addClass("tdEliminar");
        td_eliminar.html('<i class="fa fa-trash"></i>');
        td_eliminar.on("click", () => {
            eliminarVuelo(item);
        });
        // ---------------------------
        
        tr.append(td_id);
        tr.append(td_fecha);
        tr.append(td_horaSalida);
        tr.append(td_horaLlegada);
        tr.append(td_precio);
        tr.append(td_ruta);
        tr.append(td_asientos);
        tr.append(td_eliminar);
        // ----
        $("#dtVuelo > tbody").append(tr);
    });
}

function eliminarVuelo(vuelo){
    if (confirm("¿Está seguro de eliminar a " + vuelo.id + "-" + vuelo.fecha.year + "/" + vuelo.fecha.month + "/" + vuelo.fecha.day + "?")) {
        var eliminar = {id: vuelo.id};
        var mensaje = {action: "eliminarVuelo", data: eliminar};
        websocket.send(JSON.stringify(mensaje));
        alert("Eliminado con éxito");
    } else {
        return;
    }
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

