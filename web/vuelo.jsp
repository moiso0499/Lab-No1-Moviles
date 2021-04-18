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
        <script src="/Aerolinea/js/vuelo.js"></script>
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
                                    <th>Asientos
                                    </th>
                                    <th>
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
