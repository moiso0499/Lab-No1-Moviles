<%-- 
    Document   : index
    Created on : 3 abr. 2021, 20:16:51
    Author     : Moi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/parciales/head.jsp" %>
        <script src="/Aerolinea/js/login.js"></script>
        <title>Inicio de sesión</title>
    </head>
    <body>
        <%@include file="/parciales/navbar.jsp" %>

        <div class="myForm">
            <h1 class="myTitle">Inicio de sesión</h1>
            <form>
                <div class="form-group">
                    <label form="id">Identificación</label>
                    <input type="number" class="form-control" id="id" placeholder="Identificación">
                </div>

                <div class="form-group">
                    <label form="password">Contraseña</label>
                    <input type="password" class="form-control" id="password" placeholder="Contraseña">
                </div>

                <button id="loginButton" type="button" class="btn btn-primary" onclick="onLogin()">Ingresar</button>
                <button id="registerButton" type="button" class="btn btn-success" onclick="registro()">Registrarse</button>
            </form>      
        </div>
        <%@include file="/parciales/footer.jsp" %>
    </body> 
</html>
