<%-- 
    Document   : flota
    Created on : 14 abr. 2021, 17:59:51
    Author     : Moi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/parciales/head.jsp" %>
        <title>Flota</title>
        <style>
            /*Generic*/
            .wrapper{
                margin: 60px auto;
                text-align: center;
            }
            h1{
                margin-bottom: 1.25em;
            }

            /*Specific styling*/
            #content{
                padding: 15px;
                border: solid 1px #eee;
                max-width: 660px;
                margin: auto;
                border-radius: 4px;
            }

        </style>
    </head>
    <body>
        <%@include file="/parciales/navbar.jsp" %>
        <div class="container">
            <h2>Gestión de Flota</h2>
            <br>
            <!-- Nav tabs -->
            <ul class="nav nav-tabs" role="tablist">
                <li class="nav-item">
                    <a class="nav-link active" data-toggle="tab" href="#tab_newPlaneType">Agregar Tipo Avión</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" data-toggle="tab" href="#tab_PlaneTypeList">Ver Tipo Avión</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" data-toggle="tab" href="#tab_newPlane">Agregar Avión</a>
                </li>
            </ul>

            <!-- Tab panes -->
            <div class="tab-content">
                <div id="tab_newPlaneType" class="container tab-pane active"><br>
                    <form class="form-horizontal">
                        <fieldset>

                            <!-- Text input-->
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="input_modelo">Modelo</label>  
                                <div class="col-md-4">
                                    <input id="input_modelo" name="input_modelo" type="text" placeholder="Modelo" class="form-control input-md" required="">

                                </div>
                            </div>

                            <!-- Text input-->
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="input_anyo">Año</label>  
                                <div class="col-md-2">
                                    <input id="input_anyo" name="input_anyo" type="number" placeholder="Año" class="form-control input-md" required="">

                                </div>
                            </div>

                            <!-- Select Basic -->
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="select_filas">No. Filas</label>
                                <div class="col-md-2">
                                    <select id="select_filas" name="select_filas" class="form-control">
                                        <option value="9">9</option>
                                        <option value="10">10</option>
                                        <option value="11">11</option>
                                        <option value="12">12</option>
                                        <option value="13">13</option>
                                        <option value="14">14</option>
                                        <option value="15">15</option>
                                        <option value="16">16</option>
                                        <option value="17">17</option>
                                        <option value="18">18</option>
                                        <option value="19">19</option>
                                        <option value="20">20</option>
                                        <option value="21">21</option>
                                        <option value="22">22</option>
                                        <option value="23">23</option>
                                        <option value="24">24</option>
                                        <option value="25">25</option>
                                        <option value="26">26</option>
                                        <option value="27">27</option>
                                        <option value="28">28</option>
                                        <option value="29">29</option>
                                        <option value="30">30</option>
                                    </select>
                                </div>
                            </div>

                            <!-- Select Basic -->
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="select_asientos_fila">No. Asientos por fila</label>
                                <div class="col-md-2">
                                    <select id="select_asientos_fila" name="select_asientos_fila" class="form-control">
                                        <option value="6">6</option>
                                        <option value="7">7</option>
                                        <option value="8">8</option>
                                        <option value="9">9</option>
                                    </select>
                                </div>
                            </div>

                            <!-- Button -->
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="btn_registrarTipoAvion"></label>
                                <div class="col-md-4">
                                    <button id="btn_registrarTipoAvion" name="btn_registrarTipoAvion" class="btn btn-primary" onclick="agregarTipoavion()">Registrar</button>
                                </div>
                            </div>

                        </fieldset>
                    </form>
                </div>
                <!-- Tab Listado Tipo Avión-->
                <div id="tab_PlaneTypeList" class="container tab-pane fade"><br>
                    <h3>Ver Tipos de Aviones</h3>
                    <div class="table-responsive">
                        <table id="dtTipoAvion" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    <th class="th-sm">Id

                                    </th>
                                    <th class="th-sm">Año

                                    </th>
                                    <th class="th-sm">Modelo

                                    </th>
                                    <th class="th-sm">No. Filas

                                    </th>
                                    <th class="th-sm">Asientos por Fila

                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <th>Id
                                    </th>
                                    <th>Año
                                    </th>
                                    <th>Modelo
                                    </th>
                                    <th>No. Filas
                                    </th>
                                    <th>Asientos por Fila
                                    </th>
                                </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
                <div id="tab_newPlane" class="container tab-pane fade"><br>
                    <form class="form-horizontal">
                        <fieldset>

                            <!-- Form Name -->
                            <h3>Agregar Nuevo Avión</h3>

                            <!-- Select Basic -->
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="select_tipoavion">Tipo de Avión</label>
                                <div class="col-md-5">
                                    <select id="select_tipoavion" name="select_tipoavion" class="form-control">
                                    </select>
                                </div>
                            </div>

                            <!-- Select Basic -->
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="select_ruta">Ruta Asignada</label>
                                <div class="col-md-5">
                                    <select id="select_ruta" name="select_ruta" class="form-control">
                                    </select>
                                </div>
                            </div>

                            <!-- Button -->
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="button_agregarAvion"></label>
                                <div class="col-md-4">
                                    <button id="button_agregarAvion" name="button_agregarAvion" class="btn btn-success">Agregar</button>
                                </div>
                            </div>

                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
        <%@include file="/parciales/footer.jsp" %>
    </body>
    <script>
        //Websocket tipoAvion
        const websocket = new WebSocket('ws://localhost:8080/Aerolinea/tipoavion');

        websocket.addEventListener('message', function (event) {
            var mensaje = JSON.parse(event.data);
            switch (mensaje.codigo) {
                case 1:
                {
                    $("#dtTipoAvion > tbody").html("");
                    renderListaTipoAvion(mensaje.lista);
                    renderSelectTipoAvion(mensaje.lista);
                    $('#dtTipoAvion').DataTable();
                    $('.dataTables_length').addClass('bs-select');
                    break;
                }
                case 2:
                {
                    renderSelectRuta(mensaje.lista);
                }
            }
        });

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

        function renderSelectTipoAvion(listado) {
            $("#select_tipoavion").html("");
            listado.forEach((item) => {
                $("#select_tipoavion").append("<option value=\"" + item.id + "\">" + item.modelo + "</option>");
            });
        }

        function renderSelectRuta(listado) {
            $("#select_ruta").html("");
            listado.forEach((item) => {
                $("#select_ruta").append("<option value=\"" + item.id + "\">" + item.origen.descripcion + "-->" + item.destino.descripcion + "</option>");
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
                // ----Agregar campos a tr----
                tr.append(td_id_tipoavion);
                tr.append(td_anyo_tipoavion);
                tr.append(td_modelo_tipoavion);
                tr.append(td_filas_tipoavion);
                tr.append(td_asientosFila_tipoavion);
                $("#dtTipoAvion > tbody").append(tr);
            });
        }

        //CODIGOS ENVIO MENSAJE: 
        //  *agregarTipoavion

        //CODIGOS RECEPCION MENSAJE:
        //  1: enviarListaTipoAvion
        //  2: enviarListaRuta
    </script>
</html>
