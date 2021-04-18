/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
            var select_origen_edit = $("#select_origen_edit");
            var select_destino_edit = $("#select_destino_edit");
            renderSelectUbicacion(select_origen_edit, mensaje.lista);
            renderSelectUbicacion(select_destino_edit, mensaje.lista);
            break;
        }
        case 2:
        {
            $("#dtRuta > tbody").html("");
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
        // Boton editar ruta
        var td_editar = $("<td />");
        $(td_editar).addClass("tdEditar");
        td_editar.html('<i class="fa fa-edit"></i>');
        $(td_editar).attr("data-toggle", "modal");
        $(td_editar).attr("data-target", "#modal_editarRuta");
        td_editar.on("click", () => {
            sessionStorage.setItem("id_rutaEditar", item.id.toString());
        });
        // ----Agregar campos a tr----
        tr.append(td_id);
        tr.append(td_duracion);
        tr.append(td_origen);
        tr.append(td_destino);
        tr.append(td_editar);
        $("#dtRuta > tbody").append(tr);
    });
}

function checkTime(i) {
    return (i < 10) ? "0" + i : i;
}

function editarRuta(){
    var id_ruta = parseInt(sessionStorage.getItem("id_rutaEditar"));
    var input_duracionHora_edit = $("#input_duracionHora_edit").val();
    var input_duracionMinuto_edit = $("#input_duracionMinuto_edit").val();
    if (input_duracionHora_edit.length == 0 || input_duracionMinuto_edit.length == 0 || (input_duracionHora_edit == 0 && input_duracionMinuto_edit == 0)) {
        alert("Ingrese una duración válida");
        return;
    }
    var select_origen_edit = $("#select_origen_edit option:selected").val();
    var select_destino_edit = $("#select_destino_edit option:selected").val();
    if (select_origen_edit == select_destino_edit) {
        alert("Origen y destino deben ser distintos");
        return;
    }
    var input_Duracion = (input_duracionHora_edit + ":" + checkTime(input_duracionMinuto_edit) + ":00").toString();
    var ruta = {id: id_ruta, origen: {codigo: select_origen_edit}, destino: {codigo: select_destino_edit}};
    var message = {action: "editarRuta", data: ruta, duracion: input_Duracion};
    websocket.send(JSON.stringify(message));
    alert("Ruta editada correctamente");
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


