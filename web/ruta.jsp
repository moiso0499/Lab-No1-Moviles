<%-- 
    Document   : ruta
    Created on : 15 abr. 2021, 16:12:38
    Author     : Moi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/parciales/head.jsp" %>
        <title>Rutas</title>
        <script src="/Aerolinea/js/ruta.js"></script>
    </head>
    <body>
        <%@include file="/parciales/navbar.jsp" %>
        <div class="container">
            <h2>Gestión de Rutas</h2>
            <br>
            <!-- Nav tabs -->
            <ul class="nav nav-tabs" role="tablist">
                <li class="nav-item">
                    <a class="nav-link active" data-toggle="tab" href="#tab_newLocation">Agregar Ubicación</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" data-toggle="tab" href="#tab_newRoute">Agregar Ruta</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" data-toggle="tab" href="#tab_routeList">Ver Ruta</a>
                </li>
            </ul>

            <!-- Tab panes -->
            <div class="tab-content">
                <div id="tab_newLocation" class="container tab-pane active"><br>
                    <form class="form-horizontal">
                        <fieldset>

                            <!-- Form Name -->
                            <h3>Agregar Nueva Ubicación</h3>

                            <!-- Text input-->
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="input_codigo">Código</label>  
                                <div class="col-md-4">
                                    <input id="input_codigo" name="input_codigo" type="text" placeholder="Código" class="form-control input-md" required="">

                                </div>
                            </div>

                            <!-- Text input-->
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="input_descripcion">Descripción</label>  
                                <div class="col-md-8">
                                    <input id="input_descripcion" name="input_descripcion" type="text" placeholder="Descripción" class="form-control input-md" required="">

                                </div>
                            </div>

                            <!-- Button -->
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="button_agregarUbicacion"></label>
                                <div class="col-md-4">
                                    <button id="button_agregarUbicacion" name="button_agregarUbicacion" class="btn btn-success" onclick="agregarUbicacion()">Agregar</button>
                                </div>
                            </div>

                        </fieldset>
                    </form>

                </div>
                <!-- Tab Nueva Ruta-->
                <div id="tab_newRoute" class="container tab-pane fade"><br>
                    <form class="form-horizontal">
                        <fieldset>

                            <!-- Form Name -->
                            <h3>Agregar Ruta</h3>

                            <!-- Text input-->
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="input_duracionHora">Horas</label>  
                                <div class="col-md-2">
                                    <input id="input_duracionHora" name="input_duracionHora" type="number" min="0" max="23" step="1" placeholder="Horas" class="form-control input-md" required="">    
                                </div>
                                <label class="col-md-4 control-label" for="input_duracionMinuto">Minutos</label>  
                                <div class="col-md-2">
                                    <input id="input_duracionMinuto" name="input_duracionMinuto" type="number" min="0" max="59" step="1"placeholder="Minutos" class="form-control input-md" required="">    
                                </div>
                            </div>

                            <!-- Select Basic -->
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="select_origen">Origen</label>
                                <div class="col-md-4">
                                    <select id="select_origen" name="select_origen" class="form-control">
                                    </select>
                                </div>
                            </div>

                            <!-- Select Basic -->
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="select_destino">Destino</label>
                                <div class="col-md-4">
                                    <select id="select_destino" name="select_destino" class="form-control">
                                    </select>
                                </div>
                            </div>

                            <!-- Button -->
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="button_nuevaRuta"></label>
                                <div class="col-md-4">
                                    <button id="button_nuevaRuta" name="button_nuevaRuta" class="btn btn-success" onclick="agregarRuta()">Agregar</button>
                                </div>
                            </div>

                        </fieldset>
                    </form>
                </div>
                <div id="tab_routeList" class="container tab-pane fade"><br>
                    <h3>Ver Rutas</h3>
                    <div class="table-responsive">
                        <table id="dtRuta" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    <th class="th-sm">Id

                                    </th>
                                    <th class="th-sm">Duración

                                    </th>
                                    <th class="th-sm">Origen

                                    </th>
                                    <th class="th-sm">Destino

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
                                    <th>Duración
                                    </th>
                                    <th>Origen
                                    </th>
                                    <th>Destino
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

        <!-- Modal -->
        <div id="modal_editarRuta" class="modal fade" role="dialog">
            <div class="modal-dialog modal-lg">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title" id="modal-title_editarAvion">Editar Ruta</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal">
                            <fieldset>
                                <!-- Text input-->
                                <div class="form-group">
                                    <label class="col-md-4 control-label" for="input_duracionHora_edit">Horas</label>  
                                    <div class="col-md-2">
                                        <input id="input_duracionHora_edit" name="input_duracionHora_edit" type="number" min="0" max="23" step="1" placeholder="Horas" class="form-control input-md" required="">    
                                    </div>
                                    <label class="col-md-4 control-label" for="input_duracionMinuto_edit">Minutos</label>  
                                    <div class="col-md-2">
                                        <input id="input_duracionMinuto_edit" name="input_duracionMinuto_edit" type="number" min="0" max="59" step="1"placeholder="Minutos" class="form-control input-md" required="">    
                                    </div>
                                </div>

                                <!-- Select Basic -->
                                <div class="form-group">
                                    <label class="col-md-4 control-label" for="select_origen_edit">Origen</label>
                                    <div class="col-md-4">
                                        <select id="select_origen_edit" name="select_origen_edit" class="form-control">
                                        </select>
                                    </div>
                                </div>

                                <!-- Select Basic -->
                                <div class="form-group">
                                    <label class="col-md-4 control-label" for="select_destino_edit">Destino</label>
                                    <div class="col-md-4">
                                        <select id="select_destino_edit" name="select_destino_edit" class="form-control">
                                        </select>
                                    </div>
                                </div>

                            </fieldset>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-success" data-dismiss="modal" onclick="editarRuta()">Editar</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                    </div>
                </div>

            </div>
        </div>
        <%@include file="/parciales/footer.jsp" %>
    </body>
</html>
