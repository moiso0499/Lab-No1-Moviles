/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//Websocket tipoAvion
const websocket = new WebSocket('ws://localhost:8080/Aerolinea/tipoavion');

websocket.addEventListener('message', function (event) {
    var mensaje = JSON.parse(event.data);
    switch (mensaje.codigo) {
        case 1:
        {
            $("#dtTipoAvion > tbody").html("");
            renderListaTipoAvion(mensaje.lista);
            //----
            var select_tipoavion = $("#select_tipoavion");
            renderSelectTipoAvion(select_tipoavion, mensaje.lista);
            var select_tipoavion_edit = $("#select_tipoavion_edit");
            renderSelectTipoAvion(select_tipoavion_edit, mensaje.lista);
            $('#dtTipoAvion').DataTable();
            $('.dataTables_length').addClass('bs-select');
            break;
        }
        case 2:
        {
            var select_ruta = $("#select_ruta");
            renderSelectRuta(select_ruta, mensaje.lista);
            var select_ruta_edit = $("#select_ruta_edit");
            renderSelectRuta(select_ruta_edit, mensaje.lista);
            break;
        }
        case 3:
        {
            $("#dtAvion > tbody").html("");
            renderListaAvion(mensaje.lista);
            $('#dtAvion').DataTable();
            $('.dataTables_length').addClass('bs-select');
            break;
        }
    }
});

function agregarAvion() {
    var select_tipoavion = $("#select_tipoavion option:selected").val();
    var select_ruta = $("#select_ruta option:selected").val();
    var avion = {tipoAvion_id: {id: select_tipoavion}, ruta_id: {id: select_ruta}};
    var message = {action: "agregarAvion", data: avion};
    websocket.send(JSON.stringify(message));
}

function agregarTipoavion() {
    var input_modelo = $("#input_modelo").val();
    var input_anyo = $("#input_anyo").val();
    var select_filas = $("#select_filas option:selected").val();
    var select_asientos_fila = $("#select_asientos_fila option:selected").val();
    if (input_modelo.length > 0 && input_anyo.length > 0) {
        var tipoavion = {modelo: input_modelo.replace(/\s+/g, "_"), anyo: input_anyo, filas: select_filas, asientos_fila: select_asientos_fila};
        var message = {action: "agregarTipoavion", data: tipoavion};
        websocket.send(JSON.stringify(message));
    } else {
        alert("Debe llenar los campos");
    }
}

function renderSelectTipoAvion(select, listado) {
    $(select).html("");
    listado.forEach((item) => {
        $(select).append("<option value=\"" + item.id + "\">" + item.modelo + "</option>");
    });
}

function renderSelectRuta(select, listado) {
    $(select).html("");
    listado.forEach((item) => {
        $(select).append("<option value=\"" + item.id + "\">" + item.origen.descripcion + "-->" + item.destino.descripcion + "</option>");
    });
}

function renderListaAvion(listado) {
    listado.forEach((item) => {
        var tr = $("<tr />");
        // ----Campos de tr----
        var td_id_avion = $("<td />");
        td_id_avion.html(item.id);
        var td_modelo_tipoavion = $("<td />");
        td_modelo_tipoavion.html(item.tipoAvion_id.modelo);
        var td_canAsientos_tipoavion = $("<td />");
        td_canAsientos_tipoavion.html(item.tipoAvion_id.filas * item.tipoAvion_id.asientos_fila);
        var td_codigos_ruta = $("<td />");
        td_codigos_ruta.html(item.ruta_id.origen.codigo + "-->" + item.ruta_id.destino.codigo);
        var td_duracion_ruta = $("<td />");
        td_duracion_ruta.html(item.ruta_id.duracion.hour + ":" + checkTime(item.ruta_id.duracion.minute));
        // ----Botón eliminar ----------
        var td_eliminar = $("<td />");
        $(td_eliminar).addClass("tdEliminar");
        td_eliminar.html('<i class="fa fa-trash"></i>');
        td_eliminar.on("click", () => {
            eliminarAvion(item);
        });
        // ----Botón editar ----------
        var td_editar = $("<td />");
        $(td_editar).addClass("tdEditar");
        td_editar.html('<i class="fa fa-edit"></i>');
        $(td_editar).attr("data-toggle", "modal");
        $(td_editar).attr("data-target", "#modal_editarAvion");
        td_editar.on("click", () => {
            sessionStorage.setItem("id_avionEditar", item.id.toString());
        });
        // ----Agregar campos a tr----
        tr.append(td_id_avion);
        tr.append(td_modelo_tipoavion);
        tr.append(td_canAsientos_tipoavion);
        tr.append(td_codigos_ruta);
        tr.append(td_duracion_ruta);
        tr.append(td_eliminar);
        tr.append(td_editar);
        $("#dtAvion > tbody").append(tr);
    });
}

function renderListaTipoAvion(listado) {
    listado.forEach((item) => {
        var tr = $("<tr />");
        // ----Campos de tr----
        var td_id_tipoavion = $("<td />");
        td_id_tipoavion.html(item.id);
        var td_anyo_tipoavion = $("<td />");
        td_anyo_tipoavion.html(item.anyo);
        var td_modelo_tipoavion = $("<td />");
        td_modelo_tipoavion.html(item.modelo);
        var td_filas_tipoavion = $("<td />");
        td_filas_tipoavion.html(item.filas);
        var td_asientosFila_tipoavion = $("<td />");
        td_asientosFila_tipoavion.html(item.asientos_fila);
        // ----Botón con el evento asociado ----------
        var td_eliminar = $("<td />");
        $(td_eliminar).addClass("tdEliminar");
        td_eliminar.html('<i class="fa fa-trash"></i>');
        td_eliminar.on("click", () => {
            eliminarTipoavion(item);
        });
        // ----Agregar campos a tr----
        tr.append(td_id_tipoavion);
        tr.append(td_anyo_tipoavion);
        tr.append(td_modelo_tipoavion);
        tr.append(td_filas_tipoavion);
        tr.append(td_asientosFila_tipoavion);
        tr.append(td_eliminar);
        $("#dtTipoAvion > tbody").append(tr);
    });
}

function editarAvion() {
    var id_avion = parseInt(sessionStorage.getItem("id_avionEditar"));
    var select_tipoavion_edit = $("#select_tipoavion_edit option:selected").val();
    var select_ruta_edit = $("#select_ruta_edit option:selected").val();
    var avion = {id: id_avion, tipoAvion_id: {id: select_tipoavion_edit}, ruta_id: {id: select_ruta_edit}};
    var message = {action: "editarAvion", data: avion};
    websocket.send(JSON.stringify(message));
    alert("Avion editado correctamente");
}

function eliminarTipoavion(tipo) {
    if (confirm("¿Está seguro de eliminar a " + tipo.id + "-" + tipo.modelo + "?")) {
        var eliminar = {id: tipo.id};
        var mensaje = {action: "eliminarTipoavion", data: eliminar};
        websocket.send(JSON.stringify(mensaje));
        alert("Eliminado con éxito");
    } else {
        return;
    }
}

function eliminarAvion(avion) {
    if (confirm("¿Está seguro de eliminar a " + avion.id + "-" + avion.tipoAvion_id.modelo + "-" + avion.ruta_id.origen.codigo + "->" + avion.ruta_id.destino.codigo + " ?")) {
        var eliminar = {id: avion.id};
        var mensaje = {action: "eliminarAvion", data: eliminar};
        websocket.send(JSON.stringify(mensaje));
        alert("Eliminado con éxito");
    } else {
        return;
    }
}

function checkTime(i) {
    return (i < 10) ? "0" + i : i;
}

function loadEvents() {

}

//CODIGOS ENVIO MENSAJE: 
//  *agregarTipoavion

//CODIGOS RECEPCION MENSAJE:
//  1: enviarListaTipoAvion
//  2: enviarListaRuta
//  3: enviarListaAvion

