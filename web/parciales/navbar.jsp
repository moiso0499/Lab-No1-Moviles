<%-- 
    Document   : navbar
    Created on : 3 abr. 2021, 20:13:49
    Author     : Moi
--%>

<nav class="navbar bg-dark navbar-dark navbar-expand-lg sticky-top" id="mainNav">
            <div class="container">
                <a href="index.jsp" class="navbar-left"><img id="navbarIcon"src="images/airplane-circle-icon.png"></a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item active" id="navbar_li_user">
                            <a class="nav-link js-scroll-trigger" href="#">Usuario</a>
                        </li>
                        <!-- Items navbar del admin -->
                        <li class="nav-item" id="navbar_li_planes">
                            <a class="nav-link js-scroll-trigger" href="flota.jsp">Aviones</a>
                        </li>
                        <li class="nav-item" id="navbar_li_locations">
                            <a class="nav-link js-scroll-trigger" href="ruta.jsp">Rutas</a>
                        </li>
                        <li class="nav-item" id="navbar_li_flights">
                            <a class="nav-link js-scroll-trigger" href="vuelo.jsp">Vuelos</a>
                        </li>
                        <!-- ********************** -->
                        <li class="nav-item" id="navbar_li_checkFlights">
                            <a class="nav-link js-scroll-trigger" href="ver_vuelo.jsp">Ver Vuelos</a>
                        </li>
                        <!-- Aquí debo agregar los tabs del usuario -->
                        <li class="nav-item" id="navbar_li_login">
                            <a class="nav-link js-scroll-trigger" href="login.jsp">Login</a>
                        </li>
                        <li class="nav-item" id="navbar_li_logout">
                            <a class="nav-link js-scroll-trigger" href="index.jsp" onClick="logoutOnClick()">Logout</a>
                        </li>
                    </ul>
                    <!--Aquí termina el ul-->
                </div>  
            </div>
            <!--Aquí termina el container-->
        </nav>
        <!--Aquí termina el nav-->