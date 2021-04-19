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
        <script src="/Aerolinea/js/ver_vuelo.js"></script>
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

        <!-- Modal Seleccionar asientos-->
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
                        <button type="button" class="btn btn-info" id="btn_confirmar_compra" data-dismiss="modal" onclick="agregarCompra()">Confirmar Compra</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>

            </div>
        </div>

        <!-- Modal Confirmar Compra-->
        <div id="modal_confirm_book" class="modal fade" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Confirmar Compra</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>   
                    </div>
                    <div class="modal-body" id="modal-body-confirm-book">

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-info" data-dismiss="modal" onclick="guardarCompra()">Comprar</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>

            </div>
        </div>
        <%@include file="/parciales/footer.jsp" %>
    </body>
</html>
